package com.greenmark.database.service;

import com.greenmark.common.database.domain.Account;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.AccountDb;
import com.greenmark.database.db.mapper.AccountMapper;
import com.greenmark.database.db.repository.AccountRepository;
import com.greenmark.database.exceptions.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AccountDbService extends BaseDbService {

    private final AccountRepository repository;
    private final AccountMapper mapper;

    public AccountDbService(AccountRepository repository, AccountMapper mapper) {
        super("AccountDb");
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
    public Account create(@NonNull String extid, @NonNull String name, @NonNull String description) throws DatabaseCreateFailureException, DatabaseAccessException {

        try {
            AccountDb record = new AccountDb();
            record.setExtid(extid);
            record.setName(name);
            record.setDescription(description);
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);
            AccountDb saved = repository.save(record);
            log.debug(getCreatedMessage(extid));
            return mapper.toEntity(saved);
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
     * Update the AccountDb name and description
     *
     * @param extid       - the extid to use
     * @param name        - value for name
     * @param description - value for description
     * @return
     */
    public Account update(@NonNull String extid, String name, String description)
            throws DatabaseRetrievalFailureException, DatabaseUpdateFailureException, DatabaseAccessException {

        AccountDb record = repository.findByExtid(extid).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(extid)));
        try {

            record.setName(name);
            record.setDescription(description);
            record.setModifiedAt(LocalDateTime.now());
            AccountDb saved = repository.save(record);
            log.info(getUpdatedMessage(extid));
            return mapper.toEntity(saved);
        } catch (Exception e) {
            switch (e.getClass().getSimpleName()) {
                case "DataIntegrityViolationException", "ConstraintViolationException":
                    log.info(getUpdatedMessage(extid));
                    throw new DatabaseUpdateFailureException(getUpdatedMessage(extid));
                default:
                    log.info(getDbAccessMessage(extid));
                    throw new DatabaseAccessException(getDbAccessMessage(extid));
            }
        }
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
        // error if the record isn't there
        AccountDb record = repository.findByExtid(extid).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(extid)));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        repository.save(record);

        //success
        log.info(getDeletedMessage(extid));
        return true;
    }

    /**
     * Find AccountDb
     *
     * @param extid - to find
     * @return boolean
     */
    public Account findByExtid(@NonNull String extid) throws DatabaseRetrievalFailureException {
        AccountDb record = repository.findByExtid(extid).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(extid)));
        log.info(getFoundMessage(extid));
        return mapper.toEntity(record);
    }

    /**
     * @return
     */
    public List<Account> findAll() {
        List<AccountDb> records = repository.findAll();
        return mapper.toList(records);
    }
}
