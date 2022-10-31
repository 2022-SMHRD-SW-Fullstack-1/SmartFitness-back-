package com.smartfitness.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.model.Members;
import com.smartfitness.demo.model.MembersDetail;

@Mapper
public interface MembersMapper {

	MembersDetail findByUserId(String mem_id);

	int join(Members members);
	
	public Members login(Members members);

}
