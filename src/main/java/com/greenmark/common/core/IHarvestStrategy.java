package com.greenmark.common.core;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: IHarvestStrategy</p>
 * <p>Description: This interface is used to define a harvest strategy for a security.  One of:  Harvester, Bucket to Bucket.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
public interface IHarvestStrategy {

	public int getHarvestStrategyType();

	public void setOriginBucketTimeframe(int timeframe);

	public void setHarvestStrategyType(int harvestStrategyType);

	public String toXml(String prefix, String endline);

	public String getHarvestStrategyLabel();

	public String getAcronym();

	public boolean isStrategyNone();

	public boolean isStrategyHarvester();

	public boolean isStrategyB2B();

	public boolean isStrategyDropcat();

	public boolean isStrategyATH();

	public int getOriginBuyBucketTimeframe();

}
