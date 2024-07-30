package com.greenmark.common.config;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ConfigDatafeed</p>
 * <p>Description: Configuration points for the Datafeed and Prices Database packages.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ConfigDatafeed {
	private static final String CLASSNAME = "ConfigDatafeed";

	public static final String CONFIG_SLOWDOWN_MINUTE01 = "DV:XML.MODEL.SLOWDOWN_MINUTE01";

	public static final String CONFIG_DATAFEED_FINNHUB_API_KEY = "DV:XML.CONFIG_NO_DB_RESTORE.DATAFEED.FINNHUB_API_KEY";

	public static final String CONFIG_DATAFEED_TYPE = "DV:XML.CONFIG_NO_DB_RESTORE.DATAFEED.GUI.TYPE";

	// This extra config point is for the tradingClient to keep track of the default chart datafeed setting.
	// The chart app also uses it when the user selects a datafeed connection type in the select list on the GUI.
	public static final String CONFIG_CHART_DATAFEED_TYPE = "DV:XML.CONFIG_NO_DB_RESTORE.DATAFEED.CHART.TYPE";

	// The number of years we would like to seed in our PH DB. Default is 8.
	public static final String PDB_UPDATER_NUM_YEARS_DATA = "DV:XML.CONFIG_NO_DB_RESTORE.DATAFEED.PH_UPDATER.NUM_YEARS_DATA";

	/**
	 * When we add extra minutes to each datetime in the query to the Finnhub data feed, we get different results. So we can change these values when running the 'Fix Missing & Incorrect Days' utility
	 * and fix the data with less repitions of the fix utility
	 **/
	public static final String PDB_UPDATER_FIX_DATA_EXTRA_MINS = "DV:XML.CONFIG_NO_DB_RESTORE.DATAFEED.PH_UPDATER.FIX_DATA_EXTRA_MINUTES";

	/**
	 * When we slow down the Prices Database Updater, we get more reliable results from the Finnhub data feed. The below slowdown seconds are only applied to the 'Fix Missing & Incorrect Days' utility
	 * and not the initial prices updater.
	 **/
	public static final String PDB_FIX_DATA_SLOWDOWN_SPEED = "DV:XML.CONFIG_NO_DB_RESTORE.DATAFEED.PH_UPDATER.FIX_DATA_SLOWDOWN_SPEED";
	public static final String PDB_UPDATE_DATA_MIN1_SLOWDOWN_SPEED = "DV:XML.CONFIG_NO_DB_RESTORE.DATAFEED.PH_UPDATER.UPDATE_DATA_MIN1_SLOWDOWN_SPEED";
}
