package com.greenmark.account;

import com.greenmark.common.core.Labels;
import com.greenmark.utils.UTDisplayFormatter;

import java.util.Date;

public class AccountTimePeriodBase {
    /**
     * This is the class name.
     */
    public static final String CLASSNAME = "AccountTimePeriodBase";

    /**
     * Used by the database
     **/
    protected long id;  //leave as null for hibernate
    protected int active = Labels.OBJECT_ACTIVE;
    /**************************/

    protected double totalEquity;
    protected double cashOnHand;
    protected double allocatedFunds;
    protected double unsettledFunds;

    protected double totalEquityMargin;
    protected double cashOnHandMargin;
    protected double allocatedFundsMargin;
    protected double unsettledFundsMargin;

    protected double longPositionsTotal;
    protected double shortPositionsTotal;

    protected Date thisDate;
    protected long numdate;

    protected double avgNumDailyBuyLong;
    protected double avgNumMinute60BuyLong;
    protected double totalNumMinute15BuyLong;
    protected double totalNumMinute05BuyLong;

    protected double avgNumDailySellLong;
    protected double avgNumMinute60SellLong;
    protected double totalNumMinute15SellLong;
    protected double totalNumMinute05SellLong;

    protected double avgNumDailyBuyShort;
    protected double avgNumMinute60BuyShort;
    protected double totalNumMinute15BuyShort;
    protected double totalNumMinute05BuyShort;

    protected double avgNumDailySellShort;
    protected double avgNumMinute60SellShort;
    protected double totalNumMinute15SellShort;
    protected double totalNumMinute05SellShort;

    //orders
    protected double totalNumBuyLongOrdersMin05;
    protected double totalNumSellLongOrdersMin05;
    protected double totalNumBuyShortOrdersMin05;
    protected double totalNumSellShortOrdersMin05;

    //positions
    protected double avgNumPositionsLong;
    protected double avgNumPositionsShort;

    //rejects
    protected int totalNumDailyLongReject;
    protected int totalNumMinute60LongReject;
    protected int totalNumMinute15LongReject;

    protected int totalNumDailyShortReject;
    protected int totalNumMinute60ShortReject;
    protected int totalNumMinute15ShortReject;

    //reject orders
    protected int totalNumOrderLongReject;
    protected int totalNumOrderShortReject;

    protected String thisDateString;

    public AccountTimePeriodBase() {
        // empty constructor
    }

    ///////////////////////////////////////////////////////////////
    //  CONVENIENCE METHODS FOR CASH AND MARGIN ACCOUNTS
    ///////////////////////////////////////////////////////////////
    public double getGrandTotalEquity() {
        return getTotalEquity() + getTotalEquityMargin();
    }

    public double getGrandAllocatedFunds() {
        return getAllocatedFunds() + getAllocatedFundsMargin();
    }

    public double getGrandCashOnHand() {
        return getCashOnHand() + getCashOnHandMargin();
    }

    public double getGrandAvailableFunds() {
        return getGrandCashOnHand(); // Available funds is the same as cash on hand.
    }

    public double getGrandUnsettledFunds() {
        return getUnsettledFunds() + getUnsettledFundsMargin();
    }


    public double getAllocatedFunds() {
        return allocatedFunds;
    }

    public void setAllocatedFunds(double allocatedFunds) {
        this.allocatedFunds = allocatedFunds;
    }

    public double getCashOnHand() {
        return cashOnHand;
    }

    public void setCashOnHand(double cashOnHand) {
        this.cashOnHand = cashOnHand;
    }

    public double getLongPositionsTotal() {
        return longPositionsTotal;
    }

    public void setLongPositionsTotal(double longPositionsTotal) {
        this.longPositionsTotal = longPositionsTotal;
    }

    public double getShortPositionsTotal() {
        return shortPositionsTotal;
    }

    public void setShortPositionsTotal(double shortPositionsTotal) {
        this.shortPositionsTotal = shortPositionsTotal;
    }

    public double getTotalEquity() {
        return totalEquity;
    }

    public void setTotalEquity(double totalEquity) {
        this.totalEquity = totalEquity;
    }

    public String toString() {
        String ret = "id=" + id + ":";
        return ret;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int value) {
        this.active = value;
    }

    public Date getThisDate() {
        return thisDate;
    }

    public void setThisDate(Date thisDate) {
        this.thisDate = thisDate;
    }

    public String getThisDateString() {
        return UTDisplayFormatter.formatDate(thisDate);
    }

    public void setThisDateString(String thisDateString) {
        this.thisDateString = thisDateString;
    }

