package com.greenmark.database.service;

import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.database.DomainBuilderDatabase;
import com.greenmark.database.db.entity.Account;
import com.greenmark.database.db.mapper.AccountMapper;
import com.greenmark.database.db.repository.AccountRepository;
import com.greenmark.database.exceptions.DatabaseAccessException;
import com.greenmark.database.exceptions.DatabaseDeleteFailureException;
import com.greenmark.database.exceptions.DatabaseRetrievalFailureException;
import com.greenmark.database.exceptions.DatabaseUpdateFailureException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PositionDbServiceTest {

    @InjectMocks
    AccountDbService service;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper mapper;

    @Nested
    class CreateTests {

        Account item = DomainBuilderDatabase.getAccount();
        AccountDb itemDb = DomainBuilderDatabase.getAccountDb(item);
        String extid = item.getExtid();
        String name = item.getName();
        String description = item.getDescription();

        @Test
        void created() throws Exception {
            // mocks
            when(accountRepository.save(any(Account.class))).thenReturn(item);
            when(mapper.toDb(item)).thenReturn(itemDb);

            AccountDb result = service.create(extid, name, description);

            // validate
            assertNotNull(result);
            assertEquals(extid, result.getExtid());
            assertEquals(name, result.getName());
            assertEquals(description, result.getDescription());
        }
    }

    @Nested
    class UpdateTests {

        Account item = DomainBuilderDatabase.getAccount();
        AccountDb itemDb = DomainBuilderDatabase.getAccountDb(item);
        Optional<Account> itemOptional = Optional.of(item);


        @Test
        void updated() throws DatabaseUpdateFailureException, DatabaseRetrievalFailureException, DatabaseAccessException {
            String extid = UUID.randomUUID().toString();
            String newName = DomainBuilderDatabase.getNameRandom();
            String newDescription = DomainBuilderDatabase.getNameRandom();

            // mocks
            when(accountRepository.findByExtid(extid)).thenReturn(itemOptional);
            when(accountRepository.save(any(Account.class))).thenReturn(item);
            when(mapper.toDb(item)).thenReturn(itemDb);

            // execute
            AccountDb result = service.update(extid, newName, newDescription);

            // validate
            assertNotNull(result);
        }


        @Test
        void updatedBadExtid() {
            // setup
            String extidBad = item.getExtid();

            // mocks
            when(accountRepository.findByExtid(extidBad)).thenReturn(Optional.empty());

            // test
            assertThrows(DatabaseRetrievalFailureException.class, () -> service.findByExtid(extidBad));
        }
    }

    @Nested
    class FindMockTests {

        Account item = DomainBuilderDatabase.getAccount();
        AccountDb itemDb = DomainBuilderDatabase.getAccountDb(item);
        Optional<Account> itemOptional = Optional.of(item);
        String extid = item.getExtid();

        @Test
        @DisplayName("FindByExtId - Found")
        void findByExtid() throws DatabaseRetrievalFailureException {
            // mocks
            when(accountRepository.findByExtid(extid)).thenReturn(itemOptional);
            when(mapper.toDb(item)).thenReturn(itemDb);

            // execute
            AccountDb result = service.findByExtid(extid);

            // validate
            //verify(accountRepository).findByExtid(extid);
            assertNotNull(result);
            assertEquals(result.getExtid(), extid);
        }

        @Test
        @DisplayName("FindByExtId - Not found")
        void findByExtid_Notfound() throws DatabaseRetrievalFailureException {
            // setup
            String extidBad = item.getExtid();

            // mocks
            when(accountRepository.findByExtid(extidBad)).thenReturn(Optional.empty());

            // test
            assertThrows(DatabaseRetrievalFailureException.class, () -> service.findByExtid(extidBad));
        }
    }

    @Nested
    class DeleteTests {

        @Test
        void deleted() throws DatabaseRetrievalFailureException, DatabaseDeleteFailureException {
            Account item = DomainBuilderDatabase.getAccount();
            AccountDb itemDb = DomainBuilderDatabase.getAccountDb(item);
            String extid = item.getExtid();

            //mock
            when(accountRepository.findByExtid(extid)).thenReturn(Optional.of(item));

            //execute
            boolean result = service.delete(extid);

            // validate
            assertTrue(result);
        }

        @Test
        void deleteBadExtid() {
            String badExtid = UUID.randomUUID().toString();
            assertThrows(DatabaseRetrievalFailureException.class, () -> service.delete(badExtid));
        }
    }
}