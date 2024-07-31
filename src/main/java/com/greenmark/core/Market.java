package com.greenmark.core;

import com.greenmark.utils.UTCalendarTime;
import com.greenmark.utils.UTPropertyManager;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.StringTokenizer;

/////////////////////////////////////////////////
// START CLASS: Market
/////////////////////////////////////////////////

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Seibel Consulting</p>
 *
 * @author Jeb Seibel
 * @version 1.0
 */
@Slf4j
public class Market {
    /**
     * This is the class name.
     */
    public static final String CLASSNAME = "Market";

    ////// market open /////
    public static final int MARKET_OPEN = 63000; //9:30am EST
    public static final int MARKET_CLOSE = 130000; //4:00pm EST
    public static final int MARKET_CLOSED_MINUTE = 0;
    public static final int MARKET_CLOSED_SECOND = 0;
    public static final int MARKET_OPEN_MINUTE = 30;
    public static final int MARKET_OPEN_SECOND = 0;
    public static final int AVG_NUM_CALENDAR_DAYS_PER_YEAR = 365;
    public static final int AVG_NUM_CALENDAR_DAYS_PER_MONTH = 30;
    public static boolean switchMarker = false;
    public static String market_holidays = "";
    public static SimpleDateFormat formatter;
    private static int marketOpen = Market.MARKET_OPEN;
    private static int marketClosed = Market.MARKET_CLOSE;
    public boolean newday;

////////////////////////////////
// Public Methods
////////////////////////////////

    /**
     *
     */
    public static final void initializeMarketHolidays() {
        try {
            boolean firstTimeThru = true;

            String listofdays = Config.getValue(Config.CONFIG_APPLICATION_HOLIDAY_LIST);
            StringBuffer stb = new StringBuffer(listofdays.length() * 2);

            StringTokenizer st = new StringTokenizer(listofdays, ";");
            while (st.hasMoreTokens()) {
                String token = st.nextToken();

                //make sure that there are no leading or trailing spaces
                String checkday = token.trim();

                //validate the format is correct
                checkday = null;

                //add it building list
                if (!firstTimeThru)
                    stb.append(";" + checkday);
                else
                    stb.append(checkday);

                firstTimeThru = false;
            }

            //finally save it to the string we compare against
            market_holidays = stb.toString();

            //also init formatter
            formatter = new SimpleDateFormat("MM-dd-yyyy");
        } catch (Exception e) {
            System.out.println("Exception in isHoliday " + e.getMessage());
        }
    }

    public static UTCalendarTime convertMarketDaysToCalendarDays(UTCalendarTime startDate,
                                                                 int numDays, boolean moveForward) {
        UTCalendarTime currentDate = null;
//		 if(trace!=null)  log.debug ("In convertMarketDaysToCalendarDays startDate: ["+startDate.formatDateTimeDisplay() +
//								"]  numDays: [" + numDays + "]  moveForward: [" + moveForward + "]" );

        //@todo fix this with simple dates
        try {
            currentDate = new UTCalendarTime(startDate);

            for (int i = 0; i < numDays; i++) {
                while ((isHoliday(currentDate.getJavaDate())) || (currentDate.isWeekendDay())) {
                    //if(trace!=null)  log.debug ("   Holiday or Weekend  for Date: [" + currentDate.formatDateTimeDisplay()
                    //			+ "]  i is: " + i);
                    if (moveForward) {
                        currentDate.incrementDate(1);
//						 if(trace!=null)  log.debug("  Incrementing the Date: [" + currentDate.formatDateTimeDisplay()
//											+ "]  i is: " + i);
                    } else {
                        currentDate.decrementDate(1);
//						 if(trace!=null)  log.debug("  Decrementing the Date: [" + currentDate.formatDateTimeDisplay()
//											+ "]  i is: " + i);
                    }
                }

                if (moveForward) {
                    currentDate.incrementDate(1); // A market day, increment and continue.
//					 if(trace!=null)  log.debug("  Incrementing the Date: [" + currentDate.formatDateTimeDisplay()
//											+ "]  i is: " + i);
                } else {
                    currentDate.decrementDate(1); // A market day, decrement and continue.
//					 if(trace!=null)  log.debug("  Decrementing the Date: [" + currentDate.formatDateTimeDisplay()
//											+ "]  i is: " + i);
                }
            }

            //if(trace!=null)  log.debug ("  Returning the Date: [" + currentDate.formatDateTimeDisplay()  + "]");
        } catch (Exception e) {
            System.out.println("Exception in isHoliday " + e.getMessage());
        }

        return currentDate;
    }

