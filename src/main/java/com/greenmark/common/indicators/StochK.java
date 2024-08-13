package com.greenmark.common.indicators;

import com.greenmark.common.database.domain.BucketData;
import com.greenmark.datafeed.finnhub.models.Indicator;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Data
@Slf4j
public class StochK implements TAIndicator {

    private String name = this.getClass().getSimpleName();

    public StochK() {

    }

    public boolean isGreater(@NonNull BucketData bucketData, @NonNull BigDecimal value) {
        return value.compareTo(bucketData.getStochk()) > 0;
    }

    public boolean isLess(@NonNull BucketData bucketData, @NonNull BigDecimal value) {
        return value.compareTo(bucketData.getStochk()) < 0;
    }

    public boolean isEqual(@NonNull BucketData bucketData, @NonNull BigDecimal value) {
        return value.compareTo(bucketData.getStochk()) == 0;
    }
}
