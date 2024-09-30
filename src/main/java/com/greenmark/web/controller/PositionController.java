package com.greenmark.web.controller;

import com.greenmark.common.database.domain.Position;
import com.greenmark.database.exceptions.DatabaseRetrievalFailureException;
import com.greenmark.web.request.RequestPositionCreate;
import com.greenmark.web.request.RequestPositionUpdate;
import com.greenmark.web.response.ResponsePosition;
import com.greenmark.web.service.PositionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/")
    public List<ResponsePosition> getAll() throws DatabaseRetrievalFailureException {
        List<Position> result = positionService.getAll();
        return toResponse(result);
    }

    @GetMapping("/{extid}")
    public ResponsePosition getByExtid(@PathVariable String extid) {
        Position item = positionService.getByExtid(extid);
        return toResponse(item);
    }

    @PostMapping("/")
    public ResponsePosition create(@RequestBody RequestPositionCreate request) {
        Position item = Position.builder()
                .symbol(request.getSymbol())
                .name(request.getName())
                .shares(request.getShares())
                .price(request.getPrice())
                .total(request.getTotal())
                .build();
        Position result = positionService.create(item);
        return toResponse(result);
    }

    @PutMapping("/{extid}")
    public ResponsePosition update(@PathVariable String extid, @RequestBody RequestPositionUpdate request) {
        Position result =  positionService.update(extid,
                request.getName(),
                request.getShares(),
                request.getPrice(),
                request.getTotal());
        return toResponse(result);
    }

    @DeleteMapping("/{extid}")
    public void delete(@PathVariable String extid) {
        positionService.delete(extid);
    }

    private ResponsePosition toResponse(Position itemDb) {
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

    private List<ResponsePosition> toResponse(List<Position> items) {
        return items.stream().map(this::toResponse).toList();
    }
}
