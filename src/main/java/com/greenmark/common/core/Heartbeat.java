package com.greenmark.common.core;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;

////////////////////////////////////////////////////////////////////////////////
// START CLASS: Heartbeat
////////////////////////////////////////////////////////////////////////////////

@Slf4j
public class Heartbeat
        extends Thread {
    /**
     * This is the class name.
     */
    public static final String CLASSNAME = "Heartbeat";
    public static final String STATUS_ON = "beating";
    public static final String STATUS_OFF = "off";
    // The heart-beat time is 1 second (1,000 milliseconds)
    private static final long sleepTime = Labels.SECOND;
    // internal timing values
    public static int thisSecond = 0;
    public static int thisMinute = 0;
    public static int thisHour = 0;
    public static String thisTime = "";
    private static String status;
    // Every 10 iterations, attempt to re-sync.
    private final int syncIterations = 10;
    // Current sync'ing iteration, set to 8 initially to ensure an early
    // syncing right after start.
    private int currentSyncIteration = 8;
    private boolean kill = false;
    private boolean verbose = false;

////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTORS
////////////////////////////////////////////////////////////////////////////////

    public Heartbeat() {

    }

////////////////////////////////////////////////////////////////////////////////
// Public Methods
////////////////////////////////////////////////////////////////////////////////

    /**
     * Gets a time adjusted
     *
     * @return
     */
    public static final long getThreadSleepTime(long wakeupMills) throws
            Exception {
        //get the current time in mills
        GregorianCalendar calendar = new GregorianCalendar();
        long currentMills = calendar.getTimeInMillis();

        //if the time they want to wake up is before the current, then go to the next minute
        //do this as a while loop on the off chance that we are alerady too close to the next minute
        while (wakeupMills < currentMills) {
            log.error("The update took too long -- going to the next minute");
            wakeupMills = Heartbeat.getNextMinuteWakeupTime();
            log.error("wakeupMills [" + wakeupMills + "]; wakeupMills < currentMills [" + (wakeupMills < currentMills) + "]");
        }

        //compute the milliseconds they need to sleep
        //add an extra 10 milliseconds just to make sure they don't wake up too early
        long actualSleepMills = wakeupMills - currentMills + 20;

        return (actualSleepMills);
    }

    /**
     * Returns a long that identifies when the next 'top of the minute' is in milliseconds.
     * What it does is gets the current minute, adds one to it, then gets that time as a
     * long.
     *
     * @return
     */
    public static final long getNextMinuteWakeupTime() {
        return Heartbeat.getNextMinuteWakeupTime(1);
    }

//   /**
//    * Returns the system time seed that is to be used in a subsequent call
//    * to getThreadSleepTime().
//    * @return The time seed.
//    */
//   public static final long getNextMinuteInMillis()
//   {
//		long currentTimeMillis
//      return( System.currentTimeMillis() );
//   }

    /**
     * Returns a long that identifies when the next 'top of the minute' is in milliseconds.
     * What it does is gets the current minute, adds the offset minutes to it, then gets
     * that time as a long. The offset minutes is till wake up time. For example, to wake up
     * at the top of the next minute, send in a one. For a five minute interval, send in five,
     * etc.
     *
     * @param offset_minutes - number of minutes till the next wake up.
     * @return
     */
    public static final long getNextMinuteWakeupTime(int offset_minutes) {
        //get the current time
        GregorianCalendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        //roll forward to the next minute
        GregorianCalendar calendarNext = new GregorianCalendar(year, month, day, hour, minute + offset_minutes);
        return calendarNext.getTimeInMillis();
    }

    /**
     * This method returns a current date of the format specifically needed by the
     * market for the watchers.
     */
    public static final String getCurrentMarketDate() {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();

//		if ( ! DataFeedManager.isHistoricalDatafeed() )
//			calendar.setTime( trialTime );
//		else
//		{
//			java.util.Date currentDatetime = AccountManager.getAccount().getHistoryCurrentDatetime().getJavaDate();
//			calendar.setTime( currentDatetime );
//		}

        //get the year
        String sYear = Integer.toString(calendar.get(Calendar.YEAR));

        //get the month = add one since it is zero based
        String sMonth = "";
        int thisMonth = calendar.get(Calendar.MONTH) + 1;
        if (thisMonth < 10) {
            sMonth = "0" + thisMonth; //add leading zeros
        } else {
            sMonth = Integer.toString(thisMonth);
        }

        //get the day
        String sDay = "";
        int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (thisDay < 10) {
            sDay = "0" + thisDay;
        } else {
            sDay = Integer.toString(thisDay);
        }

        return sYear + sMonth + sDay;
    }

    /**
     * This method returns an int that corresponds to the day of week:
     * 1 - Sunday
     * 2 - Monday, etc...
     */
    public static final int getDayOfWeek() {
//		if ( ! DataFeedManager.isHistoricalDatafeed() )
//		{
//			UTCalendar todaysCal = new UTCalendar (new java.util.Date());
//			return todaysCal.getDayOfWeek();
//		}
//		else
//		{
//			int dayOfWeek = AccountManager.getAccount().getHistoryCurrentDatetime().getDayOfWeek();
//			return dayOfWeek;
//		}
        return 1;
    }

    /**
     * This method returns a current time of the format specifically needed by the
     * market for the watchers.
     */
    public static final String getCurrentMarketTime() {
        return getCurrentMarketTime(":");
    }

    /**
     * This method returns a current time of the format specifically needed by the
     * market for the watchers.
     */
    public static final String getCurrentMarketTime(String delim) {
        String returnTime = "";

        SimpleDateFormat formatter = new SimpleDateFormat("kk" + delim + "mm" + delim + "ss");
//
//		if ( ! DataFeedManager.isHistoricalDatafeed() )
//		{
//			java.util.Date today = new java.util.Date();
//			returnTime = formatter.format( today );
//		}
//		else
//		{
//			UTCalendarTime currentHistoryDatetime = AccountManager.getAccount().getHistoryCurrentDatetime();
////	      String debugDateString = currentHistoryDatetime.formatDateTimeDisplay();
//
//			// Instead, get the info from the UTCalendarTime object
//			returnTime = currentHistoryDatetime.formatTimeAsInt(delim);
//		}

        return returnTime;
    }

    public final int getSeconds() {
        return thisSecond;
        //return( Integer.toString( this.thisSecond ) );
    }

////////////////////////////////////////////////////////////////////////////////
// THREAD PROCESSING METHODS
////////////////////////////////////////////////////////////////////////////////

    public final int getCurrentMinute() {
//		if ( ! DataFeedManager.isHistoricalDatafeed() )
//		{
//			GregorianCalendar calendar = new GregorianCalendar();
//			return calendar.get( Calendar.MINUTE );
//		}
//
//	   else
//		{
//		   int thisMinute = AccountManager.getAccount().getHistoryCurrentDatetime().getMinute();
//			return thisMinute;
//		}
        return 1;
    }

    /**
     * Turn on the verbose flag.  Doing this will start the console output
     * that outputs a message for each "heartbeat".
     */
    public final void turnVerboseOn() {
        this.verbose = true;
    }

////////////////////////////////////////////////////////////////////////////////
// PRIVATE METHODS
////////////////////////////////////////////////////////////////////////////////

    /**
     * Turn off the verbose flag.  Doing this will end the console output
     * that outputs a message for each "heartbeat".
     */
    public final void turnVerboseOff() {
        this.verbose = false;
    }

    /**
     * Return a string representation of this object.
     *
     * @return String
     */
    public final String toString() {
        if (status.equals(STATUS_ON)) {
            return (CLASSNAME + ": " + status + ": Time: [" + thisTime +
                    "]; System Time [" + System.currentTimeMillis() +
                    "]; Sync Iteration [" + this.currentSyncIteration + "]");
        } else {
            return (CLASSNAME + ": " + status);
        }
    }

    /**
     * Thread interface method that performs the thread processing.
     */
    public final void run() {
        while (!this.kill) {
            _setStatusOn();
            try {
                _updateSelf();
                //if(verbose)  log.debug( ">>> "+this.toString() );
                sleep(this._sync());
            } catch (Throwable ex) {
                //if(verbose)  log.debug( CLASS_LABEL+": Exception: ["+ex.getClass().getName()+"]: ["+ex.getMessage()+"]" );
            }
        }
        // kill called, set status off.
        _setStatusOff();
    }

    /**
     * Start the process of terminating this thread.  This sends a signal to the
     * thread that it must halt, stop processing and terminate itself.  Once
     * this method is called, the thread will terminate itself.  This cannot be
     * undone, so be careful!
     */
    public final void kill() {
        this.kill = true;
    }

///////////////////////////////////////////////////////////////////////////////////////
// static methods
///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Do the work of updating the values of this object.
     */
    private final void _updateSelf() {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);

        // get the Hour
        Heartbeat.thisHour = calendar.get(Calendar.HOUR_OF_DAY);
        // get the Minute
        Heartbeat.thisMinute = calendar.get(Calendar.MINUTE);
        // get the Second
        Heartbeat.thisSecond = calendar.get(Calendar.SECOND);
        // get the datetime
        Heartbeat.thisTime = trialTime.toString();
    }

    /**
     * The purpose of this method is to synchronize the heart-beat up to the
     * second so that it doesn't get out of whack.
     *
     * @return The sleep time.
     */
    private final long _sync() {
        try {
            if (!(this.currentSyncIteration < this.syncIterations)) {
                // reset the synch iteration back to zero.
                this.currentSyncIteration = 0;
                // figure out the time difference
                String stDiff = String.valueOf(System.currentTimeMillis());
                long diff = Long.parseLong(stDiff.substring(stDiff.length() - 3
                ));
                // if the diff is only off by 10 miliseconds, either side, then
                // do nothing by returning the standard sleep time of one second.
                if (diff > 889 || diff < 11) {
                    return (sleepTime);
                }
                // get the delta that should be used to adjust the sleep time.
                long delta = sleepTime - diff;
                // if the sleep time is less than half a second then we are
                // really out of whack, so sync up to the next second.
                if (delta < 500) {
                    delta += sleepTime;
// log.debug( CLASS_LABEL + ": Sync'ing: diff=" + diff + "; delta=" + delta );
                    // return sync'ed sleep time
                }
                return (delta);
            }
        } catch (Throwable t) {
            // on exception reset sync iteration and try again next time.
            //if(verbose)  log.debug( CLASS_LABEL + ": Sync Exception: ["+t.getClass().getName()+"]: ["+t.getMessage()+"]" );
            this.currentSyncIteration = 0;
        }
        //
        this.currentSyncIteration++;
        // sync'ing didn't occur, return standard sleep iteration
// log.debug( CLASS_LABEL + ": Sync'ing: [" + this.sleepTime + "]" );
        return (sleepTime);
    }

    /**
     * Turn current status on.
     */
    private final void _setStatusOn() {
        status = STATUS_ON;
    }

    /**
     * Turn current status off.
     */
    private final void _setStatusOff() {
        status = STATUS_OFF;
    }

    /**
     * OLD AND DEPRECATED
     * public static final String formatTimeHHMMSS( String delim, Calendar calendar, Date trialTime )
     * {
     * //		Calendar calendar = new ();
     * //		Date trialTime = new Date();
     * calendar.setTime( trialTime );
     * <p>
     * //get the Hour
     * String sHour = "";
     * int thisHour = calendar.get( Calendar.HOUR_OF_DAY );
     * if( thisHour < 10 )
     * {
     * sHour = "0" + Integer.toString( thisHour ); //add leading zeros
     * }
     * else
     * {
     * sHour = Integer.toString( thisHour );
     * }
     * <p>
     * //get the Minute
     * String sMinute = "";
     * int thisMinute = calendar.get( Calendar.MINUTE );
     * if( thisMinute < 10 )
     * {
     * sMinute = "0" + Integer.toString( thisMinute ); //add leading zeros
     * }
     * else
     * {
     * sMinute = Integer.toString( thisMinute );
     * }
     * <p>
     * //get the Second
     * String sSecond = "";
     * int thisSecond = calendar.get( Calendar.SECOND );
     * if( thisSecond < 10 )
     * {
     * sSecond = "0" + Integer.toString( thisSecond ); //add leading zeros
     * }
     * else
     * {
     * sSecond = Integer.toString( thisSecond );
     * }
     * <p>
     * return sHour + delim + sMinute + delim + sSecond;
     * }
     **/
    public static class Labels {
        /**
         * This is the class name.
         */
        public static final String CLASSNAME = "Labels";

        ////////////////////////////////////////////////////////////////////////////////
        // PUBLIC CONSTANTS
        ////////////////////////////////////////////////////////////////////////////////

        //   public static final long MILLISECONDS = 1000;
        //
        public static final long SECOND = 1000;
        //
        public static final long MINUTE = 60 * SECOND;
        public static final long HOUR = 60 * MINUTE;
        public static final long DAY = 24 * HOUR;

        /**
         * 1 minute update cycle
         */
        public static final int TYPE_MONTHLY = 7;
        public static final int TYPE_WEEKLY = 6;
        public static final int TYPE_DAILY = 1;
        public static final int TYPE_MINUTE60 = 2;
        public static final int TYPE_MINUTE15 = 3;
        public static final int TYPE_MINUTE05 = 4;
        public static final int TYPE_MINUTE01 = 5;

        public static final String STR_TYPE_MONTHLY = "MONTHLY";
        public static final String STR_TYPE_WEEKLY = "WEEKLY";
        public static final String STR_TYPE_DAILY = "DAILY";
        public static final String STR_TYPE_MINUTE60 = "MINUTE60";
        public static final String STR_TYPE_MINUTE15 = "MINUTE15";
        public static final String STR_TYPE_MINUTE05 = "MINUTE05";
        public static final String STR_TYPE_MINUTE01 = "MINUTE01";

        public static final String CHART_STR_TYPE_DAILY = "daily";
        public static final String CHART_STR_TYPE_MINUTE60 = "sixtyminute";
        public static final String CHART_STR_TYPE_MINUTE15 = "fifteenminute";
        public static final String CHART_STR_TYPE_MINUTE05 = "fiveminute";
        public static final String CHART_STR_TYPE_MINUTE01 = "oneminute";

        public static final int TYPE_BUY = 1;
        public static final int TYPE_SELL = 2;

        public final static int BKGND_R = 193;
        public final static int BKGND_G = 211;
        public final static int BKGND_B = 189;

        public static final int OBJECT_ACTIVE = 1;
        public static final int OBJECT_INACTIVE = 0;

        public static final int IS_TRUE = 1;
        public static final int IS_FALSE = 0;

        public static final String STR_TRUE = "1";
        public static final String STR_FALSE = "0";

        ////////////////////////////////////////////////////////////////////////////////
        // CONSTRUCTORS
        ////////////////////////////////////////////////////////////////////////////////

        /**
         * Static object, Can't call
         */
        private Labels() {
        }

        public static final String getLabelForTimeValue(int value) {
            switch (value) {
                case TYPE_DAILY:
                    return Labels.STR_TYPE_DAILY;
                case TYPE_MINUTE60:
                    return Labels.STR_TYPE_MINUTE60;
                case TYPE_MINUTE15:
                    return Labels.STR_TYPE_MINUTE15;
                case TYPE_MINUTE05:
                    return Labels.STR_TYPE_MINUTE05;
                case TYPE_MINUTE01:
                    return Labels.STR_TYPE_MINUTE01;
                case TYPE_WEEKLY:
                    return Labels.STR_TYPE_WEEKLY;
                case TYPE_MONTHLY:
                    return Labels.STR_TYPE_MONTHLY;
            }
            return "";
        }

        public static final int getTimeValueForLabels(String timevalue) {
            //order in the frequency they are called
            if (timevalue.equals(Labels.STR_TYPE_MINUTE05)) {
                return TYPE_MINUTE05;
            } else if (timevalue.equals(Labels.STR_TYPE_MINUTE15)) {
                return TYPE_MINUTE15;
            } else if (timevalue.equals(Labels.STR_TYPE_MINUTE60)) {
                return TYPE_MINUTE60;
            } else if (timevalue.equals(Labels.STR_TYPE_DAILY)) {
                return TYPE_DAILY;
            }

            ////////////////////////////////////////
            else if (timevalue.equals(Labels.STR_TYPE_MINUTE01)) {
                return TYPE_MINUTE01;
            } else if (timevalue.equals(Labels.STR_TYPE_WEEKLY)) {
                return TYPE_WEEKLY;
            } else if (timevalue.equals(Labels.STR_TYPE_MONTHLY)) {
                return TYPE_MONTHLY;
            }

            return -1;
        }


    }

    /**
     * @formatter:off
     * <p>Copyright: Copyright (c) 2021</p>
     * <p>Company: Greenman Financial Services, Inc.</p>
     *
     * <p>Title: Timeslice</p>
     * <p>Description: This class is responsible for processing all timeslice data for a security.
     * Its main goal is to act as the center point for retrieving and processing
     * data.<p>
     *
     *
     * @author Monte Seibel
     * @version 7.0
     * @formatter:on
     */

    public static class Timeslice {
        public static final String CLASSNAME = "Timeslice";

        /**
         * The label constant for a stock timeslice's "date"
         */
        public static final String DATE = "date";

        /**
         * The label constant for a stock timeslice's "time"
         */
        public static final String TIME = "time";

        /**
         * The label constant for a stock timeslice's open executedPrice
         */
        public static final String OPEN = "open";

        /**
         * The label constant for a stock timeslice's high executedPrice
         */
        public static final String HIGH = "high";

        /**
         * The label constant for a stock timeslice's low executedPrice
         */
        public static final String LOW = "low";

        /**
         * The label constant for a stock timeslice's close executedPrice
         */
        public static final String CLOSE = "close";

        /**
         * The label constant for a stock timeslice's volume
         */
        public static final String VOLUME = "volume";

        // class properties
        Hashtable data;

        ////////////////////////////////////////////////////////////////////////////////
        // Constructors
        ////////////////////////////////////////////////////////////////////////////////
        public Timeslice() {
            this.data = new Hashtable();
        }

        /**
         * Creates a new instance of a timeslice. This constructor is as simple as it gets. There is no pre-processing of any values.
         *
         * @param data
         */
        public Timeslice(Hashtable data) {
            this.data = data;
        }

        ////////////////////////////////////////////////////////////////////////////////
        // Public Methods
        ////////////////////////////////////////////////////////////////////////////////

        /**
         * @param label
         * @return
         */
        public final String getData(String label) {
            if (this.data.containsKey(label)) {
                return ((String) this.data.get(label));
            }
            return ("");
        }

        public final boolean isValid60MinSummary() {
            return (getHigh() != 0.0) && (getLow() != 10000.0) && (getOpen() != 0.0);
        }

        public final float getHigh() {
            if (this.data.containsKey(Timeslice.HIGH)) {
                String strHigh = (String) this.data.get(Timeslice.HIGH);
                return Float.parseFloat(strHigh);
            }
            return (0);
        }

        public final float getLow() {
            if (this.data.containsKey(Timeslice.LOW)) {
                String strLow = (String) this.data.get(Timeslice.LOW);
                return Float.parseFloat(strLow);
            }
            return (0);
        }

        public final float getOpen() {
            if (this.data.containsKey(Timeslice.OPEN)) {
                String strOpen = (String) this.data.get(Timeslice.OPEN);
                return Float.parseFloat(strOpen);
            }
            return (0);
        }

        public final float getClose() {
            if (this.data.containsKey(Timeslice.CLOSE)) {
                String strClose = (String) this.data.get(Timeslice.CLOSE);
                return Float.parseFloat(strClose);
            }
            return (0);
        }

        public final float getVolume() {
            if (this.data.containsKey(Timeslice.VOLUME)) {
                String strVolume = (String) this.data.get(Timeslice.VOLUME);
                return Float.parseFloat(strVolume);
            }
            return (0);
        }

        /**
         * @param label
         * @param value
         */
        public final void setData(String label, String value) {
            this.data.put(label, value);
        }

        /**
         * Appends and item of data to the stock's timeslice.
         *
         * @param label - the label for the data.
         * @param value = the value of the data.
         * @return
         */
        public final boolean appendData(String label, String value) {
            try {
                this.data.put(label, value);
                return (true);
            } catch (Throwable t) {
                return (false);
            }
        }

        /**
         * Retrieve the time for this timeslice.
         *
         * @return the time.
         */
        public final String getTime() {
            // We actually parse the Time off the DATE key. Which we set on create with the datetime.
            if (this.data.containsKey(DATE)) {
                String datetime = (String) this.data.get(DATE);
                // int spacePosition = datetime.lastIndexOf(" ", 12);
                // String date = datetime.substring(datetime.lastIndexOf(" ", 12) + 1, datetime.length() );
                return (datetime.substring(datetime.lastIndexOf(" ", 12) + 1));
            }

            return ("");
        }

        // This method returns the Hour out of this Timeslice's datetime
        public final String getTimeHour() {
            // We actually parse the Time off the DATE key. Which we set on create with the datetime.
            if (this.data.containsKey(DATE)) {
                String datetime = (String) this.data.get(DATE);
                // int spacePosition = datetime.lastIndexOf(" ", 12);
                String time = datetime.substring(datetime.lastIndexOf(" ", 12) + 1);
                // int colonPosition = time.indexOf(':');
                // String hour = time.substring ( 0, time.indexOf(':') );

                return (time.substring(0, time.indexOf(':')));
            }

            return ("");
        }

        // This method returns the Minute out of this Timeslice's datetime
        public final String getTimeMinute() {
            // We actually parse the Time off the DATE key. Which we set on create with the datetime.
            if (this.data.containsKey(DATE)) {
                String datetime = (String) this.data.get(DATE);
                // int spacePosition = datetime.lastIndexOf(" ", 12);
                String time = datetime.substring(datetime.lastIndexOf(" ", 12) + 1);
                int firstColonPos = time.indexOf(':');
                // int secondColonPos = time.indexOf(':', firstColonPos + 1);
                // String minute = time.substring (firstColonPos + 1, time.indexOf(':', firstColonPos + 1) );

                return (time.substring(firstColonPos + 1, time.indexOf(':', firstColonPos + 1)));
            }

            return ("");
        }

        // This method returns the Hour and Minute out of this Timeslice's datetime: HH:MM
        public final String getTimeHourMinute() {
            // We actually parse the Time off the DATE key. Which we set on create with the datetime.
            if (this.data.containsKey(DATE)) {
                String datetime = (String) this.data.get(DATE);
                // int spacePosition = datetime.lastIndexOf(" ", 12);
                String time = datetime.substring(datetime.lastIndexOf(" ", 12) + 1);
                int firstColonPos = time.indexOf(':');
                // int secondColonPos = time.indexOf(':', firstColonPos + 1);
                // String minute = time.substring (firstColonPos + 1, time.indexOf(':', firstColonPos + 1) );

                return (time.substring(0, time.indexOf(':', firstColonPos + 1)));
            }

            return ("");
        }

        /**
         * Retrieve the date for this timeslice.
         *
         * @return the date.
         */
        public final String getDate() {
            if (this.data.containsKey(DATE)) {
                String datetime = (String) this.data.get(DATE);
                // int spacePosition = datetime.lastIndexOf(" ", 12);
                // String date = datetime.substring(0,datetime.lastIndexOf(" ", 12));
                return (datetime.substring(0, datetime.lastIndexOf(" ", 12)));
            }
            return ("");
        }

        public final String getDatetime() {
            if (this.data.containsKey(DATE)) {
                return ((String) this.data.get(DATE));
            }
            return ("");
        }

        /**
         * Returns a string representation of this object.
         *
         * @return String
         */
        public String toString() {
            String date = (String) this.data.get(Timeslice.DATE);
            String high = (String) this.data.get(Timeslice.HIGH);
            String low = (String) this.data.get(Timeslice.LOW);
            String open = (String) this.data.get(Timeslice.OPEN);
            String close = (String) this.data.get(Timeslice.CLOSE);

            return ("Stock Timeslice: " + " datetime: " + date + ", high: " + high + ", low: " + low + ", open: " + open + ", close: " + close + ";");
        }
    }
}
