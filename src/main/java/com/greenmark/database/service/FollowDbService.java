package com.greenmark.database.service;

import com.greenmark.common.database.domain.FollowDb;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.Follow;
import com.greenmark.database.db.mapper.FollowMapper;
import com.greenmark.database.db.repository.FollowRepository;
import com.greenmark.database.exceptions.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class FollowDbService extends BasicDbService {

    private final FollowRepository repository;

    public FollowDbService(FollowRepository repository) {
        super("Follow");
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
    public FollowDb create(@NonNull String extid, @NonNull String name, @NonNull String description) throws FollowCreateFailureException, DatabaseAccessException {

        //look for already created
        checkCreatedAlready(extid, getCreatedAlreadyMessage(extid));

        try {
            Follow record = new Follow();
            record.setExtid(extid);
            record.setName(name);
            record.setDescription(description);
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);
            System.out.println(record);

            Follow saved = repository.save(record);
            log.debug(getCreatedMessage(extid));
            return FollowMapper.toDb(saved);
        } catch (Exception e) {
            switch (e.getClass().getSimpleName()) {
                case "DataIntegrityViolationException":
                    log.info(getCreatedFailureMessage(extid));
                    throw new FollowCreateFailureException("DataIntegrityViolationException" + e.getMessage());
                case "ConstraintViolationException ":
                    log.info(getCreatedFailureMessage(extid));
                    throw new FollowCreateFailureException("ConstraintViolationException" + e.getMessage());
                default:
                    log.info(getDbAccessMessage(extid));
                    throw new DatabaseAccessException("DatabaseAccessException" + e.getMessage());
            }
        }
    }

    /**
     * Update the Follow name and description
     *
     * @param extid       - the extid to use
     * @param name        - value for name
     * @param description - value for description
     * @return
     */
    public FollowDb update(@NonNull String extid, String name, String description) throws FollowRetrievalFailureException, FollowUpdateFailureException {
        Follow record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        record.setName(name);
        record.setDescription(description);
        record.setModifiedAt(LocalDateTime.now());
        Follow saved = repository.save(record);

        if (saved == null) {
            throw new FollowUpdateFailureException("Follow with extid " + extid + " not saved");
        }
        checkUpdatedFailure(saved, getUpdatedFailureMessage(extid));

        log.info(getUpdatedMessage(extid));
        return FollowMapper.toDb(saved);
    }

    /**
     * Delete by Extid
     *
     * @param extid - to delete
     * @return boolean
     * @throws FollowDeleteFailureException
     * @throws FollowRetrievalFailureException
     */
    public boolean delete(@NonNull String extid) throws FollowDeleteFailureException, FollowRetrievalFailureException {
        Follow record = repository.findByExtid(extid);

        // error if the record isn't there
        checkNullRecord(record, getFoundFailureMessage(extid));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        Follow saved = repository.save(record);

        // error delete failed
        checkDeletedFailure(saved, getDeletedFailureMessage(extid));

        //success
        log.info(getDeletedMessage(extid));
        return true;
    }

    /**
     * Find Follow
     *
     * @param extid - to find
     * @return boolean
     */
    public FollowDb findByExtid(@NonNull String extid) throws FollowRetrievalFailureException {
        Follow record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        log.info(getFoundMessage(extid));
        return FollowMapper.toDb(record);
    }

    // ////////////////////////////////////////////////////////
    // CHECK METHODS
    // ////////////////////////////////////////////////////////

    /**
     * Checks if the retrieval of Follow failed elses throw an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws FollowRetrievalFailureException
     */
    private void checkNullRecord(Follow record, String message) throws FollowRetrievalFailureException {
        if (record == null) {
            throw new FollowRetrievalFailureException(message);
        }
    }

    /**
     * Checks if Follow was created else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws FollowUpdateFailureException
     */
    private void checkUpdatedFailure(Follow record, String message) throws FollowUpdateFailureException {
        if (record == null) {
            throw new FollowUpdateFailureException(message);
        }
    }

    /**
     * Checks if Follow was deleted else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws FollowDeleteFailureException
     */
    private void checkDeletedFailure(Follow record, String message) throws FollowDeleteFailureException {
        if (record == null) {
            throw new FollowDeleteFailureException(message);
        }
    }

    /**
     * Checks if Follow already exists
     *
     * @param extid   - if exists throw exception
     * @param message
     * @throws FollowCreateFailureException
     */
    private void checkCreatedAlready(String extid, String message) throws FollowCreateFailureException {
        Follow record = repository.findByExtid(extid);
        if (record != null) {
            throw new FollowCreateFailureException(message);
        }
    }


}
