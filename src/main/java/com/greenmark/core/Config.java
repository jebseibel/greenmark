package com.greenmark.core;

import com.greenmark.account.Account;
import com.greenmark.model.Model;
import com.greenmark.utils.*;
import com.greenmark.utils.dv.DVDatavault;
import com.greenmark.utils.dv.DVXmlToDatavault;
import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Properties;


/////////////////////////////////////////////////
// START CLASS: Config
/////////////////////////////////////////////////
@Slf4j
public class Config {
////////////////////////////////////////////////////////////////////////////////
// PUBLIC CONSTANTS
////////////////////////////////////////////////////////////////////////////////

    /**
     * This is the class name.
     */
    public static final String CLASSNAME = "Config";

    public static final String START_MARKET_TREND_DVTAG = "DV:XML.MODEL.MKTD";

    public static final String CONFIG_MODEL_ID = "DV:XML.MODEL.MODEL_ID";
    public static final String CONFIG_ACCOUNT_ID = "DV:XML.MODEL.ACCOUNT_ID";
    public static final String CONFIG_SCENARIO_ID = "DV:XML.MODEL.SCENARIO_ID";

    public static final String CONFIG_USE_AFTER_MARKET_DATA = "DV:XML.MODEL.MARKET.USE_AFTER_MARKET_DATA";

    public static final String CONFIG_TRACE_LOGLEVEL = "DV:XML.CONFIG_NO_DB_RESTORE.TRACE.LOG_LEVEL";

    public static final String CONFIG_SAVE_MSGS_TO_LOGFILE = "DV:XML.CONFIG_NO_DB_RESTORE.TRACE.SAVE_MSGS_TO_LOGFILE";

    public static final String CONFIG_MARKET_ALWAYS_OPEN = "DV:XML.MODEL.MARKET.ALWAYS_OPEN";
    public static final String CONFIG_MARKET_TREND_HASFLAT = "DV:XML.MODEL.MKTD.FLAT";

    public static final String CONFIG_MARKET_TREND_USE = "DV:XML.MODEL.MARKET.MARKET_TRENDS.USE";
    public static final String CONFIG_MARKET_TREND_ENABLED = "DV:XML.MODEL.MARKET.MARKET_TRENDS.ENABLE";

    public static final String CONFIG_MARKET_TREND_CURRENT_TYPE = "DV:XML.MODEL.MARKET.MARKET_TRENDS.CURRENT_TYPE";
    public static final String CONFIG_MARKET_TREND_CURRENT_VALUE = "DV:XML.MODEL.MARKET.MARKET_TRENDS.CURRENT_VALUE";

    public static final String CONFIG_MARKET_TREND_NUM_GAUGES = "DV:XML.MODEL.MARKET.MARKET_TRENDS.NUM_GAUGES";
    public static final String CONFIG_MARKET_TREND_CURRENT_GAUGE_VALUE = "DV:XML.MODEL.MARKET.MARKET_TRENDS.GAUGE_VALUE";

    public static final String CONFIG_MARKET_TREND_ANALYSIS_OPTION = "DV:XML.MODEL.MARKET.MARKET_TRENDS.ANALYSIS_OPTION";
    public static final String CONFIG_MARKET_TREND_ANALYSIS_INDICE = "DV:XML.MODEL.MARKET.MARKET_TRENDS.ANALYSIS_INDICE";

    public static final String CONFIG_MARKET_TREND_BEAR_START_STOCHK = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BEAR_START_STOCHK";
    public static final String CONFIG_MARKET_TREND_BEAR_STOP_STOCHK = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BEAR_STOP_STOCHK";
    public static final String CONFIG_MARKET_TREND_BULL_START_STOCHK = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BULL_START_STOCHK";
    public static final String CONFIG_MARKET_TREND_BULL_STOP_STOCHK = "DV:XML.MODEL.MARKET.MARKET_TRENDS.BULL_STOP_STOCHK";

    public static final int NUM_MKT_TREND_INDICATORS = 2;
    public static final String MKT_TREND_INDICATOR_STOCHASTIC = "Stochastic";
    public static final String MKT_TREND_INDICATOR_SMA_CROSSOVER = "Double SMA Crossover";

    public static final int NUM_MKT_TREND_STOCKS = 2;
    public static final String MKT_TREND_INDICE_NAME0 = "S&P 500 Index";
    public static final String MKT_TREND_INDICE_NAME1 = "Russell 2000 Index";
    public static final String MKT_TREND_INDICE_SYMBOL0 = "SPX.XO";
    public static final String MKT_TREND_INDICE_SYMBOL1 = "RUT.XO";

    public static final int NUM_MKT_TREND_TYPES = 3;
    public static final String MKT_TREND_TYPE_NONE_STRING = "NONE";
    public static final String MKT_TREND_TYPE_BEAR_STRING = "BEAR";
    public static final String MKT_TREND_TYPE_FLAT_STRING = "FLAT";
    public static final String MKT_TREND_TYPE_BULL_STRING = "BULL";

    public static final int MKT_TREND_TYPE_NONE_INT = -1;
    public static final int MKT_TREND_TYPE_BULL_INT = 0;
    public static final int MKT_TREND_TYPE_FLAT_INT = 1;
    public static final int MKT_TREND_TYPE_BEAR_INT = 2;

    public static final String CONFIG_BROKER_TYPE = "DV:XML.MODEL.BROKER.BROKER_TYPE";
    public static final String CONFIG_BROKER_TYPE_DELAY = "DV:XML.MODEL.BROKER.BROKER_TYPE_DELAY";


    public static final String CONFIG_USE_MESSAGE_PANEL = "DV:XML.CONFIG_NO_DB_RESTORE.APPLICATION.USE_MESSAGE_PANEL";
    public static final String CONFIG_STOCK_MEMORY_ALLOC_SIZE = "DV:XML.MODEL.APPLICATION.STOCK_MEMORY_ALLOC_SIZE";
    public static final String CONFIG_HISTORICAL_GUI_MODE = "DV:XML.CONFIG_NO_DB_RESTORE.APPLICATION.HISTORICAL_GUI_MODE";

    public static final String CONFIG_HISTORICAL_GUI_ON = "GUI ON";
    public static final String CONFIG_HISTORICAL_GUI_OFF = "GUI OFF";
    public static final String CONFIG_HISTORICAL_GUI_SUMMARY_ONLY = "OFF - DAILY SUMMARY ONLY";

