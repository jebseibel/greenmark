package com.greenmark.common.dto.account.database;

import java.io.Serializable;
import java.util.Iterator;

import com.greenmark.common.dto.account.timeperiod.AccountDailyDto;
import com.greenmark.common.dto.account.timeperiod.decorator.AccountDailyDtoDecorator;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountCalculatorDtoDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its AccountCalculatorDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class AccountCalculatorDtoDecorator extends AccountCalculatorDto implements Serializable {
	public static final String CLASSNAME = "AccountCalculatorDtoDecorator";
	private static final long serialVersionUID = 1L;

	public AccountCalculatorDtoDecorator() {
		super();
	}

//	public AccountCalculatorDtoDecorator(CreateAccountDto createAccountData) {
//		super(createAccountData);
//	}
//
//	public AccountCalculatorDtoDecorator(String xmldata) {
//		super(xmldata, trace);
//	}

	// ------------------------------------------------ CONVENIENCE METHODS -----------------------------------------------
	public AccountDailyDtoDecorator getAccountDailyById(long accountDailyId) {
		// Keep track of the previous day since the webpage will be asking for it.
		previousDay = null;

		Iterator accountDailyIter = accountDailyList.iterator();
		while (accountDailyIter.hasNext()) {
			AccountDailyDtoDecorator thisDaily = (AccountDailyDtoDecorator) accountDailyIter.next();
			if (thisDaily.getId() == accountDailyId)
				return thisDaily;

			previousDay = thisDaily;
		}

		return null;
	}

	public int calculateTotalNumPositions() {
		return (int) (numSuccessPositions + numLossPositions + numPainPositions + numRejectedPositions + numConfirmRejectedPositions);
	}

	public int calculateTotalNumPositionsLong() {
		return (int) (numLongSuccess + numLongLoss + numLongPain + numLongRejected + numLongConfirmRejected);
	}

	public int calculateTotalNumPositionsShort() {
		return (int) (numShortSuccess + numShortLoss + numShortPain + numShortRejected + numShortConfirmRejected);
	}

	public double calculateEndLongPositionsTotal() {
		if (hasAccountDaily) {
			AccountDailyDto lastDay = (AccountDailyDto) accountDailyList.get(accountDailyList.size() - 1);
			return lastDay.getLongPositionsTotal();
		} else
			return 0.0F;
	}

	public double calculateEndShortPositionsTotal() {
		if (hasAccountDaily) {
			AccountDailyDto lastDay = (AccountDailyDto) accountDailyList.get(accountDailyList.size() - 1);
			return lastDay.getShortPositionsTotal();
		} else
			return 0.0F;
	}

	// ------------------------------------------------ CALCULATORS -----------------------------------------------
	public float calculatePercentSuccessPositions() {
		if (calculateTotalNumPositions() != 0F) {
			float calcPercent = ((numLongSuccess + numShortSuccess) / (calculateTotalNumPositions())) * 100;
			return calcPercent;
		}
		return 0F;
	}

	public float calculatePercentFailedPositions() {
		if (calculateTotalNumPositions() != 0F) {
			float calcPercent = ((numLongLoss + numShortLoss) / (calculateTotalNumPositions())) * 100;
			return calcPercent;
		}
		return 0F;
	}

	public float calculatePercentPainPositions() {
		if (calculateTotalNumPositions() != 0F) {
			float calcPercent = ((numLongPain + numShortPain) / (calculateTotalNumPositions())) * 100;
			return calcPercent;
		}
		return 0F;
	}

	public float calculatePercentRejectedPositions() {
		if (calculateTotalNumPositions() != 0F) {

			float calcPercent = ((numLongRejected + numShortRejected) / (calculateTotalNumPositions())) * 100;
			return calcPercent;
		}
		return 0F;
	}

	public float calculatePercentConfirmRejectedPositions() {
		if (calculateTotalNumPositions() != 0F) {

			float calcPercent = ((numLongConfirmRejected + numShortConfirmRejected) / (calculateTotalNumPositions())) * 100;
			return calcPercent;
		}
		return 0F;
	}

	public float calculateAvgSuccessGain() {
		if (numSuccessPositions != 0) {
			float calcPercent = (longSuccessAvgGain + shortSuccessAvgGain) / (numSuccessPositions);
			return calcPercent;
		}
		return 0F;
	}

	public float calculateAvgFailedLoss() {
		if (numLossPositions != 0) {
			float calcPercent = (longLossAvgLoss + shortLossAvgLoss) / (numLossPositions);
			return calcPercent;
		}
		return 0F;
	}

	public float calculateAvgPainLoss() {
		if (numPainPositions != 0) {
			float calcPercent = (longPainAvgLoss + shortPainAvgLoss) / (numPainPositions);
			return calcPercent;
		}
		return 0F;
	}

	public float calculateAvgRejectedLoss() {
		if (numRejectedPositions != 0) {
			float calcPercent = (longRejectedAvgLoss + shortRejectedAvgLoss) / (numRejectedPositions);
			return calcPercent;
		}
		return 0F;
	}

	public float calculateAvgConfirmRejectedLoss() {
		if (numConfirmRejectedPositions != 0) {
			float calcPercent = (longConfirmRejectedAvgLoss + shortConfirmRejectedAvgLoss) / (numConfirmRejectedPositions);
			return calcPercent;
		}
		return 0F;
	}

	public float calculateAvgSuccessGainLong() {
		if (numLongSuccess != 0) {
			float calcPercent = longSuccessAvgGain / numLongSuccess;
			return calcPercent;
		}
		return 0F;
	}

	public float calculateAvgFailedLossLong() {
		if (numLongLoss != 0) {
			float calcPercent = longLossAvgLoss / numLongLoss;
			return calcPercent;
		}
		return 0F;
	}

	public float calculateAvgPainLossLong() {
		if (numLongPain != 0) {
			float calcPercent = longPainAvgLoss / numLongPain;
			return calcPercent;
		}
		return 0F;
	}

	public float calculateAvgRejectedLossLong() {
		if (numLongRejected != 0) {
			float calcPercent = longRejectedAvgLoss / numLongRejected;
			return calcPercent;
		}
		return 0F;
	}

	public float calculateAvgConfirmRejectedLossLong() {
		if (numLongConfirmRejected != 0) {
			float calcPercent = longConfirmRejectedAvgLoss / numLongConfirmRejected;
			return calcPercent;
		}
		return 0F;
	}

	public float calculateAvgSuccessGainShort() {
		if (numShortSuccess != 0) {
			float calcPercent = shortSuccessAvgGain / numShortSuccess;
			return calcPercent;
		}
		return 0F;
	}

	public float calculateAvgFailedLossShort() {
		if (numShortLoss != 0) {
			float calcPercent = shortLossAvgLoss / numShortLoss;
			return calcPercent;
		}
		return 0F;
	}

	public float calculateAvgPainLossShort() {
		if (numShortPain != 0) {
			float calcPercent = shortPainAvgLoss / numShortPain;
			return calcPercent;
		}
		return 0F;
	}

	public float calculateAvgRejectedLossShort() {
		if (numShortRejected != 0) {
			float calcPercent = shortRejectedAvgLoss / numShortRejected;
			return calcPercent;
		}
		return 0F;
	}

	public float calculateAvgConfirmRejectedLossShort() {
		if (numShortConfirmRejected != 0) {
			float calcPercent = shortConfirmRejectedAvgLoss / numShortConfirmRejected;
			return calcPercent;
		}
		return 0F;
	}

	// ------------------------------------------------ START/END CALCULATORS -----------------------------------------------
	public double findStartNetLiquidationValue() {
		if (hasAccountDaily) {
			AccountDailyDto firstDay = (AccountDailyDto) accountDailyList.get(0);
			return firstDay.getNetLiquidationValue();
		} else
			return 0.0F;
	}

	public double findStartGrandEquity() {
		if (hasAccountDaily) {
			AccountDailyDto firstDay = (AccountDailyDto) accountDailyList.get(0);
			return firstDay.getGrandTotalEquity();
		} else
			return 0.0F;
	}

	public double findStartEquity() {
		if (hasAccountDaily) {
			AccountDailyDto lastDay = (AccountDailyDto) accountDailyList.get(0);
			return lastDay.getTotalEquity();
		} else
			return 0.0F;
	}

	// ------------------------------------------------ END DECORATORS -----------------------------------------------
	public double findEndNetLiquidationValue() {
		if (hasAccountDaily) {
			AccountDailyDto lastDay = (AccountDailyDto) accountDailyList.get(accountDailyList.size() - 1);
			return lastDay.getNetLiquidationValue();
		} else
			return 0.0F;
	}

	public double findEndGrandEquity() {
		if (hasAccountDaily) {
			AccountDailyDto lastDay = (AccountDailyDto) accountDailyList.get(accountDailyList.size() - 1);
			return lastDay.getGrandTotalEquity();
		} else
			return 0.0F;
	}

	public double findEndGrandCashOnHand() {
		if (hasAccountDaily) {
			AccountDailyDto lastDay = (AccountDailyDto) accountDailyList.get(accountDailyList.size() - 1);
			return lastDay.getGrandCashOnHand();
		} else
			return 0.0F;
	}

	public double findEndGrandUnsettledFunds() {
		if (hasAccountDaily) {
			AccountDailyDto lastDay = (AccountDailyDto) accountDailyList.get(accountDailyList.size() - 1);
			return lastDay.getGrandUnsettledFunds();
		} else
			return 0.0F;
	}

	public double findEndEquity() {
		if (hasAccountDaily) {
			AccountDailyDto lastDay = (AccountDailyDto) accountDailyList.get(accountDailyList.size() - 1);
			return lastDay.getTotalEquity();
		} else
			return 0.0F;
	}

	public double findEndCashOnHand() {
		if (hasAccountDaily) {
			AccountDailyDto lastDay = (AccountDailyDto) accountDailyList.get(accountDailyList.size() - 1);
			return lastDay.getCashOnHand();
		} else
			return 0.0F;
	}

	public double findEndUnsettledFunds() {
		if (hasAccountDaily) {
			AccountDailyDto lastDay = (AccountDailyDto) accountDailyList.get(accountDailyList.size() - 1);
			return lastDay.getUnsettledFunds();
		} else
			return 0.0F;
	}

	public double findEndEquityMargin() {
		if (hasAccountDaily) {
			AccountDailyDto lastDay = (AccountDailyDto) accountDailyList.get(accountDailyList.size() - 1);
			return lastDay.getTotalEquityMargin();
		} else
			return 0.0F;
	}

	public double findEndCashOnHandMargin() {
		if (hasAccountDaily) {
			AccountDailyDto lastDay = (AccountDailyDto) accountDailyList.get(accountDailyList.size() - 1);
			return lastDay.getCashOnHandMargin();
		} else
			return 0.0F;
	}

	public double findEndUnsettledFundsMargin() {
		if (hasAccountDaily) {
			AccountDailyDto lastDay = (AccountDailyDto) accountDailyList.get(accountDailyList.size() - 1);
			return lastDay.getUnsettledFundsMargin();
		} else
			return 0.0F;
	}

}
