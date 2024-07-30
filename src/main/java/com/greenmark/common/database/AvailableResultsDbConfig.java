package com.greenmark.common.database;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTPropertyManager;
import com.greenmark.utils.UTXmlUtils;

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

public final class AvailableResultsDbConfig extends DbConfigBase implements Serializable {
	public static final String CLASSNAME = "AvailableResultsDbConfig";

	private static final long serialVersionUID = 1L;

	public static final String DATABASE_LISTING_KEY_CONNECTION_URL = "CONNECTION_URL";
	public static final String DATABASE_LISTING_KEY_CONNECTION_USERNAME = "CONNECTION_USERNAME";
	public static final String DATABASE_LISTING_KEY_CONNECTION_PASSWORD = "CONNECTION_PASSWORD";
	public static final String DATABASE_AVAILABLE_FOR_INVESTOR_DEMO = "AVAIL_INVESTOR_DEMO";
	/**
	 * These are the values of the keys used for the database properties.
	 */
	public static final String DATABASE_LISTING_NUM_DATABASES_KEY = "dbScenarioResults.numDatabases";
	public static final String DATABASE_LISTING_KEY = "dbScenarioResults.dbListing";
	public static final String DATABASE_LISTING_KEY_PREFIX = "dbScenarioResults";

	public AvailableResultsDbConfig() {
	}

	public static AvailableResultsDbConfig getResultsDbConfigByUrl(Map<String, AvailableResultsDbConfig> resultsConfigs, String url) {
		for (AvailableResultsDbConfig resultsConfig : resultsConfigs.values()) {
			if (resultsConfig.getDatabaseConnectionUrl().equals(url))
				return resultsConfig;
		}
		return null;
	}

	// Used at startup to seed the ApplicationDataService members for all results databases.
	public static Map<String, AvailableResultsDbConfig> createResultsDatabaseList() {
		String methodname = "createDatabaseList";
		Map<String, AvailableResultsDbConfig> allDatabasesList_OUT = new LinkedHashMap<>();

		try {
			// First get the number of databases that are defined.
			String numDBsKey = AvailableResultsDbConfig.DATABASE_LISTING_NUM_DATABASES_KEY;
			String numDatabases = UTPropertyManager.getProperty(numDBsKey);
			int numDBs = Integer.parseInt(numDatabases);

			// Next create objects on list for each of the dbs.
			for (int i = 1; i <= numDBs; i++) {
				String dbListingPropertyKey = AvailableResultsDbConfig.DATABASE_LISTING_KEY + "." + i + "." + AvailableResultsDbConfig.DATABASE_LISTING_KEY_DATABASE_NAME;
				String databaseName = UTPropertyManager.getProperty(dbListingPropertyKey);

				// Create a new AvailableDatabaseProperties object and place on the list.
				AvailableResultsDbConfig newDB = new AvailableResultsDbConfig();
				newDB.setListId(Integer.toString(i));
				newDB.setDatabaseName(databaseName);
				newDB.setPropertiesListingKeyPrefix(AvailableResultsDbConfig.DATABASE_LISTING_KEY_PREFIX + "." + databaseName);

				newDB.parseProperties();
				allDatabasesList_OUT.put(Integer.toString(i - 1), newDB);
			}
		} catch (Exception e) {
			String errorMsg = " - Failed to load available databases from property file.";
			System.out.println(CLASSNAME + "." + methodname + "============= ERROR IN GUI! Exception Message:  [" + e.getMessage() + "]");
			System.out.println(errorMsg);
		}

		return allDatabasesList_OUT;
	}

