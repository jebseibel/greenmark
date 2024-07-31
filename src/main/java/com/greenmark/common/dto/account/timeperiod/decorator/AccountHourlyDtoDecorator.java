package com.greenmark.common.dto.account.timeperiod.decorator;

import com.greenmark.common.dto.account.timeperiod.AccountHourlyDto;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTFormatter;

import java.io.Serializable;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountHourlyDtoDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its AccountHourlyDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
public class AccountHourlyDtoDecorator extends AccountHourlyDto implements Serializable {
    public static final String CLASSNAME = "AccountHourlyDtoDecorator";
    private static final long serialVersionUID = 1L;

    public AccountHourlyDtoDecorator() {
    }

    public AccountHourlyDtoDecorator(AccountHourlyDto accountDaily) {
        id = accountDaily.getId();
        active = accountDaily.getActive();
        accountDailyId = accountDaily.getAccountDailyId();

        totalEquity = accountDaily.getTotalEquity();
        cashOnHand = accountDaily.getCashOnHand();
        allocatedFunds = accountDaily.getAllocatedFunds();
        unsettledFunds = accountDaily.getUnsettledFunds();

        totalEquityMargin = accountDaily.getTotalEquityMargin();
        cashOnHandMargin = accountDaily.getCashOnHandMargin();
        allocatedFundsMargin = accountDaily.getAllocatedFundsMargin();
        unsettledFundsMargin = accountDaily.getUnsettledFundsMargin();

        longPositionsTotal = accountDaily.getLongPositionsTotal();
        shortPositionsTotal = accountDaily.getShortPositionsTotal();

        thisDate = accountDaily.getThisDate();
        numdate = accountDaily.getNumdate();

        avgNumDailyBuyLong = accountDaily.getAvgNumDailyBuyLong();
        avgNumMinute60BuyLong = accountDaily.getAvgNumMinute60BuyLong();
        totalNumMinute15BuyLong = accountDaily.getTotalNumMinute15BuyLong();
        totalNumMinute05BuyLong = accountDaily.getTotalNumMinute05BuyLong();
        totalNumMinute01BuyLong = accountDaily.getTotalNumMinute01BuyLong();

        avgNumDailySellLong = accountDaily.getAvgNumDailySellLong();
        avgNumMinute60SellLong = accountDaily.getAvgNumMinute60SellLong();
        totalNumMinute15SellLong = accountDaily.getTotalNumMinute15SellLong();
        totalNumMinute05SellLong = accountDaily.getTotalNumMinute05SellLong();
        totalNumMinute01SellLong = accountDaily.getTotalNumMinute01SellLong();

        avgNumDailyBuyShort = accountDaily.getAvgNumDailyBuyShort();
        avgNumMinute60BuyShort = accountDaily.getAvgNumMinute60BuyShort();
        totalNumMinute15BuyShort = accountDaily.getTotalNumMinute15BuyShort();
        totalNumMinute05BuyShort = accountDaily.getTotalNumMinute05BuyShort();
        totalNumMinute01BuyShort = accountDaily.getTotalNumMinute01BuyShort();

        avgNumDailySellShort = accountDaily.getAvgNumDailySellShort();
        avgNumMinute60SellShort = accountDaily.getAvgNumMinute60SellShort();
        totalNumMinute15SellShort = accountDaily.getTotalNumMinute15SellShort();
        totalNumMinute05SellShort = accountDaily.getTotalNumMinute05SellShort();
        totalNumMinute01SellShort = accountDaily.getTotalNumMinute01SellShort();

        // orders
        totalNumBuyLongOrdersMin05 = accountDaily.getTotalNumBuyLongOrdersMin05();
        totalNumSellLongOrdersMin05 = accountDaily.getTotalNumSellLongOrdersMin05();
        totalNumBuyShortOrdersMin05 = accountDaily.getTotalNumBuyShortOrdersMin05();
        totalNumSellShortOrdersMin05 = accountDaily.getTotalNumSellShortOrdersMin05();

        totalNumBuyLongOrdersMin01 = accountDaily.getTotalNumBuyLongOrdersMin01();
        totalNumSellLongOrdersMin01 = accountDaily.getTotalNumSellLongOrdersMin01();
        totalNumBuyShortOrdersMin01 = accountDaily.getTotalNumBuyShortOrdersMin01();
        totalNumSellShortOrdersMin01 = accountDaily.getTotalNumSellShortOrdersMin01();

        // positions
        avgNumPositionsLong = accountDaily.getAvgNumPositionsLong();
        avgNumPositionsShort = accountDaily.getAvgNumPositionsShort();

        // rejects
        totalNumDailyLongReject = accountDaily.getTotalNumDailyLongReject();
        totalNumMinute60LongReject = accountDaily.getTotalNumMinute60LongReject();
        totalNumMinute15LongReject = accountDaily.getTotalNumMinute15LongReject();
        totalNumMinute05LongReject = accountDaily.getTotalNumMinute05LongReject();

        totalNumDailyShortReject = accountDaily.getTotalNumDailyShortReject();
        totalNumMinute60ShortReject = accountDaily.getTotalNumMinute60ShortReject();
        totalNumMinute15ShortReject = accountDaily.getTotalNumMinute15ShortReject();
        totalNumMinute05ShortReject = accountDaily.getTotalNumMinute05ShortReject();

        // reject orders
        totalNumOrderLongRejectMinute05 = accountDaily.getTotalNumOrderLongRejectMinute05();
        totalNumOrderShortRejectMinute05 = accountDaily.getTotalNumOrderShortRejectMinute05();
        totalNumOrderLongRejectMinute01 = accountDaily.getTotalNumOrderLongRejectMinute01();
        totalNumOrderShortRejectMinute01 = accountDaily.getTotalNumOrderShortRejectMinute01();

        netLiquidationValue = accountDaily.getNetLiquidationValue();
    }

    public String getClassname() {
        return CLASSNAME;
    }

    public String getThisTimeString() {
        return UTDatetime.toTimeString(thisDate);
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

    public String getTotalEquityDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(totalEquity);
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

    public String getLongPositionsTotalDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(longPositionsTotal);
        return returnString;
    }

    public String getTotalEquityMarginDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(totalEquityMargin);
        return returnString;
    }

    public String getShortPositionsTotalDisplay() {
        String returnString = "$" + UTFormatter.formatPrice(shortPositionsTotal);
        return returnString;
    }

    public String getAvgNumPositionsLongDisplay() {
        String returnString = UTFormatter.roundDouble1Digit(getAvgNumPositionsLong());
        return returnString;
    }

    public String getAvgNumPositionsShortDisplay() {
        String returnString = UTFormatter.roundDouble1Digit(getAvgNumPositionsShort());
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

    // ORDER/POSITION DISPLAYS ON WEB
    public String getBuyLongNumOrdersDisplay() {
        String returnString = UTFormatter.formatDoubleNoPennies(getTotalNumBuyLongOrdersMin05());
        return returnString;
    }

    public String getBuyShortNumOrdersDisplay() {
        String returnString = UTFormatter.formatDoubleNoPennies(getTotalNumBuyShortOrdersMin05());
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

    public String getBuyLongNumMinute05OrdersDisqualifiedDisplay() {
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

    public String getBuyShortNumMinute05OrdersDisqualifiedDisplay() {
        String returnString = Integer.toString(getTotalNumOrderShortRejectMinute05());
        return returnString;
    }

    public String getBuyShortNumMinute01DisqualifiedDisplay() {
        String returnString = Integer.toString(getTotalNumOrderShortRejectMinute01());
        return returnString;
    }

}
