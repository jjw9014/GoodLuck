package com.help.server.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Caffeine是使用Java8对Guava缓存的重写版本，一个接近最佳的的缓存库（号称性能最好）。
 * Spring5已经放弃guava，拥抱caffeine，它的API保持了近乎和guava一致，但是性能上碾压它
 *
 */
@Configuration
@EnableCaching
public class CaffeineConfig {

    public static final int DEFAULT_MAXSIZE = 50000;
    public static final int DEFAULT_TTL = 10;

    /**
     * 定義cache名稱、超時時長（秒）、最大容量
     * 每个cache缺省：10秒超时、最多缓存50000条数据，需要修改可以在                构造方法的参数中指定。
     */
    public enum Caches{
        AREA(30* 24* 3600, 10000), //有效期30天，最多缓存1万（生产不超过5千，2020-02-03）
        AREA_PROVINCE(30* 24* 3600, 10000), //有效期30天，最多缓存1万（生产不超过5千，2020-02-03）
        AREA_CITY(30* 24* 3600, 10000), //有效期30天，最多缓存1万（生产不超过5千，2020-02-03）
        AREA_DISTRICT(30* 24* 3600, 10000), //有效期30天，最多缓存1万（生产不超过5千，2020-02-03）
        ;

        Caches() {
        }

        Caches(int ttl) {
            this.ttl = ttl;
        }

        Caches(int ttl, int maxSize) {
            this.ttl = ttl;
            this.maxSize = maxSize;
        }

        private int maxSize =DEFAULT_MAXSIZE;    //最大數量
        private int ttl = DEFAULT_TTL;        //过期时间（秒）

        public int getMaxSize() {
            return maxSize;
        }
        public int getTtl() {
            return ttl;
        }
    }

    /**
     * 创建基于Caffeine的Cache Manager
     * @return
     */
    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        ArrayList<CaffeineCache> caches = new ArrayList<CaffeineCache>();
        for(Caches c : Caches.values()){
            caches.add(new CaffeineCache(c.name(),
                    Caffeine.newBuilder().recordStats()
                            .expireAfterWrite(c.getTtl(), TimeUnit.SECONDS)
                            .maximumSize(c.getMaxSize())
                            .build())
            );
        }

        cacheManager.setCaches(caches);

        return cacheManager;
    }

}
