package hsgLandAsset.user.consultReq;

import java.util.HashMap;
import java.util.Map;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hsgLandAsset.admin.vo.SurveyVO;
import hsgLandAsset.user.consultReq.service.SurveyService;

@Controller
public class SurveyController {

	@Autowired
	SurveyService surveyService;
	
	@RequestMapping("/minwon/{registerNoAES}/survey")
	public String minwonSurvey(@PathVariable String registerNoAES, Model model) throws Exception {
		
		byte[] decodedBytes = Base64.getUrlDecoder().decode(registerNoAES);
		String registerNo = new String(decodedBytes, "UTF-8");
		SurveyVO vo = surveyService.selectSurvey(registerNo);
    	model.addAttribute("registerNoAES", registerNoAES);
    	model.addAttribute("registerNo", registerNo);
		model.addAttribute("survey", vo);
		return "minwon/surveyForm";
	}
	
	@ResponseBody
	@RequestMapping("/minwon/survey/save")
	public Map<String, Object> saveSurvey(SurveyVO surveyVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			resultMap = surveyService.insertSurvey(surveyVO);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", e.toString());
		}
		return resultMap;
	}
}
