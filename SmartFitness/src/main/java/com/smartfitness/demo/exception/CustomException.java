package com.smartfitness.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
	//최상위 exception class
	private final HttpStatus statusCode;
	private final Integer errorCode;
	private final String detail;
	 
	public CustomException(HttpStatus statusCode, Integer errorCode, String detail) {
		this.statusCode = statusCode;
		this.errorCode = errorCode;
		this.detail = detail;
	}
}
