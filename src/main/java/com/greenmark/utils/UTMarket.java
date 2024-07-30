package com.greenmark.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

import com.greenmark.common.config.Config;
import com.greenmark.common.config.ConfigApplication;
import com.greenmark.common.config.ConfigStrategy;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTMarket</p>
 * <p>Description: This utility class contains convenience methods for Exchanges (Markets).</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class UTMarket {

	public static final String CLASSNAME = "UTMarket";

	public static UTCalendarTime convert24HourMarketDaysToCalendarDays(UTCalendarTime startDate, int numDays, boolean moveForward) {
		UTCalendarTime currentDate = null;

		try {
			currentDate = new UTCalendarTime(startDate);

			for (int i = 0; i < numDays; i++) {

				if (moveForward) {
					currentDate.incrementDate(1); // A market day, increment and continue.
				} else {
					currentDate.decrementDate(1); // A market day, decrement and continue.
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in Market.convert24HourMarketDaysToCalendarDays(): message: " + e.getMessage());
		}

		return currentDate;
	}

	public static LocalDateTime incrementStartDateByNumDays(LocalDateTime startDate, int numDays, boolean moveForward) {
		LocalDateTime currentDate = null;

		try {
			currentDate = UTDatetime.clone(startDate);

			for (int i = 0; i < numDays; i++) {

				if (moveForward) {
					currentDate = currentDate.plusDays(1); // A market day, increment and continue.
				} else {
					currentDate = currentDate.minusDays(1); // A market day, decrement and continue.
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in convert24HourMarketDaysToCalendarDays " + e.getMessage());
		}

		return currentDate;
	}

	public static final String getMarketHolidays() {
		try {
			boolean firstTimeThru = true;

			String listofdays = Config.getValue(ConfigApplication.CONFIG_APPLICATION_HOLIDAY_LIST);
			StringBuffer stb = new StringBuffer(listofdays.length() * 2);

			StringTokenizer st = new StringTokenizer(listofdays, ";");
			while (st.hasMoreTokens()) {
				String token = st.nextToken();

				// make sure that there are no leading or trailing spaces
				String checkday = token.trim();

				// validate the format is correct
				checkday = UTDatetime.tryToFormatBadDate(checkday);

				// add it building list
				if (firstTimeThru == false)
					stb.append(";" + checkday);
				else
					stb.append(checkday);

				firstTimeThru = false;
			}

			// finally save it to the string we compare against
			return stb.toString();
		} catch (Exception e) {
			System.out.println("Exception in Market.initializeMarketHolidays " + e.getMessage());
		}

		return "";
	}

	public static boolean isWeekendDay(LocalDateTime datetime) {
		if (ConfigStrategy.is24HourExchange())
			return false;

		DayOfWeek mydow = datetime.getDayOfWeek();
		if ((mydow == DayOfWeek.SATURDAY) || (mydow == DayOfWeek.SUNDAY))
			return true;
		else
			return false;
	}
}
