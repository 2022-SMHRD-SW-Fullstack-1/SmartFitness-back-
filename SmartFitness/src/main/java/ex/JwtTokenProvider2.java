//package ex;
//
//import java.util.Base64;
//import java.util.Date;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.util.WebUtils;
//
//import com.smartfitness.demo.model.MembersDetail;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//@Component
//public class JwtTokenProvider2 {
//
//		private String secretKey = "webfirewood";
//		//secretKey는 토큰 유효성 확인시 사용하기에 외부에 노출되지 않게 해야한다.
//		
//		//토큰 유효시간 지정 30분
//		private long tokenValidTime = 30*60*1000L;
//		private final UserDetailsService userDetailsService;
//		
//		//객체 초기화, secret키를 Base64로 encoding한다.
//		@PostConstruct
//		protected void init(){
//			secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//		}
//		
//		//JWT 토큰 생성,Token은 String으로 구성되기 때문에 반환 타입이 String이다.
//		public String createToken(MembersDetail Mem, String tokenName) {
//			Date now = new Date();
//			long exp = this.tokenValidTime;
//			if(tokenName.equals("Refresh-Token")) {
//				exp*=8;
//			}
//			Claims claims = Jwts.claims().setSubject(Mem.getMem_id()); //JWT payload에 저장되는 정보단위
//			//claim은 payload 부분에 들어갈 정보 조각들이다.
//			// claim은 Registered claim/ Public claim / Private claim으로 나눠진다.
//			claims.put("token", tokenName); 
//			return Jwts.builder()
//					.setClaims(claims)//정보 저장
//					.setIssuedAt(now) //토큰 발행 시간 정보
//					.setExpiration(new Date(now.getTime()+exp)) //set Expire Time
//					.signWith(SignatureAlgorithm.HS256, secretKey) //사용할 암호화 알고리즘과
//					//signature에 들어갈 secret 값 세팅
//					.compact(); // 명령문을 닫아주는 명령어	
//		}
//		
//		//JWT 토큰에서 인증 정보 조회
//		public Authentication getAuthorization(String token) {
//			UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
//			return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
//		}
//		
//		//토큰에서 회원 정보 추출
//		private String getUserPk(String token) {
//			return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().getSubject();
//		}
//		
//		//Request의 Header에서 token 값을 가져온다. "X-Auth-Token" : "TOKEN값"
//		public String resolveToken(HttpServletRequest request) {
//			String token = null;
//			Cookie cookie = WebUtils.getCookie(request, "X-Auth-TOKEN");
//			if(cookie != null)  token = cookie.getValue();
//			return token;
//		}
//		
//		//토큰의 유효성 + 만료일자 확인
//		public boolean validateToken(String jwtToken) {
//			try {
//				Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
//				return ! claims.getBody().getExpiration().before(new Date());
//			}catch(Exception e) {
//				return false;
//			}
//		}
//		
//		
//		
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