    public String getThisTimeString() {
        return UTDisplayFormatter.formatTime(thisDate);
    }

    public String dbSummary() {
        String stb = " > > " + CLASSNAME + ":" +
                "id [" + id + "] " +
                "active [" + active + "] ";
        return stb;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getUnsettledFunds() {
        return unsettledFunds;
    }

    public void setUnsettledFunds(double unsettledFunds) {
        this.unsettledFunds = unsettledFunds;
    }

    public double getTotalEquityMargin() {
        return totalEquityMargin;
    }

    public void setTotalEquityMargin(double totalEquityMargin) {
        this.totalEquityMargin = totalEquityMargin;
    }

    public double getCashOnHandMargin() {
        return cashOnHandMargin;
    }

    public void setCashOnHandMargin(double cashOnHandMargin) {
        this.cashOnHandMargin = cashOnHandMargin;
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

    public double getTotalNumBuyLongOrdersMin05() {
        return totalNumBuyLongOrdersMin05;
    }

    public void setTotalNumBuyLongOrdersMin05(double avgNumBuyLongOrdersMin05) {
        this.totalNumBuyLongOrdersMin05 = avgNumBuyLongOrdersMin05;
    }

    public double getTotalNumBuyShortOrdersMin05() {
        return totalNumBuyShortOrdersMin05;
    }

    public void setTotalNumBuyShortOrdersMin05(double avgNumBuyShortOrdersMin05) {
        this.totalNumBuyShortOrdersMin05 = avgNumBuyShortOrdersMin05;
    }

    public double getAvgNumDailyBuyLong() {
        return avgNumDailyBuyLong;
    }

    public void setAvgNumDailyBuyLong(double avgNumDailyBuyLong) {
        this.avgNumDailyBuyLong = avgNumDailyBuyLong;
    }

    public double getAvgNumDailyBuyShort() {
        return avgNumDailyBuyShort;
    }

    public void setAvgNumDailyBuyShort(double avgNumDailyBuyShort) {
        this.avgNumDailyBuyShort = avgNumDailyBuyShort;
    }

    public int getTotalNumDailyLongReject() {
        return totalNumDailyLongReject;
    }

    public void setTotalNumDailyLongReject(int avgNumDailyLongReject) {
        this.totalNumDailyLongReject = avgNumDailyLongReject;
    }

    public double getAvgNumDailySellLong() {
        return avgNumDailySellLong;
    }

    public void setAvgNumDailySellLong(double avgNumDailySellLong) {
        this.avgNumDailySellLong = avgNumDailySellLong;
    }

    public double getAvgNumDailySellShort() {
        return avgNumDailySellShort;
    }

    public void setAvgNumDailySellShort(double avgNumDailySellShort) {
        this.avgNumDailySellShort = avgNumDailySellShort;
    }

    public int getTotalNumDailyShortReject() {
        return totalNumDailyShortReject;
    }

    public void setTotalNumDailyShortReject(int avgNumDailyShortReject) {
        this.totalNumDailyShortReject = avgNumDailyShortReject;
    }

    public double getTotalNumMinute05BuyLong() {
        return totalNumMinute05BuyLong;
    }

    public void setTotalNumMinute05BuyLong(double avgNumMinute05BuyLong) {
        this.totalNumMinute05BuyLong = avgNumMinute05BuyLong;
    }

    public double getTotalNumMinute05BuyShort() {
        return totalNumMinute05BuyShort;
    }

    public void setTotalNumMinute05BuyShort(double totalNumMinute05BuyShort) {
        this.totalNumMinute05BuyShort = totalNumMinute05BuyShort;
    }

    public double getTotalNumMinute05SellLong() {
        return totalNumMinute05SellLong;
    }

    public void setTotalNumMinute05SellLong(double totalNumMinute05SellLong) {
        this.totalNumMinute05SellLong = totalNumMinute05SellLong;
    }

    public double getTotalNumMinute05SellShort() {
        return totalNumMinute05SellShort;
    }

    public void setTotalNumMinute05SellShort(double totalNumMinute05SellShort) {
        this.totalNumMinute05SellShort = totalNumMinute05SellShort;
    }

    public double getTotalNumMinute15BuyLong() {
        return totalNumMinute15BuyLong;
    }

    public void setTotalNumMinute15BuyLong(double totalNumMinute15BuyLong) {
        this.totalNumMinute15BuyLong = totalNumMinute15BuyLong;
    }

    public double getTotalNumMinute15BuyShort() {
        return totalNumMinute15BuyShort;
    }

    public void setTotalNumMinute15BuyShort(double totalNumMinute15BuyShort) {
        this.totalNumMinute15BuyShort = totalNumMinute15BuyShort;
    }

    public int getTotalNumMinute15LongReject() {
        return totalNumMinute15LongReject;
    }

    public void setTotalNumMinute15LongReject(int totalNumMinute15LongReject) {
        this.totalNumMinute15LongReject = totalNumMinute15LongReject;
    }

    public double getTotalNumMinute15SellLong() {
        return totalNumMinute15SellLong;
    }

    public void setTotalNumMinute15SellLong(double totalNumMinute15SellLong) {
        this.totalNumMinute15SellLong = totalNumMinute15SellLong;
    }

    public double getTotalNumMinute15SellShort() {
        return totalNumMinute15SellShort;
    }

    public void setTotalNumMinute15SellShort(double totalNumMinute15SellShort) {
        this.totalNumMinute15SellShort = totalNumMinute15SellShort;
    }

    public int getTotalNumMinute15ShortReject() {
        return totalNumMinute15ShortReject;
    }

    public void setTotalNumMinute15ShortReject(int avgNumMinute15ShortReject) {
        this.totalNumMinute15ShortReject = avgNumMinute15ShortReject;
    }

    public double getAvgNumMinute60BuyLong() {
        return avgNumMinute60BuyLong;
    }

    public void setAvgNumMinute60BuyLong(double avgNumMinute60BuyLong) {
        this.avgNumMinute60BuyLong = avgNumMinute60BuyLong;
    }

    public double getAvgNumMinute60BuyShort() {
        return avgNumMinute60BuyShort;
    }

    public void setAvgNumMinute60BuyShort(double avgNumMinute60BuyShort) {
        this.avgNumMinute60BuyShort = avgNumMinute60BuyShort;
    }

    public int getTotalNumMinute60LongReject() {
        return totalNumMinute60LongReject;
    }

    public void setTotalNumMinute60LongReject(int avgNumMinute60LongReject) {
        this.totalNumMinute60LongReject = avgNumMinute60LongReject;
    }

    public double getAvgNumMinute60SellLong() {
        return avgNumMinute60SellLong;
    }

    public void setAvgNumMinute60SellLong(double avgNumMinute60SellLong) {
        this.avgNumMinute60SellLong = avgNumMinute60SellLong;
    }

    public double getAvgNumMinute60SellShort() {
        return avgNumMinute60SellShort;
    }

    public void setAvgNumMinute60SellShort(double avgNumMinute60SellShort) {
        this.avgNumMinute60SellShort = avgNumMinute60SellShort;
    }

    public int getTotalNumMinute60ShortReject() {
        return totalNumMinute60ShortReject;
    }

    public void setTotalNumMinute60ShortReject(int avgNumMinute60ShortReject) {
        this.totalNumMinute60ShortReject = avgNumMinute60ShortReject;
    }

    public int getTotalNumOrderLongReject() {
        return totalNumOrderLongReject;
    }

    public void setTotalNumOrderLongReject(int avgNumOrderLongReject) {
        this.totalNumOrderLongReject = avgNumOrderLongReject;
    }

    public int getTotalNumOrderShortReject() {
        return totalNumOrderShortReject;
    }

    public void setTotalNumOrderShortReject(int avgNumOrderShortReject) {
        this.totalNumOrderShortReject = avgNumOrderShortReject;
    }

    public double getTotalNumSellLongOrdersMin05() {
        return totalNumSellLongOrdersMin05;
    }

    public void setTotalNumSellLongOrdersMin05(double totalNumSellLongOrdersMin05) {
        this.totalNumSellLongOrdersMin05 = totalNumSellLongOrdersMin05;
    }

    public double getTotalNumSellShortOrdersMin05() {
        return totalNumSellShortOrdersMin05;
    }

    public void setTotalNumSellShortOrdersMin05(double totalNumSellShortOrdersMin05) {
        this.totalNumSellShortOrdersMin05 = totalNumSellShortOrdersMin05;
    }

    public long getNumdate() {
        return numdate;
    }

    public void setNumdate(long numdate) {
        this.numdate = numdate;
    }

    public double getAvgNumPositionsLong() {
        return avgNumPositionsLong;
    }

    public void setAvgNumPositionsLong(double avgNumPositionsLong) {
        this.avgNumPositionsLong = avgNumPositionsLong;
    }

    public double getAvgNumPositionsShort() {
        return avgNumPositionsShort;
    }

    public void setAvgNumPositionsShort(double avgNumPositionsShort) {
        this.avgNumPositionsShort = avgNumPositionsShort;
    }

}
