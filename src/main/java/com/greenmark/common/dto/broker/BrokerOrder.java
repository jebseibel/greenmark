package com.greenmark.common.dto.broker;

import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 * <p>Title: BrokerOrder</p>
 * <p>Description: This class is used to pass Order information from the Broker Service to other services.</p>

 * @author  Monte Seibel
 * @version 7.5
 * @formatter:on
**/

public class BrokerOrder {
	public static final String CLASSNAME = "BrokerOrder";

	public String symbol = ""; // added by GM
	public int m_orderId;
	public int m_clientId;
	public int m_permId;
	public String m_action;
	public int m_totalQuantity;
	public String m_orderType;
	public double m_lmtPrice;
	public double m_auxPrice;

	// extended order fields
	public String m_tif; // "Time in Force" - DAY, GTC, etc.
	public String m_ocaGroup; // one cancels all group name
	public int m_ocaType; // 1 = CANCEL_WITH_BLOCK, 2 = REDUCE_WITH_BLOCK, 3 = REDUCE_NON_BLOCK
	public String m_orderRef;
	public boolean m_transmit; // if false, order will be created but not transmited
	public int m_parentId; // Parent order Id, to associate Auto STP or TRAIL orders with the original order.
	public boolean m_blockOrder;
	public boolean m_sweepToFill;
	public int m_displaySize;
	public int m_triggerMethod; // 0=Default, 1=Double_Bid_Ask, 2=Last, 3=Double_Last, 4=Bid_Ask, 7=Last_or_Bid_Ask, 8=Mid-point
	public boolean m_outsideRth;
	public boolean m_hidden;
	public String m_goodAfterTime; // FORMAT: 20060505 08:00:00 {time zone}
	public String m_goodTillDate; // FORMAT: 20060505 08:00:00 {time zone}
	public boolean m_overridePercentageConstraints;
	public String m_rule80A; // Individual = 'I', Agency = 'A', AgentOtherMember = 'W', IndividualPTIA = 'J', AgencyPTIA = 'U', AgentOtherMemberPTIA = 'M', IndividualPT = 'K', AgencyPT = 'Y',
								// AgentOtherMemberPT = 'N'
	public boolean m_allOrNone;
	public int m_minQty;
	public double m_percentOffset; // REL orders only
	public double m_trailStopPrice; // for TRAILLIMIT orders only

	// Financial advisors only
	public String m_faGroup;
	public String m_faProfile;
	public String m_faMethod;
	public String m_faPercentage;

	// Institutional orders only
	public String m_openClose; // O=Open, C=Close
	public int m_origin; // 0=Customer, 1=Firm
	public int m_shortSaleSlot; // 1 if you hold the executedNumShares, 2 if they will be delivered from elsewhere. Only for Action="SSHORT
	public String m_designatedLocation; // set when slot=2 only.

	// SMART routing only
	public double m_discretionaryAmt;
	public boolean m_eTradeOnly;
	public boolean m_firmQuoteOnly;
	public double m_nbboPriceCap;

	// BOX or VOL ORDERS ONLY
	public int m_auctionStrategy; // 1=AUCTION_MATCH, 2=AUCTION_IMPROVEMENT, 3=AUCTION_TRANSPARENT

	// BOX ORDERS ONLY
	public double m_startingPrice;
	public double m_stockRefPrice;
	public double m_delta;

	// pegged to securityPrice or VOL orders
	public double m_stockRangeLower;
	public double m_stockRangeUpper;

	// VOLATILITY ORDERS ONLY
	public double m_volatility;
	public int m_volatilityType; // 1=daily, 2=annual
	public int m_continuousUpdate;
	public int m_referencePriceType; // 1=Average, 2 = BidOrAsk
	public String m_deltaNeutralOrderType;
	public double m_deltaNeutralAuxPrice;

	// COMBO ORDERS ONLY
	public double m_basisPoints; // EFP orders only
	public int m_basisPointsType; // EFP orders only

	// SCALE ORDERS ONLY
	public int m_scaleInitLevelSize;
	public int m_scaleSubsLevelSize;
	public double m_scalePriceIncrement;

