package hsgLandAsset.admin.consult.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hsgLandAsset.admin.minwon.service.AdminMinwonService;
import hsgLandAsset.admin.minwon.service.EminwonService;
import hsgLandAsset.admin.vo.ConsultVO;
import hsgLandAsset.admin.vo.MinwonVO;
import hsgLandAsset.user.consultReq.service.ConsultReqService;

@Controller
public class AdminConsultController {

	@Autowired
	ConsultReqService consultReqService;
	@Autowired
	EminwonService eminwonService;
	@Autowired
	AdminMinwonService adminMinwonService;//test..
	
	@RequestMapping("/admin/consult/list")
	public String consult(Model model,ConsultVO consultVO) throws Exception {
		model.addAttribute("list", consultReqService.selectListConsultAdmin(consultVO));
		model.addAttribute("pageVO", consultVO.getPageVO()); //text
		
		return "/consult/consultList.admin";
	}
	
	@RequestMapping("/admin/consult/detail")
	public String consultDetail(Model model,MinwonVO minwonVO) throws Exception {
		String registerNo = minwonVO.getRegisterNo();

		//민원정보
		Map<String, String> minwon = eminwonService.getMinwon(registerNo);
		model.addAttribute("minwon", minwon);

		return "/admin/consult/consultDetail";
	}
	
	@RequestMapping("/admin/consult/progress")
	public String consultProgress(Model model,MinwonVO minwonVO) throws Exception {
		String registerNo = minwonVO.getRegisterNo();

		//진행내역
		List<Map<String, String>> progress = eminwonService.getMinwonProgress(registerNo);
		model.addAttribute("progress", progress);

		return "/admin/consult/consultProgress";
	}
	
	@RequestMapping("/admin/consult/comment")
	public String consultComment(Model model,ConsultVO consultVO) throws Exception {

		String registerNo =consultVO.getRegisterNo();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("register_no", registerNo);
		
		Map<String, Object> minwon = adminMinwonService.selectOneMinwon(param);
		model.addAttribute("receiver", minwon.get("mw_aplct_hpno")); 
		model.addAttribute("consult", consultReqService.consultSelectOne(consultVO));
		
		return "/admin/consult/consultComment";
	}
	
	@ResponseBody
	@RequestMapping("/admin/consult/delete")
	public Map<String, Object> consultCommentDelete(Model model,ConsultVO consultVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			resultMap = consultReqService.deleteConsultReq(consultVO);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", e.toString());
		}
		
		return resultMap;
	}

}
