package com.greenmark.common.dto.strategy.decorator;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.greenmark.common.GmConstants;
import com.greenmark.common.dto.strategy.DropcatDto;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTFormatter;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: DropcatDtoDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its DropcatDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class DropcatDtoDecorator extends DropcatDto implements Serializable {
	public static final String CLASSNAME = "DropcatDtoDecorator";
	private static final long serialVersionUID = 1L;

	public DropcatDtoDecorator() {
	}

	public DropcatDtoDecorator(DropcatDtoDecorator inDropcat) {
		try {
			if (inDropcat != null)
				BeanUtils.copyProperties(this, inDropcat);
		} catch (InvocationTargetException ite) {
			System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage());
		} catch (IllegalAccessException iae) {
			System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
		}
	}

	public DropcatDtoDecorator(String xmldata) {
		super(xmldata);
	}

	public float getSuccessLossPercent() {
		float successLossPercent_f = 0F;

		if (longOrShortStock == GmConstants.LONG_SECURITY)
			successLossPercent_f = ((sellExecutedPrice - trapTriggeredPainPrice) / trapTriggeredPainPrice) * 100F;
		else
			successLossPercent_f = ((trapTriggeredPainPrice - sellExecutedPrice) / trapTriggeredPainPrice) * 100F;

		return successLossPercent_f;
	}

	public String getSuccessLossPercentString() throws Exception {
		return UTFormatter.formatPrice(getSuccessLossPercent()) + "%";
	}

	public String getTrapPercentLossString() throws Exception {
		return UTFormatter.formatPrice(trapPercentLoss * 100F) + "%";
	}

	public String getTrapPercentLossThresholdString() throws Exception {
		return UTFormatter.formatPrice(trapPercentLossThreshold) + "%";
	}

	public String getTrapTriggeredPainPriceString() throws Exception {
		return "$" + UTFormatter.formatPrice(trapTriggeredPainPrice);
	}

	public String getTrapPriceString() throws Exception {
		return "$" + UTFormatter.formatPrice(trapPrice);
	}

	public String getAdditionalPainPriceString() throws Exception {
		return "$" + UTFormatter.formatPrice(additionalPainPrice);
	}

	public String getAdditionalPainPriceThresholdString() throws Exception {
		return UTFormatter.formatPrice(additionalPainPriceThreshold) + "%";
	}

	public String getExitMin15StochkString() throws Exception {
		return UTFormatter.formatPrice(exitMin15Stochk);
	}

	public String getExitMin15StochkThresholdString() throws Exception {
		return UTFormatter.formatPrice(exitMin15StochkThreshold);
	}

	public String getSellPlacedMin5StochkString() throws Exception {
		return UTFormatter.formatPrice(sellPlacedMin5Stochk);
	}

	public String getSellPlacedMin5StochkThresholdString() throws Exception {
		return UTFormatter.formatPrice(sellPlacedMin5StochkThreshold);
	}

	public String getSellPlacedSellPriceString() throws Exception {
		return "$" + UTFormatter.formatPrice(sellPlacedSellPrice);
	}

	public String getSellPlacedCurrentPriceString() throws Exception {
		return "$" + UTFormatter.formatPrice(sellPlacedCurrentPrice);
	}

	public String getSellExecutedPriceString() throws Exception {
		return "$" + UTFormatter.formatPrice(sellExecutedPrice);
	}

	public String getDoubleDownBuyPriceString() throws Exception {
		return "$" + UTFormatter.formatPrice(doubleDownBuyPrice);
	}

	public String getDoubleDownMin5StochkString() throws Exception {
		return UTFormatter.formatPrice(doubleDownMin5Stochk);
	}

	public String getDoubleDownNumSharesString() throws Exception {
		return Integer.toString(doubleDownNumShares);
	}

	public String getTrappedDatetimeString() throws Exception {
		if (trappedDatetime != null)
			return UTDatetime.toString(trappedDatetime);
		else
			return "N/A";
	}

	public String getExitMin15DatetimeString() throws Exception {
		if (exitMin15Datetime != null)
			return UTDatetime.toString(exitMin15Datetime);
		else
			return "N/A";
	}

	public String getSellPlacedDatetimeString() throws Exception {
		if (sellPlacedDatetime != null)
			return UTDatetime.toString(sellPlacedDatetime);
		else
			return "N/A";
	}

	public String getSellExecutedDatetimeString() throws Exception {
		if (sellExecutedDatetime != null)
			return UTDatetime.toString(sellExecutedDatetime);
		else
			return "N/A";
	}

	public String getDoubleDownBuyDatetimeString() throws Exception {
		if (doubleDownBuyDatetime != null)
			return UTDatetime.toString(doubleDownBuyDatetime);
		else
			return "N/A";
	}
}
