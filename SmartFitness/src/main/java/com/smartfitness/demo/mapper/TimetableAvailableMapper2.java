package com.smartfitness.demo.mapper;

import com.smartfitness.demo.model.TimetableAvailable2;

public interface TimetableAvailableMapper2 {

	TimetableAvailable2 selectTimetable(int prSeq);

	int reservTimetable(int pgSeq, String reserv);

}
