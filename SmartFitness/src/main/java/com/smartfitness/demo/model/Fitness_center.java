package com.smartfitness.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Fitness_center {


		// 센터 순번
		private Integer ctr_id;

		// 센터 이름
		private String ctr_name;

		// 센터 전화번호
		private String ctr_phone;

		// 센터 주소
		private String ctr_addr;

		// 센터 연락처
		private String ctr_tel;
	}

