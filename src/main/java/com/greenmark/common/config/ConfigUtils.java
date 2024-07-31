package com.greenmark.common.config;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsDatafeed;
import com.greenmark.utils.UTPropertyManager;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ConfigUtils</p>
 * <p>Description: Contains utility methods for configuration values.  
 * NOTE: There are plenty of configuration value methods in ConfigDecorator for ease of displaying values.  This file is reserved for general
 * utility methods we don't want to put in Config.java.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ConfigUtils {
    private static final String CLASSNAME = "ConfigUtils";

    public static final int getMemoryAllocSize(int timeframe) {
        String methodname = "getMemoryAllocSize";

        int returnVal = GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE;

        try {
            int modelShortOrLong = Config.getValueAsInt(ConfigStrategy.MODEL_LONG_OR_SHORT_STRATEGY);

            // Find the base size for all timeframes except daily or 60 min.
            returnVal = GmConstants.MIN_PERIODS_FOR_DATA_CALCS;
            int dataArraySize = GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE;
            returnVal += dataArraySize;

            if (timeframe == GmConstants.TYPE_DAILY) {
                // Find the numdays specified by the harvester's num years.
                int numYears = Config.getValueAsInt(ConfigStrategy.MODEL_HARVESTER_NUM_YEARS_PRICE_HISTORY_LONG);
                int numDays = numYears * 260;

                // Find the largest value specified by the strategy
                int longPtNumPeriods = 0;
                int shortPtNumPeriods = 0;

                if ((modelShortOrLong == GmConstants.MODEL_LONG_ONLY) || (modelShortOrLong == GmConstants.MODEL_LONG_AND_SHORT))
                    longPtNumPeriods = Config.getValueAsInt(ConfigStrategy.MODEL_BUY_LONG_DAILY_LOWPT_NUMPERIODS);
                if ((modelShortOrLong == GmConstants.MODEL_SHORT_ONLY) || (modelShortOrLong == GmConstants.MODEL_LONG_AND_SHORT))
                    shortPtNumPeriods = Config.getValueAsInt(ConfigStrategy.MODEL_BUY_SHORT_DAILY_HIGHPT_NUMPERIODS);

                if (shortPtNumPeriods > longPtNumPeriods)
                    longPtNumPeriods = shortPtNumPeriods;

                if (longPtNumPeriods > numDays)
                    numDays = longPtNumPeriods;

                returnVal += numDays;
            }
            if (timeframe == GmConstants.TYPE_MINUTE60) {
                int longPtNumPeriods = 0;
                int shortPtNumPeriods = 0;

                if ((modelShortOrLong == GmConstants.MODEL_LONG_ONLY) || (modelShortOrLong == GmConstants.MODEL_LONG_AND_SHORT))
                    longPtNumPeriods = Config.getValueAsInt(ConfigStrategy.MODEL_BUY_LONG_MINUTE60_LOWPT_NUMPERIODS);
                if ((modelShortOrLong == GmConstants.MODEL_SHORT_ONLY) || (modelShortOrLong == GmConstants.MODEL_LONG_AND_SHORT))
                    shortPtNumPeriods = Config.getValueAsInt(ConfigStrategy.MODEL_BUY_SHORT_MINUTE60_HIGHPT_NUMPERIODS);

                if (shortPtNumPeriods > longPtNumPeriods)
                    longPtNumPeriods = shortPtNumPeriods;

                returnVal += longPtNumPeriods;
            }
            if (timeframe == GmConstants.TYPE_MINUTE01) // The memory size must be at least large enough to hold 1 day of 1 min data.
            {
                if (returnVal < 900)
                    returnVal = 900;
            }
        } catch (Exception ex) {
            System.out.println("Exception Chartdata:getMemoryAllocSize  timeframe: [" + timeframe + "ErrorMessage: [" + ex.getMessage() + "]");
            return returnVal;
        }

        return returnVal;
    }

    // ------------------------------------ CONVENIENCE WRAPPERS ---------------------------------------

    /**
     * Returns true/false if it is time to exit based on the Configuration value
     */
    public static boolean isExitAppTime(String nowtime) {
        try {
            String value = Config.getValue(ConfigApplication.CONFIG_APPLICATION_SLEEP_AT_NIGHT);
            if (value.equalsIgnoreCase("true")) {
                String sleeptime = Config.getValue(ConfigApplication.CONFIG_APPLICATION_SLEEP_TIME);

                if (nowtime.equals(sleeptime)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in Config.isHoliday, Exception Message:  [" + e.getMessage() + "]");
        }
        return false;
    }

    public static boolean usePopup() {
        String value = Config.getValue(ConfigApplication.CONFIG_APPLICATION_DISABLE_POPUP);
        return value.equalsIgnoreCase("true");
    }

    public static boolean isGuiOn() {
        String historicalGuiModeType = Config.getValue(ConfigApplication.CONFIG_HISTORICAL_GUI_MODE);

        return historicalGuiModeType.equals(ConfigApplication.CONFIG_HISTORICAL_GUI_ON);
    }

    public static boolean isGuiOff() {
        String historicalGuiModeType = Config.getValue(ConfigApplication.CONFIG_HISTORICAL_GUI_MODE);

        return historicalGuiModeType.equals(ConfigApplication.CONFIG_HISTORICAL_GUI_OFF);
    }

    public static boolean isDropcatUsed() {
        if (UTPropertyManager.getBooleanProperty(ConfigStrategy.MODEL_DROP_CAT)) {
            int marketTrendUsed = Config.getValueAsInt(ConfigStrategy.MODEL_DROP_CAT);
            return marketTrendUsed == 1;
        } else {
            return false;
        }
    }

    public static final Integer getNumStochkPeriods() {
        Integer stochkPeriods = Config.getValueAsInt(ConfigStrategy.MODEL_STOCHK_NUM_PERIODS);
        if (stochkPeriods < 1)
            stochkPeriods = GmConstants.NUM_PTS_STOCH_SLOW_K;

        return stochkPeriods;
    }

    public static final Integer getNumSlowDPeriods() {
        Integer stochkPeriods = Config.getValueAsInt(ConfigStrategy.MODEL_STOCHD_NUM_PERIODS);
        if (stochkPeriods < 1)
            stochkPeriods = GmConstants.NUM_PTS_STOCH_SLOW_D;

        return stochkPeriods;
    }
}
