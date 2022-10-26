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
	
	public TimetableAvailable selectTimetable(int em_seq) {
		return timetableMapper.selectTimetable(em_seq);
	}

	public int reservTimetable(int em_seq, String time) {
		int cnt = timetableMapper.reservTimetable(em_seq, time);
		return cnt;
		
	}

}
