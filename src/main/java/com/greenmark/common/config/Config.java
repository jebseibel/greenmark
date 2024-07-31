package com.greenmark.common.config;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsStrategy;
import com.greenmark.common.dto.strategy.StrategyDto;
import com.greenmark.utils.*;
import com.greenmark.utils.dv.DVDatavault;
import com.greenmark.utils.dv.DVXmlToDatavault;
import com.greenmark.utils.trace.Trace;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Hashtable;
import java.util.Properties;

@Slf4j
public class Config {
    public static final String CLASSNAME = "Config";
    public static final boolean HARVEST_NO = true;
    public static final int CONFIGURE_CLEAN = 1;
    public static final int CONFIGURE_RESTART = 2;
    public static final int CONFIGURE_FROMDB = 3;
    public static boolean greenmanPaused = false;
    public static boolean gmanCalculating;
    public static boolean startHarvester;
    public static String fileConfigXmlDefault = "";
    public static boolean init = false;
    public static int loglevel = Trace.LOGLEVEL_ANALYSIS;
    public static Properties log4j_properties = null;
    /**
     * These variables holds the last string saved to disk. We use it for comparision to see if we need to write again.
     */
    public static String lastfilesaved_config = "";
    public static String lastfilesaved_model = "";
    private static DVDatavault dvPreviousScenarioConfig = null;
    private static DVDatavault dvmodel = null;
    private static DVDatavault dvconfigXmlDefault = null;
    private static Hashtable hashConfig = new Hashtable();
    private static Hashtable hashModel = new Hashtable();
    private static StrategyDto model = null;
    private static int initState = CONFIGURE_CLEAN;
    // ------------- MARKET TREND FEATURE -------------------
    // does the config file hold or have the different market trend values?
    private static boolean useMktd = false;
    // are we using the current market trend values or just going with Flat?
    private static boolean enabledMktd = false;
    // current market trend type - start with 'none' set
    private static String currentTypeMktd = GmConstantsStrategy.MKT_TREND_TYPE_NONE_STRING;
    // current stochK value of the selected indice indicator - start with nothing
    private static float currentValueMktd = 0F;
    // holds the current indice
    private static String analysisIndiceMktd = "";

    public static final void resetCache() {
        hashModel = new Hashtable(200);
        hashConfig = new Hashtable(200);
    }

    // ------------------------------------ INITIALIZATION METHODS ---------------------------------------
    public static final void initializeClean() {
        Config.initialize(Config.CONFIGURE_CLEAN, null);
        // ApplicationDataService.initialize(null);
    }

    public static final void initializeCleanNoServices() {
        Config.initialize(Config.CONFIGURE_CLEAN, null);
        // ApplicationDataService.initialize(null);
    }

    public static final boolean initialize(int init_state, UTCalendarTime inputDate) {
        return initialize(init_state, null, inputDate);
    }

    public static final boolean initialize(int init_state, StrategyDto model, UTCalendarTime inputDate) {
        String methodname = "initialize";

        // reset just in case
        Config.resetCache();

        // early return if already initialized
        if (Config.init)
            return true;

        // else initialize
        try {
            Config.initState = init_state;
            String base_config_directory = UTPropertyManager.getProperty("base_config_directory");

            Config._initModel(init_state, base_config_directory, model, inputDate);
            Config._initConfig(init_state, base_config_directory, inputDate);
            Config._initConfigXmlDefault(init_state);

            // before retrieving data, set the market trend
            Config.initializeMarketTrends();

            Config.init = true;
        } catch (NumberFormatException ne) {
            System.out.println(" . . .NumberFormatException in " + CLASSNAME + "." + methodname + " Exception Message:  [" + ne.getMessage() + "]");
            return false;
        } catch (Exception e) {
            System.out.println("Caught exception in CLASS: " + CLASSNAME + ", initialize: error loading configuration files, message: [" + e.getMessage() + "]");
        }
        return true;
    }

