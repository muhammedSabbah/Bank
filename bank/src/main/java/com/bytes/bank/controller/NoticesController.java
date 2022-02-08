package com.bytes.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

	@GetMapping("${app.url.base.notices}")
	public String getNotices() {
		return "NoticesController";
	}
}
