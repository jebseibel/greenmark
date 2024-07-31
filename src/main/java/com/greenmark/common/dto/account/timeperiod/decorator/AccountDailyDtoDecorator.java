package com.greenmark.common.dto.account.timeperiod.decorator;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsStrategy;
import com.greenmark.common.dto.account.timeperiod.AccountDailyDto;
import com.greenmark.utils.UTFormatter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: </p>
 * <p>Description: This decorator class contains convenience methods for its AccountDailyDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
public class AccountDailyDtoDecorator extends AccountDailyDto implements Serializable {
    public static final String CLASSNAME = "AccountDailyDtoDecorator";
    private static final long serialVersionUID = 1L;

    public List<AccountHourlyDtoDecorator> hourlyList;

    public AccountDailyDtoDecorator() {
    }

    /**
     * public void addAccountHourly(AccountHourlyDtoDecorator thisHourly) { hourlyList.put( thisHourly.getId(), thisHourly ); }
     **/
    public AccountDailyDtoDecorator(AccountDailyDto accountDailyDto) {
        hourlyList = new ArrayList<>();

        // FIELDS FOR ACCOUNT DAILY ONLY
        id = accountDailyDto.getId();
        active = accountDailyDto.getActive();
        accountId = accountDailyDto.getAccountId();

        marketTrendOn = accountDailyDto.getMarketTrendOn();
        marketTrendValue = accountDailyDto.getMarketTrendValue();
        marketTrendGaugeValue = accountDailyDto.getMarketTrendGaugeValue();
        marketTrendGaugeNum = accountDailyDto.getMarketTrendGaugeNum();
        marketTrendIndicatorValue = accountDailyDto.getMarketTrendIndicatorValue();

        // Common to both Account Daily and Account Hourly
        totalEquity = accountDailyDto.getTotalEquity();
        cashOnHand = accountDailyDto.getCashOnHand();
        allocatedFunds = accountDailyDto.getAllocatedFunds();
        unsettledFunds = accountDailyDto.getUnsettledFunds();

        totalEquityMargin = accountDailyDto.getTotalEquityMargin();
        cashOnHandMargin = accountDailyDto.getCashOnHandMargin();
        allocatedFundsMargin = accountDailyDto.getAllocatedFundsMargin();
        unsettledFundsMargin = accountDailyDto.getUnsettledFundsMargin();

        longPositionsTotal = accountDailyDto.getLongPositionsTotal();
        shortPositionsTotal = accountDailyDto.getShortPositionsTotal();

        ibTotalEquity = accountDailyDto.getIbTotalEquity();
        ibAllocatedFunds = accountDailyDto.getIbAllocatedFunds();
        ibCashOnHand = accountDailyDto.getIbCashOnHand();
        ibUnsettledFunds = accountDailyDto.getIbUnsettledFunds();

        thisDate = accountDailyDto.getThisDate();
        numdate = accountDailyDto.getNumdate();

        avgNumDailyBuyLong = accountDailyDto.getAvgNumDailyBuyLong();
        avgNumMinute60BuyLong = accountDailyDto.getAvgNumMinute60BuyLong();
        totalNumMinute15BuyLong = accountDailyDto.getTotalNumMinute15BuyLong();
        totalNumMinute05BuyLong = accountDailyDto.getTotalNumMinute05BuyLong();
        totalNumMinute01BuyLong = accountDailyDto.getTotalNumMinute01BuyLong();

        avgNumDailySellLong = accountDailyDto.getAvgNumDailySellLong();
        avgNumMinute60SellLong = accountDailyDto.getAvgNumMinute60SellLong();
        totalNumMinute15SellLong = accountDailyDto.getTotalNumMinute15SellLong();
        totalNumMinute05SellLong = accountDailyDto.getTotalNumMinute05SellLong();
        totalNumMinute01SellLong = accountDailyDto.getTotalNumMinute01SellLong();

        avgNumDailyBuyShort = accountDailyDto.getAvgNumDailyBuyShort();
        avgNumMinute60BuyShort = accountDailyDto.getAvgNumMinute60BuyShort();
        totalNumMinute15BuyShort = accountDailyDto.getTotalNumMinute15BuyShort();
        totalNumMinute05BuyShort = accountDailyDto.getTotalNumMinute05BuyShort();
        totalNumMinute01BuyShort = accountDailyDto.getTotalNumMinute01BuyShort();

        avgNumDailySellShort = accountDailyDto.getAvgNumDailySellShort();
        avgNumMinute60SellShort = accountDailyDto.getAvgNumMinute60SellShort();
        totalNumMinute15SellShort = accountDailyDto.getTotalNumMinute15SellShort();
        totalNumMinute05SellShort = accountDailyDto.getTotalNumMinute05SellShort();
        totalNumMinute01SellShort = accountDailyDto.getTotalNumMinute01SellShort();

        // orders
        totalNumBuyLongOrdersMin05 = accountDailyDto.getTotalNumBuyLongOrdersMin05();
        totalNumSellLongOrdersMin05 = accountDailyDto.getTotalNumSellLongOrdersMin05();
        totalNumBuyShortOrdersMin05 = accountDailyDto.getTotalNumBuyShortOrdersMin05();
        totalNumSellShortOrdersMin05 = accountDailyDto.getTotalNumSellShortOrdersMin05();

        totalNumBuyLongOrdersMin01 = accountDailyDto.getTotalNumBuyLongOrdersMin01();
        totalNumSellLongOrdersMin01 = accountDailyDto.getTotalNumSellLongOrdersMin01();
        totalNumBuyShortOrdersMin01 = accountDailyDto.getTotalNumBuyShortOrdersMin01();
        totalNumSellShortOrdersMin01 = accountDailyDto.getTotalNumSellShortOrdersMin01();

        // positions
        avgNumPositionsLong = accountDailyDto.getAvgNumPositionsLong();
        avgNumPositionsShort = accountDailyDto.getAvgNumPositionsShort();

        // rejects
        totalNumDailyLongReject = accountDailyDto.getTotalNumDailyLongReject();
        totalNumMinute60LongReject = accountDailyDto.getTotalNumMinute60LongReject();
        totalNumMinute15LongReject = accountDailyDto.getTotalNumMinute15LongReject();
        totalNumMinute05LongReject = accountDailyDto.getTotalNumMinute05LongReject();

        totalNumDailyShortReject = accountDailyDto.getTotalNumDailyShortReject();
        totalNumMinute60ShortReject = accountDailyDto.getTotalNumMinute60ShortReject();
        totalNumMinute15ShortReject = accountDailyDto.getTotalNumMinute15ShortReject();
        totalNumMinute05ShortReject = accountDailyDto.getTotalNumMinute05ShortReject();

        // reject orders
        totalNumOrderLongRejectMinute05 = accountDailyDto.getTotalNumOrderLongRejectMinute05();
        totalNumOrderShortRejectMinute05 = accountDailyDto.getTotalNumOrderShortRejectMinute05();
        totalNumOrderLongRejectMinute01 = accountDailyDto.getTotalNumOrderLongRejectMinute01();
        totalNumOrderShortRejectMinute01 = accountDailyDto.getTotalNumOrderShortRejectMinute01();
        netLiquidationValue = accountDailyDto.getNetLiquidationValue();

        // Saved broker values
        brokerSettledCash = accountDailyDto.getBrokerSettledCash();
        brokerTotalCash = accountDailyDto.getBrokerTotalCash();
        brokerStockValue = accountDailyDto.getBrokerStockValue();
        brokerSecuritiesOptionsValue = accountDailyDto.getBrokerSecuritiesOptionsValue();
        brokerFuturesOptionsValue = accountDailyDto.getBrokerFuturesOptionsValue();
        brokerNetLiquidationValue = accountDailyDto.getBrokerNetLiquidationValue();
        brokerEquityWithLoanValue = accountDailyDto.getBrokerEquityWithLoanValue();
        brokerPreviousDayEquityWithLoanValue = accountDailyDto.getBrokerPreviousDayEquityWithLoanValue();
        brokerSma = accountDailyDto.getBrokerSma();
        brokerBuyingPower = accountDailyDto.getBrokerBuyingPower();
        brokerAvailableFunds = accountDailyDto.getBrokerAvailableFunds();
        brokerLeverage = accountDailyDto.getBrokerLeverage();
        brokerInitialMarginCurrent = accountDailyDto.getBrokerInitialMarginCurrent();
        brokerMaintenanceMarginCurrent = accountDailyDto.getBrokerMaintenanceMarginCurrent();
        brokerAvailableFundsMarginCurrent = accountDailyDto.getBrokerAvailableFundsMarginCurrent();
        brokerExcessLiquidityMarginCurrent = accountDailyDto.getBrokerExcessLiquidityMarginCurrent();
        brokerInitialMarginOvernite = accountDailyDto.getBrokerInitialMarginOvernite();
        brokerMaintenanceMarginOvernite = accountDailyDto.getBrokerMaintenanceMarginOvernite();
        brokerAvailableFundsMarginOvernite = accountDailyDto.getBrokerAvailableFundsMarginOvernite();
        brokerExcessLiquidityMarginOvernite = accountDailyDto.getBrokerExcessLiquidityMarginOvernite();

    }

