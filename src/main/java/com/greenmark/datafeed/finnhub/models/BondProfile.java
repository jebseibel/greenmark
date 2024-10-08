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
 * BondProfile
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class BondProfile {
    @SerializedName("isin")
    private String isin = null;

    @SerializedName("cusip")
    private String cusip = null;

    @SerializedName("figi")
    private String figi = null;

    @SerializedName("coupon")
    private Float coupon = null;

    @SerializedName("maturityDate")
    private String maturityDate = null;

    @SerializedName("offeringPrice")
    private Float offeringPrice = null;

    @SerializedName("issueDate")
    private String issueDate = null;

    @SerializedName("bondType")
    private String bondType = null;

    @SerializedName("debtType")
    private String debtType = null;

    @SerializedName("industryGroup")
    private String industryGroup = null;

    @SerializedName("industrySubGroup")
    private String industrySubGroup = null;

    @SerializedName("asset")
    private String asset = null;

    @SerializedName("assetType")
    private String assetType = null;

    @SerializedName("datedDate")
    private String datedDate = null;

    @SerializedName("firstCouponDate")
    private String firstCouponDate = null;

    @SerializedName("originalOffering")
    private Float originalOffering = null;

    @SerializedName("amountOutstanding")
    private Float amountOutstanding = null;

    @SerializedName("paymentFrequency")
    private String paymentFrequency = null;

    @SerializedName("securityLevel")
    private String securityLevel = null;

    @SerializedName("callable")
    private Boolean callable = null;

    @SerializedName("couponType")
    private String couponType = null;

    public BondProfile isin(String isin) {
        this.isin = isin;
        return this;
    }

    /**
     * ISIN.
     *
     * @return isin
     **/
    @Schema(description = "ISIN.")
    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public BondProfile cusip(String cusip) {
        this.cusip = cusip;
        return this;
    }

    /**
     * Cusip.
     *
     * @return cusip
     **/
    @Schema(description = "Cusip.")
    public String getCusip() {
        return cusip;
    }

    public void setCusip(String cusip) {
        this.cusip = cusip;
    }

    public BondProfile figi(String figi) {
        this.figi = figi;
        return this;
    }

    /**
     * FIGI.
     *
     * @return figi
     **/
    @Schema(description = "FIGI.")
    public String getFigi() {
        return figi;
    }

    public void setFigi(String figi) {
        this.figi = figi;
    }

    public BondProfile coupon(Float coupon) {
        this.coupon = coupon;
        return this;
    }

    /**
     * Coupon.
     *
     * @return coupon
     **/
    @Schema(description = "Coupon.")
    public Float getCoupon() {
        return coupon;
    }

    public void setCoupon(Float coupon) {
        this.coupon = coupon;
    }

    public BondProfile maturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
        return this;
    }

    /**
     * Period.
     *
     * @return maturityDate
     **/
    @Schema(description = "Period.")
    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public BondProfile offeringPrice(Float offeringPrice) {
        this.offeringPrice = offeringPrice;
        return this;
    }

    /**
     * Offering price.
     *
     * @return offeringPrice
     **/
    @Schema(description = "Offering price.")
    public Float getOfferingPrice() {
        return offeringPrice;
    }

    public void setOfferingPrice(Float offeringPrice) {
        this.offeringPrice = offeringPrice;
    }

    public BondProfile issueDate(String issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    /**
     * Issue date.
     *
     * @return issueDate
     **/
    @Schema(description = "Issue date.")
    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public BondProfile bondType(String bondType) {
        this.bondType = bondType;
        return this;
    }

    /**
     * Bond type.
     *
     * @return bondType
     **/
    @Schema(description = "Bond type.")
    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public BondProfile debtType(String debtType) {
        this.debtType = debtType;
        return this;
    }

    /**
     * Bond type.
     *
     * @return debtType
     **/
    @Schema(description = "Bond type.")
    public String getDebtType() {
        return debtType;
    }

    public void setDebtType(String debtType) {
        this.debtType = debtType;
    }

    public BondProfile industryGroup(String industryGroup) {
        this.industryGroup = industryGroup;
        return this;
    }

    /**
     * Industry.
     *
     * @return industryGroup
     **/
    @Schema(description = "Industry.")
    public String getIndustryGroup() {
        return industryGroup;
    }

    public void setIndustryGroup(String industryGroup) {
        this.industryGroup = industryGroup;
    }

    public BondProfile industrySubGroup(String industrySubGroup) {
        this.industrySubGroup = industrySubGroup;
        return this;
    }

    /**
     * Sub-Industry.
     *
     * @return industrySubGroup
     **/
    @Schema(description = "Sub-Industry.")
    public String getIndustrySubGroup() {
        return industrySubGroup;
    }

    public void setIndustrySubGroup(String industrySubGroup) {
        this.industrySubGroup = industrySubGroup;
    }

    public BondProfile asset(String asset) {
        this.asset = asset;
        return this;
    }

    /**
     * Asset.
     *
     * @return asset
     **/
    @Schema(description = "Asset.")
    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public BondProfile assetType(String assetType) {
        this.assetType = assetType;
        return this;
    }

    /**
     * Asset.
     *
     * @return assetType
     **/
    @Schema(description = "Asset.")
    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public BondProfile datedDate(String datedDate) {
        this.datedDate = datedDate;
        return this;
    }

    /**
     * Dated date.
     *
     * @return datedDate
     **/
    @Schema(description = "Dated date.")
    public String getDatedDate() {
        return datedDate;
    }

    public void setDatedDate(String datedDate) {
        this.datedDate = datedDate;
    }

    public BondProfile firstCouponDate(String firstCouponDate) {
        this.firstCouponDate = firstCouponDate;
        return this;
    }

    /**
     * First coupon date.
     *
     * @return firstCouponDate
     **/
    @Schema(description = "First coupon date.")
    public String getFirstCouponDate() {
        return firstCouponDate;
    }

    public void setFirstCouponDate(String firstCouponDate) {
        this.firstCouponDate = firstCouponDate;
    }

    public BondProfile originalOffering(Float originalOffering) {
        this.originalOffering = originalOffering;
        return this;
    }

    /**
     * Offering amount.
     *
     * @return originalOffering
     **/
    @Schema(description = "Offering amount.")
    public Float getOriginalOffering() {
        return originalOffering;
    }

    public void setOriginalOffering(Float originalOffering) {
        this.originalOffering = originalOffering;
    }

    public BondProfile amountOutstanding(Float amountOutstanding) {
        this.amountOutstanding = amountOutstanding;
        return this;
    }

    /**
     * Outstanding amount.
     *
     * @return amountOutstanding
     **/
    @Schema(description = "Outstanding amount.")
    public Float getAmountOutstanding() {
        return amountOutstanding;
    }

    public void setAmountOutstanding(Float amountOutstanding) {
        this.amountOutstanding = amountOutstanding;
    }

    public BondProfile paymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
        return this;
    }

    /**
     * Payment frequency.
     *
     * @return paymentFrequency
     **/
    @Schema(description = "Payment frequency.")
    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public BondProfile securityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
        return this;
    }

    /**
     * Security level.
     *
     * @return securityLevel
     **/
    @Schema(description = "Security level.")
    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public BondProfile callable(Boolean callable) {
        this.callable = callable;
        return this;
    }

    /**
     * Callable.
     *
     * @return callable
     **/
    @Schema(description = "Callable.")
    public Boolean isCallable() {
        return callable;
    }

    public void setCallable(Boolean callable) {
        this.callable = callable;
    }

    public BondProfile couponType(String couponType) {
        this.couponType = couponType;
        return this;
    }

    /**
     * Coupon type.
     *
     * @return couponType
     **/
    @Schema(description = "Coupon type.")
    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BondProfile bondProfile = (BondProfile) o;
        return Objects.equals(this.isin, bondProfile.isin) &&
                Objects.equals(this.cusip, bondProfile.cusip) &&
                Objects.equals(this.figi, bondProfile.figi) &&
                Objects.equals(this.coupon, bondProfile.coupon) &&
                Objects.equals(this.maturityDate, bondProfile.maturityDate) &&
                Objects.equals(this.offeringPrice, bondProfile.offeringPrice) &&
                Objects.equals(this.issueDate, bondProfile.issueDate) &&
                Objects.equals(this.bondType, bondProfile.bondType) &&
                Objects.equals(this.debtType, bondProfile.debtType) &&
                Objects.equals(this.industryGroup, bondProfile.industryGroup) &&
                Objects.equals(this.industrySubGroup, bondProfile.industrySubGroup) &&
                Objects.equals(this.asset, bondProfile.asset) &&
                Objects.equals(this.assetType, bondProfile.assetType) &&
                Objects.equals(this.datedDate, bondProfile.datedDate) &&
                Objects.equals(this.firstCouponDate, bondProfile.firstCouponDate) &&
                Objects.equals(this.originalOffering, bondProfile.originalOffering) &&
                Objects.equals(this.amountOutstanding, bondProfile.amountOutstanding) &&
                Objects.equals(this.paymentFrequency, bondProfile.paymentFrequency) &&
                Objects.equals(this.securityLevel, bondProfile.securityLevel) &&
                Objects.equals(this.callable, bondProfile.callable) &&
                Objects.equals(this.couponType, bondProfile.couponType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isin, cusip, figi, coupon, maturityDate, offeringPrice, issueDate, bondType, debtType, industryGroup, industrySubGroup, asset, assetType, datedDate, firstCouponDate, originalOffering, amountOutstanding, paymentFrequency, securityLevel, callable, couponType);
    }


    @Override
    public String toString() {

        String sb = "class BondProfile {\n" +
                "    isin: " + toIndentedString(isin) + "\n" +
                "    cusip: " + toIndentedString(cusip) + "\n" +
                "    figi: " + toIndentedString(figi) + "\n" +
                "    coupon: " + toIndentedString(coupon) + "\n" +
                "    maturityDate: " + toIndentedString(maturityDate) + "\n" +
                "    offeringPrice: " + toIndentedString(offeringPrice) + "\n" +
                "    issueDate: " + toIndentedString(issueDate) + "\n" +
                "    bondType: " + toIndentedString(bondType) + "\n" +
                "    debtType: " + toIndentedString(debtType) + "\n" +
                "    industryGroup: " + toIndentedString(industryGroup) + "\n" +
                "    industrySubGroup: " + toIndentedString(industrySubGroup) + "\n" +
                "    asset: " + toIndentedString(asset) + "\n" +
                "    assetType: " + toIndentedString(assetType) + "\n" +
                "    datedDate: " + toIndentedString(datedDate) + "\n" +
                "    firstCouponDate: " + toIndentedString(firstCouponDate) + "\n" +
                "    originalOffering: " + toIndentedString(originalOffering) + "\n" +
                "    amountOutstanding: " + toIndentedString(amountOutstanding) + "\n" +
                "    paymentFrequency: " + toIndentedString(paymentFrequency) + "\n" +
                "    securityLevel: " + toIndentedString(securityLevel) + "\n" +
                "    callable: " + toIndentedString(callable) + "\n" +
                "    couponType: " + toIndentedString(couponType) + "\n" +
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
