package com.example.demo.DTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Services.CompanyService;

@Entity
public class EmployeeDTO {
	
    private final Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private CompanyService companyService;
	
	private int id;
	private String name;
	private String lastName;
	private ArrayList<Short> positions;
	private float baseSalary;
	private float maxWorkHours;
	private float extraHours;
    private float finalSalary;
		
	public EmployeeDTO(int id, String name, String lastName) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.positions = new ArrayList<Short>();
	}
	
    public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public ArrayList<Short> getPositions() {
		return positions;
	}
	
	public float getBaseSalary() {
		return baseSalary;
	}

	public float getMaxWorkHours() {
		return maxWorkHours;
	}

	public float getExtraHours() {
		return extraHours;
	}

	public float getFinalSalary() {
		return finalSalary;
	}
	
	public void setBaseSalary(long f) {
		this.baseSalary = f;
	}
	
	public void setMaxWorkHours(long maxHours) {
		this.maxWorkHours = maxHours;
	}
	
	public void addExtraHours(long extraHours) {
		this.extraHours += extraHours;
	}
	
	public void setExtraHours(long extra) {
		this.extraHours = extra;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void addPosition(short position) {
		if (this.positions.indexOf(position) < 0)
			this.positions.add(position);
	}
	
	public void setPositions(ArrayList<Short> positions) {
		this.positions = positions;
	}
	
	public void removePosition(short position) {
		if (this.positions.indexOf(position) >= 0)
			this.positions.remove(this.positions.indexOf(position));
	}
	
	public String getInfo() throws ClassNotFoundException, SQLException {
		 //logger.info("Employee "+this.name+" "+this.lastName+" works for "+this.maxWorkHours+" for a salary of "+this.baseSalary);
		return "Employee "+this.name+" "+this.lastName+" with id: "+this.id+" works for "+this.maxWorkHours+" hours and has a salary of "+this.finalSalary;
	}
	
	public void setFinalSalary(float salary) {
		this.finalSalary = salary;
	}
	
	public ArrayList<Short> convertStringPositionToList(String positionString) {
		ArrayList<Short> list = null;
		String[] arr = positionString.split(",");
		for(String s: arr)
			list.add(Short.valueOf(s));
		return list;
	}

	public static void main(String[] args) {

		System.out.println("TEST");
	}

}
