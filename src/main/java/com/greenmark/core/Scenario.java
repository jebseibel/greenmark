package com.greenmark.core;


import com.greenmark.common.core.Labels;
import com.greenmark.utils.UTCalendarTime;

import java.util.Date;

public class Scenario {
    /**
     * This is the class name.
     */
    private static final String CLASSNAME = "Scenario";

    /**
     * Used by the database
     **/
    protected long id;           //leave as null for hibernate
    protected long modelId = 0;  //set to zero to find insert errors
    protected long accountId = 0;  //set to zero to find insert errors
    protected int active = Labels.OBJECT_ACTIVE;
//	protected Account account;    // MOVED - Accounts contain scenarios
    /**************************/

    protected String name;

    protected double openTotalEquity;
    protected double openCashOnHand;
    protected double openAllocatedFunds;
    protected double openUnsettledFunds;

    protected double openTotalEquityMargin;
    protected double openCashOnHandMargin;
    protected double openAllocatedFundsMargin;
    protected double openUnsettledFundsMargin;

    protected Date startDate;
    protected Date stopDate;

    protected String machineName;

    protected String datafeedType;  // This is NOT where we store the config datafeed type
    // that is used by the software.  That value can be found by calling:
    //  Config.getValue(DataFeedManager.CONFIG_DATAFEED_TYPE);


