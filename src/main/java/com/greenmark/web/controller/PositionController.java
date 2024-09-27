package com.greenmark.web.controller;

import com.greenmark.database.exceptions.DatabaseRetrievalFailureException;
import com.greenmark.web.request.RequestPositionCreate;
import com.greenmark.web.request.RequestPositionUpdate;
import com.greenmark.web.response.ResponsePosition;
import com.greenmark.web.service.PositionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/position")
public class PositionController {

    PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/")
    public List<ResponsePosition> getAll() throws DatabaseRetrievalFailureException {
        return positionService.getAll();
    }

    @GetMapping("/{extid}")
    public ResponsePosition getByExtid(@PathVariable String extid) {
        return positionService.getByExtid(extid);
    }

    @PostMapping("/{extid}")
    public ResponsePosition create(@RequestBody RequestPositionCreate request) {
        return positionService.create(request);
    }

    @PutMapping("/{extid}")
    public ResponsePosition update(@PathVariable String extid, @RequestBody RequestPositionUpdate request) {
        return positionService.update(extid, request);
    }

    @DeleteMapping("/{extid}")
    public boolean delete(@PathVariable String extid) {
        return positionService.delete(extid);
    }
}
