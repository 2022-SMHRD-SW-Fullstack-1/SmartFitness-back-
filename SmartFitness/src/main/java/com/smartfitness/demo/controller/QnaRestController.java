package com.smartfitness.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.smartfitness.demo.common.Criteria;
import com.smartfitness.demo.model.QnaAnswer;
import com.smartfitness.demo.model.QnaQuestion;
import com.smartfitness.demo.service.QnaService;
import com.smartfitness.demo.common.Page;

@RequestMapping("/qna")
@RestController
public class QnaRestController {
	
	Gson gson = new Gson();
	
	@Autowired
	QnaService qnaService;
	
	// qna의 질문 모두 확인
	@GetMapping("/admin")
	public String qnaAdmin(@RequestBody Map<String, Object> param, Criteria cri) {
		//관리자 id를 잡아오기
		String admin_id = (String)param.get("admin_id");
		System.out.println(admin_id);
		System.out.println(cri);
		List<QnaQuestion> qnaList = qnaService.selectQnaPagingA(cri);
		
		int total = qnaService.countQnaA();
		//paging page 개수 -> page
		Page page =new Page(cri,total);
		HashMap<String, Object> map = new HashMap<>();
		map.put("qnaList", qnaList);
		map.put("pagination", page);
		String result = gson.toJson(map);
		return result;
	}
	
	// qna에 mem_id가 쓴 글, 전체 확인
	@GetMapping("/all")
	public String qnaAll(@RequestBody Map<String, Object> param, Criteria cri) {
		String mem_id = (String)param.get("mem_id");
		//고른 페이지에서 보여줘야 하는 메세지 리스트
		List<QnaQuestion> qnaList = qnaService.selectQnaPaging(cri,mem_id);
		
		int total = qnaService.countQna(mem_id);
		//paging page 개수 -> page
		Page page =new Page(cri,total);
		HashMap<String, Object> map = new HashMap<>();
		map.put("qnaList", qnaList);
		map.put("pagination", page);
		String result = gson.toJson(map);
		return result;
	}
	
	// qna에 쓴 글, 글의 답변 확인하기
	@GetMapping("/{qna_seq}")
	public String qnaSelect(@PathVariable("qna_seq") int qna_seq) {
		HashMap<String, Object> qna = new HashMap<String, Object>(); 
		qna.put("qnaQuestion",qnaService.selectQ(qna_seq));
		qna.put("qnaAnswer",qnaService.selectA(qna_seq));
		String result = gson.toJson(qna);
		return result;
	}
	
	// qna에 글 쓰기
	@PostMapping("/que")
	public String qnaWrite(@RequestBody QnaQuestion question) throws Exception{
		System.out.println(question);
		try {
			qnaService.qnaWrite(question);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	// qna_question 수정하기
	@PostMapping("/{qna_seq}/update")
	public String qnaQueUpdate(@RequestBody QnaQuestion question) throws Exception{
		System.out.println(question);
		try {
			qnaService.qnaQueUpdate(question);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	
	// qna글에 답변하기
	@PostMapping("/{qna_seq}/ans")
	public String qnaAnswer(@PathVariable("qna_seq") int qna_seq, @RequestBody QnaAnswer answer) throws Exception {
		System.out.println(answer);
		try {
		qnaService.qnaAnswer(answer, qna_seq);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";	
		
		}		
	}
}
