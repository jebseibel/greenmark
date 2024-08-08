package com.greenmark.app;

import com.greenmark.common.DatafeedConfig;
import com.greenmark.common.database.domain.StockDailyDb;
import com.greenmark.common.database.domain.StockDb;
import com.greenmark.common.datafeed.QuoteDomain;
import com.greenmark.database.service.StockDailyDbService;
import com.greenmark.database.service.StockDbService;
import com.greenmark.datafeed.service.DatafeedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class NightlyService {

    @Autowired
    private StockDbService stockDbService;

    @Autowired
    private StockDailyDbService stockDailyDbService;

    public NightlyService() {
    }

    public void removeInactiveStocksFromStockDaily() throws Exception{
//        List<StockDb> currentActiveStocks;

//            currentStocks = stockDbService.findAll();
//            currentStockDaily = stockDailyDbService.findAll();
//            log.error("Current active stocks [{}]", currentActiveStocks.size());

            List<StockDb> currentInactiveStocks = stockDbService.findInactive();
            List<StockDailyDb> currentStockDaily = stockDailyDbService.findAll();

            //loop inactive and if found in delete
            Iterator<StockDb> iterator = currentInactiveStocks.iterator();
            while (iterator.hasNext()) {
                try {





                }
                catch (Exception e) {
                    log.error(e.getMessage());
                }
            }

        //limitPerSecond
    }
}
