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
 * <p>Title: AvailablePricesDbConfig</p>
 * <p>Description: This class is used to hold the JDBC connection string properties and is used when the user can select different databases.
 * This class is also used to parse the greenman.properties file values in its:  PRICE HISTORY DATABASES LISTING W/ CONNECTION STRINGS section.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public final class AvailablePricesDbConfig extends DbConfigBase implements Serializable {
	public static final String CLASSNAME = "AvailablePricesDbConfig";

	private static final long serialVersionUID = 1L;

	public static final String DATABASE_LISTING_KEY_CONNECTION_URL = "PDB_CONNECTION_URL";
	public static final String DATABASE_LISTING_KEY_CONNECTION_USERNAME = "PDB_CONNECTION_USERNAME";
	public static final String DATABASE_LISTING_KEY_CONNECTION_PASSWORD = "PDB_CONNECTION_PASSWORD";

	/**
	 * These are the values of the keys used for the database properties.
	 */
	public static final String DATABASE_LISTING_NUM_DATABASES_KEY = "dbPriceHistory.numDatabases";
	public static final String DATABASE_LISTING_KEY = "dbPriceHistory.dbListing";
	public static final String DATABASE_LISTING_KEY_PREFIX = "dbPriceHistory";

	public AvailablePricesDbConfig() {
	}

	public static AvailablePricesDbConfig getPricesDbConfigByUrl(Map<String, AvailablePricesDbConfig> pricesConfigs, String url) {
		for (AvailablePricesDbConfig priceConfig : pricesConfigs.values()) {
			if (priceConfig.getDatabaseConnectionUrl().equals(url))
				return priceConfig;
		}
		return null;
	}

	public static Map<String, AvailablePricesDbConfig> createPricesDatabaseList() {
		String methodname = "createDatabaseUrlSelectList";
		Map<String, AvailablePricesDbConfig> allDatabasesList_OUT = new LinkedHashMap<>();

		try {
			UTPropertyManager._initialize();

			// First get the number of databases that are defined.
			String numDBsKey = AvailablePricesDbConfig.DATABASE_LISTING_NUM_DATABASES_KEY;
			String numDatabases = UTPropertyManager.getProperty(numDBsKey);
			int numDBs = Integer.parseInt(numDatabases);

			// Next create objects on list for each of the dbs.
			for (int i = 1; i <= numDBs; i++) {
				String dbListingPropertyKey = AvailablePricesDbConfig.DATABASE_LISTING_KEY + "." + i + "." + AvailableResultsDbConfig.DATABASE_LISTING_KEY_DATABASE_NAME;
				String databaseName = UTPropertyManager.getProperty(dbListingPropertyKey);

				// Create a new AvailableDatabaseProperties object and place on the list.
				AvailablePricesDbConfig newDB = new AvailablePricesDbConfig();
				newDB.setListId(Integer.toString(i));
				newDB.setDatabaseName(databaseName);
				newDB.setPropertiesListingKeyPrefix(AvailablePricesDbConfig.DATABASE_LISTING_KEY_PREFIX + "." + databaseName);

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

	public static void createDatabaseSelectList(Map<String, AvailablePricesDbConfig> allDatabasesList_OUT, Vector<String> allDatabasesUiSelectList_OUT, boolean seedSelectListWIthNames) {
		String methodname = "createDatabaseUrlSelectList    ";

		try {
			UTPropertyManager._initialize();

			// First get the number of databases that are defined.
			String numDBsKey = AvailablePricesDbConfig.DATABASE_LISTING_NUM_DATABASES_KEY;
			String numDatabases = UTPropertyManager.getProperty(numDBsKey);
			int numDBs = Integer.parseInt(numDatabases);

			// Next create objects on list for each of the dbs.
			for (int i = 1; i <= numDBs; i++) {
				String dbListingPropertyKey = AvailablePricesDbConfig.DATABASE_LISTING_KEY + "." + i + "." + AvailableResultsDbConfig.DATABASE_LISTING_KEY_DATABASE_NAME;
				String databaseName = UTPropertyManager.getProperty(dbListingPropertyKey);

				// Create a new AvailableDatabaseProperties object and place on the list.
				AvailablePricesDbConfig newDB = new AvailablePricesDbConfig();
				newDB.setListId(Integer.toString(i));
				newDB.setDatabaseName(databaseName);
				newDB.setPropertiesListingKeyPrefix(AvailablePricesDbConfig.DATABASE_LISTING_KEY_PREFIX + "." + databaseName);

				newDB.parseProperties();
				allDatabasesList_OUT.put(Integer.toString(i - 1), newDB);

				if (seedSelectListWIthNames)
					allDatabasesUiSelectList_OUT.add(newDB.getDatabaseName());
				else
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
	}

	// -------------------------------------- XML SAVE/RESTORE --------------------------------------------
	public AvailablePricesDbConfig(String xmldata) {
		try {
			this.databaseName = UTXmlUtils.getXmlData(xmldata, "PDB_NAME");
			this.databaseConnectionUrl = UTXmlUtils.getXmlData(xmldata, "PDB_CONNECTION_URL");
			this.databaseConnectionUsername = UTXmlUtils.getXmlData(xmldata, "PDB_CONNECTION_USERNAME");
			this.databaseConnectionPassword = UTXmlUtils.getXmlData(xmldata, "PDB_CONNECTION_PASSWORD");
			this.listId = UTXmlUtils.getXmlData(xmldata, "LIST_ID");
			this.propertiesListingKeyPrefix = UTXmlUtils.getXmlData(xmldata, "LISTING_KEY_PREFIX");
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + ".Constructor; message [" + e.getMessage() + "]");
		}
	}

	public final String toXml(String prefix, String endline) {
		StringBuffer stb = new StringBuffer();
		stb.append(prefix + "<PRICES_DB>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<PDB_NAME>" + this.getDatabaseName() + "</PDB_NAME>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<PDB_CONNECTION_URL>" + this.getDatabaseConnectionUrl() + "</PDB_CONNECTION_URL>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<PDB_CONNECTION_USERNAME>" + this.getDatabaseConnectionUsername() + "</PDB_CONNECTION_USERNAME>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<PDB_CONNECTION_PASSWORD>" + this.getDatabaseConnectionPassword() + "</PDB_CONNECTION_PASSWORD>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<LIST_ID>" + this.listId + "</LIST_ID>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<LISTING_KEY_PREFIX>" + this.propertiesListingKeyPrefix + "</LISTING_KEY_PREFIX>" + endline);
		stb.append(prefix + "</PRICES_DB>" + endline);
		return stb.toString();
	}
}
