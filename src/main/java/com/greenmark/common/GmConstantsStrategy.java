package com.greenmark.common;

import com.greenmark.utils.UTLabels;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: GmConstantsStrategy</p>
 * <p>Description: Contains constant definitions that are specific to the Trading Service.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class GmConstantsStrategy {
	public static final String CLASSNAME = "GmConstantsStrategy";

	public static final int SELL_STRATEGY_DAILY = 1;
	public static final int SELL_STRATEGY_MINUTE60 = 2;
	public static final int SELL_STRATEGY_MINUTE15 = 3;
	public static final int SELL_STRATEGY_MINUTE05 = 4;
	public static final int NO_SELL_STRATEGY = 5;
	public static final int SELL_STRATEGY_DEFAULT = 1;

	// --------------------------------------------------------- Rejected Securities ----------------------------------------------------------
	// ------ THESE GET PUT ON PERMANENT ------
	// This status displays on the REJECTED BY GREENMAN List, the security is returned to its origin timeframe on expiration, user cannot remove alerts
	public static final String REJECTED_NO_DATA_AVAILABLE = "    No Data   ";
	public static final String REJECTED_NO_DATA_AVAILABLE_SELL_BUCKET = "No Data - Sell";

	// This status displays on the REJECTED BY GREENMAN List, the alert always remains and cannot be dismissed.
	public static final String REJECTED_STALE_BROKER_ORDER = "  Stale Order ";

	// ------ THESE GET PUT ON AGING ------
	// This status displays on the AGING SECURITIES List, the security is returned to its origin timeframe on expiration and the alert is automatically removed.
	public static final String REJECTED_PAIN_EXCEEDED = "Pain Triggered";

	// ------ THESE GET PUT ON DISMISSABLE ------
	// These status displays on the REJECTED BY GREENMAN (DISMISSABLE) List, the security is returned to its origin timeframe on expiration, the user can remove the alert
	public static final String REJECTED_NOT_SHORTABLE = "Not Shortable";
	public static final String REJECTED_UNAVAIL_FUNDS = "Unavail Funds";

	// ------ THESE GET PUT ON USER REJECTED ------
	// This status is when user adds a security to the REJECTED BY USER List. it remains until removed by the user and prevents Greenman from adding this security to the strategies.
	public static final String REJECTED_ADDED_BY_USER = "Reject by User";

	// --------------------------------------------------------- Market Trend Strategies ----------------------------------------------------------
	public static final int NUM_MKT_TREND_INDICATORS = 2;
	public static final String MKT_TREND_INDICATOR_STOCHASTIC = "Stochastic";
	public static final String MKT_TREND_INDICATOR_SMA_CROSSOVER = "Double SMA Crossover";

	public static final int NUM_MKT_TREND_STOCKS = 3;
	public static final String MKT_TREND_INDICE_NAME0 = "Dow Jones Industrial Avg.";
	public static final String MKT_TREND_INDICE_NAME1 = "NASDAQ Composite";
	public static final String MKT_TREND_INDICE_NAME2 = "S&P 500";
	public static final String MKT_TREND_INDICE_SYMBOL0 = "^DJI";
	public static final String MKT_TREND_INDICE_SYMBOL1 = "^IXIC";
	public static final String MKT_TREND_INDICE_SYMBOL2 = "^GSPC";

	public static final int NUM_MKT_TREND_TYPES = 7;
	public static final String MKT_TREND_TYPE_NONE_STRING = "NONE";
	public static final String MKT_TREND_TYPE_BEAR_STRATEGY_STRING = "BEAR_STRATEGY";
	public static final String MKT_TREND_TYPE_BEAR_TRENDING_STRING = "BEAR_TRENDING";
	public static final String MKT_TREND_TYPE_BEAR_STRING = "BEAR";
	public static final String MKT_TREND_TYPE_FLAT_STRING = "FLAT";
	public static final String MKT_TREND_TYPE_BULL_STRING = "BULL";
	public static final String MKT_TREND_TYPE_BULL_TRENDING_STRING = "BULL_TRENDING";
	public static final String MKT_TREND_TYPE_BULL_STRATEGY_STRING = "BULL_STRATEGY";
	public static final String MKT_TREND_TYPE_BOTH_TRENDING_STRING = "BOTH_TRENDING";

	public static final int MKT_TREND_TYPE_NONE_INT = -1;
	public static final int MKT_TREND_TYPE_BULL_STRATEGY_INT = 0;
	public static final int MKT_TREND_TYPE_BULL_TRENDING_INT = 1;
	public static final int MKT_TREND_TYPE_BULL_INT = 2;
	public static final int MKT_TREND_TYPE_FLAT_INT = 3;
	public static final int MKT_TREND_TYPE_BEAR_INT = 4;
	public static final int MKT_TREND_TYPE_BEAR_TRENDING_INT = 5;
	public static final int MKT_TREND_TYPE_BEAR_STRATEGY_INT = 6;

	// --------------------------------------------------------- Dropcat Strategies ----------------------------------------------------------
	protected int dropcatStatus = DROPCAT_STATUS_TRAP_OPENED;
	public static final int DROPCAT_STATUS_TRAP_OPENED = 0;
	public static final int DROPCAT_STATUS_TRAP_LARGEST_LOSS_FOUND = 1;
	public static final int DROPCAT_STATUS_TRAP_DOUBLED_DOWN = 2;
	public static final int DROPCAT_STATUS_SELLPLACED_BUCKETS = 3;
	public static final int DROPCAT_STATUS_SELLPLACED_ENDOFDAY = 4;
	public static final int DROPCAT_STATUS_SELLEXECUTED = 5;
	public static final int DROPCAT_STATUS_PAINED_OUT = 6;
	public static final int DROPCAT_STATUS_DOUBLEDOWN_BUYORDER_REJECTED = 8;

	// --------------------------------------------------------- Harvest Strategies ----------------------------------------------------------
	public static final int HARVEST_STRATEGY_TYPE_NONE = 0;
	public static final int HARVEST_STRATEGY_TYPE_HARVESTER = 1;
	public static final int HARVEST_STRATEGY_TYPE_B2B = 2;
	public static final int HARVEST_STRATEGY_TYPE_DROPCAT = 3;
	public static final int HARVEST_STRATEGY_TYPE_ATH = 4;

	public static final String HARVEST_STRATEGY_TYPE_NONE_STRING = "None";
	public static final String HARVEST_STRATEGY_TYPE_HARVESTER_STRING = "Harv";
	public static final String HARVEST_STRATEGY_TYPE_B2B_STRING = "B2B";
	public static final String HARVEST_STRATEGY_TYPE_DROPCAT_STRING = "Dcat";
	public static final String HARVEST_STRATEGY_TYPE_ATH_STRING = "ATH";

	// There's only 1 bucket strategy type for now.
	public static final int BUCKET_STRATEGY_TYPE = 1;

	public static final int BUCKET_STRATEGY_STATE_CALCULATING_MOMENTUM = 10;
	public static final int BUCKET_STRATEGY_STATE_COMPLETED_MOMENTUM = 15;

	public static final int BUCKET_STRATEGY_STATE_CALCULATING_MACD_NUMPERIODS = 20;
	public static final int BUCKET_STRATEGY_STATE_COMPLETED_MACD_NUMPERIODS = 25;

	public static final int BUCKET_STRATEGY_STATE_CALCULATING_MACD_SIGNAL = 30;
	public static final int BUCKET_STRATEGY_STATE_COMPLETED_MACD_SIGNAL = 35;

	public static final int BUCKET_STRATEGY_STATE_CALCULATING_834_STRATEGY = 40;
	public static final int BUCKET_STRATEGY_STATE_COMPLETED_834_STRATEGY = 45;

	// ----------------------------------- 8/34 STRATEGY -------------------------------
	public static final int STRATEGY_834_STATE_INIT = 100;
	public static final int STRATEGY_834_STATE_8OVER14 = 110;
	public static final int STRATEGY_834_STATE_8OVER34 = 120;
	public static final int STRATEGY_834_STATE_POSITION_BOUGHT = 130;
	public static final int STRATEGY_834_STATE_POSITION_CONFIRMED = 140;

	public static final int STRATEGY_834_STOPLOSS_STATE_20SMA = 210;
	public static final int STRATEGY_834_STOPLOSS_STATE_34EMA = 220;
	public static final int STRATEGY_834_STOPLOSS_STATE_CALCULATED_PAIN = 200;

	public static final int STRATEGY_834_SOLD_STATE_SUCCESS_PROFIT_TAKE = 300;
	public static final int STRATEGY_834_SOLD_STATE_SUCCESS_SMA20 = 310;
	public static final int STRATEGY_834_SOLD_STATE_SUCCESS_EMA34 = 320;
	public static final int STRATEGY_834_SOLD_STATE_CALCULATED_PAIN = 330;
	public static final int STRATEGY_834_SOLD_STATE_REJECTED = 340;
	public static final int STRATEGY_834_SOLD_STATE_CONFIRMED_THEN_REJECTED = 350;

	public static final String BUCKET_STRATEGY_STATE_CALCULATING_MOMENTUM_ABBREVIATION = "%K";
	public static final String BUCKET_STRATEGY_STATE_COMPLETED_MOMENTUM_ABBREVIATION = "%K Done";

	public static final String BUCKET_STRATEGY_STATE_CALCULATING_MACD_NUMPERIODS_ABBREVIATION = "Macd #";
	public static final String BUCKET_STRATEGY_STATE_COMPLETED_MACD_MACD_NUMPERIODS_ABBREVIATION = "Macd # Done";

	public static final String BUCKET_STRATEGY_STATE_CALCULATING_MACD_SIGNAL_ABBREVIATION = "mSignal";
	public static final String BUCKET_STRATEGY_STATE_COMPLETED_MACD_SIGNAL_ABBREVIATION = "mSignal Done";

	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_STATE_INIT_STRING = "8/34 Init.";

	// These are for Long Securities
	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_STATE_8OVER14_STRING = "8 over 14";
	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_STATE_8OVER34_STRING = "8 over 34";

	// These are for Short Securities
	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_STATE_8UNDER14_STRING = "8 under 14";
	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_STATE_8UNDER34_STRING = "8 under 34";

	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_STATE_POSITION_BOUGHT_STRING = "Pos. Bought";
	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_STATE_POSITION_CONFIRMED_STRING = "Pos. Confirmed";

	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_STOPLOSS_STATE_20SMA_STRING = "20Sma";
	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_STOPLOSS_STATE_34EMA_STRING = "34Ema";
	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_STOPLOSS_STATE_CALCULATED_STRING = "Calc.";

	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_SOLD_STATE_SUCCESS_PROFIT_TAKE = "Success - Taking Profits";
	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_SOLD_STATE_SUCCESS_SMA20 = "Sold on 20 Sma";
	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_SOLD_STATE_SUCCESS_EMA34 = "Sold on 34 Ema";
	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_SOLD_STATE_CALCULATED_PAIN = "Loss - Calculated Pain - Entered Rejected Pain timeout.";
	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_SOLD_STATE_REJECTED = "Loss - Rejected before Confirm";
	public static final String STOCK_TABLE_BUCKET_834_STRATEGY_SOLD_STATE_CONFIRMED_THEN_REJECTED = "Loss - Rejected after Confirming";

	// -------------------------------------------------- STATE CONVENIENCE METHODS --------------------------------------------------

	public static String getBucket834StrategyString(int longOrShort, int current834State) {

        switch (current834State) {
		case (STRATEGY_834_STATE_INIT): {
			return STOCK_TABLE_BUCKET_834_STRATEGY_STATE_INIT_STRING;
		}
		case (STRATEGY_834_STATE_8OVER14): {
			if (longOrShort == GmConstants.LONG_SECURITY)
				return STOCK_TABLE_BUCKET_834_STRATEGY_STATE_8OVER14_STRING;
			else
				return STOCK_TABLE_BUCKET_834_STRATEGY_STATE_8UNDER14_STRING;
		}
		case (STRATEGY_834_STATE_8OVER34): {
			if (longOrShort == GmConstants.LONG_SECURITY)
				return STOCK_TABLE_BUCKET_834_STRATEGY_STATE_8OVER34_STRING;
			else
				return STOCK_TABLE_BUCKET_834_STRATEGY_STATE_8UNDER34_STRING;
		}
		case (STRATEGY_834_STATE_POSITION_BOUGHT): {
			return STOCK_TABLE_BUCKET_834_STRATEGY_STATE_POSITION_BOUGHT_STRING;
		}
		case (STRATEGY_834_STATE_POSITION_CONFIRMED): {
			return STOCK_TABLE_BUCKET_834_STRATEGY_STATE_POSITION_CONFIRMED_STRING;
		}
		case (STRATEGY_834_SOLD_STATE_SUCCESS_PROFIT_TAKE): {
			return STOCK_TABLE_BUCKET_834_STRATEGY_SOLD_STATE_SUCCESS_PROFIT_TAKE;
		}
		case (STRATEGY_834_SOLD_STATE_SUCCESS_EMA34): {
			return "Sold" + STOCK_TABLE_BUCKET_834_STRATEGY_SOLD_STATE_SUCCESS_EMA34;
		}
		case (STRATEGY_834_SOLD_STATE_SUCCESS_SMA20): {
			return STOCK_TABLE_BUCKET_834_STRATEGY_SOLD_STATE_SUCCESS_SMA20;
		}
		case (STRATEGY_834_SOLD_STATE_CALCULATED_PAIN): {
			return STOCK_TABLE_BUCKET_834_STRATEGY_SOLD_STATE_CALCULATED_PAIN;
		}
		case (STRATEGY_834_SOLD_STATE_REJECTED): {
			return STOCK_TABLE_BUCKET_834_STRATEGY_SOLD_STATE_REJECTED;
		}
		case (STRATEGY_834_SOLD_STATE_CONFIRMED_THEN_REJECTED): {
			return STOCK_TABLE_BUCKET_834_STRATEGY_SOLD_STATE_CONFIRMED_THEN_REJECTED;
		}
		}

		return "";
	}

	public static String getBucket834StopLossStrategyString(int current834StoplossState) {

        switch (current834StoplossState) {
		case (STRATEGY_834_STOPLOSS_STATE_CALCULATED_PAIN): {
			return STOCK_TABLE_BUCKET_834_STRATEGY_STOPLOSS_STATE_CALCULATED_STRING;
		}
		case (STRATEGY_834_STOPLOSS_STATE_20SMA): {
			return STOCK_TABLE_BUCKET_834_STRATEGY_STOPLOSS_STATE_20SMA_STRING;
		}
		case (STRATEGY_834_STOPLOSS_STATE_34EMA): {
			return STOCK_TABLE_BUCKET_834_STRATEGY_STOPLOSS_STATE_34EMA_STRING;
		}
		}

		return "";
	}

	// -------------------------------------------------- CONSTANTS CONVENIENCE METHODS --------------------------------------------------
	public static final String getHarvestStrategyLabel(int harvestStrategyId, int originBuyBucketTimeframe) {
		switch (harvestStrategyId) {
		case (HARVEST_STRATEGY_TYPE_NONE):
			return HARVEST_STRATEGY_TYPE_NONE_STRING;
		case (HARVEST_STRATEGY_TYPE_HARVESTER):
			return HARVEST_STRATEGY_TYPE_HARVESTER_STRING;
		case (HARVEST_STRATEGY_TYPE_B2B):
			return HARVEST_STRATEGY_TYPE_B2B_STRING + " " + UTLabels.getGmanTimeframeAbbrv(originBuyBucketTimeframe);
		case (HARVEST_STRATEGY_TYPE_DROPCAT):
			return HARVEST_STRATEGY_TYPE_DROPCAT_STRING;
		case (HARVEST_STRATEGY_TYPE_ATH):
			return HARVEST_STRATEGY_TYPE_ATH_STRING;
		}

		return HARVEST_STRATEGY_TYPE_HARVESTER_STRING;
	}
}
