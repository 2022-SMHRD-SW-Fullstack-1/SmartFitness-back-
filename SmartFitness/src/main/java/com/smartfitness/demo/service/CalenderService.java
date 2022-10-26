package com.smartfitness.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.mapper.CalenderMapper;
import com.smartfitness.demo.model.Calender;
import com.smartfitness.demo.model.Equipments;

@Service
public class CalenderService {

	@Autowired
	CalenderMapper calenderMapper;
	
	
	
	public int calenderAdd(Calender calender) {
		calenderMapper.calenderC(calender);
		return calenderMapper.calenderAdd(calender);
		
	}
	public int enrollPT(Calender calender) {
		return calenderMapper.enrollPT(calender);
	}
	
	public int cancelPrograms(int num) {
		return calenderMapper.cancelPrograms(num);
	}
	public void reset(int num) {
		calenderMapper.resetCnt(num);
		
	}
	
}
