package com.bytes.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {
	
	@GetMapping("${app.url.base.myCards}")
	public String getMyCards() {
		return "MY CARDS";
	}
}
