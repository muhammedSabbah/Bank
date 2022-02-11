package com.module.manager.basecomponent.constant;

public enum StatusCode {
	SUCCESS(200, "SUCCESS", Severity.SUCCESS),
	FAILURE(201, "FAILURE", Severity.ERROR),
	ERROR_SAVING(504, "ERROR SAVING THE OBJECT.", Severity.ERROR),
	INVALID_OBJECT(1001, "{0}", Severity.ERROR),
	INALID_DB_ACTION(1002, "{0}", Severity.ERROR),
	INTERNAL_SYSTEM_ERROR(99999, "Internal System Error", Severity.FATAL),
	ENTITY_ALREADY_EXIST(99925, "Entity already exist in DB", Severity.FATAL),
	INVALID_ENTITY(99924, "Non Entity passed to ORM.", Severity.FATAL),
	TRANSACTION_MISSING(99999, "Query {0} Requires Transaction while There is none.", Severity.ERROR),
	QUERY_NAME_NOT_FOUND(99999, "Query {0} not found.", Severity.ERROR);
	
	private int code;
	private String description;
	private Severity severity;
	
	
	private StatusCode(int code, String description, Severity severity) {
		this.code = code;
		this.description = description;
		this.severity = severity;
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
	
	public Severity getSeverity() {
		return severity;
	}


	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

}