    private static final void _initModel(int init_state, String basedir, StrategyDto model, UTCalendarTime inputDate) throws NumberFormatException {
        String methodname = "_initModel";
        try {
            // if the strategy object is not null, use it and return
            if (init_state == CONFIGURE_FROMDB) {
                Config.model = model;
                Config.dvmodel = DVXmlToDatavault.extract(model.getXml());
            }

            // else go and get the file from somewhere
            String thisfile = UTPropertyManager.getProperty("dvmodel");
            String pathAndFile = basedir + "/" + thisfile;
            if (init_state == CONFIGURE_RESTART) {
                String savepath = basedir + "/" + UTPropertyManager.getProperty("model_save_dir");
                String savefile = "";

                // If the inputDate was passed in, load the xml file for the specified date.
                if (inputDate == null)
                    savefile = UTDirectoryHandler.getLatestFile(savepath, false);
                else
                    savefile = UTDirectoryHandler.getXmlFileByDatetime(savepath, inputDate, false);

                if (StringUtils.isBlank(savefile)) {
                    savepath = UTPropertyManager.getProperty("base_config_directory");
                    savefile = "strategy.xml";
                }

                if (UTUtils.isNotNorE(savefile))
                    pathAndFile = savepath + "/" + savefile;
                else
                    System.out.println(" . . . Model: no saved file, use default");
            }
            // System.out.println(" . . . pathAndFile [" + pathAndFile + "]");
            String config = UTFileHandler.loadTextFile(pathAndFile).toString();

            Config.model = new StrategyDto(config);
            Config.dvmodel = DVXmlToDatavault.extract(config);
        } catch (NumberFormatException ne) {
            System.out.println(" . . .NumberFormatException in " + CLASSNAME + "." + methodname);
            throw ne;
        } catch (Exception e) {
            System.out.println(" . . .Exception in " + CLASSNAME + "." + methodname + " Exception Message:  [" + e.getMessage() + "]");
        }
    }

    private static final void _initConfig(int init_state, String basedir, UTCalendarTime inputDate) throws NumberFormatException {
        String methodname = "_initConfig";
        try {
            String thisfile = UTPropertyManager.getProperty("dvconfig");
            String pathAndFile = basedir + "/" + thisfile;
            if (init_state == CONFIGURE_RESTART) {
                String savepath = basedir + "/" + UTPropertyManager.getProperty("config_save_dir");
                String savefile = "";

                // If the inputDate was passed in, load the xml file for the specified date.
                if (inputDate == null)
                    savefile = UTDirectoryHandler.getLatestFile(savepath, false);
                else
                    savefile = UTDirectoryHandler.getXmlFileByDatetime(savepath, inputDate, false);

                if (UTUtils.isNotNorE(savefile)) {
                    pathAndFile = savepath + "/" + savefile;
                    String config = UTFileHandler.loadTextFile(pathAndFile).toString();
                    Config.dvPreviousScenarioConfig = DVXmlToDatavault.extract(config);
                } else {
                    String config = getConfigXmlDefault();
                    Config.dvPreviousScenarioConfig = DVXmlToDatavault.extract(config);
                    System.out.println(" . . . Config: no saved appconfigs/config_data/config.xml file from a previous scenario, use default");
                }
            }
        } catch (NumberFormatException ne) {
            System.out.println(" . . .NumberFormatException in " + CLASSNAME + "." + methodname + " Exception Message:  [" + ne.getMessage() + "]");
            throw ne;
        } catch (Exception e) {
            System.out.println(" . . .Exception in " + CLASSNAME + "." + methodname + " Exception Message:  [" + e.getMessage() + "]");
        }
    }

    public static final void _initConfigXmlDefault(int init_state) {
        String methodname = "_initConfigDefault";

        try {
            String config = getConfigXmlDefault();
            Config.fileConfigXmlDefault = config;

            Config.dvconfigXmlDefault = DVXmlToDatavault.extract(config);
        } catch (Exception e) {
            System.out.println(" . . .Exception in " + CLASSNAME + "." + methodname + " Exception Message:  [" + e.getMessage() + "]");
        }

    }

    public static final String getConfigXmlDefault() {
        String methodname = "getConfigXmlDefault";
        String config = "";

        try {
            String basedir = UTPropertyManager.getProperty("base_directory");

            // go and get the file from somewhere
            String thisfile = UTPropertyManager.getProperty("dvconfig");
            String pathAndFile = basedir + "/" + thisfile;

            config = UTFileHandler.loadTextFile(pathAndFile).toString();
        } catch (Exception e) {
            System.out.println(" . . .Exception in " + CLASSNAME + "." + methodname + " Exception Message:  [" + e.getMessage() + "]");
        }

        return config;
    }

