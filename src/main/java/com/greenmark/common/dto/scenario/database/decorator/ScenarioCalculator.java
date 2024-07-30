package com.greenmark.common.dto.scenario.database.decorator;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.time.ZoneOffset;

import org.apache.commons.beanutils.BeanUtils;

import com.greenmark.common.GmConstants;
import com.greenmark.common.dto.account.timeperiod.AccountDailyDto;
import com.greenmark.common.dto.parameters.AccountBalanceParams;
import com.greenmark.common.dto.scenario.decorator.ScenarioDtoDecorator;
import com.greenmark.common.service.MoneyCalculatorService;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ScenarioCalculator</p>
 * <p>Description: This decorator class is labeled a Calculator because it extends its base class with methods that calculate our scenario statistics.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ScenarioCalculator extends ScenarioCalculatorDtoDecorator implements Serializable {
	public static final String CLASSNAME = "ScenarioCalculator";
	private static final long serialVersionUID = 1L;

	public ScenarioCalculator() {
	}

	public ScenarioCalculator(ScenarioDtoDecorator currentScenario, AccountBalanceParams accountBalance, int currentScenarioNumber) {
		super(currentScenario, accountBalance, currentScenarioNumber);
	}

	public ScenarioCalculator(String xmldata) {
		super(xmldata);
	}

	public ScenarioCalculator(ScenarioDbDecorator inScenario) {
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

//	public void calculateAllStatistics(List<PositionDbDecorator> positionList) {
//		this.setPositionList(positionList);
//		calcTotalSuccessPositions();
//
//		calculateYearlyROI();
//		calculateMonthlyROI();
//	}

	public float calculateYearlyROI() {
		double totalProfitLoss = getTotalProfitAndLoss();
		double longTotalProfitLoss = getLongProfitAndLoss();
		double shortTotalProfitLoss = getShortProfitAndLoss();

		this.calcRoiYearly = MoneyCalculatorService.calcROI(accountDailyList, accountType, GmConstants.AVG_NUM_CALENDAR_DAYS_PER_YEAR, (accountDailyList.size() - 1), totalProfitLoss);
		this.longCalcRoiYearly = MoneyCalculatorService.calcROI(accountDailyList, accountType, GmConstants.AVG_NUM_CALENDAR_DAYS_PER_YEAR, (accountDailyList.size() - 1), longTotalProfitLoss);
		this.shortCalcRoiYearly = MoneyCalculatorService.calcROI(accountDailyList, accountType, GmConstants.AVG_NUM_CALENDAR_DAYS_PER_YEAR, (accountDailyList.size() - 1), shortTotalProfitLoss);

		return this.calcRoiYearly;
	}

	public float calculateMonthlyROI() {
		int numDaysForAccount = getNumDaysInScenario();
		double totalProfitLoss = getTotalProfitAndLoss();
		double longTotalProfitLoss = getLongProfitAndLoss();
		double shortTotalProfitLoss = getShortProfitAndLoss();

		this.calcRoiMonthly = MoneyCalculatorService.calcROI(accountDailyList, accountType, GmConstants.AVG_NUM_CALENDAR_DAYS_PER_MONTH, numDaysForAccount, totalProfitLoss);
		this.longCalcRoiMonthly = MoneyCalculatorService.calcROI(accountDailyList, accountType, GmConstants.AVG_NUM_CALENDAR_DAYS_PER_YEAR, numDaysForAccount, longTotalProfitLoss);
		this.shortCalcRoiMonthly = MoneyCalculatorService.calcROI(accountDailyList, accountType, GmConstants.AVG_NUM_CALENDAR_DAYS_PER_YEAR, numDaysForAccount, shortTotalProfitLoss);
		return this.calcRoiMonthly;
	}

	public float calcPercentGrowth(double numDaysPerPeriod) {
		float returnVal = 0;

		if ((accountDailyList != null) && (accountDailyList.size() > 1)) {
			// Estimate the number of calendar days since the first Account Daily item and the last
			AccountDailyDto openBalancesDaily = accountDailyList.get(0); // This record is the previous day's open.
			AccountDailyDto firstDaily = null;
			if (accountDailyList.size() == 1)
				firstDaily = accountDailyList.get(0); // Get the second record, the first one is our open balance record(yesterday)
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

//	public float calcTotalSuccessPositions() {
//		totalNumPositionsLong = 0;
//
//		numSuccessPositionsLong = 0;
//		numFailedPositionsLong = 0;
//		numPainPositionsLong = 0;
//		numRejectedPositionsLong = 0;
//		numConfirmRejectedPositionsLong = 0;
//
//		totalSuccessAmountLong = 0;
//		totalFailedAmountLong = 0;
//		totalPainAmountLong = 0.0F;
//		totalRejectedAmountLong = 0.0F;
//		totalConfirmRejectedAmountLong = 0.0F;
//
//		totalNumPositionsShort = 0;
//
//		numSuccessPositionsShort = 0;
//		numFailedPositionsShort = 0;
//		numPainPositionsShort = 0;
//		numRejectedPositionsShort = 0;
//		numConfirmRejectedPositionsShort = 0;
//
//		totalSuccessAmountShort = 0;
//		totalFailedAmountShort = 0;
//		totalPainAmountShort = 0.0F;
//		totalRejectedAmountShort = 0.0F;
//		totalConfirmRejectedAmountShort = 0.0F;
//
//		numSuccessPositions = 0;
//		percentSuccessPositions = 0.0F;
//		totalSuccessAmount = 0.0F;
//		totalSuccessAvgGain = 0.0F;
//
//		numFailedPositions = 0;
//		percentFailedPositions = 0.0F;
//		totalFailedAmount = 0.0F;
//		totalFailedAvgLoss = 0.0F;
//
//		numPainPositions = 0;
//		percentPainPositions = 0.0F;
//		totalPainAmount = 0.0F;
//		totalPainAvgLoss = 0.0F;
//
//		numRejectedPositions = 0;
//		percentRejectedPositions = 0.0F;
//		totalRejectedAmount = 0.0F;
//		totalRejectedAvgLoss = 0.0F;
//
//		numConfirmRejectedPositions = 0;
//		percentConfirmRejectedPositions = 0.0F;
//		totalConfirmRejectedAmount = 0.0F;
//		totalConfirmRejectedAvgLoss = 0.0F;
//
//		// DROPCAT STATISTICS
//		dropcatNumSuccessPositionsLong = 0;
//		dropcatNumFailedPositionsLong = 0;
//		dropcatTotalSuccessAmountLong = 0;
//		dropcatTotalFailedAmountLong = 0;
//		dropcatTotalNumPositionsLong = 0;
//
//		dropcatNumSuccessPositionsShort = 0;
//		dropcatNumFailedPositionsShort = 0;
//		dropcatTotalSuccessAmountShort = 0;
//		dropcatTotalFailedAmountShort = 0;
//		dropcatTotalNumPositionsShort = 0;
//
//		float returnVal = 0;
//
//		if ((positionList != null) && (positionList.size() > 0)) {
//			for (PositionDbDecorator thisPosition : positionList) {
//
//				if (thisPosition.isPositionPainedOutNormally()) {
//					if (thisPosition.getLongOrShort() == GmConstants.LONG_SECURITY) {
//						numPainPositionsLong++;
//						numPainPositions++;
//
//						totalPainAmountLong += thisPosition.getTotalGrowthAmount();
//						totalPainAmount += thisPosition.getTotalGrowthAmount();
//					} else {
//						numPainPositionsShort++;
//						numPainPositions++;
//
//						totalPainAmountShort += thisPosition.getTotalGrowthAmount();
//						totalPainAmount += thisPosition.getTotalGrowthAmount();
//					}
//
//				} else if (thisPosition.getPositionStatus() == GmConstantsBroker.POSITION_STATUS_834_REJECTED) {
//					if (thisPosition.getLongOrShort() == GmConstants.LONG_SECURITY) {
//						numRejectedPositionsLong++;
//						numRejectedPositions++;
//
//						totalRejectedAmountLong += thisPosition.getTotalGrowthAmount();
//						totalRejectedAmount += thisPosition.getTotalGrowthAmount();
//					} else {
//						numRejectedPositionsShort++;
//						numRejectedPositions++;
//						totalRejectedAmountShort += thisPosition.getTotalGrowthAmount();
//						totalRejectedAmount += thisPosition.getTotalGrowthAmount();
//					}
//				} else if (thisPosition.getPositionStatus() == GmConstantsBroker.POSITION_STATUS_834_CONFIRMED_THEN_REJECTED) {
//					if (thisPosition.getLongOrShort() == GmConstants.LONG_SECURITY) {
//						numConfirmRejectedPositionsLong++;
//						numConfirmRejectedPositions++;
//
//						totalConfirmRejectedAmountLong += thisPosition.getTotalGrowthAmount();
//						totalConfirmRejectedAmount += thisPosition.getTotalGrowthAmount();
//					} else {
//						numConfirmRejectedPositionsShort++;
//						numConfirmRejectedPositions++;
//
//						totalConfirmRejectedAmountShort += thisPosition.getTotalGrowthAmount();
//						totalConfirmRejectedAmount += thisPosition.getTotalGrowthAmount();
//					}
//				} else {
//					if (thisPosition.getTotalGrowthAmount() > 0) {
//						if (thisPosition.getLongOrShort() == GmConstants.LONG_SECURITY) {
//							numSuccessPositionsLong++;
//							numSuccessPositions++;
//
//							totalSuccessAmountLong += thisPosition.getTotalGrowthAmount();
//							totalSuccessAmount += thisPosition.getTotalGrowthAmount();
//						} else {
//							numSuccessPositionsShort++;
//							numSuccessPositions++;
//
//							totalSuccessAmountShort += thisPosition.getTotalGrowthAmount();
//							totalSuccessAmount += thisPosition.getTotalGrowthAmount();
//						}
//					}
//					// Don't include those with zero growth, they are neither gain or loss.
//					if (thisPosition.getTotalGrowthAmount() < 0) {
//						if (thisPosition.getLongOrShort() == GmConstants.LONG_SECURITY) {
//							numFailedPositionsLong++;
//							numFailedPositions++;
//
//							totalFailedAmountLong += thisPosition.getTotalGrowthAmount();
//							totalFailedAmount += thisPosition.getTotalGrowthAmount();
//						} else {
//							numFailedPositionsShort++;
//							numFailedPositions++;
//
//							totalFailedAmountShort += thisPosition.getTotalGrowthAmount();
//							totalFailedAmount += thisPosition.getTotalGrowthAmount();
//						}
//					}
//				}
//
//				// CALCULATE DROPCAT STATISTICS
//				if (thisPosition.isPositionDropcat()) {
//					if (thisPosition.isPositionDropcatSuccess()) {
//						if (thisPosition.getLongOrShort() == GmConstants.LONG_SECURITY) {
//							dropcatNumSuccessPositionsLong++;
//							dropcatTotalSuccessAmountLong += thisPosition.getTotalGrowthAmount();
//						} else {
//							dropcatNumSuccessPositionsShort++;
//							dropcatTotalSuccessAmountShort += thisPosition.getTotalGrowthAmount();
//						}
//					} else {
//						if (thisPosition.getLongOrShort() == GmConstants.LONG_SECURITY) {
//							dropcatNumFailedPositionsLong++;
//							dropcatTotalFailedAmountLong += thisPosition.getTotalGrowthAmount();
//						} else {
//							dropcatNumFailedPositionsShort++;
//							dropcatTotalFailedAmountShort += thisPosition.getTotalGrowthAmount();
//						}
//					}
//				}
//
//			}
//
//			totalNumPositionsLong = (int) numSuccessPositionsLong + (int) numFailedPositionsLong + numPainPositionsLong + numRejectedPositionsLong + numConfirmRejectedPositionsLong;
//			totalNumPositionsShort = (int) numSuccessPositionsShort + (int) numFailedPositionsShort + numPainPositionsShort + numRejectedPositionsShort
//					+ numConfirmRejectedPositionsShort;
//
//			percentSuccessPositions = (numSuccessPositionsLong + numSuccessPositionsShort) / (float) (totalNumPositionsLong + totalNumPositionsShort);
//			percentFailedPositions = (numFailedPositionsLong + numFailedPositionsShort) / (float) (totalNumPositionsLong + totalNumPositionsShort);
//			percentPainPositions = (float) (numPainPositionsLong + numPainPositionsShort) / (float) (totalNumPositionsLong + totalNumPositionsShort);
//			percentRejectedPositions = (float) (numRejectedPositionsLong + numRejectedPositionsShort) / (float) (totalNumPositionsLong + totalNumPositionsShort);
//			percentConfirmRejectedPositions = (float) (numConfirmRejectedPositionsLong + numConfirmRejectedPositionsShort) / (float) (totalNumPositionsLong + totalNumPositionsShort);
//
//			if (numSuccessPositions != 0)
//				totalSuccessAvgGain = totalSuccessAmount / numSuccessPositions;
//
//			if (numFailedPositions != 0)
//				totalFailedAvgLoss = totalFailedAmount / numFailedPositions;
//
//			if (numPainPositions != 0)
//				totalPainAvgLoss = totalPainAmount / numPainPositions;
//
//			if (numRejectedPositions != 0)
//				totalRejectedAvgLoss = totalRejectedAmount / numRejectedPositions;
//
//			if (numConfirmRejectedPositions != 0)
//				totalConfirmRejectedAmount = totalConfirmRejectedAmount / numConfirmRejectedPositions;
//
//			dropcatTotalNumPositionsLong = (int) dropcatNumSuccessPositionsLong + (int) dropcatNumFailedPositionsLong;
//			dropcatTotalNumPositionsShort = (int) dropcatNumSuccessPositionsShort + (int) dropcatNumFailedPositionsShort;
//
//			returnVal = numSuccessPositionsLong + numSuccessPositionsShort;
//
//			////////////// CALCULATE TOTAL PROFIT/LOSSES
//			// SUM THE TOTAL PROFIT AND LOSS
//			totalProfitAndLoss = 0.0f;
//			shortProfitAndLoss = 0.0f;
//			longProfitAndLoss = 0.0f;
//
//			for (PositionDbDecorator thisPosition : positionList) {
//				totalProfitAndLoss += thisPosition.getTotalGrowthAmount();
//				if (thisPosition.getLongOrShort() == GmConstants.LONG_SECURITY)
//					longProfitAndLoss += thisPosition.getTotalGrowthAmount();
//				else
//					shortProfitAndLoss += thisPosition.getTotalGrowthAmount();
//			}
//
//			calculateYearlyROI();
//			calculateMonthlyROI();
//		}
//
//		return returnVal;
//	}
}