    public void initHourlyLists() {
        hourlyList = null;
        hourlyList = new ArrayList<>();
    }

    public boolean isHasHourly() {
        return hourlyList.size() > 0;
    }

    public String getMarketTrendValueDisplay() {
        if (marketTrendOn == GmConstants.IS_FALSE)
            return "N/A";

        StringBuffer returnVal = new StringBuffer();

        if (marketTrendValue == GmConstantsStrategy.MKT_TREND_TYPE_BEAR_STRATEGY_INT) {
            returnVal.append("<font color=\"Black\"><b>");
            returnVal.append(UTFormatter.roundDouble1Digit(marketTrendIndicatorValue));
            returnVal.append("</b></font>");
        }
        if (marketTrendValue == GmConstantsStrategy.MKT_TREND_TYPE_BEAR_TRENDING_INT) {
            returnVal.append("<font color=\"Red\"><b>");
            returnVal.append(UTFormatter.roundDouble1Digit(marketTrendIndicatorValue));
            returnVal.append("</b></font>");
        }
        if (marketTrendValue == GmConstantsStrategy.MKT_TREND_TYPE_BEAR_INT) {
            returnVal.append("<font color=\"Blue\"><b>");
            returnVal.append(UTFormatter.roundDouble1Digit(marketTrendIndicatorValue));
            returnVal.append("</b></font>");
        } else if (marketTrendValue == GmConstantsStrategy.MKT_TREND_TYPE_BULL_INT) {
            returnVal.append("<font color=\"Green\"><b>");
            returnVal.append(UTFormatter.roundDouble1Digit(marketTrendIndicatorValue));
            returnVal.append("</b></font>");
        } else if (marketTrendValue == GmConstantsStrategy.MKT_TREND_TYPE_BULL_TRENDING_INT) {
            returnVal.append("<font color=\"Red\"><b>");
            returnVal.append(UTFormatter.roundDouble1Digit(marketTrendIndicatorValue));
            returnVal.append("</b></font>");
        } else if (marketTrendValue == GmConstantsStrategy.MKT_TREND_TYPE_BULL_STRATEGY_INT) {
            returnVal.append("<font color=\"Black\"><b>");
            returnVal.append(UTFormatter.roundDouble1Digit(marketTrendIndicatorValue));
            returnVal.append("</b></font>");
        } else if (marketTrendValue == GmConstantsStrategy.MKT_TREND_TYPE_FLAT_INT) {
            returnVal.append("<font color=\"Gray\"><b>");
            returnVal.append(UTFormatter.roundDouble1Digit(marketTrendIndicatorValue));
            returnVal.append("</b></font>");
        }

        return returnVal.toString();
    }

