package com.greenmark.common.dto.security.database.decorator;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsBroker;
import com.greenmark.common.dto.security.database.SecurityBase;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: SecurityBaseDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its AccountDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class SecurityBaseDecorator extends SecurityBase implements Serializable {
	public static final String CLASSNAME = "SecurityBaseDecorator";
	private static final long serialVersionUID = 1L;

	public SecurityBaseDecorator() {
		super();
	}

	public SecurityBaseDecorator(String displaySymbol, String dbQuerySymbol, int longOrShort, int stockType) {
		super(displaySymbol, dbQuerySymbol, longOrShort, stockType);
	}

//	public SecurityBaseDecorator(String xmldata) {
//		super(xmldata);
//	}

	////////////////////////////////////////////////////////////////////////////////
	// CONVENIENCE METHODS
	////////////////////////////////////////////////////////////////////////////////
	public void reinitPositionHasSold(LocalDateTime currentLDT) {
		buyOrSell = GmConstantsBroker.TYPE_BUY;
		numPeriodsInBucket = 0;
		movedToBucketDatetime = currentLDT;

		minute60LowHighPt = 0;
		minute60LowHighCalculated = false;
		dailyLowHighPt = 0;
		dailyLowHighCalculated = false;

		numDisqualifiedMinute60 = 0;
		numDisqualifiedMinute15 = 0;
		numDisqualifiedMinute05 = 0;
		numDisqualifiedMinute01 = 0;

		calculatedEntryPrice = 0;
		highLowPriceOnOrder = 0;

		stockWatchSize = 0;
		stockWatchAmount = 0;

		orderSize = 0;
		orderAmount = 0;
		orderError = GmConstants.ORDER_ERROR_DEFAULT;
	}

	public final void setMovedFromTimeframe(int movedFromTimeframe) {
		priceHistoryInitialized = false; // Reset this so we fetch new data next time we check.
		this.movedFromTimeframe = movedFromTimeframe;
	}

	public final void incrementDisqualified(int timeframe) {
		switch (timeframe) {
		case GmConstants.TYPE_MINUTE01:
			this.numDisqualifiedMinute01++;
			break;
		case GmConstants.TYPE_MINUTE05:
			this.numDisqualifiedMinute05++;
			break;
		case GmConstants.TYPE_MINUTE15:
			this.numDisqualifiedMinute15++;
			break;
		case GmConstants.TYPE_MINUTE60:
			this.numDisqualifiedMinute60++;
			break;

		}
	}

	////////////////////////////////////////////////////////////////////////////////
	// DECORATOR METHODS
	////////////////////////////////////////////////////////////////////////////////
	public final String getStatusString() {
		if (status == GmConstantsBroker.STATUS_NEW)
			return "New";
		if (status == GmConstantsBroker.STATUS_ORDER1_PLACED)
			return "Order1 Placed";
		if (status == GmConstantsBroker.STATUS_ORDER2_QUALIFY)
			return "Order2 Qualify";
		if (status == GmConstantsBroker.STATUS_ORDER2_PLACED)
			return "Order2 Placed";
		if (status == GmConstantsBroker.STATUS_COMPLETE)
			return "Complete";
		return "None";
	}
}
