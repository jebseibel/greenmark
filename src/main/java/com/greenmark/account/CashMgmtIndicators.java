package com.greenmark.account;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2003</p>
 *
 * <p>Company: Seibel Consulting</p>
 *
 * @author not attributable
 * @version 1.0
 */
public class CashMgmtIndicators
{
	/** This is the class name. */
   public static final String CLASSNAME = "CashMgmtIndicators";

	private float   harvestHighLowPrice = 0;
	private float   harvestHighLowPriceThreshold = 0;
	private float   harvestCurrentPrice = 0;
	private float   harvestCurrentVolume = 0;
	private float   harvestStochk = 0;
	private float   harvestStochkThreshold = 0;
	private float   harvestDailySma5 = 0;
	private float   harvestDailySma20 = 0;
	private float   harvestDailySma50 = 0;

	private float   exitDailyStochk = 0;
	private float   exitDailyStochkThreshold = 0;
	private float   exitDailyLowestHighestStochk = 0;
	private float   exitDailyModifiedStochKThreshold = 0;

	private float   exitMin60Stochk = 0;
	private float   exitMin60StochkThreshold = 0;
	private float   exitMin60LowestHighestStochk = 0;
	private float   exitMin60ModifiedStochKThreshold = 0;

	private float   exitMin15Stochk = 0;
	private float   exitMin15StochkThreshold = 0;

	private float   buyPlacedMin5Stochk = 0;
	private float   buyPlacedMin5StochkThreshold = 0;
	private float   buyPlacedDailySma5 = 0;
	private float   buyPlacedDailySma20 = 0;
	private float   buyPlacedDailySma50 = 0;
	private float   buyPlacedDailyStochk = 0;
	private float   buyPlacedDailyModifiedStochkThreshold = 0;
	private float   buyPlacedCurrentPrice = 0;

