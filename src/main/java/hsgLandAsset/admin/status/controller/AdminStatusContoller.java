package hsgLandAsset.admin.status.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hsgLandAsset.admin.status.service.AdminStatusService;
import hsgLandAsset.admin.vo.StatusVO;

@Controller
public class AdminStatusContoller {
	@Autowired
	AdminStatusService statusService;

	@RequestMapping("/admin/status/list")
	public String status(Model model,StatusVO vo) throws Exception {
		
		model.addAttribute("statsList",statusService.selectStatsList(vo));
		model.addAttribute("pageVO", vo.getPageVO());

		return "/status/statusList.admin";
	}
	
	@ResponseBody
	@RequestMapping("/admin/status/getList")
	public List<StatusVO> statusList(StatusVO vo) throws Exception {
		return statusService.selectStatsList(vo);
	}
	
	@RequestMapping(value="/admin/status/excel", produces = "application/vnd.ms-excel")
	public ModelAndView applyListExcel(Model model, StatusVO statusVO) throws Exception {
    	ModelAndView mav = new ModelAndView("StatsListExcel");
    	System.out.println(statusVO);
    	mav.addObject("statusVO", statusVO);
		return mav;
	}
}
