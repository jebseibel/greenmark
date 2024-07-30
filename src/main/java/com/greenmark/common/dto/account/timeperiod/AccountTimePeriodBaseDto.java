package com.greenmark.common.dto.account.timeperiod;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.greenmark.common.GmConstants;
import com.greenmark.utils.UTDatetime;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountTimePeriodBaseDto</p>
 * <p>Description: This DTO is used as a base class for the AccountHourlyDto and AccountDailyDto classes.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class AccountTimePeriodBaseDto implements Serializable {
	public static final String CLASSNAME = "AccountTimePeriodBaseDto";
	private static final long serialVersionUID = 1L;

	protected long id;
	protected int active = GmConstants.OBJECT_ACTIVE;

	protected double netLiquidationValue;

	protected double totalEquity;
	protected double cashOnHand;
	protected double allocatedFunds;
	protected double unsettledFunds;

	protected double ibTotalEquity;
	protected double ibAllocatedFunds;
	protected double ibCashOnHand;
	protected double ibUnsettledFunds;

	protected double totalEquityMargin;
	protected double cashOnHandMargin;
	protected double allocatedFundsMargin;
	protected double unsettledFundsMargin;

	protected double longPositionsTotal;
	protected double shortPositionsTotal;

	protected LocalDateTime thisDate;
	protected long numdate;

	protected double avgNumDailyBuyLong;
	protected double avgNumMinute60BuyLong;
	protected double totalNumMinute15BuyLong;
	protected double totalNumMinute05BuyLong;
	protected double totalNumMinute01BuyLong;

	protected double avgNumDailySellLong;
	protected double avgNumMinute60SellLong;
	protected double totalNumMinute15SellLong;
	protected double totalNumMinute05SellLong;
	protected double totalNumMinute01SellLong;

	protected double avgNumDailyBuyShort;
	protected double avgNumMinute60BuyShort;
	protected double totalNumMinute15BuyShort;
	protected double totalNumMinute05BuyShort;
	protected double totalNumMinute01BuyShort;

	protected double avgNumDailySellShort;
	protected double avgNumMinute60SellShort;
	protected double totalNumMinute15SellShort;
	protected double totalNumMinute05SellShort;
	protected double totalNumMinute01SellShort;

	// orders
	protected double totalNumBuyLongOrdersMin05;
	protected double totalNumSellLongOrdersMin05;
	protected double totalNumBuyShortOrdersMin05;
	protected double totalNumSellShortOrdersMin05;

	protected double totalNumBuyLongOrdersMin01;
	protected double totalNumSellLongOrdersMin01;
	protected double totalNumBuyShortOrdersMin01;
	protected double totalNumSellShortOrdersMin01;

	// positions
	protected double avgNumPositionsLong;
	protected double avgNumPositionsShort;

	// rejects
	protected int totalNumDailyLongReject;
	protected int totalNumMinute60LongReject;
	protected int totalNumMinute15LongReject;
	protected int totalNumMinute05LongReject;

	protected int totalNumDailyShortReject;
	protected int totalNumMinute60ShortReject;
	protected int totalNumMinute15ShortReject;
	protected int totalNumMinute05ShortReject;

	// reject orders
	protected int totalNumOrderLongRejectMinute05;
	protected int totalNumOrderShortRejectMinute05;
	protected int totalNumOrderLongRejectMinute01;
	protected int totalNumOrderShortRejectMinute01;

	public AccountTimePeriodBaseDto() {
	}

	public String toString() {
		String ret = "id=" + id + ":";
		return ret;
	}

	public String dbSummary() {
        String stb = " > > " + CLASSNAME + ":" +
                "id [" + id + "] " +
                "active [" + active + "] ";
		return stb;
	}

	///////////////////////////////////////////////////////////////
	// CONVENIENCE METHODS FOR CASH AND MARGIN ACCOUNTS
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

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public double getNetLiquidationValue() {
		return netLiquidationValue;
	}

	public void setNetLiquidationValue(double netLiquidationValue) {
		this.netLiquidationValue = netLiquidationValue;
	}

	public double getTotalEquity() {
		return totalEquity;
	}

	public void setTotalEquity(double totalEquity) {
		this.totalEquity = totalEquity;
	}

	public double getCashOnHand() {
		return cashOnHand;
	}

	public void setCashOnHand(double cashOnHand) {
		this.cashOnHand = cashOnHand;
	}

	public double getAllocatedFunds() {
		return allocatedFunds;
	}

	public void setAllocatedFunds(double allocatedFunds) {
		this.allocatedFunds = allocatedFunds;
	}

	public double getUnsettledFunds() {
		return unsettledFunds;
	}

	public void setUnsettledFunds(double unsettledFunds) {
		this.unsettledFunds = unsettledFunds;
	}

	public double getIbTotalEquity() {
		return ibTotalEquity;
	}

	public void setIbTotalEquity(double ibTotalEquity) {
		this.ibTotalEquity = ibTotalEquity;
	}

	public double getIbAllocatedFunds() {
		return ibAllocatedFunds;
	}

	public void setIbAllocatedFunds(double ibAllocatedFunds) {
		this.ibAllocatedFunds = ibAllocatedFunds;
	}

	public double getIbCashOnHand() {
		return ibCashOnHand;
	}

	public void setIbCashOnHand(double ibCashOnHand) {
		this.ibCashOnHand = ibCashOnHand;
	}

	public double getIbUnsettledFunds() {
		return ibUnsettledFunds;
	}

	public void setIbUnsettledFunds(double ibUnsettledFunds) {
		this.ibUnsettledFunds = ibUnsettledFunds;
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

	public LocalDateTime getThisDate() {
		return thisDate;
	}

	public void setThisDate(LocalDateTime thisDate) {
		this.thisDate = thisDate;
	}

	public long getNumdate() {
		return numdate;
	}

	public void setNumdate(long numdate) {
		this.numdate = numdate;
	}

	public double getAvgNumDailyBuyLong() {
		return avgNumDailyBuyLong;
	}

	public void setAvgNumDailyBuyLong(double avgNumDailyBuyLong) {
		this.avgNumDailyBuyLong = avgNumDailyBuyLong;
	}

	public double getAvgNumMinute60BuyLong() {
		return avgNumMinute60BuyLong;
	}

	public void setAvgNumMinute60BuyLong(double avgNumMinute60BuyLong) {
		this.avgNumMinute60BuyLong = avgNumMinute60BuyLong;
	}

	public double getTotalNumMinute15BuyLong() {
		return totalNumMinute15BuyLong;
	}

	public void setTotalNumMinute15BuyLong(double totalNumMinute15BuyLong) {
		this.totalNumMinute15BuyLong = totalNumMinute15BuyLong;
	}

	public double getTotalNumMinute05BuyLong() {
		return totalNumMinute05BuyLong;
	}

	public void setTotalNumMinute05BuyLong(double totalNumMinute05BuyLong) {
		this.totalNumMinute05BuyLong = totalNumMinute05BuyLong;
	}

	public double getTotalNumMinute01BuyLong() {
		return totalNumMinute01BuyLong;
	}

	public void setTotalNumMinute01BuyLong(double totalNumMinute01BuyLong) {
		this.totalNumMinute01BuyLong = totalNumMinute01BuyLong;
	}

	public double getAvgNumDailySellLong() {
		return avgNumDailySellLong;
	}

	public void setAvgNumDailySellLong(double avgNumDailySellLong) {
		this.avgNumDailySellLong = avgNumDailySellLong;
	}

	public double getAvgNumMinute60SellLong() {
		return avgNumMinute60SellLong;
	}

	public void setAvgNumMinute60SellLong(double avgNumMinute60SellLong) {
		this.avgNumMinute60SellLong = avgNumMinute60SellLong;
	}

	public double getTotalNumMinute15SellLong() {
		return totalNumMinute15SellLong;
	}

	public void setTotalNumMinute15SellLong(double totalNumMinute15SellLong) {
		this.totalNumMinute15SellLong = totalNumMinute15SellLong;
	}

	public double getTotalNumMinute05SellLong() {
		return totalNumMinute05SellLong;
	}

	public void setTotalNumMinute05SellLong(double totalNumMinute05SellLong) {
		this.totalNumMinute05SellLong = totalNumMinute05SellLong;
	}

	public double getTotalNumMinute01SellLong() {
		return totalNumMinute01SellLong;
	}

	public void setTotalNumMinute01SellLong(double totalNumMinute01SellLong) {
		this.totalNumMinute01SellLong = totalNumMinute01SellLong;
	}

	public double getAvgNumDailyBuyShort() {
		return avgNumDailyBuyShort;
	}

	public void setAvgNumDailyBuyShort(double avgNumDailyBuyShort) {
		this.avgNumDailyBuyShort = avgNumDailyBuyShort;
	}

	public double getAvgNumMinute60BuyShort() {
		return avgNumMinute60BuyShort;
	}

	public void setAvgNumMinute60BuyShort(double avgNumMinute60BuyShort) {
		this.avgNumMinute60BuyShort = avgNumMinute60BuyShort;
	}

	public double getTotalNumMinute15BuyShort() {
		return totalNumMinute15BuyShort;
	}

	public void setTotalNumMinute15BuyShort(double totalNumMinute15BuyShort) {
		this.totalNumMinute15BuyShort = totalNumMinute15BuyShort;
	}

	public double getTotalNumMinute05BuyShort() {
		return totalNumMinute05BuyShort;
	}

	public void setTotalNumMinute05BuyShort(double totalNumMinute05BuyShort) {
		this.totalNumMinute05BuyShort = totalNumMinute05BuyShort;
	}

	public double getTotalNumMinute01BuyShort() {
		return totalNumMinute01BuyShort;
	}

	public void setTotalNumMinute01BuyShort(double totalNumMinute01BuyShort) {
		this.totalNumMinute01BuyShort = totalNumMinute01BuyShort;
	}

	public double getAvgNumDailySellShort() {
		return avgNumDailySellShort;
	}

	public void setAvgNumDailySellShort(double avgNumDailySellShort) {
		this.avgNumDailySellShort = avgNumDailySellShort;
	}

	public double getAvgNumMinute60SellShort() {
		return avgNumMinute60SellShort;
	}

	public void setAvgNumMinute60SellShort(double avgNumMinute60SellShort) {
		this.avgNumMinute60SellShort = avgNumMinute60SellShort;
	}

	public double getTotalNumMinute15SellShort() {
		return totalNumMinute15SellShort;
	}

	public void setTotalNumMinute15SellShort(double totalNumMinute15SellShort) {
		this.totalNumMinute15SellShort = totalNumMinute15SellShort;
	}

	public double getTotalNumMinute05SellShort() {
		return totalNumMinute05SellShort;
	}

	public void setTotalNumMinute05SellShort(double totalNumMinute05SellShort) {
		this.totalNumMinute05SellShort = totalNumMinute05SellShort;
	}

	public double getTotalNumMinute01SellShort() {
		return totalNumMinute01SellShort;
	}

	public void setTotalNumMinute01SellShort(double totalNumMinute01SellShort) {
		this.totalNumMinute01SellShort = totalNumMinute01SellShort;
	}

	public double getTotalNumBuyLongOrdersMin05() {
		return totalNumBuyLongOrdersMin05;
	}

	public void setTotalNumBuyLongOrdersMin05(double totalNumBuyLongOrdersMin05) {
		this.totalNumBuyLongOrdersMin05 = totalNumBuyLongOrdersMin05;
	}

	public double getTotalNumSellLongOrdersMin05() {
		return totalNumSellLongOrdersMin05;
	}

	public void setTotalNumSellLongOrdersMin05(double totalNumSellLongOrdersMin05) {
		this.totalNumSellLongOrdersMin05 = totalNumSellLongOrdersMin05;
	}

	public double getTotalNumBuyShortOrdersMin05() {
		return totalNumBuyShortOrdersMin05;
	}

	public void setTotalNumBuyShortOrdersMin05(double totalNumBuyShortOrdersMin05) {
		this.totalNumBuyShortOrdersMin05 = totalNumBuyShortOrdersMin05;
	}

	public double getTotalNumSellShortOrdersMin05() {
		return totalNumSellShortOrdersMin05;
	}

	public void setTotalNumSellShortOrdersMin05(double totalNumSellShortOrdersMin05) {
		this.totalNumSellShortOrdersMin05 = totalNumSellShortOrdersMin05;
	}

	public double getTotalNumBuyLongOrdersMin01() {
		return totalNumBuyLongOrdersMin01;
	}

	public void setTotalNumBuyLongOrdersMin01(double totalNumBuyLongOrdersMin01) {
		this.totalNumBuyLongOrdersMin01 = totalNumBuyLongOrdersMin01;
	}

	public double getTotalNumSellLongOrdersMin01() {
		return totalNumSellLongOrdersMin01;
	}

	public void setTotalNumSellLongOrdersMin01(double totalNumSellLongOrdersMin01) {
		this.totalNumSellLongOrdersMin01 = totalNumSellLongOrdersMin01;
	}

	public double getTotalNumBuyShortOrdersMin01() {
		return totalNumBuyShortOrdersMin01;
	}

	public void setTotalNumBuyShortOrdersMin01(double totalNumBuyShortOrdersMin01) {
		this.totalNumBuyShortOrdersMin01 = totalNumBuyShortOrdersMin01;
	}

	public double getTotalNumSellShortOrdersMin01() {
		return totalNumSellShortOrdersMin01;
	}

	public void setTotalNumSellShortOrdersMin01(double totalNumSellShortOrdersMin01) {
		this.totalNumSellShortOrdersMin01 = totalNumSellShortOrdersMin01;
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

	public int getTotalNumDailyLongReject() {
		return totalNumDailyLongReject;
	}

	public void setTotalNumDailyLongReject(int totalNumDailyLongReject) {
		this.totalNumDailyLongReject = totalNumDailyLongReject;
	}

	public int getTotalNumMinute60LongReject() {
		return totalNumMinute60LongReject;
	}

	public void setTotalNumMinute60LongReject(int totalNumMinute60LongReject) {
		this.totalNumMinute60LongReject = totalNumMinute60LongReject;
	}

	public int getTotalNumMinute15LongReject() {
		return totalNumMinute15LongReject;
	}

	public void setTotalNumMinute15LongReject(int totalNumMinute15LongReject) {
		this.totalNumMinute15LongReject = totalNumMinute15LongReject;
	}

	public int getTotalNumMinute05LongReject() {
		return totalNumMinute05LongReject;
	}

	public void setTotalNumMinute05LongReject(int totalNumMinute05LongReject) {
		this.totalNumMinute05LongReject = totalNumMinute05LongReject;
	}

	public int getTotalNumDailyShortReject() {
		return totalNumDailyShortReject;
	}

	public void setTotalNumDailyShortReject(int totalNumDailyShortReject) {
		this.totalNumDailyShortReject = totalNumDailyShortReject;
	}

	public int getTotalNumMinute60ShortReject() {
		return totalNumMinute60ShortReject;
	}

	public void setTotalNumMinute60ShortReject(int totalNumMinute60ShortReject) {
		this.totalNumMinute60ShortReject = totalNumMinute60ShortReject;
	}

	public int getTotalNumMinute15ShortReject() {
		return totalNumMinute15ShortReject;
	}

	public void setTotalNumMinute15ShortReject(int totalNumMinute15ShortReject) {
		this.totalNumMinute15ShortReject = totalNumMinute15ShortReject;
	}

	public int getTotalNumMinute05ShortReject() {
		return totalNumMinute05ShortReject;
	}

	public void setTotalNumMinute05ShortReject(int totalNumMinute05ShortReject) {
		this.totalNumMinute05ShortReject = totalNumMinute05ShortReject;
	}

	public int getTotalNumOrderLongRejectMinute05() {
		return totalNumOrderLongRejectMinute05;
	}

	public void setTotalNumOrderLongRejectMinute05(int totalNumOrderLongRejectMinute05) {
		this.totalNumOrderLongRejectMinute05 = totalNumOrderLongRejectMinute05;
	}

	public int getTotalNumOrderShortRejectMinute05() {
		return totalNumOrderShortRejectMinute05;
	}

	public void setTotalNumOrderShortRejectMinute05(int totalNumOrderShortRejectMinute05) {
		this.totalNumOrderShortRejectMinute05 = totalNumOrderShortRejectMinute05;
	}

	public int getTotalNumOrderLongRejectMinute01() {
		return totalNumOrderLongRejectMinute01;
	}

	public void setTotalNumOrderLongRejectMinute01(int totalNumOrderLongRejectMinute01) {
		this.totalNumOrderLongRejectMinute01 = totalNumOrderLongRejectMinute01;
	}

	public int getTotalNumOrderShortRejectMinute01() {
		return totalNumOrderShortRejectMinute01;
	}

	public void setTotalNumOrderShortRejectMinute01(int totalNumOrderShortRejectMinute01) {
		this.totalNumOrderShortRejectMinute01 = totalNumOrderShortRejectMinute01;
	}

	public String getThisDateString() {
		return UTDatetime.toString(thisDate);
	}
}
