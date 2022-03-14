package com.cfg.bm.data.api.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cfg.bm.data.api.model.Hability;
import com.cfg.bm.data.api.model.HabilityLevelMap;

@Repository
public interface HabilityLevelMapRepository extends PagingAndSortingRepository<HabilityLevelMap, Long> {

	long deleteByHability(Hability hability);
	List<HabilityLevelMap> findByHability(Hability hability);
}
