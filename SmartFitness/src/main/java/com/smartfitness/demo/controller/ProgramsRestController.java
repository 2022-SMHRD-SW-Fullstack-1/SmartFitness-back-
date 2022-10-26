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
import com.smartfitness.demo.model.Calender;
import com.smartfitness.demo.model.Programs;
import com.smartfitness.demo.model.TimetableAvailable;
import com.smartfitness.demo.model.TimetableAvailable2;
import com.smartfitness.demo.service.CalenderService;
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
	TimetableAvailableService2 timetableService;
	
	@Autowired
	CalenderService calenderService;

	// 프로그램 정보 추가
	@PostMapping("/add")
	public String programsAdd(@RequestBody Programs programs) {
		System.out.println(programs.toString());
		int cnt = programsService.programsAdd(programs);
		if(cnt > 0 ) {
			return "success";
		}
		else {
			return "fail";
		}

	}

	// 운동 프로그램 예약 가능 시간 확인
	@GetMapping("/timetable/{pr_seq}")
	public String selectTimetableAvailable2(@PathVariable("pr_seq") int pr_seq) {
		System.out.println(pr_seq);
		TimetableAvailable2 timetable = timetableService.selectTimetable(pr_seq);
		String result = gson.toJson(timetable);
		return result;	
	}

	// 운동 프로그램 예약
	@PostMapping("/timetable/{prSeq}/reserv")
	public String reservPrograms(@PathVariable("pr_seq") int pr_seq, 
			@RequestBody String reserv) {
		System.out.println(pr_seq);
		System.out.println(reserv);
		int cnt = timetableService.reservTimetable(pr_seq, reserv);

		if (cnt > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	

	@PostMapping("/mypage/pg/cancel/{pgrseq}")
	public String cancelPrograms(@PathVariable("pgrseq") int pgr_seq) 
			{
		int cnt = timetableService.cancelTimetable(pgr_seq);
		
		if(cnt > 0) {
			return "success";
		}else {
			return "fail";
		}
		
	}
	
	
	@PostMapping("/timetable/cal")
	public String reservPrograms(@RequestBody Calender calender)
	{
		int cnt = calenderService.calenderAdd(calender);
		if(cnt > 0 ) {
			return "success";
		}
		else {
			return "fail";
		}
		
	}
	
	//PT 결제하기

	@PostMapping("/PT/enroll")
	public String enrollPT(@RequestBody HashMap<String,Object> map) {
		programsService.enrollPT(map);
		
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", "success");
		
		Gson gson = new Gson();
		
		String result = gson.toJson(resultMap);
		
		return result;
		
		
	}

	
}



















