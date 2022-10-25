package com.smartfitness.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.model.TimetableAvailable2;

@Mapper
public interface TimetableAvailableMapper2 {

	TimetableAvailable2 selectTimetable(int prSeq);

	int reservTimetable(int pgSeq, String reserv);

}
