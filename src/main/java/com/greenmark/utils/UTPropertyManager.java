package com.greenmark.utils;

import java.net.URL;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTPropertyManager</p>
 * <p>Description: This class is used to load values from the greenman.properties file to be used by any application.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
public class UTPropertyManager {
	public static final String CLASSNAME = "UTPropertyManager";

	/** The name of the property file that will be read for the application. **/
	private static final String APPLICATION_PROPERTY_FILENAME = "greenman.properties";

	/** The name of the override property file that will be read for the application. **/
	private static final String OVERRIDE_PROPERTY_FILENAME = "greenman.overrides.properties";

	/**
	 * Holds whether the class has initialized itself yet
	 */
	public static boolean init = false;

	/**
	 * The environment we are working in. The value of this variable defines which values to pull from the property file since all the values in the property file begin with a prefix. Example:
	 * dev.base_config_directory Defines what is the base_config_directory for the dev environment. If this value is not set correctly the app will not work correctly!
	 */
	public static String thisenv = "";

	/**
	 * Holds the properties
	 */
	/// Replaced by CompositeConfiguration below. public static Properties props = new Properties();

	private static CompositeConfiguration config;

	/////////////////////////////////////////////////
	// METHODS
	/////////////////////////////////////////////////

	public UTPropertyManager() {
		UTPropertyManager._initialize();
	}

	public static final void setProperty(String propname, String value) {
		// check to see if it is initialized
		if (!UTPropertyManager.init) {
			UTPropertyManager._initialize();
		}

		UTPropertyManager.config.setProperty(propname, value);
	}

	public static final String getProperty(String propname) {
		String propvalue = "";

		// check to see if it is initialized
		if (!UTPropertyManager.init) {
			UTPropertyManager._initialize();
		}

		///////////////////////////////////
		// get the value of the property if it is environment specific
		// System.out.println(" . . . getting propname ["+propname+"] for thisenv ["+thisenv+"]");

		// start by trying to get it for a specific environment
		String keyvalue = thisenv + "." + propname;

		if (UTPropertyManager.config.containsKey(keyvalue)) {
			propvalue = (String) UTPropertyManager.config.getProperty(keyvalue);
		}

		// if nothing was found, try to get the generic specific value

		if (UTUtils.isNorE(propvalue)) {
			if (UTPropertyManager.config.containsKey(propname)) {
				propvalue = (String) UTPropertyManager.config.getProperty(propname);
			}
		}

		// System.out.println(" . . . propvalue ["+propvalue+"]");
		return propvalue;
	}

	// returns false if value not set or is invalid. Expecting: true or false to be set.
	public static final boolean getBooleanProperty(String propname) {
		String propValue = getProperty(propname);

        return propname != null && propname.equalsIgnoreCase("TRUE");
	}

	/**
	 * Grabs the properties and displays them
	 */
	public static final String toString(String endline) {
		try {
			// check to see if it is initialized
			if (!UTPropertyManager.init) {
				UTPropertyManager._initialize();
			}

			// build a sortedMap first
			SortedMap sorted = new TreeMap();

			Iterator keyIter = config.getKeys();
			while (keyIter.hasNext()) {
				System.out.println("Getting next key");
				String name = (String) keyIter.next();
				System.out.println("Found property name: " + name);

				if ((name != null) && (!name.equals(""))) {
					System.out.println("Looking for value");
					String valu = (String) config.getProperty(name);
					System.out.println("Found property valu: " + valu);

					if ((valu != null) && (!valu.equals(""))) {
						System.out.println("Adding to sorted map   Property [" + name + "]=[" + valu + "]");
						sorted.put(name, valu);
					}
				}
			}

			// build output
			StringBuffer stb = new StringBuffer(1000);
			stb.append("==============================================================" + endline);
			stb.append(CLASSNAME + ".toString()" + endline);

			Iterator iSM = sorted.keySet().iterator();
			while (iSM.hasNext()) {
				String name = (String) iSM.next();
				String valu = (String) sorted.get(name);
				stb.append("PROPERTY : " + name + " = [" + valu + "]" + endline);
			}

			stb.append("==============================================================" + endline);
			return stb.toString();
		} catch (Exception ex) {
			System.out.println("Caught exception in CLASS: " + CLASSNAME + ", message: [" + ex.getMessage() + "]");
		}
		return "";
	}

	/**
	 * Grabs the properties and stores them
	 */
	public static final void _initialize() {
		try {
			if (!init) {
				UTPropertyManager.thisenv = System.getProperty("env");
				if ((UTPropertyManager.thisenv == null) || (UTPropertyManager.thisenv == ""))
					UTPropertyManager.thisenv = "testserver";

				// use this to see if we are running client or server version
				System.out.println("Version: " + System.getProperty("java.vm.name"));
				System.out.println(UTUtils.getMemoryStamp());
				System.out.println("UTPropertyManager: the environment is [" + UTPropertyManager.thisenv + "]");

				/** NOW LOAD APACHE COMMONS CONFIGURATION W/ PROPERTY OVERRIDES **/

				// load the project property file
				// assign them to the static class config object
				UTPropertyManager.config = new CompositeConfiguration();

				ClassLoader cl = null;
				try {
					cl = Thread.currentThread().getContextClassLoader();
				} catch (Exception e) {
					cl = UTPropertyManager.class.getClassLoader();
				}

				if (cl != null) {
					URL overrideUrl = cl.getResource(OVERRIDE_PROPERTY_FILENAME);
					if (overrideUrl != null) {
						PropertiesConfiguration overrideProperties = new PropertiesConfiguration(overrideUrl);
						overrideProperties.setReloadingStrategy(new FileChangedReloadingStrategy());

						UTPropertyManager.config.addConfiguration(overrideProperties);
					}

					URL applicationUrl = cl.getResource(APPLICATION_PROPERTY_FILENAME);
					if (applicationUrl != null) {
						PropertiesConfiguration appConfig = null;
						appConfig = new PropertiesConfiguration(applicationUrl);
						appConfig.setReloadingStrategy(new FileChangedReloadingStrategy());

						UTPropertyManager.config.addConfiguration(appConfig);
					} else {
						PropertiesConfiguration appConfig = new PropertiesConfiguration(APPLICATION_PROPERTY_FILENAME);
						appConfig.setReloadingStrategy(new FileChangedReloadingStrategy());

						UTPropertyManager.config.addConfiguration(appConfig);
					}

					init = true;
					System.out.println("UTPropertyManager: loaded the property files!");
				} else
					System.out.println(" Could not get the Java Class Loader " + CLASSNAME + "._initialize()");
			}
		} catch (Exception e) {
			System.out.println("Exception in .." + CLASSNAME + "._initialize():[" + e.getMessage() + "]");
		}
    }

	public static final String getThisenv() {
		return thisenv;
	}

}