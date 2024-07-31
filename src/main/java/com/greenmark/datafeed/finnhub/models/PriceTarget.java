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
 * PriceTarget
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class PriceTarget {
    @SerializedName("symbol")
    private String symbol = null;

    @SerializedName("targetHigh")
    private Float targetHigh = null;

    @SerializedName("targetLow")
    private Float targetLow = null;

    @SerializedName("targetMean")
    private Float targetMean = null;

    @SerializedName("targetMedian")
    private Float targetMedian = null;

    @SerializedName("numberAnalysts")
    private Long numberAnalysts = null;

    @SerializedName("lastUpdated")
    private String lastUpdated = null;

    public PriceTarget symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * Company symbol.
     *
     * @return symbol
     **/
    @Schema(description = "Company symbol.")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public PriceTarget targetHigh(Float targetHigh) {
        this.targetHigh = targetHigh;
        return this;
    }

    /**
     * Highes analysts&#x27; target.
     *
     * @return targetHigh
     **/
    @Schema(description = "Highes analysts' target.")
    public Float getTargetHigh() {
        return targetHigh;
    }

    public void setTargetHigh(Float targetHigh) {
        this.targetHigh = targetHigh;
    }

    public PriceTarget targetLow(Float targetLow) {
        this.targetLow = targetLow;
        return this;
    }

    /**
     * Lowest analysts&#x27; target.
     *
     * @return targetLow
     **/
    @Schema(description = "Lowest analysts' target.")
    public Float getTargetLow() {
        return targetLow;
    }

    public void setTargetLow(Float targetLow) {
        this.targetLow = targetLow;
    }

    public PriceTarget targetMean(Float targetMean) {
        this.targetMean = targetMean;
        return this;
    }

    /**
     * Mean of all analysts&#x27; targets.
     *
     * @return targetMean
     **/
    @Schema(description = "Mean of all analysts' targets.")
    public Float getTargetMean() {
        return targetMean;
    }

    public void setTargetMean(Float targetMean) {
        this.targetMean = targetMean;
    }

    public PriceTarget targetMedian(Float targetMedian) {
        this.targetMedian = targetMedian;
        return this;
    }

    /**
     * Median of all analysts&#x27; targets.
     *
     * @return targetMedian
     **/
    @Schema(description = "Median of all analysts' targets.")
    public Float getTargetMedian() {
        return targetMedian;
    }

    public void setTargetMedian(Float targetMedian) {
        this.targetMedian = targetMedian;
    }

    public PriceTarget numberAnalysts(Long numberAnalysts) {
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

    public PriceTarget lastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    /**
     * Updated time of the data
     *
     * @return lastUpdated
     **/
    @Schema(description = "Updated time of the data")
    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PriceTarget priceTarget = (PriceTarget) o;
        return Objects.equals(this.symbol, priceTarget.symbol) &&
                Objects.equals(this.targetHigh, priceTarget.targetHigh) &&
                Objects.equals(this.targetLow, priceTarget.targetLow) &&
                Objects.equals(this.targetMean, priceTarget.targetMean) &&
                Objects.equals(this.targetMedian, priceTarget.targetMedian) &&
                Objects.equals(this.numberAnalysts, priceTarget.numberAnalysts) &&
                Objects.equals(this.lastUpdated, priceTarget.lastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, targetHigh, targetLow, targetMean, targetMedian, numberAnalysts, lastUpdated);
    }


    @Override
    public String toString() {

        String sb = "class PriceTarget {\n" +
                "    symbol: " + toIndentedString(symbol) + "\n" +
                "    targetHigh: " + toIndentedString(targetHigh) + "\n" +
                "    targetLow: " + toIndentedString(targetLow) + "\n" +
                "    targetMean: " + toIndentedString(targetMean) + "\n" +
                "    targetMedian: " + toIndentedString(targetMedian) + "\n" +
                "    numberAnalysts: " + toIndentedString(numberAnalysts) + "\n" +
                "    lastUpdated: " + toIndentedString(lastUpdated) + "\n" +
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
