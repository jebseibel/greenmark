package com.greenmark.common.dto.pricedata;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsDatafeed;
import com.greenmark.common.core.Timeslice;
import com.greenmark.common.service.ApplicationDataContext;
import com.greenmark.utils.*;
import com.greenmark.utils.trace.Trace;
import lombok.extern.slf4j.Slf4j;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: PriceDataQuery</p>
 * <p>Description: This extension of PriceDataDtoDecorator includes the methods used to query and parse HLOC data into the PriceDataDto object.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
@Slf4j
public class PriceDataQuery extends PriceDataDtoDecorator implements Serializable {
	public static final String CLASSNAME = "PriceDataQuery";
	private static final long serialVersionUID = 1L;

	private static final int numValidColumnsInData = 5; // We only need the HLOC data and can skip volume. No volume means no trades occurred.

	public LocalDateTime lastRefreshedDatetime = null;

	public int pointer_index; // This is the currently selected timeperiod for the charts.

	// These are used by PriceDataChart when user selects a chart symbol:
	protected String chartSecuritySymbol;

	protected String chartQueryStartDate;
	protected UTCalendarTime chartQueryStartDateCal;

	protected String chartQueryEndDate;
	protected UTCalendarTime chartQueryEndDateCal;

	public PriceDataQuery() {
		super();
	}

