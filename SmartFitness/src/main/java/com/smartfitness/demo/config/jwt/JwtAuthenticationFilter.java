package com.smartfitness.demo.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


@RequiredArgsConstructor
public class JwtAuthenticationFilter  extends GenericFilterBean{
	
	private final JwtTokenProvider jwtTokenProvider;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK); // 상태코드 200을 뜻하며 요청이 정상적으로 처리되었다는 뜻
		}
		
		//헤더에서 JWT를 가져옵니다.
		String token = jwtTokenProvider.resolveToken(request);
//		String bearerToken = request.getHeader("Authorization");
		//유효한 토큰인지 확인
		if (token != null && jwtTokenProvider.validateToken(token)) {
			System.out.println("유효한 토큰");
			//토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
			Authentication authentication = jwtTokenProvider.getAuthorization(token);
			//SecurityContext에 Authentication객체를 저장합니다.
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		//header에 값을 보낸다
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Header", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		chain.doFilter(req, res);
	}

}
