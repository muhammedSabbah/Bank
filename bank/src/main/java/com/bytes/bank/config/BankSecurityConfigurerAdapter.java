package com.bytes.bank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class BankSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {


	@Value("${app.url.base.contact}")
	private String CONTACT;
	@Value("${app.url.base.notices}")
	private String NOTICES;
	@Value("${app.url.base.myAccount}")
	private String ACCOUNT;
	@Value("${app.url.base.myBalance}")
	private String BALANCE;
	@Value("${app.url.base.myCards}")
	private String CARDS;
	
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("bank").password("123456").authorities("admin")
		.and().withUser("mohamed").password("456").authorities("read")
		.and().passwordEncoder(NoOpPasswordEncoder.getInstance());
	}




	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Authenticate all requests - Basic Authentication 
		/*
		http.authorizeHttpRequests().anyRequest().authenticated()
		.and()
		.formLogin()
		.and()
		.httpBasic();
		*/
		
		// Custom authentication for some requests
		
		http.authorizeHttpRequests()
		.antMatchers(ACCOUNT).authenticated()
		.antMatchers(BALANCE).authenticated()
		.antMatchers(CARDS).authenticated()
		.antMatchers(NOTICES).permitAll()
		.antMatchers(CONTACT).permitAll()
		.and()
		.formLogin()
		.and()
		.httpBasic();
		
		
		// Deny any request
		//  "status": 403
	    // "error"  : "Forbidden"
		/*
		http.authorizeHttpRequests().anyRequest().denyAll()
		.and()
		.formLogin()
		.and()
		.httpBasic();
		*/
		
		// Permit all Requests
		/*
		http.authorizeHttpRequests().anyRequest().permitAll()
		.and()
		.formLogin()
		.and()
		.httpBasic();
		*/
	}

}
