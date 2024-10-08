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
import org.threeten.bp.LocalDate;

import java.util.Objects;

/**
 * EarningRelease
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class EarningRelease {
    @SerializedName("symbol")
    private String symbol = null;

    @SerializedName("date")
    private LocalDate date = null;

    @SerializedName("hour")
    private String hour = null;

    @SerializedName("year")
    private Long year = null;

    @SerializedName("quarter")
    private Long quarter = null;

    @SerializedName("epsEstimate")
    private Float epsEstimate = null;

    @SerializedName("epsActual")
    private Float epsActual = null;

    @SerializedName("revenueEstimate")
    private Float revenueEstimate = null;

    @SerializedName("revenueActual")
    private Float revenueActual = null;

    public EarningRelease symbol(String symbol) {
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

    public EarningRelease date(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * Date.
     *
     * @return date
     **/
    @Schema(description = "Date.")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public EarningRelease hour(String hour) {
        this.hour = hour;
        return this;
    }

    /**
     * Indicates whether the earnings is announced before market open(&lt;code&gt;bmo&lt;/code&gt;), after market close(&lt;code&gt;amc&lt;/code&gt;), or during market hour(&lt;code&gt;dmh&lt;/code&gt;).
     *
     * @return hour
     **/
    @Schema(description = "Indicates whether the earnings is announced before market open(<code>bmo</code>), after market close(<code>amc</code>), or during market hour(<code>dmh</code>).")
    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public EarningRelease year(Long year) {
        this.year = year;
        return this;
    }

    /**
     * Earnings year.
     *
     * @return year
     **/
    @Schema(description = "Earnings year.")
    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public EarningRelease quarter(Long quarter) {
        this.quarter = quarter;
        return this;
    }

    /**
     * Earnings quarter.
     *
     * @return quarter
     **/
    @Schema(description = "Earnings quarter.")
    public Long getQuarter() {
        return quarter;
    }

    public void setQuarter(Long quarter) {
        this.quarter = quarter;
    }

    public EarningRelease epsEstimate(Float epsEstimate) {
        this.epsEstimate = epsEstimate;
        return this;
    }

    /**
     * EPS estimate.
     *
     * @return epsEstimate
     **/
    @Schema(description = "EPS estimate.")
    public Float getEpsEstimate() {
        return epsEstimate;
    }

    public void setEpsEstimate(Float epsEstimate) {
        this.epsEstimate = epsEstimate;
    }

    public EarningRelease epsActual(Float epsActual) {
        this.epsActual = epsActual;
        return this;
    }

    /**
     * EPS actual.
     *
     * @return epsActual
     **/
    @Schema(description = "EPS actual.")
    public Float getEpsActual() {
        return epsActual;
    }

    public void setEpsActual(Float epsActual) {
        this.epsActual = epsActual;
    }

    public EarningRelease revenueEstimate(Float revenueEstimate) {
        this.revenueEstimate = revenueEstimate;
        return this;
    }

    /**
     * Revenue estimate including Finnhub&#x27;s proprietary estimates.
     *
     * @return revenueEstimate
     **/
    @Schema(description = "Revenue estimate including Finnhub's proprietary estimates.")
    public Float getRevenueEstimate() {
        return revenueEstimate;
    }

    public void setRevenueEstimate(Float revenueEstimate) {
        this.revenueEstimate = revenueEstimate;
    }

    public EarningRelease revenueActual(Float revenueActual) {
        this.revenueActual = revenueActual;
        return this;
    }

    /**
     * Revenue actual.
     *
     * @return revenueActual
     **/
    @Schema(description = "Revenue actual.")
    public Float getRevenueActual() {
        return revenueActual;
    }

    public void setRevenueActual(Float revenueActual) {
        this.revenueActual = revenueActual;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EarningRelease earningRelease = (EarningRelease) o;
        return Objects.equals(this.symbol, earningRelease.symbol) &&
                Objects.equals(this.date, earningRelease.date) &&
                Objects.equals(this.hour, earningRelease.hour) &&
                Objects.equals(this.year, earningRelease.year) &&
                Objects.equals(this.quarter, earningRelease.quarter) &&
                Objects.equals(this.epsEstimate, earningRelease.epsEstimate) &&
                Objects.equals(this.epsActual, earningRelease.epsActual) &&
                Objects.equals(this.revenueEstimate, earningRelease.revenueEstimate) &&
                Objects.equals(this.revenueActual, earningRelease.revenueActual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, date, hour, year, quarter, epsEstimate, epsActual, revenueEstimate, revenueActual);
    }


    @Override
    public String toString() {

        String sb = "class EarningRelease {\n" +
                "    symbol: " + toIndentedString(symbol) + "\n" +
                "    date: " + toIndentedString(date) + "\n" +
                "    hour: " + toIndentedString(hour) + "\n" +
                "    year: " + toIndentedString(year) + "\n" +
                "    quarter: " + toIndentedString(quarter) + "\n" +
                "    epsEstimate: " + toIndentedString(epsEstimate) + "\n" +
                "    epsActual: " + toIndentedString(epsActual) + "\n" +
                "    revenueEstimate: " + toIndentedString(revenueEstimate) + "\n" +
                "    revenueActual: " + toIndentedString(revenueActual) + "\n" +
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
