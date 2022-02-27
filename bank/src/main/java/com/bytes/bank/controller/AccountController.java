package com.bytes.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.module.manager.basecomponent.BaseComponent;
import com.module.manager.basecomponent.exceptions.BaseSystemException;
import com.module.manager.data.access.entities.Account;
import com.module.manager.data.access.view.AccountFacadeLocal;

@RestController
public class AccountController extends BaseComponent{

	@Autowired
	AccountFacadeLocal accountFacadeLocal;
	
	@GetMapping("${app.url.base.myAccount}")
	public String getMyAccount(@PathVariable String accountName) {
		getLogger().info("MY ACCOUNT");
		Account account = null;
		try {
			account = accountFacadeLocal.loadUserByUserName(accountName);
		} catch (BaseSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getLogger().info("Account - " + account.toString());
		return "MY ACCOUNT";
	}
	
	@PostMapping("${app.url.base.newAccount}")
	public ResponseEntity<Account> createNewAccount(@RequestBody Account account) {
		Account result = null;
		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(account.getPassword());
			account.setPassword(encodedPassword);
			result = accountFacadeLocal.createNewAccount(account);
		} catch (BaseSystemException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}
}
