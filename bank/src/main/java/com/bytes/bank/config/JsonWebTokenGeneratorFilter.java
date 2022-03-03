package com.bytes.bank.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bytes.bank.config.jwt.JwtTokenUtil;

public class JsonWebTokenGeneratorFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("HERE");
		if(authentication != null) {
			System.out.println(getClass().getName());
			JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
			String token = jwtTokenUtil.generateToken(new String(authentication.getName()));
			System.out.println("Token : " + token);
			response.setHeader("JWT_HEADER", token);
		}
		filterChain.doFilter(request, response);
	}

}