    public static final void initializeMarketTrends() throws Exception {
        // make sure it is valid first
        boolean isValid = Config.isValidModelXml(Config.getDvmodel());

        // get them because they might change later
        int marketTrendUseOn = Config.getValueAsInt(ConfigMarket.CONFIG_MARKET_TREND_USE);
        int marketTrendEnableOn = Config.getValueAsInt(ConfigMarket.CONFIG_MARKET_TREND_ENABLED);

        // get/set the status of the marketTrend Usage
        if (marketTrendUseOn == GmConstants.IS_TRUE) {
            Config.setUseMktd(true);

            Config.setEnabledMktd(marketTrendEnableOn == GmConstants.IS_TRUE);
        } else {
            Config.setUseMktd(false);
        }

        // mktd indice selected
        String analysisIndiceName = Config.getValue(ConfigMarket.CONFIG_MARKET_TREND_ANALYSIS_INDICE);
        Config.setAnalysisIndiceMktd(analysisIndiceName);

        // current type
        String currentType = Config.getValue(ConfigMarket.CONFIG_MARKET_TREND_CURRENT_TYPE);
        Config.setCurrentTypeMktd(currentType);

        // current value
        float currentValue = Config.getValueAsFloat(ConfigMarket.CONFIG_MARKET_TREND_CURRENT_VALUE);
        Config.setCurrentValueMktd(currentValue);
    }

    // ------------------------------------ GETTER/SETTER that everyone uses METHODS ---------------------------------------

    /**
     * Returns the value from the configuration or strategy datavault depending on the prefix
     */
    public static final boolean hasDvmodelValue(String dvtag) {
        return Config.dvmodel.contains(dvtag);
    }

    /**
     * Returns the value from the configuration or strategy datavault depending on the prefix
     */
    public static final String getValue(String dvtag) {
        return getValue(dvtag, true);

    }

    /**
     * Returns the value from the configuration or strategy datavault depending on the prefix.
     */
    public static final String getValue(String dvtag, boolean changeTag) {
        // make sure that the DV tag starts with either strategy or config
        String value = "";

        // case non-Market Trend tag
        if (dvtag.startsWith("DV:XML.MODEL.")) {
            // if it is a market trend type tag - check if we have to modify the tag
            if (dvtag.startsWith(ConfigMarket.START_MARKET_TREND_DVTAG) && changeTag)
                dvtag = changeDvtagForMktd(dvtag);

            // check if we have it already - if so return it.
            if (hashModel.containsKey(dvtag)) {
                value = (String) hashModel.get(dvtag);
                // System.out.println(" - - - get cached ("+hashModel.size()+") dvtag [" + dvtag + "] value ["+value+"]");
                return value;
            }

            value = Config.dvmodel.getString(dvtag);

            // and save it for use later
            hashModel.put(dvtag, value);
        } else if (dvtag.startsWith("DV:XML.CONFIG_NO_DB_RESTORE.")) {
            // check if we have it already - if so return it.
            if (hashConfig.containsKey(dvtag)) {

                value = (String) hashConfig.get(dvtag);
                return value;
            }

            // First see if we have a value in XmlDefault (appconfigs/config.xml)
            value = Config.dvconfigXmlDefault.getString(dvtag);

            // if not go get it
            if (UTUtils.isNorE(value))
                value = Config.dvPreviousScenarioConfig.getString(dvtag);

            // and save it for use later
            hashConfig.put(dvtag, value);
        }
        return value;
    }

    /**
     * Returns the value from the strategy configuration datavault as an int
     */
    public static final int getValueAsInt(String dvtag) {
        try {
            String value = Config.getValue(dvtag);
            return Integer.parseInt(value);
        } catch (Exception e) {
            // System.out.println("Caught exception in CLASS: " + CLASSNAME + ".getValueAsInt(), message: [" + e.getMessage() + "] for config token: " + dvtag);
            return -1;
        }
    }

    /**
     * Returns the value from the strategy configuration datavault as a float
     */
    public static final float getValueAsFloat(String dvtag) {
        try {
            String value = Config.getValue(dvtag);
            return Float.parseFloat(value);
        } catch (Exception e) {
            // System.out.println("Caught exception in CLASS: " + CLASSNAME + ".getValueAsFloat(), message: [" + e.getMessage() + "] for config token: " + dvtag);
            return 0F;
        }
    }

