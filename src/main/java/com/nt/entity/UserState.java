package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class UserState {

	@Id
	private Integer stateId;
	@Column
	private String stateName;
	@Column
	private Integer countryId;
}
