package com.devjeans.hype.member.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.java.Log;

/**
 * 스프링 시큐리티 LoginSuccess 핸들러
 * @author 임원정
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	임원정        최초 생성
 * </pre>
 */
@Log
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {@Override
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		log.warning("Login Success");
		
		List<String> roleNames = new ArrayList<>();
		auth.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		log.warning("ROLE NAMES: " + roleNames);
		
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/admin");
			return;
		}
		
		if(roleNames.contains("ROLE_MEMBER")) {
			response.sendRedirect("/loginSuccess");
			return;
		}
		
		response.sendRedirect("/");
	}
}
