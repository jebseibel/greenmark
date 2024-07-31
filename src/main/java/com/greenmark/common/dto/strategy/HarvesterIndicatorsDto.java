package com.greenmark.common.dto.strategy;

import java.io.Serializable;

import com.greenmark.common.GmConstants;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTXmlUtils;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: HarvesterIndicatorsDto</p>
  * <p>Description: This DTO is for the reason why we harvested a security.  
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

public class HarvesterIndicatorsDto implements Serializable {
    public static final String CLASSNAME = "HarvesterIndicatorsDto";
    private static final long serialVersionUID = 1L;

    protected long id;
    protected int active = GmConstants.OBJECT_ACTIVE;
    protected long positionEventId;

    protected Boolean long8over14 = false;
    protected Float long8over14_8ema = 0F;
    protected Float long8over14_14sma = 0F;

    protected Boolean long8over34 = false;
    protected Float long8over34_8ema = 0F;
    protected Float long8over34_34ema = 0F;

    protected Boolean longPriceOver34 = false;
    protected Float longPriceOver34_34ema = 0F;

    protected Boolean longPriceConfirmed = false;
    protected Boolean longMovingAveragesAligned = false;

    protected Boolean longOver200 = false;
    protected Float longOver200_200sma = 0F;
    protected Boolean longRising200 = false;

    protected Boolean longOver50 = false;
    protected Float longOver50_50sma = 0F;
    protected Boolean longRising50 = false;

    protected Boolean longUsdUsdtOnly = false;

    protected Boolean short8under14 = false;
    protected Float short8under14_8ema = 0F;
    protected Float short8under14_14sma = 0F;

    protected Boolean short8under34 = false;
    protected Float short8under34_8ema = 0F;
    protected Float short8under34_34ema = 0F;

    protected Boolean shortPriceUnder34 = false;
    protected Float shortPriceUnder34_34ema = 0F;

    protected Boolean shortPriceConfirmed = false;
    protected Boolean shortMovingAveragesAligned = false;

    protected Boolean shortUnder200 = false;
    protected Float shortUnder200_200sma = 0F;
    protected Boolean shortFalling200 = false;

    protected Boolean shortUnder50 = false;
    protected Float shortUnder50_50sma = 0F;
    protected Boolean shortFalling50 = false;

    protected Boolean shortUsdUsdtOnly = false;

    public HarvesterIndicatorsDto() {
    }

    // These 2 variables are handled by the Harvester DAO and so we simply set them based on the harvesterConfig here.
    public HarvesterIndicatorsDto(HarvesterConfigDto harvesterConfig) {
        this.longUsdUsdtOnly = harvesterConfig.getLongUsdUsdtOnly();
        this.shortUsdUsdtOnly = harvesterConfig.getShortUsdUsdtOnly();
    }

