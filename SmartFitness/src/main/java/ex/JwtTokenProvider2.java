//package ex;
//
//import java.util.Base64;
//import java.util.Date;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//
//import com.smartfitness.demo.model.MembersDetail;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
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
//			Claims claims = Jwts.claims().setSubject(Mem.getMem_id()); //JWT payload에 저장되는 정보단위
//			//claim은 pay
//			claims.put("token", tokenName); 
//			
//		}
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
