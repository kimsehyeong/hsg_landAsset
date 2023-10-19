package hsgLandAsset.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new Interceptor())
		.excludePathPatterns("/test")
		.excludePathPatterns("/assets/**")
		.excludePathPatterns("/admin/login")
		.excludePathPatterns("/admin/redirect-login")
		.addPathPatterns("/admin/**");
	}

	
}
