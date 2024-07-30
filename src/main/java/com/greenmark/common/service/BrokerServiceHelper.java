package com.greenmark.common.service;

import java.time.LocalDateTime;

import com.greenmark.common.GmConstantsBroker;
import com.greenmark.common.dto.broker.database.decorator.OrderDbDecorator;
import com.greenmark.common.dto.broker.decorator.ExecutionDtoDecorator;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 * <p>Title: BrokerServiceHelper</p>
 * <p>Description: This class has convenience methods that are used by the different Broker services.</p>

 * @author  Monte Seibel
 * @version 7.0
 * @formatter:on
**/

public class BrokerServiceHelper {
	public static final String CLASSNAME = "BrokerServiceHelper";

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///// BROKER TYPE METHODS
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String getTypeForId(int type) {
		String typename = "Unknown";

		switch (type) {
		case GmConstantsBroker.BROKER_TYPE_TRAPPING_SIMULATOR:
			return "BROKER_TYPE_TRAPPING_SIMULATOR";

		case GmConstantsBroker.BROKER_TYPE_KUCOIN:
			return "BROKER_TYPE_KUCOIN";

		case GmConstantsBroker.BROKER_TYPE_KRAKEN:
			return "BROKER_TYPE_KRAKEN";
		}
		return typename;
	}

	public static ExecutionDtoDecorator createExecutionForOrder(OrderDbDecorator thisOrder, LocalDateTime todaysCal, float curr_price, float numShares) throws Exception {
		ExecutionDtoDecorator newExecution = new ExecutionDtoDecorator();
		newExecution.setExecutedPrice(curr_price);
		newExecution.setExecutedNumShares(numShares);
		newExecution.setExecutionDate(todaysCal);
		newExecution.setOrderId(thisOrder.getId());
		newExecution.setExternalOrderId(thisOrder.getExternalOrderId());
		newExecution.setActive(1);
		return newExecution;
	}
}
