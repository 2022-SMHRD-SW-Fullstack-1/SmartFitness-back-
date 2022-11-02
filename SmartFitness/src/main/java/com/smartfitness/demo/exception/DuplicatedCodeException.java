package com.smartfitness.demo.exception;

import org.springframework.http.HttpStatus;

public class DuplicatedCodeException extends CustomException{
	//최상위 예외 클래스를 상속받은 후 각 예외에 맞는 값을 채워넣어서 만든다
	public DuplicatedCodeException() {
		super(HttpStatus.BAD_REQUEST, 2001, "duplicated code");
	}

}
