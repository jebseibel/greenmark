package com.greenmark.database.service;

import com.greenmark.common.database.domain.StockDb;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.StockEntity;
import com.greenmark.database.db.mapper.StockMapper;
import com.greenmark.database.db.repository.StockRepository;
import com.greenmark.database.exceptions.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class StockDbService extends BasicDbService {

    private final StockRepository repository;

    public StockDbService(StockRepository repository) {
        super("Stock");
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
    public StockDb create(@NonNull String extid, @NonNull String name, @NonNull String description) throws StockCreateFailureException, DatabaseAccessException {

        //look for already created
        checkCreatedAlready(extid, getCreatedAlreadyMessage(extid));

        try {
            StockEntity record = new StockEntity();
            record.setExtid(extid);
            record.setName(name);
            record.setDescription(description);
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);
            System.out.println(record);

            StockEntity saved = repository.save(record);
            log.debug(getCreatedMessage(extid));
            return StockMapper.toDb(saved);
        } catch (Exception e) {
            switch (e.getClass().getSimpleName()) {
                case "DataIntegrityViolationException":
                    log.info(getCreatedFailureMessage(extid));
                    throw new StockCreateFailureException("DataIntegrityViolationException" + e.getMessage());
                case "ConstraintViolationException ":
                    log.info(getCreatedFailureMessage(extid));
                    throw new StockCreateFailureException("ConstraintViolationException" + e.getMessage());
                default:
                    log.info(getDbAccessMessage(extid));
                    throw new DatabaseAccessException("DatabaseAccessException" + e.getMessage());
            }
        }
    }

    /**
     * Update the Stock name and description
     *
     * @param extid       - the extid to use
     * @param name        - value for name
     * @param description - value for description
     * @return
     */
    public StockDb update(@NonNull String extid, String name, String description) throws StockRetrievalFailureException, StockUpdateFailureException {
        StockEntity record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        record.setName(name);
        record.setDescription(description);
        record.setModifiedAt(LocalDateTime.now());
        StockEntity saved = repository.save(record);

        if (saved == null) {
            throw new StockUpdateFailureException("Stock with extid " + extid + " not saved");
        }
        checkUpdatedFailure(saved, getUpdatedFailureMessage(extid));

        log.info(getUpdatedMessage(extid));
        return StockMapper.toDb(saved);
    }

    /**
     * Delete by Extid
     *
     * @param extid - to delete
     * @return boolean
     * @throws StockDeleteFailureException
     * @throws StockRetrievalFailureException
     */
    public boolean delete(@NonNull String extid) throws StockDeleteFailureException, StockRetrievalFailureException {
        StockEntity record = repository.findByExtid(extid);

        // error if the record isn't there
        checkNullRecord(record, getFoundFailureMessage(extid));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        StockEntity saved = repository.save(record);

        // error delete failed
        checkDeletedFailure(saved, getDeletedFailureMessage(extid));

        //success
        log.info(getDeletedMessage(extid));
        return true;
    }

    /**
     * Find Stock
     *
     * @param extid - to find
     * @return boolean
     */
    public StockDb findByExtid(@NonNull String extid) throws StockRetrievalFailureException {
        StockEntity record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        log.info(getFoundMessage(extid));
        return StockMapper.toDb(record);
    }

    public void loadOnStartup(@NonNull String extid, String name, String description) throws StockRetrievalFailureException, StockUpdateFailureException {
        StockEntity record = repository.findByExtid(extid);
        if (record == null) {
            log.info("Already exists stock ["+name+"]");
            return;
        }

        try {
            record = new StockEntity();
            record.setExtid(extid);
            record.setName(name);
            record.setDescription(description);
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);
            System.out.println(record);

            repository.save(record);
            log.info("Success creating stock ["+name+"]");
            return;
        } catch (Exception e) {
            log.info("Failure creating stock ["+name+"]");
            return;
        }

    }

    // ////////////////////////////////////////////////////////
    // CHECK METHODS
    // ////////////////////////////////////////////////////////

    /**
     * Checks if the retrieval of Stock failed elses throw an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws StockRetrievalFailureException
     */
    private void checkNullRecord(StockEntity record, String message) throws StockRetrievalFailureException {
        if (record == null) {
            throw new StockRetrievalFailureException(message);
        }
    }

    /**
     * Checks if Stock was created else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws StockUpdateFailureException
     */
    private void checkUpdatedFailure(StockEntity record, String message) throws StockUpdateFailureException {
        if (record == null) {
            throw new StockUpdateFailureException(message);
        }
    }

    /**
     * Checks if Stock was deleted else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws StockDeleteFailureException
     */
    private void checkDeletedFailure(StockEntity record, String message) throws StockDeleteFailureException {
        if (record == null) {
            throw new StockDeleteFailureException(message);
        }
    }

    /**
     * Checks if Stock already exists
     *
     * @param extid   - if exists throw exception
     * @param message
     * @throws StockCreateFailureException
     */
    private void checkCreatedAlready(String extid, String message) throws StockCreateFailureException {
        StockEntity record = repository.findByExtid(extid);
        if (record != null) {
            throw new StockCreateFailureException(message);
        }
    }


}
