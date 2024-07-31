package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.FollowDb;
import com.greenmark.database.db.entity.FollowEntity;
import org.modelmapper.ModelMapper;

public class FollowMapper
{
    public static FollowDb toDb(FollowEntity follow) {
        return new ModelMapper().map(follow, FollowDb.class);
    }

    public static FollowEntity toEntity(FollowDb accountDb) {
        return new ModelMapper().map(accountDb, FollowEntity.class);
    }
}
