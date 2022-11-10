package com.smartfitness.demo.config.jwt;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.smartfitness.demo.config.auth.CustomUserDetailService;
import com.smartfitness.demo.model.Members;
import com.smartfitness.demo.model.MembersDetail;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;


@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
	
	private String secretKey = "webfiredwood";
	
	//토큰 유효시간 지정 30분
	private long tokenValidTime = 30*60*1000L;
	private final CustomUserDetailService userDetailsService;
	
	//객체 초기화, secretKet를 Base64로 encoding한다.
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	//JWT토큰 생성
//	public String createToken(Authentication authentication) {
//		MembersDetail membersDetail = (MembersDetail) authentication.getPrincipal();
//		
//		Date now = new Date();
//		long exp = this.tokenValidTime;
//		
//		return Jwts.builder()
//				.setSubject((String)membersDetail.getUsername())
//				.setIssuedAt(new Date())
//				.setExpiration(new Date(now.getTime()+exp))
//				.signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과
//															   // signature에 들어갈 secret 값 세팅
//				.compact(); // 명령문을 닫아주는 명령어
//	}
	//JWT토큰 생성
	public String createToken(String sub, String tokenName) {
		Date now = new Date();
		long exp = this.tokenValidTime;
		if (tokenName.equals("Refresh-Token")) {
			exp*=8;
		}
		Claims claims = Jwts.claims().setSubject(sub); // JWT payload에 저장되는 정보단위
		claims.put("token", tokenName); // 정보는 key, value쌍으로 저장된다.
//		claims.put("mem_email", Mem.getMem_email());
//		claims.put("mem_name", Mem.getMem_name());
//		claims.put("mem_phone", Mem.getMem_phone());
		return Jwts.builder()
				.setClaims(claims) //정보 저장
				.setIssuedAt(now) // 토큰 발행 시간 정보
				.setExpiration(new Date(now.getTime()+exp)) // set Expire Time
				.signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과
				// signature에 들어갈 secret 값 세팅
				.compact(); // 명령문을 닫아주는 명령어
	}
	
	// JWT 토큰에서 인증 정보 조회
	public Authentication getAuthorization(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	//토큰에서 회원 정보 추출
	private String getUserPk(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().getSubject();
	}

	//Request의 Header에서 token 값을 가져옵니다. "X-Auth-TOKEN" : "TOKEN값"
	public String resolveToken(HttpServletRequest request) {
		String token = null;
		Cookie cookie = WebUtils.getCookie(request, "X-Auth-TOKEN");
		if(cookie != null) token = cookie.getValue();
		return token;
	}
	
	//토큰의 유효성 + 만료일자 확인
	public boolean validateToken(String jwtToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
			return !claims.getBody().getExpiration().before(new Date());
		} catch(Exception e) {
			return false;
		}
	}
}
