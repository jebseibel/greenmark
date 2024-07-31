package com.greenmark.common.dto.strategy;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.greenmark.common.enums.RejectedTypeEnum;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: RejectedSecurityDto</p>
  * <p>Description: This DTO is for the results database rejected_securities table.  
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

public class RejectedSecurityDto implements Serializable {
    public static final String CLASSNAME = "RejectedSecurityDto";
    private static final long serialVersionUID = 1L;

    protected long id;
    protected int active;

    protected long scenarioId;
    protected long securityId;

    protected String stockSymbol; // This is for the DB record and the map and map keys.
    protected String compactedExchangeSymbol;
    protected String stockName; // This is for the DB record only.
    protected String stockStrategyAcronym; // This is for the to/from XML and the map keys.

    protected String rejectedReason;
    protected String description;
    protected RejectedTypeEnum rejectedType;

    protected LocalDateTime rejectedDatetime;
    protected long rejectedNumdate;
    protected int rejectedFromTimeframe;

    protected int numPeriodsInBucket = 0;
    protected int remainingPeriodsInBucket = 0;
    protected int numPeriodsAgingThresh;

    protected LocalDateTime restoredToModelDatetime;

    protected String displaySymbol;
    protected int longOrShort;

//	public RejectedSecurityDto() {
//	}
//
//	// ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
//	public RejectedSecurityDto(String xmldata) {
//		this.id = UTXmlUtils.getXmlDataAsLong(xmldata, "ID");
//
//		this.securityId = UTXmlUtils.getXmlDataAsLong(xmldata, "SECURITY_ID");
//		this.scenarioId = UTXmlUtils.getXmlDataAsLong(xmldata, "SCENARIO_ID");
//
//		this.rejectedReason = UTXmlUtils.getXmlData(xmldata, "REJECTED_REASON");
//		this.description = UTXmlUtils.getXmlData(xmldata, "DESCRIPTION");
//		String rejectedType = UTXmlUtils.getXmlData(xmldata, "REJECTED_TYPE");
//		RejectedTypeEnum rejectedTypeEnum = RejectedTypeEnum.getByName(rejectedType).orElse(null);
//		this.rejectedType = rejectedTypeEnum;
//
//		this.rejectedDatetime = UTXmlUtils.getXmlDataAsLDT(xmldata, "REJECTED_DATETIME");
//		this.rejectedNumdate = UTXmlUtils.getXmlDataAsInt(xmldata, "REJECTED_NUMDATE");
//		this.rejectedFromTimeframe = UTXmlUtils.getXmlDataAsInt(xmldata, "REJECTED_FROM_TIMEFRAME");
//
//		this.numPeriodsInBucket = UTXmlUtils.getXmlDataAsInt(xmldata, "REJECTED_NUM_PERIODS_IN_BUCKET");
//		this.remainingPeriodsInBucket = UTXmlUtils.getXmlDataAsInt(xmldata, "REJECTED_REMAINING_PERIODS_IN_BUCKET");
//		this.numPeriodsAgingThresh = UTXmlUtils.getXmlDataAsInt(xmldata, "REJECTED_NUM_PERIODS_AGING_THRESHOLD");
//
//		this.restoredToModelDatetime = UTXmlUtils.getXmlDataAsLDT(xmldata, "RESTORED_TO_MODEL_DATETIME");
//
//		this.stockStrategyAcronym = UTXmlUtils.getXmlData(xmldata, "STOCK_STRATEGY_ACRONYM");
//		this.displaySymbol = UTXmlUtils.getXmlData(xmldata, "DISPLAY_SYMBOL");
//		this.stockSymbol = UTXmlUtils.getXmlData(xmldata, "STOCK_SYMBOL");
//		this.compactedExchangeSymbol = UTXmlUtils.getXmlData(xmldata, "COMPACTED_EXCHANGE_SYMBOL");
//		this.stockName = UTXmlUtils.getXmlData(xmldata, "STOCK_NAME");
//		this.longOrShort = UTXmlUtils.getXmlDataAsInt(xmldata, "LONG_OR_SHORT");
//		this.active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");
//	}
//
//	public String toXmlWrapper(String prefix, String endline) {
//        String stb = prefix + "<REJECTED_SECURITY>" + endline +
//                toXml(prefix, endline) +
//                prefix + "</REJECTED_SECURITY>" + endline;
//		return stb;
//	}
//
//	public String toXml(String prefix, String endline) {
//
//        String stb = prefix + UTDisplayFormatter.TAB + "<ID>" + this.id + "</ID>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<SECURITY_ID>" + this.securityId + "</SECURITY_ID>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<SCENARIO_ID>" + this.scenarioId + "</SCENARIO_ID>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<REJECTED_REASON>" + this.rejectedReason + "</REJECTED_REASON>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<DESCRIPTION>" + this.description + "</DESCRIPTION>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<REJECTED_TYPE>" + this.rejectedType.getName() + "</REJECTED_TYPE>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<REJECTED_DATETIME>" + UTDatetime.toDbString(this.rejectedDatetime) + "</REJECTED_DATETIME>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<REJECTED_NUMDATE>" + this.rejectedNumdate + "</REJECTED_NUMDATE>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<REJECTED_FROM_TIMEFRAME>" + this.rejectedFromTimeframe + "</REJECTED_FROM_TIMEFRAME>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<REJECTED_NUM_PERIODS_IN_BUCKET>" + this.numPeriodsInBucket + "</REJECTED_NUM_PERIODS_IN_BUCKET>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<REJECTED_REMAINING_PERIODS_IN_BUCKET>" + this.remainingPeriodsInBucket + "</REJECTED_REMAINING_PERIODS_IN_BUCKET>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<REJECTED_NUM_PERIODS_AGING_THRESHOLD>" + this.numPeriodsAgingThresh + "</REJECTED_NUM_PERIODS_AGING_THRESHOLD>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<RESTORED_TO_MODEL_DATETIME>" + UTDatetime.toDbString(this.restoredToModelDatetime) + "</RESTORED_TO_MODEL_DATETIME>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<STOCK_STRATEGY_ACRONYM>" + this.stockStrategyAcronym + "</STOCK_STRATEGY_ACRONYM>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<DISPLAY_SYMBOL>" + this.displaySymbol + "</DISPLAY_SYMBOL>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<STOCK_SYMBOL>" + this.stockSymbol + "</STOCK_SYMBOL>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<COMPACTED_EXCHANGE_SYMBOL>" + this.compactedExchangeSymbol + "</COMPACTED_EXCHANGE_SYMBOL>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<STOCK_NAME>" + this.stockName + "</STOCK_NAME>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<LONG_OR_SHORT>" + this.longOrShort + "</LONG_OR_SHORT>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<ACTIVE>" + this.getActive() + "</ACTIVE>" + endline;
//
//		return stb;
//	}

