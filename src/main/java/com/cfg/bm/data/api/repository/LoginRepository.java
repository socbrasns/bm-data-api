package com.cfg.bm.data.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cfg.bm.data.api.model.Login;

@Repository
public interface LoginRepository extends PagingAndSortingRepository<Login, Long> {

    @Query(value = "Select l from Login l " + "where l.logedUser = :uuid "
	    + "and l.lastLoginDate = max(l.lastLoginDate)")
    public Login findByUuidAndMaxLastLoginDate(UUID uuid);

}
