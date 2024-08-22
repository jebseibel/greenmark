package com.greenmark.database.service;

import com.greenmark.common.database.domain.BucketMinute60Db;
import com.greenmark.database.DomainBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatabaseBucketServiceTest {

    @InjectMocks
    DatabaseBucketService service;

    @Mock
    private BucketDailyDbService bucketDailyDbService;

    @Mock
    private BucketMinute60DbService bucketMinute60DbService;

    @Mock
    private BucketMinute15DbService bucketMinute15DbService;

    @Mock
    private BucketMinute05DbService bucketMinute05DbService;

    @Mock
    private BucketMinute01DbService bucketMinute01DbService;

    @BeforeEach
    void setUp() {
    }

    @Nested
    class Minute60 {

        List<BucketMinute60Db> list = new ArrayList<>();

        @BeforeEach
        void beforeEach() {
            list.add(DomainBuilder.getBucketMinute60Db());
            list.add(DomainBuilder.getBucketMinute60Db());
        }

        @Test
        void getAllMinute01() {
            when(service.getAllMinute60()).thenReturn(list);

            //test
            int size = service.getAllMinute60().size();
            assertEquals(size, list.size());
        }

    }


}