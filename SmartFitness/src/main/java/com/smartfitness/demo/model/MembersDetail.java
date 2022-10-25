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
    private String memId;
	@NonNull
    private String memPw;
	@NonNull
    private String memGrade;
	@NonNull
    private Date memJoindate;
	@NonNull
    private String memName;
	@NonNull
    private String memAddr;
	@NonNull
    private String memBirthdate;
	@NonNull
    private String memPhone;
	@NonNull
	private String memType;
	@NonNull
	private Integer ctrId;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return null;
	}
	
	@Override
	public String getPassword() {
		return memPw;
	}
	@Override
	public String getUsername() {
		return memId;
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
