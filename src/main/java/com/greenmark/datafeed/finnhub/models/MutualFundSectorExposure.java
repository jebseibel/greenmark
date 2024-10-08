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
 * MutualFundSectorExposure
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class MutualFundSectorExposure {
    @SerializedName("symbol")
    private String symbol = null;

    @SerializedName("sectorExposure")
    private List<MutualFundSectorExposureData> sectorExposure = null;

    public MutualFundSectorExposure symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * Mutual symbol.
     *
     * @return symbol
     **/
    @Schema(description = "Mutual symbol.")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public MutualFundSectorExposure sectorExposure(List<MutualFundSectorExposureData> sectorExposure) {
        this.sectorExposure = sectorExposure;
        return this;
    }

    public MutualFundSectorExposure addSectorExposureItem(MutualFundSectorExposureData sectorExposureItem) {
        if (this.sectorExposure == null) {
            this.sectorExposure = new ArrayList<MutualFundSectorExposureData>();
        }
        this.sectorExposure.add(sectorExposureItem);
        return this;
    }

    /**
     * Array of sector and exposure levels.
     *
     * @return sectorExposure
     **/
    @Schema(description = "Array of sector and exposure levels.")
    public List<MutualFundSectorExposureData> getSectorExposure() {
        return sectorExposure;
    }

    public void setSectorExposure(List<MutualFundSectorExposureData> sectorExposure) {
        this.sectorExposure = sectorExposure;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MutualFundSectorExposure mutualFundSectorExposure = (MutualFundSectorExposure) o;
        return Objects.equals(this.symbol, mutualFundSectorExposure.symbol) &&
                Objects.equals(this.sectorExposure, mutualFundSectorExposure.sectorExposure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, sectorExposure);
    }


    @Override
    public String toString() {

        String sb = "class MutualFundSectorExposure {\n" +
                "    symbol: " + toIndentedString(symbol) + "\n" +
                "    sectorExposure: " + toIndentedString(sectorExposure) + "\n" +
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
