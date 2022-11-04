package com.smartfitness.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.model.QnaAnswer;
import com.smartfitness.demo.model.QnaQuestion;
import com.smartfitness.demo.common.Criteria;
import com.smartfitness.demo.mapper.QnaMapper;

@Service
public class QnaService {

	@Autowired
	QnaMapper qnaMapper;
	
	//질문 전체 가져오기
	public List<QnaQuestion> selectQnaPagingA(Criteria cri) {
		return qnaMapper.selcetQnaPagingA(cri);
	}
	//질문 전체 페이징할 수 있는 숫자 정보 가져오기
	public int countQnaA() {
		return qnaMapper.countQnaA();
	}
	
	//mem_id의 질문 전체 가져오기
	public List<QnaQuestion> selectQnaPaging(Criteria cri, String mem_id) {
		return qnaMapper.selcetQnaPaging(cri, mem_id);
	}
	//mem_id의 질문 전체 페이징할 수 있는 숫자 정보 가져오기
	public int countQna(String mem_id) {
		return qnaMapper.countQna(mem_id);
	}
	

	//질문 작성하기
	public int qnaWrite(QnaQuestion question) {
		return qnaMapper.qnaWrite(question);
	}

	//질문 번호 qna_seq에 대하여 답변 작성하기
	public int qnaAnswer(QnaAnswer answer, int qna_seq) {
		return qnaMapper.qnaAnswer(answer, qna_seq);
	}

	//질문 번호 qna_seq의 질문 정보 가져오기
	public QnaQuestion selectQ(int qna_seq) {
		return qnaMapper.selectQ(qna_seq);
	}

	//질문 번호 qna_seq의 답변 정보 가져오기
	public QnaAnswer selectA(int qna_seq) {
		return qnaMapper.selectA(qna_seq);		
	}

	//질문 번호 qna_seq의 질문 수정하기
	public void qnaQueUpdate(QnaQuestion question) {
		qnaMapper.qnaQueUpdate(question);
	}




}
