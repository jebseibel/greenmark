package com.greenmark.utils;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsBroker;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTLabels</p>
 * <p>Description: Convenience class for formatting Greenman strings and labels.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class UTLabels {
	public static final String CLASSNAME = "UTLabels";

	public static final String STR_TYPE_MONTHLY = "MONTHLY";
	public static final String STR_TYPE_WEEKLY = "WEEKLY";
	public static final String STR_TYPE_DAILY = "DAILY";
	public static final String STR_TYPE_HOUR4 = "HOUR4";
	public static final String STR_TYPE_MINUTE60 = "MINUTE60";
	public static final String STR_TYPE_MINUTE15 = "MINUTE15";
	public static final String STR_TYPE_MINUTE05 = "MINUTE05";
	public static final String STR_TYPE_MINUTE01 = "MINUTE01";

	public static final String CHART_STR_TYPE_WEEKLY = "weekly";
	public static final String CHART_STR_TYPE_DAILY = "daily";
	public static final String CHART_STR_TYPE_HOUR4 = "fourhour";
	public static final String CHART_STR_TYPE_MINUTE60 = "sixtyminute";
	public static final String CHART_STR_TYPE_MINUTE15 = "fifteenminute";
	public static final String CHART_STR_TYPE_MINUTE05 = "fiveminute";
	public static final String CHART_STR_TYPE_MINUTE01 = "oneminute";

	public static final String STR_TYPE_BUY = "Buy ";
	public static final String STR_TYPE_SELL = "Sell ";
	public static final String STR_TYPE_COMPLETED = "Completed";

	/** Static object, Can't call */
	private UTLabels() {
	}

	public static final String getGmanLabelForGmanTimeframe(int timeframe) {
		switch (timeframe) {
		case GmConstants.TYPE_DAILY:
			return UTLabels.STR_TYPE_DAILY;
		case GmConstants.TYPE_HOUR4:
			return UTLabels.STR_TYPE_HOUR4;
		case GmConstants.TYPE_MINUTE60:
			return UTLabels.STR_TYPE_MINUTE60;
		case GmConstants.TYPE_MINUTE15:
			return UTLabels.STR_TYPE_MINUTE15;
		case GmConstants.TYPE_MINUTE05:
			return UTLabels.STR_TYPE_MINUTE05;
		case GmConstants.TYPE_MINUTE01:
			return UTLabels.STR_TYPE_MINUTE01;
		case GmConstants.TYPE_WEEKLY:
			return UTLabels.STR_TYPE_WEEKLY;
		case GmConstants.TYPE_MONTHLY:
			return UTLabels.STR_TYPE_MONTHLY;
		}

		return "";
	}

	public static final String getGmanTimeframeAbbrv(int timeframe) {
		switch (timeframe) {
		case GmConstants.TYPE_DAILY:
			return "DAY";
		case GmConstants.TYPE_HOUR4:
			return "4 HR";
		case GmConstants.TYPE_MINUTE60:
			return "60";
		case GmConstants.TYPE_MINUTE15:
			return "15";
		case GmConstants.TYPE_MINUTE05:
			return "5";
		case GmConstants.TYPE_MINUTE01:
			return "1";
		case GmConstants.TYPE_WEEKLY:
			return "WEEK";
		case GmConstants.TYPE_MONTHLY:
			return "MONTH";
		}

		return "";
	}

	public static final String getTimeframeDisplay(int timeframe) {
		switch (timeframe) {
		case GmConstants.TYPE_DAILY:
			return "Daily";
		case GmConstants.TYPE_HOUR4:
			return "4 Hour";
		case GmConstants.TYPE_MINUTE60:
			return "60 Min.";
		case GmConstants.TYPE_MINUTE15:
			return "15 Min.";
		case GmConstants.TYPE_MINUTE05:
			return "5 Min.";
		case GmConstants.TYPE_MINUTE01:
			return "1 Min.";
		case GmConstants.TYPE_WEEKLY:
			return "Weekly";
		case GmConstants.TYPE_MONTHLY:
			return "Monthly";
		}

		return "";
	}

	public static final String getLabelForBuySellLongShort(int buySellValue, int longShortValue) {
		return getLabelForBuySell(buySellValue) + getLabelForLongShort(longShortValue);
	}

	public static final String getLabelForBuySell(int value) {
		switch (value) {
		case GmConstantsBroker.TYPE_BUY:
			return UTLabels.STR_TYPE_BUY;
		case GmConstantsBroker.TYPE_SELL:
			return UTLabels.STR_TYPE_SELL;
		case GmConstantsBroker.TYPE_COMPLETED:
			return UTLabels.STR_TYPE_COMPLETED;
		}

		return "";
	}

	public static final String getLabelForLongShort(int value) {
		switch (value) {
		case GmConstants.LONG_SECURITY:
			return "Long ";
		case GmConstants.SHORT_SECURITY:
			return "Short ";
		}

		return "";
	}

	public static final int getTimeValueForLabels(String timevalue) {
		// order in the frequency they are called
		if (timevalue.equals(UTLabels.STR_TYPE_MINUTE01)) {
			return GmConstants.TYPE_MINUTE01;
		} else if (timevalue.equals(UTLabels.STR_TYPE_MINUTE05)) {
			return GmConstants.TYPE_MINUTE05;
		} else if (timevalue.equals(UTLabels.STR_TYPE_MINUTE15)) {
			return GmConstants.TYPE_MINUTE15;
		} else if (timevalue.equals(UTLabels.STR_TYPE_MINUTE60)) {
			return GmConstants.TYPE_MINUTE60;
		} else if (timevalue.equals(UTLabels.STR_TYPE_HOUR4)) {
			return GmConstants.TYPE_HOUR4;
		} else if (timevalue.equals(UTLabels.STR_TYPE_DAILY)) {
			return GmConstants.TYPE_DAILY;
		} else if (timevalue.equals(UTLabels.STR_TYPE_WEEKLY)) {
			return GmConstants.TYPE_WEEKLY;
		} else if (timevalue.equals(UTLabels.STR_TYPE_MONTHLY)) {
			return GmConstants.TYPE_MONTHLY;
		}

		return -1;
	}

}
