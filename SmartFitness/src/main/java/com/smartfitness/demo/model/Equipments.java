package com.smartfitness.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Equipments {
	// 운동 기구 정보 

	// 운동 기구 번호 
    private Integer em_seq;

    // 운동 기구 이름 
    private String em_name;

    // 운동 기구 영상 
    private String em_video;

    // 신체 자극 부위 
    private String em_part;

    //운동 기구 분류
    private String em_type;
}
