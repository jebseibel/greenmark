package com.greenmark.datafeed.redis;

import lombok.Data;
import org.springframework.data.annotation.Id;
//import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;

@Data
//@RedisHash("finnhub-macd")
public class MacdRedis {
    @Id
    private String id;
    private String symbol;
    private BigDecimal macd;
    private long timestamp;
}
