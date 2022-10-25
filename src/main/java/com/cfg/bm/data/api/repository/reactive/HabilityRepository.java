package com.cfg.bm.data.api.repository.reactive;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Component;

import com.cfg.bm.data.api.model.Hability;

@Component
public interface HabilityRepository extends ReactiveSortingRepository<Hability, Long> {


}
