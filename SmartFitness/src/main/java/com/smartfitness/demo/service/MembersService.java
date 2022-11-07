package com.smartfitness.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.mapper.MembersMapper;
import com.smartfitness.demo.model.Members;

@Service
public class MembersService {
	
	@Autowired
	MembersMapper membersMapper;
	
	//회원가입
	public void join(Members members) {
		membersMapper.join(members);
	}
	
	//로그인
	public Members login(Members members) {
		return membersMapper.login(members);
	}

	//회원정보 수정
	public void update(Members members) {
		membersMapper.update(members);
	}

	public void delete(String mem_id) {
		membersMapper.delete(mem_id);
		
	}
}

