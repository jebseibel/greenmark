package com.greenmark.database.service;

import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.common.database.domain.ScenarioDb;
import com.greenmark.database.db.entity.Scenario;
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

    private ScenarioRepository repository;

    public ScenarioDbService(ScenarioRepository repository) {
        super("Scenario");
        this.repository = repository;
    }

    /**
     * Create a record with name and description
     *
     * @param extid - the extid to use
     * @param name - value for name
     * @param description - value for description
     * @return
     * @throws DataIntegrityViolationException
     */
    public ScenarioDb create(@NonNull String extid, @NonNull String name, @NonNull String description) throws ScenarioCreateFailureException, DatabaseAccessException {

        //look for already created
        checkCreatedAlready(extid, getCreatedAlreadyMessage(extid));

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
            return ScenarioMapper.toDb(saved);
        } catch (Exception e) {
            switch (e.getClass().getSimpleName()) {
                case "DataIntegrityViolationException":
                    log.info(getCreatedFailureMessage(extid));
                    throw new ScenarioCreateFailureException("DataIntegrityViolationException" + e.getMessage());
                case "ConstraintViolationException ":
                    log.info(getCreatedFailureMessage(extid));
                    throw new ScenarioCreateFailureException("ConstraintViolationException" + e.getMessage());
                default:
                    log.info(getDbAccessMessage(extid));
                    throw new DatabaseAccessException("DatabaseAccessException" + e.getMessage());
            }
        }
    }

    /**
     * Update the Scenario name and description
     *
     * @param extid - the extid to use
     * @param name - value for name
     * @param description - value for description
     * @return
     */
    public ScenarioDb update(@NonNull String extid, String name, String description) throws ScenarioRetrievalFailureException, ScenarioUpdateFailureException {
        Scenario record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        record.setName(name);
        record.setDescription(description);
        record.setModifiedAt(LocalDateTime.now());
        Scenario saved = repository.save(record);

        if (saved == null) {
            throw new ScenarioUpdateFailureException("Scenario with extid " + extid + " not saved");
        }
        checkUpdatedFailure(saved, getUpdatedFailureMessage(extid));

        log.info(getUpdatedMessage(extid));
        return ScenarioMapper.toDb(saved);
    }

    /**
     * Delete by Extid
     * @param extid - to delete
     * @return boolean
     * @throws ScenarioDeleteFailureException
     * @throws ScenarioRetrievalFailureException
     */
    public boolean delete(@NonNull String extid) throws ScenarioDeleteFailureException, ScenarioRetrievalFailureException {
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
    public ScenarioDb findByExtid(@NonNull String extid) throws ScenarioRetrievalFailureException {
        Scenario record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        log.info(getFoundMessage(extid));
        return ScenarioMapper.toDb(record);
    }

    // ////////////////////////////////////////////////////////
    // CHECK METHODS
    // ////////////////////////////////////////////////////////
    /**
     * Checks if the retrieval of Scenario failed elses throw an exception
     * @param record - if null, throw an exception
     * @param message
     * @throws ScenarioRetrievalFailureException
     */
    private void checkNullRecord(Scenario record, String message) throws ScenarioRetrievalFailureException {
        if (record == null) {
            throw new ScenarioRetrievalFailureException(message);
        }
    }

    /**
     * Checks if Scenario was created else throws an exception
     * @param record - if null, throw an exception
     * @param message
     * @throws ScenarioUpdateFailureException
     */
    private void checkUpdatedFailure(Scenario record, String message) throws ScenarioUpdateFailureException {
        if (record == null) {
            throw new ScenarioUpdateFailureException(message);
        }
    }

    /**
     * Checks if Scenario was deleted else throws an exception
     * @param record - if null, throw an exception
     * @param message
     * @throws ScenarioDeleteFailureException
     */
    private void checkDeletedFailure(Scenario record, String message) throws ScenarioDeleteFailureException {
        if (record == null) {
            throw new ScenarioDeleteFailureException(message);
        }
    }

    /**
     * Checks if Scenario already exists
     * @param extid - if exists throw exception
     * @param message
     * @throws ScenarioCreateFailureException
     */
    private void checkCreatedAlready(String extid, String message) throws ScenarioCreateFailureException {
        Scenario record = repository.findByExtid(extid);
        if (record != null) {
            throw new ScenarioCreateFailureException(message);
        }
    }



}
