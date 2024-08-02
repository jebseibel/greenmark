package com.greenmark.common.database.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BucketMinute60Db {
    private String symbol;
    private Float current;
    private Float open;
    private Float high;
    private Float low;
    private Float previousClose;
    private Float macd;
    private Float stochk;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
    private Integer active;
}
