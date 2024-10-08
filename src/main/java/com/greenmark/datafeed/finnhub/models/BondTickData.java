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
 * BondTickData
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class BondTickData {
    @SerializedName("skip")
    private Long skip = null;

    @SerializedName("count")
    private Long count = null;

    @SerializedName("total")
    private Long total = null;

    @SerializedName("v")
    private List<Float> v = null;

    @SerializedName("p")
    private List<Float> p = null;

    @SerializedName("y")
    private List<Float> y = null;

    @SerializedName("t")
    private List<Long> t = null;

    @SerializedName("si")
    private List<String> si = null;

    @SerializedName("cp")
    private List<String> cp = null;

    @SerializedName("rp")
    private List<String> rp = null;

    @SerializedName("ats")
    private List<String> ats = null;

    @SerializedName("c")
    private List<List<String>> c = null;

    public BondTickData skip(Long skip) {
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

    public BondTickData count(Long count) {
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

    public BondTickData total(Long total) {
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

    public BondTickData v(List<Float> v) {
        this.v = v;
        return this;
    }

    public BondTickData addVItem(Float vItem) {
        if (this.v == null) {
            this.v = new ArrayList<Float>();
        }
        this.v.add(vItem);
        return this;
    }

    /**
     * List of volume data.
     *
     * @return v
     **/
    @Schema(description = "List of volume data.")
    public List<Float> getV() {
        return v;
    }

    public void setV(List<Float> v) {
        this.v = v;
    }

    public BondTickData p(List<Float> p) {
        this.p = p;
        return this;
    }

    public BondTickData addPItem(Float pItem) {
        if (this.p == null) {
            this.p = new ArrayList<Float>();
        }
        this.p.add(pItem);
        return this;
    }

    /**
     * List of price data.
     *
     * @return p
     **/
    @Schema(description = "List of price data.")
    public List<Float> getP() {
        return p;
    }

    public void setP(List<Float> p) {
        this.p = p;
    }

    public BondTickData y(List<Float> y) {
        this.y = y;
        return this;
    }

    public BondTickData addYItem(Float yItem) {
        if (this.y == null) {
            this.y = new ArrayList<Float>();
        }
        this.y.add(yItem);
        return this;
    }

    /**
     * List of yield data.
     *
     * @return y
     **/
    @Schema(description = "List of yield data.")
    public List<Float> getY() {
        return y;
    }

    public void setY(List<Float> y) {
        this.y = y;
    }

    public BondTickData t(List<Long> t) {
        this.t = t;
        return this;
    }

    public BondTickData addTItem(Long tItem) {
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

    public BondTickData si(List<String> si) {
        this.si = si;
        return this;
    }

    public BondTickData addSiItem(String siItem) {
        if (this.si == null) {
            this.si = new ArrayList<String>();
        }
        this.si.add(siItem);
        return this;
    }

    /**
     * List of values showing the side (Buy/sell) of each trade. List of supported values: &lt;a target&#x3D;\&quot;_blank\&quot; href&#x3D;\&quot;https://docs.google.com/spreadsheets/d/1O3aueXSPOqo7Iuyz4PqDG6yZunHsX8BTefZ2kFk5pz4/edit?usp&#x3D;sharing\&quot;,&gt;here&lt;/a&gt;
     *
     * @return si
     **/
    @Schema(description = "List of values showing the side (Buy/sell) of each trade. List of supported values: <a target=\"_blank\" href=\"https://docs.google.com/spreadsheets/d/1O3aueXSPOqo7Iuyz4PqDG6yZunHsX8BTefZ2kFk5pz4/edit?usp=sharing\",>here</a>")
    public List<String> getSi() {
        return si;
    }

    public void setSi(List<String> si) {
        this.si = si;
    }

    public BondTickData cp(List<String> cp) {
        this.cp = cp;
        return this;
    }

    public BondTickData addCpItem(String cpItem) {
        if (this.cp == null) {
            this.cp = new ArrayList<String>();
        }
        this.cp.add(cpItem);
        return this;
    }

    /**
     * List of values showing the counterparty of each trade. List of supported values: &lt;a target&#x3D;\&quot;_blank\&quot; href&#x3D;\&quot;https://docs.google.com/spreadsheets/d/1O3aueXSPOqo7Iuyz4PqDG6yZunHsX8BTefZ2kFk5pz4/edit?usp&#x3D;sharing\&quot;,&gt;here&lt;/a&gt;
     *
     * @return cp
     **/
    @Schema(description = "List of values showing the counterparty of each trade. List of supported values: <a target=\"_blank\" href=\"https://docs.google.com/spreadsheets/d/1O3aueXSPOqo7Iuyz4PqDG6yZunHsX8BTefZ2kFk5pz4/edit?usp=sharing\",>here</a>")
    public List<String> getCp() {
        return cp;
    }

    public void setCp(List<String> cp) {
        this.cp = cp;
    }

    public BondTickData rp(List<String> rp) {
        this.rp = rp;
        return this;
    }

    public BondTickData addRpItem(String rpItem) {
        if (this.rp == null) {
            this.rp = new ArrayList<String>();
        }
        this.rp.add(rpItem);
        return this;
    }

    /**
     * List of values showing the reporting party of each trade. List of supported values: &lt;a target&#x3D;\&quot;_blank\&quot; href&#x3D;\&quot;https://docs.google.com/spreadsheets/d/1O3aueXSPOqo7Iuyz4PqDG6yZunHsX8BTefZ2kFk5pz4/edit?usp&#x3D;sharing\&quot;,&gt;here&lt;/a&gt;
     *
     * @return rp
     **/
    @Schema(description = "List of values showing the reporting party of each trade. List of supported values: <a target=\"_blank\" href=\"https://docs.google.com/spreadsheets/d/1O3aueXSPOqo7Iuyz4PqDG6yZunHsX8BTefZ2kFk5pz4/edit?usp=sharing\",>here</a>")
    public List<String> getRp() {
        return rp;
    }

    public void setRp(List<String> rp) {
        this.rp = rp;
    }

    public BondTickData ats(List<String> ats) {
        this.ats = ats;
        return this;
    }

    public BondTickData addAtsItem(String atsItem) {
        if (this.ats == null) {
            this.ats = new ArrayList<String>();
        }
        this.ats.add(atsItem);
        return this;
    }

    /**
     * ATS flag. Y or empty
     *
     * @return ats
     **/
    @Schema(description = "ATS flag. Y or empty")
    public List<String> getAts() {
        return ats;
    }

    public void setAts(List<String> ats) {
        this.ats = ats;
    }

    public BondTickData c(List<List<String>> c) {
        this.c = c;
        return this;
    }

    public BondTickData addCItem(List<String> cItem) {
        if (this.c == null) {
            this.c = new ArrayList<List<String>>();
        }
        this.c.add(cItem);
        return this;
    }

    /**
     * List of trade conditions. A comprehensive list of trade conditions code can be found &lt;a target&#x3D;\&quot;_blank\&quot; href&#x3D;\&quot;https://docs.google.com/spreadsheets/d/1O3aueXSPOqo7Iuyz4PqDG6yZunHsX8BTefZ2kFk5pz4/edit?usp&#x3D;sharing\&quot;&gt;here&lt;/a&gt;
     *
     * @return c
     **/
    @Schema(description = "List of trade conditions. A comprehensive list of trade conditions code can be found <a target=\"_blank\" href=\"https://docs.google.com/spreadsheets/d/1O3aueXSPOqo7Iuyz4PqDG6yZunHsX8BTefZ2kFk5pz4/edit?usp=sharing\">here</a>")
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
        BondTickData bondTickData = (BondTickData) o;
        return Objects.equals(this.skip, bondTickData.skip) &&
                Objects.equals(this.count, bondTickData.count) &&
                Objects.equals(this.total, bondTickData.total) &&
                Objects.equals(this.v, bondTickData.v) &&
                Objects.equals(this.p, bondTickData.p) &&
                Objects.equals(this.y, bondTickData.y) &&
                Objects.equals(this.t, bondTickData.t) &&
                Objects.equals(this.si, bondTickData.si) &&
                Objects.equals(this.cp, bondTickData.cp) &&
                Objects.equals(this.rp, bondTickData.rp) &&
                Objects.equals(this.ats, bondTickData.ats) &&
                Objects.equals(this.c, bondTickData.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skip, count, total, v, p, y, t, si, cp, rp, ats, c);
    }


    @Override
    public String toString() {

        String sb = "class BondTickData {\n" +
                "    skip: " + toIndentedString(skip) + "\n" +
                "    count: " + toIndentedString(count) + "\n" +
                "    total: " + toIndentedString(total) + "\n" +
                "    v: " + toIndentedString(v) + "\n" +
                "    p: " + toIndentedString(p) + "\n" +
                "    y: " + toIndentedString(y) + "\n" +
                "    t: " + toIndentedString(t) + "\n" +
                "    si: " + toIndentedString(si) + "\n" +
                "    cp: " + toIndentedString(cp) + "\n" +
                "    rp: " + toIndentedString(rp) + "\n" +
                "    ats: " + toIndentedString(ats) + "\n" +
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
