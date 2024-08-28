package com.greenmark.bootstrap;

import com.greenmark.app.BucketAllData;
import com.greenmark.app.StartupService;
import com.greenmark.common.DatafeedConfig;
import com.greenmark.common.database.domain.StockDb;
import com.greenmark.database.db.entity.StockDaily;
import com.greenmark.database.db.entity.StockWatch;
import com.greenmark.database.exceptions.DatabaseRetrievalFailureException;
import com.greenmark.database.service.StockDbService;
import com.greenmark.datafeed.service.DatafeedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MainTimer implements CommandLineRunner {

    private StartupService startupService;
    private BucketAllData bucketAllData;

    public MainTimer(StartupService startupService) {
        this.startupService = startupService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.error("START MAIN TIMER");

//        startupService.loadAll();
//        bucketAllData = startupService.getBucketAllData();
//        System.out.println(bucketAllData);


        log.error("CLOSE MAIN TIMER");
    }

    public void initialize() throws DatabaseRetrievalFailureException {
        log.error("START INITIALIZE");
//        currentActiveStocks = stockDbService.findActive();
//        log.error("Current active stocks ["+currentActiveStocks.size()+"]");
//
//        Iterator<StockDb> iterator = currentActiveStocks.iterator();
//        while (iterator.hasNext()) {
//            try {
//                StockDb stockDb = iterator.next();
//                QuoteDomain domain = datafeedService.getQuote(stockDb.getSymbol());
//            }
//            catch (Exception e) {
//                log.error(e.getMessage());
//            }
//        }
        // initialize or refresh

        // FOR EACH MINUTE
        // update StockDaily
        // update BucketDaily
        // update BucketMinute60
        // update BucketMinute15
        // update BucketMinute05
        // update BucketMinute01
        log.error("CLOSE INITIALIZE");
    }


}
