package com.smartfitness.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
//			members.setMem_type("M"); // 기본값 일반M
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
	public String login(@RequestBody Map<String, String> param) throws Exception{
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
		
		String token= jwtTokenProvider.createToken(members.getMem_id(), members.getMem_name());
		String mem_id=members.getMem_id();
		String mem_type=members.getMem_type();
		System.out.println(mem_type);
		Auth auth=new Auth(token, mem_id, mem_type);
		String result = gson.toJson(auth);
		System.out.println(result);
		return result;
	}
	
	// 회원 정보 수정
	// pw, name, addrr, birthdate, phone
	// set mem_pw=#{mem_pw},mem_name=#{mem_name},mem_addr=#{mem_addr},mem_birthdate=#{mem_birthdate},mem_phone=#{mem_phone}
	@PostMapping("/update")
	public String update(@RequestBody Map<String,String> param) throws Exception{
		String jsonStr=gson.toJson(param);
		System.out.println(jsonStr);
		try {
		Members members=gson.fromJson(jsonStr, Members.class);
		System.out.println(members.toString());
		System.out.println(members.getMem_id());
		System.out.println(members.getMem_pw());
		System.out.println(members);
		String rawPassword=members.getMem_pw();
		String encPassword=passwordEncoder.encode(rawPassword);
		members.setMem_pw(encPassword);
		System.out.println(members);
		
		membersService.update(members);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
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
