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
    public static final String DATABASE_LISTING_KEY_DATABASE_NAME = "name";
    private static final long serialVersionUID = 1L;
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

        String returnString = "AvailableDatabaseProperties: " +
                "databaseName: " + databaseName +
                ",databaseConnectionUrl: " + databaseConnectionUrl +
                ",databaseConnectionUsername: " + databaseConnectionUsername +
                ",databaseConnectionPassword: " + databaseConnectionPassword +
                ",databaseAvailInvestorDemo: " + databaseAvailInvestorDemo +
                ",listId: " + listId +
                ",propertiesListingKeyPrefix: " + propertiesListingKeyPrefix;

        return returnString;
    }

    // ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getDatabaseConnectionUrl() {
        return databaseConnectionUrl;
    }

    public void setDatabaseConnectionUrl(String databaseConnectionUrl) {
        this.databaseConnectionUrl = databaseConnectionUrl;
    }

    public String getDatabaseConnectionUsername() {
        return databaseConnectionUsername;
    }

    public void setDatabaseConnectionUsername(String databaseConnectionUsername) {
        this.databaseConnectionUsername = databaseConnectionUsername;
    }

    public String getDatabaseConnectionPassword() {
        return databaseConnectionPassword;
    }

    public void setDatabaseConnectionPassword(String databaseConnectionPassword) {
        this.databaseConnectionPassword = databaseConnectionPassword;
    }

    public String getPropertiesListingKeyPrefix() {
        return propertiesListingKeyPrefix;
    }

    public void setPropertiesListingKeyPrefix(String propertiesListingKeyPrefix) {
        this.propertiesListingKeyPrefix = propertiesListingKeyPrefix;
    }

    public String getDatabaseAvailInvestorDemo() {
        return databaseAvailInvestorDemo;
    }

    public void setDatabaseAvailInvestorDemo(String databaseAvailInvestorDemo) {
        this.databaseAvailInvestorDemo = databaseAvailInvestorDemo;
    }
}
