package com.example.demo.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Promotion")
public class PromotionDTO {

	@Id
	private int id;
	@Column
	private int employeeId;	
	@Column
	private short position;
	@Column
	private float money;
}
