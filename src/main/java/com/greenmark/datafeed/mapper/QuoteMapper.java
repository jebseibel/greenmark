package com.greenmark.datafeed.mapper;

import com.greenmark.common.datafeed.QuoteDomain;
import com.greenmark.datafeed.finnhub.models.Quote;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;

public class QuoteMapper {
    public static QuoteDomain toDomain(Quote item) {
        QuoteDomain domain = new QuoteDomain();
        domain.setCurrent(toBigDecimal(item.getC()));
        domain.setOpen(toBigDecimal(item.getO()));
        domain.setLow(toBigDecimal(item.getL()));
        domain.setHigh(toBigDecimal(item.getH()));
        domain.setPreviousClose(toBigDecimal(item.getPc()));
        domain.setChange(toBigDecimal(item.getD()));
        domain.setChangePercent(toBigDecimal(item.getDp()));
        return domain;
    }

    public static BigDecimal toBigDecimal(Float value) {
        return new BigDecimal(value.toString());
    }
}
