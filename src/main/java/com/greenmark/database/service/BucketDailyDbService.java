package com.greenmark.database.service;

import com.greenmark.common.database.domain.BucketDailyDb;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.common.database.domain.StockData;
import com.greenmark.database.db.entity.BucketDaily;
import com.greenmark.database.db.mapper.BucketDailyMapper;
import com.greenmark.database.db.repository.BucketDailyRepository;
import com.greenmark.database.exceptions.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class BucketDailyDbService extends BasicDbService {

    private final BucketDailyRepository repository;

    public BucketDailyDbService(BucketDailyRepository repository) {
        super("BucketDaily");
        this.repository = repository;
    }

    /**
     *
     * @param symbol
     * @param stockData
     * @return
     * @throws DatabaseCreateFailureException
     * @throws DatabaseAccessException
     */
    public BucketDailyDb create(@NonNull String symbol, @NonNull StockData stockData) throws DatabaseCreateFailureException, DatabaseAccessException {

        repository.findBySymbol(symbol).ifPresent(s -> new DatabaseCreateFailureException(getFoundFailureMessage(symbol)));

        try {
            BucketDaily record = new BucketDaily();
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

            BucketDaily saved = repository.save(record);
            return BucketDailyMapper.toDb(saved);
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
     *
     * @param symbol
     * @param stockData
     * @return
     * @throws DatabaseRetrievalFailureException
     * @throws DatabaseUpdateFailureException
     * @throws DatabaseAccessException
     */
    public BucketDailyDb update(@NonNull String symbol, @NonNull StockData stockData) throws DatabaseRetrievalFailureException,
            DatabaseUpdateFailureException, DatabaseAccessException {

        BucketDaily record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

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

            BucketDaily saved = repository.save(record);
            log.info(getUpdatedMessage(symbol));
            return BucketDailyMapper.toDb(saved);
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
        BucketDaily record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        BucketDaily saved = repository.save(record);

        //success
        log.info(getDeletedMessage(symbol));
        return true;
    }

    /**
     * Find BucketDaily
     *
     * @param symbol - to find
     * @return boolean
     */
    public BucketDailyDb findBySymbol(@NonNull String symbol) throws DatabaseRetrievalFailureException {
        BucketDaily record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

        log.info(getFoundMessage(symbol));
        return BucketDailyMapper.toDb(record);
    }

    private BucketDaily _addStockData(BucketDaily record, StockData stockData) {
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
