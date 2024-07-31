package com.greenmark.core;

import com.greenmark.common.core.Labels;
import com.greenmark.utils.UTDisplayFormatter;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.Hashtable;

import static com.greenmark.core.enums.PositionStatus.POSITION_STATUS_NORMAL;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Seibel Consulting</p>
 *
 * @author not attributable
 * @version 1.0
 */
@Slf4j
public class Position {

    public static final String CLASSNAME = "CurrentPosition";

    protected long id;       //leave as null for hibernate
    protected long accountId = 0;      //set to zero to find insert errors
    protected long securityId = 0;
    protected int active = Labels.OBJECT_ACTIVE;
    protected int longOrShort; // Needed for DB retrievals only
    protected String stockSymbol; // Needed for DB retrievals only
    /**************************/

    //  Buy Order Summary Info
    protected float buyExecutedPrice = 0;
    protected float buyEntryPrice = 0;
    protected int buyExecutedNumShares = 0;
    protected LocalDateTime buyExecutedDatetime;
    //  Sell Order Summary Info
    protected float sellExecutedPrice = 0;
    protected float sellEntryPrice = 0;
    protected int sellExecutedNumShares = 0;
    protected LocalDateTime sellExecutedDatetime;
    protected float painPrice = 0;
    protected float percentGrowth = 0;
    protected float totalGrowthAmount = 0;
    protected float currentPrice = 0;
    protected Hashtable buyOrderList = new Hashtable();
    protected int numBuyOrders = 0;
    protected boolean hasBuyOrders;
    protected Hashtable sellOrderList = new Hashtable();
    protected int numSellOrders = 0;
    protected boolean hasSellOrders;
    protected String positionID = "0";  // Default to zero, which we can check for later.
    protected boolean positionPainedOutNormally = false;
    //  Members used by website queries
    protected int totalNumMin60Disqualified;
    protected int totalNumMin15Disqualified;
    protected int totalNumMin05Disqualified;
    //  Used when we pain on a position and try to recover.
    protected int positionStatus = POSITION_STATUS_NORMAL;


    public Position() {
        buyOrderList = new Hashtable();
        sellOrderList = new Hashtable();
//		positionSellOrderList = new Vector();
//		positionBuyOrderList = new Vector();
    }

