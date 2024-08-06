package com.greenmark.common.core;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class TechnicalIndicators {
    private float open;
    private float high;
    private float low;
    private float current;
    private float previousClose;
    private float change;
    private float changePercent;
    private float macd;
    private float stochk;
}
