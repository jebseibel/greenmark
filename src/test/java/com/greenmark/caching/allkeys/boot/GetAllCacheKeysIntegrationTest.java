package com.greenmark.caching.allkeys.boot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.greenmark.allkeys.config.AllKeysConfig;
import com.greenmark.allkeys.service.SlowServiceWithCache;
import com.github.benmanes.caffeine.cache.Cache;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = AllKeysConfig.class)
public class GetAllCacheKeysIntegrationTest {

    @Autowired
    private SlowServiceWithCache slowServiceWithCache;
    @Autowired
    private CacheManager cacheManager;

    @Test
    public void givenCaffeineCacheCachingSlowCalls_whenCacheManagerProperlyCasted_thenAllKeysAreAccessible() {
        slowServiceWithCache.save("first", "some-value-first");
        slowServiceWithCache.save("second", "other-value-second");

        Cache<Object, Object> caffeine = getNativeCaffeineCacheForSlowService();

        assertThat(caffeine.asMap().keySet()).containsOnly("first", "second");
    }

    private Cache<Object, Object> getNativeCaffeineCacheForSlowService() {
        CaffeineCacheManager caffeineCacheManager = (CaffeineCacheManager) cacheManager;
        CaffeineCache cache = (CaffeineCache) caffeineCacheManager.getCache("slowServiceCache");
        return cache.getNativeCache();
    }
}