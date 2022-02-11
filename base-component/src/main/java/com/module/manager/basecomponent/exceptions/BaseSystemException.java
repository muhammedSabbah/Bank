package com.module.manager.basecomponent.exceptions;

import java.text.MessageFormat;

import com.module.manager.basecomponent.constant.SeverityType;
import com.module.manager.basecomponent.constant.StatusCode;

public class BaseSystemException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final int GENERAL_ERROR_CODE = 99999;
	private static final String UNKNOWN_ERROR = "Unknown Error Occured";
	
	private int errorCode;
	private SeverityType severityType;
	private Object[] parameters;
	
	@Deprecated
	public BaseSystemException(String message, int errorCode, String severity, Throwable reason) {
		super(message, reason);
		setErrorCode(errorCode);
		if (severity != null) {
			setSeverityType(SeverityType.fromValue(severity));
		}
		if (reason != null) {
			setStackTrace(reason.getStackTrace());
		}
	}
	
	public BaseSystemException(String message, StatusCode statusCode) {
		this(message, statusCode.getCode(), statusCode.getSeverityType().value(), null);
	}
	
	public BaseSystemException(StatusCode statusCode) {
		this(statusCode, null, null);
	}
	
	public BaseSystemException(StatusCode statusCode, Throwable reason) {
		this(statusCode, reason, null);
	}

	public BaseSystemException(StatusCode statusCode, Object[] parameters) {
		this(statusCode, null, parameters);
	}
	
	public BaseSystemException(StatusCode statusCode, Throwable reason, Object[] parameters) {
		super(buildMessage(statusCode, reason, parameters));
		if (statusCode != null) {
			this.setErrorCode(statusCode.getCode());
			this.setSeverityType(statusCode.getSeverityType());
		} else {
			this.setErrorCode(GENERAL_ERROR_CODE);
			this.setSeverityType(SeverityType.FATAL);
		}
		setParameters(parameters);
	}
	
	private static String buildMessage(StatusCode statusCode, Throwable reason, Object[] parameters) {
		if (statusCode == null) {
			return buildMessage(SeverityType.FATAL, GENERAL_ERROR_CODE, UNKNOWN_ERROR, reason, parameters);
		}
		return buildMessage(statusCode.getSeverityType(), statusCode.getCode(), statusCode.getDescription(), reason,
				parameters);
	}
	
	private static String buildMessage(SeverityType severityType, int code, String description, Throwable reason, Object[] parameters) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(severityType);
		stringBuffer.append(":");
		stringBuffer.append(code);
		stringBuffer.append(":");
		if (description != null && parameters != null && parameters.length > 0) {
			final MessageFormat form = new MessageFormat(description);
			stringBuffer.append(form.format(parameters));
		} else {
			stringBuffer.append(description);
		}
		if (reason != null) {
			stringBuffer.append(":");
			stringBuffer.append(reason.getMessage());
		}
		return stringBuffer.toString();
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public SeverityType getSeverityType() {
		return severityType;
	}

	public void setSeverityType(SeverityType severityType) {
		this.severityType = severityType;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
	
}
