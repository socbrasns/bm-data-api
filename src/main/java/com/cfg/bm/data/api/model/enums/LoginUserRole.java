package com.cfg.bm.data.api.model.enums;

public enum LoginUserRole {
    ADMIN("ADMIN"), GUEST("GUEST");

    private String role;

    private LoginUserRole(String role) {
	this.role = role;
    }
    
    public String getRole() {
	return this.role;
    }

}