    /**
     * Returns true/false if today is a specified holiday
     *
     * @return boolean
     */
    public static boolean isHoliday(java.util.Date inDate) {
        //@todo fix this with simple dates
        try {
            String strdate;

            if (inDate != null) {
                strdate = formatter.format(inDate);
            } else {
                UTCalendarTime modifiedDatetime = null;
                java.util.Date today = null;


                today = new java.util.Date();
                strdate = formatter.format(today);

            }

            int location = market_holidays.indexOf(strdate);
//			System.out.println ("Strdate: " + strdate + "  location" + location  + "  market_holidays: [" + market_holidays + "]");

            //if they we didnt find it, its not a holiday
            return location != -1;

            //we found it, it is a holiday
        } catch (Exception e) {
            System.out.println("Exception in isHoliday " + e.getMessage());
        }
        return false;
    }


    public static final boolean isTodayMarketDay() {

        try {
            int thisdayofweek = Heartbeat.getDayOfWeek();

            int MONDAY = 2;
            int FRIDAY = 6;
            if ((MONDAY <= thisdayofweek) && (thisdayofweek <= FRIDAY)) {
                Date todaysDate = new java.util.Date();

//	         if ( DataFeedManager.isHistoricalDatafeed() )
//					todaysDate = AccountManager.getAccount().getHistoryCurrentJavadate_DISPLAY_ONLY();

                return !isHoliday(todaysDate);
            }//Endif Market actually open - not a weekend
        } catch (Exception ex) {
            log.error("Exception Market:isTodayMarketDay   ErrorMessage: [" + ex.getMessage() + "]");

            return (false);
        }


        return false;
    }

    public static final boolean isMarketClosed(boolean greenmanPaused) {
        return (!Market.isMarketOpen(greenmanPaused));
    }


    /**
     * Determines if the market is open or closed based on the current
     * time and date. The values it uses to determine open time are set
     * in this class. Date is set by being Monday - Friday with no regard
     * for Holidays.
     *
     * @return
     */
    public static final boolean isMarketOpen(boolean greenmanPaused) {
        try {

            String thistime = Heartbeat.getCurrentMarketTime("");
            int intThisTime = Integer.parseInt(thistime);
//         System.out.println(" . . . marketOpen [" +marketOpen+ "]  marketClosed [" +marketClosed+ "]");

            if (calcMarketTimes())  // First calculate market open/close for your timezone.
            {

                if (marketOpen < intThisTime && intThisTime < marketClosed) {
                    int thisdayofweek = Heartbeat.getDayOfWeek();

                    int MONDAY = 2;
                    int FRIDAY = 6;
                    if ((MONDAY <= thisdayofweek) && (thisdayofweek <= FRIDAY)) {
                        if (greenmanPaused) {
//  Don't display anything here, it screws up the user and the whole reason they paused in the first place.
//							log.info("Market is open - Greenman is PAUSED - (" + LocalDateTime.now() + ")");

                            return true;
                        } else {
                            //if the market has just opened
                            if (!Market.switchMarker) {
                                log.info("\n\nThe Market is now open!\n\n");
//                        System.out.println("\n\nThe Market is now open!\n\n");
                                Market.switchMarker = true;
                            } else {
                                log.debug("Market is open (" + LocalDateTime.now() + ")");
                            }

                            return true;
                        }//Endelse Greenman not paused
                    }//Endif Market actually open - not a weekend
                    else {
                        //if the market has just opened
                        if (Market.switchMarker) {
                            log.info("\n\nThe Market has just closed!\n\n");
//                     System.out.println("\n\nThe Market has just closed!\n\n");
                            Market.switchMarker = false;
                        }
                    }
                }//Endif times are ok
            }//Endif we could calculate Market open/closed times

            //System.out.println("Market is closed (" + LocalDateTime.now() + ")");
        } catch (Exception ex) {
            log.error("Exception Market:isMarketOpen   ErrorMessage: [" + ex.getMessage() + "]");

            return (false);
        }


        return false;
    }

    public static final boolean isAfterMarketClosed() {
        try {

            String thistime = Heartbeat.getCurrentMarketTime("");
            int intThisTime = Integer.parseInt(thistime);

            if (calcMarketTimes())  // First calculate market open/close for your timezone.
            {

                if (intThisTime > marketClosed) {
                    int thisdayofweek = Heartbeat.getDayOfWeek();

                    int MONDAY = 2;
                    int FRIDAY = 6;
                    if ((MONDAY <= thisdayofweek) && (thisdayofweek <= FRIDAY)) {
//						if ( DataFeedManager.isHistoricalDatafeed() )
//							 log.debug ("Market is isAfterMarketClosed (" + thistime + ")");
//						else
                        log.debug("Market is isAfterMarketClosed (" + LocalDateTime.now() + ")");


                        return true;
                    }//Endif Market actually open - not a weekend
                }//Endif times are ok
            }//Endif we could calculate Market open/closed times

            //System.out.println("Market is closed (" + LocalDateTime.now() + ")");
        } catch (Exception ex) {
            log.error("Exception Market:isAfterMarketClosed   ErrorMessage: [" + ex.getMessage() + "]");

            return (false);
        }


        return false;
    }


