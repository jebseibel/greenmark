package com.greenmark.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsDatafeed;
import lombok.extern.slf4j.Slf4j;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTTimeframe</p>
 * <p>Description: This utility class contains convenience methods for the timeframes (buckets).</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
@Slf4j
public class UTTimeframe {
	private static final String CLASSNAME = "UTTimeframe";

	// ----------------------------------------- Functions that calculate intervals or numChunks for intervals --------------------------------------------------------

	static final int MINUTES_PER_HOUR = 60;
	static final int SECONDS_PER_MINUTE = 60;
	static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

	public static final String getDisplayStringForGmanTimeframe(int value) {
		switch (value) {
		case GmConstants.TYPE_MONTHLY:
			return "Monthly";
		case GmConstants.TYPE_WEEKLY:
			return "Weekly";
		case GmConstants.TYPE_DAILY:
			return "Daily";
		case GmConstants.TYPE_HOUR4:
			return "4 Hour";
		case GmConstants.TYPE_MINUTE60:
			return "60 Min";
		case GmConstants.TYPE_MINUTE15:
			return "15 Min";
		case GmConstants.TYPE_MINUTE05:
			return "5 Min";
		case GmConstants.TYPE_MINUTE01:
			return "1 Min";
		}

		return "";
	}

	public static final String getChartTimeframeStringForGmanTimeframe(int value) {
		switch (value) {
		case GmConstants.TYPE_WEEKLY:
			return UTLabels.CHART_STR_TYPE_WEEKLY;
		case GmConstants.TYPE_DAILY:
			return UTLabels.CHART_STR_TYPE_DAILY;
		case GmConstants.TYPE_HOUR4:
			return UTLabels.CHART_STR_TYPE_HOUR4;
		case GmConstants.TYPE_MINUTE60:
			return UTLabels.CHART_STR_TYPE_MINUTE60;
		case GmConstants.TYPE_MINUTE15:
			return UTLabels.CHART_STR_TYPE_MINUTE15;
		case GmConstants.TYPE_MINUTE05:
			return UTLabels.CHART_STR_TYPE_MINUTE05;
		case GmConstants.TYPE_MINUTE01:
			return UTLabels.CHART_STR_TYPE_MINUTE01;
		}

		return "";
	}

	public static final int getGmanTimeframeForChartTimeframeString(String timeframe) {
		if (UTLabels.CHART_STR_TYPE_WEEKLY.equals(timeframe))
			return GmConstants.TYPE_WEEKLY;
		if (UTLabels.CHART_STR_TYPE_DAILY.equals(timeframe))
			return GmConstants.TYPE_DAILY;
		if (UTLabels.CHART_STR_TYPE_MINUTE60.equals(timeframe))
			return GmConstants.TYPE_MINUTE60;
		if (UTLabels.CHART_STR_TYPE_MINUTE15.equals(timeframe))
			return GmConstants.TYPE_MINUTE15;
		if (UTLabels.CHART_STR_TYPE_MINUTE05.equals(timeframe))
			return GmConstants.TYPE_MINUTE05;
		if (UTLabels.CHART_STR_TYPE_MINUTE01.equals(timeframe))
			return GmConstants.TYPE_MINUTE01;

		return GmConstants.TYPE_DAILY;
	}

//	public static int getNumDaysForArraySizeForTimeframe(PdbSecurityDto thisSecurity, int arraySize, int timeframe) {
//		String methodname = "getNumDaysForTimeframe";
//
//		int returnVal = 0;
//
//		try {
//			switch (timeframe) {
//			case GmConstants.TYPE_DAILY:
//				return arraySize;
//			case GmConstants.TYPE_HOUR4:
//				if (thisSecurity.getType() == GmConstants.TYPE_STOCK)
//					return arraySize / GmConstantsDatafeed.NUM_DATA_POINTS_USMARKETS_DAY_HOUR4;
//				else
//					return arraySize / GmConstantsDatafeed.NUM_DATA_POINTS_24H_DAY_HOUR4;
//			case GmConstants.TYPE_MINUTE60:
//				if (thisSecurity.getType() == GmConstants.TYPE_STOCK)
//					return arraySize / GmConstantsDatafeed.NUM_DATA_POINTS_USMARKETS_DAY_MINUTE60;
//				else
//					return arraySize / GmConstantsDatafeed.NUM_DATA_POINTS_24H_DAY_MINUTE60;
//			case GmConstants.TYPE_MINUTE15:
//				if (thisSecurity.getType() == GmConstants.TYPE_STOCK)
//					return arraySize / GmConstantsDatafeed.NUM_DATA_POINTS_USMARKETS_DAY_MINUTE15;
//				else
//					return arraySize / GmConstantsDatafeed.NUM_DATA_POINTS_24H_DAY_MINUTE15;
//			case GmConstants.TYPE_MINUTE05:
//				if (thisSecurity.getType() == GmConstants.TYPE_STOCK)
//					return arraySize / GmConstantsDatafeed.NUM_DATA_POINTS_USMARKETS_DAY_MINUTE05;
//				else
//					return arraySize / GmConstantsDatafeed.NUM_DATA_POINTS_24H_DAY_MINUTE05;
//			case GmConstants.TYPE_MINUTE01:
//				if (thisSecurity.getType() == GmConstants.TYPE_STOCK)
//					return arraySize / GmConstantsDatafeed.NUM_DATA_POINTS_USMARKETS_DAY_MINUTE01;
//				else
//					return arraySize / GmConstantsDatafeed.NUM_DATA_POINTS_24H_DAY_MINUTE01;
//			case GmConstants.TYPE_WEEKLY:
//				return arraySize * 5;
//			case GmConstants.TYPE_MONTHLY:
//				return arraySize * 20;
//			}
//		} catch (Exception ex) {
//			log.error(CLASSNAME + "." + methodname + "============= ERROR timeframe:  [" + timeframe + "]   Exception Message:  [" + ex.getMessage() + "]");
//		}
//
//		return returnVal;
//	}

