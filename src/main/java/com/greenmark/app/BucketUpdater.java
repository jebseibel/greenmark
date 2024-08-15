package com.greenmark.app;

import com.greenmark.app.buckets.Bucket01Service;
import com.greenmark.app.buckets.BucketService;
import com.greenmark.app.model.ModelLogic;
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

    public boolean update(Bucket01Service bucket01Service) {

        List<BucketData> bucketData = bucket01Service.getBucketData();
        for (BucketData bucketData1 : bucketData) {
            // build the request
            String symbol = bucketData1.getSymbol();
            TechnicalIndicatorRequest request = getRequest(symbol);

            //get the data and save
            BigDecimal macd = datafeedService.getMacd(request);
            bucketData1.setMacd(macd);
        }

        return true;
    }

    private TechnicalIndicatorRequest getRequest(String symbol) {
        TechnicalIndicatorRequest request = new TechnicalIndicatorRequest();
        request.setSymbol(symbol);
        request.setResolution(ResolutionType.DAILY);
        request.setType(TaType.MACD);
        request.setLocalDateTime(LocalDateTime.now());
        return request;
    }
}
