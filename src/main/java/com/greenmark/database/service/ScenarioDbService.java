package com.greenmark.database.service;

import com.greenmark.common.database.domain.ScenarioDb;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.Scenario;
import com.greenmark.database.db.mapper.BucketMinute01Mapper;
import com.greenmark.database.db.mapper.ScenarioMapper;
import com.greenmark.database.db.repository.ScenarioRepository;
import com.greenmark.database.exceptions.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ScenarioDbService extends BasicDbService {

    private final ScenarioRepository repository;
    private final ScenarioMapper mapper;

    public ScenarioDbService(ScenarioRepository repository, ScenarioMapper mapper) {
        super("Scenario");
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
    public ScenarioDb create(@NonNull String extid, @NonNull String name, @NonNull String description) throws DatabaseCreateFailureException, DatabaseAccessException {

        try {
            Scenario record = new Scenario();
            record.setExtid(extid);
            record.setName(name);
            record.setDescription(description);
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);
            System.out.println(record);

            Scenario saved = repository.save(record);
            log.debug(getCreatedMessage(extid));
            return mapper.toDb(saved);
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
     * Update the Scenario name and description
     *
     * @param extid       - the extid to use
     * @param name        - value for name
     * @param description - value for description
     * @return
     */
    public ScenarioDb update(@NonNull String extid, String name, String description) throws DatabaseRetrievalFailureException, DatabaseUpdateFailureException {
        Scenario record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        record.setName(name);
        record.setDescription(description);
        record.setModifiedAt(LocalDateTime.now());
        Scenario saved = repository.save(record);

        if (saved == null) {
            throw new DatabaseUpdateFailureException("Scenario with extid " + extid + " not saved");
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
        Scenario record = repository.findByExtid(extid);

        // error if the record isn't there
        checkNullRecord(record, getFoundFailureMessage(extid));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        Scenario saved = repository.save(record);

        // error delete failed
        checkDeletedFailure(saved, getDeletedFailureMessage(extid));

        //success
        log.info(getDeletedMessage(extid));
        return true;
    }

    /**
     * Find Scenario
     *
     * @param extid - to find
     * @return boolean
     */
    public ScenarioDb findByExtid(@NonNull String extid) throws DatabaseRetrievalFailureException {
        Scenario record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        log.info(getFoundMessage(extid));
        return mapper.toDb(record);
    }

    // ////////////////////////////////////////////////////////
    // CHECK METHODS
    // ////////////////////////////////////////////////////////

    /**
     * Checks if the retrieval of Scenario failed elses throw an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws DatabaseRetrievalFailureException
     */
    private void checkNullRecord(Scenario record, String message) throws DatabaseRetrievalFailureException {
        if (record == null) {
            throw new DatabaseRetrievalFailureException(message);
        }
    }

    /**
     * Checks if Scenario was created else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws DatabaseUpdateFailureException
     */
    private void checkUpdatedFailure(Scenario record, String message) throws DatabaseUpdateFailureException {
        if (record == null) {
            throw new DatabaseUpdateFailureException(message);
        }
    }

    /**
     * Checks if Scenario was deleted else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws DatabaseDeleteFailureException
     */
    private void checkDeletedFailure(Scenario record, String message) throws DatabaseDeleteFailureException {
        if (record == null) {
            throw new DatabaseDeleteFailureException(message);
        }
    }

    /**
     * Checks if Scenario already exists
     *
     * @param extid   - if exists throw exception
     * @param message
     * @throws DatabaseCreateFailureException
     */
    private void checkCreatedAlready(String extid, String message) throws DatabaseCreateFailureException {
        Scenario record = repository.findByExtid(extid);
        if (record != null) {
            throw new DatabaseCreateFailureException(message);
        }
    }


}
