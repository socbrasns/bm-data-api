package com.cfg.bm.data.api.repository.reactive;

import java.util.List;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Component;

import com.cfg.bm.data.api.model.Hability;
import com.cfg.bm.data.api.model.HabilityLevelMap;

//@Component
public interface HabilityLevelMapRepository extends ReactiveSortingRepository<HabilityLevelMap, Long> {

	long deleteByHability(Hability hability);
	List<HabilityLevelMap> findByHability(Hability hability);
}
