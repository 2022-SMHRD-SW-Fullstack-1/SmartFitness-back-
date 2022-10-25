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
import com.smartfitness.demo.model.Programs;
import com.smartfitness.demo.model.TimetableAvailable;
import com.smartfitness.demo.model.TimetableAvailable2;
import com.smartfitness.demo.service.ProgramsService;
import com.smartfitness.demo.service.TimetableAvailableService;
import com.smartfitness.demo.service.TimetableAvailableService2;

@RestController
@RequestMapping("programs")
public class ProgramsRestController {

	Gson gson = new Gson();

	@Autowired
	ProgramsService programsService;

	@Autowired
	TimetableAvailableService2 timetableAvailableService2;

	// 프로그램 정보 추가
	@PostMapping("programs/add")
	public String programsAdd(@RequestBody Programs programs) {
		System.out.println(programs.toString());
		programsService.programsAdd(programs);
		// ~DB에 넣기 위해

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "success");
		// ~
		String result = gson.toJson(resultMap);
		// hashMap을 uri로 바꾸는 작업

		return result;

	}

	// 운동 프로그램 예약 가능 시간 확인
	@GetMapping("/programs/timetable/{prSeq}")
	public String selectTimetableAvailable2(@PathVariable("prSeq") int prSeq,
			@RequestBody TimetableAvailable2 timetableAvailable2) {
		System.out.println(prSeq);
		System.out.println(timetableAvailable2);
		TimetableAvailable2 timetable = timetableAvailableService2.selectTimetableAvailable(prSeq);
		String result = gson.toJson(timetable);
		return result;
	}

	// 운동 프로그램 예약
	@PostMapping("/programs/timetable/{prSeq}/reserv")
	public String reservPrograms(@PathVariable("prSeq") int prSeq, @RequestBody String reserv) {
		System.out.println(prSeq);
		System.out.println(reserv);
		int cnt = timetableAvailableService2.reservTimetable(prSeq, reserv);

		if (cnt > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
}