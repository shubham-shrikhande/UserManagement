package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.entity.UserState;



public interface StateRepository extends JpaRepository<UserState, Integer> {

	@Query("FROM UserState WHERE countryId = :countryId")
    public List<UserState> findStatesByCountryId(@Param("countryId") Integer countryId);
}
