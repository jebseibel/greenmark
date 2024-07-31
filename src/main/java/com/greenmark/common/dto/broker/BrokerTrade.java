package com.greenmark.common.dto.broker;

import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTXmlUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 * <p>Title: BrokerTrade</p>
 * <p>Description: This class is used to pass Trade information from the Broker Service to other services.</p>

 * @author  Monte Seibel
 * @version 7.5
 * @formatter:on
 **/

public class BrokerTrade {
    public static final String CLASSNAME = "BrokerTrade";
    public static final int pendingSubmitMax = 3; // three different times that we checked the order for them to acknowledge
    public int pendingSubmitCount = 0;
    private int orderId;
    private String symbol = "";
    private String status = "";
    // next vars are updated with each execution, since IB figures these out they are always accurate
    private int remaining; // executedNumShares remaining to be filled
    private int totalShares; // total executedNumShares that we want to buy/sell

    // This is the last execution we received from IB - it has the latest executedPrice info
    // (we rolled up all the other partials into this one).
    // It is called 'last' because it is the last execution we have gotten from IB
    // When GM asks for executionDtos we roll them all up into one in the IBConverter
    // method 'createCumulativeExecution' and return it back in the 'Executions' Vector.
    private BrokerExecution lastExecution;

    private BrokerOrder order;
    private BrokerContract contract;
    private Collection<BrokerExecution> executions = new ArrayList(); // BrokerExecution

    public BrokerTrade() {
        // default constructor
    }

    // ------------------------------------------------ XML IN/OUT ---------------------------------------------------
    public BrokerTrade(String xmldata) {
        this();

        try {
            this.orderId = UTXmlUtils.getXmlDataAsInt(xmldata, "orderId");
            this.status = UTXmlUtils.getXmlData(xmldata, "status");
            this.remaining = UTXmlUtils.getXmlDataAsInt(xmldata, "remaining");
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + ".Constructor; message [" + e.getMessage() + "]");
        }
    }

    public static int getPendingsubmitmax() {
        return pendingSubmitMax;
    }

    public String toXml(String prefix, String endline) {
        StringBuffer stb = new StringBuffer();
        String contractXml = "";
        String orderXml = "";

        if (this.contract != null)
            contractXml = this.contract.toXml(prefix + UTDisplayFormatter.TAB, endline);
        if (this.order != null)
            orderXml = this.order.toXml(prefix + UTDisplayFormatter.TAB, endline);

        stb.append(prefix + "<IBTRADE>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<orderId>" + this.orderId + "</orderId>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<status>" + this.status + "</status>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<remaining>" + this.remaining + "</remaining>" + endline);
        stb.append(contractXml);
        stb.append(orderXml);

        ///////////////////////////////////////////////
        if ((executions != null) && (!this.getExecutions().isEmpty())) {
            stb.append(prefix + "<EXECUTIONS>" + endline);
            Iterator iter = executions.iterator();
            while (iter.hasNext()) {
                BrokerExecution execution = (BrokerExecution) iter.next();
                stb.append(execution.toXmlShort(prefix + UTDisplayFormatter.TAB, endline));
            }
            stb.append(prefix + "</EXECUTIONS>" + endline);
        }
        stb.append(prefix + "</IBTRADE>" + endline);

        return stb.toString();
    }

    @Deprecated
    public String toXmlAbridgedDisplay(String prefix, String endline) {
        StringBuffer stb = new StringBuffer();
        String contractXml = "";
        String orderXml = "";

        if (this.contract != null)
            contractXml = this.contract.toXmlShort(prefix + UTDisplayFormatter.TAB, endline);
        if (this.order != null)
            orderXml = this.order.toXmlShort(prefix + UTDisplayFormatter.TAB, endline);

        stb.append(prefix + "<IBTRADE>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<orderId>" + this.orderId + "</orderId>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<status>" + this.status + "</status>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "<remaining>" + this.remaining + "</remaining>" + endline);
        stb.append(contractXml);
        stb.append(orderXml);

        ///////////////////////////////////////////////
        if ((executions != null) && (!this.getExecutions().isEmpty())) {
            stb.append(prefix + "<EXECUTIONS>" + endline);
            Iterator iter = executions.iterator();
            while (iter.hasNext()) {
                BrokerExecution execution = (BrokerExecution) iter.next();
                stb.append(execution.toXmlShort(prefix + UTDisplayFormatter.TAB, endline));
            }
            stb.append(prefix + "</EXECUTIONS>" + endline);
        }
        stb.append(prefix + "</IBTRADE>" + endline);

        return stb.toString();
    }

    public String getOrderIdAsString() {
        return Integer.toString(orderId);
    }

    // ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPendingSubmitCount() {
        return pendingSubmitCount;
    }

    public void setPendingSubmitCount(int pendingSubmitCount) {
        this.pendingSubmitCount = pendingSubmitCount;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public int getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(int totalShares) {
        this.totalShares = totalShares;
    }

    public BrokerExecution getLastExecution() {
        return lastExecution;
    }

    public void setLastExecution(BrokerExecution lastExecution) {
        this.lastExecution = lastExecution;
    }

    public BrokerOrder getOrder() {
        return order;
    }

    public void setOrder(BrokerOrder order) {
        this.order = order;
    }

    public BrokerContract getContract() {
        return contract;
    }

    public void setContract(BrokerContract contract) {
        this.contract = contract;
    }

    public Collection<BrokerExecution> getExecutions() {
        return executions;
    }

    public void setExecutions(Collection<BrokerExecution> executions) {
        this.executions = executions;
    }
}
