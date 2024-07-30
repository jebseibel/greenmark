package com.greenmark.account;


public class AccountDaily extends AccountTimePeriodBase
{
   /** This is the class name. */
	public static final String CLASSNAME = "AccountDaily";

    /** Used by the database **/
   protected long accountId = 0;   //set to zero to find insert errors
	protected int marketTrendOn = -1;
	protected int marketTrendValue = -1;
	protected int marketTrendGaugeValue = -1;
	protected int marketTrendGaugeNum = -1;
	protected float marketTrendIndicatorValue = 0F;

   /**************************/

	public AccountDaily ()
	{
	}

	///////////////////////////////////////////////////////////////
	//  CONVENIENCE METHODS FOR CASH AND MARGIN ACCOUNTS
	///////////////////////////////////////////////////////////////

	public long getAccountId() {
		return accountId;
	}

	public int getMarketTrendGaugeNum()
	{
		return marketTrendGaugeNum;
	}

	public int getMarketTrendGaugeValue()
	{
		return marketTrendGaugeValue;
	}

	public int getMarketTrendOn()
	{
		return marketTrendOn;
	}

	public int getMarketTrendValue()
	{
		return marketTrendValue;
	}

	public float getMarketTrendIndicatorValue()
	{
		return marketTrendIndicatorValue;
	}

	public void setAccountId(long account_id)
	{
		this.accountId = account_id;
	}

	public void setMarketTrendValue(int marketTrendValue)
	{
		this.marketTrendValue = marketTrendValue;
	}

	public void setMarketTrendOn(int marketTrendOn)
	{
		this.marketTrendOn = marketTrendOn;
	}

	public void setMarketTrendGaugeValue(int marketTrendGaugeValue)
	{
		this.marketTrendGaugeValue = marketTrendGaugeValue;
	}

	public void setMarketTrendGaugeNum(int marketTrendGaugeNum)
	{
		this.marketTrendGaugeNum = marketTrendGaugeNum;
	}

	public void setMarketTrendIndicatorValue(float marketTrendIndicatorValue)
	{
		this.marketTrendIndicatorValue = marketTrendIndicatorValue;
	}

	public String dbSummary()
	{
		StringBuffer stb = new StringBuffer();
		stb.append( " > > " + CLASSNAME + ":");
		stb.append( "accountDailyId        [" +id+ "] ");
		stb.append( "accountId             [" +accountId+ "] ");
		stb.append( "marketTrendOn         [" +marketTrendOn        + "] ");
		stb.append( "marketTrendValue      [" +marketTrendValue     + "] ");
		stb.append( "marketTrendGaugeValue [" +marketTrendGaugeValue+ "] ");
		stb.append( "marketTrendGaugeNum   [" +marketTrendGaugeNum  + "] ");
		stb.append( "active                [" +active+ "] ");
		return stb.toString();
	}

}
