package com.bytes.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.module.manager.data.access.AccountFacade;
import com.module.manager.data.access.view.AccountFacadeLocal;

@Configuration
public class Config {

	@Bean
	public AccountFacadeLocal accountFacade() {
		return new AccountFacade();
	}
}
