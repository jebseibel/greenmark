package com.greenmark.datafeed.service;

import com.greenmark.database.db.DomainBuilder;
import com.greenmark.datafeed.finnhub.client.FinnhubClient;
import com.greenmark.datafeed.finnhub.models.Quote;
import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DatafeedServiceTest {

    @Autowired
    private DatafeedService service;

    static FinnhubClient finnhubMock;

    @BeforeAll
    static void initClientMock() throws IOException, ParseException {

        finnhubMock = mock(FinnhubClient.class);

        Quote ibm = new Quote();
        ibm.c(Float.valueOf("180.00"));
        ibm.d(Float.valueOf("185.00"));
        ibm.dp(Float.valueOf("181.00"));
        ibm.o(Float.valueOf("185.00"));
        ibm.h(Float.valueOf("185.00"));
        ibm.l(Float.valueOf("185.00"));
        ibm.pc(Float.valueOf("185.00"));

        when(finnhubMock.quote("IBM")).thenReturn(ibm);
    }

    @Nested
    class CreateTests {


        @Test
        void getQuote() throws Exception {
            // test
            String extid = DomainBuilder.getUUID();
//            AccountDb result = service.create(extid, name, description);
//
//            // validate
//            assertNotNull(result);
//            assertEquals(result.getName(), name);
//            assertEquals(result.getDescription(), description);
        }
    }
}