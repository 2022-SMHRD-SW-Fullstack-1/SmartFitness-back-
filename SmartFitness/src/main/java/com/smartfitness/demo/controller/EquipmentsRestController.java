package com.smartfitness.demo.controller;

import java.util.HashMap;
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
import com.smartfitness.demo.service.EquipmentsService;

@RequestMapping("/equipments")
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
	public String addEm(@RequestBody Equipments equipments, @RequestHeader Map<String,String> token) {
		System.out.println(equipments.toString());
		int cnt = equipmentsService.addEm(equipments);
		if (cnt > 0) {
			return "success";
		}
		else {
			return "fail";
		}
	}

	// 운동 기구 예약 가능 시간 확인
	@GetMapping("/timetable/{em_seq}")	
	public String selectCurrEm( @PathVariable("em_seq") int em_seq) {
		System.out.println(em_seq);
		CurrentEquipments curr_em = equipmentsService.selectCurrEm(em_seq);
		String result = gson.toJson(curr_em);
		return result;
	}
	

	// 운동 기구 예약
	@PostMapping("/reserv/{em_seq}/{time}")
	public Map rsvEm(@RequestBody Map<String,String> param, @PathVariable("em_seq") int em_seq,
			@PathVariable("time") int time) throws Exception {
		
		//json받아서 mem_id 추출
		String jsonStr=gson.toJson(param);
		Members user=gson.fromJson(jsonStr, Members.class);
		System.out.println(user.toString());
//		MembersDetail mem_id = membersMapper.findByUserId(user.getMem_id());
		String mem_id=user.getMem_id();
		System.out.println(mem_id);
		System.out.println(em_seq);
		System.out.println(time);
		Map<String,String> result = new HashMap<>();
		int cntT = equipmentsService.updateEm(em_seq, time);
		if (cntT > 0) {
			result.put("CurrEm", "O");//가능한가?
		} else {
			result.put("CurrEm", "X");
		}
		HashMap<String,Object> rsvM = new HashMap<String,Object>();
		rsvM.put("mem_id", mem_id);
		rsvM.put("em_seq", em_seq);
		rsvM.put("time", String.valueOf(time));
		int cntR = equipmentsService.reservEm(rsvM);
		if (cntR>0) {
			result.put("result","O");
		} else {
			result.put("result","X");
		}
		return result;
	}
	
	// 운동 기구 예약 취소

}










