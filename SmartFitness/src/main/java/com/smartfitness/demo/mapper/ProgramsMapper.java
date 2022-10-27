package com.smartfitness.demo.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.model.Calender;
import com.smartfitness.demo.model.Programs;

@Mapper
public interface ProgramsMapper {
	
	public int programsAdd(Programs programs);

	
}
