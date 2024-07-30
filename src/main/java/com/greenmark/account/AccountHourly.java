package com.greenmark.account;

public class AccountHourly extends AccountTimePeriodBase
{
   /** This is the class name. */
	public static final String CLASSNAME = "AccountHourly";

    /** Used by the database **/
   protected long accountDailyId = 0;   //set to zero to find insert errors
   /**************************/



	public AccountHourly ()
	{
		// an empty constructor
	}

	///////////////////////////////////////////////////////////////
	//  CONVENIENCE METHODS FOR CASH AND MARGIN ACCOUNTS
	///////////////////////////////////////////////////////////////


	public long getAccountDailyId() {
		return accountDailyId;
	}

	public void setAccountDailyId(long accountDailyId)
	{
		this.accountDailyId = accountDailyId;
	}


	public String dbSummary()
	{
		StringBuffer stb = new StringBuffer();
		stb.append( " > > " + CLASSNAME + ":");
		stb.append( "AccountHourlyId [" +id+ "] ");
		stb.append( "accountDailyId [" +accountDailyId+ "] ");
		stb.append( "active [" +active+ "] ");
		return stb.toString();
	}

}
