package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.entity.EUser;

public interface UEntityRepository extends JpaRepository<EUser, Integer> {

	@Query("SELECT accStatus FROM EUser WHERE email = :email")
	public String getStatusByEmail(@Param("email") String email);

	@Query("SELECT password FROM EUser WHERE email = :email")
	public String getPasswordByEmail(@Param("email") String email);

	@Query("FROM EUser WHERE email = :email")
    public EUser getUserByEmail(@Param("email") String email);

}
