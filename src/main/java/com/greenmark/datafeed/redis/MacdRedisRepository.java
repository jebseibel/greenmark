package com.greenmark.datafeed.redis;

import org.springframework.data.repository.CrudRepository;

public interface MacdRedisRepository extends CrudRepository<MacdRedis, String> {
}
