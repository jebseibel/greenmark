package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketDailyDb;
import com.greenmark.common.database.domain.BucketMinute60Db;
import com.greenmark.database.db.entity.BucketDaily;
import com.greenmark.database.db.entity.BucketMinute60;
import org.modelmapper.ModelMapper;

import java.util.List;

public class BucketMinute60Mapper {

    public static BucketMinute60Db toDb(BucketMinute60 item) {
        return new ModelMapper().map(item, BucketMinute60Db.class);
    }

    public static BucketMinute60 toEntity(BucketMinute60Db itemDb) {
        return new ModelMapper().map(itemDb, BucketMinute60.class);
    }

    public static List<BucketMinute60Db> toList(List<BucketMinute60> items) {
        return items.stream().map( item -> toDb(item)).toList();
    }
}