	public void parse(boolean priceHistoryInitialized, boolean forceNewData, String rawdatastream, int timeframe, String dataLoadAction, LocalDateTime beginDataDatetime, LocalDateTime currentDatetime,
			Trace trace) {
		String methodname = "parse";
		if (trace != null)
			trace.in(CLASSNAME, methodname);

		
			log.debug("----------------- PriceDataQuery.parse    priceHistoryInitialized [" + priceHistoryInitialized + "]    forceNewData [" + forceNewData + "]    timeframe ["
					+ UTLabels.getGmanLabelForGmanTimeframe(timeframe) + "]");

		int nlines = 0;
		int cnt = -1; // Start here because we increment immediately in loop and we want origin to be zero.

		// Historical datafeed should never try to add one data point to the end of the list.
		// It should always get the whole list, because we've already determined that we need more data.
		if (ApplicationDataContext.isHistoricalDatafeed())
			forceNewData = true;

		try {
			if (trace.isLevelSparse())
				log.debug("XXXXXXXXXXXXXXXXXXXX IN PriceDataQuery.parse currentGmDays: [" + currentGreenmanPeriod + "]");

			int arrayLength = GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE;

			if (trace.isLevelSparse())
				log.debug("XXXXXXXXXXXXXXXXXXXX IN PriceDataQuery.parse arrayLength: [" + arrayLength + "]");

			// Keep track of our last data point time, and match against incoming datetimes as we parse them.
			String lastDatapointDatetime = "";
			boolean matchedLastDatapointDatetime = false; // Set to true when we finally match against our last known point.

			// First, remember the datetime of the last data point we might currently have

			//////////////////////////// TODO: Don't use strings use LDTs
			if (priceHistoryInitialized && currentGreenmanPeriod != 0)
				lastDatapointDatetime = dateString[currentGreenmanPeriod - 1];

			if (trace.isLevelSparse())
				log.debug("XXXXXXXXXXXXXXXXXXXX IN PriceDataQuery.parse lastDatapointDatetime: " + lastDatapointDatetime + "at index: [" + (currentGreenmanPeriod - 1) + "]");

			////////////////////////////////////////////////////
			// slice up the returned data
			////////////////////////////////////////////////////
			StringTokenizer stkRow = new StringTokenizer(rawdatastream, ";");
			nlines = stkRow.countTokens();

			int numToSkip = 0;
			boolean skipLeadingData = true;

			boolean startOnInDatetime = false;
			LocalDateTime targetDatetime = null;

			String calculatedLoadAction = UTDatafeed.calcLoadStrategyForAction(dataLoadAction);
            // GreenmanUtils.DATA_LOAD_STRATEGY_STARTDATE_FORWARD
            skipLeadingData = calculatedLoadAction.equals(GmConstantsDatafeed.DATA_LOAD_STRATEGY_ENDDATE_BACKWARD);

			if (nlines > GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE) {

				// When getting 1Min data for historical scenarios (we get 1 day chunks), we might need 2 days if we're close to midnight. If so, don't start filling data until we get close to the
				// inputDatetime
				if (timeframe == GmConstants.TYPE_MINUTE01 && ApplicationDataContext.isHistoricalScenario()) {
					startOnInDatetime = true;
					long numMinutesForCalculations = UTTimeframe.calcNumMinutesForTimeframe(timeframe, GmConstants.MIN_PERIODS_FOR_DATA_CALCS);
					targetDatetime = beginDataDatetime.minusMinutes(numMinutesForCalculations + 5); // Add an extra 5 mins so we get more data than are needed for calcs.
				} else {
					numToSkip = nlines - GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE;

					if (!UTDatafeed.calcLoadStrategyForAction(dataLoadAction).equals(GmConstantsDatafeed.DATA_LOAD_STRATEGY_STARTDATE_FORWARD)) {
						if (trace.isLevelModel())
							log.info("ERROR in PriceDataQuery.parse(), Too many data points requested: " + nlines + ", for timeframe: "
									+ UTTimeframe.getChartTimeframeStringForGmanTimeframe(timeframe) + ", calculatedLoadAction: " + UTDatafeed.calcLoadStrategyForAction(dataLoadAction));
					}
				}
			}

			// If we're to forceNewData, then reset the currentGmDays to zero
			if (forceNewData)
				totalNumDataPoints = 0;

			while (stkRow.hasMoreTokens()) {

				if (skipLeadingData) {
					if (cnt < numToSkip) {
						String slice = stkRow.nextToken();
						cnt++;
						continue;
					}
					cnt++;
					if (cnt >= arrayLength + numToSkip)
						break;
				} else {
					cnt++;
					if (cnt >= arrayLength)
						break;
				}

				// get all the slice data
				String slice = stkRow.nextToken();
				String useDebugInfo = UTPropertyManager.getProperty("log_stock_debug");
				if (useDebugInfo.equals("1")) {
					if (trace.isLevelSparse())
						log.debug(slice);
				}

				StringTokenizer stkSlice = new StringTokenizer(slice, ",");

				// if the line has bad data - skip it
				if (stkSlice.countTokens() < numValidColumnsInData) { // We need at least the first 5 tokens for Date, HLOC. Ignore volume for now.

						log.info("Skipping a line of corrupt data, did not find " + numValidColumnsInData + " tokens, only found: " + stkSlice.countTokens() + ", timeframe: "
								+ UTLabels.getGmanLabelForGmanTimeframe(timeframe) + ", currentDatetime: " + UTDatetime.toString(beginDataDatetime) + ", dataLoadAction: " + dataLoadAction);
					continue;
				}

				String strDatetime = stkSlice.nextToken();
				String fixedDatetime = UTUtils.cutControlChars(strDatetime);
				LocalDateTime stkSliceLDT = UTDatetime.fromDbString(fixedDatetime);

				if (UTUtils.isNorE(fixedDatetime) || stkSliceLDT == null) {
					// We should quit the loop now and calc numDataPoints
					if (trace.isLevelModel())
						log.info(CLASSNAME + "." + methodname + "(), ERROR, CONTINUING? Found a null datetime, timeframe: " + UTLabels.getGmanLabelForGmanTimeframe(timeframe)
								+ ", currentDatetime: " + UTDatetime.toString(beginDataDatetime) + ", dataLoadAction: " + dataLoadAction);

				}

				// Check to see if we matched the parsed datetime with the current datetime now. If we have, this and all remaining data points will get added.
				if (lastDatapointDatetime.equals(fixedDatetime)) {
					matchedLastDatapointDatetime = true; // Now we'll get this (interperiod) and all the remaining points added from here on out.
					if (trace.isLevelSparse())
						log.debug("XXXXXXXXXXXXXXXXXXXX IN PriceDataQuery.parse  TIMES MATCHED ");
				}

				// Is the stkSliceLDT after the macD modified inDate time? Continue if not. (Don't fill data or advance currentGmDays
				// Don't advance cnt either, so we'll have to decrement it here.
				if (startOnInDatetime) {
					if (stkSliceLDT.isBefore(targetDatetime)) {
						cnt--;
						continue;
					}
				}

				// If we haven't initialized our data yet
				// OR we've been told to get all data
				// OR we matched our last data point and are appending all subsequent ones.
				if ((!priceHistoryInitialized) || (forceNewData) || (matchedLastDatapointDatetime)) {
					// ADD THE INCOMING DATA POINT TO OUR ARRAY
					float highCnt = Float.parseFloat(stkSlice.nextToken());
					float lowCnt = Float.parseFloat(stkSlice.nextToken());
					float openCnt = Float.parseFloat(stkSlice.nextToken());
					float closeCnt = Float.parseFloat(stkSlice.nextToken());

					// populate the data arrays
					dateString[totalNumDataPoints] = fixedDatetime;
					LocalDateTime theLDT = UTDatetime.fromDbString(fixedDatetime);
					dateLDT[totalNumDataPoints] = theLDT;
					numdate[totalNumDataPoints] = UTNumdate.fromLDT(theLDT);

					if (Trace.PRINT_DEBUG_PRICES_DATA)
						trace.addAnalysis("PriceDataQuery.parse(), dateGM[" + totalNumDataPoints + "]: " + dateString[totalNumDataPoints]);
					high[totalNumDataPoints] = highCnt;
					low[totalNumDataPoints] = lowCnt;
					open[totalNumDataPoints] = openCnt;
					close[totalNumDataPoints] = closeCnt;

					if (stkSlice.countTokens() > numValidColumnsInData)
						volume[totalNumDataPoints] = Float.parseFloat(stkSlice.nextToken());

//					if (timeframe == GmConstants.TYPE_DAILY) {
//						dateEpochSeconds[totalNumDataPoints] = UTCalendarTools.formatDateForIQChart(fixedDatetime);
//					} else {
//						dateEpochSeconds[totalNumDataPoints] = UTCalendarTools.formatDateTimeForIQChart(fixedDatetime);
//					}

					// increment currentGmDays here
					totalNumDataPoints++;
				}

				// increment the matchLastDatapointDatetime, so that we start adding points after we find the first match
			} // Endfor each data point


				log.info("--------                  IN         RAWDATA PARSE  Finished Prices Database Loop [" + cnt + "]  arrayLength - 1: [" + (arrayLength - 1) + "] currentGmDays: ["
						+ currentGreenmanPeriod + "] with totalNumDataPoints: " + totalNumDataPoints);

			if (Trace.PRINT_DEBUG_PRICES_DATA) {
				{
					log.info("----------------------------------- FINAL PROCESSED DATA STORED IN GREENMAN -------------------------------");
					for (int i = 0; i < totalNumDataPoints; i++)
						log.info("Data [" + i + "]  Date/Time: [" + dateString[i] + "]  LDT: [" + UTDatetime.toString(dateLDT[i]) + "]  numdate: [" + numdate[i] + "]  Open: [" + open[i]
								+ "]  High: [" + high[i] + "]  Low: [" + low[i] + "]  Close: [" + close[i] + "]  Volume: [" + volume[i] + "]");
				}
			}

		} catch (Exception e) {
			if (trace.isLevelModel())
				log.info(CLASSNAME + "." + methodname + " ERROR Parsing nlines [" + nlines + "] cnt [" + cnt + "]" + "Last Data Point on Greenman List     Time: ["
						+ dateString[currentGreenmanPeriod - 1] + "]  Open: [" + open[currentGreenmanPeriod - 1] + "] High: [" + high[currentGreenmanPeriod - 1] + "]  Low: ["
						+ low[currentGreenmanPeriod - 1] + "]   Close: [" + close[currentGreenmanPeriod - 1] + "] at: " + UTDatetime.toString(currentDatetime));
		}

		try {
			// Find and set the currentGreenmanPeriod NOW
			currentGreenmanPeriod = getDataIndexForDate(currentDatetime, timeframe, "");
			if (ApplicationDataContext.isChartsDatafeed() && ApplicationDataContext.isLiveDatafeed())
				currentGreenmanPeriod = totalNumDataPoints;
			pointer_index = currentGreenmanPeriod;
		} catch (Exception e) {
			if (trace.isLevelModel())
				log.info(CLASSNAME + "." + methodname + " ERROR Setting currentGreenmanPeriod! " + "Last Data Point on Greenman List     Time: [" + dateString[currentGreenmanPeriod - 1]
						+ "]  Open: [" + open[currentGreenmanPeriod - 1] + "] High: [" + high[currentGreenmanPeriod - 1] + "]  Low: [" + low[currentGreenmanPeriod - 1] + "]   Close: ["
						+ close[currentGreenmanPeriod - 1] + "] at: " + UTDatetime.toString(currentDatetime));
		}
		
    }

