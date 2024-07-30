package com.greenmark.common.dto.account;

import java.io.Serializable;

import com.greenmark.common.GmConstants;
import com.greenmark.common.dto.parameters.AccountBalanceParams;
import com.greenmark.common.dto.scenario.ScenarioDto;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountSummaryDto</p>
 * <p>Description: This DTO is for the results database account_summaries table.  A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 * DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class AccountSummaryDto implements Serializable {
	private static final String CLASSNAME = "AccountSummaryDto";
	private static final long serialVersionUID = 1L;

	public static final int ACCOUNT_SUMMARY_TYPE_CURRENT_RECORD = 1;
	public static final int ACCOUNT_SUMMARY_TYPE_COMPARE_RECORD = 2;

	protected long id;
	protected int active = GmConstants.OBJECT_ACTIVE;

	protected long accountId;

	protected int accountSummaryType;
	protected float yearlyRoi = 0F;
	protected float monthlyRoi = 0F;
	protected float totalProfitLoss = 0F;

	protected float successPositionsPercent = 0F;
	protected float successAvgGain = 0f;
	protected float lossPositionsPercent = 0F;
	protected float lossAvgLoss = 0F;
	protected float painPositionsPercent = 0F;
	protected float painAvgLoss = 0F;
	protected float rejectedPositionsPercent = 0F;
	protected float rejectedAvgLoss = 0F;
	protected float confirmRejectedPositionsPercent = 0F;
	protected float confirmRejectedAvgLoss = 0F;

	protected double openNetLiquidationValue = 0F;
	protected double openTotalEquity = 0F;
	protected double openTotalEquityMargin = 0F;
	protected double openCashOnHand = 0F;
	protected double openCashOnHandMargin = 0F;

	protected double closeNetLiquidationValue = 0F;
	protected double closeTotalEquity = 0F;
	protected double closeTotalEquityMargin = 0F;
	protected double closeCashOnHand = 0F;
	protected double closeCashOnHandMargin = 0F;

	protected float longYearlyRoi = 0F;
	protected float longMonthlyRoi = 0F;
	protected float longTotalProfitLoss = 0F;

	protected int longNumPositions = 0;
	protected float longPercentSuccess = 0F;
	protected float longSuccessAvgGain = 0F;

	protected float longPercentLoss = 0F;
	protected float longLossAvgLoss = 0F;

	protected float longPercentPain = 0F;
	protected float longPainAvgLoss = 0F;

	protected float longPercentRejected = 0F;
	protected float longRejectedAvgLoss = 0F;

	protected float longPercentConfirmRejected = 0F;
	protected float longConfirmRejectedAvgLoss = 0F;

	protected float shortYearlyRoi = 0F;
	protected float shortMonthlyRoi = 0F;
	protected float shortTotalProfitLoss = 0F;

	protected int shortNumPositions = 0;
	protected float shortPercentSuccess = 0F;
	protected float shortSuccessAvgGain = 0F;

	protected float shortPercentLoss = 0F;
	protected float shortLossAvgLoss = 0F;

	protected float shortPercentPain = 0F;
	protected float shortPainAvgLoss = 0F;

	protected float shortPercentRejected = 0F;
	protected float shortRejectedAvgLoss = 0F;

	protected float shortPercentConfirmRejected = 0F;
	protected float shortConfirmRejectedAvgLoss = 0F;

	public AccountSummaryDto() {
	}

	public AccountSummaryDto(AccountBalanceParams account) {
		this.openNetLiquidationValue = account.getNetLiquidationValue();
		this.openTotalEquity = account.getTotalEquityCash();
		this.openTotalEquityMargin = account.getTotalEquityMargin();
		this.openCashOnHand = account.getAvailableFundsCash();
		this.openCashOnHandMargin = account.getAvailableFundsMargin();
	}

	public AccountSummaryDto(ScenarioDto scenarioDbDecorator) {
		this.openNetLiquidationValue = scenarioDbDecorator.getOpenNetLiquidationValue();
		this.openTotalEquity = scenarioDbDecorator.getOpenTotalEquity();
		this.openTotalEquityMargin = scenarioDbDecorator.getOpenTotalEquityMargin();
		this.openCashOnHand = scenarioDbDecorator.getOpenCashOnHand();
		this.openCashOnHandMargin = scenarioDbDecorator.getOpenCashOnHandMargin();
	}

	// ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
	public AccountSummaryDto(String xmldata) {
		try {
			this.id = UTXmlUtils.getXmlDataAsInt(xmldata, "ID");
			this.active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");

			this.accountId = UTXmlUtils.getXmlDataAsInt(xmldata, "ACCOUNT_ID");

			this.yearlyRoi = UTXmlUtils.getXmlDataAsFloat(xmldata, "YEARLY_ROI");
			this.monthlyRoi = UTXmlUtils.getXmlDataAsFloat(xmldata, "MONTHLY_ROI");
			this.totalProfitLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "TOTAL_PROFIT_LOSS");

			this.accountSummaryType = UTXmlUtils.getXmlDataAsInt(xmldata, "ACCOUNT_SUMMARY_TYPE");
			this.successPositionsPercent = UTXmlUtils.getXmlDataAsFloat(xmldata, "SUCCESS_PERCENT");
			this.successAvgGain = UTXmlUtils.getXmlDataAsFloat(xmldata, "SUCCESS_GAIN");
			this.lossPositionsPercent = UTXmlUtils.getXmlDataAsFloat(xmldata, "LOSS_PERCENT");
			this.lossAvgLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "LOSS_LOSS");
			this.painPositionsPercent = UTXmlUtils.getXmlDataAsFloat(xmldata, "PAIN_PERCENT");
			this.painAvgLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "PAIN_LOSS");
			this.rejectedPositionsPercent = UTXmlUtils.getXmlDataAsFloat(xmldata, "REJECTED_PERCENT");
			this.rejectedAvgLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "REJECTED_LOSS");
			this.confirmRejectedPositionsPercent = UTXmlUtils.getXmlDataAsFloat(xmldata, "CONFIRM_REJECTED_PERCENT");
			this.confirmRejectedAvgLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "CONFIRM_REJECTED_LOSS");

			this.openNetLiquidationValue = UTXmlUtils.getXmlDataAsDouble(xmldata, "OPEN_NET_LIQUIDATION_VALUE");
			this.openTotalEquity = UTXmlUtils.getXmlDataAsDouble(xmldata, "OPEN_TOTAL_EQUITY");
			this.openTotalEquityMargin = UTXmlUtils.getXmlDataAsDouble(xmldata, "OPEN_TOTAL_EQUITY_MARGIN");
			this.openCashOnHand = UTXmlUtils.getXmlDataAsDouble(xmldata, "OPEN_CASH_ON_HAND");
			this.openCashOnHandMargin = UTXmlUtils.getXmlDataAsDouble(xmldata, "OPEN_CASH_ON_HAND_MARGIN");

			this.closeNetLiquidationValue = UTXmlUtils.getXmlDataAsDouble(xmldata, "CLOSE_NET_LIQUIDATION_VALUE");
			this.closeTotalEquity = UTXmlUtils.getXmlDataAsDouble(xmldata, "CLOSE_TOTAL_EQUITY");
			this.closeTotalEquityMargin = UTXmlUtils.getXmlDataAsDouble(xmldata, "CLOSE_TOTAL_EQUITY_MARGIN");
			this.closeCashOnHand = UTXmlUtils.getXmlDataAsDouble(xmldata, "CLOSE_CASH_ON_HAND");
			this.closeCashOnHandMargin = UTXmlUtils.getXmlDataAsDouble(xmldata, "CLOSE_CASH_ON_HAND_MARGIN");

			this.longNumPositions = UTXmlUtils.getXmlDataAsInt(xmldata, "LONG_NUM_POSITIONS");
			this.longPercentSuccess = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_PERCENT_SUCCESS");
			this.longSuccessAvgGain = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_SUCCESS_AVG_GAIN");
			this.longPercentLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_PERCENT_LOSS");
			this.longLossAvgLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_LOSS_AVG_LOSS");
			this.longPercentPain = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_PERCENT_PAIN");
			this.longPainAvgLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_PAIN_AVG_LOSS");
			this.longPercentRejected = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_PERCENT_REJECTED");
			this.longRejectedAvgLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_REJECTED_AVG_LOSS");
			this.longPercentConfirmRejected = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_PERCENT_CONFIRM_REJECTED");
			this.longConfirmRejectedAvgLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_CONFIRM_REJECTED_AVG_LOSS");

			this.shortNumPositions = UTXmlUtils.getXmlDataAsInt(xmldata, "SHORT_NUM_POSITIONS");
			this.shortPercentSuccess = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_PERCENT_SUCCESS");
			this.shortSuccessAvgGain = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_SUCCESS_AVG_GAIN");
			this.shortPercentLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_PERCENT_LOSS");
			this.shortLossAvgLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_LOSS_AVG_LOSS");
			this.shortPercentPain = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_PERCENT_PAIN");
			this.shortPainAvgLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_PAIN_AVG_LOSS");
			this.shortPercentRejected = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_PERCENT_REJECTED");
			this.shortRejectedAvgLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_REJECTED_AVG_LOSS");
			this.shortPercentConfirmRejected = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_PERCENT_CONFIRM_REJECTED");
			this.shortConfirmRejectedAvgLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_CONFIRM_REJECTED_AVG_LOSS");
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + ".Constructor; message [" + e.getMessage() + "]");
		}

	}

	public String toXmlWrapper(String prefix, String endline) {
        String stb = prefix + "<ACCOUNT_SUMMARY>" + endline +
                prefix + toXml(prefix, endline) + endline +
                prefix + "</ACCOUNT_SUMMARY>" + endline;
		return stb;
	}

	public final String toXml(String prefix, String endline) {

        String stb = prefix + "<ID>" + this.getId() + "</ID>" + endline +
                prefix + "<ACTIVE>" + this.getActive() + "</ACTIVE>" + endline +
                prefix + "<ACCOUNT_ID>" + this.getAccountId() + "</ACCOUNT_ID>" + endline +
                prefix + "<YEARLY_ROI>" + this.getYearlyRoi() + "</YEARLY_ROI>" + endline +
                prefix + "<MONTHLY_ROI>" + this.getMonthlyRoi() + "</MONTHLY_ROI>" + endline +
                prefix + "<TOTAL_PROFIT_LOSS>" + this.getTotalProfitLoss() + "</TOTAL_PROFIT_LOSS>" + endline +
                prefix + "<ACCOUNT_SUMMARY_TYPE>" + this.getTotalProfitLoss() + "</ACCOUNT_SUMMARY_TYPE>" + endline +
                prefix + "<SUCCESS_PERCENT>" + this.getTotalProfitLoss() + "</SUCCESS_PERCENT>" + endline +
                prefix + "<SUCCESS_GAIN>" + this.getTotalProfitLoss() + "</SUCCESS_GAIN>" + endline +
                prefix + "<LOSS_PERCENT>" + this.getTotalProfitLoss() + "</LOSS_PERCENT>" + endline +
                prefix + "<LOSS_LOSS>" + this.getTotalProfitLoss() + "</LOSS_LOSS>" + endline +
                prefix + "<PAIN_PERCENT>" + this.getTotalProfitLoss() + "</PAIN_PERCENT>" + endline +
                prefix + "<PAIN_LOSS>" + this.getTotalProfitLoss() + "</PAIN_LOSS>" + endline +
                prefix + "<REJECTED_PERCENT>" + this.getTotalProfitLoss() + "</REJECTED_PERCENT>" + endline +
                prefix + "<REJECTED_LOSS>" + this.getTotalProfitLoss() + "</REJECTED_LOSS>" + endline +
                prefix + "<CONFIRM_REJECTED_PERCENT>" + this.getTotalProfitLoss() + "</CONFIRM_REJECTED_PERCENT>" + endline +
                prefix + "<CONFIRM_REJECTED_LOSS>" + this.getTotalProfitLoss() + "</CONFIRM_REJECTED_LOSS>" + endline +
                prefix + "<OPEN_NET_LIQUIDATION_VALUE>" + this.getOpenNetLiquidationValue() + "</OPEN_NET_LIQUIDATION_VALUE>" + endline +
                prefix + "<OPEN_TOTAL_EQUITY>" + this.getOpenTotalEquity() + "</OPEN_TOTAL_EQUITY>" + endline +
                prefix + "<OPEN_TOTAL_EQUITY_MARGIN>" + this.getOpenTotalEquityMargin() + "</OPEN_TOTAL_EQUITY_MARGIN>" + endline +
                prefix + "<OPEN_CASH_ON_HAND>" + this.getOpenCashOnHand() + "</OPEN_CASH_ON_HAND>" + endline +
                prefix + "<OPEN_CASH_ON_HAND_MARGIN>" + this.getOpenCashOnHandMargin() + "</OPEN_CASH_ON_HAND_MARGIN>" + endline +
                prefix + "<CLOSE_NET_LIQUIDATION_VALUE>" + this.getCloseNetLiquidationValue() + "</CLOSE_NET_LIQUIDATION_VALUE>" + endline +
                prefix + "<CLOSE_TOTAL_EQUITY>" + this.getCloseTotalEquity() + "</CLOSE_TOTAL_EQUITY>" + endline +
                prefix + "<CLOSE_TOTAL_EQUITY_MARGIN>" + this.getCloseTotalEquityMargin() + "</CLOSE_TOTAL_EQUITY_MARGIN>" + endline +
                prefix + "<CLOSE_CASH_ON_HAND>" + this.getCloseCashOnHand() + "</CLOSE_CASH_ON_HAND>" + endline +
                prefix + "<CLOSE_CASH_ON_HAND_MARGIN>" + this.getCloseCashOnHandMargin() + "</CLOSE_CASH_ON_HAND_MARGIN>" + endline +
                prefix + "<LONG_NUM_POSITIONS>" + this.getLongNumPositions() + "</LONG_NUM_POSITIONS>" + endline +
                prefix + "<LONG_PERCENT_SUCCESS>" + this.getLongPercentSuccess() + "</LONG_PERCENT_SUCCESS>" + endline +
                prefix + "<LONG_SUCCESS_AVG_GAIN>" + this.getLongSuccessAvgGain() + "</LONG_SUCCESS_AVG_GAIN>" + endline +
                prefix + "<LONG_PERCENT_LOSS>" + this.getLongPercentLoss() + "</LONG_PERCENT_LOSS>" + endline +
                prefix + "<LONG_LOSS_AVG_LOSS>" + this.getLongLossAvgLoss() + "</LONG_LOSS_AVG_LOSS>" + endline +
                prefix + "<LONG_PERCENT_PAIN>" + this.getLongPercentPain() + "</LONG_PERCENT_PAIN>" + endline +
                prefix + "<LONG_PAIN_AVG_LOSS>" + this.getLongPainAvgLoss() + "</LONG_PAIN_AVG_LOSS>" + endline +
                prefix + "<LONG_PERCENT_REJECTED>" + this.getLongPercentRejected() + "</LONG_PERCENT_REJECTED>" + endline +
                prefix + "<LONG_REJECTED_AVG_LOSS>" + this.getLongRejectedAvgLoss() + "</LONG_REJECTED_AVG_LOSS>" + endline +
                prefix + "<LONG_PERCENT_CONFIRM_REJECTED>" + this.getLongPercentConfirmRejected() + "</LONG_PERCENT_CONFIRM_REJECTED>" + endline +
                prefix + "<LONG_CONFIRM_REJECTED_AVG_LOSS>" + this.getLongConfirmRejectedAvgLoss() + "</LONG_CONFIRM_REJECTED_AVG_LOSS>" + endline +
                prefix + "<SHORT_NUM_POSITIONS>" + this.getShortNumPositions() + "</SHORT_NUM_POSITIONS>" + endline +
                prefix + "<SHORT_PERCENT_SUCCESS>" + this.getShortPercentSuccess() + "</SHORT_PERCENT_SUCCESS>" + endline +
                prefix + "<SHORT_SUCCESS_AVG_GAIN>" + this.getShortSuccessAvgGain() + "</SHORT_SUCCESS_AVG_GAIN>" + endline +
                prefix + "<SHORT_PERCENT_LOSS>" + this.getShortPercentLoss() + "</SHORT_PERCENT_LOSS>" + endline +
                prefix + "<SHORT_LOSS_AVG_LOSS>" + this.getShortLossAvgLoss() + "</SHORT_LOSS_AVG_LOSS>" + endline +
                prefix + "<SHORT_PERCENT_PAIN>" + this.getShortPercentPain() + "</SHORT_PERCENT_PAIN>" + endline +
                prefix + "<SHORT_PAIN_AVG_LOSS>" + this.getShortPainAvgLoss() + "</SHORT_PAIN_AVG_LOSS>" + endline +
                prefix + "<SHORT_PERCENT_REJECTED>" + this.getShortPercentRejected() + "</SHORT_PERCENT_REJECTED>" + endline +
                prefix + "<SHORT_REJECTED_AVG_LOSS>" + this.getShortRejectedAvgLoss() + "</SHORT_REJECTED_AVG_LOSS>" + endline +
                prefix + "<SHORT_PERCENT_CONFIRM_REJECTED>" + this.getShortPercentConfirmRejected() + "</SHORT_PERCENT_CONFIRM_REJECTED>" + endline +
                prefix + "<SHORT_CONFIRM_REJECTED_AVG_LOSS>" + this.getShortConfirmRejectedAvgLoss() + "</SHORT_CONFIRM_REJECTED_AVG_LOSS>" + endline;

		return stb;
	}

	public int getTotalNumPositions() {
		return longNumPositions + shortNumPositions;
	}

	// --------------------------------------------- SETTERS/GETTERS ---------------------------------------
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getAccountSummaryType() {
		return accountSummaryType;
	}

	public void setAccountSummaryType(int accountSummaryType) {
		this.accountSummaryType = accountSummaryType;
	}

	public float getYearlyRoi() {
		return yearlyRoi;
	}

	public void setYearlyRoi(float yearlyRoi) {
		this.yearlyRoi = yearlyRoi;
	}

	public float getMonthlyRoi() {
		return monthlyRoi;
	}

	public void setMonthlyRoi(float monthlyRoi) {
		this.monthlyRoi = monthlyRoi;
	}

	public float getTotalProfitLoss() {
		return totalProfitLoss;
	}

	public void setTotalProfitLoss(float totalProfitLoss) {
		this.totalProfitLoss = totalProfitLoss;
	}

	public float getSuccessPositionsPercent() {
		return successPositionsPercent;
	}

	public void setSuccessPositionsPercent(float successPositionsPercent) {
		this.successPositionsPercent = successPositionsPercent;
	}

	public float getSuccessAvgGain() {
		return successAvgGain;
	}

	public void setSuccessAvgGain(float successAvgGain) {
		this.successAvgGain = successAvgGain;
	}

	public float getLossPositionsPercent() {
		return lossPositionsPercent;
	}

	public void setLossPositionsPercent(float lossPositionsPercent) {
		this.lossPositionsPercent = lossPositionsPercent;
	}

	public float getLossAvgLoss() {
		return lossAvgLoss;
	}

	public void setLossAvgLoss(float lossAvgLoss) {
		this.lossAvgLoss = lossAvgLoss;
	}

	public float getPainPositionsPercent() {
		return painPositionsPercent;
	}

	public void setPainPositionsPercent(float painPositionsPercent) {
		this.painPositionsPercent = painPositionsPercent;
	}

	public float getPainAvgLoss() {
		return painAvgLoss;
	}

	public void setPainAvgLoss(float painAvgLoss) {
		this.painAvgLoss = painAvgLoss;
	}

	public float getRejectedPositionsPercent() {
		return rejectedPositionsPercent;
	}

	public void setRejectedPositionsPercent(float rejectedPositionsPercent) {
		this.rejectedPositionsPercent = rejectedPositionsPercent;
	}

	public float getRejectedAvgLoss() {
		return rejectedAvgLoss;
	}

	public void setRejectedAvgLoss(float rejectedAvgLoss) {
		this.rejectedAvgLoss = rejectedAvgLoss;
	}

	public float getConfirmRejectedPositionsPercent() {
		return confirmRejectedPositionsPercent;
	}

	public void setConfirmRejectedPositionsPercent(float confirmRejectedPositionsPercent) {
		this.confirmRejectedPositionsPercent = confirmRejectedPositionsPercent;
	}

	public float getConfirmRejectedAvgLoss() {
		return confirmRejectedAvgLoss;
	}

	public void setConfirmRejectedAvgLoss(float confirmRejectedAvgLoss) {
		this.confirmRejectedAvgLoss = confirmRejectedAvgLoss;
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

	public double getOpenTotalEquityMargin() {
		return openTotalEquityMargin;
	}

	public void setOpenTotalEquityMargin(double openTotalEquityMargin) {
		this.openTotalEquityMargin = openTotalEquityMargin;
	}

	public double getOpenCashOnHand() {
		return openCashOnHand;
	}

	public void setOpenCashOnHand(double openCashOnHand) {
		this.openCashOnHand = openCashOnHand;
	}

	public double getOpenCashOnHandMargin() {
		return openCashOnHandMargin;
	}

	public void setOpenCashOnHandMargin(double openCashOnHandMargin) {
		this.openCashOnHandMargin = openCashOnHandMargin;
	}

	public double getCloseNetLiquidationValue() {
		return closeNetLiquidationValue;
	}

	public void setCloseNetLiquidationValue(double closeNetLiquidationValue) {
		this.closeNetLiquidationValue = closeNetLiquidationValue;
	}

	public double getCloseTotalEquity() {
		return closeTotalEquity;
	}

	public void setCloseTotalEquity(double closeTotalEquity) {
		this.closeTotalEquity = closeTotalEquity;
	}

	public double getCloseTotalEquityMargin() {
		return closeTotalEquityMargin;
	}

	public void setCloseTotalEquityMargin(double closeTotalEquityMargin) {
		this.closeTotalEquityMargin = closeTotalEquityMargin;
	}

	public double getCloseCashOnHand() {
		return closeCashOnHand;
	}

	public void setCloseCashOnHand(double closeCashOnHand) {
		this.closeCashOnHand = closeCashOnHand;
	}

	public double getCloseCashOnHandMargin() {
		return closeCashOnHandMargin;
	}

	public void setCloseCashOnHandMargin(double closeCashOnHandMargin) {
		this.closeCashOnHandMargin = closeCashOnHandMargin;
	}

	public float getLongYearlyRoi() {
		return longYearlyRoi;
	}

	public void setLongYearlyRoi(float longYearlyRoi) {
		this.longYearlyRoi = longYearlyRoi;
	}

	public float getLongMonthlyRoi() {
		return longMonthlyRoi;
	}

	public void setLongMonthlyRoi(float longMonthlyRoi) {
		this.longMonthlyRoi = longMonthlyRoi;
	}

	public float getLongTotalProfitLoss() {
		return longTotalProfitLoss;
	}

	public void setLongTotalProfitLoss(float longTotalProfitLoss) {
		this.longTotalProfitLoss = longTotalProfitLoss;
	}

	public int getLongNumPositions() {
		return longNumPositions;
	}

	public void setLongNumPositions(int longNumPositions) {
		this.longNumPositions = longNumPositions;
	}

	public float getLongPercentSuccess() {
		return longPercentSuccess;
	}

	public void setLongPercentSuccess(float longPercentSuccess) {
		this.longPercentSuccess = longPercentSuccess;
	}

	public float getLongSuccessAvgGain() {
		return longSuccessAvgGain;
	}

	public void setLongSuccessAvgGain(float longSuccessAvgGain) {
		this.longSuccessAvgGain = longSuccessAvgGain;
	}

	public float getLongPercentLoss() {
		return longPercentLoss;
	}

	public void setLongPercentLoss(float longPercentLoss) {
		this.longPercentLoss = longPercentLoss;
	}

	public float getLongLossAvgLoss() {
		return longLossAvgLoss;
	}

	public void setLongLossAvgLoss(float longLossAvgLoss) {
		this.longLossAvgLoss = longLossAvgLoss;
	}

	public float getLongPercentPain() {
		return longPercentPain;
	}

	public void setLongPercentPain(float longPercentPain) {
		this.longPercentPain = longPercentPain;
	}

	public float getLongPainAvgLoss() {
		return longPainAvgLoss;
	}

	public void setLongPainAvgLoss(float longPainAvgLoss) {
		this.longPainAvgLoss = longPainAvgLoss;
	}

	public float getLongPercentRejected() {
		return longPercentRejected;
	}

	public void setLongPercentRejected(float longPercentRejected) {
		this.longPercentRejected = longPercentRejected;
	}

	public float getLongRejectedAvgLoss() {
		return longRejectedAvgLoss;
	}

	public void setLongRejectedAvgLoss(float longRejectedAvgLoss) {
		this.longRejectedAvgLoss = longRejectedAvgLoss;
	}

	public float getLongPercentConfirmRejected() {
		return longPercentConfirmRejected;
	}

	public void setLongPercentConfirmRejected(float longPercentConfirmRejected) {
		this.longPercentConfirmRejected = longPercentConfirmRejected;
	}

	public float getLongConfirmRejectedAvgLoss() {
		return longConfirmRejectedAvgLoss;
	}

	public void setLongConfirmRejectedAvgLoss(float longConfirmRejectedAvgLoss) {
		this.longConfirmRejectedAvgLoss = longConfirmRejectedAvgLoss;
	}

	public float getShortYearlyRoi() {
		return shortYearlyRoi;
	}

	public void setShortYearlyRoi(float shortYearlyRoi) {
		this.shortYearlyRoi = shortYearlyRoi;
	}

	public float getShortMonthlyRoi() {
		return shortMonthlyRoi;
	}

	public void setShortMonthlyRoi(float shortMonthlyRoi) {
		this.shortMonthlyRoi = shortMonthlyRoi;
	}

	public float getShortTotalProfitLoss() {
		return shortTotalProfitLoss;
	}

	public void setShortTotalProfitLoss(float shortTotalProfitLoss) {
		this.shortTotalProfitLoss = shortTotalProfitLoss;
	}

	public int getShortNumPositions() {
		return shortNumPositions;
	}

	public void setShortNumPositions(int shortNumPositions) {
		this.shortNumPositions = shortNumPositions;
	}

	public float getShortPercentSuccess() {
		return shortPercentSuccess;
	}

	public void setShortPercentSuccess(float shortPercentSuccess) {
		this.shortPercentSuccess = shortPercentSuccess;
	}

	public float getShortSuccessAvgGain() {
		return shortSuccessAvgGain;
	}

	public void setShortSuccessAvgGain(float shortSuccessAvgGain) {
		this.shortSuccessAvgGain = shortSuccessAvgGain;
	}

	public float getShortPercentLoss() {
		return shortPercentLoss;
	}

	public void setShortPercentLoss(float shortPercentLoss) {
		this.shortPercentLoss = shortPercentLoss;
	}

	public float getShortLossAvgLoss() {
		return shortLossAvgLoss;
	}

	public void setShortLossAvgLoss(float shortLossAvgLoss) {
		this.shortLossAvgLoss = shortLossAvgLoss;
	}

	public float getShortPercentPain() {
		return shortPercentPain;
	}

	public void setShortPercentPain(float shortPercentPain) {
		this.shortPercentPain = shortPercentPain;
	}

	public float getShortPainAvgLoss() {
		return shortPainAvgLoss;
	}

	public void setShortPainAvgLoss(float shortPainAvgLoss) {
		this.shortPainAvgLoss = shortPainAvgLoss;
	}

	public float getShortPercentRejected() {
		return shortPercentRejected;
	}

	public void setShortPercentRejected(float shortPercentRejected) {
		this.shortPercentRejected = shortPercentRejected;
	}

	public float getShortRejectedAvgLoss() {
		return shortRejectedAvgLoss;
	}

	public void setShortRejectedAvgLoss(float shortRejectedAvgLoss) {
		this.shortRejectedAvgLoss = shortRejectedAvgLoss;
	}

	public float getShortPercentConfirmRejected() {
		return shortPercentConfirmRejected;
	}

	public void setShortPercentConfirmRejected(float shortPercentConfirmRejected) {
		this.shortPercentConfirmRejected = shortPercentConfirmRejected;
	}

	public float getShortConfirmRejectedAvgLoss() {
		return shortConfirmRejectedAvgLoss;
	}

	public void setShortConfirmRejectedAvgLoss(float shortConfirmRejectedAvgLoss) {
		this.shortConfirmRejectedAvgLoss = shortConfirmRejectedAvgLoss;
	}
}
