package com.smartfitness.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.model.Answer;
import com.smartfitness.demo.model.Qna;
import com.smartfitness.demo.common.Criteria;
import com.smartfitness.demo.mapper.QnaMapper;

@Service
public class QnaService {

	@Autowired
	QnaMapper qnaMapper;
	
	public List<Qna> selectQnaPaging(Criteria cri) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("cri", cri);
		List<Qna> qnaList = qnaMapper.selcetQnaPaging(map);
		return qnaList;
	}
	
	public int countQna() {
		return qnaMapper.countQna();
	}

	public int qnaWrite(Qna qna) {
		return qnaMapper.qnaWrite(qna);
	}

	public int qnaAnswer(Answer answer) {
		return qnaMapper.qnaAnswer(answer);
	}

}
