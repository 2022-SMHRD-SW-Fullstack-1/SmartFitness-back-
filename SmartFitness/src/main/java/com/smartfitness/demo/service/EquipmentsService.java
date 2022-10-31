package com.smartfitness.demo.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.mapper.EquipmentsMapper;
import com.smartfitness.demo.model.CurrentEquipments;
import com.smartfitness.demo.model.Equipments;

@Service
public class EquipmentsService {

	@Autowired
	EquipmentsMapper equipmentsMapper;
	
	public int addEm(Equipments equipments) {
		return equipmentsMapper.addEm(equipments);
	}

	public CurrentEquipments selectCurrEm(int em_seq) {
		return equipmentsMapper.selectCurrEm(em_seq);
	}

	public int updateEm(int em_seq, int time) {
		return equipmentsMapper.updateEm(em_seq,time);
	}

	public int reservEm(HashMap<String, Object> rsvM) {
		return equipmentsMapper.reservEm(rsvM);
	}

}