    public String getClassname() {
        return CLASSNAME;
    }

    public String getNetLiquidationValueDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(netLiquidationValue);
        return returnString;
    }

    public String getGrandAllocatedFundsDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(allocatedFunds + allocatedFundsMargin);
        return returnString;
    }

    public String getGrandUnsettledFundsDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(unsettledFunds + unsettledFundsMargin);
        return returnString;
    }

    public String getGrandTotalEquityDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(totalEquity + totalEquityMargin);
        return returnString;
    }

    public String getGrandCashOnHandDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(cashOnHand + cashOnHandMargin);
        return returnString;
    }

    public String getAllocatedFundsDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(allocatedFunds);
        return returnString;
    }

    public String getUnsettledFundsDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(unsettledFunds);
        return returnString;
    }

    public String getUnsettledFundsMarginDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(unsettledFundsMargin);
        return returnString;
    }

    public String getTotalEquityDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(totalEquity);
        return returnString;
    }

    public String getTotalEquityMarginDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(totalEquityMargin);
        return returnString;
    }

    public String getCashOnHandDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(cashOnHand);
        return returnString;
    }

    public String getCashOnHandMarginDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(cashOnHandMargin);
        return returnString;
    }

    public String getGrandPositionsTotalDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(longPositionsTotal + shortPositionsTotal);
        return returnString;
    }

    public String getLongPositionsTotalDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(longPositionsTotal);
        return returnString;
    }

    public String getShortPositionsTotalDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(shortPositionsTotal);
        return returnString;
    }

    public String getIbTotalEquityDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(ibTotalEquity);
        return returnString;
    }

    public String getIbAllocatedFundsDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(ibAllocatedFunds);
        return returnString;
    }

    public String getIbCashOnHandDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(ibCashOnHand);
        return returnString;
    }

    public String getIbUnsettledFundsDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(ibUnsettledFunds);
        return returnString;
    }

    // BUCKET DISPLAYS ON WEB
    public String getAvgNumPositionsLongDisplay() {
        String returnString = UTFormatter.roundDouble1Digit(getAvgNumPositionsLong());
        return returnString;
    }

    // BUCKET DISPLAYS ON WEB
    public String getAvgNumPositionsShortDisplay() {
        String returnString = UTFormatter.roundDouble1Digit(getAvgNumPositionsShort());
        return returnString;
    }

    // ORDER/POSITION DISPLAYS ON WEB
    public String getBuyLongNumOrdersDisplay() {
        String returnString = UTFormatter.formatDoubleNoPennies(getTotalNumBuyLongOrdersMin05());
        return returnString;
    }

    public String getBuyShortNumOrdersDisplay() {
        String returnString = UTFormatter.formatDoubleNoPennies(getTotalNumBuyShortOrdersMin05());
        return returnString;
    }

    public String getBuyLongNumOrdersDisplayMin01() {
        String returnString = UTFormatter.formatDoubleNoPennies(getTotalNumBuyLongOrdersMin01());
        return returnString;
    }

    public String getBuyShortNumOrdersDisplayMin01() {
        String returnString = UTFormatter.formatDoubleNoPennies(getTotalNumBuyShortOrdersMin01());
        return returnString;
    }

    // BUCKET DISPLAYS ON WEB
    public String getBuyLongDailyAvgNumDisplay() {
        String returnString = UTFormatter.roundDouble1Digit(getAvgNumDailyBuyLong());
        return returnString;
    }

    public String getBuyLongMinute60AvgNumDisplay() {
        String returnString = UTFormatter.roundDouble1Digit(getAvgNumMinute60BuyLong());
        return returnString;
    }

    public String getBuyLongMinute15AvgNumDisplay() {
        String returnString = UTFormatter.formatDoubleNoPennies(getTotalNumMinute15BuyLong());
        return returnString;
    }

    public String getBuyLongMinute05AvgNumDisplay() {
        String returnString = UTFormatter.formatDoubleNoPennies(getTotalNumMinute05BuyLong());
        return returnString;
    }

    public String getBuyLongMinute01AvgNumDisplay() {
        String returnString = UTFormatter.formatDoubleNoPennies(getTotalNumMinute01BuyLong());
        return returnString;
    }

    // BUCKET DISPLAYS ON WEB
    public String getBuyShortDailyAvgNumDisplay() {
        String returnString = UTFormatter.roundDouble1Digit(getAvgNumDailyBuyShort());
        return returnString;
    }

    public String getBuyShortMinute60AvgNumDisplay() {
        String returnString = UTFormatter.roundDouble1Digit(getAvgNumMinute60BuyShort());
        return returnString;
    }

    public String getBuyShortMinute15AvgNumDisplay() {
        String returnString = UTFormatter.formatDoubleNoPennies(getTotalNumMinute15BuyShort());
        return returnString;
    }

    public String getBuyShortMinute05AvgNumDisplay() {
        String returnString = UTFormatter.formatDoubleNoPennies(getTotalNumMinute05BuyShort());
        return returnString;
    }

    public String getBuyShortMinute01AvgNumDisplay() {
        String returnString = UTFormatter.formatDoubleNoPennies(getTotalNumMinute01BuyShort());
        return returnString;
    }

    // LONG DISQUALIFIED DISPLAYS ON WEB
    public String getBuyLongNumDailyDisqualifiedDisplay() {
        String returnString = Integer.toString(getTotalNumDailyLongReject());
        return returnString;
    }

    public String getBuyLongNumMinute60DisqualifiedDisplay() {
        String returnString = Integer.toString(getTotalNumMinute60LongReject());
        return returnString;
    }

    public String getBuyLongNumMinute15DisqualifiedDisplay() {
        String returnString = Integer.toString(getTotalNumMinute15LongReject());
        return returnString;
    }

    public String getBuyLongNumMinute05DisqualifiedDisplay() {
        String returnString = Integer.toString(getTotalNumMinute05LongReject());
        return returnString;
    }

    public String getBuyLongNumMinute05OrderDisqualifiedDisplay() {
        String returnString = Integer.toString(getTotalNumOrderLongRejectMinute05());
        return returnString;
    }

    public String getBuyLongNumMinute01DisqualifiedDisplay() {
        String returnString = Integer.toString(getTotalNumOrderLongRejectMinute01());
        return returnString;
    }

    // SHORT DISQUALIFIED DISPLAYS ON WEB
    public String getBuyShortNumDailyDisqualifiedDisplay() {
        String returnString = Integer.toString(getTotalNumDailyShortReject());
        return returnString;
    }

    public String getBuyShortNumMinute60DisqualifiedDisplay() {
        String returnString = Integer.toString(getTotalNumMinute60ShortReject());
        return returnString;
    }

    public String getBuyShortNumMinute15DisqualifiedDisplay() {
        String returnString = Integer.toString(getTotalNumMinute15ShortReject());
        return returnString;
    }

    public String getBuyShortNumMinute05DisqualifiedDisplay() {
        String returnString = Integer.toString(getTotalNumMinute05ShortReject());
        return returnString;
    }

    public String getBuyShortNumMinute05OrderDisqualifiedDisplay() {
        String returnString = Integer.toString(getTotalNumOrderShortRejectMinute05());
        return returnString;
    }

    public String getBuyShortNumMinute01DisqualifiedDisplay() {
        String returnString = Integer.toString(getTotalNumOrderShortRejectMinute01());
        return returnString;
    }

    public List<AccountHourlyDtoDecorator> getHourlyList() {
        return hourlyList;
    }

    public void setHourlyList(List<AccountHourlyDtoDecorator> hourlyList) {
        this.hourlyList = hourlyList;
    }

}
