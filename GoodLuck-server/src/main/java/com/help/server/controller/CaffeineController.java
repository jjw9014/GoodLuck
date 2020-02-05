/*
 * Copyright (c) 2010-2020 Founder Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Founder. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the agreements
 * you entered into with Founder.
 *
 */
package com.help.server.controller;

import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.help.server.model.Area;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@RestController
@RequestMapping("/caffeine")
public class CaffeineController {

    @Autowired
    private CacheManager cacheManager;

    @RequestMapping("/getCache")
    public String getCache(String cacheName, String key) {
        if (StringUtils.isBlank(cacheName) || StringUtils.isBlank(key)) {
            return "请输入缓存名和键值！";
        }

        if ("allkeys".equals(key)) {
            return getAll(cacheName);
        }


        Area value = cacheManager.getCache(cacheName).get(String.format("%s:%s", cacheName, key), Area.class);
        if (value == null) {
            return "缓存不存在，请先设置缓存！";
        }

        return JSON.toJSONString(value);
    }

    @RequestMapping("/clearCache")
    public String clearCache(String cacheName, String key) {
        if (StringUtils.isBlank(cacheName) || StringUtils.isBlank(key)) {
            return "请输入缓存名和键值！";
        }

        if ("allkeys".equals(key)) {
            cacheManager.getCache(cacheName).clear();
        } else {
            cacheManager.getCache(cacheName).evict(String.format("%s:%s", cacheName, key));
        }

        return "清除成功";
    }

    @RequestMapping("/stats")
    public String stats(String cacheName) {
        if (StringUtils.isBlank(cacheName)) {
            return "请输入缓存名！";
        }

        Cache cache = cacheManager.getCache(cacheName);
        com.github.benmanes.caffeine.cache.Cache c = (com.github.benmanes.caffeine.cache.Cache) cache.getNativeCache();

        CacheStats stats = c.stats();
        // hitRate()：返回命中与请求的比率
        // hitCount(): 返回命中缓存的总数
        // evictionCount()：缓存逐出的数量
        // averageLoadPenalty()：加载新值所花费的平均时间

        Map<String, Object> map = new TreeMap<>();
        map.put("缓存总数", c.asMap().size());
        map.put("命中与请求的比率", new DecimalFormat("0.00%").format(stats.hitRate()));
        map.put("命中缓存的总数", stats.hitCount());
        map.put("缓存逐出的数量", stats.evictionCount());
        map.put("加载新值所花费的平均时间", new DecimalFormat("#,###.00").format(stats.averageLoadPenalty()));
        map.put("stats", stats.toString());


        return JSON.toJSONString(map);
    }

    private String getAll(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        com.github.benmanes.caffeine.cache.Cache c = (com.github.benmanes.caffeine.cache.Cache) cache.getNativeCache();
        return JSON.toJSONString(c.asMap());
    }

}
