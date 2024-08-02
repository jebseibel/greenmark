package com.greenmark.database.service;

import com.greenmark.common.database.domain.BucketDb;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.Bucket;
import com.greenmark.database.db.mapper.BucketMapper;
import com.greenmark.database.db.repository.BucketRepository;
import com.greenmark.database.exceptions.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class BucketDbService extends BasicDbService {

    private final BucketRepository repository;

    public BucketDbService(BucketRepository repository) {
        super("Bucket");
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
    public BucketDb create(@NonNull String extid, @NonNull String name, @NonNull String description) throws DatabaseCreateFailureException, DatabaseAccessException {

        //look for already created
        checkCreatedAlready(extid, getCreatedAlreadyMessage(extid));

        try {
            Bucket record = new Bucket();
            record.setExtid(extid);
            record.setName(name);
            record.setDescription(description);
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);
            System.out.println(record);

            Bucket saved = repository.save(record);
            log.debug(getCreatedMessage(extid));
            return BucketMapper.toDb(saved);
        } catch (Exception e) {
            switch (e.getClass().getSimpleName()) {
                case "DataIntegrityViolationException":
                    log.info(getCreatedFailureMessage(extid));
                    throw new DatabaseCreateFailureException("DataIntegrityViolationException" + e.getMessage());
                case "ConstraintViolationException ":
                    log.info(getCreatedFailureMessage(extid));
                    throw new DatabaseCreateFailureException("ConstraintViolationException" + e.getMessage());
                default:
                    log.info(getDbAccessMessage(extid));
                    throw new DatabaseAccessException("DatabaseAccessException" + e.getMessage());
            }
        }
    }

    /**
     * Update the Bucket name and description
     *
     * @param extid       - the extid to use
     * @param name        - value for name
     * @param description - value for description
     * @return
     */
    public BucketDb update(@NonNull String extid, String name, String description) throws DatabaseRetrievalFailureException, DatabaseUpdateFailureException {
        Bucket record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        record.setName(name);
        record.setDescription(description);
        record.setModifiedAt(LocalDateTime.now());
        Bucket saved = repository.save(record);

        if (saved == null) {
            throw new DatabaseUpdateFailureException("Bucket with extid " + extid + " not saved");
        }
        checkUpdatedFailure(saved, getUpdatedFailureMessage(extid));

        log.info(getUpdatedMessage(extid));
        return BucketMapper.toDb(saved);
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
        Bucket record = repository.findByExtid(extid);

        // error if the record isn't there
        checkNullRecord(record, getFoundFailureMessage(extid));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        Bucket saved = repository.save(record);

        // error delete failed
        checkDeletedFailure(saved, getDeletedFailureMessage(extid));

        //success
        log.info(getDeletedMessage(extid));
        return true;
    }

    /**
     * Find Bucket
     *
     * @param extid - to find
     * @return boolean
     */
    public BucketDb findByExtid(@NonNull String extid) throws DatabaseRetrievalFailureException {
        Bucket record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        log.info(getFoundMessage(extid));
        return BucketMapper.toDb(record);
    }

    // ////////////////////////////////////////////////////////
    // CHECK METHODS
    // ////////////////////////////////////////////////////////

    /**
     * Checks if the retrieval of Bucket failed elses throw an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws DatabaseRetrievalFailureException
     */
    private void checkNullRecord(Bucket record, String message) throws DatabaseRetrievalFailureException {
        if (record == null) {
            throw new DatabaseRetrievalFailureException(message);
        }
    }

    /**
     * Checks if Bucket was created else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws DatabaseUpdateFailureException
     */
    private void checkUpdatedFailure(Bucket record, String message) throws DatabaseUpdateFailureException {
        if (record == null) {
            throw new DatabaseUpdateFailureException(message);
        }
    }

    /**
     * Checks if Bucket was deleted else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws DatabaseDeleteFailureException
     */
    private void checkDeletedFailure(Bucket record, String message) throws DatabaseDeleteFailureException {
        if (record == null) {
            throw new DatabaseDeleteFailureException(message);
        }
    }

    /**
     * Checks if Bucket already exists
     *
     * @param extid   - if exists throw exception
     * @param message
     * @throws DatabaseCreateFailureException
     */
    private void checkCreatedAlready(String extid, String message) throws DatabaseCreateFailureException {
        Bucket record = repository.findByExtid(extid);
        if (record != null) {
            throw new DatabaseCreateFailureException(message);
        }
    }


}
