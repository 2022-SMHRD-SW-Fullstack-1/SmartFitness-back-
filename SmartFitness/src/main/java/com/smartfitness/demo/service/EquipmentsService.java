package com.smartfitness.demo.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.mapper.EquipmentsMapper;
import com.smartfitness.demo.model.CurrentEquipments;
import com.smartfitness.demo.model.Equipments;
import com.smartfitness.demo.model.MembersDetail;

@Service
public class EquipmentsService {

	@Autowired
	EquipmentsMapper equipmentsMapper;
	
	public int AddEm(Equipments equipments) {
		return equipmentsMapper.AddEm(equipments);
	}

	public int reservMembers(HashMap<String, Object> rsvM) {
		return equipmentsMapper.reservEq(rsvM);
	}

	public CurrentEquipments selectCurrEm(int em_seq) {
		return equipmentsMapper.selectCurrEm(em_seq);
	}

	public int rsvEm(int em_seq, int time) {
		return equipmentsMapper.rsvEm(em_seq,time);
	}

}
