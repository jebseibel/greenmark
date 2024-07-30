package com.greenmark.account;

import com.greenmark.core.Market;
import com.greenmark.core.Scenario;
import com.greenmark.common.core.Labels;
import com.greenmark.utils.UTCalendarTime;
import com.greenmark.utils.UTXmlUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Account
{
   /** This is the class name. */
   public static final String CLASSNAME = "Account";

	public BrokerAccount cashAccount;
	public BrokerAccount marginAccount;

   /** Used by the database **/
   protected long id;       //leave as null for hibernate
   protected long scenarioId = 0;  //set to zero to find insert errors
	protected String         createDescription = "";  // Not restored/saved to XML.  That's ok, its in DB.
	protected String         resultsDescription = "";
	protected UTCalendarTime historyProcessStartTime;
	protected UTCalendarTime historyProcessEndTime;
   protected int active = Labels.OBJECT_ACTIVE;

//	protected String  resultsDbUrl = ""; // The user selects a DB URL when creating a new account.  We store that url here.
	//protected AvailableDatabaseProperties  resultsDbProperties;

   /**************************/
	protected Scenario currentScenario;
	protected UTCalendarTime historyCurrentDatetime;

   protected String name;

	protected double shortPositionsTotal = 0;
	protected double longPositionsTotal = 0;


	public Account()
	{
		// Hibernate needs an empty constructor
		cashAccount = new BrokerAccount(BrokerAccount.BROKER_ACCOUNT_CASH);
		marginAccount = new BrokerAccount(BrokerAccount.BROKER_ACCOUNT_MARGIN);
	}


   public Account ( String xmldata )
   {
      try
      {
         //ACCOUNT_ID
         this.id = UTXmlUtils.getXmlDataAsLong( xmldata, "ID" );

			//ACCOUNT_DAILY_ID
			long accountDailyID = UTXmlUtils.getXmlDataAsLong( xmldata, "ACCOUNT_DAILY_ID" );
			//AccountManager.setAccountDailyId(accountDailyID);

         //SCENARIO_ID
         this.scenarioId = UTXmlUtils.getXmlDataAsLong( xmldata, "SCENARIO_ID" );

         //ACTIVE
         this.active = UTXmlUtils.getXmlDataAsInt( xmldata, "ACTIVE" );

         //NAME
         this.name = UTXmlUtils.getXmlData( xmldata, "NAME" );

			//  RESULTS_DB_URL
         String resultsDbString = UTXmlUtils.getXmlData( xmldata, "RESULTS_DB" );
			//resultsDbProperties = new AvailableDatabaseProperties(resultsDbString);

			// Now set the DB connection properties for the restored value.
//			setDbConnectionProperties ();

         //SHORT_POSITIONS_TOTAL
         this.shortPositionsTotal = UTXmlUtils.getXmlDataAsDouble( xmldata, "SHORT_POSITIONS_TOTAL" );

         //LONG_POSITIONS_TOTAL
         this.longPositionsTotal = UTXmlUtils.getXmlDataAsDouble( xmldata, "LONG_POSITIONS_TOTAL" );

			//  HISTORY_CURRENT_DATETIME
			String currentDatetimeString = UTXmlUtils.getXmlData( xmldata, "HISTORY_CURRENT_DATETIME" );
			if ( currentDatetimeString.equals("") == false )
				historyCurrentDatetime = new UTCalendarTime ( currentDatetimeString );

//			String scenarioXmlData = UTXmlUtils.getXmlData( xmldata, "SCENARIO" );
//			currentScenario = new Scenario ( scenarioXmlData );
//
//			//  Create the Cash Account
//			String cashBrokerAccount = UTXmlUtils.getXmlData( xmldata, "CASH_BROKER_ACCOUNT" );
//			cashAccount = new BrokerAccount(cashBrokerAccount);
//
//			//  Create the Margin Account
//			String marginBrokerAccount = UTXmlUtils.getXmlData( xmldata, "MARGIN_BROKER_ACCOUNT" );
//			marginAccount = new BrokerAccount(marginBrokerAccount);
      }
      catch (Exception e)
      {
         System.out.println("Exception in "+CLASSNAME+".Constructor; message ["+e.getMessage()+"]");
      }

   }



	public void goToNextMarketDay ()
	{
//		String debugDateString = historyCurrentDatetime.formatDateTimeDisplay();
		historyCurrentDatetime.incrementDate(1);

//		String debugDateString1 = historyCurrentDatetime.formatDateTimeDisplay();

	   // Skip over holidays and weekends.
		while ( ( Market.isHoliday (historyCurrentDatetime.getJavaDate()) ) || ( historyCurrentDatetime.isWeekendDay() ) )
		{
			log.debug("   Holiday or Weekend  for Date: [" + historyCurrentDatetime.formatDateTimeDisplay() + "]");
			historyCurrentDatetime.incrementDate(1);
//			String debugDateString2 = historyCurrentDatetime.formatDateTimeDisplay();
//			int debugMe = 1;
		}

//		String heartbeatTime = Heartbeat.getCurrentMarketTime("");
		historyCurrentDatetime.resetCalendarTime( "8", "15", "00");

//		String debugDateString3 = historyCurrentDatetime.formatDateTimeDisplay();
//		String heartbeatTime2 = Heartbeat.getCurrentMarketTime("");
   }

	public void resetToPreviousMarketDay ()
	{
		historyCurrentDatetime.decrementDate(1);

		// Skip over holidays and weekends.
		while ( ( Market.isHoliday (historyCurrentDatetime.getJavaDate()) ) || ( historyCurrentDatetime.isWeekendDay() ) )
		{
			log.debug("   Holiday or Weekend  for Date: [" + historyCurrentDatetime.formatDateTimeDisplay() + "]");
			historyCurrentDatetime.decrementDate(1);
		}

		historyCurrentDatetime.resetCalendarTime( "18", "00", "00");
   }

	public boolean isScenarioOver ()
	{
		boolean returnVal = false;

	   UTCalendarTime scenarioStop = new UTCalendarTime (getCurrentScenario().getStopDate());

	   if ( historyCurrentDatetime.isAfterInCalendarDateTime(scenarioStop, true) )
			returnVal = true;

		return returnVal;
   }

   public int getActive()
   {
      return this.active;
   }

   public void setActive(int active)
   {
      this.active = active;
   }

   public String getName()
   {
      return this.name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public void setScenarioId(long scenarioId)
   {
      this.scenarioId = scenarioId;
   }

   public long getScenarioId()
   {
      return scenarioId;
   }

   public long getId()
   {
      return id;
   }

	///////////////////////////////////////////////////////////////
	//  CONVENIENCE METHODS FOR CASH AND MARGIN ACCOUNTS
	///////////////////////////////////////////////////////////////
	public double getGrandTotalEquity()
	{
		return cashAccount.getTotalEquity() + marginAccount.getTotalEquity();
	}

	public double getGrandAllocatedFunds()
	{
		return cashAccount.getAllocatedFunds() + marginAccount.getAllocatedFunds();
	}

	public double getGrandCashOnHand()
	{
		return cashAccount.getCashOnHand() + marginAccount.getCashOnHand();
	}

	public double getGrandAvailableFunds()
	{
		return cashAccount.getAvailableFunds() + marginAccount.getAvailableFunds();
	}

	public double getGrandUnsettledFunds()
	{
		return cashAccount.getUnsettledFunds() + marginAccount.getUnsettledFunds();
	}

   //////////////////////////////////////////////////////////////////
	//  Keep these original setter's and getter's, they are for the Cash Account
   public double getAllocatedFunds()
   {
      return cashAccount.getAllocatedFunds();
   }

   public void setAllocatedFunds( double allocatedFunds )
   {
		cashAccount.setAllocatedFunds(allocatedFunds);
   }

   public void addAllocatedFunds( float value )
   {
		cashAccount.addAllocatedFunds(value);
   }

   public void subtractAllocatedFunds( float value )
   {
		cashAccount.subtractAllocatedFunds(value);
   }

   //////////////////////////////////////////////////////////////////
   public double getAvailableFunds()
   {
      return cashAccount.getAvailableFunds();
   }

   public void setAvailableFunds( double availableFunds )
   {
      cashAccount.setAvailableFunds(availableFunds);
   }

   public void addAvailableFunds( float value )
   {
		cashAccount.addAvailableFunds(value);
   }

   public void subtractAvailableFunds( float value )
   {
		cashAccount.subtractAvailableFunds(value);
   }

   ///////////////////////////////////////////////////////////////////////
   public double getCashOnHand()
   {
      return cashAccount.getCashOnHand();
   }

   public void setCashOnHand( double cashOnHand )
   {
      cashAccount.setCashOnHand(cashOnHand);
   }

   public void addCashOnHand( float value )
   {
		cashAccount.addCashOnHand(value);
   }

   public void subtractCashOnHand( float value )
   {
		cashAccount.subtractCashOnHand(value);
   }

	///////////////////////////////////////////////////////////////
	public double getTotalEquity()
	{
		return cashAccount.getTotalEquity();
	}

	public void setTotalEquity( double totalEquity )
	{
		cashAccount.setTotalEquity(totalEquity);
	}

	public void addTotalEquity( float value )
	{
		cashAccount.addTotalEquity(value);
	}

	public void subtractTotalEquity( float value )
	{
		cashAccount.subtractTotalEquity(value);
   }


	///////////////////////////////////////////////////////////////
	public double getUnsettledFunds()
	{
		return cashAccount.getUnsettledFunds();
	}

	public void setUnsettledFunds( double totalEquity )
	{
		cashAccount.setUnsettledFunds(totalEquity);
	}

	public void addUnsettledFunds( float value )
	{
		cashAccount.addUnsettledFunds(value);
	}

	public void subtractUnsettledFunds( float value )
	{
		cashAccount.subtractUnsettledFunds(value);
   }


	//////////////////////////////////////////////////////////////////
	//  Here are the convenience setter's and getter's for the Margin Account
	public double getAllocatedFundsMargin()
	{
		return marginAccount.getAllocatedFunds();
	}

	public void setAllocatedFundsMargin( double allocatedFunds )
	{
		marginAccount.setAllocatedFunds(allocatedFunds);
	}

	public void addAllocatedFundsMargin( float value )
	{
		marginAccount.addAllocatedFunds(value);
	}

	public void subtractAllocatedFundsMargin( float value )
	{
		marginAccount.subtractAllocatedFunds(value);
	}

	//////////////////////////////////////////////////////////////////
	public double getAvailableFundsMargin()
	{
		return marginAccount.getAvailableFunds();
	}

	public void setAvailableFundsMargin( double availableFunds )
	{
		marginAccount.setAvailableFunds(availableFunds);
	}

	public void addAvailableFundsMargin( float value )
	{
		marginAccount.addAvailableFunds(value);
	}

	public void subtractAvailableFundsMargin( float value )
	{
		marginAccount.subtractAvailableFunds(value);
	}

	///////////////////////////////////////////////////////////////////////
	public double getCashOnHandMargin()
	{
		return marginAccount.getCashOnHand();
	}

	public void setCashOnHandMargin( double cashOnHand )
	{
		marginAccount.setCashOnHand(cashOnHand);
	}

	public void addCashOnHandMargin( float value )
	{
		marginAccount.addCashOnHand(value);
	}

	public void subtractCashOnHandMargin( float value )
	{
		marginAccount.subtractCashOnHand(value);
	}

	///////////////////////////////////////////////////////////////
	public double getTotalEquityMargin()
	{
		return marginAccount.getTotalEquity();
	}

	public void setTotalEquityMargin( double totalEquity )
	{
		marginAccount.setTotalEquity(totalEquity);
	}

	public void addTotalEquityMargin( float value )
	{
		marginAccount.addTotalEquity(value);
	}

	public void subtractTotalEquityMargin( float value )
	{
		marginAccount.subtractTotalEquity(value);
   }

	///////////////////////////////////////////////////////////////
	public double getUnsettledFundsMargin()
	{
		return marginAccount.getUnsettledFunds();
	}

	public void setUnsettledFundsMargin( double totalEquity )
	{
		marginAccount.setUnsettledFunds(totalEquity);
	}

	public void addUnsettledFundsMargin( float value )
	{
		marginAccount.addUnsettledFunds(value);
	}

	public void subtractUnsettledFundsMargin( float value )
	{
		marginAccount.subtractUnsettledFunds(value);
   }

   ///////////////////////////////////////////////////////////////////////////////
   public double getLongPositionsTotal()
   {
      return longPositionsTotal;
   }

   public void setLongPositionsTotal( double longPositionsTotal )
   {
      this.longPositionsTotal = longPositionsTotal;
   }

   public void addLongPositionsTotal( float value )
   {
      this.longPositionsTotal += value;
   }

   public void subtractLongPositionsTotal( float value )
   {
      this.longPositionsTotal -= value;
   }

   //////////////////////////////////////////////////////////////////

   public double getShortPositionsTotal()
   {
      return shortPositionsTotal;
   }

   public void setShortPositionsTotal( double shortPositionsTotal )
   {
      this.shortPositionsTotal = shortPositionsTotal;
   }

   public void addShortPositionsTotal( float value )
   {
      this.shortPositionsTotal += value;
   }

   public void subtractShortPositionsTotal( float value )
   {
      this.shortPositionsTotal -= value;
   }

	///////////////////////////////////////////////////////////////
	public void setId(long id)
	{
		this.id = id;
	}

	public Scenario getCurrentScenario()
	{
		return currentScenario;
	}

	public void setCurrentScenario(Scenario currentScenario)
	{
		this.currentScenario = currentScenario;
	}

	public void incrementHistoryMinute()
	{
		historyCurrentDatetime.incrementMinute();
	}

	public UTCalendarTime getCurrentDatetime ()
	{

			UTCalendarTime todaysCal = new UTCalendarTime (new java.util.Date() );
			return todaysCal;

}

	public UTCalendarTime getHistoryCurrentDatetime()
	{
		UTCalendarTime returnTime = null;

		try
		{
			// Return a copy of the current datetime so that noone will change this one.
			returnTime = new UTCalendarTime(historyCurrentDatetime);
		}
		catch (Exception e)
		{
			System.out.println("Exception in " + CLASSNAME + ".getHistoryCurrentDatetime; message [" + e.getMessage() + "]");
		}

		return returnTime;
	}

	public String getHistoryCurrentDatetimeString()
	{
		String returnTime = null;

		try
		{
			// Return a copy of the current datetime so that noone will change this one.
			returnTime = historyCurrentDatetime.formatSimpleDateDisplay();
		}
		catch (Exception e)
		{
			System.out.println("Exception in " + CLASSNAME + ".getHistoryCurrentDatetimeString; message [" + e.getMessage() + "]");
		}

		return returnTime;
	}

	public java.util.Date getHistoryCurrentJavadate_DISPLAY_ONLY()
	{
		java.util.Date returnTime = null;

		try
		{
			// Return a copy of the current datetime so that noone will change this one.
			returnTime = historyCurrentDatetime.getJavaDate();
		}
		catch (Exception e)
		{
			System.out.println("Exception in " + CLASSNAME + ".getHistoryCurrentJavadate_DISPLAY_ONLY; message [" + e.getMessage() + "]");
		}

		return returnTime;
	}

	public String getResultsDescription()
	{
		return resultsDescription;
	}

	public UTCalendarTime getHistoryProcessStartTime()
	{
		return historyProcessStartTime;
	}

	public UTCalendarTime getHistoryProcessEndTime()
	{
		return historyProcessEndTime;
	}

	public String getCreateDescription()
	{
		return createDescription;
	}

//	public AvailableDatabaseProperties getResultsDbProperties()
//	{
//		return resultsDbProperties;
//	}

	public void setHistoryCurrentDatetime(UTCalendarTime currentDatetime)
	{
		this.historyCurrentDatetime = currentDatetime;
	}

	public void setResultsDescription(String resultsDescription)
	{
		this.resultsDescription = resultsDescription;
	}

	public void setHistoryProcessStartTime(UTCalendarTime historyProcessStartTime)
	{
		this.historyProcessStartTime = historyProcessStartTime;
	}

	public void setHistoryProcessEndTime(UTCalendarTime historyProcessEndTime)
	{
		this.historyProcessEndTime = historyProcessEndTime;
	}

	public void setCreateDescription(String createDescription)
	{
		this.createDescription = createDescription;
	}

//	public void setResultsDbProperties(AvailableDatabaseProperties resultsDbProperties)
//	{
//		this.resultsDbProperties = resultsDbProperties;
//	}

	/**
    * Returns a string representation of this object that is suitable for use in the account.xml file
    *
    * @return String Xml format
    */
//   public final String toXml( String prefix, String endline )
//   {
//      StringBuffer stb = new StringBuffer(1000);
//      stb.append( "<ACCOUNT>" + endline );
//      stb.append( prefix + "<ID>" + this.getId() + "</ID>" + endline );
//      stb.append( prefix + "<SCENARIO_ID>" + this.getScenarioId() + "</SCENARIO_ID>" + endline );
//		stb.append( prefix + "<ACCOUNT_DAILY_ID>" + AccountManager.getAccountDailyId() + "</ACCOUNT_DAILY_ID>" + endline );
//		stb.append( prefix + "<NAME>" + this.getName() + "</NAME>" + endline );
//      stb.append( prefix + "<LONG_POSITIONS_TOTAL>" + this.getLongPositionsTotal() + "</LONG_POSITIONS_TOTAL>" + endline );
//      stb.append( prefix + "<SHORT_POSITIONS_TOTAL>" + this.getShortPositionsTotal() + "</SHORT_POSITIONS_TOTAL>" + endline );
//      stb.append( prefix + "<ACTIVE>" + this.getActive() + "</ACTIVE>" + endline );
//
//		if ( DataFeedManager.isHistoricalDatafeed() )
//			stb.append( prefix + "<HISTORY_CURRENT_DATETIME>" + this.getHistoryCurrentDatetime().formatXMLDateTime(false) + "</HISTORY_CURRENT_DATETIME>" + endline );
//		else
//			stb.append( prefix + "<HISTORY_CURRENT_DATETIME>" +  "</HISTORY_CURRENT_DATETIME>" + endline );
//
//		//add the Selected Results DB Connection information
//		stb.append( prefix + "<RESULTS_DB>" + endline );
//		stb.append( resultsDbProperties.toXml( prefix + "\t", "\n") );
//		stb.append( prefix + "</RESULTS_DB>" + endline );
//
//		//add the Scenario Info
//		stb.append( currentScenario.toXml(prefix, endline) );
//
//		//add the Cash Account
//      stb.append( prefix + "<CASH_BROKER_ACCOUNT>" + endline );
//		stb.append( cashAccount.toXml( prefix + "\t", "\n") );
//		stb.append( prefix + "</CASH_BROKER_ACCOUNT>" + endline );
//
//		//add the Margin Account
//		stb.append( prefix + "<MARGIN_BROKER_ACCOUNT>" + endline );
//		stb.append( marginAccount.toXml( prefix + "\t", "\n") );
//		stb.append( prefix + "</MARGIN_BROKER_ACCOUNT>" + endline );
//
//      //add the account event objects
//      stb.append( prefix + "<ACCOUNT_EVENTS>" + endline );
//
//      ArrayList events = AccountManager.getEventList();
//      Iterator iter = events.iterator();
//      while (iter.hasNext())
//      {
//         AccountEvent thisEvent = (AccountEvent) iter.next();
//         stb.append( thisEvent.toXml() );
//      }
//
//      stb.append( prefix + "</ACCOUNT_EVENTS>" + endline );
//
//      stb.append( "</ACCOUNT>" + endline );
//      return stb.toString();
//   }

	public String dbSummary()
	{
		StringBuffer stb = new StringBuffer();
		stb.append( " > > " + CLASSNAME + ":");
		stb.append( "accountId [" +id+ "] ");
		stb.append( "scenarioId [" +scenarioId+ "] ");
		stb.append( "name [" +name+ "] ");
		stb.append( "active [" +active+ "] ");
		return stb.toString();
	}


	public String toString ()
	{
		StringBuffer outString = new StringBuffer();
		outString.append ("ACCOUNT:   ");
		outString.append(id);
		outString.append("   ");
		outString.append(name);
		outString.append("   ");
		outString.append(active);
		outString.append("   ");

		return outString.toString();
	}




}
