package com.greenmark.core;

import com.greenmark.common.core.Labels;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Order {
    /**
     * This is the class name.
     */
    public static final String CLASSNAME = "Order";

    //  KEEP THIS ORDER, THEY MATCH THE IB ORDER IN:  ib.IBBroker.java
    public static final int STATUS_PENDING_SUBMIT = 0;
    public static final int STATUS_SUBMITTED = 1;
    public static final int STATUS_OPEN = 2;
    public static final int STATUS_PARTIAL = 3;
    public static final int STATUS_EXECUTED = 4;
    public static final int STATUS_CANCELLED = 5;
    public static final int STATUS_CANCELLING = 6;
    public static final int STATUS_REJECTED_BY_BROKER = 7;
    public static final int STATUS_UNKNOWN = 8;

    public static final int TYPE_STOP = 1;
    public static final int TYPE_MARKET = 2;
    public static final int TYPE_LIMIT = 3;

    //  Used by the OrderDecorator object to know which type of buy or sell order it is.
    public static final int TYPE_ORDER_BUY = 1;
    public static final int TYPE_ORDER_SELL = 2;

    public static final int ADDED_FROM_BUCKETS = 1;   // This is used for ALL Buy orders.
    public static final int EXCEEDED_PAIN_THRESHOLD = 2;
    public static final int USER_SELL_MARKET_ORDER = 3;
    public static final int DROPCAT_EXCEEDED_ADDTL_PAIN_THRESHOLD = 4;
    public static final int DROPCAT_END_OF_DAY = 5;
    public static final int DROPCAT_DOUBLE_DOWN_BUY = 6;
    public static final int ORDER_REASON_DEFAULT = 1;

    public static final String ORDER_ID_UNKNOWN = "0";

    /**
     * Used by the database
     **/
    protected long id;          //leave as null for hibernate
    protected int active = Labels.OBJECT_ACTIVE;
    /**************************/

    protected String stockSymbol;
    protected float entryPrice = 0;
    protected float currentPrice = 0;
    protected int status = STATUS_OPEN;
    protected int buyOrSellOrder = TYPE_ORDER_BUY;
    //protected int longOrShortOrder = Stock.LONG_STOCK;
    protected int longOrShortOrder = 1;
    protected int stopOrMarketOrder = 1;
    protected int targetSizeInShares = 0;
    protected float targetAmountInDollars = 0;
    protected float transactionFee = 0;
    protected int orderReason = ORDER_REASON_DEFAULT;   // THIS IS MOSTLY FOR SELL ORDERS, WERE THEY PAINED OUT?  USER REQUEST?
    protected String externalOrderId;
    protected String externalTrackingNum;
    protected String replaceOrderId = ORDER_ID_UNKNOWN;  // This is used by Replace Orders when getting stored to XML,
    protected boolean hasExecutions;
    // this is the orderID of the order that is getting replaced by this order.
    protected float executedPrice = 0;
    protected int executedNumShares = 0;
    protected java.util.Date executedDatetime;
    private long positionId = 0;   //set to zero to find insert errors


    public Order() {

    }

    public Order(int stopOrMktOrder) {
        this.status = STATUS_PENDING_SUBMIT;
        this.stopOrMarketOrder = stopOrMktOrder;
    }

    public Order(Order oldOrder) {
        this.positionId = oldOrder.getPositionId();

        this.entryPrice = oldOrder.getEntryPrice();
        this.targetSizeInShares = oldOrder.getTargetSizeInShares();
        this.targetAmountInDollars = oldOrder.getTargetAmountInDollars();
        this.transactionFee = oldOrder.getTransactionFee();
        this.stockSymbol = oldOrder.getStockSymbol();
        this.stopOrMarketOrder = oldOrder.getStopOrMarketOrder();
        this.currentPrice = oldOrder.getCurrentPrice();

        this.externalTrackingNum = oldOrder.getExternalTrackingNum();
        this.externalOrderId = oldOrder.getExternalOrderId();
        this.orderReason = oldOrder.getOrderReason();
        this.executedPrice = oldOrder.getExecutedPrice();
        this.executedNumShares = oldOrder.getExecutedNumShares();
        this.executedDatetime = oldOrder.getExecutedDatetime();
        this.status = oldOrder.getStatus();
        this.replaceOrderId = oldOrder.getReplaceOrderId();

        this.buyOrSellOrder = oldOrder.getBuyOrSellOrder();
        this.longOrShortOrder = oldOrder.getLongOrShortOrder();

    }

    public Order(int orderSize, float entryPrice, float currentPrice, StockStub stockStub, int stopOrMktOrder) {
        //this.positionId = myStock.getStockId();
        this.status = STATUS_PENDING_SUBMIT;
        this.entryPrice = entryPrice;
        this.targetSizeInShares = orderSize;
        this.targetAmountInDollars = orderSize * entryPrice;
        this.stockSymbol = stockStub.getSymbol();
        this.stopOrMarketOrder = stopOrMktOrder;
        this.currentPrice = currentPrice;
        //this.longOrShortOrder = stockStub.status;

    }

    //  Create a sell order from a current position
//   public Order( Position myPosition, float entryPrice, int stopOrMktOrder )
//   {
//      this.positionId = myPosition.getId();
//
//
//      this.entryPrice = entryPrice;
//      this.targetSizeInShares = myPosition.getBuyExecutedNumShares();
//      this.targetAmountInDollars = myPosition.getBuyExecutedNumShares() * entryPrice;
//
//      this.stock = myPosition.getStock();
//      this.stopOrMarketOrder = stopOrMktOrder;
//      this.currentPrice = myPosition.getStock().getCurrentClosePrice();
//		this.externalTrackingNum = "";
////		this.externalOrderId = "?????????";
//		// this.orderReason = ??????????;
//
//		this.status = STATUS_PENDING_SUBMIT;
//
//		this.buyOrSellOrder = Order.TYPE_ORDER_SELL;
//		this.longOrShortOrder = myPosition.getStock().getLongOrShort();
//
//   }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }


    public float getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(float entryPrice) {
        this.entryPrice = entryPrice;
    }

    public int getTargetSizeInShares() {
        return targetSizeInShares;
    }

    public void setTargetSizeInShares(int targetSizeInShares) {
        this.targetSizeInShares = targetSizeInShares;
    }

    public float getTargetAmountInDollars() {
        return targetAmountInDollars;
    }


    public void setTargetAmountInDollars(float targetAmountInDollars) {
        this.targetAmountInDollars = targetAmountInDollars;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getExecutedPrice() {
        return executedPrice;
    }

    public void setExecutedPrice(float executedPrice) {
        this.executedPrice = executedPrice;
    }

    public float getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(float transactionFee) {
        this.transactionFee = transactionFee;
    }

    public int getExecutedNumShares() {
        return executedNumShares;
    }

    public void setExecutedNumShares(int executedNumShares) {
        this.executedNumShares = executedNumShares;
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(String orderID) {
        this.externalOrderId = orderID;
    }

    public java.util.Date getExecutedDatetime() {
        return executedDatetime;
    }

    public void setExecutedDatetime(java.util.Date executedDatetime) {
        this.executedDatetime = executedDatetime;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getStopOrMarketOrder() {
        return stopOrMarketOrder;
    }

    public void setStopOrMarketOrder(int stopOrMarketOrder) {
        this.stopOrMarketOrder = stopOrMarketOrder;
    }

    public String getExternalTrackingNum() {
        return externalTrackingNum;
    }

    public void setExternalTrackingNum(String externalTrackingNum) {
        this.externalTrackingNum = externalTrackingNum;
    }

    public int getOrderReason() {
        return orderReason;
    }

    public void setOrderReason(int orderReason) {
        this.orderReason = orderReason;
    }

    public String getReplaceOrderId() {
        return replaceOrderId;
    }

    public void setReplaceOrderId(String replaceOrderID) {
        this.replaceOrderId = replaceOrderID;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int value) {
        this.active = value;
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

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLongOrShort() {
        //return this.stock.getLongOrShortString();
        return "Long";
    }

}
