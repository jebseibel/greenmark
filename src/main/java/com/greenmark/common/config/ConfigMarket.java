package com.greenmark.common.config;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ConfigMarket</p>
 * <p>Description: The constants in this file point to a location in the Strategy XML heirarchy (strategy.xml).  These
 * configuration points are for Market and ExchangeDto related concepts.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ConfigMarket {
	private static final String CLASSNAME = "ConfigMarket";

	public static final String START_MARKET_TREND_DVTAG = "DV:XML.MODEL.MKTD";

	// public static final String CONFIG_MARKET_ALWAYS_OPEN = "DV:XML.MODEL.MARKET.ALWAYS_OPEN";
	public static final String CONFIG_MARKET_TREND_HASFLAT = "DV:XML.MODEL.MKTD.FLAT";

	public static final String CONFIG_MARKET_TREND_USE = "DV:XML.MODEL.MARKET.MARKET_TRENDS.USE";
	public static final String CONFIG_MARKET_TREND_ENABLED = "DV:XML.MODEL.MARKET.MARKET_TRENDS.ENABLE";

	public static final String CONFIG_MARKET_TREND_ADJUST_MKT_TREND_SELL = "DV:XML.MODEL.MARKET.MARKET_TRENDS.ADJUST_MKT_TREND_SELL";

	public static final String CONFIG_MARKET_TREND_CURRENT_TYPE = "DV:XML.MODEL.MARKET.MARKET_TRENDS.CURRENT_TYPE";
	public static final String CONFIG_MARKET_TREND_CURRENT_VALUE = "DV:XML.MODEL.MARKET.MARKET_TRENDS.CURRENT_VALUE";

	public static final String CONFIG_MARKET_TREND_ANALYSIS_OPTION = "DV:XML.MODEL.MARKET.MARKET_TRENDS.ANALYSIS_OPTION";
	public static final String CONFIG_MARKET_TREND_ANALYSIS_INDICE = "DV:XML.MODEL.MARKET.MARKET_TRENDS.ANALYSIS_INDICE";

	public static final String CONFIG_MARKET_TREND_BEAR_START_STOCHK = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BEAR_START_STOCHK";
	public static final String CONFIG_MARKET_TREND_BEAR_STOP_STOCHK = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BEAR_STOP_STOCHK";
	public static final String CONFIG_MARKET_TREND_BULL_START_STOCHK = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BULL_START_STOCHK";
	public static final String CONFIG_MARKET_TREND_BULL_STOP_STOCHK = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BULL_STOP_STOCHK";

	public static final String CONFIG_MARKET_TREND_BEAR_TREND_START_STOCHK = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BEAR_TREND_START_STOCHK";
	public static final String CONFIG_MARKET_TREND_BEAR_TREND_STOP_STOCHK = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BEAR_TREND_STOP_STOCHK";
	public static final String CONFIG_MARKET_TREND_BULL_TREND_START_STOCHK = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BULL_TREND_START_STOCHK";
	public static final String CONFIG_MARKET_TREND_BULL_TREND_STOP_STOCHK = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BULL_TREND_STOP_STOCHK";

	public static final String CONFIG_MARKET_TREND_OVERRIDE_TREND_ANALYSIS = "DV:XML.MODEL.MARKET.MARKET_TRENDS.OVERRIDE_TREND_ANALYSIS";

	public static final String CONFIG_BULL_TRENDING_ORDER_TYPE = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BULL_TRENDING_ORDER_TYPE";
	public static final String CONFIG_BULL_TRENDING_ORDER_STOCHK = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BULL_TRENDING_ORDER_STOCHK";
	// public static final String CONFIG_BULL_TRENDING_SELL_STRATEGY = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BULL_TRENDING_SELL_STRATEGY";

	public static final String CONFIG_BEAR_TRENDING_ORDER_TYPE = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BEAR_TRENDING_ORDER_TYPE";
	public static final String CONFIG_BEAR_TRENDING_ORDER_STOCHK = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BEAR_TRENDING_ORDER_STOCHK";
	// public static final String CONFIG_BEAR_TRENDING_SELL_STRATEGY = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BEAR_TRENDING_SELL_STRATEGY";

	// ------------------------------------------------ BROKER ---------------------------------------------------
	public static final String MODEL_BROKER_TYPE = "DV:XML.MODEL.TRADER.BROKER_TYPE";
	public static final String MODEL_BROKER_NAME = "DV:XML.MODEL.TRADER.BROKER_NAME";

	public static final String MODEL_BROKER_TRANSACTION_FEE = "DV:XML.MODEL.TRADER.TRANSACTIONFEE";
	public static final String MODEL_BROKER_FEE_PER_UNIT = "DV:XML.MODEL.TRADER.FEE_PER_UNIT";
}
