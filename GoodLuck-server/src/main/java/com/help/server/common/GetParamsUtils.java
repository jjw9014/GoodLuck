package com.help.server.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class GetParamsUtils {
    private static final Logger log = LoggerFactory.getLogger(GetParamsUtils.class);

    public static String jsonReq(HttpServletRequest request) {
        BufferedReader br;
        StringBuilder sb = null;
        String reqBody = null;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line = null;
            sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            if (sb.length() < 1) return "";
            reqBody = URLDecoder.decode(sb.toString(), "UTF-8");
            reqBody = reqBody.substring(reqBody.indexOf("{"));
            return reqBody;
        } catch (IOException e) {
            log.error("获取json参数错误！{}", e.getMessage());
            return "";
        }
    }

    public static Map<String, Object> getParamsMap(HttpServletRequest request) {
        Map<String, Object> paramsMap = new HashMap<>();
        String json = jsonReq(request);
        if (StringUtils.isNotBlank(json)) {
            return JSONObject.parseObject(json, Map.class);
        }
        return paramsMap;
    }

    public static Map<String, Object> getParamsMap() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = sra.getRequest();
            String json = jsonReq(request);
            if (StringUtils.isNotBlank(json)) {
                return JSONObject.parseObject(json, Map.class);
            }
        } catch (Exception e) {
            log.error("json参数转换错误！{}", e.getMessage());
        }
        return paramsMap;
    }

    public static Object getValue(String key) {
        return getParamsMap().get(key);
    }

    public static Object getValueIfNull(Object value, Object requestParam) {
        return (value == null) ? requestParam : value;
    }

    public static Object getValueIfNull(Object value, Object requestParam, Object defaultValue) {
        return (value == null) ? (requestParam==null?defaultValue:requestParam) : value;
    }

    public static <T> T getParamsObject(Class clazz) {
        T t = null;
        try {
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = sra.getRequest();
            String json = jsonReq(request);
            if ("{}".equals(json) || StringUtils.isBlank(json)) {
                return null;
            }
            if (StringUtils.isNotBlank(json)) {
                return (T)JSONObject.parseObject(json, clazz);
            }
        } catch (Exception e) {
            log.error("json参数转换错误！{}", e.getMessage());
        }

        return t;
    }

}