    public Scenario() {
        // Hibernate needs an empty constructor
    }
//
//	public Scenario ( String xmldata )
//	{
//		try
//		{
//			//SCENARIO_ID
//			this.id = UTXmlUtils.getXmlDataAsLong( xmldata, "ID" );
//
//			//ACCOUNT_ID
//			this.accountId = UTXmlUtils.getXmlDataAsLong( xmldata, "ACCOUNT_ID" );
//
//			//MODEL_ID
//			this.modelId = UTXmlUtils.getXmlDataAsInt( xmldata, "MODEL_ID" );
//
//			//ACTIVE
//			this.active = UTXmlUtils.getXmlDataAsInt( xmldata, "ACTIVE" );
//
//			//OPEN_TOTAL_EQUITY
//			this.openTotalEquity = UTXmlUtils.getXmlDataAsDouble( xmldata, "OPEN_TOTAL_EQUITY_CASH" );
//
//			//OPEN_CASHON_HAND
//			this.openCashOnHand = UTXmlUtils.getXmlDataAsDouble( xmldata, "OPEN_CASHON_HAND_CASH" );
//
//			//OPEN_ALLOCATED_FUNDS
//			this.openAllocatedFunds = UTXmlUtils.getXmlDataAsDouble( xmldata, "OPEN_ALLOCATED_FUNDS_CASH" );
//
//			//OPEN_UNSETTLED_FUNDS
//			this.openUnsettledFunds = UTXmlUtils.getXmlDataAsDouble( xmldata, "OPEN_UNSETTLED_FUNDS_CASH" );
//
//			//OPEN_TOTAL_EQUITY_MARGIN
//			this.openTotalEquityMargin = UTXmlUtils.getXmlDataAsDouble( xmldata, "OPEN_TOTAL_EQUITY_MARGIN" );
//
//			//OPEN_CASHON_HAND_MARGIN
//			this.openCashOnHandMargin = UTXmlUtils.getXmlDataAsDouble( xmldata, "OPEN_CASHON_HAND_MARGIN" );
//
//			//OPEN_ALLOCATED_FUNDS_MARGIN
//			this.openAllocatedFundsMargin = UTXmlUtils.getXmlDataAsDouble( xmldata, "OPEN_ALLOCATED_FUNDS_MARGIN" );
//
//			//OPEN_UNSETTLED_FUNDS_MARGIN
//			this.openUnsettledFundsMargin = UTXmlUtils.getXmlDataAsDouble( xmldata, "OPEN_UNSETTLED_FUNDS_CASH_MARGIN" );
//
//			//START_DATE
//			String startDateString = UTXmlUtils.getXmlData ( xmldata, "START_DATE" );
//			UTCalendar startDateCal = new UTCalendar(startDateString);
//			this.startDate = startDateCal.getJavaDate();
//
//			//STOP_DATE
//			String stopDateString = UTXmlUtils.getXmlData( xmldata, "STOP_DATE" );
//			UTCalendar stopDateCal = new UTCalendar (stopDateString);
//			this.stopDate = stopDateCal.getJavaDate();
//
//			//NAME
//			this.name = UTXmlUtils.getXmlData( xmldata, "NAME" );
//
//			//MACHINE_NAME
//			this.machineName = UTXmlUtils.getXmlData( xmldata, "MACHINE_NAME" );
//
//			//DATAFEED_TYPE
//			this.datafeedType = UTXmlUtils.getXmlData( xmldata, "DATAFEED_TYPE" );
//			DataFeedManager.setDatafeedType ( this.datafeedType );
//		}
//		catch (Exception e)
//		{
//			System.out.println("Exception in "+CLASSNAME+".Constructor; message ["+e.getMessage()+"]");
//		}
//
//	}
//
//	public final String toXml ( String prefix, String endline )
//	{
//		StringBuffer stb = new StringBuffer();
//		String TAB = UTDisplayFormatter.TAB;
//
//		stb.append( prefix + TAB + TAB + "<SCENARIO>" + endline );
//		stb.append( prefix + TAB + TAB + TAB + "<ID>" + this.id + "</ID>" + endline );
//		stb.append( prefix + TAB + TAB + TAB + "<ACCOUNT_ID>" + this.accountId + "</ACCOUNT_ID>" + endline );
//		stb.append( prefix + TAB + TAB + TAB + "<MODEL_ID>" + this.modelId + "</MODEL_ID>" + endline );
//		stb.append( prefix + TAB + TAB + TAB + "<ACTIVE>" + this.active + "</ACTIVE>" + endline );
//		stb.append( prefix + TAB + TAB + TAB + "<OPEN_TOTAL_EQUITY_CASH>" + this.openTotalEquity + "</OPEN_TOTAL_EQUITY_CASH>" + endline );
//		stb.append( prefix + TAB + TAB + TAB + "<OPEN_CASHON_HAND_CASH>" + this.openCashOnHand + "</OPEN_CASHON_HAND_CASH>" + endline );
//		stb.append( prefix + TAB + TAB + TAB + "<OPEN_ALLOCATED_FUNDS_CASH>" + this.openAllocatedFunds + "</OPEN_ALLOCATED_FUNDS_CASH>" + endline );
//		stb.append( prefix + TAB + TAB + TAB + "<OPEN_UNSETTLED_FUNDS_CASH>" + this.openUnsettledFunds + "</OPEN_UNSETTLED_FUNDS_CASH>" + endline );
//
//		stb.append( prefix + TAB + TAB + TAB + "<OPEN_TOTAL_EQUITY_MARGIN>" + this.openTotalEquityMargin + "</OPEN_TOTAL_EQUITY_MARGIN>" + endline );
//		stb.append( prefix + TAB + TAB + TAB + "<OPEN_CASHON_HAND_MARGIN>" + this.openCashOnHandMargin + "</OPEN_CASHON_HAND_MARGIN>" + endline );
//		stb.append( prefix + TAB + TAB + TAB + "<OPEN_ALLOCATED_FUNDS_MARGIN>" + this.openAllocatedFundsMargin + "</OPEN_ALLOCATED_FUNDS_MARGIN>" + endline );
//		stb.append( prefix + TAB + TAB + TAB + "<OPEN_UNSETTLED_FUNDS_MARGIN>" + this.openUnsettledFundsMargin + "</OPEN_UNSETTLED_FUNDS_MARGIN>" + endline );
//
//
//      if ( DataFeedManager.isHistoricalDatafeed() )
//		{
//			UTCalendar startDateCal = new UTCalendar(this.startDate);
//			stb.append(prefix + TAB + TAB + TAB + "<START_DATE>" + startDateCal.formatDateParam() + "</START_DATE>" + endline);
//
//			UTCalendar stopDateCal = new UTCalendar(this.stopDate);
//			stb.append(prefix + TAB + TAB + TAB + "<STOP_DATE>" + stopDateCal.formatDateParam() + "</STOP_DATE>" + endline);
//		}
//		else
//		{
//			stb.append(prefix + TAB + TAB + TAB + "<START_DATE></START_DATE>" + endline);
//			stb.append(prefix + TAB + TAB + TAB + "<STOP_DATE></STOP_DATE>" + endline);
//		}
//
//		stb.append( prefix + TAB + TAB + TAB + "<NAME>" + this.name + "</NAME>" + endline );
//		stb.append( prefix + TAB + TAB + TAB + "<MACHINE_NAME>" + this.machineName + "</MACHINE_NAME>" + endline );
//		stb.append( prefix + TAB + TAB + TAB + "<DATAFEED_TYPE>" + Config.getValue(DataFeedManager.CONFIG_DATAFEED_TYPE) + "</DATAFEED_TYPE>" + endline );
//
//		stb.append( prefix + TAB + TAB + "</SCENARIO>" + endline );
//
//		return stb.toString();
//	}

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

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public double getOpenAllocatedFunds() {
        return openAllocatedFunds;
    }

