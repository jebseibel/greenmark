package com.greenmark.common.datafeed;

import com.greenmark.common.enums.ResolutionType;
import com.greenmark.common.enums.TaType;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TechnicalIndicatorRequest {
    String symbol;
    TaType type;
    ResolutionType resolution;
    LocalDateTime localDateTime;
}
