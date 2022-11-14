package com.cfg.bm.data.api.controller.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import com.cfg.bm.data.api.controller.api.FormController;
import com.cfg.bm.data.api.model.Form;
import com.cfg.bm.data.api.service.FormService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class FormControllerImpl implements FormController {

    private final FormService service;

    @Override
    public Page<Form> findAll(Pageable pageable) {
	return service.findAll(pageable);
    }

    @Override
    public Form findById(@Valid Long id) {
	return service.findById(id);
    }

    @Override
    public Form save(@Valid Form form) {
	return service.save(form);
    }

    @Override
    public Void deleteById(@Valid Long id) {
	service.delete(id);
	return null;
    }

}
