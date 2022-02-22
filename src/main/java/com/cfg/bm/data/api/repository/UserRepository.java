package com.cfg.bm.data.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cfg.bm.data.api.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {


}
