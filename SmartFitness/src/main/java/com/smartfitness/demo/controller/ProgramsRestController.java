package com.smartfitness.demo.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.smartfitness.demo.model.Programs;
import com.smartfitness.demo.service.ProgramsService;
@RestController
@RequestMapping("/programs")
public class ProgramsRestController {
	Gson gson = new Gson();
	@Autowired
	ProgramsService programsService;
	
	// 프로그램 추가
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
	
	//프로그램 예약 가능 시간 확인
	@GetMapping("/timetable/{month}")
	public List<HashMap> selectCurrPg(@PathVariable("month") int month ) {
		System.out.println(month);
		
		String result = gson.toJson(programsService.selectCurrPg(month));
		System.out.println(result);
		return programsService.selectCurrPg(month);
	}
	
	//프로그램 예약하기
	@PostMapping("/reserv")
	public String reservPg(@RequestBody HashMap<String,Object> map){
		System.out.println(map.toString());
		int cnt = programsService.reservPg(map);
		if(cnt > 0 ) {
			return "success";
		}
		else {
			return "fail";
		}
	}
	
	//예약 취소하기
	@GetMapping("/cancel/{num}")
	public String cancelPg(@PathVariable("num") int num) {
		
		int cnt = programsService.cancelPg(num);
		if(cnt > 0 ) {
			return "success";
		}
		else {
			return "fail";
		}
	}
}






























