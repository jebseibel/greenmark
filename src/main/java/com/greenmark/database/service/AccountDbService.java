package com.greenmark.database.service;

import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.common.database.domain.StockDb;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.Account;
import com.greenmark.database.db.entity.Stock;
import com.greenmark.database.db.mapper.AccountMapper;
import com.greenmark.database.db.mapper.BucketDailyMapper;
import com.greenmark.database.db.mapper.StockMapper;
import com.greenmark.database.db.repository.AccountRepository;
import com.greenmark.database.exceptions.*;
import jakarta.persistence.Access;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AccountDbService extends BasicDbService {

    private final AccountRepository repository;
    private final AccountMapper mapper;

    public AccountDbService(AccountRepository repository, AccountMapper mapper) {
        super("Account");
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Create a record with name and description
     *
     * @param extid       - the extid to use
     * @param name        - value for name
     * @param description - value for description
     * @return
     * @throws DataIntegrityViolationException
     */
    public AccountDb create(@NonNull String extid, @NonNull String name, @NonNull String description) throws DatabaseCreateFailureException, DatabaseAccessException {

        System.out.println(mapper);

        try {
            Account record = new Account();
            record.setExtid(extid);
            record.setName(name);
            record.setDescription(description);
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);
            System.out.println(record);

            Account saved = repository.save(record);
            log.debug(getCreatedMessage(extid));
            return mapper.toDb(saved);
        } catch (Exception e) {
            switch (e.getClass().getSimpleName()) {
                case "DataIntegrityViolationException":
                    log.info(getCreatedFailureMessage(extid));
                    throw new DatabaseCreateFailureException("DataIntegrityViolationException: " + e.getMessage());
                case "ConstraintViolationException ":
                    log.info(getCreatedFailureMessage(extid));
                    throw new DatabaseCreateFailureException("ConstraintViolationException: " + e.getMessage());
                default:
                    log.info(getDbAccessMessage(extid));
                    throw new DatabaseAccessException("DatabaseAccessException: " + e.getMessage());
            }
        }
    }

    /**
     * Update the Account name and description
     *
     * @param extid       - the extid to use
     * @param name        - value for name
     * @param description - value for description
     * @return
     */
    public AccountDb update(@NonNull String extid, String name, String description) throws DatabaseRetrievalFailureException, DatabaseUpdateFailureException {
        Account record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        record.setName(name);
        record.setDescription(description);
        record.setModifiedAt(LocalDateTime.now());
        Account saved = repository.save(record);

        if (saved == null) {
            throw new DatabaseUpdateFailureException("Account with extid " + extid + " not saved");
        }
        checkUpdatedFailure(saved, getUpdatedFailureMessage(extid));

        log.info(getUpdatedMessage(extid));
        return mapper.toDb(saved);
    }

    /**
     * Delete by Extid
     *
     * @param extid - to delete
     * @return boolean
     * @throws DatabaseDeleteFailureException
     * @throws DatabaseRetrievalFailureException
     */
    public boolean delete(@NonNull String extid) throws DatabaseDeleteFailureException, DatabaseRetrievalFailureException {
        Account record = repository.findByExtid(extid);

        // error if the record isn't there
        checkNullRecord(record, getFoundFailureMessage(extid));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        Account saved = repository.save(record);

        // error delete failed
        checkDeletedFailure(saved, getDeletedFailureMessage(extid));

        //success
        log.info(getDeletedMessage(extid));
        return true;
    }

    /**
     * Find Account
     *
     * @param extid - to find
     * @return boolean
     */
    public AccountDb findByExtid(@NonNull String extid) throws DatabaseRetrievalFailureException {
        Account record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        log.info(getFoundMessage(extid));
        return mapper.toDb(record);
    }

    /**
     *
     * @return
     */
    public List<AccountDb> findAll()  {
        List<Account> records = repository.findAll();
        return mapper.toList(records);
    }

    // ////////////////////////////////////////////////////////
    // CHECK METHODS
    // ////////////////////////////////////////////////////////

    /**
     * Checks if the retrieval of Account failed elses throw an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws DatabaseRetrievalFailureException
     */
    private void checkNullRecord(Account record, String message) throws DatabaseRetrievalFailureException {
        if (record == null) {
            throw new DatabaseRetrievalFailureException(message);
        }
    }

    /**
     * Checks if Account was created else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws DatabaseUpdateFailureException
     */
    private void checkUpdatedFailure(Account record, String message) throws DatabaseUpdateFailureException {
        if (record == null) {
            throw new DatabaseUpdateFailureException(message);
        }
    }

    /**
     * Checks if Account was deleted else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws DatabaseDeleteFailureException
     */
    private void checkDeletedFailure(Account record, String message) throws DatabaseDeleteFailureException {
        if (record == null) {
            throw new DatabaseDeleteFailureException(message);
        }
    }

    /**
     * Checks if Account already exists
     *
     * @param extid   - if exists throw exception
     * @param message
     * @throws DatabaseCreateFailureException
     */
    private void checkCreatedAlready(String extid, String message) throws DatabaseCreateFailureException {
        Account record = repository.findByExtid(extid);
        if (record != null) {
            throw new DatabaseCreateFailureException(message);
        }
    }


}
