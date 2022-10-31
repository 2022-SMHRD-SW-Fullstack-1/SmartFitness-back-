package com.smartfitness.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentEquipments {
	// 예약 가능 순번 
    private Integer ce_seq;

    // 예약시간(09:00-09:30) 
    private String ce09000930;

    // 예약시간(09:30-10:00) 
    private String ce09301000;

    // 예약시간(10:00-10:30) 
    private String ce10001030;

    // 예약시간(10:30-11:00) 
    private String ce10301100;

    // 예약시간(11:00-11:30) 
    private String ce11001130;

    // 예약시간(11:30-12:00) 
    private String ce11301200;
    
    // 예약시간(14:00-14:30) 
    private String ce14001430;
    
    // 예약시간(14:30-15:00) 
    private String ce14301500;
    
    // 예약시간(15:00-15:30) 
    private String ce15001530;
    
    // 예약시간(15:30-16:00) 
    private String ce15301600;

    // 예약시간(16:00-16:30) 
    private String ce16001630;
    
    // 예약시간(16:30-17:00) 
    private String ce16301700;
    
    // 예약시간(17:00-17:30) 
    private String ce17001730;

    // 예약시간(17:30-18:00) 
    private String ce17301800;

    // 예약시간(18:00-18:30) 
    private String ce18001830;

    // 예약시간(18:30-19:00) 
    private String ce18301900;

    // 예약시간(19:00-19:30) 
    private String ce19001930;

    // 예약시간(19:30-20:00) 
    private String ce19302000;

    // 예약시간(20:00-20:30) 
    private String ce20002030;

    // 예약시간(20:30-21:00) 
    private String ce20302100;

    // 예약 가능 
    private String ce_switch;

    // 운동 기구 순번 
    private Integer em_seq;
}
