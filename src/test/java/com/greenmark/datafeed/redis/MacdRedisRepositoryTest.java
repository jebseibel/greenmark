package com.greenmark.datafeed.redis;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
class MacdRedisRepositoryTest {

    @Autowired
    private MacdRedisRepository macdRedisRepository;

    @Test
    public void testPersistence() {
        //given
        MacdRedis macdRedis = new MacdRedis();
        macdRedis.setMacd(BigDecimal.TEN);
        macdRedis.setSymbol("AAPL");
        macdRedis.setTimestamp(100L);
        //when
        macdRedisRepository.save(macdRedis);

        //then
        assertNotNull(macdRedis.getId());

        MacdRedis newProduct = macdRedisRepository.findById(macdRedis.getId()).orElse(null);
        assertNotNull(newProduct);
//        Assert.assertEquals("AAPL", newProduct.getSymbol());
//        Assert.assertEquals(BIG_DECIMAL_100.compareTo(newProduct.getPrice()), 0);
//        Assert.assertEquals(IMAGE_URL, newProduct.getImageUrl());
    }
}