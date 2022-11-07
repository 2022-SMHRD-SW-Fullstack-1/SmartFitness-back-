package com.smartfitness.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.smartfitness.demo.config.auth.Auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class MembersDetail implements UserDetails{
	
	private boolean enabled;
	private Date regDate;
	private Date updateDate;
	private List<Auth> authList;
	
	@NonNull
    private String mem_id;
	@NonNull
    private String mem_pw;

    private String mem_grade;

    private Date mem_joindate;

    private String mem_name;
    
    private String mem_email;

    private String mem_addr;

    private String mem_birthdate;

    private String mem_phone;

	private String mem_auth;

	private Integer ctr_seq;
	
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(this.mem_auth));
        return authorities;
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

