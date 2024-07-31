package com.greenmark.common.dto.account.database;

import com.greenmark.common.GmConstantsAccount;
import com.greenmark.common.config.Config;
import com.greenmark.common.config.ConfigAccount;
import com.greenmark.common.dto.account.timeperiod.decorator.AccountEventDtoDecorator;
import com.greenmark.common.dto.scenario.database.decorator.ScenarioDecorator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: AccountDbDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its AccountDb base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class AccountDbDecorator extends AccountDb implements Serializable {
    public static final String CLASSNAME = "AccountDbDecorator";
    private static final long serialVersionUID = 1L;

    public AccountDbDecorator() {
        super();
    }

//	public AccountDbDecorator(CreateAccountDto createAccountData) {
//		super(createAccountData);
//	}
//
//	public AccountDbDecorator(String xmldata) {
//		super(xmldata, trace);
//	}

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// ACCOUNT EVENT LIST METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final boolean isAccountEventListOn() {
        int useEventList = Config.getValueAsInt(ConfigAccount.ACCTCONFIG_USE_ACCOUNT_EVENT_LIST);
        return useEventList == 1;
    }

    public String getAccountTypeDisplay() {
        if (accountType == GmConstantsAccount.ACCOUNT_TYPE_STANDARD)
            return GmConstantsAccount.ACCOUNT_TYPE_STANDARD_STRING;
        else
            return GmConstantsAccount.ACCOUNT_TYPE_REGT_STRING;
    }

    public void setAccountDailyList(List accountDailyList) {
        this.accountDailyList = accountDailyList;
        if (accountDailyList.size() > 0)
            hasAccountDaily = true;
    }

    public void addScenario(ScenarioDecorator newScenario) {
        if (scenarioList == null)
            scenarioList = new ArrayList<ScenarioDecorator>();

        scenarioList.add(newScenario);
        if (scenarioList.size() > 0)
            hasScenarios = true;
    }

    public final void addAccountEvent(AccountEventDtoDecorator acctEvent) {
        eventList.add(acctEvent);
    }

    public final void clearEventList() {
        eventList.clear();
    }
}
