package com.example.demo.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		String role  = authentication.getAuthorities().iterator().next().getAuthority();
		System.out.println(role);
		
		if(role.equals("ROLE_ADMIN"))
			response.sendRedirect("/admin/home");
		else if(role.equals("ROLE_USER"))
			response.sendRedirect("/user/home");
		else if(role.equals("ROLE_MANGER"))
			response.sendRedirect("/manager/home");
		else
			response.sendRedirect("/ceo/home");
	}

}
