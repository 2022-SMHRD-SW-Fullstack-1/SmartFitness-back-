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
	
	public int addPg(Programs programs) {
		return programsMapper.addPg(programs);
	}
	public List<HashMap> selectCurrPg(int month) {
		return programsMapper.selectCurrPg(month);
	}
	public int reservPg(HashMap<String, Object> map) {
		//current_programs에 인원 +
		programsMapper.reservPg2(map);
		return programsMapper.reservPg(map);
	}
	public int cancelPg(int num) {
		return programsMapper.cancelPg(num);
	}
	
	//트레이너 정보 확인
	public HashMap<String,Object> confirmT(int num) {
		return programsMapper.confirmT(num);
	}
	public List<HashMap> confirmAllT(int ex) {
		return programsMapper.confirmAllT(ex);
	}
	public int addTrainer(Trainer trainer) {
		return programsMapper.addTrainer(trainer);
	}
	public int openPg(CurrentPrograms curr) {
		return programsMapper.openPg(curr);
	}
	public int rate(HashMap<String, Object> map) {
		return programsMapper.rate(map);
	}
	
	
	
}