package com.greenmark.utils;

import com.greenmark.common.GmConstants;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTTimezone</p>
 * <p>Description: This utility class contains convenience methods for time zones.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class UTTimezone {
	private static final String CLASSNAME = "UTTimezone";

	public static final int timezoneHoursFromUTC(String configTimezone) {
		String methodname = "timezoneHoursFromUTC";

		if (configTimezone.equals(GmConstants.TIMEZONE_PST))
			return 8;
		if (configTimezone.equals(GmConstants.TIMEZONE_MST))
			return 7;
		if (configTimezone.equals(GmConstants.TIMEZONE_CST))
			return 6;
		if (configTimezone.equals(GmConstants.TIMEZONE_EST))
			return 5;

		return 0;
	}

}
