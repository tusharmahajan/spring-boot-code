package com.tushar.swiggy.urlShortner.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ShortUrlCreatorInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        String requestWrapperBody = request.getInputStream().toString();
//        System.out.println(requestWrapperBody);
        boolean isValid = false;
//        request.getA
        request.setAttribute("valid", isValid);
        return true;
    }
}

