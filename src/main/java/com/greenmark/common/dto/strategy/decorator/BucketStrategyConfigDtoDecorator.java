package com.greenmark.common.dto.strategy.decorator;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.greenmark.common.dto.strategy.BucketStrategyConfigDto;
import com.greenmark.utils.UTFormatter;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: BucketStrategyConfigDtoDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its BucketStrategyConfigDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class BucketStrategyConfigDtoDecorator extends BucketStrategyConfigDto implements Serializable {
	public static final String CLASSNAME = "BucketStrategyConfigDtoDecorator";
	private static final long serialVersionUID = 1L;

	public BucketStrategyConfigDtoDecorator() {
	}

	public BucketStrategyConfigDtoDecorator(BucketStrategyConfigDtoDecorator inDropcat) {
		try {
			if (inDropcat != null)
				BeanUtils.copyProperties(this, inDropcat);
		} catch (InvocationTargetException ite) {
			System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage());
		} catch (IllegalAccessException iae) {
			System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
		}
	}

	public BucketStrategyConfigDtoDecorator(String xmldata) {
		super(xmldata);
	}

	public String getUseStochKDisplay() throws Exception {
		if (useMomentum)
			return "Yes";
		else
			return "No";
	}

	public String getThresholdStochKDisplay() throws Exception {
		return UTFormatter.formatPrice(thresholdMomentum);
	}

	public String getUseMacdNumPeriodsDisplay() throws Exception {
		if (useMacdNumPeriods)
			return "Yes";
		else
			return "No";
	}

	public String getThresholdMacdNumPeriodsDisplay() throws Exception {
		return UTFormatter.formatPrice(thresholdMacdNumPeriods);
	}

	public String getUseMacdSignalDisplay() throws Exception {
		if (useMacdSignal)
			return "Yes";
		else
			return "No";
	}

	public String getUse834StrategyDisplay() throws Exception {
		if (use_834_Strategy)
			return "Yes";
		else
			return "No";
	}

	public String getWhenBought8EmaDisplay() throws Exception {
		return UTFormatter.formatPrice(whenBought8ema);
	}

	public String getWhenBought20SmaDisplay() throws Exception {
		return UTFormatter.formatPrice(whenBought20sma);
	}

	public String getNumPeriodsSinceSoldThresholdDisplay() throws Exception {
		return UTFormatter.formatPrice(numPeriodsSinceSoldThreshold);
	}

	public String getNumPeriodsSinceOver34ThresholdDisplay() throws Exception {
		return UTFormatter.formatPrice(numPeriodsSinceOver34Threshold);
	}

	public String getEma8ThresholdDisplay() throws Exception {
		return UTFormatter.formatPrice(ema8Threshold);
	}

	public String getSma14ThresholdDisplay() throws Exception {
		return UTFormatter.formatPrice(sma14Threshold);
	}

	public String getSma20ThresholdDisplay() throws Exception {
		return UTFormatter.formatPrice(sma20Threshold);
	}

	public String getEma34ThresholdDisplay() throws Exception {
		return UTFormatter.formatPrice(ema34Threshold);
	}

	public String getSma20StopLossThresholdDisplay() throws Exception {
		return UTFormatter.formatPrice(sma20StopLossThreshold);
	}

	public String getEma34StopLosshresholdDisplay() throws Exception {
		return UTFormatter.formatPrice(ema34StopLossThreshold);
	}

	public String getOnEntryOrderPercentDisplay() throws Exception {
		return UTFormatter.formatPercent(onEntryOrderPercent);
	}

	public String getOnConfirmOrderPercentDisplay() throws Exception {
		return UTFormatter.formatPercent(onConfirmOrderPercent);
	}
}
