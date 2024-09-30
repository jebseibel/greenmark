package com.greenmark.database.service;

import com.greenmark.common.database.domain.PositionDb;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.Position;
import com.greenmark.database.db.mapper.PositionMapper;
import com.greenmark.database.db.repository.PositionRepository;
import com.greenmark.database.exceptions.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class PositionDbService extends BaseDbService {

    private final PositionRepository repository;
    private final PositionMapper mapper;

    public PositionDbService(PositionRepository repository, PositionMapper mapper) {
        super("Position");
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Create a record with name and description
     *
//     * @param symbol - value for symbol
     * @return
     * @throws DataIntegrityViolationException
     */
    public PositionDb create(@NonNull String name, @NonNull PositionDb positionDb) throws DatabaseCreateFailureException, DatabaseAccessException {

        try {
            Position record = new Position();
            record.setExtid(UUID.randomUUID().toString());
            record.setSymbol(positionDb.getSymbol());
            record.setName(positionDb.getName());
            record.setShares(positionDb.getShares());
            record.setPrice(positionDb.getPrice());
            record.setTotal(positionDb.getTotal());
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);
            System.out.println(record);

            Position saved = repository.save(record);
            log.debug(getCreatedMessage(name));
            return mapper.toDb(saved);
        } catch (Exception e) {
            switch (e.getClass().getSimpleName()) {
                case "DataIntegrityViolationException":
                    log.info(getCreatedFailureMessage(name));
                    throw new DatabaseCreateFailureException("DataIntegrityViolationException" + e.getMessage());
                case "ConstraintViolationException ":
                    log.info(getCreatedFailureMessage(name));
                    throw new DatabaseCreateFailureException("ConstraintViolationException" + e.getMessage());
                default:
                    log.info(getDbAccessMessage(name));
                    throw new DatabaseAccessException("DatabaseAccessException" + e.getMessage());
            }
        }
    }

    /**
     * Update the Position name and description
     *
//     * @param symbol - the symbol to use
//     * @param name   - value for name
     * @return
     */
    public PositionDb update(@NonNull String extid, @NonNull String name, int shares, @NonNull BigDecimal price, @NonNull BigDecimal total) throws DatabaseRetrievalFailureException, DatabaseUpdateFailureException {
        Position record = repository.findByExtid(extid).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(extid)));
        record.setName(name);
        record.setShares(shares);
        record.setPrice(price);
        record.setTotal(total);
        record.setModifiedAt(LocalDateTime.now());

        Position saved = repository.save(record);
        log.debug(getUpdatedMessage(extid));
        return mapper.toDb(saved);
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
        Position record = repository.findByExtid(extid).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(extid)));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        Position saved = repository.save(record);

        //success
        log.debug(getDeletedMessage(extid));
        return true;
    }

    /**
     * Find Position
     *
     * @param extid - to find
     * @return boolean
     */
    public PositionDb findByExtid(@NonNull String extid) throws DatabaseRetrievalFailureException {
        Position record = repository.findByExtid(extid).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(extid)));

        log.debug(getFoundMessage(extid));
        return mapper.toDb(record);
    }

    public List<PositionDb> findAll() {
        List<Position> records = repository.findAll();
        return mapper.toList(records);
    }

    /**
     * @return
     * @throws DatabaseRetrievalFailureException
     */
    public List<PositionDb> findActive() throws DatabaseRetrievalFailureException {
        List<Position> records = repository.findByActive(ActiveEnum.ACTIVE.value);

        log.debug(getFoundActiveMessage(records.size()));
        return mapper.toList(records);
    }

    /**
     * @return
     * @throws DatabaseRetrievalFailureException
     */
    public List<PositionDb> findInactive() throws DatabaseRetrievalFailureException {
        List<Position> records = repository.findByActive(ActiveEnum.INACTIVE.value);

        log.debug(getFoundActiveMessage(records.size()));
        return mapper.toList(records);
    }
}
