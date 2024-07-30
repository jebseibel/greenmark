package com.greenmark.core;

/////////////////////////////////////////////////
// START CLASS: MarketCloseService
/////////////////////////////////////////////////

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Seibel Consulting</p>
 * @author Jeb Seibel
 * @version 1.0
 */
@Slf4j
public class MarketCloseService
{
   /** This is the class name. */
   public static final String CLASSNAME = "MarketCloseService";


	public static void marketClosedService ( int modelLongOrShort )
	{
		log.warn( " +++++++++ Entering marketClosedService +++++++++" );

		try
		{
			// Close all Open Orders Now
			//AccountManager.cancelAllOrders( true, modelLongOrShort);


		}
		catch( Exception ex )
		{
			log.info( " +++++++++ ERROR marketClosedService +++++++++   message: [" + ex.getMessage() + "]" );
		}

		//  Send Email message to administrator and user.

		log.warn( " +++++++++ Leaving marketClosedService +++++++++" );
	}
}
