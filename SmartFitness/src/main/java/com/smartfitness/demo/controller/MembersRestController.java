package com.smartfitness.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.smartfitness.demo.config.auth.Auth;
import com.smartfitness.demo.config.jwt.JwtTokenProvider;
import com.smartfitness.demo.mapper.MembersMapper;
import com.smartfitness.demo.model.Members;
import com.smartfitness.demo.model.MembersDetail;
import com.smartfitness.demo.service.MembersService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("members")
@RestController
public class MembersRestController {
	
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final MembersMapper membersMapper;
	
	Gson gson = new Gson();
	
	@Autowired
	MembersService membersService;
	
	//회원가입
	@PostMapping("/join")
	public String joinMembers(@RequestBody Members members) {
		System.out.println(members.toString());
		try {
			String rawPassword=members.getMem_pw();
			String encPassword=passwordEncoder.encode(rawPassword);
			members.setMem_pw(encPassword);
			members.setMem_type("M"); // 일반M / 관리자A
			System.out.println(members);
			membersService.join(members);
			return "success";
		} catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	//로그인
	@PostMapping("/login")
	public Members login(@RequestBody Map<String, String> param) throws Exception{
		String jsonStr=gson.toJson(param);
		System.out.println(jsonStr);
		Members user=gson.fromJson(jsonStr, Members.class);
		System.out.println(user.toString());
		System.out.println(user.getMem_id());
		System.out.println(user.getMem_pw());
		
		MembersDetail members = membersMapper.findByUserId(user.getMem_id());
		System.out.println(members);
		if(members ==null) {
			throw new UsernameNotFoundException("유효하지 않은 로그인 정보입니다.");
		}
		
		if(!passwordEncoder.matches(user.getMem_pw(), members.getMem_pw())) {
			throw new IllegalAccessException("잘못된 비밀번호입니다.");
		}
		//2.인증 성공 시 authToken생성
		String authToken= jwtTokenProvider.createToken(members.getMem_id());
		String mem_id=members.getMem_id();
		//3.refreshToken도 함께 생성
		String refreshToken = jwtTokenProvider.createRefreshToken();
		//계정 정보와 함께 refreshToken을 저장한다. 인증정보는 저장되지 않는다.
		saveRefreshToken(mem_id,refreshToken);

		Auth auth=new Auth(authToken, mem_id, refreshToken);
		String result = gson.toJson(auth);
		System.out.println(result);
//		return result;
		return Members.builder().mem_id(mem_id).authToken(authToken).refreshToken(refreshToken).build();
	}
	
	/**
	 * 사용자 인증 정보를DB에 저장한다. 아래는 편의상 MAP에 저장한 것
	 * **/
	Map<String, String> refreshTokens = new HashMap<>();
	
	private void saveRefreshToken(String mem_id, String refreshToken) {
		refreshTokens.put(mem_id, refreshToken);
	}
	public String getRefreshTokens(String mem_id){
		return refreshTokens.get(mem_id);
	}
	
	/**
	 * 로그아웃 시 refreshToken정보 삭제
	 * **/
//	public void logout(String mem_id) {
//		refreshTokens.get(mem_id);
//	}
//	@GetMapping("/logout")
//	public ResponseEntity<Void> logout(@RequestParam String mem_id) {
//		log.debug("logout: {}", mem_id);
//		membersService.logout(mem_id);
//		return new ResponseEntity<>(HttpStatus.ACCEPTED);
//	}
	
	
	//토큰이 가지고 있는 값에 따라 권한 확인할 수 있음
	@GetMapping("/user/test")
	public String ResponseTest() {
		return "user ok";
	}
	
	@GetMapping("/admin/test")
	public String adminResponseTest() {
		return "admin ok";
	}

}
