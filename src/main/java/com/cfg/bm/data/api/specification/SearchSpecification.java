package com.cfg.bm.data.api.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.cfg.bm.data.api.request.FilterRequest;
import com.cfg.bm.data.api.request.SearchRequest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SearchSpecification<T> implements Specification<T> {

    private static final long serialVersionUID = -9153865343320750644L;

    private final transient SearchRequest request;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);

	for (FilterRequest filter : this.request.getFilters()) {
	    log.info("Filter: {} {} {}", filter.getKey(), filter.getOperator().toString(), filter.getValue());
	    predicate = filter.getOperator().build(root, cb, filter, predicate);
	}

	return predicate;
    }
}
