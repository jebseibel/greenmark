package com.greenmark.common.dto.broker;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import org.apache.commons.beanutils.BeanUtils;

import com.greenmark.common.GmConstants;
import com.greenmark.common.dto.broker.decorator.OrderDtoDecorator;
import com.greenmark.common.dto.broker.decorator.PositionDtoDecorator;
import com.greenmark.common.dto.parameters.LagPeriodsPrice;
import com.greenmark.common.dto.security.SecurityDto;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTDbUtils;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTUtils;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: PositionEventDto</p>
 * <p>Description: This DTO is for the results database position_events table.  A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 * DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class PositionEventDto implements Serializable {
	public static final String CLASSNAME = "PositionEventDto";
	private static final long serialVersionUID = 1L;

	protected long id = 0;
	protected int active = GmConstants.OBJECT_ACTIVE;
	protected long positionId;

	// These get filled in by constructor inputs.
	protected int type;
	protected String description = "";

	protected int toTimeframe = 0;
	protected int fromTimeframe = 0;
	protected LocalDateTime eventDatetime;

	// These are copied from the input Stock object
	protected long securityId;
	protected String symbol;
	protected String displaySymbol;
	protected String exchange; // Also known as the market
	protected String compactedExchangeSymbol; // This is a unique identifier used by maps as it will contain the exchanges first 2 letter appended by the compactedSymbol

	protected int longOrShort;
	protected int stockType;

	protected int buyOrSell;
	protected int stopOrMarket;

	protected int numPeriodsInBucket = 0;

	protected boolean min1QuerySuccess = true;

	protected float min1high = 0F;
	protected float min1low = 0F;
	protected float min1open = 0F;
	protected float min1close = 0F;

	protected int harvestStrategyType = 0; // TODO
	protected int harvestOriginTimeframe; // TODO

	protected long orderId;

	protected float orderTransactionFee = 0F;
	protected float orderMarginFee = 0F;

	protected LocalDateTime orderPlacedDatetime;
	protected LocalDateTime orderExecutedDatetime;

	protected float orderEntryNumShares = 0F;
	protected float orderEntryPrice = 0F;
	protected LocalDateTime orderEntryPriceDatetime;
	protected int orderEntryCalcPriceHigher;

	protected float orderExecutedNumShares = 0F;
	protected float orderExecutedPrice = 0F;

	protected float positionStoplossPrice = 0F;

	public PositionEventDto() {
	}

	public PositionEventDto(PositionEventDto inEvent) {
		try {
			if (inEvent != null)
				BeanUtils.copyProperties(this, inEvent);
		} catch (InvocationTargetException ite) {
			System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage());
		} catch (IllegalAccessException iae) {
			System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
		}
	}

	// This is for Buy Orders
	public PositionEventDto(OrderDtoDecorator currentOrder) {
		if (currentOrder == null) {
			this.orderId = 0;
		} else {
			this.orderId = currentOrder.getId();
			this.buyOrSell = currentOrder.getBuyOrSellOrder();
			this.stopOrMarket = currentOrder.getStopOrMarketOrder();
			this.orderTransactionFee = currentOrder.getTransactionFee();
			this.orderMarginFee = currentOrder.getMarginFee();

			this.orderPlacedDatetime = currentOrder.getOrderPlacedDatetime();
			this.orderExecutedDatetime = currentOrder.getExecutedDatetime();

			this.orderEntryNumShares = currentOrder.getTargetSizeInShares();
			this.orderEntryPrice = currentOrder.getEntryPrice();

			this.orderExecutedNumShares = currentOrder.getExecutedNumShares();
			this.orderExecutedPrice = currentOrder.getExecutedPrice();
			this.orderEntryPriceDatetime = currentOrder.getEntryPriceDatetime();
		}
	}

	public PositionEventDto(PositionDtoDecorator currentPosition) {
		this();

		if (currentPosition == null)
			this.positionId = 0;
		else {
			this.positionId = currentPosition.getId();
			this.positionStoplossPrice = currentPosition.getPainPrice();
		}
	}

	public PositionEventDto(OrderDtoDecorator currentOrder, PositionDtoDecorator currentPosition) {
		this(currentOrder);

		if (currentPosition == null)
			this.positionId = 0;
		else {
			this.positionId = currentPosition.getId();
			this.positionStoplossPrice = currentPosition.getPainPrice();
		}
	}

	public void setValues(int eventType, int fromTimeframe, int toTimeframe, SecurityDto thisStock, LocalDateTime eventDatetime, String description, LagPeriodsPrice orderEntryPrice) {
		if (thisStock == null) {
			System.out.println(CLASSNAME + ". constructor.  thisStock is NULL: ");
		}

		this.type = eventType;
		this.eventDatetime = eventDatetime;
		this.description = description;

		this.toTimeframe = toTimeframe;
		this.fromTimeframe = fromTimeframe;

		this.securityId = thisStock.getSecurityId();
		this.symbol = thisStock.getDbQuerySymbol();
		this.displaySymbol = thisStock.getDisplaySymbol();
		this.exchange = thisStock.getMarket();
		this.compactedExchangeSymbol = thisStock.getCompactedExchangeSymbol();

		this.longOrShort = thisStock.getLongOrShort();
		this.stockType = thisStock.getStockType();

		this.buyOrSell = thisStock.getBuyOrSell();
		this.numPeriodsInBucket = thisStock.getNumPeriodsInBucket();

		if (orderEntryPrice != null) {
			this.orderEntryPrice = orderEntryPrice.getEntryPrice();
			this.orderEntryPriceDatetime = orderEntryPrice.getEntryPriceDatetime();
			this.orderEntryCalcPriceHigher = UTDbUtils.getBooleanInt(orderEntryPrice.isCalcPriceHigher());
		}
	}

	// ------------------------------------------------ XML RESTORE/SAVE ---------------------------------------------------
	public PositionEventDto(String xmldata) {
		try {
			id = UTXmlUtils.getXmlDataAsLong(xmldata, "ID");
			active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");
			positionId = UTXmlUtils.getXmlDataAsLong(xmldata, "POSITION_ID");

			type = UTXmlUtils.getXmlDataAsInt(xmldata, "EVENT_TYPE");
			description = UTXmlUtils.getXmlData(xmldata, "DESCRIPTION");

			toTimeframe = UTXmlUtils.getXmlDataAsInt(xmldata, "TO_TIMEFRAME");
			fromTimeframe = UTXmlUtils.getXmlDataAsInt(xmldata, "FROM_TIMEFRAME");
			eventDatetime = UTDatetime.fromDbString(UTXmlUtils.getXmlData(xmldata, "EVENT_DATETIME"));

			securityId = UTXmlUtils.getXmlDataAsLong(xmldata, "SECURITY_ID");
			symbol = UTXmlUtils.getXmlData(xmldata, "SYMBOL");
			displaySymbol = UTXmlUtils.getXmlData(xmldata, "DISPLAY_SYMBOL");
			exchange = UTXmlUtils.getXmlData(xmldata, "EXCHANGE");
			compactedExchangeSymbol = UTXmlUtils.getXmlData(xmldata, "COMPACTED_EXCHANGE_SYMBOL");

			longOrShort = UTXmlUtils.getXmlDataAsInt(xmldata, "LONG_OR_SHORT");
			stockType = UTXmlUtils.getXmlDataAsInt(xmldata, "STOCK_TYPE");

			buyOrSell = UTXmlUtils.getXmlDataAsInt(xmldata, "BUY_OR_SELL");
			numPeriodsInBucket = UTXmlUtils.getXmlDataAsInt(xmldata, "NUM_PERIODS_IN_BUCKET");

			min1QuerySuccess = UTXmlUtils.getXmlDataAsBoolean(xmldata, "MIN1_QUERY_SUCCESS");

			min1high = UTXmlUtils.getXmlDataAsFloat(xmldata, "MIN1_HIGH");
			min1low = UTXmlUtils.getXmlDataAsFloat(xmldata, "MIN1_LOW");
			min1open = UTXmlUtils.getXmlDataAsFloat(xmldata, "MIN1_OPEN");
			min1close = UTXmlUtils.getXmlDataAsFloat(xmldata, "MIN1_CLOSE");

			harvestStrategyType = UTXmlUtils.getXmlDataAsInt(xmldata, "HARVEST_STRATEGY_TYPE");
			harvestOriginTimeframe = UTXmlUtils.getXmlDataAsInt(xmldata, "HARVEST_ORIGIN_TIMEFRAME");

			orderId = UTXmlUtils.getXmlDataAsLong(xmldata, "ORDER_ID");
			stopOrMarket = UTXmlUtils.getXmlDataAsInt(xmldata, "STOP_OR_MARKET_ORDER");
			orderTransactionFee = UTXmlUtils.getXmlDataAsFloat(xmldata, "ORDER_TRANSACTION_FEE");
			orderMarginFee = UTXmlUtils.getXmlDataAsFloat(xmldata, "ORDER_MARGIN_FEE");

			String orderPlacedXml = UTXmlUtils.getXmlData(xmldata, "ORDER_PLACED_DATETIME");
			if (UTUtils.isNotNorE(orderPlacedXml) && !"null".equals(orderPlacedXml))
				orderPlacedDatetime = UTDatetime.fromDbString(orderPlacedXml);

			String orderExecutedXml = UTXmlUtils.getXmlData(xmldata, "ORDER_EXECUTED_DATETIME");
			if (UTUtils.isNotNorE(orderExecutedXml) && !"null".equals(orderExecutedXml))
				orderExecutedDatetime = UTDatetime.fromDbString(orderExecutedXml);

			orderEntryNumShares = UTXmlUtils.getXmlDataAsFloat(xmldata, "ORDER_ENTRY_NUM_SHARES");
			orderEntryPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "ORDER_ENTRY_PRICE");
			String orderEntryPriceXml = UTXmlUtils.getXmlData(xmldata, "ORDER_ENTRY_PRICE_DATETIME");
			if (UTUtils.isNotNorE(orderEntryPriceXml) && !"null".equals(orderEntryPriceXml))
				orderEntryPriceDatetime = UTDatetime.fromDbString(orderEntryPriceXml);
			orderEntryCalcPriceHigher = UTXmlUtils.getXmlDataAsInt(xmldata, "ORDER_ENTRY_CALC_PRICE_HIGHER");

			orderExecutedNumShares = UTXmlUtils.getXmlDataAsFloat(xmldata, "ORDER_EXECUTED_NUM_SHARES");
			orderExecutedPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "ORDER_EXECUTED_PRICE");

			positionStoplossPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "POSITION_STOPLOSS_PRICE");
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + ".Constructor xml; message [" + e.getMessage() + "]");
		}
	}

	public String toXmlWrapper(String prefix, String endline) {
        String stb = prefix + "<POSITION_EVENT>" + endline +
                prefix + toXml(prefix, endline) + endline +
                prefix + "</POSITION_EVENT>" + endline;
		return stb;
	}

	protected String toXml(String prefix, String endline) {

        String stb = prefix + UTDisplayFormatter.TAB + "<ID>" + this.id + "</ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ACTIVE>" + this.getActive() + "</ACTIVE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<POSITION_ID>" + this.positionId + "</POSITION_ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<EVENT_TYPE>" + this.type + "</EVENT_TYPE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<DESCRIPTION>" + this.description + "</DESCRIPTION>" + endline +
                prefix + UTDisplayFormatter.TAB + "<TO_TIMEFRAME>" + this.toTimeframe + "</TO_TIMEFRAME>" + endline +
                prefix + UTDisplayFormatter.TAB + "<FROM_TIMEFRAME>" + this.fromTimeframe + "</FROM_TIMEFRAME>" + endline +
                prefix + UTDisplayFormatter.TAB + "<EVENT_DATETIME>" + UTDatetime.toDbString(this.eventDatetime) + "</EVENT_DATETIME>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SECURITY_ID>" + this.securityId + "</SECURITY_ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SYMBOL>" + this.symbol + "</SYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<DISPLAY_SYMBOL>" + this.displaySymbol + "</DISPLAY_SYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<EXCHANGE>" + this.exchange + "</EXCHANGE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<COMPACTED_EXCHANGE_SYMBOL>" + this.compactedExchangeSymbol + "</COMPACTED_EXCHANGE_SYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_OR_SHORT>" + this.longOrShort + "</LONG_OR_SHORT>" + endline +
                prefix + UTDisplayFormatter.TAB + "<STOCK_TYPE>" + this.stockType + "</STOCK_TYPE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<BUY_OR_SELL>" + this.buyOrSell + "</BUY_OR_SELL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<NUM_PERIODS_IN_BUCKET>" + this.numPeriodsInBucket + "</NUM_PERIODS_IN_BUCKET>" + endline +
                prefix + UTDisplayFormatter.TAB + "<MIN1_QUERY_SUCCESS>" + min1QuerySuccess + "</MIN1_QUERY_SUCCESS>" + endline +
                prefix + UTDisplayFormatter.TAB + "<MIN1_HIGH>" + this.min1high + "</MIN1_HIGH>" + endline +
                prefix + UTDisplayFormatter.TAB + "<MIN1_LOW>" + this.min1low + "</MIN1_LOW>" + endline +
                prefix + UTDisplayFormatter.TAB + "<MIN1_OPEN>" + this.min1open + "</MIN1_OPEN>" + endline +
                prefix + UTDisplayFormatter.TAB + "<MIN1_CLOSE>" + this.min1close + "</MIN1_CLOSE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ORDER_ID>" + this.orderId + "</ORDER_ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<STOP_OR_MARKET_ORDER>" + this.stopOrMarket + "</STOP_OR_MARKET_ORDER>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ORDER_TRANSACTION_FEE>" + this.orderTransactionFee + "</ORDER_TRANSACTION_FEE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ORDER_MARGIN_FEE>" + this.orderMarginFee + "</ORDER_MARGIN_FEE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ORDER_PLACED_DATETIME>" + UTDatetime.toDbString(this.orderPlacedDatetime) + "</ORDER_PLACED_DATETIME>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ORDER_EXECUTED_DATETIME>" + UTDatetime.toDbString(this.orderExecutedDatetime) + "</ORDER_EXECUTED_DATETIME>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ORDER_ENTRY_NUM_SHARES>" + this.orderEntryNumShares + "</ORDER_ENTRY_NUM_SHARES>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ORDER_ENTRY_PRICE>" + this.orderEntryPrice + "</ORDER_ENTRY_PRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ORDER_ENTRY_PRICE_DATETIME>" + UTDatetime.toDbString(this.orderEntryPriceDatetime) + "</ORDER_ENTRY_PRICE_DATETIME>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ORDER_ENTRY_CALC_PRICE_HIGHER>" + this.orderEntryCalcPriceHigher + "</ORDER_ENTRY_CALC_PRICE_HIGHER>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ORDER_EXECUTED_NUM_SHARES>" + this.orderExecutedNumShares + "</ORDER_EXECUTED_NUM_SHARES>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ORDER_EXECUTED_PRICE>" + this.orderExecutedPrice + "</ORDER_EXECUTED_PRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<POSITION_STOPLOSS_PRICE>" + this.positionStoplossPrice + "</POSITION_STOPLOSS_PRICE>" + endline;
		return stb;
	}

	// ------------------------------------------------ REQUIRED METHODS ---------------------------------------------------
	public final String getKey() {
		if (eventDatetime != null)
			return type + ":" + toTimeframe + ":" + UTDatetime.toString(eventDatetime);
		else
			return type + ":" + toTimeframe + ":NULL_DATETIME";
	}

	public final boolean equals(Object _other) {
		if (_other == null)
			return false;

		if (_other == this)
			return true;

		if (!(_other instanceof PositionEventDto _cast))
			return false;

        if (type != _cast.type)
			return false;

		if (toTimeframe != _cast.toTimeframe)
			return false;

        return eventDatetime == null;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getToTimeframe() {
		return toTimeframe;
	}

	public void setToTimeframe(int toTimeframe) {
		this.toTimeframe = toTimeframe;
	}

	public int getFromTimeframe() {
		return fromTimeframe;
	}

	public void setFromTimeframe(int fromTimeframe) {
		this.fromTimeframe = fromTimeframe;
	}

	public LocalDateTime getEventDatetime() {
		return eventDatetime;
	}

	public void setEventDatetime(LocalDateTime eventDatetime) {
		this.eventDatetime = eventDatetime;
	}

	public long getSecurityId() {
		return securityId;
	}

	public void setSecurityId(long securityId) {
		this.securityId = securityId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDisplaySymbol() {
		return displaySymbol;
	}

	public void setDisplaySymbol(String displaySymbol) {
		this.displaySymbol = displaySymbol;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getCompactedExchangeSymbol() {
		return compactedExchangeSymbol;
	}

	public void setCompactedExchangeSymbol(String compactedExchangeSymbol) {
		this.compactedExchangeSymbol = compactedExchangeSymbol;
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

	public int getBuyOrSell() {
		return buyOrSell;
	}

	public void setBuyOrSell(int buyOrSell) {
		this.buyOrSell = buyOrSell;
	}

	public int getNumPeriodsInBucket() {
		return numPeriodsInBucket;
	}

	public void setNumPeriodsInBucket(int numPeriodsInBucket) {
		this.numPeriodsInBucket = numPeriodsInBucket;
	}

	public boolean isMin1QuerySuccess() {
		return min1QuerySuccess;
	}

	public void setMin1QuerySuccess(boolean min1QuerySuccess) {
		this.min1QuerySuccess = min1QuerySuccess;
	}

	public float getMin1high() {
		return min1high;
	}

	public void setMin1high(float min1high) {
		this.min1high = min1high;
	}

	public float getMin1low() {
		return min1low;
	}

	public void setMin1low(float min1low) {
		this.min1low = min1low;
	}

	public float getMin1open() {
		return min1open;
	}

	public void setMin1open(float min1open) {
		this.min1open = min1open;
	}

	public float getMin1close() {
		return min1close;
	}

	public void setMin1close(float min1close) {
		this.min1close = min1close;
	}

	public int getHarvestStrategyType() {
		return harvestStrategyType;
	}

	public void setHarvestStrategyType(int harvestStrategyType) {
		this.harvestStrategyType = harvestStrategyType;
	}

	public int getHarvestOriginTimeframe() {
		return harvestOriginTimeframe;
	}

	public void setHarvestOriginTimeframe(int harvestOriginTimeframe) {
		this.harvestOriginTimeframe = harvestOriginTimeframe;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getStopOrMarket() {
		return stopOrMarket;
	}

	public void setStopOrMarket(int stopOrMarket) {
		this.stopOrMarket = stopOrMarket;
	}

	public float getOrderTransactionFee() {
		return orderTransactionFee;
	}

	public void setOrderTransactionFee(float orderTransactionFee) {
		this.orderTransactionFee = orderTransactionFee;
	}

	public float getOrderMarginFee() {
		return orderMarginFee;
	}

	public void setOrderMarginFee(float orderMarginFee) {
		this.orderMarginFee = orderMarginFee;
	}

	public LocalDateTime getOrderPlacedDatetime() {
		return orderPlacedDatetime;
	}

	public void setOrderPlacedDatetime(LocalDateTime orderPlacedDatetime) {
		this.orderPlacedDatetime = orderPlacedDatetime;
	}

	public LocalDateTime getOrderExecutedDatetime() {
		return orderExecutedDatetime;
	}

	public void setOrderExecutedDatetime(LocalDateTime orderExecutedDatetime) {
		this.orderExecutedDatetime = orderExecutedDatetime;
	}

	public float getOrderEntryNumShares() {
		return orderEntryNumShares;
	}

	public void setOrderEntryNumShares(float orderEntryNumShares) {
		this.orderEntryNumShares = orderEntryNumShares;
	}

	public float getOrderEntryPrice() {
		return orderEntryPrice;
	}

	public void setOrderEntryPrice(float orderEntryPrice) {
		this.orderEntryPrice = orderEntryPrice;
	}

	public LocalDateTime getOrderEntryPriceDatetime() {
		return orderEntryPriceDatetime;
	}

	public void setOrderEntryPriceDatetime(LocalDateTime entryPriceDatetime) {
		this.orderEntryPriceDatetime = entryPriceDatetime;
	}

	public int getOrderEntryCalcPriceHigher() {
		return orderEntryCalcPriceHigher;
	}

	public void setOrderEntryCalcPriceHigher(int orderEntryCalcPriceHigher) {
		this.orderEntryCalcPriceHigher = orderEntryCalcPriceHigher;
	}

	public float getOrderExecutedNumShares() {
		return orderExecutedNumShares;
	}

	public void setOrderExecutedNumShares(float orderExecutedNumShares) {
		this.orderExecutedNumShares = orderExecutedNumShares;
	}

	public float getOrderExecutedPrice() {
		return orderExecutedPrice;
	}

	public void setOrderExecutedPrice(float orderExecutedPrice) {
		this.orderExecutedPrice = orderExecutedPrice;
	}

	public float getPositionStoplossPrice() {
		return positionStoplossPrice;
	}

	public void setPositionStoplossPrice(float positionStoplossPrice) {
		this.positionStoplossPrice = positionStoplossPrice;
	}
}
