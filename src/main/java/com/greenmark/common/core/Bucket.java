package com.greenmark.common.core;

import com.greenmark.common.database.domain.StockWatchDb;

import java.util.List;

public class Bucket {
    private String name;
    private long count;
    private List<StockWatchDb> size;
}
