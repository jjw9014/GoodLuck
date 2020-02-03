
package com.help.server.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HttpClientUtil
 * @Description httpclient 工具类
 * @Author LY
 * @Date 2019/8/15 10:31
 * @Version 1.0
 **/
public class HttpClientUtil {

	/**
	 * get请求，参数拼接在地址上
	 * 
	 * @param url 请求地址加参数
	 * @return 响应
	 */
	public static String get(String url) {
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(get);
			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				result = entityToString(entity);
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * get请求,返回JSONObject
	 * @param url
	 * @return
	 */
	public static JSONObject doGetJson(String url){
		String result = get(url);
		return JSONObject.parseObject(result);
	}

	/**
	 * get请求，参数放在map里
	 * 
	 * @param url 请求地址
	 * @param map 参数map
	 * @return 响应
	 */
	public static String getMap(String url, Map<String, String> map) {
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		CloseableHttpResponse response = null;
		try {
			URIBuilder builder = new URIBuilder(url);
			builder.setParameters(pairs);
			HttpGet get = new HttpGet(builder.build());
			response = httpClient.execute(get);
			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				result = entityToString(entity);
			}
			return result;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * 发送post请求，参数用map接收
	 * 
	 * @param url 地址
	 * @param map 参数
	 * @return 返回值
	 */
	public String postMap(String url, Map<String, String> map) {
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		CloseableHttpResponse response = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
			response = httpClient.execute(post);
			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				result = entityToString(entity);
			}
			return result;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	/**
	 * post请求，参数为json字符串
	 * 
	 * @param url 请求地址
	 * @param jsonString json字符串
	 * @return 响应
	 */
	public String postJson(String url, String jsonString) {
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			post.setEntity(new ByteArrayEntity(jsonString.getBytes("UTF-8")));
			response = httpClient.execute(post);
			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				result = entityToString(entity);
			}
			return result;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static String entityToString(HttpEntity entity) throws IOException {
		String result = null;
		if (entity != null) {
			long lenth = entity.getContentLength();
			if (lenth != -1 && lenth < 2048) {
				result = EntityUtils.toString(entity, "UTF-8");
			} else {
				InputStreamReader reader1 = new InputStreamReader(entity.getContent(), "UTF-8");
				CharArrayBuffer buffer = new CharArrayBuffer(2048);
				char[] tmp = new char[1024];
				int l;
				while ((l = reader1.read(tmp)) != -1) {
					buffer.append(tmp, 0, l);
				}
				result = buffer.toString();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String result = get("http://api.map.baidu.com/geocoder/v2/"
				+ "?ak=5E5EE28a7615536d1ffe2ce2a3667859&callback=renderReverse&location=39.983424,116.322987&output=json&pois=1");
		System.out.print(result);

	}

}
