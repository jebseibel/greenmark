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
 * Split
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class Split {
    @SerializedName("symbol")
    private String symbol = null;

    @SerializedName("date")
    private LocalDate date = null;

    @SerializedName("fromFactor")
    private Float fromFactor = null;

    @SerializedName("toFactor")
    private Float toFactor = null;

    public Split symbol(String symbol) {
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

    public Split date(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * Split date.
     *
     * @return date
     **/
    @Schema(description = "Split date.")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Split fromFactor(Float fromFactor) {
        this.fromFactor = fromFactor;
        return this;
    }

    /**
     * From factor.
     *
     * @return fromFactor
     **/
    @Schema(description = "From factor.")
    public Float getFromFactor() {
        return fromFactor;
    }

    public void setFromFactor(Float fromFactor) {
        this.fromFactor = fromFactor;
    }

    public Split toFactor(Float toFactor) {
        this.toFactor = toFactor;
        return this;
    }

    /**
     * To factor.
     *
     * @return toFactor
     **/
    @Schema(description = "To factor.")
    public Float getToFactor() {
        return toFactor;
    }

    public void setToFactor(Float toFactor) {
        this.toFactor = toFactor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Split split = (Split) o;
        return Objects.equals(this.symbol, split.symbol) &&
                Objects.equals(this.date, split.date) &&
                Objects.equals(this.fromFactor, split.fromFactor) &&
                Objects.equals(this.toFactor, split.toFactor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, date, fromFactor, toFactor);
    }


    @Override
    public String toString() {

        String sb = "class Split {\n" +
                "    symbol: " + toIndentedString(symbol) + "\n" +
                "    date: " + toIndentedString(date) + "\n" +
                "    fromFactor: " + toIndentedString(fromFactor) + "\n" +
                "    toFactor: " + toIndentedString(toFactor) + "\n" +
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
