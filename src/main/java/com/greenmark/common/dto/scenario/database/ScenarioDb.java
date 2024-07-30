package com.greenmark.common.dto.scenario.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.greenmark.common.dto.account.AccountSummaryDto;
import com.greenmark.common.dto.account.timeperiod.decorator.AccountDailyDtoDecorator;
import com.greenmark.common.dto.broker.database.decorator.PositionDbDecorator;

import com.greenmark.common.dto.parameters.AccountBalanceParams;
import com.greenmark.common.dto.scenario.decorator.ScenarioDtoDecorator;
import com.greenmark.common.dto.strategy.StrategyDto;
import com.greenmark.common.dto.strategy.decorator.RejectedSecurityDtoDecorator;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTUtils;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ScenarioDb</p>
 * <p>Description: This class extends its base DTO and adds other DTO object instances.  These are sent to/from the database to store hierarchies of objects.
 *    It is also used by the trading client to add additional information it needs when calculating, but does not need to be stored in the database. </p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ScenarioDb extends ScenarioDtoDecorator implements Serializable {
	private static final String CLASSNAME = "ScenarioDb";
	private static final long serialVersionUID = 1L;

	protected AccountSummaryDto scenarioSummary;

	// NOTE: None of the following member variables are needed in XML Save/Restore.
	protected StrategyDto strategy;

	protected List<PositionDbDecorator> positionList = new ArrayList<>();
	protected boolean hasPositions = false;

	protected List<RejectedSecurityDtoDecorator> rejectedList = new ArrayList<>();
	protected boolean hasRejectedPositions = false;

	protected List<PositionDbDecorator> dropcatPositionList = new ArrayList<>();
	protected boolean hasDropcatPositions = false;

	protected List<AccountDailyDtoDecorator> accountDailyList = new ArrayList<>();
	protected boolean hasAccountDaily;

	public ScenarioDb() {
	}

	// This is used by the historical scenarios to do 3 scenarios for 1 account.
	public ScenarioDb(ScenarioDtoDecorator currentScenario, AccountBalanceParams accountBalance, int currentScenarioNumber) {
		super(currentScenario, accountBalance, currentScenarioNumber);
	}

	// ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
	public ScenarioDb(String xmldata) {
		super(xmldata);

		try {
			String scenarioSummaryXml = UTXmlUtils.getXmlData(xmldata, "SCENARIO_SUMMARY");
			if (UTUtils.isNotNorE(scenarioSummaryXml))
				scenarioSummary = new AccountSummaryDto(scenarioSummaryXml);
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + ".Constructor; message [" + e.getMessage() + "]");
		}
	}

	public String toXmlWrapper(String prefix, String endline) {
		StringBuffer stb = new StringBuffer();
		stb.append(prefix + "<SCENARIO>" + endline);
		stb.append(prefix + toXml(prefix, endline) + endline);
		stb.append(prefix + "</SCENARIO>" + endline);
		return stb.toString();
	}

	@Override
	public final String toXml(String prefix, String endline) {
		StringBuffer stb = new StringBuffer();

		stb.append(super.toXml(prefix, endline));

		if (scenarioSummary != null) {
			stb.append(prefix + UTDisplayFormatter.TAB + "<SCENARIO_SUMMARY>" + endline);
			stb.append(scenarioSummary.toXml(prefix + UTDisplayFormatter.TAB + UTDisplayFormatter.TAB, endline));
			stb.append(prefix + UTDisplayFormatter.TAB + "</SCENARIO_SUMMARY>" + endline);
		}

		return stb.toString();
	}

	// ------------------------------------------------ CONVENIENCE METHODS ---------------------------------------------------
	// Failsafe method for website
//	public boolean isHasPositions() {
//		if (positionList == null || positionList.isEmpty()) {
//			setHasPositions(false);
//			return hasPositions;
//		} else {
//			setHasPositions(true);
//			return hasPositions;
//		}
//	}

	// Failsafe method for website
	public boolean isHasRejectedPositions() {
		if (rejectedList == null || rejectedList.isEmpty()) {
			setHasRejectedPositions(false);
			return hasRejectedPositions;
		} else {
			setHasRejectedPositions(true);
			return hasRejectedPositions;
		}
	}

	// Failsafe method for website
//	public boolean isHasDropcatPositions() {
//		if (dropcatPositionList == null || dropcatPositionList.isEmpty()) {
//			setHasDropcatPositions(false);
//			return hasDropcatPositions;
//		} else {
//			setHasDropcatPositions(true);
//			return hasDropcatPositions;
//		}
//	}

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

	public String dbSummary() {
		StringBuffer stb = new StringBuffer();
		stb.append(" > > " + CLASSNAME + " :: ");
		stb.append("id [" + id + "] ");
		stb.append("modelTemplateId [" + modelId + "] ");
		stb.append("name [" + name + "] ");
		stb.append("active [" + active + "] ");
		return stb.toString();
	}

	public int getNumDaysInScenario() {
		return accountDailyList.size() - 1;
	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public List<AccountDailyDtoDecorator> getAccountDailyList() {
		return accountDailyList;
	}

	public void setAccountDailyList(List<AccountDailyDtoDecorator> accountDailyList) {
		this.accountDailyList = accountDailyList;
	}

	public AccountSummaryDto getScenarioSummary() {
		return scenarioSummary;
	}

	public void setScenarioSummary(AccountSummaryDto scenarioSummary) {
		this.scenarioSummary = scenarioSummary;
	}

	public List<PositionDbDecorator> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<PositionDbDecorator> positionList) {
		this.positionList = positionList;
	}

	public void setHasPositions(boolean hasPositions) {
		this.hasPositions = hasPositions;
	}

	public List<RejectedSecurityDtoDecorator> getRejectedList() {
		return rejectedList;
	}

	public void setRejectedList(List<RejectedSecurityDtoDecorator> rejectedList) {
		this.rejectedList = rejectedList;
	}

	public void setHasRejectedPositions(boolean hasRejectedPositions) {
		this.hasRejectedPositions = hasRejectedPositions;
	}

	public List<PositionDbDecorator> getDropcatPositionList() {
		return dropcatPositionList;
	}

	public void setDropcatPositionList(List<PositionDbDecorator> dropcatPositionList) {
		this.dropcatPositionList = dropcatPositionList;
	}

	public void setHasDropcatPositions(boolean hasDropcatPositions) {
		this.hasDropcatPositions = hasDropcatPositions;
	}

	public void setHasAccountDaily(boolean hasAccountDaily) {
		this.hasAccountDaily = hasAccountDaily;
	}

	public StrategyDto getStrategy() {
		return strategy;
	}

	public void setStrategy(StrategyDto model) {
		this.strategy = model;
	}
}
