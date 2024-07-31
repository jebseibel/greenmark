package com.greenmark.common;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: GmConstantsAccountUtils</p>
 * <p>Description: Contains constant definitions that are specific to the Account package/service.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class GmConstantsAccount {
    public static final String CLASSNAME = "GmConstantsAccountUtils";

    public static final String ACCOUNT_TYPE_STANDARD_STRING = "STANDARD";
    public static final String ACCOUNT_TYPE_REGT_STRING = "REG T MARGIN";

    public static final int ACCOUNT_TYPE_STANDARD = 0;
    public static final int ACCOUNT_TYPE_REG_T_MARGIN = 1;

    // ------------------------------------------------ ACCOUNT -----------------------------------------------
    public static final int CALC_ORDER_ACCT_USE_ACCOUNT_BALANCE = 0;
    public static final int CALC_ORDER_ACCT_USE_CASH_ON_HAND = 1;

    public static final int ACCTCONFIG_ORDERSIZE_TYPE_CALCULATEDi = 0;
    public static final int ACCTCONFIG_ORDERSIZE_TYPE_100_SHARESi = 1;
    public static final int ACCTCONFIG_ORDERSIZE_TYPE_10_SHARESi = 2;
    public static final int ACCTCONFIG_ORDERSIZE_TYPE_SMALLESTi = 3;
    public static final int ACCTCONFIG_NUM_ORDERSIZE_TYPE = 5;

}
