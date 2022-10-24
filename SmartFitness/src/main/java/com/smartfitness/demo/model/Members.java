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
	@NonNull
    private String memId;

    // 회원 비밀번호
	@NonNull
    private String memPw;

    // 회원 등급
	@NonNull
    private String memGrade;

    // 회원 가입일
	@NonNull
    private Date memJoindate;

    // 회원 이름
	@NonNull
    private String memName;

    // 회원 주소
	@NonNull
    private String memAddr;

    // 회원 생년월일
	@NonNull
    private String memBirthdate;

    // 회원 전화번호
	@NonNull
    private String memPhone;

    // 회원 유형 일반회원:'M', 관리자:'A'
	@NonNull
	private String memType;

    // 소속 센터 순번 
	@NonNull
	private Integer ctrId;
	
	

}