	public CashMgmtIndicators()
	{
	}

//	public CashMgmtIndicators ( String xmldata )
//	{
//		try
//		{
//			this.harvestHighLowPrice = UTXmlUtils.getXmlDataAsFloat( xmldata, "HARVEST_HIGHLOW_PRICE" );
//			this.harvestHighLowPriceThreshold = UTXmlUtils.getXmlDataAsFloat( xmldata, "HARVEST_HIGHLOW_PRICE_THRESHOLD" );
//			this.harvestCurrentPrice = UTXmlUtils.getXmlDataAsFloat( xmldata, "HARVEST_CURRENT_PRICE" );
//			this.harvestCurrentVolume = UTXmlUtils.getXmlDataAsFloat( xmldata, "HARVEST_CURRENT_VOLUME" );
//			this.harvestStochk = UTXmlUtils.getXmlDataAsFloat( xmldata, "HARVEST_STOCHK" );
//			this.harvestStochkThreshold = UTXmlUtils.getXmlDataAsFloat( xmldata, "HARVEST_STOCHK_THRESHOLD" );
//			this.harvestDailySma5 = UTXmlUtils.getXmlDataAsFloat( xmldata, "HARVEST_DAILY_SMA5" );
//			this.harvestDailySma20 = UTXmlUtils.getXmlDataAsFloat( xmldata, "HARVEST_DAILY_SMA20" );
//			this.harvestDailySma50 = UTXmlUtils.getXmlDataAsFloat( xmldata, "HARVEST_DAILY_SMA50" );
//
//			this.exitDailyStochk = UTXmlUtils.getXmlDataAsFloat( xmldata, "EXITDAILY_STOCHK" );
//			this.exitDailyStochkThreshold = UTXmlUtils.getXmlDataAsFloat( xmldata, "EXITDAILY_STOCHK_THRESHOLD" );
//			this.exitDailyLowestHighestStochk = UTXmlUtils.getXmlDataAsFloat( xmldata, "EXITDAILY_LOWHIGH_STOCHK" );
//			this.exitDailyModifiedStochKThreshold = UTXmlUtils.getXmlDataAsFloat( xmldata, "EXITDAILY_MOD_STOCHK_THRESHOLD" );
//
//			this.exitMin60Stochk = UTXmlUtils.getXmlDataAsFloat( xmldata, "EXITMIN60_STOCHK" );
//			this.exitMin60StochkThreshold = UTXmlUtils.getXmlDataAsFloat( xmldata, "EXITMIN60_STOCHK_THRESHOLD" );
//			this.exitMin60LowestHighestStochk = UTXmlUtils.getXmlDataAsFloat( xmldata, "EXITMIN60_LOWHIGH_STOCHK" );
//			this.exitMin60ModifiedStochKThreshold = UTXmlUtils.getXmlDataAsFloat( xmldata, "EXITMIN60_MOD_STOCHK_THRESHOLD" );
//
//			this.exitMin15Stochk = UTXmlUtils.getXmlDataAsFloat( xmldata, "EXITMIN15_STOCHK" );
//			this.exitMin15StochkThreshold = UTXmlUtils.getXmlDataAsFloat( xmldata, "EXITMIN15_STOCHK_THRESHOLD" );
//
//			this.buyPlacedMin5Stochk = UTXmlUtils.getXmlDataAsFloat( xmldata, "BUYPLACED_MIN5_STOCHK" );
//			this.buyPlacedMin5StochkThreshold = UTXmlUtils.getXmlDataAsFloat( xmldata, "BUYPLACED_MIN5_STOCHK_THRESHOLD" );
//			this.buyPlacedDailySma5 = UTXmlUtils.getXmlDataAsFloat( xmldata, "BUYPLACED_DAILY_SMA5" );
//			this.buyPlacedDailySma20 = UTXmlUtils.getXmlDataAsFloat( xmldata, "BUYPLACED_DAILY_SMA20" );
//			this.buyPlacedDailySma50 = UTXmlUtils.getXmlDataAsFloat( xmldata, "BUYPLACED_DAILY_SMA50" );
//			this.buyPlacedDailyStochk = UTXmlUtils.getXmlDataAsFloat( xmldata, "BUYPLACED_DAILY_STOCHK" );
//			this.buyPlacedDailyModifiedStochkThreshold = UTXmlUtils.getXmlDataAsFloat( xmldata, "BUYPLACED_MOD_STOCHK_THRESHOLD" );
//			this.buyPlacedCurrentPrice = UTXmlUtils.getXmlDataAsFloat( xmldata, "BUYPLACED_CURRENT_PRICE" );
//		}
//		catch (Exception e)
//		{
//			System.out.println("Exception in "+CLASSNAME+".Constructor; message ["+e.getMessage()+"]");
//		}
//	}

//	/**
//	 * Returns a string representation of this object that is suitable for use in the account.xml file
//	 *
//	 * @param endline - a string to use as a delimiter at the end of the line. Pass empty string for nothing
//	 * @return String Xml format
//	 */
//	public final String toXml( String prefix, String endline )
//	{
//		StringBuffer stb = new StringBuffer(1000);
//		stb.append( "<CASH_MGMT_INDICATOR>" + endline );
//
//		stb.append( prefix + "<HARVEST_HIGHLOW_PRICE>" + harvestHighLowPrice + "</HARVEST_HIGHLOW_PRICE>" + endline );
//		stb.append( prefix + "<HARVEST_HIGHLOW_PRICE_THRESHOLD>" + harvestHighLowPriceThreshold + "</HARVEST_HIGHLOW_PRICE_THRESHOLD>" + endline );
//		stb.append( prefix + "<HARVEST_CURRENT_PRICE>" + harvestCurrentPrice + "</HARVEST_CURRENT_PRICE>" + endline );
//		stb.append( prefix + "<HARVEST_CURRENT_VOLUME>" + harvestCurrentVolume + "</HARVEST_CURRENT_VOLUME>" + endline );
//		stb.append( prefix + "<HARVEST_STOCHK>" + harvestStochk + "</HARVEST_STOCHK>" + endline );
//		stb.append( prefix + "<HARVEST_STOCHK_THRESHOLD>" + harvestStochkThreshold + "</HARVEST_STOCHK_THRESHOLD>" + endline );
//
//		stb.append( prefix + "<HARVEST_DAILY_SMA5>" + harvestDailySma5 + "</HARVEST_DAILY_SMA5>" + endline );
//		stb.append( prefix + "<HARVEST_DAILY_SMA20>" + harvestDailySma20 + "</HARVEST_DAILY_SMA20>" + endline );
//		stb.append( prefix + "<HARVEST_DAILY_SMA50>" + harvestDailySma50 + "</HARVEST_DAILY_SMA50>" + endline );
//
//		stb.append( prefix + "<EXITDAILY_STOCHK>" + exitDailyStochk + "</EXITDAILY_STOCHK>" + endline );
//		stb.append( prefix + "<EXITDAILY_STOCHK_THRESHOLD>" + exitDailyStochkThreshold + "</EXITDAILY_STOCHK_THRESHOLD>" + endline );
//		stb.append( prefix + "<EXITDAILY_LOWHIGH_STOCHK>" + exitDailyLowestHighestStochk + "</EXITDAILY_LOWHIGH_STOCHK>" + endline );
//		stb.append( prefix + "<EXITDAILY_MOD_STOCHK_THRESHOLD>" + exitDailyModifiedStochKThreshold + "</EXITDAILY_MOD_STOCHK_THRESHOLD>" + endline );
//
//		stb.append( prefix + "<EXITMIN60_STOCHK>" + exitMin60Stochk + "</EXITMIN60_STOCHK>" + endline );
//		stb.append( prefix + "<EXITMIN60_STOCHK_THRESHOLD>" + exitMin60StochkThreshold + "</EXITMIN60_STOCHK_THRESHOLD>" + endline );
//		stb.append( prefix + "<EXITMIN60_LOWHIGH_STOCHK>" + exitMin60LowestHighestStochk + "</EXITMIN60_LOWHIGH_STOCHK>" + endline );
//		stb.append( prefix + "<EXITMIN60_MOD_STOCHK_THRESHOLD>" + exitMin60ModifiedStochKThreshold + "</EXITMIN60_MOD_STOCHK_THRESHOLD>" + endline );
//
//		stb.append( prefix + "<EXITMIN15_STOCHK>" + exitMin15Stochk + "</EXITMIN15_STOCHK>" + endline );
//		stb.append( prefix + "<EXITMIN15_STOCHK_THRESHOLD>" + exitMin15StochkThreshold + "</EXITMIN15_STOCHK_THRESHOLD>" + endline );
//
//		stb.append( prefix + "<BUYPLACED_MIN5_STOCHK>" + buyPlacedMin5Stochk + "</BUYPLACED_MIN5_STOCHK>" + endline );
//		stb.append( prefix + "<BUYPLACED_MIN5_STOCHK_THRESHOLD>" + buyPlacedMin5StochkThreshold + "</BUYPLACED_MIN5_STOCHK_THRESHOLD>" + endline );
//		stb.append( prefix + "<BUYPLACED_DAILY_SMA5>" + buyPlacedDailySma5 + "</BUYPLACED_DAILY_SMA5>" + endline );
//		stb.append( prefix + "<BUYPLACED_DAILY_SMA20>" + buyPlacedDailySma20 + "</BUYPLACED_DAILY_SMA20>" + endline );
//		stb.append( prefix + "<BUYPLACED_DAILY_SMA50>" + buyPlacedDailySma50 + "</BUYPLACED_DAILY_SMA50>" + endline );
//		stb.append( prefix + "<BUYPLACED_DAILY_STOCHK>" + buyPlacedDailyStochk + "</BUYPLACED_DAILY_STOCHK>" + endline );
//		stb.append( prefix + "<BUYPLACED_MOD_STOCHK_THRESHOLD>" + buyPlacedDailyModifiedStochkThreshold + "</BUYPLACED_MOD_STOCHK_THRESHOLD>" + endline );
//		stb.append( prefix + "<BUYPLACED_CURRENT_PRICE>" + buyPlacedCurrentPrice + "</BUYPLACED_CURRENT_PRICE>" + endline );
//
//		stb.append( "</CASH_MGMT_INDICATOR>" + endline );
//		return stb.toString();
//   }
//



