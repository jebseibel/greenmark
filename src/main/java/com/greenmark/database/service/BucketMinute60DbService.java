package com.greenmark.database.service;

import com.greenmark.common.database.domain.BucketMinute60Db;
import com.greenmark.common.database.domain.StockData;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.BucketMinute60;
import com.greenmark.database.db.mapper.BucketMinute60Mapper;
import com.greenmark.database.db.repository.BucketMinute60Repository;
import com.greenmark.database.exceptions.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class BucketMinute60DbService extends BaseDbService {

    private final BucketMinute60Repository repository;
    private final BucketMinute60Mapper mapper;

    public BucketMinute60DbService(BucketMinute60Repository repository, BucketMinute60Mapper mapper) {
        super("BucketMinute60");
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
    public BucketMinute60Db create(@NonNull String symbol, @NonNull StockData stockData) throws DatabaseCreateFailureException, DatabaseAccessException {

        try {
            BucketMinute60 record = new BucketMinute60();
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

            BucketMinute60 saved = repository.save(record);
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
    public BucketMinute60Db update(@NonNull String symbol, @NonNull StockData stockData) throws DatabaseRetrievalFailureException,
            DatabaseUpdateFailureException, DatabaseAccessException {

        BucketMinute60 record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

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

            BucketMinute60 saved = repository.save(record);
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
        BucketMinute60 record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        BucketMinute60 saved = repository.save(record);

        //success
        log.info(getDeletedMessage(symbol));
        return true;
    }

    /**
     * Find BucketMinute60
     *
     * @param symbol - to find
     * @return boolean
     */
    public BucketMinute60Db findBySymbol(@NonNull String symbol) throws DatabaseRetrievalFailureException {
        BucketMinute60 record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

        log.info(getFoundMessage(symbol));
        return mapper.toDb(record);
    }

    /**
     * Find all active records
     * @return
     */
    public List<BucketMinute60Db> findAll()  {
        List<BucketMinute60> records = repository.findByActive(ActiveEnum.ACTIVE.value);

        log.info(getFoundActiveMessage(records.size()));
        return mapper.toList(records);
    }

    private BucketMinute60 _addStockData(BucketMinute60 record, StockData stockData) {
        record.setCurrent(stockData.getCurrent());
        record.setOpen(stockData.getOpen());
        record.setLow(stockData.getLow());
        record.setHigh(stockData.getHigh());
        record.setPreviousClose(stockData.getPreviousClose());
        record.setChanged(stockData.getChanged());
        record.setChangedPercent(stockData.getChangedPercent());
        return record;
    }

}
