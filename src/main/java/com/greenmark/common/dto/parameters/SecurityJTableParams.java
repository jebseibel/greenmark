package com.greenmark.common.dto.parameters;

import java.io.Serializable;

import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: SecurityJTableParams</p>
 * <p>Description: This class contains the parameters used to display information on the trading client GUI's buckets panels.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class SecurityJTableParams implements Serializable {
	public static final String CLASSNAME = "SecurityJTableParams";
	private static final long serialVersionUID = 1L;

	private boolean use_834_Strategy;
	private String bucketStrategyLabel;
	private String strategy834_StopLossLabel;
	private String harvestStrategyLabel;

	private int numPeriodsInBucket;

	public SecurityJTableParams() {

	}

	// ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
	public SecurityJTableParams(String xmldata) {
		try {
			this.use_834_Strategy = UTXmlUtils.getXmlDataAsBoolean(xmldata, "USE_834_STRATEGY");
			this.bucketStrategyLabel = UTXmlUtils.getXmlData(xmldata, "BUCKET_STRATEGY_LABEL");
			this.strategy834_StopLossLabel = UTXmlUtils.getXmlData(xmldata, "STRATEGY_834_STOPLOSS_LABEL");
			this.harvestStrategyLabel = UTXmlUtils.getXmlData(xmldata, "HARVEST_STRATEGY_LABEL");

		} catch (Exception e) {
			System.out.println("Error creating " + CLASSNAME + " from XML, message: " + e.getMessage());
		}
	}

	public String toXmlWrapper(String prefix, String endline) {
		StringBuffer stb = new StringBuffer();
		stb.append(prefix + "<SECURITY_JTABLE_PARAMETERS>" + endline);
		stb.append(prefix + toXml(prefix, endline) + endline);
		stb.append(prefix + "</SECURITY_JTABLE_PARAMETERS>" + endline);
		return stb.toString();
	}

	public String toXml(String prefix, String endline) {
		StringBuffer stb = new StringBuffer();

		stb.append(prefix + UTDisplayFormatter.TAB + "<USE_834_STRATEGY>" + this.use_834_Strategy + "</USE_834_STRATEGY>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<BUCKET_STRATEGY_LABEL>" + this.bucketStrategyLabel + "</BUCKET_STRATEGY_LABEL>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<STRATEGY_834_STOPLOSS_LABEL>" + this.strategy834_StopLossLabel + "</STRATEGY_834_STOPLOSS_LABEL>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<HARVEST_STRATEGY_LABEL>" + this.harvestStrategyLabel + "</HARVEST_STRATEGY_LABEL>" + endline);

		return stb.toString();
	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public boolean isUse_834_Strategy() {
		return use_834_Strategy;
	}

	public void setUse_834_Strategy(boolean use_834_Strategy) {
		this.use_834_Strategy = use_834_Strategy;
	}

	public String getBucketStrategyLabel() {
		return bucketStrategyLabel;
	}

	public void setBucketStrategyLabel(String bucketStrategyLabel) {
		this.bucketStrategyLabel = bucketStrategyLabel;
	}

	public String getStrategy834_StopLossLabel() {
		return strategy834_StopLossLabel;
	}

	public void setStrategy834_StopLossLabel(String strategy834_StopLossLabel) {
		this.strategy834_StopLossLabel = strategy834_StopLossLabel;
	}

	public String getHarvestStrategyLabel() {
		return harvestStrategyLabel;
	}

	public void setHarvestStrategyLabel(String harvestStrategyLabel) {
		this.harvestStrategyLabel = harvestStrategyLabel;
	}
}
