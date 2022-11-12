package com.smartfitness.demo.mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.model.Members;
import com.smartfitness.demo.model.MembersDetail;

@Mapper
public interface MembersMapper {

	//회원가입
	int join(Members members);
	
	//회원 정보 인증,인가, 로그인
	MembersDetail findByUserId(String mem_id);
	Members read(String mem_id);


	//회원정보 수정
	int update(Members members);

	//회원탈퇴
	int delete(String mem_id);
	
	//30일 결제
	int insertInfo(Map<String, Object> map);
	
	//45일 결제
	int insertInfo2(Map<String, Object> map);
	
	String readE(String email);

	int readM(String id);

	int updateInfo(Map<String, Object> map);

	int updateInfo2(Map<String, Object> map);

	HashMap mbs(String mem_id);



}