	public int getDataIndexForDate(LocalDateTime currentLDT, int timeframe, String debugStockSymbol) {
		String methodname = "getDataIndexForDate";

		int returnIndex = GmConstantsDatafeed.PRICE_QUERY_COULD_NOT_FIND_INDEX; // Init to -1 in case there's a problem

		try {

				log.info(CLASSNAME + "." + methodname + " symbol: [" + debugStockSymbol + "]   timeframe: [" + UTLabels.getGmanLabelForGmanTimeframe(timeframe) + "]   Datetime: ["
						+ UTDatetime.toString(currentLDT) + "]");

			int reqNumPtsOrStartIndex = GmConstants.MIN_PERIODS_FOR_DATA_CALCS;
			if (timeframe == GmConstants.TYPE_MINUTE01)
				reqNumPtsOrStartIndex = 0;

			// test that there is even enough data. If there isnt return that there is an error
			if (reqNumPtsOrStartIndex > super.totalNumDataPoints - 1) {

					log.info(CLASSNAME + "." + methodname + "Error security: " + debugStockSymbol + "   currentDatetime: [" + UTDatetime.toString(currentLDT) + ", reqNumPtsOrStartIndex: "
							+ reqNumPtsOrStartIndex + ", totalNumDataPoints - 1: " + (super.totalNumDataPoints - 1) + "]  there is not enough data");
				return returnIndex;
			}


				log.info(CLASSNAME + "." + methodname + " requiredNumPoints: [" + reqNumPtsOrStartIndex + "]");

			if (currentGreenmanPeriod == GmConstantsDatafeed.PRICE_QUERY_COULD_NOT_FIND_INDEX || currentGreenmanPeriod == 0) // First time thru, or we never could find the last matching timeslice
			{
				// If we're dealing with daily data, get the day before as the index that we're wanting to find, not the close
				// prices of today's information. That would be a day ahead of time.
				if (timeframe == GmConstants.TYPE_DAILY && ApplicationDataContext.isLiveScenario())
					currentLDT = currentLDT.minusDays(1);


					log.info(CLASSNAME + "." + methodname + " first time thru decremented Date: [" + UTDatetime.toString(currentLDT) + "]");

				returnIndex = searchDataIndexForDate(currentLDT, reqNumPtsOrStartIndex, super.totalNumDataPoints - 1, timeframe, debugStockSymbol);

				// Live can search from beginning, and then set the currentGreenmanPeriod:
				if (ApplicationDataContext.isLiveScenario() && returnIndex != GmConstantsDatafeed.PRICE_QUERY_COULD_NOT_FIND_INDEX) {
					currentGreenmanPeriod = returnIndex;
				}


					log.info(CLASSNAME + "." + methodname + " searchDataIndexForDate got the return Index the FIRST time:  [" + returnIndex + "]");

			} else // Let's shorten the searches by using the last index into the data
			{
				int endIndex = this.totalNumDataPoints - 1;

				if (ApplicationDataContext.isDebugOnForStock(debugStockSymbol)) {

						log.info(CLASSNAME + "." + methodname + " calculated endIndex  Security: " + debugStockSymbol + "   endIndex: " + endIndex + "  currentGreenmanPeriod: ["
								+ currentGreenmanPeriod + "] ");


						log.info(CLASSNAME + "." + methodname + "  calculated endIndex current date: [" + UTDatetime.toString(currentLDT) + "]   requiredNumPoints: [" + reqNumPtsOrStartIndex
								+ "]   startIndex: [" + reqNumPtsOrStartIndex + "]   dateGM[startIndex]: [" + UTDatetime.toString(dateLDT[reqNumPtsOrStartIndex]) + "]   endIndex: [" + endIndex
								+ "]   dateGM[endIndex]: [" + UTDatetime.toString(dateLDT[endIndex]) + "]   timeframe: [" + UTLabels.getGmanLabelForGmanTimeframe(timeframe) + "]   debugStockSymbol: ["
								+ debugStockSymbol + "]");
				}

				if (ApplicationDataContext.isHistoricalScenario()) {
					// If we're at the end of the array, return 0 so we get more data:
					if (currentGreenmanPeriod + 1 >= totalNumDataPoints) {

							log.info(CLASSNAME + "." + methodname + " currentGreenmanPeriod+1 > totalNumDataPoints,  calculated endIndex  Security: " + debugStockSymbol + "   endIndex: "
									+ endIndex + "  currentGreenmanPeriod: [" + currentGreenmanPeriod + "] ");
						return GmConstantsDatafeed.PRICE_QUERY_COULD_NOT_FIND_INDEX;
					}

					returnIndex = searchDataIndexForDate(currentLDT, currentGreenmanPeriod, this.totalNumDataPoints, timeframe, debugStockSymbol);
				} else { // Live can search from beginning, and then set the currentGreenmanPeriod:
					returnIndex = searchDataIndexForDate(currentLDT, 0, this.totalNumDataPoints, timeframe, debugStockSymbol);

					// This will get the last point for 1 minute
					if (timeframe == GmConstants.TYPE_MINUTE01 && this.currentGreenmanPeriod == this.totalNumDataPoints)
						returnIndex = this.currentGreenmanPeriod;


						log.info(CLASSNAME + "." + methodname + " " + UTLabels.getGmanLabelForGmanTimeframe(timeframe) + " got the return Index the SECOND TIME: [" + returnIndex + "]"
								+ "  currentGreenmanPeriod: [" + currentGreenmanPeriod + "] " + "  totalNumDataPoints: [" + this.totalNumDataPoints + "] ");

					if (returnIndex != GmConstantsDatafeed.PRICE_QUERY_COULD_NOT_FIND_INDEX)
						currentGreenmanPeriod = returnIndex;
				}

			}
		} catch (Exception ex) {

				log.error(CLASSNAME + "." + methodname + " Exception security: " + debugStockSymbol + "   currentDatetime: [" + UTDatetime.toString(currentLDT) + "]    ErrorMessage: ["
						+ ex.getMessage() + "]");
			currentGreenmanPeriod = -1;
			return GmConstantsDatafeed.PRICE_QUERY_COULD_NOT_FIND_INDEX;
		}


			log.info(CLASSNAME + "." + methodname + " EXITING  Security: " + debugStockSymbol + "   currentGreenmanPeriod: " + currentGreenmanPeriod + "   returnIndex: " + returnIndex
					+ "   Timeframe: " + UTLabels.getGmanLabelForGmanTimeframe(timeframe) + "  returning Date[" + returnIndex + "]: " + UTDatetime.toString(super.dateLDT[returnIndex])
					+ "  for input datetime: [" + UTDatetime.toString(currentLDT) + "] ");

		if (returnIndex == 0) {// The same error as not finding data

				log.info(CLASSNAME + "." + methodname + " returnIndex was ZERO  Security: " + debugStockSymbol + "  currentGreenmanPeriod: [" + currentGreenmanPeriod + "] ");

			returnIndex = GmConstantsDatafeed.PRICE_QUERY_COULD_NOT_FIND_INDEX;
		}

		return returnIndex;
	}

