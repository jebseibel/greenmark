package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketMinute60Db;
import com.greenmark.common.database.domain.StockDailyDb;
import com.greenmark.database.db.entity.BucketMinute60;
import com.greenmark.database.db.entity.StockDaily;
import org.modelmapper.ModelMapper;

import java.util.List;

public class StockDailyMapper {
    public static StockDailyDb toDb(StockDaily item) {
        return new ModelMapper().map(item, StockDailyDb.class);
    }

    public static StockDaily toEntity(StockDailyDb itemDb) {
        return new ModelMapper().map(itemDb, StockDaily.class);
    }

    public static List<StockDailyDb> toList(List<StockDaily> items) {
        return items.stream().map( item -> toDb(item)).toList();
    }
}
