package com.greenmark.core;

import com.greenmark.common.core.Labels;
import com.greenmark.utils.UTCalendarTime;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTXmlUtils;

/////////////////////////////////////////////////
// START CLASS: BucketEvent
/////////////////////////////////////////////////

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Seibel Consulting</p>
 * @author Monte Seibel
 * @version 1.0
 */

public class StockEvent
{
	/** This is the class name. */
	public static final String CLASSNAME = "StockEvent";

   public static final int EVENT_BUY_BUCKET = 1;
   public static final int EVENT_SELL_BUCKET = 2;
   public static final int EVENT_BUY_ORDER_EXECUTED = 3;
   public static final int EVENT_SELL_ORDER_EXECUTED = 4;
   public static final int EVENT_MARKET_ORDER_PLACED = 5;
   public static final int EVENT_UPDATE = 6;
	public static final int EVENT_BUY_ORDER_PLACED = 7;
   public static final int EVENT_SELL_ORDER_PLACED = 8;
	public static final int EVENT_BUY_ORDER_REPLACED = 9;
   public static final int EVENT_SELL_ORDER_REPLACED = 10;


   /** used by the database **/
   protected long id = 0;
	protected long positionId = 0; //set to zero to find insert errors
//   protected long stockId = 0;     //set to zero to find insert errors
   protected int active = Labels.OBJECT_ACTIVE;
   /**************************/

   protected int type;
   protected String description = "";
   protected java.util.Date datetime;
	protected int toTimeframe = 0;
	protected int fromTimeframe = 0;

   protected float thresholdStochK = 0f;
	protected float stochK = 0f;
	protected float macD = 0f;
	protected float rsiClassic = 0f;
	protected float ema5 = 0f;
	protected float ema20 = 0f;

   protected int numShares = 0;
	protected float price = 0;



	/**
	 *
	 */
	public StockEvent ()
	{
		// Subclass needs an empty constructor
	}

	public StockEvent( int event_type, int time_frame, Stock thisStock, UTCalendarTime date_time,
							 float inPrice, float threshold )
	{
//		try
//		{
//			Position thisStocksPosition = AccountManager.getPosition( thisStock );
//			if ( thisStocksPosition == null )
//				this.positionId = 0;
//			else
//				this.positionId = thisStocksPosition.getId();
//
//			this.type = event_type;
//			this.toTimeframe = time_frame;
//			this.thresholdStochK = threshold;
//			this.stochK = thisStock.getCurrentStochK();
//			this.macD = thisStock.getMacD();
//			this.rsiClassic = thisStock.getRsiClassic();
//			this.ema5 = thisStock.getEma5();
//			this.ema20 = thisStock.getEma20();
//			this.price = inPrice;
//
////if (trace.isLevelModel()) trace.addModel("In StockEvent constructor   todaysCal:  [" + date_time.formatDateTimeDisplay() + "]");
//
//			this.datetime = date_time.getJavaDate();
//
////if (trace.isLevelModel()) trace.addModel ("In StockEvent constructor   this.datetime: [" + UTDisplayFormatter.formatDateAndTime( this.datetime ) + "]");
//
////         String target = UTFormatter.roundFloat(threshold);
////			description = "Target ["+target+"]";
//		}
//		catch( Exception e )
//		{
//			System.out.println("Exception in "+CLASSNAME+".Constructor; message ["+e.getMessage()+"]");
//		}
	}

