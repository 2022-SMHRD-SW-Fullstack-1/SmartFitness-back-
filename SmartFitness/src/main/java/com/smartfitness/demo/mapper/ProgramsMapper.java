package com.smartfitness.demo.mapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.google.gson.JsonElement;
import com.smartfitness.demo.model.CurrentPrograms;
import com.smartfitness.demo.model.Programs;
import com.smartfitness.demo.model.Trainer;
@Mapper
public interface ProgramsMapper {
	
	public void enroll(HashMap<String, Object> map);
	
	public int addPg(Programs programs);
	
	public List<HashMap> selectCurrPg(int month);
	
	//나의 예약 내역 확인하기
	public List<Map> reservMy(String mem_id);
	public String reservMy2(int pg_seq);
	
	public int reservPg(HashMap<String,Object> map);
	public int reservPg2(HashMap<String,Object> map);
	public int reservPg3(HashMap<String, Object> map);

	public int cancelPg(int num,String mem_id);
	public void cancelPg2(int num);
	public void cancelPg3(int num);
	
	//트레이너 정보 확인
	public HashMap<String,Object> confirmT(int num);
	

	public List<HashMap> confirmAllT(int ex);

	public int addTrainer(Trainer trainer);

	public int openPg(CurrentPrograms curr);

	public int rate(HashMap<String, Object> map);

	public void reservPt(HashMap<String, Object> map);

	public List<HashMap> selectCurrPt(int num,int month);

	public HashMap sendC(int num);

	public List<HashMap> sendMy(String mem_id);

	public List<HashMap> findMy(HashMap<String, Object> map);

	

	


}