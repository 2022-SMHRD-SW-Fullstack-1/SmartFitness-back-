package com.smartfitness.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.model.TimetableAvailable;

@Mapper
public interface TimetableAvailableMapper {

	TimetableAvailable selectTimetable(int em_seq);

	int reservTimetable(int em_seq, String time);

}
