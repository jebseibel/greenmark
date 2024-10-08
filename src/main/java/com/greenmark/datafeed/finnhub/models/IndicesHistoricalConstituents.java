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
 * IndicesHistoricalConstituents
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class IndicesHistoricalConstituents {
    @SerializedName("symbol")
    private String symbol = null;

    @SerializedName("historicalConstituents")
    private List<IndexHistoricalConstituent> historicalConstituents = null;

    public IndicesHistoricalConstituents symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * Index&#x27;s symbol.
     *
     * @return symbol
     **/
    @Schema(description = "Index's symbol.")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public IndicesHistoricalConstituents historicalConstituents(List<IndexHistoricalConstituent> historicalConstituents) {
        this.historicalConstituents = historicalConstituents;
        return this;
    }

    public IndicesHistoricalConstituents addHistoricalConstituentsItem(IndexHistoricalConstituent historicalConstituentsItem) {
        if (this.historicalConstituents == null) {
            this.historicalConstituents = new ArrayList<IndexHistoricalConstituent>();
        }
        this.historicalConstituents.add(historicalConstituentsItem);
        return this;
    }

    /**
     * Array of historical constituents.
     *
     * @return historicalConstituents
     **/
    @Schema(description = "Array of historical constituents.")
    public List<IndexHistoricalConstituent> getHistoricalConstituents() {
        return historicalConstituents;
    }

    public void setHistoricalConstituents(List<IndexHistoricalConstituent> historicalConstituents) {
        this.historicalConstituents = historicalConstituents;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IndicesHistoricalConstituents indicesHistoricalConstituents = (IndicesHistoricalConstituents) o;
        return Objects.equals(this.symbol, indicesHistoricalConstituents.symbol) &&
                Objects.equals(this.historicalConstituents, indicesHistoricalConstituents.historicalConstituents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, historicalConstituents);
    }


    @Override
    public String toString() {

        String sb = "class IndicesHistoricalConstituents {\n" +
                "    symbol: " + toIndentedString(symbol) + "\n" +
                "    historicalConstituents: " + toIndentedString(historicalConstituents) + "\n" +
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
