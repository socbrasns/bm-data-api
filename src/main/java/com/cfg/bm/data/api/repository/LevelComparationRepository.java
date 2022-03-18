package com.cfg.bm.data.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cfg.bm.data.api.model.subhability.extrainfo.LevelComparation;

@Repository
public interface LevelComparationRepository extends PagingAndSortingRepository<LevelComparation, Long> {

}
