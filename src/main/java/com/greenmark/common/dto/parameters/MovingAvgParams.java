package com.greenmark.common.dto.parameters;

import java.io.Serializable;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: MovingAvgParams</p>
 * <p>Description: This class is used by the MovingAvgConfigParams class to define the number of periods used by a moving average calculation.
 *    It also defines whether the moving average is a simple or exponential moving average.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class MovingAvgParams implements Serializable {
	private static final long serialVersionUID = 1L;

	private int movingAveragePeriods;
	private boolean isSimpleMovingAverage = false;

	public MovingAvgParams() {
		// Empty constructor
	}

	public MovingAvgParams(int movingAveragePeriods, boolean isSimpleMovingAverage) {
		this.movingAveragePeriods = movingAveragePeriods;
		this.isSimpleMovingAverage = isSimpleMovingAverage;
	}

	public int getMovingAveragePeriods() {
		return movingAveragePeriods;
	}

	public void setMovingAveragePeriods(int movingAveragePeriods) {
		this.movingAveragePeriods = movingAveragePeriods;
	}

	public boolean getIsSimpleMovingAverage() {
		return isSimpleMovingAverage;
	}

	public void setIsSimpleMovingAverage(boolean isSimpleMovingAverage) {
		this.isSimpleMovingAverage = isSimpleMovingAverage;
	}

}
