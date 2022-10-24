package com.smartfitness.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartfitness.demo.mapper.TimetableAvailableMapper;
import com.smartfitness.demo.model.TimetableAvailable;

public class TimetableAvailableService {

	@Autowired
	TimetableAvailableMapper timetableAvailableMapper;
	
	public TimetableAvailable selectTimetableAvailable(int emSeq) {
		return timetableAvailableMapper.selectTimetable(emSeq);
	}

	public int reservTimetable(int emSeq, TimetableAvailable timetableAvailable) {
		int cnt = timetableAvailableMapper.reservTimetable(emSeq, timetableAvailable);
		return cnt;
		
	}

}
