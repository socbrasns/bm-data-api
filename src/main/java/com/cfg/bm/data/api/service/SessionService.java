package com.cfg.bm.data.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cfg.bm.data.api.model.Session;
import com.cfg.bm.data.api.repository.SessionRepository;
import com.cfg.bm.data.api.request.SearchRequest;
import com.cfg.bm.data.api.specification.SearchSpecification;

@Service
public class SessionService {
    
    private SessionRepository sessionRepository;

    public Page<Session> findAll(Pageable pageable) {
	return sessionRepository.findAll(pageable);
    }

    public Page<Session> search(SearchRequest searchRequest) {
	return sessionRepository.findAll(new SearchSpecification<Session>(searchRequest), searchRequest.getPageable());
    }

    public Session findById(Long id) {
	return sessionRepository.findById(id).orElseThrow();
    }

    public Session save(Session session) {
	return sessionRepository.save(session);
    }

    public void delete(Long id) {// logical
	var toDelete = findById(id);
	toDelete.setEnabled(false);
	sessionRepository.save(toDelete);
    }
}
