package com.bytes.bank.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Component;

import com.module.manager.basecomponent.exceptions.BaseSystemException;
import com.module.manager.data.access.entities.Account;
import com.module.manager.data.access.view.AccountFacadeLocal;

@Component
public class BankAuthenticationProvider implements AuthenticationProvider {

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	@Autowired
	AccountFacadeLocal accountFacadeLocal;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("START AUTH");
		String username = determineUsername(authentication);
		UserDetails user;
		try {
			user = retrieveUser(username);
		} catch (UsernameNotFoundException exception) {
			System.out.println(exception.getMessage());
			System.out.println("Failed to find user '" + username + "'");
			throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
		try {
			additionalAuthenticationChecks(user, (UsernamePasswordAuthenticationToken) authentication);
		} catch (AuthenticationException e) {
			System.out.println(e.getMessage());
			throw new BadCredentialsException("INALID PASSWORD");
		}
		
		return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
	}

	private String determineUsername(Authentication authentication) {
		return (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
	}

	protected final UserDetails retrieveUser(String username) throws AuthenticationException {
		Account account = null;
		try {
			account = accountFacadeLocal.loadUserByUserName(username);
		} catch (BaseSystemException e) {
			throw new InternalAuthenticationServiceException(username);
		} catch (Exception ex) {
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
		}
		if (account == null) {
			throw new UsernameNotFoundException("UserDetailsService returned null, which is an interface contract violation");
		}
		return User.withUsername(account.getUsername()).password(account.getPassword()).authorities(account.getAuthorities()).build();
	}
	
	protected void additionalAuthenticationChecks(UserDetails userDetails,UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if(authentication.getCredentials() == null) {
			throw new BadCredentialsException(this.messages.getMessage("BankAuthenticationProvider.badCredentials", "EMPTY Bad credentials"));
		}
		String presentedPassword = authentication.getCredentials().toString();
		if(!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
			throw new BadCredentialsException(this.messages.getMessage("BankAuthenticationProvider.badCredentials", "INALID PASSWORD Bad credentials"));
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
