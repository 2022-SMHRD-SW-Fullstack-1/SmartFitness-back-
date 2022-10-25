package com.smartfitness.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.mapper.TimetableAvailableMapper2;
import com.smartfitness.demo.model.TimetableAvailable2;

@Service
public class TimetableAvailableService2 {
	
	@Autowired
	TimetableAvailableMapper2 timetableMapper2;
	
	public TimetableAvailable2 selectTimetable(int prSeq) {
		return timetableMapper2.selectTimetable(prSeq);
	}
	
	public int reservTimetable(int prSeq, String reserv) {
		int cnt = timetableMapper2.reservTimetable(prSeq,reserv);
		return cnt;
	}

	public int cancelTimetable(int pgrSeq) {
		int cnt = timetableMapper2.cancelTimetable(pgrSeq);
		return cnt;
	}
}
