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
import com.smartfitness.demo.model.CurrentPrograms;
import com.smartfitness.demo.model.Programs;
import com.smartfitness.demo.model.Trainer;
import com.smartfitness.demo.service.ProgramsService;
@RestController
@RequestMapping("/programs")
public class ProgramsRestController {
	Gson gson = new Gson();
	@Autowired
	ProgramsService programsService;
	
	
	// 프로그램 등록하기
	@PostMapping("/enroll")
	public String enroll(@RequestBody HashMap<String,Object>map)throws Exception{
		try {
			programsService.enroll(map);
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	// 프로그램 추가
	@PostMapping("/add")
	public String addPg(@RequestBody Programs programs)throws Exception {
		try{
			programsService.addPg(programs);
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	//프로그램 개강
	@PostMapping("/open")
	public String openPg(@RequestBody CurrentPrograms curr)throws Exception {
		try{
			programsService.openPg(curr);
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	//프로그램 예약 가능 시간 확인
	@GetMapping("/timetable/{mem_id}/{month}")
	public String selectCurrPg(@PathVariable("month") int month, @PathVariable("mem_id")String mem_id) {
		//타임테이블을 클릭했을 때 회원의 예약 내역 리스트와 체육관의 수업리스트 내역을 같이 보내줘야해
		String result = gson.toJson(programsService.selectCurrPg(month,mem_id));
		return result;
	}
	
	//프로그램 예약하기
	@PostMapping("timetable/{month}/reserv")
	public String reservPg(@RequestBody HashMap<String,Object> map) throws Exception{
		try {
			programsService.reservPg(map);
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	//예약 취소하기
	@GetMapping("/cancel/{num}")
	public String cancelPg(@PathVariable("num") int num)throws Exception {
		try {
			programsService.cancelPg(num);
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	//여기부터는 PT =================================================
	
	//PT 타임테이블 보내주기(여기서 num은 트레이너 번호)
	@GetMapping("/PT/timetable/{num}/{month}")
	public String selectCurrPt(@PathVariable("num")int num ,@PathVariable("month") int month){
		String result = gson.toJson(programsService.selectCurrPt(num,month));
		return result;
	}
	
	
	//PT 예약
	@PostMapping("/PT/reserv")
	public String reservPt( @RequestBody HashMap<String,Object> map)throws Exception {
		try {
			programsService.reservPt(map);
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	//여기부터는 트레이너 ==============================================
	
	//트레이너 추가
	@PostMapping("/trainer/add")
	public String addTrainer(@RequestBody Trainer trainer)throws Exception {
		
		try {
			programsService.addTrainer(trainer);
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	//트레이너 정보 확인(1명)
	@GetMapping("/trainer/{num}")
	public String confirmT(@PathVariable("num")int num) {
		String result = gson.toJson(programsService.confirmT(num));
		return result;	
	}
	
	//트레이너 정보 확인(ALL) 운동별로(PT,GX, 필라테스)
		@GetMapping("/trainer/rank/{ex}")
		public String confirmAllT(@PathVariable("ex")int ex) {
			String result = gson.toJson(programsService.confirmAllT(ex));
			return result;	
		}
		
	//트레이너 평점 주기
		@PostMapping("/trainer/rate")
		public String rate(@RequestBody HashMap<String,Object> map)throws Exception{
			int cnt = programsService.rate(map);
			if(cnt > 0 ) {
				return "success";
			}
			else {
				return "fail";
		}	
	}		
}