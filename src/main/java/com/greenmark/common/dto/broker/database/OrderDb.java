package com.greenmark.common.dto.broker.database;

import com.greenmark.common.GmConstantsBroker;
import com.greenmark.common.dto.broker.database.decorator.OrderBrokerDecorator;
import com.greenmark.common.dto.broker.database.decorator.PositionDbDecorator;
import com.greenmark.common.dto.parameters.LagPeriodsPrice;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTXmlUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: OrderDb</p>
 * <p>Description: This class extends its base DTO and adds other DTO object instances.  These are sent to/from the database to store hierarchies of objects.
 *    It is also used by the trading client to add additional information it needs when calculating, but does not need to be stored in the database. </p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class OrderDb extends OrderBrokerDecorator implements Serializable {
    public static final String CLASSNAME = "OrderDb";
    private static final long serialVersionUID = 1L;

    protected String stockSymbol = ""; // This is used to query DB.
    protected String compactedExchangeSymbol = ""; // This is used to find stock on all our hashtables.

    protected float highLowPriceOnOrder = 0; // This is the high (long) or low (short) executedPrice when we calculated the entryPrice
    protected float currentPrice = 0;

    protected float totalSharesRemaining = 0; // Not used, except maybe by IB.

    protected String harvestStrategyAcronym = "";
    protected boolean attemptingReplaceOfOrder = false;

    public OrderDb() {
    }

    public OrderDb(OrderDb inOrder) {
        this();

        try {
            if (inOrder != null)
                BeanUtils.copyProperties(this, inOrder);
        } catch (InvocationTargetException ite) {
            System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage());
        } catch (IllegalAccessException iae) {
            System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
        }
    }

    public OrderDb(String symbol, String compactedExchangeSymbol, int stopOrMktOrder, int longOrShortOrder, String harvestStrategyAcronym, LocalDateTime orderPlacedDatetime,
                   LagPeriodsPrice lagPeriodsPrice) {
        this();

        this.status = GmConstantsBroker.STATUS_PENDING_SUBMIT;
        this.stockSymbol = symbol;
        this.compactedExchangeSymbol = compactedExchangeSymbol;
        this.stopOrMarketOrder = stopOrMktOrder;
        this.longOrShortOrder = longOrShortOrder;
        this.harvestStrategyAcronym = harvestStrategyAcronym;

        this.orderPlacedDatetime = orderPlacedDatetime;

        if (lagPeriodsPrice != null) {
            this.entryPrice = lagPeriodsPrice.getEntryPrice();
            this.entryPriceDatetime = lagPeriodsPrice.getEntryPriceDatetime();

            // If we're calculating high price lag periods (using that period's high), then our momentum is down (Buy Long/Sell Short)
            if (lagPeriodsPrice.isCalcPriceHigher()) {
                this.isMomentumUp = 0;
            } else {
                this.isMomentumUp = 1;
            }
        }
    }

    // Create a sell order from a current position
    public OrderDb(PositionDbDecorator myPosition, float currentPrice, LagPeriodsPrice entryPrice, float numShares, int stopOrMktOrder, int longOrShort) {
        this();
//		this.positionId = myPosition.getId();
//		this.stockSymbol = myPosition.getStockSymbol();
//		this.compactedExchangeSymbol = myPosition.getCompactedExchangeSymbol();

        this.status = GmConstantsBroker.STATUS_PENDING_SUBMIT;
        this.buyOrSellOrder = GmConstantsBroker.TYPE_SELL;
        this.longOrShortOrder = longOrShort;
        this.stopOrMarketOrder = stopOrMktOrder;

        this.currentPrice = currentPrice;
        this.entryPrice = entryPrice.getEntryPrice();
        this.targetSizeInShares = numShares;
        this.targetAmountInDollars = numShares * entryPrice.getEntryPrice();

        this.externalTrackingNum = "";
        this.harvestStrategyAcronym = myPosition.getHarvestStrategyAcronym();

        if (entryPrice != null) {
            this.entryPrice = entryPrice.getEntryPrice();
            this.entryPriceDatetime = entryPrice.getEntryPriceDatetime();

            // If we're calculating high price lag periods (using that period's high), then our momentum is down (Buy Long/Sell Short)
            if (entryPrice.isCalcPriceHigher()) {
                this.isMomentumUp = 0;
            } else {
                this.isMomentumUp = 1;
            }
        }
    }

    public OrderDb(String xmldata) {
        super(xmldata);

        try {
            this.stockSymbol = UTXmlUtils.getXmlData(xmldata, "STOCK_SYMBOL");
            this.compactedExchangeSymbol = UTXmlUtils.getXmlData(xmldata, "COMPACTED_EXCHANGE_SYMBOL");

            this.entryPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "ENTRY_PRICE");
            this.highLowPriceOnOrder = UTXmlUtils.getXmlDataAsFloat(xmldata, "HIGH_LOW_PRICE_ON_ORDER");
            this.currentPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "CURRENT_PRICE");

            this.totalSharesRemaining = UTXmlUtils.getXmlDataAsFloat(xmldata, "TOTAL_SHARES_REMAINING");

            this.harvestStrategyAcronym = UTXmlUtils.getXmlData(xmldata, "HARVEST_STRATEGY");
            this.attemptingReplaceOfOrder = UTXmlUtils.getXmlDataAsBoolean(xmldata, "ATTEMPTING_REPLACE");
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

        String stb = super.toXml(prefix, endline) +
                prefix + UTDisplayFormatter.TAB + "<STOCK_SYMBOL>" + stockSymbol + "</STOCK_SYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<COMPACTED_EXCHANGE_SYMBOL>" + compactedExchangeSymbol + "</COMPACTED_EXCHANGE_SYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ENTRY_PRICE>" + entryPrice + "</ENTRY_PRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<HIGH_LOW_PRICE_ON_ORDER>" + highLowPriceOnOrder + "</HIGH_LOW_PRICE_ON_ORDER>" + endline +
                prefix + UTDisplayFormatter.TAB + "<CURRENT_PRICE>" + currentPrice + "</CURRENT_PRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<TOTAL_SHARES_REMAINING>" + totalSharesRemaining + "</TOTAL_SHARES_REMAINING>" + endline +
                prefix + UTDisplayFormatter.TAB + "<HARVEST_STRATEGY>" + harvestStrategyAcronym + "</HARVEST_STRATEGY>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ATTEMPTING_REPLACE>" + attemptingReplaceOfOrder + "</ATTEMPTING_REPLACE>" + endline;
        return stb;
    }

    public LagPeriodsPrice getOrderLagPeriodsPrice() {
        if (entryPriceDatetime != null) {
            LagPeriodsPrice lagPeriodInfo = new LagPeriodsPrice(this.entryPrice, this.entryPriceDatetime);

            lagPeriodInfo.setCalcPriceHigher(this.isMomentumUp == 0);

            return lagPeriodInfo;
        } else
            return null;

    }

    // ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
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

    public float getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(float entryPrice) {
        this.entryPrice = entryPrice;
    }

    public float getHighLowPriceOnOrder() {
        return highLowPriceOnOrder;
    }

    public void setHighLowPriceOnOrder(float highLowPriceOnOrder) {
        this.highLowPriceOnOrder = highLowPriceOnOrder;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public float getTotalSharesRemaining() {
        return totalSharesRemaining;
    }

    public void setTotalSharesRemaining(float totalSharesRemaining) {
        this.totalSharesRemaining = totalSharesRemaining;
    }

    public LocalDateTime getOrderPlacedDatetime() {
        return orderPlacedDatetime;
    }

    public void setOrderPlacedDatetime(LocalDateTime orderPlacedDatetime) {
        this.orderPlacedDatetime = orderPlacedDatetime;
    }

    public String getHarvestStrategyAcronym() {
        return harvestStrategyAcronym;
    }

    public void setHarvestStrategyAcronym(String harvestStrategyAcronym) {
        this.harvestStrategyAcronym = harvestStrategyAcronym;
    }

    public boolean isAttemptingReplaceOfOrder() {
        return attemptingReplaceOfOrder;
    }

    public void setAttemptingReplaceOfOrder(boolean attemptingReplaceOfOrder) {
        this.attemptingReplaceOfOrder = attemptingReplaceOfOrder;
    }
}
