package com.greenmark.common.dto.broker;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsBroker;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTUtils;
import com.greenmark.utils.UTXmlUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ExecutionDto</p>
 * <p>Description: This DTO is for the results database executions table.  A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 * DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ExecutionDto implements Serializable {
    public static final String CLASSNAME = "ExecutionDto";
    private static final long serialVersionUID = 1L;

    protected long id;
    protected int active = GmConstants.OBJECT_ACTIVE;

    protected long orderId = 0; // set to zero to find insert errors
    protected long exchangeId = 0; // set to zero to find insert errors

    protected LocalDateTime executionDate;

    protected float executedNumShares;
    protected float executedPrice;

    protected boolean min1QuerySuccess = true;

    protected int externalSystemId;
    protected String externalOrderId;
    protected String externalExecutionId;
    protected String externalECN;

    public ExecutionDto() {
    }

    public ExecutionDto(String xmldata) {
        try {
            id = UTXmlUtils.getXmlDataAsLong(xmldata, "ID");
            active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");

            orderId = UTXmlUtils.getXmlDataAsLong(xmldata, "ORDER_ID");
            exchangeId = UTXmlUtils.getXmlDataAsLong(xmldata, "EXCHANGE_ID");

            String executedDatetimeString = UTXmlUtils.getXmlData(xmldata, "EXECUTED_DATETIME");
            if (UTUtils.isNotNorE(executedDatetimeString)) {
                executionDate = UTDatetime.fromDbString(executedDatetimeString);
            } else {
                this.executionDate = UTDatetime.getNowLDT();
            }

            executedNumShares = UTXmlUtils.getXmlDataAsFloat(xmldata, "EXECUTED_NUM_SHARES");
            executedPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "EXECUTED_PRICE");

            min1QuerySuccess = UTXmlUtils.getXmlDataAsBoolean(xmldata, "MIN1_QUERY_SUCCESS");

            externalSystemId = UTXmlUtils.getXmlDataAsInt(xmldata, "EXTERNAL_SYSTEM_ID");
            externalOrderId = UTXmlUtils.getXmlData(xmldata, "EXTERNAL_ORDER_ID");
            if (UTUtils.isNorE(externalOrderId))
                externalOrderId = GmConstantsBroker.ORDER_ID_UNKNOWN;

            externalExecutionId = UTXmlUtils.getXmlData(xmldata, "EXTERNAL_EXECUTION_ID");
            externalECN = UTXmlUtils.getXmlData(xmldata, "EXTERNAL_ECN");
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + ".Constructor; message [" + e.getMessage() + "]");
        }
    }

    public String toXml(String prefix, String endline) {
        StringBuffer stb = new StringBuffer();
        stb.append(prefix + UTDisplayFormatter.TAB + "<EXECUTION>" + endline);

        stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "<ID>" + id + "</ID>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "<ACTIVE>" + this.getActive() + "</ACTIVE>" + endline);

        stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "<ORDER_ID>" + orderId + "</ORDER_ID>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "<EXCHANGE_ID>" + exchangeId + "</EXCHANGE_ID>" + endline);

        String formattedDate = "";
        if (executionDate != null) {
            formattedDate = UTDatetime.toDbString(executionDate);
        }
        stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "<EXECUTED_DATETIME>" + formattedDate + "</EXECUTED_DATETIME>" + endline);

        stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "<EXECUTED_NUM_SHARES>" + executedNumShares + "</EXECUTED_NUM_SHARES>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "<EXECUTED_PRICE>" + executedPrice + "</EXECUTED_PRICE>" + endline);

        stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "<MIN1_QUERY_SUCCESS>" + min1QuerySuccess + "</MIN1_QUERY_SUCCESS>" + endline);

        stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "<EXTERNAL_SYSTEM_ID>" + externalSystemId + "</EXTERNAL_SYSTEM_ID>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "<EXTERNAL_ORDER_ID>" + externalOrderId + "</EXTERNAL_ORDER_ID>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "<EXTERNAL_EXECUTION_ID>" + externalExecutionId + "</EXTERNAL_EXECUTION_ID>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB + "<EXTERNAL_ECN>" + externalECN + "</EXTERNAL_ECN>" + endline);
        stb.append(prefix + UTDisplayFormatter.TAB + "</EXECUTION>" + endline);

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

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(long exchangeId) {
        this.exchangeId = exchangeId;
    }

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDateTime executionDate) {
        this.executionDate = executionDate;
    }

    public float getExecutedNumShares() {
        return executedNumShares;
    }

    public void setExecutedNumShares(float shares) {
        this.executedNumShares = shares;
    }

    public float getExecutedPrice() {
        return executedPrice;
    }

    public void setExecutedPrice(float price) {
        this.executedPrice = price;
    }

    public boolean isMin1QuerySuccess() {
        return min1QuerySuccess;
    }

    public void setMin1QuerySuccess(boolean min1QuerySuccess) {
        this.min1QuerySuccess = min1QuerySuccess;
    }

    public int getExternalSystemId() {
        return externalSystemId;
    }

    public void setExternalSystemId(int externalSystemId) {
        this.externalSystemId = externalSystemId;
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(String externalOrderId) {
        this.externalOrderId = externalOrderId;
    }

    public String getExternalExecutionId() {
        return externalExecutionId;
    }

    public void setExternalExecutionId(String externalExecutionId) {
        this.externalExecutionId = externalExecutionId;
    }

    public String getExternalECN() {
        return externalECN;
    }

    public void setExternalECN(String externalECN) {
        this.externalECN = externalECN;
    }
}
