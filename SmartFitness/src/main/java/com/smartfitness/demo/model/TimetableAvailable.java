package com.smartfitness.demo.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimetableAvailable {
	// 예약 가능 순번 
    private Integer ra_seq;

    // 예약시간(09:00-09:30) 
    private String ra09000930;

    // 예약시간(09:30-10:00) 
    private String ra09301000;

    // 예약시간(10:00-10:30) 
    private String ra10001030;

    // 예약시간(10:30-11:00) 
    private String ra10301100;

    // 예약시간(11:00-11:30) 
    private String ra11001130;

    // 예약시간(11:30-12:00) 
    private String ra11301200;
    
    // 예약시간(14:00-14:30) 
    private String ra14001430;
    
    // 예약시간(14:30-15:00) 
    private String ra14301500;
    
    // 예약시간(15:00-15:30) 
    private String ra15001530;
    
    // 예약시간(15:30-16:00) 
    private String ra15301600;

    // 예약시간(16:00-16:30) 
    private String ra16001630;
    
    // 예약시간(16:30-17:00) 
    private String ra16301700;
    
    // 예약시간(17:00-17:30) 
    private String ra17001730;

    // 예약시간(17:30-18:00) 
    private String ra17301800;

    // 예약시간(18:00-18:30) 
    private String ra18001830;

    // 예약시간(18:30-19:00) 
    private String ra18301900;

    // 예약시간(19:00-19:30) 
    private String ra19001930;

    // 예약시간(19:30-20:00) 
    private String ra19302000;

    // 예약시간(20:00-20:30) 
    private String ra20002030;

    // 예약시간(20:30-21:00) 
    private String ra20302100;

    // 예약 가능 
    private String ra_switch;

    // 운동 기구 순번 
    private Integer em_seq;
}
