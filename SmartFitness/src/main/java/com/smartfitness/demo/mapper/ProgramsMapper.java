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
	
	public List<HashMap> selectCurrPg(HashMap<String, Object> map);
	
	//나의 예약 내역 확인하기
	public List<Map> reservMy(String mem_id);
	public String reservMy2(int pg_seq);
	
	public int reservPg(HashMap<String,Object> map);
	public int reservPg2(HashMap<String,Object> map);
	public int reservPg3(HashMap<String, Object> map);

	
	//프로그램 취소
	public int cancelPg(int num,String mem_id);
	public int cancelPg2(int num);
	public int cancelPg3(int num);
	
	//PT 취소
	public int cancelPt(HashMap<String, Object> map);
	
	//트레이너 정보 확인
	public HashMap<String,Object> confirmT(String name);
	

	public List<HashMap> confirmAllT();

	public int addTrainer(Trainer trainer);

	public int openPg(CurrentPrograms curr);

	public int rate(HashMap<String, Object> map);

	public void reservPt(HashMap<String, Object> map);

	public List<HashMap> selectCurrPt(int month);

	public HashMap sendC(int num);

	public List<HashMap> sendMy(String mem_id);

	public List<HashMap> findMy(HashMap<String, Object> map);
	
	//예약하려는 프로그램이 존재하는지
	public HashMap read(int num);
	//예약하려는 프로그램의 정원이 초과했는지
	public int read2(int num2);

	
}