	public StockEvent( int event_type, int from_time_frame, int to_time_frame,
	   Stock thisStock, float inThresholdStochK, UTCalendarTime date_time, float inPrice,
		int num_shares, String inDescription )
	{
//		try
//		{
//		   Position thisStocksPosition = AccountManager.getPosition( thisStock );
//
//			if ( thisStocksPosition == null )
//				this.positionId = 0;
//			else
//				this.positionId = thisStocksPosition.getId();
//			this.type = event_type;
//			this.toTimeframe = to_time_frame;
//			this.fromTimeframe = from_time_frame;
//			this.thresholdStochK = inThresholdStochK;
//			this.stochK = thisStock.getCurrentStochK();
//			this.macD = thisStock.getMacD();
//			this.rsiClassic = thisStock.getRsiClassic();
//			this.ema5 = thisStock.getEma5();
//			this.ema20 = thisStock.getEma20();
//			this.price = inPrice;
//			this.numShares = num_shares;
//
////if (trace.isLevelModel()) trace.addModel("In StockEvent constructor   todaysCal:  [" + date_time.formatDateTimeDisplay() + "]");
//
//			this.datetime = date_time.getJavaDate();
//
////if (trace.isLevelModel()) trace.addModel ("In StockEvent constructor   this.datetime: [" + UTDisplayFormatter.formatDateAndTime( this.datetime ) + "]");
//
//			this.description = inDescription;
//		}
//		catch( Exception e )
//		{
//			System.out.println("Exception in "+CLASSNAME+".Constructor; message ["+e.getMessage()+"]");
//		}
	}

	public StockEvent( String xmldata )
	{
		try
		{
         //ID
			id = UTXmlUtils.getXmlDataAsLong( xmldata, "ID" );

	      //POSITION_ID
			positionId = UTXmlUtils.getXmlDataAsLong( xmldata, "POSITION_ID" );

			//EVENT_TYPE
			type = UTXmlUtils.getXmlDataAsInt( xmldata, "EVENT_TYPE" );

			//FROM_TIMEFRAME
			fromTimeframe = UTXmlUtils.getXmlDataAsInt( xmldata, "FROM_TIMEFRAME" );

			//TO_TIMEFRAME
			toTimeframe = UTXmlUtils.getXmlDataAsInt( xmldata, "TO_TIMEFRAME" );

			//CONFIGURED THRESHOLD  STOCH_K
			thresholdStochK = UTXmlUtils.getXmlDataAsFloat( xmldata, "THRESHOLD_STOCH_K" );

			//STOCH_K
			stochK = UTXmlUtils.getXmlDataAsFloat( xmldata, "STOCH_K" );

			//MACD
			macD = UTXmlUtils.getXmlDataAsFloat( xmldata, "MACD" );

			//RSI_CLASSIC
			rsiClassic = UTXmlUtils.getXmlDataAsFloat( xmldata, "RSI_CLASSIC" );

			//EMA5
			ema5 = UTXmlUtils.getXmlDataAsFloat( xmldata, "EMA5" );

			//EMA20
			ema20 = UTXmlUtils.getXmlDataAsFloat( xmldata, "EMA20" );

			//PRICE
			price = UTXmlUtils.getXmlDataAsFloat( xmldata, "PRICE" );

			//NUM_SHARES
			numShares = UTXmlUtils.getXmlDataAsInt ( xmldata, "NUM_SHARES" );

			//DATETIME
			UTCalendarTime thisDatetime = new UTCalendarTime (UTXmlUtils.getXmlData( xmldata, "DATETIME" ));
			datetime = thisDatetime.getJavaDate();

			//DESCRIPTION
			description = UTXmlUtils.getXmlData( xmldata, "DESCRIPTION" );

         //ACTIVE
			active = UTXmlUtils.getXmlDataAsInt ( xmldata, "ACTIVE" );
		}
		catch( Exception e )
		{
			System.out.println("Exception in "+CLASSNAME+".Constructor; message ["+e.getMessage()+"]");
		}

	}

