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
 * BasicFinancials
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class BasicFinancials {
    @SerializedName("symbol")
    private String symbol = null;

    @SerializedName("metricType")
    private String metricType = null;

    @SerializedName("series")
    private MetricSeriesMap series = null;

    @SerializedName("metric")
    private MetricMap metric = null;

    public BasicFinancials symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * Symbol of the company.
     *
     * @return symbol
     **/
    @Schema(description = "Symbol of the company.")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BasicFinancials metricType(String metricType) {
        this.metricType = metricType;
        return this;
    }

    /**
     * Metric type.
     *
     * @return metricType
     **/
    @Schema(description = "Metric type.")
    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    public BasicFinancials series(MetricSeriesMap series) {
        this.series = series;
        return this;
    }

    /**
     * Get series
     *
     * @return series
     **/
    @Schema(description = "")
    public MetricSeriesMap getSeries() {
        return series;
    }

    public void setSeries(MetricSeriesMap series) {
        this.series = series;
    }

    public BasicFinancials metric(MetricMap metric) {
        this.metric = metric;
        return this;
    }

    /**
     * Get metric
     *
     * @return metric
     **/
    @Schema(description = "")
    public MetricMap getMetric() {
        return metric;
    }

    public void setMetric(MetricMap metric) {
        this.metric = metric;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BasicFinancials basicFinancials = (BasicFinancials) o;
        return Objects.equals(this.symbol, basicFinancials.symbol) &&
                Objects.equals(this.metricType, basicFinancials.metricType) &&
                Objects.equals(this.series, basicFinancials.series) &&
                Objects.equals(this.metric, basicFinancials.metric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, metricType, series, metric);
    }


    @Override
    public String toString() {

        String sb = "class BasicFinancials {\n" +
                "    symbol: " + toIndentedString(symbol) + "\n" +
                "    metricType: " + toIndentedString(metricType) + "\n" +
                "    series: " + toIndentedString(series) + "\n" +
                "    metric: " + toIndentedString(metric) + "\n" +
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
