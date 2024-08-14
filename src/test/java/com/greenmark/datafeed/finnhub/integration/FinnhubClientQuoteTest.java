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

import com.greenmark.common.DatafeedConfig;
import com.greenmark.datafeed.finnhub.client.FinnhubClient;
import com.greenmark.datafeed.finnhub.models.Quote;
import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = FinnhubClient.class)
@EnableConfigurationProperties({DatafeedConfig.class})
public class FinnhubClientQuoteTest {

    @Autowired
    private DatafeedConfig datafeedConfig;

    @Test
    void requestGetQuote() throws ParseException, IOException {
        FinnhubClient client = new FinnhubClient(datafeedConfig.getToken());
        Quote quote = client.getQuote("TSLA");
        assertNotNull(quote);
    }
}
