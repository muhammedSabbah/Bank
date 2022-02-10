package com.bytes.bank.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl extends InMemoryUserDetailsManager {
	
	public UserDetailsServiceImpl() {
		createUser(User.withUsername("bank").password("123456").authorities("admin").build());
		createUser(User.withUsername("mohamed").password("123").authorities("read").build());
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("SABBAH - UserDetailsServiceImpl - loadUserByUsername");
		System.out.println("user : " + username);
		return super.loadUserByUsername(username);
	}

}
