package com.smartfitness.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.smartfitness.demo.common.Criteria;
import com.smartfitness.demo.model.Answer;
import com.smartfitness.demo.model.Qna;
import com.smartfitness.demo.model.TimetableAvailable;
import com.smartfitness.demo.service.QnaService;
import com.smartfitness.demo.common.Page;

@RequestMapping("/qna")
@RestController
public class QnaRestController {
	
	Gson gson = new Gson();
	
	@Autowired
	QnaService qnaService;
	
	// qna게시판에 쓰인 글, 전체 확인
	@GetMapping("/qna")
	public String qnaAll(Criteria cri) {
		//고른 페이지에서 보여줘야 하는 메세지 리스트
		List<Qna> qnaList = qnaService.selectQnaPaging(cri);
		
		int total = qnaService.countQna();
		//paging page 개수 -> page
		Page page =new Page(cri,total);
		HashMap<String, Object> map = new HashMap<>();
		map.put("qnaList", qnaList);
		map.put("pagination", page);
		String result = gson.toJson(map);
		return result;
	}
	
	// qna게시판에 글 쓰기
	@PostMapping("/qna/write")
	public String qnaWrite(Qna qna) {
		int cnt = qnaService.qnaWrite(qna);
		if(cnt>0) {
			return "succes";
		}else {
			return "fail";	
		}
		
	}
	
	// qna글에 답변하기
	@PostMapping("/qna/{qnaSeq}/answer")
	public String qnaAnswer(@PathVariable("qnaSeq") int qnaSeq,
			@RequestBody Answer answer) {
		int cnt = qnaService.qnaAnswer(answer);
		if(cnt>0) {
			return "succes";
		}else {
			return "fail";	
		}		
	}
	// qna게시판에 내가 쓴 글 확인(로그인 하고 나서 할 예정)
}
