package com.greenmark.common.dto.parameters;

import java.io.Serializable;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountSearchParams</p>
 * <p>Description: This class is used by the web application's search accounts feature and contains the parameters that are used to search for accounts.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
public class AccountSearchParams implements Serializable {
    public static final String CLASSNAME = "AccountSearchParams";
    private static final long serialVersionUID = 1L;

    private boolean findAccount_AccountDailys;
    private boolean findAccount_AccountHourlys;
    private boolean findAccount_Scenarios;

    private boolean findScenario_AccountDailys;
    private boolean findScenario_Positions;
    private boolean findScenario_DropcatPositions;
    private boolean findScenario_RejectedPositions;

    private boolean orderPositionsByBuyDate;

    private boolean includeStockSymbols;

    private boolean calcScenario_SuccessPositions;

    public AccountSearchParams() {
    }

    public boolean isFindAccount_AccountDailys() {
        return findAccount_AccountDailys;
    }

    public void setFindAccount_AccountDailys(boolean findAccount_AccountDailys) {
        this.findAccount_AccountDailys = findAccount_AccountDailys;
    }

    public boolean isFindAccount_Scenarios() {
        return findAccount_Scenarios;
    }

    public void setFindAccount_Scenarios(boolean findAccount_Scenarios) {
        this.findAccount_Scenarios = findAccount_Scenarios;
    }

    public boolean isFindScenario_AccountDailys() {
        return findScenario_AccountDailys;
    }

    public void setFindScenario_AccountDailys(boolean findScenario_AccountDailys) {
        this.findScenario_AccountDailys = findScenario_AccountDailys;
    }

    public boolean isFindScenario_Positions() {
        return findScenario_Positions;
    }

    public void setFindScenario_Positions(boolean findScenario_Positions) {
        this.findScenario_Positions = findScenario_Positions;
    }

    public boolean isFindScenario_RejectedPositions() {
        return findScenario_RejectedPositions;
    }

    public void setFindScenario_RejectedPositions(boolean findScenario_RejectedPositions) {
        this.findScenario_RejectedPositions = findScenario_RejectedPositions;
    }

    public boolean isFindScenario_DropcatPositions() {
        return findScenario_DropcatPositions;
    }

    public void setFindScenario_DropcatPositions(boolean findScenario_DropcatPositions) {
        this.findScenario_DropcatPositions = findScenario_DropcatPositions;
    }

    public boolean isIncludeStockSymbols() {
        return includeStockSymbols;
    }

    public void setIncludeStockSymbols(boolean includeStockSymbols) {
        this.includeStockSymbols = includeStockSymbols;
    }

    public boolean isCalcScenario_SuccessPositions() {
        return calcScenario_SuccessPositions;
    }

    public void setCalcScenario_SuccessPositions(boolean calcScenario_SuccessPositions) {
        this.calcScenario_SuccessPositions = calcScenario_SuccessPositions;
    }

    public boolean isOrderPositionsByBuyDate() {
        return orderPositionsByBuyDate;
    }

    public void setOrderPositionsByBuyDate(boolean orderPositionsByBuyDate) {
        this.orderPositionsByBuyDate = orderPositionsByBuyDate;
    }

    public boolean isFindAccount_AccountHourlys() {
        return findAccount_AccountHourlys;
    }

    public void setFindAccount_AccountHourlys(boolean findAccount_AccountHourlys) {
        this.findAccount_AccountHourlys = findAccount_AccountHourlys;
    }

}
