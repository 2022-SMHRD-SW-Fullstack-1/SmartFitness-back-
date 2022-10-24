package com.smartfitness.demo.mapper;

import java.util.HashMap;
import java.util.List;

import com.smartfitness.demo.model.Answer;
import com.smartfitness.demo.model.Qna;

public interface QnaMapper {

	List<Qna> selcetQnaPaging(HashMap<String, Object> map);

	int countQna();

	int qnaWrite(Qna qna);

	int qnaAnswer(Answer answer);

}
