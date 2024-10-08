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
 * CompanyESG
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class CompanyESG {
    @SerializedName("symbol")
    private String symbol = null;

    @SerializedName("totalESGScore")
    private Float totalESGScore = null;

    @SerializedName("environmentScore")
    private Float environmentScore = null;

    @SerializedName("governanceScore")
    private Float governanceScore = null;

    @SerializedName("socialScore")
    private Float socialScore = null;

    @SerializedName("data")
    private CompanyESGMap data = null;

    public CompanyESG symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * symbol
     *
     * @return symbol
     **/
    @Schema(description = "symbol")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public CompanyESG totalESGScore(Float totalESGScore) {
        this.totalESGScore = totalESGScore;
        return this;
    }

    /**
     * Total ESG Score
     *
     * @return totalESGScore
     **/
    @Schema(description = "Total ESG Score")
    public Float getTotalESGScore() {
        return totalESGScore;
    }

    public void setTotalESGScore(Float totalESGScore) {
        this.totalESGScore = totalESGScore;
    }

    public CompanyESG environmentScore(Float environmentScore) {
        this.environmentScore = environmentScore;
        return this;
    }

    /**
     * Environment Score
     *
     * @return environmentScore
     **/
    @Schema(description = "Environment Score")
    public Float getEnvironmentScore() {
        return environmentScore;
    }

    public void setEnvironmentScore(Float environmentScore) {
        this.environmentScore = environmentScore;
    }

    public CompanyESG governanceScore(Float governanceScore) {
        this.governanceScore = governanceScore;
        return this;
    }

    /**
     * Governance Score
     *
     * @return governanceScore
     **/
    @Schema(description = "Governance Score")
    public Float getGovernanceScore() {
        return governanceScore;
    }

    public void setGovernanceScore(Float governanceScore) {
        this.governanceScore = governanceScore;
    }

    public CompanyESG socialScore(Float socialScore) {
        this.socialScore = socialScore;
        return this;
    }

    /**
     * Social Score
     *
     * @return socialScore
     **/
    @Schema(description = "Social Score")
    public Float getSocialScore() {
        return socialScore;
    }

    public void setSocialScore(Float socialScore) {
        this.socialScore = socialScore;
    }

    public CompanyESG data(CompanyESGMap data) {
        this.data = data;
        return this;
    }

    /**
     * Get data
     *
     * @return data
     **/
    @Schema(description = "")
    public CompanyESGMap getData() {
        return data;
    }

    public void setData(CompanyESGMap data) {
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
        CompanyESG companyESG = (CompanyESG) o;
        return Objects.equals(this.symbol, companyESG.symbol) &&
                Objects.equals(this.totalESGScore, companyESG.totalESGScore) &&
                Objects.equals(this.environmentScore, companyESG.environmentScore) &&
                Objects.equals(this.governanceScore, companyESG.governanceScore) &&
                Objects.equals(this.socialScore, companyESG.socialScore) &&
                Objects.equals(this.data, companyESG.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, totalESGScore, environmentScore, governanceScore, socialScore, data);
    }


    @Override
    public String toString() {

        String sb = "class CompanyESG {\n" +
                "    symbol: " + toIndentedString(symbol) + "\n" +
                "    totalESGScore: " + toIndentedString(totalESGScore) + "\n" +
                "    environmentScore: " + toIndentedString(environmentScore) + "\n" +
                "    governanceScore: " + toIndentedString(governanceScore) + "\n" +
                "    socialScore: " + toIndentedString(socialScore) + "\n" +
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
