package com.greenmark.common.database;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: DbConnectionListener</p>
 * <p>Description: This class was designed to allow logging and should be deprecated in lieu of a new logging mechanism.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public interface DbConnectionListener {
	void errorThrown(String message);
}
