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
 * BreakdownItem
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class BreakdownItem {
    @SerializedName("accessNumber")
    private String accessNumber = null;

    @SerializedName("breakdown")
    private BreakdownItemMap breakdown = null;

    public BreakdownItem accessNumber(String accessNumber) {
        this.accessNumber = accessNumber;
        return this;
    }

    /**
     * Access number of the report from which the data is sourced.
     *
     * @return accessNumber
     **/
    @Schema(description = "Access number of the report from which the data is sourced.")
    public String getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(String accessNumber) {
        this.accessNumber = accessNumber;
    }

    public BreakdownItem breakdown(BreakdownItemMap breakdown) {
        this.breakdown = breakdown;
        return this;
    }

    /**
     * Get breakdown
     *
     * @return breakdown
     **/
    @Schema(description = "")
    public BreakdownItemMap getBreakdown() {
        return breakdown;
    }

    public void setBreakdown(BreakdownItemMap breakdown) {
        this.breakdown = breakdown;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BreakdownItem breakdownItem = (BreakdownItem) o;
        return Objects.equals(this.accessNumber, breakdownItem.accessNumber) &&
                Objects.equals(this.breakdown, breakdownItem.breakdown);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessNumber, breakdown);
    }


    @Override
    public String toString() {

        String sb = "class BreakdownItem {\n" +
                "    accessNumber: " + toIndentedString(accessNumber) + "\n" +
                "    breakdown: " + toIndentedString(breakdown) + "\n" +
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
