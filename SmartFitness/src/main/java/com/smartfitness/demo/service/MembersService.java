package com.smartfitness.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.config.auth.Auth;
import com.smartfitness.demo.config.jwt.JwtTokenProvider;
import com.smartfitness.demo.exception.CustomException;
import com.smartfitness.demo.exception.ErrorCode;
import com.smartfitness.demo.mapper.MembersMapper;
import com.smartfitness.demo.model.Members;
import com.smartfitness.demo.model.MembersDetail;

@Service
public class MembersService {
	
	@Autowired
	MembersMapper membersMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	//회원가입
	public void join(Members members) throws Exception{
		String mem_id = members.getMem_id();
		String DBmem_id = membersMapper.read(mem_id).getMem_id();
		if(DBmem_id!=null) {
			//아이디 중복일 때, 예외 처리
			throw new CustomException(ErrorCode.MEM_CONFLICT);	
		}
		int cnt = membersMapper.join(members);
		if(cnt==0) {
			//회원가입 실패
			throw new CustomException(ErrorCode.MEM_BAD_REQUEST);
		}
	}
	
	//로그인
	public Auth login(Members members) throws Exception{
		String mem_id = members.getMem_id();
		String mem_pw = members.getMem_pw();
		Map<String, Object> result = new HashMap<>();
		MembersDetail md = membersMapper.findByUserId(mem_id);
		if(md==null) {
			//아이디를 찾을 수 없음
			throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
		}
		else if(!passwordEncoder.matches(mem_pw, md.getPassword())) {
			//잘못된 비밀번호
			throw new CustomException(ErrorCode.WRONG_PW);
		}
		else {
			String DBmem_id = md.getMem_id();
			String DBmem_auth = md.getMem_auth();
			String accessToken= jwtTokenProvider.createToken(md, "Access-Token");
			Auth auth = new Auth(accessToken,DBmem_id,DBmem_auth);
			return auth;
		}
	}
	//회원정보 수정
	public void update(Members members) throws Exception{
		String rawPassword=members.getMem_pw();
		String encPassword=passwordEncoder.encode(rawPassword);
		members.setMem_pw(encPassword);
		int result = membersMapper.update(members);
		if(result==0) {
			//아이디 중복
			throw new CustomException(ErrorCode.MEM_CONFLICT);
		}
	}

	//회원탈퇴
	public void delete(String mem_id, String mem_pw) throws Exception {
		MembersDetail md = membersMapper.findByUserId(mem_id);
		if(!passwordEncoder.matches(mem_pw, md.getPassword())) {
			//잘못된 비밀번호
			throw new CustomException(ErrorCode.WRONG_PW);
		}
		else {
		int result = membersMapper.delete(mem_id);
			if(result==0) {
				//회원탈퇴 실패
				throw new CustomException(ErrorCode.MEM_BAD_REQUEST);
			}
		}
	}
}

