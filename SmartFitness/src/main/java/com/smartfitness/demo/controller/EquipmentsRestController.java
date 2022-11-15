package com.smartfitness.demo.controller;

import static org.junit.Assert.assertThrows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.smartfitness.demo.mapper.EquipmentsMapper;
import com.smartfitness.demo.mapper.MembersMapper;
import com.smartfitness.demo.model.Equipments;
import com.smartfitness.demo.model.Members;
import com.smartfitness.demo.model.QnaQuestion;
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
	public void addEm(@RequestBody Map<String, Object> newEquipments) throws Exception {
		System.out.println("추가하는 운동기구 정보:" + newEquipments.toString());
		equipmentsService.addEm(newEquipments);

	}

	// 전체 기구 확인
	@GetMapping("/all")
	public String selectAll() throws Exception {

		// 전체 기구 확인
		Map<String, Object> map = equipmentsService.selectAll();
		String result = gson.toJson(map);
		return result;

	}

	// 운동 기구 정보 수정
	@PostMapping("/update")
	public void updateEm(@RequestBody Map<String, Object> equipments) throws Exception {
		System.out.println("입력한 운동기구 정보:" + equipments.toString());
		equipmentsService.updateEm(equipments);
	}

	// 운동 기구 예약 가능 시간 확인
	@GetMapping("/timetable/{em_seq}")
	public ResponseEntity<?> selectCurrEm(@PathVariable("em_seq") int em_seq) throws Exception {
		System.out.println("예약 확인할 기구"+em_seq);
//		Map<String, Object> curr_em = new HashMap<String, Object>();
//		curr_em.put("current_equipment", equipmentsService.selectCurrEm(em_seq));
		// {예약시간1:Y,예약시간2:N} 으로 반환
		List<HashMap> res =  equipmentsService.selectCurrEm(em_seq);
		return ResponseEntity.ok()
				.body(res);
	}

	// 운동 기구 예약
	@PostMapping("/reserv/{em_seq}/{time}")
	public void rsvEm(@RequestBody Map<String, Object> reserv, @PathVariable("em_seq") int em_seq,
			@PathVariable("time") int time) throws Exception {

		// param에는 mem_id만 존재, param에 다른 값들을 추가
		System.out.println("???"+reserv); // 값 확인
System.out.println(em_seq);
System.out.println(time);
		reserv.put("em_seq", em_seq);
		reserv.put("time", time);

		System.out.println(reserv); // 값 확인
		equipmentsService.reservEm(reserv);

	}

	// mem_id의 운동 기구 예약 현황

	@PostMapping("/reserv/all")
	public String rsvAll(@RequestBody Map<String, Object> param) throws Exception {
		String mem_id = (String) param.get("mem_id");
		System.out.println("멤버 아이디 : " + mem_id);
		String result = gson.toJson(equipmentsService.rsvAll(mem_id));
		return result;
	}

	// 운동 기구 예약 취소 => 운동기구 상태, 맴버 예약 둘 다 취소
	@PostMapping("/cancel")
	public void cancelEm(@RequestBody Map<String, Object> param) throws Exception {
		// param에 mem_id, em_seq, time이 있다.
		System.out.println(param);
		equipmentsService.cancelEm(param);
	}
}
