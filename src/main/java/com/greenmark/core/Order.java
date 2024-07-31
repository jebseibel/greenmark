package com.greenmark.core;

import com.greenmark.common.core.Labels;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import static com.greenmark.core.enums.OrderValues.*;

@Slf4j
@Data
public class Order {

    protected long id;
    protected int active = Labels.OBJECT_ACTIVE;
    protected String stockSymbol;
    protected float entryPrice = 0;
    protected float currentPrice = 0;
    protected int status = STATUS_OPEN;
    protected int buyOrSellOrder = TYPE_ORDER_BUY;
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
    protected float executedPrice = 0;
    protected int executedNumShares = 0;
    protected LocalDateTime executedDatetime;
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

}
