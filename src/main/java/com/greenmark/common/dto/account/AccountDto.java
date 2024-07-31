package com.greenmark.common.dto.account;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.greenmark.common.GmConstants;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTDbUtils;
import com.greenmark.utils.UTUtils;
import com.greenmark.utils.UTXmlUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountDto</p>
 * <p>Description: This DTO is for the results database accounts table.  A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 * DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
@Slf4j
public class AccountDto implements Serializable {
	public static final String CLASSNAME = "AccountDto";
	private static final long serialVersionUID = 1L;

	protected long id;
	protected int active = GmConstants.OBJECT_ACTIVE;

	protected String name;
	protected String createDescription = "";
	protected String resultsDescription = "";

	protected LocalDateTime historyProcessStartTime;
	protected LocalDateTime historyProcessEndTime;

	protected boolean isRealtimeAccount;
	protected LocalDateTime realtimeAccountStartTime;

	public int accountType;

	public AccountDto() {
	}

	public final Object getKey() {
		return Long.valueOf(id);
	}

	public final Object getValue() {
		return this;
	}

	public final int hashCode() {
		return (int) id;
	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateDescription() {
		return createDescription;
	}

	public void setCreateDescription(String createDescription) {
		this.createDescription = createDescription;
	}

	public String getResultsDescription() {
		return resultsDescription;
	}

	public void setResultsDescription(String resultsDescription) {
		this.resultsDescription = resultsDescription;
	}

	public LocalDateTime getHistoryProcessStartTime() {
		return historyProcessStartTime;
	}

	public void setHistoryProcessStartTime(LocalDateTime historyProcessStartTime) {
		this.historyProcessStartTime = historyProcessStartTime;
	}

	public LocalDateTime getHistoryProcessEndTime() {
		return historyProcessEndTime;
	}

	public void setHistoryProcessEndTime(LocalDateTime historyProcessEndTime) {
		this.historyProcessEndTime = historyProcessEndTime;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public boolean isRealtimeAccount() {
		return isRealtimeAccount;
	}

	public void setRealtimeAccount(boolean isRealtimeAccount) {
		this.isRealtimeAccount = isRealtimeAccount;
	}

	public LocalDateTime getRealtimeAccountStartTime() {
		return realtimeAccountStartTime;
	}

	public void setRealtimeAccountStartTime(LocalDateTime realtimeAccountStartTime) {
		this.realtimeAccountStartTime = realtimeAccountStartTime;
	}
}
