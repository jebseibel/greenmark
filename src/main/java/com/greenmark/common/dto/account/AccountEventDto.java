package com.greenmark.common.dto.account;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.greenmark.common.dto.parameters.AccountBalanceParams;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTFormatter;
import com.greenmark.utils.UTUtils;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: </p>
 * <p>Description: This DTO is for the results database account_events table.  A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 * DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class AccountEventDto implements Serializable {
    public static final String CLASSNAME = "AccountEventDto";
    private static final long serialVersionUID = 1L;
    protected LocalDateTime eventDate;
    protected String description = "";
    protected String symbol = "";
    protected String harvestStrategyAcronym = "";
    protected float numShares = 0F;
    protected float orderPrice = 0F;
    protected float orderAmount = 0F;
    protected float transactionFee = 0F;
    protected float marginFee = 0F;
    protected double allocatedFundsCash;
    protected double unsettledFundsCash;
    protected double cashOnHand_Cash;
    protected double allocatedFundsMargin;
    protected double unsettledFundsMargin;
    protected double cashOnHand_Margin;
    protected double accountBalanceCash;
    protected double positionsTotalLong;
    protected double accountBalanceMargin;
    protected double positionsTotalShort;
    private long id;
    private long accountId;
    private int active;

    public AccountEventDto() {
        super();
    }

    public AccountEventDto(AccountBalanceParams accountBalances, LocalDateTime eventDate, String description, String symbol, String harvestStrategyAcronym, float numShares, float orderPrice,
                           float orderAmount, float transactionFee, float marginFee) {
        this.eventDate = eventDate;
        this.description = description;
        this.symbol = symbol;
        this.harvestStrategyAcronym = harvestStrategyAcronym;

        this.numShares = numShares;
        this.orderPrice = orderPrice;
        this.orderAmount = orderAmount;

        this.transactionFee = transactionFee;
        this.marginFee = marginFee;

        this.accountBalanceCash = accountBalances.getTotalEquityCash();
        this.cashOnHand_Cash = accountBalances.getAvailableFundsCash();
        this.allocatedFundsCash = accountBalances.getAllocatedFundsCash();
        this.unsettledFundsCash = accountBalances.getUnsettledFundsCash();
        this.positionsTotalLong = accountBalances.getLongPositionsTotal();

        this.accountBalanceMargin = accountBalances.getTotalEquityMargin();
        this.cashOnHand_Margin = accountBalances.getAvailableFundsMargin();
        this.allocatedFundsMargin = accountBalances.getAllocatedFundsMargin();
        this.unsettledFundsMargin = accountBalances.getUnsettledFundsMargin();
        this.positionsTotalShort = accountBalances.getShortPositionsTotal();
    }

    // ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
    public AccountEventDto(String xmldata) {
        try {
            id = UTXmlUtils.getXmlDataAsLong(xmldata, "ID");
            accountId = UTXmlUtils.getXmlDataAsLong(xmldata, "ACCOUNT_ID");
            active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");

            String eventDateXml = UTXmlUtils.getXmlData(xmldata, "EVENTDATE");
            if (UTUtils.isNotNorE(eventDateXml)) {
                eventDate = UTDatetime.fromDbString(eventDateXml);

                description = UTXmlUtils.getXmlData(xmldata, "DESCRIPTION");
                symbol = UTXmlUtils.getXmlData(xmldata, "SYMBOL");
                harvestStrategyAcronym = UTXmlUtils.getXmlData(xmldata, "HARVEST_STRATEGY_ACRONYM");

                numShares = UTXmlUtils.getXmlDataAsInt(xmldata, "NUM_SHARES");
                orderPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "ORDER_PRICE");
                orderAmount = UTXmlUtils.getXmlDataAsFloat(xmldata, "ORDER_AMOUNT");

                transactionFee = UTXmlUtils.getXmlDataAsFloat(xmldata, "TRANSACTION_FEE");
                marginFee = UTXmlUtils.getXmlDataAsFloat(xmldata, "MARGIN_FEE");

                cashOnHand_Cash = UTXmlUtils.getXmlDataAsFloat(xmldata, "CASHONHAND_CASH");
                allocatedFundsCash = UTXmlUtils.getXmlDataAsFloat(xmldata, "ALLOCATED_CASH");
                unsettledFundsCash = UTXmlUtils.getXmlDataAsFloat(xmldata, "UNSETTLED_CASH");

                cashOnHand_Margin = UTXmlUtils.getXmlDataAsFloat(xmldata, "CASHONHAND_MARGIN");
                allocatedFundsMargin = UTXmlUtils.getXmlDataAsFloat(xmldata, "ALLOCATED_MARGIN");
                unsettledFundsMargin = UTXmlUtils.getXmlDataAsFloat(xmldata, "UNSETTLED_MARGIN");

                accountBalanceCash = UTXmlUtils.getXmlDataAsFloat(xmldata, "ACCOUNT_BALANCE_CASH");
                accountBalanceMargin = UTXmlUtils.getXmlDataAsFloat(xmldata, "ACCOUNT_BALANCE_MARGIN");

                positionsTotalLong = UTXmlUtils.getXmlDataAsFloat(xmldata, "POSITIONS_TOTAL_LONG");
                positionsTotalShort = UTXmlUtils.getXmlDataAsFloat(xmldata, "POSITIONS_TOTAL_SHORT");
            }
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + ".Constructor; message [" + e.getMessage() + "]");
        }

    }

    public String toXmlWrapper(String prefix, String endline) {
        String stb = prefix + "<ACCOUNT_EVENT>" + endline +
                prefix + toXml(prefix, endline) +
                prefix + "</ACCOUNT_EVENT>" + endline;
        return stb;
    }

    public final String toXml(String prefix, String endline) {

        String stb = prefix + "<ID>" + this.id + "</ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ACCOUNT_ID>" + this.accountId + "</ACCOUNT_ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ACTIVE>" + this.active + "</ACTIVE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<EVENTDATE>" + UTDatetime.toDbString(this.eventDate) + "</EVENTDATE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<DESCRIPTION>" + this.description + "</DESCRIPTION>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SYMBOL>" + this.symbol + "</SYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<HARVEST_STRATEGY_ACRONYM>" + this.harvestStrategyAcronym + "</HARVEST_STRATEGY_ACRONYM>" + endline +
                prefix + UTDisplayFormatter.TAB + "<NUM_SHARES>" + this.numShares + "</NUM_SHARES>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ORDER_PRICE>" + this.orderPrice + "</ORDER_PRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ORDER_AMOUNT>" + this.orderAmount + "</ORDER_AMOUNT>" + endline +
                prefix + UTDisplayFormatter.TAB + "<TRANSACTION_FEE>" + this.transactionFee + "</TRANSACTION_FEE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<MARGIN_FEE>" + this.marginFee + "</MARGIN_FEE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<CASHONHAND_CASH>" + this.cashOnHand_Cash + "</CASHONHAND_CASH>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ALLOCATED_CASH>" + this.allocatedFundsCash + "</ALLOCATED_CASH>" + endline +
                prefix + UTDisplayFormatter.TAB + "<UNSETTLED_CASH>" + this.unsettledFundsCash + "</UNSETTLED_CASH>" + endline +
                prefix + UTDisplayFormatter.TAB + "<CASHONHAND_MARGIN>" + this.cashOnHand_Margin + "</CASHONHAND_MARGIN>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ALLOCATED_MARGIN>" + this.allocatedFundsMargin + "</ALLOCATED_MARGIN>" + endline +
                prefix + UTDisplayFormatter.TAB + "<UNSETTLED_MARGIN>" + this.unsettledFundsMargin + "</UNSETTLED_MARGIN>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ACCOUNT_BALANCE_CASH>" + this.accountBalanceCash + "</ACCOUNT_BALANCE_CASH>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ACCOUNT_BALANCE_MARGIN>" + this.accountBalanceMargin + "</ACCOUNT_BALANCE_MARGIN>" + endline +
                prefix + UTDisplayFormatter.TAB + "<POSITIONS_TOTAL_LONG>" + this.positionsTotalLong + "</POSITIONS_TOTAL_LONG>" + endline +
                prefix + UTDisplayFormatter.TAB + "<POSITIONS_TOTAL_SHORT>" + this.positionsTotalShort + "</POSITIONS_TOTAL_SHORT>" + endline;

        return stb;
    }

    public String toString() {
        StringBuffer stb = new StringBuffer(200);

        try {
            stb.append(UTFormatter.returnEmptyString(30 - this.getDescription().length()));
            stb.append(this.getDescription());
            stb.append(" ");

            stb.append(this.getSymbol());
            stb.append(UTFormatter.returnEmptyString(21 - this.getSymbol().length()));

            stb.append(this.getHarvestStrategyAcronym());
            stb.append(UTFormatter.returnEmptyString(7 - this.getHarvestStrategyAcronym().length()));

            stb.append(UTDatetime.toString(eventDate));
            stb.append(UTFormatter.returnEmptyString(21 - UTDatetime.toString(eventDate).length()));

            stb.append(UTFormatter.formatShares(this.getNumShares()));
            stb.append(UTFormatter.returnEmptyString(12 - UTFormatter.formatShares(this.getNumShares()).length()));

            stb.append(UTFormatter.formatPrice(this.getOrderPrice()));
            stb.append(UTFormatter.returnEmptyString(12 - UTFormatter.formatPrice(this.getOrderPrice()).length()));

            stb.append(UTFormatter.formatPrice(this.getOrderAmount()));
            stb.append(UTFormatter.returnEmptyString(11 - UTFormatter.formatPrice(this.getOrderAmount()).length()));

            stb.append(UTFormatter.formatPrice(this.getTransactionFee()));
            stb.append(UTFormatter.returnEmptyString(9 - UTFormatter.formatPrice(this.getTransactionFee()).length()));

            stb.append(UTFormatter.formatPrice(this.getMarginFee()));
            stb.append(UTFormatter.returnEmptyString(9 - UTFormatter.formatPrice(this.getMarginFee()).length()));

            stb.append(UTFormatter.formatPrice(this.getAccountBalanceCash()));
            stb.append(UTFormatter.returnEmptyString(13 - UTFormatter.formatPrice(this.getAccountBalanceCash()).length()));

            stb.append(UTFormatter.formatPrice(this.getCashOnHand_Cash()));
            stb.append(UTFormatter.returnEmptyString(14 - UTFormatter.formatPrice(this.getCashOnHand_Cash()).length()));

            stb.append(UTFormatter.formatPrice(this.getAllocatedFundsCash()));
            stb.append(UTFormatter.returnEmptyString(14 - UTFormatter.formatPrice(this.getAllocatedFundsCash()).length()));

            stb.append(UTFormatter.formatPrice(this.getPositionsTotalLong()));
            stb.append(UTFormatter.returnEmptyString(14 - UTFormatter.formatPrice(this.getPositionsTotalLong()).length()));

            stb.append(UTFormatter.formatPrice(this.getAccountBalanceMargin()));
            stb.append(UTFormatter.returnEmptyString(15 - UTFormatter.formatPrice(this.getAccountBalanceMargin()).length()));

            stb.append(UTFormatter.formatPrice(this.getCashOnHand_Margin()));
            stb.append(UTFormatter.returnEmptyString(15 - UTFormatter.formatPrice(this.getCashOnHand_Margin()).length()));

            stb.append(UTFormatter.formatPrice(this.getAllocatedFundsMargin()));
            stb.append(UTFormatter.returnEmptyString(15 - UTFormatter.formatPrice(this.getAllocatedFundsMargin()).length()));

            stb.append(UTFormatter.formatPrice(this.getPositionsTotalShort()));
            stb.append(UTFormatter.returnEmptyString(15 - UTFormatter.formatPrice(this.getPositionsTotalShort()).length()));
        } catch (Exception e) {
            System.out.println("Exception in LoadedCurrencyDecorator.toString(): message: " + e.getMessage());
        }

        return stb.toString();
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getHarvestStrategyAcronym() {
        return harvestStrategyAcronym;
    }

    public void setHarvestStrategyAcronym(String harvestStrategyAcronym) {
        this.harvestStrategyAcronym = harvestStrategyAcronym;
    }

    public float getNumShares() {
        return numShares;
    }

    public void setNumShares(float numShares) {
        this.numShares = numShares;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderAmount = orderAmount;
    }

    public double getCashOnHand_Cash() {
        return cashOnHand_Cash;
    }

    public void setCashOnHand_Cash(double cashOnHand) {
        this.cashOnHand_Cash = cashOnHand;
    }

    public double getAllocatedFundsCash() {
        return allocatedFundsCash;
    }

    public void setAllocatedFundsCash(double allocated) {
        this.allocatedFundsCash = allocated;
    }

    public double getUnsettledFundsCash() {
        return unsettledFundsCash;
    }

    public void setUnsettledFundsCash(double allocated) {
        this.unsettledFundsCash = allocated;
    }

    public double getCashOnHand_Margin() {
        return cashOnHand_Margin;
    }

    public void setCashOnHand_Margin(double cashOnHand) {
        this.cashOnHand_Margin = cashOnHand;
    }

    public double getAllocatedFundsMargin() {
        return allocatedFundsMargin;
    }

    public void setAllocatedFundsMargin(double allocated) {
        this.allocatedFundsMargin = allocated;
    }

    public double getUnsettledFundsMargin() {
        return unsettledFundsMargin;
    }

    public void setUnsettledFundsMargin(double allocated) {
        this.unsettledFundsMargin = allocated;
    }

    public double getAccountBalanceCash() {
        return accountBalanceCash;
    }

    public void setAccountBalanceCash(double accountBalance) {
        this.accountBalanceCash = accountBalance;
    }

    public double getAccountBalanceMargin() {
        return accountBalanceMargin;
    }

    public void setAccountBalanceMargin(double accountBalance) {
        this.accountBalanceMargin = accountBalance;
    }

    public double getPositionsTotalLong() {
        return positionsTotalLong;
    }

    public void setPositionsTotalLong(double positionsTotal) {
        this.positionsTotalLong = positionsTotal;
    }

    public double getPositionsTotalShort() {
        return positionsTotalShort;
    }

    public void setPositionsTotalShort(double positionsTotal) {
        this.positionsTotalShort = positionsTotal;
    }

    public float getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(float transactionFee) {
        this.transactionFee = transactionFee;
    }

    public float getMarginFee() {
        return marginFee;
    }

    public void setMarginFee(float marginFee) {
        this.marginFee = marginFee;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

}