    public static final boolean getValueAsBoolean(String dvtag) {
        try {
            String stringvalue = Config.getValue(dvtag);
            if (UTUtils.isNorE(stringvalue))
                return false;

            if (stringvalue.equalsIgnoreCase("YES"))
                return true;

            if (stringvalue.equalsIgnoreCase("NO"))
                return false;

            if (stringvalue.equalsIgnoreCase("1"))
                return true;

            if (stringvalue.equalsIgnoreCase("0"))
                return false;

            if (stringvalue.equalsIgnoreCase("TRUE"))
                return true;

            if (stringvalue.equalsIgnoreCase("FALSE"))
                return false;

            return false;// Default is false
        } catch (Exception e) {
            // System.out.println("Caught exception in CLASS: " + CLASSNAME + ".getValueAsBoolean(), message: [" + e.getMessage() + "] for config token: " + dvtag);
            return false;
        }
    }

    /**
     * Returns the value from the strategy configuration datavault as a double
     */
    public static final double getValueAsDouble(String dvtag) {
        try {
            String value = Config.getValue(dvtag);
            return Double.parseDouble(value);
        } catch (Exception e) {
            // System.out.println("Caught exception in CLASS: " + CLASSNAME + ".getValueAsDouble, message: [" + e.getMessage() + "] for config token: " + dvtag);
            return 0D;
        }
    }

    public static final void setValue(String dvtag, String value) {
        setValue(dvtag, value, GmConstantsStrategy.MKT_TREND_TYPE_FLAT_INT);
    }

    /**
     * Set the value of either the configuration or strategy depending on the dvtag. Important to remember - it doesn't matter what you send in for mktd if the dvtag does not start with
     * 'DV:XML.MODEL.MKTD'. If it doesnt start with that, mktd will be ignored.
     */
    public static final void setValue(String dvtag, String value, int mktd) {
        try {
            resetCache();

            // make sure that the DV tag starts with either strategy or config
            if (dvtag.startsWith("DV:XML.MODEL.")) {
                // if it is a market trend type tag - check if we have to modify the tag
                if (dvtag.startsWith(ConfigMarket.START_MARKET_TREND_DVTAG))
                    dvtag = changeDvtagForMktd(dvtag, mktd);

                Config.dvmodel.set(dvtag, value);
                // hashModel = new Hashtable(200);
            } else if (dvtag.startsWith("DV:XML.CONFIG_NO_DB_RESTORE.")) {
                Config.dvPreviousScenarioConfig.set(dvtag, value);
            }

        } catch (Exception e) {
            System.out.println("Caught exception in CLASS: " + CLASSNAME + ".setValue, message: [" + e.getMessage() + "]");
            // return -1;
        }
    }

    // ------------------------------------ MARKET TREND METHODS ---------------------------------------

    /**
     * Use this one to change the tag based on the current market trend
     */
    public static final String changeDvtagForMktd(String dvtag) {
        String retDvtag = dvtag;

        // check to see if market trend usage on
        if (Config.getUseMktd()) {
            if (Config.getEnabledMktd()) {
                String current = Config.getCurrentTypeMktd();
                String endOfTag = dvtag.substring(ConfigMarket.START_MARKET_TREND_DVTAG.length() + 1);
                retDvtag = ConfigMarket.START_MARKET_TREND_DVTAG + "." + current + "." + endOfTag;
            } else {
                String endOfTag = dvtag.substring(ConfigMarket.START_MARKET_TREND_DVTAG.length() + 1);
                retDvtag = ConfigMarket.START_MARKET_TREND_DVTAG + "." + "FLAT" + "." + endOfTag;
            }
        }
        // System.out.println(" . . . before ["+dvtag+"] after ["+retDvtag+"]");

        return retDvtag;
    }