    public Position(Order placedBuyOrder) {
//      //this.stockId = placedBuyOrder.getStockId();
//		 buyOrderList = new Hashtable();
//		 sellOrderList = new Hashtable();
////		 positionSellOrderList = new Vector();
////		positionBuyOrderList = new Vector();
//
//      // Create the positionID of the first buy order's orderID, it will be unique.
//		 positionID = placedBuyOrder.getExternalOrderId();
//
//	    //longOrShort = placedBuyOrder.getStock().getLongOrShort();
//
//		 buyEntryPrice = placedBuyOrder.getEntryPrice();
//      buyExecutedPrice = placedBuyOrder.getExecutedPrice();
//      currentPrice = buyExecutedPrice;
//      buyExecutedNumShares = placedBuyOrder.getExecutedNumShares();
//      stock = placedBuyOrder.getStock();
//      buyExecutedDatetime = new UTCalendarTime(placedBuyOrder.getExecutedDatetime());
//
//      // Calculate the pain price
//      float painThreshold = Config.getValueAsFloat( ModelLogic.MODEL_PAIN_THRESHOLD_LONG );
//		if ( placedBuyOrder.getStock().getLongOrShort() == Stock.SHORT_STOCK )
//			painThreshold = Config.getValueAsFloat( ModelLogic.MODEL_PAIN_THRESHOLD_SHORT );
//
//      float painPercent = painThreshold / 100;
//
//      if( stock.getLongOrShort() == Stock.LONG_STOCK )
//      {
//         painPrice = buyExecutedPrice - ( buyExecutedPrice * painPercent );
//      }
//      else
//      {
//         painPrice = buyExecutedPrice + ( buyExecutedPrice * painPercent );
//      }
//
//      //  Add it to the Buy Order List
//		addBuyOrder ( placedBuyOrder );

    }
//
//   public Position( Position sourcePosition )
//   {
//		buyOrderList = new Hashtable();
//		sellOrderList = new Hashtable();
////		positionSellOrderList = new Vector();
////		positionBuyOrderList = new Vector();
//
//	   // Copy over Buy Order List
//	   for (Enumeration e = sourcePosition.getBuyOrderList().keys(); e.hasMoreElements() ;)
//      {
// 	      String orderID = (String) e.nextElement();
// 	      Order thisOrder = (Order) sourcePosition.getBuyOrderList().get( orderID );
//			addBuyOrder ( thisOrder );
//      }
//
//		// Copy over Sell Order List
//		for (Enumeration e = sourcePosition.getSellOrderList().keys(); e.hasMoreElements() ;)
//	   {
//			 String orderID = (String) e.nextElement();
//			 Order thisOrder = (Order) sourcePosition.getSellOrderList().get( orderID );
//			 addSellOrder ( thisOrder );
//	   }
//
//      positionID = sourcePosition.getPositionID();
//		accountId = sourcePosition.getAccountId();
//		securityId = sourcePosition.getSecurityId();
//		stock = sourcePosition.getStock();
//		currentPrice = sourcePosition.getCurrentPrice();
//		longOrShort = sourcePosition.getLongOrShort();
//		stockSymbol = sourcePosition.getStockSymbol();
//
//      buyExecutedPrice = sourcePosition.getBuyExecutedPrice();
//		buyEntryPrice = sourcePosition.getBuyEntryPrice();
//      buyExecutedNumShares = sourcePosition.getBuyExecutedNumShares();
//      buyExecutedDatetime = sourcePosition.getBuyExecutedDatetime();
//
//		sellExecutedPrice = sourcePosition.getSellExecutedPrice();
//		sellEntryPrice = sourcePosition.getSellEntryPrice();
//		sellExecutedNumShares = sourcePosition.getSellExecutedNumShares();
//		sellExecutedDatetime = sourcePosition.getSellExecutedDatetime();
//
//      painPrice = sourcePosition.getPainPrice();
//      percentGrowth = sourcePosition.getPercentGrowth();
//		totalGrowthAmount = sourcePosition.getTotalGrowthAmount();
//		totalNumMin60Disqualified = sourcePosition.getTotalNumMin60Disqualified();
//		totalNumMin15Disqualified = sourcePosition.getTotalNumMin15Disqualified();
//		totalNumMin05Disqualified = sourcePosition.getTotalNumMin05Disqualified();
//
//		positionStatus = sourcePosition.getPositionStatus();
//		dropcatPainPrice = sourcePosition.getDropcatPainPrice();
//   }
/*
   public CurrentPosition( float inExecutedPrice, float inCurrentPrice, int inExecutedShares,
                           Stock inStock, java.util.Date inDate, float inPain, float inGrowth )
   {
      executedPrice = inExecutedPrice;
      currentPrice = inCurrentPrice;
      executedNumShares = inExecutedShares;
      stock = inStock;
      executedDatetime = inDate;
      painPrice = inPain;
      percentGrowth = inGrowth;
   }
*/

