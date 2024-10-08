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
 * Report
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class Report {
    @SerializedName("accessNumber")
    private String accessNumber = null;

    @SerializedName("symbol")
    private String symbol = null;

    @SerializedName("cik")
    private String cik = null;

    @SerializedName("year")
    private Long year = null;

    @SerializedName("quarter")
    private Long quarter = null;

    @SerializedName("form")
    private String form = null;

    @SerializedName("startDate")
    private String startDate = null;

    @SerializedName("endDate")
    private String endDate = null;

    @SerializedName("filedDate")
    private String filedDate = null;

    @SerializedName("acceptedDate")
    private String acceptedDate = null;

    @SerializedName("report")
    private ReportDataMap report = null;

    public Report accessNumber(String accessNumber) {
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

    public Report symbol(String symbol) {
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

    public Report cik(String cik) {
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

    public Report year(Long year) {
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

    public Report quarter(Long quarter) {
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

    public Report form(String form) {
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

    public Report startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Period start date &lt;code&gt;%Y-%m-%d %H:%M:%S&lt;/code&gt;.
     *
     * @return startDate
     **/
    @Schema(description = "Period start date <code>%Y-%m-%d %H:%M:%S</code>.")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Report endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Period end date &lt;code&gt;%Y-%m-%d %H:%M:%S&lt;/code&gt;.
     *
     * @return endDate
     **/
    @Schema(description = "Period end date <code>%Y-%m-%d %H:%M:%S</code>.")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Report filedDate(String filedDate) {
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

    public Report acceptedDate(String acceptedDate) {
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

    public Report report(ReportDataMap report) {
        this.report = report;
        return this;
    }

    /**
     * Get report
     *
     * @return report
     **/
    @Schema(description = "")
    public ReportDataMap getReport() {
        return report;
    }

    public void setReport(ReportDataMap report) {
        this.report = report;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Report report = (Report) o;
        return Objects.equals(this.accessNumber, report.accessNumber) &&
                Objects.equals(this.symbol, report.symbol) &&
                Objects.equals(this.cik, report.cik) &&
                Objects.equals(this.year, report.year) &&
                Objects.equals(this.quarter, report.quarter) &&
                Objects.equals(this.form, report.form) &&
                Objects.equals(this.startDate, report.startDate) &&
                Objects.equals(this.endDate, report.endDate) &&
                Objects.equals(this.filedDate, report.filedDate) &&
                Objects.equals(this.acceptedDate, report.acceptedDate) &&
                Objects.equals(this.report, report.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessNumber, symbol, cik, year, quarter, form, startDate, endDate, filedDate, acceptedDate, report);
    }


    @Override
    public String toString() {

        String sb = "class Report {\n" +
                "    accessNumber: " + toIndentedString(accessNumber) + "\n" +
                "    symbol: " + toIndentedString(symbol) + "\n" +
                "    cik: " + toIndentedString(cik) + "\n" +
                "    year: " + toIndentedString(year) + "\n" +
                "    quarter: " + toIndentedString(quarter) + "\n" +
                "    form: " + toIndentedString(form) + "\n" +
                "    startDate: " + toIndentedString(startDate) + "\n" +
                "    endDate: " + toIndentedString(endDate) + "\n" +
                "    filedDate: " + toIndentedString(filedDate) + "\n" +
                "    acceptedDate: " + toIndentedString(acceptedDate) + "\n" +
                "    report: " + toIndentedString(report) + "\n" +
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
