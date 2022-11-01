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
	
	public List<QnaQuestion> selectQnaPaging(Criteria cri, String mem_id) {
		return qnaMapper.selcetQnaPaging(cri, mem_id);
	}
	
	public int countQna(String mem_id) {
		return qnaMapper.countQna(mem_id);
	}

	public int qnaWrite(QnaQuestion question) {
		return qnaMapper.qnaWrite(question);
	}

	public int qnaAnswer(QnaAnswer answer, int qna_seq) {
		return qnaMapper.qnaAnswer(answer, qna_seq);
	}

	public QnaQuestion selectQ(int qna_seq) {
		return qnaMapper.selectQ(qna_seq);
	}

	public QnaAnswer selectA(int qna_seq) {
		return qnaMapper.selectA(qna_seq);		
	}

	public void qnaQueUpdate(QnaQuestion question) {
		qnaMapper.qnaQueUpdate(question);
	}

}
