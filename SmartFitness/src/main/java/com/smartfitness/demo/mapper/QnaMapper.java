package com.smartfitness.demo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.common.Criteria;
import com.smartfitness.demo.model.QnaAnswer;
import com.smartfitness.demo.model.QnaQuestion;

@Mapper
public interface QnaMapper {

	List<QnaQuestion> selcetQnaPaging(Criteria cri, String mem_id);

	int countQna(String mem_id);

	int qnaWrite(QnaQuestion question);

	int qnaAnswer(QnaAnswer answer, int qna_seq);

	QnaQuestion selectQ(int qna_seq);

	QnaAnswer selectA(int qna_seq);

	void qnaQueUpdate(QnaQuestion question);

}
