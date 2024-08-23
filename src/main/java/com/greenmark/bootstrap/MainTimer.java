package com.greenmark.bootstrap;

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

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MainTimer implements CommandLineRunner {

    private List<StockDb> currentActiveStocks;
//    private final List<BucketData> bucketData = new ArrayList<BucketData>();
    private final List<StockWatch> stockWatchMinute60s = new ArrayList<StockWatch>();
    private final List<StockWatch> stockWatchMinute15s = new ArrayList<StockWatch>();
    private final List<StockWatch> stockWatchMinute05s = new ArrayList<StockWatch>();
    private final List<StockWatch> stockWatchMinute01s = new ArrayList<StockWatch>();
    private final List<StockDaily> stockDailies = new ArrayList<StockDaily>();

    @Autowired
    private StockDbService stockDbService;

//    @Autowired
//    private StockDailyDbService stockDailyDbService;

    @Autowired
    private DatafeedService datafeedService;

    @Autowired
    private ModelLogic modelLogic;

    @Autowired
    private DatafeedConfig finnHubConfig;

    public MainTimer() {
    }

    @Override
    public void run(String... args) throws Exception {
        log.error("START MAIN TIMER");

        initialize();


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
