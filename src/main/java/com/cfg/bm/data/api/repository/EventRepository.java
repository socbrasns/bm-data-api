package com.cfg.bm.data.api.repository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cfg.bm.data.api.model.Event;
import com.cfg.bm.data.api.model.enums.FieldType;
import com.cfg.bm.data.api.model.enums.Operator;
import com.cfg.bm.data.api.request.FilterRequest;
import com.cfg.bm.data.api.request.SearchRequest;
import com.cfg.bm.data.api.specification.SearchSpecification;

import lombok.AllArgsConstructor;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long>, JpaSpecificationExecutor<Event> {

    Optional<Event> findByName(String name);

    @Component
    @Profile("dev")
    @AllArgsConstructor(onConstructor_ = { @Autowired })
    public class EventCreator {

	private EventRepository eventRepository;

	@PostConstruct
	public void init() {
	    var e = Event.builder().name("Local Session").date(Calendar.getInstance()).location("Test Location")
		    .build();
	    var filter = FilterRequest.builder().key("name").operator(Operator.EQUAL).fieldType(FieldType.STRING)
		    .value(e.getName()).build();
	    var req = SearchRequest.builder().filters(List.of(filter)).build();
	    var spec = new SearchSpecification<Event>(req);

	    if (eventRepository.findAll(spec, req.getPageable()).getTotalElements() == 0)
		eventRepository.save(e);

	}
    }
}
