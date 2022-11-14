package com.smartfitness.demo.service;
import java.util.ArrayList;
import java.util.ArrayList;
//hashmap를 담기 위해서 선언
import java.util.HashMap;
//실질적인 값들을 넣어줄 hashmap
import java.util.LinkedHashMap;
//추가적으로 hashmap과 linkedhashmap 비교를 해보겠습니다.
import java.util.Map.Entry;
//hashmap의 key 값을 가져와서 출력합니다.
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.smartfitness.demo.config.jwt.JwtTokenProvider;
import com.smartfitness.demo.exception.CustomException;
import com.smartfitness.demo.exception.ErrorCode;
import com.smartfitness.demo.mapper.ProgramsMapper;
import com.smartfitness.demo.mapper.TokenMapper;
import com.smartfitness.demo.model.CurrentPrograms;
import com.smartfitness.demo.model.Equipments;
import com.smartfitness.demo.model.Programs;
import com.smartfitness.demo.model.Trainer;
@Service
public class ProgramsService {
	@Autowired
	ProgramsMapper programsMapper;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	TokenMapper tokenMapper;
	
	//클래스 정보 보내주기
	public HashMap sendC(int num){
		return programsMapper.sendC(num);
	}
	
	// 고객이 프로그램 결제하기
	public void enroll(HashMap<String, Object> map) {
		programsMapper.enroll(map);
	}
	
	//프로그램 추가
	public void addPg(Programs programs) {
		programsMapper.addPg(programs);
	}
	//프로그램 개강
	public void openPg(CurrentPrograms curr) {
		programsMapper.openPg(curr);
	}
	//프로그램 시간표 확인
	public List<HashMap> selectCurrPg(HashMap<String, Object> map) {
		List<HashMap> res = programsMapper.selectCurrPg(map);

		
		return res;
	}
	
	//나의 프로그램 예약 내역 확인
	public List<Map> reservMy(String mem_id) {
		
		List<Map> pgList = programsMapper.reservMy(mem_id);
		
		for(int i=0; i<pgList.size();i++) {
			int seq = (int)pgList.get(i).get("pg_seq");
			//시퀀스 번호 통해서 프로그램 이름 따오기
			String pg_name = programsMapper.reservMy2(seq);
			pgList.get(i).put("pg_name", pg_name);
		}
		System.out.println(pgList.size());
		if(pgList.size()==0) {
			throw new CustomException(ErrorCode.MEM_BK_NOT_FOUND);
		}
		
		return pgList;
	}
	
	//프로그램 예약하기
	public void reservPg(HashMap<String, Object> map) throws Exception{
		
		String token=(String)map.get("token");
		System.out.println("토큰 값 추출"+token);
		String mem_token_id = jwtTokenProvider.getUserIDFromToken(token);
		System.out.println("토큰에서 아이디 추출 : "+jwtTokenProvider.getUserIDFromToken(token));
		String mem_token = tokenMapper.getToken(mem_token_id);
		System.out.println("DB에서 토큰 추출"+mem_token);
		String token_sub = jwtTokenProvider.getUserIDFromToken(mem_token);
		System.out.println("DB토큰에서 SUB추출 : "+token_sub);
		if(token_sub.equals(token)) {
			System.out.println("토큰값일치");
		}
		//else{ 추가하면 됨
		
		System.out.println(map.get("curr_pg_seq"));
		
//		//프로그램이 존재하지 않을 때 에러
		HashMap num = programsMapper.read((int)map.get("curr_pg_seq"));
		if(num==null) {
			throw new CustomException(ErrorCode.PG_NOT_FOUND);
		}
		
		//정원 초과했을 때 에러
		int num2 = programsMapper.read2((int)map.get("curr_pg_seq"));
		System.out.println(num2);
		if(num2==0) {
			throw new CustomException(ErrorCode.PG_MAX);
		}

		//예약 내역 삽입
		programsMapper.reservPg(map);
		
		//current_programs에 인원 +
		programsMapper.reservPg2(map);
		
		//current_programs에 상태 변화
		programsMapper.reservPg3(map);
	}
	
	
	
	
	public void cancelPg(int num,String mem_id) {
		
		//예약 내역 취소
		programsMapper.cancelPg(num,mem_id);
		
		//current_programs에 인원 -
		programsMapper.cancelPg2(num);
		
//		//current_programs에 상태 변화
//		programsMapper.cancelPg3(num);
	}
	

	
	// PT 예약하기
	public void reservPt(HashMap<String, Object> map) {
		
		String token=(String)map.get("token");
		System.out.println("토큰 값 추출"+token);
		String mem_token_id = jwtTokenProvider.getUserIDFromToken(token);
		System.out.println("토큰에서 아이디 추출 : "+jwtTokenProvider.getUserIDFromToken(token));
		String mem_token = tokenMapper.getToken(mem_token_id);
		System.out.println("DB에서 토큰 추출"+mem_token);
		String token_sub = jwtTokenProvider.getUserIDFromToken(mem_token);
		System.out.println("DB토큰에서 SUB추출 : "+token_sub);
		if(token_sub.equals(token)) {
			System.out.println("토큰값일치");
		}
		//else{ 추가하면 됨
		
		
		programsMapper.reservPt(map);
		
	}
	//PT 시간표 확인
	public List<HashMap> selectCurrPt(int month) {
		return programsMapper.selectCurrPt(month);
	}
	
