package com.greenmark.database.service;

import com.greenmark.common.database.domain.BucketMinute01Db;
import com.greenmark.common.database.domain.StockData;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.BucketMinute01;
import com.greenmark.database.db.mapper.BucketMinute01Mapper;
import com.greenmark.database.db.repository.BucketMinute01Repository;
import com.greenmark.database.exceptions.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class BucketMinute01DbService extends BaseDbService {

    private final BucketMinute01Repository repository;
    private final BucketMinute01Mapper mapper;

    public BucketMinute01DbService(BucketMinute01Repository repository, BucketMinute01Mapper mapper) {
        super("BucketMinute01");
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * @param symbol
     * @param stockData
     * @return
     * @throws DatabaseCreateFailureException
     * @throws DatabaseAccessException
     */
    public BucketMinute01Db create(@NonNull String symbol, @NonNull StockData stockData) throws DatabaseCreateFailureException, DatabaseAccessException {

        try {
            BucketMinute01 record = new BucketMinute01();
            record.setSymbol(symbol);
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);

            //stock data
            record.setCurrent(stockData.getCurrent());
            record.setLow(stockData.getLow());
            record.setHigh(stockData.getHigh());
            record.setOpen(stockData.getOpen());
            record.setPreviousClose(stockData.getPreviousClose());
            record.setChanged(stockData.getChanged());
            record.setChangedPercent(stockData.getChangedPercent());

            BucketMinute01 saved = repository.save(record);
            return mapper.toDb(saved);
        } catch (Exception e) {
            switch (e.getClass().getSimpleName()) {
                case "DataIntegrityViolationException", "ConstraintViolationException":
                    log.info(getCreatedFailureMessage(symbol));
                    throw new DatabaseCreateFailureException(getCreatedFailureMessage(symbol));
                default:
                    log.info(getDbAccessMessage(symbol));
                    throw new DatabaseAccessException(getDbAccessMessage(symbol));
            }
        }
    }

    /**
     * @param symbol
     * @param stockData
     * @return
     * @throws DatabaseRetrievalFailureException
     * @throws DatabaseUpdateFailureException
     * @throws DatabaseAccessException
     */
    public BucketMinute01Db update(@NonNull String symbol, @NonNull StockData stockData) throws DatabaseRetrievalFailureException,
            DatabaseUpdateFailureException, DatabaseAccessException {

        BucketMinute01 record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

        try {
            record.setModifiedAt(LocalDateTime.now());

            //stock data
            record.setCurrent(stockData.getCurrent());
            record.setLow(stockData.getLow());
            record.setHigh(stockData.getHigh());
            record.setOpen(stockData.getOpen());
            record.setPreviousClose(stockData.getPreviousClose());
            record.setChanged(stockData.getChanged());
            record.setChangedPercent(stockData.getChangedPercent());

            BucketMinute01 saved = repository.save(record);
            log.info(getUpdatedMessage(symbol));
            return mapper.toDb(saved);
        } catch (Exception e) {
            switch (e.getClass().getSimpleName()) {
                case "DataIntegrityViolationException", "ConstraintViolationException":
                    log.info(getUpdatedMessage(symbol));
                    throw new DatabaseUpdateFailureException(getUpdatedMessage(symbol));
                default:
                    log.info(getDbAccessMessage(symbol));
                    throw new DatabaseAccessException(getDbAccessMessage(symbol));
            }
        }
    }

    /**
     * Delete by Symbol
     *
     * @param symbol - stock symbol
     * @return boolean
     * @throws DatabaseDeleteFailureException
     * @throws DatabaseRetrievalFailureException
     */
    public boolean delete(@NonNull String symbol) throws DatabaseDeleteFailureException, DatabaseRetrievalFailureException {

        // error if the record isn't there
        BucketMinute01 record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        repository.save(record);

        //success
        log.info(getDeletedMessage(symbol));
        return true;
    }

    /**
     * Find BucketMinute01
     *
     * @param symbol - to find
     * @return boolean
     */
    public BucketMinute01Db findBySymbol(@NonNull String symbol) throws DatabaseRetrievalFailureException {
        BucketMinute01 record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

        log.info(getFoundMessage(symbol));
        return mapper.toDb(record);
    }

    public List<BucketMinute01Db> findAll() throws DatabaseRetrievalFailureException {
        List<BucketMinute01> records = repository.findByActive(ActiveEnum.ACTIVE.value);

        log.info(getFoundActiveMessage(records.size()));
        return mapper.toList(records);
    }
}
