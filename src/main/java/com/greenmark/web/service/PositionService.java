package com.greenmark.web.service;

import com.greenmark.common.database.domain.Position;
import com.greenmark.database.db.mapper.PositionMapper;
import com.greenmark.database.exceptions.DatabaseRetrievalFailureException;
import com.greenmark.database.service.PositionDbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * This class is primarily to deal with business logic.
 */
@Slf4j
@Service
public class PositionService {

    PositionDbService positionDbService;
    PositionMapper positionMapper;

    public PositionService(PositionDbService positionDbService, PositionMapper positionMapper) {
        this.positionDbService = positionDbService;
        this.positionMapper = positionMapper;
    }

    public Position create(Position request) {
        try {
            return positionDbService.create(request.getName(), request);
        } catch (Exception e) {
            return null;
        }
    }

    public Position update(String extid, String name, int shares, BigDecimal price, BigDecimal total) {
        try {
            return positionDbService.update(extid, name, shares, price, total);
        } catch (Exception e) {
            return null;
        }
    }

    public Position getByExtid(String extid) {
        try {
            return positionDbService.findByExtid(extid);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean delete(String extid) {
        try {
            return positionDbService.delete(extid);
        } catch (Exception e){
            return false;
        }
    }

    public List<Position> getAll() throws DatabaseRetrievalFailureException {
        List<Position> result = positionDbService.findActive();
        log.info("returned [{}]", result.size());
        return result;
    }
}
