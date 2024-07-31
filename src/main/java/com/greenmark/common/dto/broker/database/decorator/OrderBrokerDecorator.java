package com.greenmark.common.dto.broker.database.decorator;

import com.greenmark.common.dto.broker.database.OrderBroker;
import com.greenmark.common.dto.broker.decorator.ExecutionDtoDecorator;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTFormatter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: OrderBrokerDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its OrderBroker base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.
 *    It is in the Broker abstraction layer since it only contains member variables that a Broker will use.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class OrderBrokerDecorator extends OrderBroker implements Serializable {
    public static final String CLASSNAME = "OrderBrokerDecorator";

    private static final long serialVersionUID = 1L;

    public OrderBrokerDecorator() {
    }

    public OrderBrokerDecorator(OrderBrokerDecorator inOrder) {
        super(inOrder);
    }

    public OrderBrokerDecorator(String xmldata) {
        super(xmldata);
    }

    // ------------------------------------------------ CONVENIENCE METHODS ---------------------------------------------------
    public float calcExecutedNumShares() {
        String methodname = "calcExecutedNumShares";

        float totalNumShares = 0;

        try {
            if (executionDtos == null)
                return totalNumShares;

            for (Iterator I = executionDtos.iterator(); I.hasNext(); ) {
                ExecutionDtoDecorator thisExec = (ExecutionDtoDecorator) I.next();
                totalNumShares += thisExec.getExecutedNumShares();
            }
        } catch (Exception ex) {
            // Ignore, Return default value below
        }
        return totalNumShares;
    }

    public String getExecutedNumSharesDisplay() {
        return Float.toString(calcExecutedNumShares());
    }

    public float getAveragePrice() {
        String methodname = "getAveragePrice";

        float avgPrice = 0f;
        float totalShares = calcExecutedNumShares();

        try {
            if (executionDtos == null)
                return avgPrice;

            for (Iterator I = executionDtos.iterator(); I.hasNext(); ) {
                ExecutionDtoDecorator thisExec = (ExecutionDtoDecorator) I.next();
                avgPrice += thisExec.getExecutedPrice() * (thisExec.getExecutedNumShares() / totalShares);
            }

        } catch (Exception ex) {
            // Ignore, Return default value below
        }

        return avgPrice;
    }

    public String getAveragePriceDisplay() {
        String methodname = "getAveragePriceDisplay";

        String returnString = "N/A";

        try {
            returnString = UTFormatter.formatPrice(getAveragePrice());
        } catch (Exception ex) {
            // Ignore, Return default value below
        }
        return returnString;
    }

    // THIS INCLUDES THE TRANSACTION FEE
    public float getTotalExecutedAmount() {
        String methodname = "getTotalExecutedAmount";

        float totalAmount = 0f;

        try {
            if (executionDtos == null)
                return totalAmount;

            for (Iterator I = executionDtos.iterator(); I.hasNext(); ) {
                ExecutionDtoDecorator thisExec = (ExecutionDtoDecorator) I.next();
                totalAmount += thisExec.getExecutedNumShares() * thisExec.getExecutedPrice();
            }
            totalAmount += transactionFee;
            totalAmount += marginFee;
        } catch (Exception ex) {
            // Ignore, Return default value below
        }

        return totalAmount;
    }

    public String getTotalExecutedAmountDisplay() {
        String methodname = "getTotalExecutedAmountDisplay";

        String returnString = "N/A";

        try {
            returnString = UTFormatter.formatPrice(getTotalExecutedAmount());
        } catch (Exception ex) {
            // Ignore, Return default value below
        }
        return returnString;
    }

    // This method assumes the execution list was sorted from DB query by execution_date
    public LocalDateTime getFirstExecutionDatetime() {
        String methodname = "getFirstExecutionDatetime";

        LocalDateTime outDatetime = null;

        try {
            if (executionDtos == null)
                return outDatetime;

            ExecutionDtoDecorator thisExec = executionDtos.get(0);
            return thisExec.getExecutionDate();
        } catch (Exception ex) {
            // Ignore, Return default value below
        }
        return outDatetime;
    }

    // This method assumes the execution list was sorted from DB query by execution_date
    public String getFirstExecutionDatetimeDisplay() {
        String methodname = "getFirstExecutionDatetimeDisplay";

        String outDatetime = "N/A";
        try {
            LocalDateTime firstExecDate = getFirstExecutionDatetime();

            if (firstExecDate == null)
                return outDatetime;

            outDatetime = UTDatetime.toString(firstExecDate);
        } catch (Exception ex) {
            // Ignore, Return default value below
        }
        return outDatetime;
    }

    // This method assumes the execution list was sorted from DB query by execution_date
    public LocalDateTime getLastExecutionDatetime() {
        String methodname = "getLastExecutionDatetime";

        LocalDateTime outDatetime = null;

        try {
            if (executionDtos == null)
                return outDatetime;

            int lastElem = executionDtos.size() - 1;
            ExecutionDtoDecorator thisExec = executionDtos.get(lastElem);
            return thisExec.getExecutionDate();
        } catch (Exception ex) {
            // Ignore, Return default value below
        }
        return outDatetime;
    }

    // This method assumes the execution list was sorted from DB query by execution_date
    public String getLastExecutionDatetimeDisplay() {
        String methodname = "getLastExecutionDatetimeDisplay";

        String outDatetime = "N/A";
        try {
            LocalDateTime lastExecDate = getLastExecutionDatetime();

            if (lastExecDate == null)
                return outDatetime;

            outDatetime = UTDatetime.toString(lastExecDate);
        } catch (Exception ex) {
            // Ignore, Return default value below
        }
        return outDatetime;
    }
}
