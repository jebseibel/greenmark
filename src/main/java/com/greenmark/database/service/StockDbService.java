package com.greenmark.database.service;

import com.greenmark.common.database.domain.StockDb;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.Stock;
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
     * @param extid - the extid to use
     * @param symbol - value for symbol
     * @param name  - value for name
     * @return
     * @throws DataIntegrityViolationException
     */
    public StockDb create(@NonNull String extid, @NonNull String symbol, @NonNull String name) throws DatabaseCreateFailureException, DatabaseAccessException {

        //look for already created
        checkCreatedAlready(extid, getCreatedAlreadyMessage(extid));

        try {
            Stock record = new Stock();
            record.setExtid(extid);
            record.setSymbol(symbol);
            record.setName(name);
            record.setReady(ActiveEnum.ACTIVE.value);
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);
            System.out.println(record);

            Stock saved = repository.save(record);
            log.debug(getCreatedMessage(extid));
            return StockMapper.toDb(saved);
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
     * Update the Stock name and description
     *
     * @param extid - the extid to use
     * @param symbol - value for symbol
     * @param name  - value for name
     *
     * @return
     */
    public StockDb update(@NonNull String extid, String symbol, String name) throws DatabaseRetrievalFailureException, DatabaseUpdateFailureException {
        Stock record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        record.setSymbol(symbol);
        record.setName(name);
        record.setModifiedAt(LocalDateTime.now());
        Stock saved = repository.save(record);

        if (saved == null) {
            throw new DatabaseUpdateFailureException("Stock with extid " + extid + " not saved");
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
     * @throws DatabaseDeleteFailureException
     * @throws DatabaseRetrievalFailureException
     */
    public boolean delete(@NonNull String extid) throws DatabaseDeleteFailureException, DatabaseRetrievalFailureException {
        Stock record = repository.findByExtid(extid);

        // error if the record isn't there
        checkNullRecord(record, getFoundFailureMessage(extid));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        Stock saved = repository.save(record);

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
    public StockDb findByExtid(@NonNull String extid) throws DatabaseRetrievalFailureException {
        Stock record = repository.findByExtid(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        log.info(getFoundMessage(extid));
        return StockMapper.toDb(record);
    }

    public void loadOnStartup(@NonNull String extid, String name, String symbol) throws DatabaseRetrievalFailureException, DatabaseUpdateFailureException {
        Stock record = repository.findByExtid(extid);
        if (record == null) {
            log.info("Already exists stock ["+name+"]");
            return;
        }

        try {
            record = new Stock();
            record.setExtid(extid);
            record.setSymbol(symbol);
            record.setName(name);
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
     * @throws DatabaseRetrievalFailureException
     */
    private void checkNullRecord(Stock record, String message) throws DatabaseRetrievalFailureException {
        if (record == null) {
            throw new DatabaseRetrievalFailureException(message);
        }
    }

    /**
     * Checks if Stock was created else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws DatabaseUpdateFailureException
     */
    private void checkUpdatedFailure(Stock record, String message) throws DatabaseUpdateFailureException {
        if (record == null) {
            throw new DatabaseUpdateFailureException(message);
        }
    }

    /**
     * Checks if Stock was deleted else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws DatabaseDeleteFailureException
     */
    private void checkDeletedFailure(Stock record, String message) throws DatabaseDeleteFailureException {
        if (record == null) {
            throw new DatabaseDeleteFailureException(message);
        }
    }

    /**
     * Checks if Stock already exists
     *
     * @param extid   - if exists throw exception
     * @param message
     * @throws DatabaseCreateFailureException
     */
    private void checkCreatedAlready(String extid, String message) throws DatabaseCreateFailureException {
        Stock record = repository.findByExtid(extid);
        if (record != null) {
            throw new DatabaseCreateFailureException(message);
        }
    }


}
