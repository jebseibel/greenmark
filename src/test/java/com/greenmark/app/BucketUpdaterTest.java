package com.greenmark.app;

import com.greenmark.database.service.AccountDbService;
import com.greenmark.datafeed.service.DatafeedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BucketUpdaterTest {

    @InjectMocks
    BucketUpdater bucketUpdater;

    @Mock
    DatafeedService datafeedService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getBucketUpdater() {
        assertNotNull(bucketUpdater);
    }

    @Test
    void update() {
    }
}