package com.greenmark.common.dto.broker;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsBroker;
import com.greenmark.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: PositionDto</p>
 * <p>Description: This DTO is for the results database positions table.  A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 * DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
@Slf4j
public class PositionDto implements Comparator, Serializable {
    public static final String CLASSNAME = "PositionDto";
    private static final long serialVersionUID = 1L;

    protected long id; // leave as null for hibernate
    protected int active = GmConstants.OBJECT_ACTIVE;

    protected long accountId = 0; // set to zero to find insert errors
    protected long securityId = 0;

    protected int positionStatus = GmConstantsBroker.POSITION_STATUS_NORMAL;
    protected int harvestStrategyTypeId = 0; //

    protected int bucketStrategyStateId = 0;
    protected int originBucketTimeframe = GmConstants.TYPE_DAILY;
    protected int longOrShort; // Needed for DB retrievals only

    protected float painPrice = 0;

    protected float percentGrowthPrices = 0; // Does not include any transaction of margin fees
    protected float percentGrowth = 0;

    protected float totalGrowthPricesAmount = 0; // Does not include any transaction of margin fees
    protected float totalGrowthAmount = 0;

    protected float totalTransactionFees = 0;
    protected float totalMarginFees = 0;

    // Buy Order Summary Info
    protected float buyExecutedPrice = 0;
    protected float buyEntryPrice = 0;
    protected float buyExecutedNumShares = 0;
    protected UTCalendarTime buyExecutedDatetime;

    // Sell Order Summary Info
    protected float sellExecutedPrice = 0;
    protected float sellEntryPrice = 0;
    protected float sellExecutedNumShares = 0;
    protected UTCalendarTime sellExecutedDatetime;

    // One of TYPE_SELL or TYPE_COMPLETED. First phase of Position is Sell. Determines where it's global stock data is stored on BucketsServiceHelper.
    protected int positionPhase = GmConstantsBroker.TYPE_SELL;
    protected boolean positionPainedOutNormally = false;

    protected int numProfitTakeOrders = 0;
    protected float numProfitTakeShares = 0;

    // Used by website queries - TODO: Move to PositionDecorator
    protected int totalNumMin60Disqualified;
    protected int totalNumMin15Disqualified;
    protected int totalNumMin05Disqualified;

    /************ Dropcat **************/
    protected boolean positionDropcat; // Needed for DB retrievals only
    protected boolean positionDropcatDoubledDown; // Needed for DB retrievals only
    protected boolean positionDropcatSoldEndofday; // Needed for DB retrievals only
    protected boolean positionDropcatPainOut; // Needed for DB retrievals only
    protected boolean positionDropcatSuccess; // Needed for DB retrievals only

    protected float dropcatPainPrice = 0;

    protected String userNotes = ""; // For the website.

    public PositionDto() {
        positionPhase = GmConstantsBroker.TYPE_SELL;
    }

    public PositionDto(int harvestStrategyTypeId, int bucketStrategyStateId, int originBucketTimeframe) {
        this.harvestStrategyTypeId = harvestStrategyTypeId;
        this.bucketStrategyStateId = bucketStrategyStateId;
        this.originBucketTimeframe = originBucketTimeframe;
    }

