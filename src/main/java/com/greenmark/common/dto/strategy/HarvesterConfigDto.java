package com.greenmark.common.dto.strategy;

import java.io.Serializable;

import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: HarvesterConfigDto</p>
  * <p>Description: This DTO is for the Harvester Configuration set by the user.  
 *
 *      A base DTO is for a single database table and only has:
 *      1. Member variables that map to the database table columns.
 *      2. XML save/restore if the DTO is used by the trading client to store its state every minute.
 *      3. Setters/Getters for the member variables.
 *    DTO classes do not have any additional methods, these should be in its Decorator subclass.</p>
 *
 * @author Monte Seibel
 * @version 8
 * @formatter:on
 */

public class HarvesterConfigDto extends HarvesterIndicatorsDto implements Serializable {
	public static final String CLASSNAME = "HarvesterConfigDto";
	private static final long serialVersionUID = 1L;

	protected String serviceTime = "";
	protected String emailRecipients = "";

	protected Boolean longHarvesterOn = false;
	protected Float longMinSecurityPrice = 0F;
	protected Float longMaxSecurityPrice = 0F;
	protected Float longMinSecurityVolume = 0F;
	protected Integer longMomentumThreshold = 0;

	protected Boolean shortHarvesterOn = false;
	protected Float shortMinSecurityPrice = 0F;
	protected Float shortMaxSecurityPrice = 0F;
	protected Float shortMinSecurityVolume = 0F;
	protected Integer shortMomentumThreshold = 0;

	public HarvesterConfigDto() {
	}

	// ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
	public HarvesterConfigDto(String xmldata) {
		super(xmldata);

		this.serviceTime = UTXmlUtils.getXmlData(xmldata, "SERVICE_TIME");
		this.emailRecipients = UTXmlUtils.getXmlData(xmldata, "EMAIL_RECIPIENTS");

		this.longHarvesterOn = UTXmlUtils.getXmlDataAsBoolean(xmldata, "LONG_HARVESTER_ON");
		this.longMinSecurityPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_MIN_SECURITY_PRICE");
		this.longMaxSecurityPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_MAX_SECURITY_PRICE");
		this.longMinSecurityVolume = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_MIN_SECURITY_VOLUME");
		this.longMomentumThreshold = UTXmlUtils.getXmlDataAsInt(xmldata, "LONG_MOMENTUM_THRESHOLD");

		this.shortHarvesterOn = UTXmlUtils.getXmlDataAsBoolean(xmldata, "SHORT_HARVESTER_ON");
		this.shortMinSecurityPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_MIN_SECURITY_PRICE");
		this.shortMaxSecurityPrice = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_MAX_SECURITY_PRICE");
		this.shortMinSecurityVolume = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_MIN_SECURITY_VOLUME");
		this.shortMomentumThreshold = UTXmlUtils.getXmlDataAsInt(xmldata, "SHORT_MOMENTUM_THRESHOLD");
	}

	public String toXmlWrapper(String prefix, String endline) {
		StringBuffer stb = new StringBuffer();
		stb.append(prefix + "<HARVESTER_CONFIG>" + endline);
		stb.append(toXml(prefix, endline));
		stb.append(prefix + "</HARVESTER_CONFIG>" + endline);
		return stb.toString();
	}

