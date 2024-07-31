package com.greenmark.common.core;

/////////////////////////////////////////////////
// START CLASS: Labels
/////////////////////////////////////////////////

public class Labels {
    /**
     * This is the class name.
     */
    public static final String CLASSNAME = "Labels";

////////////////////////////////////////////////////////////////////////////////
// PUBLIC CONSTANTS
////////////////////////////////////////////////////////////////////////////////

    //   public static final long MILLISECONDS = 1000;
//
    public static final long SECOND = 1000;
    //
    public static final long MINUTE = 60 * SECOND;
    public static final long HOUR = 60 * MINUTE;
    public static final long DAY = 24 * HOUR;

    /**
     * 1 minute update cycle
     */
    public static final int TYPE_MONTHLY = 7;
    public static final int TYPE_WEEKLY = 6;
    public static final int TYPE_DAILY = 1;
    public static final int TYPE_MINUTE60 = 2;
    public static final int TYPE_MINUTE15 = 3;
    public static final int TYPE_MINUTE05 = 4;
    public static final int TYPE_MINUTE01 = 5;

    public static final String STR_TYPE_MONTHLY = "MONTHLY";
    public static final String STR_TYPE_WEEKLY = "WEEKLY";
    public static final String STR_TYPE_DAILY = "DAILY";
    public static final String STR_TYPE_MINUTE60 = "MINUTE60";
    public static final String STR_TYPE_MINUTE15 = "MINUTE15";
    public static final String STR_TYPE_MINUTE05 = "MINUTE05";
    public static final String STR_TYPE_MINUTE01 = "MINUTE01";

    public static final String CHART_STR_TYPE_DAILY = "daily";
    public static final String CHART_STR_TYPE_MINUTE60 = "sixtyminute";
    public static final String CHART_STR_TYPE_MINUTE15 = "fifteenminute";
    public static final String CHART_STR_TYPE_MINUTE05 = "fiveminute";
    public static final String CHART_STR_TYPE_MINUTE01 = "oneminute";

    public static final int TYPE_BUY = 1;
    public static final int TYPE_SELL = 2;

    public final static int BKGND_R = 193;
    public final static int BKGND_G = 211;
    public final static int BKGND_B = 189;

    public static final int OBJECT_ACTIVE = 1;
    public static final int OBJECT_INACTIVE = 0;

    public static final int IS_TRUE = 1;
    public static final int IS_FALSE = 0;

    public static final String STR_TRUE = "1";
    public static final String STR_FALSE = "0";

////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTORS
////////////////////////////////////////////////////////////////////////////////

    /**
     * Static object, Can't call
     */
    private Labels() {
    }

    public static final String getLabelForTimeValue(int value) {
        switch (value) {
            case TYPE_DAILY:
                return Labels.STR_TYPE_DAILY;
            case TYPE_MINUTE60:
                return Labels.STR_TYPE_MINUTE60;
            case TYPE_MINUTE15:
                return Labels.STR_TYPE_MINUTE15;
            case TYPE_MINUTE05:
                return Labels.STR_TYPE_MINUTE05;
            case TYPE_MINUTE01:
                return Labels.STR_TYPE_MINUTE01;
            case TYPE_WEEKLY:
                return Labels.STR_TYPE_WEEKLY;
            case TYPE_MONTHLY:
                return Labels.STR_TYPE_MONTHLY;
        }
        return "";
    }

    public static final int getTimeValueForLabels(String timevalue) {
        //order in the frequency they are called
        if (timevalue.equals(Labels.STR_TYPE_MINUTE05)) {
            return TYPE_MINUTE05;
        } else if (timevalue.equals(Labels.STR_TYPE_MINUTE15)) {
            return TYPE_MINUTE15;
        } else if (timevalue.equals(Labels.STR_TYPE_MINUTE60)) {
            return TYPE_MINUTE60;
        } else if (timevalue.equals(Labels.STR_TYPE_DAILY)) {
            return TYPE_DAILY;
        }

        ////////////////////////////////////////
        else if (timevalue.equals(Labels.STR_TYPE_MINUTE01)) {
            return TYPE_MINUTE01;
        } else if (timevalue.equals(Labels.STR_TYPE_WEEKLY)) {
            return TYPE_WEEKLY;
        } else if (timevalue.equals(Labels.STR_TYPE_MONTHLY)) {
            return TYPE_MONTHLY;
        }

        return -1;
    }


}
