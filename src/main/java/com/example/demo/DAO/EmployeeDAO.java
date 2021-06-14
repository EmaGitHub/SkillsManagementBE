package com.example.demo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.example.demo.DTO.Employee;

@Repository
public class EmployeeDAO {
	
    private final Logger logger = Logger.getLogger(this.getClass());
    private Connection conn;
    
    private String connString = "jdbc:mysql://localhost:3306/demo?user=root&password=root";
    
    public EmployeeDAO() throws SQLException {
    	}
	
	public void hireEmployee(int id, String name, String lastName, int position, float base_salary) throws SQLException, ClassNotFoundException {
		
		Employee newEmp = new Employee(id, name, lastName);
		
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = conn.createStatement(); 
		st.execute("INSERT INTO EMPLOYEE "
				+ "(ID, NAME, LAST_NAME, POSITION, BASE_SALARY) "
				+ "VALUES ("
				+ ""+id+", "
				+ "'"+name+"', "
				+ "'"+lastName+"', "
				+ ""+position+", "
						+ ""+base_salary+");"); 
		conn.close();
	}
	
	public void dismissEmployee(int id) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement(); 
		st.execute("DELETE FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		conn.close();
	}
	
	public ArrayList<Employee> getAllEmployees()  throws SQLException, ClassNotFoundException {
		
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement(); 
		ResultSet rs = st.executeQuery("SELECT * FROM EMPLOYEE"); 
		
		while (rs.next()) {
			Employee emp = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("last_name"));
			emp.setMaxWorkHours(rs.getLong("work_hours"));
			emp.setBaseSalary(rs.getLong("base_salary"));
			emp.setExtraHours(rs.getLong("extra_hours"));
			
			String positions = rs.getString("position");
			ArrayList<Short> posList = convertStringPositionToList(positions);
			emp.setPositions(posList);
			
			employees.add(emp);
		}
		conn.close();
		
		return employees;
	}
	
	public String getAllEmployeesInfo()  throws SQLException, ClassNotFoundException {	
		
		String employeesInfo = "";
		
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement(); 
		ResultSet rs = st.executeQuery("SELECT * FROM EMPLOYEE"); 
		
		while (rs.next()) {
			Employee emp = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("last_name"));
			emp.setMaxWorkHours(rs.getLong("work_hours"));
			emp.setBaseSalary(rs.getLong("base_salary"));
			emp.setExtraHours(rs.getLong("extra_hours"));
			
			String positions = rs.getString("position");
			ArrayList<Short> posList = convertStringPositionToList(positions);
			emp.setPositions(posList);
			
			employeesInfo += "\n"+emp.getInfo();
		}
		conn.close();
		return employeesInfo;
	}
	
	public void applySalaryToEmployee(int id, long baseSalary) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement(); 
		st.executeUpdate("UPDATE EMPLOYEE "
				+ "SET base_salary = "+baseSalary+" "
				+ "WHERE ID = "+id); 
		conn.close();
	}
	
	public void applyWorkHourToEmployee(int id, long maxWorkHours) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = conn.createStatement(); 
		st.executeUpdate("UPDATE EMPLOYEE "
				+ "SET work_hours = "+maxWorkHours+" "
				+ "WHERE ID = "+id); 
		conn.close();
	}

	public void applyPromotionToEmployee(int employeeId, float money, short position) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement();
		st.execute("INSERT INTO PROMOTION "
				+ "(EMPLOYEE_ID, POSITION, MONEY) "
				+ "VALUES ("
				+ ""+employeeId+", "
				+ ""+position+", "
				+ ""+money); 
		conn.close();
	}
	
	public ArrayList<Short> getEmployeePromotions(int id) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM PROMOTION "
				+ "WHERE EMPLOYEE_ID = "+id); 
		conn.close();
		
		String positionAsString = "";
		
		if (rs.next())
			positionAsString = rs.getString(1);
		
		return convertStringPositionToList(positionAsString);
	}
	
	public ArrayList<Short> getEmployeePositions(int id) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT POSITION FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		conn.close();
		
		String positionAsString = "";
		
		if (rs.next())
			positionAsString = rs.getString(1);
		
		return convertStringPositionToList(positionAsString);
	}
	
	public void addEmployeePosition(int id, short position) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT POSITION FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		
		String positionAsString = "";
		
		if (rs.next()) {
			positionAsString = rs.getString(1);
			positionAsString += ","+position;

			int rs2 = st.executeUpdate("UPDATE EMPLOYEE "
					+ "SET POSITION = " + positionAsString
					+ "WHERE ID = "+id); 
			conn.close();
		}
		else 
			logger.error("Position not found");
	}
	
	public ArrayList<Short> convertStringPositionToList(String positionAsString) {
		ArrayList<Short> list = new ArrayList<Short>();
		String[] arr = positionAsString.split(",");
		if (arr != null)
			for (String s: arr)
				list.add(Short.valueOf(s));
		else
			list.add(Short.valueOf(positionAsString));
		return list;
	}
	
}
