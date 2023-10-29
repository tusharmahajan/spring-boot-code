package com.tushar.swiggy.springSecurityBasics;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Base64;

@Component
public class SignUpInterceptor implements HandlerInterceptor {

    @Value("${spring.username}")
    private String appUserName;

    @Value("${spring.password}")
    private String appPassword;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String encodedCredentials = request.getHeader("authorization").split(" ")[1];

        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String decodedString = new String(decodedBytes);

        String username = decodedString.split(":")[0];
        String password = decodedString.split(":")[1];

        if(!appUserName.equals(username) || !appPassword.equals(password)){
            response.sendError(401, "Unauthorized");
            return false;
        }
        return true;
    }
}
