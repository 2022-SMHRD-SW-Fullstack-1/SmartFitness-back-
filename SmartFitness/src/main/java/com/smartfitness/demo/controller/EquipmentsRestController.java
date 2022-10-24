package com.smartfitness.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.smartfitness.demo.mapper.EquipmentsMapper;
import com.smartfitness.demo.model.Equipments;
import com.smartfitness.demo.model.TimetableAvailable;
import com.smartfitness.demo.service.EquipmentsService;
import com.smartfitness.demo.service.TimetableAvailableService;

@RequestMapping("/equipments")
@RestController
public class EquipmentsRestController {

	Gson gson = new Gson();

	@Autowired
	EquipmentsMapper equipmentsMapper;

	@Autowired
	EquipmentsService equipmentsService;

	@Autowired
	TimetableAvailableService timetableAvailableService;

	// 운동 기구 정보 추가
	@PostMapping("/equipments/add")
	public String equipmentsAdd(@RequestBody Equipments equipments) {
		System.out.println(equipments.toString());
		int cnt = equipmentsService.equipmentsAdd(equipments);
		if (cnt > 0) {
			return "success";
		}
		else {
			return "fail";
		}
	}

	// 운동 기구 예약 가능 시간 확인
	@GetMapping("/equipments/timetable/{emSeq}")
	public String selectTimetableAvailable(@PathVariable("emSeq") int emSeq) {
		System.out.println(emSeq);
		TimetableAvailable timetable = timetableAvailableService.selectTimetableAvailable(emSeq);
		String result = gson.toJson(timetable);
		return result;
	}

	// 운동 기구 예약
	@PostMapping("/equipments/timetable/{emSeq}/reserv")
	public String reservEquipments(@PathVariable("emSeq") int emSeq,
			@RequestBody TimetableAvailable timetableAvailable) {
		System.out.println(emSeq);
		System.out.println(timetableAvailable);
		int cnt = timetableAvailableService.reservTimetable(emSeq, timetableAvailable);
		if (cnt > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 운동 기구 예약 취소

}
