package com.greenmark.common.dto.broker;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsBroker;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTUtils;
import com.greenmark.utils.UTXmlUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: OrderDto</p>
 * <p>Description: This DTO is for the results database orders table.  A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 * DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class OrderDto implements Serializable {
    public static final String CLASSNAME = "OrderDto";
    private static final long serialVersionUID = 1L;

    protected long id;
    protected int active = GmConstants.OBJECT_ACTIVE;
    protected long positionId = 0;

    protected int status = GmConstantsBroker.STATUS_OPEN;
    protected int orderReason = GmConstantsBroker.ORDER_REASON_DEFAULT; // THIS IS MOSTLY FOR SELL ORDERS, WERE THEY PAINED OUT? USER REQUEST?

    protected int buyOrSellOrder = GmConstantsBroker.TYPE_BUY;
    protected int longOrShortOrder = GmConstants.LONG_SECURITY;
    protected int stopOrMarketOrder = GmConstantsBroker.TYPE_STOP;

    protected float entryPrice = 0;
    protected LocalDateTime entryPriceDatetime;
    protected int isMomentumUp;

    protected float transactionFee = 0;
    protected float marginFee = 0;
    protected float targetSizeInShares = 0;
    protected float targetAmountInDollars = 0;
    protected LocalDateTime orderPlacedDatetime; // This is the datetime when we first placed order and borrowed money from broker margin.

    protected String externalTrackingNum = "";
    protected String externalOrderId = "";

    protected float executedPrice = 0;
    protected float executedNumShares = 0;
    protected LocalDateTime executedDatetime; // This is the datetime of the last execution that was executed.

    public OrderDto() {
    }

    // ------------------------------------------------ XML IN/OUT ---------------------------------------------------
    public OrderDto(String xmldata) {
        this();

        try {
            this.id = UTXmlUtils.getXmlDataAsLong(xmldata, "ID");
            this.active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");
            this.positionId = UTXmlUtils.getXmlDataAsLong(xmldata, "POSITION_ID");

            this.status = UTXmlUtils.getXmlDataAsInt(xmldata, "STATUS");
            this.orderReason = UTXmlUtils.getXmlDataAsInt(xmldata, "ORDER_REASON");

            this.buyOrSellOrder = UTXmlUtils.getXmlDataAsInt(xmldata, "BUY_OR_SELL_ORDER");
            this.longOrShortOrder = UTXmlUtils.getXmlDataAsInt(xmldata, "LONG_OR_SHORT_ORDER");
            this.stopOrMarketOrder = UTXmlUtils.getXmlDataAsInt(xmldata, "STOP_OR_MARKET_ORDER");

            this.entryPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "ENTRY_PRICE");

            String entryPriceDatetimeString = UTXmlUtils.getXmlData(xmldata, "ENTRY_PRICE_DATETIME");
            if (UTUtils.isNotNorE(entryPriceDatetimeString))
                this.entryPriceDatetime = UTDatetime.fromDbString(entryPriceDatetimeString);
            else
                this.entryPriceDatetime = UTDatetime.getNowLDT();
            this.isMomentumUp = UTXmlUtils.getXmlDataAsInt(xmldata, "IS_MOMENTUM_UP");

            this.transactionFee = UTXmlUtils.getXmlDataAsFloat(xmldata, "TRANSACTION_FEE");
            this.marginFee = UTXmlUtils.getXmlDataAsFloat(xmldata, "MARGIN_FEE");
            this.targetSizeInShares = UTXmlUtils.getXmlDataAsFloat(xmldata, "TARGET_SIZE_IN_SHARES");
            this.targetAmountInDollars = UTXmlUtils.getXmlDataAsFloat(xmldata, "TARGET_AMOUNT_IN_DOLLARS");

            String datetimeString = UTXmlUtils.getXmlData(xmldata, "ORDER_PLACED_DATETIME");
            if (UTUtils.isNotNorE(datetimeString))
                this.orderPlacedDatetime = UTDatetime.fromDbString(datetimeString);
            else
                this.orderPlacedDatetime = UTDatetime.getNowLDT();

            this.externalTrackingNum = UTXmlUtils.getXmlData(xmldata, "EXTERNAL_TRACKING_NUM");
            this.externalOrderId = UTXmlUtils.getXmlData(xmldata, "EXTERNAL_ORDER_ID");
            if (UTUtils.isNorE(externalOrderId)) {
                this.externalOrderId = GmConstantsBroker.ORDER_ID_UNKNOWN;
            }

            this.executedPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "EXECUTED_PRICE");
            this.executedNumShares = UTXmlUtils.getXmlDataAsFloat(xmldata, "EXECUTED_NUM_SHARES");

            String executedDatetimeString = UTXmlUtils.getXmlData(xmldata, "EXECUTED_DATETIME");
            if (UTUtils.isNotNorE(executedDatetimeString))
                this.executedDatetime = UTDatetime.fromDbString(executedDatetimeString);
            // else
            // this.executedDatetime = UTDatetime.getNowLDT();
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + ".Constructor; message [" + e.getMessage() + "]");
        }
    }

    public String toXmlWrapper(String prefix, String endline) {
        String stb = prefix + "<ORDER>" + endline +
                prefix + toXml(prefix, endline) + endline +
                prefix + "</ORDER>" + endline;
        return stb;
    }

    public String toXml(String prefix, String endline) {
        StringBuffer stb = new StringBuffer();

        stb.append(prefix + UTDisplayFormatter.TAB + "<ID>" + this.id + "</ID>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<ACTIVE>" + this.getActive() + "</ACTIVE>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<POSITION_ID>" + this.positionId + "</POSITION_ID>" + endline);

        stb.append(prefix + UTDisplayFormatter.TAB + "<STATUS>" + this.status + "</STATUS>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<ORDER_REASON>" + this.orderReason + "</ORDER_REASON>" + endline);

        stb.append(prefix + UTDisplayFormatter.TAB + "<BUY_OR_SELL_ORDER>" + this.buyOrSellOrder + "</BUY_OR_SELL_ORDER>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<LONG_OR_SHORT_ORDER>" + this.longOrShortOrder + "</LONG_OR_SHORT_ORDER>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<STOP_OR_MARKET_ORDER>" + this.stopOrMarketOrder + "</STOP_OR_MARKET_ORDER>" + endline);

        stb.append(prefix + UTDisplayFormatter.TAB + "<ENTRY_PRICE>" + this.entryPrice + "</ENTRY_PRICE>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<ENTRY_PRICE_DATETIME>" + UTDatetime.toDbString(this.entryPriceDatetime) + "</ENTRY_PRICE_DATETIME>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<IS_MOMENTUM_UP>" + this.isMomentumUp + "</IS_MOMENTUM_UP>" + endline);

        stb.append(prefix + UTDisplayFormatter.TAB + "<TRANSACTION_FEE>" + this.transactionFee + "</TRANSACTION_FEE>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<MARGIN_FEE>" + this.marginFee + "</MARGIN_FEE>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<TARGET_SIZE_IN_SHARES>" + this.targetSizeInShares + "</TARGET_SIZE_IN_SHARES>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<TARGET_AMOUNT_IN_DOLLARS>" + this.targetAmountInDollars + "</TARGET_AMOUNT_IN_DOLLARS>" + endline);

        String formattedDate = "";
        if (this.orderPlacedDatetime != null) {
            formattedDate = UTDatetime.toDbString(this.orderPlacedDatetime);
        }
        stb.append(prefix + UTDisplayFormatter.TAB + "<ORDER_PLACED_DATETIME>" + formattedDate + "</ORDER_PLACED_DATETIME>" + endline);

        stb.append(prefix + UTDisplayFormatter.TAB + "<EXTERNAL_TRACKING_NUM>" + this.externalTrackingNum + "</EXTERNAL_TRACKING_NUM>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<EXTERNAL_ORDER_ID>" + this.externalOrderId + "</EXTERNAL_ORDER_ID>" + endline);

        stb.append(prefix + UTDisplayFormatter.TAB + "<EXECUTED_PRICE>" + this.executedPrice + "</EXECUTED_PRICE>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<EXECUTED_NUM_SHARES>" + this.executedNumShares + "</EXECUTED_NUM_SHARES>" + endline);

        String formattedExecutedDate = "";
        if (this.executedDatetime != null) {
            formattedExecutedDate = UTDatetime.toDbString(this.executedDatetime);
        }
        stb.append(prefix + UTDisplayFormatter.TAB + "<EXECUTED_DATETIME>" + formattedExecutedDate + "</EXECUTED_DATETIME>" + endline);
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

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrderReason() {
        return orderReason;
    }

    public void setOrderReason(int orderReason) {
        this.orderReason = orderReason;
    }

    public int getBuyOrSellOrder() {
        return buyOrSellOrder;
    }

    public void setBuyOrSellOrder(int buyOrSellOrder) {
        this.buyOrSellOrder = buyOrSellOrder;
    }

    public int getLongOrShortOrder() {
        return longOrShortOrder;
    }

    public void setLongOrShortOrder(int longOrShortOrder) {
        this.longOrShortOrder = longOrShortOrder;
    }

    public int getStopOrMarketOrder() {
        return stopOrMarketOrder;
    }

    public void setStopOrMarketOrder(int stopOrMarketOrder) {
        this.stopOrMarketOrder = stopOrMarketOrder;
    }

    public float getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(float entryPrice) {
        this.entryPrice = entryPrice;
    }

    public LocalDateTime getEntryPriceDatetime() {
        return entryPriceDatetime;
    }

    public void setEntryPriceDatetime(LocalDateTime entryPriceDatetime) {
        this.entryPriceDatetime = entryPriceDatetime;
    }

    public int getIsMomentumUp() {
        return isMomentumUp;
    }

    public void setIsMomentumUp(int isMomentumUp) {
        this.isMomentumUp = isMomentumUp;
    }

    public float getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(float transactionFee) {
        this.transactionFee = transactionFee;
    }

    public float getMarginFee() {
        return marginFee;
    }

    public void setMarginFee(float marginFee) {
        this.marginFee = marginFee;
    }

    public float getTargetSizeInShares() {
        return targetSizeInShares;
    }

    public void setTargetSizeInShares(float targetSizeInShares) {
        this.targetSizeInShares = targetSizeInShares;
    }

    public float getTargetAmountInDollars() {
        return targetAmountInDollars;
    }

    public void setTargetAmountInDollars(float targetAmountInDollars) {
        this.targetAmountInDollars = targetAmountInDollars;
    }

    public LocalDateTime getOrderPlacedDatetime() {
        return orderPlacedDatetime;
    }

    public void setOrderPlacedDatetime(LocalDateTime orderPlacedDatetime) {
        this.orderPlacedDatetime = orderPlacedDatetime;
    }

    public String getExternalTrackingNum() {
        return externalTrackingNum;
    }

    public void setExternalTrackingNum(String externalTrackingNum) {
        this.externalTrackingNum = externalTrackingNum;
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(String externalOrderId) {
        this.externalOrderId = externalOrderId;
    }

    public float getExecutedPrice() {
        return executedPrice;
    }

    public void setExecutedPrice(float executedPrice) {
        this.executedPrice = executedPrice;
    }

    public float getExecutedNumShares() {
        return executedNumShares;
    }

    public void setExecutedNumShares(float executedNumShares) {
        this.executedNumShares = executedNumShares;
    }

    public LocalDateTime getExecutedDatetime() {
        return executedDatetime;
    }

    public void setExecutedDatetime(LocalDateTime executedDatetime) {
        this.executedDatetime = executedDatetime;
    }
}