    /**
     * You this method if you want the tag modified and you know the merket trend type you want.
     */
    public static final String changeDvtagForMktd(String dvtag, int mktdType) {
        String retDvtag = dvtag;

        // early exit if it doesnt have START_MARKET_TREND_DVTAG
        if (dvtag.indexOf(ConfigMarket.START_MARKET_TREND_DVTAG) == -1)
            return dvtag;
        // set up
        String endOfTag = dvtag.substring(ConfigMarket.START_MARKET_TREND_DVTAG.length() + 1);

        // switch based on what they sent in
        switch (mktdType) {
            case GmConstantsStrategy.MKT_TREND_TYPE_BEAR_STRATEGY_INT:
                retDvtag = ConfigMarket.START_MARKET_TREND_DVTAG + "." + GmConstantsStrategy.MKT_TREND_TYPE_BEAR_STRATEGY_STRING + "." + endOfTag;
                break;
            case GmConstantsStrategy.MKT_TREND_TYPE_BEAR_TRENDING_INT:
                retDvtag = ConfigMarket.START_MARKET_TREND_DVTAG + "." + GmConstantsStrategy.MKT_TREND_TYPE_BEAR_TRENDING_STRING + "." + endOfTag;
                break;
            case GmConstantsStrategy.MKT_TREND_TYPE_BEAR_INT:
                retDvtag = ConfigMarket.START_MARKET_TREND_DVTAG + "." + GmConstantsStrategy.MKT_TREND_TYPE_BEAR_STRING + "." + endOfTag;
                break;
            case GmConstantsStrategy.MKT_TREND_TYPE_FLAT_INT:
                retDvtag = ConfigMarket.START_MARKET_TREND_DVTAG + "." + GmConstantsStrategy.MKT_TREND_TYPE_FLAT_STRING + "." + endOfTag;
                break;
            case GmConstantsStrategy.MKT_TREND_TYPE_BULL_INT:
                retDvtag = ConfigMarket.START_MARKET_TREND_DVTAG + "." + GmConstantsStrategy.MKT_TREND_TYPE_BULL_STRING + "." + endOfTag;
                break;
            case GmConstantsStrategy.MKT_TREND_TYPE_BULL_TRENDING_INT:
                retDvtag = ConfigMarket.START_MARKET_TREND_DVTAG + "." + GmConstantsStrategy.MKT_TREND_TYPE_BULL_TRENDING_STRING + "." + endOfTag;
                break;
            case GmConstantsStrategy.MKT_TREND_TYPE_BULL_STRATEGY_INT:
                retDvtag = ConfigMarket.START_MARKET_TREND_DVTAG + "." + GmConstantsStrategy.MKT_TREND_TYPE_BULL_STRATEGY_STRING + "." + endOfTag;
                break;

        }
        // System.out.println(" . . . before ["+dvtag+"] after ["+retDvtag+"]");

        return retDvtag;
    }

    public static final void initMarketTrendsWebsite() {
        // if the current config xml document is configured with MKTD, set it on.
        String useMktd = Config.dvmodel.getString(ConfigMarket.CONFIG_MARKET_TREND_USE);
        String enabledMktd = Config.dvmodel.getString(ConfigMarket.CONFIG_MARKET_TREND_ENABLED);
        System.out.println(" . . . Market Trends: analyzed");
        if (useMktd.equals(GmConstants.STR_TRUE)) {
            System.out.println(" . . . . . . Market Trends is in Use");
            Config.setUseMktd(true);

            if (enabledMktd.equals(GmConstants.STR_TRUE)) {
                System.out.println(" . . . . . . Market Trends is Enabled");
                System.out.println(" . . . . . . Market Trends current is FLAT");
                Config.setEnabledMktd(true);
                Config.setCurrentTypeMktd(GmConstantsStrategy.MKT_TREND_TYPE_FLAT_STRING);
            }

        }
    }

    public static final int getCurrentMarketTrendAsInt() {
        if (currentTypeMktd.equalsIgnoreCase(GmConstantsStrategy.MKT_TREND_TYPE_FLAT_STRING))
            return GmConstantsStrategy.MKT_TREND_TYPE_FLAT_INT;

        if (currentTypeMktd.equalsIgnoreCase(GmConstantsStrategy.MKT_TREND_TYPE_BEAR_STRING))
            return GmConstantsStrategy.MKT_TREND_TYPE_BEAR_INT;

        if (currentTypeMktd.equalsIgnoreCase(GmConstantsStrategy.MKT_TREND_TYPE_BEAR_STRATEGY_STRING))
            return GmConstantsStrategy.MKT_TREND_TYPE_BEAR_STRATEGY_INT;

        if (currentTypeMktd.equalsIgnoreCase(GmConstantsStrategy.MKT_TREND_TYPE_BEAR_TRENDING_STRING))
            return GmConstantsStrategy.MKT_TREND_TYPE_BEAR_TRENDING_INT;

        if (currentTypeMktd.equalsIgnoreCase(GmConstantsStrategy.MKT_TREND_TYPE_BULL_STRING))
            return GmConstantsStrategy.MKT_TREND_TYPE_BULL_INT;

        if (currentTypeMktd.equalsIgnoreCase(GmConstantsStrategy.MKT_TREND_TYPE_BULL_STRATEGY_STRING))
            return GmConstantsStrategy.MKT_TREND_TYPE_BULL_STRATEGY_INT;

        if (currentTypeMktd.equalsIgnoreCase(GmConstantsStrategy.MKT_TREND_TYPE_BULL_TRENDING_STRING))
            return GmConstantsStrategy.MKT_TREND_TYPE_BULL_TRENDING_INT;

        return GmConstantsStrategy.MKT_TREND_TYPE_NONE_INT;
    }

