package com.smartfitness.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.mapper.TimetableAvailableMapper;
import com.smartfitness.demo.model.TimetableAvailable;

@Service
public class TimetableAvailableService {

	@Autowired
	TimetableAvailableMapper timetableMapper;
	
	public TimetableAvailable selectTimetable(int emSeq) {
		return timetableMapper.selectTimetable(emSeq);
	}

	public int reservTimetable(int emSeq, TimetableAvailable timetableAvailable) {
		int cnt = timetableMapper.reservTimetable(emSeq, timetableAvailable);
		return cnt;
		
	}

}
