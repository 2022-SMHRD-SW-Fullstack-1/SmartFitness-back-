package com.smartfitness.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.model.TimetableAvailable;

@Mapper
public interface TimetableAvailableMapper {

	TimetableAvailable selectTimetable(int emSeq);

	int reservTimetable(int emSeq, TimetableAvailable timetableAvailable);

}
