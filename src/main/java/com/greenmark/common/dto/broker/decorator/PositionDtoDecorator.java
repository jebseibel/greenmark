package com.greenmark.common.dto.broker.decorator;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import com.greenmark.common.GmConstantsBroker;
import com.greenmark.common.dto.broker.PositionDto;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTFormatter;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: PositionBase</p>
 * <p>Description: This decorator class contains convenience methods for its PositionDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
@Slf4j
public class PositionDtoDecorator extends PositionDto implements Comparator, Serializable {
	public static final String CLASSNAME = "PositionBase";
	private static final long serialVersionUID = 1L;

	public PositionDtoDecorator() {
		super();
	}

	public PositionDtoDecorator(int harvestStrategyTypeId, int bucketStrategyStateId, int originBucketTimeframe) {
		super(harvestStrategyTypeId, bucketStrategyStateId, originBucketTimeframe);
	}

//	public PositionDtoDecorator(String xmldata) {
//		super(xmldata, trace);
//	}

	public PositionDtoDecorator(PositionDto inPosition) {
		try {
			if (inPosition != null)
				BeanUtils.copyProperties(this, inPosition);
		} catch (InvocationTargetException ite) {
			System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage());
		} catch (IllegalAccessException iae) {
			System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
		}
	}

	// ------------------------------------------------ CONVENIENCE METHODS ---------------------------------------------------
	public final Object getKey() {
		return Long.valueOf(id);
	}

	public final Object getValue() {
		return this;
	}

	public final int hashCode() {
		return (int) id;
	}

	public final boolean equals(Object compareObject) {
		PositionDtoDecorator obj = (PositionDtoDecorator) compareObject;
        return obj.getKey().equals(Long.valueOf(id));
	}

	public String getSellMitigationStrategy() {
		if (!positionDropcat) {
			if (positionPainedOutNormally)
				return "LOSS - PAIN";
			else {
				String profitLossString = "LOSS ";
				if (totalGrowthAmount > 0)
					profitLossString = "GAIN ";

				// System.out.println(" positionStatus: " + displayPositionStatus());
				switch (positionStatus) {
				case GmConstantsBroker.POSITION_STATUS_834_SUCCESS_PROFIT_TAKE:
					return profitLossString + "8/34 PROFIT TAKE";
				case GmConstantsBroker.POSITION_STATUS_834_SUCCESS_20SMA:
					return profitLossString + "8/34 EMA 20";
				case GmConstantsBroker.POSITION_STATUS_834_SUCCESS_34EMA:
					return profitLossString + "8/34 EMA 34";
				case GmConstantsBroker.POSITION_STATUS_834_CALCULATED_PAIN:
					return profitLossString + "8/34 PAIN";
				case GmConstantsBroker.POSITION_STATUS_834_REJECTED:
					return profitLossString + "8/34 REJECTED";
				case GmConstantsBroker.POSITION_STATUS_834_CONFIRMED_THEN_REJECTED:
					return profitLossString + "8/34 CONFIRM-REJECTED";
				case GmConstantsBroker.POSITION_STATUS_COMPLETED:
					return profitLossString + "COMPLETED";
				case GmConstantsBroker.POSITION_STATUS_NORMAL:
					return profitLossString + "COMPLETED";
				}
			}
		} else {
			String returnVal = "DROPCAT";

			if (positionDropcatSoldEndofday)
				returnVal += " - EndOfDay";
			else if (positionDropcatPainOut)
				returnVal += " - Pain Out";
			else
				returnVal += " - BucketsServiceHelper";

			if (positionDropcatDoubledDown)
				returnVal += ", DBL";

			return returnVal;
		}

		return "UNKNOWN STATUS";
	}

	// ------------------------------------------------ DECORATOR METHODS ---------------------------------------------------
	public String getPainPriceDisplay() {
		String returnString = "N/A";
		returnString = UTDisplayFormatter.floatToString(painPrice);
		return returnString;
	}

	public String getTotalGrowthPricesAmountDisplay() {
		String returnString = "N/A";
		returnString = UTFormatter.formatPrice(totalGrowthPricesAmount);
		return returnString;
	}

	public String getTotalGrowthAmountDisplay() {
		String returnString = "N/A";
		returnString = UTFormatter.formatPrice(totalGrowthAmount);
		return returnString;
	}

	public String getSuccessOrPain() {
		if (totalGrowthAmount > 0)
			return "SUCCESS";
		else
			return "LOSS";
	}

	public String getLongOrShortDisplay() {
		if (longOrShort == 1)
			return "LONG";
		else
			return "SHORT";
	}

	public String getBuyExecutedPriceDisplay() {
		String returnString = "N/A";
		returnString = UTDisplayFormatter.floatToString(buyExecutedPrice);
		return returnString;
	}

	public String getSellExecutedPriceDisplay() {
		String returnString = "N/A";
		returnString = UTDisplayFormatter.floatToString(sellExecutedPrice);
		return returnString;
	}

	public String getPercentGrowthPricesDisplay() {
		String returnString = "N/A";
		returnString = UTFormatter.formatPrice(percentGrowthPrices);
		return returnString;
	}

	public String getPercentGrowthDisplay() {
		String returnString = "N/A";
		returnString = UTFormatter.formatPrice(percentGrowth);
		return returnString;
	}

	public String getTotalTransactionFeesString() {
		try {
			String returnString = "N/A";
			returnString = UTFormatter.formatPrice(totalTransactionFees);
			return returnString;
		} catch (Exception ex) {
			System.out.println("============= ERROR IN " + CLASSNAME + "!   getTotalTransactionFeesString  Exception Message:  [" + ex.getMessage() + "]");
		}
		return "";
	}

	public String getTotalMarginFeesString() {
		try {
			String returnString = "N/A";
			returnString = UTFormatter.formatPrice(totalMarginFees);
			return returnString;
		} catch (Exception ex) {
			System.out.println("============= ERROR IN " + CLASSNAME + "!   getTotalMarginFeesString  Exception Message:  [" + ex.getMessage() + "]");
		}
		return "";
	}
}