    // ------------------------------------------------ XML SAVE/RESTORE ---------------------------------------------------
    public HarvesterIndicatorsDto(String xmldata) {
        this.id = UTXmlUtils.getXmlDataAsLong(xmldata, "ID");
        this.active = UTXmlUtils.getXmlDataAsInt(xmldata, "ACTIVE");
        this.positionEventId = UTXmlUtils.getXmlDataAsLong(xmldata, "POSITION_EVENT_ID");

        this.long8over14 = UTXmlUtils.getXmlDataAsBoolean(xmldata, "LONG_8_OVER_14");
        this.long8over14_8ema = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_8_OVER_14_8EMA");
        this.long8over14_14sma = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_8_OVER_14_14SMA");

        this.long8over34 = UTXmlUtils.getXmlDataAsBoolean(xmldata, "LONG_8_OVER_34");
        this.long8over34_8ema = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_8_OVER_34_8EMA");
        this.long8over34_34ema = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_8_OVER_34_34EMA");

        this.longPriceOver34 = UTXmlUtils.getXmlDataAsBoolean(xmldata, "LONG_PRICE_OVER_34");
        this.longPriceOver34_34ema = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_PRICE_OVER_34_34EMA");

        this.longPriceConfirmed = UTXmlUtils.getXmlDataAsBoolean(xmldata, "LONG_PRICE_CONFIRMED");
        this.longMovingAveragesAligned = UTXmlUtils.getXmlDataAsBoolean(xmldata, "LONG_MAS_ALIGNED");

        this.longOver200 = UTXmlUtils.getXmlDataAsBoolean(xmldata, "LONG_OVER_200");
        this.longOver200_200sma = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_OVER_200_200SMA");
        this.longRising200 = UTXmlUtils.getXmlDataAsBoolean(xmldata, "LONG_RISING_200");

        this.longOver50 = UTXmlUtils.getXmlDataAsBoolean(xmldata, "LONG_OVER_50");
        this.longOver50_50sma = UTXmlUtils.getXmlDataAsFloat(xmldata, "LONG_OVER_50_50SMA");
        this.longRising50 = UTXmlUtils.getXmlDataAsBoolean(xmldata, "LONG_RISING_50");

        this.longUsdUsdtOnly = UTXmlUtils.getXmlDataAsBoolean(xmldata, "LONG_USD_USDT_ONLY");

        this.short8under14 = UTXmlUtils.getXmlDataAsBoolean(xmldata, "SHORT_8_UNDER_14");
        this.short8under14_8ema = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_8_UNDER_14_8EMA");
        this.short8under14_14sma = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_8_UNDER_14_14SMA");

        this.short8under34 = UTXmlUtils.getXmlDataAsBoolean(xmldata, "SHORT_8_UNDER_34");
        this.short8under34_8ema = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_8_UNDER_34_8EMA");
        this.short8under34_34ema = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_8_UNDER_34_34EMA");

        this.shortPriceUnder34 = UTXmlUtils.getXmlDataAsBoolean(xmldata, "SHORT_PRICE_UNDER_34");
        this.shortPriceUnder34_34ema = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_PRICE_UNDER_34_34EMA");

        this.shortPriceConfirmed = UTXmlUtils.getXmlDataAsBoolean(xmldata, "SHORT_PRICE_CONFIRMED");
        this.shortMovingAveragesAligned = UTXmlUtils.getXmlDataAsBoolean(xmldata, "SHORT_MAS_ALIGNED");

        this.shortUnder200 = UTXmlUtils.getXmlDataAsBoolean(xmldata, "SHORT_UNDER_200");
        this.shortUnder200_200sma = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_UNDER_200_200SMA");
        this.shortFalling200 = UTXmlUtils.getXmlDataAsBoolean(xmldata, "SHORT_FALLING_200");

        this.shortUnder50 = UTXmlUtils.getXmlDataAsBoolean(xmldata, "SHORT_UNDER_50");
        this.shortUnder50_50sma = UTXmlUtils.getXmlDataAsFloat(xmldata, "SHORT_UNDER_50_50SMA");
        this.shortFalling50 = UTXmlUtils.getXmlDataAsBoolean(xmldata, "SHORT_FALLING_50");

        this.shortUsdUsdtOnly = UTXmlUtils.getXmlDataAsBoolean(xmldata, "SHORT_USD_USDT_ONLY");
    }

    public String toXmlWrapper(String prefix, String endline) {
        String stb = prefix + "<HARVESTER_INDICATORS>" + endline +
                toXml(prefix, endline) +
                prefix + "</HARVESTER_INDICATORS>" + endline;
        return stb;
    }

    public String toXml(String prefix, String endline) {

        String stb = prefix + UTDisplayFormatter.TAB + "<ID>" + this.id + "</ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<ACTIVE>" + this.getActive() + "</ACTIVE>" + endline +
                prefix + UTDisplayFormatter.TAB + "<POSITION_EVENT_ID>" + this.positionEventId + "</POSITION_EVENT_ID>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_8_OVER_14>" + this.long8over14 + "</LONG_8_OVER_14>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_8_OVER_14_8EMA>" + this.long8over14_8ema + "</LONG_8_OVER_14_8EMA>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_8_OVER_14_14SMA>" + this.long8over14_14sma + "</LONG_8_OVER_14_14SMA>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_8_OVER_34>" + this.long8over34 + "</LONG_8_OVER_34>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_8_OVER_34_8EMA>" + this.long8over34_8ema + "</LONG_8_OVER_34_8EMA>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_8_OVER_34_34EMA>" + this.long8over34_34ema + "</LONG_8_OVER_34_34EMA>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_PRICE_OVER_34>" + this.longPriceOver34 + "</LONG_PRICE_OVER_34>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_PRICE_OVER_34_34EMA>" + this.longPriceOver34_34ema + "</LONG_PRICE_OVER_34_34EMA>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_PRICE_CONFIRMED>" + this.longPriceConfirmed + "</LONG_PRICE_CONFIRMED>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_MAS_ALIGNED>" + this.longMovingAveragesAligned + "</LONG_MAS_ALIGNED>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_OVER_200>" + this.longOver200 + "</LONG_OVER_200>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_OVER_200_200SMA>" + this.longOver200_200sma + "</LONG_OVER_200_200SMA>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_RISING_200>" + this.longRising200 + "</LONG_RISING_200>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_OVER_50>" + this.longOver50 + "</LONG_OVER_50>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_OVER_50_50SMA>" + this.longOver50_50sma + "</LONG_OVER_50_50SMA>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_RISING_50>" + this.longRising50 + "</LONG_RISING_50>" + endline +
                prefix + UTDisplayFormatter.TAB + "<LONG_USD_USDT_ONLY>" + this.longUsdUsdtOnly + "</LONG_USD_USDT_ONLY>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_8_UNDER_14>" + this.short8under14 + "</SHORT_8_UNDER_14>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_8_UNDER_14_8EMA>" + this.short8under14_8ema + "</SHORT_8_UNDER_14_8EMA>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_8_UNDER_14_14SMA>" + this.short8under14_14sma + "</SHORT_8_UNDER_14_14SMA>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_8_UNDER_34>" + this.short8under34 + "</SHORT_8_UNDER_34>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_8_UNDER_34_8EMA>" + this.short8under34_8ema + "</SHORT_8_UNDER_34_8EMA>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_8_UNDER_34_34EMA>" + this.short8under34_34ema + "</SHORT_8_UNDER_34_34EMA>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_PRICE_UNDER_34>" + this.shortPriceUnder34 + "</SHORT_PRICE_UNDER_34>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_PRICE_UNDER_34_34EMA>" + this.shortPriceUnder34_34ema + "</SHORT_PRICE_UNDER_34_34EMA>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_PRICE_CONFIRMED>" + this.shortPriceConfirmed + "</SHORT_PRICE_CONFIRMED>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_MAS_ALIGNED>" + this.shortMovingAveragesAligned + "</SHORT_MAS_ALIGNED>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_UNDER_200>" + this.shortUnder200 + "</SHORT_UNDER_200>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_UNDER_200_200SMA>" + this.shortUnder200_200sma + "</SHORT_UNDER_200_200SMA>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_FALLING_200>" + this.shortFalling200 + "</SHORT_FALLING_200>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_UNDER_50>" + this.shortUnder50 + "</SHORT_UNDER_50>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_UNDER_50_50SMA>" + this.shortUnder50_50sma + "</SHORT_UNDER_50_50SMA>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_FALLING_50>" + this.shortFalling50 + "</SHORT_FALLING_50>" + endline +
                prefix + UTDisplayFormatter.TAB + "<SHORT_USD_USDT_ONLY>" + this.shortUsdUsdtOnly + "</SHORT_USD_USDT_ONLY>" + endline;

        return stb;
    }

