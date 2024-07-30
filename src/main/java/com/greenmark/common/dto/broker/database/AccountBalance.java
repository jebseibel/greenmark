package com.greenmark.common.dto.broker.database;

import java.io.Serializable;

import com.greenmark.common.dto.account.AccountDto;
import com.greenmark.common.dto.account.database.AccountDecorator;
import lombok.extern.slf4j.Slf4j;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountBalance</p>
 * <p>Description: This class extends its base DTO and adds other DTO object instances.  These are sent to/from the database to store hierarchies of objects.
 *    It is also used by the trading client to add additional information it needs when calculating, but does not need to be stored in the database. </p>

 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
@Slf4j
public class AccountBalance extends AccountDecorator implements Serializable {
	public static final String CLASSNAME = "AccountBalance";
	private static final long serialVersionUID = 1L;

	public BrokerAccount cashAccount = new BrokerAccount(BrokerAccount.BROKER_ACCOUNT_CASH);
	public BrokerAccount marginAccount = new BrokerAccount(BrokerAccount.BROKER_ACCOUNT_MARGIN);

	public BrokerAccount brokerAccountCash = new BrokerAccount(BrokerAccount.BROKER_ACCOUNT_IB);
	public BrokerAccount brokerAccountMargin = new BrokerAccount(BrokerAccount.BROKER_ACCOUNT_IB);

	public AccountBalance() {
		super();
		cashAccount = new BrokerAccount(BrokerAccount.BROKER_ACCOUNT_CASH);
		marginAccount = new BrokerAccount(BrokerAccount.BROKER_ACCOUNT_MARGIN);
	}

	public AccountBalance(AccountDto inAccount) {
		super(inAccount);
	}

//	public AccountBalance(CreateAccountDto createAccountData) {
//		super(createAccountData);
//	}
//
//	// ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
//	public AccountBalance(String xmldata) {
//		super(xmldata);
//
//		try {
//			this.cashAccount = new BrokerAccount(UTXmlUtils.getXmlData(xmldata, "CASH_ACCOUNT"));
//			this.marginAccount = new BrokerAccount(UTXmlUtils.getXmlData(xmldata, "MARGIN_ACCOUNT"));
//
//			this.brokerAccountCash = new BrokerAccount(UTXmlUtils.getXmlData(xmldata, "CASH_BROKER_ACCOUNT"));
//			this.brokerAccountMargin = new BrokerAccount(UTXmlUtils.getXmlData(xmldata, "MARGIN_BROKER_ACCOUNT"));
//		} catch (Exception ex) {
//			log.error("Caught General Exception " + CLASSNAME + ".constructor() message:[" + ex.getMessage() + "]");
//		}
//	}
//
//	public String toXmlWrapper(String prefix, String endline) {
//        String stb = prefix + "<ACCOUNT>" + endline +
//                prefix + toXml(prefix, endline) + endline +
//                prefix + "</ACCOUNT>" + endline;
//		return stb;
//	}
//
//	@Override
//	public String toXml(String prefix, String endline) {
//
//        String stb = super.toXml(prefix, endline) +
//                prefix + "<CASH_ACCOUNT>" + endline +
//                cashAccount.toXml(prefix, "\n") +
//                prefix + "</CASH_ACCOUNT>" + endline +
//                prefix + "<MARGIN_ACCOUNT>" + endline +
//                marginAccount.toXml(prefix, "\n") +
//                prefix + "</MARGIN_ACCOUNT>" + endline +
//                prefix + "<CASH_BROKER_ACCOUNT>" + endline +
//                brokerAccountCash.toXml(prefix, "\n") +
//                prefix + "</CASH_BROKER_ACCOUNT>" + endline +
//                prefix + "<MARGIN_BROKER_ACCOUNT>" + endline +
//                brokerAccountMargin.toXml(prefix, "\n") +
//                prefix + "</MARGIN_BROKER_ACCOUNT>" + endline;
//
//		return stb;
//	}

	// ------------------------------------------- CONVENIENCE METHODS -----------------------------------

	///////////////////////////////////////////////////////////////
	// CONVENIENCE METHODS FOR CASH AND MARGIN ACCOUNTS
	///////////////////////////////////////////////////////////////
	public double getGrandTotalEquity() {
		return cashAccount.getTotalEquity() + marginAccount.getTotalEquity();
	}

	public double getGrandAllocatedFunds() {
		return cashAccount.getAllocatedFunds() + marginAccount.getAllocatedFunds();
	}

	public double getGrandCashOnHand() {
		return cashAccount.getCashOnHand() + marginAccount.getCashOnHand();
	}

	// We only use CashOnHand anymore, this remains for the website.
	public double getGrandAvailableFunds() {
		return cashAccount.getCashOnHand() + marginAccount.getCashOnHand();
	}

	public double getGrandUnsettledFunds() {
		return cashAccount.getUnsettledFunds() + marginAccount.getUnsettledFunds();
	}

