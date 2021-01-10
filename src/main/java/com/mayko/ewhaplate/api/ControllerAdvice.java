package com.mayko.ewhaplate.api;

import com.mayko.ewhaplate.dto.response.ExceptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(this.getClass()); // logger.debug 안 찍힘
    @ExceptionHandler(value = Exception.class)
    public ExceptionDto handleException(Exception e) {
        logger.info("EXCEPTION ===> " + e.getMessage());
        return new ExceptionDto(e.getMessage());
    }
}
