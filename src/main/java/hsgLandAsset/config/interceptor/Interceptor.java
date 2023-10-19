package hsgLandAsset.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import hsgLandAsset.util.BaseUtil;
import hsgLandAsset.util.SessionUtil;


@Component
public class Interceptor implements HandlerInterceptor{
	
	
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)throws Exception{
		//System.out.println("Interceptor > preHandle");
		if(!SessionUtil.isLogin()){
			if(BaseUtil.isAjax(request)) {
				response.sendError(999); //ajax는 리다이렉트시키지않아야한다
				return false;
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/login");
				return false;
			}
		}		
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//System.out.println("Interceptor > postHandle");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
		// System.out.println("Interceptor > afterCompletion" );
	}
	

}
