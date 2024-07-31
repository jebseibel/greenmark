package com.greenmark.common.dto.scenario.database.decorator;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.greenmark.common.GmConstants;
import com.greenmark.common.dto.parameters.AccountBalanceParams;
import com.greenmark.common.dto.scenario.decorator.ScenarioDtoDecorator;
import com.greenmark.utils.UTFormatter;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ScenarioDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its AccountDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ScenarioDecorator extends ScenarioCalculator implements Serializable {
    public static final String CLASSNAME = "ScenarioDecorator";
    private static final long serialVersionUID = 1L;

    public ScenarioDecorator() {
    }

    public ScenarioDecorator(ScenarioDtoDecorator currentScenario, AccountBalanceParams accountBalance, int currentScenarioNumber) {
        super(currentScenario, accountBalance, currentScenarioNumber);
    }

    public ScenarioDecorator(String xmldata) {
        super(xmldata);
    }

    public ScenarioDecorator(ScenarioDbDecorator inScenario) {
        super();

        try {
            if (inScenario != null)
                BeanUtils.copyProperties(this, inScenario);
        } catch (InvocationTargetException ite) {
            System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage() + ", cause: " + ite.getCause().toString());
        } catch (IllegalAccessException iae) {
            System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
        }
    }

    public String getStartEndDatetimeParamString() {
        if ((queryStartDatetime == null) || (queryEndDatetime == null))
            return "";
        else
            return "&startDate=" + queryStartDatetime + "&endDate=" + queryEndDatetime;
    }

    public String getLeverageRatioString() {
        String returnString = "N/A";
        if (hasAccountDaily) {
            returnString = Double.toString(leverageRatio);
        }
        return returnString + "%";
    }

    public String getYearlyROIString() {
        String returnVal = "N/A ";
        float calcRoi = getYearlyROI();
        if (Float.isInfinite(calcRoi))
            return "1 DAY";

        if (calcRoi != 0)
            returnVal = UTFormatter.formatPercent(calcRoi);
        return returnVal + "%";
    }

    public String getMonthlyROIString() {
        String returnVal = "N/A";
        float calcRoi = getMonthlyROI();
        if (Float.isInfinite(calcRoi))
            return "1 DAY";

        if (calcRoi != 0)
            returnVal = UTFormatter.formatPercent(calcRoi);
        return returnVal + "%";
    }

    public String getLongYearlyROIString() {
        String returnVal = "N/A ";
        float calcRoi = getLongYearlyROI();
        if (Float.isInfinite(calcRoi))
            return "1 DAY";

        if (calcRoi != 0)
            returnVal = UTFormatter.formatPercent(calcRoi);
        return returnVal + "%";
    }

    public String getLongMonthlyROIString() {
        String returnVal = "N/A";
        float calcRoi = getLongMonthlyROI();
        if (Float.isInfinite(calcRoi))
            return "1 DAY";

        if (calcRoi != 0)
            returnVal = UTFormatter.formatPercent(calcRoi);
        return returnVal + "%";
    }

    public String getShortYearlyROIString() {
        String returnVal = "N/A ";
        float calcRoi = getShortYearlyROI();
        if (Float.isInfinite(calcRoi))
            return "1 DAY";

        if (calcRoi != 0)
            returnVal = UTFormatter.formatPercent(calcRoi);
        return returnVal + "%";
    }

    public String getShortMonthlyROIString() {
        String returnVal = "N/A";
        float calcRoi = getShortMonthlyROI();
        if (Float.isInfinite(calcRoi))
            return "1 DAY";

        if (calcRoi != 0)
            returnVal = UTFormatter.formatPercent(calcRoi);
        return returnVal + "%";
    }

    public String getYearlyPercentGrowth() {
        String returnVal = "N/A";
        float calcPercent = calcPercentGrowth(GmConstants.AVG_NUM_CALENDAR_DAYS_PER_YEAR);
        if (calcPercent != 0)
            returnVal = UTFormatter.formatPercent(calcPercent);
        return returnVal + "%";
    }

    public String getMonthlyPercentGrowth() {
        String returnVal = "N/A";
        float calcPercent = calcPercentGrowth(GmConstants.AVG_NUM_CALENDAR_DAYS_PER_MONTH);
        if (calcPercent != 0)
            returnVal = UTFormatter.formatPercent(calcPercent);
        return returnVal + "%";
    }

    public String getAvgConfirmRejectedLossLong() {
        float calcPercent = getAvgConfirmRejectedLossLong_f();
        if (calcPercent != 0)
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        return "$0.00";
    }

    // ------------------------------------------- PERCENT VALUES (TOTALS) ---------------------------------------------------
    public String getPercentFailedPositionsString() {
        if (percentFailedPositions != 0)
            return UTFormatter.formatPercent(percentFailedPositions) + "%";
        return "0%";
    }

    public String getPercentSuccessPositionsString() {
        if (percentSuccessPositions != 0)
            return UTFormatter.formatPercent(percentSuccessPositions) + "%";
        return "0%";
    }

    public String getPercentPainPositionsString() {
        if (percentPainPositions != 0)
            return UTFormatter.formatPercent(percentPainPositions) + "%";
        return "0%";
    }

    public String getPercentRejectedPositionsString() {
        if (percentRejectedPositions != 0)
            return UTFormatter.formatPercent(percentRejectedPositions) + "%";
        return "0%";
    }

    public String getPercentConfirmRejectedPositionsString() {
        if (percentConfirmRejectedPositions != 0)
            return UTFormatter.formatPercent(percentConfirmRejectedPositions) + "%";
        return "0%";
    }

    // ------------------------------------------- AVG. GAIN/LOSS VALUES (TOTALS) ---------------------------------------------------
    public String getAvgSuccessGain() {
        float calcPercent = totalSuccessAvgGain;
        if (calcPercent != 0)
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        return "$0.00";
    }

    public String getAvgFailedLoss() {
        float calcPercent = getAvgFailedLoss_f();
        if (calcPercent != 0)
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        return "$0.00";
    }

    public String getAvgPainLoss() {
        float calcPercent = getAvgPainLoss_f();
        if (calcPercent != 0)
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        return "$0.00";
    }

    public String getAvgRejectedLoss() {
        float calcPercent = getAvgRejectedLoss_f();
        if (calcPercent != 0)
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        return "$0.00";
    }

    public String getAvgConfirmRejectedLoss() {
        float calcPercent = getAvgConfirmRejectedLoss_f();
        if (calcPercent != 0)
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        return "$0.00";
    }

    // -------------------------------------------------------------- PERCENTAGE VALUES (LONG AND SHORT) ----------------------------------------------------------------

    public String getPercentSuccessPositionsLong() {
        if (getPercentSuccessPositionsLong_f() != 0)
            return UTFormatter.formatPercent(getPercentSuccessPositionsLong_f()) + "%";
        return "0%";
    }

    public String getPercentSuccessPositionsShort() {
        if (getPercentSuccessPositionsShort_f() != 0)
            return UTFormatter.formatPercent(getPercentSuccessPositionsShort_f()) + "%";
        return "0%";
    }

    public String getPercentFailedPositionsLong() {
        if (getPercentFailedPositionsLong_f() != 0)
            return UTFormatter.formatPercent(getPercentFailedPositionsLong_f()) + "%";
        return "0%";
    }

    public String getPercentPainPositionsLong() {
        if (getPercentPainPositionsLong_f() != 0) {
            return UTFormatter.formatPercent(getPercentPainPositionsLong_f()) + "%";
        }
        return "0%";
    }

    public String getPercentPainPositionsShort() {
        if (getPercentPainPositionsShort_f() != 0)
            return UTFormatter.formatPercent(getPercentPainPositionsShort_f()) + "%";
        return "0%";
    }

    public String getPercentRejectedPositionsLong() {
        if (getPercentRejectedPositionsLong_f() != 0)
            return UTFormatter.formatPercent(getPercentRejectedPositionsLong_f()) + "%";
        return "0%";
    }

    public String getPercentRejectedPositionsShort() {
        if (getPercentRejectedPositionsShort_f() != 0)
            return UTFormatter.formatPercent(getPercentRejectedPositionsShort_f()) + "%";
        return "0%";
    }

    public String getPercentConfirmRejectedPositionsLong() {
        if (getPercentConfirmRejectedPositionsLong_f() != 0)
            return UTFormatter.formatPercent(getPercentConfirmRejectedPositionsLong_f()) + "%";
        return "0%";
    }

    public String getPercentConfirmRejectedPositionsShort() {
        if (getPercentConfirmRejectedPositionsShort_f() != 0)
            return UTFormatter.formatPercent(getPercentConfirmRejectedPositionsShort_f()) + "%";
        return "0%";
    }

    // ------------------------------------------- AVG. GAIN/LOSS VALUES (LONG AND SHORT) ---------------------------------------------------
    public String getAvgSuccessGainLong() {
        float calcPercent = getAvgSuccessGainLong_f();
        if (calcPercent != 0)
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        return "$0.00";
    }

    public String getAvgFailedLossLong() {
        float calcPercent = getAvgFailedLossLong_f();
        if (calcPercent != 0)
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        return "$0.00";
    }

    public String getAvgPainLossLong() {
        float calcPercent = getAvgPainLossLong_f();
        if (calcPercent != 0)
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        return "$0.00";
    }

    public String getAvgRejectedLossLong() {
        float calcPercent = getAvgRejectedLossLong_f();
        if (calcPercent != 0)
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        return "$0.00";
    }

    public String getAvgSuccessGainShort() {
        float calcPercent = getAvgSuccessGainShort_f();
        if (calcPercent != 0)
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        return "$0.00";
    }

    public String getAvgFailedLossShort() {
        float calcPercent = getAvgFailedLossShort_f();
        if (calcPercent != 0)
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        return "$0.00";
    }

    public String getAvgPainLossShort() {
        float calcPercent = getAvgPainLossShort_f();
        if (calcPercent != 0)
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        return "$0.00";
    }

    public String getAvgRejectedLossShort() {
        float calcPercent = getAvgRejectedLossShort_f();
        if (calcPercent != 0)
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        return "$0.00";
    }

    public String getAvgConfirmRejectedLossShort() {
        float calcPercent = getAvgConfirmRejectedLossShort_f();
        if (calcPercent != 0)
            return "$" + UTFormatter.formatPercent3Digit(calcPercent);
        return "$0.00";
    }

    ///////////////////////////////////////////////////////////////
    // CONVENIENCE METHODS FOR CASH AND MARGIN ACCOUNTS
    ///////////////////////////////////////////////////////////////
    public String getTotalProfitAndLossString() {
        String returnString = UTFormatter.formatPrice(totalProfitAndLoss);

        return returnString;
    }

    public String getLongProfitAndLossString() {
        String returnString = UTFormatter.formatPrice(longProfitAndLoss);
        return returnString;
    }

    public String getShortProfitAndLossString() {
        String returnString = UTFormatter.formatPrice(shortProfitAndLoss);
        return returnString;
    }

    // DROP CAT STATISTICS
    public String getDropcatAvgSuccessGainLong() {
        String returnVal = "0";
        if ((dropcatTotalSuccessAmountLong == 0) || (dropcatNumSuccessPositionsLong == 0))
            return "0%";

        float calcPercent = dropcatTotalSuccessAmountLong / dropcatNumSuccessPositionsLong;
        if (calcPercent != 0)
            returnVal = UTFormatter.formatPercent3Digit(calcPercent);
        return returnVal + "%";
    }

    public String getDropcatAvgFailedLossLong() {
        String returnVal = "0";
        if ((dropcatTotalFailedAmountLong == 0) || (dropcatNumFailedPositionsLong == 0))
            return "0%";

        float calcPercent = dropcatTotalFailedAmountLong / dropcatNumFailedPositionsLong;
        if (calcPercent != 0)
            returnVal = UTFormatter.formatPercent3Digit(calcPercent);
        return returnVal + "%";
    }

    public String getDropcatAvgSuccessGainShort() {
        String returnVal = "0";
        if ((dropcatTotalSuccessAmountShort == 0) || (dropcatNumSuccessPositionsShort == 0))
            return "0%";

        float calcPercent = dropcatTotalSuccessAmountShort / dropcatNumSuccessPositionsShort;
        if (calcPercent != 0)
            returnVal = UTFormatter.formatPercent3Digit(calcPercent);
        return returnVal + "%";
    }

    public String getDropcatAvgFailedLossShort() {
        String returnVal = "0";
        if ((dropcatTotalFailedAmountShort == 0) || (dropcatNumFailedPositionsShort == 0))
            return "0%";

        float calcPercent = dropcatTotalFailedAmountShort / dropcatNumFailedPositionsShort;
        if (calcPercent != 0)
            returnVal = UTFormatter.formatPercent3Digit(calcPercent);
        return returnVal + "%";
    }

    public String getDropcatPercentSuccessLong() {
        String returnVal = "0";

        if ((dropcatNumSuccessPositionsLong == 0) || (dropcatTotalNumPositionsLong == 0))
            return "0%";

        returnVal = UTFormatter.formatPercent((dropcatNumSuccessPositionsLong / (float) dropcatTotalNumPositionsLong) * 100F);
        return returnVal + "%";
    }

    public String getDropcatPercentSuccessShort() {
        String returnVal = "0";

        if ((dropcatNumSuccessPositionsShort == 0) || (dropcatTotalNumPositionsShort == 0))
            return "0%";

        returnVal = UTFormatter.formatPercent((dropcatNumSuccessPositionsShort / (float) dropcatTotalNumPositionsShort) * 100F);
        return returnVal + "%";
    }
}