    /////////////////////////////////// SETTERS-GETTERS ///////////////////////////////////////
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

    public long getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(long scenarioId) {
        this.scenarioId = scenarioId;
    }

    public long getSecurityId() {
        return securityId;
    }

    public void setSecurityId(long securityId) {
        this.securityId = securityId;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getCompactedExchangeSymbol() {
        return compactedExchangeSymbol;
    }

    public void setCompactedExchangeSymbol(String compactedExchangeSymbol) {
        this.compactedExchangeSymbol = compactedExchangeSymbol;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockStrategyAcronym() {
        return stockStrategyAcronym;
    }

    public void setStockStrategyAcronym(String stockStrategyAcronym) {
        this.stockStrategyAcronym = stockStrategyAcronym;
    }

    public String getRejectedReason() {
        return rejectedReason;
    }

    public void setRejectedReason(String rejectedReason) {
        this.rejectedReason = rejectedReason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RejectedTypeEnum getRejectedType() {
        return rejectedType;
    }

    public void setRejectedType(RejectedTypeEnum rejectedType) {
        this.rejectedType = rejectedType;
    }

    public LocalDateTime getRejectedDatetime() {
        return rejectedDatetime;
    }

    public void setRejectedDatetime(LocalDateTime rejectedDatetime) {
        this.rejectedDatetime = rejectedDatetime;
    }

    public long getRejectedNumdate() {
        return rejectedNumdate;
    }

    public void setRejectedNumdate(long rejectedNumdate) {
        this.rejectedNumdate = rejectedNumdate;
    }

    public int getRejectedFromTimeframe() {
        return rejectedFromTimeframe;
    }

    public void setRejectedFromTimeframe(int rejectedFromTimeframe) {
        this.rejectedFromTimeframe = rejectedFromTimeframe;
    }

    public int getNumPeriodsInBucket() {
        return numPeriodsInBucket;
    }

    public void setNumPeriodsInBucket(int numPeriodsInBucket) {
        this.numPeriodsInBucket = numPeriodsInBucket;
    }

    public int getRemainingPeriodsInBucket() {
        return remainingPeriodsInBucket;
    }

    public void setRemainingPeriodsInBucket(int remainingPeriodsInBucket) {
        this.remainingPeriodsInBucket = remainingPeriodsInBucket;
    }

    public int getNumPeriodsAgingThresh() {
        return numPeriodsAgingThresh;
    }

    public void setNumPeriodsAgingThresh(int numPeriodsAgingThresh) {
        this.numPeriodsAgingThresh = numPeriodsAgingThresh;
    }

    public LocalDateTime getRestoredToModelDatetime() {
        return restoredToModelDatetime;
    }

    public void setRestoredToModelDatetime(LocalDateTime restoredToModelDatetime) {
        this.restoredToModelDatetime = restoredToModelDatetime;
    }

    public String getDisplaySymbol() {
        return displaySymbol;
    }

    public void setDisplaySymbol(String displaySymbol) {
        this.displaySymbol = displaySymbol;
    }

    public int getLongOrShort() {
        return longOrShort;
    }

    public void setLongOrShort(int longOrShort) {
        this.longOrShort = longOrShort;
    }
}
