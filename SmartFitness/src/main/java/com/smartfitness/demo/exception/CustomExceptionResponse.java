package com.smartfitness.demo.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CustomExceptionResponse {
	//최종적으로 응답을 내보내기전에 값을 감싸줄 클래스
	//예외 클래스에서 내부 필드가 변경되었다면 위 클래스에도 변경사항을 적용해줘야 한다.
	private Integer errorCode;
	private String detail;
		
	@Builder
	public CustomExceptionResponse(Integer errorCode, String detail) {
		this.errorCode = errorCode;
		this.detail = detail;
	}
}
