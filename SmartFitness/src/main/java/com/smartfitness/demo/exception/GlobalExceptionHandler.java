package com.smartfitness.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
/**
 * exception발생 시 전역으로 처리할 exception handler
 * **/
@Slf4j
@RestControllerAdvice // 모든 restcontrolelr에서 발생하는 exception을 처리한다.
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)//@ExceptionHandler(xxException.class) : 발생한 xxException에 대해서 처리하는 메소드를 작성한다.  
	public ResponseEntity<ErrorResponse> handleCustomException(CustomException e){
        log.error("handleCustomException",e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        log.error("handleException",e);
        ErrorResponse response = new ErrorResponse(ErrorCode.INTER_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}