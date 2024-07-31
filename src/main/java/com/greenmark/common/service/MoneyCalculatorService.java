package com.greenmark.common.service;

import com.greenmark.common.GmConstantsAccount;
import com.greenmark.common.dto.account.timeperiod.AccountDailyDto;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.util.List;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: MoneyCalculatorService</p>
 * <p>Description: Contains utility methods for calculating ROIs and other money calculations.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
@Slf4j
public class MoneyCalculatorService {
    private static final String CLASSNAME = "MoneyCalculatorService";

    // Only the Account object uses numPeriods since it can have multiple scenarios and we need to know the total days for all scenarios.
    // The Scenario Object sends 0, which indicates this method is working for a scenario.
    // We also pass in 0 if we're calculating monthly ROIs
    public static float calcROI(List accountDailyList, int accountTypeIn, int numDaysPerPeriod, int numPeriods, double totalProfitLoss) {
        String methodname = "calcROI";

        int accountType = accountTypeIn;
        if (accountTypeIn == 0)
            accountType = GmConstantsAccount.ACCOUNT_TYPE_STANDARD;

        float returnVal = 0;

        if ((accountDailyList != null) && (accountDailyList.size() > 1)) {
            AccountDailyDto openBalancesDaily = (AccountDailyDto) accountDailyList.get(0); // This record is the previous day's open.
            int numCalendarDays = numPeriods;
            double numYearsDecimal = (double) numCalendarDays / numDaysPerPeriod;

            NumberFormat formatter = NumberFormat.getCurrencyInstance();

            // if (trace.isLevelAnalysis()) {
            // log.info(CLASSNAME + "." + methodname + ", numCalendarDays: " + numCalendarDays + ", numYearsDecimal: " + numYearsDecimal + "openBalancesDaily.totalEquity: "
            // + formatter.format(totalEquityCash));
            // }

            // Get the total growth from both the short and long positions
            double equityDifference = 0;
            double percentOfOriginalEquity = 0;
            if (accountType == GmConstantsAccount.ACCOUNT_TYPE_STANDARD) {
                equityDifference = totalProfitLoss;
                percentOfOriginalEquity = equityDifference / (openBalancesDaily.getCashOnHand());

                log.info(CLASSNAME + "." + methodname + ", STANDARD: equityDifference: " + formatter.format(equityDifference) + ", ROI: " + percentOfOriginalEquity);

            } else {
                equityDifference = totalProfitLoss;
                percentOfOriginalEquity = equityDifference / openBalancesDaily.getNetLiquidationValue();

                log.info(CLASSNAME + "." + methodname + ", REG_T_MARGIN: equityDifference: " + formatter.format(equityDifference) + ", percentOfOriginalEquity: " + percentOfOriginalEquity);
            }

            // System.out.println("(percentOfOriginalEquity / numYearsDecimal): " + (percentOfOriginalEquity / numYearsDecimal));
            // Now divide by the fractional num years. Multiply by 100 to turn into a display percentage and not fractional percentage.
            returnVal = (float) (percentOfOriginalEquity / numYearsDecimal) * 100;
        }
        return returnVal;
    }
}