    public PositionDto(PositionDto oldPosition) {
        try {
            if (oldPosition != null)
                BeanUtils.copyProperties(this, oldPosition);
        } catch (InvocationTargetException ite) {
            System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage());
        } catch (IllegalAccessException iae) {
            System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
        }
    }

    // ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
    public PositionDto(String xmldata) {
        try {
            id = UTXmlUtils.getXmlDataAsLong(xmldata, "ID");
            active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");

            positionPhase = UTXmlUtils.getXmlDataAsInt(xmldata, "POSITION_PHASE");

            securityId = UTXmlUtils.getXmlDataAsInt(xmldata, "SECURITY_ID");
            accountId = UTXmlUtils.getXmlDataAsInt(xmldata, "ACCOUNT_ID");
            longOrShort = UTXmlUtils.getXmlDataAsInt(xmldata, "LONG_OR_SHORT");

            positionStatus = UTXmlUtils.getXmlDataAsInt(xmldata, "POSITION_STATUS");
            harvestStrategyTypeId = UTXmlUtils.getXmlDataAsInt(xmldata, "HARVEST_STRATEGY_TYPE_ID");

            bucketStrategyStateId = UTXmlUtils.getXmlDataAsInt(xmldata, "STOCK_STRATEGY_ID");
            originBucketTimeframe = UTXmlUtils.getXmlDataAsInt(xmldata, "ORIGIN_BUCKET_TIMEFRAME");

            dropcatPainPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "DROPCAT_PAIN_PRICE");
            buyExecutedPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "BUY_EXECUTED_PRICE");
            buyEntryPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "BUY_ENTRY_PRICE");
            buyExecutedNumShares = UTXmlUtils.getXmlDataAsFloat(xmldata, "BUY_EXECUTED_NUM_SHARES");

            String this_buyExecutedDatetime = UTXmlUtils.getXmlData(xmldata, "BUY_EXECUTED_DATE");
            if (UTUtils.isNotNorE(this_buyExecutedDatetime))
                buyExecutedDatetime = new UTCalendarTime(this_buyExecutedDatetime);

            sellExecutedPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "SELL_EXECUTED_PRICE");
            sellEntryPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "SELL_ENTRY_PRICE");
            sellExecutedNumShares = UTXmlUtils.getXmlDataAsFloat(xmldata, "SELL_EXECUTED_NUM_SHARES");

            String this_sellExecutedDatetime = UTXmlUtils.getXmlData(xmldata, "SELL_EXECUTED_DATE");
            if (UTUtils.isNotNorE(this_sellExecutedDatetime))
                sellExecutedDatetime = new UTCalendarTime(this_sellExecutedDatetime);

            painPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "PAIN_PRICE");
            percentGrowthPrices = UTXmlUtils.getXmlDataAsFloat(xmldata, "PERCENT_GROWTH_PRICES");
            percentGrowth = UTXmlUtils.getXmlDataAsFloat(xmldata, "PERCENT_GROWTH");
            totalGrowthPricesAmount = UTXmlUtils.getXmlDataAsFloat(xmldata, "TOTAL_GROWTH_PRICES_AMT");
            totalGrowthAmount = UTXmlUtils.getXmlDataAsFloat(xmldata, "TOTAL_GROWTH_AMT");
            totalTransactionFees = UTXmlUtils.getXmlDataAsFloat(xmldata, "TOTAL_TRANSACTION_FEES");
            totalMarginFees = UTXmlUtils.getXmlDataAsFloat(xmldata, "TOTAL_MARGIN_FEES");
            numProfitTakeOrders = UTXmlUtils.getXmlDataAsInt(xmldata, "NUM_PROFIT_TAKE_ORDERS");
            userNotes = UTXmlUtils.getXmlData(xmldata, "USER_NOTES");
            this.painPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "PAIN_PRICE");
        } catch (Exception e) {
            log.error("Exception in Current Position xml constructor message: " + e.getMessage());
        }

    }

    public String dbSummary() {
        String stb = " > > " + CLASSNAME + " :: " +
                "id [" + id + "] " +
                "accountId [" + accountId + "] " +
                "active [" + active + "] ";
        return stb;
    }

    // This is not used, but we have to implement it.
    public int compare(Object pos1, Object pos2) {
        return -1;
    }

    public String toXmlWrapper(String prefix, String endline) {
        String stb = prefix + "<POSITION>" + endline +
                prefix + toXml(prefix, endline) + endline +
                prefix + "</POSITION>" + endline;
        return stb;
    }

    public String toXml(String prefix, String endline) {
        String formattedDate = "";
        if (buyExecutedDatetime != null) {
            LocalDateTime buyExecutedLDT = UTDatetime.fromUTCalendarTime(buyExecutedDatetime);
            formattedDate = UTDatetime.toDbString(buyExecutedLDT);
        }
        String formattedSellDate = "";
        if (sellExecutedDatetime != null) {
            LocalDateTime sellExecutedLDT = UTDatetime.fromUTCalendarTime(sellExecutedDatetime);
            formattedSellDate = UTDatetime.toDbString(sellExecutedLDT);
        }

        String stb = prefix + UTDisplayFormatter.TAB + "<ID>" + this.id + "</ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ACCOUNT_ID>" + this.accountId + "</ACCOUNT_ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SECURITY_ID>" + this.securityId + "</SECURITY_ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_OR_SHORT>" + this.longOrShort + "</LONG_OR_SHORT>" + endline +
                prefix + UTDisplayFormatter.TAB + "<POSITION_PHASE>" + this.positionPhase + "</POSITION_PHASE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<POSITION_STATUS>" + positionStatus + "</POSITION_STATUS>" + endline +
                prefix + UTDisplayFormatter.TAB + "<HARVEST_STRATEGY_TYPE_ID>" + harvestStrategyTypeId + "</HARVEST_STRATEGY_TYPE_ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<STOCK_STRATEGY_ID>" + bucketStrategyStateId + "</STOCK_STRATEGY_ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ORIGIN_BUCKET_TIMEFRAME>" + originBucketTimeframe + "</ORIGIN_BUCKET_TIMEFRAME>" + endline +
                prefix + UTDisplayFormatter.TAB + "<DROPCAT_PAIN_PRICE>" + dropcatPainPrice + "</DROPCAT_PAIN_PRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<BUY_EXECUTED_PRICE>" + buyExecutedPrice + "</BUY_EXECUTED_PRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<BUY_ENTRY_PRICE>" + buyEntryPrice + "</BUY_ENTRY_PRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<BUY_EXECUTED_NUM_SHARES>" + buyExecutedNumShares + "</BUY_EXECUTED_NUM_SHARES>" + endline +
                prefix + UTDisplayFormatter.TAB + "<BUY_EXECUTED_DATE>" + formattedDate + "</BUY_EXECUTED_DATE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SELL_EXECUTED_PRICE>" + sellExecutedPrice + "</SELL_EXECUTED_PRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SELL_ENTRY_PRICE>" + sellEntryPrice + "</SELL_ENTRY_PRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SELL_EXECUTED_NUM_SHARES>" + sellExecutedNumShares + "</SELL_EXECUTED_NUM_SHARES>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SELL_EXECUTED_DATE>" + formattedSellDate + "</SELL_EXECUTED_DATE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<PAIN_PRICE>" + painPrice + "</PAIN_PRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<PERCENT_GROWTH_PRICES>" + percentGrowthPrices + "</PERCENT_GROWTH_PRICES>" + endline +
                prefix + UTDisplayFormatter.TAB + "<PERCENT_GROWTH>" + percentGrowth + "</PERCENT_GROWTH>" + endline +
                prefix + UTDisplayFormatter.TAB + "<TOTAL_GROWTH_PRICES_AMT>" + totalGrowthPricesAmount + "</TOTAL_GROWTH_PRICES_AMT>" + endline +
                prefix + UTDisplayFormatter.TAB + "<TOTAL_GROWTH_AMT>" + totalGrowthAmount + "</TOTAL_GROWTH_AMT>" + endline +
                prefix + UTDisplayFormatter.TAB + "<NUM_PROFIT_TAKE_ORDERS>" + numProfitTakeOrders + "</NUM_PROFIT_TAKE_ORDERS>" + endline +
                prefix + UTDisplayFormatter.TAB + "<USER_NOTES>" + userNotes + "</USER_NOTES>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ACTIVE>" + this.getActive() + "</ACTIVE>" + endline;

        return stb;
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

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getSecurityId() {
        return securityId;
    }

    public void setSecurityId(long securityId) {
        this.securityId = securityId;
    }

    public int getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(int positionStatus) {
        this.positionStatus = positionStatus;
    }

    public int getHarvestStrategyTypeId() {
        return harvestStrategyTypeId;
    }

    public void setHarvestStrategyTypeId(int harvestStrategyTypeId) {
        this.harvestStrategyTypeId = harvestStrategyTypeId;
    }

    public int getBucketStrategyStateId() {
        return bucketStrategyStateId;
    }

    public void setBucketStrategyStateId(int bucketStrategyId) {
        this.bucketStrategyStateId = bucketStrategyId;
    }

    public int getOriginBucketTimeframe() {
        return originBucketTimeframe;
    }

    public void setOriginBucketTimeframe(int originBucketTimeframe) {
        this.originBucketTimeframe = originBucketTimeframe;
    }

    public int getLongOrShort() {
        return longOrShort;
    }

    public void setLongOrShort(int longOrShort) {
        this.longOrShort = longOrShort;
    }

    public float getPainPrice() {
        return painPrice;
    }

    public void setPainPrice(float painPrice) {
        this.painPrice = painPrice;
    }

    public float getPercentGrowthPrices() {
        return percentGrowthPrices;
    }

    public void setPercentGrowthPrices(float percentGrowthPrices) {
        this.percentGrowthPrices = percentGrowthPrices;
    }

    public float getPercentGrowth() {
        return percentGrowth;
    }

    public void setPercentGrowth(float percentGrowth) {
        this.percentGrowth = percentGrowth;
    }

    public float getTotalGrowthPricesAmount() {
        return totalGrowthPricesAmount;
    }

    public void setTotalGrowthPricesAmount(float totalGrowthPricesAmount) {
        this.totalGrowthPricesAmount = totalGrowthPricesAmount;
    }

    public float getTotalGrowthAmount() {
        if (Float.isNaN(totalGrowthAmount))
            return 0.0F;

        return totalGrowthAmount;
    }

    public void setTotalGrowthAmount(float totalGrowthAmount) {
        this.totalGrowthAmount = totalGrowthAmount;
    }

    public float getTotalTransactionFees() {
        return totalTransactionFees;
    }

    public void setTotalTransactionFees(float totalTransactionFees) {
        this.totalTransactionFees = totalTransactionFees;
    }

    public float getTotalMarginFees() {
        return totalMarginFees;
    }

    public void setTotalMarginFees(float totalMarginFees) {
        this.totalMarginFees = totalMarginFees;
    }

    public float getBuyExecutedPrice() {
        return buyExecutedPrice;
    }

    public void setBuyExecutedPrice(float buyExecutedPrice) {
        this.buyExecutedPrice = buyExecutedPrice;
    }

    public float getBuyEntryPrice() {
        return buyEntryPrice;
    }

    public void setBuyEntryPrice(float buyEntryPrice) {
        this.buyEntryPrice = buyEntryPrice;
    }

    public float getBuyExecutedNumShares() {
        return buyExecutedNumShares;
    }

    public void setBuyExecutedNumShares(float buyExecutedNumShares) {
        this.buyExecutedNumShares = buyExecutedNumShares;
    }

    public UTCalendarTime getBuyExecutedDatetime() {
        return buyExecutedDatetime;
    }

    public void setBuyExecutedDatetime(UTCalendarTime buyExecutedDatetime) {
        this.buyExecutedDatetime = buyExecutedDatetime;
    }

    public float getSellExecutedPrice() {
        return sellExecutedPrice;
    }

    public void setSellExecutedPrice(float sellExecutedPrice) {
        this.sellExecutedPrice = sellExecutedPrice;
    }

    public float getSellEntryPrice() {
        return sellEntryPrice;
    }

    public void setSellEntryPrice(float sellEntryPrice) {
        this.sellEntryPrice = sellEntryPrice;
    }

    public float getSellExecutedNumShares() {
        return sellExecutedNumShares;
    }

    public void setSellExecutedNumShares(float sellExecutedNumShares) {
        this.sellExecutedNumShares = sellExecutedNumShares;
    }

    public UTCalendarTime getSellExecutedDatetime() {
        return sellExecutedDatetime;
    }

    public void setSellExecutedDatetime(UTCalendarTime sellExecutedDatetime) {
        this.sellExecutedDatetime = sellExecutedDatetime;
    }

    public int getPositionPhase() {
        return positionPhase;
    }

    public void setPositionPhase(int positionPhase) {
        this.positionPhase = positionPhase;
    }

    public boolean isPositionPainedOutNormally() {
        return positionPainedOutNormally;
    }

    public void setPositionPainedOutNormally(boolean positionPainedOutNormally) {
        this.positionPainedOutNormally = positionPainedOutNormally;
    }

    public int getNumProfitTakeOrders() {
        return numProfitTakeOrders;
    }

    public void setNumProfitTakeOrders(int numProfitTakeOrders) {
        this.numProfitTakeOrders = numProfitTakeOrders;
    }

    public float getNumProfitTakeShares() {
        return numProfitTakeShares;
    }

    public void setNumProfitTakeShares(float numProfitTakeShares) {
        this.numProfitTakeShares = numProfitTakeShares;
    }

    public int getTotalNumMin60Disqualified() {
        return totalNumMin60Disqualified;
    }

    public void setTotalNumMin60Disqualified(int totalNumMin60Disqualified) {
        this.totalNumMin60Disqualified = totalNumMin60Disqualified;
    }

    public int getTotalNumMin15Disqualified() {
        return totalNumMin15Disqualified;
    }

    public void setTotalNumMin15Disqualified(int totalNumMin15Disqualified) {
        this.totalNumMin15Disqualified = totalNumMin15Disqualified;
    }

    public int getTotalNumMin05Disqualified() {
        return totalNumMin05Disqualified;
    }

    public void setTotalNumMin05Disqualified(int totalNumMin05Disqualified) {
        this.totalNumMin05Disqualified = totalNumMin05Disqualified;
    }

    public boolean isPositionDropcat() {
        return positionDropcat;
    }

    public void setPositionDropcat(boolean positionDropcat) {
        this.positionDropcat = positionDropcat;
    }

    public boolean isPositionDropcatDoubledDown() {
        return positionDropcatDoubledDown;
    }

    public void setPositionDropcatDoubledDown(boolean positionDropcatDoubledDown) {
        this.positionDropcatDoubledDown = positionDropcatDoubledDown;
    }

    public boolean isPositionDropcatSoldEndofday() {
        return positionDropcatSoldEndofday;
    }

    public void setPositionDropcatSoldEndofday(boolean positionDropcatSoldEndofday) {
        this.positionDropcatSoldEndofday = positionDropcatSoldEndofday;
    }

    public boolean isPositionDropcatPainOut() {
        return positionDropcatPainOut;
    }

    public void setPositionDropcatPainOut(boolean positionDropcatPainOut) {
        this.positionDropcatPainOut = positionDropcatPainOut;
    }

    public boolean isPositionDropcatSuccess() {
        return positionDropcatSuccess;
    }

    public void setPositionDropcatSuccess(boolean positionDropcatSuccess) {
        this.positionDropcatSuccess = positionDropcatSuccess;
    }

    public float getDropcatPainPrice() {
        return dropcatPainPrice;
    }

    public void setDropcatPainPrice(float dropcatPainPrice) {
        this.dropcatPainPrice = dropcatPainPrice;
    }

    public String getUserNotes() {
        return userNotes;
    }

    public void setUserNotes(String userNotes) {
        this.userNotes = userNotes;
    }
}
