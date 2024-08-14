package com.greenmark.bootstrap;

import com.greenmark.common.database.domain.StockDailyDb;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@ConfigurationProperties(prefix = "model")
@Data
@Slf4j
public class ModelLogic {
    private BigDecimal stockMinPrice;
    private BigDecimal stockMaxPrice;

    private BigDecimal passStochkDaily;
    private BigDecimal passStochkMinute60;
    private BigDecimal passStochkMinute15;
    private BigDecimal passStochkMinute05;
    private BigDecimal passStochkMinute01;

    public boolean passStockDaily(StockDailyDb stock) {
        return stock.getStochk().compareTo(passStochkDaily) > 0;
    }

}
