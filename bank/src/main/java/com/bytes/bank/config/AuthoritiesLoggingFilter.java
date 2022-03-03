package com.bytes.bank.config;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthoritiesLoggingFilter implements Filter {
	
	private static final Logger logger = Logger.getLogger(AuthoritiesLoggingFilter.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			logger.info("USER : " + auth.getName() + " and authorities : " + auth.getAuthorities().toString());
		}
		chain.doFilter(request, response);
	}

}
