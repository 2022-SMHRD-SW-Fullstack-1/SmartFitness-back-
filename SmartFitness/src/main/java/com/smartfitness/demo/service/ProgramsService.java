package com.smartfitness.demo.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.smartfitness.demo.mapper.ProgramsMapper;
import com.smartfitness.demo.model.CurrentPrograms;
import com.smartfitness.demo.model.Programs;
import com.smartfitness.demo.model.Trainer;
@Service
public class ProgramsService {
	@Autowired
	ProgramsMapper programsMapper;
	//프로그램 추가
	public void addPg(Programs programs) {
		programsMapper.addPg(programs);
	}
	//프로그램 개강
	public void openPg(CurrentPrograms curr) {
		programsMapper.openPg(curr);
	}
	//프로그램 시간표 확인
	public List<HashMap> selectCurrPg(int month) {
		return programsMapper.selectCurrPg(month);
	}
	
	//프로그램 예약하기
	public void reservPg(HashMap<String, Object> map) {
		//current_programs에 인원 +	
		programsMapper.reservPg2(map);
		//예약 내역 삽입
		programsMapper.reservPg(map);
		//current_programs에 상태 변화
		programsMapper.reservPg3(map);
	}
	public void cancelPg(int num) {
		programsMapper.cancelPg2(num);
		programsMapper.cancelPg(num);
	}
	
	//트레이너 정보 확인
	public HashMap<String,Object> confirmT(int num) {
		return programsMapper.confirmT(num);
	}
	public List<HashMap> confirmAllT(int ex) {
		return programsMapper.confirmAllT(ex);
	}
	//트레이너 추가
	public int addTrainer(Trainer trainer) {
		return programsMapper.addTrainer(trainer);
	}
	
	public int rate(HashMap<String, Object> map) {
		return programsMapper.rate(map);
	}
	public void reservPt(HashMap<String, Object> map) {
		programsMapper.reservPt(map);
		
	}
	//PT 시간표 확인
	public List<HashMap> selectCurrPt(int num,int month) {
		return programsMapper.selectCurrPt(num,month);
	}
}



















