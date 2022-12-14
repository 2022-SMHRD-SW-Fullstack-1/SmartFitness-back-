package com.smartfitness.demo.config.cookie;

import java.util.Base64;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.SerializationUtils;

public class CookieUtils {

	/**
	 * 쿠키 가져오기
	 * **/
	public static Optional<Cookie> getCookie(HttpServletRequest request, String name){
		Cookie[] cookies = request.getCookies();
		/**
		 * cookie가 존재하지 않으면 콘솔에 노출하지 않게 해주는 Optional.of(cookie)
		 * **/
		if(cookies != null && cookies.length >0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return Optional.of(cookie);
				}
			}
		}
		return Optional.empty();
	}
	
	/**
	 *쿠키 추가하기
	 *
	 * **/
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
	/**
	 * 쿠키삭제,
	 * 값이 없는 쿠키로 만들기
	 * @return 비어있는 쿠키
	 * **/
	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length >0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
	}
	/**
	 * 암호화
	 * **/
	public static String serialize(Object object) {
		return Base64.getUrlEncoder()
				.encodeToString(SerializationUtils.serialize(object));
	}
	
	/**
	 * 암호해독
	 * **/
	public static <T> T deserialize(Cookie cookie, Class<T> cls) {
		return cls.cast(SerializationUtils.deserialize(
				Base64.getUrlDecoder().decode(cookie.getValue())));
	}
	
}
