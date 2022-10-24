package com.smartfitness.demo.mapper;

import java.util.List;

import com.smartfitness.demo.model.TimetableAvailable;

public interface TimetableAvailableMapper {

	List<TimetableAvailable> selectTimetable(int emSeq);

}
