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
 * Quote
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class Quote {
  @SerializedName("o")
  private Float o = null;

  @SerializedName("h")
  private Float h = null;

  @SerializedName("l")
  private Float l = null;

  @SerializedName("c")
  private Float c = null;

  @SerializedName("pc")
  private Float pc = null;

  @SerializedName("d")
  private Float d = null;

  @SerializedName("dp")
  private Float dp = null;

  public Quote o(Float o) {
    this.o = o;
    return this;
  }

   /**
   * Open price of the day
   * @return o
  **/
  @Schema(description = "Open price of the day")
  public Float getO() {
    return o;
  }

  public void setO(Float o) {
    this.o = o;
  }

  public Quote h(Float h) {
    this.h = h;
    return this;
  }

   /**
   * High price of the day
   * @return h
  **/
  @Schema(description = "High price of the day")
  public Float getH() {
    return h;
  }

  public void setH(Float h) {
    this.h = h;
  }

  public Quote l(Float l) {
    this.l = l;
    return this;
  }

   /**
   * Low price of the day
   * @return l
  **/
  @Schema(description = "Low price of the day")
  public Float getL() {
    return l;
  }

  public void setL(Float l) {
    this.l = l;
  }

  public Quote c(Float c) {
    this.c = c;
    return this;
  }

   /**
   * Current price
   * @return c
  **/
  @Schema(description = "Current price")
  public Float getC() {
    return c;
  }

  public void setC(Float c) {
    this.c = c;
  }

  public Quote pc(Float pc) {
    this.pc = pc;
    return this;
  }

   /**
   * Previous close price
   * @return pc
  **/
  @Schema(description = "Previous close price")
  public Float getPc() {
    return pc;
  }

  public void setPc(Float pc) {
    this.pc = pc;
  }

  public Quote d(Float d) {
    this.d = d;
    return this;
  }

   /**
   * Change
   * @return d
  **/
  @Schema(description = "Change")
  public Float getD() {
    return d;
  }

  public void setD(Float d) {
    this.d = d;
  }

  public Quote dp(Float dp) {
    this.dp = dp;
    return this;
  }

   /**
   * Percent change
   * @return dp
  **/
  @Schema(description = "Percent change")
  public Float getDp() {
    return dp;
  }

  public void setDp(Float dp) {
    this.dp = dp;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Quote quote = (Quote) o;
    return Objects.equals(this.o, quote.o) &&
        Objects.equals(this.h, quote.h) &&
        Objects.equals(this.l, quote.l) &&
        Objects.equals(this.c, quote.c) &&
        Objects.equals(this.pc, quote.pc) &&
        Objects.equals(this.d, quote.d) &&
        Objects.equals(this.dp, quote.dp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(o, h, l, c, pc, d, dp);
  }


  @Override
  public String toString() {

      String sb = "class Quote {\n" +
              "    o: " + toIndentedString(o) + "\n" +
              "    h: " + toIndentedString(h) + "\n" +
              "    l: " + toIndentedString(l) + "\n" +
              "    c: " + toIndentedString(c) + "\n" +
              "    pc: " + toIndentedString(pc) + "\n" +
              "    d: " + toIndentedString(d) + "\n" +
              "    dp: " + toIndentedString(dp) + "\n" +
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
