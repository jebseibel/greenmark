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
 * SimilarityIndexInfo
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class SimilarityIndexInfo {
    @SerializedName("cik")
    private String cik = null;

    @SerializedName("item1")
    private Float item1 = null;

    @SerializedName("item1a")
    private Float item1a = null;

    @SerializedName("item2")
    private Float item2 = null;

    @SerializedName("item7")
    private Float item7 = null;

    @SerializedName("item7a")
    private Float item7a = null;

    @SerializedName("accessNumber")
    private String accessNumber = null;

    @SerializedName("form")
    private String form = null;

    @SerializedName("filedDate")
    private String filedDate = null;

    @SerializedName("acceptedDate")
    private String acceptedDate = null;

    @SerializedName("reportUrl")
    private String reportUrl = null;

    @SerializedName("filingUrl")
    private String filingUrl = null;

    public SimilarityIndexInfo cik(String cik) {
        this.cik = cik;
        return this;
    }

    /**
     * CIK.
     *
     * @return cik
     **/
    @Schema(description = "CIK.")
    public String getCik() {
        return cik;
    }

    public void setCik(String cik) {
        this.cik = cik;
    }

    public SimilarityIndexInfo item1(Float item1) {
        this.item1 = item1;
        return this;
    }

    /**
     * Cosine similarity of Item 1 (Business). This number is only available for Annual reports.
     *
     * @return item1
     **/
    @Schema(description = "Cosine similarity of Item 1 (Business). This number is only available for Annual reports.")
    public Float getItem1() {
        return item1;
    }

    public void setItem1(Float item1) {
        this.item1 = item1;
    }

    public SimilarityIndexInfo item1a(Float item1a) {
        this.item1a = item1a;
        return this;
    }

    /**
     * Cosine similarity of Item 1A (Risk Factors). This number is available for both Annual and Quarterly reports.
     *
     * @return item1a
     **/
    @Schema(description = "Cosine similarity of Item 1A (Risk Factors). This number is available for both Annual and Quarterly reports.")
    public Float getItem1a() {
        return item1a;
    }

    public void setItem1a(Float item1a) {
        this.item1a = item1a;
    }

    public SimilarityIndexInfo item2(Float item2) {
        this.item2 = item2;
        return this;
    }

    /**
     * Cosine similarity of Item 2 (Management’s Discussion and Analysis of Financial Condition and Results of Operations). This number is only available for Quarterly reports.
     *
     * @return item2
     **/
    @Schema(description = "Cosine similarity of Item 2 (Management’s Discussion and Analysis of Financial Condition and Results of Operations). This number is only available for Quarterly reports.")
    public Float getItem2() {
        return item2;
    }

    public void setItem2(Float item2) {
        this.item2 = item2;
    }

    public SimilarityIndexInfo item7(Float item7) {
        this.item7 = item7;
        return this;
    }

    /**
     * Cosine similarity of Item 7 (Management’s Discussion and Analysis of Financial Condition and Results of Operations). This number is only available for Annual reports.
     *
     * @return item7
     **/
    @Schema(description = "Cosine similarity of Item 7 (Management’s Discussion and Analysis of Financial Condition and Results of Operations). This number is only available for Annual reports.")
    public Float getItem7() {
        return item7;
    }

    public void setItem7(Float item7) {
        this.item7 = item7;
    }

    public SimilarityIndexInfo item7a(Float item7a) {
        this.item7a = item7a;
        return this;
    }

    /**
     * Cosine similarity of Item 7A (Quantitative and Qualitative Disclosures About Market Risk). This number is only available for Annual reports.
     *
     * @return item7a
     **/
    @Schema(description = "Cosine similarity of Item 7A (Quantitative and Qualitative Disclosures About Market Risk). This number is only available for Annual reports.")
    public Float getItem7a() {
        return item7a;
    }

    public void setItem7a(Float item7a) {
        this.item7a = item7a;
    }

    public SimilarityIndexInfo accessNumber(String accessNumber) {
        this.accessNumber = accessNumber;
        return this;
    }

    /**
     * Access number.
     *
     * @return accessNumber
     **/
    @Schema(description = "Access number.")
    public String getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(String accessNumber) {
        this.accessNumber = accessNumber;
    }

    public SimilarityIndexInfo form(String form) {
        this.form = form;
        return this;
    }

    /**
     * Form type.
     *
     * @return form
     **/
    @Schema(description = "Form type.")
    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public SimilarityIndexInfo filedDate(String filedDate) {
        this.filedDate = filedDate;
        return this;
    }

    /**
     * Filed date &lt;code&gt;%Y-%m-%d %H:%M:%S&lt;/code&gt;.
     *
     * @return filedDate
     **/
    @Schema(description = "Filed date <code>%Y-%m-%d %H:%M:%S</code>.")
    public String getFiledDate() {
        return filedDate;
    }

    public void setFiledDate(String filedDate) {
        this.filedDate = filedDate;
    }

    public SimilarityIndexInfo acceptedDate(String acceptedDate) {
        this.acceptedDate = acceptedDate;
        return this;
    }

    /**
     * Accepted date &lt;code&gt;%Y-%m-%d %H:%M:%S&lt;/code&gt;.
     *
     * @return acceptedDate
     **/
    @Schema(description = "Accepted date <code>%Y-%m-%d %H:%M:%S</code>.")
    public String getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(String acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public SimilarityIndexInfo reportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
        return this;
    }

    /**
     * Report&#x27;s URL.
     *
     * @return reportUrl
     **/
    @Schema(description = "Report's URL.")
    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public SimilarityIndexInfo filingUrl(String filingUrl) {
        this.filingUrl = filingUrl;
        return this;
    }

    /**
     * Filing&#x27;s URL.
     *
     * @return filingUrl
     **/
    @Schema(description = "Filing's URL.")
    public String getFilingUrl() {
        return filingUrl;
    }

    public void setFilingUrl(String filingUrl) {
        this.filingUrl = filingUrl;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimilarityIndexInfo similarityIndexInfo = (SimilarityIndexInfo) o;
        return Objects.equals(this.cik, similarityIndexInfo.cik) &&
                Objects.equals(this.item1, similarityIndexInfo.item1) &&
                Objects.equals(this.item1a, similarityIndexInfo.item1a) &&
                Objects.equals(this.item2, similarityIndexInfo.item2) &&
                Objects.equals(this.item7, similarityIndexInfo.item7) &&
                Objects.equals(this.item7a, similarityIndexInfo.item7a) &&
                Objects.equals(this.accessNumber, similarityIndexInfo.accessNumber) &&
                Objects.equals(this.form, similarityIndexInfo.form) &&
                Objects.equals(this.filedDate, similarityIndexInfo.filedDate) &&
                Objects.equals(this.acceptedDate, similarityIndexInfo.acceptedDate) &&
                Objects.equals(this.reportUrl, similarityIndexInfo.reportUrl) &&
                Objects.equals(this.filingUrl, similarityIndexInfo.filingUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cik, item1, item1a, item2, item7, item7a, accessNumber, form, filedDate, acceptedDate, reportUrl, filingUrl);
    }


    @Override
    public String toString() {

        String sb = "class SimilarityIndexInfo {\n" +
                "    cik: " + toIndentedString(cik) + "\n" +
                "    item1: " + toIndentedString(item1) + "\n" +
                "    item1a: " + toIndentedString(item1a) + "\n" +
                "    item2: " + toIndentedString(item2) + "\n" +
                "    item7: " + toIndentedString(item7) + "\n" +
                "    item7a: " + toIndentedString(item7a) + "\n" +
                "    accessNumber: " + toIndentedString(accessNumber) + "\n" +
                "    form: " + toIndentedString(form) + "\n" +
                "    filedDate: " + toIndentedString(filedDate) + "\n" +
                "    acceptedDate: " + toIndentedString(acceptedDate) + "\n" +
                "    reportUrl: " + toIndentedString(reportUrl) + "\n" +
                "    filingUrl: " + toIndentedString(filingUrl) + "\n" +
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