    public static final String CONFIG_APPLICATION_DISABLE_POPUP = "DV:XML.CONFIG_NO_DB_RESTORE.APPLICATION.DISABLE_POPUPS";
    public static final String CONFIG_APPLICATION_RESTART_ON_OPEN = "DV:XML.CONFIG_NO_DB_RESTORE.APPLICATION.RESTART_ON_OPEN";
    public static final String CONFIG_APPLICATION_SLEEP_AT_NIGHT = "DV:XML.MODEL.APPLICATION.SLEEP_AT_NIGHT";
    public static final String CONFIG_APPLICATION_SLEEP_TIME = "DV:XML.MODEL.APPLICATION.SLEEP_TIME";
    public static final String CONFIG_APPLICATION_HOLIDAY_LIST = "DV:XML.MODEL.APPLICATION.HOLIDAY_LIST";

    //number of days to keep things
    public static final String CONFIG_APPLICATION_NUM_DAYS_TO_KEEP_APPCONFIGS = "DV:XML.MODEL.APPLICATION.NUM_DAYS_TO_KEEP.APPCONFIGS";
    public static final String CONFIG_APPLICATION_NUM_DAYS_TO_KEEP_TRACE_LOGS = "DV:XML.MODEL.APPLICATION.NUM_DAYS_TO_KEEP.TRACE_LOGS";
    public static final String CONFIG_APPLICATION_NUM_DAYS_TO_KEEP_DAILY_LOGS = "DV:XML.MODEL.APPLICATION.NUM_DAYS_TO_KEEP.DAILY_LOGS";
    //  These error codes are for the startHarvester variable
    public static final int HARVEST_NO = 0;
    public static final int HARVEST_YES = 1;
    public static final int HARVEST_AND_MOVE_05MIN_YES = 2;
    public static final int CONFIGURE_CLEAN = 1;
    public static final int CONFIGURE_RESTART = 2;
    public static final int CONFIGURE_FROMDB = 3;
    public static String fileAccount = "";
    public static String fileBrokerOrders = "";
    public static String fileBuckets = "";
    public static String fileConfig = "";
    public static String fileHarvester = "";
    public static String fileModel = "";
    public static String fileOrders = "";
    public static String filePositions = "";
    public static String fileReport = "";
    public static String fileSimulatorOrders = "";
    public static String fileStocks = "";
    public static boolean init = false;
    public static boolean gmanStartOrRestartComplete = false;
    public static boolean gmanCalculating = false;
    public static boolean greenmanPaused = false;
    public static boolean marketOpenServiceExecuting = false;
    public static int startHarvester = Config.HARVEST_NO;
    public static Properties log4j_properties = null;
    /**
     * These variables holds the last string saved to disk. We use it for comparision to see if we need to write again.
     */
    public static String lastfilesaved_config = "";
    public static String lastfilesaved_model = "";
    private static DVDatavault dvconfigNoDbRestore = null;
    private static DVDatavault dvmodel = null;
    private static Hashtable hashConfig = new Hashtable(200);
    private static Hashtable hashModel = new Hashtable(200);

    //private static Calendar calendar;
    private static Model model = null;
    private static int initState = CONFIGURE_CLEAN;

