package com.smartfitness.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Mapper.MemberMapper;
import com.example.demo.config.jwt.JwtTokenProvider;
import com.google.gson.Gson;
import com.smartfitness.demo.mapper.ProgramsMapper;
import com.smartfitness.demo.service.ProgramsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("programs")
public class ProgramsRestController {
	
	private final ProgramsMapper programMapper;
	
	Gson gson = new Gson();
	
	@Autowired
	ProgramsService programService;
	
	
	//프로그램 정보 추가
	PostMapping("programs/add")
	public String programsAdd(@RequestBody Equip)
	
	
}
