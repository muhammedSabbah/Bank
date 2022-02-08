package com.bytes.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
	
	@GetMapping("${app.url.base.contact}")
	public String getContact() {
		return "ContactController";
	}
	
}
