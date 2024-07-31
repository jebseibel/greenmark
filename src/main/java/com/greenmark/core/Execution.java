package com.greenmark.core;

import com.greenmark.common.core.Labels;
import com.greenmark.utils.UTCalendarTime;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTFormatter;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
//import com.greenman.database.hibernate.HiberObject;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Seibel Consulting</p>
 *
 * @author not attributable
 * @version 1.0
 */
@Slf4j
public class Execution {
    /**
     * This is the class name.
     */
    public static final String CLASSNAME = "Execution";

    /**
     * Used by the database
     **/
    protected long id;      //leave as null for hibernate
    protected long orderId = 0;      //set to zero to find insert errors
    protected long exchangeId = 0;   //set to zero to find insert errors
    protected int active = Labels.OBJECT_ACTIVE;
    /**************************/

    protected java.util.Date executionDate;

    protected int shares;
    protected float price;

    protected int externalSystemId;
    protected String externalExecutionId;
    protected String externalOrderId;

    //private String exchange;

    public Execution() {
        // Hibernate needs an empty constructor
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(String orderId) {
        this.externalOrderId = orderId;
    }

    public java.util.Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(java.util.Date executionDate) {
        this.executionDate = executionDate;
    }

    public String getExecutionDateDisplay() {
        UTCalendarTime execDate = new UTCalendarTime(executionDate);
        return execDate.getDateTimeDisplay();
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public String formatPrice() {
        String returnString = "N/A";

        try {
            returnString = UTFormatter.formatPrice(getPrice());
        } catch (Exception ex) {
//			log.info( "============= ERROR IN Order!   formatAveragePrice  Exception Message:  [" + ex.getMessage() + "]" );
        }
//		
        return returnString;

    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getExternalSystemId() {
        return externalSystemId;
    }

    public void setExternalSystemId(int external_system_id) {
        this.externalSystemId = external_system_id;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return this.toString("");
    }

    public String toString(String prefix) {
        String formattedDate = "";
        if (executionDate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            formattedDate = formatter.format(executionDate);
        }

        String stb = "\n" +
                prefix + "EXECUTION --------------------------------" + "\n" +
                prefix + "orderId            [" + this.externalExecutionId + "]" + "\n" +
                prefix + "time               [" + formattedDate + "]" + "\n" +
                prefix + "exchangeId         [" + this.exchangeId + "]" + "\n" +
                prefix + "shares             [" + this.shares + "]" + "\n" +
                prefix + "price              [" + this.price + "]" + "\n" +
                prefix + "externalSystemId   [" + this.externalSystemId + "]" + "\n";
//      stb.append( prefix + "acctNumber [" + this.acctNumber + "]" + "\n" ) ;
//		 stb.append( prefix + "side [" + this.side + "]" + "\n" ) ;

        return stb;
    }

    public String toXml(String prefix, String endline) {
        //format the date
        String formattedDate = "";
        if (executionDate != null) {
            UTCalendarTime executionDateCal = new UTCalendarTime(executionDate);
            formattedDate = executionDateCal.formatXMLDateTime(true);

//			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd kk:mm:ss" );
//			formattedDate = formatter.format( executionDate );
        }

        String stb = prefix + "<EXECUTION>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ID>" + id + "</ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ORDER_ID>" + orderId + "</ORDER_ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<EXTERNAL_ORDER_ID>" + externalOrderId + "</EXTERNAL_ORDER_ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<EXTERNAL_EXECUTION_ID>" + externalExecutionId + "</EXTERNAL_EXECUTION_ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<EXTERNAL_SYSTEM_ID>" + externalSystemId + "</EXTERNAL_SYSTEM_ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<EXECUTED_DATETIME>" + formattedDate + "</EXECUTED_DATETIME>" + endline +
                prefix + UTDisplayFormatter.TAB + "<EXCHANGE_ID>" + exchangeId + "</EXCHANGE_ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHARES>" + shares + "</SHARES>" + endline +
                prefix + UTDisplayFormatter.TAB + "<PRICE>" + price + "</PRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ACTIVE>" + this.getActive() + "</ACTIVE>" + endline +
                prefix + "</EXECUTION>" + endline;

        //return it
        return stb;
    }

    public String toXml() {
        return this.toXml("", "\n");
    }

    public int getActive() {
        return active;
    }

    public void setActive(int value) {
        this.active = value;
    }

    public String formatActive() {
        if (active == 1)
            return "ACTIVE";
        else
            return "INACTIVE";
    }

    public long getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(long exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getExternalExecutionId() {
        return externalExecutionId;
    }

    public void setExternalExecutionId(String externalExecutionId) {
        this.externalExecutionId = externalExecutionId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String dbSummary() {
        String stb = " > > " + CLASSNAME + " :: " +
                "id [" + id + "] " +
                "orderId [" + orderId + "] " +
                "exchangeId [" + exchangeId + "] " +
                "active [" + active + "] ";
        return stb;
    }

    public String getClassname() {
        return CLASSNAME;
    }
    /**
     public Order getOrder()
     {
     return order;
     }
     public void setOrder(Order order)
     {
     this.order = order;
     }
     **/
}
