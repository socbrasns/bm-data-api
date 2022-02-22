package com.cfg.bm.data.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cfg.bm.data.api.model.Session;

@Repository
public interface SessionRepository extends PagingAndSortingRepository<Session, Long> {

}
