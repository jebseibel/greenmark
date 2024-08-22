package com.greenmark.database.service;

import com.greenmark.common.database.domain.StockWatchDb;
import com.greenmark.common.database.domain.MarketData;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.common.enums.TimeframeType;
import com.greenmark.database.db.entity.StockWatch;
import com.greenmark.database.db.mapper.StockWatchMapper;
import com.greenmark.database.db.repository.StockWatchRepository;
import com.greenmark.database.exceptions.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StockWatchDbService extends BaseDbService {

    private final StockWatchRepository repository;
    private final StockWatchMapper mapper;

    public StockWatchDbService(StockWatchRepository repository, StockWatchMapper mapper) {
        super("StockWatch");
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * @param symbol
     * @param marketData
     * @return
     * @throws DatabaseCreateFailureException
     * @throws DatabaseAccessException
     */
    public StockWatchDb create(@NonNull String symbol, @NonNull TimeframeType timeframeType, @NonNull MarketData marketData) throws DatabaseCreateFailureException, DatabaseAccessException {

        try {
            StockWatch record = new StockWatch();
            record.setSymbol(symbol);
            record.setTimeframe(timeframeType.value);
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);

            //stock data
            record.setCurrent(marketData.getCurrent());
            record.setLow(marketData.getLow());
            record.setHigh(marketData.getHigh());
            record.setOpen(marketData.getOpen());
            record.setPreviousClose(marketData.getPreviousClose());
            record.setChanged(marketData.getChanged());
            record.setChangedPercent(marketData.getChangedPercent());

            StockWatch saved = repository.save(record);
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
     * @param marketData
     * @return
     * @throws DatabaseRetrievalFailureException
     * @throws DatabaseUpdateFailureException
     * @throws DatabaseAccessException
     */
    public StockWatchDb update(@NonNull String symbol, @NonNull MarketData marketData) throws DatabaseRetrievalFailureException,
            DatabaseUpdateFailureException, DatabaseAccessException {

        StockWatch record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

        try {
            record.setModifiedAt(LocalDateTime.now());

            //stock data
            record.setCurrent(marketData.getCurrent());
            record.setLow(marketData.getLow());
            record.setHigh(marketData.getHigh());
            record.setOpen(marketData.getOpen());
            record.setPreviousClose(marketData.getPreviousClose());
            record.setChanged(marketData.getChanged());
            record.setChangedPercent(marketData.getChangedPercent());

            StockWatch saved = repository.save(record);
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
        StockWatch record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        repository.save(record);

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
    public StockWatchDb findBySymbol(@NonNull String symbol) throws DatabaseRetrievalFailureException {
        StockWatch record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));
        log.info(getFoundMessage(symbol));
        return mapper.toDb(record);
    }

    /**
     *
     * @return
     */
    public List<StockWatchDb> findAll() {
        List<StockWatch> records = repository.findByActive(ActiveEnum.ACTIVE.value);
        log.info(getFoundActiveMessage(records.size()));
        return mapper.toList(records);
    }

    /**
     *
     * @return
     */
    public List<StockWatchDb> findByTimeframe(TimeframeType timeframe) {
        List<StockWatch> records = repository.findByTimeframeAndActive(timeframe.value, ActiveEnum.ACTIVE.value);
        log.info(getFoundActiveMessage(records.size()));
        return mapper.toList(records);
    }
}
