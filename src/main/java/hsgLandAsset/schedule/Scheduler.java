package hsgLandAsset.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import hsgLandAsset.admin.minwon.service.EminwonService;
import hsgLandAsset.admin.vo.MinwonVO;
import hsgLandAsset.util.KakaoUtil;

@Component
public class Scheduler {
	
	
	@Value("${dev.mode}")
	boolean dev;
	
	@Autowired
	EminwonService minwonService;
	
	
	//민원 조회후 db 저장
	//초 분 시 일 월 요일
	@Scheduled(cron = "0 * 8-19 * * *")
	public void getMinwon() throws Exception{
		
		if(!dev) {
			MinwonVO minwonVO = new MinwonVO();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			
			minwonVO.setEndDate(sdf.format(cal.getTime()));
			
			cal.add(Calendar.DAY_OF_YEAR, -7);
			minwonVO.setStartDate(sdf.format(cal.getTime()));
			
			minwonVO.setTemp(true);
			List<Map<String, String>> list = minwonService.getMinwonList(minwonVO);
			
		}
	}
	
	//카톡 메세지 전송
	//초 분 시 일 월 요일
	@Scheduled(cron = "0 0 9-18 * * *")
	public void sendMessage() throws Exception{
		
		//List<Map<String, String>> list = minwonService.selectMinwonListForSendMessage();
		
		if(!dev) {

			
		}
		
	}
	
	
	//초 분 시 일 월 요일
	//카톡 전송결과 조회
	@Scheduled(cron = "*/10 * 6-20 * * *")
	public void getKakaoResult() throws Exception{
		/*
		if(!dev) {
			KakaoUtil kakaoUtil = new KakaoUtil();
			
			String token = kakaoUtil.getToken();
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet("https://www.biztalk-api.com/v2/kko/getResultPoll"); 
			httpGet.addHeader("Accept", "application/json");
			httpGet.addHeader("Content-type", "application/json");
			httpGet.addHeader("bt-token", token);
			
			CloseableHttpResponse response = httpClient.execute(httpGet); 
			String result = EntityUtils.toString(response.getEntity());
			
			JSONObject r = new JSONObject(result);
			JSONArray ja = r.getJSONArray("response");
			if(ja!=null) {
				for(int i=0; i<ja.length(); i++) {
					JSONObject obj = (JSONObject) ja.get(i); 
					
					Map<String, String> map = new HashMap<String, String>();
					map.put("req_id", obj.getString("msgIdx"));
					map.put("send_result", obj.getString("resultCode"));
					
					minwonService.updateSendMessageResult(map);
				}
			}
			
			if(r.has("pk")) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("pk", r.getString("pk"));
				
				httpClient = HttpClients.createDefault();
				HttpPost httpPost = new HttpPost("https://www.biztalk-api.com/v2/kko/ackResultPoll"); 
				httpPost.setEntity(new StringEntity(String.valueOf(jsonObject), "UTF-8")); 
				httpPost.addHeader("Accept", "application/json");
				httpPost.addHeader("Content-type", "application/json");
				httpPost.addHeader("bt-token", token);
				
				response = httpClient.execute(httpPost); 
				result = EntityUtils.toString(response.getEntity());
				
			}
		}
		*/
	}
	
	
	
	
}
