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
 * UsptoPatent
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class UsptoPatent {
    @SerializedName("applicationNumber")
    private String applicationNumber = null;

    @SerializedName("companyFilingName")
    private List<String> companyFilingName = null;

    @SerializedName("filingDate")
    private String filingDate = null;

    @SerializedName("description")
    private String description = null;

    @SerializedName("filingStatus")
    private String filingStatus = null;

    @SerializedName("patentNumber")
    private String patentNumber = null;

    @SerializedName("publicationDate")
    private String publicationDate = null;

    @SerializedName("patentType")
    private String patentType = null;

    @SerializedName("url")
    private String url = null;

    public UsptoPatent applicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
        return this;
    }

    /**
     * Application Number.
     *
     * @return applicationNumber
     **/
    @Schema(description = "Application Number.")
    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public UsptoPatent companyFilingName(List<String> companyFilingName) {
        this.companyFilingName = companyFilingName;
        return this;
    }

    public UsptoPatent addCompanyFilingNameItem(String companyFilingNameItem) {
        if (this.companyFilingName == null) {
            this.companyFilingName = new ArrayList<String>();
        }
        this.companyFilingName.add(companyFilingNameItem);
        return this;
    }

    /**
     * Array of companies&#x27; name on the patent.
     *
     * @return companyFilingName
     **/
    @Schema(description = "Array of companies' name on the patent.")
    public List<String> getCompanyFilingName() {
        return companyFilingName;
    }

    public void setCompanyFilingName(List<String> companyFilingName) {
        this.companyFilingName = companyFilingName;
    }

    public UsptoPatent filingDate(String filingDate) {
        this.filingDate = filingDate;
        return this;
    }

    /**
     * Filing date.
     *
     * @return filingDate
     **/
    @Schema(description = "Filing date.")
    public String getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(String filingDate) {
        this.filingDate = filingDate;
    }

    public UsptoPatent description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Description.
     *
     * @return description
     **/
    @Schema(description = "Description.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UsptoPatent filingStatus(String filingStatus) {
        this.filingStatus = filingStatus;
        return this;
    }

    /**
     * Filing status.
     *
     * @return filingStatus
     **/
    @Schema(description = "Filing status.")
    public String getFilingStatus() {
        return filingStatus;
    }

    public void setFilingStatus(String filingStatus) {
        this.filingStatus = filingStatus;
    }

    public UsptoPatent patentNumber(String patentNumber) {
        this.patentNumber = patentNumber;
        return this;
    }

    /**
     * Patent number.
     *
     * @return patentNumber
     **/
    @Schema(description = "Patent number.")
    public String getPatentNumber() {
        return patentNumber;
    }

    public void setPatentNumber(String patentNumber) {
        this.patentNumber = patentNumber;
    }

    public UsptoPatent publicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    /**
     * Publication date.
     *
     * @return publicationDate
     **/
    @Schema(description = "Publication date.")
    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public UsptoPatent patentType(String patentType) {
        this.patentType = patentType;
        return this;
    }

    /**
     * Patent&#x27;s type.
     *
     * @return patentType
     **/
    @Schema(description = "Patent's type.")
    public String getPatentType() {
        return patentType;
    }

    public void setPatentType(String patentType) {
        this.patentType = patentType;
    }

    public UsptoPatent url(String url) {
        this.url = url;
        return this;
    }

    /**
     * URL of the original article.
     *
     * @return url
     **/
    @Schema(description = "URL of the original article.")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsptoPatent usptoPatent = (UsptoPatent) o;
        return Objects.equals(this.applicationNumber, usptoPatent.applicationNumber) &&
                Objects.equals(this.companyFilingName, usptoPatent.companyFilingName) &&
                Objects.equals(this.filingDate, usptoPatent.filingDate) &&
                Objects.equals(this.description, usptoPatent.description) &&
                Objects.equals(this.filingStatus, usptoPatent.filingStatus) &&
                Objects.equals(this.patentNumber, usptoPatent.patentNumber) &&
                Objects.equals(this.publicationDate, usptoPatent.publicationDate) &&
                Objects.equals(this.patentType, usptoPatent.patentType) &&
                Objects.equals(this.url, usptoPatent.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationNumber, companyFilingName, filingDate, description, filingStatus, patentNumber, publicationDate, patentType, url);
    }


    @Override
    public String toString() {

        String sb = "class UsptoPatent {\n" +
                "    applicationNumber: " + toIndentedString(applicationNumber) + "\n" +
                "    companyFilingName: " + toIndentedString(companyFilingName) + "\n" +
                "    filingDate: " + toIndentedString(filingDate) + "\n" +
                "    description: " + toIndentedString(description) + "\n" +
                "    filingStatus: " + toIndentedString(filingStatus) + "\n" +
                "    patentNumber: " + toIndentedString(patentNumber) + "\n" +
                "    publicationDate: " + toIndentedString(publicationDate) + "\n" +
                "    patentType: " + toIndentedString(patentType) + "\n" +
                "    url: " + toIndentedString(url) + "\n" +
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
