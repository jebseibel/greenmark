package com.greenmark.common.dto.broker.database.decorator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Iterator;

import com.greenmark.common.GmConstantsBroker;
import com.greenmark.common.dto.broker.ExecutionDto;
import com.greenmark.common.dto.broker.database.OrderDb;
import com.greenmark.common.dto.broker.decorator.ExecutionDtoDecorator;
import com.greenmark.common.dto.parameters.LagPeriodsPrice;
import com.greenmark.utils.UTCalendarTime;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.trace.Trace;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: OrderDbDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its AccountDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class OrderDbDecorator extends OrderDb implements Serializable {
	public static final String CLASSNAME = "OrderDbDecorator";
	private static final long serialVersionUID = 1L;

	public OrderDbDecorator() {
	}

	public OrderDbDecorator(OrderDbDecorator inOrder) {
		super(inOrder);
	}

	public OrderDbDecorator(String symbol, String compactedExchangeSymbol, int stopOrMktOrder, int longOrShortOrder, String harvestStrategyAcronym, LocalDateTime orderPlacedDatetime,
			LagPeriodsPrice lagPeriodsPrice) {
		super(symbol, compactedExchangeSymbol, stopOrMktOrder, longOrShortOrder, harvestStrategyAcronym, orderPlacedDatetime, lagPeriodsPrice);
	}

	public OrderDbDecorator(PositionDbDecorator myPosition, float currentPrice, LagPeriodsPrice entryPrice, float numShares, int stopOrMktOrder, int longOrShort) {
		super(myPosition, currentPrice, entryPrice, numShares, stopOrMktOrder, longOrShort);
	}

	public OrderDbDecorator(String xmldata) {
		super(xmldata);
	}

	// Used by the IBBroker Simulator and Live Version
	public void orderExecutedStub(Trace trace) {
		String methodname = "orderExecutedStub";
		trace.in(CLASSNAME, methodname);
		if (trace.isLevelVerbose())
			trace.addVerbose("Entering orderExecutedStub ------------");

		// If our executedPrice is zero because the cumulativeBrokerExecution is bad (Financial Advisor type account), sum the executionDtos ourselves
		sumOrderExecutions(trace);

		
	}

	public void sumOrderExecutions(Trace trace) {
		String methodname = "sumOrderExecutions";
		trace.addVerbose("Entering sumOrderExecutions ------------");

		executedNumShares = 0;
		executedPrice = 0;

		LocalDateTime earliestDatetime = UTDatetime.getNowLDT();

		// First sum the total num executed executedNumShares, then compute average
		int i = 0;
		if (executionDtos != null) {

			for (ExecutionDto thisExec : executionDtos) {
				// Only sum the execution amount if it has a positive number of executedNumShares (Investment Advisor orders allocate executedNumShares to sub-accounts and
				// these executionDtos have a negative number of executedNumShares), which mean the total becomes zero...
				if (thisExec.getExecutedNumShares() > 0F) {
					executedNumShares += thisExec.getExecutedNumShares();

					if (earliestDatetime.isAfter(thisExec.getExecutionDate()) || (i == 0))
						earliestDatetime = thisExec.getExecutionDate();

					i++;
				}

				// Also set the order ID, since these may have been added as a list by IBBroker, and we
				// will surely need it for some reason later (though the DAO insert accounts for it)
				thisExec.setOrderId(id);

			} // Endfor each execution

			executedDatetime = earliestDatetime;

			// Now compute weighted average
			for (ExecutionDto thisExec : executionDtos) {
				// Only sum the execution amount if it has a positive number of executedNumShares (Investment Advisor orders allocate executedNumShares to sub-accounts and
				// these executionDtos have a negative number of executedNumShares), which mean the total becomes zero...
				if (thisExec.getExecutedNumShares() > 0F) {
					// Simply sum weighted value of this execution executedPrice with all the other executionDtos
					executedPrice += (thisExec.getExecutedPrice() * (thisExec.getExecutedNumShares() / executedNumShares));
				}
			}
		}
	}

	public boolean isPartialExpired(int numMinutes, UTCalendarTime todaysDatetime) {

		// Get the earliest execution datetime
		ExecutionDtoDecorator firstExecution = null;
		Iterator executionIter = executionDtos.iterator();
		if (executionIter.hasNext())
			firstExecution = (ExecutionDtoDecorator) executionIter.next();

		if (firstExecution == null) // We couldn't find one
			return false;

		// Add numMinutes to the first ExecutionDtoDecorator datetime
		UTCalendarTime executionDatetime = new UTCalendarTime();
		for (int i = 0; i < numMinutes; i++)
			executionDatetime.incrementMinute();

        return executionDatetime.isBeforeInCalendarDateTime(todaysDatetime, true);// No expirations for now.....
    }

	public String getStopOrMarketOrderDisplay() {
		return GmConstantsBroker.getStopOrMarketOrderDisplay(stopOrMarketOrder);
	}

	public String getBuyOrSellOrderDisplay() {
		return GmConstantsBroker.getBuyOrSellOrderDisplay(buyOrSellOrder);
	}

	public String getStatusDisplay() {
		return GmConstantsBroker.getStatusDisplay(status);
	}

	public String toStringSystemOut() {
		StringBuffer stb = new StringBuffer();
		String formattedDate = "";
		if (executedDatetime != null) {
			formattedDate = UTDatetime.toDbString(executedDatetime);
		}
		stb.append("\n-----------------------------------------------\n");
		stb.append("Order for stock= [" + stockSymbol + "]\n");
		stb.append("   current_price= [" + currentPrice + "]\n");
		stb.append("   executed_price= [" + executedPrice + "]\n");
		stb.append("   executed_num_shares= [" + executedNumShares + "]\n");
		stb.append("   executed_datetime= [" + formattedDate + "]\n");
		stb.append("   orderID= [" + externalOrderId + "]\n");
		stb.append("   external_tracking_num= [" + externalTrackingNum + "]\n");
		stb.append("   entry_price= [" + entryPrice + "]\n");
		stb.append("   target_size_in_shares= [" + targetSizeInShares + "]\n");
		stb.append("   target_amount_in_dollars= [" + targetAmountInDollars + "]\n");
		stb.append("-----------------------------------------------\n");
		return stb.toString();
	}
}
