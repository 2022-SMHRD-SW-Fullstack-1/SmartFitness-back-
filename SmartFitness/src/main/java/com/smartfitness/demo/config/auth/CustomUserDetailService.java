package com.smartfitness.demo.config.auth;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.mapper.MembersMapper;
import com.smartfitness.demo.model.Members;
import com.smartfitness.demo.model.MembersDetail;


@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService{
	
	
	private Members members;
	
	@Autowired
	MembersMapper membersMapper;
	
	/**
	 * 로그인하면 유효한지 확인,
	 * DB에 저장된 mem_auth에 따라 ROLE부여
	 * 
	 * **/
	@Override
	public MembersDetail loadUserByUsername(String login_mem_id) throws UsernameNotFoundException{
		MembersDetail userDetails = membersMapper.findByUserId(login_mem_id);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if(userDetails == null) {
			throw new UsernameNotFoundException("유효하지 않은 로그인 정보입니다.");
		} else {
			if(membersMapper.findByUserId(login_mem_id).getMem_auth().equals("ROLE_M")){
				userDetails.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userDetails.getMem_auth())));
				return userDetails;
							
			}else {
				authorities.add(new SimpleGrantedAuthority("ROLE_A"));
				userDetails.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userDetails.getMem_auth())));
				return userDetails;
					
			}
			
		}
		
	}
}