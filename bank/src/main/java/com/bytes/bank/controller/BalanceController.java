package com.bytes.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

	@GetMapping("${app.url.base.myBalance}")
	public String getMyBalance() {
		return "MY BALANCE";
	}
	
	@PostMapping("${app.url.base.newBalance}")
	public ResponseEntity<String> createNewBalance(@RequestBody String balance) {
		return ResponseEntity.ok(balance);
	}
}
