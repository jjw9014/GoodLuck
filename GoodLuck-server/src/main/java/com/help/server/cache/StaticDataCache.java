package com.help.server.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 静态数据缓存
 */
public class StaticDataCache {

    /**
     * 身份标识集合
     */
    public final static Map<String,String> IDENTITY_TYPE_MAP = new LinkedHashMap<String,String>();

    /**
     * 身份标识集合-无黑名单
     */
    public final static Map<String,String> IDENTITY_TYPE_NO_BLACK_MAP = new LinkedHashMap<String,String>();
}
