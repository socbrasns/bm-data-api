package com.cfg.bm.data.api.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cfg.bm.data.api.model.Form;

@Repository
public interface FormRepository extends PagingAndSortingRepository<Form, Long> {

    @Component
    @Profile("dev")
    public class FormCreator {
	@Autowired
	private FormRepository formRepository;

	@PostConstruct
	public void init() {
	    Form f = new Form();
	    f.setName("WFO - Well Formed Objectives");
	    f.setVersion("v1.0-test");
	    formRepository.save(f);

	}
    }
}
