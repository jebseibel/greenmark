package com.greenmark.common.database.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockNightlyDb {
    private String symbol;
    private Float open;
    private Float close;
    private Float high;
    private Float low;
    private Long volume;
    private Float macd;
    private Float stochk;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
    private Integer active;
}
