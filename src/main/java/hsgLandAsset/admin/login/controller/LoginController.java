package hsgLandAsset.admin.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hsgLandAsset.admin.login.service.LoginService;
import hsgLandAsset.admin.vo.UserVO;
import hsgLandAsset.util.SessionUtil;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@GetMapping("/admin/login")
	public String login() {
		if (SessionUtil.isLogin())
			return "redirect:/";
		return "login/login";
	}
	
	@ResponseBody
	@RequestMapping("/admin/login")
		public Map<String, Object> loginProcess(UserVO vo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
		try {
			  resultMap = loginService.loginSelect(vo);
		}catch(Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", e.toString());
		}
		
		return resultMap;
		}
	
	@RequestMapping("/admin/logout")
	private String logout() {
		if (SessionUtil.isLogin())
			SessionUtil.removeUser();
		return "redirect:/admin/login";
	}
	
}
