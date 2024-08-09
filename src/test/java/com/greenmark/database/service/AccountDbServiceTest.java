package com.greenmark.database.service;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.database.db.entity.Account;
import com.greenmark.database.db.mapper.AccountMapper;
import com.greenmark.database.db.repository.AccountRepository;
import com.greenmark.database.exceptions.*;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AccountDbServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper mapper;

    @InjectMocks
    AccountDbService service;

    @Nested
    class FindMockTests {

        @Test
        void findByExtid() throws DatabaseRetrievalFailureException {
            // setup
            Account item = DomainBuilder.getAccount();
            AccountDb itemDb = DomainBuilder.getAccountDb(item);
            Optional<Account> itemOptional = Optional.of(item);
            String extid = item.getExtid();

            //mocks
            when(accountRepository.findByExtid(extid)).thenReturn(itemOptional);
            when(mapper.toDb(item)).thenReturn(itemDb);

            //execute
            AccountDb result = service.findByExtid(extid);

            // validate
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }
    }


    @Nested
    class CreateTests {

        AccountDb record;
//        String extid;
//        String random = DomainBuilder.randomString();
//        String name = DomainBuilder.getNameRandom(random);
//        String description = DomainBuilder.getNameRandom(random);

        @Test
        void created() throws Exception {
            // set
            Account item = DomainBuilder.getAccount();
            AccountDb itemDb = DomainBuilder.getAccountDb(item);
            String extid = item.getExtid();
            String name = item.getName();
            String description = item.getDescription();

            //mocks
            when(accountRepository.save(item)).thenReturn(item);
            //when(accountRepository.save(any(Account.class))).thenReturn(item);
            //lenient().when(accountRepository.save(item)).thenReturn(item);
            when(mapper.toDb(item)).thenReturn(itemDb);

            AccountDb result = service.create(extid, name, description);
            System.out.println(extid);
            System.out.println(result.getExtid());

            // validate
            assertNotNull(result);
            assertEquals(extid, result.getExtid());
            assertEquals(name, result.getName());
            assertEquals(description, result.getDescription());
        }

//        @Test
//        void createdTooLong() {
//            // test
//            String extid = DomainBuilder.getStringTestUUID();
//            assertThrows(DatabaseCreateFailureException.class, () -> service.create(extid, name, description));
//        }
    }

    @Disabled
    @Nested
    class UpdateTests {

        AccountDb record;
        String extid;
        String random = DomainBuilder.randomString();
        String name = DomainBuilder.getNameRandom(random);
        String description = DomainBuilder.getNameRandom(random);

        @BeforeEach
        void beforeEach() throws DatabaseCreateFailureException, DatabaseAccessException {
            extid = DomainBuilder.getUUID();
            record = service.create(extid, name, description);
        }

        @Test
        void updated() throws DatabaseUpdateFailureException, DatabaseRetrievalFailureException, DatabaseAccessException {
            String newName = DomainBuilder.getNameRandom();
            String newDescription = DomainBuilder.getNameRandom();

            //execute
            AccountDb result = service.update(extid, newName, newDescription);

            // validate
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
            assertEquals(result.getName(), newName);
            assertEquals(result.getDescription(), newDescription);
        }


        @Test
        void updatedBadExtid() {
            // test
            try {
                String badExtid = UUID.randomUUID().toString();
                service.update(badExtid, name, description);
                fail();
            }
            catch (DatabaseRetrievalFailureException e) {
                assertTrue(true);
            }
            catch (Exception e) {
                fail();
            }
        }
    }

    @Nested
    class FindTests {

//        @Test
//        void findByExtid() throws DatabaseRetrievalFailureException {
//            Account item = DomainBuilder.getAccount();
//            AccountDb itemDb = DomainBuilder.getAccountDb(item);
//            String extid = item.getExtid();
//
//            //mock
//            when(accountRepository.findByExtid(extid)).thenReturn(item);
//            when(mapper.toDb(item)).thenReturn(itemDb);
//
//            //execute
//            AccountDb result = service.findByExtid(extid);
//
//            // validate
//            assertNotNull(result);
//            assertEquals(result.getExtid(), extid);
//        }

        @Test
        void findByExtid_NotFound() {
            String badExtid = UUID.randomUUID().toString();
            assertThrows(DatabaseRetrievalFailureException.class, () -> service.findByExtid(badExtid));
        }
    }

    @Nested
    @Disabled
    class DeleteTests {

//        @Test
//        void deleted() throws DatabaseRetrievalFailureException, DatabaseDeleteFailureException {
//            Account item = DomainBuilder.getAccount();
//            AccountDb itemDb = DomainBuilder.getAccountDb(item);
//            String extid = item.getExtid();
//
//            //mock
//            when(accountRepository.findByExtid(extid)).thenReturn(item);
//            when(mapper.toDb(item)).thenReturn(itemDb);
//
//            //execute
//            AccountDb result = service.findByExtid(extid);
//
//            boolean result = service.delete(extid);
//
//            // validate
//            assertTrue(result);
//        }

        @Test
        void deleteBadExtid() {
            String badExtid = UUID.randomUUID().toString();
            assertThrows(DatabaseRetrievalFailureException.class, () -> service.delete(badExtid));
        }
    }
}