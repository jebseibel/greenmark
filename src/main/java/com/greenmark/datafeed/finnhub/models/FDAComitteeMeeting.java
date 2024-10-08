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
 * FDAComitteeMeeting
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class FDAComitteeMeeting {
    @SerializedName("fromDate")
    private String fromDate = null;

    @SerializedName("toDate")
    private String toDate = null;

    @SerializedName("eventDescription")
    private String eventDescription = null;

    @SerializedName("url")
    private String url = null;

    public FDAComitteeMeeting fromDate(String fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    /**
     * Start time of the event in EST.
     *
     * @return fromDate
     **/
    @Schema(description = "Start time of the event in EST.")
    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public FDAComitteeMeeting toDate(String toDate) {
        this.toDate = toDate;
        return this;
    }

    /**
     * End time of the event in EST.
     *
     * @return toDate
     **/
    @Schema(description = "End time of the event in EST.")
    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public FDAComitteeMeeting eventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
        return this;
    }

    /**
     * Event&#x27;s description.
     *
     * @return eventDescription
     **/
    @Schema(description = "Event's description.")
    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public FDAComitteeMeeting url(String url) {
        this.url = url;
        return this;
    }

    /**
     * URL.
     *
     * @return url
     **/
    @Schema(description = "URL.")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FDAComitteeMeeting fdAComitteeMeeting = (FDAComitteeMeeting) o;
        return Objects.equals(this.fromDate, fdAComitteeMeeting.fromDate) &&
                Objects.equals(this.toDate, fdAComitteeMeeting.toDate) &&
                Objects.equals(this.eventDescription, fdAComitteeMeeting.eventDescription) &&
                Objects.equals(this.url, fdAComitteeMeeting.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromDate, toDate, eventDescription, url);
    }


    @Override
    public String toString() {

        String sb = "class FDAComitteeMeeting {\n" +
                "    fromDate: " + toIndentedString(fromDate) + "\n" +
                "    toDate: " + toIndentedString(toDate) + "\n" +
                "    eventDescription: " + toIndentedString(eventDescription) + "\n" +
                "    url: " + toIndentedString(url) + "\n" +
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
