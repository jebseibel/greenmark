package com.greenmark.database.service;

import com.greenmark.common.database.domain.StockDb;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.Stock;
import com.greenmark.database.db.mapper.BucketMinute01Mapper;
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
public class StockDbService extends BasicDbService {

    private final StockRepository repository;
    private final StockMapper mapper;

    public StockDbService(StockRepository repository, StockMapper mapper) {
        super("Stock");
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Create a record with name and description
     *
     * @param symbol - value for symbol
     * @param name  - value for name
     * @return
     * @throws DataIntegrityViolationException
     */
    public StockDb create(@NonNull String symbol, @NonNull String name) throws DatabaseCreateFailureException, DatabaseAccessException {

        repository.findBySymbol(symbol).ifPresent(s -> new DatabaseCreateFailureException(getFoundFailureMessage(symbol)));

        try {
            Stock record = new Stock();
            record.setExtid(UUID.randomUUID().toString());
            record.setSymbol(symbol);
            record.setName(name);
            record.setReady(ActiveEnum.ACTIVE.value);
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);
            System.out.println(record);

            Stock saved = repository.save(record);
            log.debug(getCreatedMessage(symbol));
            return mapper.toDb(saved);
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
     * Update the Stock name and description
     *
     * @param symbol - the symbol to use
     * @param name  - value for name
     *
     * @return
     */
    public StockDb update(@NonNull String symbol, String name) throws DatabaseRetrievalFailureException, DatabaseUpdateFailureException {
        Stock record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

        record.setName(name);
        record.setModifiedAt(LocalDateTime.now());

        Stock saved = repository.save(record);
        log.debug(getUpdatedMessage(symbol));
        return mapper.toDb(saved);
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
        Stock record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        Stock saved = repository.save(record);

        //success
        log.debug(getDeletedMessage(symbol));
        return true;
    }

    /**
     * Find Stock
     *
     * @param symbol - to find
     * @return boolean
     */
    public StockDb findBySymbol(@NonNull String symbol) throws DatabaseRetrievalFailureException {
        Stock record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

        log.debug(getFoundMessage(symbol));
        return mapper.toDb(record);
    }

    public List<StockDb> findAll()  {
        List<Stock> records = repository.findAll();
        return mapper.toList(records);
    }

    /**
     *
     * @return
     * @throws DatabaseRetrievalFailureException
     */
    public List<StockDb> findActive() throws DatabaseRetrievalFailureException {
        List<Stock> records = repository.findByActive(ActiveEnum.ACTIVE.value);

        log.debug(getFoundActiveMessage(records.size()));
        return mapper.toList(records);
    }

    /**
     *
     * @return
     * @throws DatabaseRetrievalFailureException
     */
    public List<StockDb> findInactive() throws DatabaseRetrievalFailureException {
        List<Stock> records = repository.findByActive(ActiveEnum.INACTIVE.value);

        log.debug(getFoundActiveMessage(records.size()));
        return mapper.toList(records);
    }
}