	public float getHarvestHighLowPrice()
	{
		return harvestHighLowPrice;
	}

	public float getHarvestHighLowPriceThreshold()
	{
		return harvestHighLowPriceThreshold;
	}

	public float getHarvestCurrentPrice()
	{
		return harvestCurrentPrice;
	}

	public float getHarvestCurrentVolume()
	{
		return harvestCurrentVolume;
	}

	public float getHarvestDailySma5()
	{
		return harvestDailySma5;
	}

	public float getHarvestDailySma20()
	{
		return harvestDailySma20;
	}

	public float getHarvestDailySma50()
	{
		return harvestDailySma50;
	}

	public float getExitDailyStochk()
	{
		return exitDailyStochk;
	}

	public float getExitDailyStochkThreshold()
	{
		return exitDailyStochkThreshold;
	}

	public float getExitDailyLowestHighestStochk()
	{
		return exitDailyLowestHighestStochk;
	}

	public float getExitDailyModifiedStochKThreshold()
	{
		return exitDailyModifiedStochKThreshold;
	}

	public float getExitMin60Stochk()
	{
		return exitMin60Stochk;
	}

	public float getExitMin60StochkThreshold()
	{
		return exitMin60StochkThreshold;
	}

	public float getExitMin60LowestHighestStochk()
	{
		return exitMin60LowestHighestStochk;
	}

