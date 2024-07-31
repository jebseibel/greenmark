package com.greenmark.common.dto.broker.database;

import com.greenmark.common.dto.broker.decorator.ExecutionDtoDecorator;
import com.greenmark.common.dto.broker.decorator.OrderDtoDecorator;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTUtils;
import com.greenmark.utils.UTXmlUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: OrderBroker</p>
 * <p>Description: This class extends its base DTO and adds other DTO object instances.  These are sent to/from the database to store hierarchies of objects.
 *    It is in the Broker abstraction layer since it only contains member variables that a Broker will use.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class OrderBroker extends OrderDtoDecorator implements Serializable {
    public static final String CLASSNAME = "OrderBroker";
    private static final long serialVersionUID = 1L;

    protected List<ExecutionDtoDecorator> executionDtos;

    public OrderBroker() {
        executionDtos = new ArrayList<>();
    }

    public OrderBroker(OrderBroker inOrder) {
        try {
            if (inOrder != null)
                BeanUtils.copyProperties(this, inOrder);
        } catch (InvocationTargetException ite) {
            System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage());
        } catch (IllegalAccessException iae) {
            System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
        }
    }

    public OrderBroker(String xmldata) {
        super(xmldata);

        String execList = UTXmlUtils.getXmlData(xmldata, "EXECUTION_LIST");

        if (UTUtils.isNotNorE(execList)) {
            this.executionDtos = new ArrayList<>();
            Vector vXmlExecutions = UTXmlUtils.getElementsToVector(execList, "EXECUTION");
            for (Enumeration e = vXmlExecutions.elements(); e.hasMoreElements(); ) {
                String xmlexecution = (String) e.nextElement();
                ExecutionDtoDecorator thisExecution = new ExecutionDtoDecorator(xmlexecution);
                this.executionDtos.add(thisExecution);
            }
        }
    }

    public String toXmlWrapper(String prefix, String endline) {
        String stb = prefix + "<ORDER>" + endline +
                prefix + toXml(prefix, endline) + endline +
                prefix + "</ORDER>" + endline;
        return stb;
    }

    public String toXml(String prefix, String endline) {
        StringBuffer stb = new StringBuffer();
        stb.append(super.toXml(prefix, endline));

        if ((executionDtos != null) && (!executionDtos.isEmpty())) {
            stb.append(prefix + UTDisplayFormatter.TAB + "<EXECUTION_LIST>" + endline);
            for (ExecutionDtoDecorator thisExec : executionDtos) {
                stb.append(thisExec.toXml(prefix + UTDisplayFormatter.TAB, endline));
            }
            stb.append(prefix + UTDisplayFormatter.TAB + "</EXECUTION_LIST>" + endline);
        }

        return stb.toString();
    }

    public void addExecution(ExecutionDtoDecorator executionDtoDecorator) {
        executionDtos.add(executionDtoDecorator);
    }

    public boolean isHasExecutions() {
        return !executionDtos.isEmpty();
    }

    // ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
    public List<ExecutionDtoDecorator> getExecutionDtos() {
        return executionDtos;
    }

    public void setExecutionDtos(List<ExecutionDtoDecorator> executionDtos) {
        this.executionDtos = executionDtos;
    }
}
