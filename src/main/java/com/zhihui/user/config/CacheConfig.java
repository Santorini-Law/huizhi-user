package com.zhihui.user.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.zhihui.user.domain.GrayFeatureDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.benmanes.caffeine.cache.Cache;

import java.util.concurrent.TimeUnit;

/**
 * 缓存管理
 *
 * @author LDZ
 * @date 2020-02-28 14:17
 */
@Configuration
@Slf4j
public class CacheConfig {

    @Bean(name = "grayFeatureCache")
    public Cache<Long, GrayFeatureDO> grayFeatureCache() {
        Cache<Long, GrayFeatureDO> grayFeatureCache = Caffeine.newBuilder().expireAfterWrite(120, TimeUnit.SECONDS).build();
        log.debug("载入 grayFeatureCache");
        return grayFeatureCache;
    }
}
