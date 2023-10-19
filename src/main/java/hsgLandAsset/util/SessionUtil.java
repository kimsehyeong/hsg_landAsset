package hsgLandAsset.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import hsgLandAsset.admin.vo.UserVO;

public class SessionUtil {
	
	final String sessionName = "currentUser";

	
	//세션 등록
	public static void setUser(UserVO userVO) {
		userVO.setPwd("");
		RequestContextHolder.getRequestAttributes().setAttribute("currentUser", userVO, RequestAttributes.SCOPE_SESSION);
		
		//session timeout
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpSession session = servletRequestAttributes.getRequest().getSession(true);
		session.setMaxInactiveInterval(60 * 60 * 12); // session 12시간
	}
	
	//세션 제거
	public static void removeUser() {
		RequestContextHolder.getRequestAttributes().removeAttribute("currentUser", RequestAttributes.SCOPE_SESSION);
	}
	
	//세션 정보
	public static UserVO getCurrentUser() {
		return (UserVO)RequestContextHolder.getRequestAttributes().getAttribute("currentUser", RequestAttributes.SCOPE_SESSION);
	}
	
	public static String getId() {
		return getCurrentUser() == null? "" : getCurrentUser().getId();
	}
	
	
	//로그인 여부
	public static Boolean isLogin() {
		if (RequestContextHolder.getRequestAttributes() == null) {
			return false;
		} else {
			if (getCurrentUser() == null) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	
}
