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
 * EconomicDataInfo
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class EconomicDataInfo {
    @SerializedName("date")
    private String date = null;

    @SerializedName("value")
    private Float value = null;

    public EconomicDataInfo date(String date) {
        this.date = date;
        return this;
    }

    /**
     * Date of the reading
     *
     * @return date
     **/
    @Schema(description = "Date of the reading")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public EconomicDataInfo value(Float value) {
        this.value = value;
        return this;
    }

    /**
     * Value
     *
     * @return value
     **/
    @Schema(description = "Value")
    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EconomicDataInfo economicDataInfo = (EconomicDataInfo) o;
        return Objects.equals(this.date, economicDataInfo.date) &&
                Objects.equals(this.value, economicDataInfo.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, value);
    }


    @Override
    public String toString() {

        String sb = "class EconomicDataInfo {\n" +
                "    date: " + toIndentedString(date) + "\n" +
                "    value: " + toIndentedString(value) + "\n" +
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
