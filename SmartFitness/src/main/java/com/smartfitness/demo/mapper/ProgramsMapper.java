package com.smartfitness.demo.mapper;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.smartfitness.demo.model.CurrentPrograms;
import com.smartfitness.demo.model.Programs;
@Mapper
public interface ProgramsMapper {
	
	
	public int addPg(Programs programs);
	
	public List<HashMap> selectCurrPg(int month);
	
	public int reservPg(HashMap<String,Object> map);
	
	public int reservPg2(HashMap<String,Object> map);

	public int cancelPg(int num);
	
	
	
}