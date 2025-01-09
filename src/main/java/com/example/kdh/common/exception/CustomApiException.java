package com.example.kdh.common.exception;

import lombok.Getter;

@Getter
public class CustomApiException extends RuntimeException {

    private ApiResponseEnum error;

    public CustomApiException(ApiResponseEnum e) {
        super(e.getMessage());
        this.error = e;
    }

}
