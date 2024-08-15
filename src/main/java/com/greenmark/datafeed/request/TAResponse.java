package com.greenmark.datafeed.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TAResponse {
    private Map<String, List<Double>> values;
    private Map<String, List<Long>> timestamps;
}
