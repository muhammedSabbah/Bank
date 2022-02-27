package com.bytes.bank.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.module.manager.basecomponent.exceptions.BaseSystemException;
import com.module.manager.data.access.entities.Account;
import com.module.manager.data.access.view.AccountFacadeLocal;

@Component
public class BankAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	AccountFacadeLocal accountFacadeLocal;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("START AUTH");
		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		Account account = null;
		try {
			account = accountFacadeLocal.loadUserByUserName(username);
		} catch (BaseSystemException e) {
			throw new UsernameNotFoundException(username);
		}
		if (passwordEncoder.matches(pwd, account.getPassword())) {
			List<GrantedAuthority> authorites = new ArrayList<GrantedAuthority>();
			authorites.add(new SimpleGrantedAuthority(account.getAuthorities()));
			return new UsernamePasswordAuthenticationToken(username, pwd, authorites);
		} else {
			System.out.println("Invalid Password - SABBAH");
			throw new BadCredentialsException("Invalid Password");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
