package com.smartfitness.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Qna {
	// 질의응답 
//	CREATE TABLE qna
//	(
//	    `qna_seq`       INT             NOT NULL    AUTO_INCREMENT COMMENT '질답 순번', 
//	    `qna_title`     VARCHAR(20)     NOT NULL    COMMENT '질답 제목', 
//	    `qna_contents`  VARCHAR(20)     NOT NULL    COMMENT '질답 내용', 
//	    `qna_date`      VARCHAR(20)     NOT NULL    COMMENT '질답 작성일자', 
//	    `qna_file`      VARCHAR(400)    NULL        COMMENT '질답 첨부파일', 
//	    `mem_id`        VARCHAR(20)     NOT NULL    COMMENT '질답 작성자', 
//	     PRIMARY KEY (qna_seq)
//	);
	// 질답 순번 
    private Integer qna_seq;

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
