package com.module.manager.data.access;

import java.util.Map;

import javax.persistence.EntityExistsException;

import com.module.manager.basecomponent.constant.StatusCode;
import com.module.manager.basecomponent.exceptions.BaseSystemException;
import com.module.manager.data.access.view.AbstractFacadeLocal;

public class AbstractFacade<T> extends BaseDAO implements AbstractFacadeLocal<T>{

	private Class<T> entityClass;
	
	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Override
	public T create(T entity) throws BaseSystemException {
		System.out.println("START CREATE");
		try {
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(entity);
		} catch (EntityExistsException e) {
			throw new  BaseSystemException(StatusCode.ENTITY_ALREADY_EXIST, e);
		}
		try{
			getEntityManager().getTransaction().commit();

		} catch (Exception e){
		 	/* if the instance is not an entity */
			throw new BaseSystemException(StatusCode.FAILURE, e);
		}
		return entity;
	}
	
	public T excuteSingleResultNamedQuery(String queryName, Map<String, Object> parameters) throws BaseSystemException {
		return super.excuteSingleResultNamedQuery(queryName, parameters, entityClass);
	}
	
}