	public final String toXml ( )
	{
		String prefix = "\t";
	   String endline = "\n";
		StringBuffer stb = new StringBuffer();
		String TAB = UTDisplayFormatter.TAB;

      stb.append( prefix + TAB + TAB + "<STOCK_EVENT>" + endline );
      stb.append( prefix + TAB + TAB + TAB + "<ID>" + this.id + "</ID>" + endline );
      stb.append( prefix + TAB + TAB + TAB + "<POSITION_ID>" + this.positionId + "</POSITION_ID>" + endline );
		stb.append( prefix + TAB + TAB + TAB + "<EVENT_TYPE>" + this.type + "</EVENT_TYPE>" + endline );
		stb.append( prefix + TAB + TAB + TAB + "<FROM_TIMEFRAME>" + this.fromTimeframe + "</FROM_TIMEFRAME>" + endline );
		stb.append( prefix + TAB + TAB + TAB + "<TO_TIMEFRAME>" + this.toTimeframe + "</TO_TIMEFRAME>" + endline );
		stb.append( prefix + TAB + TAB + TAB + "<THRESHOLD_STOCH_K>" + this.thresholdStochK + "</THRESHOLD_STOCH_K>" + endline );
		stb.append( prefix + TAB + TAB + TAB + "<STOCH_K>" + this.stochK + "</STOCH_K>" + endline );
		stb.append( prefix + TAB + TAB + TAB + "<MACD>" + this.macD + "</MACD>" + endline );
		stb.append( prefix + TAB + TAB + TAB + "<RSI_CLASSIC>" + this.rsiClassic + "</RSI_CLASSIC>" + endline );
		stb.append( prefix + TAB + TAB + TAB + "<EMA5>" + this.ema5 + "</EMA5>" + endline );
		stb.append( prefix + TAB + TAB + TAB + "<EMA20>" + this.ema20 + "</EMA20>" + endline );
		stb.append( prefix + TAB + TAB + TAB + "<PRICE>" + this.price + "</PRICE>" + endline );
		stb.append( prefix + TAB + TAB + TAB + "<NUM_SHARES>" + this.numShares + "</NUM_SHARES>" + endline );

//System.out.println ("In StockEvent.toXml   this.datetime: [" + UTDisplayFormatter.formatDateAndTime( this.datetime ) + "]");

		UTCalendarTime thisDatetime = new UTCalendarTime ( this.datetime );
		stb.append( prefix + TAB + TAB + TAB + "<DATETIME>" + thisDatetime.formatXMLDateTime(true) + "</DATETIME>" + endline );

//System.out.println ("In StockEvent.toXml   thisDatetime.formatXMLDateTime: [" + thisDatetime.formatXMLDateTime(true) + "]");

	stb.append( prefix + TAB + TAB + TAB + "<DESCRIPTION>" + this.description + "</DESCRIPTION>" + endline );
      stb.append( prefix + TAB + TAB + TAB + "<ACTIVE>" + this.getActive() + "</ACTIVE>" + endline );
		stb.append( prefix + TAB + TAB + "</STOCK_EVENT>" + endline );

		return stb.toString();
	}

	public final String getKey ()
	{
		return type + ":" + toTimeframe + ":" + datetime.toString();
   }

	public final boolean equals( Object _other )
	{
		if( _other == null )
			return false;

		if( _other == this )
			return true;

		if( ! ( _other instanceof StockEvent ) )
			return false;

		final StockEvent _cast = ( StockEvent ) _other;

		if ( type != _cast.type )
			return false;

		if( toTimeframe != _cast.toTimeframe )
			return false;

		if( !datetime.toString().equals( _cast.datetime.toString() ) )
			return false;

		return true;
	}

