package com.smartfitness.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.mapper.EquipmentsMapper;
import com.smartfitness.demo.model.CurrentEquipments;
import com.smartfitness.demo.model.Equipments;

@Service
public class EquipmentsService {

	@Autowired
	EquipmentsMapper equipmentsMapper;
	
	
	//운동 기구 추가
	public void addEm(Map<String, Object> newEquipments) {
		equipmentsMapper.addEm(newEquipments);
	}
	
	//운동 기구 수정
	public void updateEm(Map<String, Object> equipments) {
		equipmentsMapper.updateEm(equipments);
	}
	
	//운동 기구 예약 가능한지 확인
	public CurrentEquipments selectCurrEm(int em_seq) {
		return equipmentsMapper.selectCurrEm(em_seq);
	}
	
	//운동 기구 예약
	public void reservEm(Map<String, Object> reserv) {
		equipmentsMapper.reservEmStatus(reserv);
		equipmentsMapper.reservEm(reserv);
	}

	//운동 기구 예약 취소
	public void cancelEm(Map<String, Object> param) {
		equipmentsMapper.cancelEmStatus(param);
		equipmentsMapper.cancelEmReserv(param);
	}

	//운동 기구 예약 내역 확인
	public List<Map> rsvAll(String mem_id) {
		List<Map> emList = equipmentsMapper.rsvAll(mem_id);
		for(int i=0; i<emList.size();i++) {
			int em_seq = (int)emList.get(i).get("em_seq");
			emList.get(i).put("em_name", equipmentsMapper.selectEmName(em_seq));
		}
		return emList;
	}


}
