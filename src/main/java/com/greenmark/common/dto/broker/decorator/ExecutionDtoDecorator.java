package com.greenmark.common.dto.broker.decorator;

import java.io.Serializable;

import com.greenmark.common.dto.broker.ExecutionDto;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTFormatter;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ExecutionDtoDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its ExecutionDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ExecutionDtoDecorator extends ExecutionDto implements Serializable {
	public static final String CLASSNAME = "ExecutionDtoDecorator";
	private static final long serialVersionUID = 1L;

	public ExecutionDtoDecorator() {
		super();
	}

	public ExecutionDtoDecorator(String xmldata) {
		super(xmldata);
	}

	public String getExecutionDateDisplay() {
		if (executionDate == null) {
			return "";
		} else
			return UTDatetime.toString(executionDate);
	}

	public String formatPrice() {
		String returnString = "N/A";

		try {
			returnString = UTFormatter.formatPrice(getExecutedPrice());
		} catch (Exception ex) {
			System.out.println("Caught exception in CLASS: " + CLASSNAME + ".formatPrice(), message: [" + ex.getMessage() + "]");
		}
		// 
		return returnString;

	}

	public String formatActive() {
		if (active == 1)
			return "ACTIVE";
		else
			return "INACTIVE";
	}

	public String dbSummary() {
        String stb = " > > " + CLASSNAME + " :: " +
                "id [" + id + "] " +
                "orderId [" + orderId + "] " +
                "exchangeId [" + exchangeId + "] " +
                "externalECN [" + externalECN + "] " +
                "active [" + active + "] ";
		return stb;
	}

	public String toString() {
        String stb = "      " +
                id +
                "    " +
                this.executedNumShares +
                "    " +
                this.executedPrice +
                "      " +
                UTDatetime.toString(executionDate) +
                "         ";
		return stb;
	}

	public String toString(String prefix) {
		String formattedDate = "";
		if (executionDate != null) {
			formattedDate = UTDatetime.toString(executionDate);
		}

        String stb = "\n" +
                prefix + "EXECUTION --------------------------------" + "\n" +
                prefix + "orderId            [" + this.externalExecutionId + "]" + "\n" +
                prefix + "time               [" + formattedDate + "]" + "\n" +
                prefix + "exchangeId         [" + this.exchangeId + "]" + "\n" +
                prefix + "executedNumShares             [" + this.executedNumShares + "]" + "\n" +
                prefix + "executedPrice              [" + this.executedPrice + "]" + "\n" +
                prefix + "externalSystemId   [" + this.externalSystemId + "]" + "\n";

		return stb;
	}
}
