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
@RequestMapping("/programs")
public class ProgramsRestController {

	Gson gson = new Gson();

	@Autowired
	ProgramsService programsService;

	
	
	@Autowired
	CalenderService calenderService;

	// 프로그램 정보 추가
	@PostMapping("/add")
	public String addPg(@RequestBody Programs programs) {
		System.out.println(programs.toString());
		int cnt = programsService.addPg(programs);
		if(cnt > 0 ) {
			return "success";
		}
		else {
			return "fail";
		}

	}
	
	
	
	
	//프로그램 예약하기
	@PostMapping("/reserv")
	public String reservPg(@RequestBody Calender calender)
	{	
		calenderService.enrollPT(calender);
		int cnt = calenderService.calenderAdd(calender);
		if(cnt > 0 ) {
			return "success";
		}
		else {
			return "fail";
		}
		
	}
	
	//프로그램 취소하기
	@GetMapping("/cancel/{cal_seq}")
	public String cancelPg(@PathVariable("cal_seq") int num) {
		calenderService.reset(num);
		int cnt = calenderService.cancelPrograms(num);
		
		if(cnt > 0 ) {
			return "success";
		}
		else {
			return "fail";
		}
		
	}
	
//	//PT 결제하기(잔여 횟수)
//	@PostMapping("/PT/enroll")
//	public String enrollPT(@RequestBody HashMap<String,Object> map) {
//		programsService.enrollPT(map);
//		
//		HashMap<String,Object> resultMap = new HashMap<String,Object>();
//		resultMap.put("result", "success");
//		
//		Gson gson = new Gson();
//		
//		String result = gson.toJson(resultMap);
//		
//		return result;		
//	}	
}



















