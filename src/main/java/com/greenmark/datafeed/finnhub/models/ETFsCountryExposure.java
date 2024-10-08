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
 * ETFsCountryExposure
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class ETFsCountryExposure {
    @SerializedName("symbol")
    private String symbol = null;

    @SerializedName("countryExposure")
    private List<ETFCountryExposureData> countryExposure = null;

    public ETFsCountryExposure symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * ETF symbol.
     *
     * @return symbol
     **/
    @Schema(description = "ETF symbol.")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public ETFsCountryExposure countryExposure(List<ETFCountryExposureData> countryExposure) {
        this.countryExposure = countryExposure;
        return this;
    }

    public ETFsCountryExposure addCountryExposureItem(ETFCountryExposureData countryExposureItem) {
        if (this.countryExposure == null) {
            this.countryExposure = new ArrayList<ETFCountryExposureData>();
        }
        this.countryExposure.add(countryExposureItem);
        return this;
    }

    /**
     * Array of countries and and exposure levels.
     *
     * @return countryExposure
     **/
    @Schema(description = "Array of countries and and exposure levels.")
    public List<ETFCountryExposureData> getCountryExposure() {
        return countryExposure;
    }

    public void setCountryExposure(List<ETFCountryExposureData> countryExposure) {
        this.countryExposure = countryExposure;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ETFsCountryExposure etFsCountryExposure = (ETFsCountryExposure) o;
        return Objects.equals(this.symbol, etFsCountryExposure.symbol) &&
                Objects.equals(this.countryExposure, etFsCountryExposure.countryExposure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, countryExposure);
    }


    @Override
    public String toString() {

        String sb = "class ETFsCountryExposure {\n" +
                "    symbol: " + toIndentedString(symbol) + "\n" +
                "    countryExposure: " + toIndentedString(countryExposure) + "\n" +
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
