package com.greenmark.common.dto.scenario.database.decorator;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.greenmark.common.dto.parameters.AccountBalanceParams;
import com.greenmark.common.dto.scenario.database.ScenarioCalculatorDto;
import com.greenmark.common.dto.scenario.decorator.ScenarioDtoDecorator;
import com.greenmark.utils.UTFormatter;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ScenarioCalculatorDtoDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its AccountDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ScenarioCalculatorDtoDecorator extends ScenarioCalculatorDto implements Serializable {
    public static final String CLASSNAME = "ScenarioCalculatorDtoDecorator";
    private static final long serialVersionUID = 1L;

    public ScenarioCalculatorDtoDecorator() {
    }

    public ScenarioCalculatorDtoDecorator(ScenarioDtoDecorator currentScenario, AccountBalanceParams accountBalance, int currentScenarioNumber) {
        super(currentScenario, accountBalance, currentScenarioNumber);
    }

    public ScenarioCalculatorDtoDecorator(String xmldata) {
        super(xmldata);
    }

    public ScenarioCalculatorDtoDecorator(ScenarioDbDecorator inScenario) {
        try {
            if (inScenario != null)
                BeanUtils.copyProperties(this, inScenario);
        } catch (InvocationTargetException ite) {
            System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage());
        } catch (IllegalAccessException iae) {
            System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
        }
    }

    public float getYearlyROI() {
        return calcRoiYearly;
    }

    public float getMonthlyROI() {
        return calcRoiMonthly;
    }

    public float getLongYearlyROI() {
        return longCalcRoiYearly;
    }

    public float getLongMonthlyROI() {
        return longCalcRoiMonthly;
    }

    public float getShortYearlyROI() {
        return shortCalcRoiYearly;
    }

    public float getShortMonthlyROI() {
        return shortCalcRoiMonthly;
    }

    public int getTotalNumPositions() {
        return totalNumPositionsLong + totalNumPositionsShort;
    }

    public float getAvgFailedLoss_f() {
        if ((numFailedPositionsLong + numFailedPositionsShort == 0))
            return 0F;
        return (totalFailedAmountLong + totalFailedAmountShort) / (numFailedPositionsLong + numFailedPositionsShort);
    }

    public float getAvgPainLoss_f() {
        if ((numPainPositionsLong + numPainPositionsShort == 0))
            return 0F;
        return (totalPainAmountLong + totalPainAmountShort) / (numPainPositionsLong + numPainPositionsShort);
    }

    public float getAvgRejectedLoss_f() {
        if ((numRejectedPositionsLong + numRejectedPositionsShort == 0))
            return 0F;
        return (totalRejectedAmountLong + totalRejectedAmountShort) / (numRejectedPositionsLong + numRejectedPositionsShort);
    }

    public float getAvgConfirmRejectedLoss_f() {
        if ((numConfirmRejectedPositionsLong + numConfirmRejectedPositionsShort == 0))
            return 0F;
        return (totalConfirmRejectedAmountLong + totalConfirmRejectedAmountShort) / (numConfirmRejectedPositionsLong + numConfirmRejectedPositionsShort);
    }

    public float getPercentSuccessPositionsLong_f() {
        if ((numSuccessPositionsLong == 0) || (totalNumPositionsLong == 0))
            return 0F;
        return (numSuccessPositionsLong / (float) totalNumPositionsLong) * 100F;
    }

    public float getPercentSuccessPositionsShort_f() {
        if ((numSuccessPositionsShort == 0) || (totalNumPositionsShort == 0))
            return 0F;
        return (numSuccessPositionsShort / (float) totalNumPositionsShort) * 100F;
    }

    public float getPercentFailedPositionsLong_f() {
        if ((numFailedPositionsLong == 0) || (totalNumPositionsLong == 0))
            return 0F;
        return (numFailedPositionsLong / (float) totalNumPositionsLong) * 100F;
    }

    public float getPercentFailedPositionsShort_f() {
        if ((numFailedPositionsShort == 0) || (totalNumPositionsShort == 0))
            return 0F;
        return (numFailedPositionsShort / (float) totalNumPositionsShort) * 100F;
    }

    public float getPercentPainPositionsLong_f() {
        if ((numPainPositionsLong == 0) || (totalNumPositionsLong == 0))
            return 0F;
        return ((float) numPainPositionsLong / (float) totalNumPositionsLong) * 100F;
    }

    public float getPercentPainPositionsShort_f() {
        if ((numPainPositionsShort == 0) || (totalNumPositionsShort == 0))
            return 0F;
        return ((float) numPainPositionsShort / (float) totalNumPositionsShort) * 100F;
    }

    public float getPercentRejectedPositionsLong_f() {
        if ((numRejectedPositionsLong == 0) || (totalNumPositionsLong == 0))
            return 0F;
        return ((float) numRejectedPositionsLong / (float) totalNumPositionsLong) * 100F;
    }

    public String getPercentFailedPositionsShort() {
        if (getPercentFailedPositionsShort_f() != 0)
            return UTFormatter.formatPercent(getPercentFailedPositionsShort_f()) + "%";
        return "0%";
    }

    public float getPercentRejectedPositionsShort_f() {
        if ((numRejectedPositionsShort == 0) || (totalNumPositionsShort == 0))
            return 0F;
        return ((float) numRejectedPositionsShort / (float) totalNumPositionsShort) * 100F;
    }

    public float getPercentConfirmRejectedPositionsLong_f() {
        if ((numConfirmRejectedPositionsLong == 0) || (totalNumPositionsLong == 0))
            return 0F;
        return ((float) numConfirmRejectedPositionsLong / (float) totalNumPositionsLong) * 100F;
    }

    public float getPercentConfirmRejectedPositionsShort_f() {
        if ((numConfirmRejectedPositionsShort == 0) || (totalNumPositionsShort == 0))
            return 0F;
        return ((float) numConfirmRejectedPositionsShort / (float) totalNumPositionsShort) * 100F;
    }

    public float getAvgRejectedLossLong_f() {
        if ((totalRejectedAmountLong == 0) || (numRejectedPositionsLong == 0))
            return 0F;
        return totalRejectedAmountLong / numRejectedPositionsLong;
    }

    public float getAvgConfirmRejectedLossLong_f() {
        if ((totalConfirmRejectedAmountLong == 0) || (numConfirmRejectedPositionsLong == 0))
            return 0F;
        return totalConfirmRejectedAmountLong / numConfirmRejectedPositionsLong;
    }

    public float getAvgSuccessGainShort_f() {
        if ((totalSuccessAmountShort == 0) || (numSuccessPositionsShort == 0))
            return 0F;
        return totalSuccessAmountShort / numSuccessPositionsShort;
    }

    public float getAvgFailedLossShort_f() {
        if ((totalFailedAmountShort == 0) || (numFailedPositionsShort == 0))
            return 0F;
        return totalFailedAmountShort / numFailedPositionsShort;
    }

    public float getAvgPainLossShort_f() {
        if ((totalPainAmountShort == 0) || (numPainPositionsShort == 0))
            return 0F;
        return totalPainAmountShort / (float) numPainPositionsShort;
    }

    public float getAvgConfirmRejectedLossShort_f() {
        if ((totalConfirmRejectedAmountShort == 0) || (numConfirmRejectedPositionsShort == 0))
            return 0F;
        return totalConfirmRejectedAmountShort / (float) numConfirmRejectedPositionsShort;
    }

    public float getAvgRejectedLossShort_f() {
        if ((totalRejectedAmountShort == 0) || (numRejectedPositionsShort == 0))
            return 0F;
        return totalRejectedAmountShort / (float) numRejectedPositionsShort;
    }

    public float getAvgSuccessGainLong_f() {
        if ((totalSuccessAmountLong == 0) || (numSuccessPositionsLong == 0))
            return 0F;
        return totalSuccessAmountLong / numSuccessPositionsLong;
    }

    public float getAvgFailedLossLong_f() {
        if ((totalFailedAmountLong == 0) || (numFailedPositionsLong == 0))
            return 0F;
        return totalFailedAmountLong / numFailedPositionsLong;
    }

    public float getAvgPainLossLong_f() {
        if ((totalPainAmountLong == 0) || (numPainPositionsLong == 0))
            return 0F;
        return totalPainAmountLong / numPainPositionsLong;
    }
}
