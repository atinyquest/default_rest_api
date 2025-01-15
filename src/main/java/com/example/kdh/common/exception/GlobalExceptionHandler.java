package com.example.kdh.common.exception;

import com.example.kdh.common.response.ApiResponse;
import java.nio.file.AccessDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomApiException.class})
    public ApiResponse exceptionHandler(final CustomApiException e) {
        log.error("{}", e.getMessage(), e);
        return new ApiResponse(e.getError(), e);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ApiResponse exceptionHandler(final MethodArgumentNotValidException e) {
        log.error("{}", e.getMessage(), e);
        return new ApiResponse(ApiResponseEnum.BAD_REQUEST, e);
    }

    @ExceptionHandler({RuntimeException.class})
    public ApiResponse exceptionHandler(final RuntimeException e) {
        log.error("{}", e.getMessage(), e);
        return new ApiResponse(ApiResponseEnum.FAIL, e);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ApiResponse exceptionHandler( final AccessDeniedException e) {
        log.error("{}", e.getMessage(), e);
        return new ApiResponse(ApiResponseEnum.FAIL, e);
    }

    @ExceptionHandler({Exception.class})
    public ApiResponse exceptionHandler(final Exception e) {
        log.error("{}", e.getMessage(), e);
        return new ApiResponse(ApiResponseEnum.FAIL, e);
    }
}
