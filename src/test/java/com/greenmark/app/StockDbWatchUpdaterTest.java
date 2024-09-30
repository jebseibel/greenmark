package com.greenmark.app;

import com.greenmark.database.db.entity.StockWatchDb;
import com.greenmark.datafeed.service.DatafeedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class StockDbWatchUpdaterTest {

    @InjectMocks
    StockWatchDb stockWatchDb;

    @Mock
    DatafeedService datafeedService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getBucketUpdater() {
        assertNotNull(stockWatchDb);
    }

    @Test
    void update() {
    }
}