	public List<HashMap> sendMy(String mem_id) {
		return programsMapper.sendMy(mem_id);
	}
	
	//PT 취소하기
	public int cancelPt(HashMap<String,Object> map) {
		
		String token=(String)map.get("token");
		System.out.println("토큰 값 추출"+token);
		String mem_token_id = jwtTokenProvider.getUserIDFromToken(token);
		System.out.println("토큰에서 아이디 추출 : "+jwtTokenProvider.getUserIDFromToken(token));
		String mem_token = tokenMapper.getToken(mem_token_id);
		System.out.println("DB에서 토큰 추출"+mem_token);
		String token_sub = jwtTokenProvider.getUserIDFromToken(mem_token);
		System.out.println("DB토큰에서 SUB추출 : "+token_sub);
		if(token_sub.equals(token)) {
			System.out.println("토큰값일치");
		}
		//else{ 추가하면 됨
		
		
		return programsMapper.cancelPt(map);
	}
	
	// 여기서부터 트레이너!!
	//트레이너 정보 확인(1명)
	public HashMap<String,Object> confirmT(String name) {
		return programsMapper.confirmT(name);
	}
	
	//트레이너 정보 확인(All)
	public List<HashMap> confirmAllT() {
		return programsMapper.confirmAllT();
	}
	//트레이너 추가
	public int addTrainer(Trainer trainer) {
		return programsMapper.addTrainer(trainer);
	}
	
	//트레이너 평점 매기기
	public void rate(HashMap<String, Object> map)throws CustomException {
		
		String token=(String)map.get("token");
		System.out.println("토큰 값 추출"+token);
		String mem_token_id = jwtTokenProvider.getUserIDFromToken(token);
		System.out.println("토큰에서 아이디 추출 : "+jwtTokenProvider.getUserIDFromToken(token));
		String mem_token = tokenMapper.getToken(mem_token_id);
		System.out.println("DB에서 토큰 추출"+mem_token);
		String token_sub = jwtTokenProvider.getUserIDFromToken(mem_token);
		System.out.println("DB토큰에서 SUB추출 : "+token_sub);
		if(token_sub.equals(token)) {
			System.out.println("토큰값일치");
		}
		//else{ 추가하면 됨
		
		
		
		int cnt2 = programsMapper.rate2(map);
		
		
	
		if(cnt2>0) {
			throw new CustomException(ErrorCode.TR_DU);
		}
		int cnt = programsMapper.rate(map);
		
	}
	
	public int rating(int tr) {
		return programsMapper.rating(tr);
	}

	public int rate2(HashMap<String, Object> map) {
		return programsMapper.rate2(map);
	}

}