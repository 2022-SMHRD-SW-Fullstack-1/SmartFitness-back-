package com.smartfitness.demo.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.config.auth.Auth;
import com.smartfitness.demo.config.auth.CustomUserDetailService;
import com.smartfitness.demo.config.jwt.AuthResponse;
import com.smartfitness.demo.config.jwt.JwtTokenProvider;
import com.smartfitness.demo.exception.CustomException;
import com.smartfitness.demo.exception.ErrorCode;
import com.smartfitness.demo.mapper.MembersMapper;
import com.smartfitness.demo.mapper.TokenMapper;
import com.smartfitness.demo.model.Members;
import com.smartfitness.demo.model.MembersDetail;

@Service
public class MembersService {
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	MembersMapper membersMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Autowired
	TokenMapper tokenMapper;
	
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
	public Auth login(Members login_members) throws Exception{
		String login_mem_id = login_members.getMem_id();
		String login_mem_pw = login_members.getMem_pw();
		
		/**
		 * DB에 있는 members 정보 불러오기(SimpleGrantedAuthority 설정도 한다 ROLE_M/ROLE_A)
		 * **/
		MembersDetail DB_members= customUserDetailService.loadUserByUsername(login_mem_id);
		if(DB_members==null) {
			//아이디를 찾을 수 없음
			throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
		}
		else if(!passwordEncoder.matches(login_mem_pw, DB_members.getPassword())) {
			//잘못된 비밀번호
			throw new CustomException(ErrorCode.WRONG_PW);
		}
		else {
			System.out.println("사용자 권한 : "+DB_members.getAuthorities());
			String DBmem_id = DB_members.getMem_id();
			String DBmem_email =DB_members.getMem_auth();
			String DBmem_name = DB_members.getMem_name();
			String DBmem_phone =DB_members.getMem_phone();
			
			/**
			 * 권한 설정
			 * **/
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(login_mem_id, login_mem_pw)
					);
			/**
			 * 권한부여
			 * **/
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println("권한 부르기");
			System.out.println(authentication);
			//안전을 위해 패스워드 정보는 로그인 성공 후에 AuthenticationManager(ProviderManager)에 의해 명시적으로 삭제(null 설정)되어 있다.
			//옵션(eraseCredentialsAfterAuthentication)으로 삭제되지 않도록 하는 것도 가능하다.
//			System.out.println((String)authentication.getCredentials());
			
//			String accessToken= jwtTokenProvider.createToken(DBmem_id, "Access-Token");
			//access token 생성 -> json payload에 담아서 보내줄 것
			String refreshToken= jwtTokenProvider.createToken(authentication);
			System.out.println("refreshToken는 DB로: "+refreshToken);
			//refresh token 생성 -> httpresponse set cookie header에 refresh token 값을 설정
			String accessToken = jwtTokenProvider.createToken(refreshToken, "Access-Token");
			//refreshToken값을 DB에 저장
			tokenMapper.insertToken(accessToken, DBmem_id);
			System.out.println("accessToken은 프론트payload로: "+accessToken);
			Auth auth = new Auth(accessToken,DBmem_id,DBmem_email,DBmem_name,DBmem_phone);
			System.out.println(ResponseEntity.ok(new AuthResponse(accessToken)));
			System.out.println(auth);
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

