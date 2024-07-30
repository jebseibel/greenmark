package com.greenmark.utils.trace;

import java.util.Hashtable;
import java.util.Vector;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: TimerManager</p>
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

public class TimerManager {
	public static final String CLASSNAME = "TimerManager";

	// class variables
	private final Hashtable timers = new Hashtable();
	private final Vector timersOrder = new Vector();

	/////////////////////////////////////////////////
	// CONSTRUCTORS
	/////////////////////////////////////////////////

	//////////////////////////////
	/**
	 * Create a new TimerManager object. Defaults to 20 timers (timers 0 through 19).
	 */
	public TimerManager() {
	}

	/////////////////////////////////////////////////
	// PUBLIC METHODS
	/////////////////////////////////////////////////

	//////////////////////////////
	/**
	 * Set the start timestamp for a timer
	 * 
	 * @param <CODE>label</CODE>
	 *            String value of the timer to start.
	 */
	public String startTimer(String label) {
		if (timers.containsKey(label)) {
			// use the label to get the number and start it.
			Timer timer = (Timer) this.timers.get(label);
			timer.start();
		} else {
			// add a new timer, then start it
			Timer timer = new Timer(label);
			this.timers.put(label, timer);
			this.timersOrder.add(label);
			timer.start();
		}
		return "Timer " + label + " has been started.";
	}

	//////////////////////////////
	/**
	 * Set the end timestamp for a timer
	 * 
	 * @param <CODE>label</CODE>
	 *            String value of the timer to end.
	 */
	public String stopTimer(String label) {
		if (this.timers.containsKey(label)) {
			Timer timer = (Timer) this.timers.get(label);
			timer.end();
			String timerresult = timer.getTimerResultAsString();
			this.timers.remove(label);
			return "Timer " + label + " has been stopped. Time result=[" + timerresult + "]";
		}
		return "There is no timer by the name [" + label + "] to stop.";
	}

	//////////////////////////////
	/**
	 * Checks if there is a timer by that name.
	 *
	 * @param <CODE>label</CODE>
	 *            Label of the timer.
	 */
	public boolean hasTimer(String label) {
        return this.timers.containsKey(label);
    }

	//////////////////////////////
	/**
	 * Returns all timer labels found.
	 */
	public Vector getTimerNames() {
		return (this.timersOrder);
	}

	/**
	 * Get the result for a timer and returns it as a String.
	 * 
	 * @param <CODE>timer</CODE>
	 *            The timer.
	 */
	public String getTimerResultString(String label) {
		if (this.timers.containsKey(label)) {
			Timer timer = (Timer) this.timers.get(label);
			String error = timer.getErrorMessage();
			if (error.length() > 0) {
				return (error);
			} else {
				Long timerLong = Long.valueOf(timer.getTimerResult());
				return (timerLong.toString());
			}
		} else {
			return ("");
		}
	}

}
