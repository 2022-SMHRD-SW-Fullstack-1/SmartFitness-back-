package com.smartfitness.demo.mapper;

import java.util.HashMap;


import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.model.CurrentEquipments;
import com.smartfitness.demo.model.Equipments;
import com.smartfitness.demo.model.MembersDetail;

@Mapper
public interface EquipmentsMapper {

	CurrentEquipments selectCurrEm(int em_seq);

	int reservEq(HashMap<String, Object> rsvM);

	int AddEm(Equipments equipments);

	int rsvEm(int em_seq, int time);

}