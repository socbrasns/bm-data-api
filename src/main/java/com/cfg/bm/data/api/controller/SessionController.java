package com.cfg.bm.data.api.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfg.bm.data.api.model.Session;
import com.cfg.bm.data.api.model.User;
import com.cfg.bm.data.api.model.elements.KeyWord;
import com.cfg.bm.data.api.repository.SessionRepository;
import com.cfg.bm.data.api.repository.UserRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sessions")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class SessionController {

	private final SessionRepository sessionRepository;
	private final UserRepository userRepository;

	@PostMapping("/create")
	public Mono<Session> create(@RequestBody User benchmarker) {
		Session session = new Session();
		userRepository.findById(benchmarker.getId()).ifPresent((c) -> {
			session.setBenchmarker(benchmarker);
		});
		return Mono.just(sessionRepository.save(session)).log();

	}

	@PostMapping("/{id}/setMetaCoach")
	public Mono<Session> setMetaCoach(@RequestBody User metaCoach, @PathVariable Long sessionId) {
		return Mono.just(sessionRepository.findById(sessionId).map((s) -> {
			userRepository.findById(metaCoach.getId()).ifPresent((c) -> {
				s.setMetaCoach(metaCoach);
			});
			return s;
		}).map(sessionRepository::save).orElseThrow(EntityNotFoundException::new));
	}

	@PostMapping("/{id}/setCoachee")
	public Mono<Session> setCoachee(@RequestBody User coachee, @PathVariable Long sessionId) {
		return Mono.just(sessionRepository.findById(sessionId).map((s) -> {
			userRepository.findById(coachee.getId()).ifPresent((c) -> {
				s.setCoachee(c);
			});
			return s;
		}).map(sessionRepository::save).orElseThrow(EntityNotFoundException::new));
	}

	@PostMapping("/{id}/addMetaPerson")
	public Mono<Session> addMetaPerson(@RequestBody User metaPerson, @PathVariable Long sessionId) {
		return Mono.just(sessionRepository.findById(sessionId).map((s) -> {
			userRepository.findById(metaPerson.getId()).ifPresent((mp) -> {
				if (!s.getMetaPerson().contains(mp))
					s.getMetaPerson().add(mp);
			});

			return s;
		}).map(sessionRepository::save).orElseThrow(EntityNotFoundException::new));
	}

	@DeleteMapping("/{id}/removeMetaPerson/{metaPersonId}")
	public Mono<Session> removeMetaPerson(@PathVariable Long metaPersonId, @PathVariable Long sessionId,
			@Autowired UserRepository userRepository) {
		return Mono.just(sessionRepository.findById(sessionId).map((s) -> {
			userRepository.findById(metaPersonId).ifPresent((mp) -> {
				if (s.getMetaPerson().contains(mp))
					s.getMetaPerson().remove(mp);
			});
			return s;
		}).map(sessionRepository::save).orElseThrow(EntityNotFoundException::new));
	}
	
	@PostMapping("/{id}/addKeyWord")
	public Mono<Session> addKeyWord(@RequestBody KeyWord keyWord, @PathVariable Long sessionId){
		return Mono.just(sessionRepository.findById(sessionId).map((s) -> {
			if(!s.getKeyWords().contains(keyWord))
				s.getKeyWords().add(keyWord);
			return s;
		}).map(sessionRepository::save).orElseThrow(EntityNotFoundException::new));
	}

}
