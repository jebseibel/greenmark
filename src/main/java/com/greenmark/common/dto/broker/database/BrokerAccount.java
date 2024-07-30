package com.greenmark.common.dto.broker.database;

import java.io.Serializable;

import com.greenmark.utils.UTDisplayFormatter;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: BrokerAccount</p>
 * <p>Description: </p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class BrokerAccount implements Serializable {
	public static final String CLASSNAME = "BrokerAccount";
	private static final long serialVersionUID = 1L;

	public static final int BROKER_ACCOUNT_CASH = 1;
	public static final int BROKER_ACCOUNT_MARGIN = 2;
	public static final int BROKER_ACCOUNT_IB = 3;

	protected int brokerAccountType;

	protected double allocatedFunds = 0; // Total value of open buy orders
	protected double cashOnHand = 0;
	protected double totalEquity = 0;
	protected double unsettledFunds = 0;

	public BrokerAccount(int inBrokerAccountType) {
		this.brokerAccountType = inBrokerAccountType;
	}

	// ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
//	public BrokerAccount(String xmldata) {
//		try {
//			this.cashOnHand = UTXmlUtils.getXmlDataAsDouble(xmldata, "CASH_ON_HAND");
//			this.totalEquity = UTXmlUtils.getXmlDataAsDouble(xmldata, "TOTAL_EQUITY");
//			this.allocatedFunds = UTXmlUtils.getXmlDataAsDouble(xmldata, "ALLOCATED_FUNDS");
//			this.unsettledFunds = UTXmlUtils.getXmlDataAsDouble(xmldata, "UNSETTLED_FUNDS");
//			this.brokerAccountType = UTXmlUtils.getXmlDataAsInt(xmldata, "BROKER_ACCOUNT_TYPE");
//		} catch (Exception e) {
//			System.out.println("Exception in " + CLASSNAME + ".Constructor; message [" + e.getMessage() + "]");
//		}
//	}
//
//	public final String toXml(String prefix, String endline) {
//        String stb = prefix + UTDisplayFormatter.TAB + "<CASH_ON_HAND>" + this.getCashOnHand() + "</CASH_ON_HAND>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<TOTAL_EQUITY>" + this.getTotalEquity() + "</TOTAL_EQUITY>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<ALLOCATED_FUNDS>" + this.getAllocatedFunds() + "</ALLOCATED_FUNDS>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<UNSETTLED_FUNDS>" + this.getUnsettledFunds() + "</UNSETTLED_FUNDS>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<BROKER_ACCOUNT_TYPE>" + this.getBrokerAccountType() + "</BROKER_ACCOUNT_TYPE>" + endline;
//		return stb;
//	}

	// ------------------------------------------------ CONVENIENCE METHODS ---------------------------------------------------
	/** Static formatter to display this broker Account's account type **/
	public static String formatAccountType(int accountType) {
		if (accountType == BROKER_ACCOUNT_CASH)
			return "Cash Account";
		else
			return "Margin Account";
	}

	public String toString() {
        String outString = "ACCOUNT:   " +
                formatAccountType(brokerAccountType) +
                "   ";

		return outString;
	}

	public String toStringSystem() {
		String prefix = UTDisplayFormatter.TAB;
		String endline = "\n";
        String stb = prefix + "-----------------------------------------------" + endline +
                prefix + CLASSNAME + endline +
                prefix + " . . . allocatedFunds = [" + allocatedFunds + "]" + endline +
                prefix + " . . . availableFundsCash     = [" + cashOnHand + "]" + endline +
                prefix + " . . . totalEquityCash    = [" + totalEquity + "]" + endline +
                prefix + " . . . unsettledFunds = [" + unsettledFunds + "]" + endline +
                prefix + "-----------------------------------------------" + endline;
		return stb;
	}

	public void addCashOnHand(float value) {
		this.cashOnHand += value;
	}

	public void subtractCashOnHand(float value) {
		this.cashOnHand -= value;
	}

	public void addAllocatedFunds(float value) {
		this.allocatedFunds += value;
	}

	public void subtractAllocatedFunds(float value) {
		this.allocatedFunds -= value;
	}

	public void addTotalEquity(float value) {
		this.totalEquity += value;
	}

	public void subtractTotalEquity(float value) {
		this.totalEquity -= value;
	}

	public void addUnsettledFunds(float value) {
		this.unsettledFunds += value;
	}

	public void subtractUnsettledFunds(float value) {
		this.unsettledFunds -= value;
	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public int getBrokerAccountType() {
		return brokerAccountType;
	}

	public void setBrokerAccountType(int brokerAccountType) {
		this.brokerAccountType = brokerAccountType;
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

	public double getTotalEquity() {
		return totalEquity;
	}

	public void setTotalEquity(double totalEquity) {
		this.totalEquity = totalEquity;
	}

	public double getUnsettledFunds() {
		return unsettledFunds;
	}

	public void setUnsettledFunds(double unsettledFunds) {
		this.unsettledFunds = unsettledFunds;
	}
}
