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
 * PressRelease
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class PressRelease {
    @SerializedName("symbol")
    private String symbol = null;

    @SerializedName("majorDevelopment")
    private List<Development> majorDevelopment = null;

    public PressRelease symbol(String symbol) {
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

    public PressRelease majorDevelopment(List<Development> majorDevelopment) {
        this.majorDevelopment = majorDevelopment;
        return this;
    }

    public PressRelease addMajorDevelopmentItem(Development majorDevelopmentItem) {
        if (this.majorDevelopment == null) {
            this.majorDevelopment = new ArrayList<Development>();
        }
        this.majorDevelopment.add(majorDevelopmentItem);
        return this;
    }

    /**
     * Array of major developments.
     *
     * @return majorDevelopment
     **/
    @Schema(description = "Array of major developments.")
    public List<Development> getMajorDevelopment() {
        return majorDevelopment;
    }

    public void setMajorDevelopment(List<Development> majorDevelopment) {
        this.majorDevelopment = majorDevelopment;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PressRelease pressRelease = (PressRelease) o;
        return Objects.equals(this.symbol, pressRelease.symbol) &&
                Objects.equals(this.majorDevelopment, pressRelease.majorDevelopment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, majorDevelopment);
    }


    @Override
    public String toString() {

        String sb = "class PressRelease {\n" +
                "    symbol: " + toIndentedString(symbol) + "\n" +
                "    majorDevelopment: " + toIndentedString(majorDevelopment) + "\n" +
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
