package com.greenmark.common.dto.broker;

import java.util.Vector;

import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 * <p>Title: BrokerContract</p>
 * <p>Description: This class is used to pass Contract information from the Broker Service to other services.</p>

 * @author  Monte Seibel
 * @version 7.5
 * @formatter:on
**/

public class BrokerContract {
	public static final String CLASSNAME = "BrokerContract";

	public int m_conId;
	public String m_symbol;
	public String m_secType;
	public String m_expiry;
	public double m_strike;
	public String m_right;
	public String m_multiplier;
	public String m_exchange;

	public String m_currency;
	public String m_localSymbol;
	public String m_primaryExch; // pick a non-aggregate (ie not the SMART exchange) exchange that the contract trades on. DO NOT SET TO SMART.
	public boolean m_includeExpired; // can not be set to true for orders.

	// COMBOS
	public String m_comboLegsDescrip; // received in open order version 14 and up for all combos
	public Vector m_comboLegs = new Vector();

	public BrokerContract() {
		// default constructor
	}

	public BrokerContract(String xmldata) {
		this();

		try {
			this.m_symbol = UTXmlUtils.getXmlData(xmldata, "M_SYMBOL");
			this.m_localSymbol = UTXmlUtils.getXmlData(xmldata, "M_LOCALSYMBOL");
			this.m_exchange = UTXmlUtils.getXmlData(xmldata, "M_EXCHANGE");
			this.m_primaryExch = UTXmlUtils.getXmlData(xmldata, "M_PRIMARYEXCH");
			this.m_secType = UTXmlUtils.getXmlData(xmldata, "M_SECTYPE");

			this.m_conId = UTXmlUtils.getXmlDataAsInt(xmldata, "M_CONID");
			this.m_expiry = UTXmlUtils.getXmlData(xmldata, "M_EXPIRY");
			this.m_strike = UTXmlUtils.getXmlDataAsDouble(xmldata, "M_STRIKE");
			this.m_right = UTXmlUtils.getXmlData(xmldata, "M_RIGHT");
			this.m_multiplier = UTXmlUtils.getXmlData(xmldata, "M_MULTIPLIER");
			this.m_currency = UTXmlUtils.getXmlData(xmldata, "M_CURRENCY");
			this.m_includeExpired = UTXmlUtils.getXmlDataAsBoolean(xmldata, "M_INCLUDEEXPIRED");
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + ".Constructor; message [" + e.getMessage() + "]");
		}
	}

	// ------------------------------------------------ XML IN/OUT ---------------------------------------------------
	public String toXml() {
		String prefix = "";
		String endline = "\n";
		return toXml(prefix, endline);
	}

	public String toXml(String prefix, String endline) {

        String stb = "<" + CLASSNAME + ">" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_SYMBOL>" + m_symbol + "</M_SYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_LOCALSYMBOL>" + m_localSymbol + "</M_LOCALSYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_EXCHANGE>" + m_exchange + "</M_EXCHANGE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_PRIMARYEXCH>" + m_primaryExch + "</M_PRIMARYEXCH>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_SECTYPE>" + m_secType + "</M_SECTYPE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_CONID>" + m_conId + "</M_CONID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_EXPIRY>" + m_expiry + "</M_EXPIRY>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_STRIKE>" + m_strike + "</M_STRIKE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_RIGHT>" + m_right + "</M_RIGHT>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_MULTIPLIER>" + m_multiplier + "</M_MULTIPLIER>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_CURRENCY>" + m_currency + "</M_CURRENCY>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_INCLUDEEXPIRED>" + m_includeExpired + "</M_INCLUDEEXPIRED>" + endline +
                "</" + CLASSNAME + ">" + endline;
		return stb;
	}

	@Deprecated
	public String toXmlShort(String prefix, String endline) {

        String stb = "<" + CLASSNAME + ">" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_SYMBOL>" + m_symbol + "</M_SYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_LOCALSYMBOL>" + m_localSymbol + "</M_LOCALSYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_EXCHANGE>" + m_exchange + "</M_EXCHANGE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_PRIMARYEXCH>" + m_primaryExch + "</M_PRIMARYEXCH>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_SECTYPE>" + m_secType + "</M_SECTYPE>" + endline +

                // stb.append(prefix + UTDisplayFormatter.TAB + "<M_CONID>" + m_conId + "</M_CONID>" + endline);
                // stb.append(prefix + UTDisplayFormatter.TAB + "<M_EXPIRY>" + m_expiry + "</M_EXPIRY>" + endline);
                // stb.append(prefix + UTDisplayFormatter.TAB + "<M_STRIKE>" + m_strike + "</M_STRIKE>" + endline);
                // stb.append(prefix + UTDisplayFormatter.TAB + "<M_RIGHT>" + m_right + "</M_RIGHT>" + endline);
                // stb.append(prefix + UTDisplayFormatter.TAB + "<M_MULTIPLIER>" + m_multiplier + "</M_MULTIPLIER>" + endline);
                // stb.append(prefix + UTDisplayFormatter.TAB + "<M_CURRENCY>" + m_currency + "</M_CURRENCY>" + endline);
                // stb.append(prefix + UTDisplayFormatter.TAB + "<M_INCLUDEEXPIRED>" + m_includeExpired + "</M_INCLUDEEXPIRED>" + endline);

                "</" + CLASSNAME + ">" + endline;
		return stb;
	}

	public String toString() {
		return toString(UTDisplayFormatter.TAB, "\n");
	}

	public String toString(String prefix, String endline) {

        String stb = prefix + "-----------------------------------------------" + endline +
                prefix + CLASSNAME + " for securityPrice= [" + m_symbol + "]" + endline +
                prefix + " . . .m_conId=   [" + m_conId + "]" + endline +
                prefix + " . . .m_symbol=  [" + m_symbol + "]" + endline +
                prefix + " . . .m_secType= [" + m_secType + "]" + endline +
                prefix + " . . .m_expiry=  [" + m_expiry + "]" + endline +
                prefix + " . . .m_strike=  [" + m_strike + "]" + endline +
                prefix + " . . .m_right=   [" + m_right + "]" + endline +
                prefix + " . . .m_multiplier= [" + m_multiplier + "]" + endline +
                prefix + " . . .m_exchange=   [" + m_exchange + "]" + endline +
                prefix + " . . .m_currency=   [" + m_currency + "]" + endline +
                prefix + " . . .m_localSymbol= [" + m_localSymbol + "]" + endline +
                prefix + " . . .m_primaryExch    = [" + m_primaryExch + "]" + endline +
                prefix + " . . .m_includeExpired = [" + m_includeExpired + "]" + endline +
                prefix + "-----------------------------------------------" + endline;

		return stb;
	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public int getM_conId() {
		return m_conId;
	}

	public void setM_conId(int m_conId) {
		this.m_conId = m_conId;
	}

	public String getM_symbol() {
		return m_symbol;
	}

	public void setM_symbol(String m_symbol) {
		this.m_symbol = m_symbol;
	}

	public String getM_secType() {
		return m_secType;
	}

	public void setM_secType(String m_secType) {
		this.m_secType = m_secType;
	}

	public String getM_expiry() {
		return m_expiry;
	}

	public void setM_expiry(String m_expiry) {
		this.m_expiry = m_expiry;
	}

	public double getM_strike() {
		return m_strike;
	}

	public void setM_strike(double m_strike) {
		this.m_strike = m_strike;
	}

	public String getM_right() {
		return m_right;
	}

	public void setM_right(String m_right) {
		this.m_right = m_right;
	}

	public String getM_multiplier() {
		return m_multiplier;
	}

	public void setM_multiplier(String m_multiplier) {
		this.m_multiplier = m_multiplier;
	}

	public String getM_exchange() {
		return m_exchange;
	}

	public void setM_exchange(String m_exchange) {
		this.m_exchange = m_exchange;
	}

	public String getM_currency() {
		return m_currency;
	}

	public void setM_currency(String m_currency) {
		this.m_currency = m_currency;
	}

	public String getM_localSymbol() {
		return m_localSymbol;
	}

	public void setM_localSymbol(String m_localSymbol) {
		this.m_localSymbol = m_localSymbol;
	}

	public String getM_primaryExch() {
		return m_primaryExch;
	}

	public void setM_primaryExch(String m_primaryExch) {
		this.m_primaryExch = m_primaryExch;
	}

	public boolean isM_includeExpired() {
		return m_includeExpired;
	}

	public void setM_includeExpired(boolean m_includeExpired) {
		this.m_includeExpired = m_includeExpired;
	}

	public String getM_comboLegsDescrip() {
		return m_comboLegsDescrip;
	}

	public void setM_comboLegsDescrip(String m_comboLegsDescrip) {
		this.m_comboLegsDescrip = m_comboLegsDescrip;
	}

	public Vector getM_comboLegs() {
		return m_comboLegs;
	}

	public void setM_comboLegs(Vector m_comboLegs) {
		this.m_comboLegs = m_comboLegs;
	}
}
