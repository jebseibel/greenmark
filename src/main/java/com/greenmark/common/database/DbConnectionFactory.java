package com.greenmark.common.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;

import com.greenmark.common.service.ApplicationDataContext;
import com.greenmark.utils.UTPropertyManager;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: DbConnectionFactory</p>
 * <p>Description: This class is used to get a database connection to either the results or prices database.  It allows connection pooling or direct JDBC connections (for debugging).</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
@Slf4j
public class DbConnectionFactory {
	public static final String CLASSNAME = "DbConnectionFactory";

	private static final Vector listeners = new Vector();
	private static boolean initializedReporting = false;

	// The key to this map is the JDBC URL
	private static final Map<String, BasicDataSource> dataSourceMap = new HashMap<>();

	// This is the new method used by the website, so we can have 2 browsers open to different databases.
	// We pass in the selected website's DB information.
	public static final Connection getWebsiteConnection(AvailableResultsDbConfig dbProps) throws Exception {
		String use_db_connection_pool = UTPropertyManager.getProperty("use_db_connection_pool");
		if ("true".equals(use_db_connection_pool))
			return getDbConnectionFromProps(dbProps);
		else
			return getConnectionNoPool(dbProps);
	}

	public static final Connection getConnection(DbConfigBase dbProps) throws Exception {
		String use_db_connection_pool = UTPropertyManager.getProperty("use_db_connection_pool");
		if ("true".equals(use_db_connection_pool))
			return getDbConnectionFromPropsUseCache(dbProps);
		else
			return getConnectionNoPool(dbProps);
	}

	// This is used to connect directly to the Live results database setup for each java -Denv. In greenman.properties:
	public static final Connection getConnection() throws Exception {
		AvailableResultsDbConfig resultsDbConfig = ApplicationDataContext.getResultsDbProperties();

		String use_db_connection_pool = UTPropertyManager.getProperty("use_db_connection_pool");
		if ("true".equals(use_db_connection_pool))
			return getDbConnectionFromPropsUseCache(resultsDbConfig);
		else
			return getConnectionNoPool(resultsDbConfig);
	}

	// This is used to connect directly to the pricehistory database setup for each java -Denv. In greenman.properties:
	public static final Connection getUserSelectedPdbConnection() throws Exception {
		Connection returnConn = null;

		AvailablePricesDbConfig pricesDbConfig = ApplicationDataContext.getPricesDbProperties();

		returnConn = getPdbConnection(pricesDbConfig);
		return returnConn;
	}

	public static final Connection getPdbConnection(AvailablePricesDbConfig pricesDbConfig) throws Exception {
		Connection returnConn = null;
		String use_db_connection_pool = UTPropertyManager.getProperty("use_db_connection_pool");

		try {
			if ("true".equals(use_db_connection_pool))
				returnConn = getDbConnectionFromPropsUseCache(pricesDbConfig);
			else
				returnConn = getConnectionNoPool(pricesDbConfig);
		} catch (Exception e) {
			log.error("Caught General Exception " + CLASSNAME + ".getUserSelectedPdbConnection:[" + e.getMessage() + "]");
			System.out.println("DbConnectionFactory.getUserSelectedPdbConnection ERROR returning connection, msg: " + e.getMessage());
		}

		return returnConn;
	}

	private static final Connection getDbConnection(DbConfigBase dbProps) throws Exception {
		Connection conn = null;

		try {
			if (dataSourceMap.containsKey(dbProps.getDatabaseConnectionUrl())) {
				BasicDataSource theDB = dataSourceMap.get(dbProps.getDatabaseConnectionUrl());
				return theDB.getConnection();
			} else {
				BasicDataSource ds = new BasicDataSource();

				ds.setDriverClassName("org.postgresql.Driver");
				ds.setUrl(dbProps.getDatabaseConnectionUrl());
				ds.setUsername(dbProps.getDatabaseConnectionUsername());
				ds.setPassword(dbProps.getDatabaseConnectionPassword());

				ds.setMinIdle(5);
				ds.setMaxIdle(10);
				ds.setMaxOpenPreparedStatements(100);

				dataSourceMap.put(dbProps.getDatabaseConnectionUrl(), ds);

				return ds.getConnection();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	private static final Connection getDbConnectionFromPropsUseCache(DbConfigBase dbProps) throws Exception {
		Connection conn = null;

		try {
			if (dataSourceMap.containsKey(dbProps.getDatabaseConnectionUrl())) {
				BasicDataSource theDB = dataSourceMap.get(dbProps.getDatabaseConnectionUrl());
				return theDB.getConnection();
			} else {
				BasicDataSource ds = new BasicDataSource();

				ds.setDriverClassName("org.postgresql.Driver");
				ds.setUrl(dbProps.getDatabaseConnectionUrl());
				ds.setUsername(dbProps.getDatabaseConnectionUsername());
				ds.setPassword(dbProps.getDatabaseConnectionPassword());

				ds.setMinIdle(5);
				ds.setMaxIdle(10);
				ds.setMaxOpenPreparedStatements(100);

				dataSourceMap.put(dbProps.getDatabaseConnectionUrl(), ds);

				return ds.getConnection();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}


			log.warn(" . . . successfully retrieved a DB connection");
		return null;
	}

	private static final Connection getDbConnectionFromProps(DbConfigBase dbProps) throws Exception {
		Connection conn = null;

		try {
			BasicDataSource ds = new BasicDataSource();

			ds.setDriverClassName("org.postgresql.Driver");
			ds.setUrl(dbProps.getDatabaseConnectionUrl());
			ds.setUsername(dbProps.getDatabaseConnectionUsername());
			ds.setPassword(dbProps.getDatabaseConnectionPassword());

			ds.setMinIdle(5);
			ds.setMaxIdle(10);
			ds.setMaxOpenPreparedStatements(100);

			return ds.getConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

			log.warn(" . . . successfully retrieved a DB connection");
		return null;
	}

	private static final Connection getConnectionNoPool(DbConfigBase dbProps) throws Exception {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(dbProps.getDatabaseConnectionUrl(), dbProps.getDatabaseConnectionUsername(), dbProps.getDatabaseConnectionPassword());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

	public static void addListener(DbConnectionListener listener) {
		listeners.add(listener);
	}

	/**
	 * This method notifies all the listeners of an exception
	 */
	public static void writeToListeners(String message) {
		for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
			DbConnectionListener listener = (DbConnectionListener) e.nextElement();
			listener.errorThrown(message);
		}
	}

	public static void closeConnection(Connection conn) throws SQLException {
		if (conn != null && !conn.isClosed())
			conn.close();
	}

	public static boolean isInitializedReporting() {
		return initializedReporting;
	}

	public static void setInitializedReporting(boolean initializedReporting) {
		DbConnectionFactory.initializedReporting = initializedReporting;
	}

}
