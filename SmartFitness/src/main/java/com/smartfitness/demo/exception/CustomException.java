package com.smartfitness.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 사용자 정의 exception class
 * unchecked Exception으로 활용(런타임 단계에서 체크하는, 예측할 수 없는 예외)
**/
@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
	private ErrorCode errorCode;
}