package com.smartfitness.demo.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.smartfitness.demo.config.auth.CustomUserDetailService;
import com.smartfitness.demo.model.MembersDetail;

public class TokenAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtTokenProvider JwtTokenProvider;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = getJwtFromRequest(request);
			
			if(StringUtils.hasText(jwt) && JwtTokenProvider.validateToken(jwt)) {
				String sub = JwtTokenProvider.getUserIDFromToken(jwt);
				MembersDetail membersDetails = customUserDetailService.loadUserByUsername(sub);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(membersDetails, null, membersDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 토큰 가져오기
	 * **/
	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			bearerToken.substring(7,bearerToken.length());
		}
		return null;
	}
}