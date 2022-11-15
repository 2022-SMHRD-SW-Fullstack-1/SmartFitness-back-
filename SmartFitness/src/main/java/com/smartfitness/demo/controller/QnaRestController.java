package com.smartfitness.demo.controller;

import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.smartfitness.demo.model.QnaAnswer;
import com.smartfitness.demo.model.QnaQuestion;
import com.smartfitness.demo.service.QnaService;

@RequestMapping("/qna")
@RestController
public class QnaRestController{

	Gson gson = new Gson();

	@Autowired
	QnaService qnaService;

	// qna의 질문 모두 확인
	@GetMapping("/admin")
	public String qnaAdmin(@RequestBody Map<String, Object> param) throws Exception {
		// 관리자 id를 잡아오기
		String admin_id = (String) param.get("admin_id");
		System.out.println(admin_id);
		List<QnaQuestion> qnaList = qnaService.selectQnaAll();

		HashMap<String, Object> map = new HashMap<>();
		map.put("qnaList", qnaList);

		String result = gson.toJson(map);
		return result;
	}

	// qna에 mem_id가 쓴 글, 전체 확인
	@GetMapping("/all")
	public String qnaAll(@RequestBody Map<String, Object> param) throws Exception {
		System.out.println(param);
		String mem_id = (String) param.get("mem_id");
		System.out.println(mem_id);
		// 고른 페이지에서 보여줘야 하는 메세지 리스트
		List<QnaQuestion> qnaList = qnaService.selectQnaMem(mem_id);

		HashMap<String, Object> map = new HashMap<>();
		map.put("qnaList", qnaList);
		String result = gson.toJson(map);
		System.out.println(result);
		return result;
	}

	// qna에 쓴 글, 글의 답변 확인하기
	@GetMapping("/{qna_seq}")
	public String qnaSelect(@PathVariable("qna_seq") int qna_seq) throws Exception {
		HashMap<String, Object> qna = new HashMap<String, Object>();
		qna.put("qna",qnaService.selectQNA(qna_seq));
		String result = gson.toJson(qna);
		return result;
	}

	// qna에 글 쓰기
	@PostMapping("/que")
	public void qnaWrite(@RequestBody QnaQuestion question) throws Exception {
		System.out.println(question);
		qnaService.qnaWrite(question);
	}

	// qna_question 수정하기
	@PostMapping("/{qna_seq}/update")
	public void qnaQueUpdate(@RequestBody QnaQuestion question) throws Exception {
		System.out.println(question);
		qnaService.qnaQueUpdate(question);

	}

	// qna글에 답변하기
	@PostMapping("/{qna_seq}/ans")
	public void qnaAnswer(@PathVariable("qna_seq") int qna_seq, @RequestBody QnaAnswer answer) throws Exception {
		System.out.println(answer);
		qnaService.qnaAnswer(answer, qna_seq);
	}
	
	// qna답변 수정하기
	@PostMapping("/{qna_seq}/ans/update")
	public void qnaAnsUpdate(@PathVariable("ans_seq") int ans_seq, @RequestBody QnaAnswer answer) throws Exception {
		System.out.println(answer);
		qnaService.qnaAnsUpdate(answer, ans_seq);
	}
}
