package com.smartfitness.demo.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//spring 전역의 예외를 처리할 수 있는 어노테이션
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// CustomException 클래스를 등록해줌으로써 해당 예외가 발생할 때, 어떻게 처리하는지 지정
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<CustomExceptionResponse> handler(CustomException e) {
		CustomExceptionResponse res = CustomExceptionResponse.builder()
				.detail(e.getDetail())
				.errorCode(e.getErrorCode())
				.build();
		return new ResponseEntity<>(res, e.getStatusCode());
	}

}
