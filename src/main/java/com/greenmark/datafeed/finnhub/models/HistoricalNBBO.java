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
 * HistoricalNBBO
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class HistoricalNBBO {
    @SerializedName("s")
    private String s = null;

    @SerializedName("skip")
    private Long skip = null;

    @SerializedName("count")
    private Long count = null;

    @SerializedName("total")
    private Long total = null;

    @SerializedName("av")
    private List<Float> av = null;

    @SerializedName("a")
    private List<Float> a = null;

    @SerializedName("ax")
    private List<String> ax = null;

    @SerializedName("bv")
    private List<Float> bv = null;

    @SerializedName("b")
    private List<Float> b = null;

    @SerializedName("bx")
    private List<String> bx = null;

    @SerializedName("t")
    private List<Long> t = null;

    @SerializedName("c")
    private List<List<String>> c = null;

    public HistoricalNBBO s(String s) {
        this.s = s;
        return this;
    }

    /**
     * Symbol.
     *
     * @return s
     **/
    @Schema(description = "Symbol.")
    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public HistoricalNBBO skip(Long skip) {
        this.skip = skip;
        return this;
    }

    /**
     * Number of ticks skipped.
     *
     * @return skip
     **/
    @Schema(description = "Number of ticks skipped.")
    public Long getSkip() {
        return skip;
    }

    public void setSkip(Long skip) {
        this.skip = skip;
    }

    public HistoricalNBBO count(Long count) {
        this.count = count;
        return this;
    }

    /**
     * Number of ticks returned. If &lt;code&gt;count&lt;/code&gt; &lt; &lt;code&gt;limit&lt;/code&gt;, all data for that date has been returned.
     *
     * @return count
     **/
    @Schema(description = "Number of ticks returned. If <code>count</code> < <code>limit</code>, all data for that date has been returned.")
    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public HistoricalNBBO total(Long total) {
        this.total = total;
        return this;
    }

    /**
     * Total number of ticks for that date.
     *
     * @return total
     **/
    @Schema(description = "Total number of ticks for that date.")
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public HistoricalNBBO av(List<Float> av) {
        this.av = av;
        return this;
    }

    public HistoricalNBBO addAvItem(Float avItem) {
        if (this.av == null) {
            this.av = new ArrayList<Float>();
        }
        this.av.add(avItem);
        return this;
    }

    /**
     * List of Ask volume data.
     *
     * @return av
     **/
    @Schema(description = "List of Ask volume data.")
    public List<Float> getAv() {
        return av;
    }

    public void setAv(List<Float> av) {
        this.av = av;
    }

    public HistoricalNBBO a(List<Float> a) {
        this.a = a;
        return this;
    }

    public HistoricalNBBO addAItem(Float aItem) {
        if (this.a == null) {
            this.a = new ArrayList<Float>();
        }
        this.a.add(aItem);
        return this;
    }

    /**
     * List of Ask price data.
     *
     * @return a
     **/
    @Schema(description = "List of Ask price data.")
    public List<Float> getA() {
        return a;
    }

    public void setA(List<Float> a) {
        this.a = a;
    }

    public HistoricalNBBO ax(List<String> ax) {
        this.ax = ax;
        return this;
    }

    public HistoricalNBBO addAxItem(String axItem) {
        if (this.ax == null) {
            this.ax = new ArrayList<String>();
        }
        this.ax.add(axItem);
        return this;
    }

    /**
     * List of venues/exchanges - Ask price. A list of exchange codes can be found &lt;a target&#x3D;\&quot;_blank\&quot; href&#x3D;\&quot;https://docs.google.com/spreadsheets/d/1Tj53M1svmr-hfEtbk6_NpVR1yAyGLMaH6ByYU6CG0ZY/edit?usp&#x3D;sharing\&quot;,&gt;here&lt;/a&gt;
     *
     * @return ax
     **/
    @Schema(description = "List of venues/exchanges - Ask price. A list of exchange codes can be found <a target=\"_blank\" href=\"https://docs.google.com/spreadsheets/d/1Tj53M1svmr-hfEtbk6_NpVR1yAyGLMaH6ByYU6CG0ZY/edit?usp=sharing\",>here</a>")
    public List<String> getAx() {
        return ax;
    }

    public void setAx(List<String> ax) {
        this.ax = ax;
    }

    public HistoricalNBBO bv(List<Float> bv) {
        this.bv = bv;
        return this;
    }

    public HistoricalNBBO addBvItem(Float bvItem) {
        if (this.bv == null) {
            this.bv = new ArrayList<Float>();
        }
        this.bv.add(bvItem);
        return this;
    }

    /**
     * List of Bid volume data.
     *
     * @return bv
     **/
    @Schema(description = "List of Bid volume data.")
    public List<Float> getBv() {
        return bv;
    }

    public void setBv(List<Float> bv) {
        this.bv = bv;
    }

    public HistoricalNBBO b(List<Float> b) {
        this.b = b;
        return this;
    }

    public HistoricalNBBO addBItem(Float bItem) {
        if (this.b == null) {
            this.b = new ArrayList<Float>();
        }
        this.b.add(bItem);
        return this;
    }

    /**
     * List of Bid price data.
     *
     * @return b
     **/
    @Schema(description = "List of Bid price data.")
    public List<Float> getB() {
        return b;
    }

    public void setB(List<Float> b) {
        this.b = b;
    }

    public HistoricalNBBO bx(List<String> bx) {
        this.bx = bx;
        return this;
    }

    public HistoricalNBBO addBxItem(String bxItem) {
        if (this.bx == null) {
            this.bx = new ArrayList<String>();
        }
        this.bx.add(bxItem);
        return this;
    }

    /**
     * List of venues/exchanges - Bid price. A list of exchange codes can be found &lt;a target&#x3D;\&quot;_blank\&quot; href&#x3D;\&quot;https://docs.google.com/spreadsheets/d/1Tj53M1svmr-hfEtbk6_NpVR1yAyGLMaH6ByYU6CG0ZY/edit?usp&#x3D;sharing\&quot;,&gt;here&lt;/a&gt;
     *
     * @return bx
     **/
    @Schema(description = "List of venues/exchanges - Bid price. A list of exchange codes can be found <a target=\"_blank\" href=\"https://docs.google.com/spreadsheets/d/1Tj53M1svmr-hfEtbk6_NpVR1yAyGLMaH6ByYU6CG0ZY/edit?usp=sharing\",>here</a>")
    public List<String> getBx() {
        return bx;
    }

    public void setBx(List<String> bx) {
        this.bx = bx;
    }

    public HistoricalNBBO t(List<Long> t) {
        this.t = t;
        return this;
    }

    public HistoricalNBBO addTItem(Long tItem) {
        if (this.t == null) {
            this.t = new ArrayList<Long>();
        }
        this.t.add(tItem);
        return this;
    }

    /**
     * List of timestamp in UNIX ms.
     *
     * @return t
     **/
    @Schema(description = "List of timestamp in UNIX ms.")
    public List<Long> getT() {
        return t;
    }

    public void setT(List<Long> t) {
        this.t = t;
    }

    public HistoricalNBBO c(List<List<String>> c) {
        this.c = c;
        return this;
    }

    public HistoricalNBBO addCItem(List<String> cItem) {
        if (this.c == null) {
            this.c = new ArrayList<List<String>>();
        }
        this.c.add(cItem);
        return this;
    }

    /**
     * List of quote conditions. A comprehensive list of quote conditions code can be found &lt;a target&#x3D;\&quot;_blank\&quot; href&#x3D;\&quot;https://docs.google.com/spreadsheets/d/1iiA6e7Osdtai0oPMOUzgAIKXCsay89dFDmsegz6OpEg/edit?usp&#x3D;sharing\&quot;&gt;here&lt;/a&gt;
     *
     * @return c
     **/
    @Schema(description = "List of quote conditions. A comprehensive list of quote conditions code can be found <a target=\"_blank\" href=\"https://docs.google.com/spreadsheets/d/1iiA6e7Osdtai0oPMOUzgAIKXCsay89dFDmsegz6OpEg/edit?usp=sharing\">here</a>")
    public List<List<String>> getC() {
        return c;
    }

    public void setC(List<List<String>> c) {
        this.c = c;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HistoricalNBBO historicalNBBO = (HistoricalNBBO) o;
        return Objects.equals(this.s, historicalNBBO.s) &&
                Objects.equals(this.skip, historicalNBBO.skip) &&
                Objects.equals(this.count, historicalNBBO.count) &&
                Objects.equals(this.total, historicalNBBO.total) &&
                Objects.equals(this.av, historicalNBBO.av) &&
                Objects.equals(this.a, historicalNBBO.a) &&
                Objects.equals(this.ax, historicalNBBO.ax) &&
                Objects.equals(this.bv, historicalNBBO.bv) &&
                Objects.equals(this.b, historicalNBBO.b) &&
                Objects.equals(this.bx, historicalNBBO.bx) &&
                Objects.equals(this.t, historicalNBBO.t) &&
                Objects.equals(this.c, historicalNBBO.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s, skip, count, total, av, a, ax, bv, b, bx, t, c);
    }


    @Override
    public String toString() {

        String sb = "class HistoricalNBBO {\n" +
                "    s: " + toIndentedString(s) + "\n" +
                "    skip: " + toIndentedString(skip) + "\n" +
                "    count: " + toIndentedString(count) + "\n" +
                "    total: " + toIndentedString(total) + "\n" +
                "    av: " + toIndentedString(av) + "\n" +
                "    a: " + toIndentedString(a) + "\n" +
                "    ax: " + toIndentedString(ax) + "\n" +
                "    bv: " + toIndentedString(bv) + "\n" +
                "    b: " + toIndentedString(b) + "\n" +
                "    bx: " + toIndentedString(bx) + "\n" +
                "    t: " + toIndentedString(t) + "\n" +
                "    c: " + toIndentedString(c) + "\n" +
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
