package com.greenmark.common;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: GmConstantsBroker</p>
 * <p>Description: Contains constant definitions that are specific to the Broker package/service.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class GmConstantsBroker {
    public static final String CLASSNAME = "GmConstantsBroker";

    // ------------------------------------------------ BROKER -----------------------------------------------
    // BROKER TYPE
    public static final int NUM_BROKER_TYPES = 4;
    public static final int BROKER_TYPE_TRAPPING_SIMULATOR = 1;
    public static final int BROKER_TYPE_KUCOIN = 2; // TBD
    public static final int BROKER_TYPE_KRAKEN = 3;

    @Deprecated
    public static final int BROKER_TYPE_SIM_RANDOM_EXECUTE = 5;

    // ------------------------------------------------ BROKER SIMULATOR -----------------------------------------------
    public static final int MIN_PARTIAL_EXECUTED_ORDER_SIZE = 100;

    // RANDOM SIMULATOR : THESE 8 NUMBERS ARE THE PERCENTAGE OUT OF 100 THAT THE EVENT WILL OCCUR
    public static final int RANDOM_SIM_BUY_EXEC_PERCENT = 100;
    public static final boolean RANDOM_SIM_BUY_PARTIAL_ON = false;
    public static final int RANDOM_SIM_BUY_PARTIAL_PERCENT_PER_EXECUTION = 20;
    public static final int RANDOM_SIM_BUY_CANCEL_PERCENT = 100;

    public static final int RANDOM_SIM_SELL_EXEC_PERCENT = 10;
    public static final boolean RANDOM_SIM_SELL_PARTIAL_ON = true;
    public static final int RANDOM_SIM_SELL_PARTIAL_PERCENT_PER_EXECUTION = 20;
    public static final int RANDOM_SIM_SELL_CANCEL_PERCENT = 100;

    // ---------------------------------- SHORTABLE -------------------------------------------
    public static final String SHORTABLE_STATUS_UNKNOWN_STRING = "Unknown";
    public static final String SHORTABLE_STATUS_NONE = "None";
    public static final String SHORTABLE_STATUS_SOME = "Some";
    public static final String SHORTABLE_STATUS_ALOT = "Alot";

    // ---------------------------------- ORDERS ----------------------------------
    public static final int TYPE_BUY = 1;
    public static final int TYPE_SELL = 2;
    public static final int TYPE_COMPLETED = 3;

    public static final int TYPE_STOP = 1;
    public static final int TYPE_MARKET = 2;
    public static final int TYPE_LIMIT = 3;

    // Order status
    public static final int STATUS_PENDING_SUBMIT = 9;
    public static final int STATUS_PENDING_SUBMIT_STUCK = 10;

    public static final int STATUS_PRESUBMITTED = 0;
    public static final int STATUS_SUBMITTED = 1;
    public static final int STATUS_OPEN = 2;

    public static final int STATUS_PARTIAL = 3;
    public static final int STATUS_EXECUTED = 4;

    public static final int STATUS_CANCELLED = 5;
    public static final int STATUS_CANCELLING = 6;

    public static final int STATUS_REJECTED_BY_BROKER = 7;
    public static final int STATUS_UNKNOWN = 8;
    public static final int STATUS_REPLACING = 9;

    // ------------------------------------------------ SECURITY -----------------------------------------------
    public static final int STATUS_NEW = 1;
    public static final int STATUS_ORDER1_PLACED = 2;
    public static final int STATUS_ORDER2_QUALIFY = 3;
    public static final int STATUS_ORDER2_PLACED = 4;
    public static final int STATUS_COMPLETE = 5;

    // ORDER REASONS
    public static final int ORDER_REASON_ADDED_FROM_BUCKETS = 10; // This is used for ALL Buy orders.
    public static final int ORDER_REASON_EXCEEDED_PAIN_THRESHOLD = 20;
    public static final int ORDER_REASON_USER_SELL_MARKET_ORDER = 30;
    public static final int ORDER_REASON_PROFIT_TAKE_MARKET_ORDER = 40;
    public static final int ORDER_REASON_SECURITY_REJECTED = 50;

    public static final int ORDER_REASON_DROPCAT_EXCEEDED_ADDTL_PAIN_THRESHOLD = 60;
    public static final int ORDER_REASON_DROPCAT_END_OF_DAY = 70;
    public static final int ORDER_REASON_DROPCAT_DOUBLE_DOWN_BUY = 80;

    public static final int ORDER_REASON_DEFAULT = 10;

    public static final String ORDER_ID_UNKNOWN = "0";

    // ---------------------------------- POSITIONS -------------------------------------------
    public static final int POSITION_STATUS_NORMAL = 10;

    public static final int POSITION_STATUS_834_SUCCESS_PROFIT_TAKE = 20;
    public static final int POSITION_STATUS_834_SUCCESS_20SMA = 30;
    public static final int POSITION_STATUS_834_SUCCESS_34EMA = 40;
    public static final int POSITION_STATUS_834_CALCULATED_PAIN = 50;
    public static final int POSITION_STATUS_834_REJECTED = 60;
    public static final int POSITION_STATUS_834_CONFIRMED_THEN_REJECTED = 70;

    public static final int POSITION_STATUS_PAIN_SLOW = 100;
    public static final int POSITION_STATUS_PAIN_DROPCAT = 110;
    public static final int POSITION_STATUS_PAIN_DROPCAT_PAINFORGOOD = 120;
    public static final int POSITION_STATUS_PAIN_SPIKEFILTER = 130;
    public static final int POSITION_STATUS_PAIN_SPIKEFILTER_PAINFORGOOD = 140;
    public static final int POSITION_STATUS_PROFIT_TAKING_SELLOFF = 150;
    public static final int POSITION_STATUS_TRACKING_SELL_ORDER = 160;
    public static final int POSITION_STATUS_COMPLETED = 170;

    // -------------------------------------------------- CONSTANTS CONVENIENCE METHODS --------------------------------------------------

    public static String getStopOrMarketOrderDisplay(int stopOrMarketOrder) {
        String outString = "UNKNOWN";

        switch (stopOrMarketOrder) {
            case (GmConstantsBroker.TYPE_STOP): {
                return "STOP";
            }
            case (GmConstantsBroker.TYPE_MARKET): {
                return "MARKET";
            }
            case (GmConstantsBroker.TYPE_LIMIT): {
                return "LIMIT";
            }
        }

        return outString;
    }

    public static String getBuyOrSellOrderDisplay(int buyOrSellOrder) {
        String outString = "UNKNOWN";

        switch (buyOrSellOrder) {
            case (GmConstantsBroker.TYPE_BUY): {
                return "BUY";
            }
            case (GmConstantsBroker.TYPE_SELL): {
                return "SELL";
            }
        }

        return outString;
    }

    public static String getStatusDisplay(int status) {
        String outString = "UNKNOWN";

        switch (status) {
            case (GmConstantsBroker.STATUS_PENDING_SUBMIT): {
                return "PENDING";
            }
            case (GmConstantsBroker.STATUS_PRESUBMITTED): {
                return "PRESUBMIT";
            }
            case (GmConstantsBroker.STATUS_SUBMITTED): {
                return "SUBMIT";
            }
            case (GmConstantsBroker.STATUS_OPEN): {
                return "OPEN";
            }
            case (GmConstantsBroker.STATUS_PARTIAL): {
                return "PARTIAL";
            }
            case (GmConstantsBroker.STATUS_EXECUTED): {
                return "EXECUTED";
            }
            case (GmConstantsBroker.STATUS_CANCELLED): {
                return "CANCELLED";
            }
            case (GmConstantsBroker.STATUS_CANCELLING): {
                return "CANCELLING";
            }
            case (GmConstantsBroker.STATUS_UNKNOWN): {
                return "UNKNOWN";
            }
        }
        return outString;
    }

    // This checks to see if order is with our broker, but not executed in any way.
    public static final boolean isSubmittedOrOpen(int status) {
        if (status == GmConstantsBroker.STATUS_OPEN)
            return true;
        if (status == GmConstantsBroker.STATUS_PENDING_SUBMIT)
            return true;
        if (status == GmConstantsBroker.STATUS_PENDING_SUBMIT_STUCK)
            return true;
        if (status == GmConstantsBroker.STATUS_PRESUBMITTED)
            return true;
        return status == GmConstantsBroker.STATUS_SUBMITTED;
    }

    public static final boolean isPartialOrExecuted(int status) {
        if (status == GmConstantsBroker.STATUS_PARTIAL)
            return true;
        return status == GmConstantsBroker.STATUS_EXECUTED;
    }
}
