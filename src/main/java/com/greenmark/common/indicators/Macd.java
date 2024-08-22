package com.greenmark.common.indicators;

import com.greenmark.common.database.domain.StockWatchDb;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Data
@Slf4j
public class Macd implements TAIndicator {

    private String name = this.getClass().getSimpleName();

    public Macd() {

    }

    public boolean isGreater(@NonNull StockWatchDb bucketData, @NonNull BigDecimal value) {
        System.out.println(bucketData.toStringTA());
        System.out.println(value);
        return value.compareTo(bucketData.getMacd()) > 0;
    }

    public boolean isLess(@NonNull StockWatchDb bucketData, @NonNull BigDecimal value) {
        return value.compareTo(bucketData.getMacd()) < 0;
    }

    public boolean isEqual(@NonNull StockWatchDb bucketData, @NonNull BigDecimal value) {
        return value.compareTo(bucketData.getMacd()) == 0;
    }
}
