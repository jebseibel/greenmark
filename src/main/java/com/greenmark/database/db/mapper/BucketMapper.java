package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketDb;
import com.greenmark.database.db.entity.Bucket;
import org.modelmapper.ModelMapper;

public class BucketMapper {
    public static BucketDb toDb(Bucket item) {
        return new ModelMapper().map(item, BucketDb.class);
    }

    public static Bucket toEntity(BucketDb itemDb) {
        return new ModelMapper().map(itemDb, Bucket.class);
    }
}
