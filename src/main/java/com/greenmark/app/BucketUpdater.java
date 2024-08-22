package com.greenmark.app;

import com.greenmark.common.database.domain.BucketData;
import com.greenmark.common.datafeed.TechnicalIndicatorRequest;
import com.greenmark.common.enums.ResolutionType;
import com.greenmark.common.enums.TaType;
import com.greenmark.datafeed.service.DatafeedService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BucketUpdater {

    private DatafeedService datafeedService;

    public BucketUpdater(DatafeedService datafeedService) {
        this.datafeedService = datafeedService;
    }

    public boolean update(List<BucketData> bucketData, ResolutionType resolutionType ) {

        for (BucketData bucketData1 : bucketData) {

            // build the request
            String symbol = bucketData1.getSymbol();
            TechnicalIndicatorRequest request = getRequest(symbol, resolutionType);

            //get the data and save
            BigDecimal macd = datafeedService.getMacd(request);
            bucketData1.setMacd(macd);
        }

        return true;
    }

    private TechnicalIndicatorRequest getRequest(String symbol, ResolutionType resolutionType) {
        TechnicalIndicatorRequest request = new TechnicalIndicatorRequest();
        request.setSymbol(symbol);
        request.setResolution(resolutionType);
        request.setType(TaType.MACD);
        request.setLocalDateTime(LocalDateTime.now());
        return request;
    }
}
