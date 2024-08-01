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
    public BucketDb create(@NonNull String extid, @NonNull String name, @NonNull String description) throws BucketCreateFailureException, DatabaseAccessException {

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
                    throw new BucketCreateFailureException("DataIntegrityViolationException" + e.getMessage());
                case "ConstraintViolationException ":
                    log.info(getCreatedFailureMessage(extid));
                    throw new BucketCreateFailureException("ConstraintViolationException" + e.getMessage());
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
    public BucketDb update(@NonNull String extid, String name, String description) throws BucketRetrievalFailureException, BucketUpdateFailureException {
        Bucket record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        record.setName(name);
        record.setDescription(description);
        record.setModifiedAt(LocalDateTime.now());
        Bucket saved = repository.save(record);

        if (saved == null) {
            throw new BucketUpdateFailureException("Bucket with extid " + extid + " not saved");
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
     * @throws BucketDeleteFailureException
     * @throws BucketRetrievalFailureException
     */
    public boolean delete(@NonNull String extid) throws BucketDeleteFailureException, BucketRetrievalFailureException {
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
    public BucketDb findByExtid(@NonNull String extid) throws BucketRetrievalFailureException {
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
     * @throws BucketRetrievalFailureException
     */
    private void checkNullRecord(Bucket record, String message) throws BucketRetrievalFailureException {
        if (record == null) {
            throw new BucketRetrievalFailureException(message);
        }
    }

    /**
     * Checks if Bucket was created else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws BucketUpdateFailureException
     */
    private void checkUpdatedFailure(Bucket record, String message) throws BucketUpdateFailureException {
        if (record == null) {
            throw new BucketUpdateFailureException(message);
        }
    }

    /**
     * Checks if Bucket was deleted else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws BucketDeleteFailureException
     */
    private void checkDeletedFailure(Bucket record, String message) throws BucketDeleteFailureException {
        if (record == null) {
            throw new BucketDeleteFailureException(message);
        }
    }

    /**
     * Checks if Bucket already exists
     *
     * @param extid   - if exists throw exception
     * @param message
     * @throws BucketCreateFailureException
     */
    private void checkCreatedAlready(String extid, String message) throws BucketCreateFailureException {
        Bucket record = repository.findByExtid(extid);
        if (record != null) {
            throw new BucketCreateFailureException(message);
        }
    }


}
