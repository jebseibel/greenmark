package com.greenmark.datafeed.mapper;

import com.greenmark.common.datafeed.QuoteDomain;
import com.greenmark.database.db.DomainBuilder;
import com.greenmark.datafeed.finnhub.models.Quote;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuoteMapperTest {

    @Test
    void testToDomain() {
        Quote item = DomainBuilder.getQuote();
        QuoteDomain domain = QuoteMapper.toDomain(item);

        assertEquals(domain.getCurrent().toString(), item.getC().toString());
        assertEquals(domain.getOpen().toString(), item.getO().toString());
        assertEquals(domain.getLow().toString(), item.getL().toString());
        assertEquals(domain.getHigh().toString(), item.getH().toString());
        assertEquals(domain.getPreviousClose().toString(), item.getPc().toString());
        assertEquals(domain.getChange().toString(), item.getD().toString());
        assertEquals(domain.getChangePercent().toString(), item.getDp().toString());
    }
}