package com.example.demo.DTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.log4j.Logger;

@Table
public class Employee {
	
    private final Logger logger = Logger.getLogger(this.getClass());
	
	@Id
	private int id;
    @Column(name = "name")
	private String name;
    @Column(name = "last_name")
	private String lastName;
	
	private ArrayList<Short> position;
	
    @Column(name = "base_salary")
	private long baseSalary;
    @Column(name = "work_hours")
	private long maxWorkHours;
    @Column(name = "extra_hours")
	private long extraHours;
		
	public Employee(int id, String name, String lastName) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		
		this.position = new ArrayList<Short>();
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
		if (this.position.indexOf(position) < 0)
			this.position.add(position);
	}
	
	public void setPositions(ArrayList<Short> positions) {
		this.position = positions;
	}
	
	public void removePosition(short position) {
		if (this.position.indexOf(position) >= 0)
			this.position.remove(this.position.indexOf(position));
	}
	
	public String getInfo() throws ClassNotFoundException, SQLException {
		 //logger.info("Employee "+this.name+" "+this.lastName+" works for "+this.maxWorkHours+" for a salary of "+this.baseSalary);
		return "Employee "+this.name+" "+this.lastName+" with id: "+this.id+" works for "+this.maxWorkHours+" hours and has a salary of "+this.getFinalSalary();
	}
	
	public long getFinalSalary() throws SQLException, ClassNotFoundException {

		long promotionEarnings = 0;
		
		Class.forName ("org.h2.Driver");
		Connection conn = DriverManager.getConnection ("jdbc:h2:~/test", "sa",""); 
		
		Statement st = conn.createStatement(); 
		ResultSet rs = st.executeQuery("SELECT FROM EMPLOYEE "
				+ "WHERE ID = "+this.id); 
		while (rs.next()) {
			this.baseSalary = rs.getLong(1);
		}
		
		rs = st.executeQuery("SELECT * FROM PROMOTIONS "
				+ "WHERE EMPLOYEE_ID = "+this.id); 
		while (rs.next()) {
			promotionEarnings += rs.getFloat("money") * rs.getInt("position");	// promotion number * position
		}
		long y = (long) ((this.baseSalary / 30 * 8) * 1.7);
			
		conn.close();
		return this.baseSalary + promotionEarnings + this.extraHours * y;
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
