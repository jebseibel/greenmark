package com.greenmark.common.dto.broker.database;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import com.greenmark.common.GmConstantsBroker;
import com.greenmark.common.dto.broker.database.decorator.OrderDbDecorator;
import com.greenmark.common.dto.broker.decorator.PositionDtoDecorator;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: PositionBroker</p>
 * <p>Description: This class extends its base DTO and adds other DTO object instances.  These are sent to/from the database to store hierarchies of objects.
 *    It is in the Broker abstraction layer since it only contains member variables that a Broker will use.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
@Slf4j
public class PositionBroker extends PositionDtoDecorator implements Serializable {
	public static final String CLASSNAME = "PositionBroker";
	private static final long serialVersionUID = 1L;

	protected String stockSymbol; // This is used to query DB.
	protected String compactedExchangeSymbol; // This is used to find stock on all our hashtables.

	protected float currentPrice = 0F;

	// The key to the map is the orderId
	protected Map<String, OrderDbDecorator> buyOrderList = new LinkedHashMap<>();
	protected int numBuyOrders = 0;
	protected boolean hasBuyOrders;

	// The key to the map is the orderId
	protected Map<String, OrderDbDecorator> sellOrderList = new LinkedHashMap<>();
	protected int numSellOrders = 0;
	protected boolean hasSellOrders;

	public PositionBroker() {
		super();
		buyOrderList = new LinkedHashMap<>();
		sellOrderList = new LinkedHashMap<>();

		// We create a Position after we've bought an order and always start in the Sell status.
		positionPhase = GmConstantsBroker.TYPE_SELL;
	}

	public PositionBroker(int harvestStrategyTypeId, int bucketStrategyStateId, int originBucketTimeframe) {
		super(harvestStrategyTypeId, bucketStrategyStateId, originBucketTimeframe);
	}

