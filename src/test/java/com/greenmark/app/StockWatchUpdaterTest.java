package com.greenmark.app;

import com.greenmark.database.db.entity.StockWatch;
import com.greenmark.datafeed.service.DatafeedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class StockWatchUpdaterTest {

    @InjectMocks
    StockWatch stockWatch;

    @Mock
    DatafeedService datafeedService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getBucketUpdater() {
        assertNotNull(stockWatch);
    }

    @Test
    void update() {
    }
}