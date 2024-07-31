package com.greenmark.database.db.mapper;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.database.db.entity.AccountEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountEntityMapperTest {

    @Test
    void testToDb() {
        AccountEntity item = DomainBuilder.getAccount();
        AccountDb itemDb = AccountMapper.toDb(item);

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