	//////////////////////////////////////////////////////////////////
	// Keep these original setter's and getter's, they are for the Cash account
	public double getAllocatedFunds() {
		return cashAccount.getAllocatedFunds();
	}

	public void setAllocatedFunds(double allocatedFunds) {
		cashAccount.setAllocatedFunds(allocatedFunds);
	}

	public void addAllocatedFunds(float value) {
		cashAccount.addAllocatedFunds(value);
	}

	public void subtractAllocatedFunds(float value) {
		cashAccount.subtractAllocatedFunds(value);
	}

	///////////////////////////////////////////////////////////////////////
	public double getCashOnHand() {
		return cashAccount.getCashOnHand();
	}

	public void setCashOnHand(double cashOnHand) {
		cashAccount.setCashOnHand(cashOnHand);
	}

	public void addCashOnHand(float value) {
		cashAccount.addCashOnHand(value);
	}

	public void subtractCashOnHand(float value) {
		cashAccount.subtractCashOnHand(value);
	}

	///////////////////////////////////////////////////////////////
	public double getTotalEquity() {
		return cashAccount.getTotalEquity();
	}

	public void setTotalEquity(double totalEquity) {
		cashAccount.setTotalEquity(totalEquity);
	}

	public void addTotalEquity(float value) {
		cashAccount.addTotalEquity(value);
	}

	public void subtractTotalEquity(float value) {
		cashAccount.subtractTotalEquity(value);
	}

	///////////////////////////////////////////////////////////////
	public double getUnsettledFunds() {
		return cashAccount.getUnsettledFunds();
	}

	public void setUnsettledFunds(double totalEquity) {
		cashAccount.setUnsettledFunds(totalEquity);
	}

	public void addUnsettledFunds(float value) {
		cashAccount.addUnsettledFunds(value);
	}

	public void subtractUnsettledFunds(float value) {
		cashAccount.subtractUnsettledFunds(value);
	}

	//////////////////////////////////////////////////////////////////
	// Here are the convenience setter's and getter's for the Margin account
	public double getAllocatedFundsMargin() {
		return marginAccount.getAllocatedFunds();
	}

	public void setAllocatedFundsMargin(double allocatedFunds) {
		marginAccount.setAllocatedFunds(allocatedFunds);
	}

	public void addAllocatedFundsMargin(float value) {
		marginAccount.addAllocatedFunds(value);
	}

	public void subtractAllocatedFundsMargin(float value) {
		marginAccount.subtractAllocatedFunds(value);
	}

	///////////////////////////////////////////////////////////////////////
	public double getCashOnHandMargin() {
		return marginAccount.getCashOnHand();
	}

	public void setCashOnHandMargin(double cashOnHand) {
		marginAccount.setCashOnHand(cashOnHand);
	}

	public void addCashOnHandMargin(float value) {
		marginAccount.addCashOnHand(value);
	}

	public void subtractCashOnHandMargin(float value) {
		marginAccount.subtractCashOnHand(value);
	}

	///////////////////////////////////////////////////////////////
	public double getTotalEquityMargin() {
		return marginAccount.getTotalEquity();
	}

	public void setTotalEquityMargin(double totalEquity) {
		marginAccount.setTotalEquity(totalEquity);
	}

	public void addTotalEquityMargin(float value) {
		marginAccount.addTotalEquity(value);
	}

	public void subtractTotalEquityMargin(float value) {
		marginAccount.subtractTotalEquity(value);
	}

	///////////////////////////////////////////////////////////////
	public double getUnsettledFundsMargin() {
		return marginAccount.getUnsettledFunds();
	}

	public void setUnsettledFundsMargin(double totalEquity) {
		marginAccount.setUnsettledFunds(totalEquity);
	}

	public void addUnsettledFundsMargin(float value) {
		marginAccount.addUnsettledFunds(value);
	}

	public void subtractUnsettledFundsMargin(float value) {
		marginAccount.subtractUnsettledFunds(value);
	}

	public BrokerAccount getCashAccount() {
		return cashAccount;
	}

	public void setCashAccount(BrokerAccount cashAccount) {
		this.cashAccount = cashAccount;
	}

	public BrokerAccount getMarginAccount() {
		return marginAccount;
	}

	public void setMarginAccount(BrokerAccount marginAccount) {
		this.marginAccount = marginAccount;
	}

	public BrokerAccount getBrokerAccountCash() {
		return brokerAccountCash;
	}

	public void setBrokerAccountCash(BrokerAccount brokerAccountCash) {
		this.brokerAccountCash = brokerAccountCash;
	}

	public BrokerAccount getBrokerAccountMargin() {
		return brokerAccountMargin;
	}

	public void setBrokerAccountMargin(BrokerAccount brokerAccountMargin) {
		this.brokerAccountMargin = brokerAccountMargin;
	}
}
