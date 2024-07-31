package com.greenmark.utils.trace;

import java.util.Date;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: Timer</p>
 * <p>Description: This class is useful in testing the performance of your Java code.  It maintains a list of timers
 * that you can set start and end timestamps for.  Then you can generate HTML or text reports to view
 * the processing time calculated by the timers.  NOTE: The timers are based on milliseconds.  This
 * means that they are not too terribly accurate.  The purpose of this class is to find bottle-necks
 * or logic inefficiencies in your code, not to give absolute execution performance analysis.  Load
 * testing should be used to determine the performance of your application.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class Timer {
    public static final String CLASSNAME = "Timer";
    /**
     * This is the value returned by Timer.getTimer() if this timer is currently testing.
     */
    public static final int CURRENTLY_TIMING = -1;
    /**
     * This is the value returned by Timer.getTimer() if the timer has not yet been started.
     */
    public static final int TIMER_NOT_STARTED = -2;
    /**
     * This is the value returned by Timer.getTimer() if the start timer is invalid.
     */
    public static final int START_TIMER_INVALID = -3;
    /**
     * This is the value returned by Timer.getTimer() if the end timer is invalid.
     */
    public static final int END_TIMER_INVALID = -4;
    /**
     * Use this value to determine if Timer.getTimer() has returned an error. The conditional test will look something like this: if( timer.getTimer() < timer.NO_ERROR ) { [do error processing here]
     * }.
     */
    public static final int NO_ERROR = 0;
    // error messages
    private static final String MSG_CURRENT = "Currently timing";
    private static final String MSG_NOTSTARTED = "Timer not started";
    private static final String MSG_INVALID = "Timer invalid";
    private static final String MSG_NOERROR = "";

    // class constants
    // class variables
    private Date start;
    private Date end;
    private final String label;
    private boolean areWeCurrentlyTiming = false;
    private boolean didWeTimeAnythingYet = false;

    /////////////////////////////////////////////////
    // CONSTRUCTORS
    /////////////////////////////////////////////////

    //////////////////////////////

    /**
     * Create a new Timer object. Defaults to 20 timers (timers 0 through 19).
     */
    public Timer(String label) {
        this.label = label;
    }

    /////////////////////////////////////////////////
    // PUBLIC METHODS
    /////////////////////////////////////////////////

    //////////////////////////////

    private static int _getError(Timer self) {
        // test to validate that we can return a timer
        if (self.areWeCurrentlyTiming) {
            return (Timer.CURRENTLY_TIMING);
        }
        if (!self.didWeTimeAnythingYet) {
            return (Timer.TIMER_NOT_STARTED);
        }
        if (self.start == null) {
            return (Timer.START_TIMER_INVALID);
        }
        if (self.end == null) {
            return (Timer.END_TIMER_INVALID);
        }
        return (Timer.NO_ERROR);
    }

    //////////////////////////////

    //
    // do the work of getting the timer
    //
    private static long _getTimer(Timer self) {
        // do error checking.
        long error = Timer._getError(self);
        if (error < Timer.NO_ERROR) {
            return (error);
        }

        // okay, tests successful, lets return the value
        long product = self.end.getTime() - self.start.getTime();
        if (product < 0) {
            return (0);
        } else {
            return (product);
        }
    }

    //////////////////////////////

    /**
     * Returns and error message if an error condition exists.
     */
    public String getErrorMessage() {
        int error = Timer._getError(this);
        String message;
        // figure out if an error message instead of the number should
        // be returned
        Long ms = Long.valueOf(getTimerResult());
        switch (ms.intValue()) {
            case Timer.CURRENTLY_TIMING:
                message = Timer.MSG_CURRENT;
                break;
            case Timer.TIMER_NOT_STARTED:
                message = Timer.MSG_NOTSTARTED;
                break;
            case Timer.START_TIMER_INVALID:
                message = Timer.MSG_INVALID;
                break;
            case Timer.END_TIMER_INVALID:
                message = Timer.MSG_INVALID;
                break;
            default:
                message = Timer.MSG_NOERROR;
                break;
        }
        return (message);
    }

    //////////////////////////////

    /**
     * Return the label for this timer.
     */
    public String getLabel() {
        return (this.label);
    }

    //////////////////////////////

    /**
     * Set the start timestamp for a timer
     *
     * @param <CODE>timer</CODE> int value of the timer to start.
     */
    public void start() {
        this.areWeCurrentlyTiming = true;
        this.didWeTimeAnythingYet = false;
        this.start = new Date();
    }

    //////////////////////////////

    /**
     * Set the end timestamp for a timer
     *
     * @param <CODE>timer</CODE> int value of the timer to end.
     */
    public void end() {
        this.end = new Date();
        this.areWeCurrentlyTiming = false;
        this.didWeTimeAnythingYet = true;
    }

    /////////////////////////////////////////////////
    // PRIVATE METHODS
    /////////////////////////////////////////////////

    /**
     * Get the result for a timer.
     *
     * @param <CODE>timer</CODE> The timer.
     */
    public long getTimerResult() {
        return (Timer._getTimer(this));
    }

    /**
     * Get the result for a timer.
     *
     * @param <CODE>timer</CODE> The timer.
     */
    public String getTimerResultAsString() {
        long result = Timer._getTimer(this);
        return Long.toString(result);
    }

}
