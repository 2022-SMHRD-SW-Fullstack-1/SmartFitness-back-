package com.smartfitness.demo.payment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smartfitness.demo.exception.CustomException;
import com.smartfitness.demo.exception.ErrorCode;
import com.smartfitness.demo.mapper.PaymentsMapper;
import com.smartfitness.demo.model.Members;

@Service
public class PaymentsService {
	
	Gson gson = new Gson();
	
	@Autowired
	PaymentsMapper paymentsMapper;
	/**
	 * 토큰을 통해 결제 정보 가져오기
	 * **/
	public PaymentsModel getPaymentInfo(String accessToken, PaymentsModel paymentsModel) throws Exception {
		String reqURL = "https://api.iamport.kr/payments/" + paymentsModel.getImp_uid();
		PaymentsModel paymentInfo = new PaymentsModel();
		try {
			URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        
	        // GET 요청
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Authorization", accessToken);
	        
	        int responseCode = conn.getResponseCode();
	        if(responseCode == 200) { // 결과 코드가 200이면 성공
	        	// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	    		String jsonStr = gson.toJson(conn.getInputStream());
	    		paymentInfo = gson.fromJson(jsonStr, PaymentsModel.class);
	    		System.out.println("결제 정보 확인 : "+paymentInfo);
	        }
	        else {
	        	throw new CustomException(ErrorCode.NOT_FOUND);
	        }
	        return paymentInfo;
		}catch(Exception e) {
	        throw new CustomException(ErrorCode.BAD_REQUEST);
		}
	}
	
	/**
	 * model정보를 토대로 DB에서 결제금액 정보 가져오기
	 * imp_uid를 받아서 amount가져오기
	 * **/
	public String getAmountToBePaid(PaymentsModel paymentsModel) {
		String imp_uid = paymentsModel.getImp_uid();
		String amount= paymentsMapper.getAmountToBePaid(imp_uid);
		return amount;
	}

	/**
	 * 결제 정보 업데이트
	 * 결제 성공하면 결제정보를 업데이트 해준다.
	 * status변경
	 * **/
	public void updatePaymentInfo(PaymentsModel paymentInfo) {
		paymentsMapper.updatePaymentInfo(paymentInfo);
	}

	/**
	 * 토큰 얻기
	 * **/
	public String getAccessToken(PaymentsModel paymentsModel) {
		String access_Token = "";
		String reqURL = "https://api.iamport.kr/users/getToken";
		Map<String, String> map = new HashMap<String, String>();
		map.put("imp_key", "6610123756310771");
		map.put("imp_secret", "nLgpQIgyUrHokuxtCWiXIzZQe2lmGh0dmjN8qdDA4s07e6BN5MaP16TGI3YDViD3HY44kZEpyiaFD0ws");
		String data= gson.toJson(map);
		try {
			URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        
	        // POST 요청
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");

	        
	        //data넣기
	        int responseCode = conn.getResponseCode();
	        if(responseCode == 200) { // 결과 코드가 200이면 성공
	        	// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	        	access_Token = gson.toJson(conn.getInputStream());
	        }
		}catch(Exception e) {
	        e.getStackTrace();
		}
		return access_Token;
	}

	/**
	 * 결제정보 DB에 입력
	 * **/
	public void insertPaymentInfo(Map<String, Object> paymentsModel) {
		paymentsMapper.insertPaymentInfo(paymentsModel);
	}
	
	

}
