package com.example.kdh.common.response;

import com.example.kdh.common.exception.ApiResponseEnum;
import com.example.kdh.common.exception.CustomApiException;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
public class ApiResponse implements Serializable {

    private final Long code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Long errorCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String errorMessage;
    private final Object body;

    private final Date timestamp;

    /**
     * 단건 정상조회
     * @param data
     * @param responseEnum
     */
    public ApiResponse(
            Object data,
            ApiResponseEnum responseEnum
    ){
        super();

        if(Objects.isNull(data)){
            this.body = Collections.EMPTY_MAP;
            this.code = (long) ApiResponseEnum.NO_CONTENT.getCode();
            this.message = ApiResponseEnum.NO_CONTENT.getMessage();
        } else {
            this.body = data;
            this.code = (long) responseEnum.getCode();
            this.message = responseEnum.getMessage();
        }

        this.timestamp = new Date();
        this.errorCode = null;
        this.errorMessage = null;
    }

    /**
     * 다건 정상조회
     * @param list
     * @param responseEnum
     */
    public ApiResponse(List<?> list, ApiResponseEnum responseEnum) {
        super();

        this.body = list;
        this.code = (long) responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.timestamp = new Date();
        this.errorCode = null;
        this.errorMessage = null;
    }

    /**
     * 에러
     * @param responseEnum
     */
    public ApiResponse(
            ApiResponseEnum responseEnum,
            Exception ex
    ) {
        super();

        this.timestamp = new Date();

        Long responseCode = (long) responseEnum.getCode();
        String responseMessage = responseEnum.getMessage();
        Long errorCode = responseCode != HttpStatus.OK.value() ? responseCode : null; // 클래스 이름 저장
        String errorMessage = getErrorMessage(responseEnum, (MethodArgumentNotValidException) ex,
                responseCode, responseMessage);

        this.code = responseCode;
        this.message = responseMessage;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.body = Collections.EMPTY_MAP;
    }

    private static String getErrorMessage(ApiResponseEnum responseEnum,
            MethodArgumentNotValidException ex, Long responseCode, String responseMessage) {
        return responseCode != HttpStatus.OK.value() ?
                getInvalidErrorMessage(responseEnum, ex, responseCode, responseMessage)
                : null;
    }

    private static String getInvalidErrorMessage(ApiResponseEnum responseEnum,
            MethodArgumentNotValidException ex, Long responseCode, String responseMessage) {
        return responseCode == responseEnum.BAD_REQUEST.getCode() ?
                ex.getBindingResult()
                        .getAllErrors().stream().map(err -> err.getDefaultMessage())
                        .collect(Collectors.joining(" , ")) : responseMessage;
    }

    public static ApiResponse customError(CustomApiException e) {
        return new ApiResponse(e.getError(), e);
    }

    public static ApiResponse success() {
        return new ApiResponse("", ApiResponseEnum.SUCCESS);
    }

    public static ApiResponse success(Object data) {
        return new ApiResponse(data, ApiResponseEnum.SUCCESS);
    }

    public static ApiResponse success(List<?> list) {
        return new ApiResponse(list, ApiResponseEnum.SUCCESS);
    }
}
