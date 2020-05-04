package com.example.transation.config;


import com.example.transation.interceptor.AuthSignCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * @Author： xzh
 * @Description：
 * @Date： 2020/1/16 上午10:04
 * @MOdified by:
 **/
@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

    @Autowired
    private AuthSignCheckInterceptor authSignCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludes = new String[]{"/user/login","/user/forget","/user/reg",
                "/page/**",
                "/assets/**", "/images/**", "/vendors/**", "/static/**",
                "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg",
                "/**/*.jpeg", "/**/*.gif", "/**/fonts/*", "/**/*.svg"};
        registry.addInterceptor(authSignCheckInterceptor).addPathPatterns("/**").excludePathPatterns(excludes);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/","classpath:/static/");
    }
}
