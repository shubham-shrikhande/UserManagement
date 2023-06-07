package com.nt.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table
public class EUser {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer userId;
	@Column
	private String fname;
	@Column
	private String lname;
	@Column(unique = true)
	private String email;
	@Column
	private String password;
	@Column
	private long phno;
	@Column
	private LocalDate dob;

	@Column
	private String gender;
	@Column
	private Integer countryId;
	@Column
	private Integer stateId;
	@Column
	private Integer cityId;
	@Column
	private String accStatus;

}
