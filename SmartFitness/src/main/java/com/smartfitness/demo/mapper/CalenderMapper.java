package com.smartfitness.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.model.Calender;

@Mapper
public interface CalenderMapper {
	
	int calenderAdd(Calender calender);

	void calenderC(Calender calender);
	
	public int enrollPT(Calender calender);

	int cancelPrograms(int num);

	void resetCnt(int num);
}