	public static Period getPeriodDiff(LocalDateTime dob, LocalDateTime now) {
		return Period.between(dob.toLocalDate(), now.toLocalDate());
	}

	public static long[] getTimeDiffs(LocalDateTime startDatetime, LocalDateTime endDatetime) {
		Duration duration = Duration.between(startDatetime, endDatetime);

		long seconds = duration.getSeconds();
		long hours = seconds / SECONDS_PER_HOUR;
		long minutes = seconds / SECONDS_PER_MINUTE;
		long secs = seconds;

		// System.out.println("UTDatetime.getTimeDiffs(), startDatetime: " + UTDatetime.getLocalDateTimeDbString(startDatetime) + ", endDatetime: " + UTDatetime.getLocalDateTimeDbString(endDatetime)
		// + ", calculated numMinutes: " + minutes);

		return new long[] { hours, minutes, secs };
	}

	public static boolean isDateWithinTimePeriod(int timeframe, LocalDateTime lastKnownDate, LocalDateTime currentDate) {
		long[] timeDiffs = getTimeDiffs(lastKnownDate, currentDate);
		long seconds = timeDiffs[2];

		switch (timeframe) {
		case (GmConstants.TYPE_WEEKLY): {
            return seconds < GmConstantsDatafeed.NUM_MINS_IN_WEEKLY * 60;
		}
		case (GmConstants.TYPE_DAILY): {
            return seconds < GmConstantsDatafeed.NUM_MINS_IN_DAILY * 60;
		}
		case (GmConstants.TYPE_HOUR4): {
            return seconds < GmConstantsDatafeed.NUM_MINS_IN_HOUR4 * 60;
		}
		case (GmConstants.TYPE_MINUTE60): {
            return seconds < GmConstantsDatafeed.NUM_MINS_IN_MIN60 * 60;
		}
		case (GmConstants.TYPE_MINUTE15): {
            return seconds < GmConstantsDatafeed.NUM_MINS_IN_MIN15 * 60;
		}
		case (GmConstants.TYPE_MINUTE05): {
            return seconds < GmConstantsDatafeed.NUM_MINS_IN_MIN05 * 60;
		}
		case (GmConstants.TYPE_MINUTE01): {
            return seconds < GmConstantsDatafeed.NUM_MINS_IN_MIN01 * 60;
		}
		}

		return false;
	}

	public static long calcNumDataPts(LocalDateTime startDatetime, LocalDateTime endDatetime, int timeframe) {
		// Find number of minutes difference
		long[] timeDiffs = getTimeDiffs(startDatetime, endDatetime);
		long numMinutes = timeDiffs[1];

		switch (timeframe) {
		case (GmConstants.TYPE_WEEKLY):
			return numMinutes / (24 * 60 * 7);
		case (GmConstants.TYPE_DAILY):
			return numMinutes / (24 * 60);
		case (GmConstants.TYPE_HOUR4):
			return numMinutes / 240;
		case (GmConstants.TYPE_MINUTE60):
			return numMinutes / 60;
		case (GmConstants.TYPE_MINUTE15):
			return numMinutes / 15;
		case (GmConstants.TYPE_MINUTE05):
			return numMinutes / 5;
		case (GmConstants.TYPE_MINUTE01):
			return numMinutes;
		}

		return 0; // This indicates input timeframe was bad.
	}

