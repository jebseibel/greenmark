package com.greenmark.common.dto.parameters;

import java.time.LocalDateTime;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 * <p>Title: LagPeriodsPrice</p>
 * <p>Description: Contains more information about how we calculate the lag periods for:  orderEntryPrice and Support/Resistance.</p>
 * 
 *  This DTO is for the results database orders table.  A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 * DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 * @author  Monte Seibel
 * @version 7.5
 * @formatter:on
**/

public class LagPeriodsPrice {
	public static final String CLASSNAME = "LagPeriodsPrice";

	private Float entryPrice = 0F;
	private LocalDateTime entryPriceDatetime;

	private boolean calcPriceHigher = false;

	private boolean success = true;
	private String errorMessage = "";

	public LagPeriodsPrice() {
		// Default constructor
	}

	// This is used for market orders where we're simply setting a price and not calculating using lag periods.
	public LagPeriodsPrice(float entryPrice, LocalDateTime entryPriceDatetime) {
		this.success = true;
		this.entryPrice = entryPrice;
		this.entryPriceDatetime = entryPriceDatetime;
	}

	public LagPeriodsPrice(String errorMessage) {
		this.success = false;
		this.errorMessage = errorMessage;
	}

	// ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
	public Float getEntryPrice() {
		return entryPrice;
	}

	public void setEntryPrice(Float entryPrice) {
		this.entryPrice = entryPrice;
	}

	public LocalDateTime getEntryPriceDatetime() {
		return entryPriceDatetime;
	}

	public void setEntryPriceDatetime(LocalDateTime entryPriceDatetime) {
		this.entryPriceDatetime = entryPriceDatetime;
	}

	public boolean isCalcPriceHigher() {
		return calcPriceHigher;
	}

	public void setCalcPriceHigher(boolean calcPriceHigher) {
		this.calcPriceHigher = calcPriceHigher;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
