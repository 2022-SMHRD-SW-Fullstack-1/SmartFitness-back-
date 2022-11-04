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

	//운동기구 전체 호출
	public List<Equipments> selectAll() {
		return equipmentsMapper.selectAll();
	}

	//운동기구 분류에 따른 호출
	public List<Equipments> selectF() {
		return equipmentsMapper.selectF();
	}
	public List<Equipments> selectM() {
		return equipmentsMapper.selectM();
	}
	public List<Equipments> selectC() {
		return equipmentsMapper.selectC();
	}


}
