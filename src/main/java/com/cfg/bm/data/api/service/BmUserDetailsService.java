package com.cfg.bm.data.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cfg.bm.data.api.model.security.User;
import com.cfg.bm.data.api.repository.UserRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class BmUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return Optional.ofNullable(userRepository.findByUsername(username))
			.orElseThrow(() -> { return new UsernameNotFoundException(username); });

	}
	
	public Flux<User> readAll(){
		return Flux.fromIterable(userRepository.findAll());
	}

	public Mono<User> readById(Long id){
		return Mono.just(userRepository.findById(id).orElseThrow());
	}
	
	public Mono<User> save(User user){
		return Mono.just(userRepository.save(user));
	}
	
	public Mono<Void> delete(Long id) {
		return Mono.just(id).doOnNext(userRepository::deleteById).then();
	}
}
