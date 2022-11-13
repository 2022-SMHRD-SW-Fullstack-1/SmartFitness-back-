package com.smartfitness.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartfitness.demo.config.auth.Auth;
import com.smartfitness.demo.config.auth.CustomUserDetailService;
import com.smartfitness.demo.config.jwt.JwtTokenProvider;
import com.smartfitness.demo.model.Members;

@RequestMapping("test")
@RestController
public class MainController {
	
	@Autowired JwtTokenProvider jwtTokenProvider;
	@Autowired CustomUserDetailService ds;
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	/**
	 * setHeader로 헤더에 값 넣기
	 * **/
	@GetMapping("/header")
	public void header(HttpServletResponse res){
		res.setHeader("name","value");
	}
	
	/**
	 * ResponseEntity로 헤더, 바디에 값 담아주기
	 * **/
	@GetMapping("/response")
	public ResponseEntity<?> r(@RequestBody Map<String, String> param){
		HttpHeaders headers = new HttpHeaders();
		String refreshToken = param.get("mem_id");
		String accessToken = jwtTokenProvider.createToken(refreshToken, "Access-Token");
		headers.add(HttpHeaders.AUTHORIZATION, accessToken);
		return ResponseEntity.ok()
		.headers(headers)
		.body("SUCCESS");
	}
	
	
	/**
	 * 헤더에 있는 authorization토큰을 받아서 유효성 검사,
	 * 토큰의 아이디 뽑는다.
	 * 이후 바디로 리턴한다
	 * **/
	@GetMapping("/request")
	public ResponseEntity<?> r(@RequestBody Map<String, String> param, @RequestHeader Map<String, String> map){
		String token = map.get("authorization");
		String tokenSub=jwtTokenProvider.getUserIDFromToken(token);
		String sub=jwtTokenProvider.getUserIDFromToken(tokenSub);
		
		HttpHeaders headers = new HttpHeaders();
		String refreshToken = param.get("mem_id");
		String accessToken = jwtTokenProvider.createToken(refreshToken, "Access-Token");
		headers.add(HttpHeaders.AUTHORIZATION, accessToken);
		return ResponseEntity.ok()
		.headers(headers)
		.body(jwtTokenProvider.validateToken(token)+" "+tokenSub+" "+sub);
		
	}
	
	

}
