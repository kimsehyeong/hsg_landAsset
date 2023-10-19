package hsgLandAsset.admin.department.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DepartmentController {

	@RequestMapping("/admin/department/list")
	public String department() throws Exception {
		return "/department/departmentList.admin";
	}
	
	@RequestMapping("/admin/department/agreement")
	public String depAgreement() throws Exception {
		return "/department/depAgreement.admin";
	}
	// 테스트입니다.
	@RequestMapping("/admin/department/manager")
	public String depManager() throws Exception {
		return "/department/depManager.admin";
	}
}
