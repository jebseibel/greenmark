package com.greenmark.common.database;

import java.io.Serializable;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AvailableResultsDbConfig</p>
 * <p>Description: This class is used to hold the JDBC connection string properties and is used when the user can select different databases.
 * This class is also used to parse the greenman.properties file values in its:  RESULTS DATABASES LISTING W/ CONNECTION STRINGS section.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class DbConfigBase implements Serializable {
	public static final String CLASSNAME = "AvailableResultsDbConfig";

	private static final long serialVersionUID = 1L;

	public static final String DATABASE_LISTING_KEY_DATABASE_NAME = "name";

	protected String databaseName;
	protected String databaseConnectionUrl;
	protected String databaseConnectionUsername;
	protected String databaseConnectionPassword;
	protected String databaseAvailInvestorDemo;

	protected String listId;

	protected String propertiesListingKeyPrefix;

	public DbConfigBase() {
	}

	@Override
	public String toString() {
		StringBuffer returnString = new StringBuffer();

		returnString.append("AvailableDatabaseProperties: ");
		returnString.append("databaseName: " + databaseName);
		returnString.append(",databaseConnectionUrl: " + databaseConnectionUrl);
		returnString.append(",databaseConnectionUsername: " + databaseConnectionUsername);
		returnString.append(",databaseConnectionPassword: " + databaseConnectionPassword);
		returnString.append(",databaseAvailInvestorDemo: " + databaseAvailInvestorDemo);
		returnString.append(",listId: " + listId);
		returnString.append(",propertiesListingKeyPrefix: " + propertiesListingKeyPrefix);

		return returnString.toString();
	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public String getDatabaseName() {
		return databaseName;
	}

	public String getListId() {
		return listId;
	}

	public String getDatabaseConnectionUrl() {
		return databaseConnectionUrl;
	}

	public String getDatabaseConnectionUsername() {
		return databaseConnectionUsername;
	}

	public String getDatabaseConnectionPassword() {
		return databaseConnectionPassword;
	}

	public String getPropertiesListingKeyPrefix() {
		return propertiesListingKeyPrefix;
	}

	public String getDatabaseAvailInvestorDemo() {
		return databaseAvailInvestorDemo;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public void setDatabaseConnectionUrl(String databaseConnectionUrl) {
		this.databaseConnectionUrl = databaseConnectionUrl;
	}

	public void setDatabaseConnectionUsername(String databaseConnectionUsername) {
		this.databaseConnectionUsername = databaseConnectionUsername;
	}

	public void setDatabaseConnectionPassword(String databaseConnectionPassword) {
		this.databaseConnectionPassword = databaseConnectionPassword;
	}

	public void setPropertiesListingKeyPrefix(String propertiesListingKeyPrefix) {
		this.propertiesListingKeyPrefix = propertiesListingKeyPrefix;
	}

	public void setDatabaseAvailInvestorDemo(String databaseAvailInvestorDemo) {
		this.databaseAvailInvestorDemo = databaseAvailInvestorDemo;
	}
}
