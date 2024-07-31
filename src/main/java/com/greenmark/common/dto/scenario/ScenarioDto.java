package com.greenmark.common.dto.scenario;

import java.io.Serializable;
import java.util.Date;

import com.greenmark.common.GmConstants;
import com.greenmark.common.config.Config;
import com.greenmark.common.config.ConfigDatafeed;
import com.greenmark.common.dto.parameters.AccountBalanceParams;
import com.greenmark.common.service.ApplicationDataContext;
import com.greenmark.utils.UTCalendar;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ScenarioDto</p>
 * <p>Description: This DTO is for the results database scenarios table.  A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 * DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ScenarioDto implements Serializable {
    private static final String CLASSNAME = "ScenarioDto";
    private static final long serialVersionUID = 1L;

    protected long id;
    protected int active = GmConstants.OBJECT_ACTIVE;

    protected long modelId = 0; // set to zero to find insert errors
    protected long accountId = 0; // set to zero to find insert errors

    protected String name;

    protected double openNetLiquidationValue;

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

    // This is NOT where we store the config datafeed type
    // that is used by the software. That value can be found by calling:
    // Config.getValue(DataFeedManager.CONFIG_DATAFEED_TYPE);
    protected String datafeedType;

    public ScenarioDto() {
    }

    // This is used by the historical scenarios to do 3 scenarios for 1 account.
    public ScenarioDto(ScenarioDto currentScenario, AccountBalanceParams accountBalance, int currentScenarioNumber) {

        this.setActive(GmConstants.OBJECT_ACTIVE);
        this.setAccountId(currentScenario.getAccountId());
        this.setModelId(currentScenario.getModelId());

        this.setName(currentScenario.getName() + " " + currentScenarioNumber);
        this.setMachineName(currentScenario.getMachineName());
        this.setDatafeedType(currentScenario.getDatafeedType());

        this.setOpenAllocatedFunds(0);
        this.setOpenUnsettledFunds(0);
        this.setOpenTotalEquity(accountBalance.getTotalEquityCash());
        this.setOpenCashOnHand(accountBalance.getAvailableFundsCash());

        this.setOpenAllocatedFundsMargin(0);
        this.setOpenUnsettledFundsMargin(0);
        this.setOpenTotalEquityMargin(accountBalance.getTotalEquityMargin());
        this.setOpenCashOnHandMargin(accountBalance.getAvailableFundsMargin());

        this.setOpenNetLiquidationValue(accountBalance.getNetLiquidationValue());
    }

    // ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
    public ScenarioDto(String xmldata) {
        try {
            this.id = UTXmlUtils.getXmlDataAsLong(xmldata, "ID");
            this.active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");

            this.modelId = UTXmlUtils.getXmlDataAsInt(xmldata, "MODEL_ID");
            this.accountId = UTXmlUtils.getXmlDataAsLong(xmldata, "ACCOUNT_ID");
            this.name = UTXmlUtils.getXmlData(xmldata, "NAME");

            this.openNetLiquidationValue = UTXmlUtils.getXmlDataAsDouble(xmldata, "OPEN_NET_LIQUIDATION_VALUE");

            this.openTotalEquity = UTXmlUtils.getXmlDataAsDouble(xmldata, "OPEN_TOTAL_EQUITY_CASH");
            this.openCashOnHand = UTXmlUtils.getXmlDataAsDouble(xmldata, "OPEN_CASHON_HAND_CASH");
            this.openAllocatedFunds = UTXmlUtils.getXmlDataAsDouble(xmldata, "OPEN_ALLOCATED_FUNDS_CASH");
            this.openUnsettledFunds = UTXmlUtils.getXmlDataAsDouble(xmldata, "OPEN_UNSETTLED_FUNDS_CASH");

            this.openTotalEquityMargin = UTXmlUtils.getXmlDataAsDouble(xmldata, "OPEN_TOTAL_EQUITY_MARGIN");
            this.openCashOnHandMargin = UTXmlUtils.getXmlDataAsDouble(xmldata, "OPEN_CASHON_HAND_MARGIN");
            this.openAllocatedFundsMargin = UTXmlUtils.getXmlDataAsDouble(xmldata, "OPEN_ALLOCATED_FUNDS_MARGIN");
            this.openUnsettledFundsMargin = UTXmlUtils.getXmlDataAsDouble(xmldata, "OPEN_UNSETTLED_FUNDS_MARGIN");

            String startDateString = UTXmlUtils.getXmlData(xmldata, "START_DATE");
            UTCalendar startDateCal = new UTCalendar(startDateString);
            this.startDate = startDateCal.getJavaDate();

            String stopDateString = UTXmlUtils.getXmlData(xmldata, "STOP_DATE");
            UTCalendar stopDateCal = new UTCalendar(stopDateString);
            this.stopDate = stopDateCal.getJavaDate();

            this.machineName = UTXmlUtils.getXmlData(xmldata, "MACHINE_NAME");
            this.datafeedType = UTXmlUtils.getXmlData(xmldata, "DATAFEED_TYPE");
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + ".Constructor; message [" + e.getMessage() + "]");
        }
    }

    public String toXmlWrapper(String prefix, String endline) {
        String stb = prefix + "<SCENARIO>" + endline +
                prefix + toXml(prefix, endline) + endline +
                prefix + "</SCENARIO>" + endline;
        return stb;
    }

    public String toXml(String prefix, String endline) {
        StringBuffer stb = new StringBuffer();

        stb.append(prefix + "<ID>" + this.id + "</ID>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<ACTIVE>" + this.active + "</ACTIVE>" + endline);

        stb.append(prefix + UTDisplayFormatter.TAB + "<MODEL_ID>" + this.modelId + "</MODEL_ID>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<ACCOUNT_ID>" + this.accountId + "</ACCOUNT_ID>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<NAME>" + this.name + "</NAME>" + endline);

        stb.append(prefix + UTDisplayFormatter.TAB + "<OPEN_NET_LIQUIDATION_VALUE>" + this.openNetLiquidationValue + "</OPEN_NET_LIQUIDATION_VALUE>" + endline);

        stb.append(prefix + UTDisplayFormatter.TAB + "<OPEN_TOTAL_EQUITY_CASH>" + this.openTotalEquity + "</OPEN_TOTAL_EQUITY_CASH>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<OPEN_CASHON_HAND_CASH>" + this.openCashOnHand + "</OPEN_CASHON_HAND_CASH>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<OPEN_ALLOCATED_FUNDS_CASH>" + this.openAllocatedFunds + "</OPEN_ALLOCATED_FUNDS_CASH>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<OPEN_UNSETTLED_FUNDS_CASH>" + this.openUnsettledFunds + "</OPEN_UNSETTLED_FUNDS_CASH>" + endline);

        stb.append(prefix + UTDisplayFormatter.TAB + "<OPEN_TOTAL_EQUITY_MARGIN>" + this.openTotalEquityMargin + "</OPEN_TOTAL_EQUITY_MARGIN>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<OPEN_CASHON_HAND_MARGIN>" + this.openCashOnHandMargin + "</OPEN_CASHON_HAND_MARGIN>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<OPEN_ALLOCATED_FUNDS_MARGIN>" + this.openAllocatedFundsMargin + "</OPEN_ALLOCATED_FUNDS_MARGIN>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<OPEN_UNSETTLED_FUNDS_MARGIN>" + this.openUnsettledFundsMargin + "</OPEN_UNSETTLED_FUNDS_MARGIN>" + endline);

        if (ApplicationDataContext.isHistoricalScenario()) {
            UTCalendar startDateCal = new UTCalendar(this.startDate);
            stb.append(prefix + UTDisplayFormatter.TAB + "<START_DATE>" + startDateCal.formatDateParam() + "</START_DATE>" + endline);

            UTCalendar stopDateCal = new UTCalendar(this.stopDate);
            stb.append(prefix + UTDisplayFormatter.TAB + "<STOP_DATE>" + stopDateCal.formatDateParam() + "</STOP_DATE>" + endline);
        } else {
            stb.append(prefix + UTDisplayFormatter.TAB + "<START_DATE></START_DATE>" + endline);
            stb.append(prefix + UTDisplayFormatter.TAB + "<STOP_DATE></STOP_DATE>" + endline);
        }

        stb.append(prefix + UTDisplayFormatter.TAB + "<MACHINE_NAME>" + this.machineName + "</MACHINE_NAME>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<DATAFEED_TYPE>" + Config.getValue(ConfigDatafeed.CONFIG_DATAFEED_TYPE) + "</DATAFEED_TYPE>" + endline);

        return stb.toString();
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

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOpenNetLiquidationValue() {
        return openNetLiquidationValue;
    }

    public void setOpenNetLiquidationValue(double openNetLiquidationValue) {
        this.openNetLiquidationValue = openNetLiquidationValue;
    }

    public double getOpenTotalEquity() {
        return openTotalEquity;
    }

    public void setOpenTotalEquity(double openTotalEquity) {
        this.openTotalEquity = openTotalEquity;
    }

    public double getOpenCashOnHand() {
        return openCashOnHand;
    }

    public void setOpenCashOnHand(double openCashOnHand) {
        this.openCashOnHand = openCashOnHand;
    }

    public double getOpenAllocatedFunds() {
        return openAllocatedFunds;
    }

    public void setOpenAllocatedFunds(double openAllocatedFunds) {
        this.openAllocatedFunds = openAllocatedFunds;
    }

    public double getOpenUnsettledFunds() {
        return openUnsettledFunds;
    }

    public void setOpenUnsettledFunds(double openUnsettledFunds) {
        this.openUnsettledFunds = openUnsettledFunds;
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

    public double getOpenAllocatedFundsMargin() {
        return openAllocatedFundsMargin;
    }

    public void setOpenAllocatedFundsMargin(double openAllocatedFundsMargin) {
        this.openAllocatedFundsMargin = openAllocatedFundsMargin;
    }

    public double getOpenUnsettledFundsMargin() {
        return openUnsettledFundsMargin;
    }

    public void setOpenUnsettledFundsMargin(double openUnsettledFundsMargin) {
        this.openUnsettledFundsMargin = openUnsettledFundsMargin;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getDatafeedType() {
        return datafeedType;
    }

    public void setDatafeedType(String datafeedType) {
        this.datafeedType = datafeedType;
    }
}
