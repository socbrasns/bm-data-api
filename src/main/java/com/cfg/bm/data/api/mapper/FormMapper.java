package com.cfg.bm.data.api.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.cfg.bm.data.api.model.Form;

@Mapper(componentModel = "spring")
public interface FormMapper {

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	void mapIgnoreNullsAndId(Form form, @MappingTarget Form model);

}
