package com.smartfitness.demo.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservEquipments {

	// 예약 순번
    private Integer reserv_em_seq;

    // 예약 회원 아이디
	private String mem_id;

    // 운동 기구 번호
	private Integer em_seq;
    
    // 예약 날짜
    private Date reserv_em_dt;

    // 예약 시간
    private String reserv_em_time;
}
