package com.smartfitness.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.config.jwt.JwtTokenProvider;
import com.smartfitness.demo.exception.CustomException;
import com.smartfitness.demo.exception.ErrorCode;
import com.smartfitness.demo.mapper.EquipmentsMapper;
import com.smartfitness.demo.mapper.TokenMapper;
import com.smartfitness.demo.model.Equipments;

@Service
public class EquipmentsService {

	@Autowired
	EquipmentsMapper equipmentsMapper;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	TokenMapper tokenMapper;

	// 운동 기구 추가
	public void addEm(Map<String, Object> newEquipments) throws Exception {
		int result = equipmentsMapper.addEm(newEquipments);
		equipmentsMapper.addEmCurr(newEquipments);
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
	public List<HashMap> selectCurrEm(int em_seq) throws Exception {
		List<HashMap> result = equipmentsMapper.selectCurrEm(em_seq);
		if (result==null) {
			throw new CustomException(ErrorCode.EM_NOT_FOUND);
		}
		return result;
	}

	// 운동 기구 예약
	public void reservEm(Map<String, Object> reserv) throws Exception {
		
		String token=(String)reserv.get("token");
		System.out.println("토큰 값 추출"+token);
		String mem_token_id = jwtTokenProvider.getUserIDFromToken(token);
		System.out.println("토큰에서 아이디 추출 : "+jwtTokenProvider.getUserIDFromToken(token));
		String mem_token = tokenMapper.getToken(mem_token_id);
		System.out.println("DB에서 토큰 추출"+mem_token);
		String token_sub = jwtTokenProvider.getUserIDFromToken(mem_token);
		System.out.println("DB토큰에서 SUB추출 : "+token_sub);
		if(token_sub.equals(token)) {
			System.out.println("토큰값일치");
		}
		//else{ 추가하면 됨
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
		
		String token=(String)param.get("token");
		System.out.println("토큰 값 추출"+token);
		String mem_token_id = jwtTokenProvider.getUserIDFromToken(token);
		System.out.println("토큰에서 아이디 추출 : "+jwtTokenProvider.getUserIDFromToken(token));
		String mem_token = tokenMapper.getToken(mem_token_id);
		System.out.println("DB에서 토큰 추출"+mem_token);
		String token_sub = jwtTokenProvider.getUserIDFromToken(mem_token);
		System.out.println("DB토큰에서 SUB추출 : "+token_sub);
		if(token_sub.equals(token)) {
			System.out.println("토큰값일치");
		}
		//else{ 추가하면 됨
		
		
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
	public List<Map> rsvAll(String mem_id) throws Exception {
		List<Map> emList = equipmentsMapper.rsvAll(mem_id);
		if(emList.size()==0) {
			//예약내역이 없다
			throw new CustomException(ErrorCode.MEM_BK_NOT_FOUND);		
		}
		for (int i=0; i<emList.size();i++) {
			int em_seq = (int) emList.get(i).get("em_seq");
			emList.get(i).put("em_name", equipmentsMapper.selectEmName(em_seq));
		}
		return emList;
	}

}

































