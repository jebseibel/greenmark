package com.greenmark.common.dto.broker;

import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 * <p>Title: BrokerExecution</p>
 * <p>Description: This class is used to pass Execution information from the Broker Service to other services.</p>

 * @author  Monte Seibel
 * @version 7.5
 * @formatter:on
 **/

public class BrokerExecution {
    public static final String CLASSNAME = "BrokerExecution";

    public int m_orderId;
    public int m_clientId;
    public String m_execId;
    public String m_time;
    public String m_acctNumber;
    public String m_exchange;
    public String m_side;
    public int m_shares;
    public double m_price;
    public int m_permId;
    public int m_liquidation;
    public int m_cumQty;
    public double m_avgPrice;

    public BrokerExecution() {
        // default constructor
    }

    public BrokerExecution(String xmldata) {
        this();

        try {
            this.m_orderId = UTXmlUtils.getXmlDataAsInt(xmldata, "M_ORDERID");
            this.m_time = UTXmlUtils.getXmlData(xmldata, "M_TIME");
            this.m_shares = UTXmlUtils.getXmlDataAsInt(xmldata, "M_SHARES");
            this.m_price = UTXmlUtils.getXmlDataAsDouble(xmldata, "M_PRICE");
            this.m_avgPrice = UTXmlUtils.getXmlDataAsDouble(xmldata, "M_AVGPRICE");

            this.m_exchange = UTXmlUtils.getXmlData(xmldata, "M_EXCHANGE");
            this.m_cumQty = UTXmlUtils.getXmlDataAsInt(xmldata, "M_CUMQTY");
            this.m_permId = UTXmlUtils.getXmlDataAsInt(xmldata, "M_PERMID");
            this.m_liquidation = UTXmlUtils.getXmlDataAsInt(xmldata, "M_LIQUIDATION");
            this.m_execId = UTXmlUtils.getXmlData(xmldata, "M_EXECID");

            this.m_clientId = UTXmlUtils.getXmlDataAsInt(xmldata, "M_CLIENTID");
            this.m_acctNumber = UTXmlUtils.getXmlData(xmldata, "M_ACCTNUMBER");
            this.m_side = UTXmlUtils.getXmlData(xmldata, "M_SIDE");
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + ".Constructor; message [" + e.getMessage() + "]");
        }
    }

    // ------------------------------------------------ XML IN/OUT ---------------------------------------------------
    public String toXmlShort() {
        String prefix = "";
        String endline = "\n";
        return toXmlShort(prefix, endline);
    }

    public String toXml(String prefix, String endline) {

        String stb = "<" + CLASSNAME + ">" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_ORDERID>" + m_orderId + "</M_ORDERID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_TIME>" + m_time + "</M_TIME>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_SHARES>" + m_shares + "</M_SHARES>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_PRICE>" + m_price + "</M_PRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_AVGPRICE>" + m_avgPrice + "</M_AVGPRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_EXCHANGE>" + m_exchange + "</M_EXCHANGE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_CUMQTY>" + m_cumQty + "</M_CUMQTY>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_PERMID>" + m_permId + "</M_PERMID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_LIQUIDATION>" + m_liquidation + "</M_LIQUIDATION>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_EXECID>" + m_execId + "</M_EXECID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_CLIENTID>" + m_clientId + "</M_CLIENTID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_ACCTNUMBER>" + m_acctNumber + "</M_ACCTNUMBER>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_SIDE>" + m_side + "</M_SIDE>" + endline +
                "</" + CLASSNAME + ">" + endline;
        return stb;
    }

    @Deprecated
    public String toXmlShort(String prefix, String endline) {

        String stb = "<" + CLASSNAME + ">" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_ORDERID>" + m_orderId + "</M_ORDERID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_TIME>" + m_time + "</M_TIME>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_SHARES>" + m_shares + "</M_SHARES>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_PRICE>" + m_price + "</M_PRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_AVGPRICE>" + m_avgPrice + "</M_AVGPRICE>" + endline +
                // stb.append( prefix + UTDisplayFormatter.TAB + "<M_EXCHANGE>" + m_exchange + "</M_EXCHANGE>" + endline );
                // stb.append( prefix + UTDisplayFormatter.TAB + "<M_CUMQTY>" + m_cumQty + "</M_CUMQTY>" + endline );
                // stb.append( prefix + UTDisplayFormatter.TAB + "<M_PERMID>" + m_permId + "</M_PERMID>" + endline );
                // stb.append( prefix + UTDisplayFormatter.TAB + "<M_LIQUIDATION>" + m_liquidation + "</M_LIQUIDATION>" + endline );
                // stb.append( prefix + UTDisplayFormatter.TAB + "<M_EXECID>" + m_execId + "</M_EXECID>" + endline );
                // stb.append( prefix + UTDisplayFormatter.TAB + "<M_CLIENTID>" + m_clientId + "</M_CLIENTID>" + endline );
                // stb.append( prefix + UTDisplayFormatter.TAB + "<M_ACCTNUMBER>" + m_acctNumber + "</M_ACCTNUMBER>" + endline );
                // stb.append( prefix + UTDisplayFormatter.TAB + "<M_SIDE>" + m_side + "</M_SIDE>" + endline );

                "</" + CLASSNAME + ">" + endline;
        return stb;
    }

    public String toString() {
        String prefix = "";
        String endline = "\n";
        return toString(prefix, endline);
    }

    public String toString(String prefix, String endline) {

        String stb = endline +
                prefix + CLASSNAME + " ----------------------------------------------" + endline +
                prefix + "m_orderId        [" + this.m_orderId + "]" + endline +
                prefix + "m_time           [" + this.m_time + "]" + endline +
                prefix + "m_shares         [" + this.m_shares + "]" + endline +
                prefix + "m_price          [" + this.m_price + "]" + endline;

        return stb;
    }

    // ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
    public int getM_orderId() {
        return m_orderId;
    }

    public void setM_orderId(int m_orderId) {
        this.m_orderId = m_orderId;
    }

    public int getM_clientId() {
        return m_clientId;
    }

    public void setM_clientId(int m_clientId) {
        this.m_clientId = m_clientId;
    }

    public String getM_execId() {
        return m_execId;
    }

    public void setM_execId(String m_execId) {
        this.m_execId = m_execId;
    }

    public String getM_time() {
        return m_time;
    }

    public void setM_time(String m_time) {
        this.m_time = m_time;
    }

    public String getM_acctNumber() {
        return m_acctNumber;
    }

    public void setM_acctNumber(String m_acctNumber) {
        this.m_acctNumber = m_acctNumber;
    }

    public String getM_exchange() {
        return m_exchange;
    }

    public void setM_exchange(String m_exchange) {
        this.m_exchange = m_exchange;
    }

    public String getM_side() {
        return m_side;
    }

    public void setM_side(String m_side) {
        this.m_side = m_side;
    }

    public int getM_shares() {
        return m_shares;
    }

    public void setM_shares(int m_shares) {
        this.m_shares = m_shares;
    }

    public double getM_price() {
        return m_price;
    }

    public void setM_price(double m_price) {
        this.m_price = m_price;
    }

    public int getM_permId() {
        return m_permId;
    }

    public void setM_permId(int m_permId) {
        this.m_permId = m_permId;
    }

    public int getM_liquidation() {
        return m_liquidation;
    }

    public void setM_liquidation(int m_liquidation) {
        this.m_liquidation = m_liquidation;
    }

    public int getM_cumQty() {
        return m_cumQty;
    }

    public void setM_cumQty(int m_cumQty) {
        this.m_cumQty = m_cumQty;
    }

    public double getM_avgPrice() {
        return m_avgPrice;
    }

    public void setM_avgPrice(double m_avgPrice) {
        this.m_avgPrice = m_avgPrice;
    }
}
