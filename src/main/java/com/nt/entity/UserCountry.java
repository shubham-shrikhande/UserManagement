package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class UserCountry {

	@Id
	private Integer countryId;
	@Column
	private String countryName;
}
