package com.bytes.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.module.manager.basecomponent.exceptions.BaseSystemException;
import com.module.manager.data.access.entities.Account;
import com.module.manager.data.access.view.AccountFacadeLocal;

@Service
public class DBUserDetailsManager implements UserDetailsService{

	@Autowired
	AccountFacadeLocal accountFacadeLocal;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = null;
		try {
			account = accountFacadeLocal.loadUserByUserName(username);
		} catch (BaseSystemException e) {
			throw new UsernameNotFoundException(username);
		}
		
		if(account == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return User.withUsername(account.getUsername()).password(account.getPassword()).authorities(account.getAuthorities()).build();
	}

}
