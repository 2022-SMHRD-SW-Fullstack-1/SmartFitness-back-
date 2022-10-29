package com.smartfitness.demo.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.mapper.ProgramsMapper;
import com.smartfitness.demo.model.Calender;
import com.smartfitness.demo.model.Programs;

@Service
public class ProgramsService {

	@Autowired
	ProgramsMapper programsMapper;
	
	public int AddPg(Programs programs) {
		return programsMapper.AddPg(programs);
	}

	
}
