package com.greenmark.common.dto.broker.timeperiod;

import java.io.Serializable;
import java.util.Date;

import com.greenmark.common.GmConstants;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: BrokerTimePeriodDtoBase</p>
 * <p>Description: This base class is used by BrokerDailyDto and BrokerHourlyDto.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class BrokerTimePeriodDtoBase implements Serializable {
	public static final String CLASSNAME = "BrokerTimePeriodDtoBase";
	private static final long serialVersionUID = 1L;

	protected long id;

	protected double totalEquity;
	protected double cashOnHand;
	protected double allocatedFunds;
	protected double unsettledFunds;

	protected double longPositionsTotal;
	protected double shortPositionsTotal;

	protected Date thisDate;
	protected long numdate;

	protected double totalEquityMargin;
	protected double cashOnHandMargin;
	protected double allocatedFundsMargin;
	protected double unsettledFundsMargin;

	protected int active = GmConstants.OBJECT_ACTIVE;

	protected String thisDateString;

	public BrokerTimePeriodDtoBase() {
	}

	public String dbSummary() {
		StringBuffer stb = new StringBuffer();
		stb.append(" > > " + CLASSNAME + ":");
		stb.append("id [" + id + "] ");
		stb.append("active [" + active + "] ");
		return stb.toString();
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

	public Date getThisDate() {
		return thisDate;
	}

	public void setThisDate(Date thisDate) {
		this.thisDate = thisDate;
	}

	public long getNumdate() {
		return numdate;
	}

	public void setNumdate(long numdate) {
		this.numdate = numdate;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getThisDateString() {
		return thisDateString;
	}

	public void setThisDateString(String thisDateString) {
		this.thisDateString = thisDateString;
	}
}
