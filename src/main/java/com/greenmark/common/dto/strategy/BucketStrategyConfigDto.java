package com.greenmark.common.dto.strategy;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.greenmark.common.GmConstants;
import com.greenmark.common.dto.parameters.MovingAvgConfigParams;
import com.greenmark.common.dto.parameters.MovingAvgParams;
import com.greenmark.common.enums.MomentumTypeEnum;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: BucketStrategyConfigDto</p>
 * <p>Description: This class contains member variables that define some of the strategy configuration values used when the trading client calculates.
 *    This DTO is for the results database bucket_strategies table.  
 *    
 *      A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 *    DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class BucketStrategyConfigDto implements Serializable {
	public static final String CLASSNAME = "BucketStrategyConfigDto";
	private static final long serialVersionUID = 1L;

	protected long id;
	protected int active = GmConstants.OBJECT_ACTIVE;
	protected long stockEventId;
	protected int currentState;

	protected boolean useMomentum = false;
	protected MomentumTypeEnum momentumType;
	protected float thresholdMomentum;

	protected boolean useMacdNumPeriods;
	protected int thresholdMacdNumPeriods;
	protected boolean useMacdSignal;

	protected boolean use_834_Strategy = false;
	protected int strategy_834_State = 0;
	protected int strategy_834_StopLossState = 0;

	protected float whenBought8ema;
	protected float whenBought20sma;

	// For Num. Periods since sold, we don't include the current day if position bought during the day.
	protected int numPeriodsSinceSoldThreshold = 3;

	// For Num. Periods over 34, we don't include the current day if position bought during the day.
	protected int numPeriodsSinceOver34Threshold = 1;

	protected int ema8Threshold = 8;
	protected int sma14Threshold = 14;
	protected int sma20Threshold = 20;
	protected int ema34Threshold = 34;
	protected int sma20StopLossThreshold = 20;
	protected int ema34StopLossThreshold = 34;

	protected boolean filterIntradaySpikes = false;

	// The sum of these 2 must equal 100%
	protected float onEntryOrderPercent;
	protected float onConfirmOrderPercent;

	public BucketStrategyConfigDto() {
	}

	public BucketStrategyConfigDto(int strategy_834_State, int strategy_834_StopLossState) {
		this();
		this.strategy_834_State = strategy_834_State;
		this.strategy_834_StopLossState = strategy_834_StopLossState;
	}

	public BucketStrategyConfigDto(BucketStrategyConfigDto inBucketStrategy) {
		this(inBucketStrategy.getStrategy_834_State(), inBucketStrategy.getStrategy_834_StopLossState());

		try {
			if (inBucketStrategy != null)
				BeanUtils.copyProperties(this, inBucketStrategy);
		} catch (InvocationTargetException ite) {
			System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage());
		} catch (IllegalAccessException iae) {
			System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
		}
	}

	public MovingAvgConfigParams getMovingAverageConfig() {
		MovingAvgParams ema8 = new MovingAvgParams(getEma8Threshold(), false);
		MovingAvgParams sma14 = new MovingAvgParams(getSma14Threshold(), true);
		MovingAvgParams sma20 = new MovingAvgParams(getSma20Threshold(), true);
		MovingAvgParams ema34 = new MovingAvgParams(getEma34Threshold(), false);
		MovingAvgParams sma20SL = new MovingAvgParams(getSma20StopLossThreshold(), true);
		MovingAvgParams ema34SL = new MovingAvgParams(getEma34StopLossThreshold(), false);

		MovingAvgConfigParams allAverages = new MovingAvgConfigParams(ema8, sma14, sma20, ema34, sma20SL, ema34SL);
		return allAverages;
	}

	public BucketStrategyConfigDto(String xmldata) {
		try {
			this.id = UTXmlUtils.getXmlDataAsLong(xmldata, "ID");
			this.active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");
			this.stockEventId = UTXmlUtils.getXmlDataAsInt(xmldata, "STOCK_EVENT_ID");
			this.currentState = UTXmlUtils.getXmlDataAsInt(xmldata, "CURRENT_STATE");

			this.useMomentum = UTXmlUtils.getXmlDataAsBoolean(xmldata, "USE_MOMENTUM");
			MomentumTypeEnum momentumType = MomentumTypeEnum.getById(UTXmlUtils.getXmlDataAsInt(xmldata, "MOMENTUM_TYPE")).orElse(null);
			this.momentumType = momentumType;
			this.thresholdMomentum = UTXmlUtils.getXmlDataAsFloat(xmldata, "THRESHOLD_MOMENTUM");

			this.useMacdNumPeriods = UTXmlUtils.getXmlDataAsBoolean(xmldata, "USE_MACD_NUM_PERIODS");
			this.thresholdMacdNumPeriods = UTXmlUtils.getXmlDataAsInt(xmldata, "THRESHOLD_MACD_NUM_PERIODS");
			this.useMacdSignal = UTXmlUtils.getXmlDataAsBoolean(xmldata, "USE_MACD_SIGNAL");

			this.use_834_Strategy = UTXmlUtils.getXmlDataAsBoolean(xmldata, "USE_834_STRATEGY");
			this.strategy_834_State = UTXmlUtils.getXmlDataAsInt(xmldata, "STRATEGY_834_STATE");
			this.strategy_834_StopLossState = UTXmlUtils.getXmlDataAsInt(xmldata, "STRATEGY_834_STOPLOSS_STATE");

			this.whenBought8ema = UTXmlUtils.getXmlDataAsFloat(xmldata, "WHEN_BOUGHT_8EMA");
			this.whenBought20sma = UTXmlUtils.getXmlDataAsFloat(xmldata, "WHEN_BOUGHT_20SMA");

			this.numPeriodsSinceSoldThreshold = UTXmlUtils.getXmlDataAsInt(xmldata, "THRESHOLD_NUM_PERIODS_SINCE_SOLD");
			this.numPeriodsSinceOver34Threshold = UTXmlUtils.getXmlDataAsInt(xmldata, "THRESHOLD_NUM_PERIODS_SINCE_OVER34");

			this.ema8Threshold = UTXmlUtils.getXmlDataAsInt(xmldata, "THRESHOLD_8EMA");
			this.sma14Threshold = UTXmlUtils.getXmlDataAsInt(xmldata, "THRESHOLD_14SMA");
			this.sma20Threshold = UTXmlUtils.getXmlDataAsInt(xmldata, "THRESHOLD_20SMA");
			this.ema34Threshold = UTXmlUtils.getXmlDataAsInt(xmldata, "THRESHOLD_34EMA");
			this.sma20StopLossThreshold = UTXmlUtils.getXmlDataAsInt(xmldata, "THRESHOLD_20SMA_STOPLOSS");
			this.ema34StopLossThreshold = UTXmlUtils.getXmlDataAsInt(xmldata, "THRESHOLD_34EMA_STOPLOSS");

			this.onEntryOrderPercent = UTXmlUtils.getXmlDataAsFloat(xmldata, "ON_ENTRY_ORDER_PERCENT");
			this.onConfirmOrderPercent = UTXmlUtils.getXmlDataAsFloat(xmldata, "ON_CONFIRM_ORDER_PERCENT");

			this.filterIntradaySpikes = UTXmlUtils.getXmlDataAsBoolean(xmldata, "FILTER_INTRADAY_SPIKES");
		} catch (Exception e) {
			System.out.println("Error creating bucket strategy from XML, BucketStrategyBase.constructor, message: " + e.getMessage());
		}
	}

	public String toXmlWrapper(String prefix, String endline) {
		StringBuffer stb = new StringBuffer();
		stb.append(prefix + "<BUCKET_STRATEGY_CONFIG>" + endline);
		stb.append(prefix + toXml(prefix, endline) + endline);
		stb.append(prefix + "</BUCKET_STRATEGY_CONFIG>" + endline);
		return stb.toString();
	}

	public String toXml(String prefix, String endline) {
		StringBuffer stb = new StringBuffer();

		stb.append(prefix + "<ID>" + this.id + "</ID>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<ACTIVE>" + this.active + "</ACTIVE>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<STOCK_EVENT_ID>" + this.stockEventId + "</STOCK_EVENT_ID>" + endline);

		stb.append(prefix + UTDisplayFormatter.TAB + "<CURRENT_STATE>" + this.currentState + "</CURRENT_STATE>" + endline);

		stb.append(prefix + UTDisplayFormatter.TAB + "<USE_MOMENTUM>" + this.isUseMomentum() + "</USE_MOMENTUM>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<MOMENTUM_TYPE>" + this.getMomentumType().getIdString() + "</MOMENTUM_TYPE>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<THRESHOLD_MOMENTUM>" + this.getThresholdMomentum() + "</THRESHOLD_MOMENTUM>" + endline);

		stb.append(prefix + UTDisplayFormatter.TAB + "<USE_MACD_NUM_PERIODS>" + this.isUseMacdNumPeriods() + "</USE_MACD_NUM_PERIODS>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<THRESHOLD_MACD_NUM_PERIODS>" + this.getThresholdMacdNumPeriods() + "</THRESHOLD_MACD_NUM_PERIODS>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<USE_MACD_SIGNAL>" + this.isUseMacdSignal() + "</USE_MACD_SIGNAL>" + endline);

		stb.append(prefix + UTDisplayFormatter.TAB + "<USE_834_STRATEGY>" + this.isUse_834_Strategy() + "</USE_834_STRATEGY>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<STRATEGY_834_STATE>" + this.strategy_834_State + "</STRATEGY_834_STATE>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<STRATEGY_834_STOPLOSS_STATE>" + this.strategy_834_StopLossState + "</STRATEGY_834_STOPLOSS_STATE>" + endline);

		stb.append(prefix + UTDisplayFormatter.TAB + "<WHEN_BOUGHT_8EMA>" + this.whenBought8ema + "</WHEN_BOUGHT_8EMA>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<WHEN_BOUGHT_20SMA>" + this.whenBought20sma + "</WHEN_BOUGHT_20SMA>" + endline);

		stb.append(prefix + UTDisplayFormatter.TAB + "<THRESHOLD_NUM_PERIODS_SINCE_SOLD>" + this.getNumPeriodsSinceSoldThreshold() + "</THRESHOLD_NUM_PERIODS_SINCE_SOLD>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<THRESHOLD_NUM_PERIODS_SINCE_OVER34>" + this.getNumPeriodsSinceOver34Threshold() + "</THRESHOLD_NUM_PERIODS_SINCE_OVER34>" + endline);

		stb.append(prefix + UTDisplayFormatter.TAB + "<THRESHOLD_8EMA>" + this.getEma8Threshold() + "</THRESHOLD_8EMA>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<THRESHOLD_14SMA>" + this.getSma14Threshold() + "</THRESHOLD_14SMA>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<THRESHOLD_20SMA>" + this.getSma20Threshold() + "</THRESHOLD_20SMA>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<THRESHOLD_34EMA>" + this.getEma34Threshold() + "</THRESHOLD_34EMA>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<THRESHOLD_20SMA_STOPLOSS>" + this.getSma20StopLossThreshold() + "</THRESHOLD_20SMA_STOPLOSS>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<THRESHOLD_34EMA_STOPLOSS>" + this.getEma34StopLossThreshold() + "</THRESHOLD_34EMA_STOPLOSS>" + endline);

		stb.append(prefix + UTDisplayFormatter.TAB + "<ON_ENTRY_ORDER_PERCENT>" + this.getOnEntryOrderPercent() + "</ON_ENTRY_ORDER_PERCENT>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<ON_CONFIRM_ORDER_PERCENT>" + this.getOnConfirmOrderPercent() + "</ON_CONFIRM_ORDER_PERCENT>" + endline);

		stb.append(prefix + UTDisplayFormatter.TAB + "<FILTER_INTRADAY_SPIKES>" + this.filterIntradaySpikes + "</FILTER_INTRADAY_SPIKES>" + endline);
		return stb.toString();
	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public long getStockEventId() {
		return stockEventId;
	}

	public void setStockEventId(long stockEventId) {
		this.stockEventId = stockEventId;
	}

	public int getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int currentState) {
		this.currentState = currentState;
	}

	public boolean isUseMomentum() {
		return useMomentum;
	}

	public void setUseMomentum(boolean useMomentum) {
		this.useMomentum = useMomentum;
	}

	public MomentumTypeEnum getMomentumType() {
		return momentumType;
	}

	public void setMomentumType(MomentumTypeEnum momentumType) {
		this.momentumType = momentumType;
	}

	public float getThresholdMomentum() {
		return thresholdMomentum;
	}

	public void setThresholdMomentum(float thresholdMomentum) {
		this.thresholdMomentum = thresholdMomentum;
	}

	public boolean isUseMacdNumPeriods() {
		return useMacdNumPeriods;
	}

	public void setUseMacdNumPeriods(boolean useMacdNumPeriods) {
		this.useMacdNumPeriods = useMacdNumPeriods;
	}

	public int getThresholdMacdNumPeriods() {
		return thresholdMacdNumPeriods;
	}

	public void setThresholdMacdNumPeriods(int thresholdMacdNumPeriods) {
		this.thresholdMacdNumPeriods = thresholdMacdNumPeriods;
	}

	public boolean isUseMacdSignal() {
		return useMacdSignal;
	}

	public void setUseMacdSignal(boolean useMacdSignal) {
		this.useMacdSignal = useMacdSignal;
	}

	public boolean isUse_834_Strategy() {
		return use_834_Strategy;
	}

	public void setUse_834_Strategy(boolean use_834_Strategy) {
		this.use_834_Strategy = use_834_Strategy;
	}

	public int getStrategy_834_State() {
		return strategy_834_State;
	}

	public void setStrategy_834_State(int strategy_834_State) {
		this.strategy_834_State = strategy_834_State;
	}

	public int getStrategy_834_StopLossState() {
		return strategy_834_StopLossState;
	}

	public void setStrategy_834_StopLossState(int strategy_834_StopLossState) {
		this.strategy_834_StopLossState = strategy_834_StopLossState;
	}

	public float getWhenBought8ema() {
		return whenBought8ema;
	}

	public void setWhenBought8ema(float whenBought8ema) {
		this.whenBought8ema = whenBought8ema;
	}

	public float getWhenBought20sma() {
		return whenBought20sma;
	}

	public void setWhenBought20sma(float whenBought20sma) {
		this.whenBought20sma = whenBought20sma;
	}

	public int getNumPeriodsSinceSoldThreshold() {
		return numPeriodsSinceSoldThreshold;
	}

	public void setNumPeriodsSinceSoldThreshold(int numPeriodsSinceSoldThreshold) {
		this.numPeriodsSinceSoldThreshold = numPeriodsSinceSoldThreshold;
	}

	public int getNumPeriodsSinceOver34Threshold() {
		return numPeriodsSinceOver34Threshold;
	}

	public void setNumPeriodsSinceOver34Threshold(int numPeriodsSinceOver34Threshold) {
		this.numPeriodsSinceOver34Threshold = numPeriodsSinceOver34Threshold;
	}

	public int getEma8Threshold() {
		return ema8Threshold;
	}

	public void setEma8Threshold(int ema8Threshold) {
		this.ema8Threshold = ema8Threshold;
	}

	public int getSma14Threshold() {
		return sma14Threshold;
	}

	public void setSma14Threshold(int sma14Threshold) {
		this.sma14Threshold = sma14Threshold;
	}

	public int getSma20Threshold() {
		return sma20Threshold;
	}

	public void setSma20Threshold(int sma20Threshold) {
		this.sma20Threshold = sma20Threshold;
	}

	public int getEma34Threshold() {
		return ema34Threshold;
	}

	public void setEma34Threshold(int ema34Threshold) {
		this.ema34Threshold = ema34Threshold;
	}

	public int getSma20StopLossThreshold() {
		return sma20StopLossThreshold;
	}

	public void setSma20StopLossThreshold(int sma20StopLossThreshold) {
		this.sma20StopLossThreshold = sma20StopLossThreshold;
	}

	public int getEma34StopLossThreshold() {
		return ema34StopLossThreshold;
	}

	public void setEma34StopLossThreshold(int ema34StopLossThreshold) {
		this.ema34StopLossThreshold = ema34StopLossThreshold;
	}

	public boolean isFilterIntradaySpikes() {
		return filterIntradaySpikes;
	}

	public void setFilterIntradaySpikes(boolean filterIntradaySpikes) {
		this.filterIntradaySpikes = filterIntradaySpikes;
	}

	public float getOnEntryOrderPercent() {
		return onEntryOrderPercent;
	}

	public void setOnEntryOrderPercent(float onEntryOrderPercent) {
		this.onEntryOrderPercent = onEntryOrderPercent;
	}

	public float getOnConfirmOrderPercent() {
		return onConfirmOrderPercent;
	}

	public void setOnConfirmOrderPercent(float onconfirmOrderPercent) {
		this.onConfirmOrderPercent = onconfirmOrderPercent;
	}
}
