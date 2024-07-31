package com.greenmark.common.dto.security;

import java.io.Serializable;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsBroker;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: SecurityDto</p>
 * <p>Description: This Dto extends its parent to add more member variables that are used when the trading client performs calculations.  These securities are placed into
 *    timeframe buckets and keep track of whether we are currently buying or selling a position, as well as other necessary information about the security needed to 
 *    process strategy calculations. These objects are also read into the CurrencyManager and are defined in the start_currency_list.xml file for that feature.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class SecurityDto extends SecurityBaseDto implements Serializable {
    public static final String CLASSNAME = "SecurityDto";
    private static final long serialVersionUID = 1L;

    protected long securityId = 0; // set to zero to find insert errors

    protected int longOrShort = GmConstants.LONG_SECURITY;
    protected int stockType = GmConstants.TYPE_CRYPTO;
    protected int buyOrSell = GmConstantsBroker.TYPE_BUY;

    protected int numPeriodsInBucket = 0;

    public SecurityDto() {
    }

    public SecurityDto(String displaySymbol, String dbQuerySymbol, int longOrShort, int stockType) {
        super(displaySymbol, dbQuerySymbol);

        this.longOrShort = longOrShort;
        this.stockType = stockType;
    }

//	// ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
//	public SecurityDto(String xmldata) {
//		super(xmldata, trace);
//
//		try {
//			securityId = UTXmlUtils.getXmlDataAsLong(xmldata, "SECURITY_ID");
//
//			longOrShort = UTXmlUtils.getXmlDataAsInt(xmldata, "LONG_OR_SHORT");
//			stockType = UTXmlUtils.getXmlDataAsInt(xmldata, "STOCK_TYPE");
//			buyOrSell = UTXmlUtils.getXmlDataAsInt(xmldata, "BUY_OR_SELL");
//
//			numPeriodsInBucket = UTXmlUtils.getXmlDataAsInt(xmldata, "NUM_PERIODS_IN_BUCKET");
//		} catch (Exception e) {
//			System.out.println("Error creating stock from XML, message: " + e.getMessage());
//		}
//	}
//
//	public String toXmlWrapper(String prefix, String endline) {
//        String stb = prefix + "<SECURITY>" + endline +
//                prefix + toXml(prefix, endline) + endline +
//                prefix + "</SECURITY>" + endline;
//		return stb;
//	}
//
//	public String toXml(String prefix, String endline) {
//
//        String stb = super.toXml(prefix, endline) +
//                prefix + UTDisplayFormatter.TAB + "<SECURITY_ID>" + this.securityId + "</SECURITY_ID>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<LONG_SHORT>" + this.longOrShort + "</LONG_SHORT>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<STOCK_TYPE>" + this.stockType + "</STOCK_TYPE>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<BUY_OR_SELL>" + this.buyOrSell + "</BUY_OR_SELL>" + endline +
//                prefix + UTDisplayFormatter.TAB + "<NUM_PERIODS_IN_BUCKET>" + this.numPeriodsInBucket + "</NUM_PERIODS_IN_BUCKET>" + endline;
//
//		return stb;
//	}

    // ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
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
}
