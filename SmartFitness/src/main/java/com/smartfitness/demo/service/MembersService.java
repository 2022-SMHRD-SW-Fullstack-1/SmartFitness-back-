package com.smartfitness.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.config.auth.Auth;
import com.smartfitness.demo.config.jwt.JwtTokenProvider;
import com.smartfitness.demo.exception.CustomException;
import com.smartfitness.demo.exception.ErrorCode;
import com.smartfitness.demo.mapper.MembersMapper;
import com.smartfitness.demo.model.Members;
import com.smartfitness.demo.model.MembersDetail;

@Service
public class MembersService {

	@Autowired
	MembersMapper membersMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtTokenProvider jwtTokenProvider;

	// 회원가입
	public void join(Members members) throws Exception {
		String mem_id = members.getMem_id();
		String DBmem_id = membersMapper.read(mem_id).getMem_id();
		if (DBmem_id != null) {
			// 아이디 중복일 때, 예외 처리
			throw new CustomException(ErrorCode.MEM_CONFLICT);
		}
		int cnt = membersMapper.join(members);
		if (cnt == 0) {
			// 회원가입 실패
			throw new CustomException(ErrorCode.MEM_BAD_REQUEST);
		}
	}

	// 로그인
	public Auth login(Members members) throws Exception {
		String mem_id = members.getMem_id();
		String mem_pw = members.getMem_pw();
		Map<String, Object> result = new HashMap<>();
		MembersDetail md = membersMapper.findByUserId(mem_id);
		if (md == null) {
			// 아이디를 찾을 수 없음
			throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
		} else if (!passwordEncoder.matches(mem_pw, md.getPassword())) {
			// 잘못된 비밀번호
			throw new CustomException(ErrorCode.WRONG_PW);
		} else {
			String DBmem_id = md.getMem_id();
			String DBmem_auth = md.getMem_auth();

			String DBmem_email = md.getMem_auth();
			String DBmem_name = md.getMem_name();
			String DBmem_phone = md.getMem_phone();
			String accessToken = jwtTokenProvider.createToken(md, "Access-Token");
			Auth auth = new Auth(accessToken, DBmem_id, DBmem_auth, DBmem_email, DBmem_name, DBmem_phone);
			return auth;
		}
	}

	// 회원정보 수정
	public void update(Members members) throws Exception {
		String rawPassword = members.getMem_pw();
		String encPassword = passwordEncoder.encode(rawPassword);
		members.setMem_pw(encPassword);
		int result = membersMapper.update(members);
		if (result == 0) {
			// 아이디 중복
			throw new CustomException(ErrorCode.MEM_CONFLICT);
		}
	}

	// 회원탈퇴
	public void delete(String mem_id, String mem_pw) throws Exception {
		MembersDetail md = membersMapper.findByUserId(mem_id);
		if (!passwordEncoder.matches(mem_pw, md.getPassword())) {
			// 잘못된 비밀번호
			throw new CustomException(ErrorCode.WRONG_PW);
		} else {
			int result = membersMapper.delete(mem_id);
			if (result == 0) {
				// 회원탈퇴 실패
				throw new CustomException(ErrorCode.MEM_BAD_REQUEST);
			}
		}
	}

	// 멤버십 결제
	public void insertInfo(Map<String, Object> paymentsModel) {
		System.out.println(paymentsModel);
		Map<String,Object> map = new HashMap<String,Object>();
		
		String email = (String)paymentsModel.get("buyer_email");
		//email 확인 mapper를 불러오자
		String id = membersMapper.readE(email);
		//금액 뽑아오자
		int amount = (int)(paymentsModel.get("amount"));
		
		System.out.println(id);
		//아이디 넣기
		map.put("mem_id",id);	
		
		System.out.println(map.get("mem_id"));
		//마감기한
		map.put("amount", amount);
		
		System.out.println("a:"+id);
		int num = membersMapper.readM(id);
		
		if(num>0) {
			if(amount==100) {
			membersMapper.updateInfo(map);
			}else if(amount==500) {
			membersMapper.updateInfo2(map);
			}
		}else {
		
		if(amount==100) {
		membersMapper.insertInfo(map);

		}else if(amount==500) {

		membersMapper.insertInfo2(map);
			}
		}
	}
}

////현재 날짜 불러오기(그냥 DB에서 하겠음)
//Date date = new Date();
//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//String date2 = formatter.format(date);
//map.put("date",date2);
