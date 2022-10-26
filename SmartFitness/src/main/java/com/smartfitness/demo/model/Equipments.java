package com.smartfitness.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Equipments {
	// 운동 기구 정보 
//	CREATE TABLE equipments
//	(
//	    `em_seq`   INT        NOT NULL AUTO_INCREMENT COMMENT '운동 기구 번호', 
//	    `em_name`  VARCHAR(30)NULL     COMMENT '운동 기구 이름', 
//	    `em_video` VARCHAR(30)NULL     COMMENT '운동 기구 영상', 
//	    `em_part`  VARCHAR(30)NULL     COMMENT '신체 자극 부위', 
//	     PRIMARY KEY (em_seq)
//	);
	// 운동 기구 번호 
    private Integer em_seq;

    // 운동 기구 이름 
    private String em_name;

    // 운동 기구 영상 
    private String em_video;

    // 신체 자극 부위 
    private String em_part;

}
