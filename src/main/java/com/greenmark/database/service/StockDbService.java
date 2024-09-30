package com.greenmark.database.service;

import com.greenmark.common.database.domain.Stock;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.StockDb;
import com.greenmark.database.db.mapper.StockMapper;
import com.greenmark.database.db.repository.StockRepository;
import com.greenmark.database.exceptions.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class StockDbService extends BaseDbService {

    private final StockRepository repository;
    private final StockMapper mapper;

    public StockDbService(StockRepository repository, StockMapper mapper) {
        super("StockDb");
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Create a record with name and description
     *
     * @param symbol - value for symbol
     * @param name   - value for name
     * @return
     * @throws DataIntegrityViolationException
     */
    public Stock create(@NonNull String symbol, @NonNull String name) throws DatabaseCreateFailureException, DatabaseAccessException {

        try {
            StockDb record = new StockDb();
            record.setExtid(UUID.randomUUID().toString());
            record.setSymbol(symbol);
            record.setName(name);
            record.setReady(ActiveEnum.ACTIVE.value);
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);
            System.out.println(record);

            StockDb saved = repository.save(record);
            log.debug(getCreatedMessage(symbol));
            return mapper.toEntity(saved);
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
     * Update the StockDb name and description
     *
     * @param symbol - the symbol to use
     * @param name   - value for name
     * @return
     */
    public Stock update(@NonNull String symbol, String name) throws DatabaseRetrievalFailureException, DatabaseUpdateFailureException {
        StockDb record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

        record.setName(name);
        record.setModifiedAt(LocalDateTime.now());

        StockDb saved = repository.save(record);
        log.debug(getUpdatedMessage(symbol));
        return mapper.toEntity(saved);
    }

    /**
     * Delete by Extid
     *
     * @param symbol - to delete
     * @return boolean
     * @throws DatabaseDeleteFailureException
     * @throws DatabaseRetrievalFailureException
     */
    public boolean delete(@NonNull String symbol) throws DatabaseDeleteFailureException, DatabaseRetrievalFailureException {
        StockDb record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        StockDb saved = repository.save(record);

        //success
        log.debug(getDeletedMessage(symbol));
        return true;
    }

    /**
     * Find StockDb
     *
     * @param symbol - to find
     * @return boolean
     */
    public Stock findBySymbol(@NonNull String symbol) throws DatabaseRetrievalFailureException {
        StockDb record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

        log.debug(getFoundMessage(symbol));
        return mapper.toEntity(record);
    }

    public List<Stock> findAll() {
        List<StockDb> records = repository.findAll();
        return mapper.toList(records);
    }

    /**
     * @return
     * @throws DatabaseRetrievalFailureException
     */
    public List<Stock> findActive() throws DatabaseRetrievalFailureException {
        List<StockDb> records = repository.findByActive(ActiveEnum.ACTIVE.value);

        log.debug(getFoundActiveMessage(records.size()));
        return mapper.toList(records);
    }

    /**
     * @return
     * @throws DatabaseRetrievalFailureException
     */
    public List<Stock> findInactive() throws DatabaseRetrievalFailureException {
        List<StockDb> records = repository.findByActive(ActiveEnum.INACTIVE.value);

        log.debug(getFoundActiveMessage(records.size()));
        return mapper.toList(records);
    }
}
