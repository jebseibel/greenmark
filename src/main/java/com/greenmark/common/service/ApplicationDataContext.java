package com.greenmark.common.service;

import com.greenmark.common.config.Config;
import com.greenmark.common.config.ConfigApplication;
import com.greenmark.common.database.AvailablePricesDbConfig;
import com.greenmark.common.database.AvailableResultsDbConfig;
import com.greenmark.common.database.DbConnectionFactory;
import com.greenmark.common.enums.GreenmanServiceEnum;
import com.greenmark.utils.*;
import com.greenmark.utils.trace.Trace;
import com.greenmark.utils.trace.TraceParameters;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.Map;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2022</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ApplicationDataContext</p>
 * <p>Description: This Context provides global information when the Greenman trading client is executing.  It allows us to store these member variables in a single location for easy access by any
 *    class or feature of any application.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
@Slf4j
public class ApplicationDataContext {
    private static final String CLASSNAME = "ApplicationDataContext";

    private static final boolean PERFORMANCE_UPDATES_PRICES_DB_ON = true;
    private static final boolean PERFORMANCE_UPDATES_RESULTS_DB_ON = false;
    // Save/Restore XML
    public static String lastfilesaved_services = "";
    private static boolean isInitialized = false;
    // Stores the user-selected database configuration
    private static AvailableResultsDbConfig resultsDbProperties;
    private static AvailablePricesDbConfig pricesDbProperties;
    private static Map<String, AvailableResultsDbConfig> configuredResultsDbs;
    private static Map<String, AvailablePricesDbConfig> configuredPricesDbs;
    // Configured once for app and used by any class or service.
    private static Trace configuredTrace;
    // Are we using the priceHistory DB or the live data feed?
    private static boolean isHistoricalDatafeed;
    // Did we instantiate the datafeed for the Charts application?
    private static boolean isChartsDatafeed;
    // This indicates the user has confirmed the NewAccount dialog and is ready to begin. Not saved/restored to XML.
    private static boolean scenarioReadyToBegin = false;

    // This is the name of a strategy that was loaded using DialogLoadStrategy, stored here so we can display in DialogAbout.
    private static String loadedStrategyName;

    // -------------- TEMP PERFORMANCE DEBUG
    public static boolean isPricesPerformanceUpdatesOn() {
        return PERFORMANCE_UPDATES_PRICES_DB_ON;
    }

    public static boolean isResultsPerformanceUpdatesOn() {
        return PERFORMANCE_UPDATES_RESULTS_DB_ON;
    }

    public static void initializeFromXml(UTCalendarTime inputDate, GreenmanServiceEnum service) {
        if (!isInitialized) {
            TraceParameters params = getTraceParameters();

            initializeConfiguredDatabases();

            _initGlobalServices(inputDate);
            initialize(lastfilesaved_services);
            isInitialized = true;
        }
    }

    public static void initializeConfiguredDatabases() {

        // Setup the RESULTS DB to use the selected properties for the user.
        Map<String, AvailableResultsDbConfig> resultsDatabases = AvailableResultsDbConfig.createResultsDatabaseList();
        setConfiguredResultsDbs(resultsDatabases);

        // Now set the first database as the default and query for it.
        AvailableResultsDbConfig selectedResultsDbProperties = resultsDatabases.get("0");
        setResultsDbProperties(selectedResultsDbProperties);
        setResultsDbConnectionProperties();

        // Setup the PRICES DB to use the selected properties for the user.
        Map<String, AvailablePricesDbConfig> pricesDatabases = AvailablePricesDbConfig.createPricesDatabaseList();
        ApplicationDataContext.setConfiguredPricesDbs(pricesDatabases);

        // Now set the first database as the default and query for it.
        AvailablePricesDbConfig selectedDbProperties = pricesDatabases.get("0");
        setPricesDbProperties(selectedDbProperties);
        setPricesDbConnectionProperties();
    }

