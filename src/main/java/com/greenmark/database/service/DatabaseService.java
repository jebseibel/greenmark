package com.greenmark.database.service;

import com.greenmark.common.database.domain.StockWatch;
import com.greenmark.common.enums.TimeframeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DatabaseService {

    private AccountDbService accountDbService;
    private ScenarioDbService scenarioDbService;
    private StockDbService stockDbService;
    private StockWatchDbService stockWatchDbService;

    public DatabaseService(
            AccountDbService accountDbService,
            StockWatchDbService stockWatchDbService,
            ScenarioDbService scenarioDbService,
            StockDbService stockDbService
    ) {
        this.accountDbService = accountDbService;
        this.scenarioDbService = scenarioDbService;
        this.stockWatchDbService = stockWatchDbService;
        this.stockDbService = stockDbService;
    }

    public List<StockWatch> getAllMinute01 () {
        return stockWatchDbService.findByTimeframe(TimeframeType.MINUTE01);
    }

    public List<StockWatch> getAllMinute05 () {
        return stockWatchDbService.findByTimeframe(TimeframeType.MINUTE05);
    }

    public List<StockWatch> getAllMinute15 () {
        return stockWatchDbService.findByTimeframe(TimeframeType.MINUTE15);
    }

    public List<StockWatch> getAllMinute60 () {
        return stockWatchDbService.findByTimeframe(TimeframeType.MINUTE60);
    }

    public List<StockWatch> getAllDaily () {
        return stockWatchDbService.findByTimeframe(TimeframeType.DAILY);
    }
}
