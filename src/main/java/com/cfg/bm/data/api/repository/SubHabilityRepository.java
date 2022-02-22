package com.cfg.bm.data.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cfg.bm.data.api.model.subhability.SubHability;

@Repository
public interface SubHabilityRepository extends PagingAndSortingRepository<SubHability, Long> {

}
