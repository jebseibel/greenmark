package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketDb;
import com.greenmark.database.db.entity.Bucket;
import org.modelmapper.ModelMapper;

public class BucketMapper
{
    public static BucketDb toDb(Bucket bucket) {
        return new ModelMapper().map(bucket, BucketDb.class);
    }

    public static Bucket toEntity(BucketDb accountDb) {
        return new ModelMapper().map(accountDb, Bucket.class);
    }
}
