package com.greenmark.database.service;

import com.greenmark.common.database.domain.AccountDb;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.AccountEntity;
import com.greenmark.database.db.mapper.AccountMapper;
import com.greenmark.database.db.repository.AccountRepository;
import com.greenmark.database.exceptions.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AccountDbService extends BasicDbService {

    private final AccountRepository repository;

    public AccountDbService(AccountRepository repository) {
        super("Account");
        this.repository = repository;
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
    public AccountDb create(@NonNull String extid, @NonNull String name, @NonNull String description) throws AccountCreateFailureException, DatabaseAccessException {

        //look for already created
        checkCreatedAlready(extid, getCreatedAlreadyMessage(extid));

        try {
            AccountEntity record = new AccountEntity();
            record.setExtid(extid);
            record.setName(name);
            record.setDescription(description);
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);
            System.out.println(record);

            AccountEntity saved = repository.save(record);
            log.debug(getCreatedMessage(extid));
            return AccountMapper.toDb(saved);
        } catch (Exception e) {
            switch (e.getClass().getSimpleName()) {
                case "DataIntegrityViolationException":
                    log.info(getCreatedFailureMessage(extid));
                    throw new AccountCreateFailureException("DataIntegrityViolationException" + e.getMessage());
                case "ConstraintViolationException ":
                    log.info(getCreatedFailureMessage(extid));
                    throw new AccountCreateFailureException("ConstraintViolationException" + e.getMessage());
                default:
                    log.info(getDbAccessMessage(extid));
                    throw new DatabaseAccessException("DatabaseAccessException" + e.getMessage());
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
    public AccountDb update(@NonNull String extid, String name, String description) throws AccountRetrievalFailureException, AccountUpdateFailureException {
        AccountEntity record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        record.setName(name);
        record.setDescription(description);
        record.setModifiedAt(LocalDateTime.now());
        AccountEntity saved = repository.save(record);

        if (saved == null) {
            throw new AccountUpdateFailureException("Account with extid " + extid + " not saved");
        }
        checkUpdatedFailure(saved, getUpdatedFailureMessage(extid));

        log.info(getUpdatedMessage(extid));
        return AccountMapper.toDb(saved);
    }

    /**
     * Delete by Extid
     *
     * @param extid - to delete
     * @return boolean
     * @throws AccountDeleteFailureException
     * @throws AccountRetrievalFailureException
     */
    public boolean delete(@NonNull String extid) throws AccountDeleteFailureException, AccountRetrievalFailureException {
        AccountEntity record = repository.findByExtid(extid);

        // error if the record isn't there
        checkNullRecord(record, getFoundFailureMessage(extid));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        AccountEntity saved = repository.save(record);

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
    public AccountDb findByExtid(@NonNull String extid) throws AccountRetrievalFailureException {
        AccountEntity record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        log.info(getFoundMessage(extid));
        return AccountMapper.toDb(record);
    }

    // ////////////////////////////////////////////////////////
    // CHECK METHODS
    // ////////////////////////////////////////////////////////

    /**
     * Checks if the retrieval of Account failed elses throw an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws AccountRetrievalFailureException
     */
    private void checkNullRecord(AccountEntity record, String message) throws AccountRetrievalFailureException {
        if (record == null) {
            throw new AccountRetrievalFailureException(message);
        }
    }

    /**
     * Checks if Account was created else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws AccountUpdateFailureException
     */
    private void checkUpdatedFailure(AccountEntity record, String message) throws AccountUpdateFailureException {
        if (record == null) {
            throw new AccountUpdateFailureException(message);
        }
    }

    /**
     * Checks if Account was deleted else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws AccountDeleteFailureException
     */
    private void checkDeletedFailure(AccountEntity record, String message) throws AccountDeleteFailureException {
        if (record == null) {
            throw new AccountDeleteFailureException(message);
        }
    }

    /**
     * Checks if Account already exists
     *
     * @param extid   - if exists throw exception
     * @param message
     * @throws AccountCreateFailureException
     */
    private void checkCreatedAlready(String extid, String message) throws AccountCreateFailureException {
        AccountEntity record = repository.findByExtid(extid);
        if (record != null) {
            throw new AccountCreateFailureException(message);
        }
    }


}
