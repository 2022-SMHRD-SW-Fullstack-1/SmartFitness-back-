package com.smartfitness.demo.mapper;

import java.util.List;

import com.smartfitness.demo.model.TimetableAvailable;

public interface TimetableAvailableMapper {

	TimetableAvailable selectTimetable(int emSeq);

	int reservTimetable(int emSeq, TimetableAvailable timetableAvailable);

}
