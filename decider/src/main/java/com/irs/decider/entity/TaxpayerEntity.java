package com.irs.decider.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class TaxpayerEntity {
	
	@Id
	private String document;
	
	@Column
	private String name;
	
	@Column
	private String email;

}