	// Returns zero if no date found
	public int searchDataIndexForDate(LocalDateTime currentLDT, int startIndex, int endIndexInput, int timeframe, String debugStockSymbol) {
		String methodname = "searchDataIndexForDate   ";

		int returnIndex = 0;

		currentLDT = currentLDT.withSecond(0).withNano(0);

		try {
			int endIndex = endIndexInput;
			if (endIndex > dateString.length - 1) // If they've calculated endIndex past array, don't do it
				endIndex = dateString.length - 1; // Put endIndex to end of array.

			// if (trace.isLevelModel())
			// log.info(CLASSNAME + "." + methodname + " currentDatetimeString: " + UTDatetime.toString(currentLDT) + " startIndex: [" + startIndex + "] Start Datetime[" + startIndex + "]: "
			// + UTDatetime.toString(super.dateLDT[startIndex]) + " endIndex: [" + endIndex + " End Datetime[" + endIndex + "]: " + UTDatetime.toString(super.dateLDT[endIndex])
			// + " endIndexInput: [" + endIndexInput);

			if (startIndex == endIndex) {
				if (ApplicationDataContext.isLiveScenario()) {
					log.info(CLASSNAME + "." + methodname + "  LIVE SCENARIO:   START and END index are the same. currentDatetimeString: " + UTDatetime.toString(currentLDT) + "  startIndex: ["
							+ startIndex + "]  Start Datetime[" + startIndex + "]: " + UTDatetime.toString(super.dateLDT[startIndex]) + "  endIndex: [" + endIndex + "     End Datetime[" + endIndex
							+ "]: " + UTDatetime.toString(super.dateLDT[endIndex]) + "  endIndexInput: [" + endIndexInput);
					return startIndex - 1;
				} else {
					// This occurs when we're at the end of our data and need to retrieve more.

						log.info(CLASSNAME + "." + methodname + "  START and END index are the same. currentDatetimeString: " + UTDatetime.toString(currentLDT) + "  startIndex: [" + startIndex
								+ "]  Start Datetime[" + startIndex + "]: " + UTDatetime.toString(super.dateLDT[startIndex]) + "  endIndex: [" + endIndex + "     End Datetime[" + endIndex + "]: "
								+ UTDatetime.toString(super.dateLDT[endIndex]) + "  endIndexInput: [" + endIndexInput);
					return GmConstantsDatafeed.PRICE_QUERY_COULD_NOT_FIND_INDEX;
				}
			}

			for (int arrayIndex = startIndex; arrayIndex <= endIndexInput && arrayIndex < GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE; arrayIndex++) {
				// Have we've reached the end of the data?
				if (ApplicationDataContext.isLiveScenario() && arrayIndex >= dateLDT.length) {
					return arrayIndex - 1; // We have to return the last data point of data we have when running realtime.
				}

				LocalDateTime stockDataLDT = dateLDT[arrayIndex];

				if (stockDataLDT == null) {
					// We've reached the end of our data, return arrayIndex - 1;

						log.info(CLASSNAME + "." + methodname + "  WE REACHED END OF DATA. currentDatetimeString: " + UTDatetime.toString(currentLDT) + "  startIndex: [" + startIndex
								+ "]  Start Datetime[" + startIndex + "]: " + UTDatetime.toString(super.dateLDT[startIndex]) + "  endIndex: [" + endIndex + "     End Datetime[" + endIndex + "]: "
								+ UTDatetime.toString(super.dateLDT[endIndex]) + "  endIndexInput: [" + endIndexInput);
					return arrayIndex - 1;
				}
				stockDataLDT = stockDataLDT.withSecond(0).withNano(0);


					log.info(CLASSNAME + "." + methodname + " Searching for index: [" + UTDatetime.toString(currentLDT) + "]  gmLDT: [" + UTDatetime.toString(stockDataLDT) + "] ");

				if (timeframe == GmConstants.TYPE_DAILY) {
					stockDataLDT = stockDataLDT.withHour(0).withMinute(0);
					currentLDT = currentLDT.withHour(0).withMinute(0);
				}

				// Sometimes there's holes in the data so .isEqual doesn't work. When our input date is after our current arrayIndex date, we return the previous datetime index.
				if (stockDataLDT.isAfter(currentLDT)) {

						log.info(CLASSNAME + "." + methodname + " RETURNING currentLDT: [" + UTDatetime.toString(currentLDT) + "]  gmLDT: [" + UTDatetime.toString(stockDataLDT) + "] ");
					return arrayIndex - 1;
				}

				if (stockDataLDT.isEqual(currentLDT)) {

						log.info(CLASSNAME + "." + methodname + " RETURNING currentLDT: [" + UTDatetime.toString(currentLDT) + "]  gmLDT: [" + UTDatetime.toString(stockDataLDT) + "] ");
					return arrayIndex;
				}
			}

			// WE SHOULD'VE FOUND THE DATA, RESPOND with a zero.

				log.info(CLASSNAME + "." + methodname + " COULD NOT FIND DATA, returning: " + returnIndex + " for  currentLDT: [" + UTDatetime.toString(currentLDT) + "]  Start Datetime["
						+ startIndex + "]: " + UTDatetime.toString(super.dateLDT[startIndex]) + ", End Datetime[" + endIndex + "]: " + UTDatetime.toString(super.dateLDT[endIndex]) + "] ");

			// displayDataPoints(trace);
		} catch (Exception ex) {
				log.error(
						CLASSNAME + "." + methodname + " Exception " + debugStockSymbol + "   currentDatetime: [" + UTDatetime.toString(currentLDT) + "]    ErrorMessage: [" + ex.getMessage() + "]");
			return (super.currentGreenmanPeriod);
		}

		return returnIndex;
	}

