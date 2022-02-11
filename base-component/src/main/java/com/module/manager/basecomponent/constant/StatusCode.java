package com.module.manager.basecomponent.constant;

public enum StatusCode {
	SUCCESS(200, "SUCCESS", SeverityType.SUCCESS),
	FAILURE(201, "FAILURE", SeverityType.ERROR),
	ERROR_SAVING(504, "ERROR SAVING THE OBJECT.", SeverityType.ERROR),
	INVALID_OBJECT(1001, "{0}", SeverityType.ERROR),
	INALID_DB_ACTION(1002, "{0}", SeverityType.ERROR),
	INTERNAL_SYSTEM_ERROR(99999, "Internal System Error", SeverityType.FATAL),
	ENTITY_ALREADY_EXIST(99925, "Entity already exist in DB", SeverityType.FATAL),
	INVALID_ENTITY(99924, "Non Entity passed to ORM.", SeverityType.FATAL),
	TRANSACTION_MISSING(99999, "Query {0} Requires Transaction while There is none.", SeverityType.ERROR),
	QUERY_NAME_NOT_FOUND(99999, "Query {0} not found.", SeverityType.ERROR);
	
	private int code;
	private String description;
	private SeverityType severityType;
	
	
	private StatusCode(int code, String description, SeverityType severityType) {
		this.code = code;
		this.description = description;
		this.severityType = severityType;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public SeverityType getSeverityType() {
		return severityType;
	}


	public void setSeverityType(SeverityType severityType) {
		this.severityType = severityType;
	}

}
