package com.example.demo.DTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Model.BaseModel;

import lombok.Getter;
import lombok.Setter;

/**
 * classe Pojo per entità
 * @author ecalvisi
 *
 */

@Entity
@Table(name = "Employee")
public class EmployeeDTO extends BaseModel {
	
	@Getter @Setter
    @Column
	private String name;
    @Column(name="last_name")
	private String lastName;
	private ArrayList<Short> positions;
	@Column(name="base_salary")
	private float baseSalary;
	@Column(name="work_hours")
	private float maxWorkHours;
	@Column(name="extra_hours")
	private float extraHours;
    private float finalSalary;
		
	public EmployeeDTO(String name, String lastName) {
		this.name = name;
		this.lastName = lastName;
		this.positions = new ArrayList<Short>();
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

	public String getName() {
		return this.name;
	}

}
