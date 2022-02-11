package com.bytes.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.module.manager.basecomponent.BaseComponent;

@RestController
public class AccountController extends BaseComponent{

	@GetMapping("${app.url.base.myAccount}")
	public String getMyAccount() {
		getLogger().info("MY ACCOUNT");
		return "MY ACCOUNT";
	}
	
}
