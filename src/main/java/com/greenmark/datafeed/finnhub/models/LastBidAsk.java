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
 * LastBidAsk
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class LastBidAsk {
    @SerializedName("b")
    private Float b = null;

    @SerializedName("a")
    private Float a = null;

    @SerializedName("bv")
    private Float bv = null;

    @SerializedName("av")
    private Float av = null;

    @SerializedName("t")
    private Long t = null;

    public LastBidAsk b(Float b) {
        this.b = b;
        return this;
    }

    /**
     * Bid price.
     *
     * @return b
     **/
    @Schema(description = "Bid price.")
    public Float getB() {
        return b;
    }

    public void setB(Float b) {
        this.b = b;
    }

    public LastBidAsk a(Float a) {
        this.a = a;
        return this;
    }

    /**
     * Ask price.
     *
     * @return a
     **/
    @Schema(description = "Ask price.")
    public Float getA() {
        return a;
    }

    public void setA(Float a) {
        this.a = a;
    }

    public LastBidAsk bv(Float bv) {
        this.bv = bv;
        return this;
    }

    /**
     * Bid volume.
     *
     * @return bv
     **/
    @Schema(description = "Bid volume.")
    public Float getBv() {
        return bv;
    }

    public void setBv(Float bv) {
        this.bv = bv;
    }

    public LastBidAsk av(Float av) {
        this.av = av;
        return this;
    }

    /**
     * Ask volume.
     *
     * @return av
     **/
    @Schema(description = "Ask volume.")
    public Float getAv() {
        return av;
    }

    public void setAv(Float av) {
        this.av = av;
    }

    public LastBidAsk t(Long t) {
        this.t = t;
        return this;
    }

    /**
     * Reference UNIX timestamp in ms.
     *
     * @return t
     **/
    @Schema(description = "Reference UNIX timestamp in ms.")
    public Long getT() {
        return t;
    }

    public void setT(Long t) {
        this.t = t;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LastBidAsk lastBidAsk = (LastBidAsk) o;
        return Objects.equals(this.b, lastBidAsk.b) &&
                Objects.equals(this.a, lastBidAsk.a) &&
                Objects.equals(this.bv, lastBidAsk.bv) &&
                Objects.equals(this.av, lastBidAsk.av) &&
                Objects.equals(this.t, lastBidAsk.t);
    }

    @Override
    public int hashCode() {
        return Objects.hash(b, a, bv, av, t);
    }


    @Override
    public String toString() {

        String sb = "class LastBidAsk {\n" +
                "    b: " + toIndentedString(b) + "\n" +
                "    a: " + toIndentedString(a) + "\n" +
                "    bv: " + toIndentedString(bv) + "\n" +
                "    av: " + toIndentedString(av) + "\n" +
                "    t: " + toIndentedString(t) + "\n" +
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