    /**  Returns the total amount of this stock to be included in Short/Long Positions total.   */
    /**
     * newPrice is either the currentClosePrice or the sellPrice, depending on who's calling this method.
     */
    public float calcPositionBalances(float newPrice, int numShares) {
        float totalStockValue = 0f;

        try {
            // Recalculate Account Balances

            //if (stock.getLongOrShort() == Stock.LONG_STOCK)
            boolean isTrue = true;
            if (isTrue) {
                totalStockValue = newPrice * numShares;
                // Calculate the Position's %Growth
                float percentGrowth = (newPrice - getBuyExecutedPrice()) /
                        getBuyExecutedPrice();
                // Now turn decimal into a percentage
                percentGrowth *= 100;
                setPercentGrowth(percentGrowth);

                float totalGrowthAmt = (newPrice - getBuyExecutedPrice()) *
                        (float) getBuyExecutedNumShares();
                setTotalGrowthAmount(totalGrowthAmt);

                log.debug("Calculated Position LONG Stock: [" + "stock.getSymbol()" + "] " +
                        "Curr Price: [" + newPrice + "] " +
                        "Buy Price: [" + getBuyExecutedPrice() + "] " +
                        "Exec Num Shares: [" + getBuyExecutedNumShares() + "] " +
                        "% Growth: [" + percentGrowth + "] " +
                        "Tot Growth Amt: [" + totalGrowthAmt + "] ");
            } else {
                // Recalculate Account Balances
                // Short positions are different, you add the difference between buy and current price
                // to the purchase amount.
                float profitOnPrice = getBuyEntryPrice() - newPrice;
                float shortStockPriceActualValue = getBuyEntryPrice() + profitOnPrice;
                totalStockValue = shortStockPriceActualValue * numShares;

                // Calculate the Position's %Growth
                float percentGrowth = profitOnPrice / getBuyExecutedPrice();

                // Now turn decimal into a percentage
                percentGrowth *= 100;
                setPercentGrowth(percentGrowth);

                float totalGrowthAmt = profitOnPrice * getBuyExecutedNumShares();
                setTotalGrowthAmount(totalGrowthAmt);

                log.debug("Calculated Position SHORT Stock: [" + "stock.getSymbol()" + "] " +
                        "New Price: [" + newPrice + "] " +
                        "Curr Price: [" + currentPrice + "] " +
                        "Buy Price: [" + getBuyExecutedPrice() + "] " +
                        "Profit on Price: [" + profitOnPrice + "] " +
                        "Exec Num Shares: [" + getBuyExecutedNumShares() + "] " +
                        "% Growth: [" + percentGrowth + "] " +
                        "Tot Growth Amt: [" + totalGrowthAmt + "] ");
            } //Endif Short position
        } catch (Exception ex) {
            log.debug("============= ERROR IN POSITION!   calcPositionBalances     Exception Message:  [" +
                    ex.getMessage() + "]");
        }

        return (totalStockValue);
    }


    public String toString() {
        String methodname = "toString";
        StringBuffer outString = new StringBuffer();

        try {
//			outString.append( "  " );
//			outString.append( UTDisplayFormatter.padString( stock.getSymbol(), 5 ) );
//			outString.append( " ");
            outString.append(UTDisplayFormatter.formatNumShares(buyExecutedNumShares, 4));
            outString.append(" ");
//			outString.append( UTDisplayFormatter.formatDateTime(buyExecutedDatetime) );
//			outString.append( " " );
            outString.append(UTDisplayFormatter.formatPriceDisplay(buyExecutedPrice));
            outString.append("   ");
            outString.append(UTDisplayFormatter.formatPriceDisplay(currentPrice));
            outString.append("   ");
            outString.append(UTDisplayFormatter.formatPriceDisplay(painPrice));
            outString.append("  ");
            outString.append(UTDisplayFormatter.formatPercent(percentGrowth));
            outString.append("  ");
            outString.append(UTDisplayFormatter.formatPriceDisplay(totalGrowthAmount));
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
        }
        return outString.toString();
    }

