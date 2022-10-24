package com.smartfitness.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.model.Equipments;

@Mapper
public interface EquipmentsMapper {

	void equipmentsAdd(Equipments equipments);

}
