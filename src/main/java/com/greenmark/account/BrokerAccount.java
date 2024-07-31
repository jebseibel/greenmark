package com.greenmark.account;

import com.greenmark.common.core.Labels;

public class BrokerAccount {
    /**
     * This is the class name.
     */
    public static final String CLASSNAME = "BrokerAccount";

    public static final int BROKER_ACCOUNT_CASH = 1;
    public static final int BROKER_ACCOUNT_MARGIN = 2;
    protected int brokerAccountType;

    /**
     * Used by the database
     **/
    protected int active = Labels.OBJECT_ACTIVE;


    protected double allocatedFunds = 0; // Total value of open buy orders
    protected double availableFunds = 0;
    protected double cashOnHand = 0;
    protected double totalEquity = 0;
    protected double unsettledFunds = 0;

    public BrokerAccount(int inBrokerAccountType) {
        this.brokerAccountType = inBrokerAccountType;
    }

//   public BrokerAccount ( String xmldata )
//   {
//      try
//      {
//         //CASH_ON_HAND
//         this.cashOnHand = UTXmlUtils.getXmlDataAsDouble( xmldata, "CASH_ON_HAND" );
//
//         //TOTAL_EQUITY
//         this.totalEquity = UTXmlUtils.getXmlDataAsDouble( xmldata, "TOTAL_EQUITY" );
//
//         //AVAILABLE_FUNDS
//         this.availableFunds = UTXmlUtils.getXmlDataAsDouble( xmldata, "AVAILABLE_FUNDS" );
//
//			//ALLOCATED_FUNDS
//         this.allocatedFunds = UTXmlUtils.getXmlDataAsDouble( xmldata, "ALLOCATED_FUNDS" );
//
//			//UNSETTLED_FUNDS
//         this.unsettledFunds = UTXmlUtils.getXmlDataAsDouble( xmldata, "UNSETTLED_FUNDS" );
//
//			//BROKER_ACCOUNT_TYPE
//         this.brokerAccountType = UTXmlUtils.getXmlDataAsInt( xmldata, "BROKER_ACCOUNT_TYPE" );
//
//			//ACTIVE
//         this.active = UTXmlUtils.getXmlDataAsInt( xmldata, "ACTIVE" );
//      }
//      catch (Exception e)
//      {
//         System.out.println("Exception in "+CLASSNAME+".Constructor; message ["+e.getMessage()+"]");
//      }
//
//   }

//	/**
//	 * Returns a string representation of this object that is suitable for use in the account.xml file
//	 *
//	 * @param endline - a string to use as a delimiter at the end of the line. Pass empty string for nothing
//	 * @return String Xml format
//	 */
//	public final String toXml( String prefix, String endline )
//	{
//		StringBuffer stb = new StringBuffer(1500);
//		stb.append( prefix + "<CASH_ON_HAND>" + this.getCashOnHand() + "</CASH_ON_HAND>" + endline );
//		stb.append( prefix + "<TOTAL_EQUITY>" + this.getTotalEquity() + "</TOTAL_EQUITY>" + endline );
//		stb.append( prefix + "<AVAILABLE_FUNDS>" + this.getAvailableFunds() + "</AVAILABLE_FUNDS>" + endline );
//		stb.append( prefix + "<ALLOCATED_FUNDS>" + this.getAllocatedFunds() + "</ALLOCATED_FUNDS>" + endline );
//		stb.append( prefix + "<UNSETTLED_FUNDS>" + this.getUnsettledFunds() + "</UNSETTLED_FUNDS>" + endline );
//		stb.append( prefix + "<BROKER_ACCOUNT_TYPE>" + this.getBrokerAccountType() + "</BROKER_ACCOUNT_TYPE>" + endline );
//		stb.append( prefix + "<ACTIVE>" + this.getActive() + "</ACTIVE>" + endline );
//		return stb.toString();
//   }

    /**
     * Static formatter to display this broker Account's account type
     **/
    public static String formatAccountType(int accountType) {
        if (accountType == BROKER_ACCOUNT_CASH)
            return "Cash Account";
        else
            return "Margin Account";
    }

    public int getActive() {
        return this.active;
    }

    public void setActive(int active) {
        this.active = active;
    }


    //////////////////////////////////////////////////////////////////

    public double getAllocatedFunds() {
        return allocatedFunds;
    }

    public void setAllocatedFunds(double allocatedFunds) {
        this.allocatedFunds = allocatedFunds;
    }

    public void addAllocatedFunds(float value) {
        this.allocatedFunds += value;
    }

    public void subtractAllocatedFunds(float value) {
        this.allocatedFunds -= value;
    }

    //////////////////////////////////////////////////////////////////
    public double getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(double availableFunds) {
        this.availableFunds = availableFunds;
    }

    public void addAvailableFunds(float value) {
        this.availableFunds += value;
    }

    public void subtractAvailableFunds(float value) {
        this.availableFunds -= value;
    }

    ///////////////////////////////////////////////////////////////////////
    public double getCashOnHand() {
        return cashOnHand;
    }

    public void setCashOnHand(double cashOnHand) {
        this.cashOnHand = cashOnHand;
    }

    public void addCashOnHand(float value) {
        this.cashOnHand += value;
    }

    public void subtractCashOnHand(float value) {
        this.cashOnHand -= value;
    }

    //////////////////////////////////////////////////////////////////

    public double getTotalEquity() {
        return totalEquity;
    }

    public void setTotalEquity(double totalEquity) {
        this.totalEquity = totalEquity;
    }

    public void addTotalEquity(float value) {
        this.totalEquity += value;
    }

    public void subtractTotalEquity(float value) {
        this.totalEquity -= value;
    }

    //////////////////////////////////////////////////////////////////
    public double getUnsettledFunds() {
        return unsettledFunds;
    }

    public void setUnsettledFunds(double unsettledFunds) {
        this.unsettledFunds = unsettledFunds;
    }

    public void addUnsettledFunds(float value) {
        this.unsettledFunds += value;
    }

    public void subtractUnsettledFunds(float value) {
        this.unsettledFunds -= value;
    }


    public int getBrokerAccountType() {
        return brokerAccountType;
    }

    public void setBrokerAccountType(int brokerAccountType) {
        this.brokerAccountType = brokerAccountType;
    }


}
