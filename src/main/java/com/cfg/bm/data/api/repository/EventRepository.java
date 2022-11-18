package com.cfg.bm.data.api.repository;

import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cfg.bm.data.api.model.Event;
import com.cfg.bm.data.api.model.Place;
import com.cfg.bm.data.api.model.enums.Country;

import lombok.AllArgsConstructor;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    @Component
    @Profile("dev")
    @AllArgsConstructor(onConstructor_ = { @Autowired })
    public class EventCreator {

	private EventRepository eventRepository;

	@PostConstruct
	public void init() {
	    eventRepository.save(new Event().name("Local Session").date(Calendar.getInstance())
		    .palce(new Place().country(Country.BR).location("Test Location")));

	}
    }
}
