package com.smartfitness.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Fitness_center {


		// 센터 순번
		private Integer ctrId;

		// 센터 이름
		private String ctrName;

		// 센터 전화번호
		private String ctrPhone;

		// 센터 주소
		private String ctrAddr;

		// 센터 연락처
		private String ctrTel;
	}

