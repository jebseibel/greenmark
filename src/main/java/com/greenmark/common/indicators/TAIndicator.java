package com.greenmark.common.indicators;

import com.greenmark.common.database.domain.StockWatch;
import lombok.NonNull;

import java.math.BigDecimal;

public interface TAIndicator {
    String getName();

    boolean isGreater(@NonNull StockWatch bucketData, @NonNull BigDecimal value);

    boolean isLess(@NonNull StockWatch bucketData, @NonNull BigDecimal value);

    boolean isEqual(@NonNull StockWatch bucketData, @NonNull BigDecimal value);
}
