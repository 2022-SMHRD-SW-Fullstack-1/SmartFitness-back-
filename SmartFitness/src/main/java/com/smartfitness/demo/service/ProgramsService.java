package com.smartfitness.demo.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.mapper.ProgramsMapper;
import com.smartfitness.demo.model.Programs;

@Service
public class ProgramsService {

	@Autowired
	ProgramsMapper programsMapper;
	
	public int programsAdd(Programs programs) {
		return programsMapper.programsAdd(programs);
	}

	public int enrollPT(HashMap<String, Object> map) {
		return programsMapper.enrollPT(map);
	}
}
