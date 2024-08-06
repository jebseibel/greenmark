package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketMinute01Db;
import com.greenmark.database.db.entity.BucketMinute01;
import org.modelmapper.ModelMapper;

public class BucketMinute01Mapper {
    public static BucketMinute01Db toDb(BucketMinute01 item) {
        return new ModelMapper().map(item, BucketMinute01Db.class);
    }

    public static BucketMinute01 toEntity(BucketMinute01Db itemDb) {
        return new ModelMapper().map(itemDb, BucketMinute01.class);
    }
}
