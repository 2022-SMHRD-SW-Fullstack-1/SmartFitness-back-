package com.smartfitness.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.smartfitness.demo.config.auth.Auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@Data
public class MembersDetail implements UserDetails{
	

    private String mem_id;

    private String mem_pw;
    
    private String mem_name;
    
    private String mem_email;

    private String mem_phone;

    private String mem_auth;
    private Collection<? extends GrantedAuthority> authorities;
	private Map<String,Object> attributes;
    /**
    * 해당 유저의 권한 목록
    */
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();   
//        authorities.add(new SimpleGrantedAuthority("ROLE_M"));
//        return authorities;
//    }
    private MembersDetail(String mem_id, String mem_pw, String mem_name, String mem_email, String mem_phone,
    		Collection<? extends GrantedAuthority> authorities){
		this.mem_id=mem_id;
		this.mem_pw=mem_pw;
		this.mem_name=mem_name;
		this.mem_email=mem_email;
		this.mem_phone=mem_phone;
		this.authorities=authorities;
	}
    
    public static MembersDetail create(Members members) {
    	List<GrantedAuthority> authorities = Collections.
    			singletonList(new SimpleGrantedAuthority(members.getMem_auth()));
    	return new MembersDetail(
    			members.getMem_id(),
    			members.getMem_pw(),
    			members.getMem_name(),
    			members.getMem_email(),
    			members.getMem_phone(),
    			authorities);
    }
    
    public static MembersDetail create(Members members, Map<String,Object> attributes) {
		MembersDetail membersDetail = MembersDetail.create(members);
		membersDetail.setAttributes(attributes);
		return membersDetail;
	}
    
	/**
	 * PK값
	 * **/
	@Override
	public String getUsername() {
		return mem_id;
	}
	
	/**
	* 비밀번호
	*/
	@Override
	public String getPassword() {
		return mem_pw;
	}
	
    /**
     * 계정 만료 여부
     * true : 만료 안됨
     * false : 만료
     * @return
     */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
    /**
     * 계정 잠김 여부
     * true : 잠기지 않음
     * false : 잠김
     * @return
     */
	@Override
	public boolean isAccountNonLocked(){
		return true;
	}
	
    /**
     * 비밀번호 만료 여부
     * true : 만료 안됨
     * false : 만료
     * @return
     */
	@Override
	public boolean isCredentialsNonExpired(){
		return true;
	}
	
    /**
     * 사용자 활성화 여부
     * ture : 활성화
     * false : 비활성화
     * @return
     */
	@Override
	public boolean isEnabled(){
		return true;
	}
	
}

