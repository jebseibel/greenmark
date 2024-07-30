package com.greenmark.common.dto.account.timeperiod.decorator;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.greenmark.common.dto.account.AccountEventDto;
import com.greenmark.common.dto.parameters.AccountBalanceParams;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTFormatter;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountEventDtoDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its AccountEventDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class AccountEventDtoDecorator extends AccountEventDto implements Serializable {
	private static final String CLASSNAME = "AccountEventDtoDecorator";
	private static final long serialVersionUID = 1L;

	public AccountEventDtoDecorator() {
	}

	public AccountEventDtoDecorator(AccountBalanceParams account, LocalDateTime eventDate, String description, String symbol, String harvestStrategyAcronym, float numShares, float orderPrice,
			float orderAmount, float transactionFee, float marginFee) {
		super(account, eventDate, description, symbol, harvestStrategyAcronym, numShares, orderPrice, orderAmount, transactionFee, marginFee);
	}

	public AccountEventDtoDecorator(String xmldata) {
		super(xmldata);
	}

	public String getEventDateString() {
		return UTDatetime.toString(eventDate);
	}

	public String getNumSharesString() {
		if (getNumShares() != 0) {
			return "$" + UTFormatter.formatPercent3Digit(getNumShares());
		}
		return "$0";
	}

	public String getOrderPriceString() {
		if (getOrderPrice() != 0) {
			return "$" + UTFormatter.formatPercent3Digit(getOrderPrice());
		}
		return "$0";
	}

	public String getOrderAmountString() {
		if (getOrderAmount() != 0) {
			return "$" + UTFormatter.formatPercent3Digit(getOrderAmount());
		}
		return "$0";
	}

	public String getTransactionFeeString() {
		if (getTransactionFee() != 0) {
			return "$" + UTFormatter.formatPercent3Digit(getTransactionFee());
		}
		return "$0";
	}

	public String getMarginFeeString() {
		if (getMarginFee() != 0) {
			return "$" + UTFormatter.formatPercent3Digit(getMarginFee());
		}
		return "$0";
	}

	public String getAllocatedFundsCashString() {
		if (getAllocatedFundsCash() != 0) {
			return "$" + UTFormatter.formatPercent3Digit(getAllocatedFundsCash());
		}
		return "$0";
	}

	public String getUnsettledFundsCashString() {
		if (getUnsettledFundsCash() != 0) {
			return "$" + UTFormatter.formatPercent3Digit(getUnsettledFundsCash());
		}
		return "$0";
	}

	public String getCashOnHand_CashString() {
		if (getCashOnHand_Cash() != 0) {
			return "$" + UTFormatter.formatPercent3Digit(getCashOnHand_Cash());
		}
		return "$0";
	}

	public String getAllocatedFundsMarginString() {
		if (getAllocatedFundsMargin() != 0) {
			return "$" + UTFormatter.formatPercent3Digit(getAllocatedFundsMargin());
		}
		return "$0";
	}

	public String getUnsettledFundsMarginString() {
		if (getUnsettledFundsMargin() != 0) {
			return "$" + UTFormatter.formatPercent3Digit(getUnsettledFundsMargin());
		}
		return "$0";
	}

	public String getCashOnHand_MarginString() {
		if (getCashOnHand_Margin() != 0) {
			return "$" + UTFormatter.formatPercent3Digit(getCashOnHand_Margin());
		}
		return "$0";
	}

	public String getAccountBalanceCashString() {
		if (getAccountBalanceCash() != 0) {
			return "$" + UTFormatter.formatPercent3Digit(getAccountBalanceCash());
		}
		return "$0";
	}

	public String getPositionsTotalLongString() {
		if (getPositionsTotalLong() != 0) {
			return "$" + UTFormatter.formatPercent3Digit(getPositionsTotalLong());
		}
		return "$0";
	}

	public String getAccountBalanceMarginString() {
		if (getAccountBalanceMargin() != 0) {
			return "$" + UTFormatter.formatPercent3Digit(getAccountBalanceMargin());
		}
		return "$0";
	}

	public String getPositionsTotalShortString() {
		if (getPositionsTotalShort() != 0) {
			return "$" + UTFormatter.formatPercent3Digit(getPositionsTotalShort());
		}
		return "$0";
	}

}
