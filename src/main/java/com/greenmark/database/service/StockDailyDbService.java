package com.greenmark.database.service;

import com.greenmark.common.database.domain.StockDailyDb;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.StockDaily;
import com.greenmark.database.db.mapper.StockDailyMapper;
import com.greenmark.database.db.repository.StockDailyRepository;
import com.greenmark.database.exceptions.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class StockDailyDbService extends BasicDbService {

    private final StockDailyRepository repository;

    public StockDailyDbService(StockDailyRepository repository) {
        super("StockDaily");
        this.repository = repository;
    }

    /**
     * Create a record with name and description
     *
     * @param symbol - value for symbol
     * @return
     * @throws DataIntegrityViolationException
     */
    public StockDailyDb create(@NonNull String symbol) throws DatabaseCreateFailureException, DatabaseAccessException {

        //look for already created
        checkCreatedAlready(symbol, getCreatedAlreadyMessage(symbol));

        try {
            StockDaily record = new StockDaily();
            record.setSymbol(symbol);
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);
            System.out.println(record);

            StockDaily saved = repository.save(record);
            log.debug(getCreatedMessage(symbol));
            return StockDailyMapper.toDb(saved);
        } catch (Exception e) {
            switch (e.getClass().getSimpleName()) {
                case "DataIntegrityViolationException":
                    log.info(getCreatedFailureMessage(symbol));
                    throw new DatabaseCreateFailureException("DataIntegrityViolationException" + e.getMessage());
                case "ConstraintViolationException ":
                    log.info(getCreatedFailureMessage(symbol));
                    throw new DatabaseCreateFailureException("ConstraintViolationException" + e.getMessage());
                default:
                    log.info(getDbAccessMessage(symbol));
                    throw new DatabaseAccessException("DatabaseAccessException" + e.getMessage());
            }
        }
    }

    /**
     * Update the StockDaily name and description
     *
     * @param symbol - the symbol to use
     *
     * @return
     */
    public StockDailyDb update(@NonNull String symbol) throws DatabaseRetrievalFailureException, DatabaseUpdateFailureException {
        StockDaily record = repository.findBySymbol(symbol);
        checkNullRecord(record, getFoundFailureMessage(symbol));

        record.setSymbol(symbol);
        record.setModifiedAt(LocalDateTime.now());
        StockDaily saved = repository.save(record);

        if (saved == null) {
            throw new DatabaseUpdateFailureException("StockDaily with extid " + symbol + " not saved");
        }
        checkUpdatedFailure(saved, getUpdatedFailureMessage(symbol));

        log.info(getUpdatedMessage(symbol));
        return StockDailyMapper.toDb(saved);
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
        StockDaily record = repository.findBySymbol(extid);

        // error if the record isn't there
        checkNullRecord(record, getFoundFailureMessage(extid));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        StockDaily saved = repository.save(record);

        // error delete failed
        checkDeletedFailure(saved, getDeletedFailureMessage(extid));

        //success
        log.info(getDeletedMessage(extid));
        return true;
    }

    /**
     * Find StockDaily
     *
     * @param extid - to find
     * @return boolean
     */
    public StockDailyDb findByExtid(@NonNull String extid) throws DatabaseRetrievalFailureException {
        StockDaily record = repository.findBySymbol(extid);
        checkNullRecord(record, getFoundFailureMessage(extid));

        log.info(getFoundMessage(extid));
        return StockDailyMapper.toDb(record);
    }

    // ////////////////////////////////////////////////////////
    // CHECK METHODS
    // ////////////////////////////////////////////////////////

    /**
     * Checks if the retrieval of StockDaily failed elses throw an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws DatabaseRetrievalFailureException
     */
    private void checkNullRecord(StockDaily record, String message) throws DatabaseRetrievalFailureException {
        if (record == null) {
            throw new DatabaseRetrievalFailureException(message);
        }
    }

    /**
     * Checks if StockDaily was created else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws DatabaseUpdateFailureException
     */
    private void checkUpdatedFailure(StockDaily record, String message) throws DatabaseUpdateFailureException {
        if (record == null) {
            throw new DatabaseUpdateFailureException(message);
        }
    }

    /**
     * Checks if StockDaily was deleted else throws an exception
     *
     * @param record  - if null, throw an exception
     * @param message
     * @throws StockDeleteFailureException
     */
    private void checkDeletedFailure(StockDaily record, String message) throws DatabaseDeleteFailureException {
        if (record == null) {
            throw new DatabaseDeleteFailureException(message);
        }
    }

    /**
     * Checks if StockDaily already exists
     *
     * @param extid   - if exists throw exception
     * @param message
     * @throws DatabaseCreateFailureException
     */
    private void checkCreatedAlready(String symbol, String message) throws DatabaseCreateFailureException {
        StockDaily record = repository.findBySymbol(symbol);
        if (record != null) {
            throw new DatabaseCreateFailureException(message);
        }
    }
}
