package com.greenmark.common.dto.pricedata;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsDatafeed;
import com.greenmark.common.config.Config;
import com.greenmark.common.config.ConfigStrategy;
import com.greenmark.common.service.ApplicationDataContext;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTPropertyManager;
import com.greenmark.utils.UTUtils;
import com.greenmark.utils.trace.Trace;
import lombok.extern.slf4j.Slf4j;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: PriceDataCalculator</p>
 * <p>Description: This class contains all the member variables and methods needed to calculate all the technical indicators using the data in its base class: PriceDataDto base class.
 * Only calculators go in this class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
@Slf4j
public class PriceDataCalculator extends PriceDataQuery implements Serializable {
	public static final String CLASSNAME = "PriceDataCalculator";
	private static final long serialVersionUID = 1L;

	public static final int NUM_MOVING_AVERAGES = 6;

	public static final int CM_NONE = 0;
	public static final int CM_MA1 = 2;
	public static final int CM_MA2 = 3;
	public static final int CM_BB = 4;
	public static final int CM_LR = 5;
	public static final int CM_MA3 = 6;
	public static final int CM_ENV = 10;
	public static final int CM_SAR = 11;
	public static final int CM_OGSP = 12;
	public static final int CM_IQCZ = 13;
	public static final int CM_UDIO = 14;
	public static final int CM_OBV = 15;
	public static final int CM_EV = 16;
	public static final int CM_ACCB1 = 17;
	public static final int CM_ACCB2 = 18;
	public static final int CM_WMA = 19;
	public static final int CM_RG = 20;
	public static final int CM_TSI = 21;
	public static final int CM_FIB = 22;
	public static final int CM_FW = 23;
	public static final int CM_FIB_OVRL = 24;
	public static final int UDIO_NEUTRAL = 0;
	public static final int UDIO_UP = 1;
	public static final int UDIO_DOWN = 2;
	public static final int UDIO_INSIDE = 3;
	public static final int UDIO_OUTSIDE = 4;
	static final int N_REF = 4;

	public float stoch[];
	public float stoch_K[];
	public float stoch_D[];

	public float slope[];

	public float macd[];
	public float macdFastEma[]; // Used to calc macd
	public float macdSlowEma[]; // Used to calc macd
	public float macdSignal[];
	public float macdHistogram[];

	public int macdFastEmaPeriod;
	public int macdSlowEmaPeriod;
	public int macdSignalEmaPeriod;

	public float movingAverage1[];
	public float movingAverage2[];
	public float movingAverage3[];
	public float movingAverage4[];
	public float movingAverage5[];
	public float movingAverage6[];

	public int movingAverage1Periods = GmConstantsDatafeed.NUM_MA_8_PERIODS;
	public int movingAverage2Periods = GmConstantsDatafeed.NUM_MA_14_PERIODS;
	public int movingAverage3Periods = GmConstantsDatafeed.NUM_MA_20_PERIODS;
	public int movingAverage4Periods = GmConstantsDatafeed.NUM_MA_34_PERIODS;
	public int movingAverage5Periods = GmConstantsDatafeed.NUM_MA_50_PERIODS;
	public int movingAverage6Periods = GmConstantsDatafeed.NUM_MA_200_PERIODS;

	public int movingAveragesCalcSMA[];

	public float range_high;
	public float range_low;
	public float volume_high;
	public float ref_high;
	public float ref_low;
	public float true_range_high;
	public float true_range_low;
	public int low_index;
	public int high_index;
	public int ds;
	public int cross_x;
	public float cross_y;
	public float block_y;
	public int ref_low_index[];
	public float ref[][];
	public float scale_ref[][];
	public float support;

	public float lr[];
	public float lr_ch_up[];
	public float lr_ch_down[];
	public int lr_period;
	public int lr_stddev;
	public float bband[];
	public float bband_up[];
	public float bband_down[];

	// A running StdDeviation of # periods for each bollinger band.
	public float bband_up_stdev[];
	public float bband_down_stdev[];
	public float bband_diff_stdev[];

	// A running StdDeviation of # periods for each bollinger band, expressed as a percentage of the security price.
	public float bband_up_stdev_price_percent[];
	public float bband_down_stdev_price_percent[];
	public float bband_diff_stdev_price_percent[];

	public int bband_period;
	public int bband_percent;
	public float sar[];
	public float og_sp[];
	public float og_sp_int[];
	public float og_sp_st[];
	public int FINAL_SWING;
	public int udio[];

	public int st_k_period;
	public int st_k_ema_period;
	public int st_d_period;
	public float atr[];
	public float atr_max;
	public float atr_min;
	public int atr_period;
	public float tsi[];
	public float tsi_signal[];
	public float tsi_signal_diff[];
	public float tsi_arrows[];
	public float tsi_arrows_ud[];
	public int tsi_primary_period;
	public int tsi_secondary_period;
	public int tsi_signal_period;
	public float fib_r50[];
	public float fib_r382[];
	public float fib_r618[];
	public float fib_reversal_1[];
	public float fib_reversal_2[];
	public float fib_reversal_Z[];
	public float fib_reversal_points[];
	public float fibo_r50[];
	public float fibo_r382[];
	public float fibo_r618[];
	public float fibo_reversal_1[];
	public float fibo_reversal_2[];
	public float fibo_reversal_Z[];
	public float fibo_reversal_points[];
	public int fib_reversal_percent;
	public float fw[];
	public int fw_reversal_percent;
	public float rsi[];
	public float rsi_high;
	public float rsi_low;
	public int rsi_period;
	public float classic_rsi[];
	public float classic_rsi_high;
	public float classic_rsi_low;
	public int classic_rsi_period;
	public float mt[];
	public float mt_high;
	public float mt_low;
	public int mt_period;
	public float volatility[];
	public float voli_high;
	public float voli_low;
	public int voli_period;
	public float macd_high;
	public float macd_low;
	public float macd_slope[];
	public float macd_slope_arrows[];
	public float macd_slope_arrows_ud[];
	public float macd_slope_high;
	public float macd_slope_low;
	public float percentR[];
	public int percentR_period;
	public float percentR_high;
	public float percentR_low;
	public float uo[];
	public float uo_high;
	public float uo_low;
	public float obv[];
	public float obv_ma[];
	public float mma_obv[];
	public float o_obv[];
	public float obv_high;
	public float obv_low;
	public int obv_period;
	public float cci[];
	public float cci_high;
	public float cci_low;
	public int cci_period;
	public float dx[];
	public float adx[];
	public float pdi[];
	public float mdi[];
	public float dxt[];
	public float adxt[];
	public float pdit[];
	public float mdit[];
	public int dmi_period;
	public float mfi[];
	public float mfi_high;
	public float choppy[];
	public float moneyflow_a[];
	public float moneyflow_high;
	public float moneyflow_low;
	public float moneyflow_a_high;
	public float moneyflow_a_low;
	public float forceIndex[];
	public float forceIndexSMA1[];
	public float forceIndexSMA2[];
	public int fiSMA1_period;
	public int fiSMA2_period;
	public float volAvg[];
	public int volAvg_period;
	public int accband_period1;
	public int accband_period2;
	public float accband1U[];
	public float accbandSMA1[];
	public float accband1L[];
	public float accband2U[];
	public float accbandSMA2[];
	public float accband2L[];
	protected int FastMA_MD;
	protected int SlowMA_MD;
	protected int MacdMA_MD;
	public int md_period1;
	public int md_period2;
	public float plot1A_MD[];
	public float plot1B_MD[];
	public float plot2A_MD[];
	public float plot2B_MD[];
	public int trend_initiation_period1;
	public int trend_initiation_period2;
	public final int lengthTI = 10;
	public final int avgLenTI = 5;
	public final int trendLevelTI = 10;
	public float plot1A_TI[];
	public float plot1B_TI[];
	public float plot1C_TI[];
	public float plot1D_TI[];
	public float plot2A_TI[];
	public float plot2B_TI[];
	public float plot2C_TI[];
	public float plot2D_TI[];
	public int wma_period;
	public float wma_low[];
	public float wma_high[];
	static final int PFMAX = 300;
	static final int PFHIGH = 200;
	public char pf[][];
	public float pf_grid[];
	public int pf_grid_width;
	public int pf_grid_height;
	public float pf_high;
	public float pf_low;
	public float pf_price[] = { 0, 0.25F, 1.0F, 5F, 20F, 100F, 200F, 500F, 1000F, 5000F, 25000F };
	public float pf_box[] = { 0.0625F, 0.125F, 0.25F, 0.5F, 1.0F, 2.0F, 4F, 5F, 20F, 50F, 500F };

	public int startdate;
	public int enddate;

	public float iperiod_stoch[];
	public float iperiod_stoch_K[];
	public float iperiod_stoch_D[];

	public PriceDataCalculator() {
		super();
		allocStockdataArraysForIndicatorCalculations(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE);
	}

	public PriceDataCalculator(int chartPreferences[], int isMovingAverageSMA[], int startDateInt, int stopDateInt) {
		this();

		pointer_index = -1;
		cross_x = -1;
		cross_y = -1F;
		block_y = -1F;
		ref_low_index = new int[4];
		ref = new float[4][GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		scale_ref = new float[4][GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		movingAverage1 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		movingAverage2 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		movingAverage3 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		movingAverage4 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		movingAverage5 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		movingAverage6 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		movingAveragesCalcSMA = new int[NUM_MOVING_AVERAGES];
		lr = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		lr_ch_up = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		lr_ch_down = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];

		bband = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		bband_up = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		bband_down = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];

		bband_up_stdev = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		bband_down_stdev = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		bband_diff_stdev = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];

