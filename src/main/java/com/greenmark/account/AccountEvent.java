package com.greenmark.account;

public class AccountEvent {
    public static final String CLASSNAME = "AccountEvent";
    private String action = "";
    private float fee = 0F;
    private double amount;

    private double allocatedFundsCash;
    private double unsettledFundsCash;
    private double cashOnHand_Cash;

    private double allocatedFundsMargin;
    private double unsettledFundsMargin;
    private double cashOnHand_Margin;

    private double accountBalanceCash;
    private double positionsTotalLong;

    private double accountBalanceMargin;
    private double positionsTotalShort;

    private String eventdate = "";


    /**
     *
     */
    public AccountEvent() {
        super();
        // needed by Hibernate
    }

    /**
     *
     */
//    public AccountEvent(String action, double amount) {
//        this.action = action;
//        this.amount = amount;
//
//        Account account = AccountManager.getAccount();
//        this.cashOnHand_Cash = account.getCashOnHand();
//        this.allocatedFundsCash = account.getAllocatedFunds();
//        this.unsettledFundsCash = account.getUnsettledFunds();
//
//        this.cashOnHand_Margin = account.getCashOnHandMargin();
//        this.allocatedFundsMargin = account.getAllocatedFundsMargin();
//        this.unsettledFundsMargin = account.getUnsettledFundsMargin();
//
//        this.accountBalanceCash = account.getTotalEquity();
//        this.accountBalanceMargin = account.getTotalEquityMargin();
//        this.positionsTotalLong = account.getLongPositionsTotal();
//        this.positionsTotalShort = account.getShortPositionsTotal();
//        java.util.Date today = new java.util.Date();
//        this.eventdate = UTDatetime.getDateAsOneForFilename(today);
//    }

    /**
     *
     */
//    public AccountEvent(String action, float amount, float fee) {
//        this.action = action;
//        this.amount = amount;
//        this.fee = fee;
//
//        Account account = AccountManager.getAccount();
//        this.cashOnHand_Cash = account.getCashOnHand();
//        this.allocatedFundsCash = account.getAllocatedFunds();
//        this.unsettledFundsCash = account.getUnsettledFunds();
//
//        this.cashOnHand_Margin = account.getCashOnHandMargin();
//        this.allocatedFundsMargin = account.getAllocatedFundsMargin();
//        this.unsettledFundsMargin = account.getUnsettledFundsMargin();
//
//        this.accountBalanceCash = account.getTotalEquity();
//        this.accountBalanceMargin = account.getTotalEquityMargin();
//        this.positionsTotalLong = account.getLongPositionsTotal();
//        this.positionsTotalShort = account.getShortPositionsTotal();
//
//        java.util.Date today = new java.util.Date();
//        this.eventdate = UTDatetime.getDateAsOneForFilename(today);
//    }

    /**
     * @return Returns the action.
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action The action to set.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return Returns the amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount The amount to set.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return Returns the fee.
     */
    public float getFee() {
        return fee;
    }

    /**
     * @param fee The fee to set.
     */
    public void setFee(float fee) {
        this.fee = fee;
    }

    /**
     * @return Returns the cashOnHand.
     */
    public double getCashOnHand_Cash() {
        return cashOnHand_Cash;
    }

    /**
     * @param cashOnHand The cashOnHand to set.
     */
    public void setCashOnHand_Cash(double cashOnHand) {
        this.cashOnHand_Cash = cashOnHand;
    }

    /**
     * @return Returns the allocated.
     */
    public double getAllocatedFundsCash() {
        return allocatedFundsCash;
    }

    /**
     * @param allocated The allocated to set.
     */
    public void setAllocatedFundsCash(double allocated) {
        this.allocatedFundsCash = allocated;
    }

    /**
     * @return Returns the unsettledFundsCash.
     */
    public double getUnsettledFundsCash() {
        return unsettledFundsCash;
    }

    /**
     * @param allocated The unsettledFundsCash to set.
     */
    public void setUnsettledFundsCash(double allocated) {
        this.unsettledFundsCash = allocated;
    }

    /**
     * @return Returns the cashOnHand.
     */
    public double getCashOnHand_Margin() {
        return cashOnHand_Margin;
    }

    /**
     * @param cashOnHand The cashOnHand to set.
     */
    public void setCashOnHand_Margin(double cashOnHand) {
        this.cashOnHand_Margin = cashOnHand;
    }

    /**
     * @return Returns the allocated.
     */
    public double getAllocatedFundsMargin() {
        return allocatedFundsMargin;
    }

    /**
     * @param allocated The allocated to set.
     */
    public void setAllocatedFundsMargin(double allocated) {
        this.allocatedFundsMargin = allocated;
    }

    /**
     * @return Returns the unsettledFundsMargin.
     */
    public double getUnsettledFundsMargin() {
        return unsettledFundsMargin;
    }

    /**
     * @param allocated The unsettledFundsMargin to set.
     */
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

    /**
     * @return Returns the action.
     */
    public String getEventdate() {
        return this.eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }


}