	public float getExitMin60ModifiedStochKThreshold()
	{
		return exitMin60ModifiedStochKThreshold;
	}

	public float getExitMin15Stochk()
	{
		return exitMin15Stochk;
	}

	public float getExitMin15StochkThreshold()
	{
		return exitMin15StochkThreshold;
	}

	public float getBuyPlacedMin5Stochk()
	{
		return buyPlacedMin5Stochk;
	}

	public float getBuyPlacedMin5StochkThreshold()
	{
		return buyPlacedMin5StochkThreshold;
	}

	public float getBuyPlacedDailySma5()
	{
		return buyPlacedDailySma5;
	}

	public float getBuyPlacedDailySma20()
	{
		return buyPlacedDailySma20;
	}

	public float getBuyPlacedDailySma50()
	{
		return buyPlacedDailySma50;
	}

	public float getBuyPlacedDailyStochk()
	{
		return buyPlacedDailyStochk;
	}

	public float getBuyPlacedDailyModifiedStochkThreshold()
	{
		return buyPlacedDailyModifiedStochkThreshold;
	}

	public float getBuyPlacedCurrentPrice()
	{
		return buyPlacedCurrentPrice;
	}

	public float getHarvestStochk()
	{
		return harvestStochk;
	}

	public float getHarvestStochkThreshold()
	{
		return harvestStochkThreshold;
	}

	public void setHarvestHighLowPrice(float harvestHighLowPrice)
	{
		this.harvestHighLowPrice = harvestHighLowPrice;
	}

	public void setHarvestHighLowPriceThreshold(float harvestHighLowPriceThreshold)
	{
		this.harvestHighLowPriceThreshold = harvestHighLowPriceThreshold;
	}

	public void setHarvestCurrentPrice(float harvestCurrentPrice)
	{
		this.harvestCurrentPrice = harvestCurrentPrice;
	}

	public void setHarvestCurrentVolume(float harvestCurrentVolume)
	{
		this.harvestCurrentVolume = harvestCurrentVolume;
	}

