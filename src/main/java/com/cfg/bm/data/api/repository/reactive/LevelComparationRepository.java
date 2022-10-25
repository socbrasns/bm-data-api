package com.cfg.bm.data.api.repository.reactive;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Component;

import com.cfg.bm.data.api.model.subhability.extrainfo.LevelComparation;

@Component
public interface LevelComparationRepository extends ReactiveSortingRepository<LevelComparation, Long> {

}
