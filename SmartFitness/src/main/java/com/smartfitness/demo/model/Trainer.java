package com.smartfitness.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Trainer {

	

		// 강사 번호
		private Integer trainer_seq;

		// 강사 이름
		private String trainer_name;

		// 강사 소개
		private String trainer_info;

		// 강사 사진
		private String trainer_pic;

		// 강사 멘트
		private String trainer_ment;

	}

