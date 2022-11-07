package com.smartfitness.demo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.common.Criteria;
import com.smartfitness.demo.model.QnaAnswer;
import com.smartfitness.demo.model.QnaQuestion;

@Mapper
public interface QnaMapper {

	//admin이 확인할 질문 전체 정보
	List<QnaQuestion> selcetQnaPagingA();
	
	//mem_id의 질문 목록
	List<QnaQuestion> selcetQnaPaging(String mem_id);
	//mem_id의 질문 페이징 정보
	int countQna(String mem_id);

	//질문 작성
	int qnaWrite(QnaQuestion question);

	//답변 작성
	int qnaAnswer(QnaAnswer answer, int qna_seq);

	//질문(질문번호 qna_seq) 정보가져오기
	QnaQuestion selectQ(int qna_seq);
	//질문 수정하기
	void qnaQueUpdate(QnaQuestion question);

	//답변(답변번호 qna_seq) 정보가져오기
	QnaAnswer selectA(int qna_seq);





}
