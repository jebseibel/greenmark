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
 * VisaApplication
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class VisaApplication {
    @SerializedName("year")
    private Long year = null;

    @SerializedName("quarter")
    private Long quarter = null;

    @SerializedName("symbol")
    private String symbol = null;

    @SerializedName("caseNumber")
    private String caseNumber = null;

    @SerializedName("caseStatus")
    private String caseStatus = null;

    @SerializedName("receivedDate")
    private String receivedDate = null;

    @SerializedName("visaClass")
    private String visaClass = null;

    @SerializedName("jobTitle")
    private String jobTitle = null;

    @SerializedName("socCode")
    private String socCode = null;

    @SerializedName("fullTimePosition")
    private String fullTimePosition = null;

    @SerializedName("beginDate")
    private String beginDate = null;

    @SerializedName("endDate")
    private String endDate = null;

    @SerializedName("employerName")
    private String employerName = null;

    @SerializedName("worksiteAddress")
    private String worksiteAddress = null;

    @SerializedName("worksiteCity")
    private String worksiteCity = null;

    @SerializedName("worksiteCounty")
    private String worksiteCounty = null;

    @SerializedName("worksiteState")
    private String worksiteState = null;

    @SerializedName("worksitePostalCode")
    private String worksitePostalCode = null;

    @SerializedName("wageRangeFrom")
    private Float wageRangeFrom = null;

    @SerializedName("wageRangeTo")
    private Float wageRangeTo = null;

    @SerializedName("wageUnitOfPay")
    private String wageUnitOfPay = null;

    @SerializedName("wageLevel")
    private String wageLevel = null;

    @SerializedName("h1bDependent")
    private String h1bDependent = null;

    public VisaApplication year(Long year) {
        this.year = year;
        return this;
    }

    /**
     * Year.
     *
     * @return year
     **/
    @Schema(description = "Year.")
    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public VisaApplication quarter(Long quarter) {
        this.quarter = quarter;
        return this;
    }

    /**
     * Quarter.
     *
     * @return quarter
     **/
    @Schema(description = "Quarter.")
    public Long getQuarter() {
        return quarter;
    }

    public void setQuarter(Long quarter) {
        this.quarter = quarter;
    }

    public VisaApplication symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * Symbol.
     *
     * @return symbol
     **/
    @Schema(description = "Symbol.")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public VisaApplication caseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
        return this;
    }

    /**
     * Case number.
     *
     * @return caseNumber
     **/
    @Schema(description = "Case number.")
    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public VisaApplication caseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
        return this;
    }

    /**
     * Case status.
     *
     * @return caseStatus
     **/
    @Schema(description = "Case status.")
    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public VisaApplication receivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }

    /**
     * Received date.
     *
     * @return receivedDate
     **/
    @Schema(description = "Received date.")
    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public VisaApplication visaClass(String visaClass) {
        this.visaClass = visaClass;
        return this;
    }

    /**
     * Visa class.
     *
     * @return visaClass
     **/
    @Schema(description = "Visa class.")
    public String getVisaClass() {
        return visaClass;
    }

    public void setVisaClass(String visaClass) {
        this.visaClass = visaClass;
    }

    public VisaApplication jobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    /**
     * Job Title.
     *
     * @return jobTitle
     **/
    @Schema(description = "Job Title.")
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public VisaApplication socCode(String socCode) {
        this.socCode = socCode;
        return this;
    }

    /**
     * SOC Code. A list of SOC code can be found &lt;a href&#x3D;\&quot;https://www.bls.gov/oes/current/oes_stru.htm\&quot; target&#x3D;\&quot;_blank\&quot;&gt;here&lt;/a&gt;.
     *
     * @return socCode
     **/
    @Schema(description = "SOC Code. A list of SOC code can be found <a href=\"https://www.bls.gov/oes/current/oes_stru.htm\" target=\"_blank\">here</a>.")
    public String getSocCode() {
        return socCode;
    }

    public void setSocCode(String socCode) {
        this.socCode = socCode;
    }

    public VisaApplication fullTimePosition(String fullTimePosition) {
        this.fullTimePosition = fullTimePosition;
        return this;
    }

    /**
     * Full-time position flag.
     *
     * @return fullTimePosition
     **/
    @Schema(description = "Full-time position flag.")
    public String getFullTimePosition() {
        return fullTimePosition;
    }

    public void setFullTimePosition(String fullTimePosition) {
        this.fullTimePosition = fullTimePosition;
    }

    public VisaApplication beginDate(String beginDate) {
        this.beginDate = beginDate;
        return this;
    }

    /**
     * Job&#x27;s start date.
     *
     * @return beginDate
     **/
    @Schema(description = "Job's start date.")
    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public VisaApplication endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Job&#x27;s end date.
     *
     * @return endDate
     **/
    @Schema(description = "Job's end date.")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public VisaApplication employerName(String employerName) {
        this.employerName = employerName;
        return this;
    }

    /**
     * Company&#x27;s name.
     *
     * @return employerName
     **/
    @Schema(description = "Company's name.")
    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public VisaApplication worksiteAddress(String worksiteAddress) {
        this.worksiteAddress = worksiteAddress;
        return this;
    }

    /**
     * Worksite address.
     *
     * @return worksiteAddress
     **/
    @Schema(description = "Worksite address.")
    public String getWorksiteAddress() {
        return worksiteAddress;
    }

    public void setWorksiteAddress(String worksiteAddress) {
        this.worksiteAddress = worksiteAddress;
    }

    public VisaApplication worksiteCity(String worksiteCity) {
        this.worksiteCity = worksiteCity;
        return this;
    }

    /**
     * Worksite city.
     *
     * @return worksiteCity
     **/
    @Schema(description = "Worksite city.")
    public String getWorksiteCity() {
        return worksiteCity;
    }

    public void setWorksiteCity(String worksiteCity) {
        this.worksiteCity = worksiteCity;
    }

    public VisaApplication worksiteCounty(String worksiteCounty) {
        this.worksiteCounty = worksiteCounty;
        return this;
    }

    /**
     * Worksite county.
     *
     * @return worksiteCounty
     **/
    @Schema(description = "Worksite county.")
    public String getWorksiteCounty() {
        return worksiteCounty;
    }

    public void setWorksiteCounty(String worksiteCounty) {
        this.worksiteCounty = worksiteCounty;
    }

    public VisaApplication worksiteState(String worksiteState) {
        this.worksiteState = worksiteState;
        return this;
    }

    /**
     * Worksite state.
     *
     * @return worksiteState
     **/
    @Schema(description = "Worksite state.")
    public String getWorksiteState() {
        return worksiteState;
    }

    public void setWorksiteState(String worksiteState) {
        this.worksiteState = worksiteState;
    }

    public VisaApplication worksitePostalCode(String worksitePostalCode) {
        this.worksitePostalCode = worksitePostalCode;
        return this;
    }

    /**
     * Worksite postal code.
     *
     * @return worksitePostalCode
     **/
    @Schema(description = "Worksite postal code.")
    public String getWorksitePostalCode() {
        return worksitePostalCode;
    }

    public void setWorksitePostalCode(String worksitePostalCode) {
        this.worksitePostalCode = worksitePostalCode;
    }

    public VisaApplication wageRangeFrom(Float wageRangeFrom) {
        this.wageRangeFrom = wageRangeFrom;
        return this;
    }

    /**
     * Wage range from.
     *
     * @return wageRangeFrom
     **/
    @Schema(description = "Wage range from.")
    public Float getWageRangeFrom() {
        return wageRangeFrom;
    }

    public void setWageRangeFrom(Float wageRangeFrom) {
        this.wageRangeFrom = wageRangeFrom;
    }

    public VisaApplication wageRangeTo(Float wageRangeTo) {
        this.wageRangeTo = wageRangeTo;
        return this;
    }

    /**
     * Wage range to.
     *
     * @return wageRangeTo
     **/
    @Schema(description = "Wage range to.")
    public Float getWageRangeTo() {
        return wageRangeTo;
    }

    public void setWageRangeTo(Float wageRangeTo) {
        this.wageRangeTo = wageRangeTo;
    }

    public VisaApplication wageUnitOfPay(String wageUnitOfPay) {
        this.wageUnitOfPay = wageUnitOfPay;
        return this;
    }

    /**
     * Wage unit of pay.
     *
     * @return wageUnitOfPay
     **/
    @Schema(description = "Wage unit of pay.")
    public String getWageUnitOfPay() {
        return wageUnitOfPay;
    }

    public void setWageUnitOfPay(String wageUnitOfPay) {
        this.wageUnitOfPay = wageUnitOfPay;
    }

    public VisaApplication wageLevel(String wageLevel) {
        this.wageLevel = wageLevel;
        return this;
    }

    /**
     * Wage level.
     *
     * @return wageLevel
     **/
    @Schema(description = "Wage level.")
    public String getWageLevel() {
        return wageLevel;
    }

    public void setWageLevel(String wageLevel) {
        this.wageLevel = wageLevel;
    }

    public VisaApplication h1bDependent(String h1bDependent) {
        this.h1bDependent = h1bDependent;
        return this;
    }

    /**
     * H1B dependent flag.
     *
     * @return h1bDependent
     **/
    @Schema(description = "H1B dependent flag.")
    public String getH1bDependent() {
        return h1bDependent;
    }

    public void setH1bDependent(String h1bDependent) {
        this.h1bDependent = h1bDependent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VisaApplication visaApplication = (VisaApplication) o;
        return Objects.equals(this.year, visaApplication.year) &&
                Objects.equals(this.quarter, visaApplication.quarter) &&
                Objects.equals(this.symbol, visaApplication.symbol) &&
                Objects.equals(this.caseNumber, visaApplication.caseNumber) &&
                Objects.equals(this.caseStatus, visaApplication.caseStatus) &&
                Objects.equals(this.receivedDate, visaApplication.receivedDate) &&
                Objects.equals(this.visaClass, visaApplication.visaClass) &&
                Objects.equals(this.jobTitle, visaApplication.jobTitle) &&
                Objects.equals(this.socCode, visaApplication.socCode) &&
                Objects.equals(this.fullTimePosition, visaApplication.fullTimePosition) &&
                Objects.equals(this.beginDate, visaApplication.beginDate) &&
                Objects.equals(this.endDate, visaApplication.endDate) &&
                Objects.equals(this.employerName, visaApplication.employerName) &&
                Objects.equals(this.worksiteAddress, visaApplication.worksiteAddress) &&
                Objects.equals(this.worksiteCity, visaApplication.worksiteCity) &&
                Objects.equals(this.worksiteCounty, visaApplication.worksiteCounty) &&
                Objects.equals(this.worksiteState, visaApplication.worksiteState) &&
                Objects.equals(this.worksitePostalCode, visaApplication.worksitePostalCode) &&
                Objects.equals(this.wageRangeFrom, visaApplication.wageRangeFrom) &&
                Objects.equals(this.wageRangeTo, visaApplication.wageRangeTo) &&
                Objects.equals(this.wageUnitOfPay, visaApplication.wageUnitOfPay) &&
                Objects.equals(this.wageLevel, visaApplication.wageLevel) &&
                Objects.equals(this.h1bDependent, visaApplication.h1bDependent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, quarter, symbol, caseNumber, caseStatus, receivedDate, visaClass, jobTitle, socCode, fullTimePosition, beginDate, endDate, employerName, worksiteAddress, worksiteCity, worksiteCounty, worksiteState, worksitePostalCode, wageRangeFrom, wageRangeTo, wageUnitOfPay, wageLevel, h1bDependent);
    }


    @Override
    public String toString() {

        String sb = "class VisaApplication {\n" +
                "    year: " + toIndentedString(year) + "\n" +
                "    quarter: " + toIndentedString(quarter) + "\n" +
                "    symbol: " + toIndentedString(symbol) + "\n" +
                "    caseNumber: " + toIndentedString(caseNumber) + "\n" +
                "    caseStatus: " + toIndentedString(caseStatus) + "\n" +
                "    receivedDate: " + toIndentedString(receivedDate) + "\n" +
                "    visaClass: " + toIndentedString(visaClass) + "\n" +
                "    jobTitle: " + toIndentedString(jobTitle) + "\n" +
                "    socCode: " + toIndentedString(socCode) + "\n" +
                "    fullTimePosition: " + toIndentedString(fullTimePosition) + "\n" +
                "    beginDate: " + toIndentedString(beginDate) + "\n" +
                "    endDate: " + toIndentedString(endDate) + "\n" +
                "    employerName: " + toIndentedString(employerName) + "\n" +
                "    worksiteAddress: " + toIndentedString(worksiteAddress) + "\n" +
                "    worksiteCity: " + toIndentedString(worksiteCity) + "\n" +
                "    worksiteCounty: " + toIndentedString(worksiteCounty) + "\n" +
                "    worksiteState: " + toIndentedString(worksiteState) + "\n" +
                "    worksitePostalCode: " + toIndentedString(worksitePostalCode) + "\n" +
                "    wageRangeFrom: " + toIndentedString(wageRangeFrom) + "\n" +
                "    wageRangeTo: " + toIndentedString(wageRangeTo) + "\n" +
                "    wageUnitOfPay: " + toIndentedString(wageUnitOfPay) + "\n" +
                "    wageLevel: " + toIndentedString(wageLevel) + "\n" +
                "    h1bDependent: " + toIndentedString(h1bDependent) + "\n" +
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
