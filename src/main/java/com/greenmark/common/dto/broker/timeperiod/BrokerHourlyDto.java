package com.greenmark.common.dto.broker.timeperiod;

import java.io.Serializable;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: BrokerHourlyDto</p>
 * <p>Description: This Dto is used to store the Broker account balance data for a hourly time period.  This data is stored in the account_hourlies table.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class BrokerHourlyDto extends BrokerTimePeriodDtoBase implements Serializable {
	public static final String CLASSNAME = "BrokerHourlyDto";
	private static final long serialVersionUID = 1L;

	protected long accountDailyId = 0;

	public BrokerHourlyDto() {
	}

	public String dbSummary() {
		StringBuffer stb = new StringBuffer();
		stb.append(" > > " + CLASSNAME + ":");
		stb.append("BrokerAccountHourlyId [" + id + "] ");
		stb.append("accountDailyId [" + accountDailyId + "] ");
		stb.append("active [" + active + "] ");
		return stb.toString();
	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public long getAccountDailyId() {
		return accountDailyId;
	}

	public void setAccountDailyId(long accountDailyId) {
		this.accountDailyId = accountDailyId;
	}
}
