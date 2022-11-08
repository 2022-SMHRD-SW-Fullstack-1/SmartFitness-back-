package com.smartfitness.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.model.QnaAnswer;
import com.smartfitness.demo.model.QnaQuestion;
import com.smartfitness.demo.common.Criteria;
import com.smartfitness.demo.exception.CustomException;
import com.smartfitness.demo.exception.ErrorCode;
import com.smartfitness.demo.mapper.QnaMapper;

@Service
public class QnaService {

	@Autowired
	QnaMapper qnaMapper;
	
	//질문 전체 가져오기
	public List<QnaQuestion> selectQnaAll() throws Exception{
		 List<QnaQuestion> qnaListAll = qnaMapper.selectQnaAll();
		 if(qnaListAll.get(0).getQna_seq()==null) {
			 //질문이 존재 하지 않는다
			 throw new CustomException(ErrorCode.QNA_NOT_FOUND);
		 }
		 return qnaListAll;
	}
	
	//mem_id의 질문 전체 가져오기
	public List<QnaQuestion> selectQnaMem(String mem_id) throws Exception{
		List<QnaQuestion> qnaList = qnaMapper.selcetQnaMem(mem_id);
		if(qnaList.get(0).getQna_seq()==null) {
			//질문이 존재 하지 않는다
			throw new CustomException(ErrorCode.QNA_NOT_FOUND);
		}
		return qnaList;
	}
	//질문 작성하기
	public void qnaWrite(QnaQuestion question) throws Exception{
		int result = qnaMapper.qnaWrite(question);
		if (result == 0) {
			// 질문작성 전달 값 오류
			throw new CustomException(ErrorCode.QNA_BAD_REQUEST);
		}
	}

	//질문 번호 qna_seq의 질문 정보 가져오기
	public QnaQuestion selectQ(int qna_seq) throws Exception{
		QnaQuestion result = qnaMapper.selectQ(qna_seq);
		if(result==null) {
			//질문이 없음
			throw new CustomException(ErrorCode.QNA_NOT_FOUND);
		}
		return result;
	}

	//질문 번호 qna_seq의 질문 수정하기
	public void qnaQueUpdate(QnaQuestion question) throws Exception{
		int result = qnaMapper.qnaQueUpdate(question);
		if(result==0) {
			//질문이 없음
			throw new CustomException(ErrorCode.QNA_NOT_FOUND);
		}
	}
	
	//질문 번호 qna_seq의 답변 정보 가져오기
	public QnaAnswer selectA(int qna_seq) throws Exception{
		QnaAnswer result = qnaMapper.selectA(qna_seq);		
		if(result==null) {
			//답변이 없음
			throw new CustomException(ErrorCode.QNA_NOT_FOUND);
		}
		return result;
	}
	
	//질문 번호 qna_seq에 대하여 답변 작성하기
	public void qnaAnswer(QnaAnswer answer, int qna_seq) throws Exception{
		int result = qnaMapper.qnaAnswer(answer, qna_seq);
		if(result==0) {
			//값을 잘못 전달함
			throw new CustomException(ErrorCode.QNA_BAD_REQUEST);
		}
	}

	//답변 수정하기
	public void qnaAnsUpdate(QnaAnswer answer, int ans_seq) throws Exception{
		int result = qnaMapper.qnaAnsUpdate(answer, ans_seq);
		if(result==0) {
			//값을 잘못 전달함
			throw new CustomException(ErrorCode.QNA_BAD_REQUEST);
		}
		
	}
	
	//질문 번호 qna_seq의 질문 정보 가져오기
	//질문 번호 qna_seq의 답변 정보 가져오기
	public Map<String, Object> selectQNA(int qna_seq) throws Exception{
		Map<String, Object> result = new HashMap<>();
		result.put("qnaQuestion", qnaMapper.selectQ(qna_seq));
		if(result.get("qnaQuestion")==null) {
			//질문이 없다
			throw new CustomException(ErrorCode.QNA_NOT_FOUND);
		}
		result.put("qnaAnswer", qnaMapper.selectA(qna_seq));
		return result;
	}


}
