package com.module.manager.basecomponent;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.module.manager.logging.LogRecordManager;
import com.module.manager.logging.LoggerManager;

public class BaseComponent {
	private LoggerManager logger;

	public LoggerManager getLogger() {
		if(logger == null) {
			logger = new LoggerManager(getClass().getName()) {
				@Override
				public void log(Level level, String message, Throwable thrown, Object[] params) {
					if(isLoggable(level)) {
						System.out.println("MESSAGE " + message);
						LogRecordManager record = new LogRecordManager(level, message, params, thrown);
						record.setSourceClassName(loggerName);
						record.setSourceMethodName(getCallingMethod());
						Logger.getLogger(loggerName).log(record);
					}
				}
				
				@Override
				public void entering(String sourceClass, String sourceMethod, Object[] params) {
					addHandler(Level.ALL, Level.FINER);
					super.entering(sourceClass, sourceMethod, params);
				}
				
				@Override
				public void exiting(String sourceClass, String sourceMethod, Object result) {
					addHandler(Level.ALL, Level.FINER);
					super.exiting(sourceClass, sourceMethod, result);
				}
				
				@Override
				public void throwing(String sourceClass, String sourceMethod, Throwable thrown) {
					log(Level.SEVERE, "Throwing Exception : ", thrown, null);
				}
				
			};
		}
		return logger;
	}
}
