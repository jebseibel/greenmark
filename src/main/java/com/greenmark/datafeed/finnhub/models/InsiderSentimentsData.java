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
 * InsiderSentimentsData
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class InsiderSentimentsData {
    @SerializedName("symbol")
    private String symbol = null;

    @SerializedName("year")
    private Long year = null;

    @SerializedName("month")
    private Long month = null;

    @SerializedName("change")
    private Long change = null;

    @SerializedName("mspr")
    private Float mspr = null;

    public InsiderSentimentsData symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * Symbol.
     *
     * @return symbol
     **/
    @Schema(description = "Symbol.")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public InsiderSentimentsData year(Long year) {
        this.year = year;
        return this;
    }

    /**
     * Year.
     *
     * @return year
     **/
    @Schema(description = "Year.")
    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public InsiderSentimentsData month(Long month) {
        this.month = month;
        return this;
    }

    /**
     * Month.
     *
     * @return month
     **/
    @Schema(description = "Month.")
    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public InsiderSentimentsData change(Long change) {
        this.change = change;
        return this;
    }

    /**
     * Net buying/selling from all insiders&#x27; transactions.
     *
     * @return change
     **/
    @Schema(description = "Net buying/selling from all insiders' transactions.")
    public Long getChange() {
        return change;
    }

    public void setChange(Long change) {
        this.change = change;
    }

    public InsiderSentimentsData mspr(Float mspr) {
        this.mspr = mspr;
        return this;
    }

    /**
     * Monthly share purchase ratio.
     *
     * @return mspr
     **/
    @Schema(description = "Monthly share purchase ratio.")
    public Float getMspr() {
        return mspr;
    }

    public void setMspr(Float mspr) {
        this.mspr = mspr;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InsiderSentimentsData insiderSentimentsData = (InsiderSentimentsData) o;
        return Objects.equals(this.symbol, insiderSentimentsData.symbol) &&
                Objects.equals(this.year, insiderSentimentsData.year) &&
                Objects.equals(this.month, insiderSentimentsData.month) &&
                Objects.equals(this.change, insiderSentimentsData.change) &&
                Objects.equals(this.mspr, insiderSentimentsData.mspr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, year, month, change, mspr);
    }


    @Override
    public String toString() {

        String sb = "class InsiderSentimentsData {\n" +
                "    symbol: " + toIndentedString(symbol) + "\n" +
                "    year: " + toIndentedString(year) + "\n" +
                "    month: " + toIndentedString(month) + "\n" +
                "    change: " + toIndentedString(change) + "\n" +
                "    mspr: " + toIndentedString(mspr) + "\n" +
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
