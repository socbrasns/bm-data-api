package com.cfg.bm.data.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cfg.bm.data.api.model.Hability;
import com.cfg.bm.data.api.repository.HabilityRepository;
import com.cfg.bm.data.api.request.SearchRequest;
import com.cfg.bm.data.api.specification.SearchSpecification;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class HabilityService {

    private HabilityRepository habilityRepository;

    public Page<Hability> findAll(Pageable pageable) {
	return habilityRepository.findAll(pageable);
    }

    public Page<Hability> search(SearchRequest searchRequest) {
	return habilityRepository.findAll(new SearchSpecification<Hability>(searchRequest),
		searchRequest.getPageable());
    }

    public Hability findById(Long id) {
	return habilityRepository.findById(id).orElseThrow();
    }

    public Hability save(Hability hability) {
	return habilityRepository.save(hability);
    }

    public void delete(Long id) {// logical
	var toDelete = findById(id);
	toDelete.setEnabled(false);
	habilityRepository.save(toDelete);
    }

}
