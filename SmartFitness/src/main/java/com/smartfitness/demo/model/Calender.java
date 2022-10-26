package com.smartfitness.demo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Calender {
	
	//예약 번호
	private Integer calSeq;
	
	//시간 정보
	private String time;
	
	//프로그램 이름
	private String programName;
	
	//회원 아이디
	private String mem_id;
	
	//트레이너 이름
	private String trainer_name;
	
	
}


