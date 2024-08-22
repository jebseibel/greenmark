package com.greenmark.common.datafeed;

import com.greenmark.common.enums.TimeframeType;
import com.greenmark.common.enums.TaType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TechnicalIndicatorRequest {
    String symbol;
    TaType type;
    TimeframeType resolution;
    LocalDateTime localDateTime;
}
