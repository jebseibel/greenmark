package com.greenmark.common.dto.broker.database.decorator;

import com.greenmark.common.GmConstantsBroker;
import com.greenmark.common.dto.broker.database.PositionDb;
import com.greenmark.utils.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: PositionDbDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its PositionDb base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class PositionDbDecorator extends PositionDb implements Serializable {
    public static final String CLASSNAME = "PositionDbDecorator";
    private static final long serialVersionUID = 1L;

    private String id;
    private String stockSymbol;

    public PositionDbDecorator() {
        super();
    }

    public PositionDbDecorator(int harvestStrategyTypeId, int bucketStrategyStateId, int originBucketTimeframe) {
        super(harvestStrategyTypeId, bucketStrategyStateId, originBucketTimeframe);
    }

    public PositionDbDecorator(PositionDbDecorator oldPosition) {
        super(oldPosition);
    }

//	public PositionDbDecorator(String xmldata) {
//		super(xmldata, trace);
//	}

    public String getStockSymbolAndId() {
        return stockSymbol + "    :    " + id;
    }

    public String getHarvestStrategyString() {
        return harvestStrategyAcronym;
    }

    public String getOriginBucketTimeframeString() {
//		return UTLabels.getGmanLabelForGmanTimeframe(originBucketTimeframe);
        return UTLabels.getGmanLabelForGmanTimeframe(1);
    }

    public String getBuyExecutedDateString() {
//		if (buyExecutedDatetime != null)
//			return buyExecutedDatetime.formatDateTimeDisplay();
//		else
        return "Unknown";
    }

    public String getBuyExecutedDateOnlyString() {
//		if (buyExecutedDatetime != null)
//			return buyExecutedDatetime.formatDateDisplay();
//		else
        return "Unknown";
    }

    public String getSellExecutedDateOnlyString() {
//		if (sellExecutedDatetime != null)
//			return sellExecutedDatetime.formatDateDisplay();
//		else
        return "Unknown";
    }

    public String getSellExecutedDateString() {
//		if (sellExecutedDatetime != null)
//			return sellExecutedDatetime.formatDateTimeDisplay();
//		else
        return "Unknown";
    }

    /////////////// CONVENIENCE METHODS FOR DISPLAY FORMATTERS (ENTRY NUM SHARES) /////////////////////
    public float calcTargetNumShares(int buyOrSell) throws Exception {
        float totalNumShares = 0f;

        Map iterList;
//		if (buyOrSell == GmConstantsBroker.TYPE_BUY)
//			iterList = buyOrderList;
//		else
//			iterList = sellOrderList;
//
//		if (iterList == null)
//			return 0;
//
//		try {
//
//			for (Iterator I = iterList.values().iterator(); I.hasNext();) {
//				OrderBrokerDecorator thisOrder = (OrderBrokerDecorator) I.next();
//				totalNumShares += thisOrder.getTargetSizeInShares();
//			}
//		} catch (Exception ex) {
//			System.out.println("============= ERROR IN " + CLASSNAME + "!   calcTargetNumShares  Exception Message:  [" + ex.getMessage() + "]");
//			//throw new Exception("" + CLASSNAME + ": calcTargetNumShares   Exception Message: " + ex.getMessage());
//		}

        return totalNumShares;
    }

    public String formatTargetNumShares(int buyOrSell) throws Exception {
        try {
            String returnString = "N/A";
            returnString = UTFormatter.formatShares(calcTargetNumShares(buyOrSell));
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN " + CLASSNAME + "!   formatTargetNumShares  Exception Message:  [" + ex.getMessage() + "]");
            throw new Exception(CLASSNAME + ": formatTargetNumShares   Exception Message: " + ex.getMessage());
        }
    }

    public String getBuyTargetNumShares() throws Exception {
        return formatTargetNumShares(GmConstantsBroker.TYPE_BUY);
    }

    public String getSellTargetNumShares() throws Exception {
        return formatTargetNumShares(GmConstantsBroker.TYPE_SELL);
    }

    /////////////// CONVENIENCE METHODS FOR DISPLAY FORMATTERS (ENTRY PRICE) /////////////////////
    public float calcTargetPrice(int buyOrSell) throws Exception {
        float avgPrice = 0f;

//		try {
//			float totalShares = (float) calcTargetNumShares(buyOrSell);
//
//			Map iterList;
//			if (buyOrSell == GmConstantsBroker.TYPE_BUY)
//				iterList = buyOrderList;
//			else
//				iterList = sellOrderList;
//
//			if (iterList == null)
//				return 0;
//
//			for (Iterator I = iterList.values().iterator(); I.hasNext();) {
//				OrderBrokerDecorator thisOrder = (OrderBrokerDecorator) I.next();
//				avgPrice += thisOrder.getEntryPrice() * ((float) thisOrder.getTargetSizeInShares() / totalShares);
//			}
//		} catch (Exception ex) {
//			System.out.println("============= ERROR IN " + CLASSNAME + "!   calcAveragePrice  Exception Message:  [" + ex.getMessage() + "]");
//			throw new Exception("" + CLASSNAME + ": calcAveragePrice   Exception Message: " + ex.getMessage());
//		}

        return avgPrice;
    }

    public String formatTargetPrice(int buyOrSell) throws Exception {
        try {
            String returnString = "N/A";
            returnString = UTDisplayFormatter.floatToString(calcTargetPrice(buyOrSell));
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN " + CLASSNAME + "!   calcAveragePrice  Exception Message:  [" + ex.getMessage() + "]");
            throw new Exception(CLASSNAME + ": formatTargetPrice   Exception Message: " + ex.getMessage());
        }
    }

    public String getBuyTargetPrice() throws Exception {
        return formatTargetPrice(GmConstantsBroker.TYPE_BUY);
    }

    public String getSellTargetPrice() throws Exception {
        return formatTargetPrice(GmConstantsBroker.TYPE_SELL);
    }

    /////////////// CONVENIENCE METHODS FOR DISPLAY FORMATTERS (TRANSACTION FEES) /////////////////////
    public float calcTransactionFees(int buyOrSell) throws Exception {
        float avgPrice = 0f;

//		try {
//			float totalShares = (float) calcTargetNumShares(buyOrSell);
//
//			Map iterList;
//			if (buyOrSell == GmConstantsBroker.TYPE_BUY)
//				iterList = buyOrderList;
//			else
//				iterList = sellOrderList;
//
//			if (iterList == null)
//				return 0;
//
//			for (Iterator I = iterList.values().iterator(); I.hasNext();) {
//				OrderBrokerDecorator thisOrder = (OrderBrokerDecorator) I.next();
//				avgPrice += thisOrder.getTransactionFee();
//			}
//		} catch (Exception ex) {
//			System.out.println("============= ERROR IN " + CLASSNAME + "!   calcAveragePrice  Exception Message:  [" + ex.getMessage() + "]");
//			throw new Exception("" + CLASSNAME + ": calcAveragePrice   Exception Message: " + ex.getMessage());
//		}

        return avgPrice;
    }

    public String formatTransactionFees(int buyOrSell) throws Exception {
        try {
            String returnString = "N/A";
            returnString = UTFormatter.formatPrice(calcTransactionFees(buyOrSell));
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN " + CLASSNAME + "!   calcAveragePrice  Exception Message:  [" + ex.getMessage() + "]");
            throw new Exception(CLASSNAME + ": formatTargetPrice   Exception Message: " + ex.getMessage());
        }
    }

    public String getBuyTransactionFees() throws Exception {
        return formatTransactionFees(GmConstantsBroker.TYPE_BUY);
    }

    public String getSellTransactionFees() throws Exception {
        return formatTransactionFees(GmConstantsBroker.TYPE_SELL);
    }

    /////////////// CONVENIENCE METHODS FOR DISPLAY FORMATTERS (EXECUTED NUM SHARES) /////////////////////
    public float calcTotalExecNumShares(int buyOrSell) throws Exception {
        float totalNumShares = 0f;

//		try {
//			Map iterList;
//			if (buyOrSell == GmConstantsBroker.TYPE_BUY)
//				iterList = buyOrderList;
//			else
//				iterList = sellOrderList;
//
//			if (iterList == null)
//				return 0;
//
//			for (Iterator I = iterList.values().iterator(); I.hasNext();) {
//				OrderBrokerDecorator thisOrder = (OrderBrokerDecorator) I.next();
//				totalNumShares += thisOrder.calcExecutedNumShares();
//			}
//		} catch (Exception ex) {
//			System.out.println("============= ERROR IN " + CLASSNAME + "!   calcTotalExecNumShares  Exception Message:  [" + ex.getMessage() + "]");
//			throw new Exception("" + CLASSNAME + ": calcTotalExecNumShares   Exception Message: " + ex.getMessage());
//		}

        return totalNumShares;
    }

    public String formatTotalExecNumShares(int buyOrSell) throws Exception {
        try {
            String returnString = "N/A";
            returnString = UTFormatter.formatShares(calcTotalExecNumShares(buyOrSell));
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN " + CLASSNAME + "!   formatTotalExecNumShares  Exception Message:  [" + ex.getMessage() + "]");
            throw new Exception(CLASSNAME + ": formatTotalExecNumShares   Exception Message: " + ex.getMessage());
        }
    }

    public String getBuyTotalExecNumShares() throws Exception {
        return formatTotalExecNumShares(GmConstantsBroker.TYPE_BUY);
    }

    public String getSellTotalExecNumShares() throws Exception {
        return formatTotalExecNumShares(GmConstantsBroker.TYPE_SELL);
    }

    /////////////// CONVENIENCE METHODS FOR DISPLAY FORMATTERS (AVERAGE PRICE) /////////////////////
    public float calcAveragePrice(int buyOrSell) throws Exception {
        float avgPrice = 0f;

//		try {
//			float totalShares = (float) calcTotalExecNumShares(buyOrSell);
//
//			Map iterList;
//			if (buyOrSell == GmConstantsBroker.TYPE_BUY)
//				iterList = buyOrderList;
//			else
//				iterList = sellOrderList;
//
//			if (iterList == null)
//				return 0;
//
//			for (Iterator I = iterList.values().iterator(); I.hasNext();) {
//				OrderBrokerDecorator thisOrder = (OrderBrokerDecorator) I.next();
//				avgPrice += thisOrder.getAveragePrice() * ((float) thisOrder.calcExecutedNumShares() / totalShares);
//			}
//		} catch (Exception ex) {
//			System.out.println("============= ERROR IN " + CLASSNAME + "!   calcAveragePrice  Exception Message:  [" + ex.getMessage() + "]");
//			throw new Exception("" + CLASSNAME + ": calcAveragePrice   Exception Message: " + ex.getMessage());
//		}

        return avgPrice;
    }

    public String formatAveragePrice(int buyOrSell) throws Exception {
        try {
            String returnString = "N/A";
            returnString = UTDisplayFormatter.floatToString(calcAveragePrice(buyOrSell));
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN " + CLASSNAME + "!   formatAveragePrice  Exception Message:  [" + ex.getMessage() + "]");
            throw new Exception(CLASSNAME + ": formatAveragePrice   Exception Message: " + ex.getMessage());
        }
    }

    public String getBuyAveragePrice() throws Exception {
        return formatAveragePrice(GmConstantsBroker.TYPE_BUY);
    }

    public String getSellAveragePrice() throws Exception {
        return formatAveragePrice(GmConstantsBroker.TYPE_SELL);
    }

    /////////////// CONVENIENCE METHODS FOR DISPLAY FORMATTERS (TOTAL ORDER AMOUNT) /////////////////////
    public float calcOrderListTotalAmount(int buyOrSell) throws Exception {
        float totalNumShares = 0f;

//		Map iterList;
//		if (buyOrSell == GmConstantsBroker.TYPE_BUY)
//			iterList = buyOrderList;
//		else
//			iterList = sellOrderList;
//
//		if (iterList == null)
//			return 0;
//
//		try {
//			for (Iterator I = iterList.values().iterator(); I.hasNext();) {
//				OrderBrokerDecorator thisOrder = (OrderBrokerDecorator) I.next();
//				totalNumShares += thisOrder.calcExecutedNumShares() * thisOrder.getAveragePrice();
//			}
//		} catch (Exception ex) {
//			System.out.println("============= ERROR IN " + CLASSNAME + "!   calcTotalExecNumShares  Exception Message:  [" + ex.getMessage() + "]");
//			throw new Exception("" + CLASSNAME + ": calcTotalExecNumShares   Exception Message: " + ex.getMessage());
//		}

        return totalNumShares;
    }

    public String formatOrderListTotalAmount(int buyOrSell) throws Exception {
        try {
            String returnString = "N/A";
            returnString = UTFormatter.formatPrice(calcOrderListTotalAmount(buyOrSell));
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN " + CLASSNAME + "!   formatOrderListTotalAmount  Exception Message:  [" + ex.getMessage() + "]");
            throw new Exception(CLASSNAME + ": formatOrderListTotalAmount   Exception Message: " + ex.getMessage());
        }
    }

    public String getBuyOrderListTotalAmount() throws Exception {
        return formatOrderListTotalAmount(GmConstantsBroker.TYPE_BUY);
    }

    public String getSellOrderListTotalAmount() throws Exception {
        return formatOrderListTotalAmount(GmConstantsBroker.TYPE_SELL);
    }

    /////////////// CONVENIENCE METHODS FOR DISPLAY FORMATTERS (FIRST DATETIME) /////////////////////
    public java.util.Date calcFirstDatetime(int buyOrSell) throws Exception {
        UTCalendarTime returnTime = new UTCalendarTime();
        returnTime.setCalendar("January", "01", "3000", "12", "01", "01");
        LocalDateTime firstDate = UTDatetime.fromUTCalendarTime(returnTime);
        boolean foundADate = false;

//		Map iterList;
//		if (buyOrSell == GmConstantsBroker.TYPE_BUY)
//			iterList = buyOrderList;
//		else
//			iterList = sellOrderList;
//
//		if (iterList == null)
//			return null;
//
//		try {
//			for (Iterator I = iterList.values().iterator(); I.hasNext();) {
//				OrderBrokerDecorator thisOrder = (OrderBrokerDecorator) I.next();
//
//				// java.util.Date firstDatetime = thisOrder.getFirstExecutionDatetime();
//				if (thisOrder.getFirstExecutionDatetime() == null)
//					return null;
//				else {
//					if (thisOrder.getFirstExecutionDatetime().isBefore(firstDate)) {
//						firstDate = thisOrder.getFirstExecutionDatetime();
//						foundADate = true;
//					}
//				}
//			}
//		} catch (Exception ex) {
//			System.out.println("============= ERROR IN " + CLASSNAME + "!   calcFirstDatetime  Exception Message:  [" + ex.getMessage() + "]");
//			throw new Exception("" + CLASSNAME + ": calcFirstDatetime   Exception Message: " + ex.getMessage());
//		}

        if (foundADate)
            return UTDatetime.toDate(firstDate);
        else
            return null;
    }

    public String formatFirstDatetime(int buyOrSell) throws Exception {
        try {
            String returnString = "N/A";
            java.util.Date firstDatetime = calcFirstDatetime(buyOrSell);
            if (firstDatetime != null) {
                UTCalendarTime returnTime = new UTCalendarTime(firstDatetime);
                returnString = returnTime.formatDateTimeDisplay();
            }
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN " + CLASSNAME + "!   formatFirstDatetime  Exception Message:  [" + ex.getMessage() + "]");
            throw new Exception(CLASSNAME + ": formatFirstDatetime   Exception Message: " + ex.getMessage());
        }
    }

    public String getBuyFirstDatetime() throws Exception {
        return formatFirstDatetime(GmConstantsBroker.TYPE_BUY);
    }

    public String getSellFirstDatetime() throws Exception {
        return formatFirstDatetime(GmConstantsBroker.TYPE_SELL);
    }

    /////////////// CONVENIENCE METHODS FOR DISPLAY FORMATTERS (LAST DATETIME) /////////////////////
    public java.util.Date calcLastDatetime(int buyOrSell) throws Exception {
        UTCalendarTime returnTime = new UTCalendarTime();

        returnTime.setCalendar("January", "01", "1900", "12", "01", "01");
        LocalDateTime lastDate = UTDatetime.fromUTCalendarTime(returnTime);
        boolean foundADate = false;

//		Map iterList;
//		if (buyOrSell == GmConstantsBroker.TYPE_BUY)
//			iterList = buyOrderList;
//		else
//			iterList = sellOrderList;
//
//		if (iterList == null)
//			return null;
//
//		try {
//			for (Iterator I = iterList.values().iterator(); I.hasNext();) {
//				OrderBrokerDecorator thisOrder = (OrderBrokerDecorator) I.next();
//
//				// java.util.Date lastDatetime = thisOrder.getLastExecutionDatetime();
//				if (thisOrder.getLastExecutionDatetime() == null)
//					return null;
//				else {
//					if (thisOrder.getLastExecutionDatetime().isAfter(lastDate)) {
//						lastDate = thisOrder.getLastExecutionDatetime();
//						foundADate = true;
//					}
//				}
//			}
//		} catch (Exception ex) {
//			System.out.println("============= ERROR IN " + CLASSNAME + "!   calcLastDatetime  Exception Message:  [" + ex.getMessage() + "]");
//			throw new Exception("" + CLASSNAME + ": calcLastDatetime   Exception Message: " + ex.getMessage());
//		}

        if (foundADate)
            return UTDatetime.toDate(lastDate);
        else
            return null;
    }

    public String formatLastDatetime(int buyOrSell) throws Exception {
        try {
            String returnString = "N/A";
            java.util.Date lastDatetime = calcLastDatetime(buyOrSell);
            if (lastDatetime != null) {
                UTCalendarTime returnTime = new UTCalendarTime(lastDatetime);
                returnString = returnTime.formatDateTimeDisplay();
            }
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN " + CLASSNAME + "!   formatLastDatetime  Exception Message:  [" + ex.getMessage() + "]");
            throw new Exception(CLASSNAME + ": formatLastDatetime   Exception Message: " + ex.getMessage());
        }
    }

    public String getBuyLastDatetime() throws Exception {
        return formatLastDatetime(GmConstantsBroker.TYPE_BUY);
    }

    public String getSellLastDatetime() throws Exception {
        return formatLastDatetime(GmConstantsBroker.TYPE_SELL);
    }

    /////////////// CONVENIENCE METHODS FOR DISPLAY FORMATTERS (MEMBER VARIABLES) /////////////////////
    public String getShortOrLong() throws Exception {
        String returnString = "N/A";

//		try {
//			Map iterList;
//			if (buyOrderList != null)
//				iterList = buyOrderList;
//			else if (sellOrderList != null)
//				iterList = sellOrderList;
//			else
//				return returnString;
//
//			Iterator I = iterList.values().iterator();
//			if (I.hasNext()) {
//				OrderBrokerDecorator firstOrder = (OrderBrokerDecorator) I.next();
//				returnString = firstOrder.getLongOrShortOrderDisplay();
//			}
//		} catch (Exception ex) {
//			System.out.println("============= ERROR IN " + CLASSNAME + "!   formatShortOrLong  Exception Message:  [" + ex.getMessage() + "]");
//			throw new Exception("" + CLASSNAME + ": formatShortOrLong   Exception Message: " + ex.getMessage());
//		}
        return returnString;
    }

    public String getPainPriceDisplay() {
        String returnString = "N/A";
//		returnString = UTDisplayFormatter.floatToString(painPrice);
        return returnString;
    }

    public String getTotalGrowthPricesAmountDisplay() {
        String returnString = "N/A";
//		returnString = UTFormatter.formatPrice(totalGrowthPricesAmount);
        return returnString;
    }

    public String getTotalGrowthAmountDisplay() {
        String returnString = "N/A";
//		returnString = UTFormatter.formatPrice(totalGrowthAmount);
        return returnString;
    }

    public String getSuccessOrPain() {
//		if (totalGrowthAmount > 0)
//			return "SUCCESS";
//		else
        return "LOSS";
    }

    public String getLongOrShortDisplay() {
//		if (longOrShort == 1)
//			return "LONG";
//		else
        return "SHORT";
    }

    public String getBuyExecutedPriceDisplay() {
        String returnString = "N/A";
//		returnString = UTDisplayFormatter.floatToString(buyExecutedPrice);
        return returnString;
    }

    public String getSellExecutedPriceDisplay() {
        String returnString = "N/A";
//		returnString = UTDisplayFormatter.floatToString(sellExecutedPrice);
        return returnString;
    }

    public String getPercentGrowthPricesDisplay() {
        String returnString = "N/A";
//		returnString = UTFormatter.formatPrice(percentGrowthPrices);
        return returnString;
    }

    public String getPercentGrowthDisplay() {
        String returnString = "N/A";
//		returnString = UTFormatter.formatPrice(percentGrowth);
        return returnString;
    }

    public String getTotalTransactionFeesString() {
        try {
            String returnString = "N/A";
//			returnString = UTFormatter.formatPrice(totalTransactionFees);
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN " + CLASSNAME + "!   getTotalTransactionFeesString  Exception Message:  [" + ex.getMessage() + "]");
        }
        return "";
    }

    public String getTotalMarginFeesString() {
        try {
            String returnString = "N/A";
//			returnString = UTFormatter.formatPrice(totalMarginFees);
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN " + CLASSNAME + "!   getTotalMarginFeesString  Exception Message:  [" + ex.getMessage() + "]");
        }
        return "";
    }
}
