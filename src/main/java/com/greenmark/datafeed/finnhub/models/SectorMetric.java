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
 * SectorMetric
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class SectorMetric {
  @SerializedName("region")
  private String region = null;

  @SerializedName("data")
  private List<SectorMetricData> data = null;

  public SectorMetric region(String region) {
    this.region = region;
    return this;
  }

   /**
   * Region.
   * @return region
  **/
  @Schema(description = "Region.")
  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public SectorMetric data(List<SectorMetricData> data) {
    this.data = data;
    return this;
  }

  public SectorMetric addDataItem(SectorMetricData dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<SectorMetricData>();
    }
    this.data.add(dataItem);
    return this;
  }

   /**
   * Metrics for each sector.
   * @return data
  **/
  @Schema(description = "Metrics for each sector.")
  public List<SectorMetricData> getData() {
    return data;
  }

  public void setData(List<SectorMetricData> data) {
    this.data = data;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SectorMetric sectorMetric = (SectorMetric) o;
    return Objects.equals(this.region, sectorMetric.region) &&
        Objects.equals(this.data, sectorMetric.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(region, data);
  }


  @Override
  public String toString() {

      String sb = "class SectorMetric {\n" +
              "    region: " + toIndentedString(region) + "\n" +
              "    data: " + toIndentedString(data) + "\n" +
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
