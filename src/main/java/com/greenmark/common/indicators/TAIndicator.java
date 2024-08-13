package com.greenmark.common.indicators;

import com.greenmark.common.database.domain.BucketData;
import lombok.NonNull;

import java.math.BigDecimal;

public interface TAIndicator {
    String getName();
    boolean isGreater(@NonNull BucketData bucketData, @NonNull BigDecimal value);
    boolean isLess(@NonNull BucketData bucketData, @NonNull BigDecimal value);
    boolean isEqual(@NonNull BucketData bucketData, @NonNull BigDecimal value);
}
