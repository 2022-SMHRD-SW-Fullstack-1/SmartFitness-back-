package com.smartfitness.demo.exception;

import lombok.Getter;

/**
 * 사용자 정의 exception class
**/
@Getter
public class CustomException extends RuntimeException{
	private ErrorCode errorCode;
	
	public CustomException(String messeage, ErrorCode errorCode) {
		super(messeage);
		this.errorCode = errorCode;
	}
}
