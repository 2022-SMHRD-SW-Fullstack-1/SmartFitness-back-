package com.smartfitness.demo.exception;

import java.security.Timestamp;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * exception발생 시 실제 유저에게 응답하는 에러 정보 클래스
 * message만 있어도 된다
 * **/
@Getter
@Builder
public class ErrorResponse {
	private static LocalDateTime timestamp = LocalDateTime.now();
	//NOT_FOUND(404,"COMMON-ERR-404","PAGE NOT FOUND"),
    private int status;
    private String errorcode;
    private String message;
    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode){
    	return ResponseEntity
    			.status(errorCode.getStatus())
    			.body(ErrorResponse.builder()//시간넣기
    					.status(errorCode.getStatus())
    					.errorcode(errorCode.getErrorCode())
    					.message(errorCode.getMessage())
    					.build()
    					);
    }
}