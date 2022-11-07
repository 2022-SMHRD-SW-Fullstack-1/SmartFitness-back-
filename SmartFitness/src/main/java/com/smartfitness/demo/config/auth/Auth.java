package com.smartfitness.demo.config.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
	private String token;
	private String mem_id;
	private String mem_auth;
	private String mem_email;
//	private String ctr_seq;

}