    /////////////////////////////////// SETTERS-GETTERS ///////////////////////////////////////
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

    public long getPositionEventId() {
        return positionEventId;
    }

    public void setPositionEventId(long positionEventId) {
        this.positionEventId = positionEventId;
    }

    public Boolean getLong8over14() {
        return long8over14;
    }

    public void setLong8over14(Boolean long8over14) {
        this.long8over14 = long8over14;
    }

    public Float getLong8over14_8ema() {
        return long8over14_8ema;
    }

    public void setLong8over14_8ema(Float long8over14_8ema) {
        this.long8over14_8ema = long8over14_8ema;
    }

    public Float getLong8over14_14sma() {
        return long8over14_14sma;
    }

    public void setLong8over14_14sma(Float long8over14_14sma) {
        this.long8over14_14sma = long8over14_14sma;
    }

    public Boolean getLong8over34() {
        return long8over34;
    }

    public void setLong8over34(Boolean long8over34) {
        this.long8over34 = long8over34;
    }

    public Float getLong8over34_8ema() {
        return long8over34_8ema;
    }

    public void setLong8over34_8ema(Float long8over34_8ema) {
        this.long8over34_8ema = long8over34_8ema;
    }

    public Float getLong8over34_34ema() {
        return long8over34_34ema;
    }

    public void setLong8over34_34ema(Float long8over34_34ema) {
        this.long8over34_34ema = long8over34_34ema;
    }

    public Boolean getLongPriceOver34() {
        return longPriceOver34;
    }

    public void setLongPriceOver34(Boolean longPriceOver34) {
        this.longPriceOver34 = longPriceOver34;
    }

    public Float getLongPriceOver34_34ema() {
        return longPriceOver34_34ema;
    }

    public void setLongPriceOver34_34ema(Float longPriceOver34_34ema) {
        this.longPriceOver34_34ema = longPriceOver34_34ema;
    }

    public Boolean getLongPriceConfirmed() {
        return longPriceConfirmed;
    }

    public void setLongPriceConfirmed(Boolean longPriceConfirmed) {
        this.longPriceConfirmed = longPriceConfirmed;
    }

    public Boolean getLongMovingAveragesAligned() {
        return longMovingAveragesAligned;
    }

    public void setLongMovingAveragesAligned(Boolean longMovingAveragesAligned) {
        this.longMovingAveragesAligned = longMovingAveragesAligned;
    }

    public Boolean getLongOver200() {
        return longOver200;
    }

    public void setLongOver200(Boolean longOver200) {
        this.longOver200 = longOver200;
    }

    public Float getLongOver200_200sma() {
        return longOver200_200sma;
    }