    public String toStringSystemOut() {
        StringBuffer stb = new StringBuffer();
        String formattedBuyDate = "";
        if (buyExecutedDatetime != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            formattedBuyDate = formatter.format(buyExecutedDatetime);
        }
        String formattedSellDate = "";
        if (sellExecutedDatetime != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            formattedSellDate = formatter.format(sellExecutedDatetime);
        }

        stb.append("-----------------------------------------------\n");
        //stb.append( "Position for stock= [" + stock.getSymbol()  + "]\n");
        //stb.append( "   current_price=" + currentPrice + "]\n" );
        stb.append("   Buy executed_price=" + buyExecutedPrice + "]\n");
        stb.append("   Buy executed_num_shares=" + buyExecutedNumShares + "]\n");
        stb.append("   Buy executed_date=" + formattedBuyDate + "]\n");
        stb.append("   Sell executed_price=" + sellExecutedPrice + "]\n");
        stb.append("   Sell executed_num_shares=" + sellExecutedNumShares + "]\n");
        stb.append("   Sell executed_date=" + formattedSellDate + "]\n");
        stb.append("   pain_price=" + painPrice + "]\n");
        stb.append("   percent_growth=" + percentGrowth + "]\n");
        stb.append("-----------------------------------------------\n");
        return stb.toString();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////   BUY ORDER LIST CONVENIENCE FUNCTIONS
    public int getNumBuyOrders() {
        return numBuyOrders;
    }

    public void setNumBuyOrders(int numBuyOrders) {
        this.numBuyOrders = numBuyOrders;
    }

    public Hashtable getBuyOrderList() {
        return buyOrderList;
    }

    public void setBuyOrderList(Hashtable buyOrderList) {
        this.buyOrderList = buyOrderList;
        if (buyOrderList.size() > 0) {
            this.hasBuyOrders = true;
            this.numBuyOrders = buyOrderList.size();
        } else {
            this.hasBuyOrders = false;
            this.numBuyOrders = 0;
        }
    }

    public final boolean hasBuyOrder(String orderID) {
        return buyOrderList.containsKey(orderID);
    }

    public Order getBuyOrder(String orderID) {
        if (buyOrderList.containsKey(orderID))
            return (Order) buyOrderList.get(orderID);
        else
            return null;
    }

    public void addBuyOrder(Order order) {
        buyOrderList.put(order.getExternalOrderId(), order);
        numBuyOrders++;
    }

    public boolean removeBuyOrder(String orderID) {
        if (buyOrderList.containsKey(orderID)) {
            buyOrderList.remove(orderID);
            numBuyOrders--;
            return true;
        } else
            return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////   SELL ORDER LIST CONVENIENCE FUNCTIONS
    public int getNumSellOrders() {
        return numSellOrders;
    }

    public void setNumSellOrders(int numSellOrders) {
        this.numSellOrders = numSellOrders;
    }

    public Hashtable getSellOrderList() {
        return sellOrderList;
    }

    public void setSellOrderList(Hashtable sellOrderList) {
        this.sellOrderList = sellOrderList;
        if (sellOrderList.size() > 0) {
            this.hasSellOrders = true;
            this.numSellOrders = sellOrderList.size();
        } else {
            this.hasSellOrders = false;
            this.numSellOrders = 0;
        }

    }

    public boolean hasSellOrder(String orderID) {
        return sellOrderList.containsKey(orderID);
    }

    public Order getSellOrder(String orderID) {
        if (sellOrderList.containsKey(orderID))
            return (Order) sellOrderList.get(orderID);
        else
            return null;
    }

    public void addSellOrder(Order placedSellOrder) {
        sellEntryPrice = placedSellOrder.getEntryPrice();
        sellExecutedPrice = placedSellOrder.getExecutedPrice();
        sellExecutedNumShares = placedSellOrder.getExecutedNumShares();
        //sellExecutedDatetime = new UTCalendarTime (placedSellOrder.getExecutedDatetime());

        sellOrderList.put(placedSellOrder.getExternalOrderId(), placedSellOrder);
        numSellOrders++;
    }

    public boolean removeSellOrder(String orderID) {
        if (sellOrderList.containsKey(orderID)) {
            sellOrderList.remove(orderID);
            numSellOrders--;
            return true;
        } else
            return false;
    }

    public float getAllBuyOrderEntryPricesAsOne() {
        int total_shares = 0;
        float total_price = 0;

        //loop each order computing the number shares and pricw
        for (Enumeration e = buyOrderList.keys(); e.hasMoreElements(); ) {
            String name = (String) e.nextElement();
            Order order = (Order) buyOrderList.get(name);

            //get the number of shares
            int shares = order.getTargetSizeInShares();
            total_shares = total_shares + shares;

            //figure out the total spent
            float price = order.getEntryPrice();
            total_price = total_price + (shares * price);
        }

        //divide the total price by total shares for entry price of all orders
        float freturn = total_price / total_shares;

        return freturn;
    }

    public float getAllBuyOrderExecPricesAsOne() {
        int total_shares = 0;
        float total_price = 0;

        //loop each order computing the number shares and pricw
        for (Enumeration e = buyOrderList.keys(); e.hasMoreElements(); ) {
            String name = (String) e.nextElement();
            Order order = (Order) buyOrderList.get(name);

            //get the number of shares
            int shares = order.getExecutedNumShares();
            total_shares = total_shares + shares;

            //figure out the total spent
            float price = order.getExecutedPrice();
            total_price = total_price + (shares * price);
        }

        //divide the total price by total shares for entry price of all orders
        float freturn = total_price / total_shares;

        return freturn;
    }

    public float getAllSellOrderEntryPricesAsOne() {
        int total_shares = 0;
        float total_price = 0;

        //loop each order computing the number shares and pricw
        for (Enumeration e = sellOrderList.keys(); e.hasMoreElements(); ) {
            String name = (String) e.nextElement();
            Order order = (Order) sellOrderList.get(name);

            //get the number of shares
            int shares = order.getTargetSizeInShares();
            total_shares = total_shares + shares;

            //figure out the total spent
            float price = order.getEntryPrice();
            total_price = total_price + (shares * price);
        }

        //divide the total price by total shares for entry price of all orders
        float freturn = total_price / total_shares;

        return freturn;
    }

    public float getAllSellOrderExecPricesAsOne() {
        int total_shares = 0;
        float total_price = 0;

        //loop each order computing the number shares and pricw
        for (Enumeration e = sellOrderList.keys(); e.hasMoreElements(); ) {
            String name = (String) e.nextElement();
            Order order = (Order) sellOrderList.get(name);

            //get the number of shares
            int shares = order.getExecutedNumShares();
            total_shares = total_shares + shares;

            //figure out the total spent
            float price = order.getExecutedPrice();
            total_price = total_price + (shares * price);
        }

        //divide the total price by total shares for entry price of all orders
        float freturn = total_price / total_shares;

        return freturn;
    }

    public String getAllBuyOrderReasonsAsOne() {

        boolean first = true;

        //loop each order computing the number shares and pricw
//      for (Enumeration e = buyOrderList.keys() ; e.hasMoreElements() ;)
//      {
//         String name = ( String ) e.nextElement();
//         Order order = ( Order ) buyOrderList.get( name ) ;
//         String thisreason = order.formatOrderReason();
//
//         //different for first reason
//         if ( first)
//         {
//            first = false;
//            reasons.append( thisreason );
//         }
//         else
//         {
//            reasons.append( ", " + thisreason );
//         }
//      }
        return "";
    }


    public String getAllSellOrderReasonsAsOne() {

        boolean first = true;

//   //loop each order computing the number shares and pricw
//      for (Enumeration e = sellOrderList.keys() ; e.hasMoreElements() ;)
//      {
//         String name = ( String ) e.nextElement();
//         Order order = ( Order ) sellOrderList.get( name ) ;
//         String thisreason = order.formatOrderReason();
//
//         //different for first reason
//         if ( first)
//         {
//            first = false;
//            reasons.append( thisreason );
//         }
//         else
//         {
//            reasons.append( ", " + thisreason );
//         }
//      }


        return "";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////   PUBLIC ACCESSORS CONVENIENCE FUNCTIONS
    public LocalDateTime getBuyExecutedDatetime() {
        return buyExecutedDatetime;
    }


    //public void setBuyExecutedDatetime( UTCalendarTime executedDate )   {      this.buyExecutedDatetime = executedDate;   }

    public int getBuyExecutedNumShares() {
        return buyExecutedNumShares;
    }

    public void setBuyExecutedNumShares(int executedNumShares) {
        this.buyExecutedNumShares = executedNumShares;
    }

    public float getBuyExecutedPrice() {
        return buyExecutedPrice;
    }

    public void setBuyExecutedPrice(float executedPrice) {
        this.buyExecutedPrice = executedPrice;
    }

    public float getPainPrice() {
        return painPrice;
    }

    public void setPainPrice(float painPrice) {
        this.painPrice = painPrice;
    }

    public float getPercentGrowth() {
        return percentGrowth;
    }

    public void setPercentGrowth(float percentGrowth) {
        this.percentGrowth = percentGrowth;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getPositionID() {
        return positionID;
    }

    public void setPositionID(String positionID) {
        this.positionID = positionID;
    }


    public int getSellExecutedNumShares() {
        return sellExecutedNumShares;
    }

    public void setSellExecutedNumShares(int sellExecutedNumShares) {
        this.sellExecutedNumShares = sellExecutedNumShares;
    }

    public float getSellExecutedPrice() {
        return sellExecutedPrice;
    }

    public void setSellExecutedPrice(float sellExecutedPrice) {
        this.sellExecutedPrice = sellExecutedPrice;
    }

    public float getTotalGrowthAmount() {
        return totalGrowthAmount;
    }

    public void setTotalGrowthAmount(float totalGrowthAmt) {
        this.totalGrowthAmount = totalGrowthAmt;
    }

    public float getBuyEntryPrice() {
        return buyEntryPrice;
    }

    public void setBuyEntryPrice(float buyEntryPrice) {
        this.buyEntryPrice = buyEntryPrice;
    }

    public float getSellEntryPrice() {
        return sellEntryPrice;
    }

    public void setSellEntryPrice(float sellEntryPrice) {
        this.sellEntryPrice = sellEntryPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int value) {
        this.active = value;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String dbSummary() {
        String stb = " > > " + CLASSNAME + " :: " +
                "id [" + id + "] " +
                "accountId [" + accountId + "] " +
                "active [" + active + "] ";
        return stb;
    }

    public String getClassname() {
        return CLASSNAME;
    }

    // Fixed a bug where the buy order lists where not getting re-initialized...?
    public void initOrderLists() {
        buyOrderList = null;
        buyOrderList = new Hashtable();
        sellOrderList = null;
        sellOrderList = new Hashtable();
    }


    public boolean isHasBuyOrders() {
        return hasBuyOrders;
    }

    public void setHasBuyOrders(boolean hasBuyOrders) {
        this.hasBuyOrders = hasBuyOrders;
    }

    public boolean isHasSellOrders() {
        return hasSellOrders;
    }

    public void setHasSellOrders(boolean hasSellOrders) {
        this.hasSellOrders = hasSellOrders;
    }

    public long getSecurityId() {
        return securityId;
    }

    public void setSecurityId(long securityId) {
        this.securityId = securityId;
    }

    public int getLongOrShort() {
        return longOrShort;
    }

    public void setLongOrShort(int longOrShort) {
        this.longOrShort = longOrShort;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getTotalNumMin60Disqualified() {
        return totalNumMin60Disqualified;
    }

    public void setTotalNumMin60Disqualified(int totalNumMin60Disqualified) {
        this.totalNumMin60Disqualified = totalNumMin60Disqualified;
    }

    public int getTotalNumMin15Disqualified() {
        return totalNumMin15Disqualified;
    }

    public void setTotalNumMin15Disqualified(int totalNumMin15Disqualified) {
        this.totalNumMin15Disqualified = totalNumMin15Disqualified;
    }

    public int getTotalNumMin05Disqualified() {
        return totalNumMin05Disqualified;
    }

    public void setTotalNumMin05Disqualified(int totalNumMin05Disqualified) {
        this.totalNumMin05Disqualified = totalNumMin05Disqualified;
    }

    public int getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(int positionStatus) {
        this.positionStatus = positionStatus;
    }

    public boolean isPositionPainedOutNormally() {
        return positionPainedOutNormally;
    }

    public void setPositionPainedOutNormally(boolean positionPainedOutNormally) {
        this.positionPainedOutNormally = positionPainedOutNormally;
    }

    public int isPositionPainedOutNormallyForDB() {
        if (positionPainedOutNormally)
            return 1;
        else
            return 0;
    }

    public void setPositionPainedOutNormallyForDB(int positionPainedOutNormal) {
        positionPainedOutNormally = positionPainedOutNormal == 1;
    }


}
