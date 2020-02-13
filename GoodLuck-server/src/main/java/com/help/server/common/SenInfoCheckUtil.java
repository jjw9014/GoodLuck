package com.help.server.common;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Slf4j
public class SenInfoCheckUtil {


    /**
     * 图片违规检测
     * @param accessToken
     * @param file
     * @return
     */
    public static Boolean imgFilter(String accessToken, MultipartFile file){
        String contentType = file.getContentType();
        return checkPic(file, accessToken,contentType);
    }

    /**
     * 文本违规检测
     * @param accessToken
     * @param content
     * @return
     */
    public static Boolean cotentFilter(String accessToken, String content){
        return checkContent(accessToken,content);
    }

    /**
     *  恶意图片过滤
     * @param multipartFile
     * @return
     */
    private static Boolean checkPic(MultipartFile multipartFile, String accessToken,String contentType) {
        try {

            CloseableHttpClient httpclient = HttpClients.createDefault();

            CloseableHttpResponse response = null;

            HttpPost request = new HttpPost("https://api.weixin.qq.com/wxa/img_sec_check?access_token=" + accessToken);
            request.addHeader("Content-Type", "application/octet-stream");

            InputStream inputStream = multipartFile.getInputStream();

            byte[] byt = new byte[inputStream.available()];
            inputStream.read(byt);
            request.setEntity(new ByteArrayEntity(byt, ContentType.create(contentType)));

            response = httpclient.execute(request);
            HttpEntity httpEntity = response.getEntity();
            String result = EntityUtils.toString(httpEntity, "UTF-8");// 转成string
            JSONObject jso = JSONObject.parseObject(result);
            return getResult(jso);
        } catch (Exception e) {
            log.error("调用腾讯内容过滤系统出错",e);
            return true;
        }
    }

    private static Boolean checkContent(String accessToken,String content) {
        try {

            CloseableHttpClient httpclient = HttpClients.createDefault();

            CloseableHttpResponse response = null;

            HttpPost request = new HttpPost("https://api.weixin.qq.com/wxa/msg_sec_check?access_token=" + accessToken);
            request.addHeader("Content-Type", "application/json");
            Map<String, String> map = new HashMap<>();
            map.put("content",content);
            String body = JSONObject.toJSONString(map);
            request.setEntity(new StringEntity(body,ContentType.create("text/json", "UTF-8")));
            response = httpclient.execute(request);
            HttpEntity httpEntity = response.getEntity();
            String result = EntityUtils.toString(httpEntity, "UTF-8");// 转成string
            JSONObject jso = JSONObject.parseObject(result);
            return getResult(jso);
        } catch (Exception e) {
            log.error("调用腾讯内容过滤系统出错",e);
            return true;
        }
    }

    private static Boolean  getResult(JSONObject jso){
        Object errcode = jso.get("errcode");
        int errCode = (int) errcode;
        if (errCode == 0) {
            return true;
        } else if (errCode == 87014) {
            log.info("内容违规");
            return false;
        }
        return true;
    }

    /**
     * 组装参数
     * @param request
     * @return
     */
    public static String makeUpParams(HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer("");
        //得到枚举类型的参数名称，参数名称若有重复的只能得到第一个
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paramName = (String) enums.nextElement();
            String paramValue = request.getParameter(paramName);
            buffer.append(paramValue + " ");
        }
        return buffer.toString();
    }

}


