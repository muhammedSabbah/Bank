package com.module.manager.data.access;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.module.manager.basecomponent.exceptions.BaseSystemException;
import com.module.manager.data.access.entities.Account;
import com.module.manager.data.access.view.AccountFacadeLocal;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(AccountFacadeLocal.class)
public class AccountFacade extends AbstractFacade<Account> implements AccountFacadeLocal {

	public AccountFacade() {
		super(Account.class);
	}

	@Override
	public Account loadUserByUserName(String username) throws BaseSystemException{
		Account account = null;
		if(username != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("username", username);
			account = excuteSingleResultNamedQuery("loadUserByUserName", params);
		}
		return account;
	}

}
