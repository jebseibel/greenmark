package com.greenmark.common.dto.security;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.greenmark.common.GmConstants;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTDbUtils;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTFormatter;
import com.greenmark.utils.UTStrings;
import com.greenmark.utils.UTUtils;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: SecurityExchangeDto</p>
 * <p>Description: This class is used to encapsulate the SECURITY_X_EXCHANGE table. </p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class SecurityExchangeDto implements Serializable {
	private static final String CLASSNAME = "SecurityExchangeDto";
	private static final long serialVersionUID = 1L;

	private long id;

	private long securityId;
	private long exchangeId;

	private LocalDateTime listingDate;
	private LocalDateTime dataStartDate;
	private LocalDateTime dataEndDate;

	private Integer inverse = 0; // set to 0 for false, 1 for true
	private Integer shortable = 0; // set to 0 for false, 1 for true

	private Float leverageRatio = 0F;

	private int active = GmConstants.OBJECT_ACTIVE;

	public SecurityExchangeDto() {
	}

//	// This is used by the PdbLoadService.
//	public SecurityExchangeDto(PdbExchangeDto newExchange, PdbSecurityDto security) {
//		this.exchangeId = newExchange.getId();
//		this.securityId = security.getId();
//		this.listingDate = security.getLaunchDate();
//	}

	// ------------------------------ XML SAVE/RETRIEVE METHODS ----------------------------------
	public SecurityExchangeDto(String xmldata) {
		try {
			id = UTXmlUtils.getXmlDataAsLong(xmldata, "ID");
			active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");

			securityId = UTXmlUtils.getXmlDataAsLong(xmldata, "SECURITY_ID");
			exchangeId = UTXmlUtils.getXmlDataAsLong(xmldata, "EXCHANGE_ID");

			if (UTUtils.isNotNorE(UTXmlUtils.getXmlData(xmldata, "LISTING_DATE")))
				listingDate = UTDatetime.fromDbString(UTXmlUtils.getXmlData(xmldata, "LISTING_DATE"));

			inverse = UTXmlUtils.getXmlDataAsInt(xmldata, "INVERSE");
			shortable = UTXmlUtils.getXmlDataAsInt(xmldata, "SHORTABLE");

			leverageRatio = UTXmlUtils.getXmlDataAsFloat(xmldata, "LEVERAGE_RATIO");

			if (UTUtils.isNotNorE(UTXmlUtils.getXmlData(xmldata, "LISTING_DATE")))
				listingDate = UTDatetime.fromDbString(UTXmlUtils.getXmlData(xmldata, "LISTING_DATE"));

			if (UTUtils.isNotNorE(UTXmlUtils.getXmlData(xmldata, "DATA_START_DATE")))
				dataStartDate = UTDatetime.fromDbString(UTXmlUtils.getXmlData(xmldata, "DATA_START_DATE"));

			if (UTUtils.isNotNorE(UTXmlUtils.getXmlData(xmldata, "DATA_END_DATE")))
				dataEndDate = UTDatetime.fromDbString(UTXmlUtils.getXmlData(xmldata, "DATA_END_DATE"));

		} catch (Exception e) {
			System.out.println("Error creating SecurityExchangeDto from XML for ID: " + id + ", message: " + e.getMessage());
		}
	}

	public final String toXml(String prefix, String endline) {
		StringBuffer stb = new StringBuffer();
		String TAB = UTDisplayFormatter.TAB;

		stb.append(prefix + TAB + "<ID>" + this.id + "</ID>" + endline);
		stb.append(prefix + TAB + "<ACTIVE>" + this.active + "</ACTIVE>" + endline);

		stb.append(prefix + TAB + "<SECURITY_ID>" + this.securityId + "</SECURITY_ID>" + endline);
		stb.append(prefix + TAB + "<EXCHANGE_ID>" + this.exchangeId + "</EXCHANGE_ID>" + endline);

		stb.append(prefix + TAB + "<INVERSE>" + this.inverse + "</INVERSE>" + endline);
		stb.append(prefix + TAB + "<SHORTABLE>" + this.shortable + "</SHORTABLE>" + endline);

		stb.append(prefix + TAB + "<LEVERAGE_RATIO>" + this.leverageRatio + "</LEVERAGE_RATIO>" + endline);

		if (this.listingDate != null)
			stb.append(prefix + TAB + "<LISTING_DATE>" + UTDatetime.toDbString(this.listingDate) + "</LISTING_DATE>" + endline);

		if (this.dataStartDate != null)
			stb.append(prefix + TAB + "<DATA_START_DATE>" + UTDatetime.toDbString(this.dataStartDate) + "</DATA_START_DATE>" + endline);

		if (this.dataEndDate != null)
			stb.append(prefix + TAB + "<DATA_END_DATE>" + UTDatetime.toDbString(this.dataEndDate) + "</DATA_END_DATE>" + endline);

		return stb.toString();
	}

	@Override
	public String toString() {
		StringBuffer stb = new StringBuffer(200);

		try {
			// stb.append(" "); // width = 1
			// stb.append(name);
			// stb.append(UTFormatter.returnEmptyString(20 - name.length()));
			//
			// stb.append(exchangeName);
			// stb.append(UTFormatter.returnEmptyString(12 - exchangeName.length()));

			stb.append(inverse);
			stb.append(UTFormatter.returnEmptyString(8 - inverse.toString().length()));

			stb.append(shortable);
			stb.append(UTFormatter.returnEmptyString(8 - UTStrings.getBooleanStringYN(UTDbUtils.getBooleanInt(shortable)).length()));

			stb.append(leverageRatio);
			stb.append(UTFormatter.returnEmptyString(10 - leverageRatio.toString().length()));

			stb.append(UTDatetime.toDateOnlyString(listingDate));
			stb.append(UTFormatter.returnEmptyString(50 - UTDatetime.toDateOnlyString(listingDate).length()));
		} catch (Exception e) {
			// System.out.println("Exception in LoadedCurrency.toString(): message: " + e.getMessage());
		}

		return stb.toString();
	}

	public String dbSummary() {
        String stb = " > > " + CLASSNAME + " :: " +
                "id [" + id + "] " +
                "active [" + active + "] ";
		return stb;
	}

	// ------------------------------ SETTER/GETTER METHODS ----------------------------------
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

	public long getSecurityId() {
		return securityId;
	}

	public void setSecurityId(long securityId) {
		this.securityId = securityId;
	}

	public long getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(long exchangeId) {
		this.exchangeId = exchangeId;
	}

	public LocalDateTime getListingDate() {
		return listingDate;
	}

	public void setListingDate(LocalDateTime listingDate) {
		this.listingDate = listingDate;
	}

	public LocalDateTime getDataStartDate() {
		return dataStartDate;
	}

	public void setDataStartDate(LocalDateTime dataStartDate) {
		this.dataStartDate = dataStartDate;
	}

	public LocalDateTime getDataEndDate() {
		return dataEndDate;
	}

	public void setDataEndDate(LocalDateTime dataEndDate) {
		this.dataEndDate = dataEndDate;
	}

	public void setInverse(Integer inverse) {
		this.inverse = inverse;
	}

	public void setShortable(Integer shortable) {
		this.shortable = shortable;
	}

	public void setLeverageRatio(Float leverageRatio) {
		this.leverageRatio = leverageRatio;
	}

	public int getInverse() {
		return inverse;
	}

	public void setInverse(int inverse) {
		this.inverse = inverse;
	}

	public int getShortable() {
		return shortable;
	}

	public void setShortable(int shortable) {
		this.shortable = shortable;
	}

	public Float getLeverageRatio() {
		return leverageRatio;
	}
}
