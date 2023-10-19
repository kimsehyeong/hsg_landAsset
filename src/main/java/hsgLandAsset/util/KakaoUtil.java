package hsgLandAsset.util;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class KakaoUtil {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	String senderKey = "c5701ad00e59b346c9900a49a4c4a6ad4697c37e";
	String apiKey = "v3dpdyk8har8pb3646x4yqivy61g5goj"; // API key
	String userid = "hjc1026"; // 알리고 사이트 아이디
	String sender = "010-5002-8392";
    

	public String getToken() throws Exception {
		
		String url = "https://kakaoapi.aligo.in/akv10/token/create/30/s/";
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost http = new HttpPost(url);

		http.setHeader("Accept", "application/json");
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("apikey", apiKey));
		params.add(new BasicNameValuePair("userid", userid));
		http.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		CloseableHttpResponse response = httpClient.execute(http);
		String entity = EntityUtils.toString(response.getEntity());

		JSONObject responseJson = new JSONObject(entity);
		String token = responseJson.getString("token");
		return token;
	}
	
	public Map<String, Object> send(Map<String, Object> body) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String url = "https://kakaoapi.aligo.in/akv10/alimtalk/send/";

//		body.put("receiver_1", "010-8309-5542");
		body.put("receiver_1", "010-5694-2178");
		body.put("senderkey", senderKey);
		body.put("apikey", apiKey);
		body.put("userid", userid);
		body.put("sender", sender);
		body.put("token", getToken());
		
		

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost http = new HttpPost(url);
		http.addHeader("Accept", "application/json");
		http.addHeader("Content-type", "application/x-www-form-urlencoded");
		http.addHeader("token", getToken());

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		body.forEach((key, value) -> {
			params.add(new BasicNameValuePair(key.toString(), value.toString()));
		});
		http.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		CloseableHttpResponse response = httpClient.execute(http);
		String entity = EntityUtils.toString(response.getEntity());
		resultMap = new ObjectMapper().readValue(entity, Map.class);

		System.out.println("==========================================================================================================");
		System.out.println("[" + sdf.format(new Date()) + "]");
		System.out.println("POST : " + url);
		body.forEach((key, value) -> {
			System.out.println(key + " => " + value);
		});
		System.out.println("----------------------------------------------------------------------------------------------------------");
		System.out.println(resultMap.toString());
		System.out.println("==========================================================================================================");

		messageLog(body,resultMap);
		
		
		return resultMap;
	}
	
	
	public Map<String, Object> messageLog(Map<String, Object> body, Map<String, Object> messageLog) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println("body::"+body);
		System.out.println("messageLog::"+messageLog);
		
		if (messageLog.containsKey("info")) {
			Map<String, Object> info = (Map<String, Object>) messageLog.get("info");
			
			if (info != null && info.containsKey("mid")) {
				String mid = String.valueOf(info.get("mid"));
				System.out.println("mid::" + mid);
				body.put("mid", mid);
			}
		}
		body.get("token");
		
		
		String url = "https://kakaoapi.aligo.in/akv10/history/detail/";
		
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost http = new HttpPost(url);
		http.addHeader("Accept", "application/json");
		http.addHeader("Content-type", "application/x-www-form-urlencoded");
		http.addHeader("token", getToken());
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		body.forEach((key, value) -> {
			params.add(new BasicNameValuePair(key.toString(), value.toString()));
		});
		http.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		CloseableHttpResponse response = httpClient.execute(http);
		String entity = EntityUtils.toString(response.getEntity());
		resultMap = new ObjectMapper().readValue(entity, Map.class);
		
		System.out.println("==========================================================================================================");
		System.out.println("[" + sdf.format(new Date()) + "]");
		System.out.println("POST : " + url);
		body.forEach((key, value) -> {
			System.out.println(key + " => " + value);
		});
		System.out.println("----------------------------------------------------------------------------------------------------------");
		System.out.println(resultMap.toString());
		System.out.println("==========================================================================================================");
		return resultMap;

	}
	
	
	
	
	
	
	
	
}
