package com.smartfitness.demo.mapper;
import java.util.HashMap;
import org.apache.ibatis.annotations.Mapper;
import com.smartfitness.demo.model.CurrentPrograms;
import com.smartfitness.demo.model.Programs;
@Mapper
public interface ProgramsMapper {
	
	public int addPg(Programs program);
	
	public HashMap<String,Object> selectCurrPg(int month);
	
}