package com.greenmark.database.service;

import com.greenmark.common.database.domain.MarketData;
import com.greenmark.common.database.domain.StockWatch;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.common.enums.TimeframeType;
import com.greenmark.database.db.entity.StockWatchDb;
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
        super("StockWatchDb");
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
    public StockWatch create(@NonNull String symbol, @NonNull TimeframeType timeframeType, @NonNull MarketData marketData) throws DatabaseCreateFailureException, DatabaseAccessException {

        try {
            StockWatchDb record = new StockWatchDb();
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

            StockWatchDb saved = repository.save(record);
            return mapper.toEntity(saved);
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
    public StockWatch update(@NonNull String symbol, @NonNull MarketData marketData) throws DatabaseRetrievalFailureException,
            DatabaseUpdateFailureException, DatabaseAccessException {

        StockWatchDb record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

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

            StockWatchDb saved = repository.save(record);
            log.info(getUpdatedMessage(symbol));
            return mapper.toEntity(saved);
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
        StockWatchDb record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));

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
    public StockWatch findBySymbol(@NonNull String symbol) throws DatabaseRetrievalFailureException {
        StockWatchDb record = repository.findBySymbol(symbol).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(symbol)));
        log.info(getFoundMessage(symbol));
        return mapper.toEntity(record);
    }

    /**
     *
     * @return
     */
    public List<StockWatch> findAll() {
        List<StockWatchDb> records = repository.findByActive(ActiveEnum.ACTIVE.value);
        log.info(getFoundActiveMessage(records.size()));
        return mapper.toList(records);
    }

    /**
     *
     * @return
     */
    public List<StockWatch> findByTimeframe(TimeframeType timeframe) {
        List<StockWatchDb> records = repository.findByTimeframeAndActive(timeframe.value, ActiveEnum.ACTIVE.value);
        log.info(getFoundActiveMessage(records.size()));
        return mapper.toList(records);
    }
}
