package com.smartfitness.demo.controller;

import java.util.HashMap;
import java.util.List;

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

@RequestMapping("equipments")
@RestController
public class EquipmentsRestController {
	
	@Autowired
	EquipmentsMapper equipmentsMapper;
	
	@Autowired
	EquipmentsService equipmentsService;
	
	@Autowired
	TimetableAvailableService timetableAvailableService;
	
	//운동 기구 정보 추가
	@PostMapping("/equipments/add")
	public String equipmentsAdd(@RequestBody Equipments equipments) {
		System.out.println(equipments.toString());
		equipmentsService.equipmentsAdd(equipments);
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "success");
		
		Gson gson = new Gson();
		String result = gson.toJson(resultMap);
		
		return result;
	}
	
	//운동 기구 예약 가능 시간 확인
	@GetMapping("/euipments/timetable/{emSeq}")
	public String selectTimetableAvailable(@PathVariable("emSeq") int emSeq) {
		System.out.println(emSeq);
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", timetableAvailableService.selectTimetableAvailable(emSeq));
		
		Gson gson = new Gson();
		String result = gson.toJson(resultMap);
		
		return result; 
	}
	
	//운동 기구 예약
	@PostMapping("/equipments/reserv")
	public String reservEquipments() {
		
	
	String result="";
	return result;
	}
	
}