	public static int calcNumDaysToRetrievePerChunk(int timeframe) {

		int numDataPointsPerRequest = GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE;

		int numDaysPerRequestWeekly = numDataPointsPerRequest / GmConstantsDatafeed.NUM_DATA_POINTS_PER_DAY_WEEKLY;
		int numDaysPerRequestDaily = numDataPointsPerRequest / GmConstantsDatafeed.NUM_DATA_POINTS_PER_DAY_DAILY;
		int numDaysPerRequestHour4 = numDataPointsPerRequest / GmConstantsDatafeed.NUM_DATA_POINTS_24H_DAY_HOUR4;
		int numDaysPerRequestMin60 = numDataPointsPerRequest / GmConstantsDatafeed.NUM_DATA_POINTS_24H_DAY_MINUTE60;
		int numDaysPerRequestMin15 = numDataPointsPerRequest / GmConstantsDatafeed.NUM_DATA_POINTS_24H_DAY_MINUTE15;
		int numDaysPerRequestMin05 = (numDataPointsPerRequest / GmConstantsDatafeed.NUM_DATA_POINTS_24H_DAY_MINUTE05 < 1 ? 1
				: numDataPointsPerRequest / GmConstantsDatafeed.NUM_DATA_POINTS_24H_DAY_MINUTE05);
		int numDaysPerRequestMin01 = (numDataPointsPerRequest / GmConstantsDatafeed.NUM_DATA_POINTS_24H_DAY_MINUTE01 < 1 ? 1
				: numDataPointsPerRequest / GmConstantsDatafeed.NUM_DATA_POINTS_24H_DAY_MINUTE01);

		switch (timeframe) {
		case (GmConstants.TYPE_WEEKLY):
			return numDaysPerRequestWeekly - 10; // Don't get a whole 500 chunk for dailys, it causes problems paging correctly. Just get a little less.
		case (GmConstants.TYPE_DAILY):
			return numDaysPerRequestDaily - 10; // Don't get a whole 500 chunk for dailys, it causes problems paging correctly. Just get a little less.
		case (GmConstants.TYPE_HOUR4):
			return numDaysPerRequestHour4;
		case (GmConstants.TYPE_MINUTE60):
			return numDaysPerRequestMin60;
		case (GmConstants.TYPE_MINUTE15):
			return numDaysPerRequestMin15;
		case (GmConstants.TYPE_MINUTE05):
			return numDaysPerRequestMin05;
		case (GmConstants.TYPE_MINUTE01):
			return numDaysPerRequestMin01;
		}

		return 0; // This indicates input timeframe was bad.
	}

	// numDataPoints is the number of minutes you want for each timeframe.
	public static long calcNumMinutesForTimeframe(int timeframe, int numDataPoints) {
		switch (timeframe) {
		case (GmConstants.TYPE_WEEKLY):
			return (long) numDataPoints * GmConstantsDatafeed.NUM_MINS_IN_WEEKLY;
		case (GmConstants.TYPE_DAILY):
			return (long) numDataPoints * GmConstantsDatafeed.NUM_MINS_IN_DAILY;
		case (GmConstants.TYPE_HOUR4):
			return (long) numDataPoints * GmConstantsDatafeed.NUM_MINS_IN_HOUR4;
		case (GmConstants.TYPE_MINUTE60):
			return (long) numDataPoints * GmConstantsDatafeed.NUM_MINS_IN_MIN60;
		case (GmConstants.TYPE_MINUTE15):
			return (long) numDataPoints * GmConstantsDatafeed.NUM_MINS_IN_MIN15;
		case (GmConstants.TYPE_MINUTE05):
			return (long) numDataPoints * GmConstantsDatafeed.NUM_MINS_IN_MIN05;
		case (GmConstants.TYPE_MINUTE01):
			return numDataPoints * GmConstantsDatafeed.NUM_MINS_IN_MIN01;
		}

		return 0;
	}

