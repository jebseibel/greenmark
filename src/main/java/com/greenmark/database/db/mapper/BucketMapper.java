package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketDb;
import com.greenmark.database.db.entity.BucketEntity;
import org.modelmapper.ModelMapper;

public class BucketMapper
{
    public static BucketDb toDb(BucketEntity bucket) {
        return new ModelMapper().map(bucket, BucketDb.class);
    }

    public static BucketEntity toEntity(BucketDb accountDb) {
        return new ModelMapper().map(accountDb, BucketEntity.class);
    }
}
