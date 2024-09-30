package com.greenmark.database.service;

import com.greenmark.common.database.domain.Position;
import com.greenmark.common.enums.ActiveEnum;
import com.greenmark.database.db.entity.PositionDb;
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
        super("PositionDb");
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
    public Position create(@NonNull String name, @NonNull Position position) throws DatabaseCreateFailureException, DatabaseAccessException {

        try {
            PositionDb record = new PositionDb();
            record.setExtid(UUID.randomUUID().toString());
            record.setSymbol(position.getSymbol());
            record.setName(position.getName());
            record.setShares(position.getShares());
            record.setPrice(position.getPrice());
            record.setTotal(position.getTotal());
            record.setCreatedAt(LocalDateTime.now());
            record.setActive(ActiveEnum.ACTIVE.value);
            System.out.println(record);

            PositionDb saved = repository.save(record);
            log.debug(getCreatedMessage(name));
            return mapper.toEntity(saved);
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
     * Update the PositionDb name and description
     *
//     * @param symbol - the symbol to use
//     * @param name   - value for name
     * @return
     */
    public Position update(@NonNull String extid, @NonNull String name, int shares, @NonNull BigDecimal price, @NonNull BigDecimal total) throws DatabaseRetrievalFailureException, DatabaseUpdateFailureException {
        PositionDb record = repository.findByExtid(extid).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(extid)));
        record.setName(name);
        record.setShares(shares);
        record.setPrice(price);
        record.setTotal(total);
        record.setModifiedAt(LocalDateTime.now());

        PositionDb saved = repository.save(record);
        log.debug(getUpdatedMessage(extid));
        return mapper.toEntity(saved);
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
        PositionDb record = repository.findByExtid(extid).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(extid)));

        //update record to show it is deleted
        record.setDeletedAt(LocalDateTime.now());
        record.setActive(ActiveEnum.INACTIVE.value);
        PositionDb saved = repository.save(record);

        //success
        log.debug(getDeletedMessage(extid));
        return true;
    }

    /**
     * Find PositionDb
     *
     * @param extid - to find
     * @return boolean
     */
    public Position findByExtid(@NonNull String extid) throws DatabaseRetrievalFailureException {
        PositionDb record = repository.findByExtid(extid).orElseThrow(() -> new DatabaseRetrievalFailureException(getFoundFailureMessage(extid)));

        log.debug(getFoundMessage(extid));
        return mapper.toEntity(record);
    }

    public List<Position> findAll() {
        List<PositionDb> records = repository.findAll();
        return mapper.toList(records);
    }

    /**
     * @return
     * @throws DatabaseRetrievalFailureException
     */
    public List<Position> findActive() throws DatabaseRetrievalFailureException {
        List<PositionDb> records = repository.findByActive(ActiveEnum.ACTIVE.value);

        log.debug(getFoundActiveMessage(records.size()));
        return mapper.toList(records);
    }

    /**
     * @return
     * @throws DatabaseRetrievalFailureException
     */
    public List<Position> findInactive() throws DatabaseRetrievalFailureException {
        List<PositionDb> records = repository.findByActive(ActiveEnum.INACTIVE.value);

        log.debug(getFoundActiveMessage(records.size()));
        return mapper.toList(records);
    }
}
