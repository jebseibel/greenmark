package com.greenmark.common.dto.parameters;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: MovingAvgConfigParams</p>
 * <p>Description: This class is used to set moving average values that are used by our technical indicators.  It is passed to PriceData objects to set the 6 moving average
 *    number of periods and whether they are simple or exponential moving averages.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class MovingAvgConfigParams implements Serializable {
    public static final String CLASSNAME = "MovingAvgConfigParams";
    private static final long serialVersionUID = 1L;

    MovingAvgParams ema8;
    MovingAvgParams sma14;
    MovingAvgParams sma20;
    MovingAvgParams ema34;
    MovingAvgParams sma20SL;
    MovingAvgParams ema34SL;

    public MovingAvgConfigParams() {
    }

    public MovingAvgConfigParams(MovingAvgParams ema8, MovingAvgParams sma14, MovingAvgParams sma20, MovingAvgParams ema34, MovingAvgParams sma20SL, MovingAvgParams ema34SL) {
        this.ema8 = ema8;
        this.sma14 = sma14;
        this.sma20 = sma20;
        this.ema34 = ema34;
        this.sma20SL = sma20SL;
        this.ema34SL = ema34SL;
    }

    public MovingAvgConfigParams(MovingAvgConfigParams inMaConfig) {
        try {
            if (inMaConfig != null)
                BeanUtils.copyProperties(this, inMaConfig);
        } catch (InvocationTargetException ite) {
            System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage());
        } catch (IllegalAccessException iae) {
            System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
        }
    }

    // ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
    public MovingAvgParams getEma8() {
        return ema8;
    }

    public void setEma8(MovingAvgParams ema8) {
        this.ema8 = ema8;
    }

    public MovingAvgParams getSma14() {
        return sma14;
    }

    public void setSma14(MovingAvgParams sma14) {
        this.sma14 = sma14;
    }

    public MovingAvgParams getSma20() {
        return sma20;
    }

    public void setSma20(MovingAvgParams sma20) {
        this.sma20 = sma20;
    }

    public MovingAvgParams getEma34() {
        return ema34;
    }

    public void setEma34(MovingAvgParams ema34) {
        this.ema34 = ema34;
    }

    public MovingAvgParams getSma20SL() {
        return sma20SL;
    }

    public void setSma20SL(MovingAvgParams sma20sl) {
        sma20SL = sma20sl;
    }

    public MovingAvgParams getEma34SL() {
        return ema34SL;
    }

    public void setEma34SL(MovingAvgParams ema34sl) {
        ema34SL = ema34sl;
    }

}
