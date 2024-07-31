package com.greenmark.common;

import com.greenmark.utils.UTUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: GmConstants</p>
 * <p>Description: Contains constant definitions that are used throughout the application and are not specific to any feature.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class GmConstants {
    public static final String CLASSNAME = "GmConstants";

    public static final int MIN_PERIODS_FOR_DATA_CALCS = 200; // Do not make this any less than 200 or the 34 ema calcs are wrong.
    public static final int MIN_PERIODS_FOR_DATA_CALCS_REALTIME = 200;
    public static final int MIN_PERIODS_FOR_DATA_CALCS_REALTIME_MIN1 = 50;
    public static final int MIN_PERIODS_FOR_PRICES_REALTIME_MIN1 = 3; // We need at least this much data to get PriceDataIndicators for min1 calcs.

    public static final String TRUE = "true";
    public static final String FALSE = "false";

    public static final int IS_TRUE = 1;
    public static final int IS_FALSE = 0;

    public static final String STR_TRUE = "1";
    public static final String STR_FALSE = "0";

    public static final int LONG_SECURITY = 1;
    public static final int SHORT_SECURITY = 2;

    public static final int MODEL_LONG_ONLY = 1;
    public static final int MODEL_SHORT_ONLY = 2;
    public static final int MODEL_LONG_AND_SHORT = 3;

    // This is the Asset Class Type:
    public static final int TYPE_STOCK = 1;
    public static final int TYPE_INDEX = 2;
    public static final int TYPE_CRYPTO = 3;
    public static final int TYPE_GLOBAL_CURRENCY = 4;

    // These are the asset class values for the Exchanges in the prices db exchanges table.
    // We're only using 2 of the above types.
    public static final String FINNHUB_ASSET_CLASS_CRYPTO = "Crypto currency";
    public static final String FINNHUB_ASSET_CLASS_FOREX = "Global currency";

    // TIMEFRAME ENUM VALUES (Used everywhere as timeframe parameter)
    public static final int TYPE_MONTHLY = 6;
    public static final int TYPE_WEEKLY = 7;
    public static final int TYPE_DAILY = 1;
    public static final int TYPE_HOUR4 = 8;
    public static final int TYPE_MINUTE60 = 2;
    public static final int TYPE_MINUTE15 = 3;
    public static final int TYPE_MINUTE05 = 4;
    public static final int TYPE_MINUTE01 = 5;

    // ---------------------------------- DB Constants -------------------------------------------
    public static final int OBJECT_ACTIVE = 1;
    public static final int OBJECT_INACTIVE = 0;

    // ---------------------------------- GUI CONSTANTS -------------------------------------------
    public final static int BKGND_R = 193;
    public final static int BKGND_G = 211;
    public final static int BKGND_B = 189;

    // ---------------------------------- EXCHANGES (sometimes referred to as Markets) -------------------------------------------
    public static final String EXCHANGE_SYMBOL_NYSE = "NYSE";
    public static final String EXCHANGE_SYMBOL_NASDAQ = "NASDAQ";

    public static final String EXCHANGE_SYMBOL_BINANCE = "BINANCE";
    public static final String EXCHANGE_SYMBOL_COINBASE = "COINBASE";
    public static final String EXCHANGE_SYMBOL_KUCOIN = "KUCOIN";

    public static final int AVG_NUM_CALENDAR_DAYS_PER_YEAR = 365;
    public static final int AVG_NUM_CALENDAR_DAYS_PER_MONTH = 30;

    // ---------------------------------- SECURITY -------------------------------------------

    // This is for the Slow K, How many Fast Ks you average together to make the Slow K. (Calculate EMA)
    public static final int NUM_PTS_STOCH_SLOW_K = 12;
    public static final int NUM_PTS_STOCH_SLOW_K_EMA_PERIOD = 5;
    public static final int NUM_PTS_STOCH_SLOW_D = 5;

    public static final int ORDER_ERROR_DEFAULT = 1;

    // ---------------------------------- POSITION EVENTS -------------------------------------------
    public static final int EVENT_MIN1_QUERY_FAILED = 5;

    public static final int EVENT_BUY_BUCKET = 10;
    public static final int EVENT_SELL_BUCKET = 20;

    public static final int EVENT_ENTERED_BUY_BUCKET = 15;
    public static final int EVENT_ENTERED_SELL_BUCKET = 25;

    public static final int EVENT_BUY_BUCKET_STRATEGY_MOMENTUM = 30;
    public static final int EVENT_BUY_BUCKET_STRATEGY_MACD_NUM_PERIODS = 33;
    public static final int EVENT_BUY_BUCKET_STRATEGY_MACD_SIGNAL = 34;

    public static final int EVENT_SELL_BUCKET_STRATEGY_MOMENTUM = 40;
    public static final int EVENT_SELL_BUCKET_STRATEGY_MACD_NUM_PERIODS = 43;
    public static final int EVENT_SELL_BUCKET_STRATEGY_MACD_SIGNAL = 44;

    public static final int EVENT_MARKET_ORDER_PLACED = 50;

    public static final int EVENT_BUY_ORDER_PLACED = 60;
    public static final int EVENT_SELL_ORDER_PLACED = 70;

    public static final int EVENT_BUY_ORDER_REPLACED = 80;
    public static final int EVENT_SELL_ORDER_REPLACED = 90;

    public static final int EVENT_BUY_ORDER_EXECUTED = 100;
    public static final int EVENT_SELL_ORDER_EXECUTED = 110;

    public static final int EVENT_BUY_ORDER_CANCELLED = 120;
    public static final int EVENT_SELL_ORDER_CANCELLED = 130;

    public static final int EVENT_834_STRATEGY_STATE_INIT = 200;
    public static final int EVENT_834_STRATEGY_STATE_8_BACKUNDER_14 = 205;
    public static final int EVENT_834_STRATEGY_STATE_8_BACKUNDER_34 = 207;
    public static final int EVENT_834_STRATEGY_STATE_8OVER14 = 210;
    public static final int EVENT_834_STRATEGY_STATE_8OVER34 = 220;
    public static final int EVENT_834_STRATEGY_STATE_POSITION_BOUGHT = 230;
    public static final int EVENT_834_STRATEGY_STATE_POSITION_CONFIRMED = 240;
    public static final int EVENT_834_STRATEGY_STATE_SOLD = 245;

    public static final int EVENT_834_STRATEGY_STOPLOSS_STATE_CALCULATED = 250;
    public static final int EVENT_834_STRATEGY_STOPLOSS_STATE_SMA20 = 260;
    public static final int EVENT_834_STRATEGY_STOPLOSS_STATE_EMA34 = 270;

    public static final int EVENT_834_STRATEGY_PROFIT_SELL_SECURITY = 280;
    public static final int EVENT_834_STRATEGY_PAIN_SELL_SECURITY = 290;

    public static final int EVENT_REJECTED_RESTORED_TO_BUCKET = 300;

    public static final int EVENT_HARVESTER = 310;

    // ---------------------------------- PRICE DATA -------------------------------------------
    public static final int UPDATER_EMA50_NUM_PERIODS = 50;
    public static final int UPDATER_SMA200_NUM_PERIODS = 200;

    // ---------------------------------- PRICES DATABASE UPDATER -------------------------------------------
    public static final int PDB_LOADER_NUM_HOURS_OFFSET_FOR_FULL_DAY_DATA = 2;

    // ---------------------------------- CHARTS -------------------------------------------
    public static final int NUM_PERIODS_TO_DISPLAY_DEFAULT = 100;

    // ---------------------------------- RAWDATA (and Charts) -------------------------------------------
    public static final String CHARTS_1_MINUTE = "oneminute";
    public static final String CHARTS_5_MINUTE = "fiveminute";
    public static final String CHARTS_15_MINUTE = "fifteenminute";
    public static final String CHARTS_60_MINUTE = "sixtyminute";
    public static final String CHARTS_HOUR4 = "fourhour";
    public static final String CHARTS_DAILY = "daily";
    public static final String CHARTS_WEEKLY = "weekly";

    // ---------------------------------- TIME -------------------------------------------
    public static final long NUM_MILLISECONDS_IN_DAY = 86400000;

    public static final long SECOND_IN_MILLIS = 1000;
    public static final long MINUTE_IN_MILLIS = 60 * SECOND_IN_MILLIS;
    public static final long HOUR_IN_MILLIS = 60 * MINUTE_IN_MILLIS;
    public static final long DAY_IN_MILLIS = 24 * HOUR_IN_MILLIS;

    public static final long SECONDS_IN_DAY = 60 * 60 * 24;

    // ---------------------------------- TIMEZONES -------------------------------------------
    public static final String TIMEZONE_GMT = "GMT"; // This is the same as UTC

    public static final String TIMEZONE_EST = "EST";
    public static final String TIMEZONE_CST = "CST";
    public static final String TIMEZONE_MST = "MST";
    public static final String TIMEZONE_PST = "PST";

    public static int getMinDataPoints(boolean isLiveScenario, int timeframe) {
        if (isLiveScenario) {
            if (timeframe == GmConstants.TYPE_MINUTE01)
                return MIN_PERIODS_FOR_DATA_CALCS_REALTIME_MIN1;
            else
                return MIN_PERIODS_FOR_DATA_CALCS_REALTIME;
        } else
            return MIN_PERIODS_FOR_DATA_CALCS;
    }

    public static int getExchangeAssetClassType(String assetClass) {
        if (UTUtils.isNotNorE(assetClass)) {
            if (assetClass.equals(FINNHUB_ASSET_CLASS_CRYPTO))
                return TYPE_CRYPTO;

            if (assetClass.equals(FINNHUB_ASSET_CLASS_FOREX))
                return TYPE_GLOBAL_CURRENCY;
        }

        return GmConstants.TYPE_CRYPTO; // Use Crypto as default asset_class
    }

    public static boolean is1MinEvent(int positionEventType) {
        switch (positionEventType) {
            case (EVENT_BUY_ORDER_EXECUTED): {
                return true;
            }
            case (EVENT_SELL_ORDER_EXECUTED): {
                return true;
            }
        }
        return false;
    }

    public static boolean isOrderEvent(int positionEventType) {
        switch (positionEventType) {
            case (EVENT_MARKET_ORDER_PLACED): {
                return true;
            }
            case (EVENT_BUY_ORDER_PLACED): {
                return true;
            }
            case (EVENT_SELL_ORDER_PLACED): {
                return true;
            }
            case (EVENT_BUY_ORDER_REPLACED): {
                return true;
            }
            case (EVENT_SELL_ORDER_REPLACED): {
                return true;
            }
            case (EVENT_BUY_ORDER_EXECUTED): {
                return true;
            }
            case (EVENT_SELL_ORDER_EXECUTED): {
                return true;
            }
            case (EVENT_BUY_ORDER_CANCELLED): {
                return true;
            }
            case (EVENT_SELL_ORDER_CANCELLED): {
                return true;
            }
        }
        return false;
    }
}
