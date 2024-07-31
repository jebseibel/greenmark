package com.greenmark.common.dto.account.database;

import com.greenmark.common.dto.account.AccountDto;
import com.greenmark.common.dto.account.timeperiod.AccountDailyDto;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTFormatter;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its AccountCalculator base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class AccountDecorator extends AccountCalculator implements Serializable {
    private static final String CLASSNAME = "AccountDecorator";
    private static final long serialVersionUID = 1L;

    public AccountDecorator() {
        super();
    }

    public AccountDecorator(AccountDto inAccount) {
        this();

        try {
            if (inAccount != null)
                BeanUtils.copyProperties(this, inAccount);
        } catch (InvocationTargetException ite) {
            System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage());
        } catch (IllegalAccessException iae) {
            System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
        }
    }

//	public AccountDecorator(CreateAccountDto createAccountData) {
//		super(createAccountData);
//	}
//
//	public AccountDecorator(String xmldata) {
//		super(xmldata, trace);
//	}

    // ----------------------------- ACCOUNT SUMMARY GETTERS --------------------------------------
    public String getIdString() {
        return ((Long) id).toString();
    }

    public String getYearlyROIString() {
        String returnVal = "0%";
        if (Float.isInfinite(yearlyRoi))
            return "1 DAY";

        if (yearlyRoi != 0)
            returnVal = UTFormatter.formatPercent(yearlyRoi);
        return returnVal + "%";
    }

    public String getMonthlyROIString() {
        String returnVal = "0%";
        if (Float.isInfinite(monthlyRoi))
            return "1 DAY";

        if (monthlyRoi != 0)
            returnVal = UTFormatter.formatPercent(monthlyRoi);
        return returnVal + "%";
    }

    public String getYearlyPercentGrowthString() {
        String returnVal = "0%";
        if (calcPercentGrowthYearly != 0)
            returnVal = UTFormatter.formatPercent(calcPercentGrowthYearly);
        return returnVal + "%";
    }

    public String getMonthlyPercentGrowthString() {
        String returnVal = "0%";
        if (calcPercentGrowthMonthly != 0)
            returnVal = UTFormatter.formatPercent(calcPercentGrowthMonthly);
        return returnVal + "%";
    }

    public String getTotalProfitAndLossString() {
        String returnString = "$" + UTFormatter.formatPrice(totalProfitLoss);
        return returnString;
    }

    public String getLongProfitAndLossString() {
        String returnString = UTFormatter.formatPrice(longTotalProfitLoss);
        return returnString;
    }

    public String getShortProfitAndLossString() {
        String returnString = UTFormatter.formatPrice(shortTotalProfitLoss);
        return returnString;
    }

    public String getTotalNumPositionsString() {
        return Integer.toString(calculateTotalNumPositions());
    }

    public String getTotalNumPositionsLongString() {
        return Integer.toString(calculateTotalNumPositionsLong());
    }

    public String getTotalNumPositionsShortString() {
        return Integer.toString(calculateTotalNumPositionsShort());
    }

    public String getPercentSuccessPositionsString() {
        String returnVal = "0";
        if (successPositionsPercent == 0)
            return "0%";
        else
            returnVal = UTFormatter.formatPercent(calculatePercentSuccessPositions());

        return returnVal + "%";
    }

    public String getPercentFailedPositionsString() {
        String returnVal = "0";
        if ((longPercentLoss == 0) && (shortPercentLoss == 0))
            return "0%";
        else
            returnVal = UTFormatter.formatPercent(calculatePercentFailedPositions());

        return returnVal + "%";
    }

    public String getPercentPainPositionsString() {
        String returnVal = "0";
        if ((longPercentPain == 0) && (shortPercentPain == 0))
            return "0%";
        else
            returnVal = UTFormatter.formatPercent(calculatePercentPainPositions());

        return returnVal + "%";
    }

    public String getPercentRejectedPositionsString() {
        String returnVal = "0";
        if ((longPercentRejected == 0) && (shortPercentRejected == 0))
            return "0%";
        else
            returnVal = UTFormatter.formatPercent(calculatePercentRejectedPositions());

        return returnVal + "%";
    }

    public String getPercentConfirmRejectedPositionsString() {
        String returnVal = "0";
        if ((longPercentConfirmRejected == 0) && (shortPercentConfirmRejected == 0))
            return "0%";
        else
            returnVal = UTFormatter.formatPercent(calculatePercentConfirmRejectedPositions());

        return returnVal + "%";
    }

    public String getAvgSuccessGainString() {
        if (numSuccessPositions != 0) {
            float calcPercent = calculateAvgSuccessGain();
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        }

        return "$0";
    }

    public String getAvgFailedLossString() {
        if (numLossPositions != 0) {
            float calcPercent = calculateAvgFailedLoss();
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        }
        return "$0";
    }

    public String getAvgPainLossString() {
        if (numPainPositions != 0) {
            float calcPercent = calculateAvgPainLoss();
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        }
        return "$0";
    }

    public String getAvgRejectedLossString() {
        if (numRejectedPositions != 0) {
            float calcPercent = calculateAvgRejectedLoss();
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        }
        return "$0";
    }

    public String getAvgConfirmRejectedLossString() {
        if (numConfirmRejectedPositions != 0) {
            float calcPercent = calculateAvgConfirmRejectedLoss();
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        }
        return "$0";
    }

    // ----------------------------- ACCOUNT BALANCE GETTERS --------------------------------------
    public String getEndEquityMargin_NoPenniesString() {
        String returnString = "$0.00";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatDoubleNoPennies(findEndEquityMargin());

        return returnString;
    }

    public String getEndCashOnHandMarginString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
            returnString = UTFormatter.formatPrice(lastDay.getCashOnHandMargin());
        }
        return "$" + returnString;
    }

    public String getEndAllocatedFundsMarginString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
            returnString = UTFormatter.formatPrice(lastDay.getAllocatedFundsMargin());
        }
        return "$" + returnString;
    }

    public String getEndUnsettledFundsMarginString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
            returnString = UTFormatter.formatPrice(lastDay.getUnsettledFundsMargin());
        }
        return "$" + returnString;
    }

    public String getEndLongPositionsTotalString() {
        String returnString = "$0.00";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatPrice(calculateEndLongPositionsTotal());

        return returnString;
    }

    public String getEndLongPositionsTotal_NoPenniesString() {
        String returnString = "$0";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatDoubleNoPennies(calculateEndLongPositionsTotal());

        return returnString;
    }

    public String getEndShortPositionsTotalString() {
        String returnString = "$0.00";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatPrice(calculateEndShortPositionsTotal());
        return returnString;
    }

    public String getEndShortPositionsTotal_NoPenniesString() {
        String returnString = "$0";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatDoubleNoPennies(calculateEndShortPositionsTotal());
        return returnString;
    }

    public String getEndEquity_NoPenniesString() {
        String returnString = "$0";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatDoubleNoPennies(findEndEquity());

        return returnString;
    }

    public String getEndCashOnHandString() {
        String returnString = "$0.00";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatPrice(findEndCashOnHand());

        return returnString;
    }

    public String getEndAllocatedFundsString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
            returnString = UTFormatter.formatPrice(lastDay.getAllocatedFunds());
        }
        return "$" + returnString;
    }

    public String getEndUnsettledFundsString() {
        String returnString = "$0.00";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatPrice(findEndUnsettledFunds());

        return returnString;
    }

    public String getEndGrandAllocatedFundsString() {
        String returnString = "$0.00";
        if (hasAccountDaily) {
            AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
            returnString = UTFormatter.formatPrice(lastDay.getGrandAllocatedFunds());
        }
        return "$" + returnString;
    }

    public String getEndGrandUnsettledFundsString() {
        String returnString = "$0.00";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatPrice(findEndGrandUnsettledFunds());

        return returnString;
    }

    public String getEndGrandCashOnHandString() {
        String returnString = "$0.00";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatPrice(findEndGrandCashOnHand());

        return returnString;
    }

    public String getStartCashOnHandString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto firstDay = accountDailyList.get(0);
            returnString = UTFormatter.formatPrice(firstDay.getCashOnHand());
        }
        return "$" + returnString;
    }

    public String getStartAllocatedFundsString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto firstDay = accountDailyList.get(0);
            returnString = UTFormatter.formatPrice(firstDay.getAllocatedFunds());
        }
        return "$" + returnString;
    }

    public String getStartUnsettledFundsString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto firstDay = accountDailyList.get(0);
            returnString = UTFormatter.formatPrice(firstDay.getUnsettledFunds());
        }
        return "$" + returnString;
    }

    public String getStartEquityMarginString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto firstDay = accountDailyList.get(0);
            returnString = UTFormatter.formatPrice(firstDay.getTotalEquityMargin());
        }
        return "$" + returnString;
    }

    public String getStartCashOnHandMarginString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto firstDay = accountDailyList.get(0);
            returnString = UTFormatter.formatPrice(firstDay.getCashOnHandMargin());
        }
        return "$" + returnString;
    }

    public String getStartAllocatedFundsMarginString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto firstDay = accountDailyList.get(0);
            returnString = UTFormatter.formatPrice(firstDay.getAllocatedFundsMargin());
        }
        return "$" + returnString;
    }

    public String getStartUnsettledFundsMarginString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto firstDay = accountDailyList.get(0);
            returnString = UTFormatter.formatPrice(firstDay.getUnsettledFundsMargin());
        }
        return "$" + returnString;
    }

    public String getStartLongPositionsTotalString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto firstDay = accountDailyList.get(0);
            returnString = UTFormatter.formatPrice(firstDay.getLongPositionsTotal());
        }
        return "$" + returnString;
    }

    public String getStartShortPositionsTotalString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto firstDay = accountDailyList.get(0);
            returnString = UTFormatter.formatPrice(firstDay.getShortPositionsTotal());
        }
        return "$" + returnString;
    }

    public String getEndEquityString() {
        String returnString = "N/A";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatPrice(findEndEquity());

        return returnString;
    }

    public String getEndEquityMarginString() {
        String returnString = "N/A";
        if (hasAccountDaily)
            returnString = UTFormatter.formatPrice(findEndEquityMargin());

        return "$" + returnString;
    }

    public String getEndNetLiquidationValueString() {
        String returnString = "$0.00";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatPrice(findEndNetLiquidationValue());

        return returnString;
    }

    public String getEndNetLiquidationValue_NoPenniesString() {
        String returnString = "$0";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatDoubleNoPennies(findEndNetLiquidationValue());

        return returnString;
    }

    public String getEndGrandEquityString() {
        String returnString = "$0.00";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatPrice(findEndGrandEquity());

        return returnString;
    }

    public String getEndGrandEquity_NoPenniesString() {
        String returnString = "$0";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatDoubleNoPennies(findEndGrandEquity());

        return returnString;
    }

    public String getStartGrandCashOnHandString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto firstDay = accountDailyList.get(0);
            returnString = UTFormatter.formatPrice(firstDay.getGrandCashOnHand());
        }
        return "$" + returnString;
    }

    public String getStartGrandAllocatedFundsString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto firstDay = accountDailyList.get(0);
            returnString = UTFormatter.formatPrice(firstDay.getGrandAllocatedFunds());
        }
        return "$" + returnString;
    }

    public String getStartGrandUnsettledFundsString() {
        String returnString = "0.00";
        if (hasAccountDaily) {
            AccountDailyDto firstDay = accountDailyList.get(0);
            returnString = UTFormatter.formatPrice(firstDay.getGrandUnsettledFunds());
        }
        return "$" + returnString;
    }

    public String getStartEquityString() {
        String returnString = "$0.00";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatPrice(findStartEquity());

        return returnString;
    }

    public String getStartGrandEquityString() {
        String returnString = "$0.00";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatPrice(findStartGrandEquity());

        return returnString;
    }

    public String getStartGrandEquity_NoPenniesString() {
        String returnString = "$0";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatDoubleNoPennies(findStartGrandEquity());

        return returnString;
    }

    public String getStartNetLiquidationValueString() {
        String returnString = "$0.00";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatPrice(findStartNetLiquidationValue());

        return returnString;
    }

    public String getStartNetLiquidationValue_NoPenniesString() {
        String returnString = "$0";
        if (hasAccountDaily)
            returnString = "$" + UTFormatter.formatDoubleNoPennies(findStartNetLiquidationValue());

        return returnString;
    }

    public String getLeverageRatioString() {
        String returnString = "$0.00";
        if (hasAccountDaily) {
            returnString = Double.toString(leverageRatio);
        }
        return returnString + "%";
    }

    // ----------------------------- LONG/SHORT SUMMARY GETTERS --------------------------------------

    public String getPercentFailedPositionsLongString() {
        String returnVal = "0";
        if (longPercentLoss != 0)
            returnVal = UTFormatter.formatPercent(longPercentLoss);

        return returnVal + "%";
    }

    public String getPercentFailedPositionsShortString() {
        String returnVal = "0";
        if (shortPercentLoss != 0)
            returnVal = UTFormatter.formatPercent(shortPercentLoss);

        return returnVal + "%";
    }

    public String getPercentPainPositionsLongString() {
        String returnVal = "0";
        if (longPercentPain != 0)
            returnVal = UTFormatter.formatPercent(longPercentPain);

        return returnVal + "%";
    }

    public String getPercentPainPositionsShortString() {
        String returnVal = "0";
        if (shortPercentPain != 0)
            returnVal = UTFormatter.formatPercent(shortPercentPain);

        return returnVal + "%";
    }

    public String getPercentRejectedPositionsLongString() {
        String returnVal = "0";
        if (longPercentRejected != 0)
            returnVal = UTFormatter.formatPercent(longPercentRejected);

        return returnVal + "%";
    }

    public String getPercentRejectedPositionsShortString() {
        String returnVal = "0";
        if (shortPercentRejected != 0)
            returnVal = UTFormatter.formatPercent(shortPercentRejected);

        return returnVal + "%";
    }

    public String getPercentConfirmRejectedPositionsLongString() {
        String returnVal = "0";
        if (longPercentConfirmRejected != 0)
            returnVal = UTFormatter.formatPercent(longPercentConfirmRejected);

        return returnVal + "%";
    }

    public String getPercentConfirmRejectedPositionsShortString() {
        String returnVal = "0";
        if (shortPercentConfirmRejected != 0)
            returnVal = UTFormatter.formatPercent(shortPercentConfirmRejected);

        return returnVal + "%";
    }

    public String getAvgSuccessGainLongString() {
        if (numLongSuccess != 0) {
            float calcPercent = calculateAvgSuccessGainLong();
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        }

        return "$0";
    }

    public String getAvgFailedLossLongString() {
        if (numLongLoss != 0) {
            float calcPercent = calculateAvgFailedLossLong();
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        }

        return "$0";
    }

    public String getAvgPainLossLongString() {
        if (numLongPain != 0) {
            float calcPercent = calculateAvgPainLossLong();
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        }

        return "$0";
    }

    public String getAvgRejectedLossLongString() {
        if (numLongRejected != 0) {
            float calcPercent = calculateAvgRejectedLossLong();
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        }

        return "$0";
    }

    public String getAvgConfirmRejectedLossLongString() {
        if (numLongConfirmRejected != 0) {
            float calcPercent = calculateAvgConfirmRejectedLossLong();
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        }

        return "$0";
    }

    public String getAvgSuccessGainShortString() {
        if (numShortSuccess != 0) {
            float calcPercent = calculateAvgSuccessGainShort();
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        }

        return "$0";
    }

    public String getAvgFailedLossShortString() {
        if (numShortLoss != 0) {
            float calcPercent = calculateAvgFailedLossShort();
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        }
        return "$0";
    }

    public String getAvgPainLossShortString() {
        if (numShortPain != 0) {
            float calcPercent = calculateAvgPainLossShort();
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        }
        return "$0";
    }

    public String getAvgRejectedLossShortString() {
        if (numShortRejected != 0) {
            float calcPercent = calculateAvgRejectedLossShort();
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        }
        return "$0";
    }

    public String getAvgConfirmRejectedLossShortString() {
        if (numShortConfirmRejected != 0) {
            float calcPercent = calculateAvgConfirmRejectedLossShort();
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        }
        return "$0";
    }

    public String getHistoryProcessStartTimeString() {
        return UTDatetime.toDbString(historyProcessStartTime);
    }

    public String getHistoryProcessEndTimeString() {
        return UTDatetime.toDbString(historyProcessEndTime);
    }

    public String getPercentSuccessPositionsLongString0() {
        String returnVal = "0";
        if (longPercentSuccess != 0)
            returnVal = UTFormatter.formatPercent(longPercentSuccess);

        return returnVal + "%";
    }

    public String getPercentSuccessPositionsShortString() {
        String returnVal = "0";
        if (shortPercentSuccess != 0)
            returnVal = UTFormatter.formatPercent(shortPercentSuccess);

        return returnVal + "%";
    }

    // ------------------------------------------------------ DATES ------------------------------------------------------
    public String getStartAndEndDates() {
        if (hasAccountDaily) {
            AccountDailyDto firstDay = null;
            if (accountDailyList.size() == 1)
                firstDay = accountDailyList.get(0); // Get the second record, the first one is our open balance record(yesterday)
            else
                firstDay = accountDailyList.get(1); // Get the second record, the first one is our open balance record(yesterday)

            AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);

            return firstDay.getThisDateString() + " - " + lastDay.getThisDateString();
        } else
            return "Unknown";
    }

    public String getStartDate() {
        String returnString = "N/A";
        if (hasAccountDaily) {
            AccountDailyDto firstDay = null;
            if (accountDailyList.size() == 1)
                firstDay = accountDailyList.get(0);
            else
                firstDay = accountDailyList.get(1); // Get the second record, the first one is our open balance record(yesterday)

            returnString = "( " + UTDatetime.toDateOnlyString(firstDay.getThisDate()) + " )";
        }
        return returnString;
    }

    public String getEndDate() {
        String returnString = "N/A";
        if (hasAccountDaily) {
            AccountDailyDto lastDay = accountDailyList.get(accountDailyList.size() - 1);
            returnString = "( " + UTDatetime.toDateOnlyString(lastDay.getThisDate()) + " )";
        }
        return returnString;
    }

    // ------------------------------------------------------ DROPCAT ------------------------------------------------------
    public String getDropcatAvgFailedLoss() {
        if (dropcatNumFailedPositions != 0) {
            float calcPercent = dropcatTotalFailedLossAmount / dropcatNumFailedPositions;
            return UTFormatter.formatPercent3Digit(calcPercent) + "%";
        }

        return "0";
    }

    public String getDropcatAvgSuccessGain() {
        if (dropcatNumSuccessPositions != 0) {
            float calcPercent = dropcatTotalSuccessGainAmount / dropcatNumSuccessPositions;
            return UTFormatter.formatPercent3Digit(calcPercent) + "%";
        }

        return "0";
    }

    public String getDropcatNumPositions() {
        return Integer.toString(dropcatNumFailedPositions + dropcatNumSuccessPositions);
    }

}
