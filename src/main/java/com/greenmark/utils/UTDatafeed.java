package com.greenmark.utils;

import com.greenmark.common.GmConstantsDatafeed;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTDatafeed</p>
 * <p>Description: This class is used by the datafeed utility as consumers pass in a data load action that is used to calculate a loading strategy.  When querying for data, a consumer class can
 *    specify if it needs the returned data from the input data forward, or if the input date is an enddate and we need all the prices data up until this date.  The live data feeds prefer that later
 *    and historical datafeeds prefer the former.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class UTDatafeed {
    private static final String CLASSNAME = "UTDatafeed";

    public static final String calcLoadStrategyForAction(String loadAction) {
        if (GmConstantsDatafeed.DATA_LOAD_STRATEGY_STARTDATE_FORWARD.equals(loadAction) || GmConstantsDatafeed.DATA_LOAD_STRATEGY_ENDDATE_BACKWARD.equals(loadAction))
            return loadAction;

        if (GmConstantsDatafeed.DATA_LOAD_ACTION_CHART_FF.equals(loadAction))
            return GmConstantsDatafeed.DATA_LOAD_STRATEGY_STARTDATE_FORWARD;

        else if (GmConstantsDatafeed.DATA_LOAD_ACTION_CHART_REW.equals(loadAction))
            return GmConstantsDatafeed.DATA_LOAD_STRATEGY_ENDDATE_BACKWARD;

        else if (GmConstantsDatafeed.DATA_LOAD_ACTION_CHART_CHANGE_TIMEFRAME.equals(loadAction))
            return GmConstantsDatafeed.DATA_LOAD_STRATEGY_ENDDATE_BACKWARD; // Make sure to add alot to end date!

        else if (GmConstantsDatafeed.DATA_LOAD_ACTION_CHART_PRESS_BOOKMARK.equals(loadAction))
            return GmConstantsDatafeed.DATA_LOAD_STRATEGY_ENDDATE_BACKWARD; // Make sure to add alot to end date!

        else if (GmConstantsDatafeed.DATA_LOAD_ACTION_CHART_ENTER_DATE.equals(loadAction))
            return GmConstantsDatafeed.DATA_LOAD_STRATEGY_ENDDATE_BACKWARD; // Make sure to add alot to end date!

        else if (GmConstantsDatafeed.DATA_LOAD_ACTION_PDB_LOADER.equals(loadAction))
            return GmConstantsDatafeed.DATA_LOAD_STRATEGY_STARTDATE_FORWARD;

        return GmConstantsDatafeed.DATA_LOAD_STRATEGY_ENDDATE_BACKWARD; // Default value
    }
}
