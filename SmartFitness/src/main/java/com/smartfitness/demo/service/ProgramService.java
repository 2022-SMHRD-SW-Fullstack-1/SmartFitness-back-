package com.smartfitness.demo.service;

import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.mapper.ProgramMapper;
import com.smartfitness.demo.model.Program;

@Service
public class ProgramService {

	@Autowired
	ProgramMapper programMapper;
	
	//프로그램 추가
	public int programAdd(Program program) {
		return programMapper.programAdd(program);
	}
	
	//

	
}
