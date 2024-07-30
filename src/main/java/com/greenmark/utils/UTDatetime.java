package com.greenmark.utils;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import com.greenmark.common.GmConstants;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTDatetime</p>
 * <p>Description: This class provides all the utilities to deal with dates.  It especially contains convenience methods to LocalDateTime to be converted to/from other types of date objects.
 *    It is very useful for removing occurences of the deprecated classes:  UTCalendar and UTCalendarTime.
 *    
 *    TODO: Its numdate methods should be moved to the newer UTNumdate class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class UTDatetime {
	private final String CLASS = "UTDatetime";

	public static final float HOURS_IN_YEAR = 365 * 24;

	private static final String DATE_FORMAT_DBDATE_ONLY_STRING = "yyyy-MM-dd";
	private static final DateTimeFormatter DATE_FORMAT_DBDATE_ONLY = DateTimeFormatter.ofPattern(DATE_FORMAT_DBDATE_ONLY_STRING);

	private static final String DATE_FORMAT_DBDATE_TIME_STRING = "yyyy-MM-dd HH:mm:ss";
	private static final DateTimeFormatter DATE_FORMAT_DBDATE_TIME = DateTimeFormatter.ofPattern(DATE_FORMAT_DBDATE_TIME_STRING);
	private static final SimpleDateFormat DATE_FORMAT_PRICES_DATA = new SimpleDateFormat(DATE_FORMAT_DBDATE_TIME_STRING);

	private static final String DATE_FORMAT_DATE_ONLY_STRING = "MM-d" + "d-yyyy";
	private static final DateTimeFormatter DATE_FORMAT_DATE_ONLY = DateTimeFormatter.ofPattern(DATE_FORMAT_DATE_ONLY_STRING);

	private static final String DATE_FORMAT_DATE_TIME_STRING = "MM-dd-yyyy HH:mm:ss";
	private static final DateTimeFormatter DATE_FORMAT_DATE_TIME = DateTimeFormatter.ofPattern(DATE_FORMAT_DATE_TIME_STRING);

	private static final String DATE_FORMAT_TIME_STRING = "HH:mm:ss";
	private static final DateTimeFormatter DATE_FORMAT_TIME = DateTimeFormatter.ofPattern(DATE_FORMAT_TIME_STRING);

	private static final String DATE_FORMAT_TIME_NOSECONDS_STRING = "HH:mm";
	private static final DateTimeFormatter DATE_FORMAT_TIME_NOSECONDS = DateTimeFormatter.ofPattern(DATE_FORMAT_TIME_NOSECONDS_STRING);

	private static final String DATE_FORMAT_FILENAME_STRING = "yyyy_MM_dd-HH-mm";
	private static final DateTimeFormatter DATE_FORMAT_FILENAME_TIME = DateTimeFormatter.ofPattern(DATE_FORMAT_FILENAME_STRING);

	private static final SimpleDateFormat DATE_FORMAT_LONG = new SimpleDateFormat("yyyyMMdd-HH-mm");
	private static final SimpleDateFormat DATE_FORMAT_YYYYMMDD_ONLY = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat DATE_FORMAT_EEE = new SimpleDateFormat("EEE, MMM d, yyyy");
	private static final SimpleDateFormat DATE_FORMAT_BASIC_YYYY_MM_DD = new SimpleDateFormat("yyyy_MM_dd");
	private static final SimpleDateFormat DATE_FORMAT_BASIC_YYYY_MM_DD_HH_MM = new SimpleDateFormat("yyyy_MM_dd-HH-mm");
	private static final SimpleDateFormat DATE_FORMAT_TIME_ONLY = new SimpleDateFormat("kk:mm:ss");
	private static final SimpleDateFormat DATE_FORMAT_MM_DD_ONLY = new SimpleDateFormat("MM-dd");
	private static final SimpleDateFormat DATE_FORMAT_HOUR_ONLY = new SimpleDateFormat("kk");

	private static final String DATE_FORMAT_BASIC_YMD_FORMAT_STRING = "yyyy-MM-dd";
	private static final SimpleDateFormat DATE_FORMAT_BASIC_YMD = new SimpleDateFormat(DATE_FORMAT_BASIC_YMD_FORMAT_STRING);
	private static final SimpleDateFormat DATE_FORMAT_YMD_M_FIRST = new SimpleDateFormat("MM-dd-yyyy");

	/////////////////////////////////////////////////
	// PUBLIC METHODS
	/////////////////////////////////////////////////

	// This should only be used for features where we need rightNow in the local timezone.
	// The historical and real-time scenarios, as well as the PdbUpdater always use UTC timezone
	// For example, filename paths
	public static LocalDateTime getNowLDT() {
		return LocalDateTime.now();
	}

	// ----------------------------------------- Epoch converters --------------------------------------------------------
	public static LocalDateTime fromEpoch(long epoch) {
		long epochMillis = epoch * 1000;
		LocalDateTime date = Instant.ofEpochMilli(epochMillis).atZone(ZoneOffset.UTC).toLocalDateTime();
		return date;
	}

	public static long toEpoch(LocalDateTime ldt) {
		return ldt.atZone(ZoneOffset.UTC).toEpochSecond();
	}

	// ----------------------------------------- LocalDate converters --------------------------------------------------------
	public static LocalDate fromDbStringToLDonly(String dbString) {
		if (dbString == null)
			return null;

		return LocalDate.parse(dbString, DATE_FORMAT_DBDATE_ONLY);
	}

	public static LocalDate fromDateToLDOnly(Date inDate) {
		if (inDate == null)
			return null;

		return inDate.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
	}

	public static LocalDate fromUTCalendarTimeLDonly(UTCalendarTime inCalendar) {
		if (inCalendar == null)
			return null;

		return inCalendar.getJavaDate().toInstant().atZone(ZoneOffset.UTC).toLocalDate();
	}

	// ----------------------------------------- LocalDateTime converters --------------------------------------------------------
	public static LocalDateTime fromDbString(String dbString) {
		if (dbString == null)
			return null;

		return LocalDateTime.parse(dbString, DATE_FORMAT_DBDATE_TIME);
	}

	public static LocalDateTime fromDbDateOnlyString(String dbString) {
		if (dbString == null)
			return null;

		return LocalDateTime.parse(dbString + " 00:00:00", DATE_FORMAT_DBDATE_TIME);
	}

	public static LocalDateTime fromDate(Date inDate) {
		if (inDate == null)
			return null;

		return inDate.toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();
	}

	public static LocalDateTime fromSqlDate(java.sql.Date inDate) {
		if (inDate == null)
			return null;

		Timestamp timestamp = new Timestamp(inDate.getTime());

		return timestamp.toLocalDateTime();
	}

	public static LocalDateTime fromUTCalendar(UTCalendar inCalendar) {
		if (inCalendar == null)
			return null;

		return fromDate(inCalendar.getJavaDate());
	}

	public static LocalDateTime fromUTCalendarTime(UTCalendarTime inCalendar) {
		if (inCalendar == null)
			return null;

		return fromDate(inCalendar.getJavaDate());
	}

	public static LocalDateTime clone(LocalDateTime inDatetime) {
		if (inDatetime == null)
			return null;

		return fromDbString(UTDatetime.toDbString(inDatetime));
	}

	public static LocalDate cloneToLD(LocalDateTime inDatetime) {
		if (inDatetime == null)
			return null;

		return fromDbStringToLDonly(UTDatetime.toDbDateOnlyString(inDatetime));
	}

	public static Date fromDateString(String dateString) {
		if (dateString == null || dateString.isEmpty())
			return null;

		return UTDatetime.toDate(fromLDTString(dateString));
	}

	public static LocalDateTime fromLDTString(String dateString) {
		if (dateString == null || dateString.isEmpty())
			return null;

		return LocalDateTime.parse(dateString, DATE_FORMAT_DBDATE_TIME);
	}

	public static String toString(LocalDateTime ldt) {
		if (ldt == null)
			return "null";
		return ldt.format(DATE_FORMAT_DATE_TIME);
	}

	public static String toTimeString(LocalDateTime ldt) {
		if (ldt == null)
			return "null";
		return ldt.format(DATE_FORMAT_TIME);
	}

	public static String toTimeNoSecondsString(LocalDateTime ldt) {
		if (ldt == null)
			return "null";
		return ldt.format(DATE_FORMAT_TIME_NOSECONDS);
	}

	public static String toDateOnlyString(LocalDateTime ldt) {
		if (ldt == null)
			return "null";
		return ldt.format(DATE_FORMAT_DATE_ONLY);
	}

	public static String toDbString(LocalDateTime ldt) {
		if (ldt == null)
			return "null";
		return ldt.format(DATE_FORMAT_DBDATE_TIME);
	}

	public static String toDbString(LocalDate ldt) {
		if (ldt == null)
			return "null";
		return ldt.format(DATE_FORMAT_DBDATE_TIME);
	}

	public static String toDbDateOnlyString(LocalDateTime ldt) {
		if (ldt == null)
			return "null";
		return ldt.format(DATE_FORMAT_DBDATE_ONLY);
	}

	public static String toFilenameString(LocalDateTime ldt) {
		if (ldt == null)
			return "null";
		return ldt.format(DATE_FORMAT_FILENAME_TIME);
	}

	public static Date toDate(LocalDateTime ldt) {
		if (ldt == null)
			return null;

		return java.util.Date.from(ldt.atZone(ZoneOffset.UTC).toInstant());
	}

	public static java.sql.Date toSqlDate(LocalDateTime ldt) {
		if (ldt == null)
			return null;

		return java.sql.Date.valueOf(ldt.toLocalDate());
	}

	public static UTCalendar toUTCalendar(LocalDateTime ldt) {
		if (ldt == null)
			return null;
		return new UTCalendar(toDate(ldt));
	}

	public static UTCalendarTime toUTCalendarTime(LocalDateTime ldt) {
		if (ldt == null)
			return null;

		Date returnVal = toDate(ldt);
		return new UTCalendarTime(returnVal);
	}

	/**
	 * Returns the current date in java SimpleDateFormat, format ("yyyy-MM-dd kk:mm:ss"). for example 2003-12-27-09:34:00
	 */
	public static final String getCurrentDatetimeForFilename() {
		java.util.Date today = new java.util.Date();
		String datenewformat = DATE_FORMAT_LONG.format(today);
		return datenewformat;
	}

	/**
	 * Returns the current date in java SimpleDateFormat, format ("yyyy-MM-dd"). for example 2003-12-27-09:34:00
	 */
	public static final String getDateAsOneForFilename(java.util.Date today) {
		String datenewformat = DATE_FORMAT_YYYYMMDD_ONLY.format(today);
		return datenewformat;
	}

	/**
	 * Returns the current date in java SimpleDateFormat, format ("yyyy_MM_dd"). for example 2003-12-27-09:34:00
	 */
	public static final String getCurrentDateForDisplay() {
		java.util.Date today = new java.util.Date();
		String datenewformat = DATE_FORMAT_EEE.format(today);
		return datenewformat;
	}

	/**
	 * Returns the current date in java SimpleDateFormat, format ("yyyy_MM_dd"). for example 2003-12-27-09:34:00
	 */
	public static final String getCurrentDateForFilename() {
		java.util.Date today = new java.util.Date();
		String datenewformat = DATE_FORMAT_BASIC_YYYY_MM_DD_HH_MM.format(today);
		return datenewformat;
	}

	public static final String getCurrentDateHourMinuteForFilename() {
		java.util.Date today = new java.util.Date();
		String datenewformat = DATE_FORMAT_BASIC_YYYY_MM_DD_HH_MM.format(today);
		return datenewformat;
	}

	/**
	 * Returns the current date in java SimpleDateFormat, format ("yyyy-MM-dd"). for example 2003-12-27. This format is consumable by the MS Sql database.
	 */
	public static final String getCurrentDate() {
		java.util.Date today = new java.util.Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(today);
		return strDate;
	}

	/**
	 * Switch java.util.Date to sql.Date
	 */
	public static final java.sql.Date getSqlDate(java.util.Date date) {
		java.sql.Date sqlnow = new java.sql.Date(date.getTime());
		return sqlnow;
	}

	/**
	 * Returns the current date in java sql format
	 */
	public static final java.sql.Date getSqlNow() {
		Calendar rightNow = Calendar.getInstance();
		java.sql.Date sqlnow = new java.sql.Date(rightNow.getTimeInMillis());
		return sqlnow;
	}

	/**
	 * Returns the current date in java SimpleDateFormat, format ("yyyy-MM-dd kk:mm:ss"). for example 2003-12-27-09:34:00 This format is consumable by the MS Sql database
	 */
	public static final String getCurrentDatetime() {
		java.util.Date today = new java.util.Date();
		return DATE_FORMAT_PRICES_DATA.format(today);
	}

	public static final String getPriceDbFormat(Date inDate) {
		return DATE_FORMAT_PRICES_DATA.format(inDate);
	}

	/**
	 * Returns the current date in java SimpleDateFormat, format ("yyyy-MM-dd"). for example 2003-12-27. This format is consumable by the MS Sql database.
	 */
	public static final String getHolidayFormat() {
		java.util.Date today = new java.util.Date();
		String strDate = DATE_FORMAT_YMD_M_FIRST.format(today);
		return strDate;
	}

	/**
	 * Returns the current date in java SimpleDateFormat, format ("yyyy-MM-dd"). for example 2003-12-27. This format is consumable by the MS Sql database.
	 */
	public static final Date getDbDateForString(String strdate) {
		java.util.Date date = DATE_FORMAT_PRICES_DATA.parse(strdate, new ParsePosition(0));
		return date;
	}

	/**
	 * Returns the current date in java SimpleDateFormat, format ("yyyy-MM-dd"). for example 2003-12-27. This format is consumable by the MS Sql database.
	 */
	public static final Date getYMDDateForString(String strdate) {
		java.util.Date date = DATE_FORMAT_BASIC_YMD.parse(strdate, new ParsePosition(0));
		return date;
	}

	/**
	 * Returns the current hour at a string.
	 */
	public static final String getCurrentHour() {
		java.util.Date today = new java.util.Date();
		String strDate = DATE_FORMAT_HOUR_ONLY.format(today);
		return strDate;
	}

	/**
	 * Returns the current date in java SimpleDateFormat, format ("kk:mm:ss.M"). for example 09:34:00.0 This format is consumable by the MS Sql database.
	 */
	public static final String getCurrentTime() {
		java.util.Date today = new java.util.Date();
		String strTime = DATE_FORMAT_TIME_ONLY.format(today) + ".0";
		return strTime;
	}

	// Used to calculate the number of days the market is open between startDate1 and stopDate1
	public static final int calculateNumMarketDates24Hours(UTCalendarTime startDate, UTCalendarTime stopDate) {
		UTCalendarTime startDateIterator = new UTCalendarTime(startDate.getJavaDate());
		int returnVal = 0;

		while (startDateIterator.isBeforeInCalendarDateTime(stopDate, false)) {
			returnVal++;
			startDateIterator.incrementDate(1);
		}

		if (returnVal == 0)
			returnVal++; // Add one to account for isBeforeInCalendarDate(), we want the return value to be inclusive

		return returnVal;
	}

	// --------------------------- NUMDATE METHODS -------------------------------
	public static final LocalDateTime numdateToLDT(int numdate) {
		// We add one or else we'll return 1 day early due to calculation truncations.
		return UTDatetime.fromEpoch((numdate) * GmConstants.SECONDS_IN_DAY);
	}

	public static final java.util.Date numdateToDate(int numdate) {
		return UTDatetime.toDate(numdateToLDT(numdate));
	}

	public static final String numdateToString(int numdate) {
		return UTDatetime.toString(numdateToLDT(numdate));
	}

	public static final String tryToFormatBadDate(String value) {
		String returnValue = "";
		String blankString = "";
		int ind1 = value.indexOf('-');
		int ind2 = value.lastIndexOf('-');

		// if the date is missing pieces, give up
		if ((ind1 == -1) || (ind2 == -1) || (ind1 == ind2)) {
			return blankString;
		}

		if (ind1 == 2 && ind2 == 5)
			return value;

		try {
			// get the values and check for missing zeros
			String mon = value.substring(0, ind1);
			String day = value.substring(ind1 + 1, ind2);
			String year = value.substring(ind2 + 1);

			// add leading zeros if needed
			if (day.length() == 1)
				day = "0" + day;
			if (mon.length() == 1)
				mon = "0" + mon;
			returnValue = mon + "-" + day + "-" + year;

			// if it is too long, blow out
			if (returnValue.length() != 10)
				return blankString;
        } catch (NumberFormatException nfe) {
			return blankString;
		}

		// If we did't bail by now, we're OK
		return returnValue;
	}
}
