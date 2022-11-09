package com.smartfitness.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.exception.CustomException;
import com.smartfitness.demo.exception.ErrorCode;
import com.smartfitness.demo.mapper.EquipmentsMapper;
import com.smartfitness.demo.model.Equipments;

@Service
public class EquipmentsService {

	@Autowired
	EquipmentsMapper equipmentsMapper;

	// 운동 기구 추가
	public void addEm(Map<String, Object> newEquipments) throws Exception {
		int result = equipmentsMapper.addEm(newEquipments);
		if (result == 0) {
			// 운동기구 정보 전달 오류
			throw new CustomException(ErrorCode.EM_BAD_REQUEST);
		}
	}

	// 운동 기구 수정
	public void updateEm(Map<String, Object> equipments) throws Exception {
		int result = equipmentsMapper.updateEm(equipments);
		if (result == 0) {
			// 운동기구 정보 전달 오류
			throw new CustomException(ErrorCode.EM_BAD_REQUEST);
		}
	}

	// 운동 기구 예약 가능한지 확인
	public Map<String, Object> selectCurrEm(int em_seq) throws Exception {
		Map<String, Object> result = equipmentsMapper.selectCurrEm(em_seq);
		if (result.get("current_equipment")==null) {
			throw new CustomException(ErrorCode.EM_NOT_FOUND);
		}
		return result;
	}

	// 운동 기구 예약
	public void reservEm(Map<String, Object> reserv) throws Exception {
		Map<String, Object> result = new HashMap<>();
		result.put("기구예약가능", equipmentsMapper.reservEmStatus(reserv));
		System.out.println(result);
		if(result.get("기구예약가능")==null) {
			//운동기구 예약 불가
			throw new CustomException(ErrorCode.EM_BK_NOT_FOUND);
		}
		result.put("회원예약가능", equipmentsMapper.reservEm(reserv));
		if(result.get("회원예약가능")==null) {
			//회원 예약 불가
			throw new CustomException(ErrorCode.BK_CONFLICT);
		}
	}

	// 운동 기구 예약 취소
	public void cancelEm(Map<String, Object> param) throws Exception {
		Map<String, Object> result = new HashMap<>();
		int resultEm= equipmentsMapper.cancelEmStatus(param);
		if(resultEm==0) {
			//운동기구 불러오지 못함
			throw new CustomException(ErrorCode.EM_BK_NOT_FOUND);
		}
		int resultMem=equipmentsMapper.cancelEmReserv(param);
		if(resultMem==0) {
			//회원 예약 내역을 찾을 수 없음
			throw new CustomException(ErrorCode.MEM_BK_NOT_FOUND);
		}
	}

	// 운동기구 전체 호출
	// 운동기구 분류에 따른 호출
	public Map<String, Object> selectAll() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("all", equipmentsMapper.selectAll());
		if(result.get("all")==null) {
			//운동기구가 없을 때 예외 발생
			throw new CustomException(ErrorCode.EM_NOT_FOUND);
		}
		result.put("F", equipmentsMapper.selectF());
		result.put("M", equipmentsMapper.selectM());
		result.put("C", equipmentsMapper.selectC());
		return result;
	}

	// 운동 기구 예약 내역 확인
	public List<Map<String,Object>> rsvAll(String mem_id) throws Exception {
		List<Map<String,Object>> emList = equipmentsMapper.rsvAll(mem_id);
		for (int i = 0; i < emList.size(); i++) {
			if(emList.get(0).get("reserv_em_seq")==null) {
				//예약내역이 없다
				throw new CustomException(ErrorCode.MEM_BK_NOT_FOUND);
			}
			int em_seq = (int) emList.get(i).get("em_seq");
			emList.get(i).put("em_name", equipmentsMapper.selectEmName(em_seq));
		}
		return emList;
	}

}
