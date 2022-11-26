package com.cfg.bm.data.api.request;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.cfg.bm.data.api.model.enums.FieldType;
import com.cfg.bm.data.api.model.enums.Operator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SearchRequest implements Serializable {

    private static final long serialVersionUID = 8514625832019794838L;

    @Builder.Default
    private Collection<FilterRequest> filters = new CopyOnWriteArrayList<FilterRequest>();

    @Builder.Default
    @Setter(onParam_ = { @Autowired, @PageableDefault(page = 0, size = 10) })
    private Pageable pageable = PageRequest.of(0, 10);

    private boolean onlyEnabled;

    public Collection<FilterRequest> getFilters() {
	if (onlyEnabled)
	    this.filters.add(FilterRequest.builder().key("enabled").fieldType(FieldType.BOOLEAN)
		    .operator(Operator.EQUAL).value(Boolean.TRUE).build());

	return this.filters;
    }

}
