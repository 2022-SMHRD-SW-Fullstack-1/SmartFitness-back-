package com.smartfitness.demo.payment;

import java.io.BufferedReader;
import java.io.InputStream;
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
	public PaymentsModel getPaymentInfo(String accessToken, PaymentsModel paymentsModel) {
		PaymentsModel paymentInfo = new PaymentsModel();
		String reqURL = "https://api.iamport.kr/payments/" + paymentsModel.getImp_uid();
		try {
			URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        
	        // GET 요청
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Authorization", accessToken);
	        
	        int responseCode = conn.getResponseCode();
	        if(responseCode == 200) { // 결과 코드가 200이면 성공
	        	// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            String result = "";
	            
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	     
	            // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
	            JsonParser parser = new JsonParser();
	            JsonElement element = parser.parse(result);
	            
	            JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();
	            if(!response.getAsJsonObject().get("amount").isJsonNull()) { paymentInfo.setAmount(response.getAsJsonObject().get("amount").getAsInt()); }
	            if(!response.getAsJsonObject().get("apply_num").isJsonNull()) { paymentInfo.setApply_num(response.getAsJsonObject().get("apply_num").getAsString()); }
	            if(!response.getAsJsonObject().get("bank_code").isJsonNull()) { paymentInfo.setBank_code(response.getAsJsonObject().get("bank_code").getAsString()); }
	            if(!response.getAsJsonObject().get("status").isJsonNull()) { paymentInfo.setStatus(response.getAsJsonObject().get("status").getAsString()); }
	            br.close();	
	        }
		}catch(Exception e) {
	        e.printStackTrace();
		}
		return paymentInfo;
	}
	
	/**
	 * model정보를 토대로 DB에서 결제금액 정보 가져오기
	 * imp_uid를 받아서 amount가져오기
	 * **/
	public int getAmountToBePaid(PaymentsModel paymentsModel) {
		String imp_uid = paymentsModel.getImp_uid();
		int amount= paymentsMapper.getAmountToBePaid(imp_uid);
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
		String data = "{ \"imp_key\" : \"DwHLTXWMsQqmhdp0dDaWNh7nmWuyAhAEzBZRYwQlovYspROQQGEAlUTcDp51FohYghY2aa7N9uEDPgym\", \"imp_secret\" : \"8440563800274615\"}";
		try {
			URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        
	        // POST 요청
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	   
	        // data 넣기
	        try (OutputStream os = conn.getOutputStream()){
				byte request_data[] = data.getBytes("utf-8");
				os.write(request_data);
				os.close();
			} catch(Exception e) {
				e.printStackTrace();
			}	
	        
	        int responseCode = conn.getResponseCode();
	        if(responseCode == 200) { // 결과 코드가 200이면 성공
	        	// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            String result = "";
	            
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }

	            // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
	            JsonParser parser = new JsonParser();
	            JsonElement element = parser.parse(result);
	            
	            access_Token = element.getAsJsonObject().get("response").getAsJsonObject().get("access_token").getAsString();
	            br.close();	
	        }
		}catch(Exception e) {
			 // TODO Auto-generated catch block
	        e.printStackTrace();
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
