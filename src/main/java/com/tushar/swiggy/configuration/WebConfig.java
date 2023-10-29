package com.tushar.swiggy.configuration;

import com.tushar.swiggy.springSecurityBasics.AuthenticationInterceptor;
import com.tushar.swiggy.urlShortner.interceptor.ShortUrlCreatorInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    ShortUrlCreatorInterceptor shortUrlCreatorInterceptor;

    @Autowired
    AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(shortUrlCreatorInterceptor).addPathPatterns("/validate/shortenUrl");
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/authenticate/signup");
    }

}
