package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.entity.UserCity;

public interface CityRepository extends JpaRepository<UserCity, Integer> {

	@Query("FROM UserCity WHERE stateId = :stateId")
	public List<UserCity> findCitiesByStateId(@Param("stateId") Integer stateId);
}
