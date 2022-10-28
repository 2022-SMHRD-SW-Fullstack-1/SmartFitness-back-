package com.smartfitness.demo.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//운동 프로그램 정보 
public class ReservPrograms {

 // 프로그램 예약 번호
 private Integer reserv_pg_seq;

 // 회원 아이디
 private String mem_id;

 //운동 프로그램 회차 순번
 private Integer curr_pg_seq;
 
 // 예약 일자
 private Date reserv_pg_dt;

 // 예약 시간
 private String reserv_time;
}