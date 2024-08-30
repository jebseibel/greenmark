package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.database.DomainBuilderDatabase;
import com.greenmark.database.db.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AccountMapperTest {

    @Autowired
    AccountMapper mapper;

    @Test
    void testToDb() {
        Account item = DomainBuilderDatabase.getAccount();
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

    @Test
    void testToList() {
        Account item1 = DomainBuilderDatabase.getAccount();
        Account item2 = DomainBuilderDatabase.getAccount();
        List<Account> items = Arrays.asList(item1, item2);
        List<AccountDb> itemDbs = mapper.toList(items);

        //test
        assertEquals(items.size(), itemDbs.size());
        assertEquals(2, items.size());
        assertEquals(2, itemDbs.size());
    }
}