	public final String toString()
	{
		String methodname = "toString";
		StringBuffer outString = new StringBuffer();

		try
		{
			///  DISPLAY THE EVENT TYPE STRING
			if ( ( type == EVENT_BUY_BUCKET ) || ( type == EVENT_SELL_BUCKET ) )
			{
				if ( fromTimeframe == toTimeframe )
					outString.append("        New      ");
				else
				{
					switch (fromTimeframe)
					{
						case Labels.TYPE_DAILY:
							outString.append("Daily Bucket     ");
							break;
						case Labels.TYPE_MINUTE60:
							outString.append("60 Minute Bucket ");
							break;
						case Labels.TYPE_MINUTE15:
							outString.append("15 Minute Bucket ");
							break;
						case Labels.TYPE_MINUTE05:
							outString.append(" 5 Minute Bucket ");
							break;
						case Labels.TYPE_MINUTE01:
							outString.append(" 1 Minute Bucket ");
							break;
						default:
							outString.append("                 ");
					}
				}

				switch (toTimeframe)
				{
					case Labels.TYPE_DAILY:
						outString.append("Daily Bucket     ");
						break;
					case Labels.TYPE_MINUTE60:
						outString.append("60 Minute Bucket ");
						break;
					case Labels.TYPE_MINUTE15:
						outString.append("15 Minute Bucket ");
						break;
					case Labels.TYPE_MINUTE05:
						outString.append(" 5 Minute Bucket ");
						break;
					case Labels.TYPE_MINUTE01:
						outString.append(" 1 Minute Bucket ");
						break;
					default:
						outString.append("                 ");
				}
			}
			else if ( type == EVENT_BUY_ORDER_PLACED )
			{
				outString.append("         Buy Order Placed         ");
			}
			else if ( type == EVENT_SELL_ORDER_PLACED )
			{
				outString.append("        Sell Order Placed         ");
			}
			else if ( type == EVENT_BUY_ORDER_REPLACED )
			{
				outString.append("         Buy Order Replaced         ");
			}
			else if ( type == EVENT_SELL_ORDER_REPLACED )
			{
				outString.append("        Sell Order Replaced         ");
			}
			else if ( type == EVENT_BUY_ORDER_EXECUTED )
			{
				outString.append("        Buy Order Executed        ");
         }
			else if ( type == EVENT_SELL_ORDER_EXECUTED )
			{
				outString.append("       Sell Order Executed        ");
			}
			else if ( type == EVENT_MARKET_ORDER_PLACED )
			{
				outString.append("       Market Order Placed        ");
			}
			else if ( type == EVENT_UPDATE )
			{
				switch (toTimeframe)
				{
					case Labels.TYPE_DAILY:
						outString.append("                 Daily Bucket     ");
						break;
					case Labels.TYPE_MINUTE60:
						outString.append("                 60 Minute Bucket ");
						break;
					case Labels.TYPE_MINUTE15:
						outString.append("                 15 Minute Bucket ");
						break;
					case Labels.TYPE_MINUTE05:
						outString.append("                  5 Minute Bucket ");
						break;
					case Labels.TYPE_MINUTE01:
						outString.append("                  1 Minute Bucket ");
						break;
					default:
						outString.append("                                  ");
				}
			}
			else
				outString.append("       Unknown Event Type         ");

			outString.append("  ");
			UTCalendarTime thisDatetime = new UTCalendarTime ( this.datetime );
			outString.append( thisDatetime.formatXMLDateTime(false) );
			outString.append("  ");

			///  DISPLAY THE CONFIGURED THRESHOLD STOCH_K
			if ( thresholdStochK == 0f )
				outString.append( "N/A" );
//			else
//				outString.append( UTDisplayFormatter.formatKSlow( thresholdStochK ) );
			outString.append("   ");


			///  DISPLAY THE STOCH_K
//			if ( ( eventType == EVENT_BUY_BUCKET ) || ( eventType == EVENT_SELL_BUCKET ) )
//				outString.append( UTDisplayFormatter.formatKSlow( stochK ) );
//			else
//			   outString.append(" N/A");

			outString.append("  ");

			///  DISPLAY THE MACD
//			if ( ( eventType == EVENT_BUY_BUCKET ) || ( eventType == EVENT_SELL_BUCKET ) )
				outString.append( UTDisplayFormatter.formatMacD( macD ) );
//			else
//			   outString.append(" N/A ");

			outString.append(" ");

			///  DISPLAY THE PRICE (EXECUTED OR ENTRY)
			if ( ( type == EVENT_BUY_ORDER_PLACED ) ||
				  ( type == EVENT_SELL_ORDER_PLACED ) ||
				  ( type == EVENT_BUY_ORDER_REPLACED ) ||
				  ( type == EVENT_SELL_ORDER_REPLACED ) ||
				  ( type == EVENT_BUY_ORDER_EXECUTED ) ||
				  ( type == EVENT_SELL_ORDER_EXECUTED ) ||
				  ( type == EVENT_MARKET_ORDER_PLACED ) )
				outString.append( UTDisplayFormatter.formatPriceDisplay( price ) );
			else
				outString.append("  N/A  ");

			outString.append(" ");

			///  DISPLAY THE NUM_SHARES (EXECUTED OR ENTRY)
			if ( ( type == EVENT_BUY_ORDER_PLACED ) ||
			     ( type == EVENT_SELL_ORDER_PLACED ) ||
				  ( type == EVENT_BUY_ORDER_REPLACED ) ||
				  ( type == EVENT_SELL_ORDER_REPLACED ) ||
				  ( type == EVENT_BUY_ORDER_EXECUTED ) ||
			     ( type == EVENT_SELL_ORDER_EXECUTED ) ||
				  ( type == EVENT_MARKET_ORDER_PLACED ) )
				outString.append( UTDisplayFormatter.formatNumShares( numShares, 4 ) );
			else
				outString.append(" N/A ");

			outString.append("   ");
			outString.append( description );
		}
		catch( Exception e )
		{
			System.out.println("Exception in "+CLASSNAME+"."+methodname+"; message ["+e.getMessage()+"]");
		}
		return outString.toString();
	}


