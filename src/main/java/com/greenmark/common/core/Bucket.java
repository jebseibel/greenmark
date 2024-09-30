package com.greenmark.common.core;

import com.greenmark.common.database.domain.StockWatch;

import java.util.List;

public class Bucket {
    private String name;
    private long count;
    private List<StockWatch> size;
}
