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

import com.greenmark.datafeed.finnhub.models.Quote;
import com.greenmark.datafeed.finnhub.models.StockSymbol;
import com.greenmark.datafeed.finnhub.client.FinnhubClient;
import com.greenmark.datafeed.finnhub.models.*;
import com.greenmark.datafeed.service.DatafeedConfig;
import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = FinnhubClient.class)
@EnableConfigurationProperties({DatafeedConfig.class})
public class FinnhubClientBuilderTest {

    @Autowired
    private DatafeedConfig datafeedConfig;

    @Test
    void invocationQuote() throws ParseException, IOException {
        FinnhubClient client = new FinnhubClient.Builder().token(datafeedConfig.getToken()).build();
        Quote quote = client.quote("TSLA");
        System.out.println(quote);
        assertNotNull(quote);
    }
    
    @Test
    void invocationCompanyProfile() throws ParseException, IOException {
        FinnhubClient client = new FinnhubClient.Builder().token(datafeedConfig.getToken()).build();
        CompanyProfile2 companyProfile = client.companyProfile("TSLA");
        assertNotNull(companyProfile);
    }
    
    @Test
    void invocationSymbols() throws ParseException, IOException {
        FinnhubClient client = new FinnhubClient.Builder().token(datafeedConfig.getToken()).build();
        List<StockSymbol> symbols = client.symbols(Exchange.US_EXCHANGES.toString());
        List<StockSymbol> t = symbols.stream().filter(s -> s.getDescription().contains("AMAZON.COM")).collect(Collectors.toList());
        assertEquals(1, t.size());
    }

    @Test
    void invocationSymbolLookup() throws ParseException, IOException {
        FinnhubClient client = new FinnhubClient.Builder().token(datafeedConfig.getToken()).build();
        SymbolLookup lookup = client.searchSymbol("apple");
        assertEquals(22, lookup.getCount());
    }
}
