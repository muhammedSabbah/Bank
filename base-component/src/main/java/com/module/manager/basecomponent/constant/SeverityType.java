package com.module.manager.basecomponent.constant;

public enum SeverityType {
	FATAL("FATAL"),
	ERROR("ERROR"),
	WARNING("WARNING"),
	SUCCESS("SUCCESS"),
	INFO("INFO");
	
	private String value;
	
	SeverityType(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
	public static SeverityType fromValue(String value) {
		for(SeverityType c : SeverityType.values()) {
			if(c.value.equals(value)) {
				return c;
			}
		}
		throw new IllegalArgumentException(value);
	}
}
