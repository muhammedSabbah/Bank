package com.module.manager.data.access.view;

import com.module.manager.basecomponent.exceptions.BaseSystemException;
import com.module.manager.data.access.entities.Account;

public interface AccountFacadeLocal extends AbstractFacadeLocal<Account>{
	
	public Account createNewAccount(Object newAccount) throws BaseSystemException;
	
	public Account loadUserByUserName(String username) throws BaseSystemException;

}