   public java.util.Date getDatetime()
   {
      return datetime;
   }

   public void setDatetime(java.util.Date datetime)
   {
      this.datetime = datetime;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public float getMacD()
   {
      return macD;
   }

   public void setMacD(float macD)
   {
      this.macD = macD;
   }

   public float getStochK()
   {
      return stochK;
   }

   public void setStochK(float stochK)
   {
      this.stochK = stochK;
   }

   public int getToTimeframe()
   {
      return toTimeframe;
   }

   public void setToTimeframe(int timeframe)
   {
      this.toTimeframe = timeframe;
   }


	public String formatType ()
	{
		String outString = "UNKNOWN";

		switch( type )
		{
			case (EVENT_BUY_BUCKET):
			{
				return "BUY BUCKET";
			}
			case (EVENT_SELL_BUCKET):
			{
				return "SELL BUCKET";
			}
			case (EVENT_BUY_ORDER_PLACED):
			{
				return "BUY ORDER PLACED";
			}
			case (EVENT_SELL_ORDER_PLACED):
			{
				return "SELL ORDER PLACED";
			}
			case (EVENT_BUY_ORDER_EXECUTED):
			{
				return "BUY ORDER EXECUTED";
			}
			case (EVENT_SELL_ORDER_EXECUTED):
			{
				return "SELL ORDER EXECUTED";
			}
			case (EVENT_MARKET_ORDER_PLACED):
			{
				return "MARKET ORDER PLACED";
			}
			case (EVENT_UPDATE):
			{
				return "UPDATE";
			}

		}//End switch

		return outString;
	}

   public int getType()
   {
      return type;
   }

   public void setType(int eventType)
   {
      this.type = eventType;
   }

   public int getNumShares()
   {
      return numShares;
   }

   public void setNumShares(int numShares)
   {
      this.numShares = numShares;
   }

   public float getPrice()
   {
      return price;
   }

   public void setPrice(float price)
   {
      this.price = price;
   }

   public float getRsiClassic()
   {
      return rsiClassic;
   }

   public void setRsiClassic(float rsiClassic)
   {
      this.rsiClassic = rsiClassic;
   }

   public float getEma20()
   {
      return ema20;
   }

   public void setEma20(float ema20)
   {
      this.ema20 = ema20;
   }

   public float getEma5()
   {
      return ema5;
   }

   public void setEma5(float ema5)
   {
      this.ema5 = ema5;
   }

  public float getThresholdStochK() {
    return thresholdStochK;
  }

   public long getId()
   {
      return id;
   }

   public void setThresholdStochK(float thresholdStochK) {
    this.thresholdStochK = thresholdStochK;
  }

   public int getActive()
   {
      return active;
   }

   public void setActive(int active)
   {
      this.active = active;
   }

	public int getFromTimeframe()
	{
		return fromTimeframe;
	}

	public void setFromTimeframe(int fromTimeframe)
	{
		this.fromTimeframe = fromTimeframe;
	}
/**
	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}
**/
	public String dbSummary()
	{
		StringBuffer stb = new StringBuffer();
		stb.append( " > > " + CLASSNAME + ":");
		stb.append( "stockEventId [" +id+ "] ");
		stb.append( "positionId [" +positionId+ "] ");
		stb.append( "active [" +active+ "] ");
		return stb.toString();
	}

   public void setId(long id)
   {
      this.id = id;
   }

   public String getClassname()
   {
      return CLASSNAME;
   }

   public long getPositionId()
   {
      return positionId;
   }

   public void setPositionId(long positionId)
   {
      this.positionId = positionId;
   }

}

