package com.greenmark.common.dto.broker.decorator;

import java.io.Serializable;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsBroker;
import com.greenmark.common.dto.broker.OrderDto;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTFormatter;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: OrderDtoDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its OrderDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class OrderDtoDecorator extends OrderDto implements Serializable {
	public static final String CLASSNAME = "OrderDtoDecorator";
	private static final long serialVersionUID = 1L;

	public OrderDtoDecorator() {
	}

	public OrderDtoDecorator(String xmldata) {
		super(xmldata);
	}

	// ------------------------------------------------ REQUIRED METHODS ---------------------------------------------------
	public final boolean equals(Object compareObject) {
		OrderDtoDecorator obj = (OrderDtoDecorator) compareObject;
		if (obj.getKey().equals(new Long(id))) {
			return true;
		} else {
			return false;
		}
	}

	public final Object getKey() {
		return (Object) new Long(id);
	}

	public final Object getValue() {
		return (Object) this;
	}

	public final int hashCode() {
		return (int) id;
	}

	// ------------------------------------------------ CONVENIENCE METHODS ---------------------------------------------------
	public String getLongOrShortDisplay() {
		return getLongOrShort();
	}

	public String getEntryPriceDatetimeString() {
		if (entryPriceDatetime != null)
			return UTDatetime.toString(entryPriceDatetime);

		return "";
	}

	public String getLongOrShort() {
		if (longOrShortOrder == GmConstants.SHORT_SECURITY)
			return "Short";
		if (longOrShortOrder == GmConstants.LONG_SECURITY)
			return "Long";
		return "None";
	}

	public String getIsMomentumUpString() {
		if (isMomentumUp == 0)
			return "No";
		else
			return "Yes";
	}

	public String getTargetAmountInDollarsDisplay() {
		String methodname = "getTargetAmountInDollarsDisplay";

		String returnString = "N/A";

		try {
			returnString = UTFormatter.formatPrice(targetAmountInDollars);
		} catch (Exception ex) {
			// Ignore, Return default value below
		}
		return returnString;
	}

	public String getTypeSellOrder() {
		return Integer.toString(GmConstantsBroker.TYPE_SELL);
	}

	public String getOrderReasonDisplay() {
		return formatOrderReason();
	}

	public String getBuyOrSellOrderString() {
		if (buyOrSellOrder == GmConstantsBroker.TYPE_BUY)
			return "BUY";
		else if (buyOrSellOrder == GmConstantsBroker.TYPE_SELL)
			return "SELL";
		else
			return "UNKNOWN";
	}

	public String formatOrderReason() {
		if (orderReason == GmConstantsBroker.ORDER_REASON_ADDED_FROM_BUCKETS)
			return "BUCKETS";
		else if (orderReason == GmConstantsBroker.ORDER_REASON_EXCEEDED_PAIN_THRESHOLD)
			return "PAIN OUT";
		else if (orderReason == GmConstantsBroker.ORDER_REASON_USER_SELL_MARKET_ORDER)
			return "MKT ORDER";
		else if (orderReason == GmConstantsBroker.ORDER_REASON_DEFAULT)
			return "STOP ORDER";
		else if (orderReason == GmConstantsBroker.ORDER_REASON_DROPCAT_EXCEEDED_ADDTL_PAIN_THRESHOLD)
			return "DCAT PAINED";
		else if (orderReason == GmConstantsBroker.ORDER_REASON_DROPCAT_END_OF_DAY)
			return "DCAT EODay";
		else if (orderReason == GmConstantsBroker.ORDER_REASON_DROPCAT_DOUBLE_DOWN_BUY)
			return "DCAT DBL BUY";
		else if (orderReason == GmConstantsBroker.ORDER_REASON_PROFIT_TAKE_MARKET_ORDER)
			return "PROFIT TAKE";
		else if (orderReason == GmConstantsBroker.ORDER_REASON_SECURITY_REJECTED)
			return "SECURITY REJECTED";
		else
			return "";
	}

	public String formatOrderStatus() {
		String statusMsg = "";

		if (status == GmConstantsBroker.STATUS_PENDING_SUBMIT) {
			statusMsg = "  PENDING  ";
		} else if (status == GmConstantsBroker.STATUS_PENDING_SUBMIT_STUCK) {
			statusMsg = "  PENDING  ";
		} else if (status == GmConstantsBroker.STATUS_SUBMITTED || status == GmConstantsBroker.STATUS_PRESUBMITTED) {
			statusMsg = "  SUBMIT   ";
		} else if (status == GmConstantsBroker.STATUS_OPEN) {
			statusMsg = "   OPEN    ";
		} else if (status == GmConstantsBroker.STATUS_PARTIAL) {
			statusMsg = "  PARTIAL  ";
		} else if (status == GmConstantsBroker.STATUS_EXECUTED) {
			statusMsg = " EXECUTED  ";
		} else if (status == GmConstantsBroker.STATUS_CANCELLED) {
			statusMsg = " CANCELLED ";
		} else if (status == GmConstantsBroker.STATUS_CANCELLING) {
			statusMsg = " CANCELING ";
		} else if (status == GmConstantsBroker.STATUS_REJECTED_BY_BROKER) {
			statusMsg = " REJECTED ";
		} else if (status == GmConstantsBroker.STATUS_UNKNOWN) {
			statusMsg = "  UNKNOWN  ";
		} else if (status == GmConstantsBroker.STATUS_REPLACING) {
			statusMsg = " REPLACING ";
		}

		else {
			statusMsg = "  OTHER  ";
		}

		return statusMsg;
	}

	public String getTransactionFeeDisplay() {
		return UTFormatter.formatPrice(transactionFee);
	}

	public String getMarginFeeDisplay() {
		return UTFormatter.formatPrice(marginFee);
	}

	public String getEntryPriceDisplay() {
		String entryPriceString = UTDisplayFormatter.floatToString(entryPrice);
		return entryPriceString;
	}

	public String getTargetSizeInSharesDisplay() {
		return Float.toString(targetSizeInShares);
	}

	public float getRemainingShares() {
		return targetSizeInShares - executedNumShares;
	}

	public String getActiveDisplay() {
		if (active == 1)
			return "ACTIVE";
		else
			return "INACTIVE";
	}

	public String getLongOrShortOrderDisplay() {
		if (longOrShortOrder == 1)
			return "LONG";
		else
			return "SHORT";
	}
}
