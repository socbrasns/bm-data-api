package com.cfg.bm.data.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cfg.bm.data.api.model.subhability.SubHability;
import com.cfg.bm.data.api.model.subhability.extrainfo.LevelComparation;
import com.cfg.bm.data.api.model.subhability.extrainfo.Markable;

@Repository
public interface MarkableRepository extends PagingAndSortingRepository<Markable, Long> {

}
