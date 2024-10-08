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
 * SearchBody
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class SearchBody {
    @SerializedName("query")
    private String query = null;

    @SerializedName("isins")
    private String isins = null;

    @SerializedName("cusips")
    private String cusips = null;

    @SerializedName("ciks")
    private String ciks = null;

    @SerializedName("sedarIds")
    private String sedarIds = null;

    @SerializedName("chIds")
    private String chIds = null;

    @SerializedName("symbols")
    private String symbols = null;

    @SerializedName("sedols")
    private String sedols = null;

    @SerializedName("sources")
    private String sources = null;

    @SerializedName("forms")
    private String forms = null;

    @SerializedName("gics")
    private String gics = null;

    @SerializedName("naics")
    private String naics = null;

    @SerializedName("exhibits")
    private String exhibits = null;

    @SerializedName("exchanges")
    private String exchanges = null;

    @SerializedName("countries")
    private String countries = null;

    @SerializedName("acts")
    private String acts = null;

    @SerializedName("caps")
    private String caps = null;

    @SerializedName("fromDate")
    private String fromDate = null;

    @SerializedName("toDate")
    private String toDate = null;

    @SerializedName("page")
    private String page = null;

    @SerializedName("sort")
    private String sort = null;

    @SerializedName("highlighted")
    private Boolean highlighted = null;

    public SearchBody query(String query) {
        this.query = query;
        return this;
    }

    /**
     * Search query
     *
     * @return query
     **/
    @Schema(required = true, description = "Search query")
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public SearchBody isins(String isins) {
        this.isins = isins;
        return this;
    }

    /**
     * List of isin to search, comma separated (Max: 50).
     *
     * @return isins
     **/
    @Schema(description = "List of isin to search, comma separated (Max: 50).")
    public String getIsins() {
        return isins;
    }

    public void setIsins(String isins) {
        this.isins = isins;
    }

    public SearchBody cusips(String cusips) {
        this.cusips = cusips;
        return this;
    }

    /**
     * List of cusip to search, comma separated (Max: 50).
     *
     * @return cusips
     **/
    @Schema(description = "List of cusip to search, comma separated (Max: 50).")
    public String getCusips() {
        return cusips;
    }

    public void setCusips(String cusips) {
        this.cusips = cusips;
    }

    public SearchBody ciks(String ciks) {
        this.ciks = ciks;
        return this;
    }

    /**
     * List of SEC Center Index Key to search, comma separated (Max: 50).
     *
     * @return ciks
     **/
    @Schema(description = "List of SEC Center Index Key to search, comma separated (Max: 50).")
    public String getCiks() {
        return ciks;
    }

    public void setCiks(String ciks) {
        this.ciks = ciks;
    }

    public SearchBody sedarIds(String sedarIds) {
        this.sedarIds = sedarIds;
        return this;
    }

    /**
     * List of SEDAR issuer number to search, comma separated (Max: 50).
     *
     * @return sedarIds
     **/
    @Schema(description = "List of SEDAR issuer number to search, comma separated (Max: 50).")
    public String getSedarIds() {
        return sedarIds;
    }

    public void setSedarIds(String sedarIds) {
        this.sedarIds = sedarIds;
    }

    public SearchBody chIds(String chIds) {
        this.chIds = chIds;
        return this;
    }

    /**
     * List of Companies House number to search, comma separated (Max: 50).
     *
     * @return chIds
     **/
    @Schema(description = "List of Companies House number to search, comma separated (Max: 50).")
    public String getChIds() {
        return chIds;
    }

    public void setChIds(String chIds) {
        this.chIds = chIds;
    }

    public SearchBody symbols(String symbols) {
        this.symbols = symbols;
        return this;
    }

    /**
     * List of symbols to search, comma separated (Max: 50).
     *
     * @return symbols
     **/
    @Schema(description = "List of symbols to search, comma separated (Max: 50).")
    public String getSymbols() {
        return symbols;
    }

    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }

    public SearchBody sedols(String sedols) {
        this.sedols = sedols;
        return this;
    }

    /**
     * List of sedols to search, comma separated (Max: 50).
     *
     * @return sedols
     **/
    @Schema(description = "List of sedols to search, comma separated (Max: 50).")
    public String getSedols() {
        return sedols;
    }

    public void setSedols(String sedols) {
        this.sedols = sedols;
    }

    public SearchBody sources(String sources) {
        this.sources = sources;
        return this;
    }

    /**
     * List of sources to search, comma separated (Max: 50). Look at &lt;code&gt;/filter&lt;/code&gt; endpoint to see all available values.
     *
     * @return sources
     **/
    @Schema(description = "List of sources to search, comma separated (Max: 50). Look at <code>/filter</code> endpoint to see all available values.")
    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public SearchBody forms(String forms) {
        this.forms = forms;
        return this;
    }

    /**
     * List of forms to search, comma separated (Max: 50). Look at &lt;code&gt;/filter&lt;/code&gt; endpoint to see all available values.
     *
     * @return forms
     **/
    @Schema(description = "List of forms to search, comma separated (Max: 50). Look at <code>/filter</code> endpoint to see all available values.")
    public String getForms() {
        return forms;
    }

    public void setForms(String forms) {
        this.forms = forms;
    }

    public SearchBody gics(String gics) {
        this.gics = gics;
        return this;
    }

    /**
     * List of gics to search, comma separated (Max: 50). Look at &lt;code&gt;/filter&lt;/code&gt; endpoint to see all available values.
     *
     * @return gics
     **/
    @Schema(description = "List of gics to search, comma separated (Max: 50). Look at <code>/filter</code> endpoint to see all available values.")
    public String getGics() {
        return gics;
    }

    public void setGics(String gics) {
        this.gics = gics;
    }

    public SearchBody naics(String naics) {
        this.naics = naics;
        return this;
    }

    /**
     * List of sources to search, comma separated (Max: 50). Look at &lt;code&gt;/filter&lt;/code&gt; endpoint to see all available values.
     *
     * @return naics
     **/
    @Schema(description = "List of sources to search, comma separated (Max: 50). Look at <code>/filter</code> endpoint to see all available values.")
    public String getNaics() {
        return naics;
    }

    public void setNaics(String naics) {
        this.naics = naics;
    }

    public SearchBody exhibits(String exhibits) {
        this.exhibits = exhibits;
        return this;
    }

    /**
     * List of exhibits to search, comma separated (Max: 50). Look at &lt;code&gt;/filter&lt;/code&gt; endpoint to see all available values.
     *
     * @return exhibits
     **/
    @Schema(description = "List of exhibits to search, comma separated (Max: 50). Look at <code>/filter</code> endpoint to see all available values.")
    public String getExhibits() {
        return exhibits;
    }

    public void setExhibits(String exhibits) {
        this.exhibits = exhibits;
    }

    public SearchBody exchanges(String exchanges) {
        this.exchanges = exchanges;
        return this;
    }

    /**
     * List of exchanges to search, comma separated (Max: 50). Look at &lt;code&gt;/filter&lt;/code&gt; endpoint to see all available values.
     *
     * @return exchanges
     **/
    @Schema(description = "List of exchanges to search, comma separated (Max: 50). Look at <code>/filter</code> endpoint to see all available values.")
    public String getExchanges() {
        return exchanges;
    }

    public void setExchanges(String exchanges) {
        this.exchanges = exchanges;
    }

    public SearchBody countries(String countries) {
        this.countries = countries;
        return this;
    }

    /**
     * List of sources to search, comma separated (Max: 50). Look at &lt;code&gt;/filter&lt;/code&gt; endpoint to see all available values.
     *
     * @return countries
     **/
    @Schema(description = "List of sources to search, comma separated (Max: 50). Look at <code>/filter</code> endpoint to see all available values.")
    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public SearchBody acts(String acts) {
        this.acts = acts;
        return this;
    }

    /**
     * List of SEC&#x27;s exchanges act to search, comma separated. Look at &lt;code&gt;/filter&lt;/code&gt; endpoint to see all available values.
     *
     * @return acts
     **/
    @Schema(description = "List of SEC's exchanges act to search, comma separated. Look at <code>/filter</code> endpoint to see all available values.")
    public String getActs() {
        return acts;
    }

    public void setActs(String acts) {
        this.acts = acts;
    }

    public SearchBody caps(String caps) {
        this.caps = caps;
        return this;
    }

    /**
     * List of market capitalization to search, comma separated. Look at &lt;code&gt;/filter&lt;/code&gt; endpoint to see all available values.
     *
     * @return caps
     **/
    @Schema(description = "List of market capitalization to search, comma separated. Look at <code>/filter</code> endpoint to see all available values.")
    public String getCaps() {
        return caps;
    }

    public void setCaps(String caps) {
        this.caps = caps;
    }

    public SearchBody fromDate(String fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    /**
     * Search from date in format: YYYY-MM-DD, default from the last 2 years
     *
     * @return fromDate
     **/
    @Schema(description = "Search from date in format: YYYY-MM-DD, default from the last 2 years")
    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public SearchBody toDate(String toDate) {
        this.toDate = toDate;
        return this;
    }

    /**
     * Search to date in format: YYYY-MM-DD, default to today
     *
     * @return toDate
     **/
    @Schema(description = "Search to date in format: YYYY-MM-DD, default to today")
    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public SearchBody page(String page) {
        this.page = page;
        return this;
    }

    /**
     * Use for pagination, default to page 1
     *
     * @return page
     **/
    @Schema(description = "Use for pagination, default to page 1")
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public SearchBody sort(String sort) {
        this.sort = sort;
        return this;
    }

    /**
     * Sort result by, default: sortMostRecent. Look at &lt;code&gt;/filter&lt;/code&gt; endpoint to see all available values.
     *
     * @return sort
     **/
    @Schema(description = "Sort result by, default: sortMostRecent. Look at <code>/filter</code> endpoint to see all available values.")
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public SearchBody highlighted(Boolean highlighted) {
        this.highlighted = highlighted;
        return this;
    }

    /**
     * Enable highlight in returned filings. If enabled, only return 10 results each time
     *
     * @return highlighted
     **/
    @Schema(description = "Enable highlight in returned filings. If enabled, only return 10 results each time")
    public Boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(Boolean highlighted) {
        this.highlighted = highlighted;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchBody searchBody = (SearchBody) o;
        return Objects.equals(this.query, searchBody.query) &&
                Objects.equals(this.isins, searchBody.isins) &&
                Objects.equals(this.cusips, searchBody.cusips) &&
                Objects.equals(this.ciks, searchBody.ciks) &&
                Objects.equals(this.sedarIds, searchBody.sedarIds) &&
                Objects.equals(this.chIds, searchBody.chIds) &&
                Objects.equals(this.symbols, searchBody.symbols) &&
                Objects.equals(this.sedols, searchBody.sedols) &&
                Objects.equals(this.sources, searchBody.sources) &&
                Objects.equals(this.forms, searchBody.forms) &&
                Objects.equals(this.gics, searchBody.gics) &&
                Objects.equals(this.naics, searchBody.naics) &&
                Objects.equals(this.exhibits, searchBody.exhibits) &&
                Objects.equals(this.exchanges, searchBody.exchanges) &&
                Objects.equals(this.countries, searchBody.countries) &&
                Objects.equals(this.acts, searchBody.acts) &&
                Objects.equals(this.caps, searchBody.caps) &&
                Objects.equals(this.fromDate, searchBody.fromDate) &&
                Objects.equals(this.toDate, searchBody.toDate) &&
                Objects.equals(this.page, searchBody.page) &&
                Objects.equals(this.sort, searchBody.sort) &&
                Objects.equals(this.highlighted, searchBody.highlighted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, isins, cusips, ciks, sedarIds, chIds, symbols, sedols, sources, forms, gics, naics, exhibits, exchanges, countries, acts, caps, fromDate, toDate, page, sort, highlighted);
    }


    @Override
    public String toString() {

        String sb = "class SearchBody {\n" +
                "    query: " + toIndentedString(query) + "\n" +
                "    isins: " + toIndentedString(isins) + "\n" +
                "    cusips: " + toIndentedString(cusips) + "\n" +
                "    ciks: " + toIndentedString(ciks) + "\n" +
                "    sedarIds: " + toIndentedString(sedarIds) + "\n" +
                "    chIds: " + toIndentedString(chIds) + "\n" +
                "    symbols: " + toIndentedString(symbols) + "\n" +
                "    sedols: " + toIndentedString(sedols) + "\n" +
                "    sources: " + toIndentedString(sources) + "\n" +
                "    forms: " + toIndentedString(forms) + "\n" +
                "    gics: " + toIndentedString(gics) + "\n" +
                "    naics: " + toIndentedString(naics) + "\n" +
                "    exhibits: " + toIndentedString(exhibits) + "\n" +
                "    exchanges: " + toIndentedString(exchanges) + "\n" +
                "    countries: " + toIndentedString(countries) + "\n" +
                "    acts: " + toIndentedString(acts) + "\n" +
                "    caps: " + toIndentedString(caps) + "\n" +
                "    fromDate: " + toIndentedString(fromDate) + "\n" +
                "    toDate: " + toIndentedString(toDate) + "\n" +
                "    page: " + toIndentedString(page) + "\n" +
                "    sort: " + toIndentedString(sort) + "\n" +
                "    highlighted: " + toIndentedString(highlighted) + "\n" +
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
