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
 * SentimentContent
 */

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-22T10:38:13.551248835+01:00[Europe/Rome]")

public class SentimentContent {
  @SerializedName("mention")
  private Long mention = null;

  @SerializedName("positiveMention")
  private Long positiveMention = null;

  @SerializedName("negativeMention")
  private Long negativeMention = null;

  @SerializedName("positiveScore")
  private Float positiveScore = null;

  @SerializedName("negativeScore")
  private Float negativeScore = null;

  @SerializedName("score")
  private Float score = null;

  @SerializedName("atTime")
  private String atTime = null;

  public SentimentContent mention(Long mention) {
    this.mention = mention;
    return this;
  }

   /**
   * Number of mentions
   * @return mention
  **/
  @Schema(description = "Number of mentions")
  public Long getMention() {
    return mention;
  }

  public void setMention(Long mention) {
    this.mention = mention;
  }

  public SentimentContent positiveMention(Long positiveMention) {
    this.positiveMention = positiveMention;
    return this;
  }

   /**
   * Number of positive mentions
   * @return positiveMention
  **/
  @Schema(description = "Number of positive mentions")
  public Long getPositiveMention() {
    return positiveMention;
  }

  public void setPositiveMention(Long positiveMention) {
    this.positiveMention = positiveMention;
  }

  public SentimentContent negativeMention(Long negativeMention) {
    this.negativeMention = negativeMention;
    return this;
  }

   /**
   * Number of negative mentions
   * @return negativeMention
  **/
  @Schema(description = "Number of negative mentions")
  public Long getNegativeMention() {
    return negativeMention;
  }

  public void setNegativeMention(Long negativeMention) {
    this.negativeMention = negativeMention;
  }

  public SentimentContent positiveScore(Float positiveScore) {
    this.positiveScore = positiveScore;
    return this;
  }

   /**
   * Positive score. Range 0-1
   * @return positiveScore
  **/
  @Schema(description = "Positive score. Range 0-1")
  public Float getPositiveScore() {
    return positiveScore;
  }

  public void setPositiveScore(Float positiveScore) {
    this.positiveScore = positiveScore;
  }

  public SentimentContent negativeScore(Float negativeScore) {
    this.negativeScore = negativeScore;
    return this;
  }

   /**
   * Negative score. Range 0-1
   * @return negativeScore
  **/
  @Schema(description = "Negative score. Range 0-1")
  public Float getNegativeScore() {
    return negativeScore;
  }

  public void setNegativeScore(Float negativeScore) {
    this.negativeScore = negativeScore;
  }

  public SentimentContent score(Float score) {
    this.score = score;
    return this;
  }

   /**
   * Final score. Range: -1 to 1 with 1 is very positive and -1 is very negative
   * @return score
  **/
  @Schema(description = "Final score. Range: -1 to 1 with 1 is very positive and -1 is very negative")
  public Float getScore() {
    return score;
  }

  public void setScore(Float score) {
    this.score = score;
  }

  public SentimentContent atTime(String atTime) {
    this.atTime = atTime;
    return this;
  }

   /**
   * Period.
   * @return atTime
  **/
  @Schema(description = "Period.")
  public String getAtTime() {
    return atTime;
  }

  public void setAtTime(String atTime) {
    this.atTime = atTime;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SentimentContent sentimentContent = (SentimentContent) o;
    return Objects.equals(this.mention, sentimentContent.mention) &&
        Objects.equals(this.positiveMention, sentimentContent.positiveMention) &&
        Objects.equals(this.negativeMention, sentimentContent.negativeMention) &&
        Objects.equals(this.positiveScore, sentimentContent.positiveScore) &&
        Objects.equals(this.negativeScore, sentimentContent.negativeScore) &&
        Objects.equals(this.score, sentimentContent.score) &&
        Objects.equals(this.atTime, sentimentContent.atTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mention, positiveMention, negativeMention, positiveScore, negativeScore, score, atTime);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SentimentContent {\n");
    
    sb.append("    mention: ").append(toIndentedString(mention)).append("\n");
    sb.append("    positiveMention: ").append(toIndentedString(positiveMention)).append("\n");
    sb.append("    negativeMention: ").append(toIndentedString(negativeMention)).append("\n");
    sb.append("    positiveScore: ").append(toIndentedString(positiveScore)).append("\n");
    sb.append("    negativeScore: ").append(toIndentedString(negativeScore)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
    sb.append("    atTime: ").append(toIndentedString(atTime)).append("\n");
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
