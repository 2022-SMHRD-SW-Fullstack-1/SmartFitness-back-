package com.smartfitness.demo.exception;

import java.net.BindException;


import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;
/**
 * exception발생 시 전역으로 처리할 exception handler
 * **/
@RestControllerAdvice // 모든 restcontrolelr에서 발생하는 exception을 처리한다.
public class GlobalExceptionHandler extends Exception{

	
	//제약 조건 위배, 데이터의 삽입/수정이 무결성 제약조건을 위반할 때 발생하는 예외
	@ExceptionHandler(value= {ConstraintViolationException.class, DataIntegrityViolationException.class})
	protected ResponseEntity<ErrorResponse> handleDuplicationException(){
		return ErrorResponse.toResponseEntity(ErrorCode.INTER_SERVER_ERROR);
	}
	
	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e){
		return ErrorResponse.toResponseEntity(e.getErrorCode());
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)  
	protected ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException e,HttpServletRequest request) {
		return ErrorResponse.toResponseEntity(ErrorCode.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
		return ErrorResponse.toResponseEntity(ErrorCode.BAD_REQUEST);
	}
}