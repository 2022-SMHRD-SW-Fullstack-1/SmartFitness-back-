package com.smartfitness.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.model.Programs;

@Mapper
public interface ProgramsMapper {
	
	void programsAdd(Programs programs);
}
