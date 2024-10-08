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
 * MutualFundCountryExposureData
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class MutualFundCountryExposureData {
    @SerializedName("country")
    private String country = null;

    @SerializedName("exposure")
    private Float exposure = null;

    public MutualFundCountryExposureData country(String country) {
        this.country = country;
        return this;
    }

    /**
     * Country
     *
     * @return country
     **/
    @Schema(description = "Country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public MutualFundCountryExposureData exposure(Float exposure) {
        this.exposure = exposure;
        return this;
    }

    /**
     * Percent of exposure.
     *
     * @return exposure
     **/
    @Schema(description = "Percent of exposure.")
    public Float getExposure() {
        return exposure;
    }

    public void setExposure(Float exposure) {
        this.exposure = exposure;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MutualFundCountryExposureData mutualFundCountryExposureData = (MutualFundCountryExposureData) o;
        return Objects.equals(this.country, mutualFundCountryExposureData.country) &&
                Objects.equals(this.exposure, mutualFundCountryExposureData.exposure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, exposure);
    }


    @Override
    public String toString() {

        String sb = "class MutualFundCountryExposureData {\n" +
                "    country: " + toIndentedString(country) + "\n" +
                "    exposure: " + toIndentedString(exposure) + "\n" +
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