    public static final boolean isMarketOpenForDate(UTCalendarTime inDate) {
        try {
//			int thisdayofweek = Heartbeat.getDayOfWeek();
            int dayOfWeek = inDate.getDayOfWeek();

            int MONDAY = 2;
            int FRIDAY = 6;
            if ((MONDAY <= dayOfWeek) && (dayOfWeek <= FRIDAY)) {
                if (!Market.isHoliday(inDate.getJavaDate())) {

                    return true;
                }

            }//Endif Market actually open - not a weekend

        } catch (Exception ex) {
            log.error("Exception Market:isMarketOpenForDate   ErrorMessage: [" + ex.getMessage() + "]");

            return (false);
        }


        return false;
    }


    public static final boolean calcMarketTimes() {
        try {
            marketOpen = adjustTimeForTimezone(Market.MARKET_OPEN);
            marketClosed = adjustTimeForTimezone(Market.MARKET_CLOSE);
        } catch (Exception ex) {
            log.error("Exception Market:calcMarketTimes   ErrorMessage: [" + ex.getMessage() + "]");

            return (false);
        }

        return true;
    }

    public static final int adjustTimeForTimezone(int inputTime) {

        int localTime = inputTime;

        try {
            // Determine the Local Time Zone
            // Set the Default Time Zone to PST

            String localTimeZone = UTPropertyManager.getProperty("local_time_zone");

//	      if ( DataFeedManager.isHistoricalDatafeed() )  // Historical mode is always in Eastern Time
//				localTimeZone = "EST";

            if (localTimeZone.equals("MST"))
                localTime += 10000;

            if (localTimeZone.equals("CST"))
                localTime += 20000;

            if (localTimeZone.equals("EST"))
                localTime += 30000;

        } catch (Exception ex) {
            log.error("Exception Market:adjustTimeForTimezone   ErrorMessage: [" + ex.getMessage() + "]");

            return (localTime);
        }


        return localTime;
    }


    public static final int numMinsTillMarketClose(UTCalendarTime inCal) {

        try {
            if (calcMarketTimes())  // First calculate market open/close for your timezone.
            {
                long numCalMins = inCal.getMinutesSince1970();
                log.debug("     numMinsSince1970 for in datetime: [" + numCalMins +
                        "] for datetime: [" + inCal.getDateTimeDisplay() + "]");

                // Make a temp Calendar to reset that day to market closed time.
                UTCalendarTime tempInCal = new UTCalendarTime(inCal);

                log.debug("     tempInCal: [" + tempInCal.getDateTimeDisplay() + "]");
                tempInCal.resetCalendarTime(Market.getMarketClosedHour(), Integer.toString(Market.MARKET_CLOSED_MINUTE),
                        Integer.toString(Market.MARKET_CLOSED_SECOND));
                log.debug("     tempInCal: [" + tempInCal.getDateTimeDisplay() + "]");
                long numCloseMins = tempInCal.getMinutesSince1970();
                log.debug("     numMinsSince1970 for Market Close datetime on that day: [" + numCloseMins +
                        "] for datetime: [" + tempInCal.getDateTimeDisplay() + "]");

                long numMins = numCloseMins - numCalMins;
                log.debug("     numMinsTillMarketClose: [" + numMins + "]");

                return (int) numMins;
            }//Endif calcMarketTimes - No else, will leave on failure
        } catch (Exception ex) {
            log.error("Exception Market:numMinsTillMarketClose   ErrorMessage: [" + ex.getMessage() + "]");

            return (0);
        }

        //Exit if failure.  Success should have exited earlier.
        log.debug("     numMinsTillMarketClose FAILURE: [0]");

        return 0;
    }


    public static final boolean isAfterMarketClose(UTCalendarTime inCal) {

        try {
            if (calcMarketTimes())  // First calculate market open/close for your timezone.
            {
                long numCalMins = inCal.getMinutesSince1970();
                log.debug("     numMinsSince1970 for in datetime: [" + numCalMins +
                        "] for datetime: [" + inCal.getDateTimeDisplay() + "]");

                // Make a temp Calendar to reset that day to market closed time.
                UTCalendarTime tempInCal = new UTCalendarTime(inCal);

                log.debug("     tempInCal: [" + tempInCal.getDateTimeDisplay() + "]");
                tempInCal.resetCalendarTime(Market.getMarketClosedHour(), Integer.toString(Market.MARKET_CLOSED_MINUTE),
                        Integer.toString(Market.MARKET_CLOSED_SECOND));
                log.debug("     tempInCal: [" + tempInCal.getDateTimeDisplay() + "]");
                long numCloseMins = tempInCal.getMinutesSince1970();
                log.debug("     numMinsSince1970 for Market Close datetime on that day: [" + numCloseMins +
                        "] for datetime: [" + tempInCal.getDateTimeDisplay() + "]");

                return numCalMins > numCloseMins;
            }//Endif calcMarketTimes - No else, will leave on failure
        } catch (Exception ex) {
            log.error("Exception Market:isAfterMarketClose   ErrorMessage: [" + ex.getMessage() + "]");

            return (false);
        }

        //Exit if failure.  Success should have exited earlier.
        log.debug("     isAfterMarketClose FAILURE: [0]");

        return false;
    }


