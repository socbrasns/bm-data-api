package com.cfg.bm.data.api.model.enums.converters;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.cfg.bm.data.api.model.enums.SessionUserRole;

@Converter(autoApply = true)
public class SessionUserRoleConverter implements AttributeConverter<SessionUserRole, String> {

    @Override
    public String convertToDatabaseColumn(SessionUserRole sessionUserRole) {
	if (sessionUserRole == null) {
	    return null;
	}
	return sessionUserRole.getRole();
    }

    @Override
    public SessionUserRole convertToEntityAttribute(String role) {
	if (role == null) {
	    return null;
	}

	return Stream.of(SessionUserRole.values()).filter(r -> r.getRole().equals(role)).findFirst()
		.orElseThrow(IllegalArgumentException::new);
    }
}
