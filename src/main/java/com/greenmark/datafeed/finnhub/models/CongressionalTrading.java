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
 * CongressionalTrading
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class CongressionalTrading {
    @SerializedName("symbol")
    private String symbol = null;

    @SerializedName("data")
    private List<CongressionalTransaction> data = null;

    public CongressionalTrading symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * Symbol of the company.
     *
     * @return symbol
     **/
    @Schema(description = "Symbol of the company.")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public CongressionalTrading data(List<CongressionalTransaction> data) {
        this.data = data;
        return this;
    }

    public CongressionalTrading addDataItem(CongressionalTransaction dataItem) {
        if (this.data == null) {
            this.data = new ArrayList<CongressionalTransaction>();
        }
        this.data.add(dataItem);
        return this;
    }

    /**
     * Array of stock trades.
     *
     * @return data
     **/
    @Schema(description = "Array of stock trades.")
    public List<CongressionalTransaction> getData() {
        return data;
    }

    public void setData(List<CongressionalTransaction> data) {
        this.data = data;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CongressionalTrading congressionalTrading = (CongressionalTrading) o;
        return Objects.equals(this.symbol, congressionalTrading.symbol) &&
                Objects.equals(this.data, congressionalTrading.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, data);
    }


    @Override
    public String toString() {

        String sb = "class CongressionalTrading {\n" +
                "    symbol: " + toIndentedString(symbol) + "\n" +
                "    data: " + toIndentedString(data) + "\n" +
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
