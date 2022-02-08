package com.bytes.bank.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	static List<UserDetails> userDetails;
	static {
		userDetails = new ArrayList<UserDetails>();
	}

	{
		userDetails.add(User.withUsername("bank").password("123456").authorities("admin").build());
		userDetails.add(User.withUsername("mohamed").password("123").authorities("read").build());
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("SABBAH - UserDetailsServiceImpl - loadUserByUsername");
		System.out.println("user : " + username);
		for(UserDetails user : userDetails) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

}