    public static final String convertCurrentMarketTrend(int value) {
        if (value == GmConstantsStrategy.MKT_TREND_TYPE_FLAT_INT)
            return GmConstantsStrategy.MKT_TREND_TYPE_FLAT_STRING;

        if (value == GmConstantsStrategy.MKT_TREND_TYPE_BEAR_INT)
            return GmConstantsStrategy.MKT_TREND_TYPE_BEAR_STRING;

        if (value == GmConstantsStrategy.MKT_TREND_TYPE_BEAR_STRATEGY_INT)
            return GmConstantsStrategy.MKT_TREND_TYPE_BEAR_STRATEGY_STRING;

        if (value == GmConstantsStrategy.MKT_TREND_TYPE_BEAR_TRENDING_INT)
            return GmConstantsStrategy.MKT_TREND_TYPE_BEAR_TRENDING_STRING;

        if (value == GmConstantsStrategy.MKT_TREND_TYPE_BULL_INT)
            return GmConstantsStrategy.MKT_TREND_TYPE_BULL_STRING;

        if (value == GmConstantsStrategy.MKT_TREND_TYPE_BULL_STRATEGY_INT)
            return GmConstantsStrategy.MKT_TREND_TYPE_BULL_STRATEGY_STRING;

        if (value == GmConstantsStrategy.MKT_TREND_TYPE_BULL_TRENDING_INT)
            return GmConstantsStrategy.MKT_TREND_TYPE_BULL_TRENDING_STRING;

        return GmConstantsStrategy.MKT_TREND_TYPE_NONE_STRING;
    }

    public static int getAnalysisIndiceSelected() {
        int value = -1;

        if (analysisIndiceMktd.equalsIgnoreCase(GmConstantsStrategy.MKT_TREND_INDICE_NAME0))
            return 0;
        if (analysisIndiceMktd.equalsIgnoreCase(GmConstantsStrategy.MKT_TREND_INDICE_NAME1))
            return 1;
        if (analysisIndiceMktd.equalsIgnoreCase(GmConstantsStrategy.MKT_TREND_INDICE_NAME2))
            return 2;

        return value;
    }

    public static String getAnalysisIndiceSymbol() {
        String value = analysisIndiceMktd;
        return getAnalysisIndiceSymbol(value);
    }

    public static String getAnalysisIndiceSymbol(String indiceName) {
        String value = "";

        if (indiceName.equalsIgnoreCase(GmConstantsStrategy.MKT_TREND_INDICE_NAME0))
            return GmConstantsStrategy.MKT_TREND_INDICE_SYMBOL0;
        if (indiceName.equalsIgnoreCase(GmConstantsStrategy.MKT_TREND_INDICE_NAME1))
            return GmConstantsStrategy.MKT_TREND_INDICE_SYMBOL1;
        if (indiceName.equalsIgnoreCase(GmConstantsStrategy.MKT_TREND_INDICE_NAME2))
            return GmConstantsStrategy.MKT_TREND_INDICE_SYMBOL2;

        return value;
    }

    public static boolean isValidModelXml(String xml) throws Exception {
        DVDatavault dv = new DVDatavault();
        dv = DVXmlToDatavault.extract(xml);
        return isValidModelXml(dv);
    }

