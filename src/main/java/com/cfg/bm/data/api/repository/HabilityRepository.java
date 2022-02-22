package com.cfg.bm.data.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cfg.bm.data.api.model.Hability;

@Repository
public interface HabilityRepository extends PagingAndSortingRepository<Hability, Long> {


}