    private static final void _initGlobalServices(UTCalendarTime inputDate) throws NumberFormatException {
        String methodname = "_initGlobalServices";

        try {
            String basedir = UTPropertyManager.getProperty("base_config_directory");

            String pathAndFile = "";

            String savepath = basedir + "/" + UTPropertyManager.getProperty("global_data_save_dir");
            String savefile = UTPropertyManager.getProperty("global_data_filename_prefix");

            // If the inputDate was passed in, load the xml file for the specified date.
            if (inputDate == null)
                savefile = UTDirectoryHandler.getLatestFile(savepath, false);
            else
                savefile = UTDirectoryHandler.getXmlFileByDatetime(savepath, inputDate, false);

            String config = "";
            if (UTUtils.isNotNorE(savefile)) {
                pathAndFile = savepath + "/" + savefile;
                config = UTFileHandler.loadTextFile(pathAndFile).toString();
            } else
                System.out.println(" . . . GlobalExecutionServices: no saved file, use default");

            lastfilesaved_services = config;

        } catch (NumberFormatException ne) {
            System.out.println(" . . .NumberFormatException in " + CLASSNAME + "." + methodname + " Exception Message:  [" + ne.getMessage() + "]");
            throw ne;
        } catch (Exception e) {
            System.out.println(" . . .Exception in " + CLASSNAME + "." + methodname + " Exception Message:  [" + e.getMessage() + "]");
        }

    }

    public static final void dumpGlobalDataToFile(Trace trace) {
        String methodname = "dumpGlobalDataToFile";
        trace.in(methodname, CLASSNAME);

        try {
            // start by getting the xml output
            String basicxmldata = ApplicationDataContext.toXml(UTDisplayFormatter.TAB, "\n");

            // compare it to the last one that was saved
            // if it is different, write it out
            if (!lastfilesaved_services.equals(basicxmldata)) {
                // if it is different, write it out
                String filepath = UTPropertyManager.getProperty("base_config_directory") + "/" + UTPropertyManager.getProperty("global_data_save_dir") + "/";
                String fileprefix = UTPropertyManager.getProperty("global_data_filename_prefix");
                String filename = filepath + fileprefix + "_" + UTDatetime.getCurrentDatetimeForFilename() + ".xml";

                UTFileHandler.writeFileToDisk(filename, basicxmldata);
                if (trace.isLevelSparse())
                    log.debug("********************************************** writing file [" + filename + "]");

                // finally save this one as the new reference
                lastfilesaved_services = basicxmldata;
            }
        } catch (Exception ex) {
            log.error("Caught General Exception " + CLASSNAME + "." + methodname + ":[" + ex.getMessage() + "]");
        }
        trace.out(methodname, CLASSNAME);
    }

    // ------------------------------------------------------------------- MISCELLANEOUS METHODS ----------------------------------------------------------------

    // This is called if we have to re-init the DB Connection pool, the user changed their selected DB
    public static void setResultsDbConnectionProperties() {
        DbConnectionFactory.setInitializedReporting(false);

        UTPropertyManager.setProperty(UTPropertyManager.getThisenv() + "." + AvailableResultsDbConfig.DATABASE_LISTING_KEY_DATABASE_NAME, resultsDbProperties.getDatabaseName());
        UTPropertyManager.setProperty(UTPropertyManager.getThisenv() + "." + AvailableResultsDbConfig.DATABASE_LISTING_KEY_CONNECTION_URL, resultsDbProperties.getDatabaseConnectionUrl());
        UTPropertyManager.setProperty(UTPropertyManager.getThisenv() + "." + AvailableResultsDbConfig.DATABASE_LISTING_KEY_CONNECTION_USERNAME, resultsDbProperties.getDatabaseConnectionUsername());
        UTPropertyManager.setProperty(UTPropertyManager.getThisenv() + "." + AvailableResultsDbConfig.DATABASE_LISTING_KEY_CONNECTION_PASSWORD, resultsDbProperties.getDatabaseConnectionPassword());
    }

    public static void setPricesDbConnectionProperties() {
        DbConnectionFactory.setInitializedReporting(false);

        UTPropertyManager.setProperty(UTPropertyManager.getThisenv() + "." + AvailablePricesDbConfig.DATABASE_LISTING_KEY_DATABASE_NAME, pricesDbProperties.getDatabaseName());
        UTPropertyManager.setProperty(UTPropertyManager.getThisenv() + "." + AvailablePricesDbConfig.DATABASE_LISTING_KEY_CONNECTION_URL, pricesDbProperties.getDatabaseConnectionUrl());
        UTPropertyManager.setProperty(UTPropertyManager.getThisenv() + "." + AvailablePricesDbConfig.DATABASE_LISTING_KEY_CONNECTION_USERNAME, pricesDbProperties.getDatabaseConnectionUsername());
        UTPropertyManager.setProperty(UTPropertyManager.getThisenv() + "." + AvailablePricesDbConfig.DATABASE_LISTING_KEY_CONNECTION_PASSWORD, pricesDbProperties.getDatabaseConnectionPassword());
    }

