package com.greenmark.utils.trace;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import com.greenmark.common.enums.GreenmanServiceEnum;
import com.greenmark.common.service.ApplicationDataContext;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTPropertyManager;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: </p>
 * <p>Description: This object is used to collect, log and/or display debug output
 * from the transaction.  The trace messages we use can be used for debugging, but are mostly used to output logfile messages that contain the reasons the strategy makes decisions for a security in
 * the trading client.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class Trace {
	public static final String CLASSNAME = "Trace";

	public static final boolean PRINT_WEBSITE_DEBUG = false;
	public static final boolean PRINT_DEBUG_PRICES_DATA = false;

	public static final boolean PRINT_DEBUG_CHARTS = false;
	public static final boolean PRINT_DEBUG_PDB_UPDATER = true;
	public static final boolean PRINT_DEBUG_STRATEGY = false;
	public static final boolean PRINT_DEBUG_ACCOUNT_MGMT = false;
	public static final boolean PRINT_DEBUG_BROKER = false;

	public static final boolean PRINT_DEBUG_DATAFEED = false;

	// use booleans here for speed of comparisions
	protected boolean guiOut = false;
	protected boolean systemOut = false;
	protected boolean messageOn = false;
	protected boolean timerOn = false;

	protected boolean verboseOn = false;

	// this is a timer
	protected TimerManager tracetimer;

	protected static Vector listeners = new Vector();
	protected int loglevel = 4;

	// trace log file - this is the one for internal use
	protected FileWriter loggerTrace;
	protected String tracefilename = "";
	protected boolean saveTraceMessagesToFile = false;

	// trace log file - this is the one for internal use
	protected FileWriter loggerDaily;
	protected String dailyfilename = "";
	protected boolean saveDailyMessagesToFile = true;

	// alternate logger (Used for pdbUpdater Service for now.)
	protected FileWriter loggerAlternate;
	public boolean saveAlternateMessages = false; // This is set to true when an app adds an alternate logger.

	protected boolean saveToWebsiteStack = false;
	protected Vector websiteStack;

	// constants
	private static final String EMPTY_STRING = new String("");
	private static final String ENTRY_STRING = new String("---ENTERING: ");
	private static final String EXIT_STRING = new String("---EXITING: ");
	private static final String SEPARATER = new String("##########################################################################");
	private static final String TAB = new String("   ");
	private static final String INDENT = new String("   ");
	private static final String LS = System.getProperty("line.separator");
	private static final String DELIM_PARAMETERS = "/";
	private static final String DEFAULT_CONFIG = "messages/timers";
	private static final String FONTCOLOR_TIMER = "#cccccc";

	/** level for fatal programming statements */
	public static final int LOGLEVEL_FATAL = 5;

	/** level for model logic statements only (chris) */
	public static final int LOGLEVEL_MODEL = 4;

	/** level for chris model logic, indicators and values used by indicators (chris) */
	public static final int LOGLEVEL_ANALYSIS = 3;

	/** level for basic programming statements */
	public static final int LOGLEVEL_SW_SPARSE = 2;

	/** level for verbose programming statements */
	public static final int LOGLEVEL_SW_VERBOSE = 1;

	/** global indicator for messages without level indicated */
	public static final int LOGLEVEL_DEFAULT = 3;

	/////////////////////////////////////////////////
	// CONSTRUCTORS
	/////////////////////////////////////////////////

	public Trace() {
	}

	/**
	 * Constructs a new instance of Trace with all trace functions turned off.
	 * 
	 * Only used by Config.initialized. ALL OTHER METHODS must use Config.getConfiguredTrace() to get a new trace object.
	 */
	public Trace(TraceParameters traceParameters, GreenmanServiceEnum service) {
		this.tracetimer = new TimerManager();
		this.getConfigParameters(traceParameters, service);
	}

	/**
	 * FOR UNIT TESTS ONLY.
	 * 
	 * Constructs a new instance of Trace and uses a passed in parameters to configure it. This constructor does not use the Config object and is meant FOR UNIT TESTS ONLY.
	 * <p>
	 * For more information see the method configure().
	 */
	public Trace(String parameters) {
		this.tracetimer = new TimerManager();

		// only go farther if parameters are not empty
		if (TraceUtils.isNotNorE(parameters)) {
			this._configure(parameters);
		}
	}

	/**
	 * @todo Fill in this stub to dump the Trace info to the JSP Don't think that imagePath should be required. If no trace output, return the relative pathname to the Spacer.gif, so the html table
	 *       will look ok.
	 **/
	public final String toStringHtml(String imagePath) {
		return ("");
	}

	public final String getHtmlString() {

		StringBuffer outString = new StringBuffer();

		Iterator msgIter = websiteStack.iterator();

		if (msgIter.hasNext() == false) // There are no messages, return blank space.
			return "&nbsp;";

		if (this.getGlobalMessageOn()) {
			while (msgIter.hasNext()) {
				outString.append(msgIter.next());
				outString.append("<BR>");
			}
		}

		return outString.toString();
	}

	private final void getConfigParameters(TraceParameters traceParameters, GreenmanServiceEnum service) {
		String methodname = "getConfigParameters";
		try {
			// set the log level to a default model
			this.loglevel = Trace.LOGLEVEL_MODEL;

			// TRACE LOGFILE
			// get the log filename(s) from the property file
			String logFolderName = UTPropertyManager.getProperty("logs_directory");

			switch (service) {
			case BROKER:
				logFolderName += "/" + service.getLogSubfolderName() + "/broker_logfile";
				break;
			case NOTIFICATION:
				logFolderName += "/" + service.getLogSubfolderName() + "/notification_logfile";
				break;
			case TRADING_CLIENT:
				logFolderName += "/" + service.getLogSubfolderName() + "/tradingclient_logfile";
				break;
			case PDB_UPDATER:
				logFolderName += "/" + service.getLogSubfolderName() + "/pdbupdater_logfile";
				break;
			case CHARTS:
				logFolderName += "/" + service.getLogSubfolderName() + "/charts_logfile";
				break;
			case WEBAPP:
				logFolderName += "/" + service.getLogSubfolderName() + "/webapp_logfile";
				break;
			}

			// get the file and set it to append - even if log to file is not on.
			this.tracefilename = logFolderName + "_" + UTDatetime.getCurrentDateHourMinuteForFilename() + ".log";

			if (ApplicationDataContext.isHistoricalDatafeed()) {
				// Set default filename for historical scenarios in case the next few calls fail.
				this.tracefilename = logFolderName + "_" + UTDatetime.getCurrentDateHourMinuteForFilename() + "_Historical_Scenario.log";
			} // Endif historical datafeed

			this.loggerTrace = new FileWriter(tracefilename, true);
			this.loggerTrace.write("Reopening file at [" + UTDatetime.getCurrentTime() + "]\n");

			// DAILY LOGFILE
			// get the log filename(s) from the property file
			String strDailyFilename = UTPropertyManager.getProperty("logs_directory");
			strDailyFilename += "/daily_transaction_logs/gman_daily_logfile";

			// get the file and set it to append - even if log to file is not on.
			this.dailyfilename = strDailyFilename + "_" + UTDatetime.getCurrentDateHourMinuteForFilename() + ".log";

			if (ApplicationDataContext.isHistoricalDatafeed()) {
				// Set default filename for historical scenarios in case the next few calls fail.
				this.dailyfilename = strDailyFilename + "_" + UTDatetime.getCurrentDateHourMinuteForFilename() + "_Historical_Scenario.log";
			} // Endif historical datafeed

			this.loggerDaily = new FileWriter(dailyfilename, true);
			this.loggerDaily.write("Reopening file at [" + UTDatetime.getCurrentTime() + "]\n");

			// ceck to see if we should log by looking at the current model config
			// String flagLogtofile = Config.getValue( Config.CONFIG_SAVE_MSGS_TO_LOGFILE );
			String flagLogtofile = traceParameters.getSaveMessagesToFile();
			if (flagLogtofile.equals("1")) {
				this.saveTraceMessagesToFile = true;
			}

			String messagesOn = traceParameters.getMessagesOn();
			if (messagesOn.equals("1")) {
				this.messageOn = true;
			}

			String timerOn = traceParameters.getTimerOn();
			if (timerOn.equals("1")) {
				this.timerOn = true;
			}

			String systemOut = traceParameters.getSystemOut();
			if (systemOut.equals("1")) {
				this.systemOut = true;
			}

			// configure the log level - get it from the model config
			// String strlevel = Config.getValue( Config.CONFIG_TRACE_LOGLEVEL ).toUpperCase();
			String strlevel = traceParameters.getLogLevel();
			if (strlevel.equals("VERBOSE")) {
				this.loglevel = Trace.LOGLEVEL_SW_VERBOSE;
			} else if (strlevel.equals("SPARSE")) {
				this.loglevel = Trace.LOGLEVEL_SW_SPARSE;
			} else if (strlevel.equals("ANALYSIS")) {
				this.loglevel = Trace.LOGLEVEL_ANALYSIS;
			} else if (strlevel.equals("MODEL")) {
				this.loglevel = Trace.LOGLEVEL_MODEL;
			} else if (strlevel.equals("FATAL")) {
				this.loglevel = Trace.LOGLEVEL_FATAL;
			} else {
				this.loglevel = Trace.LOGLEVEL_ANALYSIS;
			}
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + " Message: [" + e.getMessage() + "]");
		}
	}

	/////////////////////////////////////////////////
	// PUBLIC METHODS
	/////////////////////////////////////////////////

	/**
	 *
	 * @param trace
	 */
	public void addListener(TraceMessageListener listener) {
		// there are too many places this is set (addListener for frame)
		// add an extra check here
		if (ApplicationDataContext.isHistoricalDatafeed()) {
			if (listener.getListenerName().equals("MainFrame")) {
				return;
			}
		}
		if (this.listeners.contains(listener) == false) // Don't keep adding them if they're there.
			this.listeners.add(listener);
	}

	public void removeListener(TraceMessageListener listener) {
		if (this.listeners.contains(listener)) // Only try to remove them if they're there.
			this.listeners.remove(listener);
	}

	/**
	 * Add a line that is an exception to the trace object.
	 * <P>
	 * However, a line is only added when either global message is turned on.
	 *
	 * @param <code>line</code>
	 *            - a string object to be added
	 * @return boolean - true if the line has been added, or false if not added
	 */
	public boolean addException(String line) {
		return this._add(line);
	}

	/**
	 * Add a message directly to the alternate logger
	 * 
	 * @param line
	 *            String
	 * @return
	 */
	public void addAlternate(String line) {
		// add it to the trace logger
		try {
			this.loggerAlternate.write(line + "\n");
			this.loggerAlternate.flush();
		} catch (Exception e) {
			System.out.println("Exception writing to the alternate logger");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Add a line to the trace object. If the logging is on, it logs it to the level *model*. However, a line is only added when either global message is turned on.
	 * <P>
	 * 
	 * @param <code>line</code>
	 *            - a string object to be added
	 * @return boolean - true if the line has been added, or false if not added
	 */
	public void addModel(String line) {
		if (isLevelModel()) {
			this._add(line);
			this._addDaily(line);
		}
		return;
	}

	public boolean isLevelModel() {
		// We're not wanting to log any messages in historical mode, if we aren't storing anything in a file.
		if ((ApplicationDataContext.isHistoricalDatafeed()) && (saveTraceMessagesToFile == false))
			return false;

		if (messageOn == false) // We're not logging messages if the user turned off all messaging.
			return false;

		if (this.loglevel <= LOGLEVEL_MODEL)
			return true;
		return false;
	}

	/**
	 * Add a line to the trace object. If the logging is on, it logs it to the level *analysis*. However, a line is only added when either global message is turned on.
	 * <P>
	 * 
	 * @param <code>line</code>
	 *            - a string object to be added
	 * @return boolean - true if the line has been added, or false if not added
	 */
	public void addAnalysis(String line) {
		if (isLevelAnalysis()) {
			this._add(line);
		}
		return;
	}

	public boolean isLevelAnalysis() {
		// We're not wanting to log any messages in historical mode, if we aren't storing anything in a file.
		if ((ApplicationDataContext.isHistoricalDatafeed()) && (saveTraceMessagesToFile == false))
			return false;

		if (messageOn == false) // We're not logging messages if the user turned off all messaging.
			return false;

		if (this.loglevel <= LOGLEVEL_ANALYSIS)
			return true;
		return false;
	}

	/**
	 * Add a line to the trace object. If the logging is on, it logs it to the level *sw_sparse*. However, a line is only added when either global message is turned on.
	 * <P>
	 * 
	 * @param <code>line</code>
	 *            - a string object to be added
	 * @return boolean - true if the line has been added, or false if not added
	 */
	public void addSparse(String line) {
		if (isLevelSparse()) {
			this._add(line);
		}
		return;
	}

	public boolean isLevelSparse() {
		if (messageOn == false) // We're not logging messages if the user turned off all messaging.
			return false;

		if (this.loglevel <= LOGLEVEL_SW_SPARSE)
			return true;
		return false;
	}

	/**
	 * Add a line to the trace object. If the logging is on, it logs it to the level *sw_verbose*. However, a line is only added when either global message is turned on.
	 * <P>
	 * 
	 * @param <code>line</code>
	 *            - a string object to be added
	 * @return boolean - true if the line has been added, or false if not added
	 */
	public void addVerbose(String line) {
		if (isLevelVerbose()) {
			this._add(line);
		}
		return;
	}

	public boolean isLevelVerbose() {
		// We're not wanting to log any messages in historical mode, if we aren't storing anything in a file.
		if ((ApplicationDataContext.isHistoricalDatafeed()) && (saveTraceMessagesToFile == false))
			return false;

		if (messageOn == false) // We're not logging messages if the user turned off all messaging.
			return false;

		if (this.loglevel <= LOGLEVEL_SW_VERBOSE)
			return true;
		return false;
	}

	/**
	 * Add a line to the trace object. If the logging is on, it logs it to the level *fatal*. However, a line is only added when either global message is turned on.
	 * <P>
	 * 
	 * @param <code>line</code>
	 *            - a string object to be added
	 * @return boolean - true if the line has been added, or false if not added
	 */
	public void addFatal(String line) {
		if (isLevelFatal()) {
			this._add(line);
		}
		return;
	}

	public boolean isLevelFatal() {
		if (messageOn == false) // We're not logging messages if the user turned off all messaging.
			return false;

		if (this.loglevel <= LOGLEVEL_FATAL)
			return true;
		return false;
	}

	/**
	 * This method notifies all the listeners of a message to be added
	 * 
	 * @param message
	 *            String
	 */
	private void writeToListeners(String message) {
		for (Enumeration e = this.listeners.elements(); e.hasMoreElements();) {
			TraceMessageListener listener = (TraceMessageListener) e.nextElement();
			listener.addMessage(message);
		}
	}

	// does the work of adding a message
	//
	private boolean _add(String line) {
		// this is for the gui
		if (!listeners.isEmpty()) {
			writeToListeners(line);
		} else {
			System.out.println(line);
		}

		if (this.saveToWebsiteStack)
			websiteStack.add(line);

		// add it to the trace logger
		if (this.saveTraceMessagesToFile && this.loggerTrace != null) {
			try {
				this.loggerTrace.write(line + "\n");
				this.loggerTrace.flush();
			} catch (Exception e) {
				System.out.println("Exception writing to the Trace.logger");
				System.out.println(e.getMessage());
			}
		}

		// add it to the alternate logger
		if (this.saveAlternateMessages && this.loggerAlternate != null) {
			try {
				this.loggerAlternate.write(line + "\n");
				this.loggerAlternate.flush();
			} catch (Exception e) {
				System.out.println("Exception writing to the alternate logger");
				System.out.println(e.getMessage());
			}
		}

		if (this.saveDailyMessagesToFile && this.loggerDaily != null) {
			try {
				this.loggerDaily.write(line + "\n");
				this.loggerDaily.flush();
			} catch (Exception e) {
				System.out.println("Exception writing to the daily logger");
				System.out.println(e.getMessage());
			}
		}

		return (true);
	}

	//
	// does the work of adding a message
	//
	private boolean _add_internal(String line, int loglevel) {
		// this is for the gui
		if (this.loglevel <= loglevel) {
			if (!listeners.isEmpty()) {
				writeToListeners(line);
			}
		}
		return (true);
	}

	// does the work of adding a daily message
	//
	private boolean _addDaily(String line) {
		// add it to the daily logger
		if (this.saveDailyMessagesToFile) {
			try {
				this.loggerDaily.write(line + "\n");
				this.loggerDaily.flush();
			} catch (Exception e) {
				// System.out.println( "Exception writing to the Daily.logger" ) ;
				// System.out.println( e.getMessage() ) ;
			}
		}

		return (true);
	}

	////////////////////////////// SystemOut //////////////////////////////
	/**
	 * This method allows you to set the SystemOut value on; i.e. all trace messages that are added to this object are also printed to the System.out.println.
	 */
	public void setSystemOutOn() {
		this._add_internal("TRACE: setting system out on", this.loglevel);
		this.systemOut = true;
	}

	/**
	 * This method allows you to set the SystemOut value off; i.e. all trace messages that are added to this object are NOT also printed to the System.out.println.
	 */
	public void setSystemOutOff() {
		this._add_internal("TRACE: setting system out off", this.loglevel);
		this.systemOut = false;
	}

	////////////////////////////// ALL ON ///////////////////////////////////

	/**
	 * This method sets the global message and timer flags on.
	 */
	public void setAllOn() {
		this._add_internal("TRACE: setting global messages and timers on.", this.loglevel);
		this.messageOn = true;
		this.timerOn = true;
		this.systemOut = true;
	}

	/**
	 * This method sets the global message and timer flags on.
	 */
	public void setAllOff() {
		this._add_internal("TRACE: setting global messages and timers off.", this.loglevel);
		this.messageOn = false;
		this.timerOn = false;
		this.systemOut = false;
	}

	////////////////////////////// IS GLOBAL ON ///////////////////////////////////

	/**
	 * This method returns a boolean depending on whether message logging is currently turned on or off.
	 *
	 * @return true if message logging is on, false otherwise
	 */
	public boolean IsMessagesOn() {
		return this.messageOn;
	}

	/**
	 * This method returns a boolean depending on whether timers are currently turned on or off.
	 *
	 * @return true if timers are on, false otherwise
	 */
	public boolean IsTimersOn() {
		return this.timerOn;
	}

	/**
	 * This method returns a boolean depending on the status of the globalMessageFlag.
	 * <p>
	 * True if global message is on, false if it is off.
	 */
	private boolean _isGlobalMessageOn() {
		return this.messageOn;
	}

	/**
	 * This method returns a boolean depending on the status of the globalTimerFlag.
	 * <p>
	 * True if global timer is on, false if it is off.
	 */
	private boolean _isGlobalTimerOn() {
		return this.timerOn;
	}

	////////////////////////////// GlobalMessage //////////////////////////////

	/**
	 * This method turns ALL messages ON.
	 * <p>
	 * This method sets a global message flag that will overwrite any specific class name/method status.
	 */
	public void setGlobalMessageOn() {
		this._add_internal("TRACE: setting global message on", this.loglevel);
		this.messageOn = true;
	}

	public boolean getGlobalMessageOn() {
		return this.messageOn;
	}

	/**
	 * This method turns ALL messages OFF.
	 * <p>
	 * This method sets a global message flag that will overwrite any specific class name/method status.
	 */
	public void setGlobalMessageOff() {
		this._add_internal("TRACE: setting global message off", this.loglevel);
		this.messageOn = false;
	}

	////////////////////////////// GuiOutput //////////////////////////////

	/**
	 * This method turns ALL Gui Output messages ON.
	 */
	public void setGuiMessageOn() {
		this._add_internal("TRACE: setting global message on", this.loglevel);
		this.messageOn = true;
	}

	/**
	 * This method turns ALL Gui Output messages OFF.
	 */
	public void setGuiMessageOff() {
		this._add_internal("TRACE: setting global message off", this.loglevel);
		this.messageOn = false;
	}

	////////////////////////////// GuiLogLevel //////////////////////////////

	/**
	*
	*/
	public void setGuiLogLevel(int guiloglevel) {
		String msg = "Setting Gui Log level to  [" + Trace.getGuiOutputLevelAsString(guiloglevel) + "]";
		// System.out.println( msg );
		// this._add(msg);
		this.loglevel = guiloglevel;
	}

	/**
	*
	*/
	public int getGuiOutputLevel() {
		return loglevel;
	}

	/**
	*
	*/
	public String getGuiOutputLevelAsString() {
		if (loglevel == LOGLEVEL_MODEL)
			return "LOGLEVEL_MODEL";
		else if (loglevel == LOGLEVEL_ANALYSIS)
			return "LOGLEVEL_ANALYSIS";
		else if (loglevel == LOGLEVEL_SW_SPARSE)
			return "LOGLEVEL_SW_SPARSE";
		else if (loglevel == LOGLEVEL_SW_VERBOSE)
			return "LOGLEVEL_SW_VERBOSE";
		else
			return "none";
	}

	/**
	*
	*/
	public static final String getGuiOutputLevelAsString(int thisLogLevel) {
		if (thisLogLevel == LOGLEVEL_MODEL)
			return "LOGLEVEL_MODEL";
		else if (thisLogLevel == LOGLEVEL_ANALYSIS)
			return "LOGLEVEL_ANALYSIS";
		else if (thisLogLevel == LOGLEVEL_SW_SPARSE)
			return "LOGLEVEL_SW_SPARSE";
		else if (thisLogLevel == LOGLEVEL_SW_VERBOSE)
			return "LOGLEVEL_SW_VERBOSE";
		else if (thisLogLevel == LOGLEVEL_FATAL)
			return "LOGLEVEL_FATAL";
		else
			return "none";
	}

	////////////////////////////// GlobalTimer //////////////////////////////

	/**
	 * This method returns a boolean depending on whether timers are currently turned on or off.
	 *
	 * @return true if timers are is on, false otherwise
	 */
	public boolean TimersIsOn() {
		return this.timerOn;
	}

	/**
	 * This method sets a global timer flag that will overwrite any specific class name/method status. Using this method turns ALL timers ON.
	 */
	public void setGlobalTimerOn() {
		this._add_internal("TRACE: setting global timer on", this.loglevel);
		this.timerOn = true;
	}

	/**
	 * This method sets a global message flag that will overwrite any specific class name/method status. Using this method turns ALL trace messages OFF.
	 */
	public void setGlobalTimerOff() {
		this._add_internal("TRACE: setting global timer off", this.loglevel);
		this.timerOn = false;
	}

	////////////////////////////////////////////////////////////

	/**
	 * The trace in method notifies the trace object as to where you currently are in the transaction. That is, in order for trace to know if it should be recording information, it has to know if it
	 * is in a location that has asked to be traced. So correct use of the trace object mandates that you tell it where you are when you enter a new locationd. Get it?
	 * <p>
	 * 
	 * @param <CODE>location</CODE>
	 *            - Where you are in the code.
	 */
	public void in(String location) {
		this.in(location, location);
	}

	/**
	 * The trace in method notifies the trace object as to where you currently are in the transaction.
	 * <p>
	 * 
	 * @param <CODE>className</CODE>
	 *            - The name of the class
	 * @param <CODE>classMethod</CODE>
	 *            - The method of the class
	 */
	public void in(String className, String classMethod) {
		// add timer if enabled
		if (this.timerOn) {
			String timermsg = this.tracetimer.startTimer(className + "." + classMethod + "()");
			this._add_internal(timermsg, this.loglevel);
		}

		this.addVerbose(Trace.ENTRY_STRING + className + "." + classMethod + "()");
	}

	/**
	 * This method notifies the trace object that you are exiting a block of code.
	 * <p>
	 * 
	 * @param <CODE>location</CODE>
	 *            - The location of code you are exiting.
	 * 
	 */
	public void out(String location) {
		this.out(location, location);
		return;
	}

	/**
	 * This method notifies the trace object that you are exiting a current class and method
	 * <p>
	 * 
	 * @param <CODE>className</CODE>
	 *            - The name of the class
	 * @param <CODE>classMethod</CODE>
	 *            - The method of the class
	 */
	public void out(String className, String classMethod) {
		// stop timer if enabled
		if (this.timerOn) {
			String timermsg = this.tracetimer.stopTimer(className + "." + classMethod + "()");
			this._add_internal(timermsg, this.loglevel);
		}

		this.addVerbose(Trace.EXIT_STRING + className + "." + classMethod + "()");
	}

	/////////////////////////////////////////////////
	// REPORT METHODS
	/////////////////////////////////////////////////

	////////////////////////////// TIMER CONVENIENCE //////////////////////////////

	/**
	 * Start a timer.
	 * <p>
	 * This method will not start a timer unless one of the following conditions are true.
	 * <ul>
	 * <li>Global timer is on, i.e. timers are on for all transactions.
	 * <li>Transaction timer is on, i.e. timers are on for this transaction.
	 * <li>The current classname/classmethod of this trace object appears in the timerwatch list.
	 * </ul>
	 * <p>
	 * 
	 * @param <CODE>timername</CODE>
	 *            The name of the timer to start.
	 * @see com.disney.dep.util.trace.Timer
	 */
	public void startTimer(String timername) {
		if (this.timerOn) {
			String timermsg = this.tracetimer.startTimer(timername);
			this._add_internal(timermsg, this.loglevel);
		}
	}

	/**
	 * Stop a timer.
	 * <p>
	 * Unlike the start timer function, we don't check to see if any transaction timer conditions need to be met in order to stop a timer. See <code>startTimer</code> in this object. We do this
	 * because you can't stop a timer that doesn't exist....
	 * <p>
	 * 
	 * @param <CODE>timername</CODE>
	 *            The name of the timer to stop.
	 * @see com.disney.dep.util.trace.Timer
	 */
	public void stopTimer(String timername) {
		// there is no switch to check if global timers is on here, since we want to make
		// sure that anything we turn on is turned off, regardless of whether the user
		// turned off the timers option before this was stopped
		if (this.tracetimer.hasTimer(timername)) {
			String timermsg = this.tracetimer.stopTimer(timername);

			// only output a message though if the timerOn is set
			if (this.TimersIsOn()) {
				this._add_internal(timermsg, this.loglevel);
			}
		}
	}

	public String getTimerResult(String timername) {
		// there is no switch to check if global timers is on here, since we want to make
		// sure that anything we turn on is turned off, regardless of whether the user
		// turned off the timers option before this was stopped
		String result = "";
		if (this.tracetimer.hasTimer(timername)) {
			result = this.tracetimer.getTimerResultString(timername);
		}
		return result;
	}

	/**
	 * This method takes a parameter string and uses it to configure the trace object.
	 * <p>
	 * The purpose of this method is to allow quick configuration of the trace object from any servlet or jsp that wants to use it. That is, by adding a a standard formatted parameter to a query
	 * string, you can control what trace does. You will still have to do the job of grabbing that parameter and passing it to this method (unlike its name suggests, it does not take the whole query
	 * string as its input) but this should make life easier.
	 * <p>
	 * When this method receives an input string it assumes a certain format (shown later). Here are the parameters it is looking for:
	 * 
	 * </TD>
	 * <TD>Dumps all messages to system out as they are added to the trace object.</TD>
	 * </TR>
	 * </TABLE>
	 * <p>
	 * <b>SAMPLE CALLS AND EXPLANATIONS</B>
	 * <p>
	 * Case A: <code>www.example.com/servletname?trace=message</code><br>
	 * -- turns tracing on
	 * <p>
	 * Case B: <code>www.example.com/servletname/skin?trace=message/timer/system</code><br>
	 * -- turns tracing on, turns on the timer for this transaction and dumps trace messages to the system out as they are written to trace
	 * <p>
	 * Case B: <code>www.example.com/servletname/skin?trace=message/timer/logfile</code><br>
	 * -- turns tracing on and timer for just the run method of the Servlet class.
	 * <p>
	 */
	public void configure(String parameters, boolean useWebsiteStack) {
		this._configure(parameters);

		this.saveToWebsiteStack = useWebsiteStack;
		if (useWebsiteStack)
			websiteStack = new Vector();
	}

	////////
	private void _configure(String parameters) {
		try {
			Vector traceparams = new Vector();

			// early exit if no parameters
			if (TraceUtils.isNorE(parameters)) {
				return;
			}

			// Begin by getting a list of all parameters
			StringTokenizer tkr = new StringTokenizer(parameters, Trace.DELIM_PARAMETERS);
			int count = tkr.countTokens();

			// early return if no trace params
			if (count == 0) {
				return;
			}

			// pull the admin params off
			for (int i = 1; i <= count; i++) {
				traceparams.addElement(tkr.nextToken());
			}

			String tokens, token, name, valu, param = "";
			int cnt, tindx;

			////////////////////////////////////////////////////
			// SWITCHES
			////////////////////////////////////////////////////
			// loop through the trace params looking for information keys
			for (Enumeration en = traceparams.elements(); en.hasMoreElements();) {
				param = (String) en.nextElement();

				//////////////////////////// MESSAGE ////////////////////////////
				// check to see if messages should be turned ON for ALL transactions.
				if (param.startsWith("message")) {
					this.setGlobalMessageOn();
				}

				//////////////////////////// TIMER ////////////////////////////
				// check to see if the timers should be turned ON for ALL transactions.
				else if (param.startsWith("timer")) {
					this.setGlobalTimerOn();
				}

				//////////////////////////// ALL ////////////////////////////
				// check to see if tracing should be dumped to log4j
				else if (param.startsWith("all")) {
					this.setGlobalMessageOn();
					this.setAllOn();
				}

			}
		} catch (Exception e) {
		}
	}

	public boolean isVerboseOn() {
		return verboseOn;
	}

	public void setVerboseOn(boolean verboseOn) {
		this.verboseOn = verboseOn;
	}

	public String getDailyfilename() {
		return dailyfilename;
	}

	public void setDailyfilename(String dailyfilename) {
		this.dailyfilename = dailyfilename;
	}

	public String getTracefilename() {
		return tracefilename;
	}

	public void setTracefilename(String tracefilename) {
		this.tracefilename = tracefilename;
	}

	public boolean isSaveDailyMessagesToFile() {
		return saveDailyMessagesToFile;
	}

	public boolean isSaveTraceMessagesToFile() {
		return saveTraceMessagesToFile;
	}

	public void setSaveTraceMessagesToFile(boolean saveTraceMessagesToFile) {
		this.saveTraceMessagesToFile = saveTraceMessagesToFile;
	}

	public void setSaveDailyMessagesToFile(boolean saveDailyMessagesToFile) {
		this.saveDailyMessagesToFile = saveDailyMessagesToFile;
	}

	public FileWriter getLoggerTrace() {
		return loggerTrace;
	}

	public void setLoggerTrace(FileWriter loggerTrace) {
		this.loggerTrace = loggerTrace;
	}

	public FileWriter getLoggerDaily() {
		return loggerDaily;
	}

	public void setLoggerDaily(FileWriter loggerDaily) {
		this.loggerDaily = loggerDaily;
	}

	/**
	 * Pass in the path and filename where you want the logging to take place. The method will reopen the file (if its already there) or create the file and begin to log to it.
	 * <p>
	 * to turn off, send in a null value
	 *
	 * @param loggerAlternate
	 *            FileWriter
	 */
	public void setAlternateLogger(String pathAndFileLocation) {
		if (pathAndFileLocation != null) {
			try {
				this.loggerAlternate = new FileWriter(pathAndFileLocation, true);
				this.loggerAlternate.write("\n==========================================================\n");
				this.loggerAlternate.write("Opening file for alternate logging " + UTDatetime.getCurrentTime() + "\n");

				// will only get here if file can be opened
				this.saveAlternateMessages = true;

				// When using the alternate logger, don't use the normal trace logger or we'll see output in both dirs:
				this.saveTraceMessagesToFile = false;
				this.loggerTrace = null;

				// When using the alternate logger, don't use the daily trace logger or we'll see output in both dirs:
				this.saveDailyMessagesToFile = false;
				this.loggerDaily = null;
			} catch (IOException ex) {
				System.out.println("Exception opening alternate logger file [" + ex.getMessage() + "]");
			}
		} else {
			try {
				this.loggerAlternate.close();
			} catch (IOException ex) {
				System.out.println("Exception opening alternate logger file [" + ex.getMessage() + "]");
			}

			this.saveAlternateMessages = false;
		}
	}

	public boolean isSaveToWebsiteStack() {
		return saveToWebsiteStack;
	}

	public void setSaveToWebsiteStack(boolean saveToWebsiteStack) {
		this.saveToWebsiteStack = saveToWebsiteStack;
	}

	public void setTestOn() {
		this.setGuiLogLevel(LOGLEVEL_SW_VERBOSE);
		this.setSystemOutOn();
	}

	public int getLoglevel() {
		return loglevel;
	}

	public boolean getWebsiteDebugOn() {
		return PRINT_WEBSITE_DEBUG;
	}

	public String getLoglevelAsString() {
		if (loglevel == LOGLEVEL_SW_VERBOSE)
			return "Verbose";
		if (loglevel == LOGLEVEL_SW_SPARSE)
			return "Sparse";
		if (loglevel == LOGLEVEL_ANALYSIS)
			return "Analysis";
		if (loglevel == LOGLEVEL_MODEL)
			return "Model";
		if (loglevel == LOGLEVEL_FATAL)
			return "Fatal";
		return "Error";
	}

	public void setLoglevel(int loglevel) {
		this.loglevel = loglevel;
	}

	public boolean getSaveAlternateMessages() {
		return this.saveAlternateMessages;
	}

} // end class
