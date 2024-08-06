package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketDailyDb;
import com.greenmark.database.db.entity.BucketDaily;
import org.modelmapper.ModelMapper;

public class BucketDailyMapper {
    public static BucketDailyDb toDb(BucketDaily item) {
        return new ModelMapper().map(item, BucketDailyDb.class);
    }

    public static BucketDaily toEntity(BucketDailyDb itemDb) {
        return new ModelMapper().map(itemDb, BucketDaily.class);
    }
}
