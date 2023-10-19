package hsgLandAsset.user.myProcess;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hsgLandAsset.admin.minwon.service.AdminMinwonService;
import hsgLandAsset.admin.minwon.service.EminwonService;
import hsgLandAsset.admin.vo.MinwonVO;

@Controller
public class myProcessController {

	@Autowired
	EminwonService eminwonService;
	@Autowired
	AdminMinwonService adminMinwonService;
	
	
	
	@RequestMapping("/minwon/{registerNoAES}/myProcess")
	public String frontIndex(@PathVariable String registerNoAES, Model model) throws Exception {
		
		byte[] decodedBytes = Base64.getUrlDecoder().decode(registerNoAES);
		String registerNo = new String(decodedBytes, "UTF-8");
		
		Map<String, String> minwon = eminwonService.getMinwon(registerNo);
		String planDay = minwon.get("deal_plan_ymd");
		String realDoneday = minwon.get("real_deal_ymd");
		String dpp_nm = minwon.get("dpp_nm");
		String deal_nm = minwon.get("deal_nm");
		
		System.out.println(minwon);
		
		if(deal_nm.equals("해결")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate planDate = LocalDate.parse(planDay, formatter);
			LocalDate endDate = LocalDate.parse(realDoneday, formatter);

			long daysBetween = ChronoUnit.DAYS.between(endDate, planDate);
			Map<String, Object> map = new HashMap<String, Object>();
				map.put("step_end_td", String.valueOf(daysBetween));
				map.put("dpp_nm", dpp_nm);

				model.addAttribute("result", map);
		}
		

		
		
		MinwonVO minwonVO = new MinwonVO();
		minwonVO.setRegisterNo(registerNo);
		
		model.addAttribute("minwon", minwon);
		model.addAttribute("location", adminMinwonService.selectLandLocation(minwonVO));
    	model.addAttribute("registerNo", registerNoAES);

		return "minwon/myProcess.minwon";
	}
	@RequestMapping("/minwon/{registerNoAES}/receipt")
	public String consultReceipt(@PathVariable String registerNoAES, Model model) throws Exception {
		
		byte[] decodedBytes = Base64.getUrlDecoder().decode(registerNoAES);
		String registerNo = new String(decodedBytes, "UTF-8");

		
		Map<String, String> minwon = eminwonService.getMinwon(registerNo);

		model.addAttribute("minwon", minwon);
		model.addAttribute("registerNoAES", registerNoAES);
		return "minwon/consultReceipt.minwon";
	}
	

}
