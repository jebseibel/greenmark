package com.greenmark.datafeed.service;

import com.greenmark.datafeed.exceptions.FinnhubException;
import com.greenmark.datafeed.finnhub.client.FinnhubClient;
import com.greenmark.datafeed.finnhub.models.Quote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DatafeedService {

    private final FinnhubClient finnhubClient;

    public DatafeedService(FinnhubClient finnhubClient) {

        this.finnhubClient = finnhubClient;
    }

    public Quote getQuote(String symbol) throws FinnhubException {
        try {
            return finnhubClient.quote(symbol);
        } catch (Exception e) {
            log.error("Finnhub exception {} for symbol {}", e.getMessage(), symbol);
            throw new FinnhubException(e.getMessage());
        }
    }

    public Quote TechnicalIndicator(String symbol) throws FinnhubException {
        try {
            return finnhubClient.quote(symbol);
        } catch (Exception e) {
            log.error("Finnhub exception {} for symbol {}", e.getMessage(), symbol);
            throw new FinnhubException(e.getMessage());
        }
    }
}
