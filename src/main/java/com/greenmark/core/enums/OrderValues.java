package com.greenmark.core.enums;

public class OrderValues {
    public static final int STATUS_PENDING_SUBMIT = 0 ;
    public static final int STATUS_SUBMITTED = 1 ;
    public static final int STATUS_OPEN = 2 ;
    public static final int STATUS_PARTIAL = 3 ;
    public static final int STATUS_EXECUTED = 4 ;
    public static final int STATUS_CANCELLED = 5 ;
    public static final int STATUS_CANCELLING = 6 ;
    public static final int STATUS_REJECTED_BY_BROKER =  7 ;
    public static final int STATUS_UNKNOWN =  8 ;

    public static final int TYPE_STOP = 1;
    public static final int TYPE_MARKET = 2;
    public static final int TYPE_LIMIT = 3;

    //  Used by the OrderDecorator object to know which type of buy or sell order it is.
    public static final int TYPE_ORDER_BUY = 1;
    public static final int TYPE_ORDER_SELL = 2;

    public static final int ADDED_FROM_BUCKETS = 1;   // This is used for ALL Buy orders.
    public static final int EXCEEDED_PAIN_THRESHOLD = 2;
    public static final int USER_SELL_MARKET_ORDER = 3;
    public static final int DROPCAT_EXCEEDED_ADDTL_PAIN_THRESHOLD = 4;
    public static final int DROPCAT_END_OF_DAY = 5;
    public static final int DROPCAT_DOUBLE_DOWN_BUY = 6;
    public static final int ORDER_REASON_DEFAULT = 1;

    public static final String ORDER_ID_UNKNOWN = "0";

    public OrderValues() {

    }
}