    public static final TraceParameters getTraceParameters() {
        String saveMessagesToFile = Config.getValue(ConfigApplication.CONFIG_SAVE_MSGS_TO_LOGFILE);
        String logLevel = Config.getValue(ConfigApplication.CONFIG_TRACE_LOGLEVEL).toUpperCase();
        String messagesOn = Config.getValue(ConfigApplication.CONFIG_TRACE_MESSAGES_ON);
        String timerOn = Config.getValue(ConfigApplication.CONFIG_TRACE_TIMER_ON);
        String systemOut = Config.getValue(ConfigApplication.CONFIG_TRACE_SYSTEM_OUT);
        TraceParameters params = new TraceParameters(saveMessagesToFile, logLevel, messagesOn, timerOn, systemOut);
        return params;
    }

    // ------------------------------------------------------------------- XML STORE/RETRIEVE ----------------------------------------------------------------
    public static void initialize(String xmldata) {
        try {
            isHistoricalDatafeed = UTXmlUtils.getXmlDataAsBoolean(xmldata, "IS_HISTORICAL_DATAFEED");
            isChartsDatafeed = UTXmlUtils.getXmlDataAsBoolean(xmldata, "IS_CHARTS_DATAFEED");

            String resultsDbString = UTXmlUtils.getXmlData(xmldata, "RESULTS_DB_CONNECTION_DATA");

            // If we can't find the account.xml file and this string is empty, setup the first database in the list as the default
            if ((resultsDbString != null) && (!resultsDbString.trim().equals(""))) {
                resultsDbProperties = new AvailableResultsDbConfig(resultsDbString);

                // Now set the DB connection properties for the restored value.
                setResultsDbConnectionProperties();
            }

            String pricesDbString = UTXmlUtils.getXmlData(xmldata, "PRICES_DB_CONNECTION_DATA");

            // If we can't find the account.xml file and this string is empty, don't setup any database connection stuff.
            if ((pricesDbString != null) && (!pricesDbString.trim().equals(""))) {
                pricesDbProperties = new AvailablePricesDbConfig(pricesDbString);

                // Now set the DB connection properties for the restored value.
                setPricesDbConnectionProperties();
            }
        } catch (Exception ex) {
            log.error("Caught General Exception " + CLASSNAME + ".constructor() message:[" + ex.getMessage() + "]");
        }
    }

    public static final String toXml(String prefix, String endline) {

        String stb = "<GLOBAL_EXECUTION_DATA>" + endline +
                prefix + "<IS_HISTORICAL_DATAFEED>" + isHistoricalDatafeed + "</IS_HISTORICAL_DATAFEED>" + endline +
                prefix + "<IS_CHARTS_DATAFEED>" + isChartsDatafeed + "</IS_CHARTS_DATAFEED>" + endline +

                //
                // add the Selected Results DB Connection information
                UTDisplayFormatter.TAB + "<RESULTS_DB_CONNECTION_DATA>" + endline +
                resultsDbProperties.toXml(prefix + UTDisplayFormatter.TAB, "\n") +
                UTDisplayFormatter.TAB + "</RESULTS_DB_CONNECTION_DATA>" + endline +

                // add the Prices Results DB Connection information
                UTDisplayFormatter.TAB + "<PRICES_DB_CONNECTION_DATA>" + endline +
                pricesDbProperties.toXml(prefix + UTDisplayFormatter.TAB, "\n") +
                UTDisplayFormatter.TAB + "</PRICES_DB_CONNECTION_DATA>" + endline +
                "</GLOBAL_EXECUTION_DATA>" + endline;
        return stb;
    }

    // ------------------------------------------------------------------- CONVENIENCE WRAPPERS ----------------------------------------------------------------
    public static boolean isHistoricalScenario() {
        return isHistoricalDatafeed() || isHistoricalDatafeed_Chart();
    }

    public static boolean isLiveScenario() {
        return isLiveDatafeed() || isLiveDatafeed_Chart();
    }

