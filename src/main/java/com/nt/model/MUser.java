package com.nt.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;

import lombok.Data;

@Data
public class MUser {

	private String fname;

	private String lname;
	
	private String email;

	private long phno;

	private LocalDate dob;

	private String gender;

	private Integer countryId;

	private Integer stateId;

	private Integer cityId;

}
