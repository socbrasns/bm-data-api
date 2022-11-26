package com.cfg.bm.data.api.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cfg.bm.data.api.model.Login;

@Repository
public interface LoginRepository extends PagingAndSortingRepository<Login, Long>, JpaSpecificationExecutor<Login> {

}
