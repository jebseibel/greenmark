package com.greenmark.common.dto.account.database;

import com.greenmark.common.dto.account.decorator.AccountDtoDecorator;
import com.greenmark.common.dto.account.timeperiod.decorator.AccountDailyDtoDecorator;
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
 * <p>Title: AccountDb</p>
 * <p>Description: This class extends its base DTO and adds other DTO object instances.  These are sent to/from the database to store hierarchies of objects.
 *    It is also used by the trading client to add additional information it needs when calculating, but does not need to be stored in the database.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class AccountDb extends AccountDtoDecorator implements Serializable {
    public static final String CLASSNAME = "AccountDb";
    private static final long serialVersionUID = 1L;

    protected int numDaysInAccount = 0;

    protected List<AccountEventDtoDecorator> eventList = new ArrayList<>();

    protected List<ScenarioDecorator> scenarioList = new ArrayList<>();
    protected boolean hasScenarios;


    // NOTE: None of the following member variables are needed in XML Save/Restore.
    protected List<AccountDailyDtoDecorator> accountDailyList = new ArrayList<>();
    protected boolean hasAccountDaily;

    protected AccountDailyDtoDecorator previousDay = null; // Used by the website AccountDailySummary webpage for display

    public AccountDb() {
        super();
    }

    // ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
//	public AccountDb(String xmldata) {
//		super(xmldata, trace);
//
//		this.numDaysInAccount = UTXmlUtils.getXmlDataAsInt(xmldata, "NUM_DAYS_IN_ACCOUNT");
//
//		String xmlAccountEventList = UTXmlUtils.getXmlData(xmldata, "ACCOUNT_EVENT_LIST");
//		if (UTUtils.isNotNorE(xmlAccountEventList)) {
//			Vector vXmlAccountEvents = UTXmlUtils.getElementsToVector(xmlAccountEventList, "ACCOUNT_EVENT");
//
//			for (Enumeration e = vXmlAccountEvents.elements(); e.hasMoreElements();) {
//				String xml = (String) e.nextElement();
//				AccountEventDtoDecorator thisEvent = new AccountEventDtoDecorator(xml);
//				this.eventList.add(thisEvent);
//			}
//		}
//
//		String xmlScenarioList = UTXmlUtils.getXmlData(xmldata, "SCENARIO_LIST");
//		if (UTUtils.isNotNorE(xmlScenarioList)) {
//			Vector vXmlScenarios = UTXmlUtils.getElementsToVector(xmlScenarioList, "SCENARIO");
//
//			for (Enumeration e = vXmlScenarios.elements(); e.hasMoreElements();) {
//				String xml = (String) e.nextElement();
//				ScenarioDecorator thisEvent = new ScenarioDecorator(xml);
//				this.scenarioList.add(thisEvent);
//				hasScenarios = true;
//			}
//		}
//
//		if (UTUtils.isNotNorE(UTXmlUtils.getXmlData(xmldata, "CREATE_ACCOUNT")))
//			createAccountData = new CreateAccountDto(UTXmlUtils.getXmlData(xmldata, "CREATE_ACCOUNT"), trace);
//	}
//
//	public String toXmlWrapper(String prefix, String endline) {
//		StringBuffer stb = new StringBuffer();
//		stb.append(prefix + "<ACCOUNT>" + endline);
//		stb.append(prefix + toXml(prefix, endline) + endline);
//		stb.append(prefix + "</ACCOUNT>" + endline);
//		return stb.toString();
//	}
//
//	public String toXml(String prefix, String endline) {
//		StringBuffer stb = new StringBuffer();
//		stb.append(super.toXml(prefix, endline));
//
//		stb.append(prefix + "<NUM_DAYS_IN_ACCOUNT>" + this.numDaysInAccount + "</NUM_DAYS_IN_ACCOUNT>" + endline);
//
//		if (eventList != null) {
//			stb.append(UTDisplayFormatter.TAB + "<ACCOUNT_EVENT_LIST>" + endline);
//			for (AccountEventDtoDecorator thisEvent : eventList) {
//				stb.append(thisEvent.toXmlWrapper(prefix + UTDisplayFormatter.TAB, endline));
//			}
//			stb.append(prefix + "</ACCOUNT_EVENT_LIST>" + endline);
//		}
//
//		if (scenarioList != null) {
//			stb.append(UTDisplayFormatter.TAB + "<SCENARIO_LIST>" + endline);
//			for (ScenarioDecorator thisEvent : scenarioList) {
//				stb.append(thisEvent.toXmlWrapper(prefix + UTDisplayFormatter.TAB, endline));
//			}
//			stb.append(prefix + "</SCENARIO_LIST>" + endline);
//		}
//
//		if (createAccountData != null) {
//			stb.append(prefix + "<CREATE_ACCOUNT>" + endline);
//			stb.append(createAccountData.toXml(prefix + UTDisplayFormatter.TAB, "\n"));
//			stb.append(prefix + "</CREATE_ACCOUNT>" + endline);
//		}
//
//		return stb.toString();
//	}

    public static String getClassname() {
        return CLASSNAME;
    }

    // ------------------------------------------- CONVENIENCE METHODS -----------------------------------
    public void addScenario(ScenarioDecorator newScenario) {
        if (scenarioList == null)
            scenarioList = new ArrayList<ScenarioDecorator>();

        scenarioList.add(newScenario);
        if (scenarioList.size() > 0)
            hasScenarios = true;
    }

    // Failsafe method for website
    public boolean isHasScenarios() {
        if (scenarioList == null || scenarioList.isEmpty()) {
            setHasScenarios(false);
            return hasScenarios;
        } else {
            setHasScenarios(true);
            return hasScenarios;
        }
    }

    public void setHasScenarios(boolean hasScenarios) {
        this.hasScenarios = hasScenarios;
    }

    // Failsafe method for website
    public boolean isHasAccountDaily() {
        if (accountDailyList == null || accountDailyList.isEmpty()) {
            setHasAccountDaily(false);
            return hasAccountDaily;
        } else {
            setHasAccountDaily(true);
            return hasAccountDaily;
        }
    }

    public void setHasAccountDaily(boolean hasAccountDaily) {
        this.hasAccountDaily = hasAccountDaily;
    }

    public int getNumDaysInAccount() {
        // Every scenario adds an extra day.
        int numScenarios = scenarioList.size();

        int returnVal = accountDailyList.size() - numScenarios;
        return returnVal;
    }

    // ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
    public void setNumDaysInAccount(int numDaysInAccount) {
        this.numDaysInAccount = numDaysInAccount;
    }

    public List<AccountEventDtoDecorator> getEventList() {
        return eventList;
    }

    public void setEventList(List<AccountEventDtoDecorator> eventList) {
        this.eventList = eventList;
    }

    public List<ScenarioDecorator> getScenarioList() {
        return scenarioList;
    }

    public void setScenarioList(List<ScenarioDecorator> scenarioList) {
        this.scenarioList = scenarioList;
    }

    public List<AccountDailyDtoDecorator> getAccountDailyList() {
        return accountDailyList;
    }

    public void setAccountDailyList(List<AccountDailyDtoDecorator> accountDailyList) {
        this.accountDailyList = accountDailyList;
    }

    public AccountDailyDtoDecorator getPreviousDay() {
        return previousDay;
    }

    public void setPreviousDay(AccountDailyDtoDecorator previousDay) {
        this.previousDay = previousDay;
    }
}