	public void setHarvestDailySma5(float harvestDailySma5)
	{
		this.harvestDailySma5 = harvestDailySma5;
	}

	public void setHarvestDailySma20(float harvestDailySma20)
	{
		this.harvestDailySma20 = harvestDailySma20;
	}

	public void setHarvestDailySma50(float harvestDailySma50)
	{
		this.harvestDailySma50 = harvestDailySma50;
	}

	public void setExitDailyStochk(float exitDailyStochk)
	{
		this.exitDailyStochk = exitDailyStochk;
	}

	public void setExitDailyStochkThreshold(float exitDailyStochkThreshold)
	{
		this.exitDailyStochkThreshold = exitDailyStochkThreshold;
	}

	public void setExitDailyLowestHighestStochk(float exitDailyLowestHighestStochk)
	{
		this.exitDailyLowestHighestStochk = exitDailyLowestHighestStochk;
	}

	public void setExitDailyModifiedStochKThreshold(float exitDailyModifiedStochKThreshold)
	{
		this.exitDailyModifiedStochKThreshold = exitDailyModifiedStochKThreshold;
	}

	public void setExitMin60Stochk(float exitMin60Stochk)
	{
		this.exitMin60Stochk = exitMin60Stochk;
	}

	public void setExitMin60StochkThreshold(float exitMin60StochkThreshold)
	{
		this.exitMin60StochkThreshold = exitMin60StochkThreshold;
	}

	public void setExitMin60LowestHighestStochk(float exitMin60LowestHighestStochk)
	{
		this.exitMin60LowestHighestStochk = exitMin60LowestHighestStochk;
	}

	public void setExitMin60ModifiedStochKThreshold(float exitMin60ModifiedStochKThreshold)
	{
		this.exitMin60ModifiedStochKThreshold = exitMin60ModifiedStochKThreshold;
	}

	public void setExitMin15Stochk(float exitMin15Stochk)
	{
		this.exitMin15Stochk = exitMin15Stochk;
	}

	public void setExitMin15StochkThreshold(float exitMin15StochkThreshold)
	{
		this.exitMin15StochkThreshold = exitMin15StochkThreshold;
	}

	public void setBuyPlacedMin5Stochk(float buyPlacedMin5Stochk)
	{
		this.buyPlacedMin5Stochk = buyPlacedMin5Stochk;
	}

	public void setBuyPlacedMin5StochkThreshold(float buyPlacedMin5StochkThreshold)
	{
		this.buyPlacedMin5StochkThreshold = buyPlacedMin5StochkThreshold;
	}

	public void setBuyPlacedDailySma5(float buyPlacedDailySma5)
	{
		this.buyPlacedDailySma5 = buyPlacedDailySma5;
	}

	public void setBuyPlacedDailySma20(float buyPlacedDailySma20)
	{
		this.buyPlacedDailySma20 = buyPlacedDailySma20;
	}

	public void setBuyPlacedDailySma50(float buyPlacedDailySma50)
	{
		this.buyPlacedDailySma50 = buyPlacedDailySma50;
	}

	public void setBuyPlacedDailyStochk(float buyPlacedDailyStochk)
	{
		this.buyPlacedDailyStochk = buyPlacedDailyStochk;
	}

	public void setBuyPlacedDailyModifiedStochkThreshold(float buyPlacedDailyModifiedStochkThreshold)
	{
		this.buyPlacedDailyModifiedStochkThreshold = buyPlacedDailyModifiedStochkThreshold;
	}

	public void setBuyPlacedCurrentPrice(float buyPlacedCurrentPrice)
	{
		this.buyPlacedCurrentPrice = buyPlacedCurrentPrice;
	}

	public void setHarvestStochk(float harvestStochk)
	{
		this.harvestStochk = harvestStochk;
	}

	public void setHarvestStochkThreshold(float harvestStochkThreshold)
	{
		this.harvestStochkThreshold = harvestStochkThreshold;
	}

}
