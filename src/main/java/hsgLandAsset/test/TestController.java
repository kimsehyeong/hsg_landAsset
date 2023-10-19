package hsgLandAsset.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hsgLandAsset.admin.minwon.service.EminwonService;
import hsgLandAsset.admin.vo.MinwonVO;

@Controller
public class TestController {

	
	@Autowired
	EminwonService eminwonService;
	
	
	
	@RequestMapping("/test")
	public String test(MinwonVO minwonVO) throws Exception {
		
		minwonVO.setStartDate("2023-06-05");
		minwonVO.setEndDate("2023-06-16");

		eminwonService.getMinwonList(minwonVO);
		return "test";
	}
	
	
	
}