	// NOTE: The following JIRA was created to remove the encoding/decoding of urlParams and simply pass the correct information directly to this method.
	// GMT-231: Remove the coding/encoding layer and call query methods directly.
	public static String encodeUrlToLoadData(String symbol, String timeframe, int start, int stop) {
		String urlTimeframeString = "5";
		if (timeframe.equals("fiveminute"))
			urlTimeframeString = "5";
		else if (timeframe.equals("tenminute"))
			urlTimeframeString = "5";
		else if (timeframe.equals("fifteenminute"))
			urlTimeframeString = "15";
		else if (timeframe.equals("thirtyminute"))
			urlTimeframeString = "30";
		else if (timeframe.equals("sixtyminute"))
			urlTimeframeString = "60";
		else if (timeframe.equals("oneminute"))
			urlTimeframeString = "1";
		else if (timeframe.equals("daily"))
			urlTimeframeString = "D";

		String encodedUrl = "sym=" + symbol.toUpperCase() + "&time=" + urlTimeframeString + "&name=t&start=" + start + "&stop=" + stop;
		return encodedUrl;
	}

	// ------------------ The following methods are not used but remain in the event they are someday useful. ----------------------------------

	public static final Timeslice createTimesliceFromData(Vector vSlice) {
		Timeslice newTimeSlice = null;

		try {
			// set other slices
			Hashtable slicedata = new Hashtable();

			// drop the damn control character
			String strDatetime = (String) vSlice.get(0);
			String fixedDatetime = UTUtils.cutControlChars(strDatetime);

			/** @todo get Volume */
			slicedata.put(Timeslice.DATE, fixedDatetime);
			slicedata.put(Timeslice.HIGH, vSlice.get(1));
			slicedata.put(Timeslice.LOW, vSlice.get(2));
			slicedata.put(Timeslice.OPEN, vSlice.get(3));
			slicedata.put(Timeslice.CLOSE, vSlice.get(4));
			slicedata.put(Timeslice.VOLUME, vSlice.get(5));

			newTimeSlice = new Timeslice(slicedata);
		} catch (Exception e) {
			log.warn("Error in " + CLASSNAME + ":");
		}

		return newTimeSlice;
	}

