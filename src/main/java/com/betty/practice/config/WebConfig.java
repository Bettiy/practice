package com.betty.practice.config;

import com.betty.practice.interceptor.BrushProofInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
}
