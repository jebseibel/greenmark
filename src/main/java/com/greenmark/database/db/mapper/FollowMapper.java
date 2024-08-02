package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.FollowDb;
import com.greenmark.database.db.entity.Follow;
import org.modelmapper.ModelMapper;

public class FollowMapper {
    public static FollowDb toDb(Follow item) {
        return new ModelMapper().map(item, FollowDb.class);
    }

    public static Follow toEntity(FollowDb itemDb) {
        return new ModelMapper().map(itemDb, Follow.class);
    }
}
