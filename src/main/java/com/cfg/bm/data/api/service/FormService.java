package com.cfg.bm.data.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cfg.bm.data.api.model.Form;
import com.cfg.bm.data.api.repository.FormRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class FormService {

    FormRepository formRepository;

    public Page<Form> findAll(Pageable pageable) {
	return formRepository.findAll(pageable);
    }

    public Form findById(Long id) {
	return formRepository.findById(id).orElseThrow();
    }

    public Form save(Form form) {
	return formRepository.save(form);
    }

    public void delete(Long id) {// logical
	var toDelete = findById(id);
	toDelete.setEnabled(false);
	formRepository.save(toDelete);
    }

}