    public static boolean isValidModelXml(DVDatavault dv) throws Exception {
        boolean isValid = true;
        boolean containsFlat = dv.contains(ConfigMarket.CONFIG_MARKET_TREND_HASFLAT);
        String use = dv.getString(ConfigMarket.CONFIG_MARKET_TREND_USE);
        String enabled = dv.getString(ConfigMarket.CONFIG_MARKET_TREND_ENABLED);

        String current_type = dv.getString(ConfigMarket.CONFIG_MARKET_TREND_CURRENT_TYPE);
        String current_value = dv.getString(ConfigMarket.CONFIG_MARKET_TREND_CURRENT_VALUE);

        String bear_start_stochk = dv.getString(ConfigMarket.CONFIG_MARKET_TREND_BEAR_START_STOCHK);
        String bear_stop_stochk = dv.getString(ConfigMarket.CONFIG_MARKET_TREND_BEAR_STOP_STOCHK);
        String bull_start_stochk = dv.getString(ConfigMarket.CONFIG_MARKET_TREND_BULL_START_STOCHK);
        String bull_stop_stochk = dv.getString(ConfigMarket.CONFIG_MARKET_TREND_BULL_STOP_STOCHK);
        String indice_selected = dv.getString(ConfigMarket.CONFIG_MARKET_TREND_ANALYSIS_INDICE);

        // printMarketTrendStats(dv);

        // make sure we have all the values we need
        if (use.length() == 0)
            isValid = false;
        if (enabled.length() == 0)
            isValid = false;

        if (current_type.length() == 0)
            isValid = false;
        if (current_value.length() == 0)
            isValid = false;

        if (bear_start_stochk.length() == 0)
            isValid = false;
        if (bear_stop_stochk.length() == 0)
            isValid = false;
        if (bull_start_stochk.length() == 0)
            isValid = false;
        if (bull_stop_stochk.length() == 0)
            isValid = false;
        if (indice_selected.length() == 0)
            isValid = false;

        // early return if we dont have the needed values
        if (!isValid) {
            System.out.println("isValid [" + isValid + "]");
            return false;
        }

        // see if
        boolean isUseOn = use.equals(GmConstants.STR_TRUE);

        boolean isEnableOn = enabled.equals(GmConstants.STR_TRUE);

        /////////////////////////////////////
        // do the comparision
        /////////////////////////////////////
        // if Use=on, enable=off and hasFlat=true
        if (isUseOn && (!isEnableOn) && containsFlat) {
            // System.out.println(" . . . isUseOn && !isEnableOn && hasFlat");
            // System.out.println("isValid [true]");
            return true;
        }

        // if Use=on, enable=off and hasFlat=true
        if (isUseOn && isEnableOn && containsFlat) {
            // System.out.println(" . . . isUseOn && isEnableOn && hasFlat");
            // System.out.println("isValid [true]");
            return true;
        }

        // if Use=off, enable=off and hasFlat=false
        if (!isUseOn && !isEnableOn && !containsFlat) {
            // System.out.println(" . . . !isUseOn && !isEnableOn && !hasFlat");
            // System.out.println("isValid [true]");
            return true;
        }
        System.out.println("   isValidModelXml [" + isValid + "]");

        throw new Exception("Config.isValidModelXml() StrategyDto XML is invalid");
    }

    // ------------------------------------ EXPORT/DUMP WRAPPERS ---------------------------------------
    public static final void saveConfigParameters(Trace trace) {
        String methodname = "saveConfigParameters";
        trace.in(CLASSNAME, methodname);
        try {
            Config.dumpConfigToFile(trace);
        } catch (Exception e) {
            if (trace.isLevelFatal())
                trace.addFatal("Exception in " + CLASSNAME + "." + methodname + " Exception Message:  [" + e.getMessage() + "]");
        }

    }

    public static final void saveStrategyXml(Trace trace) {
        String methodname = "saveModelParameters";
        trace.in(CLASSNAME, methodname);
        try {
            Config.dumpModelToFile(trace);
        } catch (Exception e) {
            if (trace.isLevelFatal())
                trace.addFatal("Exception in " + CLASSNAME + "." + methodname + " Exception Message:  [" + e.getMessage() + "]");
        }

    }

    /**
     * This method dumps the output of the object to an xml file.
     *
     * @param trace Trace
     */
    public static final void dumpModelToFile(Trace trace) {
        String methodname = "dumpModelToFile";

        try {
            // System.out.println(" . . IN dumpModelToFile");
            // start by getting the xml output
            String basicxmldata = Config.dvmodel.toXml(UTDisplayFormatter.TAB, "\n").toString();
            String xmldata = UTXmlUtils.getXmlData(basicxmldata, "XML");

            // compare it to the last one that was saved
            // if it is different, write it out
            if (!Config.lastfilesaved_model.equals(xmldata)) {
                // if it is different, write it out
                String filepath = UTPropertyManager.getProperty("base_config_directory") + "/" + UTPropertyManager.getProperty("model_save_dir") + "/";
                String fileprefix = UTPropertyManager.getProperty("model_filename_prefix");
                String filename = filepath + fileprefix + "_" + UTDatetime.getCurrentDatetimeForFilename() + ".xml";

                UTFileHandler.writeFileToDisk(filename, xmldata);
                if (trace.isLevelSparse())
                    log.debug("********************************************** writing file [" + filename + "]");

                // finally save this one as the new reference
                Config.lastfilesaved_model = xmldata;
            }
        } catch (Exception ex) {
            log.error("Caught General Exception " + CLASSNAME + "." + methodname + ":[" + ex.getMessage() + "]");
        }
        trace.out(methodname, CLASSNAME);
    }

