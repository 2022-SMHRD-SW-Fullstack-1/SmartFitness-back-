package com.smartfitness.demo.payment;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smartfitness.demo.service.MembersService;

@RestController
@RequestMapping("/payments")
public class PaymentsController {
	
	@Autowired
	MembersService memberService;
	
	@Autowired
    private PaymentsService paymentsService;
	
	@ResponseBody
	@PostMapping("/insertPaymentInfo")
	public String insertPaymentInfo(@RequestBody Map<String, Object> paymentsModel) {
		System.out.println("결제요청 : "+paymentsModel);
		// STEP5-3. 결제 정보 검증 후 저장하기
		// 처음에 요청했던 금액 저장하기
		try {
			memberService.insertInfo(paymentsModel);
			paymentsService.insertPaymentInfo(paymentsModel);
			return "ok";	
		}catch(Exception e){
			e.getStackTrace();
			return "ng";
		}
	}
	
	@ResponseBody
	@PostMapping("/complete")
	public String complete(@RequestBody PaymentsModel paymentsModel) {
		// STEP5. 결제 정보 검증 및 저장하기

			// STEP5-2. 결제 정보 조회하기
			// 액세스 토큰(access token) 발급 받기
			String accessToken = paymentsService.getAccessToken(paymentsModel); 
			System.out.println("haha"+ accessToken);
			// imp_uid로 아임포트 서버에서 결제 정보 조회
			PaymentsModel paymentInfo = paymentsService.getPaymentInfo(accessToken, paymentsModel);
			// STEP5-3. 결제 정보 검증 후 저장하기
			// DB에서 결제되어야 하는 금액 조회
			int amountToBePaid = paymentsService.getAmountToBePaid(paymentsModel);
			int amount = paymentInfo.getAmount();
			// 결제 검증하기
			if(amountToBePaid == amount) {
				paymentsService.updatePaymentInfo(paymentInfo); // DB에 결제 정보 저장
				if(paymentInfo.getStatus().equals("paid")) { // 결제 완료
					return "success";
				}else if(paymentInfo.getStatus().equals("ready")) { // 가상계좌발급
					return "vbankIssued";
				}else {
					return "fail";
				}
			}else { // 결제금액 불일치. 위/변조 된 결제
				return "forgery";
			}

	}
}
