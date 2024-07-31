package com.greenmark.common.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 * <p>Title: GreenmanServiceEnum</p>
 * <p>Description: This enum is used to define our different services and applications.  It is used to configure Trace for each
 *    service/application to log to different folders.</p>

 * @author  Monte Seibel
 * @version 7.5
 * @formatter:on
 **/

public enum GreenmanServiceEnum {
    // @formatter:off
	BROKER("Broker Service", "broker_logs", "brokerService", 1), 
	NOTIFICATION("Notification Service", "notification_logs", "notificationService", 2), 
	TRADING_CLIENT("Trading Client","trading_client_logs", "tradingService", 3), 
	PDB_UPDATER("Prices DB Updater", "pdbupdater_logs", "pdbService", 4), 
	CHARTS("Charts", "chart_logs", "chartService", 5), 
	WEBAPP("Web Application", "webapp_logs", "webapp", 6);
	// @formatter:on

    private final String name;
    private final String logSubfolderName;
    private final String springHttpBeanName;
    private final int id;

    GreenmanServiceEnum(String name, String logSubfolderName, String springHttpBeanName, int id) {
        this.name = name;
        this.logSubfolderName = logSubfolderName;
        this.springHttpBeanName = springHttpBeanName;
        this.id = id;
    }

    public static Optional<GreenmanServiceEnum> getByName(String value) {
        return Arrays.stream(GreenmanServiceEnum.values()).filter(accStatus -> accStatus.name.equals(value)).findFirst();
    }

    public static Optional<GreenmanServiceEnum> getBySpringHttpBeanName(String value) {
        return Arrays.stream(GreenmanServiceEnum.values()).filter(accStatus -> accStatus.springHttpBeanName.equals(value)).findFirst();
    }

    public static Optional<GreenmanServiceEnum> getById(int value) {
        return Arrays.stream(GreenmanServiceEnum.values()).filter(accStatus -> accStatus.id == value).findFirst();
    }

    public String getName() {
        return name;
    }

    public String getLogSubfolderName() {
        return logSubfolderName;
    }

    public String getSpringHttpBeanName() {
        return springHttpBeanName;
    }

    public int getId() {
        return id;
    }

}
