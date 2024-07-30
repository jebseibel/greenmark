package com.greenmark.core;

import com.greenmark.common.core.Labels;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

////////////////////////////////////////////////////////////////////////////////
// START CLASS: Heartbeat
////////////////////////////////////////////////////////////////////////////////

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Seibel Consulting</p>
 * @author Jeb Seibel
 * @version 1.0
 */
@Slf4j
public class Heartbeat
   extends Thread
{
   /** This is the class name. */
   public static final String CLASSNAME = "Heartbeat";

   // internal timing values
   public static int thisSecond = 0;
   public static int thisMinute = 0;
   public static int thisHour = 0;
   public static String thisTime = "";

   private static String status;

   // The heart-beat time is 1 second (1,000 milliseconds)
   private static long sleepTime = Labels.SECOND;

   // Every 10 iterations, attempt to re-sync.
   private int syncIterations = 10;

   // Current sync'ing iteration, set to 8 initially to ensure an early
   // syncing right after start.
   private int currentSyncIteration = 8;

   private boolean kill = false;

   public static final String STATUS_ON = "beating";
   public static final String STATUS_OFF = "off";


   private boolean verbose = false;

////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTORS
////////////////////////////////////////////////////////////////////////////////

   public Heartbeat()
   {

   }

////////////////////////////////////////////////////////////////////////////////
// Public Methods
////////////////////////////////////////////////////////////////////////////////

   public final int getSeconds()
   {
      return this.thisSecond;
      //return( Integer.toString( this.thisSecond ) );
   }

   public final int getCurrentMinute()
   {
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
    * Gets a time adjusted
    * @return
    */
   public static final long getThreadSleepTime( long wakeupMills) throws
      Exception
   {
      //get the current time in mills
      GregorianCalendar calendar = new GregorianCalendar();
      long currentMills = calendar.getTimeInMillis();

      //if the time they want to wake up is before the current, then go to the next minute
      //do this as a while loop on the off chance that we are alerady too close to the next minute
      while( wakeupMills < currentMills )
      {
         log.error( "The update took too long -- going to the next minute" );
         wakeupMills = Heartbeat.getNextMinuteWakeupTime( );
         log.error( "wakeupMills [" + wakeupMills + "]; wakeupMills < currentMills [" + ( wakeupMills < currentMills ) + "]" );
      }

      //compute the milliseconds they need to sleep
      //add an extra 10 milliseconds just to make sure they don't wake up too early
      long actualSleepMills = wakeupMills - currentMills + 20;

      return( actualSleepMills );
   }

   /**
    * Returns a long that identifies when the next 'top of the minute' is in milliseconds.
    * What it does is gets the current minute, adds one to it, then gets that time as a
    * long.
    *
    * @return
    */
   public static final long getNextMinuteWakeupTime(  )
   {
      return Heartbeat.getNextMinuteWakeupTime( 1 );
   }

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
   public static final long getNextMinuteWakeupTime( int offset_minutes )
   {
      //get the current time
      GregorianCalendar calendar = new GregorianCalendar();
      int year = calendar.get( Calendar.YEAR );
      int month = calendar.get( Calendar.MONTH );
      int day = calendar.get( Calendar.DAY_OF_MONTH );
      int hour = calendar.get( Calendar.HOUR_OF_DAY );
      int minute = calendar.get( Calendar.MINUTE );

      //roll forward to the next minute
      GregorianCalendar calendarNext = new GregorianCalendar( year, month, day, hour, minute+offset_minutes );
      return calendarNext.getTimeInMillis();
   }

   /**
    * Turn on the verbose flag.  Doing this will start the console output
    * that outputs a message for each "heartbeat".
    */
   public final void turnVerboseOn()
   {
      this.verbose = true;
   }

   /**
    * Turn off the verbose flag.  Doing this will end the console output
    * that outputs a message for each "heartbeat".
    */
   public final void turnVerboseOff()
   {
      this.verbose = false;
   }

   /**
    * Return a string representation of this object.
    * @return String
    */
   public final String toString()
   {
      if( this.status.equals( STATUS_ON ) )
      {
         return( CLASSNAME + ": " + this.status + ": Time: [" + this.thisTime +
                 "]; System Time [" + System.currentTimeMillis() +
                 "]; Sync Iteration [" + this.currentSyncIteration + "]" );
      }
      else
      {
         return( CLASSNAME + ": " + this.status );
      }
   }

////////////////////////////////////////////////////////////////////////////////
// THREAD PROCESSING METHODS
////////////////////////////////////////////////////////////////////////////////

   /**
    * Thread interface method that performs the thread processing.
    */
   public final void run()
   {
      while( !this.kill )
      {
         _setStatusOn();
         try
         {
            _updateSelf();
            //if(verbose)  log.debug( ">>> "+this.toString() );
            this.sleep( this._sync() );
         }
         catch( Throwable ex )
         {
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
   public final void kill()
   {
      this.kill = true;
   }

////////////////////////////////////////////////////////////////////////////////
// PRIVATE METHODS
////////////////////////////////////////////////////////////////////////////////


   /**
    * Do the work of updating the values of this object.
    */
   private final void _updateSelf()
   {
      Calendar calendar = new GregorianCalendar();
      Date trialTime = new Date();
      calendar.setTime( trialTime );

      // get the Hour
      Heartbeat.thisHour = calendar.get( Calendar.HOUR_OF_DAY );
      // get the Minute
      Heartbeat.thisMinute = calendar.get( Calendar.MINUTE );
      // get the Second
      Heartbeat.thisSecond = calendar.get( Calendar.SECOND );
      // get the datetime
      Heartbeat.thisTime = trialTime.toString();
   }

   /**
    * The purpose of this method is to synchronize the heart-beat up to the
    * second so that it doesn't get out of whack.
    * @return The sleep time.
    */
   private final long _sync()
   {
      try
      {
         if( ! ( this.currentSyncIteration < this.syncIterations ) )
         {
            // reset the synch iteration back to zero.
            this.currentSyncIteration = 0;
            // figure out the time difference
            String stDiff = String.valueOf( System.currentTimeMillis() );
            long diff = Long.parseLong( stDiff.substring( stDiff.length() - 3,
               stDiff.length() ) );
            // if the diff is only off by 10 miliseconds, either side, then
            // do nothing by returning the standard sleep time of one second.
            if( diff > 889 || diff < 11 )
            {
               return( this.sleepTime );
            }
            // get the delta that should be used to adjust the sleep time.
            long delta = this.sleepTime - diff;
            // if the sleep time is less than half a second then we are
            // really out of whack, so sync up to the next second.
            if( delta < 500 )
            {
               delta += this.sleepTime;
// log.debug( CLASS_LABEL + ": Sync'ing: diff=" + diff + "; delta=" + delta );
               // return sync'ed sleep time
            }
            return( delta );
         }
      }
      catch( Throwable t )
      {
         // on exception reset sync iteration and try again next time.
         //if(verbose)  log.debug( CLASS_LABEL + ": Sync Exception: ["+t.getClass().getName()+"]: ["+t.getMessage()+"]" );
         this.currentSyncIteration = 0;
      }
      //
      this.currentSyncIteration++;
      // sync'ing didn't occur, return standard sleep iteration
// log.debug( CLASS_LABEL + ": Sync'ing: [" + this.sleepTime + "]" );
      return( this.sleepTime );
   }

   /**
    * Turn current status on.
    */
   private final void _setStatusOn()
   {
      this.status = STATUS_ON;
   }

   /**
    * Turn current status off.
    */
   private final void _setStatusOff()
   {
      this.status = STATUS_OFF;
   }

///////////////////////////////////////////////////////////////////////////////////////
// static methods
///////////////////////////////////////////////////////////////////////////////////////
   /**
    * This method returns a current date of the format specifically needed by the
    * market for the watchers.
    */
   public static final String getCurrentMarketDate()
   {
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
      String sYear = Integer.toString( calendar.get( Calendar.YEAR ) );

      //get the month = add one since it is zero based
      String sMonth = "";
      int thisMonth = calendar.get( Calendar.MONTH ) + 1;
      if( thisMonth < 10 )
      {
         sMonth = "0" + Integer.toString( thisMonth ); //add leading zeros
      }
      else
      {
         sMonth = Integer.toString( thisMonth );
      }

      //get the day
      String sDay = "";
      int thisDay = calendar.get( Calendar.DAY_OF_MONTH );
      if( thisDay < 10 )
      {
         sDay = "0" + Integer.toString( thisDay );
      }
      else
      {
         sDay = Integer.toString( thisDay );
      }

      return sYear + sMonth + sDay;
   }

   /**
    * This method returns an int that corresponds to the day of week:
    * 1 - Sunday
    * 2 - Monday, etc...
    */
   public static final int getDayOfWeek()
   {
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
   public static final String getCurrentMarketTime()
   {
      return getCurrentMarketTime( ":" );
   }

   /**
    * This method returns a current time of the format specifically needed by the
    * market for the watchers.
    */
   public static final String getCurrentMarketTime( String delim )
   {
		String returnTime = "";

	   SimpleDateFormat formatter = new SimpleDateFormat( "kk"+delim+"mm"+delim+"ss" );
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
/**  OLD AND DEPRECATED
	public static final String formatTimeHHMMSS( String delim, Calendar calendar, Date trialTime )
	{
//		Calendar calendar = new ();
//		Date trialTime = new Date();
		calendar.setTime( trialTime );

		//get the Hour
		String sHour = "";
		int thisHour = calendar.get( Calendar.HOUR_OF_DAY );
		if( thisHour < 10 )
		{
			sHour = "0" + Integer.toString( thisHour ); //add leading zeros
		}
		else
		{
			sHour = Integer.toString( thisHour );
		}

		//get the Minute
		String sMinute = "";
		int thisMinute = calendar.get( Calendar.MINUTE );
		if( thisMinute < 10 )
		{
			sMinute = "0" + Integer.toString( thisMinute ); //add leading zeros
		}
		else
		{
			sMinute = Integer.toString( thisMinute );
		}

		//get the Second
		String sSecond = "";
		int thisSecond = calendar.get( Calendar.SECOND );
		if( thisSecond < 10 )
		{
			sSecond = "0" + Integer.toString( thisSecond ); //add leading zeros
		}
		else
		{
			sSecond = Integer.toString( thisSecond );
		}

		return sHour + delim + sMinute + delim + sSecond;
	}
**/
}
