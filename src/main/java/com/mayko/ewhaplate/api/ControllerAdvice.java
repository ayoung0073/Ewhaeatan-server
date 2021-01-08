package com.mayko.ewhaplate.api;

import com.mayko.ewhaplate.dto.response.ExceptionDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = Exception.class)
    public ExceptionDto handleException(Exception e) {
        return new ExceptionDto(e.getMessage());
    }
}
