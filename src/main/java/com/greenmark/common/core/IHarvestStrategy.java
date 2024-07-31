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

    int getHarvestStrategyType();

    void setHarvestStrategyType(int harvestStrategyType);

    void setOriginBucketTimeframe(int timeframe);

    String toXml(String prefix, String endline);

    String getHarvestStrategyLabel();

    String getAcronym();

    boolean isStrategyNone();

    boolean isStrategyHarvester();

    boolean isStrategyB2B();

    boolean isStrategyDropcat();

    boolean isStrategyATH();

    int getOriginBuyBucketTimeframe();

}
