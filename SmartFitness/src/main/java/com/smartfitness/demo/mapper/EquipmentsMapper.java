package com.smartfitness.demo.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.model.Equipments;

@Mapper
public interface EquipmentsMapper {

	int equipmentsAdd(Equipments equipments);

}