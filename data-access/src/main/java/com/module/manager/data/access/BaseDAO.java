package com.module.manager.data.access;

import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockTimeoutException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import com.module.manager.basecomponent.BaseComponent;
import com.module.manager.basecomponent.constant.StatusCode;
import com.module.manager.basecomponent.exceptions.BaseSystemException;

public class BaseDAO extends BaseComponent{
	protected static EntityManagerFactory factory;
	protected EntityManager entityManager;
	
	static {
		factory = Persistence.createEntityManagerFactory( "DAL_ENTITIES" );
	}

	public EntityManager getEntityManager() {
		if(entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}
	
	protected <T> T excuteSingleResultNamedQuery(String queryName, Map<String, Object> parameters, Class<T> entityClass) throws BaseSystemException{
		TypedQuery<T> query = prepatedNamedQuery(queryName, parameters, entityClass);
		T result = excuteSingleResultQuery(query);
		return result;
	}
	
	private <T> TypedQuery<T> prepatedNamedQuery(String queryName, Map<String, Object> parameters, Class<T> entityClass) throws BaseSystemException {
		TypedQuery<T> query = getNamedQuery(queryName, entityClass);
		setParameters(query, parameters);
		return query;
	}
	
	private <T> TypedQuery<T> getNamedQuery(String queryName, Class<T> entityClass) throws BaseSystemException {
		TypedQuery<T> createNamedQuery;
		try {
			createNamedQuery = getEntityManager().createNamedQuery(queryName, entityClass);
		} catch (IllegalArgumentException e) {
			throw new BaseSystemException(StatusCode.QUERY_NAME_NOT_FOUND, e);
		}
		return createNamedQuery;
	}
	
	private <T> void setParameters(TypedQuery<T> query, Map<String, Object> parameters) {
		if(parameters != null) {
			Set<String> paramKeySet =  parameters.keySet();
			for(String key : paramKeySet) {
				query.setParameter(key, parameters.get(key));
			}
		}
	}
	
	private <T> T excuteSingleResultQuery(TypedQuery<T> query) throws BaseSystemException {
		T result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {

			getLogger().config("No Result Found Query : " + query.toString());
		} catch (IllegalStateException e) {

			throw new BaseSystemException(StatusCode.INTERNAL_SYSTEM_ERROR, e);
		} catch (QueryTimeoutException e) {

			throw new BaseSystemException(StatusCode.INTERNAL_SYSTEM_ERROR, e);
		} catch (TransactionRequiredException e) {

			throw new BaseSystemException(StatusCode.INTERNAL_SYSTEM_ERROR, e);
		} catch (PessimisticLockException e) {

			throw new BaseSystemException(StatusCode.INTERNAL_SYSTEM_ERROR, e);
		} catch (LockTimeoutException e) {

			throw new BaseSystemException(StatusCode.INTERNAL_SYSTEM_ERROR, e);
		} catch (PersistenceException e) {
			
			throw new BaseSystemException(StatusCode.INTERNAL_SYSTEM_ERROR, e);
		}
		return result;
	}
}
