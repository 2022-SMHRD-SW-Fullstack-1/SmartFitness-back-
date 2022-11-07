package com.smartfitness.demo.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * exception발생 시 응답하는 에러 정보 클래스
 * **/
@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private String code;

    public ErrorResponse(ErrorCode errorCode){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.code = errorCode.getErrorCode();
    }
}