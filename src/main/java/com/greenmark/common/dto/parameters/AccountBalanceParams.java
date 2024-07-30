package com.greenmark.common.dto.parameters;

import java.io.Serializable;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountBalanceParams</p>
 * <p>Description: This class contains parameters that are used for Account Events and Scenarios.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class AccountBalanceParams implements Serializable {
	public static final String CLASSNAME = "AccountBalanceParams";
	private static final long serialVersionUID = 1L;

	protected double netLiquidationValue;

	protected double totalEquityCash;
	protected double availableFundsCash;
	protected double allocatedFundsCash;
	protected double unsettledFundsCash;

	protected double totalEquityMargin;
	protected double availableFundsMargin;
	protected double allocatedFundsMargin;
	protected double unsettledFundsMargin;

	protected double shortPositionsTotal = 0;
	protected double longPositionsTotal = 0;

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public double getNetLiquidationValue() {
		return netLiquidationValue;
	}

	public void setNetLiquidationValue(double netLiquidationValue) {
		this.netLiquidationValue = netLiquidationValue;
	}

	public double getTotalEquityCash() {
		return totalEquityCash;
	}

	public void setTotalEquityCash(double totalEquityCash) {
		this.totalEquityCash = totalEquityCash;
	}

	public double getAvailableFundsCash() {
		return availableFundsCash;
	}

	public void setAvailableFundsCash(double availableFundsCash) {
		this.availableFundsCash = availableFundsCash;
	}

	public double getAllocatedFundsCash() {
		return allocatedFundsCash;
	}

	public void setAllocatedFundsCash(double allocatedFundsCash) {
		this.allocatedFundsCash = allocatedFundsCash;
	}

	public double getUnsettledFundsCash() {
		return unsettledFundsCash;
	}

	public void setUnsettledFundsCash(double unsettledFundsCash) {
		this.unsettledFundsCash = unsettledFundsCash;
	}

	public double getTotalEquityMargin() {
		return totalEquityMargin;
	}

	public void setTotalEquityMargin(double totalEquityMargin) {
		this.totalEquityMargin = totalEquityMargin;
	}

	public double getAvailableFundsMargin() {
		return availableFundsMargin;
	}

	public void setAvailableFundsMargin(double availableFundsMargin) {
		this.availableFundsMargin = availableFundsMargin;
	}

	public double getAllocatedFundsMargin() {
		return allocatedFundsMargin;
	}

	public void setAllocatedFundsMargin(double allocatedFundsMargin) {
		this.allocatedFundsMargin = allocatedFundsMargin;
	}

	public double getUnsettledFundsMargin() {
		return unsettledFundsMargin;
	}

	public void setUnsettledFundsMargin(double unsettledFundsMargin) {
		this.unsettledFundsMargin = unsettledFundsMargin;
	}

	public double getShortPositionsTotal() {
		return shortPositionsTotal;
	}

	public void setShortPositionsTotal(double shortPositionsTotal) {
		this.shortPositionsTotal = shortPositionsTotal;
	}

	public double getLongPositionsTotal() {
		return longPositionsTotal;
	}

	public void setLongPositionsTotal(double longPositionsTotal) {
		this.longPositionsTotal = longPositionsTotal;
	}
}
