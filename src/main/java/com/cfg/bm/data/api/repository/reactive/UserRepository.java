package com.cfg.bm.data.api.repository.reactive;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Component;

import com.cfg.bm.data.api.model.security.User;

import reactor.core.publisher.Mono;

@Component
public interface UserRepository extends ReactiveSortingRepository<User, Long> {

	Mono<User> findByUsername(String username);

}
