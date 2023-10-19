package hsgLandAsset.admin.login.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hsgLandAsset.admin.vo.UserVO;
import hsgLandAsset.util.SHAUtil;
import hsgLandAsset.util.SessionUtil;

@Service
public class LoginService {

	@Autowired
	LoginMapper loginMapper;
	
	public Map<String, Object> loginSelect(UserVO vo) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		UserVO userVO = loginMapper.loginSelect(vo);
		try {
			if (userVO == null) {
				resultMap.put("result", false); 
				resultMap.put("msg", "존재하지 않는 ID 입니다.");
			}else {
				System.out.println("process");
				if (!userVO.getPwd().equals(SHAUtil.getSHA512(vo.getPwd()))) {
					System.out.println("패스워드 불일치");
					resultMap.put("result", false); 
					resultMap.put("msg", "패스워드가 일치하지 않습니다. ");
				}else {
					System.out.println("로그인 성공");
					SessionUtil.setUser(userVO);
					
					resultMap.put("result", true); //성공
					resultMap.put("redirect", "/admin/minwon/list");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		return resultMap;
	}
	
	
	
}