		bband_up_stdev_price_percent = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		bband_down_stdev_price_percent = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		bband_diff_stdev_price_percent = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];

		sar = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		og_sp = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		og_sp_int = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		og_sp_st = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		udio = new int[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		stoch = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		stoch_K = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		stoch_D = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		atr = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		tsi = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		tsi_signal = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		tsi_signal_diff = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		tsi_arrows = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		tsi_arrows_ud = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		tsi_primary_period = 14;
		tsi_secondary_period = 3;
		tsi_signal_period = 7;
		fib_r50 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		fib_r382 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		fib_r618 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		fib_reversal_1 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		fib_reversal_2 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		fib_reversal_Z = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		fib_reversal_points = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		fibo_r50 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		fibo_r382 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		fibo_r618 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		fibo_reversal_1 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		fibo_reversal_2 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		fibo_reversal_Z = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		fibo_reversal_points = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		fw = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		rsi = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		rsi_low = 1000F;
		classic_rsi = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		classic_rsi_low = 1000F;
		mt = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		volatility = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		macd = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];

		macdHistogram = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		macdFastEma = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		macdSlowEma = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		macdSignal = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		macd_slope = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		macd_slope_arrows = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		macd_slope_arrows_ud = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];

		percentR = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		uo = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		obv = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		obv_ma = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		mma_obv = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		o_obv = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		cci = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		dx = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		adx = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		pdi = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		mdi = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		dxt = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		adxt = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		pdit = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		mdit = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		mfi = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		choppy = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		moneyflow_a = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		forceIndex = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		forceIndexSMA1 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		forceIndexSMA2 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		volAvg = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		accband1U = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		accbandSMA1 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		accband1L = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		accband2U = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		accbandSMA2 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		accband2L = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		FastMA_MD = 12;
		SlowMA_MD = 26;
		MacdMA_MD = 9;
		plot1A_MD = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		plot1B_MD = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		plot2A_MD = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		plot2B_MD = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		plot1A_TI = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		plot1B_TI = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		plot1C_TI = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		plot1D_TI = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		plot2A_TI = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		plot2B_TI = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		plot2C_TI = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		plot2D_TI = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		wma_period = 5;
		wma_low = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		wma_high = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		pf = new char[300][200];
		pf_grid = new float[200];

		movingAverage1Periods = chartPreferences[0];
		movingAverage2Periods = chartPreferences[1];
		movingAverage3Periods = chartPreferences[15];

		movingAverage4Periods = chartPreferences[35];
		movingAverage5Periods = chartPreferences[36];
		movingAverage6Periods = chartPreferences[37];

		for (int i1 = 0; i1 < NUM_MOVING_AVERAGES; i1++)
			movingAveragesCalcSMA[i1] = isMovingAverageSMA[i1];

		lr_period = chartPreferences[2];
		bband_period = chartPreferences[3];
		st_k_period = chartPreferences[4];
		st_d_period = chartPreferences[5];
		rsi_period = chartPreferences[6];
		mt_period = chartPreferences[7];
		macdFastEmaPeriod = chartPreferences[8];
		macdSlowEmaPeriod = chartPreferences[9];
		dmi_period = chartPreferences[10];
		cci_period = chartPreferences[11];
		voli_period = chartPreferences[12];
		percentR_period = chartPreferences[13];
		bband_percent = chartPreferences[14];

		macdSignalEmaPeriod = chartPreferences[16];
		atr_period = chartPreferences[17];
		fiSMA1_period = chartPreferences[18];
		fiSMA2_period = chartPreferences[19];
		volAvg_period = chartPreferences[20];
		classic_rsi_period = chartPreferences[21];
		accband_period1 = chartPreferences[22];
		trend_initiation_period1 = chartPreferences[23];
		md_period1 = chartPreferences[24];
		accband_period2 = chartPreferences[25];
		md_period2 = chartPreferences[26];
		trend_initiation_period2 = chartPreferences[27];
		wma_period = chartPreferences[28];
		tsi_primary_period = chartPreferences[29];
		tsi_secondary_period = chartPreferences[30];
		tsi_signal_period = chartPreferences[31];
		fib_reversal_percent = chartPreferences[32];
		fw_reversal_percent = chartPreferences[33];
		lr_stddev = chartPreferences[34];
		st_k_ema_period = chartPreferences[38];
		if (st_k_ema_period == 0)
			st_k_ema_period = GmConstants.NUM_PTS_STOCH_SLOW_K_EMA_PERIOD;
		startdate = startDateInt;
		enddate = stopDateInt;
	}

	public void allocStockdataArraysForIndicatorCalculations() {
		allocStockdataArraysForIndicatorCalculations(GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE);
	}

	public void allocStockdataArraysForIndicatorCalculations(int dataArraySize) {
		movingAverage1 = new float[dataArraySize];
		movingAverage2 = new float[dataArraySize];
		movingAverage3 = new float[dataArraySize];
		movingAverage4 = new float[dataArraySize];
		movingAverage5 = new float[dataArraySize];
		movingAverage6 = new float[dataArraySize];

		iperiod_stoch = new float[dataArraySize];
		iperiod_stoch_K = new float[dataArraySize];
		iperiod_stoch_D = new float[dataArraySize];

		stoch = new float[dataArraySize];
		stoch_K = new float[dataArraySize];
		stoch_D = new float[dataArraySize];

		rsi = new float[dataArraySize];
		classic_rsi = new float[dataArraySize];
		classic_rsi_low = 1000F;

		slope = new float[dataArraySize];

		macd = new float[dataArraySize];
		macdHistogram = new float[dataArraySize];
		macdFastEma = new float[dataArraySize];
		macdSlowEma = new float[dataArraySize];
		macdSignal = new float[dataArraySize];
		macd_slope = new float[dataArraySize];
		macd_slope_arrows = new float[dataArraySize];
		macd_slope_arrows_ud = new float[dataArraySize];

		bband = new float[dataArraySize];
		bband_up = new float[dataArraySize];
		bband_down = new float[dataArraySize];

		bband_up_stdev = new float[dataArraySize];
		bband_down_stdev = new float[dataArraySize];
		bband_diff_stdev = new float[dataArraySize];

		bband_up_stdev_price_percent = new float[dataArraySize];
		bband_down_stdev_price_percent = new float[dataArraySize];
		bband_diff_stdev_price_percent = new float[dataArraySize];

		FastMA_MD = 12;
		SlowMA_MD = 26;
		MacdMA_MD = 9;

		st_k_period = Config.getValueAsInt(ConfigStrategy.MODEL_STOCHK_NUM_PERIODS);
		st_k_ema_period = GmConstants.NUM_PTS_STOCH_SLOW_K_EMA_PERIOD;
		st_d_period = Config.getValueAsInt(ConfigStrategy.MODEL_STOCHD_NUM_PERIODS);
	}

	public void CalculateGreenmanIndicators(Trace trace) {
		String methodname = "CalculateGreenmanIndicators";
		trace.in(CLASSNAME, methodname);

		try {
			allocStockdataArraysForIndicatorCalculations(super.totalNumDataPoints);

			int fastKNumPeriods = Config.getValueAsInt(ConfigStrategy.MODEL_STOCHK_NUM_PERIODS);
			int slowDNumPeriods = Config.getValueAsInt(ConfigStrategy.MODEL_STOCHD_NUM_PERIODS);
			CalculateStochastics(fastKNumPeriods, GmConstants.NUM_PTS_STOCH_SLOW_K_EMA_PERIOD, slowDNumPeriods);

			int macDnumPeriodsLong = Config.getValueAsInt(ConfigStrategy.MODEL_MACD_NUM_PERIODS_LONG);
			int macDnumPeriodsShort = Config.getValueAsInt(ConfigStrategy.MODEL_MACD_NUM_PERIODS_SHORT);
			int macDnumPeriodsSlow = Config.getValueAsInt(ConfigStrategy.MODEL_MACD_NUM_PERIODS_SLOW);

			if (trace.isLevelAnalysis())
				log.info(" PriceDataHistory.CalculateIndicatorsGreenman()      macDnumPeriodsShort: " + macDnumPeriodsShort + " macDnumPeriodsLong: " + macDnumPeriodsLong);

			CalculateMACD(macDnumPeriodsShort, macDnumPeriodsLong, macDnumPeriodsSlow, false);

			classic_rsi_period = GmConstantsDatafeed.NUM_RSI_CLASSIC_PERIODS;
			CalculateClassicRSI();

			if (trace.isLevelAnalysis())
				log.info(" PriceDataHistory.CalculateIndicatorsGreenman(), movingAverage1Periods: " + movingAverage1Periods + ", movingAverage2Periods: " + movingAverage2Periods
						+ ", movingAverage3Periods: " + movingAverage3Periods + ", movingAverage4Periods: " + movingAverage4Periods + ", movingAverage5Periods: " + movingAverage5Periods
						+ ", movingAverage6Periods: " + movingAverage6Periods);

			int cnt = 0;

			 {
				for (int emaOrSma : movingAveragesCalcSMA) {
					{
						log.info(CLASSNAME + "." + methodname + " Ema/Sma[" + cnt + "]: " + emaOrSma);
						cnt++;
					}
				}

				log.info(CLASSNAME + "." + methodname + " ma1Periods: " + movingAverage1Periods + "\n" + " ma2Periods: " + movingAverage2Periods + "\n" + " ma3Periods: " + movingAverage3Periods
						+ "\n" + " ma4Periods: " + movingAverage4Periods + "\n" + " ma5Periods: " + movingAverage5Periods + "\n" + " ma6Periods: " + movingAverage6Periods + "\n");
			}

			CalculateMovingAverage_1();
			CalculateMovingAverage_2();
			CalculateMovingAverage_3();
			CalculateMovingAverage_4();
			CalculateMovingAverage_5();
			CalculateMovingAverage_6();

			// log.error("Before CalculateBollingerBandStdDeviation");
			// We're waiting to do this, exception in historical when we try this:
			// CalculateBollingerBand();
			// CalculateBollingerBandStdDeviation();
			// CalculateBollingerBandStdDevPricePercent();

		} catch (Exception e) {
			if (UTUtils.isNotNorE(e.getMessage()) && "null".equals(e.getMessage()) == false) // Ignore null exception messages.
				log.error("Error in " + CLASSNAME + ": " + methodname + "  Last Data Point on Greenman List     Time[" + (super.currentGreenmanPeriod - 1) + "]: ["
						+ super.dateString[super.currentGreenmanPeriod - 1] + "]  Open: [" + super.open[super.currentGreenmanPeriod - 1] + "] High: [" + super.high[super.currentGreenmanPeriod - 1]
						+ "]  Low: [" + super.low[super.currentGreenmanPeriod - 1] + "]   Close: [" + super.close[super.currentGreenmanPeriod - 1] + "]" + ", Message: " + e.getMessage());
		}

		
		return;
	}

	public void CalculateMovingAverages() {
		CalculateMovingAverage_1();
		CalculateMovingAverage_2();
		CalculateMovingAverage_3();
		CalculateMovingAverage_4();
		CalculateMovingAverage_5();
		CalculateMovingAverage_6();
	}

	public void doCalculation(String chartTimeframeString) {
		CalculateMovingAverage_1();
		CalculateMovingAverage_2();
		CalculateMovingAverage_3();
		CalculateMovingAverage_4();
		CalculateMovingAverage_5();
		CalculateMovingAverage_6();

		CalculateLinearRegression();
		CalculateBollingerBand();
		CalculateBollingerBandStdDeviation();
		CalculateBollingerBandStdDevPricePercent();
		CalculateSar();
		CalculateOGSPClassic();
		CalculateUDIO();
		CalculateATR();
		CalculateTSI();
		CalculateFibOverlay();
		CalculateFW();
		CalculateStochastics(st_k_period, st_k_ema_period, st_d_period);
		CalculateMomentum();
		CalculateRSI();
		CalculateMACD(FastMA_MD, SlowMA_MD, macdSignalEmaPeriod, false);
		CalculateCCI();
		CalculateOBV();
		CalculateUltimateOscillator();
		CalculateVolatility(chartTimeframeString);
		CalculateDMI();
		CalculateDMIT();
		CalculatePercentR();
		CalculateMoneyFlow();
		CalculateForceIndex();
		CalculateVolAvg();
		CalculateClassicRSI();
		CalculateAccelerationBands(accband1U, accband1L, accbandSMA1, accband_period1);
		CalculateAccelerationBands(accband2U, accband2L, accbandSMA2, accband_period2);
		CalculateMomentumDivergence(plot1A_MD, plot1B_MD, md_period1);
		CalculateMomentumDivergence(plot2A_MD, plot2B_MD, md_period2);
		CalculateTrendInitiation(plot1A_TI, plot1B_TI, plot1C_TI, plot1D_TI, trend_initiation_period1);
		CalculateTrendInitiation(plot2A_TI, plot2B_TI, plot2C_TI, plot2D_TI, trend_initiation_period2);
		CalculateWMA();
	}

	public void CalculateAccelerationBands1() {
		CalculateAccelerationBands(accband1U, accband1L, accbandSMA1, accband_period1);
	}

	public void CalculateAccelerationBands2() {
		CalculateAccelerationBands(accband2U, accband2L, accbandSMA2, accband_period2);
	}

	public void CalculateMomentumDivergence1() {
		CalculateMomentumDivergence(plot1A_MD, plot1B_MD, md_period1);
	}

	public void CalculateMomentumDivergence2() {
		CalculateMomentumDivergence(plot2A_MD, plot2B_MD, md_period2);
	}

	public void CalculateTrendInitiation1() {
		CalculateTrendInitiation(plot1A_TI, plot1B_TI, plot1C_TI, plot1D_TI, trend_initiation_period1);
	}

	public void CalculateTrendInitiation2() {
		CalculateTrendInitiation(plot2A_TI, plot2B_TI, plot2C_TI, plot2D_TI, trend_initiation_period2);
	}

	public void CalculateMoneyFlow() {
		moneyflow_a[0] = super.moneyflow[0];
		for (int i = 1; i < super.totalNumDataPoints; i++)
			moneyflow_a[i] = moneyflow_a[i - 1] + super.moneyflow[i];
	}

	public void CalculateOverLayOBV() {
		float f = -1E+007F;
		float f1 = 1E+007F;
		float f2 = -100000F;
		float f3 = 100000F;
		for (int i = low_index; i <= high_index; i++) {
			if (obv[i] > f)
				f = obv[i];
			if (obv[i] < f1)
				f1 = obv[i];
			if (super.close[i] > f2)
				f2 = super.close[i];
			if (super.close[i] < f3)
				f3 = super.close[i];
		}

		for (int j = low_index; j <= high_index; j++)
			o_obv[j] = f3 + ((obv[j] - f1) * (f2 - f3)) / (f - f1);
	}

	public void calculateMMA(float af[], float af1[], int i) {
		float f = 0.0F;
		float f2 = 0.0F;
		for (int j = 0; j < i; j++)
			af[j] = 0.0F;

		for (int k = i - 1; k < super.totalNumDataPoints; k++) {
			float f1 = 0.0F;
			float f3 = 0.0F;
			for (int l = (k - i) + 1; l <= k; l++) {
				f1 += af1[l];
				f3 += (float) ((i - (k - l) * 2 - 1) / 2) * af1[l];
			}

			af[k] = f1 / (float) i + (6F * f3) / (float) ((i + 1) * i);
		}
	}

	public void CalculateMovingAverage_1() {
		if (movingAveragesCalcSMA[0] == 1) {
			CalculateSMA(movingAverage1, super.close, movingAverage1Periods);
			return;
		} else {
			CalculateEMA(movingAverage1, super.close, movingAverage1Periods, false, false);
			return;
		}
	}

	public void CalculateMovingAverage_2() {
		if (movingAveragesCalcSMA[1] == 1) {
			CalculateSMA(movingAverage2, super.close, movingAverage2Periods);
			return;
		} else {
			CalculateEMA(movingAverage2, super.close, movingAverage2Periods, false, false);
			return;
		}
	}

	public void CalculateMovingAverage_3() {
		if (movingAveragesCalcSMA[2] == 1) {
			CalculateSMA(movingAverage3, super.close, movingAverage3Periods);
			return;
		} else {
			CalculateEMA(movingAverage3, super.close, movingAverage3Periods, false, false);
			return;
		}
	}

	public void CalculateMovingAverage_4() {
		if (movingAveragesCalcSMA[3] == 1) {
			CalculateSMA(movingAverage4, super.close, movingAverage4Periods);
			return;
		} else {
			CalculateEMA(movingAverage4, super.close, movingAverage4Periods, false, false);
			return;
		}
	}

	public void CalculateMovingAverage_5() {
		if (movingAveragesCalcSMA[4] == 1) {
			CalculateSMA(movingAverage5, super.close, movingAverage5Periods);
			return;
		} else {
			CalculateEMA(movingAverage5, super.close, movingAverage5Periods, false, false);
			return;
		}
	}

	public void CalculateMovingAverage_6() {
		if (movingAveragesCalcSMA[5] == 1) {
			CalculateSMA(movingAverage6, super.close, movingAverage6Periods);
			return;
		} else {
			CalculateEMA(movingAverage6, super.close, movingAverage6Periods, false, false);
			return;
		}
	}

	public void CalculateSMA(float outSmaValues[], float inputPrices[], int numPeriods) {
		int beginIndex = 0;
		boolean debugOn = false;

		for (int dataPointsIndex = 0; dataPointsIndex <= super.totalNumDataPoints; dataPointsIndex++) {
			if (inputPrices[dataPointsIndex] == 0.0F)
				continue;
			beginIndex = dataPointsIndex;
			break;
		}

		for (int outerIndex = (beginIndex + numPeriods) - 1; outerIndex < super.totalNumDataPoints; outerIndex++) {
			if (outerIndex == 54) {
				debugOn = false;
			}
			if (debugOn)
				System.out.println("CalculateSMA outer loop, outerIndex: " + outerIndex + ", totalNumDataPoints: " + super.totalNumDataPoints);

			float priceSumTotal = 0.0F;
			for (int innerIndex = (outerIndex - numPeriods) + 1; innerIndex <= outerIndex; innerIndex++) {
				if (debugOn)
					System.out.println("CalculateSMA inner loop, innerIndex: " + innerIndex + ", totalNumDataPoints: " + super.totalNumDataPoints);

				priceSumTotal += inputPrices[innerIndex];
			}

			if (debugOn)
				System.out.println("CalculateSMA outer loop, priceSumTotal: " + priceSumTotal + ", numPeriods: " + numPeriods);
			if (numPeriods != 0) {
				if (debugOn)
					System.out.println("CalculateSMA calculate outValue, priceSumTotal: " + priceSumTotal + ", numPeriods: " + numPeriods);
				outSmaValues[outerIndex] = priceSumTotal / (float) numPeriods;
			}
		}
	}

	public void CalculateLinearRegression() {
		float f = 0.0F;
		float f1 = 0.0F;
		float f2 = 0.0F;
		float f3 = 0.0F;
		float f4 = 0.0F;
		float f5 = 0.0F;
		float f6 = 0.0F;
		float f8 = 0.0F;
		int k1;
		if (super.totalNumDataPoints < lr_period)
			k1 = 0;
		else
			k1 = super.totalNumDataPoints - lr_period;
		for (int i = k1; i < super.totalNumDataPoints; i++)
			if (super.high[i] != super.low[i]) {
				f += 1.0F / ((super.high[i] - super.low[i]) * (super.high[i] - super.low[i]));
				f1 += (float) i / ((super.high[i] - super.low[i]) * (super.high[i] - super.low[i]));
				f2 += super.close[i] / ((super.high[i] - super.low[i]) * (super.high[i] - super.low[i]));
				f3 += (float) (i * i) / ((super.high[i] - super.low[i]) * (super.high[i] - super.low[i]));
				f4 += ((float) i * super.close[i]) / ((super.high[i] - super.low[i]) * (super.high[i] - super.low[i]));
			}

		f5 = f * f3 - f1 * f1;
		if (f5 != 0.0F) {
			float f7 = (f3 * f2 - f1 * f4) / f5;
			float f9 = (f * f4 - f1 * f2) / f5;
			for (int j = k1; j < super.totalNumDataPoints; j++)
				lr[j] = f7 + f9 * (float) j;

		}
		float f10 = 0.0F;
		float f11 = super.totalNumDataPoints - k1;
		for (int k = k1; k < super.totalNumDataPoints; k++)
			f10 += super.close[k];

		f10 /= f11;
		float f12 = 0.0F;
		for (int l = k1; l < super.totalNumDataPoints; l++)
			f12 += ((super.close[l] - f10) * (super.close[l] - f10)) / f11;

		f12 = (float) Math.sqrt(f12);
		float f13 = f12 * (float) lr_stddev;
		for (int i1 = 0; i1 < k1; i1++) {
			lr_ch_up[i1] = -1F;
			lr_ch_down[i1] = -1F;
		}

		for (int j1 = k1; j1 < super.totalNumDataPoints; j1++) {
			lr_ch_up[j1] = lr[j1] + f13;
			lr_ch_down[j1] = lr[j1] - f13;
		}
	}

	public void CalculateBollingerBand() {
		float f1 = 0.0F;
		for (int j = 0; j <= bband_period - 1; j++)
			f1 += super.close[j];

		if (bband_period != 0)
			bband[bband_period - 1] = f1 / (float) bband_period;
		for (int i = bband_period; i < super.totalNumDataPoints; i++) {
			float f2 = 0.0F;
			float f3 = 0.0F;
			for (int k = (i - bband_period) + 1; k <= i; k++)
				f2 += super.close[k];

			if (bband_period != 0)
				bband[i] = f2 / (float) bband_period;
			f3 = bband[i];
			f2 = 0.0F;
			for (int l = (i - bband_period) + 1; l <= i; l++)
				f2 += (f3 - super.close[l]) * (f3 - super.close[l]);

			if (bband_period != 0)
				f2 /= bband_period;
			float f = (float) Math.sqrt(f2);
			bband_up[i] = bband[i] + (f * (float) bband_percent) / 100F;
			bband_down[i] = bband[i] - (f * (float) bband_percent) / 100F;
		}
	}

	private static int BB_STD_DEVIATION_NUM_PERIODS = 4;

	public void CalculateBollingerBandStdDeviation() {

		float[] bband_up_data = new float[BB_STD_DEVIATION_NUM_PERIODS];
		float[] bband_down_data = new float[BB_STD_DEVIATION_NUM_PERIODS];
		float[] bband_diff_data = new float[BB_STD_DEVIATION_NUM_PERIODS];

		for (int currentDataPeriod = bband_period + BB_STD_DEVIATION_NUM_PERIODS; currentDataPeriod < super.totalNumDataPoints; currentDataPeriod++) {

			// Assemble the data point arrays
			for (int dataIndex = 0; dataIndex < BB_STD_DEVIATION_NUM_PERIODS; dataIndex++) {

				// These will go in the data arrays backwards:
				bband_up_data[dataIndex] = bband_up[currentDataPeriod - dataIndex];
				bband_down_data[dataIndex] = bband_down[currentDataPeriod - dataIndex];
				bband_diff_data[dataIndex] = Math.abs(bband_up[currentDataPeriod - dataIndex] - bband_down[currentDataPeriod - dataIndex]);
			}

			bband_up_stdev[currentDataPeriod] = CalculateStdDeviation(bband_up_data);
			bband_down_stdev[currentDataPeriod] = CalculateStdDeviation(bband_down_data);
			bband_diff_stdev[currentDataPeriod] = CalculateStdDeviation(bband_diff_data);
		}
	}

	public void CalculateBollingerBandStdDevPricePercent() {
		for (int currentDataPeriod = bband_period + BB_STD_DEVIATION_NUM_PERIODS; currentDataPeriod < super.totalNumDataPoints; currentDataPeriod++) {

			// These 2 end up drawing the exact same graph as above.
			bband_up_stdev_price_percent[currentDataPeriod] = bband_up_stdev[currentDataPeriod] / close[currentDataPeriod];
			bband_down_stdev_price_percent[currentDataPeriod] = bband_down_stdev[currentDataPeriod] / close[currentDataPeriod];

			// Here's what we're calculating, the different between the up/down std deviations.
			bband_diff_stdev_price_percent[currentDataPeriod] = Math.abs(bband_up_stdev[currentDataPeriod] - bband_down_stdev[currentDataPeriod]);
		}
	}

	// Returns a single value, which is the standard deviation of the input array of float.
	public float CalculateStdDeviation(float[] inValues) {
		if (inValues == null || inValues.length == 0)
			return 0F;

		float total = 0F;
		float average = 0F;
		float[] distance2fromAvg = new float[inValues.length];
		float distance2fromAvgSum = 0F;

		// sum values and find average
		for (int i = 0; i < inValues.length; i++) {
			total += inValues[i];
		}
		average = total / inValues.length;

		for (int i = 0; i < inValues.length; i++) {

			// For each data point, find the square of its distance to the mean.
			float distanceToAvg = Math.abs(inValues[i] - average);
			distance2fromAvg[i] = distanceToAvg * distanceToAvg;

			// Sum these values
			distance2fromAvgSum += distance2fromAvg[i];
		}

		// Divide by the number of data points and take the square root.
		float returnVal = (float) Math.sqrt(distance2fromAvgSum / inValues.length);
		return returnVal;
	}

	public void CalculateOBV() {
		obv_high = -1E+009F;
		obv_low = 1E+009F;
		obv[0] = 0.0F;
		for (int i = 1; i < super.totalNumDataPoints; i++) {
			if (super.close[i] > super.close[i - 1])
				obv[i] = obv[i - 1] + super.volume[i];
			else if (super.close[i] < super.close[i - 1])
				obv[i] = obv[i - 1] - super.volume[i];
			else
				obv[i] = obv[i - 1];
			obv_ma[i] = obv[i];
		}
	}

	public void CalculateCCI() {
		cci_high = -1000F;
		cci_low = 1000F;
		if (cci_period >= 1 && cci_period < super.totalNumDataPoints) {
			for (int i = cci_period - 1; i < super.totalNumDataPoints; i++) {
				float f = (super.high[i] + super.low[i] + super.close[i]) / 3F;
				float f1 = 0.0F;
				for (int j = (i - cci_period) + 1; j <= i; j++)
					f1 += (super.high[j] + super.low[j] + super.close[j]) / 3F;

				f1 /= cci_period;
				float f2 = 0.0F;
				for (int k = (i - cci_period) + 1; k <= i; k++)
					f2 = Math.abs((super.high[k] + super.low[k] + super.close[k]) / 3F - f1) + f2;

				f2 /= cci_period;
				cci[i] = (float) ((double) (f - f1) / (0.014999999999999999D * (double) f2));
			}
		}
	}

	public void CalculatePercentR() {
		for (int i = percentR_period - 1; i < super.totalNumDataPoints; i++) {
			float f = 10000F;
			float f1 = 0.0F;
			for (int j = 0; j < percentR_period; j++) {
				if (f > super.low[i - j])
					f = super.low[i - j];
				if (f1 < super.high[i - j])
					f1 = super.high[i - j];
			}

			if (f != f1)
				percentR[i] = (float) ((100D * (double) (f1 - super.close[i])) / (double) (f1 - f));
			else
				percentR[i] = 0.0F;
		}
	}

	public void CalculateSar() {
		boolean flag = true;
		int k2 = 0;
		float f = 0.0F;
		boolean flag1 = false;
		boolean flag3 = false;
		boolean flag4 = false;
		boolean flag5 = true;
		for (int i = 0; i < GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE; i++)
			sar[i] = 0.0F;

		for (int j = 3; j < 15; j++) {
			if (super.high[j] > super.high[j - 1] && super.high[j] > super.high[j - 2] && super.high[j] > super.high[j - 3] && super.high[j] > super.high[j + 1] && super.high[j] > super.high[j + 2]
					&& super.high[j] > super.high[j + 3]) {
				k2 = j + 1;
				flag = false;
				f = super.high[j];
				break;
			}
			if (super.low[j] < super.low[j - 1] && super.low[j] < super.low[j - 2] && super.low[j] < super.low[j - 3] && super.low[j] > super.low[j + 1] && super.low[j] > super.low[j + 2]
					&& super.low[j] > super.low[j + 3]) {
				k2 = j + 1;
				flag = true;
				f = super.low[j];
				break;
			}
			if (j != 14)
				continue;
			float f1 = (super.close[j - 3] + super.close[j - 2] + super.close[j - 1] + super.close[j]) / 4F;
			if (super.close[j] > f1) {
				k2 = 15;
				flag = true;
				f = super.low[j];
			} else {
				k2 = 15;
				flag = false;
				f = super.high[j];
			}
			break;
		}

		sar[k2] = f;
		float f2 = 0.01F;
		int j2 = k2 + 1;
		for (int k = j2; k < super.totalNumDataPoints; k++)
			if (k == k2) {
				int j3 = k - 10;
				if (j3 <= 0)
					j3 = 1;
				if (flag) {
					for (int j1 = k - 1; j1 >= j3; j1--) {
						if (super.low[j1] <= super.low[j1 - 1] && super.low[j1] <= super.low[j1 + 1]) {
							sar[k] = super.low[j1];
							break;
						}
						sar[k] = super.low[k - 3];
					}
				} else {
					for (int k1 = k - 1; k1 >= j3; k1--) {
						if (super.high[k1] >= super.high[k1 - 1] && super.high[k1] >= super.high[k1 + 1]) {
							sar[k] = super.high[k1];
							break;
						}
						sar[k] = super.high[k - 3];
					}
				}
			} else {
				if (flag)
					sar[k] = sar[k - 1] + f2 * (super.high[k - 1] - sar[k - 1]);
				else
					sar[k] = sar[k - 1] + f2 * (super.low[k - 1] - sar[k - 1]);
				if (flag && sar[k] > super.low[k - 1])
					sar[k] = super.low[k - 1];
				if (flag && sar[k] > super.low[k - 2])
					sar[k] = super.low[k - 2];
				if (!flag && sar[k] < super.low[k - 1])
					sar[k] = super.high[k - 1];
				if (!flag && sar[k] < super.low[k - 2])
					sar[k] = super.high[k - 2];
				if (flag) {
					int l2 = 0;
					for (int l = k2; l < k; l++) {
						boolean flag6 = true;
						for (int l1 = l; l1 >= k2; l1--)
							if (super.high[l1] > super.high[l])
								flag6 = false;

						if (flag6)
							l2++;
					}

					f2 = 0.02F * (float) (l2 + 1);
				} else {
					int i3 = 0;
					for (int i1 = k2; i1 < k; i1++) {
						boolean flag7 = true;
						for (int i2 = i1; i2 >= k2; i2--)
							if (super.low[i2] < super.low[i1])
								flag7 = false;

						if (flag7)
							i3++;
					}

					f2 = 0.02F * (float) (i3 + 1);
				}
				if ((double) f2 > 0.20000000000000001D)
					f2 = 0.2F;
				boolean flag2;
				if (flag)
					flag2 = true;
				else
					flag2 = false;
				if (flag2) {
					if (sar[k] <= super.high[k] && sar[k] >= super.low[k]) {
						flag = false;
						k2 = k + 1;
						f2 = 0.02F;
					}
				} else if (sar[k] <= super.high[k] && sar[k] >= super.low[k]) {
					flag = true;
					k2 = k + 1;
					f2 = 0.02F;
				}
			}
	}

	public void CalculateFW() {
		CalculatePercentageReversal(fw, fw_reversal_percent);
	}

	public void CalculateFib(int i, int j) {
		for (int k = 0; k < super.totalNumDataPoints; k++) {
			fib_reversal_1[k] = -1F;
			fib_r50[k] = -1F;
			fib_r382[k] = -1F;
			fib_r618[k] = -1F;
			fib_reversal_2[k] = -1F;
			fib_reversal_Z[k] = -1F;
			fib_reversal_points[k] = -1F;
		}

		if (i < 0)
			return;
		if (j < 0)
			return;
		fib_reversal_points[i] = super.low[i];
		fib_reversal_points[j] = super.high[j];
		float f = super.high[j];
		float f1 = super.low[i];
		float f2 = f - f1;
		int l = Math.min(i, j);
		float f3 = f2 / (float) (super.totalNumDataPoints - l - 1);
		for (int i1 = l; i1 < super.totalNumDataPoints; i1++) {
			fib_reversal_1[i1] = f1;
			fib_r50[i1] = f1 + f2 * 0.5F;
			fib_r382[i1] = f1 + f2 * 0.382F;
			fib_r618[i1] = f1 + f2 * 0.618F;
			fib_reversal_2[i1] = f;
			fib_reversal_Z[i1] = f1 + (float) (i1 - l) * f3;
			fib_reversal_points[i1] = -1F;
		}

		fib_reversal_points[i] = super.low[i];
		fib_reversal_points[j] = super.high[j];
	}

	public void CalculateFibOverlay() {
		for (int i = 0; i < super.totalNumDataPoints; i++) {
			fibo_reversal_1[i] = -1F;
			fibo_r50[i] = -1F;
			fibo_r382[i] = -1F;
			fibo_r618[i] = -1F;
			fibo_reversal_2[i] = -1F;
			fibo_reversal_Z[i] = -1F;
			fibo_reversal_points[i] = -1F;
		}

		CalculatePercentageReversal(fibo_reversal_points, fib_reversal_percent);
		int j = -1;
		int k = -1;
		for (int l = 0; l < super.totalNumDataPoints; l++)
			if (fibo_reversal_points[l] > 0.0F) {
				k = j;
				j = l;
				fibo_reversal_points[l] = -1F;
			}

		if (k < 0)
			return;

		fibo_reversal_points[k] = super.close[k];
		fibo_reversal_points[j] = super.close[j];

		float f = super.close[j];
		float f1 = super.close[k];
		if (f1 > f) {
			float f2 = f1;
			f1 = f;
			f = f2;
		}

		float f3 = f - f1;
		int i1 = Math.min(k, j);
		float f4 = f3 / (float) (super.totalNumDataPoints - i1 - 1);
		for (int j1 = i1; j1 < super.totalNumDataPoints; j1++) {
			fibo_reversal_1[j1] = f1;
			fibo_r50[j1] = f1 + f3 * 0.5F;
			fibo_r382[j1] = f1 + f3 * 0.382F;
			fibo_r618[j1] = f1 + f3 * 0.618F;
			fibo_reversal_2[j1] = f;
			fibo_reversal_Z[j1] = f1 + (float) (j1 - i1) * f4;
			fibo_reversal_points[j1] = -1F;
		}

		fibo_reversal_points[k] = super.close[k];
		fibo_reversal_points[j] = super.close[j];
	}

	private void CalculatePercentageReversal(float af[], int i) {
		boolean flag = false;
		boolean flag1 = false;
		int j = 0;
		int k = 0;
		boolean flag2 = false;
		boolean flag3 = false;
		af[0] = -1F;
		if (super.totalNumDataPoints < 1)
			return;
		for (int j1 = 1; j1 < super.totalNumDataPoints; j1++) {
			af[j1] = -1F;
			if (!flag && !flag1) {
				if (super.close[j1] < super.close[j1 - 1]) {
					flag = true;
					j = j1;
				} else if (super.close[j1] > super.close[j1 - 1]) {
					flag1 = true;
					k = j1;
				}
			} else if (flag && super.close[j1] > super.close[j1 - 1]) {
				if (j < 0)
					j = j1 - 1;
				else if (super.close[j1 - 1] < super.close[j])
					j = j1 - 1;
				float f = ((super.close[j1] - super.close[j]) / super.close[j]) * 100F;
				if ((int) f >= i) {
					flag = false;
					flag1 = true;
					int l = j;
					af[l] = super.close[l];
					k = j1;
				}
			} else if (flag1 && super.close[j1] < super.close[j1 - 1]) {
				if (k < 0)
					k = j1 - 1;
				else if (super.close[j1 - 1] > super.close[k])
					k = j1 - 1;
				float f1 = ((super.close[k] - super.close[j1]) / super.close[k]) * 100F;
				if ((int) f1 >= i) {
					flag = true;
					flag1 = false;
					int i1 = k;
					af[i1] = super.close[i1];
					j = j1;
				}
			} else {
				boolean _tmp = flag1 && super.close[j1] >= super.close[j1 - 1];
			}
		}

		if (flag && j > 0) {
			if (super.close[super.totalNumDataPoints - 1] < super.close[j])
				j = super.totalNumDataPoints - 1;
			af[j] = super.close[j];
			return;
		}
		if (flag1 && k > 0) {
			if (super.close[super.totalNumDataPoints - 1] > super.close[k])
				k = super.totalNumDataPoints - 1;
			af[k] = super.close[k];
		}
	}

	public void CalculateTSI() {
		float af[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af1[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af2[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af3[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af4[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af5[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		for (int i = 1; i < super.totalNumDataPoints; i++) {
			af[i] = super.close[i] - super.close[i - 1];
			af3[i] = Math.abs(af[i]);
		}

		CalculateEMA(af1, af, tsi_primary_period, false, false);
		CalculateEMA(af2, af1, tsi_secondary_period, false, false);
		CalculateEMA(af4, af3, tsi_primary_period, false, false);
		CalculateEMA(af5, af4, tsi_secondary_period, false, false);
		for (int j = 0; j < super.totalNumDataPoints; j++)
			tsi[j] = af5[j] != 0.0F ? (af2[j] / af5[j]) * 100F : 0.0F;

		CalculateEMA(tsi_signal, tsi, tsi_signal_period, false, false);
		for (int k = 0; k < super.totalNumDataPoints; k++) {
			tsi_signal_diff[k] = tsi[k] - tsi_signal[k];
			tsi_arrows[k] = 3.402823E+038F;
			if (k > 0 && tsi_signal_diff[k] > 0.0F && tsi_signal_diff[k - 1] <= 0.0F) {
				tsi_arrows[k] = tsi_signal[k];
				tsi_arrows_ud[k] = 1.0F;
			} else if (k > 0 && tsi_signal_diff[k] < 0.0F && tsi_signal_diff[k - 1] >= 0.0F) {
				tsi_arrows[k] = tsi_signal[k];
				tsi_arrows_ud[k] = -1F;
			}
		}
	}

	public void adjustTSIDate() {
		adjustTSIDate(low_index, high_index);
	}

	public void adjustTSIDate(int i, int j) {
		float f = 3.402823E+038F;
		float f1 = 1.401298E-045F;
		float f2 = 3.402823E+038F;
		float f3 = 1.401298E-045F;
		for (int k = i; k < j; k++) {
			if (super.close[k] > f1)
				f1 = super.close[k];
			if (super.close[k] < f)
				f = super.close[k];
		}

		int l;
		for (l = i - 1; tsi[++l] == 0.0F && l < tsi.length;)
			;
		for (int i1 = l; i1 < j; i1++) {
			if (tsi[i1] > f3)
				f3 = tsi[i1];
			if (tsi[i1] < f2)
				f2 = tsi[i1];
		}
	}

	public void CalculateATR() {
		float af[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		af[0] = super.high[0] - super.low[0];
		atr_min = 3.402823E+038F;
		atr_max = 0.0F;
		for (int i = 1; i < GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE; i++) {
			float f1 = Math.abs(super.high[i] - super.low[i]);
			float f3 = Math.abs(super.high[i] - super.close[i - 1]);
			float f4 = Math.abs(super.low[i] - super.close[i - 1]);
			float f = Math.max(f1, f3);
			f = Math.max(f, f4);
			af[i] = f;
		}

		for (int j = 0; j < GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE; j++)
			atr[j] = 0.0F;

		float f2 = 0.0F;
		for (int k = 0; k < atr_period; k++)
			f2 += af[k];

		f2 /= atr_period;
		atr[atr_period - 1] = f2;
		for (int l = atr_period; l < GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE; l++) {
			f2 = ((float) (atr_period - 1) * f2 + af[l]) / (float) atr_period;
			atr[l] = f2;
		}

		for (int i1 = 0; i1 < GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE; i1++)
			if (atr[i1] > 0.0F) {
				if (atr[i1] < atr_min)
					atr_min = atr[i1];
				if (atr[i1] > atr_max)
					atr_max = atr[i1];
			}

		if ((double) atr_min < 0.10000000000000001D)
			atr_min = 0.0F;
	}

	public void CalculateUDIO() {
		boolean flag = false;
		if (flag) {
			udio[0] = 0;
			for (int i = 1; i < GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE; i++)
				if (super.close[i] > super.close[i - 1])
					udio[i] = 1;
				else if (super.close[i] < super.close[i - 1])
					udio[i] = 2;
				else if (super.high[i] < super.high[i - 1] && super.low[i] > super.low[i - 1])
					udio[i] = 3;
				else if (super.high[i] > super.high[i - 1] && super.low[i] < super.low[i - 1])
					udio[i] = 4;
				else
					udio[i] = 0;
			return;
		}
		udio[0] = 0;
		for (int j = 1; j < GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE; j++)
			if (super.high[j] > super.high[j - 1] && super.low[j] > super.low[j - 1])
				udio[j] = 1;
			else if (super.high[j] < super.high[j - 1] && super.low[j] < super.low[j - 1])
				udio[j] = 2;
			else if (super.high[j] < super.high[j - 1] && super.low[j] > super.low[j - 1])
				udio[j] = 3;
			else if (super.high[j] > super.high[j - 1] && super.low[j] < super.low[j - 1])
				udio[j] = 4;
			else
				udio[j] = 0;
	}

	public void CalculateOGSP() {
		int i = 0;
		int j = 1;
		byte byte0 = 2;
		int k = i;
		int l = 0;
		int i1 = 0;
		og_sp[0] = 0.0F;
		og_sp[1] = 0.0F;
		og_sp_int[0] = 0.0F;
		og_sp_int[1] = 0.0F;
		og_sp_st[0] = 0.0F;
		og_sp_st[1] = 0.0F;
		for (int j1 = 2; j1 < GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE; j1++) {
			og_sp[j1] = 0.0F;
			og_sp_int[j1] = 0.0F;
			if (k == i) {
				og_sp_st[j1] = 0.0F;
				if (testInitialUpSwing(j1)) {
					og_sp[j1 - 1] = super.low[j1 - 1];
					i1 = j1 - 1;
					l = j1;
					k = byte0;
				} else if (testInitialDownSwing(j1)) {
					og_sp[j1 - 1] = -1F * super.high[j1 - 1];
					l = j1 - 1;
					i1 = j1;
					k = j;
				}
			} else if (k == j) {
				og_sp_st[j1] = super.high[j1 - 1];
				if (super.low[j1] < super.low[i1])
					i1 = j1;
				if (testUpSwing(j1)) {
					if (l == i1)
						og_sp_int[l] = og_sp[l];
					og_sp[i1] = super.low[i1];
					l = j1;
					k = byte0;
				}
			} else if (k == byte0) {
				og_sp_st[j1] = super.low[j1 - 1];
				if (testDownSwing(j1)) {
					if (l == i1)
						og_sp_int[l] = og_sp[l];
					og_sp[l] = -1F * super.high[l];
					i1 = j1;
					k = j;
				}
				if (super.high[j1] > super.high[l])
					l = j1;
			}
		}
	}

	public void CalculateOGSPClassic() {
		int i = 0;
		int j = 1;
		byte byte0 = 2;
		int k = i;
		int l = 0;
		int i1 = 0;
		og_sp[0] = 0.0F;
		og_sp[1] = 0.0F;
		og_sp_int[0] = 0.0F;
		og_sp_int[1] = 0.0F;
		og_sp_st[0] = 0.0F;
		og_sp_st[1] = 0.0F;
		for (int j1 = 2; j1 < super.totalNumDataPoints; j1++) {
			og_sp[j1] = 0.0F;
			og_sp_int[j1] = 0.0F;
			if (k == i) {
				og_sp_st[j1] = 0.0F;
				if (testInitialUpSwingClassic(j1)) {
					og_sp[j1 - 1] = super.low[j1 - 1];
					i1 = j1 - 1;
					l = j1;
					k = byte0;
				} else if (testInitialDownSwingClassic(j1)) {
					og_sp[j1 - 1] = -1F * super.high[j1 - 1];
					l = j1 - 1;
					i1 = j1;
					k = j;
				}
			}
			if (k == j) {
				og_sp_st[j1] = super.high[j1 - 1];
				if (super.high[j1] < super.high[j1 - 1] && super.low[j1] < super.low[j1 - 1]) {
					if (super.low[j1] < super.low[i1])
						i1 = j1;
					continue;
				}
				if (super.high[j1] <= super.high[j1 - 1] && super.low[j1] >= super.low[j1 - 1])
					continue;
				if (super.high[j1] > super.high[j1 - 1] && super.low[j1] < super.low[j1 - 1]) {
					if (super.low[j1] < super.low[i1])
						i1 = j1;
					continue;
				}
				if (testUpSwingClassic(j1)) {
					if (l == i1)
						og_sp_int[l] = og_sp[l];
					og_sp[i1] = super.low[i1];
					l = j1;
					k = byte0;
				}
				if (k == j && super.low[j1] < super.low[i1])
					i1 = j1;
			} else if (k == byte0) {
				og_sp_st[j1] = super.low[j1 - 1];
				if (super.high[j1] > super.high[j1 - 1] && super.low[j1] > super.low[j1 - 1]) {
					if (super.high[j1] > super.high[l])
						l = j1;
					continue;
				}
				if (super.high[j1] <= super.high[j1 - 1] && super.low[j1] >= super.low[j1 - 1])
					continue;
				if (super.high[j1] > super.high[j1 - 1] && super.low[j1] < super.low[j1 - 1]) {
					if (super.high[j1] > super.high[l])
						l = j1;
					continue;
				}
				if (testDownSwingClassic(j1)) {
					if (l == i1)
						og_sp_int[l] = og_sp[l];
					og_sp[l] = -1F * super.high[l];
					i1 = j1;
					k = j;
				}
				if (k == byte0 && super.high[j1] > super.high[l])
					l = j1;
			}
			FINAL_SWING = k;
		}
	}

	private boolean testInitialUpSwing(int i) {
		if (i < 1)
			return false;
		return super.high[i] > super.high[i - 1] && super.high[i] > super.high[i - 2];
	}

	private boolean testInitialDownSwing(int i) {
		if (i < 1)
			return false;
		return super.low[i] < super.low[i - 1] && super.low[i] < super.low[i - 2];
	}

	private boolean testInitialUpSwingClassic(int i) {
		if (i < 1)
			return false;
		return super.high[i] > super.high[i - 1] && super.low[i] > super.low[i - 1];
	}

	private boolean testInitialDownSwingClassic(int i) {
		if (i < 1)
			return false;
		return super.high[i] < super.high[i - 1] && super.low[i] < super.low[i - 1];
	}

	private boolean testUpSwing(int i) {
		return testCondA1(i);
	}

	private boolean testUpSwingClassic(int i) {
		if (testCondA1Classic(i))
			return true;
		if (testCondBClassic(i))
			return true;
		else
			return testCondA2Classic(i);
	}

	private boolean testDownSwing(int i) {
		return testCondC1(i);
	}

	private boolean testDownSwingClassic(int i) {
		if (testCondC1Classic(i))
			return true;
		if (testCondDClassic(i))
			return true;
		else
			return testCondC2Classic(i);
	}

	private boolean testCondA1(int i) {
		if (i < 2)
			return false;
		return super.high[i] > super.high[i - 1] && super.high[i] > super.high[i - 2];
	}

	private boolean testCondA1Classic(int i) {
		if (i < 3)
			return false;
		return super.high[i - 2] < super.high[i - 3] && super.high[i - 1] < super.high[i - 2] && super.high[i] > super.high[i - 2];
	}

	private boolean testCondA2Classic(int i) {
		if (i < 3)
			return false;
		return super.high[i] > super.high[i - 3] && super.high[i] > super.high[i - 2] && super.high[i] > super.high[i - 1];
	}

	private boolean testCondBClassic(int i) {
		if (i < 2)
			return false;
		return super.high[i - 1] > super.high[i - 2] && super.high[i] > super.high[i - 1];
	}

	private boolean testCondC1(int i) {
		if (i < 2)
			return false;
		return super.low[i] < super.low[i - 1] && super.low[i] < super.low[i - 2];
	}

	private boolean testCondC1Classic(int i) {
		if (i < 3)
			return false;
		return super.low[i - 2] > super.low[i - 3] && super.low[i - 1] > super.low[i - 2] && super.low[i] < super.low[i - 2];
	}

	private boolean testCondC2Classic(int i) {
		if (i < 3)
			return false;
		return super.low[i] < super.low[i - 3] && super.low[i] < super.low[i - 2] && super.low[i] < super.low[i - 1];
	}

	private boolean testCondDClassic(int i) {
		if (i < 2)
			return false;
		return super.low[i - 1] < super.low[i - 2] && super.low[i] < super.low[i - 1];
	}

	public void CalculateStochastics(int numPeriodsFastK, int numPeriodsFastKSma, int numPeriodsSlowD) {
		Trace trace = ApplicationDataContext.getConfiguredTrace();

		float lowestPt = 100000F;
		float highestPt = 0.0F;

		try {
			for (int i = numPeriodsFastK - 1; i < super.totalNumDataPoints; i++) {
				lowestPt = 100000F;
				highestPt = 0.0F;

				// Looking for the lowest low and highest high here.
				for (int j = 0; j < numPeriodsFastK; j++) {
					if (lowestPt > super.low[i - j])
						lowestPt = super.low[i - j];
					if (highestPt < super.high[i - j])
						highestPt = super.high[i - j];
					// int debugMe = 1;
				}

				if (highestPt != lowestPt)
					stoch[i] = (float) ((100D * (double) (super.close[i] - lowestPt)) / (double) (highestPt - lowestPt));
				if (stoch[i] < 0.0F)
					stoch[i] = 0.0F;
			}
		} catch (Exception e) {
			System.out.println("Exception message: " + e.getMessage());
		}

		boolean debugOn = false;
		if (debugOn && super.currentGreenmanPeriod > 0)
			log.info("In CalcStoch initial stoch[" + (super.currentGreenmanPeriod - 1) + "]:  [" + stoch[super.currentGreenmanPeriod - 1] + "]   highestPt: [" + highestPt + "] lowestPt: ["
					+ lowestPt + "] last Close: [" + super.close[(super.currentGreenmanPeriod - 1)] + "] super.currentGmDays:  [" + super.currentGreenmanPeriod + "]");

		CalculateEMA(stoch_K, stoch, numPeriodsFastKSma, false, false);
		CalculateEMA(stoch_D, stoch_K, numPeriodsSlowD, false, false);

		if (debugOn && super.currentGreenmanPeriod > 0)
			log.info("In CalcStoch initial stoch[" + (super.currentGreenmanPeriod - 1) + "]:  [" + stoch[super.currentGreenmanPeriod - 1] + "] stoch_K[" + (super.currentGreenmanPeriod - 1)
					+ "]:  [" + stoch_K[super.currentGreenmanPeriod - 1] + "] stoch_D[" + (super.currentGreenmanPeriod - 1) + "]:  [" + stoch_D[super.currentGreenmanPeriod - 1] + "] for Datetime: "
					+ UTDatetime.toString(dateLDT[super.currentGreenmanPeriod - 1]));

		// KEEP THIS DEBUG OUTPUT String useDebugInfo = UTPropertyManager.getProperty("log_stock_debug");
		// WE NEED TO SEE THIS FOR ALL STOCKS, ALL THE TIME. if ( useDebugInfo.equals("1") )
		// IT ONLY PRINTS THE LAST FEW VALUES ANYWAY {
		if (debugOn) {
			log.debug(" --------------- SLOW K & SLOW D------------------  super.currentGmDays: [" + super.currentGreenmanPeriod + "]  open data array length: [" + open.length + "]");
			for (int i = super.currentGreenmanPeriod - 15; i < super.currentGreenmanPeriod; i++)
				
					log.debug("    Datetime[" + i + "]:  [" + dateString[i] + "]  Open: [" + open[i] + "]  High: [" + high[i] + "]  Low: [" + low[i] + "]  Close: [" + close[i] + "]  stochK: ["
							+ stoch_K[i] + "]  stochD: [" + stoch_D[i] + "]");
		}
		// }// Endif debug configured on
	}

	public void CalculateEMA(float outEmaList[], float inPriceList[], int numPeriodsForAvg, boolean interperiodExtra, boolean displayDebugLines) {
		Trace trace = ApplicationDataContext.getConfiguredTrace();

		if (displayDebugLines) {
			log.info(CLASSNAME + "CalculateEMA, numPeriodsForAvg: " + numPeriodsForAvg + ", interperiodExtra: " + interperiodExtra);
		}

		int startCalcIndex = 0;
		float priceSum = 0.0F;
		int endIndex = super.totalNumDataPoints;
		if (interperiodExtra) // Add an extra timeslice to the calcs, this is the interperiod timeslice.
			endIndex++;

		for (int k = 0; k <= endIndex; k++) {
			if (inPriceList[k] == 0.0F || inPriceList[k] == (0.0F / 0.0F)) {
				continue;
			}
			startCalcIndex = k;
			break;
		}

		for (int l = startCalcIndex; l <= (startCalcIndex + numPeriodsForAvg) - 1; l++) {
			if (displayDebugLines) {
				log.info(CLASSNAME + "CalculateEMA, date[" + l + "]: " + dateString[l] + ", priceSum: " + priceSum + ", inPriceList[" + l + "]: " + inPriceList[l] + ", outEmaList[" + l + "]: "
						+ outEmaList[l]);
			}
			priceSum += inPriceList[l];
		}

		if (numPeriodsForAvg != 0) {
			int outEmaListIndex = (startCalcIndex + numPeriodsForAvg) - 1;
			// For ema numPeriods that are less than super.totalNumDataPoints only. Otherwise ArrayOutOfBoundsException
			if (outEmaListIndex < super.totalNumDataPoints) {
				outEmaList[outEmaListIndex] = priceSum / (float) numPeriodsForAvg;

				if (displayDebugLines) {
					log.info(CLASSNAME + "CalculateEMA, date[" + outEmaListIndex + "]: " + dateString[outEmaListIndex] + ", priceSum: " + priceSum + ", inPriceList[" + outEmaListIndex + "]: "
							+ inPriceList[outEmaListIndex] + ", outEmaList[" + outEmaListIndex + "]: " + outEmaList[outEmaListIndex]);
				}
			}
		}

		float f1 = 2.0F / (float) (numPeriodsForAvg + 1);

		for (int emaListIndex = startCalcIndex + numPeriodsForAvg; emaListIndex < endIndex; emaListIndex++) {
			outEmaList[emaListIndex] = (inPriceList[emaListIndex] - outEmaList[emaListIndex - 1]) * f1 + outEmaList[emaListIndex - 1];

			if (displayDebugLines) {
				log.info(CLASSNAME + "CalculateEMA, date[" + emaListIndex + "]: " + dateString[emaListIndex] + ", priceSum: " + priceSum + ", outEmaList[" + emaListIndex + "]: "
						+ outEmaList[emaListIndex]);
			}
		}
	}

	public void CalculateRSI() {
		float f2 = 0.0F;
		float f4 = 0.0F;
		for (int i = rsi_period; i < super.totalNumDataPoints; i++) {
			float f = 0.0F;
			float f1 = 0.0F;
			float f3 = 0.0F;
			float f5 = 0.0F;
			for (int j = 0; j < rsi_period; j++)
				if (super.close[i - j] > super.close[i - j - 1]) {
					f++;
					f3 += super.close[i - j] - super.close[i - j - 1];
				} else if (super.close[i - j] < super.close[i - j - 1]) {
					f1++;
					f5 += super.close[i - j - 1] - super.close[i - j];
				}

			if (f5 == 0.0F)
				rsi[i] = 100000F;
			else
				rsi[i] = f3 / f5 + 1.0F;
			rsi[i] = (float) (100D - (double) (100F / rsi[i]));
		}
	}

	public void CalculateClassicRSI() {
		float f2 = 0.0F;
		float f4 = 0.0F;
		float f6 = 0.0F;
		float f8 = 0.0F;
		float af[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af1[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		for (int i = classic_rsi_period; i < super.totalNumDataPoints; i++) {
			float f = 0.0F;
			float f1 = 0.0F;
			float f3 = 0.0F;
			float f5 = 0.0F;
			float f7 = 0.0F;
			float f9 = 0.0F;
			for (int j = 0; j < classic_rsi_period; j++)
				if (super.close[i - j] > super.close[i - j - 1]) {
					f++;
					f3 += super.close[i - j] - super.close[i - j - 1];
				} else if (super.close[i - j] < super.close[i - j - 1]) {
					f1++;
					f5 += super.close[i - j - 1] - super.close[i - j];
				}

			if (i == classic_rsi_period) {
				af[i] = f3 / (float) classic_rsi_period;
				af1[i] = f5 / (float) classic_rsi_period;
				classic_rsi[i] = af[i] / af1[i];
			} else {
				float f10 = super.close[i] - super.close[i - 1];
				if (f10 > 0.0F)
					f7 = f10;
				else if (f10 < 0.0F) {
					f9 = 0.0F - f10;
				} else {
					f7 = 0.0F;
					f9 = 0.0F;
				}
				af[i] = (af[i - 1] * (float) (classic_rsi_period - 1) + f7) / (float) classic_rsi_period;
				af1[i] = (af1[i - 1] * (float) (classic_rsi_period - 1) + f9) / (float) classic_rsi_period;
				classic_rsi[i] = af[i] / af1[i];
			}
			classic_rsi[i] = (float) (100D - (double) (100F / (1.0F + classic_rsi[i])));
		}
	}

	public void CalculateDMI() {
		float af[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af1[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af2[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af3[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af4[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af5[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		for (int i = 1; i < super.totalNumDataPoints; i++) {
			float f = super.high[i] - super.low[i];
			float f1 = Math.abs(super.high[i] - super.close[i - 1]);
			f = Math.max(f, f1);
			f1 = Math.abs(super.low[i] - super.close[i - 1]);
			f = Math.max(f, f1);
			af[i] = f;
			if (super.high[i] <= super.high[i - 1] && super.low[i] >= super.low[i - 1]) {
				af1[i] = 0.0F;
				af2[i] = 0.0F;
			} else if (super.high[i] <= super.high[i - 1] && super.low[i] < super.low[i - 1]) {
				af1[i] = 0.0F;
				af2[i] = super.low[i - 1] - super.low[i];
			} else if (super.high[i] > super.high[i - 1] && super.low[i] >= super.low[i - 1]) {
				af1[i] = super.high[i] - super.high[i - 1];
				af2[i] = 0.0F;
			} else if (super.high[i] > super.high[i - 1] && super.low[i] < super.low[i - 1]) {
				af1[i] = Math.abs(super.high[i] - super.high[i - 1]);
				af2[i] = Math.abs(super.low[i] - super.low[i - 1]);
				if (af1[i] > af2[i])
					af2[i] = 0.0F;
				else
					af1[i] = 0.0F;
			}
		}

		CalculateEMA(af3, af, dmi_period, false, false);
		CalculateEMA(af4, af1, dmi_period, false, false);
		CalculateEMA(af5, af2, dmi_period, false, false);
		for (int j = dmi_period; j < super.totalNumDataPoints; j++) {
			if (af3[j] != 0.0F)
				pdi[j] = (af4[j] / af3[j]) * 100F;
			if (af3[j] != 0.0F)
				mdi[j] = (af5[j] / af3[j]) * 100F;
			if (pdi[j] + mdi[j] != 0.0F)
				dx[j] = (Math.abs(pdi[j] - mdi[j]) / (pdi[j] + mdi[j])) * 100F;
		}

		CalculateEMA(adx, dx, dmi_period, false, false);
	}

	public void CalculateDMIT() {
		float af[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af1[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af2[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		for (int i = 1; i < super.totalNumDataPoints; i++) {
			float f = super.high[i] - super.low[i];
			float f2 = Math.abs(super.high[i] - super.close[i - 1]);
			f = Math.max(f, f2);
			f2 = Math.abs(super.low[i] - super.close[i - 1]);
			f = Math.max(f, f2);
			af[i] = f;
			if (super.high[i] <= super.high[i - 1] && super.low[i] >= super.low[i - 1]) {
				af1[i] = 0.0F;
				af2[i] = 0.0F;
			} else if (super.high[i] <= super.high[i - 1] && super.low[i] < super.low[i - 1]) {
				af1[i] = 0.0F;
				af2[i] = super.low[i - 1] - super.low[i];
			} else if (super.high[i] > super.high[i - 1] && super.low[i] >= super.low[i - 1]) {
				af1[i] = super.high[i] - super.high[i - 1];
				af2[i] = 0.0F;
			} else if (super.high[i] > super.high[i - 1] && super.low[i] < super.low[i - 1]) {
				af1[i] = Math.abs(super.high[i] - super.high[i - 1]);
				af2[i] = Math.abs(super.low[i] - super.low[i - 1]);
				if (af1[i] > af2[i])
					af2[i] = 0.0F;
				else
					af1[i] = 0.0F;
			}
		}

		float f1 = 0.0F;
		float f3 = 0.0F;
		float f4 = 0.0F;
		for (int j = 0; j < dmi_period; j++) {
			f1 += af1[j];
			f3 += af2[j];
			f4 += af[j];
			pdit[j] = 0.0F;
			mdit[j] = 0.0F;
		}

		for (int k = dmi_period; k < super.totalNumDataPoints; k++) {
			f1 = (f1 - f1 / (float) dmi_period) + af1[k];
			f3 = (f3 - f3 / (float) dmi_period) + af2[k];
			f4 = (f4 - f4 / (float) dmi_period) + af[k];
			pdit[k] = (f1 / f4) * 100F;
			mdit[k] = (f3 / f4) * 100F;
		}

		for (int l = dmi_period; l < super.totalNumDataPoints; l++)
			if (pdit[l] + mdit[l] != 0.0F)
				dxt[l] = (Math.abs(pdit[l] - mdit[l]) / (pdit[l] + mdit[l])) * 100F;

		float f5 = 0.0F;
		for (int i1 = dmi_period; i1 < dmi_period + dmi_period; i1++) {
			f5 += dxt[i1];
			adxt[i1] = 0.0F;
		}

		f5 /= dmi_period;
		for (int j1 = dmi_period + dmi_period; j1 < super.totalNumDataPoints; j1++) {
			f5 = (f5 * (float) (dmi_period - 1) + dxt[j1]) / (float) dmi_period;
			adxt[j1] = f5;
		}
	}

	public void CalculateMomentum() {
		float af[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		for (int i = mt_period; i < super.totalNumDataPoints; i++)
			af[i] = super.close[i] - super.close[i - mt_period];

		CalculateEMA(mt, af, 5, false, false);
	}

	public void CalculateVolatility(String chartTimeframeString) {
		float af[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		if (voli_period >= 1) {
			for (int i = 0; i < super.totalNumDataPoints; i++)
				if (super.close[i] > 0.0F)
					af[i] = (float) Math.log(super.close[i]);
				else
					af[i] = 0.0F;

			for (int j = voli_period - 1; j < super.totalNumDataPoints; j++) {
				float f = 0.0F;
				volatility[j] = 0.0F;
				for (int l = 0; l < voli_period; l++)
					f += af[j - l];

				if (voli_period != 0)
					f /= voli_period;
				for (int i1 = 0; i1 < voli_period; i1++)
					volatility[j] += (af[j - i1] - f) * (af[j - i1] - f);

				if (voli_period != 0) {
					if (chartTimeframeString.equals("daily"))
						volatility[j] = (float) (Math.sqrt((volatility[j] * 260F) / (float) voli_period) * 100D);
					if (chartTimeframeString.equals("weekly"))
						volatility[j] = (float) (Math.sqrt((volatility[j] * 52F) / (float) voli_period) * 100D);
					if (chartTimeframeString.equals("monthly"))
						volatility[j] = (float) (Math.sqrt((volatility[j] * 12F) / (float) voli_period) * 100D);
				}
			}

		}

		voli_high = 0.0F;
		voli_low = 100F;
		for (int k = voli_period; k < super.totalNumDataPoints; k++) {
			if (volatility[k] > voli_high)
				voli_high = volatility[k];
			if (volatility[k] < voli_low)
				voli_low = volatility[k];
		}
	}

	// This is used by the Momentum indicator
	public void CalculateMACD(float[] paramArrayOffloat1, float[] paramArrayOffloat2, int paramInt1, int paramInt2) {
		float[] arrayOfFloat1 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float[] arrayOfFloat2 = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];

		CalculateEMA(arrayOfFloat1, paramArrayOffloat1, paramInt1, false, false);
		CalculateEMA(arrayOfFloat2, paramArrayOffloat1, paramInt2, false, false);

		if (paramInt2 > paramInt1) {
			for (int j = paramInt2 - 1; j < this.totalNumDataPoints; j++)
				paramArrayOffloat2[j] = arrayOfFloat1[j] - arrayOfFloat2[j];
			return;
		}
		for (int i = paramInt1 - 1; i < this.totalNumDataPoints; i++) {
			paramArrayOffloat2[i] = arrayOfFloat2[i] - arrayOfFloat1[i];
		}
	}

	public void CalculateMACD(int macDnumPeriodsSmall, int macDnumPeriodsBig, int macdSignalEmaPeriod, boolean displayDataPoints) {
		String methodname = "CalculateMACD";
		Trace trace = ApplicationDataContext.getConfiguredTrace();

		
			log.info(
					CLASSNAME + "." + methodname + ", macDnumPeriodsSmall: " + macDnumPeriodsSmall + ", macDnumPeriodsBig: " + macDnumPeriodsBig + ", macdSignalEmaPeriod: " + macdSignalEmaPeriod);

		CalculateEMA(macdFastEma, super.close, macDnumPeriodsSmall, false, displayDataPoints);
		CalculateEMA(macdSlowEma, super.close, macDnumPeriodsBig, false, displayDataPoints);

		try {
			if (macDnumPeriodsSmall < macDnumPeriodsBig) {
				for (int i = macDnumPeriodsBig - 1; i < super.totalNumDataPoints; i++) {
					macd[i] = macdFastEma[i] - macdSlowEma[i];
					if (i > macDnumPeriodsBig - 1) {
						macd_slope[i] = macd[i] - macd[i - 1];
						if (i > macDnumPeriodsBig)
							if (macd_slope[i - 1] < 0.0F && macd_slope[i] >= 0.0F)
								macd_slope_arrows[i] = super.close[i];
							else if (macd_slope[i - 1] > 0.0F && macd_slope[i] <= 0.0F)
								macd_slope_arrows[i] = -1F * super.close[i];
							else
								macd_slope_arrows[i] = 3.402823E+038F;
					}
				}
			} else {
				for (int j = macDnumPeriodsSmall - 1; j < super.totalNumDataPoints; j++) {
					macd[j] = macdSlowEma[j] - macdFastEma[j];
					if (j > macDnumPeriodsSmall - 1) {
						macd_slope[j] = macd[j] - macd[j - 1];
						if (j > macDnumPeriodsSmall)
							if (macd_slope[j - 1] < 0.0F && macd_slope[j] >= 0.0F)
								macd_slope_arrows[j] = super.close[j];
							else if (macd_slope[j - 1] > 0.0F && macd_slope[j] <= 0.0F)
								macd_slope_arrows[j] = -1F * super.close[j];
							else
								macd_slope_arrows[j] = 3.402823E+038F;
					}
				}
			}

			CalculateEMA(macdSignal, macd, macdSignalEmaPeriod, false, false);
			for (int k = 1; k < super.totalNumDataPoints; k++) {
				macd_slope[k] = macdSignal[k] - macdSignal[k - 1];
				if (k > 2)
					if (macd_slope[k - 1] < 0.0F && macd_slope[k] >= 0.0F) {
						macd_slope_arrows[k] = macd_slope[k];
						macd_slope_arrows_ud[k] = 1.0F;
					} else if (macd_slope[k - 1] > 0.0F && macd_slope[k] <= 0.0F) {
						macd_slope_arrows[k] = macd_slope[k];
						macd_slope_arrows_ud[k] = -1F;
					} else {
						macd_slope_arrows[k] = 3.402823E+038F;
					}
			}

			// Calculate the MacD Histogram.
			for (int i = macDnumPeriodsBig - 1; i < super.totalNumDataPoints; i++) {
				macdHistogram[i] = macd[i] - macdSignal[i];
			}

		} catch (Exception ex) {
			log.info("\t\t" + CLASSNAME + "." + methodname + ", currentGreenmanPeriod: " + getCurrentGreenmanPeriod() + ", totalNumDataPoints: " + getTotalNumDataPoints());

			displayRecentStockdataDataOnTraceModel(trace);

			log.info("Exception CalculateMACD, message: " + ex.getMessage());
			throw ex;
		}

		if (trace.isLevelAnalysis()) {
			for (int index = 0; index < GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE; index++) {
				log.info(CLASSNAME + "." + methodname + ", SMA12[" + index + "]: " + macdFastEma[index] + ", SMA26[" + index + "]: " + macdSlowEma[index] + ", macD[" + index + "]: "
						+ macd[index] + ", macdSignal[" + index + "]: " + macdSignal[index] + ", macdHistogram[" + index + "]: " + macdHistogram[index]);
			}
		}

		if (displayDataPoints) {
			DecimalFormat df = new DecimalFormat("#.############");
			if (trace.isLevelAnalysis()) {
				log.info("  CALCULATE MACD");
				for (int i = 0; i < GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE; i++) {
					log.info("\tCalculateEMA: macd[" + i + "]: " + df.format(macd[i]) + ", close[" + i + "]: " + df.format(super.close[i]) + ", emaSmall[" + i + "]: " + macdFastEma[i]
							+ ", emaBig[" + i + "]: " + macdSlowEma[i] + ", date[" + i + "]: " + UTDatetime.toString(super.dateLDT[i]));
				}
			}
		}
	}

	// The original method used by the charts ONLY!!
	public void CalculateMACD() {
		CalculateEMA(this.macdFastEma, this.close, this.macdFastEmaPeriod, false, false);
		CalculateEMA(this.macdSlowEma, this.close, this.macdSlowEmaPeriod, false, false);

		if (this.macdFastEmaPeriod < this.macdSlowEmaPeriod) {
			for (int i = this.macdSlowEmaPeriod - 1; i < this.totalNumDataPoints; i++) {
				this.macd[i] = this.macdFastEma[i] - this.macdSlowEma[i];

				if (i > this.macdSlowEmaPeriod - 1) {
					this.macd_slope[i] = this.macd[i] - this.macd[i - 1];
					if (i > this.macdSlowEmaPeriod) {
						if (this.macd_slope[i - 1] < 0.0F && this.macd_slope[i] >= 0.0F) {
							this.macd_slope_arrows[i] = this.close[i];
						} else if (this.macd_slope[i - 1] > 0.0F && this.macd_slope[i] <= 0.0F) {
							this.macd_slope_arrows[i] = -1.0F * this.close[i];
						} else {
							this.macd_slope_arrows[i] = Float.MAX_VALUE;
						}
					}
				}
			}
		} else {
			for (int i = this.macdFastEmaPeriod - 1; i < this.totalNumDataPoints; i++) {
				this.macd[i] = this.macdSlowEma[i] - this.macdFastEma[i];

				if (i > this.macdFastEmaPeriod - 1) {
					this.macd_slope[i] = this.macd[i] - this.macd[i - 1];
					if (i > this.macdFastEmaPeriod) {
						if (this.macd_slope[i - 1] < 0.0F && this.macd_slope[i] >= 0.0F) {
							this.macd_slope_arrows[i] = this.close[i];
						} else if (this.macd_slope[i - 1] > 0.0F && this.macd_slope[i] <= 0.0F) {
							this.macd_slope_arrows[i] = -1.0F * this.close[i];
						} else {
							this.macd_slope_arrows[i] = Float.MAX_VALUE;
						}
					}
				}
			}
		}

		CalculateEMA(this.macdSignal, this.macd, this.macdSignalEmaPeriod, false, false);

		for (byte b = 1; b < this.totalNumDataPoints; b++) {
			this.macd_slope[b] = this.macdSignal[b] - this.macdSignal[b - 1];
			if (b > 2) {
				if (this.macd_slope[b - 1] < 0.0F && this.macd_slope[b] >= 0.0F) {
					this.macd_slope_arrows[b] = this.macd_slope[b];
					this.macd_slope_arrows_ud[b] = 1.0F;
				} else if (this.macd_slope[b - 1] > 0.0F && this.macd_slope[b] <= 0.0F) {
					this.macd_slope_arrows[b] = this.macd_slope[b];
					this.macd_slope_arrows_ud[b] = -1.0F;
				} else {
					this.macd_slope_arrows[b] = Float.MAX_VALUE;
				}
			}
		}
	}

	public void CalculateUltimateOscillator() {
		for (int i = 21; i < super.totalNumDataPoints; i++) {
			float f3 = 0.0F;
			float f4 = 0.0F;
			for (int j = 0; j < 7; j++) {
				if (super.close[i - j - 1] < super.low[i - j])
					f3 = (f3 + super.close[i - j]) - super.close[i - j - 1];
				else
					f3 = (f3 + super.close[i - j]) - super.low[i - j];
				float f = super.high[i - j] - super.low[i - j] <= super.high[i - j] - super.close[i - j - 1] ? super.high[i - j] - super.close[i - j - 1] : super.high[i - j] - super.low[i - j];
				float f9 = f <= super.close[i - j - 1] - super.low[i - j] ? super.close[i - j - 1] - super.low[i - j] : f;
				f4 += f9;
			}

			float f5 = f3;
			float f6 = f4;
			for (int k = 7; k < 14; k++) {
				if (super.close[i - k - 1] < super.low[i - k])
					f5 = (f5 + super.close[i - k]) - super.close[i - k - 1];
				else
					f5 = (f5 + super.close[i - k]) - super.low[i - k];
				float f1 = super.high[i - k] - super.low[i - k] <= super.high[i - k] - super.close[i - k - 1] ? super.high[i - k] - super.close[i - k - 1] : super.high[i - k] - super.low[i - k];
				float f10 = f1 <= super.close[i - k - 1] - super.low[i - k] ? super.close[i - k - 1] - super.low[i - k] : f1;
				f6 += f10;
			}

			float f7 = f5;
			float f8 = f6;
			for (int l = 14; l < 21; l++) {
				if (super.close[i - l - 1] < super.low[i - l])
					f7 = (f7 + super.close[i - l]) - super.close[i - l - 1];
				else
					f7 = (f7 + super.close[i - l]) - super.low[i - l];
				float f2 = super.high[i - l] - super.low[i - l] <= super.high[i - l] - super.close[i - l - 1] ? super.high[i - l] - super.close[i - l - 1] : super.high[i - l] - super.low[i - l];
				float f11 = f2 <= super.close[i - l - 1] - super.low[i - l] ? super.close[i - l - 1] - super.low[i - l] : f2;
				f8 += f11;
			}

			if (f4 != 0.0F && f6 != 0.0F && f8 != 0.0F)
				uo[i] = (float) ((100D * (double) ((4F * f3) / f4 + (2.0F * f5) / f6 + f7 / f8)) / 7D);
		}
	}

	public void CalculateForceIndex() {
		for (int i = 1; i < super.totalNumDataPoints; i++)
			forceIndex[i] = super.volume[i] * (super.close[i] - super.close[i - 1]);

		CalculateSMA(forceIndexSMA1, forceIndex, fiSMA1_period);
		CalculateSMA(forceIndexSMA2, forceIndex, fiSMA2_period);
	}

	public void CalculateVolAvg() {
		CalculateSMA(volAvg, super.volume, volAvg_period);
	}

	public void CalculateAccelerationBands(float af[], float af1[], float af2[], int i) {
		CalculateSMA(af2, super.close, i);
		float af3[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af4[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		for (int j = 0; j < super.totalNumDataPoints; j++) {
			af3[j] = (float) ((double) super.high[j] * (1.0D + 2D * ((double) (((super.high[j] - super.low[j]) / ((super.high[j] + super.low[j]) / 2.0F)) * 1000F) * 0.001D)));
			af4[j] = (float) ((double) super.low[j] * (1.0D - 2D * ((double) (((super.high[j] - super.low[j]) / ((super.high[j] + super.low[j]) / 2.0F)) * 1000F) * 0.001D)));
		}

		CalculateSMA(af, af3, i);
		CalculateSMA(af1, af4, i);
	}

	public void CalculateMomentumDivergence(float af[], float af1[], int i) {
		float af2[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af3[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af4[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		CalculateMACD(super.close, af2, FastMA_MD, SlowMA_MD);
		for (int j = i - 1; j < super.totalNumDataPoints; j++) {
			float f = Highest(af2, j, i) - Lowest(af2, j, i);
			af3[j] = f == 0.0F ? 50F : f;
			float f1 = Highest(super.close, j, i) - Lowest(super.close, j, i);
			af4[j] = f1 == 0.0F ? 50F : f1;
			af[j] = 100F * ((super.close[j] - Lowest(super.close, j, i)) / af4[j]);
			af1[j] = 100F * ((af2[j] - Lowest(af2, j, i)) / af3[j]);
		}
	}

	public float Highest(float af[], int i, int j) {
		float f = (0.0F / 0.0F);
		if (i >= af.length)
			return f;
		if ((i - j) + 1 < 0)
			return f;
		f = af[i];
		for (int k = (i - j) + 1; k <= i; k++)
			f = af[k] <= f ? f : af[k];

		return f;
	}

	public float Lowest(float af[], int i, int j) {
		float f = (0.0F / 0.0F);
		if (i >= af.length)
			return f;
		if ((i - j) + 1 < 0)
			return f;
		f = af[i];
		for (int k = (i - j) + 1; k <= i; k++)
			f = af[k] >= f ? f : af[k];

		return f;
	}

	public void CalculateTrendInitiation(float af[], float af1[], float af2[], float af3[], int i) {
		float af4[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af5[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af6[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af7[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		float af8[] = new float[GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE];
		for (int j = i - 1; j < super.totalNumDataPoints; j++) {
			float f = super.high[j] - Lowest(super.high, j, i);
			af4[j] = f <= 0.0F ? 0.0F : f;
			float f1 = super.low[j] - Highest(super.low, j, i);
			af5[j] = f1 >= 0.0F ? 0.0F : f1;
		}

		CalculateSMA(af6, af4, 5);
		CalculateSMA(af7, af5, 5);
		for (int k = (i + 5) - 1; k < super.totalNumDataPoints; k++)
			af[k] = (float) ((double) (af6[k] + af7[k]) * Math.sqrt(10D));

		CalculateSMA(af8, af, 2);
		for (int l = 0; l < GmConstantsDatafeed.MAX_RAWDATA_ARRAY_SIZE; l++) {
			af1[l] = (0.0F / 0.0F);
			af2[l] = (0.0F / 0.0F);
			af3[l] = (0.0F / 0.0F);
		}

		for (int i1 = ((i + 5) - 1) + 1; i1 < super.totalNumDataPoints; i1++) {
			if (af[i1] < 0.0F) {
				af1[i1] = -10F;
				if (af[i1] < af8[i1 - 1])
					af3[i1] = 0.0F;
			}
			if (af[i1] > 0.0F) {
				af2[i1] = 10F;
				if (af[i1] > af8[i1 - 1])
					af3[i1] = 0.0F;
			}
		}
	}

	public void CalculateWMA() {
		if (wma_period <= 0)
			return;
		for (int i = wma_period - 1; i < super.totalNumDataPoints; i++) {
			float f = 0.0F;
			float f1 = 0.0F;
			int j = 0;
			int k = i;
			for (int l = wma_period; k >= (i + 1) - wma_period; l--) {
				f += super.low[k] * (float) l;
				f1 += super.high[k] * (float) l;
				j += l;
				k--;
			}

			wma_low[i] = f / (float) j;
			wma_high[i] = f1 / (float) j;
		}
	}

	public void CalculatePF() {
		float f = 1.0F;
		float f1 = 1.0F;
		int k3 = 1;
		int l3 = 0;
		pf_high = 0.0F;
		pf_low = 1000000F;
		for (int i = 0; i < super.totalNumDataPoints; i++) {
			if (super.high[i] > pf_high)
				pf_high = super.high[i];
			if (super.low[i] < pf_low)
				pf_low = super.low[i];
		}

		for (int j = 0; j < 10; j++) {
			if (pf_low < pf_price[j] || pf_low >= pf_price[j + 1])
				continue;
			f1 = pf_price[j];
			f = pf_box[j];
			k3 = j;
			break;
		}

		pf_low = f1 + (float) (int) ((pf_low - f1) / f) * f;
		if (pf_low < 0.0625F)
			pf_low = 0.0625F;
		l3 = 0;
		pf_grid[l3] = pf_low;
		for (int k = 1; k < 200; k++) {
			if (pf_grid[k - 1] >= pf_price[k3 + 1])
				k3++;
			pf_grid[k] = pf_grid[k - 1] + pf_box[k3];
			if (pf_grid[k] < pf_high)
				continue;
			pf_grid_height = k + 1;
			break;
		}

		int i4 = getHighBoxNumber(pf_grid, super.high[0], pf_grid_height);
		boolean flag = true;
		int j4 = 0;
		char c = '0';
		byte byte0 = 48;
		c = getMonth(super.dateEpochSeconds[0]);
		for (int l = 0; l < 300; l++) {
			for (int l1 = 0; l1 < 200; l1++)
				pf[l][l1] = '0';
		}

		for (int i1 = 1; i1 < super.totalNumDataPoints; i1++) {
			int i3 = getHighBoxNumber(pf_grid, super.high[i1], pf_grid_height);
			int j3 = getLowBoxNumber(pf_grid, super.low[i1], pf_grid_height);
			char c1 = getMonth(super.dateEpochSeconds[i1]);
			if (flag) {
				if (i3 - i4 >= 1) {
					for (int i2 = i4 + 1; i2 <= i3; i2++)
						if (c1 == c) {
							pf[j4][i2] = 'X';
						} else {
							pf[j4][i2] = c1;
							c = c1;
						}

					i4 = i3;
				} else if (i4 - j3 >= 3) {
					j4++;
					for (int j2 = j3; j2 < i4; j2++)
						pf[j4][j2] = 'O';

					if (c1 != c) {
						pf[j4][j3] = c1;
						c = c1;
					}
					i4 = j3;
					flag = false;
				}
			} else if (i4 - j3 >= 1) {
				for (int k2 = j3; k2 < i4; k2++)
					if (c1 == c) {
						pf[j4][k2] = 'O';
					} else {
						pf[j4][k2] = c1;
						c = c1;
					}

				i4 = j3;
			} else if (i3 - i4 >= 3) {
				j4++;
				for (int l2 = i4 + 1; l2 <= i3; l2++)
					pf[j4][l2] = 'X';

				if (c1 != c) {
					pf[j4][i3] = c1;
					c = c1;
				}
				i4 = i3;
				flag = true;
			}
		}

		pf_grid_width = j4 + 1;
		l3 = 0;
		for (int j1 = 0; j1 < pf_grid_height; j1++)
			if (pf[0][j1] != '0')
				l3++;

		if (l3 <= 3) {
			for (int k1 = 0; k1 < pf_grid_height; k1++)
				pf[0][k1] = '0';
		}
	}

	private char getMonth(int i) {
		int k = i / 100;
		int j = i / 10000;
		k -= j * 100;
		char c;
		if (k == 10)
			c = 'A';
		else if (k == 11)
			c = 'B';
		else if (k == 12)
			c = 'C';
		else
			c = (char) (48 + k);
		return c;
	}

	private int getHighBoxNumber(float af[], float f, int i) {
		for (int j = 0; j < i - 1; j++)
			if (f >= af[j] && f < af[j + 1])
				return j;

		return i - 1;
	}

	private int getLowBoxNumber(float af[], float f, int i) {
		for (int j = 0; j < i - 1; j++)
			if (f > af[j] && f <= af[j + 1])
				return j + 1;

		return i - 1;
	}

	// ------------------------------------------------ CONVENIENCE METHODS ---------------------------------------------
	public void displayRecentStockdataData(boolean debugOnForStock) {
		String methodname = "displayRecentStockdataData";

		if (super.currentGreenmanPeriod < 0)
			return;

		try {
			String useDebugInfo = UTPropertyManager.getProperty("log_stock_debug");
			if (useDebugInfo.equals("1")) {
					log.warn("----------------------------------- FINAL PROCESSED DATA STORED IN GREENMAN -------------------------------");
					log.warn("-            Stock_K Num_Periods: " + st_k_period + " Stock_K_Ema Num_Periods: " + st_k_ema_period + " Stock_D Num_Periods: " + st_d_period);

				int endIndex = super.currentGreenmanPeriod;
				for (int i = 0; i < endIndex; i++)
						log.warn("Data [" + i + "]  Time: [" + super.dateString[i] + "]  Open: [" + super.open[i] + "]  LDT: [" + UTDatetime.toString(dateLDT[i]) + "]  numdate: [" + numdate[i]
								+ "]  High: [" + super.high[i] + "]  Low: [" + super.low[i] + "]  Close: [" + super.close[i] + "]  Volume: [" + super.volume[i] + "]   Stoch_K: [" + stoch_K[i]
								+ "]   Stoch_D: [" + stoch_D[i] + "]");
			}
		} catch (Exception e) {
			log.error("Error in " + CLASSNAME + ":" + methodname);
			log.error("Last Data Point on Greenman List     Time: [" + super.dateString[super.currentGreenmanPeriod - 1] + "]  LDT: ["
					+ UTDatetime.toString(dateLDT[super.currentGreenmanPeriod - 1]) + "]  numdate: [" + numdate[super.currentGreenmanPeriod - 1] + "]  Open: ["
					+ super.open[super.currentGreenmanPeriod - 1] + "] High: [" + super.high[super.currentGreenmanPeriod - 1] + "]  Low: [" + super.low[super.currentGreenmanPeriod - 1]
					+ "]   Close: [" + super.close[super.currentGreenmanPeriod - 1] + "]");
		}

		return;
	}

	private static final int NUM_DATA_POINTS_TO_DISPLAY = 5;

	public void displayRecentStockdataDataOnTraceModel(Trace trace) {
		String methodname = "displayRecentStockdataDataOnTraceModel";

		if (super.totalNumDataPoints < NUM_DATA_POINTS_TO_DISPLAY)
			return;

		try {
			{
				log.info("-            Stock_K Num_Periods: " + st_k_period + " Stock_K_Ema Num_Periods: " + st_k_ema_period + " Stock_D Num_Periods: " + st_d_period);
				log.info("----------------------------------- FINAL PROCESSED DATA STORED IN GREENMAN -------------------------------");
				for (int i = super.totalNumDataPoints - NUM_DATA_POINTS_TO_DISPLAY; i < super.totalNumDataPoints; i++)
					if (trace.isLevelModel())
						log.info("Data [" + i + "]  Time: [" + super.dateString[i] + "]  LDT: [" + UTDatetime.toString(dateLDT[i]) + "]  numdate: [" + numdate[i] + "]  Open: [" + super.open[i]
								+ "]  High: [" + super.high[i] + "]  Low: [" + super.low[i] + "]  Close: [" + super.close[i] + "]  Volume: [" + super.volume[i] + "]   Stoch_K: [" + stoch_K[i]
								+ "]   Stoch_D: [" + stoch_D[i] + "]");
			}
		} catch (Exception e) {
			log.error("Error in " + CLASSNAME + ":" + methodname);
			log.error("Last Data Point on Greenman List     Time: [" + super.dateString[super.currentGreenmanPeriod - 1] + "]  Open: [" + super.open[super.currentGreenmanPeriod - 1]
					+ "] High: [" + super.high[super.currentGreenmanPeriod - 1] + "]  Low: [" + super.low[super.currentGreenmanPeriod - 1] + "]   Close: ["
					+ super.close[super.currentGreenmanPeriod - 1] + "]");
		}

		return;
	}

	// Used for debugging.
	public final void displayDataPointsToGreenmanPeriod(int numPoints) {
		String methodname = "displayDataPointsToGreenmanPeriod";

		try {
			log.info("----------------------------------- FINAL PROCESSED DATA STORED IN GREENMAN -------------------------------");
			log.info("        Num Data Points: [" + super.currentGreenmanPeriod + "]");

			int startPoint = 0;
			if (numPoints < super.currentGreenmanPeriod) {
				startPoint = super.currentGreenmanPeriod - numPoints;
				startPoint--;
			}

			for (int i = startPoint; i < super.currentGreenmanPeriod; i++)
					log.info("Data [" + i + "]  Time: [" + super.dateString[i] + "]  Open: [" + super.open[i] + "]  LDT: [" + UTDatetime.toString(dateLDT[i]) + "]  numdate: [" + numdate[i]
							+ "]  High: [" + super.high[i] + "]  Low: [" + super.low[i] + "]  Close: [" + super.close[i] + "]  Volume: [" + super.volume[i] + "]  stochK[i]:  [" + stoch_K[i] + "]"
							+ "]  stochD[i]:  [" + stoch_D[i] + "]");
		} catch (Exception e) {
			log.error("Error in " + CLASSNAME + ":" + methodname + " message: " + e.getMessage());
		}

		return;
	}

	// ------------------------------------------------ CONVENIENCE SETTERS/GETTERS ---------------------------------------------
	// These are all the getters/setters we need on this class, don't add them for all member variables.
	public String getFirstDate() {
		return dateString[GmConstants.MIN_PERIODS_FOR_DATA_CALCS];
	}

	public LocalDateTime getFirstLDT() {
		return dateLDT[GmConstants.MIN_PERIODS_FOR_DATA_CALCS];
	}

	public LocalDateTime getZeroLDT(Trace trace) {
		return dateLDT[0];
	}

	public void setSt_k_period(int numPeriods) {
		this.st_k_period = numPeriods;
	}

	public void setSt_k_Ema_period(int numPeriods) {
		this.st_k_ema_period = numPeriods;
	}

	public void setSt_d_period(int numPeriods) {
		this.st_d_period = numPeriods;
	}

	public int[] getNumdate() {
		return numdate;
	}

	public void setNumdate(int[] numdate) {
		this.numdate = numdate;
	}

	// ------------------------------------------------ SIMPLE SETTERS/GETTERS ---------------------------------------------
	public float[] getStoch() {
		return stoch;
	}

	public void setStoch(float[] stoch) {
		this.stoch = stoch;
	}

	public float[] getStoch_K() {
		return stoch_K;
	}

	public void setStoch_K(float[] stoch_K) {
		this.stoch_K = stoch_K;
	}

	public float[] getStoch_D() {
		return stoch_D;
	}

	public void setStoch_D(float[] stoch_D) {
		this.stoch_D = stoch_D;
	}

	public float[] getSlope() {
		return slope;
	}

	public void setSlope(float[] slope) {
		this.slope = slope;
	}

	public float[] getMacd() {
		return macd;
	}

	public void setMacd(float[] macd) {
		this.macd = macd;
	}

	public float[] getMacdFastEma() {
		return macdFastEma;
	}

	public void setMacdFastEma(float[] macdFastEma) {
		this.macdFastEma = macdFastEma;
	}

	public float[] getMacdSlowEma() {
		return macdSlowEma;
	}

	public void setMacdSlowEma(float[] macdSlowEma) {
		this.macdSlowEma = macdSlowEma;
	}

	public float[] getMacdSignal() {
		return macdSignal;
	}

	public void setMacdSignal(float[] macdSignal) {
		this.macdSignal = macdSignal;
	}

	public float[] getMacdHistogram() {
		return macdHistogram;
	}

	public void setMacdHistogram(float[] macdHistogram) {
		this.macdHistogram = macdHistogram;
	}

	public int getMacdFastEmaPeriod() {
		return macdFastEmaPeriod;
	}

	public void setMacdFastEmaPeriod(int macdFastEmaPeriod) {
		this.macdFastEmaPeriod = macdFastEmaPeriod;
	}

	public int getMacdSlowEmaPeriod() {
		return macdSlowEmaPeriod;
	}

	public void setMacdSlowEmaPeriod(int macdSlowEmaPeriod) {
		this.macdSlowEmaPeriod = macdSlowEmaPeriod;
	}

	public int getMacdSignalEmaPeriod() {
		return macdSignalEmaPeriod;
	}

	public void setMacdSignalEmaPeriod(int macdSignalEmaPeriod) {
		this.macdSignalEmaPeriod = macdSignalEmaPeriod;
	}

	public float[] getMovingAverage1() {
		return movingAverage1;
	}

	public void setMovingAverage1(float[] movingAverage1) {
		this.movingAverage1 = movingAverage1;
	}

	public float[] getMovingAverage2() {
		return movingAverage2;
	}

	public void setMovingAverage2(float[] movingAverage2) {
		this.movingAverage2 = movingAverage2;
	}

	public float[] getMovingAverage3() {
		return movingAverage3;
	}

	public void setMovingAverage3(float[] movingAverage3) {
		this.movingAverage3 = movingAverage3;
	}

	public float[] getMovingAverage4() {
		return movingAverage4;
	}

	public void setMovingAverage4(float[] movingAverage4) {
		this.movingAverage4 = movingAverage4;
	}

	public float[] getMovingAverage5() {
		return movingAverage5;
	}

	public void setMovingAverage5(float[] movingAverage5) {
		this.movingAverage5 = movingAverage5;
	}

	public float[] getMovingAverage6() {
		return movingAverage6;
	}

	public void setMovingAverage6(float[] movingAverage6) {
		this.movingAverage6 = movingAverage6;
	}

	public int getMovingAverage1Periods() {
		return movingAverage1Periods;
	}

	public void setMovingAverage1Periods(int movingAverage1Periods) {
		this.movingAverage1Periods = movingAverage1Periods;
	}

	public int getMovingAverage2Periods() {
		return movingAverage2Periods;
	}

	public void setMovingAverage2Periods(int movingAverage2Periods) {
		this.movingAverage2Periods = movingAverage2Periods;
	}

	public int getMovingAverage3Periods() {
		return movingAverage3Periods;
	}

	public void setMovingAverage3Periods(int movingAverage3Periods) {
		this.movingAverage3Periods = movingAverage3Periods;
	}

	public int getMovingAverage4Periods() {
		return movingAverage4Periods;
	}

	public void setMovingAverage4Periods(int movingAverage4Periods) {
		this.movingAverage4Periods = movingAverage4Periods;
	}

	public int getMovingAverage5Periods() {
		return movingAverage5Periods;
	}

	public void setMovingAverage5Periods(int movingAverage5Periods) {
		this.movingAverage5Periods = movingAverage5Periods;
	}

	public int getMovingAverage6Periods() {
		return movingAverage6Periods;
	}

	public void setMovingAverage6Periods(int movingAverage6Periods) {
		this.movingAverage6Periods = movingAverage6Periods;
	}

	public int[] getMovingAveragesCalcSMA() {
		return movingAveragesCalcSMA;
	}

	public void setMovingAveragesCalcSMA(int[] movingAveragesCalcSMA) {
		this.movingAveragesCalcSMA = movingAveragesCalcSMA;
	}

	public float getRange_high() {
		return range_high;
	}

	public void setRange_high(float range_high) {
		this.range_high = range_high;
	}

	public float getRange_low() {
		return range_low;
	}

	public void setRange_low(float range_low) {
		this.range_low = range_low;
	}

	public float getVolume_high() {
		return volume_high;
	}

	public void setVolume_high(float volume_high) {
		this.volume_high = volume_high;
	}

	public float getRef_high() {
		return ref_high;
	}

	public void setRef_high(float ref_high) {
		this.ref_high = ref_high;
	}

	public float getRef_low() {
		return ref_low;
	}

	public void setRef_low(float ref_low) {
		this.ref_low = ref_low;
	}

	public float getTrue_range_high() {
		return true_range_high;
	}

	public void setTrue_range_high(float true_range_high) {
		this.true_range_high = true_range_high;
	}

	public float getTrue_range_low() {
		return true_range_low;
	}

	public void setTrue_range_low(float true_range_low) {
		this.true_range_low = true_range_low;
	}

	public int getLow_index() {
		return low_index;
	}

	public void setLow_index(int low_index) {
		this.low_index = low_index;
	}

	public int getHigh_index() {
		return high_index;
	}

	public void setHigh_index(int high_index) {
		this.high_index = high_index;
	}

	public int getDs() {
		return ds;
	}

	public void setDs(int ds) {
		this.ds = ds;
	}

	public int getCross_x() {
		return cross_x;
	}

	public void setCross_x(int cross_x) {
		this.cross_x = cross_x;
	}

	public float getCross_y() {
		return cross_y;
	}

	public void setCross_y(float cross_y) {
		this.cross_y = cross_y;
	}

	public float getBlock_y() {
		return block_y;
	}

	public void setBlock_y(float block_y) {
		this.block_y = block_y;
	}

	public int[] getRef_low_index() {
		return ref_low_index;
	}

	public void setRef_low_index(int[] ref_low_index) {
		this.ref_low_index = ref_low_index;
	}

	public float[][] getRef() {
		return ref;
	}

	public void setRef(float[][] ref) {
		this.ref = ref;
	}

	public float[][] getScale_ref() {
		return scale_ref;
	}

	public void setScale_ref(float[][] scale_ref) {
		this.scale_ref = scale_ref;
	}

	public float getSupport() {
		return support;
	}

	public void setSupport(float support) {
		this.support = support;
	}

	public float[] getLr() {
		return lr;
	}

	public void setLr(float[] lr) {
		this.lr = lr;
	}

	public float[] getLr_ch_up() {
		return lr_ch_up;
	}

	public void setLr_ch_up(float[] lr_ch_up) {
		this.lr_ch_up = lr_ch_up;
	}

	public float[] getLr_ch_down() {
		return lr_ch_down;
	}

	public void setLr_ch_down(float[] lr_ch_down) {
		this.lr_ch_down = lr_ch_down;
	}

	public int getLr_period() {
		return lr_period;
	}

	public void setLr_period(int lr_period) {
		this.lr_period = lr_period;
	}

	public int getLr_stddev() {
		return lr_stddev;
	}

	public void setLr_stddev(int lr_stddev) {
		this.lr_stddev = lr_stddev;
	}

	public float[] getBband() {
		return bband;
	}

	public void setBband(float[] bband) {
		this.bband = bband;
	}

	public float[] getBband_up() {
		return bband_up;
	}

	public void setBband_up(float[] bband_up) {
		this.bband_up = bband_up;
	}

	public float[] getBband_down() {
		return bband_down;
	}

	public void setBband_down(float[] bband_down) {
		this.bband_down = bband_down;
	}

	public float[] getBband_up_stdev() {
		return bband_up_stdev;
	}

	public void setBband_up_stdev(float[] bband_up_stdev) {
		this.bband_up_stdev = bband_up_stdev;
	}

	public float[] getBband_down_stdev() {
		return bband_down_stdev;
	}

	public void setBband_down_stdev(float[] bband_down_stdev) {
		this.bband_down_stdev = bband_down_stdev;
	}

	public float[] getBband_diff_stdev() {
		return bband_diff_stdev;
	}

	public void setBband_diff_stdev(float[] bband_diff_stdev) {
		this.bband_diff_stdev = bband_diff_stdev;
	}

	public float[] getBband_up_stdev_price_percent() {
		return bband_up_stdev_price_percent;
	}

	public void setBband_up_stdev_price_percent(float[] bband_up_stdev_price_percent) {
		this.bband_up_stdev_price_percent = bband_up_stdev_price_percent;
	}

	public float[] getBband_down_stdev_price_percent() {
		return bband_down_stdev_price_percent;
	}

	public void setBband_down_stdev_price_percent(float[] bband_down_stdev_price_percent) {
		this.bband_down_stdev_price_percent = bband_down_stdev_price_percent;
	}

	public float[] getBband_diff_stdev_price_percent() {
		return bband_diff_stdev_price_percent;
	}

	public void setBband_diff_stdev_price_percent(float[] bband_diff_stdev_price_percent) {
		this.bband_diff_stdev_price_percent = bband_diff_stdev_price_percent;
	}

	public int getBband_period() {
		return bband_period;
	}

	public void setBband_period(int bband_period) {
		this.bband_period = bband_period;
	}

	public int getBband_percent() {
		return bband_percent;
	}

	public void setBband_percent(int bband_percent) {
		this.bband_percent = bband_percent;
	}

	public float[] getSar() {
		return sar;
	}

	public void setSar(float[] sar) {
		this.sar = sar;
	}

	public float[] getOg_sp() {
		return og_sp;
	}

	public void setOg_sp(float[] og_sp) {
		this.og_sp = og_sp;
	}

	public float[] getOg_sp_int() {
		return og_sp_int;
	}

	public void setOg_sp_int(float[] og_sp_int) {
		this.og_sp_int = og_sp_int;
	}

	public float[] getOg_sp_st() {
		return og_sp_st;
	}

	public void setOg_sp_st(float[] og_sp_st) {
		this.og_sp_st = og_sp_st;
	}

	public int[] getUdio() {
		return udio;
	}

	public void setUdio(int[] udio) {
		this.udio = udio;
	}

	public float[] getAtr() {
		return atr;
	}

	public void setAtr(float[] atr) {
		this.atr = atr;
	}

	public float getAtr_max() {
		return atr_max;
	}

	public void setAtr_max(float atr_max) {
		this.atr_max = atr_max;
	}

	public float getAtr_min() {
		return atr_min;
	}

	public void setAtr_min(float atr_min) {
		this.atr_min = atr_min;
	}

	public int getAtr_period() {
		return atr_period;
	}

	public void setAtr_period(int atr_period) {
		this.atr_period = atr_period;
	}

	public float[] getTsi() {
		return tsi;
	}

	public void setTsi(float[] tsi) {
		this.tsi = tsi;
	}

	public float[] getTsi_signal() {
		return tsi_signal;
	}

	public void setTsi_signal(float[] tsi_signal) {
		this.tsi_signal = tsi_signal;
	}

	public float[] getTsi_signal_diff() {
		return tsi_signal_diff;
	}

	public void setTsi_signal_diff(float[] tsi_signal_diff) {
		this.tsi_signal_diff = tsi_signal_diff;
	}

	public float[] getTsi_arrows() {
		return tsi_arrows;
	}

	public void setTsi_arrows(float[] tsi_arrows) {
		this.tsi_arrows = tsi_arrows;
	}

	public float[] getTsi_arrows_ud() {
		return tsi_arrows_ud;
	}

	public void setTsi_arrows_ud(float[] tsi_arrows_ud) {
		this.tsi_arrows_ud = tsi_arrows_ud;
	}

	public int getTsi_primary_period() {
		return tsi_primary_period;
	}

	public void setTsi_primary_period(int tsi_primary_period) {
		this.tsi_primary_period = tsi_primary_period;
	}

	public int getTsi_secondary_period() {
		return tsi_secondary_period;
	}

	public void setTsi_secondary_period(int tsi_secondary_period) {
		this.tsi_secondary_period = tsi_secondary_period;
	}

	public int getTsi_signal_period() {
		return tsi_signal_period;
	}

	public void setTsi_signal_period(int tsi_signal_period) {
		this.tsi_signal_period = tsi_signal_period;
	}

	public float[] getFib_r50() {
		return fib_r50;
	}

	public void setFib_r50(float[] fib_r50) {
		this.fib_r50 = fib_r50;
	}

	public float[] getFib_r382() {
		return fib_r382;
	}

	public void setFib_r382(float[] fib_r382) {
		this.fib_r382 = fib_r382;
	}

	public float[] getFib_r618() {
		return fib_r618;
	}

	public void setFib_r618(float[] fib_r618) {
		this.fib_r618 = fib_r618;
	}

	public float[] getFib_reversal_1() {
		return fib_reversal_1;
	}

	public void setFib_reversal_1(float[] fib_reversal_1) {
		this.fib_reversal_1 = fib_reversal_1;
	}

	public float[] getFib_reversal_2() {
		return fib_reversal_2;
	}

	public void setFib_reversal_2(float[] fib_reversal_2) {
		this.fib_reversal_2 = fib_reversal_2;
	}

	public float[] getFib_reversal_Z() {
		return fib_reversal_Z;
	}

	public void setFib_reversal_Z(float[] fib_reversal_Z) {
		this.fib_reversal_Z = fib_reversal_Z;
	}

	public float[] getFib_reversal_points() {
		return fib_reversal_points;
	}

	public void setFib_reversal_points(float[] fib_reversal_points) {
		this.fib_reversal_points = fib_reversal_points;
	}

	public float[] getFibo_r50() {
		return fibo_r50;
	}

	public void setFibo_r50(float[] fibo_r50) {
		this.fibo_r50 = fibo_r50;
	}

	public float[] getFibo_r382() {
		return fibo_r382;
	}

	public void setFibo_r382(float[] fibo_r382) {
		this.fibo_r382 = fibo_r382;
	}

	public float[] getFibo_r618() {
		return fibo_r618;
	}

	public void setFibo_r618(float[] fibo_r618) {
		this.fibo_r618 = fibo_r618;
	}

	public float[] getFibo_reversal_1() {
		return fibo_reversal_1;
	}

	public void setFibo_reversal_1(float[] fibo_reversal_1) {
		this.fibo_reversal_1 = fibo_reversal_1;
	}

	public float[] getFibo_reversal_2() {
		return fibo_reversal_2;
	}

	public void setFibo_reversal_2(float[] fibo_reversal_2) {
		this.fibo_reversal_2 = fibo_reversal_2;
	}

	public float[] getFibo_reversal_Z() {
		return fibo_reversal_Z;
	}

	public void setFibo_reversal_Z(float[] fibo_reversal_Z) {
		this.fibo_reversal_Z = fibo_reversal_Z;
	}

	public float[] getFibo_reversal_points() {
		return fibo_reversal_points;
	}

	public void setFibo_reversal_points(float[] fibo_reversal_points) {
		this.fibo_reversal_points = fibo_reversal_points;
	}

	public int getFib_reversal_percent() {
		return fib_reversal_percent;
	}

	public void setFib_reversal_percent(int fib_reversal_percent) {
		this.fib_reversal_percent = fib_reversal_percent;
	}

	public float[] getFw() {
		return fw;
	}

	public void setFw(float[] fw) {
		this.fw = fw;
	}

	public int getFw_reversal_percent() {
		return fw_reversal_percent;
	}

	public void setFw_reversal_percent(int fw_reversal_percent) {
		this.fw_reversal_percent = fw_reversal_percent;
	}

	public float[] getRsi() {
		return rsi;
	}

	public void setRsi(float[] rsi) {
		this.rsi = rsi;
	}

	public float getRsi_high() {
		return rsi_high;
	}

	public void setRsi_high(float rsi_high) {
		this.rsi_high = rsi_high;
	}

	public float getRsi_low() {
		return rsi_low;
	}

	public void setRsi_low(float rsi_low) {
		this.rsi_low = rsi_low;
	}

	public int getRsi_period() {
		return rsi_period;
	}

	public void setRsi_period(int rsi_period) {
		this.rsi_period = rsi_period;
	}

	public float[] getClassic_rsi() {
		return classic_rsi;
	}

	public void setClassic_rsi(float[] classic_rsi) {
		this.classic_rsi = classic_rsi;
	}

	public float getClassic_rsi_high() {
		return classic_rsi_high;
	}

	public void setClassic_rsi_high(float classic_rsi_high) {
		this.classic_rsi_high = classic_rsi_high;
	}

	public float getClassic_rsi_low() {
		return classic_rsi_low;
	}

	public void setClassic_rsi_low(float classic_rsi_low) {
		this.classic_rsi_low = classic_rsi_low;
	}

	public int getClassic_rsi_period() {
		return classic_rsi_period;
	}

	public void setClassic_rsi_period(int classic_rsi_period) {
		this.classic_rsi_period = classic_rsi_period;
	}

	public float[] getMt() {
		return mt;
	}

	public void setMt(float[] mt) {
		this.mt = mt;
	}

	public float getMt_high() {
		return mt_high;
	}

	public void setMt_high(float mt_high) {
		this.mt_high = mt_high;
	}

	public float getMt_low() {
		return mt_low;
	}

	public void setMt_low(float mt_low) {
		this.mt_low = mt_low;
	}

	public int getMt_period() {
		return mt_period;
	}

	public void setMt_period(int mt_period) {
		this.mt_period = mt_period;
	}

	public float[] getVolatility() {
		return volatility;
	}

	public void setVolatility(float[] volatility) {
		this.volatility = volatility;
	}

	public float getVoli_high() {
		return voli_high;
	}

	public void setVoli_high(float voli_high) {
		this.voli_high = voli_high;
	}

	public float getVoli_low() {
		return voli_low;
	}

	public void setVoli_low(float voli_low) {
		this.voli_low = voli_low;
	}

	public int getVoli_period() {
		return voli_period;
	}

	public void setVoli_period(int voli_period) {
		this.voli_period = voli_period;
	}

	public float getMacd_high() {
		return macd_high;
	}

	public void setMacd_high(float macd_high) {
		this.macd_high = macd_high;
	}

	public float getMacd_low() {
		return macd_low;
	}

	public void setMacd_low(float macd_low) {
		this.macd_low = macd_low;
	}

	public float[] getMacd_slope() {
		return macd_slope;
	}

	public void setMacd_slope(float[] macd_slope) {
		this.macd_slope = macd_slope;
	}

	public float[] getMacd_slope_arrows() {
		return macd_slope_arrows;
	}

	public void setMacd_slope_arrows(float[] macd_slope_arrows) {
		this.macd_slope_arrows = macd_slope_arrows;
	}

	public float[] getMacd_slope_arrows_ud() {
		return macd_slope_arrows_ud;
	}

	public void setMacd_slope_arrows_ud(float[] macd_slope_arrows_ud) {
		this.macd_slope_arrows_ud = macd_slope_arrows_ud;
	}

	public float getMacd_slope_high() {
		return macd_slope_high;
	}

	public void setMacd_slope_high(float macd_slope_high) {
		this.macd_slope_high = macd_slope_high;
	}

	public float getMacd_slope_low() {
		return macd_slope_low;
	}

	public void setMacd_slope_low(float macd_slope_low) {
		this.macd_slope_low = macd_slope_low;
	}

	public float[] getPercentR() {
		return percentR;
	}

	public void setPercentR(float[] percentR) {
		this.percentR = percentR;
	}

	public int getPercentR_period() {
		return percentR_period;
	}

	public void setPercentR_period(int percentR_period) {
		this.percentR_period = percentR_period;
	}

	public float getPercentR_high() {
		return percentR_high;
	}

	public void setPercentR_high(float percentR_high) {
		this.percentR_high = percentR_high;
	}

	public float getPercentR_low() {
		return percentR_low;
	}

	public void setPercentR_low(float percentR_low) {
		this.percentR_low = percentR_low;
	}

	public float[] getUo() {
		return uo;
	}

	public void setUo(float[] uo) {
		this.uo = uo;
	}

	public float getUo_high() {
		return uo_high;
	}

	public void setUo_high(float uo_high) {
		this.uo_high = uo_high;
	}

	public float getUo_low() {
		return uo_low;
	}

	public void setUo_low(float uo_low) {
		this.uo_low = uo_low;
	}

	public float[] getObv() {
		return obv;
	}

	public void setObv(float[] obv) {
		this.obv = obv;
	}

	public float[] getObv_ma() {
		return obv_ma;
	}

	public void setObv_ma(float[] obv_ma) {
		this.obv_ma = obv_ma;
	}

	public float[] getMma_obv() {
		return mma_obv;
	}

	public void setMma_obv(float[] mma_obv) {
		this.mma_obv = mma_obv;
	}

	public float[] getO_obv() {
		return o_obv;
	}

	public void setO_obv(float[] o_obv) {
		this.o_obv = o_obv;
	}

	public float getObv_high() {
		return obv_high;
	}

	public void setObv_high(float obv_high) {
		this.obv_high = obv_high;
	}

	public float getObv_low() {
		return obv_low;
	}

	public void setObv_low(float obv_low) {
		this.obv_low = obv_low;
	}

	public int getObv_period() {
		return obv_period;
	}

	public void setObv_period(int obv_period) {
		this.obv_period = obv_period;
	}

	public float[] getCci() {
		return cci;
	}

	public void setCci(float[] cci) {
		this.cci = cci;
	}

	public float getCci_high() {
		return cci_high;
	}

	public void setCci_high(float cci_high) {
		this.cci_high = cci_high;
	}

	public float getCci_low() {
		return cci_low;
	}

	public void setCci_low(float cci_low) {
		this.cci_low = cci_low;
	}

	public int getCci_period() {
		return cci_period;
	}

	public void setCci_period(int cci_period) {
		this.cci_period = cci_period;
	}

	public float[] getDx() {
		return dx;
	}

	public void setDx(float[] dx) {
		this.dx = dx;
	}

	public float[] getAdx() {
		return adx;
	}

	public void setAdx(float[] adx) {
		this.adx = adx;
	}

	public float[] getPdi() {
		return pdi;
	}

	public void setPdi(float[] pdi) {
		this.pdi = pdi;
	}

	public float[] getMdi() {
		return mdi;
	}

	public void setMdi(float[] mdi) {
		this.mdi = mdi;
	}

	public float[] getDxt() {
		return dxt;
	}

	public void setDxt(float[] dxt) {
		this.dxt = dxt;
	}

	public float[] getAdxt() {
		return adxt;
	}

	public void setAdxt(float[] adxt) {
		this.adxt = adxt;
	}

	public float[] getPdit() {
		return pdit;
	}

	public void setPdit(float[] pdit) {
		this.pdit = pdit;
	}

	public float[] getMdit() {
		return mdit;
	}

	public void setMdit(float[] mdit) {
		this.mdit = mdit;
	}

	public int getDmi_period() {
		return dmi_period;
	}

	public void setDmi_period(int dmi_period) {
		this.dmi_period = dmi_period;
	}

	public float[] getMfi() {
		return mfi;
	}

	public void setMfi(float[] mfi) {
		this.mfi = mfi;
	}

	public float getMfi_high() {
		return mfi_high;
	}

	public void setMfi_high(float mfi_high) {
		this.mfi_high = mfi_high;
	}

	public float[] getChoppy() {
		return choppy;
	}

	public void setChoppy(float[] choppy) {
		this.choppy = choppy;
	}

	public float[] getMoneyflow_a() {
		return moneyflow_a;
	}

	public void setMoneyflow_a(float[] moneyflow_a) {
		this.moneyflow_a = moneyflow_a;
	}

	public float getMoneyflow_high() {
		return moneyflow_high;
	}

	public void setMoneyflow_high(float moneyflow_high) {
		this.moneyflow_high = moneyflow_high;
	}

	public float getMoneyflow_low() {
		return moneyflow_low;
	}

	public void setMoneyflow_low(float moneyflow_low) {
		this.moneyflow_low = moneyflow_low;
	}

	public float getMoneyflow_a_high() {
		return moneyflow_a_high;
	}

	public void setMoneyflow_a_high(float moneyflow_a_high) {
		this.moneyflow_a_high = moneyflow_a_high;
	}

	public float getMoneyflow_a_low() {
		return moneyflow_a_low;
	}

	public void setMoneyflow_a_low(float moneyflow_a_low) {
		this.moneyflow_a_low = moneyflow_a_low;
	}

	public float[] getForceIndex() {
		return forceIndex;
	}

	public void setForceIndex(float[] forceIndex) {
		this.forceIndex = forceIndex;
	}

	public float[] getForceIndexSMA1() {
		return forceIndexSMA1;
	}

	public void setForceIndexSMA1(float[] forceIndexSMA1) {
		this.forceIndexSMA1 = forceIndexSMA1;
	}

	public float[] getForceIndexSMA2() {
		return forceIndexSMA2;
	}

	public void setForceIndexSMA2(float[] forceIndexSMA2) {
		this.forceIndexSMA2 = forceIndexSMA2;
	}

	public int getFiSMA1_period() {
		return fiSMA1_period;
	}

	public void setFiSMA1_period(int fiSMA1_period) {
		this.fiSMA1_period = fiSMA1_period;
	}

	public int getFiSMA2_period() {
		return fiSMA2_period;
	}

	public void setFiSMA2_period(int fiSMA2_period) {
		this.fiSMA2_period = fiSMA2_period;
	}

	public float[] getVolAvg() {
		return volAvg;
	}

	public void setVolAvg(float[] volAvg) {
		this.volAvg = volAvg;
	}

	public int getVolAvg_period() {
		return volAvg_period;
	}

	public void setVolAvg_period(int volAvg_period) {
		this.volAvg_period = volAvg_period;
	}

	public int getAccband_period1() {
		return accband_period1;
	}

	public void setAccband_period1(int accband_period1) {
		this.accband_period1 = accband_period1;
	}

	public int getAccband_period2() {
		return accband_period2;
	}

	public void setAccband_period2(int accband_period2) {
		this.accband_period2 = accband_period2;
	}

	public float[] getAccband1U() {
		return accband1U;
	}

	public void setAccband1U(float[] accband1u) {
		accband1U = accband1u;
	}

	public float[] getAccbandSMA1() {
		return accbandSMA1;
	}

	public void setAccbandSMA1(float[] accbandSMA1) {
		this.accbandSMA1 = accbandSMA1;
	}

	public float[] getAccband1L() {
		return accband1L;
	}

	public void setAccband1L(float[] accband1l) {
		accband1L = accband1l;
	}

	public float[] getAccband2U() {
		return accband2U;
	}

	public void setAccband2U(float[] accband2u) {
		accband2U = accband2u;
	}

	public float[] getAccbandSMA2() {
		return accbandSMA2;
	}

	public void setAccbandSMA2(float[] accbandSMA2) {
		this.accbandSMA2 = accbandSMA2;
	}

	public float[] getAccband2L() {
		return accband2L;
	}

	public void setAccband2L(float[] accband2l) {
		accband2L = accband2l;
	}

	public int getFastMA_MD() {
		return FastMA_MD;
	}

	public void setFastMA_MD(int fastMA_MD) {
		FastMA_MD = fastMA_MD;
	}

	public int getSlowMA_MD() {
		return SlowMA_MD;
	}

	public void setSlowMA_MD(int slowMA_MD) {
		SlowMA_MD = slowMA_MD;
	}

	public int getMacdMA_MD() {
		return MacdMA_MD;
	}

	public void setMacdMA_MD(int macdMA_MD) {
		MacdMA_MD = macdMA_MD;
	}

	public int getMd_period1() {
		return md_period1;
	}

	public void setMd_period1(int md_period1) {
		this.md_period1 = md_period1;
	}

	public int getMd_period2() {
		return md_period2;
	}

	public void setMd_period2(int md_period2) {
		this.md_period2 = md_period2;
	}

	public float[] getPlot1A_MD() {
		return plot1A_MD;
	}

	public void setPlot1A_MD(float[] plot1a_MD) {
		plot1A_MD = plot1a_MD;
	}

	public float[] getPlot1B_MD() {
		return plot1B_MD;
	}

	public void setPlot1B_MD(float[] plot1b_MD) {
		plot1B_MD = plot1b_MD;
	}

	public float[] getPlot2A_MD() {
		return plot2A_MD;
	}

	public void setPlot2A_MD(float[] plot2a_MD) {
		plot2A_MD = plot2a_MD;
	}

	public float[] getPlot2B_MD() {
		return plot2B_MD;
	}

	public void setPlot2B_MD(float[] plot2b_MD) {
		plot2B_MD = plot2b_MD;
	}

	public int getTrend_initiation_period1() {
		return trend_initiation_period1;
	}

	public void setTrend_initiation_period1(int trend_initiation_period1) {
		this.trend_initiation_period1 = trend_initiation_period1;
	}

	public int getTrend_initiation_period2() {
		return trend_initiation_period2;
	}

	public void setTrend_initiation_period2(int trend_initiation_period2) {
		this.trend_initiation_period2 = trend_initiation_period2;
	}

	public float[] getPlot1A_TI() {
		return plot1A_TI;
	}

	public void setPlot1A_TI(float[] plot1a_TI) {
		plot1A_TI = plot1a_TI;
	}

	public float[] getPlot1B_TI() {
		return plot1B_TI;
	}

	public void setPlot1B_TI(float[] plot1b_TI) {
		plot1B_TI = plot1b_TI;
	}

	public float[] getPlot1C_TI() {
		return plot1C_TI;
	}

	public void setPlot1C_TI(float[] plot1c_TI) {
		plot1C_TI = plot1c_TI;
	}

	public float[] getPlot1D_TI() {
		return plot1D_TI;
	}

	public void setPlot1D_TI(float[] plot1d_TI) {
		plot1D_TI = plot1d_TI;
	}

	public float[] getPlot2A_TI() {
		return plot2A_TI;
	}

	public void setPlot2A_TI(float[] plot2a_TI) {
		plot2A_TI = plot2a_TI;
	}

	public float[] getPlot2B_TI() {
		return plot2B_TI;
	}

	public void setPlot2B_TI(float[] plot2b_TI) {
		plot2B_TI = plot2b_TI;
	}

	public float[] getPlot2C_TI() {
		return plot2C_TI;
	}

	public void setPlot2C_TI(float[] plot2c_TI) {
		plot2C_TI = plot2c_TI;
	}

	public float[] getPlot2D_TI() {
		return plot2D_TI;
	}

	public void setPlot2D_TI(float[] plot2d_TI) {
		plot2D_TI = plot2d_TI;
	}

	public int getWma_period() {
		return wma_period;
	}

	public void setWma_period(int wma_period) {
		this.wma_period = wma_period;
	}

	public float[] getWma_low() {
		return wma_low;
	}

	public void setWma_low(float[] wma_low) {
		this.wma_low = wma_low;
	}

	public float[] getWma_high() {
		return wma_high;
	}

	public void setWma_high(float[] wma_high) {
		this.wma_high = wma_high;
	}

	public char[][] getPf() {
		return pf;
	}

	public void setPf(char[][] pf) {
		this.pf = pf;
	}

	public float[] getPf_grid() {
		return pf_grid;
	}

	public void setPf_grid(float[] pf_grid) {
		this.pf_grid = pf_grid;
	}

	public int getPf_grid_width() {
		return pf_grid_width;
	}

	public void setPf_grid_width(int pf_grid_width) {
		this.pf_grid_width = pf_grid_width;
	}

	public int getPf_grid_height() {
		return pf_grid_height;
	}

	public void setPf_grid_height(int pf_grid_height) {
		this.pf_grid_height = pf_grid_height;
	}

	public float getPf_high() {
		return pf_high;
	}

	public void setPf_high(float pf_high) {
		this.pf_high = pf_high;
	}

	public float getPf_low() {
		return pf_low;
	}

	public void setPf_low(float pf_low) {
		this.pf_low = pf_low;
	}

	public float[] getPf_price() {
		return pf_price;
	}

	public void setPf_price(float[] pf_price) {
		this.pf_price = pf_price;
	}

	public float[] getPf_box() {
		return pf_box;
	}

	public void setPf_box(float[] pf_box) {
		this.pf_box = pf_box;
	}

	public int getStartdate() {
		return startdate;
	}

	public void setStartdate(int startdate) {
		this.startdate = startdate;
	}

	public int getEnddate() {
		return enddate;
	}

	public void setEnddate(int enddate) {
		this.enddate = enddate;
	}

	public float[] getIperiod_stoch() {
		return iperiod_stoch;
	}

	public void setIperiod_stoch(float[] iperiod_stoch) {
		this.iperiod_stoch = iperiod_stoch;
	}

	public float[] getIperiod_stoch_K() {
		return iperiod_stoch_K;
	}

	public void setIperiod_stoch_K(float[] iperiod_stoch_K) {
		this.iperiod_stoch_K = iperiod_stoch_K;
	}

	public float[] getIperiod_stoch_D() {
		return iperiod_stoch_D;
	}

	public void setIperiod_stoch_D(float[] iperiod_stoch_D) {
		this.iperiod_stoch_D = iperiod_stoch_D;
	}

	public int getSt_k_period() {
		return st_k_period;
	}

	public int getSt_k_Ema_period() {
		return st_k_ema_period;
	}

	public int getSt_d_period() {
		return st_d_period;
	}

	public int getLengthTI() {
		return lengthTI;
	}

	public int getAvgLenTI() {
		return avgLenTI;
	}

	public int getTrendLevelTI() {
		return trendLevelTI;
	}
}
