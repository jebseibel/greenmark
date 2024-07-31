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
 * CountryMetadata
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class CountryMetadata {
    @SerializedName("country")
    private String country = null;

    @SerializedName("code2")
    private String code2 = null;

    @SerializedName("code3")
    private String code3 = null;

    @SerializedName("codeNo")
    private String codeNo = null;

    @SerializedName("currency")
    private String currency = null;

    @SerializedName("currencyCode")
    private String currencyCode = null;

    @SerializedName("region")
    private String region = null;

    @SerializedName("subRegion")
    private String subRegion = null;

    @SerializedName("rating")
    private String rating = null;

    @SerializedName("defaultSpread")
    private Float defaultSpread = null;

    @SerializedName("countryRiskPremium")
    private Float countryRiskPremium = null;

    @SerializedName("equityRiskPremium")
    private Float equityRiskPremium = null;

    public CountryMetadata country(String country) {
        this.country = country;
        return this;
    }

    /**
     * Country name
     *
     * @return country
     **/
    @Schema(description = "Country name")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CountryMetadata code2(String code2) {
        this.code2 = code2;
        return this;
    }

    /**
     * Alpha 2 code
     *
     * @return code2
     **/
    @Schema(description = "Alpha 2 code")
    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public CountryMetadata code3(String code3) {
        this.code3 = code3;
        return this;
    }

    /**
     * Alpha 3 code
     *
     * @return code3
     **/
    @Schema(description = "Alpha 3 code")
    public String getCode3() {
        return code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public CountryMetadata codeNo(String codeNo) {
        this.codeNo = codeNo;
        return this;
    }

    /**
     * UN code
     *
     * @return codeNo
     **/
    @Schema(description = "UN code")
    public String getCodeNo() {
        return codeNo;
    }

    public void setCodeNo(String codeNo) {
        this.codeNo = codeNo;
    }

    public CountryMetadata currency(String currency) {
        this.currency = currency;
        return this;
    }

    /**
     * Currency name
     *
     * @return currency
     **/
    @Schema(description = "Currency name")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public CountryMetadata currencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    /**
     * Currency code
     *
     * @return currencyCode
     **/
    @Schema(description = "Currency code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public CountryMetadata region(String region) {
        this.region = region;
        return this;
    }

    /**
     * Region
     *
     * @return region
     **/
    @Schema(description = "Region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public CountryMetadata subRegion(String subRegion) {
        this.subRegion = subRegion;
        return this;
    }

    /**
     * Sub-Region
     *
     * @return subRegion
     **/
    @Schema(description = "Sub-Region")
    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public CountryMetadata rating(String rating) {
        this.rating = rating;
        return this;
    }

    /**
     * Moody&#x27;s credit risk rating.
     *
     * @return rating
     **/
    @Schema(description = "Moody's credit risk rating.")
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public CountryMetadata defaultSpread(Float defaultSpread) {
        this.defaultSpread = defaultSpread;
        return this;
    }

    /**
     * Default spread
     *
     * @return defaultSpread
     **/
    @Schema(description = "Default spread")
    public Float getDefaultSpread() {
        return defaultSpread;
    }

    public void setDefaultSpread(Float defaultSpread) {
        this.defaultSpread = defaultSpread;
    }

    public CountryMetadata countryRiskPremium(Float countryRiskPremium) {
        this.countryRiskPremium = countryRiskPremium;
        return this;
    }

    /**
     * Country risk premium
     *
     * @return countryRiskPremium
     **/
    @Schema(description = "Country risk premium")
    public Float getCountryRiskPremium() {
        return countryRiskPremium;
    }

    public void setCountryRiskPremium(Float countryRiskPremium) {
        this.countryRiskPremium = countryRiskPremium;
    }

    public CountryMetadata equityRiskPremium(Float equityRiskPremium) {
        this.equityRiskPremium = equityRiskPremium;
        return this;
    }

    /**
     * Equity risk premium
     *
     * @return equityRiskPremium
     **/
    @Schema(description = "Equity risk premium")
    public Float getEquityRiskPremium() {
        return equityRiskPremium;
    }

    public void setEquityRiskPremium(Float equityRiskPremium) {
        this.equityRiskPremium = equityRiskPremium;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CountryMetadata countryMetadata = (CountryMetadata) o;
        return Objects.equals(this.country, countryMetadata.country) &&
                Objects.equals(this.code2, countryMetadata.code2) &&
                Objects.equals(this.code3, countryMetadata.code3) &&
                Objects.equals(this.codeNo, countryMetadata.codeNo) &&
                Objects.equals(this.currency, countryMetadata.currency) &&
                Objects.equals(this.currencyCode, countryMetadata.currencyCode) &&
                Objects.equals(this.region, countryMetadata.region) &&
                Objects.equals(this.subRegion, countryMetadata.subRegion) &&
                Objects.equals(this.rating, countryMetadata.rating) &&
                Objects.equals(this.defaultSpread, countryMetadata.defaultSpread) &&
                Objects.equals(this.countryRiskPremium, countryMetadata.countryRiskPremium) &&
                Objects.equals(this.equityRiskPremium, countryMetadata.equityRiskPremium);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, code2, code3, codeNo, currency, currencyCode, region, subRegion, rating, defaultSpread, countryRiskPremium, equityRiskPremium);
    }


    @Override
    public String toString() {

        String sb = "class CountryMetadata {\n" +
                "    country: " + toIndentedString(country) + "\n" +
                "    code2: " + toIndentedString(code2) + "\n" +
                "    code3: " + toIndentedString(code3) + "\n" +
                "    codeNo: " + toIndentedString(codeNo) + "\n" +
                "    currency: " + toIndentedString(currency) + "\n" +
                "    currencyCode: " + toIndentedString(currencyCode) + "\n" +
                "    region: " + toIndentedString(region) + "\n" +
                "    subRegion: " + toIndentedString(subRegion) + "\n" +
                "    rating: " + toIndentedString(rating) + "\n" +
                "    defaultSpread: " + toIndentedString(defaultSpread) + "\n" +
                "    countryRiskPremium: " + toIndentedString(countryRiskPremium) + "\n" +
                "    equityRiskPremium: " + toIndentedString(equityRiskPremium) + "\n" +
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
