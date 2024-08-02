package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketMinute60Db;
import com.greenmark.database.db.entity.BucketMinute60;
import org.modelmapper.ModelMapper;

public class BucketMinute60Mapper {
    public static BucketMinute60Db toDb(BucketMinute60 stock) {
        return new ModelMapper().map(stock, BucketMinute60Db.class);
    }

    public static BucketMinute60 toEntity(BucketMinute60Db itemDb) {
        return new ModelMapper().map(itemDb, BucketMinute60.class);
    }
}
