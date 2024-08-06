package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketMinute05Db;
import com.greenmark.common.database.domain.BucketMinute60Db;
import com.greenmark.database.db.entity.BucketMinute05;
import com.greenmark.database.db.entity.BucketMinute60;
import org.modelmapper.ModelMapper;

import java.util.List;

public class BucketMinute05Mapper {

    public static BucketMinute05Db toDb(BucketMinute05 item) {
        return new ModelMapper().map(item, BucketMinute05Db.class);
    }

    public static BucketMinute05 toEntity(BucketMinute05Db itemDb) {
        return new ModelMapper().map(itemDb, BucketMinute05.class);
    }

    public static List<BucketMinute05Db> toList(List<BucketMinute05> items) {
        return items.stream().map( item -> toDb(item)).toList();
    }
}