    /**
     * Returns true/false if we should restart on open
     *
     * @return boolean
     */
    public static boolean restartOnOpen() {
        //     System.out.println("IN Config.restartOnOpen");
        try {
            String base_config_directory = UTPropertyManager.getProperty("base_config_directory");
            String config_file = UTPropertyManager.getProperty("dvconfig");
            String config = UTFileHandler.loadTextFile(base_config_directory + "/" + config_file).toString();
            String value = UTXmlUtils.getXmlData(config, "RESTART_ON_OPEN");

            if (value.equalsIgnoreCase("true")) {
//            System.out.println("RESTARTING...");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Exception in restartOnOpen " + e.getMessage());
        }
        return false;
    }

    public static boolean loadRestartData() {
//      System.out.println("IN Config.loadRestartData");
        try {
            //initialize the buckets object and update the gui
//         Buckets.initialize(  );
//         log.info( "Buckets initialized" );

//         //initialize the Account
//         AccountManager.initialize(  );
//         if (AccountManager.isNew == true)
//         {
//            AccountManager.isNew = false;
//            AccountManager.clearEventList();
//         }
//         log.info( "Account initialized" );

//         //initialize the Broker Simulator
//         BrokerSimulator.initialize();
//         log.info( "Broker Simulator initialized" );
//
//         //initialize the Broker
//         Broker.initialize();
//         log.info( "Broker initialized" );
//
//         //initialize the Harvester
//         Harvester.initialize();
//         log.info( "Harvester initialized" );
//
//         //initialize the Report
//         Report.initialize();
//         log.info( "Report initialized" );

        } catch (Exception e) {
            System.out.println("Exception in loadRestartData " + e.getMessage());
        }
        return false;
    }

    /**
     * Returns true/false if it is time to exit based on the Configuration value
     *
     * @return boolean
     */
    public static boolean isExitAppTime(String nowtime) {
        try {
            String value = Config.getValue(Config.CONFIG_APPLICATION_SLEEP_AT_NIGHT);
            //        System.out.println("isExitAppTime value ["+value+"]");
            if (value.equalsIgnoreCase("true")) {
                String sleeptime = Config.getValue(Config.CONFIG_APPLICATION_SLEEP_TIME);
//            System.out.println("nowtime ["+nowtime+"] sleeptime ["+sleeptime+"] equals: [" + nowtime.equals(sleeptime) +"]" );

                if (nowtime.equals(sleeptime)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in isHoliday " + e.getMessage());
        }
        return false;
    }

    /**
     * Returns true/false if Config value
     *
     * @return boolean
     */
    public static boolean usePopup() {
        String value = Config.getValue(Config.CONFIG_APPLICATION_DISABLE_POPUP);
        return value.equalsIgnoreCase("true");
    }

    /**
     * Use this one to change the tag based on the current market trend
     *
     * @param dvtag String
     * @return String
     */
    public static final String changeDvtagForMktd(String dvtag) {
        String retDvtag = dvtag;

//		 //check to see if market trend usage on
//		 if (MarketTrends.getUseMktd())
//		 {
//			 if (MarketTrends.getEnabledMktd())
//			 {
//				 String current = MarketTrends.getCurrentType();
//				 String endOfTag = dvtag.substring( START_MARKET_TREND_DVTAG.length()+1 );
//				 retDvtag = START_MARKET_TREND_DVTAG + "." + current + "." + endOfTag;
//			 }
//			 else
//			 {
//				 String endOfTag = dvtag.substring( START_MARKET_TREND_DVTAG.length()+1 );
//				 retDvtag = START_MARKET_TREND_DVTAG + "." + "FLAT" + "." + endOfTag;
//			 }
//		 }
        //System.out.println(" . . . before ["+dvtag+"] after ["+retDvtag+"]");

        return retDvtag;
    }

    /**
     * You this method if you want the tag modified and you know the merket trend type you want.
     *
     * @param dvtag    String
     * @param mktdType String
     * @return String
     */
    public static final String changeDvtagForMktd(String dvtag, int mktdType) {
        String retDvtag = dvtag;

        //early exit if it doesnt have START_MARKET_TREND_DVTAG
        if (dvtag.indexOf(START_MARKET_TREND_DVTAG) == -1) return dvtag;
        //set up
        String endOfTag = dvtag.substring(START_MARKET_TREND_DVTAG.length() + 1);

        //switch based on what they sent in
        switch (mktdType) {
            case Config.MKT_TREND_TYPE_BEAR_INT:
                retDvtag = START_MARKET_TREND_DVTAG + "." + Config.MKT_TREND_TYPE_BEAR_STRING + "." + endOfTag;
                break;
            case Config.MKT_TREND_TYPE_FLAT_INT:
                retDvtag = START_MARKET_TREND_DVTAG + "." + Config.MKT_TREND_TYPE_FLAT_STRING + "." + endOfTag;
                break;
            case Config.MKT_TREND_TYPE_BULL_INT:
                retDvtag = START_MARKET_TREND_DVTAG + "." + Config.MKT_TREND_TYPE_BULL_STRING + "." + endOfTag;
                break;
        }
        //System.out.println(" . . . before ["+dvtag+"] after ["+retDvtag+"]");

        return retDvtag;
    }

    /**
     * Returns the value from the configuration or model datavault depending on the prefix
     *
     * @return String
     */
    public static final boolean hasDvmodelValue(String dvtag) {
        if (!Config.init) {
            Config.initialize(CONFIGURE_CLEAN, null);
        }

        return Config.dvmodel.contains(dvtag);
    }

    /**
     * Returns the value from the configuration or model datavault depending on the prefix
     *
     * @return String
     */
    public static final String getValue(String dvtag) {
        return getValue(dvtag, true);

    }

    /**
     * Returns the value from the configuration or model datavault depending on the prefix.
     *
     * @param dvtag
     * @return String
     */
    public static final String getValue(String dvtag, boolean changeTag) {
        if (!Config.init) {
            Config.initialize(CONFIGURE_CLEAN, null);
        }

        //make sure that the DV tag starts with either model or config
        String value = "";

        //case non-Market Trend tag
        if (dvtag.startsWith("DV:XML.MODEL.")) {
            //if it is a market trend type tag - check if we have to modify the tag
            if (dvtag.startsWith(START_MARKET_TREND_DVTAG) && changeTag) dvtag = changeDvtagForMktd(dvtag);

            //check if we have it already - if so return it.
            if (hashModel.containsKey(dvtag)) {
                value = (String) hashModel.get(dvtag);
                //System.out.println(" - - - get cached ("+hashModel.size()+") dvtag [" + dvtag + "] value ["+value+"]");
                return value;
            }

            //if not go get it
            value = Config.dvmodel.getString(dvtag);

            //and save it for use later
            hashModel.put(dvtag, value);
        } else if (dvtag.startsWith("DV:XML.CONFIG_NO_DB_RESTORE.")) {
            //check if we have it already - if so return it.
            if (hashConfig.containsKey(dvtag)) {
                value = (String) hashConfig.get(dvtag);
                return value;
            }

            //if not go get it
            value = Config.dvconfigNoDbRestore.getString(dvtag);

            //and save it for use later
            hashConfig.put(dvtag, value);
        }
        return value;
    }

    /**
     * Returns the value from the model configuration datavault as an int
     *
     * @return int
     */
    public static final int getValueAsInt(String dvtag) {
        if (!Config.init) {
            Config.initialize(CONFIGURE_CLEAN, null);
        }

        try {
            String value = Config.getValue(dvtag);
            return Integer.parseInt(value);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Returns the value from the model configuration datavault as a float
     *
     * @return float
     */
    public static final float getValueAsFloat(String dvtag) {
        if (!Config.init) {
            Config.initialize(CONFIGURE_CLEAN, null);
        }

        try {
            String value = Config.getValue(dvtag);
            return Float.parseFloat(value);
        } catch (Exception e) {
            return 0F;
        }
    }

    /**
     * Returns the value from the model configuration datavault as a double
     *
     * @return double
     */
    public static final double getValueAsDouble(String dvtag) {
        if (!Config.init) {
            Config.initialize(CONFIGURE_CLEAN, null);
        }

        try {
            String value = Config.getValue(dvtag);
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0D;
        }
    }

    public static final void setValue(String dvtag, String value) {
        setValue(dvtag, value, Config.MKT_TREND_TYPE_FLAT_INT);
    }

    /**
     * Set the value of either the configuration or model depending on the dvtag.
     * Important to remember - it doesn't matter what you send in for mktd if
     * the dvtag does not start with 'DV:XML.MODEL.MKTD'. If it doesnt start
     * with that, mktd will be ignored.
     *
     * @return int
     */
    public static final void setValue(String dvtag, String value, int mktd) {
        if (!Config.init) {
            Config.initialize(CONFIGURE_CLEAN, null);
        }

        try {
            //make sure that the DV tag starts with either model or config
            if (dvtag.startsWith("DV:XML.MODEL.")) {
                //if it is a market trend type tag - check if we have to modify the tag
                if (dvtag.startsWith(START_MARKET_TREND_DVTAG)) dvtag = changeDvtagForMktd(dvtag, mktd);

                Config.dvmodel.set(dvtag, value);
                hashModel = new Hashtable(200);
            } else if (dvtag.startsWith("DV:XML.CONFIG_NO_DB_RESTORE.")) {
                Config.dvconfigNoDbRestore.set(dvtag, value);
                resetCache();
            }

        } catch (Exception e) {
            //return -1;
        }
    }

    /**
     * Returns the file holding all the positions information
     *
     * @return String
     */
    public static final String getPositions() {
        if (!Config.init) {
            Config.initialize(CONFIGURE_CLEAN, null);
        }
        return Config.filePositions;
    }

    /**
     * Returns the file holding all the stocks information
     *
     * @return String
     */
    public static final String getStocks() {
        if (!Config.init) {
            Config.initialize(CONFIGURE_CLEAN, null);
        }
        return Config.fileStocks;
    }

    /**
     * Returns the file holding all the harvester information
     *
     * @return String
     */
    public static final String getHarvester() {
        if (!Config.init) {
            Config.initialize(CONFIGURE_CLEAN, null);
        }
        return Config.fileHarvester;
    }

    /**
     * Returns the file holding all the positions information
     *
     * @return String
     */
    public static final String getOrders() {
        if (!Config.init) {
            Config.initialize(CONFIGURE_CLEAN, null);
        }
        return Config.fileOrders;
    }

    public static final String getSimulatorOrders() {
        if (!Config.init) {
            Config.initialize(CONFIGURE_CLEAN, null);
        }

        return Config.fileSimulatorOrders;
    }

    public static final String getReport() {
        if (!Config.init) {
            Config.initialize(CONFIGURE_CLEAN, null);
        }

        return Config.fileReport;
    }

    public static final String getBrokerOrders() {
        if (!Config.init) {
            Config.initialize(CONFIGURE_CLEAN, null);
        }

        return Config.fileBrokerOrders;
    }

    /**
     *
     */
    public static final void initialize() {
        Config.initialize(Config.CONFIGURE_CLEAN, null);
    }

    /**
     * @param init_state = the initial state to use
     */
    public static final boolean initialize(int init_state, UTCalendarTime inputDate) {
        return initialize(init_state, null, null, inputDate);
    }

    /**
     * New initialize used with the database.
     *
     * @param init_state = the initial state to use
     */
    public static final boolean initialize(int init_state, Account account, Model model, UTCalendarTime inputDate) {
        String methodname = "initialize";

        //reset just in case
        Config.resetCache();

        //early return if already initialized
        if (Config.init) return true;

        //else initialize
        try {
            Config.initState = init_state;

            //get the basedir
            String base_config_directory = UTPropertyManager.getProperty("base_config_directory");
            //System.out.println(" . . base_config_directory [" + base_config_directory + "]");

            //get the files needed
            Config._initModel(init_state, base_config_directory, model, inputDate);
            //Config._initMarketTrends();
            Config._initConfig(init_state, base_config_directory, inputDate);
            Config._initAccount(init_state, base_config_directory, account, inputDate);

            Config._initPositions(init_state, base_config_directory, inputDate);
            Config._initOrders(init_state, base_config_directory, inputDate);

            Config._initStocks(init_state, base_config_directory, inputDate);
            Config._initBuckets(init_state, base_config_directory, inputDate);
            Config._initHarvester(init_state, base_config_directory, inputDate);

            //set it true first, because we're gonna get the broker type next.
            Config.init = true;

            //before retrieving data, set the market trend
            //Config.initializeMarketTrends();


//         //init the market and DataFeedManager
//	      Market.initializeMarketHolidays();
//        DataFeedManager.setDatafeedType(DataFeedManager.CONFIG_DATASOURCE_LIVE);
//        DataFeedManager.initialize();
//
//	       int brokerType = getValueAsInt(Broker.MODEL_BROKER_TYPE);
//			 if ( ( brokerType == Broker.BROKER_TYPE_SIM_TRAP_EXECUTE ) ||
//				   ( brokerType == Broker.BROKER_TYPE_SIM_RANDOM_EXECUTE ) )
//			 {
//				 Config._initBrokerSimulator( init_state, base_config_directory, inputDate);
//          }

            Config._initBroker(init_state, base_config_directory, inputDate);

            //create the base calendar object
            //UTCalendar.initialize();

            //set it true
            Config.init = true;
        } catch (NumberFormatException ne) {
            System.out.println(" . . .NumberFormatException in " + CLASSNAME + "." + methodname);
            return false;
        } catch (Exception e) {
            System.out.println("Config.initialize: error loading configuration files");
        }
        //System.out.println("OUT: "+CLASSNAME +"."+ methodname );
        return true;
    }

    public static final void resetCache() {
        hashModel = new Hashtable(200);
        hashConfig = new Hashtable(200);
    }

    /**
     *
     */
    private static final void _initModel(int init_state, String basedir, Model model, UTCalendarTime inputDate)
            throws NumberFormatException {
        String methodname = "_initModel";
        try {
            //if the model object is not null, use it and return
            if (init_state == CONFIGURE_FROMDB) {
                Config.model = model;
                Config.dvmodel = DVXmlToDatavault.extract(model.getXml());
            }

            //else go and get the file from somewhere
            String thisfile = UTPropertyManager.getProperty("dvmodel");
            String pathAndFile = basedir + "/" + thisfile;
            if (init_state == CONFIGURE_RESTART) {
                String savepath = basedir + "/" + UTPropertyManager.getProperty("model_save_dir");
                String savefile = "";

                //  If the inputDate was passed in, load the xml file for the specified date.
                if (inputDate == null)
                    savefile = UTDirectoryHandler.getLatestFile(savepath, false);
                else
                    savefile = UTDirectoryHandler.getXmlFileByDatetime(savepath, inputDate, false);

                if (UTUtils.isNotNorE(savefile))
                    pathAndFile = savepath + "/" + savefile;
                else
                    System.out.println(" . . . Model: no saved file, use default");
            }
            System.out.println(" . . . pathAndFile [" + pathAndFile + "]");
            String config = UTFileHandler.loadTextFile(pathAndFile).toString();
            Config.model = new Model(config);
            Config.dvmodel = DVXmlToDatavault.extract(config);
        } catch (NumberFormatException ne) {
            System.out.println(" . . .NumberFormatException in " + CLASSNAME + "." + methodname);
            throw ne;
        } catch (Exception e) {
            System.out.println(" . . .Exception in " + CLASSNAME + "." + methodname);
        }
    }
//	public static final void initMarketTrends( )
//	{
//		//if the current config xml document is configured with MKTD, set it on.
//		//System.out.println("Config.dvmodel.dump() [" + Config.dvmodel.dump() + "]");
//		String useMktd = Config.dvmodel.getString(CONFIG_MARKET_TREND_USE );
//		String enabledMktd = Config.dvmodel.getString ( CONFIG_MARKET_TREND_ENABLED );
//      System.out.println(" . . . Market Trends: analyzed");
//		if (useMktd.equals(Labels.STR_TRUE))
//		{
//			System.out.println(" . . . . . . Market Trends is in Use");
//			MarketTrends.useMarketTrends = true;
//
//			if (enabledMktd.equals(Labels.STR_TRUE))
//			{
//				System.out.println(" . . . . . . Market Trends is Enabled");
//				System.out.println(" . . . . . . Market Trends current is FLAT");
//				MarketTrends.enabledMarketTrends = true;
//				MarketTrends.currentMarketTrend = Config.MKT_TREND_TYPE_FLAT_STRING;
//			}
//			else
//			{
//				System.out.println(" . . . . . . Market Trends is Disabled");
//				System.out.println(" . . . . . . Market Trends current is NONE");
//				MarketTrends.enabledMarketTrends = false;
//				MarketTrends.currentMarketTrend = Config.MKT_TREND_TYPE_NONE_STRING;
//			}
//		}
//		else
//		{
//			System.out.println(" . . . . . . Market Trends is NOT Use");
//			MarketTrends.useMarketTrends = false;
//
//	      System.out.println(" . . . . . . Market Trends is Disabled");
//	      System.out.println(" . . . . . . Market Trends current is NONE");
//	      MarketTrends.enabledMarketTrends = false;
//	      MarketTrends.currentMarketTrend = Config.MKT_TREND_TYPE_NONE_STRING;
//		}
//   return;
//	}

    /**
     *
     */
    private static final void _initConfig(int init_state, String basedir, UTCalendarTime inputDate)
            throws NumberFormatException {
        String methodname = "_initConfig";
        try {
            String thisfile = UTPropertyManager.getProperty("dvconfig");
            String pathAndFile = basedir + "/" + thisfile;
            if (init_state == CONFIGURE_RESTART) {
                String savepath = basedir + "/" + UTPropertyManager.getProperty("config_save_dir");
                String savefile = "";

                //  If the inputDate was passed in, load the xml file for the specified date.
                if (inputDate == null)
                    savefile = UTDirectoryHandler.getLatestFile(savepath, false);
                else
                    savefile = UTDirectoryHandler.getXmlFileByDatetime(savepath, inputDate, false);

                if (UTUtils.isNotNorE(savefile))
                    pathAndFile = savepath + "/" + savefile;
                else
                    System.out.println(" . . . Config: no saved file, use default");
            }
            String config = UTFileHandler.loadTextFile(pathAndFile).toString();
            Config.dvconfigNoDbRestore = DVXmlToDatavault.extract(config);
        } catch (NumberFormatException ne) {
            System.out.println(" . . .NumberFormatException in " + CLASSNAME + "." + methodname);
            throw ne;
        } catch (Exception e) {
            System.out.println(" . . .Exception in " + CLASSNAME + "." + methodname);
        }
    }

    /**
     *
     */
    private static final void _initAccount(int init_state, String basedir, Account account, UTCalendarTime inputDate)
            throws NumberFormatException {
        String methodname = "_initAccount";
        //System.out.println("\n\n======================= _initAccount =======================\n\n");
        try {
            //if the account object is not null, use it and return
            if (init_state == CONFIGURE_FROMDB) {
                //AccountManager.setAccount(account);
                return;
            }

            //else go and get the file from somewhere
            String thisfile = UTPropertyManager.getProperty("dvaccount");
            String pathAndFile = basedir + "/" + thisfile;
            if (init_state == CONFIGURE_RESTART) {
                String savepath = basedir + "/" + UTPropertyManager.getProperty("account_save_dir");
                String savefile = "";

                //  If the inputDate was passed in, load the xml file for the specified date.
                if (inputDate == null)
                    savefile = UTDirectoryHandler.getLatestFile(savepath, false);
                else
                    savefile = UTDirectoryHandler.getXmlFileByDatetime(savepath, inputDate, false);

                if (UTUtils.isNotNorE(savefile)) {
                    pathAndFile = savepath + "/" + savefile;
                } else
                    System.out.println(" . . . Account: no saved file, use default");
            } else {
//            Account account = AccountManager.getAccount();
//				account.setLongPositionsTotal(0);
//				account.setShortPositionsTotal(0);
            }

            String config = UTFileHandler.loadTextFile(pathAndFile).toString();
//         System.out.println("config account file length ["+config.length()+"]");
            Config.fileAccount = config;
        } catch (NumberFormatException ne) {
            System.out.println(" . . .NumberFormatException in " + CLASSNAME + "." + methodname);
            throw ne;
        } catch (Exception e) {
            //System.out.println(" . . .Exception in "+CLASSNAME+"."+methodname);
        }
        //System.out.println("OUT: "+CLASSNAME +"."+ methodname );
    }

    private static final void _initBrokerSimulator(int init_state, String basedir, UTCalendarTime inputDate)
            throws NumberFormatException {
        String methodname = "_initBrokerSimulator";
        try {

            //if we are starting fresh set the file to empty
            if ((init_state == CONFIGURE_FROMDB) || (init_state == CONFIGURE_CLEAN)) {
                Config.fileSimulatorOrders = "";
                return;
            }

            //else go and get the file from somewhere
            String savepath = basedir + "/" + UTPropertyManager.getProperty("simulator_save_dir");
            String savefile = "";

            //  If the inputDate was passed in, load the xml file for the specified date.
            if (inputDate == null)
                savefile = UTDirectoryHandler.getLatestFile(savepath, true);
            else
                savefile = UTDirectoryHandler.getXmlFileByDatetime(savepath, inputDate, true);

            if (UTUtils.isNotNorE(savefile)) {
                String pathAndFile = savepath + "/" + savefile;
                String config = UTFileHandler.loadTextFile(pathAndFile).toString();
                Config.fileSimulatorOrders = config;
            } else {
                System.out.println(" . . . Broker Simulator: no saved file, not restoring");
            }
        } catch (NumberFormatException ne) {
            System.out.println(" . . .NumberFormatException in " + CLASSNAME + "." + methodname);
            throw ne;
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + "." + methodname);
        }
    }

    private static final void _initBroker(int init_state, String basedir, UTCalendarTime inputDate)
            throws NumberFormatException {
        String methodname = "_initBroker";
        try {
            //if we are starting fresh set the file to empty
            if ((init_state == CONFIGURE_FROMDB) || (init_state == CONFIGURE_CLEAN)) {
                Config.fileBrokerOrders = "";
                return;
            }

            //else go get a file, either start or restart
            String savepath = basedir + "/" + UTPropertyManager.getProperty("broker_save_dir");
            String savefile = "";

            //  If the inputDate was passed in, load the xml file for the specified date.
            if (inputDate == null)
                savefile = UTDirectoryHandler.getLatestFile(savepath, false);
            else
                savefile = UTDirectoryHandler.getXmlFileByDatetime(savepath, inputDate, false);

            if (UTUtils.isNotNorE(savefile)) {
                String pathAndFile = savepath + "/" + savefile;
                String config = UTFileHandler.loadTextFile(pathAndFile).toString();
                Config.fileBrokerOrders = config;
            } else {
                System.out.println(" . . . Broker: no saved file, not restoring");
            }
        } catch (NumberFormatException ne) {
            System.out.println(" . . .NumberFormatException in " + CLASSNAME + "." + methodname);
            throw ne;
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + "." + methodname);
        }
    }

    /**
     *
     */
    private static final void _initPositions(int init_state, String basedir, UTCalendarTime inputDate)
            throws NumberFormatException {
        String methodname = "_initPositions";
        //System.out.println("IN: "+CLASSNAME +"."+ methodname );
        try {
            //if we are starting fresh set the file to empty
            if ((init_state == CONFIGURE_FROMDB) || (init_state == CONFIGURE_CLEAN)) {
                Config.filePositions = "";
                return;
            }

            //else go get a file, either start or restart
            String thisfile = UTPropertyManager.getProperty("positions");
            String pathAndFile = basedir + "/" + thisfile;

            String savepath = basedir + "/" + UTPropertyManager.getProperty("positions_save_dir");
            String savefile = "";

            //  If the inputDate was passed in, load the xml file for the specified date.
            if (inputDate == null)
                savefile = UTDirectoryHandler.getLatestFile(savepath, false);
            else
                savefile = UTDirectoryHandler.getXmlFileByDatetime(savepath, inputDate, false);

            if (UTUtils.isNotNorE(savefile))
                pathAndFile = savepath + "/" + savefile;
            else
                System.out.println(" . . . Positions: no saved file, use default");

            //System.out.println(" . . .pathAndFile [" + pathAndFile + "]");
            String config = UTFileHandler.loadTextFile(pathAndFile).toString();
            Config.filePositions = config;
        } catch (NumberFormatException ne) {
            System.out.println(" . . .NumberFormatException in " + CLASSNAME + "." + methodname);
            throw ne;
        } catch (Exception e) {
            //System.out.println(" . . .Exception in "+CLASSNAME+"."+methodname);
        }
        //System.out.println("OUT: "+CLASSNAME +"."+ methodname );
    }

    /**
     *
     */
    private static final void _initOrders(int init_state, String basedir, UTCalendarTime inputDate)
            throws NumberFormatException {
        String methodname = "_initOrders";
        //System.out.println("IN: "+CLASSNAME +"."+ methodname );

        try {
            //if we are starting fresh set the file to empty
            if ((init_state == CONFIGURE_FROMDB) || (init_state == CONFIGURE_CLEAN)) {
                Config.fileOrders = "";
                return;
            }

            //else go get a file, either start or restart
            String thisfile = UTPropertyManager.getProperty("orders");
            String pathAndFile = basedir + "/" + thisfile;

            String savepath = basedir + "/" + UTPropertyManager.getProperty("orders_save_dir");
            String savefile = "";

            //  If the inputDate was passed in, load the xml file for the specified date.
            if (inputDate == null)
                savefile = UTDirectoryHandler.getLatestFile(savepath, false);
            else
                savefile = UTDirectoryHandler.getXmlFileByDatetime(savepath, inputDate, false);

            if (UTUtils.isNotNorE(savefile))
                pathAndFile = savepath + "/" + savefile;
            else
                System.out.println(" . . . Orders: no saved file, use default");

            //System.out.println(" . . .pathAndFile [" + pathAndFile + "]");
            String config = UTFileHandler.loadTextFile(pathAndFile).toString();
            Config.fileOrders = config;
        } catch (NumberFormatException ne) {
            System.out.println(" . . .NumberFormatException in " + CLASSNAME + "." + methodname);
            throw ne;
        } catch (Exception e) {
            //System.out.println(" . . .Exception in "+CLASSNAME+"."+methodname);
        }

        //System.out.println("OUT: "+CLASSNAME +"."+ methodname );
    }

    /**
     *
     */
    private static final void _initStocks(int init_state, String basedir, UTCalendarTime inputDate)
            throws NumberFormatException {
        String methodname = "_initStocks";
        //System.out.println("IN: "+CLASSNAME +"."+ methodname );
        try {
            //if we are starting fresh set the file to empty
            if ((init_state == CONFIGURE_FROMDB) || (init_state == CONFIGURE_CLEAN)) {
                Config.fileStocks = "";
                return;
            }

            //else go get a file, either start or restart
            String thisfile = UTPropertyManager.getProperty("stocks");
            String pathAndFile = basedir + "/" + thisfile;

            String savepath = basedir + "/" + UTPropertyManager.getProperty("stocks_save_dir");
            String savefile = "";

            //  If the inputDate was passed in, load the xml file for the specified date.
            if (inputDate == null)
                savefile = UTDirectoryHandler.getLatestFile(savepath, false);
            else
                savefile = UTDirectoryHandler.getXmlFileByDatetime(savepath, inputDate, false);

            if (UTUtils.isNotNorE(savefile))
                pathAndFile = savepath + "/" + savefile;
            else
                System.out.println(" . . . Stocks: no saved file, use default");

            //System.out.println(" . . .pathAndFile [" + pathAndFile + "]");
            String config = UTFileHandler.loadTextFile(pathAndFile).toString();
            Config.fileStocks = config;
        } catch (NumberFormatException ne) {
            System.out.println(" . . .NumberFormatException in " + CLASSNAME + "." + methodname);
            throw ne;
        } catch (Exception e) {
            //System.out.println(" . . .Exception in "+CLASSNAME+"."+methodname);
        }

        //System.out.println("OUT: "+CLASSNAME +"."+ methodname );
    }

    /**
     *
     */
    private static final void _initHarvester(int init_state, String basedir, UTCalendarTime inputDate)
            throws NumberFormatException {
        String methodname = "_initHarvester";

        try {
            //if we are starting fresh set the file to empty
            if ((init_state == CONFIGURE_FROMDB) || (init_state == CONFIGURE_CLEAN)) {
                Config.fileHarvester = "";
                return;
            }

            //else this is a restart
            String thisfile = UTPropertyManager.getProperty("harvester");
            String pathAndFile = basedir + "/" + thisfile;

            String savepath = basedir + "/" + UTPropertyManager.getProperty("harvester_save_dir");
            String savefile = "";

            //  If the inputDate was passed in, load the xml file for the specified date.
            if (inputDate == null)
                savefile = UTDirectoryHandler.getLatestFile(savepath, false);
            else
                savefile = UTDirectoryHandler.getXmlFileByDatetime(savepath, inputDate, false);

            if (UTUtils.isNotNorE(savefile))
                pathAndFile = savepath + "/" + savefile;
            else
                System.out.println(" . . . Harvester: no saved file, use default");

            String config = UTFileHandler.loadTextFile(pathAndFile).toString();
            Config.fileHarvester = config;
        } catch (NumberFormatException ne) {
            System.out.println(" . . .NumberFormatException in " + CLASSNAME + "." + methodname);
            throw ne;
        } catch (Exception e) {
            //System.out.println(" . . .Exception in "+CLASSNAME+"."+methodname);
        }
    }

    /**
     *
     */
    private static final void _initBuckets(int init_state, String basedir, UTCalendarTime inputDate)
            throws NumberFormatException {
        String methodname = "_initBuckets";
        //System.out.println("IN: "+CLASSNAME +"."+ methodname );

        try {
            //if we are starting fresh set the file to empty
            if ((init_state == CONFIGURE_FROMDB) || (init_state == CONFIGURE_CLEAN)) {
                Config.fileBuckets = "";
                return;
            }

            //else go get a file, either start or restart
            String thisfile = UTPropertyManager.getProperty("dvbuckets");
            String pathAndFile = basedir + "/" + thisfile;

            String savepath = basedir + "/" + UTPropertyManager.getProperty("buckets_save_dir");
            String savefile = "";

            //  If the inputDate was passed in, load the xml file for the specified date.
            if (inputDate == null)
                savefile = UTDirectoryHandler.getLatestFile(savepath, false);
            else
                savefile = UTDirectoryHandler.getXmlFileByDatetime(savepath, inputDate, false);

            if (UTUtils.isNotNorE(savefile))
                pathAndFile = savepath + "/" + savefile;
            else
                System.out.println(" . . . Buckets: no saved file, use default");

            String config = UTFileHandler.loadTextFile(pathAndFile).toString();
            Config.fileBuckets = config;

        } catch (NumberFormatException ne) {
            System.out.println(" . . .NumberFormatException in " + CLASSNAME + "." + methodname);
            throw ne;
        } catch (Exception e) {
            //System.out.println(" . . .Exception in "+CLASSNAME+"."+methodname);
        }

        //System.out.println("OUT: "+CLASSNAME +"."+ methodname );
    }

    public static final void saveConfigParameters() {

        try {
            Config.dumpConfigToFile();
        } catch (Exception e) {
            log.debug("Exception in " + CLASSNAME);
        }
    }

    public static final void saveModelParameters() {
        try {
            Config.dumpModelToFile();

/**  NOT STORED TO DB HERE.
 java.util.Date today = new java.util.Date();

 //build the object
 Model model = new Model();
 model.setActive(Labels.OBJECT_ACTIVE);
 model.setModifiedDatetime( today );

 //set the type as the base type
 model.setType(Model.MODEL_TYPE_INSTANCE);

 //get the xml
 String basicxmldata = Config.dvmodel.toXml( "\t", "\n" ).toString();
 String xmldata = UTXmlUtils.getXmlData( basicxmldata, "XML");
 model.setXml( xmldata);

 //save it to the database
 ModelDAO.insert( model, trace );
 **/
        } catch (Exception e) {
            log.debug("Exception in " + CLASSNAME);
        }

    }

    /**
     * This method dumps the output of the object to an xml file.
     */
    public static final void dumpModelToFile() {
        try {
//         System.out.println(" . . IN dumpModelToFile");
            //start by getting the xml output
            String basicxmldata = Config.dvmodel.toXml("\t", "\n").toString();
            String xmldata = UTXmlUtils.getXmlData(basicxmldata, "XML");

            //compare it to the last one that was saved
            //if it is different, write it out
            if (!Config.lastfilesaved_model.equals(xmldata)) {
                //if it is different, write it out
                String filepath = UTPropertyManager.getProperty("base_config_directory") + "/" + UTPropertyManager.getProperty("model_save_dir") + "/";
                String fileprefix = UTPropertyManager.getProperty("model_filename_prefix");
                String filename = filepath + fileprefix + "_" + UTDatetime.getCurrentDatetimeForFilename() + ".xml";

                UTFileHandler.writeFileToDisk(filename, xmldata);
                log.warn("********************************************** writing file [" + filename + "]");

                //finally save this one as the new reference
                Config.lastfilesaved_model = xmldata;
            }
        } catch (Exception e) {
            log.error("Exception dumping Model to file");
        }

    }

    /**
     * This method dumps the output of the object to an xml file.
     * <p>
     * Trace
     */
    public static final void dumpConfigToFile() {
        try {
            //start by getting the xml output
//         System.out.println(" . . IN dumpConfigToFile");
            String basicxmldata = Config.dvconfigNoDbRestore.toXml("\t", "\n").toString();
            String xmldata = UTXmlUtils.getXmlData(basicxmldata, "XML");

            //compare it to the last one that was saved
            //if it is different, write it out
            if (!Config.lastfilesaved_config.equals(xmldata)) {
                //if it is different, write it out
                String filepath = UTPropertyManager.getProperty("base_config_directory") + "/" + UTPropertyManager.getProperty("config_save_dir") + "/";
                String fileprefix = UTPropertyManager.getProperty("config_filename_prefix");
                String filename = filepath + fileprefix + "_" + UTDatetime.getCurrentDatetimeForFilename() + ".xml";

                UTFileHandler.writeFileToDisk(filename, xmldata);
                log.warn("********************************************** writing file [" + filename + "]");

                //finally save this one as the new reference
                Config.lastfilesaved_config = xmldata;
            }
        } catch (Exception e) {
            log.error("Exception dumping Model to file");
        }

    }

    public static int getInitState() {
        return initState;
    }

    public static boolean isGmanCalculating() {
        return gmanCalculating;
    }

    public static void setGmanCalculating(boolean gmanCalculating) {
        gmanCalculating = gmanCalculating;
    }

    public static boolean isGreenmanPaused() {
        return greenmanPaused;
    }

    public static void setGreenmanPaused(boolean greenmanPaused) {
        greenmanPaused = greenmanPaused;
    }

    public static boolean isMarketOpenServiceExecuting() {
        return marketOpenServiceExecuting;
    }

    public static boolean isGuiOn() {
        String historicalGuiModeType = Config.getValue(CONFIG_HISTORICAL_GUI_MODE);

        return historicalGuiModeType.equals(CONFIG_HISTORICAL_GUI_ON);
    }

//  public static final void initializeMarketTrends( ) throws Exception
//  {
//	   //make sure it is valid first
//	   boolean isValid = MarketTrends.isValidModelXml(Config.getDvmodel());
//		System.out.println("IN initializeMarketTrends");
//		System.out.println(" . . . isValid [" + isValid + "]");
//
//      //get them because they might change later
//		int marketTrendUseOn = Config.getValueAsInt(Config.CONFIG_MARKET_TREND_USE);
//	   int marketTrendEnableOn = Config.getValueAsInt(Config.CONFIG_MARKET_TREND_ENABLED);
//		System.out.println(" . . . marketTrendUseOn [" + marketTrendUseOn + "]");
//		System.out.println(" . . . EnableOn [" + marketTrendEnableOn + "]");
//
//	   //get/set the status of the marketTrend Usage
//	   if (marketTrendUseOn == Labels.IS_TRUE)
//		{
//			MarketTrends.setUseMktd(true);
//
//			if (marketTrendEnableOn == Labels.IS_TRUE)
//			{
//				MarketTrends.setEnabledMktd(true);
//			}
//			else
//			{
//				MarketTrends.setEnabledMktd(false);
//			}
//		}
//		else
//		{
//			MarketTrends.setUseMktd(false);
//      }
//
//	   //mktd indice selected
//	   String analysisIndiceName = Config.getValue(Config.CONFIG_MARKET_TREND_ANALYSIS_INDICE);
//		MarketTrends.setAnalysisIndice(analysisIndiceName);
//	   System.out.println(" . . . analysisIndiceName [" + analysisIndiceName + "]");
//
//		String analysisIndiceSymbol = MarketTrends.getAnalysisIndiceSymbol();
//		System.out.println(" . . . analysisIndiceSymbol [" + analysisIndiceSymbol + "]");
//
//	   //current type
//		String currentType = Config.getValue(Config.CONFIG_MARKET_TREND_CURRENT_TYPE);
//		MarketTrends.setCurrentType(currentType);
//	   System.out.println(" . . . currentType [" + currentType + "]");
//
//		//current value
//		float currentValue = Config.getValueAsFloat(Config.CONFIG_MARKET_TREND_CURRENT_VALUE);
//		MarketTrends.setCurrentValue(currentValue);
//	   System.out.println(" . . . currentValue [" + currentValue + "]");
//   }

//  public static void updateModelInDB ()  {
//	  try
//	  {
//		  Account account = AccountManager.getAccount();
//		  long accountId = account.getId();
//
//		  long oldScenarioId = account.getScenarioId();
////		  System.out.println(" . . . oldScenarioId ["+oldScenarioId+"]");
//		  Scenario scenario = ScenarioDAO.getById(oldScenarioId, trace);
//
//		  Model thismodel = ModelDAO.getById( scenario.getModelId(), trace );
//
//		  //get the current datetime
//		  thismodel.setModifiedDatetime(UTDatetime.getNow());
//
//		  //get the current dvmodel as xml from the config object
//		  //get the xml
//		  String basicxmldata = dvmodel.toXml().toString();
//		  String xmldata = UTXmlUtils.getXmlData(basicxmldata, "XML");
//		  thismodel.setXml(xmldata);
//
//		  //Update the database
//		  ModelDAO.update(thismodel, trace);
//	  }
//	  catch( Exception ex )
//	  {
//		  System.out.println( "============= ERROR IN GUI!   Config.updateModelInDB    Exception Message:  [" + ex.getMessage() + "]" );
//	  }
//  }

    public static boolean isGuiOff() {
        String historicalGuiModeType = Config.getValue(CONFIG_HISTORICAL_GUI_MODE);

        return historicalGuiModeType.equals(CONFIG_HISTORICAL_GUI_OFF);
    }

    public static boolean isGuiDailyReportOnly() {
        String historicalGuiModeType = Config.getValue(CONFIG_HISTORICAL_GUI_MODE);

        return historicalGuiModeType.equals(CONFIG_HISTORICAL_GUI_SUMMARY_ONLY);
    }

    public static boolean isGuiBucketsOff() {
        String historicalGuiModeType = Config.getValue(CONFIG_HISTORICAL_GUI_MODE);

        return (historicalGuiModeType.equals(CONFIG_HISTORICAL_GUI_OFF)) ||
                (historicalGuiModeType.equals(CONFIG_HISTORICAL_GUI_SUMMARY_ONLY));
    }

    public static DVDatavault getDvconfigNoDbRestore() {
        return dvconfigNoDbRestore;
    }

    public static void setDvconfigNoDbRestore(DVDatavault dvconfig) {
        //set the new DV
        Config.dvconfigNoDbRestore = dvconfig;

        //clear the cache hash
        resetCache();
    }

    public static DVDatavault getDvmodel() {
        return dvmodel;
    }

    public static void setDvmodel(DVDatavault dvmodel) {
        Config.dvmodel = dvmodel;

        //clear the cache hash
        resetCache();
    }

    public static String dumpModel() {
        StringBuffer str = dvmodel.dump();
        return str.toString();
    }

    public static String dumpConfig() {
        StringBuffer str = dvconfigNoDbRestore.dump();
        return str.toString();
    }

    public static String getModelToXml() {
        return Config.dvmodel.toXml().toString();
    }

    public static Model getModel() {
        return Config.model;
    }

    /**
     * Setter on the model object
     *
     * @param model Model
     */
    public static void setModel(Model model) {
        Config.model = model;
    }
}
