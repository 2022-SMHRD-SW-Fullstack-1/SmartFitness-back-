package com.smartfitness.demo.controller;

import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.smartfitness.demo.model.Program;
import com.smartfitness.demo.service.ProgramService;


@RestController
@RequestMapping("/program")
public class ProgramRestController {

	Gson gson = new Gson();

	@Autowired
	ProgramService programService;

	

	// 프로그램 추가
	@PostMapping("/add")
	public String programsAdd(@RequestBody Program program) {
		System.out.println(program.toString());
		int cnt = programService.programAdd(program);
		if(cnt > 0 ) {
			return "success";
		}
		else {
			return "fail";
		}

	}
}



















