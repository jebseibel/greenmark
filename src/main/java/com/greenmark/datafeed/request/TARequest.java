package com.greenmark.datafeed.request;

import lombok.Data;

import java.util.Map;

@Data
public class TARequest {
    private String symbol;
    private String resolution;
    private long from;
    private long to;
    private String indicator;
    private Map<String, Integer> indicatorFields;
}
