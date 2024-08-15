package com.greenmark.datafeed.service;

import com.greenmark.common.datafeed.TechnicalIndicatorRequest;
import com.greenmark.datafeed.DomainBuilder;
import com.greenmark.datafeed.finnhub.client.FinnhubClient;
import com.greenmark.datafeed.finnhub.models.Quote;
import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class DatafeedServiceTest {

    static FinnhubClient finnhubMock;

    @Autowired
    private DatafeedService service;

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

        when(finnhubMock.getQuote("IBM")).thenReturn(ibm);
    }

    @Test
    void getQuote() {
    }

    @Test
    void getMacd() {
        TechnicalIndicatorRequest request = DomainBuilder.getMacd("SYMBOL");
        BigDecimal result = service.getMacd(request);
        System.out.println(result);
    }

    @Test
    void getStochk() {
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