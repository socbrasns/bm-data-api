package com.cfg.bm.data.api.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cfg.bm.data.api.repository.PercentageRepository;

@Service
@Primary
public interface PercentageService extends PercentageRepository {

}
