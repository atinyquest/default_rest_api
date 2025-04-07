package com.example.kdh.common.exception;

import org.springframework.http.HttpStatus;

public enum ApiResponseEnum {

    //  공통
    OK(HttpStatus.OK.value(),"성공"),
    FAIL(HttpStatus.BAD_REQUEST.value(),"실패"),
    SUCCESS(HttpStatus.OK.value(),"성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(),"실패"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(),"인증 실패"),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(),"권한 거부"),
    TOO_MANY_REQUEST(HttpStatus.TOO_MANY_REQUESTS.value(),"요청 한도 초과"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),"시스템오류"),
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY.value(),"게이트웨이 오류"),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE.value(),"서비스 이용 불가"),
    NO_CONTENT(HttpStatus.NO_CONTENT.value(),"데이터 없음"),

    //  사용자
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(),"사용자를 찾을 수 없음"),
    INCORRECT_NAME_AND_EMAIL(HttpStatus.BAD_REQUEST.value(),"사용자 이름/이메일이 잘못됨"),
    VALIDITY_PERIOD_EXPIRED(HttpStatus.UNAUTHORIZED.value(),"유효기간 만료"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(),"유효하지 않은 토큰"),
    ;

    private final int code;
    private final String message;

    ApiResponseEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }
}

