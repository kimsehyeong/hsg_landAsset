package hsgLandAsset.user.docDisplay;

import java.util.Map;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hsgLandAsset.admin.minwon.service.AdminMinwonService;
import hsgLandAsset.admin.minwon.service.EminwonService;
import hsgLandAsset.admin.vo.MinwonVO;

@Controller
public class DocDisplayController {

	@Autowired
	EminwonService eminwonService;
	
	@Autowired
	AdminMinwonService adminMinwonService;
	
	
	
	@RequestMapping("/minwon/{registerNoAES}/docDisplay")
	public String frontIndex(@PathVariable String registerNoAES,Model model) throws Exception {
		byte[] decodedBytes = Base64.getUrlDecoder().decode(registerNoAES);
		String registerNo = new String(decodedBytes, "UTF-8");

		
		Map<String, String> minwon = eminwonService.getMinwon(registerNo);
		model.addAttribute("minwon", minwon);
		
		MinwonVO minwonVO = new MinwonVO();
		minwonVO.setRegisterNo(registerNo);
    	model.addAttribute("registerNoAES", registerNoAES);
    	model.addAttribute("registerNo", registerNo);
		model.addAttribute("file", adminMinwonService.minwonFileList(minwonVO));
		model.addAttribute("location", adminMinwonService.selectLandLocation(minwonVO));
		
		return "minwon/docDisplay.minwon";
	}
}
