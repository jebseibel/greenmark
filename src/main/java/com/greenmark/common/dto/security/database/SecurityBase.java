package com.greenmark.common.dto.security.database;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsBroker;
import com.greenmark.common.GmConstantsStrategy;
import com.greenmark.common.dto.security.database.decorator.SecurityDbDecorator;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: SecurityBase</p>
 * <p>Description: This class extends its base DTO and adds other DTO object instances.  These are used by the trading client to add additional 
 *     information it needs when calculating, but does not need to be stored in the database.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class SecurityBase extends SecurityDbDecorator implements Serializable {
    public static final String CLASSNAME = "SecurityBase";
    private static final long serialVersionUID = 1L;

    protected int status = GmConstantsBroker.STATUS_NEW;
    protected boolean newlyHarvested = false;

    protected LocalDateTime movedToBucketDatetime;
    protected int currentTimeframe; // Which bucket is it in?
    protected int movedFromTimeframe; // Which Bucket did it come from?

    protected float minute60LowHighPt = 0;
    protected boolean minute60LowHighCalculated = false;
    protected float dailyLowHighPt = 0;
    protected boolean dailyLowHighCalculated = false;

    protected float stockWatchSize = 0;
    protected float stockWatchAmount = 0;

    protected float calculatedEntryPrice = 0; // If order is placed to buy/sell security
    protected float highLowPriceOnOrder = 0; // This is the high (long) or low (short) executedPrice when we calculated the entryPrice
    protected float orderSize = 0;
    protected float orderAmount = 0;
    protected int orderError = GmConstants.ORDER_ERROR_DEFAULT;

    protected boolean priceHistoryInitialized = false;
    protected boolean rawDataPrinted = false;

    protected int sellStrategyWhenCreated = GmConstantsStrategy.NO_SELL_STRATEGY;

    public SecurityBase() {
        super();
    }

    public SecurityBase(String displaySymbol, String dbQuerySymbol, int longOrShort, int stockType) {
        super(displaySymbol, dbQuerySymbol, longOrShort, stockType);
    }

//	// ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
//	public SecurityBase(String xmldata) {
//		super(xmldata, trace);
//
//		try {
//			status = UTXmlUtils.getXmlDataAsInt(xmldata, "STOCK_STATUS");
//			newlyHarvested = UTXmlUtils.getXmlDataAsBoolean(xmldata, "NEWLY_HARVESTED");
//
//			if (UTUtils.isNotNorE(UTXmlUtils.getXmlData(xmldata, "MOVED_TO_BUCKET_DATETIME")))
//				movedToBucketDatetime = UTDatetime.fromDbString(UTXmlUtils.getXmlData(xmldata, "MOVED_TO_BUCKET_DATETIME"));
//			currentTimeframe = UTXmlUtils.getXmlDataAsInt(xmldata, "CURRENT_TIMEFRAME");
//			movedFromTimeframe = UTXmlUtils.getXmlDataAsInt(xmldata, "MOVED_FROM_TIMEFRAME");
//
//			minute60LowHighPt = UTXmlUtils.getXmlDataAsFloat(xmldata, "MIN60_LOW_HIGH_POINT");
//			minute60LowHighCalculated = UTXmlUtils.getXmlDataAsBoolean(xmldata, "MIN60_LOW_HIGH_CALCULATED");
//			dailyLowHighPt = UTXmlUtils.getXmlDataAsFloat(xmldata, "DAILY_LOW_HIGH_PT");
//			dailyLowHighCalculated = UTXmlUtils.getXmlDataAsBoolean(xmldata, "DAILY_LOW_HIGH_CALCULATED");
//
//			stockWatchSize = UTXmlUtils.getXmlDataAsInt(xmldata, "STOCK_WATCH_SIZE");
//			stockWatchAmount = UTXmlUtils.getXmlDataAsFloat(xmldata, "STOCK_WATCH_AMOUNT");
//
//			calculatedEntryPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "CALCULATED_ENTRY_PRICE");
//			highLowPriceOnOrder = UTXmlUtils.getXmlDataAsFloat(xmldata, "HIGH_LOW_PRICE_ON_ORDER");
//
//			orderSize = UTXmlUtils.getXmlDataAsInt(xmldata, "ORDER_SIZE");
//			orderAmount = UTXmlUtils.getXmlDataAsFloat(xmldata, "ORDER_AMOUNT");
//			orderError = UTXmlUtils.getXmlDataAsInt(xmldata, "ORDER_ERROR");
//
//			priceHistoryInitialized = UTXmlUtils.getXmlDataAsBoolean(xmldata, "PRICE_HISTORY_INITIALIZED");
//			rawDataPrinted = UTXmlUtils.getXmlDataAsBoolean(xmldata, "RAW_DATA_PRINTED");
//
//			sellStrategyWhenCreated = UTXmlUtils.getXmlDataAsInt(xmldata, "SELL_STRATEGY_WHEN_CREATED");
//		} catch (Exception e) {
//			System.out.println("Error creating stock from XML, message: " + e.getMessage());
//		}
//	}
//
//	public String toXmlWrapper(String prefix, String endline) {
//        String stb = prefix + "<SECURITY>" + endline +
//                prefix + toXml(prefix, endline) + endline +
//                prefix + "</SECURITY>" + endline;
//		return stb;
//	}
//
//	public String toXml(String prefix, String endline) {
//		StringBuffer stb = new StringBuffer();
//		String TAB = UTDisplayFormatter.TAB;
//
//		stb.append(super.toXml(prefix, endline));
//
//		stb.append(prefix + UTDisplayFormatter.TAB + "<STOCK_STATUS>" + this.status + "</STOCK_STATUS>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<NEWLY_HARVESTED>" + this.newlyHarvested + "</NEWLY_HARVESTED>" + endline);
//
//		if (this.movedToBucketDatetime != null)
//			stb.append(prefix + UTDisplayFormatter.TAB + "<MOVED_TO_BUCKET_DATETIME>" + UTDatetime.toDbString(this.movedToBucketDatetime) + "</MOVED_TO_BUCKET_DATETIME>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<CURRENT_TIMEFRAME>" + this.currentTimeframe + "</CURRENT_TIMEFRAME>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<MOVED_FROM_TIMEFRAME>" + this.movedFromTimeframe + "</MOVED_FROM_TIMEFRAME>" + endline);
//
//		stb.append(prefix + UTDisplayFormatter.TAB + "<MINUTE60_LOW_HIGH_PT>" + this.minute60LowHighPt + "</MINUTE60_LOW_HIGH_PT>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<MIN60_LOW_HIGH_CALCULATED>" + this.minute60LowHighCalculated + "</MIN60_LOW_HIGH_CALCULATED>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<DAILY_LOW_HIGH_PT>" + this.dailyLowHighPt + "</DAILY_LOW_HIGH_PT>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<DAILY_LOW_HIGH_CALCULATED>" + this.dailyLowHighCalculated + "</DAILY_LOW_HIGH_CALCULATED>" + endline);
//
//		stb.append(prefix + UTDisplayFormatter.TAB + "<STOCK_WATCH_SIZE>" + this.stockWatchSize + "</STOCK_WATCH_SIZE>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<STOCK_WATCH_AMOUNT>" + this.stockWatchAmount + "</STOCK_WATCH_AMOUNT>" + endline);
//
//		stb.append(prefix + UTDisplayFormatter.TAB + "<CALCULATED_ENTRY_PRICE>" + this.calculatedEntryPrice + "</CALCULATED_ENTRY_PRICE>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<HIGH_LOW_PRICE_ON_ORDER>" + this.highLowPriceOnOrder + "</HIGH_LOW_PRICE_ON_ORDER>" + endline);
//
//		stb.append(prefix + UTDisplayFormatter.TAB + "<ORDER_SIZE>" + this.orderSize + "</ORDER_SIZE>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<ORDER_AMOUNT>" + this.orderAmount + "</ORDER_AMOUNT>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<ORDER_ERROR>" + this.orderError + "</ORDER_ERROR>" + endline);
//
//		stb.append(prefix + UTDisplayFormatter.TAB + "<PRICE_HISTORY_INITIALIZED>" + this.priceHistoryInitialized + "</PRICE_HISTORY_INITIALIZED>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<RAW_DATA_PRINTED>" + this.rawDataPrinted + "</RAW_DATA_PRINTED>" + endline);
//
//		stb.append(prefix + UTDisplayFormatter.TAB + "<SELL_STRATEGY_WHEN_CREATED>" + this.sellStrategyWhenCreated + "</SELL_STRATEGY_WHEN_CREATED>" + endline);
//
//		return stb.toString();
//	}

    // ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLongOrShort() {
        return longOrShort;
    }

    public void setLongOrShort(int longOrShort) {
        this.longOrShort = longOrShort;
    }

    public int getStockType() {
        return stockType;
    }

    public void setStockType(int stockType) {
        this.stockType = stockType;
    }

    public boolean isPriceHistoryInitialized() {
        return priceHistoryInitialized;
    }

    public void setPriceHistoryInitialized(boolean priceHistoryInitialized) {
        this.priceHistoryInitialized = priceHistoryInitialized;
    }

    public boolean isRawDataPrinted() {
        return rawDataPrinted;
    }

    public void setRawDataPrinted(boolean rawDataPrinted) {
        this.rawDataPrinted = rawDataPrinted;
    }

    public int getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(int buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

    public int getSellStrategyWhenCreated() {
        return sellStrategyWhenCreated;
    }

    public void setSellStrategyWhenCreated(int sellStrategyWhenCreated) {
        this.sellStrategyWhenCreated = sellStrategyWhenCreated;
    }

    public boolean isNewlyHarvested() {
        return newlyHarvested;
    }

    public void setNewlyHarvested(boolean newlyHarvested) {
        this.newlyHarvested = newlyHarvested;
    }

    public int getNumPeriodsInBucket() {
        return numPeriodsInBucket;
    }

    public void setNumPeriodsInBucket(int numPeriodsInBucket) {
        this.numPeriodsInBucket = numPeriodsInBucket;
    }

    public LocalDateTime getMovedToBucketDatetime() {
        return movedToBucketDatetime;
    }

    public void setMovedToBucketDatetime(LocalDateTime movedToBucketDatetime) {
        this.movedToBucketDatetime = movedToBucketDatetime;
    }

    public int getCurrentTimeframe() {
        return currentTimeframe;
    }

    public void setCurrentTimeframe(int currentTimeframe) {
        this.currentTimeframe = currentTimeframe;
    }

    public int getMovedFromTimeframe() {
        return movedFromTimeframe;
    }

    public void setMovedFromTimeframe(int movedFromTimeframe) {
        this.movedFromTimeframe = movedFromTimeframe;
    }

    public float getMinute60LowHighPt() {
        return minute60LowHighPt;
    }

    public void setMinute60LowHighPt(float minute60LowHighPt) {
        this.minute60LowHighPt = minute60LowHighPt;
    }

    public boolean isMinute60LowHighCalculated() {
        return minute60LowHighCalculated;
    }

    public void setMinute60LowHighCalculated(boolean minute60LowHighCalculated) {
        this.minute60LowHighCalculated = minute60LowHighCalculated;
    }

    public float getDailyLowHighPt() {
        return dailyLowHighPt;
    }

    public void setDailyLowHighPt(float dailyLowHighPt) {
        this.dailyLowHighPt = dailyLowHighPt;
    }

    public boolean isDailyLowHighCalculated() {
        return dailyLowHighCalculated;
    }

    public void setDailyLowHighCalculated(boolean dailyLowHighCalculated) {
        this.dailyLowHighCalculated = dailyLowHighCalculated;
    }

    public float getCalculatedEntryPrice() {
        return calculatedEntryPrice;
    }

    public void setCalculatedEntryPrice(float calculatedEntryPrice) {
        this.calculatedEntryPrice = calculatedEntryPrice;
    }

    public float getHighLowPriceOnOrder() {
        return highLowPriceOnOrder;
    }

    public void setHighLowPriceOnOrder(float highLowPriceOnOrder) {
        this.highLowPriceOnOrder = highLowPriceOnOrder;
    }

    public float getStockWatchSize() {
        return stockWatchSize;
    }

    public void setStockWatchSize(float stockWatchSize) {
        this.stockWatchSize = stockWatchSize;
    }

    public float getStockWatchAmount() {
        return stockWatchAmount;
    }

    public void setStockWatchAmount(float stockWatchAmount) {
        this.stockWatchAmount = stockWatchAmount;
    }

    public float getOrderSize() {
        return orderSize;
    }

    public void setOrderSize(float orderSize) {
        this.orderSize = orderSize;
    }

    public float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getOrderError() {
        return orderError;
    }

    public void setOrderError(int orderError) {
        this.orderError = orderError;
    }
}
