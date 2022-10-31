package com.smartfitness.demo.model;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NonNull;

@Data
public class MembersDetail implements UserDetails{
	
	@NonNull
    private String mem_id;
	@NonNull
    private String mem_pw;

    private String mem_grade;

    private Date mem_joindate;

    private String mem_name;

    private String mem_addr;

    private String mem_birthdate;

    private String mem_phone;

	private String mem_type;

	private Integer ctr_seq;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return null;
	}
	
	@Override
	public String getUsername() {
		return mem_id;
	}
	@Override
	public String getPassword() {
		return mem_pw;
	}
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}
	@Override
	public boolean isAccountNonLocked(){
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired(){
		return false;
	}
	@Override
	public boolean isEnabled(){
		return false;
	}
	
}
