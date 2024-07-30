package com.greenmark.common.config;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ConfigApplication</p>
 * <p>Description: The constants in this file point to a location in the Strategy XML heirarchy (strategy.xml).  These configuration points
 * are for running the application and scenarios.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ConfigApplication {
	private static final String CLASSNAME = "ConfigApplication";

	public static final String CONFIG_MODEL_ID = "DV:XML.MODEL.MODEL_ID";
	public static final String CONFIG_ACCOUNT_ID = "DV:XML.MODEL.ACCOUNT_ID";
	public static final String CONFIG_SCENARIO_ID = "DV:XML.MODEL.SCENARIO_ID";

	public static final String CONFIG_DISABLE_LOGIN = "DV:XML.CONFIG_NO_DB_RESTORE.APPLICATION.USE_SIMULATOR_REVIEW";
	public static final String CONFIG_DISPLAY_DATAFEED_PRICES = "DV:XML.CONFIG_NO_DB_RESTORE.APPLICATION.DISPLAY_DATAFEED_PRICES";
	public static final String CONFIG_DISPLAY_REALTIME_LOGS = "DV:XML.CONFIG_NO_DB_RESTORE.APPLICATION.DISPLAY_REALTIME_LOGS";

	public static final String CONFIG_TRACE_LOGLEVEL = "DV:XML.CONFIG_NO_DB_RESTORE.TRACE.LOG_LEVEL";

	public static final String CONFIG_SAVE_MSGS_TO_LOGFILE = "DV:XML.CONFIG_NO_DB_RESTORE.TRACE.SAVE_MSGS_TO_LOGFILE";
	public static final String CONFIG_TRACE_MESSAGES_ON = "DV:XML.CONFIG_NO_DB_RESTORE.TRACE.MESSAGES_ON";
	public static final String CONFIG_TRACE_TIMER_ON = "DV:XML.CONFIG_NO_DB_RESTORE.TRACE.TIMER_ON";
	public static final String CONFIG_TRACE_SYSTEM_OUT = "DV:XML.CONFIG_NO_DB_RESTORE.TRACE.SYSTEM_OUT";

	public static final String CONFIG_USE_MESSAGE_PANEL = "DV:XML.CONFIG_NO_DB_RESTORE.APPLICATION.USE_MESSAGE_PANEL";
	public static final String CONFIG_HISTORICAL_GUI_MODE = "DV:XML.CONFIG_NO_DB_RESTORE.APPLICATION.HISTORICAL_GUI_MODE";

	public static final String CONFIG_HISTORICAL_GUI_ON = "GUI ON";
	public static final String CONFIG_HISTORICAL_GUI_OFF = "GUI OFF";
	public static final String CONFIG_HISTORICAL_GUI_SUMMARY_ONLY = "OFF - DAILY SUMMARY ONLY";

	public static final String CONFIG_APPLICATION_DISABLE_POPUP = "DV:XML.CONFIG_NO_DB_RESTORE.APPLICATION.DISABLE_POPUPS";

	public static final String CONFIG_SCENARIO_NO_STARTUP_CONFIRMATIONS = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO.NO_STARTUP_CONFIRM_DIALOGS";

	public static final String CONFIG_SCENARIO_START_CASH = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO.START_CASH";
	public static final String CONFIG_SCENARIO_START_MARGIN = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO.START_MARGIN";

	public static final String CONFIG_SCENARIO1_START_DATE = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO1.START_DATE";
	public static final String CONFIG_SCENARIO1_END_DATE = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO1.END_DATE";

	public static final String CONFIG_SCENARIO2_START_DATE = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO2.START_DATE";
	public static final String CONFIG_SCENARIO2_END_DATE = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO2.END_DATE";

	public static final String CONFIG_SCENARIO3_START_DATE = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO3.START_DATE";
	public static final String CONFIG_SCENARIO3_END_DATE = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO3.END_DATE";

	public static final String CONFIG_SCENARIO_LOAD_TIMEFRAME_DAILY = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO.LOAD_TIMEFRAME_DAILY";
	public static final String CONFIG_SCENARIO_LOAD_TIMEFRAME_MINUTE60 = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO.LOAD_TIMEFRAME_MINUTE60";
	public static final String CONFIG_SCENARIO_LOAD_TIMEFRAME_MINUTE15 = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO.LOAD_TIMEFRAME_MINUTE15";
	public static final String CONFIG_SCENARIO_LOAD_TIMEFRAME_MINUTE05 = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO.LOAD_TIMEFRAME_MINUTE05";

	public static final String CONFIG_SCENARIO_LOAD_LONG = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO.LOAD_LONG";
	public static final String CONFIG_SCENARIO_LOAD_SHORT = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO.LOAD_SHORT";

	public static final String CONFIG_SCENARIO_LOAD_SELECTED_SECURITIES = "DV:XML.CONFIG_NO_DB_RESTORE.SCENARIO.SELECTED_SECURITIES";

	public static final String CONFIG_APPLICATION_SLEEP_AT_NIGHT = "DV:XML.MODEL.APPLICATION.SLEEP_AT_NIGHT";
	public static final String CONFIG_APPLICATION_SLEEP_TIME = "DV:XML.MODEL.APPLICATION.SLEEP_TIME";
	public static final String CONFIG_APPLICATION_HOLIDAY_LIST = "DV:XML.MODEL.APPLICATION.HOLIDAY_LIST";

	// number of days to keep things
	public static final String CONFIG_APPLICATION_NUM_DAYS_TO_KEEP_APPCONFIGS = "DV:XML.MODEL.APPLICATION.NUM_DAYS_TO_KEEP.APPCONFIGS";
	// public static final String CONFIG_APPLICATION_NUM_DAYS_TO_KEEP_TRACE_LOGS = "DV:XML.MODEL.APPLICATION.NUM_DAYS_TO_KEEP.TRACE_LOGS";
	public static final String CONFIG_APPLICATION_NUM_DAYS_TO_KEEP_DAILY_LOGS = "DV:XML.MODEL.APPLICATION.NUM_DAYS_TO_KEEP.DAILY_LOGS";

	// ------------------------------------------------ SERVICE TIMES ---------------------------------------------------
	public static final String CONFIG_MARKET_CLOSED_SERVICE_TIME_LONG = "DV:XML.MODEL.MARKET.MARKET_CLOSED_SERVICE_TIME_LONG";
	public static final String CONFIG_FIRST_MINUTE60_CALC_TIME_LONG = "DV:XML.MODEL.MKTD.SCENARIO.FIRST_MINUTE60_CALC_TIME_LONG";

	public static final String CONFIG_MARKET_CLOSED_SERVICE_TIME_SHORT = "DV:XML.MODEL.MARKET.MARKET_CLOSED_SERVICE_TIME_SHORT";
	public static final String CONFIG_FIRST_MINUTE60_CALC_TIME_SHORT = "DV:XML.MODEL.MKTD.SCENARIO.FIRST_MINUTE60_CALC_TIME_SHORT";

	public static final String MODEL_REALTIME_REBOOT_TIME = "DV:XML.MODEL.APPLICATION.REALTIME_REBOOT_TIME";

	public static final String MODEL_NIGHTLY_SERVICE_TIME = "DV:XML.MODEL.APPLICATION.NIGHTLY_SERVICE_TIME";
	public static final String MODEL_NIGHTLY_SERVICE_EMAIL = "DV:XML.MODEL.APPLICATION.NIGHTLY_SERVICE_EMAIL";
	public static final String MODEL_SIMULATOR_SERVICE_EMAIL = "DV:XML.CONFIG_NO_DB_RESTORE.APPLICATION.SIMULATOR_SERVICE_EMAIL";
	public static final String MODEL_ADMIN_EMAIL = "DV:XML.MODEL.APPLICATION.ADMIN_EMAIL";
}
