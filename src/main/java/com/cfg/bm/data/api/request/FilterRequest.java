package com.cfg.bm.data.api.request;

import java.io.Serializable;
import java.util.Collection;

import com.cfg.bm.data.api.model.enums.FieldType;
import com.cfg.bm.data.api.model.enums.Operator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilterRequest implements Serializable {

    private static final long serialVersionUID = 6293344849078612450L;

    private String key;

    @Builder.Default
    private Operator operator = Operator.EQUAL;

    @Builder.Default
    private FieldType fieldType = FieldType.STRING;

    private transient Object value;

    private transient Object valueTo;

    private transient Collection<Object> values;

}