    public void setLongOver200_200sma(Float longOver200_200sma) {
        this.longOver200_200sma = longOver200_200sma;
    }

    public Boolean getLongRising200() {
        return longRising200;
    }

    public void setLongRising200(Boolean longRising200) {
        this.longRising200 = longRising200;
    }

    public Boolean getLongOver50() {
        return longOver50;
    }

    public void setLongOver50(Boolean longOver50) {
        this.longOver50 = longOver50;
    }

    public Float getLongOver50_50sma() {
        return longOver50_50sma;
    }

    public void setLongOver50_50sma(Float longOver50_50sma) {
        this.longOver50_50sma = longOver50_50sma;
    }

    public Boolean getLongRising50() {
        return longRising50;
    }

    public void setLongRising50(Boolean longRising50) {
        this.longRising50 = longRising50;
    }

    public Boolean getLongUsdUsdtOnly() {
        return longUsdUsdtOnly;
    }

    public void setLongUsdUsdtOnly(Boolean longUsdUsdtOnly) {
        this.longUsdUsdtOnly = longUsdUsdtOnly;
    }

    public Boolean getShort8under14() {
        return short8under14;
    }

    public void setShort8under14(Boolean short8under14) {
        this.short8under14 = short8under14;
    }

    public Float getShort8under14_8ema() {
        return short8under14_8ema;
    }

    public void setShort8under14_8ema(Float short8under14_8ema) {
        this.short8under14_8ema = short8under14_8ema;
    }

    public Float getShort8under14_14sma() {
        return short8under14_14sma;
    }

    public void setShort8under14_14sma(Float short8under14_14sma) {
        this.short8under14_14sma = short8under14_14sma;
    }

    public Boolean getShort8under34() {
        return short8under34;
    }

    public void setShort8under34(Boolean short8under34) {
        this.short8under34 = short8under34;
    }

    public Float getShort8under34_8ema() {
        return short8under34_8ema;
    }

    public void setShort8under34_8ema(Float short8under34_8ema) {
        this.short8under34_8ema = short8under34_8ema;
    }

    public Float getShort8under34_34ema() {
        return short8under34_34ema;
    }

    public void setShort8under34_34ema(Float short8under34_34ema) {
        this.short8under34_34ema = short8under34_34ema;
    }

    public Boolean getShortPriceUnder34() {
        return shortPriceUnder34;
    }

    public void setShortPriceUnder34(Boolean shortPriceUnder34) {
        this.shortPriceUnder34 = shortPriceUnder34;
    }

    public Float getShortPriceUnder34_34ema() {
        return shortPriceUnder34_34ema;
    }

    public void setShortPriceUnder34_34ema(Float shortPriceUnder34_34ema) {
        this.shortPriceUnder34_34ema = shortPriceUnder34_34ema;
    }

    public Boolean getShortPriceConfirmed() {
        return shortPriceConfirmed;
    }

    public void setShortPriceConfirmed(Boolean shortPriceConfirmed) {
        this.shortPriceConfirmed = shortPriceConfirmed;
    }

    public Boolean getShortMovingAveragesAligned() {
        return shortMovingAveragesAligned;
    }

    public void setShortMovingAveragesAligned(Boolean shortMovingAveragesAligned) {
        this.shortMovingAveragesAligned = shortMovingAveragesAligned;
    }

    public Boolean getShortUnder200() {
        return shortUnder200;
    }

    public void setShortUnder200(Boolean shortUnder200) {
        this.shortUnder200 = shortUnder200;
    }

    public Float getShortUnder200_200sma() {
        return shortUnder200_200sma;
    }

    public void setShortUnder200_200sma(Float shortUnder200_200sma) {
        this.shortUnder200_200sma = shortUnder200_200sma;
    }

    public Boolean getShortFalling200() {
        return shortFalling200;
    }

    public void setShortFalling200(Boolean shortFalling200) {
        this.shortFalling200 = shortFalling200;
    }

    public Boolean getShortUnder50() {
        return shortUnder50;
    }

    public void setShortUnder50(Boolean shortUnder50) {
        this.shortUnder50 = shortUnder50;
    }

    public Float getShortUnder50_50sma() {
        return shortUnder50_50sma;
    }

    public void setShortUnder50_50sma(Float shortUnder50_50sma) {
        this.shortUnder50_50sma = shortUnder50_50sma;
    }

    public Boolean getShortFalling50() {
        return shortFalling50;
    }

    public void setShortFalling50(Boolean shortFalling50) {
        this.shortFalling50 = shortFalling50;
    }

    public Boolean getShortUsdUsdtOnly() {
        return shortUsdUsdtOnly;
    }

    public void setShortUsdUsdtOnly(Boolean shortUsdUsdtOnly) {
        this.shortUsdUsdtOnly = shortUsdUsdtOnly;
    }
}
