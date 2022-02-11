package com.module.manager.basecomponent;

import com.module.manager.logging.LoggerManager;

public class BaseComponent {
	private LoggerManager logger;

	public LoggerManager getLogger() {
		if (logger == null) {
			logger = new LoggerManager(getClass().getName());
		}
		return logger;
	}
}
