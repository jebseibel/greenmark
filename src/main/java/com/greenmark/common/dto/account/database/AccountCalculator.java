package com.greenmark.common.dto.account.database;

import com.greenmark.common.GmConstants;
import com.greenmark.common.dto.account.timeperiod.AccountDailyDto;
import com.greenmark.common.dto.scenario.database.decorator.ScenarioDecorator;
import com.greenmark.common.service.MoneyCalculatorService;

import java.io.Serializable;
import java.time.ZoneOffset;
import java.util.Iterator;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountCalculator</p>
 * <p>Description: This decorator class is labeled a Calculator because it extends its base class with methods that calculate our account statistics.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class AccountCalculator extends AccountCalculatorDtoDecorator implements Serializable {
    public static final String CLASSNAME = "AccountCalculator";
    private static final long serialVersionUID = 1L;

    public AccountCalculator() {
        super();
    }

//	public AccountCalculator(CreateAccountDto createAccountData) {
//		super(createAccountData);
//	}
//
//	public AccountCalculator(String xmldata) {
//		super(xmldata, trace);
//	}

//	public void calculateAllStatistics(List<PositionDbDecorator> positionList) {
//		calculatePercentSuccessPositions(positionList);
//
//		calculateYearlyROI();
//		calculateMonthlyROI();
//
//		calculateYearlyPercentGrowth();
//		calculateMonthlyPercentGrowth();
//	}

    public void calculateAccountStatistics() {
        double netLiquidTotal = 0;
        int numDays = 0, dayIndex = 0;

        if ((accountDailyList != null) && (accountDailyList.size() > 1)) {
            // Estimate the number of calendar days since the first Account Daily item and the last
            AccountDailyDto openBalancesDaily = accountDailyList.get(0); // This record is the previous day's open.
            AccountDailyDto firstDaily = null;
            if (accountDailyList.size() == 1)
                firstDaily = accountDailyList.get(0); // Get the second record, the first one is our open balance record(yesterday)
            else {
                firstDaily = accountDailyList.get(1); // But this is the first date of user search.
                dayIndex = 1;
            }
            AccountDailyDto lastDaily = accountDailyList.get(accountDailyList.size() - 1);

            for (int i = dayIndex; i < accountDailyList.size(); i++) {
                AccountDailyDto dailyRecord = accountDailyList.get(i);

                double divisor = dailyRecord.getNetLiquidationValue();
                if (!isAccountTypeRegT())
                    divisor = dailyRecord.getGrandTotalEquity() / 2.0;
                leverageRatio += (dailyRecord.getLongPositionsTotal() + dailyRecord.getShortPositionsTotal()) / divisor;
                numDays++;
            }

            leverageRatio = leverageRatio / numDays;
        }
    }

    public float calcPercentGrowth(double numDaysPerPeriod) {
        float returnVal = 0;

        if ((accountDailyList != null) && (accountDailyList.size() > 1)) {
            // Estimate the number of calendar days since the first Account Daily item and the last
            AccountDailyDto openBalancesDaily = accountDailyList.get(0); // This record is the previous day's open.
            AccountDailyDto firstDaily = null;
            if (accountDailyList.size() == 1)
                firstDaily = accountDailyList.get(0);
            else
                firstDaily = accountDailyList.get(1); // But this is the first date of user search.
            AccountDailyDto lastDaily = accountDailyList.get(accountDailyList.size() - 1);

            // Don't include the open balance record when determining how many days this calculation is for.
            long lastEpochSecond = lastDaily.getThisDate().atZone(ZoneOffset.UTC).toEpochSecond();
            long firstEpochSecond = firstDaily.getThisDate().atZone(ZoneOffset.UTC).toEpochSecond();

            long millisecondDifference = (lastEpochSecond * 1000) - (firstEpochSecond * 1000);
            long numCalendarDays = millisecondDifference / GmConstants.NUM_MILLISECONDS_IN_DAY;
            double numYearsDecimal = (double) numCalendarDays / numDaysPerPeriod;

            // Get the total growth from both the short and long positions
            double equityDifference = 0;
            double percentOfOriginalEquity = 0;

            equityDifference = lastDaily.getGrandTotalEquity() - openBalancesDaily.getGrandTotalEquity();
            percentOfOriginalEquity = equityDifference / openBalancesDaily.getGrandTotalEquity();

            // Now divide by the fractional num years. Multiply by 100 to turn into a display percentage and not fractional percentage.
            returnVal = (float) (percentOfOriginalEquity / numYearsDecimal) * 100;
        }
        return returnVal;
    }

    public float calculateYearlyROI() {
        int numDaysForAccount = 0;
        double totalProfitLoss = 0F;
        double longTotalProfitLoss = 0F;
        double shortTotalProfitLoss = 0F;

        if ((scenarioList != null) && (scenarioList.size() > 0)) {
            Iterator scenarioListIter = scenarioList.iterator();
            while (scenarioListIter.hasNext()) {
                ScenarioDecorator thisScenario = (ScenarioDecorator) scenarioListIter.next();
                numDaysForAccount += thisScenario.getNumDaysInScenario();

                totalProfitLoss += thisScenario.getTotalProfitAndLoss();
                longTotalProfitLoss += thisScenario.getLongProfitAndLoss();
                shortTotalProfitLoss += thisScenario.getShortProfitAndLoss();
            }
        }

        yearlyRoi = MoneyCalculatorService.calcROI(accountDailyList, accountType, GmConstants.AVG_NUM_CALENDAR_DAYS_PER_YEAR, numDaysForAccount, totalProfitLoss);
        longYearlyRoi = MoneyCalculatorService.calcROI(accountDailyList, accountType, GmConstants.AVG_NUM_CALENDAR_DAYS_PER_YEAR, numDaysForAccount, longTotalProfitLoss);
        shortYearlyRoi = MoneyCalculatorService.calcROI(accountDailyList, accountType, GmConstants.AVG_NUM_CALENDAR_DAYS_PER_YEAR, numDaysForAccount, shortTotalProfitLoss);
        return yearlyRoi;
    }

    public float calculateMonthlyROI() {
        int numDaysForAccount = 0;
        double totalProfitLoss = 0F;
        double longTotalProfitLoss = 0F;
        double shortTotalProfitLoss = 0F;

        if ((scenarioList != null) && (scenarioList.size() > 0)) {
            Iterator scenarioListIter = scenarioList.iterator();
            while (scenarioListIter.hasNext()) {
                ScenarioDecorator thisScenario = (ScenarioDecorator) scenarioListIter.next();
                numDaysForAccount += thisScenario.getNumDaysInScenario();

                totalProfitLoss += thisScenario.getTotalProfitAndLoss();
                longTotalProfitLoss += thisScenario.getLongProfitAndLoss();
                shortTotalProfitLoss += thisScenario.getShortProfitAndLoss();
            }
        }

        monthlyRoi = MoneyCalculatorService.calcROI(accountDailyList, accountType, GmConstants.AVG_NUM_CALENDAR_DAYS_PER_MONTH, numDaysForAccount, totalProfitLoss);
        longMonthlyRoi = MoneyCalculatorService.calcROI(accountDailyList, accountType, GmConstants.AVG_NUM_CALENDAR_DAYS_PER_MONTH, numDaysForAccount, longTotalProfitLoss);
        shortMonthlyRoi = MoneyCalculatorService.calcROI(accountDailyList, accountType, GmConstants.AVG_NUM_CALENDAR_DAYS_PER_MONTH, numDaysForAccount, shortTotalProfitLoss);
        return monthlyRoi;
    }

    public float calculateMonthlyPercentGrowth() {
        calcPercentGrowthMonthly = calcPercentGrowth(GmConstants.AVG_NUM_CALENDAR_DAYS_PER_MONTH);
        return calcPercentGrowthMonthly;
    }

    public float calculateYearlyPercentGrowth() {
        calcPercentGrowthYearly = calcPercentGrowth(GmConstants.AVG_NUM_CALENDAR_DAYS_PER_YEAR);
        return calcPercentGrowthYearly;
    }

//	public boolean calculatePercentSuccessPositions(List<PositionDbDecorator> positionList) {
//		// TOTAL
//		numSuccessPositions = 0;
//		successPositionsPercent = 0.0F;
//		successTotalAmount = 0.0F;
//		successAvgGain = 0.0F;
//
//		numLossPositions = 0;
//		lossPositionsPercent = 0.0F;
//		lossTotalAmount = 0.0F;
//		lossAvgLoss = 0.0F;
//
//		numPainPositions = 0;
//		painPositionsPercent = 0.0F;
//		painTotalAmount = 0.0F;
//		painAvgLoss = 0.0F;
//
//		numRejectedPositions = 0;
//		rejectedPositionsPercent = 0.0F;
//		rejectedTotalAmount = 0.0F;
//		rejectedAvgLoss = 0.0F;
//
//		numConfirmRejectedPositions = 0;
//		confirmRejectedPositionsPercent = 0.0F;
//		confirmRejectedTotalAmount = 0.0F;
//
//		// LONG
//		numLongSuccess = 0;
//		longSuccessAvgGain = 0;
//		longPercentSuccess = 0;
//
//		numLongLoss = 0;
//		longLossAvgLoss = 0;
//		longPercentLoss = 0;
//
//		numLongPain = 0;
//		longPainAvgLoss = 0.0F;
//		longPercentPain = 0;
//
//		numLongRejected = 0;
//		longRejectedAvgLoss = 0.0F;
//		longPercentRejected = 0;
//
//		numLongConfirmRejected = 0;
//		longConfirmRejectedAvgLoss = 0.0F;
//		longPercentConfirmRejected = 0;
//
//		// SHORT
//		numShortSuccess = 0;
//		shortSuccessAvgGain = 0;
//		shortPercentSuccess = 0;
//
//		numShortLoss = 0;
//		shortLossAvgLoss = 0;
//		shortPercentLoss = 0;
//
//		numShortPain = 0;
//		shortPainAvgLoss = 0.0F;
//		shortPercentPain = 0;
//
//		numShortRejected = 0;
//		shortRejectedAvgLoss = 0.0F;
//		shortPercentRejected = 0;
//
//		numShortConfirmRejected = 0;
//		shortConfirmRejectedAvgLoss = 0.0F;
//		shortPercentConfirmRejected = 0;
//
//		dropcatNumSuccessPositions = 0;
//		dropcatNumFailedPositions = 0;
//		dropcatTotalSuccessGainAmount = 0;
//		dropcatTotalFailedLossAmount = 0;
//		dropcatPercentSuccessPositions = 0;
//
//		numDaysInAccount = 0;
//
//		if ((scenarioList != null) && (scenarioList.size() > 0)) {
//			for (ScenarioDecorator thisScenario : scenarioList) {
//
//				thisScenario.calcTotalSuccessPositions();
//
//				numDaysInAccount += thisScenario.getNumDaysInScenario();
//
//				// ------------- LONG ---------------
//				numLongSuccess += thisScenario.getNumSuccessPositionsLong();
//				numLongLoss += thisScenario.getNumFailedPositionsLong();
//				numLongPain += thisScenario.getNumPainPositionsLong();
//				numLongRejected += thisScenario.getNumRejectedPositionsLong();
//				numLongConfirmRejected += thisScenario.getNumConfirmRejectedPositionsLong();
//
//				if (thisScenario.getNumSuccessPositionsLong() != 0F)
//					longSuccessAvgGain += thisScenario.getTotalSuccessAmountLong() / thisScenario.getNumSuccessPositionsLong();
//
//				if (thisScenario.getNumFailedPositionsLong() != 0F)
//					longLossAvgLoss += thisScenario.getTotalFailedAmountLong() / thisScenario.getNumFailedPositionsLong();
//
//				if (thisScenario.getNumPainPositionsLong() != 0F)
//					longPainAvgLoss += thisScenario.getTotalPainAmountLong() / thisScenario.getNumPainPositionsLong();
//
//				if (thisScenario.getNumRejectedPositionsLong() != 0F)
//					longRejectedAvgLoss += thisScenario.getTotalRejectedAmountLong() / thisScenario.getNumRejectedPositionsLong();
//
//				if (thisScenario.getNumConfirmRejectedPositionsLong() != 0F)
//					longConfirmRejectedAvgLoss += thisScenario.getTotalConfirmRejectedAmountLong() / thisScenario.getNumConfirmRejectedPositionsLong();
//
//				// ------------- SHORT ---------------
//				numShortSuccess += thisScenario.getNumSuccessPositionsShort();
//				numShortLoss += thisScenario.getNumFailedPositionsShort();
//				numShortPain += thisScenario.getNumPainPositionsShort();
//				numShortRejected += thisScenario.getNumRejectedPositionsShort();
//				numShortConfirmRejected += thisScenario.getNumConfirmRejectedPositionsShort();
//
//				if (thisScenario.getNumSuccessPositionsShort() != 0F)
//					shortSuccessAvgGain += thisScenario.getTotalSuccessAmountShort() / thisScenario.getNumSuccessPositionsShort();
//
//				if (thisScenario.getNumFailedPositionsShort() != 0F)
//					shortLossAvgLoss += thisScenario.getTotalFailedAmountShort() / thisScenario.getNumFailedPositionsShort();
//
//				if (thisScenario.getNumPainPositionsShort() != 0F)
//					shortPainAvgLoss += thisScenario.getTotalPainAmountShort() / thisScenario.getNumPainPositionsShort();
//
//				if (thisScenario.getNumRejectedPositionsShort() != 0F)
//					shortRejectedAvgLoss += thisScenario.getTotalRejectedAmountShort() / thisScenario.getNumRejectedPositionsShort();
//
//				if (thisScenario.getNumConfirmRejectedPositionsShort() != 0F)
//					shortConfirmRejectedAvgLoss += thisScenario.getTotalConfirmRejectedAmountShort() / thisScenario.getNumConfirmRejectedPositionsShort();
//
//				// ------------- TOTAL ---------------
//				numSuccessPositions += thisScenario.getNumSuccessPositions();
//				numLossPositions += thisScenario.getNumFailedPositions();
//				numPainPositions += thisScenario.getNumPainPositions();
//				numRejectedPositions += thisScenario.getNumRejectedPositions();
//				numConfirmRejectedPositions += thisScenario.getNumConfirmRejectedPositions();
//
//				successTotalAmount += thisScenario.getTotalSuccessAmount();
//				lossTotalAmount += thisScenario.getTotalFailedAmount();
//				painTotalAmount += thisScenario.getTotalPainAmount();
//				rejectedTotalAmount += thisScenario.getTotalRejectedAmount();
//				confirmRejectedTotalAmount += thisScenario.getTotalConfirmRejectedAmount();
//
//				// ------------- DROPCAT ---------------
//				dropcatNumSuccessPositions += (thisScenario.getDropcatNumSuccessPositionsLong() + thisScenario.getDropcatNumSuccessPositionsShort());
//				dropcatNumFailedPositions += (thisScenario.getDropcatNumFailedPositionsLong() + thisScenario.getDropcatNumFailedPositionsShort());
//				dropcatTotalSuccessGainAmount += (thisScenario.getDropcatTotalSuccessAmountLong() + thisScenario.getDropcatTotalSuccessAmountShort());
//				dropcatTotalFailedLossAmount += (thisScenario.getDropcatTotalFailedAmountLong() + thisScenario.getDropcatTotalFailedAmountShort());
//			}
//
//			numPositions += (numSuccessPositions + numLossPositions + numPainPositions + numRejectedPositions + numConfirmRejectedPositions);
//
//			if (numSuccessPositions != 0F)
//				successAvgGain = successTotalAmount / numSuccessPositions;
//
//			if (numLossPositions != 0F)
//				lossAvgLoss = lossTotalAmount / numLossPositions;
//
//			if (numPainPositions != 0F)
//				painAvgLoss = painTotalAmount / numPainPositions;
//
//			if (numRejectedPositions != 0F)
//				rejectedAvgLoss = rejectedTotalAmount / numRejectedPositions;
//
//			if (numConfirmRejectedPositions != 0F)
//				confirmRejectedAvgLoss = confirmRejectedTotalAmount / numConfirmRejectedPositions;
//
//			successPositionsPercent += numSuccessPositions / numPositions;
//			lossPositionsPercent += numLossPositions / numPositions;
//			painPositionsPercent += numPainPositions / numPositions;
//			rejectedPositionsPercent += numRejectedPositions / numPositions;
//			confirmRejectedPositionsPercent += numConfirmRejectedPositions / numPositions;
//
//			//////////////// SUCCESS POSITION CALCS
//			if (numLongSuccess == 0) // If we divide zero by anything, it'll be zero
//				longPercentSuccess = 0;
//			else {
//				if (numLongSuccess + numLongLoss + numLongPain + numLongRejected + numLongConfirmRejected != 0) // No division by zero allowed in
//																												// remedial math.
//					longPercentSuccess = (numLongSuccess / (numLongSuccess + numLongLoss + numLongPain + numLongRejected + numLongConfirmRejected)) * 100; // Turn into percentage and
//																																							// not a //
//				// fraction.
//				else
//					longPercentSuccess = 0;
//			}
//
//			if (numShortSuccess == 0) // If we divide zero by anything, it'll be zero
//				shortPercentSuccess = 0;
//			else {
//				if (numShortSuccess + numShortLoss + numShortPain + numShortRejected + numShortConfirmRejected != 0) // No division by zero allowed in
//																														// remedial math.
//					shortPercentSuccess = (numShortSuccess / (numShortSuccess + numShortLoss + numShortPain + numShortRejected + numShortConfirmRejected)) * 100; // Turn into
//																																									// percentage and
//																																									// not a //
//				// fraction.
//				else
//					shortPercentSuccess = 0;
//			}
//
//			//////////////// FAILED POSITION CALCS
//			if (numLongLoss == 0) // If we divide zero by anything, it'll be zero
//				longPercentLoss = 0;
//			else {
//				if (numLongSuccess + numLongLoss + numLongPain + numLongRejected + numLongConfirmRejected != 0) // No division by zero allowed in
//																												// remedial math.
//					longPercentLoss = (numLongLoss / (numLongSuccess + numLongLoss + numLongPain + numLongRejected + numLongConfirmRejected)) * 100; // Turn into percentage and
//																																						// not a //
//				// fraction.
//				else
//					longPercentLoss = 0;
//			}
//
//			if (numShortLoss == 0) // If we divide zero by anything, it'll be zero
//				shortPercentLoss = 0;
//			else {
//				if (numShortSuccess + numShortLoss + numShortPain + numShortRejected + numShortConfirmRejected != 0) // No division by zero allowed in
//																														// remedial math.
//					shortPercentLoss = (numShortLoss / (numShortSuccess + numShortLoss + numShortPain + numShortRejected + numShortConfirmRejected)) * 100; // Turn into
//																																							// percentage and
//																																							// not a //
//				// fraction.
//				else
//					shortPercentLoss = 0;
//			}
//
//			//////////////// PAIN POSITION CALCS
//			if (numLongPain == 0) // If we divide zero by anything, it'll be zero
//				longPercentPain = 0;
//			else {
//				if (numLongSuccess + numLongLoss + numLongPain + numLongRejected + numLongConfirmRejected != 0) // No division by zero allowed in
//																												// remedial math.
//					longPercentPain = (numLongPain / (numLongSuccess + numLongLoss + numLongPain + numLongRejected + numLongConfirmRejected)) * 100; // Turn into percentage and
//																																						// not a // fraction.
//				else
//					longPercentPain = 0;
//			}
//
//			if (numShortPain == 0) // If we divide zero by anything, it'll be zero
//				shortPercentPain = 0;
//			else {
//				if (numShortSuccess + numShortLoss + numShortPain + numShortRejected + numShortConfirmRejected != 0) // No division by zero allowed in
//																														// remedial math.
//					shortPercentPain = (numShortPain / (numShortSuccess + numShortLoss + numShortPain + numShortRejected + numShortConfirmRejected)) * 100; // Turn into
//																																							// percentage and
//																																							// not a //
//				// fraction.
//				else
//					shortPercentPain = 0;
//			}
//
//			//////////////// REJECTED POSITION CALCS
//			if (numLongRejected == 0) // If we divide zero by anything, it'll be zero
//				longPercentRejected = 0;
//			else {
//				if (numLongSuccess + numLongLoss + numLongPain + numLongRejected + numLongConfirmRejected != 0) // No division by zero allowed in
//																												// remedial math.
//					longPercentRejected = (numLongRejected / (numLongSuccess + numLongLoss + numLongPain + numLongRejected + numLongConfirmRejected)) * 100; // Turn into percentage and
//																																								// not a // fraction.
//				else
//					longPercentRejected = 0;
//			}
//
//			if (numShortRejected == 0) // If we divide zero by anything, it'll be zero
//				shortPercentRejected = 0;
//			else {
//				if (numShortSuccess + numShortLoss + numShortPain + numShortRejected + numShortConfirmRejected != 0) // No division by zero allowed in
//																														// remedial math.
//					shortPercentRejected = (numShortRejected / (numShortSuccess + numShortLoss + numShortPain + numShortRejected + numShortConfirmRejected)) * 100; // Turn into
//																																									// percentage and
//																																									// not a //
//				// fraction.
//				else
//					shortPercentRejected = 0;
//			}
//
//			//////////////// CONFIRM REJECTED POSITION CALCS
//			if (numLongConfirmRejected == 0) // If we divide zero by anything, it'll be zero
//				longPercentConfirmRejected = 0;
//			else {
//				if (numLongSuccess + numLongLoss + numLongPain + numLongRejected + numLongConfirmRejected != 0) // No division by zero allowed in
//																												// remedial math.
//					longPercentConfirmRejected = (numLongConfirmRejected / (numLongSuccess + numLongLoss + numLongPain + numLongRejected + numLongConfirmRejected)) * 100; // Turn into percentage and
//																																											// not a // fraction.
//				else
//					longPercentConfirmRejected = 0;
//			}
//
//			if (numShortConfirmRejected == 0) // If we divide zero by anything, it'll be zero
//				shortPercentConfirmRejected = 0;
//			else {
//				if (numShortSuccess + numShortLoss + numShortPain + numShortRejected + numShortConfirmRejected != 0) // No division by zero allowed in
//																														// remedial math.
//					shortPercentConfirmRejected = (numShortConfirmRejected / (numShortSuccess + numShortLoss + numShortPain + numShortRejected + numShortConfirmRejected)) * 100; // Turn into
//																																													// percentage and
//																																													// not a //
//				// fraction.
//				else
//					shortPercentConfirmRejected = 0;
//			}
//
//			//////////////// DROPCAT POSITION CALCS
//			if (dropcatNumSuccessPositions == 0) // If we divide zero by anything, it'll be zero
//				dropcatPercentSuccessPositions = 0;
//			else {
//				if (dropcatNumSuccessPositions + dropcatNumFailedPositions != 0) // No division by zero allowed in remedial math.
//					dropcatPercentSuccessPositions = (float) ((float) dropcatNumSuccessPositions / ((float) dropcatNumSuccessPositions + (float) dropcatNumFailedPositions)) * 100F; // Turn into
//				else
//					dropcatPercentSuccessPositions = 0;
//			}
//
//			////////////// CALCULATE TOTAL PROFIT/LOSSES
//			// SUM THE TOTAL PROFIT AND LOSS
//			totalProfitLoss = 0.0f;
//			shortTotalProfitLoss = 0.0f;
//			longTotalProfitLoss = 0.0f;
//
//			Iterator positionIter = positionList.iterator();
//			while (positionIter.hasNext()) {
//				PositionDbDecorator thisPosition = (PositionDbDecorator) positionIter.next();
//				totalProfitLoss += thisPosition.getTotalGrowthAmount();
//				if (thisPosition.getLongOrShort() == GmConstants.LONG_SECURITY)
//					longTotalProfitLoss += thisPosition.getTotalGrowthAmount();
//				else
//					shortTotalProfitLoss += thisPosition.getTotalGrowthAmount();
//			}
//		}
//
//		return true;
//	}
//
}
