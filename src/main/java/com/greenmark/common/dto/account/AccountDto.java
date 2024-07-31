package com.greenmark.common.dto.account;

import com.greenmark.common.GmConstants;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTDbUtils;
import com.greenmark.utils.UTUtils;
import com.greenmark.utils.UTXmlUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    public int accountType;
    protected long id;
    protected int active = GmConstants.OBJECT_ACTIVE;
    protected String name;
    protected String createDescription = "";
    protected String resultsDescription = "";
    protected LocalDateTime historyProcessStartTime;
    protected LocalDateTime historyProcessEndTime;
    protected boolean isRealtimeAccount;
    protected LocalDateTime realtimeAccountStartTime;

    public AccountDto() {
    }

    // ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
    public AccountDto(String xmldata) {
        try {
            this.id = UTXmlUtils.getXmlDataAsLong(xmldata, "ID");
            this.active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");

            this.name = UTXmlUtils.getXmlData(xmldata, "NAME");
            this.createDescription = UTXmlUtils.getXmlData(xmldata, "CREATE_DESCRIPTION");
            this.resultsDescription = UTXmlUtils.getXmlData(xmldata, "RESULTS_DESCRIPTION");

            String startTime = UTXmlUtils.getXmlData(xmldata, "HISTORY_PROCESS_START_TIME");
            if (UTUtils.isNotNorE(startTime))
                this.historyProcessStartTime = UTDatetime.fromDbString(startTime);

            String endTime = UTXmlUtils.getXmlData(xmldata, "HISTORY_PROCESS_END_TIME");
            if (UTUtils.isNotNorE(endTime))
                this.historyProcessEndTime = UTDatetime.fromDbString(endTime);

            this.accountType = UTXmlUtils.getXmlDataAsInt(xmldata, "ACCOUNT_TYPE");

            this.isRealtimeAccount = UTDbUtils.getBooleanInt(UTXmlUtils.getXmlDataAsInt(xmldata, "IS_REALTIME_ACCOUNT"));
            String realtimeAccountStartString = UTXmlUtils.getXmlData(xmldata, "REALTIME_ACCOUNT_START_TIME");
            if (UTUtils.isNotNorE(realtimeAccountStartString))
                this.realtimeAccountStartTime = UTDatetime.fromDbString(realtimeAccountStartString);

        } catch (Exception ex) {
            log.error("Caught General Exception " + CLASSNAME + ".constructor() message:[" + ex.getMessage() + "]");
        }
    }

    public String toXmlWrapper(String prefix, String endline) {
        String stb = prefix + "<ACCOUNT>" + endline +
                prefix + toXml(prefix, endline) + endline +
                prefix + "</ACCOUNT>" + endline;
        return stb;
    }

    public String toXml(String prefix, String endline) {
        StringBuffer stb = new StringBuffer();
        stb.append(prefix + "<ID>" + this.id + "</ID>" + endline);
        stb.append(prefix + "<ACTIVE>" + this.active + "</ACTIVE>" + endline);

        stb.append(prefix + "<NAME>" + this.name + "</NAME>" + endline);
        stb.append(prefix + "<CREATE_DESCRIPTION>" + this.createDescription + "</CREATE_DESCRIPTION>" + endline);
        stb.append(prefix + "<RESULTS_DESCRIPTION>" + this.resultsDescription + "</RESULTS_DESCRIPTION>" + endline);

        if (historyProcessStartTime != null)
            stb.append(prefix + "<HISTORY_PROCESS_START_TIME>" + UTDatetime.toDbString(historyProcessStartTime) + "</HISTORY_PROCESS_START_TIME>" + endline);

        if (historyProcessEndTime != null)
            stb.append(prefix + "<HISTORY_PROCESS_END_TIME>" + UTDatetime.toDbString(historyProcessEndTime) + "</HISTORY_PROCESS_END_TIME>" + endline);

        stb.append(prefix + "<ACCOUNT_TYPE>" + this.getAccountType() + "</ACCOUNT_TYPE>" + endline);

        stb.append(prefix + "<IS_REALTIME_ACCOUNT>" + UTDbUtils.getBooleanInt(this.isRealtimeAccount) + "</IS_REALTIME_ACCOUNT>" + endline);
        if (realtimeAccountStartTime != null)
            stb.append(prefix + "<REALTIME_ACCOUNT_START_TIME>" + UTDatetime.toDbString(realtimeAccountStartTime) + "</REALTIME_ACCOUNT_START_TIME>" + endline);
        return stb.toString();
    }

    // ------------------------------------------- REQUIRED METHODS -----------------------------------
    public final boolean equals(Object compareObject) {
        AccountDto obj = (AccountDto) compareObject;
        return obj.getKey().equals(Long.valueOf(id));
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
