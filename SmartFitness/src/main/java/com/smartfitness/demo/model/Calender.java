package com.smartfitness.demo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Calender {
	
	//예약 번호
	private Integer cal_seq;
	
	//시작 날짜
	private String start_time;
	
	//종료 날짜
	private String end_time;
	
	//프로그램 이름
	private String program_name;
	
	//회원 아이디
	private String mem_id;
	
	//트레이너 이름
	private String trainer_name;
	
	
}
