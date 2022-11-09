package com.smartfitness.demo.payment;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentsMapper {

	/**
	 * 결제정보 DB에 저장
	 * **/
	void insertPaymentInfo(PaymentsModel paymentsModel);

	
	/**
	 * 결제가 성공하면 DB에 있는 status 변경
	 * **/
	void updatePaymentInfo(PaymentsModel paymentInfo);

	/**
	 * imp_uid를 토대로 DB에서 amount 추출
	 * **/
	String getAmountToBePaid(String imp_uid);

}
