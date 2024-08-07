package com.greenmark.datafeed.mapper;

import com.greenmark.common.datafeed.QuoteDomain;
import com.greenmark.datafeed.finnhub.models.Quote;
import org.modelmapper.ModelMapper;

import java.util.List;

public class QuoteMapper {
    public static QuoteDomain toDomain(Quote item) {
        return new ModelMapper().map(item, QuoteDomain.class);
    }

    public static List<QuoteDomain> toList(List<Quote> items) {
        return items.stream().map( item -> toDomain(item)).toList();
    }
}
