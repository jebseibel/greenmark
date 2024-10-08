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
 * InstitutionalOwnershipInfo
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class InstitutionalOwnershipInfo {
    @SerializedName("cik")
    private String cik = null;

    @SerializedName("name")
    private String name = null;

    @SerializedName("putCall")
    private String putCall = null;

    @SerializedName("change")
    private Float change = null;

    @SerializedName("noVoting")
    private Float noVoting = null;

    @SerializedName("percentage")
    private Float percentage = null;

    @SerializedName("share")
    private Float share = null;

    @SerializedName("sharedVoting")
    private Float sharedVoting = null;

    @SerializedName("soleVoting")
    private Float soleVoting = null;

    @SerializedName("value")
    private Float value = null;

    public InstitutionalOwnershipInfo cik(String cik) {
        this.cik = cik;
        return this;
    }

    /**
     * Investor&#x27;s company CIK.
     *
     * @return cik
     **/
    @Schema(description = "Investor's company CIK.")
    public String getCik() {
        return cik;
    }

    public void setCik(String cik) {
        this.cik = cik;
    }

    public InstitutionalOwnershipInfo name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Firm&#x27;s name.
     *
     * @return name
     **/
    @Schema(description = "Firm's name.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InstitutionalOwnershipInfo putCall(String putCall) {
        this.putCall = putCall;
        return this;
    }

    /**
     * &lt;code&gt;put&lt;/code&gt; or &lt;code&gt;call&lt;/code&gt; for options.
     *
     * @return putCall
     **/
    @Schema(description = "<code>put</code> or <code>call</code> for options.")
    public String getPutCall() {
        return putCall;
    }

    public void setPutCall(String putCall) {
        this.putCall = putCall;
    }

    public InstitutionalOwnershipInfo change(Float change) {
        this.change = change;
        return this;
    }

    /**
     * Number of shares change.
     *
     * @return change
     **/
    @Schema(description = "Number of shares change.")
    public Float getChange() {
        return change;
    }

    public void setChange(Float change) {
        this.change = change;
    }

    public InstitutionalOwnershipInfo noVoting(Float noVoting) {
        this.noVoting = noVoting;
        return this;
    }

    /**
     * Number of shares with no voting rights.
     *
     * @return noVoting
     **/
    @Schema(description = "Number of shares with no voting rights.")
    public Float getNoVoting() {
        return noVoting;
    }

    public void setNoVoting(Float noVoting) {
        this.noVoting = noVoting;
    }

    public InstitutionalOwnershipInfo percentage(Float percentage) {
        this.percentage = percentage;
        return this;
    }

    /**
     * Percentage of portfolio.
     *
     * @return percentage
     **/
    @Schema(description = "Percentage of portfolio.")
    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public InstitutionalOwnershipInfo share(Float share) {
        this.share = share;
        return this;
    }

    /**
     * News score.
     *
     * @return share
     **/
    @Schema(description = "News score.")
    public Float getShare() {
        return share;
    }

    public void setShare(Float share) {
        this.share = share;
    }

    public InstitutionalOwnershipInfo sharedVoting(Float sharedVoting) {
        this.sharedVoting = sharedVoting;
        return this;
    }

    /**
     * Number of shares with shared voting rights.
     *
     * @return sharedVoting
     **/
    @Schema(description = "Number of shares with shared voting rights.")
    public Float getSharedVoting() {
        return sharedVoting;
    }

    public void setSharedVoting(Float sharedVoting) {
        this.sharedVoting = sharedVoting;
    }

    public InstitutionalOwnershipInfo soleVoting(Float soleVoting) {
        this.soleVoting = soleVoting;
        return this;
    }

    /**
     * Number of shares with sole voting rights.
     *
     * @return soleVoting
     **/
    @Schema(description = "Number of shares with sole voting rights.")
    public Float getSoleVoting() {
        return soleVoting;
    }

    public void setSoleVoting(Float soleVoting) {
        this.soleVoting = soleVoting;
    }

    public InstitutionalOwnershipInfo value(Float value) {
        this.value = value;
        return this;
    }

    /**
     * PositionDb value.
     *
     * @return value
     **/
    @Schema(description = "PositionDb value.")
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
        InstitutionalOwnershipInfo institutionalOwnershipInfo = (InstitutionalOwnershipInfo) o;
        return Objects.equals(this.cik, institutionalOwnershipInfo.cik) &&
                Objects.equals(this.name, institutionalOwnershipInfo.name) &&
                Objects.equals(this.putCall, institutionalOwnershipInfo.putCall) &&
                Objects.equals(this.change, institutionalOwnershipInfo.change) &&
                Objects.equals(this.noVoting, institutionalOwnershipInfo.noVoting) &&
                Objects.equals(this.percentage, institutionalOwnershipInfo.percentage) &&
                Objects.equals(this.share, institutionalOwnershipInfo.share) &&
                Objects.equals(this.sharedVoting, institutionalOwnershipInfo.sharedVoting) &&
                Objects.equals(this.soleVoting, institutionalOwnershipInfo.soleVoting) &&
                Objects.equals(this.value, institutionalOwnershipInfo.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cik, name, putCall, change, noVoting, percentage, share, sharedVoting, soleVoting, value);
    }


    @Override
    public String toString() {

        String sb = "class InstitutionalOwnershipInfo {\n" +
                "    cik: " + toIndentedString(cik) + "\n" +
                "    name: " + toIndentedString(name) + "\n" +
                "    putCall: " + toIndentedString(putCall) + "\n" +
                "    change: " + toIndentedString(change) + "\n" +
                "    noVoting: " + toIndentedString(noVoting) + "\n" +
                "    percentage: " + toIndentedString(percentage) + "\n" +
                "    share: " + toIndentedString(share) + "\n" +
                "    sharedVoting: " + toIndentedString(sharedVoting) + "\n" +
                "    soleVoting: " + toIndentedString(soleVoting) + "\n" +
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
