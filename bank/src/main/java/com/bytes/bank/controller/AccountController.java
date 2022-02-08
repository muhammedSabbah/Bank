package com.bytes.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

	@GetMapping("${app.url.base.myAccount}")
	public String getMyAccount() {
		return "MY ACCOUNT";
	}
	
}
