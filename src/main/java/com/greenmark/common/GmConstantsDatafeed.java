package com.greenmark.common;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: GmConstantsDatafeed</p>
 * <p>Description: </p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class GmConstantsDatafeed {
    public static final String CLASSNAME = "GmConstantsDatafeed";

    // -------------------------------------------------- IMPORTANT --------------------------------------------------

    public static final int MAX_RAWDATA_ARRAY_SIZE = 3000;

    // UTLabels for the UI and the user to choose
    public static final String CONFIG_DATASOURCE_SCENARIO_LIVE = "REALTIME-SCENARIO";
    public static final String CONFIG_DATASOURCE_SCENARIO_HISTORY = "HISTORICAL-SCENARIO";

    public static final String CONFIG_DATASOURCE_CHART_LIVE = "REALTIME-CHART";
    public static final String CONFIG_DATASOURCE_CHART_HISTORY = "HISTORICAL-CHART";

    // For now, we only have 1 exchange per prices DB, so this will work:
    public static final Long PRICES_DB_CURRENT_EXCHANGE_ID = 1L;

    public static final Integer PRICE_QUERY_COULD_NOT_FIND_INDEX = -1;

    public static final Integer HTTP_CONNECT_TIMEOUT_MILLIS = 3000;

    public static final Integer FINNHUB_MAX_DAILY_PER_REQUEST = 40;
    public static final Integer FINNHUB_MAX_DATA_POINTS_PER_REQUEST_BINANCE = 500;
    public static final int NUM_ATTEMPTS_FINNHUB = 5;

    public static final int SLEEP_MILLISECONDS_BETWEEN_ATTEMPTS_PDB_UPDATER = 3000;
    public static final int SLEEP_MILLISECONDS_BETWEEN_ATTEMPTS_REALTIME_SIMULATOR = 300;

    public static final int DONT_USE_INTERPERIOD_DATA = 0;
    public static final int USE_THE_INTERPERIOD_DATA = 1;
    public static final int FIRST_PERIOD_AFTER_MARK = 1;

    // -------------------------------------------------- PRICE DATA CALCULATIONS --------------------------------------------------
    public static final int NUM_RSI_CLASSIC_PERIODS = 14;
    public static final int NUM_MA_8_PERIODS = 8;
    public static final int NUM_MA_14_PERIODS = 14;
    public static final int NUM_MA_20_PERIODS = 20;
    public static final int NUM_MA_34_PERIODS = 34;
    public static final int NUM_MA_50_PERIODS = 50;
    public static final int NUM_MA_200_PERIODS = 200;

    // -------------------------------------------------- DATAFEED --------------------------------------------------
    public static final int CALC_ENTRY_PRICE_REWIND_PERIODS = 2720; // If we fail to calc an entry executedPrice, we have to get more previous data.

    // These are for a 7 hour day. That is the 6.5 hours the market is open plus an extra 1/2 hour for after close data.
    // Please don't change these numbers.
    public static final int NUM_DATA_POINTS_USMARKETS_DAY_HOUR4 = 2;
    public static final int NUM_DATA_POINTS_USMARKETS_DAY_MINUTE60 = 7;
    public static final int NUM_DATA_POINTS_USMARKETS_DAY_MINUTE15 = 28;
    public static final int NUM_DATA_POINTS_USMARKETS_DAY_MINUTE05 = 84;
    public static final int NUM_DATA_POINTS_USMARKETS_DAY_MINUTE01 = 400;

    // THESE ARE FOR 24 HOUR EXCHANGES
    public static final Integer NUM_DATA_POINTS_PER_DAY_WEEKLY = 1;
    public static final Integer NUM_DATA_POINTS_PER_DAY_DAILY = 1;
    public static final Integer NUM_DATA_POINTS_24H_DAY_HOUR4 = 6;
    public static final Integer NUM_DATA_POINTS_24H_DAY_MINUTE60 = 24;
    public static final Integer NUM_DATA_POINTS_24H_DAY_MINUTE15 = 96;
    public static final Integer NUM_DATA_POINTS_24H_DAY_MINUTE05 = 288;
    public static final Integer NUM_DATA_POINTS_24H_DAY_MINUTE01 = 1440;

    public static final int NUM_MINS_IN_MIN01 = 1;
    public static final int NUM_MINS_IN_MIN05 = 5;
    public static final int NUM_MINS_IN_MIN15 = 15;
    public static final int NUM_MINS_IN_MIN60 = 60;
    public static final int NUM_MINS_IN_HOUR4 = 240;
    public static final int NUM_MINS_IN_DAILY = 1440;
    public static final int NUM_MINS_IN_WEEKLY = 10080;

    // -------------------------------------------------- DATAFEED (DATA LOADING) --------------------------------------------------
    // This is used to indicate why we're loading data from a data feed.
    public static final String DATA_LOAD_ACTION_CHART_FF = "CHART-FF"; // Will always pass to datafeed a datetime
    public static final String DATA_LOAD_ACTION_CHART_REW = "CHART-REW"; // Will always pass to datafeed a datetime

    public static final String DATA_LOAD_ACTION_CHART_CHANGE_TIMEFRAME = "CHART-CHANGE-TIMEFRAME"; // May pass null to datafeed
    public static final String DATA_LOAD_ACTION_CHART_PRESS_BOOKMARK = "CHART-PRESS-BOOKMARK"; // May pass null to datafeed
    public static final String DATA_LOAD_ACTION_CHART_ENTER_DATE = "CHART-ENTER-DATE"; // Will always pass to datafeed a datetime

    public static final String DATA_LOAD_ACTION_PDB_LOADER = "PRICE-HISTORY-LOADER"; // Will always pass to datafeed a datetime

    // Some actions will want their data to be from the end_date back. And if we lose any data at the front, we're ok.
    // Other actions will want their data to be from the start_date forward. And if we lose any data at the back, we're ok.

    // Some actions (automated actions) can choose one of these 2 values directly:
    public static final String DATA_LOAD_STRATEGY_STARTDATE_FORWARD = "LOAD-STRATEGY-STARTDATE-FORWARD";
    public static final String DATA_LOAD_STRATEGY_ENDDATE_BACKWARD = "LOAD-STRATEGY-ENDDATE-BACKWARD";

    public static final int IN_DATETIME_BEFORE_PRICEDATA_TIMES = 0;
    public static final int IN_DATETIME_WITHIN_PRICEDATA_TIMES = 1;
    public static final int IN_DATETIME_AFTER_PRICEDATA_TIMES = 2;
    public static final int IN_DATETIME_INVALID_NO_DATA_YET = 3;

    // -------------------------------------------------- DATASOURCE --------------------------------------------------
    public static final int CONFIG_DATASOURCE_SCENARIO_LIVE_INT = 1;
    public static final int CONFIG_DATASOURCE_SCENARIO_HISTORY_INT = 2;

    public static final int CONFIG_DATASOURCE_CHART_LIVE_INT = 3;
    public static final int CONFIG_DATASOURCE_CHART_HISTORY_INT = 4;

    // -------------------------------------------------- STATUS of PdbSecurityDto in pdbUpdater --------------------------------------------------
    public static final int PDB_SECURITY_STATUS_ALL = -1;
    public static final int PDB_SECURITY_STATUS_ACTIVE = 0;
    public static final int PDB_SECURITY_STATUS_EXCEPTIONED = 1;
    public static final int PDB_SECURITY_STATUS_DELISTED = 2;
    public static final int PDB_SECURITY_STATUS_RETIRED = 3;

    // -------------------------------------------------- CONSTANTS CONVENIENCE METHODS --------------------------------------------------
    public static String getDatasourceFeedString(int datasourceFeedType) {
        switch (datasourceFeedType) {
            case GmConstantsDatafeed.CONFIG_DATASOURCE_CHART_LIVE_INT: {
                return CONFIG_DATASOURCE_CHART_LIVE;
            }
            case GmConstantsDatafeed.CONFIG_DATASOURCE_CHART_HISTORY_INT: {
                return CONFIG_DATASOURCE_CHART_HISTORY;
            }
            case GmConstantsDatafeed.CONFIG_DATASOURCE_SCENARIO_LIVE_INT: {
                return CONFIG_DATASOURCE_SCENARIO_LIVE;
            }
            case GmConstantsDatafeed.CONFIG_DATASOURCE_SCENARIO_HISTORY_INT: {
                return CONFIG_DATASOURCE_SCENARIO_HISTORY;
            }
        }

        return CONFIG_DATASOURCE_CHART_LIVE; // Default is Live
    }

    public static int getDatasourceFeedInt(String datasourceType) {
        if (datasourceType.equals(CONFIG_DATASOURCE_CHART_LIVE)) {
            return GmConstantsDatafeed.CONFIG_DATASOURCE_CHART_LIVE_INT;
        } else if (datasourceType.equals(CONFIG_DATASOURCE_CHART_HISTORY)) {
            return GmConstantsDatafeed.CONFIG_DATASOURCE_CHART_HISTORY_INT;
        } else if (datasourceType.equals(CONFIG_DATASOURCE_SCENARIO_LIVE)) {
            return GmConstantsDatafeed.CONFIG_DATASOURCE_SCENARIO_LIVE_INT;
        } else if (datasourceType.equals(CONFIG_DATASOURCE_SCENARIO_HISTORY)) {
            return GmConstantsDatafeed.CONFIG_DATASOURCE_SCENARIO_HISTORY_INT;
        } else {
            return GmConstantsDatafeed.CONFIG_DATASOURCE_CHART_LIVE_INT;
        }
    }

    // Input integerTimeframeIndex is one of 1, 5, 15, 60 4h, D or W
    public static int getGreenmanTimeframe(String integerTimeframeIndex) {
        try {
            int returnVal = GmConstants.TYPE_DAILY;

            if (integerTimeframeIndex.equals("1"))
                returnVal = GmConstants.TYPE_MINUTE01;
            else if (integerTimeframeIndex.equals("5"))
                returnVal = GmConstants.TYPE_MINUTE05;
            else if (integerTimeframeIndex.equals("15"))
                returnVal = GmConstants.TYPE_MINUTE15;
            else if (integerTimeframeIndex.equals("60"))
                returnVal = GmConstants.TYPE_MINUTE60;
            else if (integerTimeframeIndex.equals("4h"))
                returnVal = GmConstants.TYPE_HOUR4;
            else if (integerTimeframeIndex.equals("D"))
                returnVal = GmConstants.TYPE_DAILY;
            else if (integerTimeframeIndex.equals("W"))
                returnVal = GmConstants.TYPE_WEEKLY;

            return returnVal;
        } catch (Exception ex) {
            System.out.println("Exception Chartdata:getGreenmanTimeframe  integerTimeframeIndex [" + integerTimeframeIndex + "]   ErrorMessage: [" + ex.getMessage() + "]");
            return GmConstants.TYPE_DAILY;
        }
    }
}