    public static boolean isLiveDatafeed_Chart() {
        return !isHistoricalDatafeed() && isChartsDatafeed();
    }

    public static boolean isHistoricalDatafeed_Chart() {
        return isHistoricalDatafeed() && isChartsDatafeed();
    }

    public static boolean isDebugOnForStock(String symbol) {
        String useDebugInfo = UTPropertyManager.getProperty("log_stock_debug");
        if (useDebugInfo.equals("1")) {
            // if (symbol.equals("IEP"))
            // return true;
        }

        return false;
    }

    public static Connection getDefaultResultsDbConnection() throws Exception {
        AvailableResultsDbConfig defaultResultsDb = null;
        Map<String, AvailableResultsDbConfig> resultsDatabasesList = ApplicationDataContext.getConfiguredResultsDbs();
        for (AvailableResultsDbConfig resultsDbConfig : resultsDatabasesList.values()) {
            defaultResultsDb = resultsDbConfig;
            break;
        }
        return DbConnectionFactory.getWebsiteConnection(defaultResultsDb);
    }

    public static Connection getDefaultPricesDbConnection() throws Exception {
        AvailablePricesDbConfig defaultPricesDb = null;
        Map<String, AvailablePricesDbConfig> resultsDatabasesList = ApplicationDataContext.getConfiguredPricesDbs();
        for (AvailablePricesDbConfig resultsDbConfig : resultsDatabasesList.values()) {
            defaultPricesDb = resultsDbConfig;
            break;
        }
        return DbConnectionFactory.getPdbConnection(defaultPricesDb);
    }

    // ------------------------------------------------------------------- SETTERS/GETTERS ----------------------------------------------------------------
    public static AvailableResultsDbConfig getResultsDbProperties() {
        return resultsDbProperties;
    }

    public static void setResultsDbProperties(AvailableResultsDbConfig resultsDbProperties) {
        ApplicationDataContext.resultsDbProperties = resultsDbProperties;
    }

    public static AvailablePricesDbConfig getPricesDbProperties() {
        return pricesDbProperties;
    }

    public static void setPricesDbProperties(AvailablePricesDbConfig pricesDbProperties) {
        ApplicationDataContext.pricesDbProperties = pricesDbProperties;
    }

    public static Map<String, AvailableResultsDbConfig> getConfiguredResultsDbs() {
        return configuredResultsDbs;
    }

    public static void setConfiguredResultsDbs(Map<String, AvailableResultsDbConfig> configuredResultsDbs) {
        ApplicationDataContext.configuredResultsDbs = configuredResultsDbs;
    }

    public static Map<String, AvailablePricesDbConfig> getConfiguredPricesDbs() {
        return configuredPricesDbs;
    }

    public static void setConfiguredPricesDbs(Map<String, AvailablePricesDbConfig> configuredPricesDbs) {
        ApplicationDataContext.configuredPricesDbs = configuredPricesDbs;
    }

    public static Trace getConfiguredTrace() {
        return configuredTrace;
    }

    // This is used by webapp in BackbeanBase.configureTrace()
    public static void setConfiguredTrace(Trace configuredTrace) {
        ApplicationDataContext.configuredTrace = configuredTrace;
    }

    public static boolean isHistoricalDatafeed() {
        return isHistoricalDatafeed;
    }

    public static void setHistoricalDatafeed(boolean isHistoricalDatafeed) {
        ApplicationDataContext.isHistoricalDatafeed = isHistoricalDatafeed;
    }

    public static boolean isLiveDatafeed() {
        return !isHistoricalDatafeed;
    }

    public static boolean isChartsDatafeed() {
        return isChartsDatafeed;
    }

    public static void setChartsDatafeed(boolean isChartsDatafeed) {
        ApplicationDataContext.isChartsDatafeed = isChartsDatafeed;
    }

    public static boolean isScenarioReadyToBegin() {
        return scenarioReadyToBegin;
    }

    public static void setScenarioReadyToBegin(boolean scenarioReadyToBeginIn) {
        scenarioReadyToBegin = scenarioReadyToBeginIn;
    }

    public static String getLoadedStrategyName() {
        return loadedStrategyName;
    }

    public static void setLoadedStrategyName(String loadedStrategyName) {
        ApplicationDataContext.loadedStrategyName = loadedStrategyName;
    }
}
