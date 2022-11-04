package com.smartfitness.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.smartfitness.demo.mapper.EquipmentsMapper;
import com.smartfitness.demo.mapper.MembersMapper;
import com.smartfitness.demo.model.CurrentEquipments;
import com.smartfitness.demo.model.Equipments;
import com.smartfitness.demo.model.Members;
import com.smartfitness.demo.model.QnaQuestion;
import com.smartfitness.demo.model.ReservEquipments;
import com.smartfitness.demo.service.EquipmentsService;

@RequestMapping("/equipments/*")
@RestController
public class EquipmentsRestController {

	Gson gson = new Gson();

	@Autowired
	EquipmentsMapper equipmentsMapper;

	@Autowired
	EquipmentsService equipmentsService;

	@Autowired
	MembersMapper membersMapper;
	
	// 운동 기구 정보 추가
	@PostMapping("/add")
	public String addEm(@RequestBody Map<String, Object> newEquipments) throws Exception{
		System.out.println(newEquipments.toString());
		try {
			equipmentsService.addEm(newEquipments);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			 return "fail";
		}
	}
	
	// 전체 기구 확인
	@GetMapping("/all")
	public String selectAll() throws Exception{
		
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Equipments> emList = equipmentsService.selectAll();
			map.put("all", emList);
			// 프리웨이트 기구 확인
			List<Equipments> emFList = equipmentsService.selectF();
			map.put("F", emFList);
			// 머신 기구 확인
			List<Equipments> emMList = equipmentsService.selectM();
			map.put("M", emMList);
			// 카디오 기구 확인
			List<Equipments> emCList = equipmentsService.selectC();
			map.put("C", emCList);
			String result = gson.toJson(map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	
	
	// 운동 기구 정보 수정
	@PostMapping("/update")
	public String updateEm(@RequestBody Map<String, Object> equipments) throws Exception{
		System.out.println(equipments.toString());
		try {
			equipmentsService.updateEm(equipments);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			 return "fail";
		}
	}
	

	// 운동 기구 예약 가능 시간 확인
	@GetMapping("/timetable/{em_seq}")	
	public String selectCurrEm( @PathVariable("em_seq") int em_seq) throws Exception{
		System.out.println(em_seq);
		try {
		CurrentEquipments curr_em = equipmentsService.selectCurrEm(em_seq);
		// {예약시간1:Y,예약시간2:N} 으로 반환
		String result = gson.toJson(curr_em);
		return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	

	// 운동 기구 예약
	@PostMapping("/reserv/{em_seq}/{time}")
	public String rsvEm(@RequestBody Map<String,Object> reserv, @PathVariable("em_seq") int em_seq,
			@PathVariable("time") int time) throws Exception {
		
		//param에는 mem_id만 존재, param에 다른 값들을 추가
		System.out.println(reserv); // 값 확인
		
		reserv.put("em_seq", em_seq);
		reserv.put("time",time);
		
		System.out.println(reserv); // 값 확인
		
		try { // 오류가 발생하지 않으면 success 반환
			equipmentsService.reservEm(reserv);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	// 운동 기구 예약 취소 => 운동기구 상태, 맴버 예약 둘 다 취소
	@PostMapping("/cancel")
	public String cancelEm(@RequestBody Map<String,Object> param)  throws Exception{
		//param에 mem_id, em_seq, time이 있다.
		System.out.println(param);
		try { // 오류가 발생하지 않으면 success 반환
			equipmentsService.cancelEm(param);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
}










