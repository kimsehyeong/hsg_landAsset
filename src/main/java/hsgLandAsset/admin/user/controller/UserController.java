package hsgLandAsset.admin.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hsgLandAsset.admin.user.service.UserService;
import hsgLandAsset.admin.vo.UserVO;

@Controller
public class UserController {

	@Autowired
	UserService userSerivce;
	
	
	@RequestMapping("/admin/user/list")
	public String user(Model model, UserVO vo) throws Exception {
		model.addAttribute("list", userSerivce.selectList(vo));
		model.addAttribute("pageVO", vo.getPageVO());
		return "/user/userList.admin";
	}
	
	
	@RequestMapping("/admin/user/add")
	public String userAdd(Model model,UserVO vo) throws Exception{
		model.addAttribute("item", userSerivce.selectOneUser(vo));
		return "/admin/user/userAdd";
	}
	
	@ResponseBody
	@RequestMapping("/admin/user/insert")
	public Map<String, Object> userInsert(UserVO vo) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			 resultMap = userSerivce.insertUser(vo);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", e.toString());
		}
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/admin/user/delete")
	public Map<String, Object> userDelete(UserVO vo) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			 resultMap = userSerivce.deleteUser(vo);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", e.toString());
		}
		return resultMap;
	}
}
