package com.smartfitness.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.mapper.TimetableAvailableMapper2;
import com.smartfitness.demo.model.TimetableAvailable2;

@Service
public class TimetableAvailableService2 {
	
	@Autowired
	TimetableAvailableMapper2 timetableAvailableMapper2;
	
	public TimetableAvailable2 selectTimetableAvailable(int prSeq) {
		return timetableAvailableMapper2.selectTimetable(prSeq);
	}
	
	public int reservTimetable(int prSeq, String reserv) {
		int cnt = timetableAvailableMapper2.reservTimetable(prSeq,reserv);
		return cnt;
	}
}
