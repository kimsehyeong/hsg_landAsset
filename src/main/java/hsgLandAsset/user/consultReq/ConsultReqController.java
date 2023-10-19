package hsgLandAsset.user.consultReq;

import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hsgLandAsset.admin.message.service.AdminMessageService;
import hsgLandAsset.admin.minwon.service.AdminMinwonService;
import hsgLandAsset.admin.minwon.service.EminwonService;
import hsgLandAsset.admin.vo.ConsultVO;
import hsgLandAsset.admin.vo.MessageVO;
import hsgLandAsset.admin.vo.MinwonVO;
import hsgLandAsset.user.consultReq.service.ConsultReqService;

@Controller
public class ConsultReqController {

	@Autowired
	ConsultReqService service;
	
	@Autowired
	AdminMessageService messageService;
	
	@Autowired
	AdminMinwonService adminMinwonService;
	
	@RequestMapping("/minwon/{registerNoAES}/consultReq")
	public String frontIndex(@PathVariable String registerNoAES ,Model model) throws Exception {
		
		byte[] decodedBytes = Base64.getUrlDecoder().decode(registerNoAES);
		String registerNo = new String(decodedBytes, "UTF-8");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("register_no", registerNo);
		
		Map<String, Object> minwon = adminMinwonService.selectOneMinwon(param);
		model.addAttribute("receiver", minwon.get("mw_aplct_hpno")); //202142600580000239은 minwon List에 없다
		
		model.addAttribute("consultList", service.selectListConsultReq(registerNo));
    	model.addAttribute("registerNoAES", registerNoAES);
    	model.addAttribute("registerNo", registerNo);

		return "minwon/consultReq.minwon";
	}
	
	@RequestMapping("/minwon/consultReq/update/form")
	public String consultUpdateForm(ConsultVO consultVO,Model model) throws Exception {
		model.addAttribute("consultUpdateOne", service.consultSelectOne(consultVO));

		
		return "minwon/consultUpdateForm";
	}
	
	@ResponseBody
	@RequestMapping("/minwon/consultReq/save")
	public Map<String, Object> consultSave(ConsultVO consultVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap = service.insertConsultReq(consultVO);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", e.toString());

		}
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/minwon/consultReq/update")
	public Map<String, Object> consultupdate(ConsultVO consultVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap = service.updateConsultInfo(consultVO);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", e.toString());

		}
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/minwon/consultReq/delete")
	public Map<String, Object> consultDelete(ConsultVO consultVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap = service.deleteConsultReq(consultVO);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", e.toString());

		}
		return resultMap;
	}
}