	// Clearing info
	public String m_account; // IB accountBroker
	public String m_settlingFirm;
	public String m_clearingAccount; // True beneficiary of the order
	public String m_clearingIntent; // "" (Default), "IB", "Away", "PTA" (PostTrade)

	// What-if
	public boolean m_whatIf;

	public BrokerOrder() {
		// default constructor
	}

	public BrokerOrder(String xmldata) {
		this();

		try {
			this.symbol = UTXmlUtils.getXmlData(xmldata, "SYMBOL");
			this.m_orderId = UTXmlUtils.getXmlDataAsInt(xmldata, "M_ORDERID");
			this.m_action = UTXmlUtils.getXmlData(xmldata, "M_ACTION");
			this.m_totalQuantity = UTXmlUtils.getXmlDataAsInt(xmldata, "M_TOTALQUANTITY");
			this.m_lmtPrice = UTXmlUtils.getXmlDataAsDouble(xmldata, "M_LMTPRICE");

			this.m_auxPrice = UTXmlUtils.getXmlDataAsDouble(xmldata, "M_AUXPRICE");
			this.m_orderType = UTXmlUtils.getXmlData(xmldata, "M_ORDERTYPE");
			this.m_permId = UTXmlUtils.getXmlDataAsInt(xmldata, "M_PERMID");
			this.m_clientId = UTXmlUtils.getXmlDataAsInt(xmldata, "M_CLIENTID");
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
                prefix + UTDisplayFormatter.TAB + "<SYMBOL>" + symbol + "</SYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_ORDERID>" + m_orderId + "</M_ORDERID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_ACTION>" + m_action + "</M_ACTION>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_TOTALQUANTITY>" + m_totalQuantity + "</M_TOTALQUANTITY>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_LMTPRICE>" + m_lmtPrice + "</M_LMTPRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_AUXPRICE>" + m_auxPrice + "</M_AUXPRICE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_ORDERTYPE>" + m_orderType + "</M_ORDERTYPE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_PERMID>" + m_permId + "</M_PERMID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_CLIENTID>" + m_clientId + "</M_CLIENTID>" + endline +
                "</" + CLASSNAME + ">" + endline;
		return stb;
	}

	@Deprecated
	public String toXmlShort(String prefix, String endline) {

        String stb = "<" + CLASSNAME + ">" + endline +
                prefix + UTDisplayFormatter.TAB + "<SYMBOL>" + symbol + "</SYMBOL>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_ORDERID>" + m_orderId + "</M_ORDERID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_ACTION>" + m_action + "</M_ACTION>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_TOTALQUANTITY>" + m_totalQuantity + "</M_TOTALQUANTITY>" + endline +
                prefix + UTDisplayFormatter.TAB + "<M_LMTPRICE>" + m_lmtPrice + "</M_LMTPRICE>" + endline +
                // stb.append( prefix + UTDisplayFormatter.TAB + "<M_AUXPRICE>" + m_auxPrice + "</M_AUXPRICE>" + endline );
                prefix + UTDisplayFormatter.TAB + "<M_ORDERTYPE>" + m_orderType + "</M_ORDERTYPE>" + endline +
                // stb.append( prefix + UTDisplayFormatter.TAB + "<M_PERMID>" + m_permId + "</M_PERMID>" + endline );
                // stb.append( prefix + UTDisplayFormatter.TAB + "<M_CLIENTID>" + m_clientId + "</M_CLIENTID>" + endline );

                "</" + CLASSNAME + ">" + endline;
		return stb;
	}

	public String toString() {
		return toString(UTDisplayFormatter.TAB, "\n");
	}

	public String toString(String prefix, String endline) {

        String stb = prefix + "-----------------------------------------------" + endline +
                prefix + CLASSNAME + " [" + symbol + "]" + endline +
                prefix + " . . . m_orderId=   [" + m_orderId + "]" + endline +
                prefix + " . . . m_clientId=   [" + m_clientId + "]" + endline +
                prefix + " . . . m_permId=   [" + m_permId + "]" + endline +
                prefix + " . . . m_action=   [" + m_action + "]" + endline +
                prefix + " . . . m_totalQuantity=   [" + m_totalQuantity + "]" + endline +
                prefix + " . . . m_orderType=   [" + m_orderType + "]" + endline +
                prefix + " . . . m_lmtPrice=   [" + m_lmtPrice + "]" + endline +
                prefix + " . . . m_auxPrice=   [" + m_auxPrice + "]" + endline +
                prefix + "-----------------------------------------------" + endline;
		return stb;
	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getM_orderId() {
		return m_orderId;
	}

	public void setM_orderId(int m_orderId) {
		this.m_orderId = m_orderId;
	}

	public int getM_clientId() {
		return m_clientId;
	}

	public void setM_clientId(int m_clientId) {
		this.m_clientId = m_clientId;
	}

	public int getM_permId() {
		return m_permId;
	}

	public void setM_permId(int m_permId) {
		this.m_permId = m_permId;
	}

	public String getM_action() {
		return m_action;
	}

	public void setM_action(String m_action) {
		this.m_action = m_action;
	}

	public int getM_totalQuantity() {
		return m_totalQuantity;
	}

	public void setM_totalQuantity(int m_totalQuantity) {
		this.m_totalQuantity = m_totalQuantity;
	}

	public String getM_orderType() {
		return m_orderType;
	}

	public void setM_orderType(String m_orderType) {
		this.m_orderType = m_orderType;
	}

	public double getM_lmtPrice() {
		return m_lmtPrice;
	}

	public void setM_lmtPrice(double m_lmtPrice) {
		this.m_lmtPrice = m_lmtPrice;
	}

	public double getM_auxPrice() {
		return m_auxPrice;
	}

	public void setM_auxPrice(double m_auxPrice) {
		this.m_auxPrice = m_auxPrice;
	}

	public String getM_tif() {
		return m_tif;
	}

	public void setM_tif(String m_tif) {
		this.m_tif = m_tif;
	}

	public String getM_ocaGroup() {
		return m_ocaGroup;
	}

	public void setM_ocaGroup(String m_ocaGroup) {
		this.m_ocaGroup = m_ocaGroup;
	}

	public int getM_ocaType() {
		return m_ocaType;
	}

	public void setM_ocaType(int m_ocaType) {
		this.m_ocaType = m_ocaType;
	}

	public String getM_orderRef() {
		return m_orderRef;
	}

	public void setM_orderRef(String m_orderRef) {
		this.m_orderRef = m_orderRef;
	}

	public boolean isM_transmit() {
		return m_transmit;
	}

	public void setM_transmit(boolean m_transmit) {
		this.m_transmit = m_transmit;
	}

	public int getM_parentId() {
		return m_parentId;
	}

	public void setM_parentId(int m_parentId) {
		this.m_parentId = m_parentId;
	}

	public boolean isM_blockOrder() {
		return m_blockOrder;
	}

	public void setM_blockOrder(boolean m_blockOrder) {
		this.m_blockOrder = m_blockOrder;
	}

	public boolean isM_sweepToFill() {
		return m_sweepToFill;
	}

	public void setM_sweepToFill(boolean m_sweepToFill) {
		this.m_sweepToFill = m_sweepToFill;
	}

	public int getM_displaySize() {
		return m_displaySize;
	}

	public void setM_displaySize(int m_displaySize) {
		this.m_displaySize = m_displaySize;
	}

	public int getM_triggerMethod() {
		return m_triggerMethod;
	}

	public void setM_triggerMethod(int m_triggerMethod) {
		this.m_triggerMethod = m_triggerMethod;
	}

	public boolean isM_outsideRth() {
		return m_outsideRth;
	}

	public void setM_outsideRth(boolean m_outsideRth) {
		this.m_outsideRth = m_outsideRth;
	}

	public boolean isM_hidden() {
		return m_hidden;
	}

	public void setM_hidden(boolean m_hidden) {
		this.m_hidden = m_hidden;
	}

	public String getM_goodAfterTime() {
		return m_goodAfterTime;
	}

	public void setM_goodAfterTime(String m_goodAfterTime) {
		this.m_goodAfterTime = m_goodAfterTime;
	}

	public String getM_goodTillDate() {
		return m_goodTillDate;
	}

	public void setM_goodTillDate(String m_goodTillDate) {
		this.m_goodTillDate = m_goodTillDate;
	}

	public boolean isM_overridePercentageConstraints() {
		return m_overridePercentageConstraints;
	}

	public void setM_overridePercentageConstraints(boolean m_overridePercentageConstraints) {
		this.m_overridePercentageConstraints = m_overridePercentageConstraints;
	}

	public String getM_rule80A() {
		return m_rule80A;
	}

	public void setM_rule80A(String m_rule80A) {
		this.m_rule80A = m_rule80A;
	}

	public boolean isM_allOrNone() {
		return m_allOrNone;
	}

	public void setM_allOrNone(boolean m_allOrNone) {
		this.m_allOrNone = m_allOrNone;
	}

	public int getM_minQty() {
		return m_minQty;
	}

	public void setM_minQty(int m_minQty) {
		this.m_minQty = m_minQty;
	}

	public double getM_percentOffset() {
		return m_percentOffset;
	}

	public void setM_percentOffset(double m_percentOffset) {
		this.m_percentOffset = m_percentOffset;
	}

	public double getM_trailStopPrice() {
		return m_trailStopPrice;
	}

	public void setM_trailStopPrice(double m_trailStopPrice) {
		this.m_trailStopPrice = m_trailStopPrice;
	}

	public String getM_faGroup() {
		return m_faGroup;
	}

	public void setM_faGroup(String m_faGroup) {
		this.m_faGroup = m_faGroup;
	}

	public String getM_faProfile() {
		return m_faProfile;
	}

	public void setM_faProfile(String m_faProfile) {
		this.m_faProfile = m_faProfile;
	}

	public String getM_faMethod() {
		return m_faMethod;
	}

	public void setM_faMethod(String m_faMethod) {
		this.m_faMethod = m_faMethod;
	}

	public String getM_faPercentage() {
		return m_faPercentage;
	}

	public void setM_faPercentage(String m_faPercentage) {
		this.m_faPercentage = m_faPercentage;
	}

	public String getM_openClose() {
		return m_openClose;
	}

	public void setM_openClose(String m_openClose) {
		this.m_openClose = m_openClose;
	}

	public int getM_origin() {
		return m_origin;
	}

	public void setM_origin(int m_origin) {
		this.m_origin = m_origin;
	}

	public int getM_shortSaleSlot() {
		return m_shortSaleSlot;
	}

	public void setM_shortSaleSlot(int m_shortSaleSlot) {
		this.m_shortSaleSlot = m_shortSaleSlot;
	}

	public String getM_designatedLocation() {
		return m_designatedLocation;
	}

	public void setM_designatedLocation(String m_designatedLocation) {
		this.m_designatedLocation = m_designatedLocation;
	}

	public double getM_discretionaryAmt() {
		return m_discretionaryAmt;
	}

	public void setM_discretionaryAmt(double m_discretionaryAmt) {
		this.m_discretionaryAmt = m_discretionaryAmt;
	}

	public boolean isM_eTradeOnly() {
		return m_eTradeOnly;
	}

	public void setM_eTradeOnly(boolean m_eTradeOnly) {
		this.m_eTradeOnly = m_eTradeOnly;
	}

	public boolean isM_firmQuoteOnly() {
		return m_firmQuoteOnly;
	}

	public void setM_firmQuoteOnly(boolean m_firmQuoteOnly) {
		this.m_firmQuoteOnly = m_firmQuoteOnly;
	}

	public double getM_nbboPriceCap() {
		return m_nbboPriceCap;
	}

	public void setM_nbboPriceCap(double m_nbboPriceCap) {
		this.m_nbboPriceCap = m_nbboPriceCap;
	}

	public int getM_auctionStrategy() {
		return m_auctionStrategy;
	}

	public void setM_auctionStrategy(int m_auctionStrategy) {
		this.m_auctionStrategy = m_auctionStrategy;
	}

	public double getM_startingPrice() {
		return m_startingPrice;
	}

	public void setM_startingPrice(double m_startingPrice) {
		this.m_startingPrice = m_startingPrice;
	}

	public double getM_stockRefPrice() {
		return m_stockRefPrice;
	}

	public void setM_stockRefPrice(double m_stockRefPrice) {
		this.m_stockRefPrice = m_stockRefPrice;
	}

	public double getM_delta() {
		return m_delta;
	}

	public void setM_delta(double m_delta) {
		this.m_delta = m_delta;
	}

	public double getM_stockRangeLower() {
		return m_stockRangeLower;
	}

	public void setM_stockRangeLower(double m_stockRangeLower) {
		this.m_stockRangeLower = m_stockRangeLower;
	}

	public double getM_stockRangeUpper() {
		return m_stockRangeUpper;
	}

	public void setM_stockRangeUpper(double m_stockRangeUpper) {
		this.m_stockRangeUpper = m_stockRangeUpper;
	}

	public double getM_volatility() {
		return m_volatility;
	}

	public void setM_volatility(double m_volatility) {
		this.m_volatility = m_volatility;
	}

	public int getM_volatilityType() {
		return m_volatilityType;
	}

	public void setM_volatilityType(int m_volatilityType) {
		this.m_volatilityType = m_volatilityType;
	}

	public int getM_continuousUpdate() {
		return m_continuousUpdate;
	}

	public void setM_continuousUpdate(int m_continuousUpdate) {
		this.m_continuousUpdate = m_continuousUpdate;
	}

	public int getM_referencePriceType() {
		return m_referencePriceType;
	}

	public void setM_referencePriceType(int m_referencePriceType) {
		this.m_referencePriceType = m_referencePriceType;
	}

	public String getM_deltaNeutralOrderType() {
		return m_deltaNeutralOrderType;
	}

	public void setM_deltaNeutralOrderType(String m_deltaNeutralOrderType) {
		this.m_deltaNeutralOrderType = m_deltaNeutralOrderType;
	}

	public double getM_deltaNeutralAuxPrice() {
		return m_deltaNeutralAuxPrice;
	}

	public void setM_deltaNeutralAuxPrice(double m_deltaNeutralAuxPrice) {
		this.m_deltaNeutralAuxPrice = m_deltaNeutralAuxPrice;
	}

	public double getM_basisPoints() {
		return m_basisPoints;
	}

	public void setM_basisPoints(double m_basisPoints) {
		this.m_basisPoints = m_basisPoints;
	}

	public int getM_basisPointsType() {
		return m_basisPointsType;
	}

	public void setM_basisPointsType(int m_basisPointsType) {
		this.m_basisPointsType = m_basisPointsType;
	}

	public int getM_scaleInitLevelSize() {
		return m_scaleInitLevelSize;
	}

	public void setM_scaleInitLevelSize(int m_scaleInitLevelSize) {
		this.m_scaleInitLevelSize = m_scaleInitLevelSize;
	}

	public int getM_scaleSubsLevelSize() {
		return m_scaleSubsLevelSize;
	}

	public void setM_scaleSubsLevelSize(int m_scaleSubsLevelSize) {
		this.m_scaleSubsLevelSize = m_scaleSubsLevelSize;
	}

	public double getM_scalePriceIncrement() {
		return m_scalePriceIncrement;
	}

	public void setM_scalePriceIncrement(double m_scalePriceIncrement) {
		this.m_scalePriceIncrement = m_scalePriceIncrement;
	}

	public String getM_account() {
		return m_account;
	}

	public void setM_account(String m_account) {
		this.m_account = m_account;
	}

	public String getM_settlingFirm() {
		return m_settlingFirm;
	}

	public void setM_settlingFirm(String m_settlingFirm) {
		this.m_settlingFirm = m_settlingFirm;
	}

	public String getM_clearingAccount() {
		return m_clearingAccount;
	}

	public void setM_clearingAccount(String m_clearingAccount) {
		this.m_clearingAccount = m_clearingAccount;
	}

	public String getM_clearingIntent() {
		return m_clearingIntent;
	}

	public void setM_clearingIntent(String m_clearingIntent) {
		this.m_clearingIntent = m_clearingIntent;
	}

	public boolean isM_whatIf() {
		return m_whatIf;
	}

	public void setM_whatIf(boolean m_whatIf) {
		this.m_whatIf = m_whatIf;
	}
}
