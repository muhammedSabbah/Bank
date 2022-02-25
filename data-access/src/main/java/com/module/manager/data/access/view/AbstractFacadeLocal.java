package com.module.manager.data.access.view;

import com.module.manager.basecomponent.exceptions.BaseSystemException;

public interface AbstractFacadeLocal<T> {

	T create(T entity) throws BaseSystemException;
}
