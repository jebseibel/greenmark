package com.greenmark.utils.trace;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: TraceParameters</p>
 * <p>Description: This class contains the parameters used by the Trace object when it is initialized.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
public class TraceParameters {
	public static final String CLASSNAME = "TraceParameters";

	public String saveMessagesToFile = ""; // Turning this off improves the speed of historical scenarios due to no disk I/O.

	public String messagesOn = ""; // Turning this off improves the speed of historical scenarios because no string manipulation will occur.

	public String timerOn = "";
	public String systemOut = "";

	private String logLevel = ""; // One of MODEL, ANALYSIS, SPARSE, VERBOSE.

	private boolean isHistorical = false;

	/**
	 * This class is used to remove the dependency of trace on any other package
	 * 
	 * @param saveMessagesToFile
	 *            int
	 * @param logLevel
	 *            int
	 */
	public TraceParameters(String saveMessagesToFile, String logLevel, String messagesOn, String timerOn, String systemOut) {
		this.saveMessagesToFile = saveMessagesToFile;
		this.logLevel = logLevel;
		this.messagesOn = messagesOn;
		this.timerOn = timerOn;
		this.systemOut = systemOut;
	}

	public String getSaveMessagesToFile() {
		return saveMessagesToFile;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public boolean IsHistorical() {
		return isHistorical;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public void setSaveMessagesToFile(String saveMessagesToFile) {
		this.saveMessagesToFile = saveMessagesToFile;
	}

	public void setIsHistorical(boolean isHistorical) {
		this.isHistorical = isHistorical;
	}

	public String getMessagesOn() {
		return messagesOn;
	}

	public void setMessagesOn(String messagesOn) {
		this.messagesOn = messagesOn;
	}

	public String getTimerOn() {
		return timerOn;
	}

	public void setTimerOn(String timerOn) {
		this.timerOn = timerOn;
	}

	public String getSystemOut() {
		return systemOut;
	}

	public void setSystemOut(String systemOut) {
		this.systemOut = systemOut;
	}

}
