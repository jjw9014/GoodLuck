package com.help.server.service;

import java.util.Map;

public interface BaseDicService {

    /**
     * 缓存身份标识集合
     */
    void cacheIndentityTypes();

    /**
     * 获取身份标识集合
     */
    Map<String,String> getIndentityTypes();

    /**
     * 获取身份标识集合-无黑名单
     */
    Map<String,String> getIndentityNoBlackTypes();
}
