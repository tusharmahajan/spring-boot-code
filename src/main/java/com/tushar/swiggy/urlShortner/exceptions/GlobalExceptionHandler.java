package com.tushar.swiggy.urlShortner.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorInfo> handleException(HttpServletRequest httpServletRequest, Exception e) {
        System.out.println("I am exception handler");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorInfo(httpServletRequest.getRequestURL().toString(), e.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value())));
    }

    @Getter
    private class ErrorInfo {

        private final String url;
        private final String message;
        private final String responseCode;

        public ErrorInfo(String url, String message, String responseCode) {
            this.url = url;
            this.message = message;
            this.responseCode = responseCode;
        }
    }
}