	public String toXml(String prefix, String endline) {
		StringBuffer stb = new StringBuffer();
		stb.append(super.toXml(prefix, endline));

		stb.append(prefix + UTDisplayFormatter.TAB + "<SERVICE_TIME>" + this.serviceTime + "</SERVICE_TIME>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<EMAIL_RECIPIENTS>" + this.emailRecipients + "</EMAIL_RECIPIENTS>" + endline);

		stb.append(prefix + UTDisplayFormatter.TAB + "<LONG_HARVESTER_ON>" + this.longHarvesterOn + "</LONG_HARVESTER_ON>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<LONG_MIN_SECURITY_PRICE>" + this.longMinSecurityPrice + "</LONG_MIN_SECURITY_PRICE>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<LONG_MAX_SECURITY_PRICE>" + this.longMaxSecurityPrice + "</LONG_MAX_SECURITY_PRICE>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<LONG_MIN_SECURITY_VOLUME>" + this.longMinSecurityVolume + "</LONG_MIN_SECURITY_VOLUME>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<LONG_MOMENTUM_THRESHOLD>" + this.longMomentumThreshold + "</LONG_MOMENTUM_THRESHOLD>" + endline);

		stb.append(prefix + UTDisplayFormatter.TAB + "<SHORT_HARVESTER_ON>" + this.shortHarvesterOn + "</SHORT_HARVESTER_ON>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<SHORT_MIN_SECURITY_PRICE>" + this.shortMinSecurityPrice + "</SHORT_MIN_SECURITY_PRICE>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<SHORT_MAX_SECURITY_PRICE>" + this.shortMaxSecurityPrice + "</SHORT_MAX_SECURITY_PRICE>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<SHORT_MIN_SECURITY_VOLUME>" + this.shortMinSecurityVolume + "</SHORT_MIN_SECURITY_VOLUME>" + endline);
		stb.append(prefix + UTDisplayFormatter.TAB + "<SHORT_MOMENTUM_THRESHOLD>" + this.shortMomentumThreshold + "</SHORT_MOMENTUM_THRESHOLD>" + endline);

		return stb.toString();
	}

	/////////////////////////////////// SETTERS-GETTERS ///////////////////////////////////////
	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getEmailRecipients() {
		return emailRecipients;
	}

	public void setEmailRecipients(String emailRecipients) {
		this.emailRecipients = emailRecipients;
	}

	public Boolean getLongHarvesterOn() {
		return longHarvesterOn;
	}

	public void setLongHarvesterOn(Boolean longHarvesterOn) {
		this.longHarvesterOn = longHarvesterOn;
	}

	public Float getLongMinSecurityPrice() {
		return longMinSecurityPrice;
	}

	public void setLongMinSecurityPrice(Float longMinSecurityPrice) {
		this.longMinSecurityPrice = longMinSecurityPrice;
	}

	public Float getLongMaxSecurityPrice() {
		return longMaxSecurityPrice;
	}

	public void setLongMaxSecurityPrice(Float longMaxSecurityPrice) {
		this.longMaxSecurityPrice = longMaxSecurityPrice;
	}

	public Float getLongMinSecurityVolume() {
		return longMinSecurityVolume;
	}

	public void setLongMinSecurityVolume(Float longMinSecurityVolume) {
		this.longMinSecurityVolume = longMinSecurityVolume;
	}

	public Integer getLongMomentumThreshold() {
		return longMomentumThreshold;
	}

	public void setLongMomentumThreshold(Integer longMomentumThreshold) {
		this.longMomentumThreshold = longMomentumThreshold;
	}

	public Boolean getShortHarvesterOn() {
		return shortHarvesterOn;
	}

	public void setShortHarvesterOn(Boolean shortHarvesterOn) {
		this.shortHarvesterOn = shortHarvesterOn;
	}

	public Float getShortMinSecurityPrice() {
		return shortMinSecurityPrice;
	}

	public void setShortMinSecurityPrice(Float shortMinSecurityPrice) {
		this.shortMinSecurityPrice = shortMinSecurityPrice;
	}

	public Float getShortMaxSecurityPrice() {
		return shortMaxSecurityPrice;
	}

	public void setShortMaxSecurityPrice(Float shortMaxSecurityPrice) {
		this.shortMaxSecurityPrice = shortMaxSecurityPrice;
	}

	public Float getShortMinSecurityVolume() {
		return shortMinSecurityVolume;
	}

	public void setShortMinSecurityVolume(Float shortMinSecurityVolume) {
		this.shortMinSecurityVolume = shortMinSecurityVolume;
	}

	public Integer getShortMomentumThreshold() {
		return shortMomentumThreshold;
	}

	public void setShortMomentumThreshold(Integer shortMomentumThreshold) {
		this.shortMomentumThreshold = shortMomentumThreshold;
	}
}
