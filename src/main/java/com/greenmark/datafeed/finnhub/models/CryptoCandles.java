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
 * CryptoCandles
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class CryptoCandles {
    @SerializedName("o")
    private List<Float> o = null;

    @SerializedName("h")
    private List<Float> h = null;

    @SerializedName("l")
    private List<Float> l = null;

    @SerializedName("c")
    private List<Float> c = null;

    @SerializedName("v")
    private List<Float> v = null;

    @SerializedName("t")
    private List<Long> t = null;

    @SerializedName("s")
    private String s = null;

    public CryptoCandles o(List<Float> o) {
        this.o = o;
        return this;
    }

    public CryptoCandles addOItem(Float oItem) {
        if (this.o == null) {
            this.o = new ArrayList<Float>();
        }
        this.o.add(oItem);
        return this;
    }

    /**
     * List of open prices for returned candles.
     *
     * @return o
     **/
    @Schema(description = "List of open prices for returned candles.")
    public List<Float> getO() {
        return o;
    }

    public void setO(List<Float> o) {
        this.o = o;
    }

    public CryptoCandles h(List<Float> h) {
        this.h = h;
        return this;
    }

    public CryptoCandles addHItem(Float hItem) {
        if (this.h == null) {
            this.h = new ArrayList<Float>();
        }
        this.h.add(hItem);
        return this;
    }

    /**
     * List of high prices for returned candles.
     *
     * @return h
     **/
    @Schema(description = "List of high prices for returned candles.")
    public List<Float> getH() {
        return h;
    }

    public void setH(List<Float> h) {
        this.h = h;
    }

    public CryptoCandles l(List<Float> l) {
        this.l = l;
        return this;
    }

    public CryptoCandles addLItem(Float lItem) {
        if (this.l == null) {
            this.l = new ArrayList<Float>();
        }
        this.l.add(lItem);
        return this;
    }

    /**
     * List of low prices for returned candles.
     *
     * @return l
     **/
    @Schema(description = "List of low prices for returned candles.")
    public List<Float> getL() {
        return l;
    }

    public void setL(List<Float> l) {
        this.l = l;
    }

    public CryptoCandles c(List<Float> c) {
        this.c = c;
        return this;
    }

    public CryptoCandles addCItem(Float cItem) {
        if (this.c == null) {
            this.c = new ArrayList<Float>();
        }
        this.c.add(cItem);
        return this;
    }

    /**
     * List of close prices for returned candles.
     *
     * @return c
     **/
    @Schema(description = "List of close prices for returned candles.")
    public List<Float> getC() {
        return c;
    }

    public void setC(List<Float> c) {
        this.c = c;
    }

    public CryptoCandles v(List<Float> v) {
        this.v = v;
        return this;
    }

    public CryptoCandles addVItem(Float vItem) {
        if (this.v == null) {
            this.v = new ArrayList<Float>();
        }
        this.v.add(vItem);
        return this;
    }

    /**
     * List of volume data for returned candles.
     *
     * @return v
     **/
    @Schema(description = "List of volume data for returned candles.")
    public List<Float> getV() {
        return v;
    }

    public void setV(List<Float> v) {
        this.v = v;
    }

    public CryptoCandles t(List<Long> t) {
        this.t = t;
        return this;
    }

    public CryptoCandles addTItem(Long tItem) {
        if (this.t == null) {
            this.t = new ArrayList<Long>();
        }
        this.t.add(tItem);
        return this;
    }

    /**
     * List of timestamp for returned candles.
     *
     * @return t
     **/
    @Schema(description = "List of timestamp for returned candles.")
    public List<Long> getT() {
        return t;
    }

    public void setT(List<Long> t) {
        this.t = t;
    }

    public CryptoCandles s(String s) {
        this.s = s;
        return this;
    }

    /**
     * Status of the response. This field can either be ok or no_data.
     *
     * @return s
     **/
    @Schema(description = "Status of the response. This field can either be ok or no_data.")
    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CryptoCandles cryptoCandles = (CryptoCandles) o;
        return Objects.equals(this.o, cryptoCandles.o) &&
                Objects.equals(this.h, cryptoCandles.h) &&
                Objects.equals(this.l, cryptoCandles.l) &&
                Objects.equals(this.c, cryptoCandles.c) &&
                Objects.equals(this.v, cryptoCandles.v) &&
                Objects.equals(this.t, cryptoCandles.t) &&
                Objects.equals(this.s, cryptoCandles.s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(o, h, l, c, v, t, s);
    }


    @Override
    public String toString() {

        String sb = "class CryptoCandles {\n" +
                "    o: " + toIndentedString(o) + "\n" +
                "    h: " + toIndentedString(h) + "\n" +
                "    l: " + toIndentedString(l) + "\n" +
                "    c: " + toIndentedString(c) + "\n" +
                "    v: " + toIndentedString(v) + "\n" +
                "    t: " + toIndentedString(t) + "\n" +
                "    s: " + toIndentedString(s) + "\n" +
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
