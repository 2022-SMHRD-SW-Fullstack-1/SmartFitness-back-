package com.smartfitness.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class QnaQuestion {
	// 질의응답 

	// 질답 순번 
    private Integer qna_seq;

    // 질답 유형
    private String qna_cate;
    
    // 질답 제목 
    private String qna_title;

    // 질답 내용 
    private String qna_contents;

    // 질답 작성일자 
    private String qna_date;

    // 질답 첨부파일 
    private String qna_file;

    // 질답 작성자 
    private String mem_id;
}
