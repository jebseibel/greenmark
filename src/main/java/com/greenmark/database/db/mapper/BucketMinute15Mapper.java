package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketMinute15Db;
import com.greenmark.database.db.entity.BucketMinute15;
import org.modelmapper.ModelMapper;

public class BucketMinute15Mapper {
    public static BucketMinute15Db toDb(BucketMinute15 item) {
        return new ModelMapper().map(item, BucketMinute15Db.class);
    }

    public static BucketMinute15 toEntity(BucketMinute15Db itemDb) {
        return new ModelMapper().map(itemDb, BucketMinute15.class);
    }
}
