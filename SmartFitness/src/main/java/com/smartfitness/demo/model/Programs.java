package com.smartfitness.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
//운동 프로그램 정보 
public class Programs {

 // 운동 프로그램 번호
 private Integer pg_seq;

 // 운동 프로그램 이름
 private String pg_name;

 // 운동 프로그램 설명 
 private String pg_info;

 // 운동 프로그램 사진1 
 private String pg_img1;

 // 운동 프로그램 사진2 
 private String pg_img2;

}