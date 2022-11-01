package com.smartfitness.demo.mapper;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.google.gson.JsonElement;
import com.smartfitness.demo.model.CurrentPrograms;
import com.smartfitness.demo.model.Programs;
import com.smartfitness.demo.model.Trainer;
@Mapper
public interface ProgramsMapper {
	
	
	public int addPg(Programs programs);
	
	public List<HashMap> selectCurrPg(int month);
	
	public int reservPg(HashMap<String,Object> map);
	
	public int reservPg2(HashMap<String,Object> map);

	public int cancelPg(int num);
	
	//트레이너 정보 확인
	public HashMap<String,Object> confirmT(int num);

	public List<HashMap> confirmAllT(int ex);

	public int addTrainer(Trainer trainer);

	public int openPg(CurrentPrograms curr);

	public int rate(HashMap<String, Object> map);
	
	
	
}