package com.smartfitness.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Answer {
	//답변
//	CREATE TABLE answer
//	(
//	    `ans_seq`      INT             NOT NULL    AUTO_INCREMENT COMMENT '답변 순번', 
//	    `qna_seq`      INT             NOT NULL    COMMENT '질답 순번', 
//	    `ans_content`  TEXT            NOT NULL    COMMENT '답변 내용', 
//	    `ans_file`     VARCHAR(400)    NOT NULL    COMMENT '답변 첨부파일', 
//	    `ans_date`     DATETIME        NOT NULL    COMMENT '답변 작성일자', 
//	    `admin_id`     VARCHAR(20)     NOT NULL    COMMENT '답변 작성자', 
//	     PRIMARY KEY (ans_seq)
//	);
	// 답변 순번 
    private Integer ans_seq;

    // 질답 순번 
    private Integer qna_seq;

    // 답변 내용 
    private String ans_content;

    // 답변 첨부파일 
    private String ans_file;

    // 답변 작성일자 
    private String ans_date;

    // 답변 작성자 
    private String admin_id;
}
