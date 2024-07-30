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

/**
 * SymbolLookupInfo
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class SymbolLookupInfo {
  @SerializedName("description")
  private String description = null;

  @SerializedName("displaySymbol")
  private String displaySymbol = null;

  @SerializedName("symbol")
  private String symbol = null;

  @SerializedName("type")
  private String type = null;

  public SymbolLookupInfo description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Symbol description
   * @return description
  **/
  @Schema(description = "Symbol description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public SymbolLookupInfo displaySymbol(String displaySymbol) {
    this.displaySymbol = displaySymbol;
    return this;
  }

   /**
   * Display symbol name.
   * @return displaySymbol
  **/
  @Schema(description = "Display symbol name.")
  public String getDisplaySymbol() {
    return displaySymbol;
  }

  public void setDisplaySymbol(String displaySymbol) {
    this.displaySymbol = displaySymbol;
  }

  public SymbolLookupInfo symbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

   /**
   * Unique symbol used to identify this symbol used in &lt;code&gt;/stock/candle&lt;/code&gt; endpoint.
   * @return symbol
  **/
  @Schema(description = "Unique symbol used to identify this symbol used in <code>/stock/candle</code> endpoint.")
  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public SymbolLookupInfo type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Security type.
   * @return type
  **/
  @Schema(description = "Security type.")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SymbolLookupInfo symbolLookupInfo = (SymbolLookupInfo) o;
    return Objects.equals(this.description, symbolLookupInfo.description) &&
        Objects.equals(this.displaySymbol, symbolLookupInfo.displaySymbol) &&
        Objects.equals(this.symbol, symbolLookupInfo.symbol) &&
        Objects.equals(this.type, symbolLookupInfo.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, displaySymbol, symbol, type);
  }


  @Override
  public String toString() {

      String sb = "class SymbolLookupInfo {\n" +
              "    description: " + toIndentedString(description) + "\n" +
              "    displaySymbol: " + toIndentedString(displaySymbol) + "\n" +
              "    symbol: " + toIndentedString(symbol) + "\n" +
              "    type: " + toIndentedString(type) + "\n" +
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
