package com.greenmark.common.dto.security.database;

import java.io.Serializable;
import java.util.List;

import com.greenmark.common.dto.ExchangeDto;
import com.greenmark.common.dto.security.SecurityDto;
import com.greenmark.common.dto.security.SecurityExchangeDto;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: SecurityDb</p>
 * <p>Description: This class extends its base DTO and adds other DTO object instances.  These are sent to/from the database to store hierarchies of objects.
 *    It is also used by the trading client to add additional information it needs when calculating, but does not need to be stored in the database.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class SecurityDb extends SecurityDto implements Serializable {
    public static final String CLASSNAME = "SecurityDb";
    private static final long serialVersionUID = 1L;

    protected long accountId = 0; // set to zero to find insert errors

    protected int numDisqualifiedMinute60 = 0;
    protected int numDisqualifiedMinute15 = 0;
    protected int numDisqualifiedMinute05 = 0;
    protected int numDisqualifiedMinute01 = 0;

    protected List<ExchangeDto> exchanges = null;
    protected SecurityExchangeDto securityExchangeDto; // Contains leverageRatio, shortable and inverse members.

    public SecurityDb() {
        super();
        securityExchangeDto = new SecurityExchangeDto();
    }

    public SecurityDb(String displaySymbol, String dbQuerySymbol, int longOrShort, int stockType) {
        super(displaySymbol, dbQuerySymbol, longOrShort, stockType);
        securityExchangeDto = new SecurityExchangeDto();
    }

    // ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
//	public SecurityDb(String xmldata) {
//		super(xmldata, trace);
//
//		try {
//			accountId = UTXmlUtils.getXmlDataAsLong(xmldata, "ACCOUNT_ID");
//
//			numDisqualifiedMinute01 = UTXmlUtils.getXmlDataAsInt(xmldata, "NUM_DIS_MIN_01");
//			numDisqualifiedMinute05 = UTXmlUtils.getXmlDataAsInt(xmldata, "NUM_DIS_MIN_05");
//			numDisqualifiedMinute15 = UTXmlUtils.getXmlDataAsInt(xmldata, "NUM_DIS_MIN_15");
//			numDisqualifiedMinute60 = UTXmlUtils.getXmlDataAsInt(xmldata, "NUM_DIS_MIN_60");
//
//			String execList = UTXmlUtils.getXmlData(xmldata, "EXCHANGE_LIST");
//			Vector vXmlExchanges = UTXmlUtils.getElementsToVector(execList, "EXCHANGE");
//			for (Enumeration e = vXmlExchanges.elements(); e.hasMoreElements();) {
//				String xmlExchange = (String) e.nextElement();
//				ExchangeDto thisExchange = new ExchangeDto(xmlExchange);
//				this.exchanges.add(thisExchange);
//			}
//
//			String securityExchangeData = UTXmlUtils.getXmlData(xmldata, "SECURITY_EXCHANGE");
//			if (UTUtils.isNotNorE(securityExchangeData)) {
//				securityExchangeDto = new SecurityExchangeDto(securityExchangeData);
//			}
//
//		} catch (Exception e) {
//			System.out.println("Error creating stock from XML, message: " + e.getMessage());
//		}
//	}
//
//	public String toXmlWrapper(String prefix, String endline) {
//		StringBuffer stb = new StringBuffer();
//		stb.append(prefix + "<SECURITY>" + endline);
//		stb.append(prefix + toXml(prefix, endline) + endline);
//		stb.append(prefix + "</SECURITY>" + endline);
//		return stb.toString();
//	}
//
//	public String toXml(String prefix, String endline) {
//		StringBuffer stb = new StringBuffer();
//
//		stb.append(super.toXml(prefix, endline));
//
//		stb.append(prefix + UTDisplayFormatter.TAB + "<ACCOUNT_ID>" + this.accountId + "</ACCOUNT_ID>" + endline);
//
//		stb.append(prefix + UTDisplayFormatter.TAB + "<NUM_DIS_MIN_01>" + this.numDisqualifiedMinute01 + "</NUM_DIS_MIN_01>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<NUM_DIS_MIN_05>" + this.numDisqualifiedMinute05 + "</NUM_DIS_MIN_05>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<NUM_DIS_MIN_15>" + this.numDisqualifiedMinute15 + "</NUM_DIS_MIN_15>" + endline);
//		stb.append(prefix + UTDisplayFormatter.TAB + "<NUM_DIS_MIN_60>" + this.numDisqualifiedMinute60 + "</NUM_DIS_MIN_60>" + endline);
//
//		if (exchanges != null && !exchanges.isEmpty()) {
//			stb.append(prefix + UTDisplayFormatter.TAB + "<EXCHANGE_LIST>" + endline);
//			for (ExchangeDto exchange : exchanges) {
//				stb.append(prefix + exchange.toXmlWrapper(prefix + UTDisplayFormatter.TAB, endline) + endline);
//			}
//			stb.append(prefix + UTDisplayFormatter.TAB + "</EXCHANGE_LIST>" + endline);
//		}
//
//		if (securityExchangeDto != null) {
//			stb.append(prefix + UTDisplayFormatter.TAB + "<SECURITY_EXCHANGE>" + endline);
//			stb.append(securityExchangeDto.toXml(prefix + UTDisplayFormatter.TAB, "\n"));
//			stb.append(prefix + UTDisplayFormatter.TAB + "</SECURITY_EXCHANGE>" + endline);
//		}
//
//		return stb.toString();
//	}

    // ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public int getNumDisqualifiedMinute60() {
        return numDisqualifiedMinute60;
    }

    public void setNumDisqualifiedMinute60(int numDisqualifiedMinute60) {
        this.numDisqualifiedMinute60 = numDisqualifiedMinute60;
    }

    public int getNumDisqualifiedMinute15() {
        return numDisqualifiedMinute15;
    }

    public void setNumDisqualifiedMinute15(int numDisqualifiedMinute15) {
        this.numDisqualifiedMinute15 = numDisqualifiedMinute15;
    }

    public int getNumDisqualifiedMinute05() {
        return numDisqualifiedMinute05;
    }

    public void setNumDisqualifiedMinute05(int numDisqualifiedMinute05) {
        this.numDisqualifiedMinute05 = numDisqualifiedMinute05;
    }

    public int getNumDisqualifiedMinute01() {
        return numDisqualifiedMinute01;
    }

    public void setNumDisqualifiedMinute01(int numDisqualifiedMinute01) {
        this.numDisqualifiedMinute01 = numDisqualifiedMinute01;
    }

    public long getSecurityId() {
        return securityId;
    }

    public void setSecurityId(long securityId) {
        this.securityId = securityId;
    }

    public int getLongOrShort() {
        return longOrShort;
    }

    public void setLongOrShort(int longOrShort) {
        this.longOrShort = longOrShort;
    }

    public int getStockType() {
        return stockType;
    }

    public void setStockType(int stockType) {
        this.stockType = stockType;
    }

    public SecurityExchangeDto getSecurityExchangeDto() {
        return securityExchangeDto;
    }

    public void setSecurityExchangeDto(SecurityExchangeDto securityExchangeDto) {
        this.securityExchangeDto = securityExchangeDto;
    }

    public int getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(int buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

    public int getNumPeriodsInBucket() {
        return numPeriodsInBucket;
    }

    public void setNumPeriodsInBucket(int numPeriodsInBucket) {
        this.numPeriodsInBucket = numPeriodsInBucket;
    }

    public List<ExchangeDto> getExchanges() {
        return exchanges;
    }

    public void setExchanges(List<ExchangeDto> exchanges) {
        this.exchanges = exchanges;
    }
}
