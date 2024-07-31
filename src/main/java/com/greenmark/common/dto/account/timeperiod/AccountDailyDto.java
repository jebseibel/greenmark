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
    protected long accountId = 0; // set to zero to find insert errors
    protected int marketTrendOn = -1;
    protected int marketTrendValue = -1;
    protected int marketTrendGaugeValue = -1;
    protected int marketTrendGaugeNum = -1;
    protected float marketTrendIndicatorValue = 0F;

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

    public void setAccountId(long account_id) {
        this.accountId = account_id;
    }

    public int getMarketTrendGaugeNum() {
        return marketTrendGaugeNum;
    }

    public void setMarketTrendGaugeNum(int marketTrendGaugeNum) {
        this.marketTrendGaugeNum = marketTrendGaugeNum;
    }

    public int getMarketTrendGaugeValue() {
        return marketTrendGaugeValue;
    }

    public void setMarketTrendGaugeValue(int marketTrendGaugeValue) {
        this.marketTrendGaugeValue = marketTrendGaugeValue;
    }

    public int getMarketTrendOn() {
        return marketTrendOn;
    }

    public void setMarketTrendOn(int marketTrendOn) {
        this.marketTrendOn = marketTrendOn;
    }

    public int getMarketTrendValue() {
        return marketTrendValue;
    }

    public void setMarketTrendValue(int marketTrendValue) {
        this.marketTrendValue = marketTrendValue;
    }

    public float getMarketTrendIndicatorValue() {
        return marketTrendIndicatorValue;
    }

    public void setMarketTrendIndicatorValue(float marketTrendIndicatorValue) {
        this.marketTrendIndicatorValue = marketTrendIndicatorValue;
    }

    public String getBrokerAvailableFunds() {
        return brokerAvailableFunds;
    }

    public void setBrokerAvailableFunds(String brokerAvailableFunds) {
        this.brokerAvailableFunds = brokerAvailableFunds;
    }

    public String getBrokerAvailableFundsMarginCurrent() {
        return brokerAvailableFundsMarginCurrent;
    }

    public void setBrokerAvailableFundsMarginCurrent(String brokerAvailableFundsMarginCurrent) {
        this.brokerAvailableFundsMarginCurrent = brokerAvailableFundsMarginCurrent;
    }

    public String getBrokerAvailableFundsMarginOvernite() {
        return brokerAvailableFundsMarginOvernite;
    }

    public void setBrokerAvailableFundsMarginOvernite(String brokerAvailableFundsMarginOvernite) {
        this.brokerAvailableFundsMarginOvernite = brokerAvailableFundsMarginOvernite;
    }

    public String getBrokerBuyingPower() {
        return brokerBuyingPower;
    }

    public void setBrokerBuyingPower(String brokerBuyingPower) {
        this.brokerBuyingPower = brokerBuyingPower;
    }

    public String getBrokerEquityWithLoanValue() {
        return brokerEquityWithLoanValue;
    }

    public void setBrokerEquityWithLoanValue(String brokerEquityWithLoanValue) {
        this.brokerEquityWithLoanValue = brokerEquityWithLoanValue;
    }

    public String getBrokerExcessLiquidityMarginCurrent() {
        return brokerExcessLiquidityMarginCurrent;
    }

    public void setBrokerExcessLiquidityMarginCurrent(String brokerExcessLiquidityMarginCurrent) {
        this.brokerExcessLiquidityMarginCurrent = brokerExcessLiquidityMarginCurrent;
    }

    public String getBrokerExcessLiquidityMarginOvernite() {
        return brokerExcessLiquidityMarginOvernite;
    }

    public void setBrokerExcessLiquidityMarginOvernite(String brokerExcessLiquidityMarginOvernite) {
        this.brokerExcessLiquidityMarginOvernite = brokerExcessLiquidityMarginOvernite;
    }

    public String getBrokerFuturesOptionsValue() {
        return brokerFuturesOptionsValue;
    }

    public void setBrokerFuturesOptionsValue(String brokerFuturesOptionsValue) {
        this.brokerFuturesOptionsValue = brokerFuturesOptionsValue;
    }

    public String getBrokerInitialMarginCurrent() {
        return brokerInitialMarginCurrent;
    }

    public void setBrokerInitialMarginCurrent(String brokerInitialMarginCurrent) {
        this.brokerInitialMarginCurrent = brokerInitialMarginCurrent;
    }

    public String getBrokerInitialMarginOvernite() {
        return brokerInitialMarginOvernite;
    }

    public void setBrokerInitialMarginOvernite(String brokerInitialMarginOvernite) {
        this.brokerInitialMarginOvernite = brokerInitialMarginOvernite;
    }

    public String getBrokerLeverage() {
        return brokerLeverage;
    }

    public void setBrokerLeverage(String brokerLeverage) {
        this.brokerLeverage = brokerLeverage;
    }

    public String getBrokerMaintenanceMarginCurrent() {
        return brokerMaintenanceMarginCurrent;
    }

    public void setBrokerMaintenanceMarginCurrent(String brokerMaintenanceMarginCurrent) {
        this.brokerMaintenanceMarginCurrent = brokerMaintenanceMarginCurrent;
    }

    public String getBrokerMaintenanceMarginOvernite() {
        return brokerMaintenanceMarginOvernite;
    }

    public void setBrokerMaintenanceMarginOvernite(String brokerMaintenanceMarginOvernite) {
        this.brokerMaintenanceMarginOvernite = brokerMaintenanceMarginOvernite;
    }

    public String getBrokerNetLiquidationValue() {
        return brokerNetLiquidationValue;
    }

    public void setBrokerNetLiquidationValue(String brokerNetLiquidationValue) {
        this.brokerNetLiquidationValue = brokerNetLiquidationValue;
    }

    public String getBrokerPreviousDayEquityWithLoanValue() {
        return brokerPreviousDayEquityWithLoanValue;
    }

    public void setBrokerPreviousDayEquityWithLoanValue(String brokerPreviousDayEquityWithLoanValue) {
        this.brokerPreviousDayEquityWithLoanValue = brokerPreviousDayEquityWithLoanValue;
    }

    public String getBrokerSecuritiesOptionsValue() {
        return brokerSecuritiesOptionsValue;
    }

    public void setBrokerSecuritiesOptionsValue(String brokerSecuritiesOptionsValue) {
        this.brokerSecuritiesOptionsValue = brokerSecuritiesOptionsValue;
    }

    public String getBrokerSettledCash() {
        return brokerSettledCash;
    }

    public void setBrokerSettledCash(String brokerSettledCash) {
        this.brokerSettledCash = brokerSettledCash;
    }

    public String getBrokerSma() {
        return brokerSma;
    }

    public void setBrokerSma(String brokerSma) {
        this.brokerSma = brokerSma;
    }

    public String getBrokerStockValue() {
        return brokerStockValue;
    }

    public void setBrokerStockValue(String brokerStockValue) {
        this.brokerStockValue = brokerStockValue;
    }

    public String getBrokerTotalCash() {
        return brokerTotalCash;
    }

    public void setBrokerTotalCash(String brokerTotalCash) {
        this.brokerTotalCash = brokerTotalCash;
    }
}
