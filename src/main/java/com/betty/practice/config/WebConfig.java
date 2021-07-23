package com.betty.practice.config;

import com.betty.practice.interceptor.BrushProofInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Betty
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private BrushProofInterceptor brushProofInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(brushProofInterceptor);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:D:Upload/practice/file/");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 设置允许跨域的路径
		registry.addMapping("/**")
			// 设置允许跨域请求的域名
			.allowedOrigins("*")
			// 是否允许证书 不再默认开启
			.allowCredentials(true)
			// 设置允许的方法
			.allowedMethods("*")
			// 跨域允许时间
			.maxAge(3600);
	}
}
