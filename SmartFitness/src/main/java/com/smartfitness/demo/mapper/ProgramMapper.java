package com.smartfitness.demo.mapper;

import java.util.HashMap;


import org.apache.ibatis.annotations.Mapper;
import com.smartfitness.demo.model.Program;

@Mapper
public interface ProgramMapper {
	
	public int programAdd(Program program);

	
}