	public static final boolean isNewDay(Timeslice newTimeslice) {

		boolean returnVal = false;

		try {
			// String currentHour = newTimeslice.getTimeHour();
			String currentDate = newTimeslice.getDate(); // MLS DEBUG ONLY, INCLUDE IN NEXT LINE

			if (lastDay.equals(currentDate)) {
				return false;
			} else {
				lastHour = newTimeslice.getTimeHour();
				lastDay = currentDate;
				return true;
			}

		} catch (Exception e) {
			System.out.println("Error in " + CLASSNAME + ":");
		}
		return returnVal;
	}

	public static final String getNextHourMark(Timeslice currentHourSlice) {
		String returnVal = "";

		try {
			// String currentTime = currentHourSlice.getTime(); // MLS DEBUG ONLY, INCLUDE IN NEXT LINE
			String currentHour = currentHourSlice.getTimeHour();
			int currentHourInt = Integer.parseInt(currentHour);
			// String currentMinute = currentHourSlice.getTimeMinute();

			// It's the next hour on the 45 minute mark.
			if (currentHourSlice.getTimeMinute().equals("45"))
				currentHourInt++;

			return currentHourSlice.getDate() + " " + currentHourInt + ":30:00";

		} catch (Exception e) {
			System.out.println("Error in " + CLASSNAME );
		}

		return returnVal;
	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public LocalDateTime getLastRefreshedDatetime() {
		return lastRefreshedDatetime;
	}

	public void setLastRefreshedDatetime(LocalDateTime lastRefreshedDatetime) {
		this.lastRefreshedDatetime = lastRefreshedDatetime;
	}

	public int getPointer_index() {
		return pointer_index;
	}

	public void setPointer_index(int pointer_index) {
		this.pointer_index = pointer_index;
	}
}
