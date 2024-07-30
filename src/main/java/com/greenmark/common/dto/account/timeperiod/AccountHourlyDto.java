package com.greenmark.common.dto.account.timeperiod;

import java.io.Serializable;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountHourlyDto</p>
 * <p>Description: This DTO is for the results database account_hourlies table.  A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 * DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class AccountHourlyDto extends AccountTimePeriodBaseDto implements Serializable {
	public static final String CLASSNAME = "AccountHourlyDto";
	private static final long serialVersionUID = 1L;

	protected long accountDailyId = 0; // set to zero to find insert errors

	public AccountHourlyDto() {
	}

	public String dbSummary() {
        String stb = " > > " + CLASSNAME + ":" +
                "AccountHourlyId [" + id + "] " +
                "accountDailyId [" + accountDailyId + "] " +
                "active [" + active + "] ";
		return stb;
	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public long getAccountDailyId() {
		return accountDailyId;
	}

	public void setAccountDailyId(long accountDailyId) {
		this.accountDailyId = accountDailyId;
	}
}
