package com.greenmark.database.db.mapper;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.common.database.domain.FollowDb;
import com.greenmark.database.db.entity.Follow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FollowMapperTest {

    @Test
    void testToDb() {
        Follow item = DomainBuilder.getFollow();
        FollowDb itemDb = FollowMapper.toDb(item);

        //test
        assertEquals(item.getExtid(), itemDb.getExtid());
        assertEquals(item.getName(), itemDb.getName());
        assertEquals(item.getDescription(), itemDb.getDescription());
        assertEquals(item.getCreatedAt(), itemDb.getCreatedAt());
        assertEquals(item.getModifiedAt(), itemDb.getModifiedAt());
        assertEquals(item.getDeletedAt(), itemDb.getDeletedAt());
        assertEquals(item.getActive(), itemDb.getActive());
    }
}