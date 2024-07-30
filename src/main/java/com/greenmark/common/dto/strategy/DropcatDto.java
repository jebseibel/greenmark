package com.greenmark.common.dto.strategy;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsStrategy;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTUtils;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: DropcatDto</p>
 * <p>Description: This DTO is for the results database position_dropcat table. 
 *  
 *      A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 *    DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class DropcatDto implements Serializable {
	public static final String CLASSNAME = "DropcatDto";
	private static final long serialVersionUID = 1L;

	protected long id = 0;
	protected int active = GmConstants.OBJECT_ACTIVE;
	protected long positionId = 0;

	protected LocalDateTime trappedDatetime;
	protected float preTrapClosePrice; // This is the last executedPrice before the stock started falling/rising.
	protected float trapTriggeredPainPrice; // This is the executedPrice that triggered the dropcat because we pained out originally.
	protected float trapPrice; // This is the executedPrice after we crossed the threshold, and found the largest loss (kept riding the wave).

	protected float trapPercentLoss;
	protected float trapPercentLossThreshold;

	protected float additionalPainPrice;
	protected float additionalPainPriceThreshold;

	protected float exitMin15Stochk;
	protected float exitMin15StochkThreshold;
	protected LocalDateTime exitMin15Datetime;

	protected float sellPlacedMin5Stochk;
	protected float sellPlacedMin5StochkThreshold;

	protected LocalDateTime sellPlacedDatetime;
	protected float sellPlacedSellPrice;
	protected float sellPlacedCurrentPrice;
	protected boolean sellPlacedEndOfDay;

	protected boolean painedOutPosition;
	protected LocalDateTime sellExecutedDatetime;
	protected float sellExecutedPrice;

	protected boolean doubledDown;
	protected int doubleDownNumShares;
	protected LocalDateTime doubleDownBuyDatetime;
	protected float doubleDownBuyPrice;
	protected float doubleDownMin5Stochk;

	protected int longOrShortStock; // This is for the website only. Decorator calculations need to know if the stock was long/short
	protected int dropcatStatus = GmConstantsStrategy.DROPCAT_STATUS_TRAP_OPENED;

	public DropcatDto() {
	}

	public DropcatDto(String xmldata) {

		try {
			id = UTXmlUtils.getXmlDataAsLong(xmldata, "ID");
			positionId = UTXmlUtils.getXmlDataAsLong(xmldata, "POSITION_ID");

			String this_trappedDatetime = UTXmlUtils.getXmlData(xmldata, "TRAPPED_DATE");
			if (UTUtils.isNotNorE(this_trappedDatetime))
				trappedDatetime = UTDatetime.fromDbString(this_trappedDatetime);

			trapPercentLoss = UTXmlUtils.getXmlDataAsFloat(xmldata, "TRAP_PERCENT_LOSS");
			trapPercentLossThreshold = UTXmlUtils.getXmlDataAsFloat(xmldata, "TRAP_PERCENT_LOSS_THRESH");
			trapPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "TRAP_PRICE");
			additionalPainPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "ADDTL_PAIN_PRICE");
			additionalPainPriceThreshold = UTXmlUtils.getXmlDataAsFloat(xmldata, "ADDTL_PAIN_PRICE_THRESH");
			exitMin15Stochk = UTXmlUtils.getXmlDataAsFloat(xmldata, "EXITMIN15_STOCHK");
			exitMin15StochkThreshold = UTXmlUtils.getXmlDataAsFloat(xmldata, "EXITMIN15_STOCHK_THRESH");

			String this_exitMin15Datetime = UTXmlUtils.getXmlData(xmldata, "EXITMIN15_DATE");
			if (UTUtils.isNotNorE(this_exitMin15Datetime))
				exitMin15Datetime = UTDatetime.fromDbString(this_exitMin15Datetime);

			sellPlacedMin5Stochk = UTXmlUtils.getXmlDataAsFloat(xmldata, "SELLPLACED_MIN5_STOCHK");
			sellPlacedMin5StochkThreshold = UTXmlUtils.getXmlDataAsFloat(xmldata, "SELLPLACED_MIN5_STOCHK_THRESH");

			String this_sellPlacedDatetime = UTXmlUtils.getXmlData(xmldata, "SELLPLACED_DATE");
			if (UTUtils.isNotNorE(this_sellPlacedDatetime))
				sellPlacedDatetime = UTDatetime.fromDbString(this_sellPlacedDatetime);

			sellPlacedSellPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "SELLPLACED_SELL_PRICE");
			sellPlacedCurrentPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "SELLPLACED_CURRENT_PRICE");

			String sellPlacedEndOfDayStr = UTXmlUtils.getXmlData(xmldata, "SELLPLACED_END_OF_DAY");
			if (sellPlacedEndOfDayStr.equals("false"))
				sellPlacedEndOfDay = false;
			else
				sellPlacedEndOfDay = true;

			String painedOutPositionStr = UTXmlUtils.getXmlData(xmldata, "PAINED_OUT_POSITION");
			if (painedOutPositionStr.equals("false"))
				painedOutPosition = false;
			else
				painedOutPosition = true;

			String this_sellExecutedDatetime = UTXmlUtils.getXmlData(xmldata, "SELLEXECUTED_DATE");
			if (UTUtils.isNotNorE(this_sellExecutedDatetime))
				sellExecutedDatetime = UTDatetime.fromDbString(this_sellExecutedDatetime);

			sellExecutedPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "SELLEXECUTED_PRICE");

			String doubledDownStr = UTXmlUtils.getXmlData(xmldata, "DOUBLED_DOWN");
			if (doubledDownStr.equals("false"))
				doubledDown = false;
			else
				doubledDown = true;

			doubleDownNumShares = UTXmlUtils.getXmlDataAsInt(xmldata, "DOUBLEDOWN_NUM_SHARES");

			String this_doubleDownBuyDatetime = UTXmlUtils.getXmlData(xmldata, "DOUBLEDOWN_BUY_DATETIME");
			if (UTUtils.isNotNorE(this_doubleDownBuyDatetime))
				doubleDownBuyDatetime = UTDatetime.fromDbString(this_doubleDownBuyDatetime);

			doubleDownBuyPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "DOUBLEDOWN_BUY_PRICE");
			doubleDownMin5Stochk = UTXmlUtils.getXmlDataAsFloat(xmldata, "DOUBLEDOWN_MIN5_STOCHK");
			dropcatStatus = UTXmlUtils.getXmlDataAsInt(xmldata, "DROPCAT_STATUS");
		} catch (Exception e) {
			System.out.println("Error creating DropcatDtoDecorator.constructor, message: " + e.getMessage());
		}
	}

	public String toXml(String prefix, String endline) {
		String formattedSellDate = "";
		if (sellPlacedDatetime != null)
			formattedSellDate = UTDatetime.toDbString(sellPlacedDatetime);

		String formattedTrappedDatetime = "";
		if (trappedDatetime != null)
			formattedTrappedDatetime = UTDatetime.toDbString(trappedDatetime);

		String formattedExitMin15Datetime = "";
		if (exitMin15Datetime != null)
			formattedExitMin15Datetime = UTDatetime.toDbString(exitMin15Datetime);

		String formattedSellExecutedDatetime = "";
		if (sellExecutedDatetime != null)
			formattedSellExecutedDatetime = UTDatetime.toDbString(sellExecutedDatetime);

		String formattedDoubleDownBuyDatetime = "";
		if (doubleDownBuyDatetime != null)
			formattedDoubleDownBuyDatetime = UTDatetime.toDbString(doubleDownBuyDatetime);

		StringBuffer stb = new StringBuffer(250);
		stb.append(prefix + "<DROPCAT_INFO>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<ID>" + this.id + "</ID>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<POSITION_ID>" + positionId + "</POSITION_ID>" + endline);

		stb.append(prefix + UTDisplayFormatter.TAB + "<TRAPPED_DATE>" + formattedTrappedDatetime + "</TRAPPED_DATE>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<TRAP_PERCENT_LOSS>" + trapPercentLoss + "</TRAP_PERCENT_LOSS>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<TRAP_PERCENT_LOSS_THRESH>" + trapPercentLossThreshold + "</TRAP_PERCENT_LOSS_THRESH>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<TRAP_PRICE>" + trapPrice + "</TRAP_PRICE>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<ADDTL_PAIN_PRICE>" + additionalPainPrice + "</ADDTL_PAIN_PRICE>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<ADDTL_PAIN_PRICE_THRESH>" + additionalPainPriceThreshold + "</ADDTL_PAIN_PRICE_THRESH>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<EXITMIN15_STOCHK>" + exitMin15Stochk + "</EXITMIN15_STOCHK>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<EXITMIN15_STOCHK_THRESH>" + exitMin15StochkThreshold + "</EXITMIN15_STOCHK_THRESH>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<EXITMIN15_DATE>" + formattedExitMin15Datetime + "</EXITMIN15_DATE>" + endline);

		stb.append(prefix + UTDisplayFormatter.TAB + "<SELLPLACED_MIN5_STOCHK>" + sellPlacedMin5Stochk + "</SELLPLACED_MIN5_STOCHK>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<SELLPLACED_MIN5_STOCHK_THRESH>" + sellPlacedMin5StochkThreshold + "</SELLPLACED_MIN5_STOCHK_THRESH>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<SELLPLACED_DATE>" + formattedSellDate + "</SELLPLACED_DATE>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<SELLPLACED_SELL_PRICE>" + sellPlacedSellPrice + "</SELLPLACED_SELL_PRICE>" + endline);

		stb.append(prefix + UTDisplayFormatter.TAB + "<SELLPLACED_CURRENT_PRICE>" + sellPlacedCurrentPrice + "</SELLPLACED_CURRENT_PRICE>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<SELLPLACED_END_OF_DAY>" + sellPlacedEndOfDay + "</SELLPLACED_END_OF_DAY>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<PAINED_OUT_POSITION>" + painedOutPosition + "</PAINED_OUT_POSITION>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<SELLEXECUTED_DATE>" + formattedSellExecutedDatetime + "</SELLEXECUTED_DATE>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<SELLEXECUTED_PRICE>" + sellExecutedPrice + "</SELLEXECUTED_PRICE>" + endline);

		stb.append(prefix + UTDisplayFormatter.TAB + "<DOUBLED_DOWN>" + doubledDown + "</DOUBLED_DOWN>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<DOUBLEDOWN_BUY_DATETIME>" + formattedDoubleDownBuyDatetime + "</DOUBLEDOWN_BUY_DATETIME>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<DOUBLEDOWN_BUY_PRICE>" + doubleDownBuyPrice + "</DOUBLEDOWN_BUY_PRICE>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<DOUBLEDOWN_MIN5_STOCHK>" + doubleDownMin5Stochk + "</DOUBLEDOWN_MIN5_STOCHK>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<DROPCAT_STATUS>" + dropcatStatus + "</DROPCAT_STATUS>" + endline);

		stb.append(prefix + "</DROPCAT_INFO>" + endline);
		return stb.toString();
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

	public long getPositionId() {
		return positionId;
	}

	public void setPositionId(long positionId) {
		this.positionId = positionId;
	}

	public LocalDateTime getTrappedDatetime() {
		return trappedDatetime;
	}

	public void setTrappedDatetime(LocalDateTime trappedDatetime) {
		this.trappedDatetime = trappedDatetime;
	}

	public float getPreTrapClosePrice() {
		return preTrapClosePrice;
	}

	public void setPreTrapClosePrice(float preTrapClosePrice) {
		this.preTrapClosePrice = preTrapClosePrice;
	}

	public float getTrapTriggeredPainPrice() {
		return trapTriggeredPainPrice;
	}

	public void setTrapTriggeredPainPrice(float trapTriggeredPainPrice) {
		this.trapTriggeredPainPrice = trapTriggeredPainPrice;
	}

	public float getTrapPrice() {
		return trapPrice;
	}

	public void setTrapPrice(float trapPrice) {
		this.trapPrice = trapPrice;
	}

	public float getTrapPercentLoss() {
		return trapPercentLoss;
	}

	public void setTrapPercentLoss(float trapPercentLoss) {
		this.trapPercentLoss = trapPercentLoss;
	}

	public float getTrapPercentLossThreshold() {
		return trapPercentLossThreshold;
	}

	public void setTrapPercentLossThreshold(float trapPercentLossThreshold) {
		this.trapPercentLossThreshold = trapPercentLossThreshold;
	}

	public float getAdditionalPainPrice() {
		return additionalPainPrice;
	}

	public void setAdditionalPainPrice(float additionalPainPrice) {
		this.additionalPainPrice = additionalPainPrice;
	}

	public float getAdditionalPainPriceThreshold() {
		return additionalPainPriceThreshold;
	}

	public void setAdditionalPainPriceThreshold(float additionalPainPriceThreshold) {
		this.additionalPainPriceThreshold = additionalPainPriceThreshold;
	}

	public float getExitMin15Stochk() {
		return exitMin15Stochk;
	}

	public void setExitMin15Stochk(float exitMin15Stochk) {
		this.exitMin15Stochk = exitMin15Stochk;
	}

	public float getExitMin15StochkThreshold() {
		return exitMin15StochkThreshold;
	}

	public void setExitMin15StochkThreshold(float exitMin15StochkThreshold) {
		this.exitMin15StochkThreshold = exitMin15StochkThreshold;
	}

	public LocalDateTime getExitMin15Datetime() {
		return exitMin15Datetime;
	}

	public void setExitMin15Datetime(LocalDateTime exitMin15Datetime) {
		this.exitMin15Datetime = exitMin15Datetime;
	}

	public float getSellPlacedMin5Stochk() {
		return sellPlacedMin5Stochk;
	}

	public void setSellPlacedMin5Stochk(float sellPlacedMin5Stochk) {
		this.sellPlacedMin5Stochk = sellPlacedMin5Stochk;
	}

	public float getSellPlacedMin5StochkThreshold() {
		return sellPlacedMin5StochkThreshold;
	}

	public void setSellPlacedMin5StochkThreshold(float sellPlacedMin5StochkThreshold) {
		this.sellPlacedMin5StochkThreshold = sellPlacedMin5StochkThreshold;
	}

	public LocalDateTime getSellPlacedDatetime() {
		return sellPlacedDatetime;
	}

	public void setSellPlacedDatetime(LocalDateTime sellPlacedDatetime) {
		this.sellPlacedDatetime = sellPlacedDatetime;
	}

	public float getSellPlacedSellPrice() {
		return sellPlacedSellPrice;
	}

	public void setSellPlacedSellPrice(float sellPlacedSellPrice) {
		this.sellPlacedSellPrice = sellPlacedSellPrice;
	}

	public float getSellPlacedCurrentPrice() {
		return sellPlacedCurrentPrice;
	}

	public void setSellPlacedCurrentPrice(float sellPlacedCurrentPrice) {
		this.sellPlacedCurrentPrice = sellPlacedCurrentPrice;
	}

	public boolean isSellPlacedEndOfDay() {
		return sellPlacedEndOfDay;
	}

	public void setSellPlacedEndOfDay(boolean sellPlacedEndOfDay) {
		this.sellPlacedEndOfDay = sellPlacedEndOfDay;
	}

	public boolean isPainedOutPosition() {
		return painedOutPosition;
	}

	public void setPainedOutPosition(boolean painedOutPosition) {
		this.painedOutPosition = painedOutPosition;
	}

	public LocalDateTime getSellExecutedDatetime() {
		return sellExecutedDatetime;
	}

	public void setSellExecutedDatetime(LocalDateTime sellExecutedDatetime) {
		this.sellExecutedDatetime = sellExecutedDatetime;
	}

	public float getSellExecutedPrice() {
		return sellExecutedPrice;
	}

	public void setSellExecutedPrice(float sellExecutedPrice) {
		this.sellExecutedPrice = sellExecutedPrice;
	}

	public boolean isDoubledDown() {
		return doubledDown;
	}

	public void setDoubledDown(boolean doubledDown) {
		this.doubledDown = doubledDown;
	}

	public int getDoubleDownNumShares() {
		return doubleDownNumShares;
	}

	public void setDoubleDownNumShares(int doubleDownNumShares) {
		this.doubleDownNumShares = doubleDownNumShares;
	}

	public LocalDateTime getDoubleDownBuyDatetime() {
		return doubleDownBuyDatetime;
	}

	public void setDoubleDownBuyDatetime(LocalDateTime doubleDownBuyDatetime) {
		this.doubleDownBuyDatetime = doubleDownBuyDatetime;
	}

	public float getDoubleDownBuyPrice() {
		return doubleDownBuyPrice;
	}

	public void setDoubleDownBuyPrice(float doubleDownBuyPrice) {
		this.doubleDownBuyPrice = doubleDownBuyPrice;
	}

	public float getDoubleDownMin5Stochk() {
		return doubleDownMin5Stochk;
	}

	public void setDoubleDownMin5Stochk(float doubleDownMin5Stochk) {
		this.doubleDownMin5Stochk = doubleDownMin5Stochk;
	}

	public int getLongOrShortStock() {
		return longOrShortStock;
	}

	public void setLongOrShortStock(int longOrShortStock) {
		this.longOrShortStock = longOrShortStock;
	}

	public int getDropcatStatus() {
		return dropcatStatus;
	}

	public void setDropcatStatus(int dropcatStatus) {
		this.dropcatStatus = dropcatStatus;
	}
}
