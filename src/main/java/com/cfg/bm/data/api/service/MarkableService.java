package com.cfg.bm.data.api.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cfg.bm.data.api.repository.MarkableRepository;

@Service
@Primary
public interface MarkableService extends MarkableRepository {

}
