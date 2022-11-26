package com.cfg.bm.data.api.model.enums.converters;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;

import com.cfg.bm.data.api.model.enums.LoginUserRole;

public class LoginUserRoleConverter implements AttributeConverter<LoginUserRole, String> {

    @Override
    public String convertToDatabaseColumn(LoginUserRole loginUserRole) {
	if (loginUserRole == null) {
	    return null;
	}
	return loginUserRole.getRole();
    }

    @Override
    public LoginUserRole convertToEntityAttribute(String role) {
	if (role == null) {
	    return null;
	}

	return Stream.of(LoginUserRole.values()).filter(r -> r.getRole().equals(role)).findFirst()
		.orElseThrow(IllegalArgumentException::new);
    }
}
