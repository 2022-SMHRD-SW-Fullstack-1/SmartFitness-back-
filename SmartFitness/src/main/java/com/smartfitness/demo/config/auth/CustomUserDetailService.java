package com.smartfitness.demo.config.auth;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartfitness.demo.mapper.MembersMapper;


@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService{
	private MembersMapper membersMapper;
	
	/**
	 * 로그인하면 유효한지 확인,
	 * DB에 저장된 mem_auth에 따라 ROLE부여
	 * **/
	@Override
	public UserDetails loadUserByUsername(String mem_id) throws UsernameNotFoundException{
		UserDetails userDetails = membersMapper.findByUserId(mem_id);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if(userDetails == null) {
			throw new UsernameNotFoundException("유효하지 않은 로그인 정보입니다.");
		} else {
			if(membersMapper.findByUserId(mem_id).getMem_auth().equals("ROLE_M")){
				authorities.add(new SimpleGrantedAuthority("ROLE_M"));
				return new User(
					userDetails.getUsername(),
					userDetails.getPassword(),
					authorities
					);
			}else {
				authorities.add(new SimpleGrantedAuthority("ROLE_A"));
				return new User(
					userDetails.getUsername(),
					userDetails.getPassword(),
					authorities);
			}
			
		}
		
	}
}
