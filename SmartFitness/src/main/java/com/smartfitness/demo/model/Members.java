package com.smartfitness.demo.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@NoArgsConstructor
public class Members {
  
	
	@NonNull
	private String mem_id;
	@NonNull
	private String mem_pw;
	
	
	   // 회원 아이디 
    private String memId;

    // 회원 비밀번호 
    private String memPw;

    // 회원 등급 
    private String memGrade;

    // 회원 가입일 
    private Date memJoindate;

    // 회원 이름 
    private String memName;

    // 회원 주소 
    private String memAddr;

    // 회원 생년월일 
    private String memBirthdate;

    // 회원 전화번호 
    private String memPhone;

    // 회원 유형 일반회원:'M', 관리자:'A'
    private String memType;

    // 소속 센터 순번 
    private Integer ctrId;
	
	

}
