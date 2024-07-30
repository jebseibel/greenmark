package com.greenmark.common.dto.security;

import java.io.Serializable;

import com.greenmark.common.GmConstants;
import com.greenmark.common.enums.CurrencySymbolEnum;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: SecurityBaseDto</p>
 * <p>Description: This DTO is for the results database securities table.  A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 * DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class SecurityBaseDto implements Serializable {
	public static final String CLASSNAME = "SecurityBaseDto";
	private static final long serialVersionUID = 1L;

	protected long id;
	protected int active = GmConstants.OBJECT_ACTIVE;

	protected String dbQuerySymbol; // Unique symbol for DB queries: Securities.symbol, for crypto format: <EXCHANGE>:<SHORTSYMBOL>
	protected String displaySymbol; // Used every label on GUI. From Prices DB column: Securities.name;

	protected String compactedSymbol; // This is parsed off the symbol after the ':'
	protected String market; // Also known as the exchange, parsed off the symbol before the ':'
	protected String compactedExchangeSymbol; // This is a unique identifier used by maps as it will contain the exchanges first 2 letter appended by the compactedSymbol

	protected String name; // Not used. This is for NYSE/NASDAQ or better crypto queries from Finnhub.

	public SecurityBaseDto() {
	}

	public SecurityBaseDto(String displaySymbol, String dbQuerySymbol) {
		// This has crypto format of exchange:compactedSymbol
		if (dbQuerySymbol.contains(":")) {
			this.market = dbQuerySymbol.split(":")[0];
			this.compactedSymbol = dbQuerySymbol.split(":")[1];

			this.displaySymbol = displaySymbol;
			this.name = displaySymbol;
		}

		this.dbQuerySymbol = dbQuerySymbol;
	}

	public boolean isUSD() {
        return dbQuerySymbol.endsWith(CurrencySymbolEnum.USD.getName());
	}

	public boolean isUSDT() {
        return dbQuerySymbol.endsWith(CurrencySymbolEnum.USDT.getName());
	}

	public boolean isUSDC() {
        return dbQuerySymbol.endsWith(CurrencySymbolEnum.USDC.getName());
	}

	public boolean isAnyUSD() {
        return isUSD() || isUSDT() || isUSDC();
	}

	// ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
	public SecurityBaseDto(String xmldata) {
		this();

		try {
			id = UTXmlUtils.getXmlDataAsInt(xmldata, "ID");
			active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");

			dbQuerySymbol = UTXmlUtils.getXmlData(xmldata, "DBQUERY_SYMBOL");
			displaySymbol = UTXmlUtils.getXmlData(xmldata, "DISPLAY_SYMBOL");

			compactedSymbol = UTXmlUtils.getXmlData(xmldata, "COMPACTED_SYMBOL");
			market = UTXmlUtils.getXmlData(xmldata, "MARKET");
			compactedExchangeSymbol = UTXmlUtils.getXmlData(xmldata, "COMPACTED_EXCHANGE_SYMBOL");

			name = UTXmlUtils.getXmlData(xmldata, "NAME");
		} catch (Exception e) {
			System.out.println("Error creating stock from XML, message: " + e.getMessage());
		}
	}

	public String toXmlWrapper(String prefix, String endline) {
        String stb = prefix + "<SECURITY>" + endline +
                prefix + toXml(prefix, endline) + endline +
                prefix + "</SECURITY>" + endline;
		return stb;
	}

	public String toXml(String prefix, String endline) {

        String stb = prefix + UTDisplayFormatter.TAB + "<ID>" + this.id + "</ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ACTIVE>" + this.getActive() + "</ACTIVE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<DBQUERY_SYMBOL>" + this.dbQuerySymbol + "</DBQUERY_SYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<DISPLAY_SYMBOL>" + this.displaySymbol + "</DISPLAY_SYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<COMPACTED_SYMBOL>" + this.compactedSymbol + "</COMPACTED_SYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<MARKET>" + this.market + "</MARKET>" + endline +
                prefix + UTDisplayFormatter.TAB + "<COMPACTED_EXCHANGE_SYMBOL>" + this.compactedExchangeSymbol + "</COMPACTED_EXCHANGE_SYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<NAME>" + this.name + "</NAME>" + endline;

		return stb;
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

	public String getDbQuerySymbol() {
		return dbQuerySymbol;
	}

	public void setDbQuerySymbol(String symbol) {
		this.dbQuerySymbol = symbol;
	}

	public String getDisplaySymbol() {
		return displaySymbol;
	}

	public void setDisplaySymbol(String displaySymbol) {
		this.displaySymbol = displaySymbol;
	}

	public String getCompactedSymbol() {
		return compactedSymbol;
	}

	public void setCompactedSymbol(String compactedSymbol) {
		this.compactedSymbol = compactedSymbol;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getCompactedExchangeSymbol() {
		return compactedExchangeSymbol;
	}

	public void setCompactedExchangeSymbol(String compactedExchangeSymbol) {
		this.compactedExchangeSymbol = compactedExchangeSymbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
