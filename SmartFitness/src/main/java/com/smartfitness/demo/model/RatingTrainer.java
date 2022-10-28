package com.smartfitness.demo.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RatingTrainer {

	

	// 평점 순번
	private Integer rating_seq;

	// 강사 번호
	private Integer trainer_seq;
		
	// 평점
	private float rating;

	// 작성 일자
	private Date rating_dt;

	// 작성자 아이디
	private String mem_id;

}

