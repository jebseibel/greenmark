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
 * RevenueEstimatesInfo
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class RevenueEstimatesInfo {
    @SerializedName("revenueAvg")
    private Float revenueAvg = null;

    @SerializedName("revenueHigh")
    private Float revenueHigh = null;

    @SerializedName("revenueLow")
    private Float revenueLow = null;

    @SerializedName("numberAnalysts")
    private Long numberAnalysts = null;

    @SerializedName("period")
    private LocalDate period = null;

    @SerializedName("year")
    private Long year = null;

    @SerializedName("quarter")
    private Long quarter = null;

    public RevenueEstimatesInfo revenueAvg(Float revenueAvg) {
        this.revenueAvg = revenueAvg;
        return this;
    }

    /**
     * Average revenue estimates including Finnhub&#x27;s proprietary estimates.
     *
     * @return revenueAvg
     **/
    @Schema(description = "Average revenue estimates including Finnhub's proprietary estimates.")
    public Float getRevenueAvg() {
        return revenueAvg;
    }

    public void setRevenueAvg(Float revenueAvg) {
        this.revenueAvg = revenueAvg;
    }

    public RevenueEstimatesInfo revenueHigh(Float revenueHigh) {
        this.revenueHigh = revenueHigh;
        return this;
    }

    /**
     * Highest estimate.
     *
     * @return revenueHigh
     **/
    @Schema(description = "Highest estimate.")
    public Float getRevenueHigh() {
        return revenueHigh;
    }

    public void setRevenueHigh(Float revenueHigh) {
        this.revenueHigh = revenueHigh;
    }

    public RevenueEstimatesInfo revenueLow(Float revenueLow) {
        this.revenueLow = revenueLow;
        return this;
    }

    /**
     * Lowest estimate.
     *
     * @return revenueLow
     **/
    @Schema(description = "Lowest estimate.")
    public Float getRevenueLow() {
        return revenueLow;
    }

    public void setRevenueLow(Float revenueLow) {
        this.revenueLow = revenueLow;
    }

    public RevenueEstimatesInfo numberAnalysts(Long numberAnalysts) {
        this.numberAnalysts = numberAnalysts;
        return this;
    }

    /**
     * Number of Analysts.
     *
     * @return numberAnalysts
     **/
    @Schema(description = "Number of Analysts.")
    public Long getNumberAnalysts() {
        return numberAnalysts;
    }

    public void setNumberAnalysts(Long numberAnalysts) {
        this.numberAnalysts = numberAnalysts;
    }

    public RevenueEstimatesInfo period(LocalDate period) {
        this.period = period;
        return this;
    }

    /**
     * Period.
     *
     * @return period
     **/
    @Schema(description = "Period.")
    public LocalDate getPeriod() {
        return period;
    }

    public void setPeriod(LocalDate period) {
        this.period = period;
    }

    public RevenueEstimatesInfo year(Long year) {
        this.year = year;
        return this;
    }

    /**
     * Fiscal year.
     *
     * @return year
     **/
    @Schema(description = "Fiscal year.")
    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public RevenueEstimatesInfo quarter(Long quarter) {
        this.quarter = quarter;
        return this;
    }

    /**
     * Fiscal quarter.
     *
     * @return quarter
     **/
    @Schema(description = "Fiscal quarter.")
    public Long getQuarter() {
        return quarter;
    }

    public void setQuarter(Long quarter) {
        this.quarter = quarter;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RevenueEstimatesInfo revenueEstimatesInfo = (RevenueEstimatesInfo) o;
        return Objects.equals(this.revenueAvg, revenueEstimatesInfo.revenueAvg) &&
                Objects.equals(this.revenueHigh, revenueEstimatesInfo.revenueHigh) &&
                Objects.equals(this.revenueLow, revenueEstimatesInfo.revenueLow) &&
                Objects.equals(this.numberAnalysts, revenueEstimatesInfo.numberAnalysts) &&
                Objects.equals(this.period, revenueEstimatesInfo.period) &&
                Objects.equals(this.year, revenueEstimatesInfo.year) &&
                Objects.equals(this.quarter, revenueEstimatesInfo.quarter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(revenueAvg, revenueHigh, revenueLow, numberAnalysts, period, year, quarter);
    }


    @Override
    public String toString() {

        String sb = "class RevenueEstimatesInfo {\n" +
                "    revenueAvg: " + toIndentedString(revenueAvg) + "\n" +
                "    revenueHigh: " + toIndentedString(revenueHigh) + "\n" +
                "    revenueLow: " + toIndentedString(revenueLow) + "\n" +
                "    numberAnalysts: " + toIndentedString(numberAnalysts) + "\n" +
                "    period: " + toIndentedString(period) + "\n" +
                "    year: " + toIndentedString(year) + "\n" +
                "    quarter: " + toIndentedString(quarter) + "\n" +
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
