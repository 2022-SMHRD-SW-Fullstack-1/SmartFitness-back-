package com.smartfitness.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class QnaAnswer {
	//답변

	// 답변 순번 
    private Integer ans_seq;

    // 질답 순번 
    private Integer qna_seq;

    // 답변 내용 
    private String ans_contents;

    // 답변 첨부파일 
    private String ans_file;

    // 답변 작성일자 
    private String ans_date;

    // 답변 작성자 
    private String admin_id;
}
