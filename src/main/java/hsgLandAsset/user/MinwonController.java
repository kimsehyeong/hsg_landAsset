package hsgLandAsset.user;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MinwonController {
	
	@RequestMapping("/minwon/{registerNoAES}/index")
	public String frontIndex(@PathVariable String registerNoAES,Model model) throws Exception {
		
		String registerNo = new String(Base64.decodeBase64(registerNoAES), "UTF-8");

    	model.addAttribute("registerNo", registerNo);
		return "minwon/myProcess.minwon";
	}
}
