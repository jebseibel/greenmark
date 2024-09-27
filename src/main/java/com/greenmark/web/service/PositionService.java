package com.greenmark.web.service;

import com.greenmark.common.database.domain.PositionDb;
import com.greenmark.database.db.mapper.PositionMapper;
import com.greenmark.database.exceptions.DatabaseRetrievalFailureException;
import com.greenmark.database.service.PositionDbService;
import com.greenmark.web.request.RequestPositionCreate;
import com.greenmark.web.request.RequestPositionUpdate;
import com.greenmark.web.response.ResponsePosition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PositionService {

    PositionDbService positionDbService;
    PositionMapper positionMapper;

    public PositionService(PositionDbService positionDbService, PositionMapper positionMapper) {
        this.positionDbService = positionDbService;
        this.positionMapper = positionMapper;
    }

    public ResponsePosition create(RequestPositionCreate request) {
        try {
            PositionDb item = PositionDb.builder()
                    .symbol(request.getSymbol())
                    .name(request.getName())
                    .shares(request.getShares())
                    .price(request.getPrice())
                    .total(request.getTotal())
                    .build();
            PositionDb result = positionDbService.create(request.getName(), item);

            //change to response
            return toResponse(result);
        } catch (Exception e) {
            return null;
        }
    }

    public ResponsePosition update(String extid, RequestPositionUpdate request) {
        try {
            PositionDb result = positionDbService.update(
                    extid,
                    request.getShares(),
                    request.getPrice(),
                    request.getTotal());
            System.out.println(result);
            //change to response
            return toResponse(result);
        } catch (Exception e) {
            return null;
        }
    }

    public ResponsePosition getByExtid(String extid) {
        try {
            PositionDb result = positionDbService.findByExtid(extid);

            //change to response
            ResponsePosition address =  toResponse(result);
            log.info(String.valueOf(address));
            return address;
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

    public List<ResponsePosition> getAll() throws DatabaseRetrievalFailureException {
        List<PositionDb> result = positionDbService.findActive();
        return toResponse(result);
    }

    public ResponsePosition toResponse(PositionDb itemDb) {
        return ResponsePosition.builder()
                .extid(itemDb.getExtid())
                .name(itemDb.getName())
                .symbol(itemDb.getSymbol())
                .price(itemDb.getPrice())
                .shares(itemDb.getShares())
                .total(itemDb.getTotal())
                .created(itemDb.getCreatedAt())
                .modified(itemDb.getModifiedAt())
                .deleted(itemDb.getDeletedAt())
                .build();
    }

    public List<ResponsePosition> toResponse(List<PositionDb> items) {
        return items.stream().map(this::toResponse).toList();
    }
}
