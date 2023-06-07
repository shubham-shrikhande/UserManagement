package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.UserCountry;

public interface CountryRepository extends JpaRepository<UserCountry, Integer> {


}
