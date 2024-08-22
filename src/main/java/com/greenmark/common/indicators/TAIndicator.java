package com.greenmark.common.indicators;

import com.greenmark.common.database.domain.StockWatchDb;
import lombok.NonNull;

import java.math.BigDecimal;

public interface TAIndicator {
    String getName();

    boolean isGreater(@NonNull StockWatchDb bucketData, @NonNull BigDecimal value);

    boolean isLess(@NonNull StockWatchDb bucketData, @NonNull BigDecimal value);

    boolean isEqual(@NonNull StockWatchDb bucketData, @NonNull BigDecimal value);
}
