package com.greenmark.common.dto.security.database.decorator;

import java.io.Serializable;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsBroker;
import com.greenmark.common.dto.security.database.SecurityDb;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: SecurityDbDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its SecurityDb base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class SecurityDbDecorator extends SecurityDb implements Serializable {
    public static final String CLASSNAME = "SecurityDbDecorator";
    private static final long serialVersionUID = 1L;

    public SecurityDbDecorator() {
        super();
    }

    public SecurityDbDecorator(String displaySymbol, String dbQuerySymbol, int longOrShort, int stockType) {
        super(displaySymbol, dbQuerySymbol, longOrShort, stockType);
    }

//	public SecurityDbDecorator(String xmldata) {
//		super(xmldata, trace);
//	}

    /////////////////////////////////////////////////////////////////////////////////
    public static final String parseCryptoExchangeFromSymbol(String stockSymbol) {
        if (stockSymbol != null && !stockSymbol.isEmpty()) {
            String[] parsedSymbol = stockSymbol.split(":");
            if (parsedSymbol[0] == null) {
                return "";
            } else
                return parsedSymbol[0];
        }

        return "";
    }

    /**
     * Gets the "label" for this security. A very convient string to be used in logging.
     *
     * @return the label for this security.
     */
    public final String getLabel() {
        return ("[" + this.getMarket() + ":" + this.getDbQuerySymbol() + ":" + this.getName() + "]");
    }

    /**
     * Returns a string representation of this object.
     *
     * @return String
     */
    public final String toStringShort() {
        String stb = "\n\t+=========================================================+\n" +
                "\t| STOCK: market:" + this.getMarket() + " symbol:" + this.getDbQuerySymbol() + " name:" + this.getName() + "\n" +
                "\t+=========================================================+\n";
        return (stb);
    }

    /**
     * Returns a string representation of this object.
     *
     * @return String
     */
    public final String toStringDb() {
        String stb = "+=========================================================+\n" +
                "| STOCK: " + this.getId() + ": " + this.getSecurityId() + ": " + this.getAccountId() + "\n" +
                "+=========================================================+\n";
        return (stb);
    }

    public final String getLongOrShortString() {
        if (longOrShort == GmConstants.SHORT_SECURITY)
            return "Short";
        if (longOrShort == GmConstants.LONG_SECURITY)
            return "Long";
        return "None";
    }

    public final boolean isLong() {
        return this.longOrShort == GmConstants.LONG_SECURITY;
    }

    public final boolean isShort() {
        return this.longOrShort == GmConstants.SHORT_SECURITY;
    }

    public final String getBuyOrSellString() {
        if (buyOrSell == GmConstantsBroker.TYPE_BUY)
            return "Buy";
        if (buyOrSell == GmConstantsBroker.TYPE_SELL)
            return "Sell";
        return "None";
    }

    public final boolean isBuy() {
        return this.buyOrSell == GmConstantsBroker.TYPE_BUY;
    }

    public final boolean isSell() {
        return this.buyOrSell == GmConstantsBroker.TYPE_SELL;
    }

    public String dbSummary() {
        String stb = " > > " + CLASSNAME + " :: " +
                "id [" + id + "] " +
                "securityId [" + securityId + "] " +
                "accountId [" + accountId + "] " +
                "active [" + active + "] ";
        return stb;
    }
}
