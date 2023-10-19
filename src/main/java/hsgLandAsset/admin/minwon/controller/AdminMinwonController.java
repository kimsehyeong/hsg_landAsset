
package hsgLandAsset.admin.minwon.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Base64;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import hsgLandAsset.admin.message.service.AdminMessageService;
import hsgLandAsset.admin.minwon.service.AdminMinwonService;
import hsgLandAsset.admin.minwon.service.EminwonService;
import hsgLandAsset.admin.vo.ConsultVO;
import hsgLandAsset.admin.vo.FileVO;
import hsgLandAsset.admin.vo.LandLocationVO;
import hsgLandAsset.admin.vo.MinwonVO;
import hsgLandAsset.user.consultReq.service.ConsultReqService;
import hsgLandAsset.util.BaseUtil;
import hsgLandAsset.util.KakaoUtil;
import hsgLandAsset.util.SMSUtil;
import hsgLandAsset.util.SessionUtil;




@Controller
public class AdminMinwonController {

	@Autowired
	AdminMinwonService service;
	
	@Autowired
	EminwonService eminwonService;
	
	@Autowired
	ConsultReqService consultReqService;
	
	@Autowired
	AdminMessageService messageService;
	
	
	
	@RequestMapping("/")
	public String mainBasic() throws Exception{
		return "redirect:/admin/minwon/list";
	}
	
	@RequestMapping("/admin/minwon/list")
	public String main(Model model, MinwonVO minwonVO) throws Exception {
		
		model.addAttribute("list", eminwonService.getMinwonList(minwonVO));
		model.addAttribute("pageVO", minwonVO.getPageVO());
		
		return "/minwon/minwonList.admin";
	}
	
	
	
	
	
	
	
