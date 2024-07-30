package com.greenmark.common.dto.account.timeperiod;

import java.io.Serializable;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountDailyDto</p>
 * <p>Description: This DTO is for the results database account_dailies table.  A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 * DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class AccountDailyDto extends AccountTimePeriodBaseDto implements Serializable {
	public static final String CLASSNAME = "AccountDailyDto";
	private static final long serialVersionUID = 1L;

	protected long accountId = 0; // set to zero to find insert errors

	protected int marketTrendOn = -1;
	protected int marketTrendValue = -1;
	protected int marketTrendGaugeValue = -1;
	protected int marketTrendGaugeNum = -1;
	protected float marketTrendIndicatorValue = 0F;

	/*** These values are stored in the DB and displayed by the website **/
	public String brokerSettledCash;
	public String brokerTotalCash;
	public String brokerStockValue;
	public String brokerSecuritiesOptionsValue;
	public String brokerFuturesOptionsValue;
	public String brokerNetLiquidationValue;
	public String brokerEquityWithLoanValue;
	public String brokerPreviousDayEquityWithLoanValue;
	public String brokerSma;
	public String brokerBuyingPower;
	public String brokerAvailableFunds;
	public String brokerLeverage;
	public String brokerInitialMarginCurrent;
	public String brokerMaintenanceMarginCurrent;
	public String brokerAvailableFundsMarginCurrent;
	public String brokerExcessLiquidityMarginCurrent;
	public String brokerInitialMarginOvernite;
	public String brokerMaintenanceMarginOvernite;
	public String brokerAvailableFundsMarginOvernite;
	public String brokerExcessLiquidityMarginOvernite;

	public AccountDailyDto() {
	}

	public String dbSummary() {
        String stb = " > > " + CLASSNAME + ":" +
                "accountDailyId        [" + id + "] " +
                "accountId             [" + accountId + "] " +
                "marketTrendOn         [" + marketTrendOn + "] " +
                "marketTrendValue      [" + marketTrendValue + "] " +
                "marketTrendGaugeValue [" + marketTrendGaugeValue + "] " +
                "marketTrendGaugeNum   [" + marketTrendGaugeNum + "] " +
                "active                [" + active + "] ";
		return stb;
	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public long getAccountId() {
		return accountId;
	}

	public int getMarketTrendGaugeNum() {
		return marketTrendGaugeNum;
	}

	public int getMarketTrendGaugeValue() {
		return marketTrendGaugeValue;
	}

	public int getMarketTrendOn() {
		return marketTrendOn;
	}

	public int getMarketTrendValue() {
		return marketTrendValue;
	}

	public float getMarketTrendIndicatorValue() {
		return marketTrendIndicatorValue;
	}

	public String getBrokerAvailableFunds() {
		return brokerAvailableFunds;
	}

	public String getBrokerAvailableFundsMarginCurrent() {
		return brokerAvailableFundsMarginCurrent;
	}

	public String getBrokerAvailableFundsMarginOvernite() {
		return brokerAvailableFundsMarginOvernite;
	}

	public String getBrokerBuyingPower() {
		return brokerBuyingPower;
	}

	public String getBrokerEquityWithLoanValue() {
		return brokerEquityWithLoanValue;
	}

	public String getBrokerExcessLiquidityMarginCurrent() {
		return brokerExcessLiquidityMarginCurrent;
	}

	public String getBrokerExcessLiquidityMarginOvernite() {
		return brokerExcessLiquidityMarginOvernite;
	}

	public String getBrokerFuturesOptionsValue() {
		return brokerFuturesOptionsValue;
	}

	public String getBrokerInitialMarginCurrent() {
		return brokerInitialMarginCurrent;
	}

	public String getBrokerInitialMarginOvernite() {
		return brokerInitialMarginOvernite;
	}

	public String getBrokerLeverage() {
		return brokerLeverage;
	}

	public String getBrokerMaintenanceMarginCurrent() {
		return brokerMaintenanceMarginCurrent;
	}

	public String getBrokerMaintenanceMarginOvernite() {
		return brokerMaintenanceMarginOvernite;
	}

	public String getBrokerNetLiquidationValue() {
		return brokerNetLiquidationValue;
	}

	public String getBrokerPreviousDayEquityWithLoanValue() {
		return brokerPreviousDayEquityWithLoanValue;
	}

	public String getBrokerSecuritiesOptionsValue() {
		return brokerSecuritiesOptionsValue;
	}

	public String getBrokerSettledCash() {
		return brokerSettledCash;
	}

	public String getBrokerSma() {
		return brokerSma;
	}

	public String getBrokerStockValue() {
		return brokerStockValue;
	}

	public String getBrokerTotalCash() {
		return brokerTotalCash;
	}

	public void setAccountId(long account_id) {
		this.accountId = account_id;
	}

	public void setMarketTrendValue(int marketTrendValue) {
		this.marketTrendValue = marketTrendValue;
	}

	public void setMarketTrendOn(int marketTrendOn) {
		this.marketTrendOn = marketTrendOn;
	}

	public void setMarketTrendGaugeValue(int marketTrendGaugeValue) {
		this.marketTrendGaugeValue = marketTrendGaugeValue;
	}

	public void setMarketTrendGaugeNum(int marketTrendGaugeNum) {
		this.marketTrendGaugeNum = marketTrendGaugeNum;
	}

	public void setMarketTrendIndicatorValue(float marketTrendIndicatorValue) {
		this.marketTrendIndicatorValue = marketTrendIndicatorValue;
	}

	public void setBrokerAvailableFunds(String brokerAvailableFunds) {
		this.brokerAvailableFunds = brokerAvailableFunds;
	}

	public void setBrokerAvailableFundsMarginCurrent(String brokerAvailableFundsMarginCurrent) {
		this.brokerAvailableFundsMarginCurrent = brokerAvailableFundsMarginCurrent;
	}

	public void setBrokerAvailableFundsMarginOvernite(String brokerAvailableFundsMarginOvernite) {
		this.brokerAvailableFundsMarginOvernite = brokerAvailableFundsMarginOvernite;
	}

	public void setBrokerBuyingPower(String brokerBuyingPower) {
		this.brokerBuyingPower = brokerBuyingPower;
	}

	public void setBrokerEquityWithLoanValue(String brokerEquityWithLoanValue) {
		this.brokerEquityWithLoanValue = brokerEquityWithLoanValue;
	}

	public void setBrokerExcessLiquidityMarginCurrent(String brokerExcessLiquidityMarginCurrent) {
		this.brokerExcessLiquidityMarginCurrent = brokerExcessLiquidityMarginCurrent;
	}

	public void setBrokerExcessLiquidityMarginOvernite(String brokerExcessLiquidityMarginOvernite) {
		this.brokerExcessLiquidityMarginOvernite = brokerExcessLiquidityMarginOvernite;
	}

	public void setBrokerFuturesOptionsValue(String brokerFuturesOptionsValue) {
		this.brokerFuturesOptionsValue = brokerFuturesOptionsValue;
	}

	public void setBrokerInitialMarginCurrent(String brokerInitialMarginCurrent) {
		this.brokerInitialMarginCurrent = brokerInitialMarginCurrent;
	}

	public void setBrokerInitialMarginOvernite(String brokerInitialMarginOvernite) {
		this.brokerInitialMarginOvernite = brokerInitialMarginOvernite;
	}

	public void setBrokerLeverage(String brokerLeverage) {
		this.brokerLeverage = brokerLeverage;
	}

	public void setBrokerMaintenanceMarginCurrent(String brokerMaintenanceMarginCurrent) {
		this.brokerMaintenanceMarginCurrent = brokerMaintenanceMarginCurrent;
	}

	public void setBrokerMaintenanceMarginOvernite(String brokerMaintenanceMarginOvernite) {
		this.brokerMaintenanceMarginOvernite = brokerMaintenanceMarginOvernite;
	}

	public void setBrokerNetLiquidationValue(String brokerNetLiquidationValue) {
		this.brokerNetLiquidationValue = brokerNetLiquidationValue;
	}

	public void setBrokerPreviousDayEquityWithLoanValue(String brokerPreviousDayEquityWithLoanValue) {
		this.brokerPreviousDayEquityWithLoanValue = brokerPreviousDayEquityWithLoanValue;
	}

	public void setBrokerSecuritiesOptionsValue(String brokerSecuritiesOptionsValue) {
		this.brokerSecuritiesOptionsValue = brokerSecuritiesOptionsValue;
	}

	public void setBrokerSettledCash(String brokerSettledCash) {
		this.brokerSettledCash = brokerSettledCash;
	}

	public void setBrokerSma(String brokerSma) {
		this.brokerSma = brokerSma;
	}

	public void setBrokerStockValue(String brokerStockValue) {
		this.brokerStockValue = brokerStockValue;
	}

	public void setBrokerTotalCash(String brokerTotalCash) {
		this.brokerTotalCash = brokerTotalCash;
	}
}
