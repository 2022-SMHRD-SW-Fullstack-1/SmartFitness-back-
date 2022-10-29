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
	
	public List<QnaQuestion> selectQnaPaging(Criteria cri) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("cri", cri);
		List<QnaQuestion> qnaList = qnaMapper.selcetQnaPaging(map);
		return qnaList;
	}
	
	public int countQna() {
		return qnaMapper.countQna();
	}

	public int qnaWrite(QnaQuestion question) {
		return qnaMapper.qnaWrite(question);
	}

	public int qnaAnswer(QnaAnswer answer) {
		return qnaMapper.qnaAnswer(answer);
	}

}
