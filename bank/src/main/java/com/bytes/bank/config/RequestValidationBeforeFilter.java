package com.bytes.bank.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class RequestValidationBeforeFilter implements Filter{

	private static final String AUTHENTICATION_SCHEME_BASIC = "Basic";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String header = req.getHeader(AUTHORIZATION);
		if(header != null) {
			header = header.trim();
			if(StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC)) {
				byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
				System.out.println("encoded =  " + base64Token);
				byte[] decoded;
				try {
					decoded = Base64.getDecoder().decode(base64Token);
					String token = new String(decoded);
					int delim = token.indexOf(":");
					String user = token.substring(0, delim);
					if(user.toLowerCase().contains(".com")) {
						res.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
						return;
					}
				} catch (IllegalArgumentException e) {
					throw new BadCredentialsException("Failed to Decode the authentication token");
				}
			}
		}
		chain.doFilter(request, response);
	}

}
