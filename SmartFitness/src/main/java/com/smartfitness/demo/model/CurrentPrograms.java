package com.smartfitness.demo.model;




import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentPrograms {
	// 운동 프로그램 회차 순번
    private Integer curr_pg_seq;

    // 운동 프로그램 번호
    private Integer pg_seq;

    // 강사 번호
    private Integer trainer_seq;
    
    // 운동 프로그램 시작 시간 
    private Date curr_pg_s_dt;
    // 운동 프로그램 종료 시간 
    private Date curr_pg_d_dt;
    
    // 최대 정원
    private Integer pg_max_peo;
    // 현재 정원
    private Integer curr_num_peo;
    
    // 운동 프로그램 상태
    private String pg_status;
}
