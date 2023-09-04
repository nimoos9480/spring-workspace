package com.kh.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		List<String> roleList = new ArrayList();
		
		// 사용자인증에 대한 권한을 가져오는 메소드
		authentication.getAuthorities().forEach(auth -> {
			System.out.println("auth : " + auth); // 권한 그자체를 가져옴(ROLE_MEMBER, ROLE_ADMIN)		
			
			roleList.add(auth.getAuthority());
		}); 
		
		if(roleList.contains("ROLE_MEMBER")) {
			response.sendRedirect("/member");
			return;
		} 
		
		if(roleList.contains("ROLE_ADMIN")) {
			response.sendRedirect("/admin");
			return;
		}
		
		response.sendRedirect("/all");
		
	}

}
