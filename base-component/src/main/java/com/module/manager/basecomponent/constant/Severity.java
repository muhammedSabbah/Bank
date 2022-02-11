package com.module.manager.basecomponent.constant;

public enum Severity {
	FATAL("FATAL"),
	ERROR("ERROR"),
	WARNING("WARNING"),
	SUCCESS("SUCCESS"),
	INFO("INFO");
	
	private String value;
	
	Severity(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
	public static Severity fromValue(String value) {
		for(Severity c : Severity.values()) {
			if(c.value.equals(value)) {
				return c;
			}
		}
		throw new IllegalArgumentException(value);
	}
}
