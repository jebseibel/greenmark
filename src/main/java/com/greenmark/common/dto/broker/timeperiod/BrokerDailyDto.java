package com.greenmark.common.dto.broker.timeperiod;

import java.io.Serializable;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: BrokerDailyDto</p>
 * <p>Description: This Dto is used to store the Broker account balance data for a daily time period.  This data is stored in the account_dailies table</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class BrokerDailyDto extends BrokerTimePeriodDtoBase implements Serializable {
	public static final String CLASSNAME = "BrokerDailyDto";
	private static final long serialVersionUID = 1L;

	protected long accountId = 0;

	public BrokerDailyDto() {
	}

	public String dbSummary() {
        String stb = " > > " + CLASSNAME + ":" +
                "accountDailyId        [" + id + "] " +
                "accountId             [" + accountId + "] " +
                "active                [" + active + "] ";
		return stb;
	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long account_id) {
		this.accountId = account_id;
	}
}