    public void setOpenAllocatedFunds(double openAllocatedFunds) {
        this.openAllocatedFunds = openAllocatedFunds;
    }

    public double getOpenCashOnHand() {
        return openCashOnHand;
    }

    public void setOpenCashOnHand(double openCashOnHand) {
        this.openCashOnHand = openCashOnHand;
    }

    public double getOpenTotalEquity() {
        return openTotalEquity;
    }

    public void setOpenTotalEquity(double openTotalEquity) {
        this.openTotalEquity = openTotalEquity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date start_date) {
        this.startDate = start_date;
    }

    public String getStartDateInFieldDisplay() {
        String returnString = "N/A";

        try {
            if (startDate != null) {
                UTCalendarTime returnTime = new UTCalendarTime(startDate);
                returnString = returnTime.formatParameterDate();
            }
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN Scenario!   getStartDateInFieldDisplay  Exception Message:  [" + ex.getMessage() + "]");
        }

        return returnString;
    }

    public String getStartDateDisplay() {
        String returnString = "N/A";

        try {
            if (startDate != null) {
                UTCalendarTime returnTime = new UTCalendarTime(startDate);
                returnString = returnTime.formatDateDisplay();
            }
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN Scenario!   getStartDateDisplay  Exception Message:  [" + ex.getMessage() + "]");
        }

        return returnString;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stop_date) {
        this.stopDate = stop_date;
    }

    public String getStopDateInFieldDisplay() {
        String returnString = "N/A";

        try {
            if (stopDate != null) {
                UTCalendarTime returnTime = new UTCalendarTime(stopDate);
                returnString = returnTime.formatParameterDate();
            }
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN Scenario!   getStopDateInFieldDisplay  Exception Message:  [" + ex.getMessage() + "]");
        }

        return returnString;
    }

    public String getStopDateDisplay() {
        String returnString = "N/A";

        try {
            if (stopDate != null) {
                UTCalendarTime returnTime = new UTCalendarTime(stopDate);
                returnString = returnTime.formatDateDisplay();
            }
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN Scenario!   getStopDateDisplay  Exception Message:  [" + ex.getMessage() + "]");
        }

        return returnString;
    }

    public String getDatafeedType() {
        return datafeedType;
    }

    public void setDatafeedType(String datafeedType) {
        this.datafeedType = datafeedType;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String dbSummary() {
        String stb = " > > " + CLASSNAME + " :: " +
                "id [" + id + "] " +
                "modelTemplateId [" + modelId + "] " +
                "name [" + name + "] " +
                "active [" + active + "] ";
        return stb;
    }

    public String getClassname() {
        return CLASSNAME;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getOpenAllocatedFundsMargin() {
        return openAllocatedFundsMargin;
    }

    public void setOpenAllocatedFundsMargin(double openAllocatedFundsMargin) {
        this.openAllocatedFundsMargin = openAllocatedFundsMargin;
    }

    public double getOpenTotalEquityMargin() {
        return openTotalEquityMargin;
    }

    public void setOpenTotalEquityMargin(double openTotalEquityMargin) {
        this.openTotalEquityMargin = openTotalEquityMargin;
    }

    public double getOpenCashOnHandMargin() {
        return openCashOnHandMargin;
    }

    public void setOpenCashOnHandMargin(double openCashOnHandMargin) {
        this.openCashOnHandMargin = openCashOnHandMargin;
    }

    public double getOpenUnsettledFunds() {
        return openUnsettledFunds;
    }

    public void setOpenUnsettledFunds(double openUnsettledFunds) {
        this.openUnsettledFunds = openUnsettledFunds;
    }

    public double getOpenUnsettledFundsMargin() {
        return openUnsettledFundsMargin;
    }

    public void setOpenUnsettledFundsMargin(double openUnsettledFundsMargin) {
        this.openUnsettledFundsMargin = openUnsettledFundsMargin;
    }
}
