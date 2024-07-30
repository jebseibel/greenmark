package com.greenmark.utils;

public class UTCalendarTools
{
	public static final int FormatDateForIQChart( String datestring )
	{
		String yearString, monthString, dayString;
		int result = 1060101;

		//handle bad date
		if( ( datestring == null ) || ( datestring.length() < 1 ) ) return result;

		try
		{
			int year = Integer.parseInt( datestring.substring(0, 4));

			//early exit for bad year
			if (year == 9999 || year == 9998)  return result;

			if ( year > 2000 )
			{
				int twoDigitYear = year % 2000;
				if ( twoDigitYear < 10 )
					yearString = "0" + Integer.toString(twoDigitYear);
				else
					yearString = Integer.toString(twoDigitYear);
			}
			else
			{
				int twoDigitYear = year % 1900;
				if ( twoDigitYear < 10 )
					yearString = "0" + Integer.toString(twoDigitYear);
				else
					yearString = Integer.toString(twoDigitYear);
			}

			monthString = datestring.substring(5, 7 );
			dayString = datestring.substring(8, 10 );

			String finalString = "1" + yearString + monthString + dayString;
			result = Integer.parseInt(finalString);
		}
		catch (Exception e)
		{
			//the insva
			return result;
		}
		return result;
	}

	public static final int FormatDateTimeForIQChart( String datestring )
	{
		String monthString, dayString, hourString, minuteString;
		int result = 901010101;

		//handle bad date
		if( ( datestring == null ) || ( datestring.length() < 1 ) ) return result;

		try
		{
			int year = Integer.parseInt( datestring.substring(0, 4));

		   //early exit for bad year
		   if (year == 9999 || year == 9998)  return result;

			monthString = datestring.substring(5, 7 );
			dayString = datestring.substring(8, 10 );
			hourString = datestring.substring(11, 13 );
			minuteString = datestring.substring(14, 16 );

			String finalString = "9" + monthString + dayString + hourString + minuteString ;
			result = Integer.parseInt(finalString);
		}
		catch (Exception e)
		{
			//the insva
			return result;
		}
		return result;
	}
}
