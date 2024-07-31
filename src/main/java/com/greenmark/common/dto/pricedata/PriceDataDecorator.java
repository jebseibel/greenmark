package com.greenmark.common.dto.pricedata;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Hashtable;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsDatafeed;
import com.greenmark.common.config.ConfigStrategy;
import com.greenmark.common.core.Timeslice;
import com.greenmark.common.dto.parameters.MovingAvgConfigParams;
import com.greenmark.utils.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PriceDataDecorator extends PriceData implements Serializable {
    public static final String CLASSNAME = "PriceDataDecorator";
    private static final long serialVersionUID = 1L;

    public PriceDataDecorator() {
        super();
    }

    public PriceDataDecorator(int[] chartPreferences, int[] isMovingAverageSMA, int startDateInt, int stopDateInt) {
        super(chartPreferences, isMovingAverageSMA, startDateInt, stopDateInt);
    }

    public PriceDataDecorator(MovingAvgConfigParams movingAvgConfig, int timeframe, String symbol) {
        super(movingAvgConfig, timeframe, symbol);
    }

    /**
     * This method uses the input timeslice as the last interperiod point on the array. It sets this object's currentInterperiodClose and currentInterperiodStochK member variables.
     * <p>
     * It will use all the present data from 0 to the last data point (super.currentGmDays - 1). Then it will append the interperiod slice at super.currentGmDays
     * <p>
     * NOTE: Interperiod data has not been tested for V7, but it worked in V6!
     *
     * @param interperiodSlice
     * @param debugOnForStock
     */
    public void CalcStochasticsInterperiod_Historical(Timeslice interperiodSlice, boolean debugOnForStock) {
        String methodname = "CalcStochasticsInterperiod_Historical";

        // Start at the beginning of the array (when you have enough data points for Stochastics to calculate
        // Go till you reach one point past the current datetime (which was set by the calling method before calling this method),
        // so that we can include the interperiod slice at the one point past.
        for (int i = st_k_period - 1; i <= super.currentGreenmanPeriod; i++) {
            float lowestPt = 100000F;
            float highestPt = 0.0F;

            float lastClose = super.close[i];
            // Looking for the lowest low and highest high here.

            // We've passed the last datapoint, add the interperiod data point to the end.
            if (i == super.currentGreenmanPeriod) {
                if (debugOnForStock)

                    log.debug(CLASSNAME + "." + methodname + " At the LAST DATA Point Time: [" + UTDatetime.toString(super.dateLDT[i - 1]) + "]   i-1:  [" + (i - 1)
                            + "}  Appending TimesliceInfo: " + interperiodSlice.toString());

                for (int j = 1; j < st_k_period; j++) // Skip the first point in the list (which is the interperiod point)
                { // This little loop goes from the current point backwards st_k_periods to find lowestPt or highestPt
                    if (lowestPt > super.low[i - j])
                        lowestPt = super.low[i - j];
                    if (highestPt < super.high[i - j])
                        highestPt = super.high[i - j];
                }

                if (debugOnForStock)

                    log.debug(CLASSNAME + "." + methodname + " lowestPt:  [" + lowestPt + "]   highestPt:  [" + highestPt + "]    Comparing against timeslice now.....");

                // Now compare with the interperiod slice
                if (lowestPt > interperiodSlice.getLow())
                    lowestPt = interperiodSlice.getLow();
                if (highestPt < interperiodSlice.getHigh())
                    highestPt = interperiodSlice.getHigh();

                if (debugOnForStock)

                    log.debug(CLASSNAME + "." + methodname + " lowestPt:  [" + lowestPt + "]   highestPt:  [" + highestPt + "]    Compared against timeslice");

                lastClose = interperiodSlice.getClose();
                currentInterperiodClose = lastClose;
            } else // This is not the last data point, thus it doesn't use the interperiodSlice.
            {
                // This little loop goes from the current point backwards st_k_periods to find lowestPt or highestPt
                for (int j = 0; j < st_k_period; j++) {
                    if (lowestPt > super.low[i - j])
                        lowestPt = super.low[i - j];
                    if (highestPt < super.high[i - j])
                        highestPt = super.high[i - j];
                }
            }

            if (highestPt != lowestPt)
                iperiod_stoch[i] = (float) ((100D * (double) (lastClose - lowestPt)) / (double) (highestPt - lowestPt));
            if (iperiod_stoch[i] < 0.0F)
                iperiod_stoch[i] = 0.0F;

            if (debugOnForStock)

                log.debug(CLASSNAME + "." + methodname + " ALL CALCULATED - INITIAL VAL (NOT FINAL %K):   Time: [" + UTDatetime.toString(super.dateLDT[i]) + "]   original stoch[i]:  ["
                        + stoch[i] + "]   interperiod stoch[i]:  [" + iperiod_stoch[i] + "]   lastClose:  [" + lastClose + "]   lowestPt:  [" + lowestPt + "]   highestPt:  [" + highestPt
                        + "]   super.currentGmDays:  [" + super.currentGreenmanPeriod + "]");
        } // End for each data point to calculate a stochastic for.

        if (debugOnForStock)
            log.debug(CLASSNAME + "." + methodname + " Calculating EMA values now.");

        CalculateEMA(iperiod_stoch_K, iperiod_stoch, st_d_period, true, false);
        CalculateEMA(iperiod_stoch_D, iperiod_stoch_K, st_d_period, true, false);

        currentInterperiodStochK = iperiod_stoch_K[super.currentGreenmanPeriod];
        if (debugOnForStock) {

            log.debug(CLASSNAME + "." + methodname + " currentInterperiodStochK: [" + currentInterperiodStochK + "]");

            log.debug(" --------------- SLOW K ------------------");
            for (int i = st_k_period + 1; i <= super.currentGreenmanPeriod; i++)

                log.debug(CLASSNAME + "." + methodname + " Stoch K   Time: [" + UTDatetime.toString(super.dateLDT[i]) + "]   stochK[i]:  [" + stoch_K[i] + "]"
                        + "]   Interperiod stochK[i]:  [" + iperiod_stoch_K[i] + "]");


            log.debug(" --------------- SLOW D ------------------");
            for (int i = st_k_period + 1; i <= super.currentGreenmanPeriod; i++)

                log.debug(CLASSNAME + "." + methodname + " Stoch D   Time: [" + UTDatetime.toString(super.dateLDT[i]) + "]   stochD[i]:  [" + stoch_D[i] + "]");
        }
    }

    // This method assumes that super.currentGmDays has already been set to the correct date for the current Datetime.
    // Then it sums from the current Datetime back to the target datetime.
    public final Timeslice sumHistoricalInterperiodData(UTCalendarTime backToDatetime) {
        String methodname = "sumHistoricalInterperiodData";

        float dailyOpen = 0f;
        float dailyClose = super.close[super.currentGreenmanPeriod - 1];
        float dailyHigh = 0f;
        float dailyLow = 10000f;

        Timeslice returnTimeslice;

        // First, determine if all our UTCalendarTime comparison functions will care about hours and minutes.
        // This method is used on 1 minute data, so we always care.
        boolean considerHoursAndMinutes = true;

        log.debug(CLASSNAME + "." + methodname + " backToDatetime: [" + backToDatetime.formatDateTimeDisplay() + "]   super.currentGmDays - 1:  [" + (super.currentGreenmanPeriod - 1) + "]");

        try {
            int i = super.currentGreenmanPeriod - 1;
            UTCalendarTime sliceCal = new UTCalendarTime(dateString[i]);


            log.debug(CLASSNAME + "." + methodname + " First sliceCal: [" + sliceCal.formatDateTimeDisplay() + "]   dateGM[super.currentGmDays - 1]:  [" + dateString[i] + "]");

            // Start at the most recent 60 Min. point and work backwords. Most you'll ever need is 9 timeperiods.
            while (backToDatetime.isBeforeInCalendarDateTime(sliceCal, considerHoursAndMinutes)) {
                // This is a timeslice for today.
                if (super.high[i] > dailyHigh)
                    dailyHigh = super.high[i];

                if (super.low[i] < dailyLow)
                    dailyLow = super.low[i];


                log.debug(CLASSNAME + "." + methodname + " Time: [" + dateString[i] + "]  LDT: [" + UTDatetime.toString(dateLDT[i]) + "]  numdate: [" + numdate[i] + "]  open[i]:  ["
                        + super.open[i] + "]  close[i]:  [" + super.close[i] + "]  stoch[i]:  [" + stoch[i] + "]  high[i]:  [" + super.high[i] + "]  low[i]:  [" + super.low[i]
                        + "]  super.currentGmDays:  [" + super.currentGreenmanPeriod + "]");

                i--;
                sliceCal = new UTCalendarTime(dateString[i]);
            } // End while

            UTCalendarTime debugSliceCal = new UTCalendarTime(dateString[super.currentGreenmanPeriod - 1]);

            log.debug(
                    CLASSNAME + "." + methodname + " From Datetime: [" + debugSliceCal.formatDateTimeDisplay() + "]" + "   To Date: [" + backToDatetime.formatDateTimeDisplay() + "]");

            dailyOpen = super.open[i + 1];

            Hashtable slicedata = new Hashtable();
            slicedata.put(Timeslice.DATE, backToDatetime.formatDateTimeDisplay());
            slicedata.put(Timeslice.HIGH, Float.toString(dailyHigh));
            slicedata.put(Timeslice.LOW, Float.toString(dailyLow));
            slicedata.put(Timeslice.OPEN, Float.toString(dailyOpen));
            slicedata.put(Timeslice.CLOSE, Float.toString(dailyClose));

            returnTimeslice = new Timeslice(slicedata);


            log.debug(CLASSNAME + "." + methodname + " Summary: [" + returnTimeslice + "]");
        } catch (Exception ex) {

            log.debug(CLASSNAME + "." + methodname + "Exception ErrorMessage: [" + ex.getMessage() + "]");
            return null;
        }

        return (returnTimeslice);
    }

    public final Timeslice sumDailyInterperiodData(UTCalendar todaysCal) {
        String methodname = "sumDailyInterperiodData";

        float dailyOpen = 0f;
        float dailyClose = super.close[super.currentGreenmanPeriod - 1];
        float dailyHigh = 0f;
        float dailyLow = 10000f;

        Timeslice returnTimeslice;

        try {
            int i = super.currentGreenmanPeriod - 1;
            UTCalendar sliceCal = new UTCalendar(dateString[i]);

            // Start at the most recent 60 Min. point and work backwords. Most you'll ever need is 9 timeperiods.
            while (todaysCal.isEqualInCalendarDate(sliceCal)) {
                // This is a timeslice for today.
                if (super.high[i] > dailyHigh)
                    dailyHigh = super.high[i];

                if (super.low[i] < dailyLow)
                    dailyLow = super.low[i];


                log.debug(CLASSNAME + "." + methodname + " Time: [" + dateString[i] + "]  LDT: [" + UTDatetime.toString(dateLDT[i]) + "]  numdate: [" + numdate[i] + "]  open[i]:  ["
                        + super.open[i] + "]  close[i]:  [" + super.close[i] + "]  stoch[i]:  [" + stoch[i] + "]  high[i]:  [" + super.high[i] + "]  low[i]:  [" + super.low[i]
                        + "]  super.currentGmDays:  [" + super.currentGreenmanPeriod + "]");

                i--;
                sliceCal = new UTCalendar(dateString[i]);
            } // End while

            dailyOpen = super.open[i + 1];

            Hashtable slicedata = new Hashtable();
            slicedata.put(Timeslice.DATE, todaysCal.formatSimpleDateDisplay());
            slicedata.put(Timeslice.HIGH, Float.toString(dailyHigh));
            slicedata.put(Timeslice.LOW, Float.toString(dailyLow));
            slicedata.put(Timeslice.OPEN, Float.toString(dailyOpen));
            slicedata.put(Timeslice.CLOSE, Float.toString(dailyClose));

            returnTimeslice = new Timeslice(slicedata);


            log.debug(CLASSNAME + "." + methodname + " Summary: [" + returnTimeslice + "]");
        } catch (Exception ex) {
            log.error(CLASSNAME + "." + methodname + "Exception ErrorMessage: [" + ex.getMessage() + "]");
            return null;
        }

        return (returnTimeslice);
    }

    // ------------------------------------------------ CURRENT POINTER, DATE IN RANGE FUNCTIONS ---------------------------------------------
    // This method determines if the input currentDatetime is in the PriceData data set. It is used when the trading client executes and advances its current date, then checks to see if we
    // can reuse the data in this PriceData object, or if we have to retrieve and populate a new object instance.
    public int isDateInRange(LocalDateTime currentDatetime, String debugStockSymbol, int minNumDataPoints, int timeframe) {
        String methodname = "isDateInRange";


        log.debug(CLASSNAME + "." + methodname + "------ Entering " + debugStockSymbol + "  timeframe: " + UTLabels.getGmanLabelForGmanTimeframe(timeframe) + ", total_num_data_points: "
                + totalNumDataPoints);

        try {
            if (totalNumDataPoints < minNumDataPoints)
                return GmConstantsDatafeed.IN_DATETIME_INVALID_NO_DATA_YET;

            if (minNumDataPoints < ConfigStrategy.NUM_EXTRA_PTS_TO_RETRIEVE)
                minNumDataPoints = ConfigStrategy.NUM_EXTRA_PTS_TO_RETRIEVE;

            LocalDateTime tempStartDatetime = dateLDT[minNumDataPoints - ConfigStrategy.NUM_EXTRA_PTS_TO_RETRIEVE];
            LocalDateTime endDataDatetime = dateLDT[totalNumDataPoints - 1];


            log.debug(CLASSNAME + "." + methodname + " security:  " + debugStockSymbol + "  currentDatetime: " + UTDatetime.toString(currentDatetime) + "  startDataDatetime:  "
                    + UTDatetime.toString(tempStartDatetime) + "  endDataDatetime: " + UTDatetime.toString(endDataDatetime));

            if (currentDatetime.isBefore(tempStartDatetime)) {

                log.debug(CLASSNAME + "." + methodname + " returning IN_DATETIME_BEFORE_STOCKDATA_TIMES for symbol: " + debugStockSymbol + "  timeframe: "
                        + UTLabels.getGmanLabelForGmanTimeframe(timeframe) + "  currentDatetime: " + UTDatetime.toString(currentDatetime) + "  startDataDatetime:  "
                        + UTDatetime.toString(tempStartDatetime) + "  endDataDatetime: " + UTDatetime.toString(endDataDatetime));

                return GmConstantsDatafeed.IN_DATETIME_BEFORE_PRICEDATA_TIMES;
            }

            if (currentDatetime.isAfter(endDataDatetime)) {

                log.debug(CLASSNAME + "." + methodname + " returning IN_DATETIME_AFTER_STOCKDATA_TIMES for symbol: " + debugStockSymbol + "  timeframe: "
                        + UTLabels.getGmanLabelForGmanTimeframe(timeframe) + "  currentDatetime: " + UTDatetime.toString(currentDatetime) + "  startDataDatetime:  "
                        + UTDatetime.toString(tempStartDatetime) + "  endDataDatetime: " + UTDatetime.toString(endDataDatetime));
                return GmConstantsDatafeed.IN_DATETIME_AFTER_PRICEDATA_TIMES;
            }
        } catch (Exception ex) {
            log.error(CLASSNAME + "." + methodname + " Exception security: " + debugStockSymbol + "   currentDatetime: [" + UTDatetime.toString(currentDatetime) + "]    ErrorMessage: ["
                    + ex.getMessage() + "]");
            return (super.currentGreenmanPeriod);
        }

        return GmConstantsDatafeed.IN_DATETIME_WITHIN_PRICEDATA_TIMES; // Return WITHIN by default, let an exception handle it later if we're wrong.
    }

    public int calcPointerIndex(LocalDateTime datetimeForIndexSearch, String timeframe) {
        String methodname = "calcPointerIndex";

        int pointerIndex = searchGMDateByDate(datetimeForIndexSearch, false, timeframe);

        log.debug(CLASSNAME + "." + methodname + " found pointerIndex: " + pointerIndex + ", for: " + UTDatetime.toString(datetimeForIndexSearch));

        if (pointerIndex == -1) {

            log.debug(CLASSNAME + "." + methodname + "ERROR: Could not find pointer_index from GM Date array! at: " + UTDatetime.toString(datetimeForIndexSearch));
        }

        return pointerIndex;
    }

    protected int searchGMDateByDate(LocalDateTime datetimeForIndexSearch, boolean displayAllDates, String timeframe) {
        String methodname = "searchGMDateByDate";

        int dateIndex = 0;
        if (displayAllDates) {
            log.debug(CLASSNAME + "." + methodname + " FIRST ELEMENT: dateGM[" + dateIndex + "]: " + dateString[dateIndex] + ", open: " + open[dateIndex] + ", dateInt: "
                    + dateEpochSeconds[dateIndex] + ", dateIndex: " + dateIndex + ", datetimeForIndexSearch: " + UTDatetime.toDbString(datetimeForIndexSearch));

            dateIndex = dateString.length - 1;
            log.debug(CLASSNAME + "." + methodname + " LAST ELEMENT: dateGM[" + dateIndex + "]: " + dateString[dateIndex] + ", open: " + open[dateIndex] + ", dateInt: "
                    + dateEpochSeconds[dateIndex] + ", dateIndex: " + dateIndex + ", datetimeForIndexSearch: " + UTDatetime.toDbString(datetimeForIndexSearch));
        }

        // For daily, we have to remove hour, minute infoFrame before making the compare.
        if (GmConstants.CHARTS_DAILY.equals(timeframe)) {
            LocalDateTime datetimeForSearch = datetimeForIndexSearch;
            datetimeForSearch = datetimeForSearch.withHour(0);

            for (dateIndex = 0; dateIndex < dateLDT.length; dateIndex++) {
                if (dateLDT[dateIndex] == null || dateLDT[dateIndex].equals("")) {
                    if (displayAllDates) {
                        log.debug(CLASSNAME + "." + methodname + " REACHED END OF ARRAY dateLDT[" + dateIndex + "is null: " + dateString[dateIndex - 1] + ", open: " + open[dateIndex - 1]
                                + ", dateInt: " + dateEpochSeconds[dateIndex - 1] + ", dateIndex: " + (dateIndex - 1) + ", datetimeForIndexSearch: " + UTDatetime.toDbString(datetimeForIndexSearch));
                    }
                    break;
                }

                LocalDateTime gmDatetimeLDT = dateLDT[dateIndex];
                gmDatetimeLDT = gmDatetimeLDT.withHour(0);

                if (displayAllDates)
                    log.debug(CLASSNAME + "." + methodname + " dateGM[" + dateIndex + "]: " + UTDatetime.toDbString(gmDatetimeLDT) + ", open: " + open[dateIndex]
                            + ", dateInt: " + dateEpochSeconds[dateIndex] + ", dateIndex: " + dateIndex + ", datetimeForIndexSearch: " + UTDatetime.toDbString(datetimeForSearch));

                if (gmDatetimeLDT.isEqual(datetimeForSearch) || gmDatetimeLDT.isAfter(datetimeForSearch)) {
                    if (displayAllDates)
                        log.debug(CLASSNAME + "." + methodname + " FOUND dateGM[" + dateIndex + "]: " + UTDatetime.toDbString(gmDatetimeLDT) + ", open: " + open[dateIndex]
                                + ", dateInt: " + dateEpochSeconds[dateIndex] + ", dateIndex: " + dateIndex + ", datetimeForIndexSearch: " + UTDatetime.toDbString(datetimeForSearch));

                    return dateIndex;
                }
            }
        } else {
            for (dateIndex = 0; dateIndex < dateLDT.length; dateIndex++) {

                if (dateLDT[dateIndex] == null || dateLDT[dateIndex].equals("")) {
                    if (displayAllDates) {
                        log.debug(CLASSNAME + "." + methodname + " REACHED END OF ARRAY dateLDT[" + dateIndex + "is null: " + dateString[dateIndex - 1] + ", open: " + open[dateIndex - 1]
                                + ", dateInt: " + dateEpochSeconds[dateIndex - 1] + ", dateIndex: " + (dateIndex - 1) + ", datetimeForIndexSearch: " + UTDatetime.toDbString(datetimeForIndexSearch));
                    }
                    break;
                }

                if (displayAllDates)
                    log.debug(CLASSNAME + "." + methodname + " dateGM[" + dateIndex + "]: " + dateString[dateIndex] + ", dateLDT: " + dateLDT[dateIndex] + ", open: "
                            + open[dateIndex] + ", dateInt: " + dateEpochSeconds[dateIndex] + ", dateIndex: " + dateIndex + ", datetimeForIndexSearch: "
                            + UTDatetime.toDbString(datetimeForIndexSearch));

                if (dateLDT[dateIndex].equals(datetimeForIndexSearch) || dateLDT[dateIndex].isAfter(datetimeForIndexSearch)) {

                    if (displayAllDates)
                        log.debug(CLASSNAME + "." + methodname + " FOUND dateGM[" + dateIndex + "]: " + dateString[dateIndex] + ", open: " + open[dateIndex] + ", dateInt: "
                                + dateEpochSeconds[dateIndex] + ", dateIndex: " + dateIndex + ", datetimeForIndexSearch: " + UTDatetime.toDbString(datetimeForIndexSearch));

                    return dateIndex;
                }

                if (dateLDT[dateIndex] == null || dateLDT[dateIndex].equals("")) {
                    if (displayAllDates) {
                        log.debug(CLASSNAME + "." + methodname + " FOUND previous dateGM: " + dateString[dateIndex - 1] + ", open: " + open[dateIndex - 1] + ", dateInt: "
                                + dateEpochSeconds[dateIndex - 1] + ", dateIndex: " + (dateIndex - 1) + ", datetimeForIndexSearch: " + UTDatetime.toDbString(datetimeForIndexSearch));
                        log.warn(CLASSNAME + "." + methodname + " FOUND dateGM[" + dateIndex + "]: " + dateString[dateIndex] + ", open: " + open[dateIndex]
                                + ", dateInt: " + dateEpochSeconds[dateIndex] + ", dateIndex: " + dateIndex + ", datetimeForIndexSearch: " + UTDatetime.toDbString(datetimeForIndexSearch));
                    }
                    break;
                }
            }
        }

        return -1;
    }

    public String getChartDateString(int dataIndex) {
        String methodname = "getChartDateString";

        String[] dateArray = dateString[dataIndex].split(" ");
        String[] dayYearArray = dateArray[0].split("-");
        String secondsTruncated = dateArray[1].substring(0, dateArray[1].lastIndexOf(":"));
        return dayYearArray[1] + "/" + dayYearArray[2] + "/" + dayYearArray[0] + " " + secondsTruncated;
    }

    // Array origin is zero, so use currentGmDays - 1
    public void replaceLastTimeslice(Timeslice newTimeslice) {
        String methodname = "replaceLastTimeslice";

        try {
            super.open[super.currentGreenmanPeriod - 1] = newTimeslice.getOpen();
            super.close[super.currentGreenmanPeriod - 1] = newTimeslice.getClose();
            super.high[super.currentGreenmanPeriod - 1] = newTimeslice.getHigh();
            super.low[super.currentGreenmanPeriod - 1] = newTimeslice.getLow();
            super.dateString[super.currentGreenmanPeriod - 1] = newTimeslice.getDatetime();
            LocalDateTime thisDate = UTDatetime.fromDbString(newTimeslice.getDatetime());
            super.dateLDT[super.currentGreenmanPeriod - 1] = thisDate;
            super.numdate[currentGreenmanPeriod - 1] = UTNumdate.fromLDT(thisDate);

        } catch (Exception ex) {
            log.error(CLASSNAME + ":" + methodname + "Exception ErrorMessage: [" + ex.getMessage() + "]");
        }
    }

    // Array origin is zero, so use currentGmDays - 1
    public void addTimeslice(Timeslice newTimeslice) {
        String methodname = "addTimeslice";

        try {
            super.open[super.currentGreenmanPeriod] = newTimeslice.getOpen();
            super.close[super.currentGreenmanPeriod] = newTimeslice.getClose();
            super.high[super.currentGreenmanPeriod] = newTimeslice.getHigh();
            super.low[super.currentGreenmanPeriod] = newTimeslice.getLow();
            super.dateString[super.currentGreenmanPeriod] = newTimeslice.getDatetime();

            LocalDateTime thisDate = UTDatetime.fromDbString(newTimeslice.getDatetime());
            super.dateLDT[super.currentGreenmanPeriod] = thisDate;
            super.numdate[super.currentGreenmanPeriod] = UTNumdate.fromLDT(thisDate);

            super.currentGreenmanPeriod++;
        } catch (Exception ex) {
            log.error(CLASSNAME + ":" + methodname + "Exception ErrorMessage: [" + ex.getMessage() + "]");
        }
    }

    public float getPreviousPriceForTodayOnly(int numMinutesAgo, boolean getLowestLow, UTCalendarTime todaysCal) {
        String methodname = "getPreviousPriceForTodayOnly";

        try {
            // First, find out how far we can look back.
            UTCalendarTime modifiedDatetime = new UTCalendarTime(todaysCal);
            modifiedDatetime.decrementMinute(numMinutesAgo);

            int lastPossibleTimePeriod = super.currentGreenmanPeriod - 1;


            log.debug(CLASSNAME + ":" + methodname + " search for last possible timeperiod starts at: [" + super.dateString[lastPossibleTimePeriod] + "]");

            UTCalendarTime dataDatetime = new UTCalendarTime(super.dateString[lastPossibleTimePeriod]);

            while (dataDatetime.isAfterInCalendarDateTime(modifiedDatetime, true)) {
                lastPossibleTimePeriod--;
                dataDatetime = new UTCalendarTime(super.dateString[lastPossibleTimePeriod]);
            }


            log.debug(CLASSNAME + ":" + methodname + " found the last possible timeperiod for computations: [" + super.dateString[lastPossibleTimePeriod] + "]");

            // Now find the highest high, or lowest low
            if (getLowestLow) {
                int index = lastPossibleTimePeriod;
                int lowestLowIndex = index;
                float lowestLow = super.low[index];
                while (index < super.currentGreenmanPeriod - 1) {
                    if (super.low[index] < lowestLow) {
                        lowestLow = super.low[index];
                        lowestLowIndex = index;
                    }
                    index++;
                }

                log.debug(CLASSNAME + ":" + methodname + " returning lowest low: [" + lowestLow + "]   for date-time: [" + super.dateString[lowestLowIndex] + "]   index:" + index
                        + " super.currentGmDays: " + super.currentGreenmanPeriod);

                return lowestLow;
            } else // Get highest high
            {
                int index = lastPossibleTimePeriod;
                int highestHighIndex = index;
                float highestHigh = super.high[index];
                while (index < super.currentGreenmanPeriod - 1) {
                    if (super.high[index] > highestHigh) {
                        highestHigh = super.high[index];
                        highestHighIndex = index;
                    }
                    index++;
                }

                log.debug(CLASSNAME + ":" + methodname + " returning highest high: [" + highestHigh + "]   for date-time: [" + super.dateString[highestHighIndex] + "]  index:" + index
                        + " super.currentGmDays: " + super.currentGreenmanPeriod);

                return highestHigh;
            }
        } catch (Exception e) {
            return super.close[super.currentGreenmanPeriod - 1];
        }
    }
}
