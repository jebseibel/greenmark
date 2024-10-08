/*
 * Finnhub API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.greenmark.datafeed.finnhub.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * MarketHolidayData
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class MarketHolidayData {
    @SerializedName("eventName")
    private String eventName = null;

    @SerializedName("atDate")
    private String atDate = null;

    @SerializedName("tradingHour")
    private String tradingHour = null;

    public MarketHolidayData eventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    /**
     * Holiday&#x27;s name.
     *
     * @return eventName
     **/
    @Schema(description = "Holiday's name.")
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public MarketHolidayData atDate(String atDate) {
        this.atDate = atDate;
        return this;
    }

    /**
     * Date.
     *
     * @return atDate
     **/
    @Schema(description = "Date.")
    public String getAtDate() {
        return atDate;
    }

    public void setAtDate(String atDate) {
        this.atDate = atDate;
    }

    public MarketHolidayData tradingHour(String tradingHour) {
        this.tradingHour = tradingHour;
        return this;
    }

    /**
     * Trading hours for this day if the market is partially closed only.
     *
     * @return tradingHour
     **/
    @Schema(description = "Trading hours for this day if the market is partially closed only.")
    public String getTradingHour() {
        return tradingHour;
    }

    public void setTradingHour(String tradingHour) {
        this.tradingHour = tradingHour;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MarketHolidayData marketHolidayData = (MarketHolidayData) o;
        return Objects.equals(this.eventName, marketHolidayData.eventName) &&
                Objects.equals(this.atDate, marketHolidayData.atDate) &&
                Objects.equals(this.tradingHour, marketHolidayData.tradingHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, atDate, tradingHour);
    }


    @Override
    public String toString() {

        String sb = "class MarketHolidayData {\n" +
                "    eventName: " + toIndentedString(eventName) + "\n" +
                "    atDate: " + toIndentedString(atDate) + "\n" +
                "    tradingHour: " + toIndentedString(tradingHour) + "\n" +
                "}";
        return sb;
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