	// Used by GUI elements, both parameters are output params.
	public static void createDatabaseSelectList(Map<String, AvailableResultsDbConfig> allDatabasesList_OUT, Vector<String> allDatabasesUiSelectList_OUT) {
		String methodname = "createDatabaseSelectList";

		try {
			UTPropertyManager._initialize();

			// First get the number of databases that are defined.
			String numDBsKey = AvailableResultsDbConfig.DATABASE_LISTING_NUM_DATABASES_KEY;
			String numDatabases = UTPropertyManager.getProperty(numDBsKey);
			int numDBs = Integer.parseInt(numDatabases);

			// Next create objects on list for each of the dbs.
			for (int i = 1; i <= numDBs; i++) {
				String dbListingPropertyKey = AvailableResultsDbConfig.DATABASE_LISTING_KEY + "." + i + "." + AvailableResultsDbConfig.DATABASE_LISTING_KEY_DATABASE_NAME;
				String databaseName = UTPropertyManager.getProperty(dbListingPropertyKey);

				// Create a new AvailableDatabaseProperties object and place on the list.
				AvailableResultsDbConfig newDB = new AvailableResultsDbConfig();
				newDB.setListId(Integer.toString(i));
				newDB.setDatabaseName(databaseName);
				newDB.setPropertiesListingKeyPrefix(AvailableResultsDbConfig.DATABASE_LISTING_KEY_PREFIX + "." + databaseName);

				newDB.parseProperties();
				allDatabasesList_OUT.put(Integer.toString(i - 1), newDB);
				allDatabasesUiSelectList_OUT.add(newDB.getDatabaseConnectionUrl());
			}
		} catch (Exception e) {
			String errorMsg = " - Failed to load available databases from property file.";
			System.out.println(CLASSNAME + "." + methodname + "============= ERROR IN GUI! Exception Message:  [" + e.getMessage() + "]");
			System.out.println(errorMsg);
		}
	}

	public void parseProperties() throws Exception {
		String methodname = "parseProperties";

		databaseName = UTPropertyManager.getProperty(propertiesListingKeyPrefix + "." + DbConfigBase.DATABASE_LISTING_KEY_DATABASE_NAME);
		databaseConnectionUrl = UTPropertyManager.getProperty(propertiesListingKeyPrefix + "." + DATABASE_LISTING_KEY_CONNECTION_URL);
		databaseConnectionUsername = UTPropertyManager.getProperty(propertiesListingKeyPrefix + "." + DATABASE_LISTING_KEY_CONNECTION_USERNAME);
		databaseConnectionPassword = UTPropertyManager.getProperty(propertiesListingKeyPrefix + "." + DATABASE_LISTING_KEY_CONNECTION_PASSWORD);
		databaseAvailInvestorDemo = UTPropertyManager.getProperty(propertiesListingKeyPrefix + "." + DATABASE_AVAILABLE_FOR_INVESTOR_DEMO);
	}

	// -------------------------------------- XML SAVE/RESTORE --------------------------------------------
	public AvailableResultsDbConfig(String xmldata) {
		try {
			this.databaseName = UTXmlUtils.getXmlData(xmldata, "DB_NAME");
			this.databaseConnectionUrl = UTXmlUtils.getXmlData(xmldata, "DB_CONNECTION_URL");
			this.databaseConnectionUsername = UTXmlUtils.getXmlData(xmldata, "DB_CONNECTION_USERNAME");
			this.databaseConnectionPassword = UTXmlUtils.getXmlData(xmldata, "DB_CONNECTION_PASSWORD");
			this.databaseAvailInvestorDemo = UTXmlUtils.getXmlData(xmldata, "AVAIL_INVESTOR_DEMO");
			this.listId = UTXmlUtils.getXmlData(xmldata, "LIST_ID");
			this.propertiesListingKeyPrefix = UTXmlUtils.getXmlData(xmldata, "LISTING_KEY_PREFIX");
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + ".Constructor; message [" + e.getMessage() + "]");
		}
	}

	public String toXml(String prefix, String endline) {
        String stb = prefix + "<RESULTS_DB>" + endline +
                prefix + UTDisplayFormatter.TAB + "<DB_NAME>" + this.getDatabaseName() + "</DB_NAME>" + endline +
                prefix + UTDisplayFormatter.TAB + "<DB_CONNECTION_URL>" + this.getDatabaseConnectionUrl() + "</DB_CONNECTION_URL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<DB_CONNECTION_USERNAME>" + this.getDatabaseConnectionUsername() + "</DB_CONNECTION_USERNAME>" + endline +
                prefix + UTDisplayFormatter.TAB + "<DB_CONNECTION_PASSWORD>" + this.getDatabaseConnectionPassword() + "</DB_CONNECTION_PASSWORD>" + endline +
                prefix + UTDisplayFormatter.TAB + "<AVAIL_INVESTOR_DEMO>" + this.getDatabaseAvailInvestorDemo() + "</AVAIL_INVESTOR_DEMO>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LIST_ID>" + this.listId + "</LIST_ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LISTING_KEY_PREFIX>" + this.propertiesListingKeyPrefix + "</LISTING_KEY_PREFIX>" + endline +
                prefix + "</RESULTS_DB>" + endline;
		return stb;
	}
}
