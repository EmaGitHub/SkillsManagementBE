package com.example.demo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.demo.DTO.EmployeeDTO;

@Repository
public class EmployeeDAO {
	
    private final Logger logger = Logger.getLogger(this.getClass());
    private Connection conn;
    
    private String connString = "jdbc:mysql://localhost:3306/demo?user=root&password=root";
    
    public EmployeeDAO() throws SQLException {
    	}
	
	public void hireEmployee(int id, String name, String lastName, int position, float base_salary) throws SQLException, ClassNotFoundException {
		
		EmployeeDTO newEmp = new EmployeeDTO(id, name, lastName);
		
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
	
	public ArrayList<EmployeeDTO> getAllEmployees()  throws SQLException, ClassNotFoundException {
		
		ArrayList<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();
		
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement(); 
		ResultSet rs = st.executeQuery("SELECT * FROM EMPLOYEE"); 
		
		while (rs.next()) {
			EmployeeDTO emp = new EmployeeDTO(rs.getInt("id"), rs.getString("name"), rs.getString("last_name"));
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
	
	public ArrayList<EmployeeDTO> getAllEmployeesInfo()  throws SQLException, ClassNotFoundException {	
		
		ArrayList<EmployeeDTO> employeesList = new ArrayList<EmployeeDTO>();
		
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement(); 
		ResultSet rs = st.executeQuery("SELECT * FROM EMPLOYEE"); 
		
		while (rs.next()) {
			EmployeeDTO emp = new EmployeeDTO(rs.getInt("id"), rs.getString("name"), rs.getString("last_name"));
			emp.setMaxWorkHours(rs.getLong("work_hours"));
			emp.setBaseSalary(rs.getLong("base_salary"));
			emp.setExtraHours(rs.getLong("extra_hours"));
			String positions = rs.getString("positions");
			ArrayList<Short> posList = convertStringPositionToList(positions);
			emp.setPositions(posList);
			emp.setFinalSalary(this.getFinalSalary(emp.getId()));
			
			employeesList.add(emp);
		}
		conn.close();
		return employeesList;
	}
	
	public EmployeeDTO getEmployeeInfo(int id) throws SQLException, ClassNotFoundException {
		EmployeeDTO emp = null;
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement(); 
		ResultSet rs = st.executeQuery("SELECT * FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		while (rs.next()) {
			emp = new EmployeeDTO(rs.getInt("id"), rs.getString("name"), rs.getString("last_name"));
			emp.setMaxWorkHours(rs.getLong("work_hours"));
			emp.setBaseSalary(rs.getLong("base_salary"));
			emp.setExtraHours(rs.getLong("extra_hours"));
			
			String positions = rs.getString("positions");
			ArrayList<Short> posList = convertStringPositionToList(positions);
			emp.setPositions(posList);
			emp.setFinalSalary(this.getFinalSalary(emp.getId()));
		}
		
		return emp;
	}
	
	public void applySalaryToEmployee(int id, float baseSalary) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
		Statement st = this.conn.createStatement(); 
		st.executeUpdate("UPDATE EMPLOYEE "
				+ "SET base_salary = "+baseSalary+" "
				+ "WHERE ID = "+id); 
		conn.close();
	}
	
	public void applyWorkHourToEmployee(int id, float maxWorkHours) throws SQLException, ClassNotFoundException {
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
				+ ""+money+")"); 
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
		ResultSet rs = st.executeQuery("SELECT POSITIONS FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		
		String positionAsString = "";
		
		if (rs.next()) {
			positionAsString = rs.getString(1);
			positionAsString += ","+position;

			int rs2 = st.executeUpdate("UPDATE EMPLOYEE "
					+ "SET POSITIONS = '" + positionAsString + "'"
					+ "WHERE ID = "+id); 
			conn.close();
		}
		else 
			logger.error("Positions not found");
	}
	
	public float getFinalSalary(int id) throws SQLException, ClassNotFoundException {

		long promotionEarnings = 0;
		
		float baseSalary = 0;
		float extraHours = 0;
		float workHours = 0;
		
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
    	
		Statement st = this.conn.createStatement();
		ResultSet rs;
		rs = st.executeQuery("SELECT BASE_SALARY FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		while (rs.next()) {
			baseSalary = rs.getFloat(1);
		}
		
		rs = st.executeQuery("SELECT EXTRA_HOURS FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		while (rs.next()) {
			extraHours += rs.getFloat(1);
		}
		
		rs = st.executeQuery("SELECT WORK_HOURS FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		while (rs.next()) {
			workHours += rs.getFloat(1);
		}
		
		rs = st.executeQuery("SELECT * FROM PROMOTION "
				+ "WHERE EMPLOYEE_ID = "+id); 
		while (rs.next()) {
			promotionEarnings += rs.getFloat("money");	// promotion number * position
		}
		float y = (float) ((baseSalary / 30 * workHours) * 1.7);
			
		conn.close();
		return (baseSalary + promotionEarnings + extraHours * y);
	}
	
	public void changeEmployeePosition(int id, short position) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection(this.connString);
    	ResultSet rs;
    	
    	int promotionNumber = 0;
    	float baseSalary = 0;
    	
		Statement st = this.conn.createStatement();
		st.executeUpdate("UPDATE EMPLOYEE SET "
				+ "PRINCIPAL_POSITION = "+position+" "
				+ "WHERE ID = "+id);
		
		rs = st.executeQuery("SELECT count(*) FROM PROMOTION "
				+ "WHERE EMPLOYEE_ID = "+id); 
		while (rs.next()) {
			promotionNumber += rs.getInt(1);
		}
		
		rs = st.executeQuery("SELECT BASE_SALARY FROM EMPLOYEE "
				+ "WHERE ID = "+id); 
		while (rs.next()) {
			baseSalary += rs.getFloat(1);
		}
		
		float newSalary;
		newSalary = baseSalary + promotionNumber * position;
		st.executeUpdate("UPDATE EMPLOYEE "
				+ "SET BASE_SALARY = "+newSalary+" "
				+ "where ID = "+id);
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