	public PositionBroker(PositionBroker oldPosition) {
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
//	public PositionBroker(String xmldata) {
//		super(xmldata, trace);
//
//		buyOrderList = new LinkedHashMap<>();
//		sellOrderList = new LinkedHashMap<>();
//
//		try {
//			stockSymbol = UTXmlUtils.getXmlData(xmldata, "STOCK_SYMBOL");
//			compactedExchangeSymbol = UTXmlUtils.getXmlData(xmldata, "COMPACTED_EXCHANGE_SYMBOL");
//			currentPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "CURRENT_PRICE");
//
//			// build the buy order list
//			String xmlBuyOrderList = UTXmlUtils.getXmlData(xmldata, "BUY_ORDER_LIST");
//			Vector vXmlBuyOrders = UTXmlUtils.getElementsToVector(xmlBuyOrderList, "ORDER");
//
//				log.debug(" . . . vXmlBuyOrder.size() [" + vXmlBuyOrders.size() + "]");
//			for (Enumeration e = vXmlBuyOrders.elements(); e.hasMoreElements();) {
//				String xmlorder = (String) e.nextElement();
//				OrderDbDecorator thisOrder = new OrderDbDecorator(xmlorder);
//				addBuyOrder(thisOrder);
//			}
//
//			// build the sell order list
//			String xmlSellOrderList = UTXmlUtils.getXmlData(xmldata, "SELL_ORDER_LIST");
//			Vector vXmlSellOrders = UTXmlUtils.getElementsToVector(xmlSellOrderList, "ORDER");
//
//				log.debug(" . . . vXmlBuyOrder.size() [" + vXmlSellOrders.size() + "]");
//			for (Enumeration e = vXmlSellOrders.elements(); e.hasMoreElements();) {
//				String xmlorder = (String) e.nextElement();
//				OrderDbDecorator thisOrder = new OrderDbDecorator(xmlorder);
//				addSellOrder(thisOrder, trace);
//			}
//		} catch (Exception e) {
//			log.error("Exception in Current Position xml constructor message: " + e.getMessage());
//		}
//	}

//	public String toXmlWrapper(String prefix, String endline) {
//		StringBuffer stb = new StringBuffer();
//		stb.append(prefix + "<POSITION>" + endline);
//		stb.append(prefix + toXml(prefix, endline, true) + endline);
//		stb.append(prefix + "</POSITION>" + endline);
//		return stb.toString();
//	}
//
//	public String toXml(String prefix, String endline, boolean includeOrders) {
//		StringBuffer stb = new StringBuffer();
//		stb.append(super.toXml(prefix, endline));
//
//		stb.append(prefix + UTDisplayFormatter.TAB + "<STOCK_SYMBOL>" + stockSymbol + "</STOCK_SYMBOL>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<COMPACTED_EXCHANGE_SYMBOL>" + compactedExchangeSymbol + "</COMPACTED_EXCHANGE_SYMBOL>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<CURRENT_PRICE>" + currentPrice + "</CURRENT_PRICE>" + endline);
//
//		if (includeOrders) {
//			stb.append(prefix + toXmlOrders(prefix, endline));
//		}
//
//		return stb.toString();
//	}
//
//	public final String toXmlOrders(String prefix, String endline) {
//		StringBuffer stb = new StringBuffer();
//		stb.append(prefix + "<ORDERS>" + endline);
//
//		// sell order list
//		stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "<SELL_ORDER_LIST>" + endline);
//		for (OrderDbDecorator order : sellOrderList.values()) {
//			stb.append(prefix + order.toXml(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB, endline));
//		}
//		stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "</SELL_ORDER_LIST>" + endline);
//
//		// buy order list
//		stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "<BUY_ORDER_LIST>" + endline);
//		for (OrderDbDecorator order : buyOrderList.values()) {
//			stb.append(prefix + order.toXml(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB, endline));
//		}
//		stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "</BUY_ORDER_LIST>" + endline);
//
//		stb.append(prefix + UTDisplayFormatter.TAB + "</ORDERS>" + endline);
//
//		return stb.toString();
//	}

	public void addBuyOrder(OrderDbDecorator placedBuyOrder) {
		//buyOrderList.put(placedBuyOrder.getExternalOrderId(), placedBuyOrder);
		numBuyOrders++;
		//buyExecutedDatetime = new UTCalendarTime(String.valueOf(placedBuyOrder.getExecutedDatetime()));
	}

	public void addSellOrder(OrderDbDecorator placedSellOrder) {
		//sellOrderList.put(placedSellOrder.getExternalOrderId(), placedSellOrder);
		numSellOrders++;
		//sellExecutedDatetime = new UTCalendarTime(placedSellOrder.getExecutedDatetime());
	}

	// Failsafe method for website
	public boolean isHasBuyOrders() {
		if (buyOrderList == null || buyOrderList.isEmpty()) {
			setHasBuyOrders(false);
			return hasBuyOrders;
		} else {
			setHasBuyOrders(true);
			return hasBuyOrders;
		}
	}

	public boolean isHasSellOrders() {
		if (sellOrderList == null || sellOrderList.isEmpty()) {
			setHasSellOrders(false);
			return hasSellOrders;
		} else {
			setHasSellOrders(true);
			return hasSellOrders;
		}
	}

	public Map<String, OrderDbDecorator> getBuyOrderMap() {
		return buyOrderList;
	}

	public List<OrderDbDecorator> getBuyOrderList() {
		List<OrderDbDecorator> returnList = new ArrayList<>();
		returnList.addAll(buyOrderList.values());
		return returnList;
	}

	public Map<String, OrderDbDecorator> getSellOrderMap() {
		return sellOrderList;
	}

	public List<OrderDbDecorator> getSellOrderList() {
		List<OrderDbDecorator> returnList = new ArrayList<>();
		returnList.addAll(sellOrderList.values());
		return returnList;
	}

	// ------------------------------------------------ SETTER/GETTER METHODS ---------------------------------------------------
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

	public float getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}

	public void setBuyOrderList(Map<String, OrderDbDecorator> buyOrderList) {
		this.buyOrderList = buyOrderList;
	}

	public int getNumBuyOrders() {
		return numBuyOrders;
	}

	public void setNumBuyOrders(int numBuyOrders) {
		this.numBuyOrders = numBuyOrders;
	}

	public void setHasBuyOrders(boolean hasBuyOrders) {
		this.hasBuyOrders = hasBuyOrders;
	}

	public void setSellOrderList(Map<String, OrderDbDecorator> sellOrderList) {
		this.sellOrderList = sellOrderList;
	}

	public int getNumSellOrders() {
		return numSellOrders;
	}

	public void setNumSellOrders(int numSellOrders) {
		this.numSellOrders = numSellOrders;
	}

	public void setHasSellOrders(boolean hasSellOrders) {
		this.hasSellOrders = hasSellOrders;
	}
}
