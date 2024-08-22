package com.greenmark.datafeed.service;

import com.greenmark.common.DatafeedConfig;
import com.greenmark.common.datafeed.QuoteDomain;
import com.greenmark.common.datafeed.TechnicalIndicatorRequest;
import com.greenmark.datafeed.exceptions.FinnhubException;
import com.greenmark.datafeed.finnhub.client.FinnhubClient;
import com.greenmark.datafeed.finnhub.models.Quote;
import com.greenmark.datafeed.mapper.QuoteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

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
            Quote quote = finnhubClient.getQuote(symbol);
            return QuoteMapper.toDomain(quote);
        } catch (Exception e) {
            log.error("Finnhub exception {} for symbol {}", e.getMessage(), symbol);
            throw new FinnhubException(e.getMessage());
        }
    }

    public QuoteDomain getQuoteKotlin(String symbol) throws FinnhubException {
        try {
            FinnhubClient finnhubClient = new FinnhubClient(finnHubConfig.getToken());
            Quote quote = finnhubClient.getQuote(symbol);
            return QuoteMapper.toDomain(quote);
        } catch (Exception e) {
            log.error("Finnhub exception {} for symbol {}", e.getMessage(), symbol);
            throw new FinnhubException(e.getMessage());
        }
    }

    public BigDecimal getMacd(TechnicalIndicatorRequest request) {
        return getMacdRandom();
    }

    public BigDecimal getStochk(TechnicalIndicatorRequest request) {

        return randomBigDecimal();
    }

    ////////////////////////////////////////////////////////////////////////////////////////
//    private TAResponse getFakeResponse() {
//        List<Double> xx = Arrays.asList(0.0, 0.0, 73.74833, 73.72416, 70.676666, 70.045, 68.911, 67.41666, 66.802);
//        Map<String, List<Double>> indicat = new HashMap<>();
//        indicat.put("sma", xx);
//
//        List<Long> yy = Arrays.asList(1583107200L,1583193600L,1583280000L,1583366400L,1583452800L,1583712000L,1583798400L,1583884800L,1583971200L,1584057600L);
//        Map<String, List<Long>> timest = new HashMap<>();
//        timest.put("t", yy);
//
//        //
//        TAResponse response = new TAResponse();
//        response.setValues(indicat);
//        response.setTimestamps(timest);
//        return response;
//    }

    public BigDecimal getMacdRandom() {
        return randomBigDecimal();
    }

    public BigDecimal randomBigDecimal() {
        float random = randomFloat(100);
        return new BigDecimal(random);
    }

    public float randomFloat(int max) {
        return new Random().nextFloat(max);
    }
}
