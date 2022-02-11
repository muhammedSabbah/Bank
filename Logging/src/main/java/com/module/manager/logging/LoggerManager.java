package com.module.manager.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerManager {

	private static LoggerManager logger;
	protected String loggerName;

	public static LoggerManager getLogger(final String name) {
		if (logger == null) {
			logger = new LoggerManager(name);
		}
		return logger;
	}

	public LoggerManager(String name) {
		loggerName = name;
	}

	public void log(Level level, String msg) {
		log(level, msg, null, null);
	}

	public void log(Level level, String message, Throwable thrown, Object[] params) {

	}

	public String getCallingMethod() {
		String methodName = null;
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		if (stackTraceElements != null) {
			int stackLength = stackTraceElements.length;
			for (int index = 0; index < stackLength; index++) {
				if (true == stackTraceElements[index].getClassName().equals(loggerName)) {
					String name = stackTraceElements[index].toString();
					methodName = name.substring(loggerName.length() + 1);
				}
			}
		}
		return methodName;
	}

	public boolean isLoggable(Level level) {
		return Logger.getLogger(loggerName).isLoggable(level);
	}

	public void all(final String msg) {
		log(Level.ALL, msg);
	}

	public void warning(final String msg) {
		log(Level.WARNING, msg);
	}

	public void warning(final String msg, Throwable thrown) {
		log(Level.WARNING, msg, thrown, null);
	}

	public void info(final String msg) {
		log(Level.INFO, msg);
	}

	public void severe(final String msg) {
		log(Level.SEVERE, msg);
	}

	public void severe(final String msg, Object[] params) {
		log(Level.SEVERE, msg, null, params);
	}

	public void severe(final String msg, Throwable thrown, Object[] params) {
		log(Level.SEVERE, msg, thrown, params);
	}

	public void config(final String msg) {
		log(Level.CONFIG, msg);
	}

	public void fine(final String msg) {
		log(Level.FINE, msg);
	}

	public void finer(final String msg) {
		log(Level.FINER, msg);
	}

	public void entering(Object[] params) {
		entering(loggerName, getCallingMethod(), params);
	}
	
	public void entering(String sourceClass, String sourceMethod) {
		entering(sourceClass, sourceMethod, null);
	}

	public void entering(String sourceClass, String sourceMethod, Object param) {
		entering(sourceClass, sourceMethod, new Object[] { param });
	}

	public void entering(String sourceClass, String sourceMethod, Object[] params) {
		Logger.getLogger(loggerName).entering(sourceClass, sourceMethod, params);
	}

	public void exiting(Object result) {
		if (isLoggable(Level.FINER)) {
			exiting(loggerName, getCallingMethod(), result);
		}
	}

	public void exiting(String sourceClass, String sourceMethod) {
		exiting(sourceClass, sourceMethod, null);
	}

	public void exiting(String sourceClass, String sourceMethod, Object result) {
		Logger.getLogger(loggerName).exiting(sourceClass, sourceMethod, result);
	}

	public void throwing(Throwable thrown) {
		throwing(loggerName, getCallingMethod(), thrown);
	}

	public void throwing(String sourceClass, String sourceMethod, Throwable thrown) {
		Logger.getLogger(loggerName).throwing(sourceClass, sourceMethod, thrown);
	}
	
	public void addHandler(Level handlerLevel, Level loggerLevel) {
		Handler handler = new ConsoleHandler();
		handler.setLevel(handlerLevel);
		Logger.getLogger(loggerName).addHandler(handler);
		Logger.getLogger(loggerName).setLevel(loggerLevel);
		Logger.getLogger(loggerName).setUseParentHandlers(false);
	}

}
