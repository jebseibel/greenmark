package com.greenmark.datafeed.service;

import com.greenmark.common.DatafeedConfig;
import com.greenmark.common.datafeed.QuoteDomain;
import com.greenmark.datafeed.exceptions.FinnhubException;
import com.greenmark.datafeed.finnhub.client.FinnhubClient;
import com.greenmark.datafeed.finnhub.models.Quote;
import com.greenmark.datafeed.mapper.QuoteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DatafeedService {

    @Autowired
    private DatafeedConfig finnHubConfig;

    public DatafeedService() {
    }

    public QuoteDomain getQuote(String symbol) throws FinnhubException {
        try {
            FinnhubClient finnhubClient = new FinnhubClient(finnHubConfig.getToken());
            Quote quote = finnhubClient.quote(symbol);
            return QuoteMapper.toDomain(quote);
        } catch (Exception e) {
            log.error("Finnhub exception {} for symbol {}", e.getMessage(), symbol);
            throw new FinnhubException(e.getMessage());
        }
    }
}
