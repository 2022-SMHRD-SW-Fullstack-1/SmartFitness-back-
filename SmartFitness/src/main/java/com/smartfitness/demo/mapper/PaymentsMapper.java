package com.smartfitness.demo.mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.smartfitness.demo.payment.PaymentsModel;

@Mapper
public interface PaymentsMapper {

	/**
	 * 결제정보 DB에 저장
	 * **/
	void insertPaymentInfo(Map<String, Object> paymentsModel);

	
	/**
	 * 결제가 성공하면 DB에 있는 status 변경
	 * **/
	void updatePaymentInfo(PaymentsModel paymentInfo);

	/**
	 * imp_uid를 토대로 DB에서 amount 추출
	 * **/
	int getAmountToBePaid(String imp_uid);


	int enroll(HashMap map);

}
