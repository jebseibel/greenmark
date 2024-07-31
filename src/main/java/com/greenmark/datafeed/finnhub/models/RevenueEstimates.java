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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RevenueEstimates
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class RevenueEstimates {
    @SerializedName("data")
    private List<RevenueEstimatesInfo> data = null;

    @SerializedName("freq")
    private String freq = null;

    @SerializedName("symbol")
    private String symbol = null;

    public RevenueEstimates data(List<RevenueEstimatesInfo> data) {
        this.data = data;
        return this;
    }

    public RevenueEstimates addDataItem(RevenueEstimatesInfo dataItem) {
        if (this.data == null) {
            this.data = new ArrayList<RevenueEstimatesInfo>();
        }
        this.data.add(dataItem);
        return this;
    }

    /**
     * List of estimates
     *
     * @return data
     **/
    @Schema(description = "List of estimates")
    public List<RevenueEstimatesInfo> getData() {
        return data;
    }

    public void setData(List<RevenueEstimatesInfo> data) {
        this.data = data;
    }

    public RevenueEstimates freq(String freq) {
        this.freq = freq;
        return this;
    }

    /**
     * Frequency: annual or quarterly.
     *
     * @return freq
     **/
    @Schema(description = "Frequency: annual or quarterly.")
    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public RevenueEstimates symbol(String symbol) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RevenueEstimates revenueEstimates = (RevenueEstimates) o;
        return Objects.equals(this.data, revenueEstimates.data) &&
                Objects.equals(this.freq, revenueEstimates.freq) &&
                Objects.equals(this.symbol, revenueEstimates.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, freq, symbol);
    }


    @Override
    public String toString() {

        String sb = "class RevenueEstimates {\n" +
                "    data: " + toIndentedString(data) + "\n" +
                "    freq: " + toIndentedString(freq) + "\n" +
                "    symbol: " + toIndentedString(symbol) + "\n" +
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
