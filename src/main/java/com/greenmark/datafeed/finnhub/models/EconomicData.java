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
 * EconomicData
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class EconomicData {
  @SerializedName("data")
  private List<EconomicDataInfo> data = null;

  @SerializedName("code")
  private String code = null;

  public EconomicData data(List<EconomicDataInfo> data) {
    this.data = data;
    return this;
  }

  public EconomicData addDataItem(EconomicDataInfo dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<EconomicDataInfo>();
    }
    this.data.add(dataItem);
    return this;
  }

   /**
   * Array of economic data for requested code.
   * @return data
  **/
  @Schema(description = "Array of economic data for requested code.")
  public List<EconomicDataInfo> getData() {
    return data;
  }

  public void setData(List<EconomicDataInfo> data) {
    this.data = data;
  }

  public EconomicData code(String code) {
    this.code = code;
    return this;
  }

   /**
   * Finnhub economic code
   * @return code
  **/
  @Schema(description = "Finnhub economic code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EconomicData economicData = (EconomicData) o;
    return Objects.equals(this.data, economicData.data) &&
        Objects.equals(this.code, economicData.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, code);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EconomicData {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("}");
    return sb.toString();
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
