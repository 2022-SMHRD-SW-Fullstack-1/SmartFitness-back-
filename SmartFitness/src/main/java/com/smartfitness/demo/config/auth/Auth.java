package com.smartfitness.demo.config.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
	private String accessToken;
//	private String refreshToken;
	private String mem_id;
	private String mem_email;
	private String mem_name;
	private String mem_phone;
//	private String ctr_seq;

}
