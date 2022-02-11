package com.module.manager.data.access;

import java.util.Map;

import com.module.manager.basecomponent.exceptions.BaseSystemException;
import com.module.manager.data.access.view.AbstractFacadeLocal;

public class AbstractFacade<T> extends BaseDAO implements AbstractFacadeLocal<T>{

	private Class<T> entityClass;
	
	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public T excuteSingleResultNamedQuery(String queryName, Map<String, Object> parameters) throws BaseSystemException {
		return super.excuteSingleResultNamedQuery(queryName, parameters, entityClass);
	}
	
}
