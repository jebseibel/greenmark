package com.greenmark.database.db.mapper;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.database.db.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountMapperTest {

    @Autowired
    AccountMapper mapper;

    @Test
    void testToDb() {
        Account item = DomainBuilder.getAccount();
        AccountDb itemDb = mapper.toDb(item);

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