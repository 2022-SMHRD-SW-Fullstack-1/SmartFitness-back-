package com.smartfitness.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.smartfitness.demo.config.jwt.JwtAuthenticationFilter;
import com.smartfitness.demo.config.jwt.JwtTokenProvider;
import com.smartfitness.demo.model.MembersDetail;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private CorsConfig corsConfig;
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder(); // 회원가입 시 비밀번호 암호화
	}
	
	//암호화에 필요한 PasswordEncoder를 Bean 등록한다.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	// authenticationManager를 Bean 등록한다
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
				.addFilter(corsConfig.corsFilter())
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 역시 사용하지 않습니다.
				.and()
				.formLogin().disable()
				.httpBasic().disable()
				.authorizeHttpRequests() //요청에 대한 사용권한 체크
//				.antMatchers("/admin/**").hasRole("ROLE_A") //A만 접근 가능
//				.antMatchers("/mypage/**").hasAnyRole("ROLE_M","ROLE_A") // M, A중 하나만 있으면 접근가능
//				.antMatchers("/add/**").hasRole("A") or authority
				.anyRequest().permitAll() //그외 나머지 요청은 누구나 접근 가능
				.and()
				.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
				// JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다.
		http
				.logout()
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
				.deleteCookies("X-Auth-Token","X-Auth-Token");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.inMemoryAuthentication()
			.withUser("ADMIN")
			.password(passwordEncoder().encode("ADMIN"))
			.roles("A")
			.accountExpired(true)
			.and()
			.withUser("MEMBER")
			.password(passwordEncoder().encode("MEMBER"))
			.roles("M");
	}

}
