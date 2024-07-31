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

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * SearchResponse
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class SearchResponse {
    @SerializedName("count")
    private Integer count = null;

    @SerializedName("took")
    private Integer took = null;

    @SerializedName("page")
    private Integer page = null;

    @SerializedName("filings")
    private List<FilingResponse> filings = null;

    public SearchResponse count(Integer count) {
        this.count = count;
        return this;
    }

    /**
     * Total filing matched your search criteria.
     *
     * @return count
     **/
    @Schema(description = "Total filing matched your search criteria.")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public SearchResponse took(Integer took) {
        this.took = took;
        return this;
    }

    /**
     * Time took to execute your search query on our server, value in ms.
     *
     * @return took
     **/
    @Schema(description = "Time took to execute your search query on our server, value in ms.")
    public Integer getTook() {
        return took;
    }

    public void setTook(Integer took) {
        this.took = took;
    }

    public SearchResponse page(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * Current search page
     *
     * @return page
     **/
    @Schema(description = "Current search page")
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public SearchResponse filings(List<FilingResponse> filings) {
        this.filings = filings;
        return this;
    }

    public SearchResponse addFilingsItem(FilingResponse filingsItem) {
        if (this.filings == null) {
            this.filings = new ArrayList<FilingResponse>();
        }
        this.filings.add(filingsItem);
        return this;
    }

    /**
     * Filing match your search criteria.
     *
     * @return filings
     **/
    @Schema(description = "Filing match your search criteria.")
    public List<FilingResponse> getFilings() {
        return filings;
    }

    public void setFilings(List<FilingResponse> filings) {
        this.filings = filings;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchResponse searchResponse = (SearchResponse) o;
        return Objects.equals(this.count, searchResponse.count) &&
                Objects.equals(this.took, searchResponse.took) &&
                Objects.equals(this.page, searchResponse.page) &&
                Objects.equals(this.filings, searchResponse.filings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, took, page, filings);
    }


    @Override
    public String toString() {

        String sb = "class SearchResponse {\n" +
                "    count: " + toIndentedString(count) + "\n" +
                "    took: " + toIndentedString(took) + "\n" +
                "    page: " + toIndentedString(page) + "\n" +
                "    filings: " + toIndentedString(filings) + "\n" +
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