	public static LocalDateTime addDateForward(LocalDateTime inDatetime, int timeframe, int numDataPointsPerChunk) {
		LocalDateTime returnLDT = null;

		switch (timeframe) {
		case (GmConstants.TYPE_WEEKLY):
			returnLDT = inDatetime.plusDays(numDataPointsPerChunk * 7L);
			return returnLDT;
		case (GmConstants.TYPE_DAILY):
			returnLDT = inDatetime.plusDays(numDataPointsPerChunk);
			return returnLDT;
		case (GmConstants.TYPE_HOUR4):
			returnLDT = inDatetime.plusMinutes(numDataPointsPerChunk * 240L);
			return returnLDT;
		case (GmConstants.TYPE_MINUTE60):
			returnLDT = inDatetime.plusMinutes(numDataPointsPerChunk * 60L);
			return returnLDT;
		case (GmConstants.TYPE_MINUTE15):
			returnLDT = inDatetime.plusMinutes(numDataPointsPerChunk * 15L);
			return returnLDT;
		case (GmConstants.TYPE_MINUTE05):
			returnLDT = inDatetime.plusMinutes(numDataPointsPerChunk * 5L);
			return returnLDT;
		case (GmConstants.TYPE_MINUTE01):
			returnLDT = inDatetime.plusMinutes(numDataPointsPerChunk);
			return returnLDT;
		}

		return returnLDT;
	}

	public static LocalDateTime incrementDatetimeForTimeframe(LocalDateTime inDatetime, int timeframe) {
		LocalDateTime returnLDT = null;

		switch (timeframe) {
		case (GmConstants.TYPE_WEEKLY):
			returnLDT = inDatetime.plusDays(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE / 7);
			return returnLDT;
		case (GmConstants.TYPE_DAILY):
			returnLDT = inDatetime.plusDays(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE);
			return returnLDT;
		case (GmConstants.TYPE_HOUR4):
			returnLDT = inDatetime.plusMinutes(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE * 240);
			return returnLDT;
		case (GmConstants.TYPE_MINUTE60):
			returnLDT = inDatetime.plusMinutes(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE * 60);
			return returnLDT;
		case (GmConstants.TYPE_MINUTE15):
			returnLDT = inDatetime.plusMinutes(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE * 15);
			return returnLDT;
		case (GmConstants.TYPE_MINUTE05):
			returnLDT = inDatetime.plusMinutes(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE * 5);
			return returnLDT;
		case (GmConstants.TYPE_MINUTE01):
			returnLDT = inDatetime.plusMinutes(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE);
			return returnLDT;
		}

		return returnLDT;
	}

	public static LocalDateTime decrementDatetimeForTimeframe(LocalDateTime inDatetime, int timeframe) {
		LocalDateTime returnLDT = null;

		switch (timeframe) {
		case (GmConstants.TYPE_WEEKLY):
			returnLDT = inDatetime.minusDays(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE / 7);
			return returnLDT;
		case (GmConstants.TYPE_DAILY):
			returnLDT = inDatetime.minusDays(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE);
			return returnLDT;
		case (GmConstants.TYPE_HOUR4):
			returnLDT = inDatetime.minusMinutes(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE * 240);
			return returnLDT;
		case (GmConstants.TYPE_MINUTE60):
			returnLDT = inDatetime.minusMinutes(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE * 60);
			return returnLDT;
		case (GmConstants.TYPE_MINUTE15):
			returnLDT = inDatetime.minusMinutes(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE * 15);
			return returnLDT;
		case (GmConstants.TYPE_MINUTE05):
			returnLDT = inDatetime.minusMinutes(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE * 5);
			return returnLDT;
		case (GmConstants.TYPE_MINUTE01):
			returnLDT = inDatetime.minusMinutes(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE);
			return returnLDT;
		}

		return returnLDT;
	}

	public static LocalDateTime adjustRealtimeLDTForTimeframe(LocalDateTime inDatetime, int timeframe) {
		LocalDateTime returnLDT = null;

		switch (timeframe) {
		case (GmConstants.TYPE_DAILY): {
			returnLDT = inDatetime.withHour(0).withMinute(0);
			return returnLDT;
		}
		case (GmConstants.TYPE_HOUR4): {
			long numHours = inDatetime.getHour();
			long modulus = numHours % 4;
			returnLDT = inDatetime.minusHours(modulus);
			returnLDT = returnLDT.withMinute(0);
			return returnLDT;
		}
		case (GmConstants.TYPE_MINUTE60): {
			returnLDT = inDatetime.withMinute(0);
			return returnLDT;
		}
		case (GmConstants.TYPE_MINUTE15): {
			long numberMinutes = inDatetime.getMinute();
			long modulus = numberMinutes % 15;
			returnLDT = inDatetime.minusMinutes(modulus);
			return returnLDT;
		}
		case (GmConstants.TYPE_MINUTE05): {
			long numberMinutes = inDatetime.getMinute();
			long modulus = numberMinutes % 5;
			returnLDT = inDatetime.minusMinutes(modulus);
			return returnLDT;
		}
		case (GmConstants.TYPE_MINUTE01):
			returnLDT = inDatetime.minusMinutes(1);
			return returnLDT;
		}

		return returnLDT;
	}
}
