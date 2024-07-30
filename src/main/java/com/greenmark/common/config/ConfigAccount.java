package com.greenmark.common.config;

import com.greenmark.common.GmConstants;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ConfigAccount</p>
 * <p>Description: The constants in this file point to a location in the Strategy XML heirarchy (strategy.xml) for 
 * Account/Position/Order/ExecutionDtoDecorator configuration points.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ConfigAccount {
	private static final String CLASSNAME = "ConfigAccount";

	public static final String ACCTCONFIG_USE_ACCOUNT_EVENT_LIST = "DV:XML.MODEL.APPLICATION.USE_ACCOUNT_EVENT_LIST";

	public static final String ACCTCONFIG_MIN_CASH_ON_HAND_LONG = "DV:XML.MODEL.MKTD.ACCTCONFIG.MINCASHONHAND_LONG";
	public static final String ACCTCONFIG_MIN_CASH_ON_HAND_SHORT = "DV:XML.MODEL.MKTD.ACCTCONFIG.MINCASHONHAND_SHORT";

	public static final String ACCTCONFIG_MAX_POSITIONS_LONG = "DV:XML.MODEL.MKTD.ACCTCONFIG.MAXNUMPOSITIONS_LONG";
	public static final String ACCTCONFIG_MAX_POSITIONS_SHORT = "DV:XML.MODEL.MKTD.ACCTCONFIG.MAXNUMPOSITIONS_SHORT";

	public static final String ACCTCONFIG_MAX_OPEN_ORDERS_LONG = "DV:XML.MODEL.MKTD.ACCTCONFIG.MAXNUMORDERS_LONG";
	public static final String ACCTCONFIG_MAX_OPEN_ORDERS_SHORT = "DV:XML.MODEL.MKTD.ACCTCONFIG.MAXNUMORDERS_SHORT";

	public static final String ACCTCONFIG_UNSETTLED_AGING_DAYS_CASH = "DV:XML.MODEL.TRADER.UNSETTLED_AGING_DAYS_CASH";
	public static final String ACCTCONFIG_UNSETTLED_AGING_DAYS_MARGIN = "DV:XML.MODEL.TRADER.UNSETTLED_AGING_DAYS_MARGIN";

	// public static final String ACCTCONFIG_UNSETTLED_AGING_DAYS_MKTREND_CASH = "DV:XML.MODEL.MKTD.ACCTCONFIG.UNSETTLED_AGING_DAYS_CASH_MKTD";
	// public static final String ACCTCONFIG_UNSETTLED_AGING_DAYS_MKTREND_MARGIN = "DV:XML.MODEL.MKTD.ACCTCONFIG.UNSETTLED_AGING_DAYS_MARGIN_MKTD";

	// HISTORICAL DATA CALCULATIONS
	public static final String HISTORICAL_CONFIG_SCENARIO_STARTDATE = "DV:XML.MODEL.MARKET.SCENARIO_START";
	public static final String HISTORICAL_CONFIG_SCENARIO_ENDDATE = "DV:XML.MODEL.MARKET.SCENARIO_END";

	// THESE ARE FOR AN INVESTMENT ADVISOR ACCOUNT AND ALLOCATION GROUPS.
	public static final String ACCTCONFIG_IS_INVESTMENT_ADVISOR = "DV:XML.MODEL.TRADER.IS_INVESTMENT_ADVISOR";
	public static final String ACCTCONFIG_ACCOUNT_GROUP_LONG = "DV:XML.MODEL.TRADER.ACCOUNT_GROUP_LONG";
	public static final String ACCTCONFIG_ACCOUNT_GROUP_SHORT = "DV:XML.MODEL.TRADER.ACCOUNT_GROUP_SHORT";
	public static final String ACCTCONFIG_ADVISOR_ACCOUNT_FOR_ORDERS = "DV:XML.MODEL.TRADER.ADVISOR_ACCOUNT_FOR_ORDERS";
	public static final String ACCTCONFIG_ADVISOR_ACCOUNT_FOR_BALANCES = "DV:XML.MODEL.TRADER.ADVISOR_ACCOUNT_FOR_BALANCES";
	public static final String ACCTCONFIG_ALLOCATION_METHOD_LONG = "DV:XML.MODEL.TRADER.ALLOCATION_METHOD_LONG";
	public static final String ACCTCONFIG_ALLOCATION_METHOD_SHORT = "DV:XML.MODEL.TRADER.ALLOCATION_METHOD_SHORT";

	public static final String ACCTCONFIG_CALC_ORDER_AMT_FROM_ACCT_LONG = "DV:XML.MODEL.MKTD.ACCTCONFIG.CALC_ORDER_AMT_FROM_ACCT_LONG";
	public static final String ACCTCONFIG_CALC_ORDER_AMT_FROM_ACCT_SHORT = "DV:XML.MODEL.MKTD.ACCTCONFIG.CALC_ORDER_AMT_FROM_ACCT_SHORT";

	public static final String ACCTCONFIG_MIN_ORDER_SIZE_LONG = "DV:XML.MODEL.MKTD.ACCTCONFIG.BUYBLOCKSIZE.MINORDER_LONG";
	public static final String ACCTCONFIG_MIN_ORDER_SIZE_SHORT = "DV:XML.MODEL.MKTD.ACCTCONFIG.BUYBLOCKSIZE.MINORDER_SHORT";

	public static final String ACCTCONFIG_MAX_STOCK_WATCH_PRICE_LONG = "DV:XML.MODEL.MKTD.ACCTCONFIG.STOCKWATCHTRADE.MAXPRICE_LONG";
	public static final String ACCTCONFIG_MAX_STOCK_WATCH_PRICE_SHORT = "DV:XML.MODEL.MKTD.ACCTCONFIG.STOCKWATCHTRADE.MAXPRICE_SHORT";

	public static final String ACCTCONFIG_MAX_STOCK_WATCH_PERCENT_OF_BALANCE_LONG = "DV:XML.MODEL.MKTD.ACCTCONFIG.STOCKWATCHTRADE.MAXPERCENTOFBALANCE_LONG";
	public static final String ACCTCONFIG_MAX_STOCK_WATCH_PERCENT_OF_BALANCE_SHORT = "DV:XML.MODEL.MKTD.ACCTCONFIG.STOCKWATCHTRADE.MAXPERCENTOFBALANCE_SHORT";

	public static final String ACCTCONFIG_MAX_ORDERS_PERCENT_OF_BALANCE_LONG = "DV:XML.MODEL.MKTD.ACCTCONFIG.MAXORDERSPERCENTOFBALANCE_LONG";
	public static final String ACCTCONFIG_MAX_ORDERS_PERCENT_OF_BALANCE_SHORT = "DV:XML.MODEL.MKTD.ACCTCONFIG.MAXORDERSPERCENTOFBALANCE_SHORT";

	public static final String ACCTCONFIG_END_OF_DAY_THRESHOLD_LONG = "DV:XML.MODEL.MKTD.SCENARIO.ENDOFDAY.TIMETHRESHOLD_LONG";
	public static final String ACCTCONFIG_END_OF_DAY_THRESHOLD_SHORT = "DV:XML.MODEL.MKTD.SCENARIO.ENDOFDAY.TIMETHRESHOLD_SHORT";
	public static final String ACCTCONFIG_END_OF_DAY_PERCENT_ORDER_LONG = "DV:XML.MODEL.MKTD.SCENARIO.ENDOFDAY.PERCENTORDER_LONG";
	public static final String ACCTCONFIG_END_OF_DAY_PERCENT_ORDER_SHORT = "DV:XML.MODEL.MKTD.SCENARIO.ENDOFDAY.PERCENTORDER_SHORT";

	public static final String ACCTCONFIG_COMMISSION_FEE_BROKER = "DV:XML.MODEL.TRADER.TRANSACTION_FEE_BROKER";

	public static final String ACCTCONFIG_ORDERSIZE_TYPE = "DV:XML.MODEL.MKTD.ACCTCONFIG.ACCOUNT_ORDERSIZE_TYPE";

	public static final String CONFIG_BROKER_TYPE = "DV:XML.MODEL.BROKER.BROKER_TYPE";
	public static final String CONFIG_BROKER_TYPE_DELAY = "DV:XML.MODEL.BROKER.BROKER_TYPE_DELAY";

	public static final boolean isInvestmentAdvisorType() {
		int investmentAdvisorOn = Config.getValueAsInt(ConfigAccount.ACCTCONFIG_IS_INVESTMENT_ADVISOR);
		if (investmentAdvisorOn == 1)
			return true;
		else
			return false;
	}

	public static float getMinCashOnHand(int longOrShort) {
		float minCashOnHand = Float.parseFloat(Config.getValue(ConfigAccount.ACCTCONFIG_MIN_CASH_ON_HAND_LONG));
		if (longOrShort == GmConstants.SHORT_SECURITY) {
			minCashOnHand = Float.parseFloat(Config.getValue(ConfigAccount.ACCTCONFIG_MIN_CASH_ON_HAND_SHORT));
		}

		return minCashOnHand;
	}
}
