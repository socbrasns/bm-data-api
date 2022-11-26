package com.cfg.bm.data.api.repository;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cfg.bm.data.api.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByUsername(String username);

    @Component
    @Profile("dev")
    public class UserCreator {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;

	@PostConstruct
	public void init() {
	    User u = new User();
	    u.setUsername("test@test.com");
	    u.setPassword(encoder.encode("test"));
	    Optional.of(u).map(User::getName).map(userRepository::findByUsername).filter(Optional::isEmpty)
		    .ifPresent(o -> userRepository.save(u));

	}
    }
}
