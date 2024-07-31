package com.greenmark.common.dto.account.database;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountCalculatorDto</p>
 * <p>Description: This DTO class is not used to store data into a DB table.  It is used to calculate account or scenario statistics, some of which are
 *    stored in the account_summaries table.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class AccountCalculatorDto extends AccountDbDecorator implements Serializable {
    public static final String CLASSNAME = "AccountCalculatorDto";
    private static final long serialVersionUID = 1L;

    protected String machineName = "";
    protected String modelName = "";
    protected long modelId;

    // NOTE: None of the following member variables are needed in XML Save/Restore.
    protected float calcPercentGrowthYearly = 0.0F;
    protected float calcPercentGrowthMonthly = 0.0F;
    protected float yearlyRoi = 0.0F;
    protected float monthlyRoi = 0.0F;
    protected float totalProfitLoss = 0.0f;

    protected float longYearlyRoi = 0.0F;
    protected float longMonthlyRoi = 0.0F;
    protected float longTotalProfitLoss = 0.0f;

    protected float shortYearlyRoi = 0.0F;
    protected float shortMonthlyRoi = 0.0F;
    protected float shortTotalProfitLoss = 0.0f;

    protected float numPositions = 0F;

    protected float numSuccessPositions = 0;
    protected float successPositionsPercent = 0.0F;
    protected float successTotalAmount = 0.0F;
    protected float successAvgGain = 0.0F;

    protected float numLossPositions = 0;
    protected float lossPositionsPercent = 0.0F;
    protected float lossTotalAmount = 0.0F;
    protected float lossAvgLoss = 0.0F;

    protected float numPainPositions = 0;
    protected float painPositionsPercent = 0.0F;
    protected float painTotalAmount = 0.0F;
    protected float painAvgLoss = 0.0F;

    protected float numRejectedPositions = 0;
    protected float rejectedPositionsPercent = 0.0F;
    protected float rejectedTotalAmount = 0.0F;
    protected float rejectedAvgLoss = 0.0F;

    protected float numConfirmRejectedPositions = 0;
    protected float confirmRejectedPositionsPercent = 0.0F;
    protected float confirmRejectedTotalAmount = 0.0F;
    protected float confirmRejectedAvgLoss = 0.0F;

    // ------------------------------------------------- LONG ---------------------------------------------
    protected float numLongSuccess = 0; // Using float for float divisor
    protected float longSuccessAvgGain = 0.0F; // For each position in numSuccessPositions, will add up their % growth
    protected float longPercentSuccess = 0.0F;

    protected float numLongLoss = 0; // Using float for float divisor
    protected float longLossAvgLoss = 0.0F; // For each position in numFailedPositions, will add up their % growth
    protected float longPercentLoss = 0.0F;

    protected float numLongPain = 0; // Using float for float divisor
    protected float longPainAvgLoss = 0.0F; // For each position in numFailedPositions, will add up their % growth
    protected float longPercentPain = 0.0F;

    protected float numLongRejected = 0; // Using float for float divisor
    protected float longRejectedAvgLoss = 0.0F; // For each position in numFailedPositions, will add up their % growth
    protected float longPercentRejected = 0.0F;

    protected float numLongConfirmRejected = 0; // Using float for float divisor
    protected float longConfirmRejectedAvgLoss = 0.0F; // For each position in numFailedPositions, will add up their % growth
    protected float longPercentConfirmRejected = 0.0F;

    // ------------------------------------------------- SHORT ---------------------------------------------
    protected float numShortSuccess = 0; // Using float for float divisor
    protected float shortSuccessAvgGain = 0.0F;
    protected float shortPercentSuccess = 0.0F;

    protected float numShortLoss = 0; // Using float for float divisor
    protected float shortLossAvgLoss = 0.0F; // For each position in numFailedPositions, will add up their % growth
    protected float shortPercentLoss = 0.0F;

    protected float numShortPain = 0; // Using float for float divisor
    protected float shortPainAvgLoss = 0.0F; // For each position in numFailedPositions, will add up their % growth
    protected float shortPercentPain = 0.0F;

    protected float numShortRejected = 0; // Using float for float divisor
    protected float shortRejectedAvgLoss = 0.0F; // For each position in numFailedPositions, will add up their % growth
    protected float shortPercentRejected = 0.0F;

    protected float numShortConfirmRejected = 0; // Using float for float divisor
    protected float shortConfirmRejectedAvgLoss = 0.0F; // For each position in numFailedPositions, will add up their % growth
    protected float shortPercentConfirmRejected = 0.0F;

    // ------------------------------------------------- DROPCAT ---------------------------------------------
    protected float dropcatPercentSuccessPositions = 0.0F;
    protected float dropcatTotalSuccessGainAmount = 0.0F; // For each position in numSuccessPositions, will add up their % growth
    protected int dropcatNumSuccessPositions = 0; // Using float for float divisor

    protected float dropcatTotalFailedLossAmount = 0.0F; // For each position in numFailedPositions, will add up their % growth
    protected int dropcatNumFailedPositions = 0; // Using float for float divisor

    // Calculate the amount invested versus the net liquid value
    protected double leverageRatio = 0.0F;

    public AccountCalculatorDto() {
        super();
        this.scenarioList = new ArrayList<>();
    }


    // ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
//	public AccountCalculatorDto(String xmldata) {
//		super(xmldata, trace);
//
//		this.machineName = UTXmlUtils.getXmlData(xmldata, "MACHINE_NAME");
//		this.modelName = UTXmlUtils.getXmlData(xmldata, "MODEL_NAME");
//		this.modelId = UTXmlUtils.getXmlDataAsLong(xmldata, "MODEL_ID");
//	}
//
//	public String toXmlWrapper(String prefix, String endline) {
//        String stb = prefix + "<ACCOUNT>" + endline +
//                prefix + toXml(prefix, endline) + endline +
//                prefix + "</ACCOUNT>" + endline;
//		return stb;
//	}
//
//	public String toXml(String prefix, String endline) {
//
//        String stb = super.toXml(prefix, endline) +
//                prefix + "<MACHINE_NAME>" + this.machineName + "</MACHINE_NAME>" + endline +
//                prefix + "<MODEL_NAME>" + this.modelName + "</MODEL_NAME>" + endline +
//                prefix + "<MODEL_ID>" + this.modelId + "</MODEL_ID>" + endline;
//
//		return stb;
//	}

    // ------------------------------------------- MEMBER VARIABLE WRAPPER METHODS -----------------------------------
    // Failsafe method for website
    public boolean isHasScenarios() {
        if (scenarioList == null || scenarioList.isEmpty()) {
            setHasScenarios(false);
            return hasScenarios;
        } else {
            setHasScenarios(true);
            return hasScenarios;
        }
    }

    // ------------------------------------------- SETTERS/GETTERS-----------------------------------
    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public float getCalcPercentGrowthYearly() {
        return calcPercentGrowthYearly;
    }

    public void setCalcPercentGrowthYearly(float calcPercentGrowthYearly) {
        this.calcPercentGrowthYearly = calcPercentGrowthYearly;
    }

    public float getCalcPercentGrowthMonthly() {
        return calcPercentGrowthMonthly;
    }

    public void setCalcPercentGrowthMonthly(float calcPercentGrowthMonthly) {
        this.calcPercentGrowthMonthly = calcPercentGrowthMonthly;
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

    public float getNumPositions() {
        return numPositions;
    }

    public void setNumPositions(float numPositions) {
        this.numPositions = numPositions;
    }

    public float getNumSuccessPositions() {
        return numSuccessPositions;
    }

    public void setNumSuccessPositions(float numSuccessPositions) {
        this.numSuccessPositions = numSuccessPositions;
    }

    public float getSuccessPositionsPercent() {
        return successPositionsPercent;
    }

    public void setSuccessPositionsPercent(float successPositionsPercent) {
        this.successPositionsPercent = successPositionsPercent;
    }

    public float getSuccessTotalAmount() {
        return successTotalAmount;
    }

    public void setSuccessTotalAmount(float successTotalAmount) {
        this.successTotalAmount = successTotalAmount;
    }

    public float getSuccessAvgGain() {
        return successAvgGain;
    }

    public void setSuccessAvgGain(float successAvgGain) {
        this.successAvgGain = successAvgGain;
    }

    public float getNumLossPositions() {
        return numLossPositions;
    }

    public void setNumLossPositions(float numLossPositions) {
        this.numLossPositions = numLossPositions;
    }

    public float getLossPositionsPercent() {
        return lossPositionsPercent;
    }

    public void setLossPositionsPercent(float lossPositionsPercent) {
        this.lossPositionsPercent = lossPositionsPercent;
    }

    public float getLossTotalAmount() {
        return lossTotalAmount;
    }

    public void setLossTotalAmount(float lossTotalAmount) {
        this.lossTotalAmount = lossTotalAmount;
    }

    public float getLossAvgLoss() {
        return lossAvgLoss;
    }

    public void setLossAvgLoss(float lossAvgLoss) {
        this.lossAvgLoss = lossAvgLoss;
    }

    public float getNumPainPositions() {
        return numPainPositions;
    }

    public void setNumPainPositions(float numPainPositions) {
        this.numPainPositions = numPainPositions;
    }

    public float getPainPositionsPercent() {
        return painPositionsPercent;
    }

    public void setPainPositionsPercent(float painPositionsPercent) {
        this.painPositionsPercent = painPositionsPercent;
    }

    public float getPainTotalAmount() {
        return painTotalAmount;
    }

    public void setPainTotalAmount(float painTotalAmount) {
        this.painTotalAmount = painTotalAmount;
    }

    public float getPainAvgLoss() {
        return painAvgLoss;
    }

    public void setPainAvgLoss(float painAvgLoss) {
        this.painAvgLoss = painAvgLoss;
    }

    public float getNumRejectedPositions() {
        return numRejectedPositions;
    }

    public void setNumRejectedPositions(float numRejectedPositions) {
        this.numRejectedPositions = numRejectedPositions;
    }

    public float getRejectedPositionsPercent() {
        return rejectedPositionsPercent;
    }

    public void setRejectedPositionsPercent(float rejectedPositionsPercent) {
        this.rejectedPositionsPercent = rejectedPositionsPercent;
    }

    public float getRejectedTotalAmount() {
        return rejectedTotalAmount;
    }

    public void setRejectedTotalAmount(float rejectedTotalAmount) {
        this.rejectedTotalAmount = rejectedTotalAmount;
    }

    public float getRejectedAvgLoss() {
        return rejectedAvgLoss;
    }

    public void setRejectedAvgLoss(float rejectedAvgLoss) {
        this.rejectedAvgLoss = rejectedAvgLoss;
    }

    public float getNumConfirmRejectedPositions() {
        return numConfirmRejectedPositions;
    }

    public void setNumConfirmRejectedPositions(float numConfirmRejectedPositions) {
        this.numConfirmRejectedPositions = numConfirmRejectedPositions;
    }

    public float getConfirmRejectedPositionsPercent() {
        return confirmRejectedPositionsPercent;
    }

    public void setConfirmRejectedPositionsPercent(float confirmRejectedPositionsPercent) {
        this.confirmRejectedPositionsPercent = confirmRejectedPositionsPercent;
    }

    public float getConfirmRejectedTotalAmount() {
        return confirmRejectedTotalAmount;
    }

    public void setConfirmRejectedTotalAmount(float confirmRejectedTotalAmount) {
        this.confirmRejectedTotalAmount = confirmRejectedTotalAmount;
    }

    public float getConfirmRejectedAvgLoss() {
        return confirmRejectedAvgLoss;
    }

    public void setConfirmRejectedAvgLoss(float confirmRejectedAvgLoss) {
        this.confirmRejectedAvgLoss = confirmRejectedAvgLoss;
    }

    public float getNumLongSuccess() {
        return numLongSuccess;
    }

    public void setNumLongSuccess(float numLongSuccess) {
        this.numLongSuccess = numLongSuccess;
    }

    public float getLongSuccessAvgGain() {
        return longSuccessAvgGain;
    }

    public void setLongSuccessAvgGain(float longSuccessAvgGain) {
        this.longSuccessAvgGain = longSuccessAvgGain;
    }

    public float getLongPercentSuccess() {
        return longPercentSuccess;
    }

    public void setLongPercentSuccess(float longPercentSuccess) {
        this.longPercentSuccess = longPercentSuccess;
    }

    public float getNumLongLoss() {
        return numLongLoss;
    }

    public void setNumLongLoss(float numLongLoss) {
        this.numLongLoss = numLongLoss;
    }

    public float getLongLossAvgLoss() {
        return longLossAvgLoss;
    }

    public void setLongLossAvgLoss(float longLossAvgLoss) {
        this.longLossAvgLoss = longLossAvgLoss;
    }

    public float getLongPercentLoss() {
        return longPercentLoss;
    }

    public void setLongPercentLoss(float longPercentLoss) {
        this.longPercentLoss = longPercentLoss;
    }

    public float getNumLongPain() {
        return numLongPain;
    }

    public void setNumLongPain(float numLongPain) {
        this.numLongPain = numLongPain;
    }

    public float getLongPainAvgLoss() {
        return longPainAvgLoss;
    }

    public void setLongPainAvgLoss(float longPainAvgLoss) {
        this.longPainAvgLoss = longPainAvgLoss;
    }

    public float getLongPercentPain() {
        return longPercentPain;
    }

    public void setLongPercentPain(float longPercentPain) {
        this.longPercentPain = longPercentPain;
    }

    public float getNumLongRejected() {
        return numLongRejected;
    }

    public void setNumLongRejected(float numLongRejected) {
        this.numLongRejected = numLongRejected;
    }

    public float getLongRejectedAvgLoss() {
        return longRejectedAvgLoss;
    }

    public void setLongRejectedAvgLoss(float longRejectedAvgLoss) {
        this.longRejectedAvgLoss = longRejectedAvgLoss;
    }

    public float getLongPercentRejected() {
        return longPercentRejected;
    }

    public void setLongPercentRejected(float longPercentRejected) {
        this.longPercentRejected = longPercentRejected;
    }

    public float getNumLongConfirmRejected() {
        return numLongConfirmRejected;
    }

    public void setNumLongConfirmRejected(float numLongConfirmRejected) {
        this.numLongConfirmRejected = numLongConfirmRejected;
    }

    public float getLongConfirmRejectedAvgLoss() {
        return longConfirmRejectedAvgLoss;
    }

    public void setLongConfirmRejectedAvgLoss(float longConfirmRejectedAvgLoss) {
        this.longConfirmRejectedAvgLoss = longConfirmRejectedAvgLoss;
    }

    public float getLongPercentConfirmRejected() {
        return longPercentConfirmRejected;
    }

    public void setLongPercentConfirmRejected(float longPercentConfirmRejected) {
        this.longPercentConfirmRejected = longPercentConfirmRejected;
    }

    public float getNumShortSuccess() {
        return numShortSuccess;
    }

    public void setNumShortSuccess(float numShortSuccess) {
        this.numShortSuccess = numShortSuccess;
    }

    public float getShortSuccessAvgGain() {
        return shortSuccessAvgGain;
    }

    public void setShortSuccessAvgGain(float shortSuccessAvgGain) {
        this.shortSuccessAvgGain = shortSuccessAvgGain;
    }

    public float getShortPercentSuccess() {
        return shortPercentSuccess;
    }

    public void setShortPercentSuccess(float shortPercentSuccess) {
        this.shortPercentSuccess = shortPercentSuccess;
    }

    public float getNumShortLoss() {
        return numShortLoss;
    }

    public void setNumShortLoss(float numShortLoss) {
        this.numShortLoss = numShortLoss;
    }

    public float getShortLossAvgLoss() {
        return shortLossAvgLoss;
    }

    public void setShortLossAvgLoss(float shortLossAvgLoss) {
        this.shortLossAvgLoss = shortLossAvgLoss;
    }

    public float getShortPercentLoss() {
        return shortPercentLoss;
    }

    public void setShortPercentLoss(float shortPercentLoss) {
        this.shortPercentLoss = shortPercentLoss;
    }

    public float getNumShortPain() {
        return numShortPain;
    }

    public void setNumShortPain(float numShortPain) {
        this.numShortPain = numShortPain;
    }

    public float getShortPainAvgLoss() {
        return shortPainAvgLoss;
    }

    public void setShortPainAvgLoss(float shortPainAvgLoss) {
        this.shortPainAvgLoss = shortPainAvgLoss;
    }

    public float getShortPercentPain() {
        return shortPercentPain;
    }

    public void setShortPercentPain(float shortPercentPain) {
        this.shortPercentPain = shortPercentPain;
    }

    public float getNumShortRejected() {
        return numShortRejected;
    }

    public void setNumShortRejected(float numShortRejected) {
        this.numShortRejected = numShortRejected;
    }

    public float getShortRejectedAvgLoss() {
        return shortRejectedAvgLoss;
    }

    public void setShortRejectedAvgLoss(float shortRejectedAvgLoss) {
        this.shortRejectedAvgLoss = shortRejectedAvgLoss;
    }

    public float getShortPercentRejected() {
        return shortPercentRejected;
    }

    public void setShortPercentRejected(float shortPercentRejected) {
        this.shortPercentRejected = shortPercentRejected;
    }

    public float getNumShortConfirmRejected() {
        return numShortConfirmRejected;
    }

    public void setNumShortConfirmRejected(float numShortConfirmRejected) {
        this.numShortConfirmRejected = numShortConfirmRejected;
    }

    public float getShortConfirmRejectedAvgLoss() {
        return shortConfirmRejectedAvgLoss;
    }

    public void setShortConfirmRejectedAvgLoss(float shortConfirmRejectedAvgLoss) {
        this.shortConfirmRejectedAvgLoss = shortConfirmRejectedAvgLoss;
    }

    public float getShortPercentConfirmRejected() {
        return shortPercentConfirmRejected;
    }

    public void setShortPercentConfirmRejected(float shortPercentConfirmRejected) {
        this.shortPercentConfirmRejected = shortPercentConfirmRejected;
    }

    public float getDropcatPercentSuccessPositions() {
        return dropcatPercentSuccessPositions;
    }

    public void setDropcatPercentSuccessPositions(float dropcatPercentSuccessPositions) {
        this.dropcatPercentSuccessPositions = dropcatPercentSuccessPositions;
    }

    public float getDropcatTotalSuccessGainAmount() {
        return dropcatTotalSuccessGainAmount;
    }

    public void setDropcatTotalSuccessGainAmount(float dropcatTotalSuccessGainAmount) {
        this.dropcatTotalSuccessGainAmount = dropcatTotalSuccessGainAmount;
    }

    public int getDropcatNumSuccessPositions() {
        return dropcatNumSuccessPositions;
    }

    public void setDropcatNumSuccessPositions(int dropcatNumSuccessPositions) {
        this.dropcatNumSuccessPositions = dropcatNumSuccessPositions;
    }

    public float getDropcatTotalFailedLossAmount() {
        return dropcatTotalFailedLossAmount;
    }

    public void setDropcatTotalFailedLossAmount(float dropcatTotalFailedLossAmount) {
        this.dropcatTotalFailedLossAmount = dropcatTotalFailedLossAmount;
    }

    public int getDropcatNumFailedPositions() {
        return dropcatNumFailedPositions;
    }

    public void setDropcatNumFailedPositions(int dropcatNumFailedPositions) {
        this.dropcatNumFailedPositions = dropcatNumFailedPositions;
    }

    public double getLeverageRatio() {
        return leverageRatio;
    }

    public void setLeverageRatio(double leverageRatio) {
        this.leverageRatio = leverageRatio;
    }
}
