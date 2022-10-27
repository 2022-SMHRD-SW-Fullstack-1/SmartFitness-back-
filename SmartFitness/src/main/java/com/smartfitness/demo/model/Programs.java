package com.smartfitness.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
//운동 프로그램 정보 
public class Programs {
	
	
	
	
//	CREATE TABLE athletics_info
//	(
//	    `pg_seq`   INT             NOT NULL    AUTO_INCREMENT COMMENT '운동 프로그램 번호', 
//	    `pg_name`  VARCHAR(30)     NULL        COMMENT '운동 프로그램 이름', 
//	    `pg_max`   INT             NULL        COMMENT '운동 프로그램 정원', 
//	    `pg_info`  TEXT            NULL        COMMENT '운동 프로그램 설명', 
//	    `pg_img1`  VARCHAR(400)    NULL        COMMENT '운동 프로그램 사진1', 
//	    `pg_img2`  VARCHAR(400)    NULL        COMMENT '운동 프로그램 사진2', 
//	     PRIMARY KEY (pg_seq)
//	);

 // 운동 프로그램 번호

 private Integer pg_seq;

 // 운동 프로그램 이름
 
 private String pg_name;

 // 운동 프로그램 정원 
 private Integer pg_max;

 // 운동 프로그램 설명 
 private String pg_info;

 // 운동 프로그램 사진1 
 private String pg_img1;

 // 운동 프로그램 사진2 
 private String pg_img2;
 
 // 현재 정원
 private Integer pg_cur_cnt;
}