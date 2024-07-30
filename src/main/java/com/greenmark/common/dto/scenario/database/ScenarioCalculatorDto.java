package com.greenmark.common.dto.scenario.database;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.greenmark.common.GmConstantsAccount;
import com.greenmark.common.dto.parameters.AccountBalanceParams;
import com.greenmark.common.dto.scenario.database.decorator.ScenarioDbDecorator;
import com.greenmark.common.dto.scenario.decorator.ScenarioDtoDecorator;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ScenarioCalculatorDto</p>
 * <p>Description: This DTO class is not used to store data into a DB table.  It is used to calculate account or scenario statistics, some of which are
 *    stored in the scenario_summaries table.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ScenarioCalculatorDto extends ScenarioDbDecorator implements Serializable {
	public static final String CLASSNAME = "ScenarioCalculatorDto";
	private static final long serialVersionUID = 1L;

	protected String queryStartDate;
	protected String queryStartDatetime;

	protected String queryEndDate;
	protected String queryEndDatetime;

	protected float calcRoiYearly = 0.0F;
	protected float calcRoiMonthly = 0.0F;
	protected float totalProfitAndLoss;

	protected float longCalcRoiYearly = 0.0F;
	protected float longCalcRoiMonthly = 0.0F;
	protected float longProfitAndLoss;

	protected float shortCalcRoiYearly = 0.0F;
	protected float shortCalcRoiMonthly = 0.0F;
	protected float shortProfitAndLoss;

	// TOTAL ACCOUNT STATS
	protected float numSuccessPositions = 0;
	protected float percentSuccessPositions = 0.0F;
	protected float totalSuccessAmount = 0.0F;
	protected float totalSuccessAvgGain = 0.0F;

	protected float numFailedPositions = 0;
	protected float percentFailedPositions = 0.0F;
	protected float totalFailedAmount = 0.0F;
	protected float totalFailedAvgLoss = 0.0F;

	protected float numPainPositions = 0;
	protected float percentPainPositions = 0.0F;
	protected float totalPainAmount = 0.0F;
	protected float totalPainAvgLoss = 0.0F;

	protected float numRejectedPositions = 0;
	protected float percentRejectedPositions = 0.0F;
	protected float totalRejectedAmount = 0.0F;
	protected float totalRejectedAvgLoss = 0.0F;

	protected float numConfirmRejectedPositions = 0;
	protected float percentConfirmRejectedPositions = 0.0F;
	protected float totalConfirmRejectedAmount = 0.0F;
	protected float totalConfirmRejectedAvgLoss = 0.0F;

	// LONG POSITION STATS
	protected int totalNumPositionsLong = 0;

	protected float numSuccessPositionsLong = 0; // Using float for float divisor
	protected float totalSuccessAmountLong = 0.0F; // For each position in numSuccessPositions, will add up their % growth

	protected float numFailedPositionsLong = 0; // Using float for float divisor
	protected float totalFailedAmountLong = 0.0F; // For each position in numFailedPositions, will add up their % growth

	protected int numPainPositionsLong = 0;
	protected float totalPainAmountLong = 0.0F;

	protected int numRejectedPositionsLong = 0;
	protected float totalRejectedAmountLong = 0.0F;

	protected int numConfirmRejectedPositionsLong = 0;
	protected float totalConfirmRejectedAmountLong = 0.0F;

	// SHORT POSITION STATS
	protected int totalNumPositionsShort = 0;

	protected float numSuccessPositionsShort = 0; // Using float for float divisor
	protected float totalSuccessAmountShort = 0.0F; // For each position in numSuccessPositions, will add up their % growth

	protected float numFailedPositionsShort = 0; // Using float for float divisor
	protected float totalFailedAmountShort = 0.0F; // For each position in numFailedPositions, will add up their % growth

	protected int numPainPositionsShort = 0;
	protected float totalPainAmountShort = 0.0F;

	protected int numRejectedPositionsShort = 0;
	protected float totalRejectedAmountShort = 0.0F;

	protected int numConfirmRejectedPositionsShort = 0;
	protected float totalConfirmRejectedAmountShort = 0.0F;

	// LONG DROP CAT POSITION STATS
	protected float dropcatTotalSuccessAmountLong = 0.0F; // For each position in numSuccessPositions, will add up their % growth
	protected float dropcatNumSuccessPositionsLong = 0; // Using float for float divisor

	protected float dropcatTotalFailedAmountLong = 0.0F; // For each position in numFailedPositions, will add up their % growth
	protected float dropcatNumFailedPositionsLong = 0; // Using float for float divisor

	protected int dropcatTotalNumPositionsLong = 0;

	// SHORT DROP CAT POSITION STATS
	protected float dropcatTotalSuccessAmountShort = 0.0F; // For each position in numSuccessPositions, will add up their % growth
	protected float dropcatNumSuccessPositionsShort = 0; // Using float for float divisor

	protected float dropcatTotalFailedAmountShort = 0.0F; // For each position in numFailedPositions, will add up their % growth
	protected float dropcatNumFailedPositionsShort = 0; // Using float for float divisor

	protected int dropcatTotalNumPositionsShort = 0;

	protected int accountType = GmConstantsAccount.ACCOUNT_TYPE_STANDARD; // This must be set by the website when creating these ScenarioDecorators

	// Calculate the amount invested versus the net liquid value
	protected double leverageRatio = 0.0F;

	public ScenarioCalculatorDto() {
	}

	public ScenarioCalculatorDto(ScenarioDtoDecorator currentScenario, AccountBalanceParams accountBalance, int currentScenarioNumber) {
		super(currentScenario, accountBalance, currentScenarioNumber);
	}

	public ScenarioCalculatorDto(String xmldata) {
		super(xmldata);
	}

	public ScenarioCalculatorDto(ScenarioDbDecorator inScenario) {
		try {
			if (inScenario != null)
				BeanUtils.copyProperties(this, inScenario);
		} catch (InvocationTargetException ite) {
			System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage());
		} catch (IllegalAccessException iae) {
			System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
		}
	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public String getQueryStartDate() {
		return queryStartDate;
	}

	public void setQueryStartDate(String queryStartDate) {
		this.queryStartDate = queryStartDate;
	}

	public String getQueryStartDatetime() {
		return queryStartDatetime;
	}

	public void setQueryStartDatetime(String queryStartDatetime) {
		this.queryStartDatetime = queryStartDatetime;
	}

	public String getQueryEndDate() {
		return queryEndDate;
	}

	public void setQueryEndDate(String queryEndDate) {
		this.queryEndDate = queryEndDate;
	}

	public String getQueryEndDatetime() {
		return queryEndDatetime;
	}

	public void setQueryEndDatetime(String queryEndDatetime) {
		this.queryEndDatetime = queryEndDatetime;
	}

	public float getCalcRoiYearly() {
		return calcRoiYearly;
	}

	public void setCalcRoiYearly(float calcRoiYearly) {
		this.calcRoiYearly = calcRoiYearly;
	}

	public float getCalcRoiMonthly() {
		return calcRoiMonthly;
	}

	public void setCalcRoiMonthly(float calcRoiMonthly) {
		this.calcRoiMonthly = calcRoiMonthly;
	}

	public float getTotalProfitAndLoss() {
		return totalProfitAndLoss;
	}

	public void setTotalProfitAndLoss(float totalProfitAndLoss) {
		this.totalProfitAndLoss = totalProfitAndLoss;
	}

	public float getLongCalcRoiYearly() {
		return longCalcRoiYearly;
	}

	public void setLongCalcRoiYearly(float longCalcRoiYearly) {
		this.longCalcRoiYearly = longCalcRoiYearly;
	}

	public float getLongCalcRoiMonthly() {
		return longCalcRoiMonthly;
	}

	public void setLongCalcRoiMonthly(float longCalcRoiMonthly) {
		this.longCalcRoiMonthly = longCalcRoiMonthly;
	}

	public float getLongProfitAndLoss() {
		return longProfitAndLoss;
	}

	public void setLongProfitAndLoss(float longProfitAndLoss) {
		this.longProfitAndLoss = longProfitAndLoss;
	}

	public float getShortCalcRoiYearly() {
		return shortCalcRoiYearly;
	}

	public void setShortCalcRoiYearly(float shortCalcRoiYearly) {
		this.shortCalcRoiYearly = shortCalcRoiYearly;
	}

	public float getShortCalcRoiMonthly() {
		return shortCalcRoiMonthly;
	}

	public void setShortCalcRoiMonthly(float shortCalcRoiMonthly) {
		this.shortCalcRoiMonthly = shortCalcRoiMonthly;
	}

	public float getShortProfitAndLoss() {
		return shortProfitAndLoss;
	}

	public void setShortProfitAndLoss(float shortProfitAndLoss) {
		this.shortProfitAndLoss = shortProfitAndLoss;
	}

	public float getNumSuccessPositions() {
		return numSuccessPositions;
	}

	public void setNumSuccessPositions(float numSuccessPositions) {
		this.numSuccessPositions = numSuccessPositions;
	}

	public float getPercentSuccessPositions() {
		return percentSuccessPositions;
	}

	public void setPercentSuccessPositions(float percentSuccessPositions) {
		this.percentSuccessPositions = percentSuccessPositions;
	}

	public float getTotalSuccessAmount() {
		return totalSuccessAmount;
	}

	public void setTotalSuccessAmount(float totalSuccessAmount) {
		this.totalSuccessAmount = totalSuccessAmount;
	}

	public float getTotalSuccessAvgGain() {
		return totalSuccessAvgGain;
	}

	public void setTotalSuccessAvgGain(float totalSuccessAvgGain) {
		this.totalSuccessAvgGain = totalSuccessAvgGain;
	}

	public float getNumFailedPositions() {
		return numFailedPositions;
	}

	public void setNumFailedPositions(float numFailedPositions) {
		this.numFailedPositions = numFailedPositions;
	}

	public float getPercentFailedPositions() {
		return percentFailedPositions;
	}

	public void setPercentFailedPositions(float percentFailedPositions) {
		this.percentFailedPositions = percentFailedPositions;
	}

	public float getTotalFailedAmount() {
		return totalFailedAmount;
	}

	public void setTotalFailedAmount(float totalFailedAmount) {
		this.totalFailedAmount = totalFailedAmount;
	}

	public float getTotalFailedAvgLoss() {
		return totalFailedAvgLoss;
	}

	public void setTotalFailedAvgLoss(float totalFailedAvgLoss) {
		this.totalFailedAvgLoss = totalFailedAvgLoss;
	}

	public float getNumPainPositions() {
		return numPainPositions;
	}

	public void setNumPainPositions(float numPainPositions) {
		this.numPainPositions = numPainPositions;
	}

	public float getPercentPainPositions() {
		return percentPainPositions;
	}

	public void setPercentPainPositions(float percentPainPositions) {
		this.percentPainPositions = percentPainPositions;
	}

	public float getTotalPainAmount() {
		return totalPainAmount;
	}

	public void setTotalPainAmount(float totalPainAmount) {
		this.totalPainAmount = totalPainAmount;
	}

	public float getTotalPainAvgLoss() {
		return totalPainAvgLoss;
	}

	public void setTotalPainAvgLoss(float totalPainAvgLoss) {
		this.totalPainAvgLoss = totalPainAvgLoss;
	}

	public float getNumRejectedPositions() {
		return numRejectedPositions;
	}

	public void setNumRejectedPositions(float numRejectedPositions) {
		this.numRejectedPositions = numRejectedPositions;
	}

	public float getPercentRejectedPositions() {
		return percentRejectedPositions;
	}

	public void setPercentRejectedPositions(float percentRejectedPositions) {
		this.percentRejectedPositions = percentRejectedPositions;
	}

	public float getTotalRejectedAmount() {
		return totalRejectedAmount;
	}

	public void setTotalRejectedAmount(float totalRejectedAmount) {
		this.totalRejectedAmount = totalRejectedAmount;
	}

	public float getTotalRejectedAvgLoss() {
		return totalRejectedAvgLoss;
	}

	public void setTotalRejectedAvgLoss(float totalRejectedAvgLoss) {
		this.totalRejectedAvgLoss = totalRejectedAvgLoss;
	}

	public float getNumConfirmRejectedPositions() {
		return numConfirmRejectedPositions;
	}

	public void setNumConfirmRejectedPositions(float numConfirmRejectedPositions) {
		this.numConfirmRejectedPositions = numConfirmRejectedPositions;
	}

	public float getPercentConfirmRejectedPositions() {
		return percentConfirmRejectedPositions;
	}

	public void setPercentConfirmRejectedPositions(float percentConfirmRejectedPositions) {
		this.percentConfirmRejectedPositions = percentConfirmRejectedPositions;
	}

	public float getTotalConfirmRejectedAmount() {
		return totalConfirmRejectedAmount;
	}

	public void setTotalConfirmRejectedAmount(float totalConfirmRejectedAmount) {
		this.totalConfirmRejectedAmount = totalConfirmRejectedAmount;
	}

	public float getTotalConfirmRejectedAvgLoss() {
		return totalConfirmRejectedAvgLoss;
	}

	public void setTotalConfirmRejectedAvgLoss(float totalConfirmRejectedAvgLoss) {
		this.totalConfirmRejectedAvgLoss = totalConfirmRejectedAvgLoss;
	}

	public int getTotalNumPositionsLong() {
		return totalNumPositionsLong;
	}

	public void setTotalNumPositionsLong(int totalNumPositionsLong) {
		this.totalNumPositionsLong = totalNumPositionsLong;
	}

	public float getNumSuccessPositionsLong() {
		return numSuccessPositionsLong;
	}

	public void setNumSuccessPositionsLong(float numSuccessPositionsLong) {
		this.numSuccessPositionsLong = numSuccessPositionsLong;
	}

	public float getTotalSuccessAmountLong() {
		return totalSuccessAmountLong;
	}

	public void setTotalSuccessAmountLong(float totalSuccessAmountLong) {
		this.totalSuccessAmountLong = totalSuccessAmountLong;
	}

	public float getNumFailedPositionsLong() {
		return numFailedPositionsLong;
	}

	public void setNumFailedPositionsLong(float numFailedPositionsLong) {
		this.numFailedPositionsLong = numFailedPositionsLong;
	}

	public float getTotalFailedAmountLong() {
		return totalFailedAmountLong;
	}

	public void setTotalFailedAmountLong(float totalFailedAmountLong) {
		this.totalFailedAmountLong = totalFailedAmountLong;
	}

	public int getNumPainPositionsLong() {
		return numPainPositionsLong;
	}

	public void setNumPainPositionsLong(int numPainPositionsLong) {
		this.numPainPositionsLong = numPainPositionsLong;
	}

	public float getTotalPainAmountLong() {
		return totalPainAmountLong;
	}

	public void setTotalPainAmountLong(float totalPainAmountLong) {
		this.totalPainAmountLong = totalPainAmountLong;
	}

	public int getNumRejectedPositionsLong() {
		return numRejectedPositionsLong;
	}

	public void setNumRejectedPositionsLong(int numRejectedPositionsLong) {
		this.numRejectedPositionsLong = numRejectedPositionsLong;
	}

	public float getTotalRejectedAmountLong() {
		return totalRejectedAmountLong;
	}

	public void setTotalRejectedAmountLong(float totalRejectedAmountLong) {
		this.totalRejectedAmountLong = totalRejectedAmountLong;
	}

	public int getNumConfirmRejectedPositionsLong() {
		return numConfirmRejectedPositionsLong;
	}

	public void setNumConfirmRejectedPositionsLong(int numConfirmRejectedPositionsLong) {
		this.numConfirmRejectedPositionsLong = numConfirmRejectedPositionsLong;
	}

	public float getTotalConfirmRejectedAmountLong() {
		return totalConfirmRejectedAmountLong;
	}

	public void setTotalConfirmRejectedAmountLong(float totalConfirmRejectedAmountLong) {
		this.totalConfirmRejectedAmountLong = totalConfirmRejectedAmountLong;
	}

	public int getTotalNumPositionsShort() {
		return totalNumPositionsShort;
	}

	public void setTotalNumPositionsShort(int totalNumPositionsShort) {
		this.totalNumPositionsShort = totalNumPositionsShort;
	}

	public float getNumSuccessPositionsShort() {
		return numSuccessPositionsShort;
	}

	public void setNumSuccessPositionsShort(float numSuccessPositionsShort) {
		this.numSuccessPositionsShort = numSuccessPositionsShort;
	}

	public float getTotalSuccessAmountShort() {
		return totalSuccessAmountShort;
	}

	public void setTotalSuccessAmountShort(float totalSuccessAmountShort) {
		this.totalSuccessAmountShort = totalSuccessAmountShort;
	}

	public float getNumFailedPositionsShort() {
		return numFailedPositionsShort;
	}

	public void setNumFailedPositionsShort(float numFailedPositionsShort) {
		this.numFailedPositionsShort = numFailedPositionsShort;
	}

	public float getTotalFailedAmountShort() {
		return totalFailedAmountShort;
	}

	public void setTotalFailedAmountShort(float totalFailedAmountShort) {
		this.totalFailedAmountShort = totalFailedAmountShort;
	}

	public int getNumPainPositionsShort() {
		return numPainPositionsShort;
	}

	public void setNumPainPositionsShort(int numPainPositionsShort) {
		this.numPainPositionsShort = numPainPositionsShort;
	}

	public float getTotalPainAmountShort() {
		return totalPainAmountShort;
	}

	public void setTotalPainAmountShort(float totalPainAmountShort) {
		this.totalPainAmountShort = totalPainAmountShort;
	}

	public int getNumRejectedPositionsShort() {
		return numRejectedPositionsShort;
	}

	public void setNumRejectedPositionsShort(int numRejectedPositionsShort) {
		this.numRejectedPositionsShort = numRejectedPositionsShort;
	}

	public float getTotalRejectedAmountShort() {
		return totalRejectedAmountShort;
	}

	public void setTotalRejectedAmountShort(float totalRejectedAmountShort) {
		this.totalRejectedAmountShort = totalRejectedAmountShort;
	}

	public int getNumConfirmRejectedPositionsShort() {
		return numConfirmRejectedPositionsShort;
	}

	public void setNumConfirmRejectedPositionsShort(int numConfirmRejectedPositionsShort) {
		this.numConfirmRejectedPositionsShort = numConfirmRejectedPositionsShort;
	}

	public float getTotalConfirmRejectedAmountShort() {
		return totalConfirmRejectedAmountShort;
	}

	public void setTotalConfirmRejectedAmountShort(float totalConfirmRejectedAmountShort) {
		this.totalConfirmRejectedAmountShort = totalConfirmRejectedAmountShort;
	}

	public float getDropcatTotalSuccessAmountLong() {
		return dropcatTotalSuccessAmountLong;
	}

	public void setDropcatTotalSuccessAmountLong(float dropcatTotalSuccessAmountLong) {
		this.dropcatTotalSuccessAmountLong = dropcatTotalSuccessAmountLong;
	}

	public float getDropcatNumSuccessPositionsLong() {
		return dropcatNumSuccessPositionsLong;
	}

	public void setDropcatNumSuccessPositionsLong(float dropcatNumSuccessPositionsLong) {
		this.dropcatNumSuccessPositionsLong = dropcatNumSuccessPositionsLong;
	}

	public float getDropcatTotalFailedAmountLong() {
		return dropcatTotalFailedAmountLong;
	}

	public void setDropcatTotalFailedAmountLong(float dropcatTotalFailedAmountLong) {
		this.dropcatTotalFailedAmountLong = dropcatTotalFailedAmountLong;
	}

	public float getDropcatNumFailedPositionsLong() {
		return dropcatNumFailedPositionsLong;
	}

	public void setDropcatNumFailedPositionsLong(float dropcatNumFailedPositionsLong) {
		this.dropcatNumFailedPositionsLong = dropcatNumFailedPositionsLong;
	}

	public int getDropcatTotalNumPositionsLong() {
		return dropcatTotalNumPositionsLong;
	}

	public void setDropcatTotalNumPositionsLong(int dropcatTotalNumPositionsLong) {
		this.dropcatTotalNumPositionsLong = dropcatTotalNumPositionsLong;
	}

	public float getDropcatTotalSuccessAmountShort() {
		return dropcatTotalSuccessAmountShort;
	}

	public void setDropcatTotalSuccessAmountShort(float dropcatTotalSuccessAmountShort) {
		this.dropcatTotalSuccessAmountShort = dropcatTotalSuccessAmountShort;
	}

	public float getDropcatNumSuccessPositionsShort() {
		return dropcatNumSuccessPositionsShort;
	}

	public void setDropcatNumSuccessPositionsShort(float dropcatNumSuccessPositionsShort) {
		this.dropcatNumSuccessPositionsShort = dropcatNumSuccessPositionsShort;
	}

	public float getDropcatTotalFailedAmountShort() {
		return dropcatTotalFailedAmountShort;
	}

	public void setDropcatTotalFailedAmountShort(float dropcatTotalFailedAmountShort) {
		this.dropcatTotalFailedAmountShort = dropcatTotalFailedAmountShort;
	}

	public float getDropcatNumFailedPositionsShort() {
		return dropcatNumFailedPositionsShort;
	}

	public void setDropcatNumFailedPositionsShort(float dropcatNumFailedPositionsShort) {
		this.dropcatNumFailedPositionsShort = dropcatNumFailedPositionsShort;
	}

	public int getDropcatTotalNumPositionsShort() {
		return dropcatTotalNumPositionsShort;
	}

	public void setDropcatTotalNumPositionsShort(int dropcatTotalNumPositionsShort) {
		this.dropcatTotalNumPositionsShort = dropcatTotalNumPositionsShort;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public double getLeverageRatio() {
		return leverageRatio;
	}

	public void setLeverageRatio(double leverageRatio) {
		this.leverageRatio = leverageRatio;
	}
}
