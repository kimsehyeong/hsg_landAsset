package hsgLandAsset.admin.message.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import hsgLandAsset.admin.message.service.AdminMessageService;
import hsgLandAsset.admin.vo.MessageVO;

@Controller
public class AdminMessageController {

	@Autowired
	AdminMessageService messageService;
	
	@RequestMapping("/admin/message/list")
	public String message(Model model,MessageVO messageVO) throws Exception {
		List<MessageVO> list = messageService.selectSendMessageList(messageVO);
		model.addAttribute("messageList", list);
		model.addAttribute("pageVO", messageVO.getPageVO());
		
		return "/message/messageList.admin";
	}
}
