package com.greenmark.common.dto.scenario.decorator;

import java.io.Serializable;

import com.greenmark.common.dto.parameters.AccountBalanceParams;
import com.greenmark.common.dto.scenario.ScenarioDto;
import com.greenmark.utils.UTCalendarTime;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ScenarioDtoDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its ScenarioDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ScenarioDtoDecorator extends ScenarioDto implements Serializable {
    private static final String CLASSNAME = "ScenarioDtoDecorator";
    private static final long serialVersionUID = 1L;

    public ScenarioDtoDecorator() {
    }

    // This is used by the historical scenarios to do 3 scenarios for 1 account.
    public ScenarioDtoDecorator(ScenarioDtoDecorator currentScenario, AccountBalanceParams accountBalance, int currentScenarioNumber) {
        super(currentScenario, accountBalance, currentScenarioNumber);
    }

    public ScenarioDtoDecorator(String xmldata) {
        super(xmldata);
    }

    // ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
    public String getStartDateInFieldDisplay() {
        String returnString = "N/A";

        try {
            if (startDate != null) {
                UTCalendarTime returnTime = new UTCalendarTime(startDate);
                returnString = returnTime.formatParameterDate();
            }
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN ScenarioDbDecorator!   getStartDateInFieldDisplay  Exception Message:  [" + ex.getMessage() + "]");
        }

        return returnString;
    }

    public String getStartDateDisplay() {
        String returnString = "N/A";

        try {
            if (startDate != null) {
                UTCalendarTime returnTime = new UTCalendarTime(startDate);
                returnString = returnTime.formatDateDisplay();
            }
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN ScenarioDbDecorator!   getStartDateDisplay  Exception Message:  [" + ex.getMessage() + "]");
        }

        return returnString;
    }

    public String getStopDateInFieldDisplay() {
        String returnString = "N/A";

        try {
            if (stopDate != null) {
                UTCalendarTime returnTime = new UTCalendarTime(stopDate);
                returnString = returnTime.formatParameterDate();
            }
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN ScenarioDbDecorator!   getStopDateInFieldDisplay  Exception Message:  [" + ex.getMessage() + "]");
        }

        return returnString;
    }

    public String getStopDateDisplay() {
        String returnString = "N/A";

        try {
            if (stopDate != null) {
                UTCalendarTime returnTime = new UTCalendarTime(stopDate);
                returnString = returnTime.formatDateDisplay();
            }
            return returnString;
        } catch (Exception ex) {
            System.out.println("============= ERROR IN ScenarioDbDecorator!   getStopDateDisplay  Exception Message:  [" + ex.getMessage() + "]");
        }

        return returnString;
    }

    public String dbSummary() {
        String stb = " > > " + CLASSNAME + " :: " +
                "id [" + id + "] " +
                "modelTemplateId [" + modelId + "] " +
                "name [" + name + "] " +
                "active [" + active + "] ";
        return stb;
    }
}
