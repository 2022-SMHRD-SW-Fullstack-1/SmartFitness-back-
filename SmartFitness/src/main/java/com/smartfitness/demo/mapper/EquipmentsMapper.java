package com.smartfitness.demo.mapper;

import java.util.HashMap;


import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.model.CurrentEquipments;
import com.smartfitness.demo.model.Equipments;

@Mapper
public interface EquipmentsMapper {

	CurrentEquipments selectCurrEm(int em_seq);

	int reservEm(HashMap<String, Object> reservEm);

	int addEm(Equipments equipments);

	int updateEm(int em_seq, int time);

}