    public static final boolean isBeforeMarketOpen(UTCalendarTime inCal) {
        try {
            if (calcMarketTimes())  // First calculate market open/close for your timezone.
            {
                long numCalMins = inCal.getMinutesSince1970();
                String debugCal1 = inCal.getDateTimeDisplay();
                log.debug("     numMinsSince1970 for in datetime: [" + numCalMins +
                        "] for datetime: [" + inCal.getDateTimeDisplay() + "]");

                // Make a temp Calendar to reset that day to market closed time.
                UTCalendarTime tempInCal = new UTCalendarTime(inCal);
                String debugCal = tempInCal.getDateTimeDisplay();
                tempInCal.resetCalendarTime(Market.getMarketOpenHour(), Integer.toString(Market.MARKET_OPEN_MINUTE),
                        Integer.toString(Market.MARKET_OPEN_SECOND));
                String debugCal2 = tempInCal.getDateTimeDisplay();

                long numOpenMins = tempInCal.getMinutesSince1970();
                log.debug("     numMinsSince1970 for Market Open datetime on that day: [" + numOpenMins +
                        "] for datetime: [" + tempInCal.getDateTimeDisplay() + "]");

                return numOpenMins > numCalMins;
            }//Endif calcMarketTimes - No else, will leave on failure
        } catch (Exception ex) {
            log.error("Exception Market:isBeforeMarketOpen   ErrorMessage: [" + ex.getMessage() + "]");

            return (false);
        }

        log.debug("     isBeforeMarketOpen FAILURE: [0]");

        return false;
    }


    public static final int numMinsSinceMarketOpen(UTCalendarTime inCal) {
        log.debug("Input UTCalendarTime: [" + inCal.getDateTimeDisplay() + "] ----------------");

        try {
//			 if ( calcMarketTimes)  // First calculate market open/close for your timezone.
//			 {
//				 long numCalMins = inCal.getMinutesSince1970();
//				  log.debug ( "     numMinsSince1970 for in datetime: [" + numCalMins +
//										  "] for datetime: [" + inCal.getDateTimeDisplay() + "]" );
//
//				 // Make a temp Calendar to reset that day to market closed time.
//				 UTCalendarTime tempInCal = new UTCalendarTime (inCal);
//				 tempInCal.resetCalendarTime( Market.getMarketOpenHour(), Integer.toString(Market.MARKET_OPEN_MINUTE),
//														Integer.toString(Market.MARKET_OPEN_SECOND) );
//				 long numOpenMins = tempInCal.getMinutesSince1970();
//				  log.debug ( "     numMinsSince1970 for Market Open datetime on that day: [" + numOpenMins +
//										  "] for datetime: [" + tempInCal.getDateTimeDisplay() + "]" );
//
//				 long numMins = numCalMins - numOpenMins;
//
//				  log.debug ( "     numMinsSinceMarketOpen: [" + numMins + "]" );
//				 if ( numMins < 0 )
//					 return 0;
//				 else
//					 return (int) numMins;
//			 }//Endif calcMarketTimes - No else, will leave on failure
        } catch (Exception ex) {
            log.error("Exception Market:numMinsSinceMarketOpen   ErrorMessage: [" + ex.getMessage() + "]");

            return (0);
        }

        log.debug("     numMinsSinceMarketOpen FAILURE: [0]");

        return 0;
    }

    public static final String getMarketOpenHour() {
        try {
            int openHour = marketOpen / 10000;
            return Integer.toString(openHour);
        } catch (Exception ex) {
            System.out.println("Exception Market:getMarketOpenHour   ErrorMessage:  [" + ex.getMessage() + "]");
            return "";
        }
    }

    public static final String getMarketClosedHour() {
        try {
            int closeHour = marketClosed / 10000;
            return Integer.toString(closeHour);
        } catch (Exception ex) {
            System.out.println("Exception Market:getMarketClosedHour   ErrorMessage:  [" + ex.getMessage() + "]");
            return "";
        }
    }

}
