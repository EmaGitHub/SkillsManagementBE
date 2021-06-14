package com.example.demo.DTO;

import javax.persistence.Column;
import javax.persistence.Table;

@Table
public class Promotion {

    @Column(name = "employee_id")	
	private int employeeId;	
    @Column(name = "position")	
	private short position;
    @Column(name = "money")	
	private float money;
}
