package com.module.manager.logging;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LogRecordManager extends LogRecord {
	private static final String DELIMETER = "|";
	private static final String TERMINATOR = ">>";

	private String serviceName;

	public LogRecordManager(Level level, String msg) {
		this(level, msg, null, null);
	}

	public LogRecordManager(Level level, String msg, Object[] params) {
		this(level, msg, params, null);
	}

	public LogRecordManager(Level level, String msg, Object[] params, Throwable thrown) {
		super(level, msg);
		setParameters(params);
		setThrown(thrown);
	}

	@Override
	public String getMessage() {
		StringBuilder buffer = new StringBuilder();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");

		buffer.append(dateFormat.format(new Date()));
		buffer.append(DELIMETER);
		buffer.append(super.getLevel());
		buffer.append(TERMINATOR);

		if (serviceName != null) {
			buffer.append(serviceName);
			buffer.append(DELIMETER);
		}

		if (getSourceClassName() != null) {
			buffer.append(getSourceClassName());
			buffer.append(DELIMETER);
		}

		if (getSourceMethodName() != null) {
			buffer.append(getSourceMethodName());
			buffer.append(DELIMETER);
		}

		String message = super.getMessage();
		if (message != null && message.length() > 0) {
			Object[] parameters = getParameters();
			if (parameters != null) {
				MessageFormat form = new MessageFormat(message);
				message = form.format(parameters);
			}
			buffer.append(message);
		} else {
			buffer.append("UNDEFINIED_MESSAGE");
		}

		buffer.append(DELIMETER);

		if (getThrown() != null) {
			buffer.append(getThrown().getMessage());
		}

		buffer.append(TERMINATOR);

		return buffer.toString();

	}

}
