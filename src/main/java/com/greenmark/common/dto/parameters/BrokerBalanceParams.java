package com.greenmark.common.dto.parameters;

import java.io.Serializable;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: BrokerBalanceParams</p>
 * <p>Description: This class is used to transfer values from an IBAccount object to the AccountDaily object.  Its values are stored in the account_dailies table in the results DB.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class BrokerBalanceParams extends AccountBalanceParams implements Serializable {
	public static final String CLASSNAME = "BrokerBalanceParams";
	private static final long serialVersionUID = 1L;

	protected Double netLiquidation = 0D;

	protected Double settledCash = 0D;
	protected Double totalCashBalance = 0D;
	protected Double buyingPower = 0D;
	protected Double availableFunds = 0D;

	protected Double stockMarketValue = 0D;
	protected Double optionMarketValue = 0D;
	protected Double futureOptionValue = 0D;
	protected Double equityWithLoanValue = 0D;

	protected Double sma = 0D;

	protected Double leverage = 0D;
	protected Double initMarginReq = 0D;
	protected Double maintMarginReq = 0D;
	protected Double maintMarginReq_Full = 0D;

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public Double getNetLiquidation() {
		return netLiquidation;
	}

	public void setNetLiquidation(Double netLiquidation) {
		this.netLiquidation = netLiquidation;
	}

	public Double getSettledCash() {
		return settledCash;
	}

	public void setSettledCash(Double settledCash) {
		this.settledCash = settledCash;
	}

	public Double getTotalCashBalance() {
		return totalCashBalance;
	}

	public void setTotalCashBalance(Double totalCashBalance) {
		this.totalCashBalance = totalCashBalance;
	}

	public Double getBuyingPower() {
		return buyingPower;
	}

	public void setBuyingPower(Double buyingPower) {
		this.buyingPower = buyingPower;
	}

	public Double getAvailableFunds() {
		return availableFunds;
	}

	public void setAvailableFunds(Double availableFunds) {
		this.availableFunds = availableFunds;
	}

	public Double getStockMarketValue() {
		return stockMarketValue;
	}

	public void setStockMarketValue(Double stockMarketValue) {
		this.stockMarketValue = stockMarketValue;
	}

	public Double getOptionMarketValue() {
		return optionMarketValue;
	}

	public void setOptionMarketValue(Double optionMarketValue) {
		this.optionMarketValue = optionMarketValue;
	}

	public Double getFutureOptionValue() {
		return futureOptionValue;
	}

	public void setFutureOptionValue(Double futureOptionValue) {
		this.futureOptionValue = futureOptionValue;
	}

	public Double getEquityWithLoanValue() {
		return equityWithLoanValue;
	}

	public void setEquityWithLoanValue(Double equityWithLoanValue) {
		this.equityWithLoanValue = equityWithLoanValue;
	}

	public Double getSma() {
		return sma;
	}

	public void setSma(Double sma) {
		this.sma = sma;
	}

	public Double getLeverage() {
		return leverage;
	}

	public void setLeverage(Double leverage) {
		this.leverage = leverage;
	}

	public Double getInitMarginReq() {
		return initMarginReq;
	}

	public void setInitMarginReq(Double initMarginReq) {
		this.initMarginReq = initMarginReq;
	}

	public Double getMaintMarginReq() {
		return maintMarginReq;
	}

	public void setMaintMarginReq(Double maintMarginReq) {
		this.maintMarginReq = maintMarginReq;
	}

	public Double getMaintMarginReq_Full() {
		return maintMarginReq_Full;
	}

	public void setMaintMarginReq_Full(Double maintMarginReq_Full) {
		this.maintMarginReq_Full = maintMarginReq_Full;
	}
}