    /**
     * This method dumps the output of the object to an xml file.
     *
     * @param trace Trace
     */
    public static final void dumpConfigToFile(Trace trace) {
        String methodname = "dumpConfigToFile";
        trace.in(methodname, CLASSNAME);

        try {
            // start by getting the xml output
            // System.out.println(" . . IN dumpConfigToFile");
            String basicxmldata = Config.dvPreviousScenarioConfig.toXml(UTDisplayFormatter.TAB, "\n").toString();
            String xmldata = UTXmlUtils.getXmlData(basicxmldata, "XML");

            // compare it to the last one that was saved
            // if it is different, write it out
            if (!Config.lastfilesaved_config.equals(xmldata)) {
                // if it is different, write it out
                String filepath = UTPropertyManager.getProperty("base_config_directory") + "/" + UTPropertyManager.getProperty("config_save_dir") + "/";
                String fileprefix = UTPropertyManager.getProperty("config_filename_prefix");
                String filename = filepath + fileprefix + "_" + UTDatetime.getCurrentDatetimeForFilename() + ".xml";

                UTFileHandler.writeFileToDisk(filename, xmldata);
                if (trace.isLevelSparse())
                    log.debug("********************************************** writing file [" + filename + "]");

                // finally save this one as the new reference
                Config.lastfilesaved_config = xmldata;
            }
        } catch (Exception ex) {
            log.error("Caught General Exception " + CLASSNAME + "." + methodname + ":[" + ex.getMessage() + "]");
        }
        trace.out(methodname, CLASSNAME);
    }

    public static String dumpModel() {
        StringBuffer str = dvmodel.dump();
        return str.toString();
    }

    public static String dumpConfig() {
        StringBuffer str = dvPreviousScenarioConfig.dump();
        return str.toString();
    }

    // ------------------------------------ SETTERS/GETTERS ---------------------------------------

    /**
     * Returns the file holding all the currency information
     */
    public static final String getFileConfigXmlDefault() {
        return Config.fileConfigXmlDefault;
    }

    public static DVDatavault getDvPreviousScenarioConfig() {
        return dvPreviousScenarioConfig;
    }

    public static void setDvPreviousScenarioConfig(DVDatavault dvconfig) {
        Config.dvPreviousScenarioConfig = dvconfig;
        resetCache();
    }

    public static DVDatavault getDvmodel() {
        return dvmodel;
    }

    public static void setDvmodel(DVDatavault dvmodel) {
        Config.dvmodel = dvmodel;
        resetCache();
    }

    public static String getModelToXml() {
        return Config.dvmodel.toXml().toString();
    }

    public static StrategyDto getModel() {
        return Config.model;
    }

    public static void setModel(StrategyDto model) {
        Config.model = model;
    }

    public static int getInitState() {
        return initState;
    }

    ////////////////// SECURITY TREND INDICATOR //////////////////////////
    public static boolean getUseMktd() {
        return Config.useMktd;
    }

    public static void setUseMktd(boolean value) {
        Config.useMktd = value;
    }

    public static boolean getEnabledMktd() {
        return Config.enabledMktd;
    }

    public static void setEnabledMktd(boolean value) {
        Config.enabledMktd = value;
    }

    public static final int getEnabledAsInt() {
        if (Config.enabledMktd)
            return GmConstants.IS_TRUE;
        return GmConstants.IS_FALSE;
    }

    public static String getCurrentTypeMktd() {
        return Config.currentTypeMktd;
    }

    public static void setCurrentTypeMktd(String value) {
        Config.currentTypeMktd = value;
    }

    public static float getCurrentValueMktd() {
        return currentValueMktd;
    }

    public static void setCurrentValueMktd(float value) {
        Config.currentValueMktd = value;
    }

    public static String getAnalysisIndiceMktd() {
        return Config.analysisIndiceMktd;
    }

    public static void setAnalysisIndiceMktd(String value) {
        Config.analysisIndiceMktd = value;
    }

    public static final void printConfigToStdoutTest() {
        System.out.println("================== CONTENTS OF DVMODEL ======================");
        StringBuffer contents = Config.dvmodel.dump();
        System.out.println(contents);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("================== CONTENTS OF HASHMODEL ======================");
        String contentsHash = hashConfig.toString();
        System.out.println(contentsHash);
    }
}
