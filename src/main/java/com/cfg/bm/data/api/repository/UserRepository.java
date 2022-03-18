package com.cfg.bm.data.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cfg.bm.data.api.model.security.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByUsername(String name);

}
