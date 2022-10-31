package com.smartfitness.demo.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smartfitness.demo.mapper.ProgramsMapper;
import com.smartfitness.demo.model.CurrentPrograms;
import com.smartfitness.demo.model.Programs;
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
	
	
	
}