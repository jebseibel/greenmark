package com.greenmark.core;

import com.greenmark.account.CashMgmtIndicators;
import com.greenmark.common.core.Labels;
import com.greenmark.core.enums.LongShort;
import com.greenmark.core.enums.StockStatus;
import com.greenmark.utils.UTCalendarTime;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Vector;


/////////////////////////////////////////////////
// START CLASS: Stock
/////////////////////////////////////////////////
@Slf4j
public class Stock {
    public static final int NO_ORDER_ERROR = 1;
    public static final int EXCEED_MAX_ORDERS = 2;
    public static final int EXCEED_MAX_POSITIONS = 3;
    public static final int EXCEED_MIN_CASH_ON_HAND = 4;
    public static final int EXCEED_MAX_ACCT_BAL_FOR_OPEN_ORDERS = 5;
    public static final int CALC_SIZE_LESS_THAN_MIN_ORDER_SIZE = 6;
    public static final int CALC_STOCK_WATCH_SIZE_IS_ZERO = 7;
    public static final int CALC_ORDER_SIZE_IS_ZERO = 8;
    public static final int ORDER_ERROR_DEFAULT = 1;
    public static final int SELL_STRATEGY_DAILY = 1;
    public static final int SELL_STRATEGY_MINUTE60 = 2;
    public static final int SELL_STRATEGY_MINUTE15 = 3;
    public static final int SELL_STRATEGY_MINUTE05 = 4;
    public static final int NO_SELL_STRATEGY = 5;
    public static final int SELL_STRATEGY_DEFAULT = 1;
    // This is for the Slow K, How many Fast Ks you average together to make the Slow K.  (Calculate EMA)
    public static final int NUM_PTS_STOCH_SLOW_K = 5;
    public static final int NUM_PTS_STOCH_SLOW_D = 5;
    public static final int NUM_PER_DAY_MINUTE60 = 7;
    public static final int NUM_PER_DAY_MINUTE15 = 26;
    public static final int NUM_PER_DAY_MINUTE05 = 78;
    public static final int NUM_PER_DAY_MINUTE01 = 390;
    public static final int NUM_MINS_IN_MIN05 = 5;
    public static final int NUM_MINS_IN_MIN15 = 15;
    public static final int NUM_MINS_IN_MIN60 = 60;
    public static final int NUM_MINS_IN_DAILY = 1440;
    protected long id;           //leave as null for hibernate
    protected long accountId = 0;     //set to zero to find insert errors
    protected long securityId = 0;     //set to zero to find insert errors
    protected int active = Labels.OBJECT_ACTIVE;
    protected String symbol;
    /**
     * The standardized stock symbol
     */
    protected String name;
    /**
     * The name of the stock's company
     */

    protected NumberFormat nf = NumberFormat.getCurrencyInstance(); // For toString() formatting function.
    protected String market;
    /**
     * The standardized market symbol
     */

    ////////////////////////////////////////////////////////////
    //  Type and Status Information
    protected int longOrShort = LongShort.LONG.value;
    protected StockStatus status = StockStatus.NEW;
    protected boolean priceHistoryInitialized = false;
    protected boolean newlyHarvested = false;
    protected boolean rawDataPrinted = false;
    ////////////////////////////////////////////////////////////
    //  Bucket and Timeframe Information
    protected int numPeriodsInBucket = 0;
    protected UTCalendarTime movedToBucketDatetime;
    protected int currentTimeframe; // Which bucket is it in?
    protected int movedFromTimeframe; // Which Bucket did it come from?
    protected int numDisqualifiedMinute60 = 0;
    protected int numDisqualifiedMinute15 = 0;
    protected int numDisqualifiedMinute05 = 0;
    ////////////////////////////////////////////////////////////
    //  Indicators and Calculations
    protected float macD = 0; //  Used by the GUI for display.  Is this the histogram MacD or the MacD
    protected float currentStochK = 0; //  Used by the GUI for display.
    protected float rsiClassic = 0;
    protected float ema5 = 0;
    protected float ema20 = 0;
    protected float ema50 = 0;
    protected float minute60LowHighPt = 0;
    protected boolean minute60LowHighCalculated = false;
    protected float dailyLowHighPt = 0;
    protected boolean dailyLowHighCalculated = false;
    protected CashMgmtIndicators indicators;
    ////////////////////////////////////////////////////////////
    //  Pricing and Order Information
    protected float currentOpenPrice = 0; // The current 1 Minute Open price of the stock
    protected float currentClosePrice = 0; // The current 1 Minute Close price of the stock
    protected float currentHighPrice = 0; // The current 1 Minute High price of the stock
    protected float currentLowPrice = 0; // The current 1 Minute Low price of the stock
    protected float calculatedEntryPrice = 0; // If order is placed to buy/sell stock
    protected int stockWatchSize = 0;
    protected float stockWatchAmount = 0;
    protected boolean ordersCalculated = false;
    protected int orderSize = 0;
    protected float orderAmount = 0;
    protected int orderError = ORDER_ERROR_DEFAULT;
    protected int sellStrategyWhenCreated = NO_SELL_STRATEGY;
    private Vector stockEvents;
    private final boolean hasStockEvents = false;
    private LinkedList stockEventList;

    private LocalDateTime rejectedDatetime;
    private final String rejectedDescription = "";
    private final int rejectedAgingNumDays = 1;
    private long rejectedStockId;  // Save the pk of the rejected_stock record so we can update when restored.


////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR
////////////////////////////////////////////////////////////////////////////////

    public Stock() {
        indicators = new CashMgmtIndicators();
    }

    /**
     * Create a new stock object.ol
     *
     * @param name   - the company name or id
     * @param symbol - the stock symbol
     * @param market - the market to track the stock on
     * @throws java.lang.Exception All parameters are required.  If any are missing, or is an empty string, an Exception is thrown.
     */
    public Stock(String name, String symbol, String market, int longOrShort) {
//      if( name != null && name.length() > 0 &&
//          symbol != null && symbol.length() > 0 &&
//          market != null && market.length() > 0 &&
//          ( longOrShort == Stock.LONG_STOCK || longOrShort == Stock.SHORT_STOCK ) )
//      {
//			stockEventList = new LinkedList ();
//			indicators = new CashMgmtIndicators();
//
//			daily_Stockdata = null;
//			sixtyminute_Stockdata = null;
//			fifteenminute_Stockdata = null;
//			fiveminute_Stockdata = null;
//			oneminute_Stockdata = null;
//			oneminute_history_interperiod_Stockdata = null;
//
//         this.name = name;
//         this.symbol = symbol;
//         this.market = market;
//         this.longOrShort = longOrShort;
//      }

    }


}
