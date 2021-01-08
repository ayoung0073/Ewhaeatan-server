package com.mayko.ewhaplate.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDto {

    private boolean success = false;
    private String message;

    public ExceptionDto(String message){
        this.message = message;
    }
}
