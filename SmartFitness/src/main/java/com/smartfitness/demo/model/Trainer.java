package com.smartfitness.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Trainer {

	

		// 강사 번호
		private Integer trainerSeq;

		// 강사 이름
		private String trainerName;

		// 강사 소개
		private String trainerInfo;

		// 강사 사진
		private String trainerPic;

		// 강사 멘트
		private String trainerMent;

	}