	@RequestMapping("/admin/minwon/view")
	public String minwonView(Model model, MinwonVO minwonVO) throws Exception {
		String registerNo = minwonVO.getRegisterNo();
		String in_mw_aplr_nm = minwonVO.getIn_mw_aplr_nm();
		String mw_aplct_hpno = minwonVO.getMw_aplct_hpno();
		
		model.addAttribute("in_mw_aplr_nm", in_mw_aplr_nm);
		model.addAttribute("mw_aplct_hpno", mw_aplct_hpno);
		//민원정보
		Map<String, String> minwon = eminwonService.getMinwon(registerNo);
		model.addAttribute("minwon", minwon);
		
		//진행내역
		List<Map<String, String>> progress = eminwonService.getMinwonProgress(registerNo);

		//해결 추가
		if(minwon.get("deal_nm")!=null && minwon.get("deal_nm").equals("해결")) {
			Map<String, String> temp = new HashMap<String, String>();
			temp.put("bpm_step_nm", minwon.get("deal_nm"));
			temp.put("user_name", minwon.get("dpp_nm"));
			temp.put("dept_name", minwon.get("main_deal_dep_nm"));
			temp.put("tel", minwon.get("dpp_nm_tel"));
			temp.put("email", minwon.get("dpp_nm_email"));
			temp.put("step_end_td", minwon.get("real_deal_ymd"));
			progress.add(temp);
		}
		
		for(Map<String, String> item : progress) {
			item.put("register_no", registerNo);
			item.put("template", "progress");
			item.put("msgCount", service.selectProgressMsgCount(item)+"");
		}
		model.addAttribute("consultList", consultReqService.selectListConsultReq(registerNo));
		model.addAttribute("progress", progress);
		model.addAttribute("file", service.minwonFileList(minwonVO));
		model.addAttribute("location", service.selectLandLocation(minwonVO));
		
		
		return "/minwon/minwonView.admin";
	}
	
	
	@RequestMapping("/admin/minwon/fileupload/form")
	public String minwonFileuploadForm() throws Exception {
		return "/admin/minwon/minwonFileuploadForm";
	}
	@RequestMapping("/admin/minwon/consultComment/form")
	public String consultComment(Model model,ConsultVO consultVO) throws Exception {
		String registerNo =consultVO.getRegisterNo();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("register_no", registerNo);
		Map<String, Object> minwon = service.selectOneMinwon(param);
		model.addAttribute("receiver", minwon.get("mw_aplct_hpno")); 
		
		model.addAttribute("consultOne", consultReqService.consultSelectOne(consultVO));
		return "/admin/minwon/consultComment";
	}
	
	
	@ResponseBody
	@RequestMapping("/admin/minwon/comment/save")
	public Map<String, Object> commentSave(ConsultVO consultVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap = consultReqService.updateComments(consultVO);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", e.toString());
		}
		return resultMap;
	}
	
	
	@ResponseBody
	@RequestMapping("/admin/minwon/file/upload")
	public Map<String, Object> minwonFileupload(@RequestParam Map<String, Object> param,MultipartHttpServletRequest req) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			resultMap = service.MinwonfileUpload(param, req);
			resultMap.put("result", true);
			resultMap.put("msg", "업로드 되었습니다");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", e.toString());
		}
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/admin/minwon/file/delete")
	public Map<String, Object> minwonFileRemove(FileVO fileVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			resultMap = service.minwonFiledelete(fileVO);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", e.toString());
		}
		return resultMap;
	}
	
	@RequestMapping("/admin/minwon/file/download")
	public void fileDownLoad(HttpServletResponse response, FileVO fileVO, HttpServletRequest request) throws Exception {
		fileVO.setFilePath("minwon/");
		service.minwonFileDownload(response, fileVO,request);
	}
	
	
	@ResponseBody
	@RequestMapping("/admin/minwon/landLocation")
	public Map<String, Object> saveLandLocation(LandLocationVO vo) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			resultMap = service.insertLandLocation(vo);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", e.toString());
		}
		return resultMap;
	}
	
	
	
	@Transactional
	@ResponseBody
	@RequestMapping("/admin/minwon/sendMessageList")
	public Map<String, Object> sendMessageList(@RequestBody List<Map<String, Object>> params) throws Exception {
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		
		
		try {
			for (Map<String, Object> map : params) {
				String registerNo = BaseUtil.getString(map.get("registerNo"));
				String receiverName = BaseUtil.getString(map.get("receiverName"));
				String receiverPhone = BaseUtil.getString(map.get("receiverPhone"));
				String template = BaseUtil.getString(map.get("template"));
				
				Map<String, String> getMinwon = eminwonService.getMinwon(registerNo);
				String manager = getMinwon.get("dpp_nm");
				String managerNumber = getMinwon.get("dpp_nm_tel");
				String real_deal_ymd = getMinwon.get("real_deal_ymd");
				
		    	//발송일
		        LocalDate currentDate = LocalDate.now();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		        String formattedDate = real_deal_ymd.format(formatter);
				
		        
		    	// 인코딩
		    	byte[] encodedBytes = Base64.getUrlEncoder().encode(registerNo.getBytes("UTF-8"));
		    	String registerNoAES = new String(encodedBytes, "UTF-8");

				Map<String, Object> params1 = new HashMap<String, Object>(); //메세지 발송내역
				params1.put("register_no", registerNo);
				params1.put("status", "해결");
				params1.put("template", template);
				params1.put("refer", map.get("refer"));
				params1.put("receiver", receiverPhone);
				params1.put("sender", SessionUtil.getId());
				
				
				Map<String, Object> body = new HashMap<String, Object>();
				body.put("receiver_1", receiverPhone);
				body.put("subject_1", "테스트");
				
				
				JSONObject button = new JSONObject();
				JSONArray buttons = new JSONArray();
				
				JSONObject button1 = new JSONObject();
				JSONObject button2 = new JSONObject();
				JSONObject button3 = new JSONObject();

		        String msg = "안녕하세요 " + receiverName + "님~\n";
				msg += "\n";
				
	    		if(template.equals("민원해결(등기촉탁)")) {
	    			body.put("tpl_code", "TN_5635");
	    			
	    			msg += receiverName + "께서 신청하신 토지이동에 따른 토지표시변경등기촉탁을 " + real_deal_ymd + "에 완료하였습니다.";
					msg += "\n";
					msg += "\n이에 등기완료통지서, 지적정리결과통보서를 다음과 같이 보내드리오니 참고하시기 바랍니다.";
					msg += "\n";
					msg += "\n기타 궁금하신 점이 있으시면 언제든 연락주시면 소통하겠습니다. 감사합니다. ^^";
					msg += "\n";
					
					button1.put("name", "만족도 조사");
					button1.put("linkType", "WL");
					button1.put("linkTypeName", "웹링크");
					button1.put("linkM", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/receipt");
					button1.put("linkP", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/receipt");
					
					
					button2.put("name", "내 민원 조회");
					button2.put("linkType", "WL");
					button2.put("linkTypeName", "웹링크");
					button2.put("linkMo", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/myProcess");
					button2.put("linkPc", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/myProcess");
					
					button3.put("name", "상담신청");
					button3.put("linkType", "WL");
					button3.put("linkTypeName", "웹링크");
					button3.put("linkMo", "http://152.99.149.15/8888/minwon/"+registerNoAES+"/consultReq");
					button3.put("linkPc", "http://152.99.149.15/:8888/minwon/"+registerNoAES+"/consultReq");
					
					
					buttons.put(button1);
					buttons.put(button2);
					buttons.put(button3);
					button.put("button", buttons);
					
	    		}else if(template.equals("민원해결(개발행위허가-원스톱건)")) {
	    			body.put("tpl_code", "TN_5202");
	    			msg += receiverName+"께서 신청하신 "+template+" 신청건이 승인되었습니다.";
					msg += "\n";
	    			msg += "\n원스톱 처리건으로 토지이동(분할)접수하여 지적공부정리 및 토지분할 등기촉탁예정이오니(3~5일 소요)재산권 행사에 참고하시기 바랍니다.";
					msg += "\n";
					msg += "\n기타 궁금하신 점이 있으시면 언제든 연락주시면 소통하겠습니다. 감사합니다. ^^";
					msg += "\n";
	    			
					button1.put("name", "만족도 조사");
					button1.put("linkType", "WL");
					button1.put("linkTypeName", "웹링크");
					button1.put("linkM", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/receipt");
					button1.put("linkP", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/receipt");
					
					
					button2.put("name", "내 민원 조회");
					button2.put("linkType", "WL");
					button2.put("linkTypeName", "웹링크");
					button2.put("linkMo", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/myProcess");
					button2.put("linkPc", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/myProcess");
					
					button3.put("name", "상담신청");
					button3.put("linkType", "WL");
					button3.put("linkTypeName", "웹링크");
					button3.put("linkMo", "http://152.99.149.15/8888/minwon/"+registerNoAES+"/consultReq");
					button3.put("linkPc", "http://152.99.149.15/:8888/minwon/"+registerNoAES+"/consultReq");
					
					
					buttons.put(button1);
					buttons.put(button2);
					buttons.put(button3);
					button.put("button", buttons);
	    		}
	    		
				msg += "\n-횡성군청 토지재산과 "+manager+" "+managerNumber+"-";
				body.put("message_1", msg);
				body.put("button_1", button);
				
				KakaoUtil kakaoUtil = new KakaoUtil();
				Map<String, Object> result_code =  kakaoUtil.send(body);
				
				
				params1.put("result_code",result_code.get("code"));
				params1.put("content",msg);
				params1.put("response",result_code.get("message").toString());
				
				if(result_code.get("code").equals(0)) {
					resultMap.put("result", true); // 성공
					resultMap.put("msg", "카톡이 발송되었습니다.");
				}else {
					resultMap.put("result", false); // 실패
					resultMap.put("msg", "실패하였습니다.");
				
				}

				messageService.insertSendMessageLog(params1);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return resultMap;

	}
	

	
	
	@Transactional
	@ResponseBody
	@RequestMapping("/admin/minwon/sendMessage")
	public Map<String, Object> sendMessage(@RequestParam Map<String, String> map) throws Exception{
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		System.out.println(map);
		
		
		String receiver = map.get("receiverNumber");
		String registerNo = map.get("registerNo");
    	String template = map.get("template");
    	String receiverName = map.get("receiverName");
    	String status = map.get("status");
    	
    	//발송일
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        
        //담당자 , 담당자번호
    	String manager = map.get("manager");
    	String managerNumber = map.get("managerNumber");

		
    	// 인코딩
    	byte[] encodedBytes = Base64.getUrlEncoder().encode(map.get("registerNo").getBytes("UTF-8"));
    	String registerNoAES = new String(encodedBytes, "UTF-8");

    	
		if(receiver.equals("")) {
			resultMap.put("result", false); // 실패
			resultMap.put("msg", "민원인 전화번호가 없습니다.");
			return resultMap;
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("register_no", registerNo);
		params.put("status", status);
		params.put("template", template);
		params.put("refer", map.get("refer"));
		params.put("receiver", receiver);
		params.put("sender", SessionUtil.getId());

		
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("receiver_1", receiver);
		body.put("subject_1", "테스트");


		JSONObject button = new JSONObject();
		JSONArray buttons = new JSONArray();
		
		JSONObject button1 = new JSONObject();
		JSONObject button2 = new JSONObject();
		JSONObject button3 = new JSONObject();

        String msg = "안녕하세요 " + receiverName + "님~\n";
		msg += "\n";

		try {
			
    		if(template.equals("민원해결(등기촉탁)")) {
    			body.put("tpl_code", "TN_5635");
    			
    			msg += receiverName + "께서 신청하신 토지이동에 따른 토지표시변경등기촉탁을 " + formattedDate + "에 완료하였습니다.";
				msg += "\n";
				msg += "\n이에 등기완료통지서, 지적정리결과통보서를 다음과 같이 보내드리오니 참고하시기 바랍니다.";
				msg += "\n";
				msg += "\n기타 궁금하신 점이 있으시면 언제든 연락주시면 소통하겠습니다. 감사합니다. ^^";
				msg += "\n";
				
				button1.put("name", "만족도 조사");
				button1.put("linkType", "WL");
				button1.put("linkTypeName", "웹링크");
				button1.put("linkM", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/receipt");
				button1.put("linkP", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/receipt");
				
				
				button2.put("name", "내 민원 조회");
				button2.put("linkType", "WL");
				button2.put("linkTypeName", "웹링크");
				button2.put("linkMo", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/myProcess");
				button2.put("linkPc", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/myProcess");
				
				button3.put("name", "상담신청");
				button3.put("linkType", "WL");
				button3.put("linkTypeName", "웹링크");
				button3.put("linkMo", "http://152.99.149.15/8888/minwon/"+registerNoAES+"/consultReq");
				button3.put("linkPc", "http://152.99.149.15/:8888/minwon/"+registerNoAES+"/consultReq");
				
				
				buttons.put(button1);
				buttons.put(button2);
				buttons.put(button3);
				button.put("button", buttons);
				
    		}else if(template.equals("민원해결(개발행위허가-원스톱건)")) {
    			body.put("tpl_code", "TN_5202");
    			msg += receiverName+"께서 신청하신 "+template+" 신청건이 승인되었습니다.";
				msg += "\n";
    			msg += "\n원스톱 처리건으로 토지이동(분할)접수하여 지적공부정리 및 토지분할 등기촉탁예정이오니(3~5일 소요)재산권 행사에 참고하시기 바랍니다.";
				msg += "\n";
				msg += "\n기타 궁금하신 점이 있으시면 언제든 연락주시면 소통하겠습니다. 감사합니다. ^^";
				msg += "\n";
				
				button1.put("name", "만족도 조사");
				button1.put("linkType", "WL");
				button1.put("linkTypeName", "웹링크");
				button1.put("linkM", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/receipt");
				button1.put("linkP", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/receipt");
				
				
				button2.put("name", "내 민원 조회");
				button2.put("linkType", "WL");
				button2.put("linkTypeName", "웹링크");
				button2.put("linkMo", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/myProcess");
				button2.put("linkPc", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/myProcess");
				
				button3.put("name", "상담신청");
				button3.put("linkType", "WL");
				button3.put("linkTypeName", "웹링크");
				button3.put("linkMo", "http://152.99.149.15/8888/minwon/"+registerNoAES+"/consultReq");
				button3.put("linkPc", "http://152.99.149.15/:8888/minwon/"+registerNoAES+"/consultReq");
				
				
				buttons.put(button1);
				buttons.put(button2);
				buttons.put(button3);
				button.put("button", buttons);
    			
    		}
			
			if(status.equals("접수확인")) {
				body.put("tpl_code", "TN_5154");
				msg += receiverName+"님께서 신청하신 "+template+"이 접수되었습니다.";
				msg += "\n처리기한은 2023-12-31까지로 기한 내 신속히 처리하도록 하겠습니다.";
				msg += "\n감사합니다. ^^";
				msg += "\n";
				
				button1.put("name", "내 민원 조회");
				button1.put("linkType", "WL");
				button1.put("linkTypeName", "웹링크");
				button1.put("linkMo", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/myProcess");
				button1.put("linkPc", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/myProcess");
				
				
				button2.put("name", "상담신청");
				button2.put("linkType", "WL");
				button2.put("linkTypeName", "웹링크");
				button2.put("linkMo", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/consultReq");
				button2.put("linkPc", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/consultReq");
				
				
				buttons.put(button1);
				buttons.put(button2);
				button.put("button", buttons);
				
			}
	    	if(status.equals("해결")) {
	    		

	    		if (template.equals("토지(임야)분할신청") || template.equals("토지(임야)합병신청") || 
	    				template.equals("지목변경") || template.equals("등록전환신청")) {
	    			body.put("tpl_code", "TN_5205");

	    			msg += receiverName+"께서 신청하신 토지이동신청 "+template+"의 지적공부정리가 완료되었습니다.";
	    			msg += "\n토지표시변경에 대한 등기촉탁은 1~2일내 처리될 예정이오니 재산권 행사에 참고하시기 바랍니다.";
					msg += "\n";
					msg += "\n기타 궁금하신 점이 있으시면 언제든 연락주시면 소통하겠습니다. 감사합니다. ^^";
					msg += "\n";

	    		}
	    		else if(template.equals("개발행위허가(토지형질변경, 토석채취, 공작물설치, 토지분할, 물건적치)")) {
	    			body.put("tpl_code", "TN_5204");
	    			
	    			msg += receiverName+"께서 신청하신 "+template+" 신청건이 "+formattedDate+"에 승인되었습니다.";
					msg += "\n";
					msg += "이에 후속절차(분할측량, 공부정리 신청 등)를 진행하시기 바랍니다.";
					msg += "\n";
					msg += "\n기타 궁금하신 점이 있으시면 언제든 연락주시면 소통하겠습니다. 감사합니다. ^^";
					msg += "\n";

	    		}
	    		else { //일반민원
	    			body.put("tpl_code", "TN_5155");
	    			msg += receiverName+"님께서 신청하신 "+template+"이 "+formattedDate+"에 처리되었습니다.";
					msg += "\n기타 궁금하신 점이 있으시면 언제든 연락주시면 소통하겠습니다. 감사합니다. ^^";
					msg += "\n";

	    		}
				button1.put("name", "만족도 조사");
				button1.put("linkType", "WL");
				button1.put("linkTypeName", "웹링크");
				button1.put("linkM", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/receipt");
				button1.put("linkP", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/receipt");
				
				
				button2.put("name", "내 민원 조회");
				button2.put("linkType", "WL");
				button2.put("linkTypeName", "웹링크");
				button2.put("linkMo", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/myProcess");
				button2.put("linkPc", "http://152.99.149.15:8888/minwon/"+registerNoAES+"/myProcess");
				
				button3.put("name", "상담신청");
				button3.put("linkType", "WL");
				button3.put("linkTypeName", "웹링크");
				button3.put("linkMo", "http://152.99.149.15/8888/minwon/"+registerNoAES+"/consultReq");
				button3.put("linkPc", "http://152.99.149.15/:8888/minwon/"+registerNoAES+"/consultReq");
				
				
				buttons.put(button1);
				buttons.put(button2);
				buttons.put(button3);
				button.put("button", buttons);
	    		
	    	}
	    	
	    	
	    	
			msg += "\n-횡성군청 토지재산과 "+manager+" "+managerNumber+"-";
			body.put("message_1", msg);
			body.put("button_1", button);


			KakaoUtil kakaoUtil = new KakaoUtil();
			Map<String, Object> result_code =  kakaoUtil.send(body);
			
			
			params.put("result_code",result_code.get("code"));
			params.put("content",msg);
			params.put("response",result_code.get("message").toString());
			
			if(result_code.get("code").equals(0)) {
				resultMap.put("result", true); // 성공
				resultMap.put("msg", "카톡이 발송되었습니다.");
			}else {
				resultMap.put("result", false); // 실패
				resultMap.put("msg", "실패하였습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			messageService.insertSendMessageLog(params);
		}

		return resultMap;
	}
}
