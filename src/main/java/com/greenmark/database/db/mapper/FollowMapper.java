package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.FollowDb;
import com.greenmark.database.db.entity.Follow;
import org.modelmapper.ModelMapper;

public class FollowMapper {
    public static FollowDb toDb(Follow follow) {
        return new ModelMapper().map(follow, FollowDb.class);
    }

    public static Follow toEntity(FollowDb accountDb) {
        return new ModelMapper().map(accountDb, Follow.class);
    }
}
