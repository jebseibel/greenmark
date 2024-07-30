package com.greenmark.common.dto;

import java.io.Serializable;
import java.util.Set;

import com.greenmark.common.GmConstants;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: ExchangeDto</p>
 * <p>Description: This DTO is for the RESULTS database exchanges table (NOT the pdb exchanges table).  A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 * DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class ExchangeDto implements Serializable {
	public static final String CLASSNAME = "ExchangeDto";
	private static final long serialVersionUID = 1L;

	private long id;
	private int active = GmConstants.OBJECT_ACTIVE;

	private String symbol;
	private String name;
	private Long securityId;

	private Set securities;

	public ExchangeDto() {
	}

	public ExchangeDto(String exchangeSymbolName) {
		this.symbol = exchangeSymbolName;
		this.name = exchangeSymbolName;
	}

//	public ExchangeDto(String xmldata) {
//		try {
//			id = UTXmlUtils.getXmlDataAsLong(xmldata, "ID");
//			active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");
//
//			symbol = UTXmlUtils.getXmlData(xmldata, "SYMBOL");
//			name = UTXmlUtils.getXmlData(xmldata, "NAME");
//			securityId = UTXmlUtils.getXmlDataAsLong(xmldata, "SECURITY_ID");
//		} catch (Exception e) {
//			System.out.println("Error creating exchange from XML, message: " + e.getMessage());
//		}
//	}
//
//	public String toXmlWrapper(String prefix, String endline) {
//        String stb = prefix + "<EXCHANGE>" + endline +
//                prefix + toXml(prefix, endline) + endline +
//                prefix + "</EXCHANGE>" + endline;
//		return stb;
//	}
//
//	public String toXml(String prefix, String endline) {
//
//        String stb = prefix + UTDisplayFormatter.TAB + "<ID>" + this.id + "</ID>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<ACTIVE>" + this.active + "</ACTIVE>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<SYMBOL>" + this.symbol + "</SYMBOL>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<NAME>" + this.name + "</NAME>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<SECURITY_ID>" + this.securityId + "</SECURITY_ID>" + endline;
//
//		return stb;
//	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public long getId() {
		return this.id;
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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getClassname() {
		return CLASSNAME;
	}

	public Set getSecurities() {
		return securities;
	}

	public void setSecurities(Set securities) {
		this.securities = securities;
	}

	public Long getSecurityId() {
		return securityId;
	}

	public void setSecurityId(Long security_id) {
		this.securityId = security_id;
	}

}
