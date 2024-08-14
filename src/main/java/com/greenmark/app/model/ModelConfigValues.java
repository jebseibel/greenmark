package com.greenmark.app.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@Data
@Slf4j
public class ModelConfigValues {

    @Value("${model.minute01.demote}")
    private BigDecimal model_minute01_demote;

    @Value("${model.minute01.promote}")
    private BigDecimal model_minute01_promote;

    @Value("${model.minute05.demote}")
    private BigDecimal model_minute05_demote;

    @Value("${model.minute05.promote}")
    private BigDecimal model_minute05_promote;

    @Value("${model.minute15.demote}")
    private BigDecimal model_minute15_demote;

    @Value("${model.minute15.promote}")
    private BigDecimal model_minute15_promote;

    @Value("${model.minute60.demote}")
    private BigDecimal model_minute60_demote;

    @Value("${model.minute60.promote}")
    private BigDecimal model_minute60_promote;

    @Value("${model.daily.demote}")
    private BigDecimal model_daily_demote;

    @Value("${model.daily.promote}")
    private BigDecimal model_daily_promote;

    @Value("${model.all.demote}")
    private BigDecimal model_all_demote;

    @Value("${model.all.promote}")
    private BigDecimal model_all_promote;
}
