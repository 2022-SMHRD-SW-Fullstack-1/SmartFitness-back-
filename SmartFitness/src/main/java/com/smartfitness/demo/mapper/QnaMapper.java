package com.smartfitness.demo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.model.Answer;
import com.smartfitness.demo.model.Qna;
import com.smartfitness.demo.model.QnaAnswer;
import com.smartfitness.demo.model.QnaQuestion;

@Mapper
public interface QnaMapper {

	List<QnaQuestion> selcetQnaPaging(HashMap<String, Object> map);

	int countQna();

	int qnaWrite(QnaQuestion question);

	int qnaAnswer(QnaAnswer answer);

}
