package com.greenmark.common.dto.pricedata;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.greenmark.common.GmConstantsDatafeed;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: PriceDataDto</p>
 * <p>Description: This base class is used to store the data points retrieved from a datafeed or database into arrays, each index representing a new HLOC data set.  It's subclasses use this data
 * in many ways.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class PriceDataDto implements Serializable {
    public static final String CLASSNAME = "PriceDataDto";
    public static final int TYPE_HIGH = 1;
    public static final int TYPE_LOW = 2;
    public static final int TYPE_OPEN = 3;
    public static final int TYPE_CLOSE = 4;
    static final int MAX_DAYS = GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE;
    static final int MAX_CACHE = GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE;
    static final int DEFAULT_INTRA = 1000;
    static final int DEFAULT_DAILY = 1000;
    static final int DEFAULT_WEEKLY = 200;
    static final int DEFAULT_MONTHLY = 200;
    static final int SOCKET_TIMEOUT = 60000;
    static final int FIXED_SIZE = 50;
    static final int CACHE_HISTORY = 100;
    private static final long serialVersionUID = 1L;
    protected static String lastDay = "00";
    protected static String lastHour = "00";
    // TODO: Reduce this to just numdate and dateLDT
    protected int[] numdate;// This is used by datafeeds when putting data in these objects to seed the PH DB.
    protected int[] dateEpochSeconds;
    protected LocalDateTime[] dateLDT;
    protected String[] dateString;

    protected float[] open;
    protected float[] close;
    protected float[] high;
    protected float[] low;

    protected float[] rsi_ranking;
    protected float[] volume;
    protected float[] moneyflow;
    protected int[] iqcpower;

    protected boolean min1QuerySuccess = true;
    protected int totalNumDataPoints; // This is how many data points are in this object.
    protected int currentGreenmanPeriod = 0; // This is the index to the current GM historical datetime.

    public PriceDataDto() {
        reallocArraySizes(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE);
        totalNumDataPoints = 0;
        currentGreenmanPeriod = 0;

    }

    // ------------------------------------------------ SIMPLE SETTERS/GETTERS ---------------------------------------------
    public static String getLastDay() {
        return lastDay;
    }

    public static void setLastDay(String lastDay) {
        PriceDataDto.lastDay = lastDay;
    }

    public static String getLastHour() {
        return lastHour;
    }

    public static void setLastHour(String lastHour) {
        PriceDataDto.lastHour = lastHour;
    }

    public void reallocArraySizes(int dataArraySize) {
        numdate = new int[dataArraySize];
        dateEpochSeconds = new int[dataArraySize];
        dateLDT = new LocalDateTime[dataArraySize];
        dateString = new String[dataArraySize];

        open = new float[dataArraySize];
        close = new float[dataArraySize];
        high = new float[dataArraySize];
        low = new float[dataArraySize];
        rsi_ranking = new float[dataArraySize];
        volume = new float[dataArraySize];
        moneyflow = new float[dataArraySize];
        iqcpower = new int[dataArraySize];

        for (int k = 0; k < dataArraySize; k++)
            iqcpower[k] = 0;

        for (int l = 0; l < dataArraySize; l++)
            rsi_ranking[l] = 0.0F;

        reinitMaxDay();
        min1QuerySuccess = true;
    }

    public void reinitMaxDay() {
        currentGreenmanPeriod = 0;
        totalNumDataPoints = 0;
    }

    public int[] getNumdate() {
        return numdate;
    }

    public void setNumdate(int[] numdate) {
        this.numdate = numdate;
    }

    public int[] getDateEpochSeconds() {
        return dateEpochSeconds;
    }

    public void setDateEpochSeconds(int[] dateEpochSeconds) {
        this.dateEpochSeconds = dateEpochSeconds;
    }

    public LocalDateTime[] getDateLDT() {
        return dateLDT;
    }

    public void setDateLDT(LocalDateTime[] dateLDT) {
        this.dateLDT = dateLDT;
    }

    public String[] getDateString() {
        return dateString;
    }

    public void setDateString(String[] dateString) {
        this.dateString = dateString;
    }

    public float[] getOpen() {
        return open;
    }

    public void setOpen(float[] open) {
        this.open = open;
    }

    public float[] getClose() {
        return close;
    }

    public void setClose(float[] close) {
        this.close = close;
    }

    public float[] getHigh() {
        return high;
    }

    public void setHigh(float[] high) {
        this.high = high;
    }

    public float[] getLow() {
        return low;
    }

    public void setLow(float[] low) {
        this.low = low;
    }

    public float[] getRsi_ranking() {
        return rsi_ranking;
    }

    public void setRsi_ranking(float[] rsi_ranking) {
        this.rsi_ranking = rsi_ranking;
    }

    public float[] getVolume() {
        return volume;
    }

    public void setVolume(float[] volume) {
        this.volume = volume;
    }

    public float[] getMoneyflow() {
        return moneyflow;
    }

    public void setMoneyflow(float[] moneyflow) {
        this.moneyflow = moneyflow;
    }

    public int[] getIqcpower() {
        return iqcpower;
    }

    public void setIqcpower(int[] iqcpower) {
        this.iqcpower = iqcpower;
    }

    public boolean isMin1QuerySuccess() {
        return min1QuerySuccess;
    }

    public void setMin1QuerySuccess(boolean min1QuerySuccess) {
        this.min1QuerySuccess = min1QuerySuccess;
    }

    public int getTotalNumDataPoints() {
        return totalNumDataPoints;
    }

    public void setTotalNumDataPoints(int totalNumDataPoints) {
        this.totalNumDataPoints = totalNumDataPoints;
    }

    public int getCurrentGreenmanPeriod() {
        return currentGreenmanPeriod;
    }

    public void setCurrentGreenmanPeriod(int currentGreenmanPeriod) {
        this.currentGreenmanPeriod = currentGreenmanPeriod;
    }
}
