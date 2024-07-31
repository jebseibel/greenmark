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
 * KeyCustomersSuppliers
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class KeyCustomersSuppliers {
    @SerializedName("symbol")
    private String symbol = null;

    @SerializedName("name")
    private String name = null;

    @SerializedName("country")
    private String country = null;

    @SerializedName("industry")
    private String industry = null;

    @SerializedName("customer")
    private Boolean customer = null;

    @SerializedName("supplier")
    private Boolean supplier = null;

    @SerializedName("oneMonthCorrelation")
    private Float oneMonthCorrelation = null;

    @SerializedName("oneYearCorrelation")
    private Float oneYearCorrelation = null;

    @SerializedName("sixMonthCorrelation")
    private Float sixMonthCorrelation = null;

    @SerializedName("threeMonthCorrelation")
    private Float threeMonthCorrelation = null;

    @SerializedName("twoWeekCorrelation")
    private Float twoWeekCorrelation = null;

    @SerializedName("twoYearCorrelation")
    private Float twoYearCorrelation = null;

    public KeyCustomersSuppliers symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * Symbol
     *
     * @return symbol
     **/
    @Schema(description = "Symbol")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public KeyCustomersSuppliers name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Name
     *
     * @return name
     **/
    @Schema(description = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KeyCustomersSuppliers country(String country) {
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

    public KeyCustomersSuppliers industry(String industry) {
        this.industry = industry;
        return this;
    }

    /**
     * Industry
     *
     * @return industry
     **/
    @Schema(description = "Industry")
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public KeyCustomersSuppliers customer(Boolean customer) {
        this.customer = customer;
        return this;
    }

    /**
     * Whether the company is a customer.
     *
     * @return customer
     **/
    @Schema(description = "Whether the company is a customer.")
    public Boolean isCustomer() {
        return customer;
    }

    public void setCustomer(Boolean customer) {
        this.customer = customer;
    }

    public KeyCustomersSuppliers supplier(Boolean supplier) {
        this.supplier = supplier;
        return this;
    }

    /**
     * Whether the company is a supplier
     *
     * @return supplier
     **/
    @Schema(description = "Whether the company is a supplier")
    public Boolean isSupplier() {
        return supplier;
    }

    public void setSupplier(Boolean supplier) {
        this.supplier = supplier;
    }

    public KeyCustomersSuppliers oneMonthCorrelation(Float oneMonthCorrelation) {
        this.oneMonthCorrelation = oneMonthCorrelation;
        return this;
    }

    /**
     * 1-month price correlation
     *
     * @return oneMonthCorrelation
     **/
    @Schema(description = "1-month price correlation")
    public Float getOneMonthCorrelation() {
        return oneMonthCorrelation;
    }

    public void setOneMonthCorrelation(Float oneMonthCorrelation) {
        this.oneMonthCorrelation = oneMonthCorrelation;
    }

    public KeyCustomersSuppliers oneYearCorrelation(Float oneYearCorrelation) {
        this.oneYearCorrelation = oneYearCorrelation;
        return this;
    }

    /**
     * 1-year price correlation
     *
     * @return oneYearCorrelation
     **/
    @Schema(description = "1-year price correlation")
    public Float getOneYearCorrelation() {
        return oneYearCorrelation;
    }

    public void setOneYearCorrelation(Float oneYearCorrelation) {
        this.oneYearCorrelation = oneYearCorrelation;
    }

    public KeyCustomersSuppliers sixMonthCorrelation(Float sixMonthCorrelation) {
        this.sixMonthCorrelation = sixMonthCorrelation;
        return this;
    }

    /**
     * 6-month price correlation
     *
     * @return sixMonthCorrelation
     **/
    @Schema(description = "6-month price correlation")
    public Float getSixMonthCorrelation() {
        return sixMonthCorrelation;
    }

    public void setSixMonthCorrelation(Float sixMonthCorrelation) {
        this.sixMonthCorrelation = sixMonthCorrelation;
    }

    public KeyCustomersSuppliers threeMonthCorrelation(Float threeMonthCorrelation) {
        this.threeMonthCorrelation = threeMonthCorrelation;
        return this;
    }

    /**
     * 3-month price correlation
     *
     * @return threeMonthCorrelation
     **/
    @Schema(description = "3-month price correlation")
    public Float getThreeMonthCorrelation() {
        return threeMonthCorrelation;
    }

    public void setThreeMonthCorrelation(Float threeMonthCorrelation) {
        this.threeMonthCorrelation = threeMonthCorrelation;
    }

    public KeyCustomersSuppliers twoWeekCorrelation(Float twoWeekCorrelation) {
        this.twoWeekCorrelation = twoWeekCorrelation;
        return this;
    }

    /**
     * 2-week price correlation
     *
     * @return twoWeekCorrelation
     **/
    @Schema(description = "2-week price correlation")
    public Float getTwoWeekCorrelation() {
        return twoWeekCorrelation;
    }

    public void setTwoWeekCorrelation(Float twoWeekCorrelation) {
        this.twoWeekCorrelation = twoWeekCorrelation;
    }

    public KeyCustomersSuppliers twoYearCorrelation(Float twoYearCorrelation) {
        this.twoYearCorrelation = twoYearCorrelation;
        return this;
    }

    /**
     * 2-year price correlation
     *
     * @return twoYearCorrelation
     **/
    @Schema(description = "2-year price correlation")
    public Float getTwoYearCorrelation() {
        return twoYearCorrelation;
    }

    public void setTwoYearCorrelation(Float twoYearCorrelation) {
        this.twoYearCorrelation = twoYearCorrelation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KeyCustomersSuppliers keyCustomersSuppliers = (KeyCustomersSuppliers) o;
        return Objects.equals(this.symbol, keyCustomersSuppliers.symbol) &&
                Objects.equals(this.name, keyCustomersSuppliers.name) &&
                Objects.equals(this.country, keyCustomersSuppliers.country) &&
                Objects.equals(this.industry, keyCustomersSuppliers.industry) &&
                Objects.equals(this.customer, keyCustomersSuppliers.customer) &&
                Objects.equals(this.supplier, keyCustomersSuppliers.supplier) &&
                Objects.equals(this.oneMonthCorrelation, keyCustomersSuppliers.oneMonthCorrelation) &&
                Objects.equals(this.oneYearCorrelation, keyCustomersSuppliers.oneYearCorrelation) &&
                Objects.equals(this.sixMonthCorrelation, keyCustomersSuppliers.sixMonthCorrelation) &&
                Objects.equals(this.threeMonthCorrelation, keyCustomersSuppliers.threeMonthCorrelation) &&
                Objects.equals(this.twoWeekCorrelation, keyCustomersSuppliers.twoWeekCorrelation) &&
                Objects.equals(this.twoYearCorrelation, keyCustomersSuppliers.twoYearCorrelation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, name, country, industry, customer, supplier, oneMonthCorrelation, oneYearCorrelation, sixMonthCorrelation, threeMonthCorrelation, twoWeekCorrelation, twoYearCorrelation);
    }


    @Override
    public String toString() {

        String sb = "class KeyCustomersSuppliers {\n" +
                "    symbol: " + toIndentedString(symbol) + "\n" +
                "    name: " + toIndentedString(name) + "\n" +
                "    country: " + toIndentedString(country) + "\n" +
                "    industry: " + toIndentedString(industry) + "\n" +
                "    customer: " + toIndentedString(customer) + "\n" +
                "    supplier: " + toIndentedString(supplier) + "\n" +
                "    oneMonthCorrelation: " + toIndentedString(oneMonthCorrelation) + "\n" +
                "    oneYearCorrelation: " + toIndentedString(oneYearCorrelation) + "\n" +
                "    sixMonthCorrelation: " + toIndentedString(sixMonthCorrelation) + "\n" +
                "    threeMonthCorrelation: " + toIndentedString(threeMonthCorrelation) + "\n" +
                "    twoWeekCorrelation: " + toIndentedString(twoWeekCorrelation) + "\n" +
                "    twoYearCorrelation: " + toIndentedString(twoYearCorrelation) + "\n" +
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
