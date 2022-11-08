package com.smartfitness.demo.service;
import java.util.ArrayList;
import java.util.ArrayList;
//hashmap를 담기 위해서 선언
import java.util.HashMap;
//실질적인 값들을 넣어줄 hashmap
import java.util.LinkedHashMap;
//추가적으로 hashmap과 linkedhashmap 비교를 해보겠습니다.
import java.util.Map.Entry;
//hashmap의 key 값을 가져와서 출력합니다.
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.smartfitness.demo.mapper.ProgramsMapper;
import com.smartfitness.demo.model.CurrentPrograms;
import com.smartfitness.demo.model.Equipments;
import com.smartfitness.demo.model.Programs;
import com.smartfitness.demo.model.Trainer;
@Service
public class ProgramsService {
	@Autowired
	ProgramsMapper programsMapper;
	
	//클래스 정보 보내주기
	public HashMap sendC(int num){
		return programsMapper.sendC(num);
	}
	
	// 고객이 프로그램 결제하기
	public void enroll(HashMap<String, Object> map) {
		programsMapper.enroll(map);
	}
	
	//프로그램 추가
	public void addPg(Programs programs) {
		programsMapper.addPg(programs);
	}
	//프로그램 개강
	public void openPg(CurrentPrograms curr) {
		programsMapper.openPg(curr);
	}
	//프로그램 시간표 확인
	public List<HashMap> selectCurrPg(HashMap<String, Object> map) {
		List<HashMap> res = programsMapper.selectCurrPg(map);
//		map.put("")
		
		return res;
	}
	
	//나의 프로그램 예약 내역 확인
	public List<Map> reservMy(String mem_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map> pgList = programsMapper.reservMy(mem_id);
		
		for(int i=0; i<pgList.size();i++) {
			int seq = (int)pgList.get(i).get("pg_seq");
			//시퀀스 번호 통해서 프로그램 이름 따오기
			String pg_name = programsMapper.reservMy2(seq);
			pgList.get(i).put("pg_name", pg_name);
		}
		return pgList;
	}
	
	//프로그램 예약하기
	public void reservPg(HashMap<String, Object> map) {
		
		
		//예약 내역 삽입
		programsMapper.reservPg(map);
		
		//current_programs에 인원 +
		programsMapper.reservPg2(map);
		
		//current_programs에 상태 변화
		programsMapper.reservPg3(map);
	}
	
	
	
	
	public void cancelPg(int num,String mem_id) {
		//예약 내역 취소
		programsMapper.cancelPg(num,mem_id);
		
		//current_programs에 인원 -
		programsMapper.cancelPg2(num);
		
//		//current_programs에 상태 변화
//		programsMapper.cancelPg3(num);
	}
	
	//트레이너 정보 확인(1명)
	public HashMap<String,Object> confirmT(int num) {
		return programsMapper.confirmT(num);
	}
	
	//트레이너 정보 확인(All)
	public List<HashMap> confirmAllT(int ex) {
		return programsMapper.confirmAllT(ex);
	}
	//트레이너 추가
	public int addTrainer(Trainer trainer) {
		return programsMapper.addTrainer(trainer);
	}
	
	//트레이너 평점 매기기
	public int rate(HashMap<String, Object> map) {
		return programsMapper.rate(map);
	}
	
	// PT 예약하기
	public void reservPt(HashMap<String, Object> map) {
		programsMapper.reservPt(map);
		
	}
	//PT 시간표 확인
	public List<HashMap> selectCurrPt(int month) {
		return programsMapper.selectCurrPt(month);
	}

	public List<HashMap> sendMy(String mem_id) {
		return programsMapper.sendMy(mem_id);
	}

	



	
	
	
}



















