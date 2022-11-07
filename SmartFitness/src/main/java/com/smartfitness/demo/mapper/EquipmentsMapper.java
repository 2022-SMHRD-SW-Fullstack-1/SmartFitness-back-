package com.smartfitness.demo.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.model.Equipments;

@Mapper
public interface EquipmentsMapper {


	//운동 기구 추가
	void addEm(Map<String, Object> newEquipments);

	//운동 기구 수정
	void updateEm(Map<String, Object> equipments);
	
	//운동 기구 확인
	Map<String, Object> selectCurrEm(int em_seq);
	
	//운동 기구 예약
	int reservEmStatus(Map<String, Object> reserv);
	int reservEm(Map<String, Object> reserv);

	//운동 기구 예약 취소
	int cancelEmStatus(Map<String, Object> param);
	int cancelEmReserv(Map<String, Object> param);

	//운동 기구 전체 확인 
	List<Equipments> selectAll();

	List<Equipments> selectF();
	List<Equipments> selectM();
	List<Equipments> selectC();

	List<Map> rsvAll(String mem_id);

	String selectEmName(int em_seq);


}