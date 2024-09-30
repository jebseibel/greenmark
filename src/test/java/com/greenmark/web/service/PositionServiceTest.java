package com.greenmark.web.service;

import com.greenmark.database.service.PositionDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PositionServiceTest {

    @Autowired
    private PositionService positionService;

    @Autowired
    private PositionDbService positionDbService;

    @Test
    void create() {
//        positionDbService.create()
    }

    @Test
    void update() {
    }

    @Test
    void getByExtid() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }

    @Test
    void toResponse() {
    }

    @Test
    void testToResponse() {
    }
}