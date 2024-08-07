/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greenmark.datafeed.finnhub.integration;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.greenmark.AppConfig;
import com.greenmark.datafeed.finnhub.models.Quote;
import com.greenmark.datafeed.finnhub.models.StockSymbol;
import com.greenmark.datafeed.finnhub.client.FinnhubClient;

import com.greenmark.datafeed.finnhub.models.*;
import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FinnhubClient.class)
@EnableConfigurationProperties({AppConfig.class})
public class FinnhubClientTest {

    @Autowired
    AppConfig appConfig;
    private String token;


    @Test
    void requestQuote() throws ParseException, IOException {
        token = appConfig.getFinnhubtoken();
    	FinnhubClient client = new FinnhubClient(token);
        Quote quote = client.quote("TSLA");
        assertNotNull(quote);
    }

    @Test
    void requestCompanyTest() throws ParseException, IOException {
    	FinnhubClient client = new FinnhubClient(token);
        CompanyProfile2 companyProfile = client.companyProfile("TSLA");
        assertNotNull(companyProfile);
    }

    @Test
    void requestCompanyProfile() throws ParseException, IOException {
        FinnhubClient client = new FinnhubClient(System.getenv(token));
        CompanyProfile2 companyProfile = client.companyProfile("TSLA");
        assertNotNull(companyProfile);
    }

    @Test
    void requestSymbols() throws ParseException, IOException {
    	FinnhubClient client = new FinnhubClient(token);
        List<StockSymbol> symbols = client.symbols(Exchange.US_EXCHANGES.toString());
        List<StockSymbol> t = symbols.stream().filter(s -> s.getDescription().contains("AMAZON.COM")).collect(Collectors.toList());
        assertEquals(1, t.size());
    }

    @Test
    void requestSymbolLookup() throws ParseException, IOException {
    	FinnhubClient client = new FinnhubClient(token);
        SymbolLookup lookup = client.searchSymbol("apple");
        assertEquals(22, lookup.getCount());
    }

    @Test
    void requestMarketStatus() throws ParseException, IOException {
        FinnhubClient client = new FinnhubClient(token);
        MarketStatus status = client.marketStatus(Exchange.US_EXCHANGES.code());
        assertNotNull(status);
    }

    @Test
    void requestMarketHoliday() throws ParseException, IOException {
        FinnhubClient client = new FinnhubClient(token);
        MarketHoliday holidays = client.marketHoliday(Exchange.US_EXCHANGES.code());
        assertNotNull(holidays);
        assertTrue(holidays.getData().size() >= 1);
    }

    @Test
    void requestMarketNews() throws ParseException, IOException {
        FinnhubClient client = new FinnhubClient(token);
        List<MarketNews> marketNews = client.marketNews("general");
        assertNotNull(marketNews);
        assertTrue(marketNews.size() >= 1);
    }

//    @Test
//    void requestCandlesOneDay() throws ParseException, IOException {
//        FinnhubClient client = new FinnhubClient(token);
//        StockCandles candle = client.candle(
//            "TSLA",
//            "D",
//            // Nov/5/2021 18:00:00 GMT = 1636135200
//            1636135200,
//            1636135200);
//
//        assertEquals("ok", candle.getS());
//        assertEquals(1, candle.getC().size());
//    }

//    @Test
//    void requestCandlesTwoDays() throws ParseException, IOException {
//        FinnhubClient client = new FinnhubClient(token);
//        StockCandles candle = client.candle(
//            "TSLA",
//            "D",
//            // Nov/4/2021 18:00:00 GMT = 1636048800
//            1636048800,  // Nov/4
//            1636135200   // Nov/5
//            );
//
//        assertEquals(2, candle.getC().size());
//        assertEquals("ok", candle.getS());
//
//    }

//    @Test
//    void requestDividends() throws ParseException, IOException {
//        FinnhubClient client = new FinnhubClient(token);
//        List<Dividends> dividends = client.dividends("TSLA", "2020-01-01", "2023-01-01");
//        assertNotNull(dividends);
//        assertEquals(dividends.size(), 0);
//    }

    @Test
    void requestCompanyNews() throws ParseException, IOException {
        FinnhubClient client = new FinnhubClient(token);
        List<CompanyNews> companyNews = client.companyNews("TSLA", "2024-01-01", "2024-03-20");
        assertNotNull(companyNews);
    }

    @Test
    void requestBasicFinancials() throws ParseException, IOException {
        FinnhubClient client = new FinnhubClient(token);
        BasicFinancials metrics = client.basicFinancials("TSLA", null);
        assertNotNull(metrics);
        assertNotNull(metrics.getMetric());
        metrics = client.basicFinancials("TSLA", "all");
        assertNotNull(metrics);
        assertNotNull(metrics.getMetric());
    }

    @Test
    void requestInsiderTransactions() throws ParseException, IOException {
        FinnhubClient client = new FinnhubClient(token);
        InsiderTransactions insiderTransactions = client.insiderTransactions("TSLA", null, null);
        assertNotNull(insiderTransactions);
        assertNotNull(insiderTransactions.getData().size() > 0);
    }
}
