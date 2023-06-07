package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table
@Entity
public class UserCity {

	@Id	
	private Integer cityId;
	@Column
	private String cityName;
	@Column
	private Integer stateId;
}
