package hsgLandAsset.admin.survey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import hsgLandAsset.admin.vo.SurveyVO;
import hsgLandAsset.user.consultReq.service.SurveyService;

@Controller
public class AdminSurveyController {

	@Autowired
	SurveyService surveyService;
	
	@RequestMapping("/admin/survey/list")
	public String survey(Model model,SurveyVO surveyVO) throws Exception {
		model.addAttribute("list", surveyService.selectListSurvey(surveyVO));
		model.addAttribute("pageVO", surveyVO.getPageVO());

		return "/survey/surveyList.admin";
	}
	@ResponseBody
	@RequestMapping("/admin/survey/getlist")
	public List<SurveyVO> getlistSurvey(Model model,SurveyVO surveyVO) throws Exception {
		return surveyService.selectListSurvey(surveyVO);
	}
	
	@RequestMapping("/admin/survey/view")
	public String surveyView(SurveyVO surveyVO,Model model) throws Exception {
		String registerNo = surveyVO.getRegisterNo();
		SurveyVO vo = surveyService.selectSurvey(registerNo);
		model.addAttribute("survey", vo);
		return "admin/survey/surveyView";
	}
	
	@RequestMapping(value="/admin/survey/excel", produces = "application/vnd.ms-excel")
	public ModelAndView applyListExcel(Model model, SurveyVO surveyVO) throws Exception {
    	ModelAndView mav = new ModelAndView("SurveyListExcel");
    	mav.addObject("surveyVO", surveyVO);
		return mav;
	}
}
