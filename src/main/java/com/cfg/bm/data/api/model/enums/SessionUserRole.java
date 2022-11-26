package com.cfg.bm.data.api.model.enums;

public enum SessionUserRole {
    BENCHMARKER("BENCHMARKER"), META_COACH("META_COACH"), META_PERSON("META_PERSON"), COACHEE("COACHEE");

    private String role;

    private SessionUserRole(String role) {
	this.role = role;
    }
    
    public String getRole() {
	return this.role;
    }
}
