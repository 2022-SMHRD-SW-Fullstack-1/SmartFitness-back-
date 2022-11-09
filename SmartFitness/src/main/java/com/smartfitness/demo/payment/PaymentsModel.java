package com.smartfitness.demo.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentsModel {

	
	//
	private String pg; //PG사
	//
	private String pay_method; //결제수단
	private String merchant_uid; //주문번호
	//
	private int amount; //결제금액
	//
	private String name; //주문명
	private String buyer_email; //고객 이메일
	private String buyer_name; //고객 이름
	//
	private String buyer_tel; //고객 전화번호
	private String buyer_addr; //고객 주소
	private String buyer_postcode; //고객 우편번호
	
	private String status; // 주문 상태

	private String apply_num; // 결제번호
	private String bank_name; // 은행이름
	
	private String imp_uid;
	private String channel;
	private String pg_provider;
	private String emb_pg_provider;
	private String pg_tid;
	private String escrow;
	private String bank_code;
	private String card_code;
	private String card_name;
	private String card_number;
	private String card_quota;
	private String card_type;
	private String vbank_code;
	private String vbank_name;
	private String vbank_holder;
	private String vbank_date;
	private String vbank_issued_at;
	private String cancel_amount;
	private String currency; //원화 KRW

}
//
//	@SerializedName("custom_data")
//	String custom_data;
//
//	@SerializedName("started_at")
//	long started_at;
//
//	@SerializedName("paid_at")
//	long paid_at;
//
//	@SerializedName("failed_at")
//	long failed_at;
//
//	@SerializedName("cancelled_at")
//	long cancelled_at;
//
//	@SerializedName("fail_reason")
//	String fail_reason;
//
//	@SerializedName("cancel_reason")
//	String cancel_reason;
//
//	@SerializedName("receipt_url")
//	String receipt_url;
//
//	@SerializedName("cancel_history")
//	PaymentCancelDetail[] cancel_history;
//
//	@SerializedName("cash_receipt_issued")
//	boolean cash_receipt_issued;
//
//	@SerializedName("customer_uid")
//	String customer_uid;
//
//	@SerializedName("customer_uid_usage")
//	String customer_uid_usage;