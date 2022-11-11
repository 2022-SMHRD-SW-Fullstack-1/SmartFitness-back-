package com.smartfitness.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.smartfitness.demo.config.auth.Auth;
import com.smartfitness.demo.config.jwt.JwtTokenProvider;
import com.smartfitness.demo.mapper.MembersMapper;
import com.smartfitness.demo.model.Members;
import com.smartfitness.demo.model.MembersDetail;
import com.smartfitness.demo.service.MembersService;
import com.sun.jdi.request.DuplicateRequestException;

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

	// 회원가입
	@PostMapping("/join")
	public void joinMembers(@RequestBody Members members) throws Exception{
		System.out.println("사용자가 입력한 값:"+members.toString());
		String rawPassword = members.getMem_pw();
		//비밀번호 암호화
		String encPassword = passwordEncoder.encode(rawPassword);
		members.setMem_pw(encPassword);
		members.setMem_auth("ROLE_M"); // 기본값 일반M
		System.out.println("권한설정 : "+members.getMem_auth());
		membersService.join(members);
	}

	// 로그인
	@PostMapping("/login")
	public String login(@RequestBody Map<String, String> param) throws Exception {
		String jsonStr = gson.toJson(param);
		System.out.println("사용자가 입력한 값 : "+jsonStr);
		Members user = gson.fromJson(jsonStr, Members.class);
		System.out.println("사용자가 입력한 id : " + user.getMem_id());
		System.out.println("사용자가 입력한 pw : " + user.getMem_pw());

		// 로그인 이후, 권한을 부여하고 토큰을 발행한다
		// 오류 처리는 service에서 했다
		Auth auth = membersService.login(user);
		String result = gson.toJson(auth);
		System.out.println("반환 정보 : " + result);
		return result;
	}

	// 회원 정보 수정
	// pw, name, addrr, birthdate, phone
	// set
	// mem_pw=#{mem_pw},mem_name=#{mem_name},mem_addr=#{mem_addr},mem_birthdate=#{mem_birthdate},mem_phone=#{mem_phone}
	@PostMapping("/update")
	public void update(@RequestBody Map<String, String> param) throws Exception {
		String jsonStr = gson.toJson(param);
		System.out.println(jsonStr);
		Members members = gson.fromJson(jsonStr, Members.class);
		System.out.println("입력한 정보 : " + members);
		membersService.update(members);
	}

	// 회원탈퇴
	@GetMapping("/delete")
	public void delete(@RequestBody Map<String, Object> param) throws Exception {
		String mem_id = (String) param.get("mem_id");
		String mem_pw = (String) param.get("mem_pw");
		membersService.delete(mem_id,mem_pw);
	}

	// 토큰이 가지고 있는 값에 따라 권한 확인할 수 있음
	@GetMapping("/user/test")
	public String ResponseTest() {
		return "user ok";
	}

	@GetMapping("/admin/test")
	public String adminResponseTest() {
		return "admin ok";
	}

}
