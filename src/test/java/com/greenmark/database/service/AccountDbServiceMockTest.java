package com.greenmark.database.service;

import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.database.db.DomainBuilder;
import com.greenmark.database.db.mapper.AccountMapper;
import com.greenmark.database.db.repository.AccountRepository;
import com.greenmark.database.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountDbServiceMockTest {

    @Mock
    AccountRepository mockRepository;

//    @Mock
//    AccountMapper mockMapper;

    @InjectMocks
    AccountDbService service;

//    @Test
//    void findAll() throws Exception {
//        // setup
//        AccountDb accountDb = new AccountDb();
//        List<AccountDb> accountDbs = new ArrayList<AccountDb>();
//        accountDbs.add(accountDb);
//
//        // setup Mock
//        when(service.findAll()).thenReturn(accountDbs);
//        when(mockMapper.toList(any())).thenReturn(accountDbs);
//
//        //run test
//        List<AccountDb> result = service.findAll();
//
//        //validate
//        verify(mockRepository.findAll());
//        assertThat(result).hasSize(1);
//    }

    @Disabled
    @Nested
    class CreateTests {

        String random = DomainBuilder.randomString();
        String name = DomainBuilder.getNameRandom(random);
        String description = DomainBuilder.getNameRandom(random);

        @Test
        void created() throws Exception {
            // test
            String extid = DomainBuilder.getUUID();
            AccountDb result = service.create(extid, name, description);

            // validate
            assertNotNull(result);
            assertEquals(result.getName(), name);
            assertEquals(result.getDescription(), description);
        }

        @Test
        void createdTooLong() {
            // test
            String extid = DomainBuilder.getStringTestUUID();
            assertThrows(DatabaseCreateFailureException.class, () -> service.create(extid, name, description));
        }
    }

}