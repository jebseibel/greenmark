package com.greenmark.common.database.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Data
public class StockData {
    private BigDecimal current;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal previousClose;
    private BigDecimal changed;
    private BigDecimal changedPercent;
    private BigDecimal macd;
    private BigDecimal stochk;

    public StockData() {

    }
}
