package com.greenmark.common.dto.pricedata;

import java.io.Serializable;

import com.greenmark.common.dto.parameters.MovingAvgConfigParams;
import com.greenmark.common.dto.parameters.MovingAvgParams;
import lombok.extern.slf4j.Slf4j;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: PriceData</p>
 * <p>Description: This contains additional member variables and convenience methods needed on top of the HLOC price data and their calculated technical indicators.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
@Slf4j
public class PriceData extends PriceDataCalculator implements Serializable {
    public static final String CLASSNAME = "PriceData";
    private static final long serialVersionUID = 1L;
    protected int greenmanTimeframe;
    protected float currentInterperiodClose;
    protected float currentInterperiodStochK;
    private String tickerSymbol = "";

    public PriceData() {
        super();
    }

    public PriceData(int[] chartPreferences, int[] isMovingAverageSMA, int startDateInt, int stopDateInt) {
        super(chartPreferences, isMovingAverageSMA, startDateInt, stopDateInt);
    }

    // This constructor is used by Greenman objects when creating ChartStockData objects
    public PriceData(MovingAvgConfigParams movingAvgConfig, int timeframe, String tickerSymbol) {
        this();
        this.tickerSymbol = tickerSymbol;
        this.greenmanTimeframe = timeframe;
        setMovingAveragePeriods(movingAvgConfig);
    }

    public void setMovingAveragePeriods(MovingAvgParams ma1, MovingAvgParams ma2, MovingAvgParams ma3, MovingAvgParams ma4, MovingAvgParams ma5, MovingAvgParams ma6) {
        String methodname = "setMovingAveragePeriods";

        {
            log.info(CLASSNAME + "." + methodname + " input moving average periods: " + ma1.getMovingAveragePeriods() + ",\t" + ma2.getMovingAveragePeriods() + ", \t"
                    + ma3.getMovingAveragePeriods() + ", \t" + ma4.getMovingAveragePeriods() + ", \t" + ma5.getMovingAveragePeriods() + ", \t" + ma6.getMovingAveragePeriods());
            log.info(CLASSNAME + "." + methodname + " input is SMA: " + ma1.getIsSimpleMovingAverage() + ",\t" + ma2.getIsSimpleMovingAverage() + ", \t" + ma3.getIsSimpleMovingAverage() + ", \t"
                    + ma4.getIsSimpleMovingAverage() + ", \t" + ma5.getIsSimpleMovingAverage() + ", \t" + ma6.getIsSimpleMovingAverage());
        }

        movingAveragesCalcSMA = new int[NUM_MOVING_AVERAGES];

        movingAverage1Periods = ma1.getMovingAveragePeriods();
        if (ma1.getIsSimpleMovingAverage())
            movingAveragesCalcSMA[0] = 1;
        else
            movingAveragesCalcSMA[0] = 0;

        movingAverage2Periods = ma2.getMovingAveragePeriods();
        if (ma2.getIsSimpleMovingAverage())
            movingAveragesCalcSMA[1] = 1;
        else
            movingAveragesCalcSMA[1] = 0;

        movingAverage3Periods = ma3.getMovingAveragePeriods();
        if (ma3.getIsSimpleMovingAverage())
            movingAveragesCalcSMA[2] = 1;
        else
            movingAveragesCalcSMA[2] = 0;

        movingAverage4Periods = ma4.getMovingAveragePeriods();
        if (ma4.getIsSimpleMovingAverage())
            movingAveragesCalcSMA[3] = 1;
        else
            movingAveragesCalcSMA[3] = 0;

        movingAverage5Periods = ma5.getMovingAveragePeriods();
        if (ma5.getIsSimpleMovingAverage())
            movingAveragesCalcSMA[4] = 1;
        else
            movingAveragesCalcSMA[4] = 0;

        movingAverage6Periods = ma6.getMovingAveragePeriods();
        if (ma6.getIsSimpleMovingAverage())
            movingAveragesCalcSMA[5] = 1;
        else
            movingAveragesCalcSMA[5] = 0;
    }

    public MovingAvgConfigParams getMovingAveragePeriods() {
        MovingAvgConfigParams returnVal = new MovingAvgConfigParams();

        // EMA-8
        MovingAvgParams ma1 = new MovingAvgParams();
        if (movingAveragesCalcSMA[0] == 1)
            ma1.setIsSimpleMovingAverage(true);
        ma1.setMovingAveragePeriods(movingAverage1Periods);
        returnVal.setEma8(ma1);

        // SMA-14
        MovingAvgParams ma2 = new MovingAvgParams();
        if (movingAveragesCalcSMA[1] == 1)
            ma2.setIsSimpleMovingAverage(true);
        ma2.setMovingAveragePeriods(movingAverage2Periods);
        returnVal.setSma14(ma2);

        // SMA-20
        MovingAvgParams ma3 = new MovingAvgParams();
        if (movingAveragesCalcSMA[2] == 1)
            ma3.setIsSimpleMovingAverage(true);
        ma3.setMovingAveragePeriods(movingAverage3Periods);
        returnVal.setSma20(ma3);

        // EMA-34
        MovingAvgParams ma4 = new MovingAvgParams();
        if (movingAveragesCalcSMA[3] == 1)
            ma4.setIsSimpleMovingAverage(true);
        ma4.setMovingAveragePeriods(movingAverage4Periods);
        returnVal.setEma34(ma4);

        // SMA-20 S/L
        MovingAvgParams ma5 = new MovingAvgParams();
        if (movingAveragesCalcSMA[4] == 1)
            ma5.setIsSimpleMovingAverage(true);
        ma5.setMovingAveragePeriods(movingAverage5Periods);
        returnVal.setSma20SL(ma5);

        // EMA-34 S/L
        MovingAvgParams ma6 = new MovingAvgParams();
        if (movingAveragesCalcSMA[5] == 1)
            ma6.setIsSimpleMovingAverage(true);
        ma6.setMovingAveragePeriods(movingAverage6Periods);
        returnVal.setEma34SL(ma6);

        return returnVal;
    }

    public void setMovingAveragePeriods(MovingAvgConfigParams movingAvgConfig) {
        setMovingAveragePeriods(movingAvgConfig.getEma8(), movingAvgConfig.getSma14(), movingAvgConfig.getSma20(), movingAvgConfig.getEma34(), movingAvgConfig.getSma20SL(),
                movingAvgConfig.getEma34SL());
    }

    // ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------
    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public int getGreenmanTimeframe() {
        return greenmanTimeframe;
    }

    public void setGreenmanTimeframe(int greenmanTimeframe) {
        this.greenmanTimeframe = greenmanTimeframe;
    }

    public float getCurrentInterperiodClose() {
        return currentInterperiodClose;
    }

    public void setCurrentInterperiodClose(float currentInterperiodClose) {
        this.currentInterperiodClose = currentInterperiodClose;
    }

    public float getCurrentInterperiodStochK() {
        return currentInterperiodStochK;
    }

    public void setCurrentInterperiodStochK(float currentInterperiodStochK) {
        this.currentInterperiodStochK = currentInterperiodStochK;
    }
}
