package com.greenmark.common.dto.scenario.database.decorator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.greenmark.common.GmConstantsDatafeed;
import com.greenmark.common.dto.account.timeperiod.AccountDailyDto;
import com.greenmark.common.dto.broker.database.decorator.PositionDbDecorator;
import com.greenmark.common.dto.parameters.AccountBalanceParams;
import com.greenmark.common.dto.scenario.database.ScenarioDb;
import com.greenmark.common.dto.scenario.decorator.ScenarioDtoDecorator;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTFormatter;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ScenarioDbDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its AccountDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ScenarioDbDecorator extends ScenarioDb implements Serializable {
	private static final String CLASSNAME = "ScenarioDbDecorator";
	private static final long serialVersionUID = 1L;

	public ScenarioDbDecorator() {
	}

	public ScenarioDbDecorator(int datafeedTypeInt) {
		String dataFeedString = GmConstantsDatafeed.getDatasourceFeedString(datafeedTypeInt);
	}

	// This is used by the historical scenarios to do 3 scenarios for 1 account.
	public ScenarioDbDecorator(ScenarioDtoDecorator currentScenario, AccountBalanceParams accountBalance, int currentScenarioNumber) {
		super(currentScenario, accountBalance, currentScenarioNumber);
	}

	public ScenarioDbDecorator(String xmldata) {
		super(xmldata);
	}

	// For Demo user of website.
	public boolean purgeCurrentPositions() {
		boolean returnVal = true;

		if (positionList != null) {
			List<PositionDbDecorator> newPositionList = new ArrayList<>();

			for (PositionDbDecorator thisPosition : newPositionList) {
//				if (thisPosition.getSellExecutedNumShares() > 0)
					newPositionList.add(thisPosition);
			}

			positionList = newPositionList;
		}

		return returnVal;
	}

	// -------------------------------------------------------------- CASH AND LIQUIDATION VALUES ----------------------------------------------------------------

	public String getStartNetLiquidationValue() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = accountDailyList.get(0);
			returnString = UTFormatter.formatPrice(firstDay.getNetLiquidationValue());
		}
		return "$" + returnString;
	}

	public String getStartGrandEquity() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = accountDailyList.get(0);
			returnString = UTFormatter.formatPrice(firstDay.getGrandTotalEquity());
		}
		return "$" + returnString;
	}

	public String getStartGrandCashOnHand() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = accountDailyList.get(0);
			returnString = UTFormatter.formatPrice(firstDay.getGrandCashOnHand());
		}
		return "$" + returnString;
	}

	public String getStartGrandAllocatedFunds() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = accountDailyList.get(0);
			returnString = UTFormatter.formatPrice(firstDay.getGrandAllocatedFunds());
		}
		return "$" + returnString;
	}

	public String getStartGrandUnsettledFunds() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = accountDailyList.get(0);
			returnString = UTFormatter.formatPrice(firstDay.getGrandUnsettledFunds());
		}
		return "$" + returnString;
	}

	public String getStartEquity() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = accountDailyList.get(0);
			returnString = UTFormatter.formatPrice(firstDay.getTotalEquity());
		}
		return "$" + returnString;
	}

	public String getStartCashOnHand() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = accountDailyList.get(0);
			returnString = UTFormatter.formatPrice(firstDay.getCashOnHand());
		}
		return "$" + returnString;
	}

	public String getStartAllocatedFunds() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = accountDailyList.get(0);
			returnString = UTFormatter.formatPrice(firstDay.getAllocatedFunds());
		}
		return "$" + returnString;
	}

	public String getStartUnsettledFunds() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = accountDailyList.get(0);
			returnString = UTFormatter.formatPrice(firstDay.getUnsettledFunds());
		}
		return "$" + returnString;
	}

	public String getStartEquityMargin() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = accountDailyList.get(0);
			returnString = UTFormatter.formatPrice(firstDay.getTotalEquityMargin());
		}
		return "$" + returnString;
	}

	public String getStartCashOnHandMargin() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = accountDailyList.get(0);
			returnString = UTFormatter.formatPrice(firstDay.getCashOnHandMargin());
		}
		return "$" + returnString;
	}

	public String getStartAllocatedFundsMargin() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = accountDailyList.get(0);
			returnString = UTFormatter.formatPrice(firstDay.getAllocatedFundsMargin());
		}
		return "$" + returnString;
	}

	public String getStartUnsettledFundsMargin() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = accountDailyList.get(0);
			returnString = UTFormatter.formatPrice(firstDay.getUnsettledFundsMargin());
		}
		return "$" + returnString;
	}

	public String getStartLongPositionsTotal() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = accountDailyList.get(0);
			returnString = UTFormatter.formatPrice(firstDay.getLongPositionsTotal());
		}
		return "$" + returnString;
	}

	public String getStartShortPositionsTotal() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = accountDailyList.get(0);
			returnString = UTFormatter.formatPrice(firstDay.getShortPositionsTotal());
		}
		return "$" + returnString;
	}

	public String getEndNetLiquidationValueString() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnString = UTFormatter.formatPrice(lastDay.getNetLiquidationValue());
		}
		return "$" + returnString;
	}

	public double getEndNetLiquidationValue() {
		double returnVal = 0D;

		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnVal = lastDay.getNetLiquidationValue();
		}
		return returnVal;
	}

	public String getEndGrandEquity() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnString = UTFormatter.formatPrice(lastDay.getGrandTotalEquity());
		}
		return "$" + returnString;
	}

	public String getEndGrandCashOnHand() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnString = UTFormatter.formatPrice(lastDay.getGrandCashOnHand());
		}
		return "$" + returnString;
	}

	public String getEndGrandAllocatedFunds() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnString = UTFormatter.formatPrice(lastDay.getGrandAllocatedFunds());
		}
		return "$" + returnString;
	}

	public String getEndGrandUnsettledFunds() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnString = UTFormatter.formatPrice(lastDay.getGrandUnsettledFunds());
		}
		return "$" + returnString;
	}

	public String getEndEquity() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnString = UTFormatter.formatPrice(lastDay.getTotalEquity());
		}
		return "$" + returnString;
	}

	public String getEndCashOnHand() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnString = UTFormatter.formatPrice(lastDay.getCashOnHand());
		}
		return "$" + returnString;
	}

	public String getEndAllocatedFunds() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnString = UTFormatter.formatPrice(lastDay.getAllocatedFunds());
		}
		return "$" + returnString;
	}

	public String getEndUnsettledFunds() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnString = UTFormatter.formatPrice(lastDay.getUnsettledFunds());
		}
		return "$" + returnString;
	}

	public String getEndEquityMargin() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnString = UTFormatter.formatPrice(lastDay.getTotalEquityMargin());
		}
		return "$" + returnString;
	}

	public String getEndCashOnHandMargin() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnString = UTFormatter.formatPrice(lastDay.getCashOnHandMargin());
		}
		return "$" + returnString;
	}

	public String getEndAllocatedFundsMargin() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnString = UTFormatter.formatPrice(lastDay.getAllocatedFundsMargin());
		}
		return "$" + returnString;
	}

	public String getEndUnsettledFundsMargin() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnString = UTFormatter.formatPrice(lastDay.getUnsettledFundsMargin());
		}
		return "$" + returnString;
	}

	public String getEndLongPositionsTotal() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnString = UTFormatter.formatPrice(lastDay.getLongPositionsTotal());
		}
		return "$" + returnString;
	}

	public String getEndShortPositionsTotal() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
			returnString = UTFormatter.formatPrice(lastDay.getShortPositionsTotal());
		}
		return "$" + returnString;
	}

	public String getStartDateDisplayString() {
		String returnString = "N/A";
		if (hasAccountDaily) {
			AccountDailyDto firstDay = null;
			if (accountDailyList.size() == 1)
				firstDay = accountDailyList.get(0); // Get the second record, the first one is our open balance record(yesterday)
			else
				firstDay = accountDailyList.get(1); // Get the second record, the first one is our open balance record(yesterday)

			returnString = UTDatetime.toDateOnlyString(firstDay.getThisDate());
		}
		return returnString;
	}
}
