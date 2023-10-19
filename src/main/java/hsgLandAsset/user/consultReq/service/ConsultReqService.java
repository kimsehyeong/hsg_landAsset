package hsgLandAsset.user.consultReq.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hsgLandAsset.admin.minwon.service.AdminMinwonService;
import hsgLandAsset.admin.user.service.UserService;
import hsgLandAsset.admin.vo.ConsultVO;
import hsgLandAsset.admin.vo.PageVO;
import hsgLandAsset.admin.vo.UserVO;
import hsgLandAsset.util.BaseUtil;
import hsgLandAsset.util.KakaoUtil;
import hsgLandAsset.util.SessionUtil;

@Service
public class ConsultReqService {

	@Autowired
	ConsultReqMapper mapper;
	
	@Autowired
	UserService userService;
	@Autowired
	AdminMinwonService adminMinwonService;
	
	@Transactional
	public Map<String, Object> insertConsultReq(ConsultVO consultVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int result;
		result = mapper.insertConsultReq(consultVO);
		
		if(result > 0) {
			resultMap.put("result", true);
			resultMap.put("msg", "저장되었습니다");
			
			
			
			
			//관리자에게 카톡
			UserVO admin = userService.getUserById("admin");
			String receiver = admin.getTel().replaceAll("-", "");
			String registerNo = consultVO.getRegisterNo();
			String message = "접수번호 "+registerNo+" 민원에 대한 상담이 등록되었습니다.";
			
			Map<String, Object> body = new HashMap<String, Object>();
			body.put("receiver_1", "010-5002-8392");
			body.put("subject_1", "테스트");
			body.put("message_1", message);
			body.put("tpl_code", "TN_5584");

			KakaoUtil kakaoUtil = new KakaoUtil();
			kakaoUtil.send(body);
			
			
			
			
			
			
//			KakaoUtil kakaoUtil = new KakaoUtil();
//			String token = kakaoUtil.getToken();
//			JSONObject jsonObject = kakaoUtil.buildMessageForAdmin(consultVO.getRegisterNo());
//			jsonObject.put("recipient", receiver);
//			
//			CloseableHttpClient httpClient = HttpClients.createDefault();
//			HttpPost httpPost = new HttpPost("https://kakaoapi.aligo.in/akv10/alimtalk/send/"); 
//			httpPost.setEntity(new StringEntity(String.valueOf(jsonObject), "UTF-8")); 
//			httpPost.addHeader("Accept", "application/json");
//			httpPost.addHeader("Content-type", "application/json");
//			httpPost.addHeader("token", token);
//			
//			CloseableHttpResponse response = httpClient.execute(httpPost); 
//			System.out.println(response);
			
			
			
			
		}else {
			resultMap.put("result", false);
			resultMap.put("msg", "저장 중 오류가 발생했습니다.");
		}

		return resultMap;
	}

	public List<ConsultVO> selectListConsultReq(String registerNo) throws Exception {
		return mapper.selectListConsultReq(registerNo);
	}

	@Transactional
	public Map<String, Object> deleteConsultReq(ConsultVO consultVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int result;
		
		result = mapper.deleteConsultReq(consultVO);
		
		if(result > 0) {
			resultMap.put("result",true);
			resultMap.put("msg", "상담취소되었습니다");
		}else {
			resultMap.put("result",false);
			resultMap.put("msg", "상담취소 중 오류가 발생했습니다.");
		}

		return resultMap;
	}

	public ConsultVO consultSelectOne(ConsultVO consultVO) throws Exception {
		return mapper.consultSelectOne(consultVO);
	}
	
	@Transactional
	public Map<String, Object> updateComments(ConsultVO consultVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int result;
		consultVO.setCommentUser(SessionUtil.getId());
		result = mapper.updateComments(consultVO);
       resultMap.put("msg", result > 0 ? "답변등록되었습니다." : "답변등록 중 오류가 발생했습니다.");
	    resultMap.put("result", result > 0);	
	    
		Map<String, Object> param = new HashMap<String, Object>();
		System.out.println(consultVO);
	    String receiver = consultVO.getReceiver();
	    String registerNo = consultVO.getRegisterNo();
	    param.put("register_no",registerNo);
	    
	    Map<String, Object> minwonOne = adminMinwonService.selectOneMinwon(param);
	    String receiverName = BaseUtil.getString(minwonOne.get("mw_aplct_nm"));
	    
	    
    	// 인코딩
    	byte[] encodedBytes = Base64.getUrlEncoder().encode(registerNo.getBytes("UTF-8"));
    	String registerNoAES = new String(encodedBytes, "UTF-8");
	   // ============================================================================================ 
		Map<String, Object> body = new HashMap<String, Object>();
//		receiver ="01083095542";
		body.put("receiver_1", receiver);
		body.put("subject_1", "테스트");
		body.put("tpl_code", "TN_5583");

		JSONObject button = new JSONObject();
		JSONArray buttons = new JSONArray();
		JSONObject button1 = new JSONObject();

        String msg = "안녕하세요 " + receiverName + "님~\n";
		msg += "\n";
		msg += receiverName+"님의 상담신청에 답변이 등록되었습니다.";
		msg += "\n오늘하루도 행복이 가득하시길 바랍니다.";
		msg += "\n감사합니다.";
		
		button1.put("name", "답변확인");
		button1.put("linkType", "WL");
		button1.put("linkTypeName", "웹링크");
		button1.put("linkMo", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/consultReq");
		button1.put("linkPc", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/consultReq");
		
		buttons.put(button1);
		button.put("button", buttons);
		
		body.put("message_1", msg);
		body.put("button_1", button);
		KakaoUtil kakaoUtil = new KakaoUtil();
		kakaoUtil.send(body);
		return resultMap;
	}
	@Transactional
	public Map<String, Object> updateConsultInfo(ConsultVO consultVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int result;
		consultVO.setCommentUser(SessionUtil.getId());
		result = mapper.updateConsultInfo(consultVO);
       resultMap.put("msg", result > 0 ? "상담수정되었습니다." : "상담수정 중 오류가 발생했습니다.");
       
	    resultMap.put("result", result > 0);		
		    
		return resultMap;
	}

	public List<ConsultVO> selectListConsultAdmin(ConsultVO vo) throws Exception {
		vo.setPageVO(new PageVO(vo.getListSize(), vo.getPage(), mapper.getConsultCount(vo)));
		return mapper.selectListConsultAdmin(vo);
	}
	
	
}
