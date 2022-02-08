package com.bytes.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

	@GetMapping("${app.url.base.myBalance}")
	public String getMyBalance() {
		return "MY BALANCE";
	}
}
