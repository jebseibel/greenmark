package com.greenmark.common.datafeed;

import com.greenmark.common.enums.TaType;
import com.greenmark.common.enums.TimeframeType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TechnicalIndicatorRequest {
    String symbol;
    TaType type;
    TimeframeType resolution;
    LocalDateTime localDateTime;
}
